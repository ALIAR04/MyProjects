#include <stdio.h>
#include <stdbool.h>
#include <math.h>
#include <time.h>


#define WIDTH_OF_SCREEN 1650
#define HEIGHT_OF_SCREEN 950
#define BALL_RADIUS 25.0F
#define BALL_GRAVITY 0.08F

//asm.s functions
extern void straight_move_asm(float *x, float x_speed);
extern void mohadab_move_asm(float *y, float *y_speed, float gravity);
extern float sinouside_move_asm(float x, float y0);
extern float get_remainder_asm(float x);

//select type of moving
void check_key_pressed(int *type_moving, bool *is_moving) {
    while (*type_moving != 1 && *type_moving != 2 && *type_moving != 3) {
        scanf("%d", type_moving);
    }
    *is_moving = true;
}

int main() {
    // set coordinate of ball and vector of speed
    float x_position = 50;
    float y_position = HEIGHT_OF_SCREEN / 2;
    float x_speed = 10;
    float y_speed = 0;
    
    int type_moving = 0;        //1: straight  2: sinouside  3: mohadab
    bool is_moving = false;
    float y0 = y_position;      //for sinouside moving
    int times = 0;
    clock_t s, e;
    double chekc;

    while (1) {
        //getting type of move
        if (!is_moving) {
            check_key_pressed(&type_moving, &is_moving);
            s = clock();
        }

        if (is_moving) {
            //call funtion according to the type_moving
            straight_move_asm(&x_position, x_speed);
            if (type_moving == 2) {
                float temp = get_remainder_asm(x_position * 0.03);
                if (temp > 3.14) temp -= 6.28;
                else if (temp < -3.14) temp += 6.28;

                y_position = sinouside_move_asm(temp, y0);
            } else if (type_moving == 3) {
                mohadab_move_asm(&y_position, &y_speed, BALL_GRAVITY);
            }
        }

        // checking a whole cycle moving (assume a graphical program)
        if (x_position >= WIDTH_OF_SCREEN - 50 ||
        y_position >= HEIGHT_OF_SCREEN - 50) {
            x_position = 50;
            y_position = HEIGHT_OF_SCREEN / 2;
            y_speed = 0;
            times++;
        }

        // checking for times of completed moving of ball
        // if it is complete: boolean is_moving = false
        //                    print result and everything will be zero
        if (times == 1000000){
            type_moving = 0;
            is_moving = false;
            times = 0;
            e = clock();
            chekc = ((double) (e - s)) / CLOCKS_PER_SEC;
            printf("ASM version: %.6f seconds\n", chekc);
        }
    }
    
    return 0;

}