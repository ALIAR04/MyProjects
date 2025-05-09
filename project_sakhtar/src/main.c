#include "raylib.h"
#include <stdio.h>
#include <math.h>
#include <time.h>


#define WIDTH_OF_SCREEN 1650
#define HEIGHT_OF_SCREEN 950
#define BALL_RADIUS 25.0F
#define BALL_GRAVITY 0.0003F

void check_key_pressed(char text[], int *type_moving, bool *is_moving, double *strart_time) {
    if (IsKeyPressed(KEY_ONE)) {
        *type_moving = 1;
        *is_moving = true;
        // *strart_time = GetTime();
        sprintf(text, "                 Value: straight");
    }

    if (IsKeyPressed(KEY_TWO)) {
        *type_moving = 2;
        *is_moving = true;
        // *strart_time = GetTime();
        sprintf(text, "              Value: sinouside");
    }

    if (IsKeyPressed(KEY_THREE)) {
        *type_moving = 3;
        *is_moving = true;
        // *strart_time = GetTime();
        sprintf(text, "                 Value: mohadab");
    }
}

void straight_moving(Vector2 *ball_position, Vector2 *ball_velocity) {
    (*ball_position).x += (*ball_velocity).x;
}

void mohadab_moving(Vector2 *ball_position, Vector2 *ball_velocity) {
    (*ball_position).y += (*ball_velocity).y;
    (*ball_velocity).y += BALL_GRAVITY;
}

void sinouside_moving(Vector2 *ball_position, float y0) {
    float temp = remainder((*ball_position).x * 0.03 , 6.2831);
    if (temp < -3.14) temp = - (temp + 3.14);
    else if (temp > 3.14) temp = - (temp - 3.14);
    
    (*ball_position).y = y0 + 35 * sin(temp);
}

int main() {
    InitWindow(WIDTH_OF_SCREEN, HEIGHT_OF_SCREEN, "C program");       //name of window
    
    SetTargetFPS(10000);        //more: excute instructions more

    Vector2 ball_position = {50, HEIGHT_OF_SCREEN / 2};     //x: first  y: second
    Vector2 ball_velocity = {0.6, 0};       //first: speed in x   second: speed in y
    
    int type_moving = 0;        //1:straight  2:sinouside  3:mohadab
    char text[40] = "Choose 1.straight 2.sinouside 3.mohadab", times_text[20] = "0";
    char timer[30] = "00.000 s";        //showing time of program
    bool is_moving = false;             
    float y0 = ball_position.y;         //necessary for sinouside moving
    int times = 0;                      // number of whole moving on screen
    double start_time, finish_time = 0;
    clock_t s, e;       //another way of showing time
    double chekc;       //for time


    while (!WindowShouldClose()) {
        //click 1 or 2 or 3 for selecting type of moving
        if (!is_moving) {
            check_key_pressed(text, &type_moving, &is_moving, &start_time);
            s = clock();
        }
 
        //a rectangle with red border
        DrawRectangleLines(50, 50, WIDTH_OF_SCREEN - 100,
        HEIGHT_OF_SCREEN - 100, RED);
    
        if (is_moving) {
            //call correct function moving with type_moving
            start_time = GetTime();
            straight_moving(&ball_position, &ball_velocity);        //ball moves in x anyway
            if (type_moving == 2) {
                sinouside_moving(&ball_position, y0);
            } else if (type_moving == 3) {
                mohadab_moving(&ball_position, &ball_velocity);
            }
            finish_time += GetTime() - start_time;
        }

        // if ball is out of red rectangle: ball will be put at first state
        if (ball_position.x >= WIDTH_OF_SCREEN - 50 ||
        ball_position.y >= HEIGHT_OF_SCREEN - 50) {
            ball_position.x = 50;
            ball_position.y = HEIGHT_OF_SCREEN / 2;
            ball_velocity.y = 0;
            times++;        //add a whole cycle moving
            sprintf(times_text, "%d", times);
        }

        //showing time of execute
        if (times != 50 && type_moving != 0) {
            // sprintf(timer, "%.6f s", (GetTime() - start_time));
            sprintf(timer, "%.6f s", finish_time);
        }
        
        
        // checking for times of completed moving of ball
        // if it is complete: boolean is_moving = false
        //                    print result and everything will be zero
        if (times == 50){
            type_moving = 0;
            is_moving = false;
            times = 0;
            e = clock();
            chekc = ((double) (e - s)) / CLOCKS_PER_SEC;
            printf("C version: %f seconds\n", chekc);
        }
        

        //these of for making window and graphic program
        BeginDrawing();
        ClearBackground(RAYWHITE);
        DrawCircleV(ball_position, BALL_RADIUS, BLACK);
        DrawText(text, WIDTH_OF_SCREEN / 2 - 250, 15, 25, BLACK);
        DrawText(times_text, WIDTH_OF_SCREEN / 2 - 10, 100, 40, DARKBLUE);
        DrawText(timer, WIDTH_OF_SCREEN / 2 - 50, 150, 35, DARKBLUE);
        EndDrawing();
    }
    

    CloseWindow();
    return 0;
}