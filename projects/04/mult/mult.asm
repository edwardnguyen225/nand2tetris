// This file is part of www.nand2tetris.org
// and the book "The Elements of Computing Systems"
// by Nisan and Schocken, MIT Press.
// File name: projects/04/Mult.asm

// Multiplies R0 and R1 and stores the result in R2.
// (R0, R1, R2 refer to RAM[0], RAM[1], and RAM[2], respectively.)

// Put your code here.
@result
M=0
@R2
M=0
@i
M=0
@R1
D=M
@n
M=D

@isRsNegative
M=0    // isRsNegative = false

//********** check the negative condition of rs **********
    @R1
    D=M
    @PROCESS_R0_R1_NEGATIVE
    D; JGT
    // (R1 < 0)
    @n
    M=-M
    @R0
    D=M
    // if (R0 > 0) isRsNegative = 1
    @PROCESS_R0_R1_NEGATIVE
    D; JLE
    @isRsNegative
    M=1
    (PROCESS_R0_R1_NEGATIVE)
//************************************************************

//********** for loop **********
(FOR_LOOP)
    @i
    D=M
    @n
    D=M-D                   // D = R1 - i
    @END
    D; JEQ                  // if (D == 0) goto END 

    // result += R0
    @R0
    D=M
    @result
    M=M+D

    //i++
    @i
    M=M+1

    @FOR_LOOP
    0;JMP
(END)
//************************************************************

//********** check if result need to be negative **********
// isRsNegative = {-1; 1}
// isBothNegative = true = 1
// if (!isRsNegative && isBothNegative) break
// else rs = -rs
    @isRsNegative
    D=M
    @PROCESS_RS
    D; JEQ
    @result
    M=-M
    (PROCESS_RS)
//************************************************************

@result
D=M
@R2
M=D

// end program
(END_PROGRAM)
@END_PROGRAM
0;JEQ
