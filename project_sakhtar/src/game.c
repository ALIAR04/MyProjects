#include "raylib.h"
#include <stdio.h>
#include <time.h>
#include <stdlib.h>


#define WIDTH_OF_SCREEN 1650
#define HEIGHT_OF_SCREEN 950
#define BALL_RADIUS 20.0F
#define BALL_GRAVITY 0.0003F

void check_key_pressed(char text[], int *type_moving, bool *is_moving) {
    if (IsKeyPressed(KEY_ONE)) {
        *type_moving = 1;
        *is_moving = true;
        sprintf(text, "                 Value: straight");
    }

    if (IsKeyPressed(KEY_TWO)) {
        *type_moving = 2;
        *is_moving = true;
        sprintf(text, "              Value: sinouside");
    }

    if (IsKeyPressed(KEY_THREE)) {
        *type_moving = 3;
        *is_moving = true;
        sprintf(text, "                 Value: mohadab");
    }
}

void move_up_down(float *position, float speed) {
    //it shouldn't be higher or lower of y borders
    if (speed < 0) speed *= -1;     
    if (IsKeyDown(KEY_UP) && *position > 50) (*position) -= speed;
    if (IsKeyDown(KEY_DOWN) && *position < 770) (*position) += speed;
}

//functions in asm.s
extern void straight_move_asm(float *x, float x_speed);
extern void mohadab_move_asm(float *y, float *y_speed, float gravity);
extern float sinouside_move_asm(float x, float y0);
extern float get_remainder_asm(float x);

//make a random number between -0.1 and 0.1
float random_number() {
    float min = -0.1, max = 0.1, rand_num;
    do {
        rand_num = min + (float)rand() / RAND_MAX * (max - min);
    } while (rand_num < 0.1 && rand_num > -0.1);
    return rand_num;
}

int main() {
    InitWindow(WIDTH_OF_SCREEN, HEIGHT_OF_SCREEN, "PROJECT");       //name of window
    
    SetTargetFPS(10000);        //more: excute instructions more

    Vector2 ball_position = {50.001 + BALL_RADIUS, HEIGHT_OF_SCREEN / 2};     //x: first  y: second
    Vector2 ball_velocity = {0.3, random_number()};       //first: speed in x   second: speed in y
    float rec_position = HEIGHT_OF_SCREEN / 2 - 65;     //y position of rectantgle that hit the ball
    
    int type_moving = 0;        //1:straight  2:sinouside  3:mohadab
    char text[40] = "Choose 1.straight 2.sinouside 3.mohadab", times_text[20] = "0";
    char timer[30] = "00.000 s";        //showing time of program
    bool is_moving = false, restart_game = true;   
    float y0 = ball_position.y;         //necessary for sinouside moving
    int times = 0;                      // number of whole moving on screen
    bool direction = false;

    while (!WindowShouldClose()) {
        //click 1 or 2 or 3 for selecting type of moving
        if (!is_moving && restart_game) {
            check_key_pressed(text, &type_moving, &is_moving);
        } else if (!restart_game) {
            char temp_text[100];
            sprintf(temp_text, "You lost! Your score is %d.\nPlease restart a new game. Select the Enter key\n", times);
            DrawText(temp_text, WIDTH_OF_SCREEN / 2 - 300, 500, 35, BLACK);
            if (IsKeyPressed(KEY_ENTER)) {
                times = 0;
                restart_game = true;
                ball_position.x = 50.001 + BALL_RADIUS;
                ball_position.y = HEIGHT_OF_SCREEN / 2;
                rec_position = HEIGHT_OF_SCREEN / 2 - 65;
                sprintf(text, "Choose 1.straight 2.sinouside 3.mohadab");
            }
        }
        
        //a rectangle with red border
        DrawRectangleLines(50, 50, WIDTH_OF_SCREEN - 100,
        HEIGHT_OF_SCREEN - 100, RED);

        if (is_moving) {
            move_up_down(&rec_position, ball_velocity.x);          //moving left rectangle with up and down keys
            straight_move_asm(&ball_position.x, ball_velocity.x);       //ball moves in x anyway
            straight_move_asm(&ball_position.y, ball_velocity.y);       //ball moves in y anyway
            if (type_moving == 2) {
                //size of input for getting sinus should be between -pi to +pi
                //because sinus is calculated with taylor series
                float temp = get_remainder_asm(ball_position.x * 0.01);
                if (temp > 3.14) temp -= 6.28;
                else if (temp < -3.14) temp += 6.28;
                
                if (direction) {        // move down in y
                    y0 += 0.01 * (sinouside_move_asm(temp, y0) - y0);
                    ball_position.y = y0;
                } else {                // move up in y
                    y0 -= 0.01 * (sinouside_move_asm(temp, y0) - y0);
                    ball_position.y = y0;
                }
                
            } else if (type_moving == 3) {
                mohadab_move_asm(&ball_position.y, &ball_velocity.y, BALL_GRAVITY);
            }
        }
        
        // ball reflection with right border
        if (is_moving && ball_position.x + BALL_RADIUS >= WIDTH_OF_SCREEN - 50) {
            ball_velocity.x = - (ball_velocity.x + 0.005);
            sprintf(times_text, "%d", times);
        }

        // ball reflection with left border
        if (is_moving && ball_position.x - BALL_RADIUS <= 50) {
            if (ball_position.y >= rec_position - 10 && ball_position.y <= rec_position + 140) {
                ball_velocity.x = - (ball_velocity.x - 0.005);
                ball_position.x = 50.001 + BALL_RADIUS;
                times++;
            } else {    //finishing game
                is_moving = false;
                restart_game = false;
                type_moving = 0;
                ball_velocity.x = 0.3;
                ball_velocity.y = random_number();
            }
            sprintf(times_text, "%d", times);
        }

        // ball reflection with y borders
        if (is_moving && (ball_position.y + BALL_RADIUS >= HEIGHT_OF_SCREEN - 50 ||
        ball_position.y - BALL_RADIUS <= 50)) {
            if (ball_velocity.y > 0) ball_velocity.y = - (ball_velocity.y + 0.005);
            else if (ball_velocity.y < 0) ball_velocity.y = - (ball_velocity.y - 0.005);
            if (direction) direction = false;
            else direction = true;
            sprintf(times_text, "%d", times);
        }

    
        
        
        //these of for making window and graphic program
        BeginDrawing();
        ClearBackground(RAYWHITE);
        DrawCircleV(ball_position, BALL_RADIUS, BLACK);
        DrawText(text, WIDTH_OF_SCREEN / 2 - 250, 15, 25, BLACK);
        DrawText(times_text, WIDTH_OF_SCREEN / 2 - 10, 100, 40, DARKBLUE);
        DrawRectangle(40, rec_position, 10, 130, DARKBLUE);
        EndDrawing();
    }
    

    CloseWindow();
    return 0;
}