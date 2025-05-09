global straight_move_asm
global mohadab_move_asm
global sinouside_move_asm
global get_remainder_asm

section .text
straight_move_asm:
    ; load ball_position.x to xmm0:
    movss   xmm0, [rcx]
    ; xmm1 = speed, add speed to ball_position.x:
    addss   xmm0, xmm1
    ;mov ball_position.x to memory
    movss   [rcx], xmm0
    ret

mohadab_move_asm:
    movss xmm0, [rcx]       ;xmm0 = *y
    movss xmm1, [rdx]       ;xmm1 = *y_speed
    addss xmm0, xmm1        ;*y += *y_speed
    addss xmm1, xmm2        ;xmm2 = gravity
    movss [rcx], xmm0       ;load new y
    movss [rdx], xmm1       ;load new y_speed
    ret

sinouside_move_asm:
    movss xmm2, xmm0      ;xmm2 = xmm0 = x

    
    ;xmm0 = x
    movss xmm4, xmm0        ; xmm4 = x

    ;calculate x^3
    mulss xmm4, xmm0        ; xmm4 = x^2
    mulss xmm4, xmm0        ; xmm4 = x^3
    mulss xmm4, dword [rel c3] ; xmm4 = x^3/3!
    subss xmm2, xmm4        ; xmm4 = x^3/3! , xmm2 = x - x^3/3!
                            ; xmm1 = y0 , xmm0 = x 

    ;calculate x^5
    mulss xmm4, xmm0        ; xmm4 = x^4
    mulss xmm4, xmm0        ; xmm4 = x^5
    mulss xmm4, dword [rel c5] ; xmm4 = x^5/5!
    addss xmm2, xmm4        ; xmm4 = x^5/5! , xmm2 = x - x^3/3! + x^5/5!
                            ; xmm1 = y0 , xmm0 = x

    ;calculate x^7
    mulss xmm4, xmm0        ; xmm4 = x^6
    mulss xmm4, xmm0        ; xmm4 = x^7
    mulss xmm4, dword [rel c7] ; xmm4 = x^7/7!
    subss xmm2, xmm4        ; xmm4 = x^7/7! ,xmm2 = x - x^3/3! + x^5/5! - x^7/7!
                            ; xmm1 = y0 , xmm0 = x

    mulss xmm2, dword [rel amp]     ; xmm2 = 15sin(0.03x)
    addss xmm2, xmm1        ; xmm2 = y0 + 15sin(0.03x)

    movss xmm0, xmm2
    ret

get_remainder_asm:
    ; movss xmm2, dword [rel two_pi]  ; xmm2 = 2pi
    movss xmm3, xmm0                ; xmm3 = x
    divss xmm3, dword [rel two_pi]                ; xmm3 = x / 2pi              
    roundss xmm3, xmm3, 1           ; floor x / 2pi
    mulss xmm3, dword [rel two_pi]               ; xmm3 = x - floor(x / 2pi) * 2pi
    subss xmm0, xmm3                ; xmm0 = remain of x
    ret



section .rodata
    two_pi dd 6.2831853
    amp   dd 35.0
    c3    dd 0.16666667  ; 1/3!
    c5    dd 0.05        ; 1/5*4
    c7    dd 0.02380     ; 1/7*6