// This file is part of www.nand2tetris.org
// and the book "The Elements of Computing Systems"
// by Nisan and Schocken, MIT Press.
// File name: projects/04/Fill.asm

// Runs an infinite loop that listens to the keyboard input.
// When a key is pressed (any key), the program blackens the screen,
// i.e. writes "black" in every pixel;
// the screen should remain fully black as long as the key is pressed. 
// When no key is pressed, the program clears the screen, i.e. writes
// "white" in every pixel;
// the screen should remain fully clear as long as no key is pressed.

// Put your code here.
@KBD // KBD = 24576 == maxRAM
D=A
@maxIndex
M=D

(LOOP)
    @SCREEN
    D=A
    @index
    M=D

(CHECK_KEY_PRESSED)
    @KBD
    D=M
    @BLACKOUT
    D; JNE

    (CLRSCR)
        @index
        A=M
        M=0
    @CONTINUE
    0; JMP

    (BLACKOUT)
        @index
        A=M
        M=-1

    (CONTINUE)
        @index
        MD=M+1
        @maxIndex
        D=M-D
        @LOOP
        D; JEQ // if (index == 24576) goto LOOP
    @CHECK_KEY_PRESSED
    0; JMP
