@file:Suppress("DuplicatedCode")

package com.puffinsoft.k6502

import kotlin.experimental.and

class InstructionSet {
    val instructions = Array<Instruction>(256) { XXX(Implied(), 2) }.apply {
        /*
        Load Accumulator with Memory

        M -> A                                      NZCIDV
                                                    ++----
        addressing      assembler       opc bytes   cycles
        --------------------------------------------------
        immediate       LDA #oper       A9      2       2
        zeropage        LDA oper        A5      2       3
        zeropage,X      LDA oper,X      B5      2       4
        absolute        LDA oper        AD      3       4
        absolute,X      LDA oper,X      BD      3       4*
        absolute,Y      LDA oper,Y      B9      3       4*
        (indirect,X)    LDA (oper,X)    A1      2       6
        (indirect),Y    LDA (oper),Y    B1      2       5*
         */
        this[0xa9] = LDA(Immediate(), 2)
        this[0xa5] = LDA(ZeroPage(), 3)
        this[0xb5] = LDA(ZeroPageX(), 4)
        this[0xad] = LDA(Absolute(), 4)
        this[0xbd] = LDA(AbsoluteX(), 4)
        this[0xb9] = LDA(AbsoluteY(), 4)
        this[0xa1] = LDA(IndirectX(), 6)
        this[0xb1] = LDA(IndirectY(), 5)

        /*
        Load Index X with Memory

        M -> X                                      NZCIDV
                                                    ++----
        addressing      assembler       opc bytes   cycles
        --------------------------------------------------
        immediate       LDX #oper       A2      2       2
        zeropage        LDX oper        A6      2       3
        zeropage,Y      LDX oper,Y      B6      2       4
        absolute        LDX oper        AE      3       4
        absolute,Y      LDX oper,Y      BE      3       4*
         */
        this[0xa2] = LDX(Immediate(), 2)
        this[0xa6] = LDX(ZeroPage(), 3)
        this[0xb6] = LDX(ZeroPageY(), 4)
        this[0xae] = LDX(Absolute(), 4)
        this[0xbe] = LDX(AbsoluteY(), 4)

        /*
        Load Index Y with Memory

        M -> Y                                      NZCIDV
                                                    ++----
        addressing      assembler       opc bytes   cycles
        --------------------------------------------------
        immediate       LDY #oper       A0      2       2
        zeropage        LDY oper        A4      2       3
        zeropage,X      LDY oper,X      B4      2       4
        absolute        LDY oper        AC      3       4
        absolute,X      LDY oper,X      BC      3       4*
         */
        this[0xa0] = LDY(Immediate(), 2)
        this[0xa4] = LDY(ZeroPage(), 3)
        this[0xb4] = LDY(ZeroPageX(), 4)
        this[0xac] = LDY(Absolute(), 4)
        this[0xbc] = LDY(AbsoluteX(), 4)

        /*
        Store Accumulator in Memory

        A -> M                                      NZCIDV
                                                    ------
        addressing      assembler       opc bytes   cycles
        --------------------------------------------------
        zeropage        STA oper        85      2       3
        zeropage,X      STA oper,X      95      2       4
        absolute        STA oper        8D      3       4
        absolute,X      STA oper,X      9D      3       5
        absolute,Y      STA oper,Y      99      3       5
        (indirect,X)    STA (oper,X)    81      2       6
        (indirect),Y    STA (oper),Y    91      2       6
         */
        this[0x85] = STA(ZeroPage(), 3)
        this[0x95] = STA(ZeroPageX(), 4)
        this[0x8D] = STA(Absolute(), 4)
        this[0x9D] = STA(AbsoluteX(), 5)
        this[0x99] = STA(AbsoluteY(), 5)
        this[0x81] = STA(IndirectX(), 6)
        this[0x91] = STA(IndirectY(), 6)

        /*
        Store Index X in Memory

        X -> M                                      NZCIDV
                                                    ------
        addressing      assembler       opc bytes   cycles
        --------------------------------------------------
        zeropage        STX oper        86      2       3
        zeropage,Y      STX oper,Y      96      2       4
        absolute        STX oper        8E      3       4
         */
        this[0x86] = STX(ZeroPage(), 3)
        this[0x96] = STX(ZeroPageY(), 4)
        this[0x8e] = STX(Absolute(), 4)

        /*
        Store Index Y in Memory

        Y -> M                                      NZCIDV
                                                    ------
        addressing      assembler       opc bytes   cycles
        --------------------------------------------------
        zeropage        STY oper        84      2       3
        zeropage,X      STY oper,X      94      2       4
        absolute        STY oper        8C      3       4
         */
        this[0x84] = STY(ZeroPage(), 3)
        this[0x94] = STY(ZeroPageX(), 4)
        this[0x8c] = STY(Absolute(), 4)

        /*
        Transfer Accumulator to Index X

        A -> X                                      NZCIDV
                                                    ++----
        addressing      assembler       opc bytes   cycles
        --------------------------------------------------
        implied         TAX             AA      1       2
         */
        this[0xaa] = TAX(Implied(), 2)

        /*
        Transfer Accumulator to Index Y

        A -> Y                                      NZCIDV
                                                    ++----
        addressing      assembler       opc bytes   cycles
        --------------------------------------------------
        implied         TAY             A8      1       2
         */
        this[0xa8] = TAY(Implied(), 2)

        /*
        Transfer Stack Pointer to Index X

        SP -> X                                     NZCIDV
                                                    ++----
        addressing      assembler       opc bytes   cycles
        --------------------------------------------------
        implied         TSX             BA      1       2
         */
        this[0xba] = TSX(Implied(), 2)

        /*
        Transfer Index X to Accumulator

        X -> A                                      NZCIDV
                                                    ++----
        addressing      assembler       opc bytes   cycles
        --------------------------------------------------
        implied         TXA             8A      1       2
         */
        this[0x8a] = TXA(Implied(), 2)

        /*
        Transfer Index X to Stack Register

        X -> SP                                     NZCIDV
                                                    ------
        addressing      assembler       opc bytes   cycles
        --------------------------------------------------
        implied         TXS             9A      1       2
         */
        this[0x9a] = TXS(Implied(), 2)

        /*
        Transfer Index Y to Accumulator

        Y -> A                                      NZCIDV
                                                    ++----
        addressing      assembler       opc bytes   cycles
        --------------------------------------------------
        implied         TYA             98      1       2
         */
        this[0x98] = TYA(Implied(), 2)

        /*
        Push Accumulator on Stack

        push A                                      NZCIDV
                                                    ------
        addressing      assembler       opc bytes   cycles
        --------------------------------------------------
        implied         PHA             48      1       3
         */
        this[0x48] = PHA(Implied(), 3)

        /*
        Push Processor Status on Stack

        The status register will be pushed with the break
        flag and bit 5 set to 1.

        push SR                                     NZCIDV
                                                    ------
        addressing      assembler       opc bytes   cycles
        --------------------------------------------------
        implied         PHP             08      1       3
         */
        this[0x08] = PHP(Implied(), 3)

        /*
        Pull Accumulator from Stack

        pull A                                      NZCIDV
                                                    ++----
        addressing      assembler       opc bytes   cycles
        --------------------------------------------------
        implied         PLA             68      1       4
         */
        this[0x68] = PLA(Implied(), 4)

        /*
        Pull Processor Status from Stack

        The status register will be pulled with the break
        flag and bit 5 ignored.

        pull SR                                     NZCIDV
                                                from stack
        addressing      assembler       opc bytes   cycles
        --------------------------------------------------
        implied         PLP             28      1       4
         */
        this[0x28] = PLP(Implied(), 4)

        /*
        Decrement Memory by One

        M - 1 -> M                                  NZCIDV
                                                    ++----
        addressing      assembler       opc bytes   cycles
        --------------------------------------------------
        zeropage        DEC oper        C6      2       5
        zeropage,X      DEC oper,X      D6      2       6
        absolute        DEC oper        CE      3       6
        absolute,X      DEC oper,X      DE      3       7
         */
        this[0xc6] = DEC(ZeroPage(), 5)
        this[0xd6] = DEC(ZeroPageX(), 6)
        this[0xce] = DEC(Absolute(), 6)
        this[0xde] = DEC(AbsoluteX(), 7)

        /*
        Decrement Index X by One

        X - 1 -> X                                  NZCIDV
                                                    ++----
        addressing      assembler       opc bytes   cycles
        --------------------------------------------------
        implied         DEX             CA      1       2
         */
        this[0xca] = DEX(Implied(), 2)

        /*
        Decrement Index Y by One

        Y - 1 -> Y                                  NZCIDV
                                                    ++----
        addressing      assembler       opc bytes   cycles
        --------------------------------------------------
        implied         DEY             88      1       2
         */
        this[0x88] = DEY(Implied(), 2)

        /*
        Increment Memory by One

        M + 1 -> M                                  NZCIDV
                                                    ++----
        addressing      assembler       opc bytes   cycles
        --------------------------------------------------
        zeropage        INC oper        E6      2       5
        zeropage,X      INC oper,X      F6      2       6
        absolute        INC oper        EE      3       6
        absolute,X      INC oper,X      FE      3       7
         */
        this[0xe6] = INC(ZeroPage(), 5)
        this[0xf6] = INC(ZeroPageX(), 6)
        this[0xee] = INC(Absolute(), 6)
        this[0xfe] = INC(AbsoluteX(), 7)

        /*
        Increment Index X by One

        X + 1 -> X                                  NZCIDV
                                                    ++----
        addressing      assembler       opc bytes   cycles
        --------------------------------------------------
        implied         INX             E8      1       2
         */
        this[0xe8] = INX(Implied(), 2)

        /*
        Increment Index Y by One

        Y + 1 -> Y                                  NZCIDV
                                                    ++----
        addressing      assembler       opc bytes   cycles
        --------------------------------------------------
        implied         INY             C8      1       2
         */
        this[0xc8] = INY(Implied(), 2)

        /*
        Add Memory to Accumulator with Carry

        A + M + C -> A, C                           NZCIDV
                                                    +++--+
        addressing      assembler       opc bytes   cycles
        --------------------------------------------------
        immediate       ADC #oper       69      2       2
        zeropage        ADC oper        65      2       3
        zeropage,X      ADC oper,X      75      2       4
        absolute        ADC oper        6D      3       4
        absolute,X      ADC oper,X      7D      3       4*
        absolute,Y      ADC oper,Y      79      3       4*
        (indirect,X)    ADC (oper,X)    61      2       6
        (indirect),Y    ADC (oper),Y    71      2       5*
         */
        this[0x69] = ADC(Immediate(), 2)
        this[0x65] = ADC(ZeroPage(), 3)
        this[0x75] = ADC(ZeroPageX(), 4)
        this[0x6d] = ADC(Absolute(), 4)
        this[0x7d] = ADC(AbsoluteX(), 4)
        this[0x79] = ADC(AbsoluteY(), 4)
        this[0x61] = ADC(IndirectX(), 6)
        this[0x71] = ADC(IndirectY(), 5)

        /*
        Subtract Memory from Accumulator with Borrow

        A - M - C -> A                              NZCIDV
                                                    +++--+
        addressing      assembler       opc bytes   cycles
        --------------------------------------------------
        immediate       SBC #oper       E9      2       2
        zeropage        SBC oper        E5      2       3
        zeropage,X      SBC oper,X      F5      2       4
        absolute        SBC oper        ED      3       4
        absolute,X      SBC oper,X      FD      3       4*
        absolute,Y      SBC oper,Y      F9      3       4*
        (indirect,X)    SBC (oper,X)    E1      2       6
        (indirect),Y    SBC (oper),Y    F1      2       5*
         */
        this[0xe9] = SBC(Immediate(), 2)
        this[0xe5] = SBC(ZeroPage(), 3)
        this[0xf5] = SBC(ZeroPageX(), 4)
        this[0xed] = SBC(Absolute(), 4)
        this[0xfd] = SBC(AbsoluteX(), 4)
        this[0xf9] = SBC(AbsoluteY(), 4)
        this[0xe1] = SBC(IndirectX(), 6)
        this[0xf1] = SBC(IndirectY(), 5)

        /*
        AND Memory with Accumulator

        A AND M -> A                                NZCIDV
                                                    ++----
        addressing      assembler       opc bytes   cycles
        --------------------------------------------------
        immediate       AND #oper       29      2       2
        zeropage        AND oper        25      2       3
        zeropage,X      AND oper,X      35      2       4
        absolute        AND oper        2D      3       4
        absolute,X      AND oper,X      3D      3       4*
        absolute,Y      AND oper,Y      39      3       4*
        (indirect,X)    AND (oper,X)    21      2       6
        (indirect),Y    AND (oper),Y    31      2       5*
         */
        this[0x29] = AND(Immediate(), 2)
        this[0x25] = AND(ZeroPage(), 3)
        this[0x35] = AND(ZeroPageX(), 4)
        this[0x2d] = AND(Absolute(), 4)
        this[0x3d] = AND(AbsoluteX(), 4)
        this[0x39] = AND(AbsoluteY(), 4)
        this[0x21] = AND(IndirectX(), 6)
        this[0x31] = AND(IndirectY(), 5)

        /*
        Exclusive-OR Memory with Accumulator

        A EOR M -> A                                NZCIDV
                                                    ++----
        addressing      assembler       opc bytes   cycles
        --------------------------------------------------
        immediate       EOR #oper       49      2       2
        zeropage        EOR oper        45      2       3
        zeropage,X      EOR oper,X      55      2       4
        absolute        EOR oper        4D      3       4
        absolute,X      EOR oper,X      5D      3       4*
        absolute,Y      EOR oper,Y      59      3       4*
        (indirect,X)    EOR (oper,X)    41      2       6
        (indirect),Y    EOR (oper),Y    51      2       5*
         */
        this[0x49] = EOR(Immediate(), 2)
        this[0x45] = EOR(ZeroPage(), 3)
        this[0x55] = EOR(ZeroPageX(), 4)
        this[0x4d] = EOR(Absolute(), 4)
        this[0x5d] = EOR(AbsoluteX(), 4)
        this[0x59] = EOR(AbsoluteY(), 4)
        this[0x41] = EOR(IndirectX(), 6)
        this[0x51] = EOR(IndirectY(), 5)

        /*
        OR Memory with Accumulator

        A OR M -> A                                 NZCIDV
                                                    ++----
        addressing      assembler       opc bytes   cycles
        --------------------------------------------------
        immediate       ORA #oper       09      2       2
        zeropage        ORA oper        05      2       3
        zeropage,X      ORA oper,X      15      2       4
        absolute        ORA oper        0D      3       4
        absolute,X      ORA oper,X      1D      3       4*
        absolute,Y      ORA oper,Y      19      3       4*
        (indirect,X)    ORA (oper,X)    01      2       6
        (indirect),Y    ORA (oper),Y    11      2       5*
         */
        this[0x09] = ORA(Immediate(), 2)
        this[0x05] = ORA(ZeroPage(), 3)
        this[0x15] = ORA(ZeroPageX(), 4)
        this[0x0d] = ORA(Absolute(), 4)
        this[0x1d] = ORA(AbsoluteX(), 4)
        this[0x19] = ORA(AbsoluteY(), 4)
        this[0x01] = ORA(IndirectX(), 6)
        this[0x11] = ORA(IndirectY(), 5)

        /*
        Shift Left One Bit (Memory or Accumulator)

        C <- [76543210] <- 0
                                                    NZCIDV
                                                    +++---
        addressing      assembler       opc bytes   cycles
        --------------------------------------------------
        accumulator     ASL A           0A      1       2
        zeropage        ASL oper        06      2       5
        zeropage,X      ASL oper,X      16      2       6
        absolute        ASL oper        0E      3       6
        absolute,X      ASL oper,X      1E      3       7
         */
        this[0x0a] = ASL(Accumulator(), 2)
        this[0x06] = ASL(ZeroPage(), 5)
        this[0x16] = ASL(ZeroPageX(), 6)
        this[0x0e] = ASL(Absolute(), 6)
        this[0x1e] = ASL(AbsoluteX(), 7)

        /*
        Shift One Bit Right (Memory or Accumulator)

        0 -> [76543210] -> C                        NZCIDV
                                                    0++---
        addressing      assembler       opc bytes   cycles
        --------------------------------------------------
        accumulator     LSR A           4A      1       2
        zeropage        LSR oper        46      2       5
        zeropage,X      LSR oper,X      56      2       6
        absolute        LSR oper        4E      3       6
        absolute,X      LSR oper,X      5E      3       7
         */
        this[0x4a] = LSR(Accumulator(), 2)
        this[0x46] = LSR(ZeroPage(), 5)
        this[0x56] = LSR(ZeroPageX(), 6)
        this[0x4e] = LSR(Absolute(), 6)
        this[0x5e] = LSR(AbsoluteX(), 7)

        /*
        Rotate One Bit Left (Memory or Accumulator)

        C <- [76543210] <- C                        NZCIDV
                                                    +++---
        addressing      assembler       opc bytes   cycles
        --------------------------------------------------
        accumulator     ROL A           2A      1       2
        zeropage        ROL oper        26      2       5
        zeropage,X      ROL oper,X      36      2       6
        absolute        ROL oper        2E      3       6
        absolute,X      ROL oper,X      3E      3       7
         */
        this[0x2a] = ROL(Accumulator(), 2)
        this[0x26] = ROL(ZeroPage(), 5)
        this[0x36] = ROL(ZeroPageX(), 6)
        this[0x2e] = ROL(Absolute(), 6)
        this[0x3e] = ROL(AbsoluteX(), 7)

        /*
        Rotate One Bit Right (Memory or Accumulator)

        C -> [76543210] -> C                        NZCIDV
                                                    +++---
        addressing      assembler       opc bytes   cycles
        --------------------------------------------------
        accumulator     ROR A           6A      1       2
        zeropage        ROR oper        66      2       5
        zeropage,X      ROR oper,X      76      2       6
        absolute        ROR oper        6E      3       6
        absolute,X      ROR oper,X      7E      3       7
         */
        this[0x6a] = ROR(Accumulator(), 2)
        this[0x66] = ROR(ZeroPage(), 5)
        this[0x76] = ROR(ZeroPageX(), 6)
        this[0x6e] = ROR(Absolute(), 6)
        this[0x7e] = ROR(AbsoluteX(), 7)

        /*
        Clear Carry Flag

        0 -> C                                      NZCIDV
                                                    --0---
        addressing      assembler       opc bytes   cycles
        --------------------------------------------------
        implied         CLC             18      1       2
         */
        this[0x18] = CLC(Implied(), 2)

        /*
        Clear Decimal Mode

        0 -> D                                      NZCIDV
                                                    ----0-
        addressing      assembler       opc bytes   cycles
        --------------------------------------------------
        implied         CLD             D8      1       2
         */
        this[0xd8] = CLD(Implied(), 2)

        /*
        Clear Interrupt Disable Bit

        0 -> I                                      NZCIDV
                                                    ---0--
        addressing      assembler       opc bytes   cycles
        --------------------------------------------------
        implied         CLI             58      1       2
         */
        this[0x58] = CLI(Implied(), 2)

        /*
        Clear Overflow Flag

        0 -> V                                      NZCIDV
                                                    -----0
        addressing      assembler       opc bytes   cycles
        --------------------------------------------------
        implied         CLV             B8      1       2
         */
        this[0xb8] = CLV(Implied(), 2)

        /*
        Set Carry Flag

        1 -> C                                      NZCIDV
                                                    --1---
        addressing      assembler       opc bytes   cycles
        --------------------------------------------------
        implied         SEC             38      1       2
         */
        this[0x38] = SEC(Implied(), 2)

        /*
        Set Decimal Flag

        1 -> D                                      NZCIDV
                                                    ----1-
        addressing      assembler       opc bytes   cycles
        --------------------------------------------------
        implied         SED             F8      1       2
         */
        this[0xf8] = SED(Implied(), 2)

        /*
        Set Interrupt Disable Status

        1 -> I                                      NZCIDV
                                                    ---1--
        addressing      assembler       opc bytes   cycles
        --------------------------------------------------
        implied         SEI             78      1       2
         */
        this[0x78] = SEI(Implied(), 2)

        /*
        Compare Memory with Accumulator

        A - M                                       NZCIDV
                                                    +++---
        addressing      assembler       opc bytes   cycles
        --------------------------------------------------
        immediate       CMP #oper       C9      2       2
        zeropage        CMP oper        C5      2       3
        zeropage,X      CMP oper,X      D5      2       4
        absolute        CMP oper        CD      3       4
        absolute,X      CMP oper,X      DD      3       4*
        absolute,Y      CMP oper,Y      D9      3       4*
        (indirect,X)    CMP (oper,X)    C1      2       6
        (indirect),Y    CMP (oper),Y    D1      2       5*
         */
        this[0xc9] = CMP(Immediate(), 2)
        this[0xc5] = CMP(ZeroPage(), 3)
        this[0xd5] = CMP(ZeroPageX(), 4)
        this[0xcd] = CMP(Absolute(), 4)
        this[0xdd] = CMP(AbsoluteX(), 4)
        this[0xd9] = CMP(AbsoluteY(), 4)
        this[0xc1] = CMP(IndirectX(), 6)
        this[0xd1] = CMP(IndirectY(), 5)

        /*
        Compare Memory and Index X

        X - M                                       NZCIDV
                                                    +++---
        addressing      assembler       opc bytes   cycles
        --------------------------------------------------
        immediate       CPX #oper       E0      2       2
        zeropage        CPX oper        E4      2       3
        absolute        CPX oper        EC      3       4
         */
        this[0xe0] = CPX(Immediate(), 2)
        this[0xe4] = CPX(ZeroPage(), 3)
        this[0xec] = CPX(Absolute(), 4)

        /*
        Compare Memory and Index Y

        Y - M                                       NZCIDV
                                                    +++---
        addressing      assembler       opc bytes   cycles
        --------------------------------------------------
        immediate       CPY #oper       C0      2       2
        zeropage        CPY oper        C4      2       3
        absolute        CPY oper        CC      3       4
         */
        this[0xc0] = CPY(Immediate(), 2)
        this[0xc4] = CPY(ZeroPage(), 3)
        this[0xcc] = CPY(Absolute(), 4)

        /*
        Branch on Carry Clear

        branch on C = 0                             NZCIDV
                                                    ------
        addressing      assembler       opc bytes   cycles
        --------------------------------------------------
        relative        BCC oper        90      2       2**
         */
        this[0x90] = BCC(Relative(), 2)

        /*
        Branch on Carry Set

        branch on C = 1                             NZCIDV
                                                    ------
        addressing      assembler       opc bytes   cycles
        --------------------------------------------------
        relative        BCS oper        B0      2       2**
         */
        this[0xb0] = BCS(Relative(), 2)

        /*
        Branch on Result Zero

        branch on Z = 1                             NZCIDV
                                                    ------
        addressing      assembler       opc bytes   cycles
        --------------------------------------------------
        relative        BEQ oper        F0      2       2**
         */
        this[0xf0] = BEQ(Relative(), 2)

        /*
        Branch on Result Minus

        branch on N = 1                             NZCIDV
                                                    ------
        addressing      assembler       opc bytes   cycles
        --------------------------------------------------
        relative        BMI oper        30      2       2**
         */
        this[0x30] = BMI(Relative(), 2)

        /*
        Branch on Result not Zero

        branch on Z = 0                             NZCIDV
                                                    ------
        addressing      assembler       opc bytes   cycles
        --------------------------------------------------
        relative        BNE oper        D0      2       2**
         */
        this[0xd0] = BNE(Relative(), 2)

        /*
        Branch on Result Plus

        branch on N = 0                             NZCIDV
                                                    ------
        addressing      assembler       opc bytes   cycles
        --------------------------------------------------
        relative        BPL oper        10      2       2**
         */
        this[0x10] = BPL(Relative(), 2)

        /*
        Branch on Overflow Clear

        branch on V = 0                             NZCIDV
                                                    ------
        addressing      assembler       opc bytes   cycles
        --------------------------------------------------
        relative        BVC oper        50      2       2**
         */
        this[0x50] = BVC(Relative(), 2)

        /*
        Branch on Overflow Set

        branch on V = 1                             NZCIDV
                                                    ------
        addressing      assembler       opc bytes   cycles
        --------------------------------------------------
        relative        BVS oper        70      2       2**
         */
        this[0x70] = BVS(Relative(), 2)

        /*
        Jump to New Location

        (PC+1) -> PCL
        (PC+2) -> PCH                               NZCIDV
                                                    ------
        addressing      assembler       opc bytes   cycles
        --------------------------------------------------
        absolute        JMP oper        4C      3       3
        indirect        JMP (oper)      6C      3       5
         */
        this[0x4c] = JMP(Absolute(), 3)
        this[0x6c] = JMP(Indirect(), 5)

        /*
        Jump to New Location Saving Return Address

        push (PC+2),
        (PC+1) -> PCL
        (PC+2) -> PCH
                                                    NZCIDV
                                                    ------
        addressing      assembler       opc bytes   cycles
        --------------------------------------------------
        absolute        JSR oper        20      3       6
         */
        this[0x20] = JSR(Absolute(), 6)

        /*
        Return from Subroutine

        pull PC, PC+1 -> PC                         NZCIDV
                                                    ------
        addressing      assembler       opc bytes   cycles
        --------------------------------------------------
        implied         RTS             60      1       6
         */
        this[0x60] = RTS(Implied(), 6)

        /*
        Force Break

        BRK initiates a software interrupt similar to a hardware
        interrupt (IRQ). The return address pushed to the stack is
        PC+2, providing an extra byte of spacing for a break mark
        (identifying a reason for the break.)
        The status register will be pushed to the stack with the break
        flag set to 1. However, when retrieved during RTI or by a PLP
        instruction, the break flag will be ignored.
        The interrupt disable flag is not set automatically.

        interrupt,
        push PC+2, push SR                          NZCIDV
                                                    ---1--
        addressing      assembler       opc bytes   cycles
        --------------------------------------------------
        implied         BRK             00      1       7
         */
        this[0x00] = BRK(Implied(), 7)

        /*
        Return from Interrupt

        The status register is pulled with the break flag
        and bit 5 ignored. Then PC is pulled from the stack.

        pull SR, pull PC                            NZCIDV
                                                from stack
        addressing      assembler       opc bytes   cycles
        --------------------------------------------------
        implied         RTI             40      1       6
         */
        this[0x40] = RTI(Implied(), 6)

        /*
        No Operation

        ---                                         NZCIDV
                                                    ------
        addressing      assembler       opc bytes   cycles
        --------------------------------------------------
        implied         NOP             EA      1       2
         */
        this[0xea] = NOP(Implied(), 2)

        /*
        Test Bits in Memory with Accumulator

        bits 7 and 6 of operand are transferred to bit 7 and 6 of SR (N,V);
        the zero-flag is set to the result of operand AND accumulator.

        A AND M, M7 -> N, M6 -> V                  NZCIDV
                                                  M7+---M6
        addressing      assembler       opc bytes   cycles
        --------------------------------------------------
        zeropage        BIT oper        24      2       3
        absolute        BIT oper        2C      3       4
         */
        this[0x24] = BIT(ZeroPage(), 3)
        this[0x2c] = BIT(Absolute(), 4)
    }
}

abstract class AddressMode {
    abstract val opcode: String

    abstract fun execute(cpu: Cpu): ExecutionResult

    data class ExecutionResult(val result: Short, val additionalCycles: Int = 0)

    companion object {
        fun getByte(cpu: Cpu, address: Short): UByte {
            return cpu.read(address)
        }

        fun getNextByte(cpu: Cpu): UByte {
            val byte = cpu.read(cpu.programCounter)
            cpu.programCounter++
            return byte
        }

        fun getNextAddress(cpu: Cpu): Short {
            val lo = getNextByte(cpu)
            val hi = getNextByte(cpu)

            return Utils.convertBytesToAddress(lo, hi)
        }

        fun crossesPageBoundary(startAddress: Short, endAddress: Short): Boolean {
            val startHiByte = Utils.getHiByte(startAddress)
            val endHiByte = Utils.getHiByte(endAddress)
            return startHiByte != endHiByte
        }
    }
}

class Accumulator(override val opcode: String = "ACC") : AddressMode() {
    override fun execute(cpu: Cpu): ExecutionResult {
        return ExecutionResult(cpu.a.toShort())
    }
}

class Absolute(override val opcode: String = "ABS") : AddressMode() {
    override fun execute(cpu: Cpu): ExecutionResult {
        return ExecutionResult(getNextAddress(cpu))
    }
}

class AbsoluteX(override val opcode: String = "ABX") : AddressMode() {
    override fun execute(cpu: Cpu): ExecutionResult {
        val startAddress = getNextAddress(cpu)
        val finalAddress = (startAddress + cpu.x.toShort()).toShort()

        var additionalCycles = 0

        if (crossesPageBoundary(startAddress, finalAddress)) {
            additionalCycles++
        }

        return ExecutionResult(finalAddress, additionalCycles)
    }
}

class AbsoluteY(override val opcode: String = "ABY") : AddressMode() {
    override fun execute(cpu: Cpu): ExecutionResult {
        val startAddress = getNextAddress(cpu)
        val finalAddress = (startAddress + cpu.y.toShort()).toShort()

        var additionalCycles = 0

        if (crossesPageBoundary(startAddress, finalAddress)) {
            additionalCycles++
        }

        return ExecutionResult(finalAddress, additionalCycles)
    }
}

class Immediate(override val opcode: String = "IMM") : AddressMode() {
    override fun execute(cpu: Cpu): ExecutionResult {
        return ExecutionResult(cpu.programCounter++)
    }
}

class Implied(override val opcode: String = "IMP") : AddressMode() {
    override fun execute(cpu: Cpu): ExecutionResult {
        return ExecutionResult(0)
    }
}

class Indirect(override val opcode: String = "IND") : AddressMode() {
    override fun execute(cpu: Cpu): ExecutionResult {
        val address = getNextAddress(cpu)

        val lo = getByte(cpu, address)
        val hi = getByte(cpu, (address + 1).toShort())

        return ExecutionResult(Utils.convertBytesToAddress(lo, hi))
    }
}

class IndirectX(override val opcode: String = "IZX") : AddressMode() {
    override fun execute(cpu: Cpu): ExecutionResult {
        val zpAddress = (getNextByte(cpu) + cpu.x).toUByte().toShort()

        val lo = getByte(cpu, zpAddress)
        val hi = getByte(cpu, (zpAddress + 1).toUByte().toShort())

        val finalAddress = Utils.convertBytesToAddress(lo, hi)

        return ExecutionResult(finalAddress)
    }
}

class IndirectY(override val opcode: String = "IZY") : AddressMode() {
    override fun execute(cpu: Cpu): ExecutionResult {
        val zpAddress = getNextByte(cpu).toShort()

        val lo = getByte(cpu, zpAddress)
        val hi = getByte(cpu, (zpAddress + 1).toUByte().toShort())

        val startAddress = Utils.convertBytesToAddress(lo, hi)
        val finalAddress = (startAddress + cpu.y.toShort()).toShort()

        var additionalCycles = 0

        if (crossesPageBoundary(startAddress, finalAddress)) {
            additionalCycles++
        }

        return ExecutionResult(finalAddress, additionalCycles)
    }
}

class Relative(override val opcode: String = "REL") : AddressMode() {
    override fun execute(cpu: Cpu): ExecutionResult {
        val address = getNextByte(cpu).toByte()

        return ExecutionResult(address.toShort())
    }
}

class ZeroPage(override val opcode: String = "ZPP") : AddressMode() {
    override fun execute(cpu: Cpu): ExecutionResult {
        val startAddress = getNextByte(cpu)

        return ExecutionResult(startAddress.toShort())
    }
}

class ZeroPageX(override val opcode: String = "ZPX") : AddressMode() {
    override fun execute(cpu: Cpu): ExecutionResult {
        val startAddress = getNextByte(cpu)

        return ExecutionResult((startAddress + cpu.x).toUByte().toShort())
    }
}

class ZeroPageY(override val opcode: String = "ZPY") : AddressMode() {
    override fun execute(cpu: Cpu): ExecutionResult {
        val startAddress = getNextByte(cpu)

        return ExecutionResult((startAddress + cpu.y).toUByte().toShort())
    }
}

class XXX(override val addressMode: AddressMode, override val cycles: Int) : Instruction() {
    override val opcode = "XXX"

    override fun execute(cpu: Cpu): Int {
        throw RuntimeException("Unknown opcode!")
    }
}

abstract class Instruction {
    abstract val opcode: String
    abstract val addressMode: AddressMode
    abstract val cycles: Int

    abstract fun execute(cpu: Cpu): Int

    protected fun setZeroAndNegativeFlags(statusRegister: StatusRegister, value: UByte) {
        statusRegister.zeroFlag = value == 0x00.toUByte()
        statusRegister.negativeFlag = Utils.getBit(value, StatusRegister.NEGATIVE_BIT_POSITION)
    }

    protected fun crossesPageBoundary(startAddress: Short, endAddress: Short): Boolean {
        val startHiByte = Utils.getHiByte(startAddress)
        val endHiByte = Utils.getHiByte(endAddress)
        return startHiByte != endHiByte
    }
}

abstract class ArithmeticInstruction : Instruction() {
    protected fun addBinary(cpu: Cpu, operand: UByte): Byte {
        val newValue = cpu.a.toInt() + operand.toInt() + if (cpu.statusRegister.carryFlag) 1 else 0

        cpu.statusRegister.zeroFlag = (newValue and 0x00ff) == 0
        cpu.statusRegister.carryFlag = newValue > 0xff
        cpu.statusRegister.negativeFlag = (newValue and 0x80) == 0x80
        val checkOperandSign = (cpu.a xor operand).inv().toInt()
        val checkNewValueSign = (cpu.a.toInt() xor newValue)
        cpu.statusRegister.overflowFlag = (checkOperandSign and checkNewValueSign) and 0x0080 == 0x0080

        return newValue.toByte()
    }
}

class LDA(override val addressMode: AddressMode, override val cycles: Int) : Instruction() {
    override val opcode = "LDA"

    override fun execute(cpu: Cpu): Int {
        val (address, additionalCycles) = addressMode.execute(cpu)

        cpu.a = cpu.read(address)
        setZeroAndNegativeFlags(cpu.statusRegister, cpu.a)

        return cycles + additionalCycles
    }
}

class LDX(override val addressMode: AddressMode, override val cycles: Int) : Instruction() {
    override val opcode = "LDX"

    override fun execute(cpu: Cpu): Int {
        val (address, additionalCycles) = addressMode.execute(cpu)

        cpu.x = cpu.read(address)
        setZeroAndNegativeFlags(cpu.statusRegister, cpu.x)

        return cycles + additionalCycles
    }
}

class LDY(override val addressMode: AddressMode, override val cycles: Int) : Instruction() {
    override val opcode = "LDY"

    override fun execute(cpu: Cpu): Int {
        val (address, additionalCycles) = addressMode.execute(cpu)

        cpu.y = cpu.read(address)
        setZeroAndNegativeFlags(cpu.statusRegister, cpu.y)

        return cycles + additionalCycles
    }
}

class STA(override val addressMode: AddressMode, override val cycles: Int) : Instruction() {
    override val opcode = "STA"

    override fun execute(cpu: Cpu): Int {
        val (address, _) = addressMode.execute(cpu)

        cpu.write(address, cpu.a)

        // STA always performs a cross-page check before write which consumes a cycle no matter what, so we'll
        // ignore what the addressMode returns
        return cycles
    }
}

class STX(override val addressMode: AddressMode, override val cycles: Int) : Instruction() {
    override val opcode = "STX"

    override fun execute(cpu: Cpu): Int {
        val (address, _) = addressMode.execute(cpu)

        cpu.write(address, cpu.x)

        // STX doesn't use any address mode that can add a cycle
        return cycles
    }
}

class STY(override val addressMode: AddressMode, override val cycles: Int) : Instruction() {
    override val opcode = "STY"

    override fun execute(cpu: Cpu): Int {
        val (address, _) = addressMode.execute(cpu)

        cpu.write(address, cpu.y)

        // STY doesn't use any address mode that can add a cycle
        return cycles
    }
}

class TAX(override val addressMode: AddressMode, override val cycles: Int) : Instruction() {
    override val opcode = "TAX"

    override fun execute(cpu: Cpu): Int {
        val (_, _) = addressMode.execute(cpu)

        cpu.x = cpu.a
        setZeroAndNegativeFlags(cpu.statusRegister, cpu.x)

        // TAX always takes two cycles
        return cycles
    }
}

class TAY(override val addressMode: AddressMode, override val cycles: Int) : Instruction() {
    override val opcode = "TAY"

    override fun execute(cpu: Cpu): Int {
        val (_, _) = addressMode.execute(cpu)

        cpu.y = cpu.a
        setZeroAndNegativeFlags(cpu.statusRegister, cpu.y)

        // TAY always takes two cycles
        return cycles
    }
}

class TSX(override val addressMode: AddressMode, override val cycles: Int) : Instruction() {
    override val opcode = "TSX"

    override fun execute(cpu: Cpu): Int {
        val (_, _) = addressMode.execute(cpu)

        cpu.x = cpu.stackPointer
        setZeroAndNegativeFlags(cpu.statusRegister, cpu.x)

        // TSX always takes two cycles
        return cycles
    }
}

class TXA(override val addressMode: AddressMode, override val cycles: Int) : Instruction() {
    override val opcode = "TXA"

    override fun execute(cpu: Cpu): Int {
        val (_, _) = addressMode.execute(cpu)

        cpu.a = cpu.x
        setZeroAndNegativeFlags(cpu.statusRegister, cpu.a)

        // TXA always takes two cycles
        return cycles
    }
}

class TXS(override val addressMode: AddressMode, override val cycles: Int) : Instruction() {
    override val opcode = "TXS"

    override fun execute(cpu: Cpu): Int {
        val (_, _) = addressMode.execute(cpu)

        cpu.stackPointer = cpu.x

        // TXS always takes two cycles
        return cycles
    }
}

class TYA(override val addressMode: AddressMode, override val cycles: Int) : Instruction() {
    override val opcode = "TYA"

    override fun execute(cpu: Cpu): Int {
        val (_, _) = addressMode.execute(cpu)

        cpu.a = cpu.y
        setZeroAndNegativeFlags(cpu.statusRegister, cpu.a)

        // TYA always takes two cycles
        return cycles
    }
}

class PHA(override val addressMode: AddressMode, override val cycles: Int) : Instruction() {
    override val opcode = "PHA"

    override fun execute(cpu: Cpu): Int {
        val (_, _) = addressMode.execute(cpu)

        cpu.writeStack(cpu.a)

        // PHA always takes three cycles
        return cycles
    }
}

class PHP(override val addressMode: AddressMode, override val cycles: Int) : Instruction() {
    override val opcode = "PHP"

    override fun execute(cpu: Cpu): Int {
        val (_, _) = addressMode.execute(cpu)

        val flags = cpu.statusRegister.copy()
        flags.breakFlag = true
        flags.ignoredFlag = true

        cpu.writeStack(flags.flags)

        // PHP always takes three cycles
        return cycles
    }
}

class PLA(override val addressMode: AddressMode, override val cycles: Int) : Instruction() {
    override val opcode = "PLA"

    override fun execute(cpu: Cpu): Int {
        val (_, _) = addressMode.execute(cpu)

        cpu.a = cpu.readStack()
        setZeroAndNegativeFlags(cpu.statusRegister, cpu.a)

        // PLA always takes four cycles
        return cycles
    }
}

class PLP(override val addressMode: AddressMode, override val cycles: Int) : Instruction() {
    override val opcode = "PLP"

    override fun execute(cpu: Cpu): Int {
        val (_, _) = addressMode.execute(cpu)

        val byte = cpu.readStack()
        cpu.statusRegister.negativeFlag = Utils.getBit(byte, StatusRegister.NEGATIVE_BIT_POSITION)
        cpu.statusRegister.overflowFlag = Utils.getBit(byte, StatusRegister.OVERFLOW_BIT_POSITION)
        // ignore bit 5
        // ignore Break
        cpu.statusRegister.decimalFlag = Utils.getBit(byte, StatusRegister.DECIMAL_BIT_POSITION)
        cpu.statusRegister.interruptDisabledFlag = Utils.getBit(byte, StatusRegister.INTERRUPT_DISABLED_BIT_POSITION)
        cpu.statusRegister.zeroFlag = Utils.getBit(byte, StatusRegister.ZERO_BIT_POSITION)
        cpu.statusRegister.carryFlag = Utils.getBit(byte, StatusRegister.CARRY_BIT_POSITION)

        // PLP always return 4 cycles
        return cycles
    }
}

class DEC(override val addressMode: AddressMode, override val cycles: Int) : Instruction() {
    override val opcode = "DEC"

    override fun execute(cpu: Cpu): Int {
        val (address, _) = addressMode.execute(cpu)

        val originalByte = cpu.read(address)
        val decremented = (originalByte - 1u).toUByte()

        cpu.write(address, decremented)
        setZeroAndNegativeFlags(cpu.statusRegister, decremented)

        // DEC always performs a cross-page check before write which consumes a cycle no matter what, so we'll
        // ignore what the addressMode returns
        return cycles
    }
}

class DEX(override val addressMode: AddressMode, override val cycles: Int) : Instruction() {
    override val opcode = "DEX"

    override fun execute(cpu: Cpu): Int {
        val (_, _) = addressMode.execute(cpu)

        cpu.x--
        setZeroAndNegativeFlags(cpu.statusRegister, cpu.x)

        return cycles
    }
}

class DEY(override val addressMode: AddressMode, override val cycles: Int) : Instruction() {
    override val opcode = "DEY"

    override fun execute(cpu: Cpu): Int {
        val (_, _) = addressMode.execute(cpu)

        cpu.y--
        setZeroAndNegativeFlags(cpu.statusRegister, cpu.y)

        return cycles
    }
}

class INC(override val addressMode: AddressMode, override val cycles: Int) : Instruction() {
    override val opcode = "INC"

    override fun execute(cpu: Cpu): Int {
        val (address, _) = addressMode.execute(cpu)

        val originalByte = cpu.read(address)
        val incremented = (originalByte + 1u).toUByte()

        cpu.write(address, incremented)
        setZeroAndNegativeFlags(cpu.statusRegister, incremented)

        // INC always performs a cross-page check before write which consumes a cycle no matter what, so we'll
        // ignore what the addressMode returns
        return cycles
    }
}

class INX(override val addressMode: AddressMode, override val cycles: Int) : Instruction() {
    override val opcode = "INX"

    override fun execute(cpu: Cpu): Int {
        val (_, _) = addressMode.execute(cpu)

        cpu.x++
        setZeroAndNegativeFlags(cpu.statusRegister, cpu.x)

        return cycles
    }
}

class INY(override val addressMode: AddressMode, override val cycles: Int) : Instruction() {
    override val opcode = "INY"

    override fun execute(cpu: Cpu): Int {
        val (_, _) = addressMode.execute(cpu)

        cpu.y++
        setZeroAndNegativeFlags(cpu.statusRegister, cpu.y)

        return cycles
    }
}

class ADC(override val addressMode: AddressMode, override val cycles: Int) : ArithmeticInstruction() {
    override val opcode = "ADC"

    override fun execute(cpu: Cpu): Int {
        val (address, additionalCycles) = addressMode.execute(cpu)

        val operand = cpu.read(address)

        if (cpu.statusRegister.decimalFlag) {
            // Z flag is based on the binary addition.
            cpu.statusRegister.zeroFlag = (cpu.a.toInt() + operand.toInt() + if (cpu.statusRegister.carryFlag) 1 else 0 and 0x00ff) == 0

            var lo = (cpu.a.toByte() and 0x0f) + (operand.toByte() and 0x0f) + if (cpu.statusRegister.carryFlag) 1 else 0
            if (lo > 9) {
                lo = ((lo + 6) and 0x0f) + 0x10
            }

            var r = (cpu.a.toInt() and 0xf0) + (operand.toInt() and 0xf0) + lo

            // N and V are calculated before doing the decimal adjust on the high nibble
            cpu.statusRegister.negativeFlag = (r and 0x80) == 0x80
            cpu.statusRegister.overflowFlag = ((cpu.a.toInt() xor r) and (operand.toInt() xor r)) and 0x0080 == 0x0080

            if (r >= 160) {
                r += 96
            }

            cpu.statusRegister.carryFlag = r >= 256

            cpu.a = (r and 0xff).toUByte()
        } else {
            cpu.a = addBinary(cpu, operand).toUByte()
        }

        return cycles + additionalCycles
    }
}

class SBC(override val addressMode: AddressMode, override val cycles: Int) : ArithmeticInstruction() {
    override val opcode = "SBC"

    override fun execute(cpu: Cpu): Int {
        val (address, additionalCycles) = addressMode.execute(cpu)

        val operand = cpu.read(address)

        if (cpu.statusRegister.decimalFlag) {
            val decA = cpu.a.toByte() and 0x0f
            val decM = operand.toByte() and 0x0f
            val carry = if (cpu.statusRegister.carryFlag) 0 else 1

            var lo = decA - decM - carry
            if (lo < 0) {
                lo = ((lo - 6) and 0x0f) - 0x10
            }

            var r = ((cpu.a.toInt() and 0xf0) - (operand.toInt() and 0xf0)) + lo
            if (r < 0) {
                r -= 96
            }

            // this might not work with 65C02 code
            addBinary(cpu, operand.inv())

            cpu.a = (r and 0xff).toUByte()
        } else {
            cpu.a = addBinary(cpu, operand.inv()).toUByte()
        }

        return cycles + additionalCycles
    }
}

class AND(override val addressMode: AddressMode, override val cycles: Int) : Instruction() {
    override val opcode = "AND"

    override fun execute(cpu: Cpu): Int {
        val (address, additionalCycles) = addressMode.execute(cpu)

        val operand = cpu.read(address)

        cpu.a = cpu.a and operand
        setZeroAndNegativeFlags(cpu.statusRegister, cpu.a)

        return cycles + additionalCycles
    }
}

class EOR(override val addressMode: AddressMode, override val cycles: Int) : Instruction() {
    override val opcode = "EOR"

    override fun execute(cpu: Cpu): Int {
        val (address, additionalCycles) = addressMode.execute(cpu)

        val operand = cpu.read(address)

        cpu.a = cpu.a xor operand
        setZeroAndNegativeFlags(cpu.statusRegister, cpu.a)

        return cycles + additionalCycles
    }
}

class ORA(override val addressMode: AddressMode, override val cycles: Int) : Instruction() {
    override val opcode = "ORA"

    override fun execute(cpu: Cpu): Int {
        val (address, additionalCycles) = addressMode.execute(cpu)

        val operand = cpu.read(address)

        cpu.a = cpu.a or operand
        setZeroAndNegativeFlags(cpu.statusRegister, cpu.a)

        return cycles + additionalCycles
    }
}

class ASL(override val addressMode: AddressMode, override val cycles: Int) : Instruction() {
    override val opcode = "ASL"

    override fun execute(cpu: Cpu): Int {
        if (addressMode is Accumulator) {
            val shiftedValue = performShiftAndSetRegisters(cpu, cpu.a)

            cpu.a = shiftedValue
        } else {
            val (address, _) = addressMode.execute(cpu)

            val shiftedValue = performShiftAndSetRegisters(cpu, cpu.read(address))

            cpu.write(address, shiftedValue)
        }

        return cycles
    }

    private fun performShiftAndSetRegisters(cpu: Cpu, value: UByte): UByte {
        val shiftedValueInt = value.toInt() shl 1
        val shiftedValueByte = shiftedValueInt.toUByte()

        setZeroAndNegativeFlags(cpu.statusRegister, shiftedValueByte)
        cpu.statusRegister.carryFlag = Utils.getBit(shiftedValueInt, 8)

        return shiftedValueByte
    }
}

class LSR(override val addressMode: AddressMode, override val cycles: Int) : Instruction() {
    override val opcode = "LSR"

    override fun execute(cpu: Cpu): Int {
        if (addressMode is Accumulator) {
            val shiftedValue = performShiftAndSetRegisters(cpu, cpu.a)

            cpu.a = shiftedValue
        } else {
            val (address, _) = addressMode.execute(cpu)

            val shiftedValue = performShiftAndSetRegisters(cpu, cpu.read(address))

            cpu.write(address, shiftedValue)
        }

        return cycles
    }

    private fun performShiftAndSetRegisters(cpu: Cpu, value: UByte): UByte {
        cpu.statusRegister.carryFlag = Utils.getBit(value, 0)

        val shiftedValueInt = value.toInt() shr 1
        val shiftedValueByte = shiftedValueInt.toUByte()

        setZeroAndNegativeFlags(cpu.statusRegister, shiftedValueByte)

        return shiftedValueByte
    }
}

class ROL(override val addressMode: AddressMode, override val cycles: Int) : Instruction() {
    override val opcode = "ROL"

    override fun execute(cpu: Cpu): Int {
        if (addressMode is Accumulator) {
            val rotatedValue = performRotateAndSetRegisters(cpu, cpu.a)

            cpu.a = rotatedValue
        } else {
            val (address, _) = addressMode.execute(cpu)

            val rotatedValue = performRotateAndSetRegisters(cpu, cpu.read(address))

            cpu.write(address, rotatedValue)
        }

        return cycles
    }

    private fun performRotateAndSetRegisters(cpu: Cpu, value: UByte): UByte {
        val currentCarry = cpu.statusRegister.carryFlag

        val rotatedValueInt = (value.toInt() shl 1) + if (currentCarry) 1 else 0
        val rotatedValueByte = rotatedValueInt.toUByte()

        cpu.statusRegister.carryFlag = Utils.getBit(rotatedValueInt, 8)
        setZeroAndNegativeFlags(cpu.statusRegister, rotatedValueByte)

        return rotatedValueByte
    }
}

class ROR(override val addressMode: AddressMode, override val cycles: Int) : Instruction() {
    override val opcode = "ROR"

    override fun execute(cpu: Cpu): Int {
        if (addressMode is Accumulator) {
            val rotatedValue = performRotateAndSetRegisters(cpu, cpu.a)

            cpu.a = rotatedValue
        } else {
            val (address, _) = addressMode.execute(cpu)

            val rotatedValue = performRotateAndSetRegisters(cpu, cpu.read(address))

            cpu.write(address, rotatedValue)
        }

        return cycles
    }

    private fun performRotateAndSetRegisters(cpu: Cpu, value: UByte): UByte {
        val nextCarry = Utils.getBit(value, 0)

        val rotatedValueInt = (value.toInt() shr 1) + if (cpu.statusRegister.carryFlag) 0x80 else 0
        val rotatedValueByte = rotatedValueInt.toUByte()

        cpu.statusRegister.carryFlag = nextCarry
        setZeroAndNegativeFlags(cpu.statusRegister, rotatedValueByte)

        return rotatedValueByte
    }
}

class CLC(override val addressMode: AddressMode, override val cycles: Int) : Instruction() {
    override val opcode = "CLC"

    override fun execute(cpu: Cpu): Int {
        val (_, _) = addressMode.execute(cpu)

        cpu.statusRegister.carryFlag = false

        return cycles
    }
}

class CLD(override val addressMode: AddressMode, override val cycles: Int) : Instruction() {
    override val opcode = "CLD"

    override fun execute(cpu: Cpu): Int {
        val (_, _) = addressMode.execute(cpu)

        cpu.statusRegister.decimalFlag = false

        return cycles
    }
}

class CLI(override val addressMode: AddressMode, override val cycles: Int) : Instruction() {
    override val opcode = "CLI"

    override fun execute(cpu: Cpu): Int {
        val (_, _) = addressMode.execute(cpu)

        cpu.statusRegister.interruptDisabledFlag = false

        return cycles
    }
}

class CLV(override val addressMode: AddressMode, override val cycles: Int) : Instruction() {
    override val opcode = "CLV"

    override fun execute(cpu: Cpu): Int {
        val (_, _) = addressMode.execute(cpu)

        cpu.statusRegister.overflowFlag = false

        return cycles
    }
}

class SEC(override val addressMode: AddressMode, override val cycles: Int) : Instruction() {
    override val opcode = "SEC"

    override fun execute(cpu: Cpu): Int {
        val (_, _) = addressMode.execute(cpu)

        cpu.statusRegister.carryFlag = true

        return cycles
    }
}

class SED(override val addressMode: AddressMode, override val cycles: Int) : Instruction() {
    override val opcode = "SED"

    override fun execute(cpu: Cpu): Int {
        val (_, _) = addressMode.execute(cpu)

        cpu.statusRegister.decimalFlag = true

        return cycles
    }
}

class SEI(override val addressMode: AddressMode, override val cycles: Int) : Instruction() {
    override val opcode = "SEI"

    override fun execute(cpu: Cpu): Int {
        val (_, _) = addressMode.execute(cpu)

        cpu.statusRegister.interruptDisabledFlag = true

        return cycles
    }
}

class CMP(override val addressMode: AddressMode, override val cycles: Int) : Instruction() {
    override val opcode = "CMP"

    override fun execute(cpu: Cpu): Int {
        val (address, additionalCycles) = addressMode.execute(cpu)

        val accumulatorInt = cpu.a.toInt()
        val valueInt = cpu.read(address).toInt()
        val subtractionInt = accumulatorInt - valueInt

        setZeroAndNegativeFlags(cpu.statusRegister, subtractionInt.toUByte())
        cpu.statusRegister.carryFlag = accumulatorInt >= valueInt

        return cycles + additionalCycles
    }
}

class CPX(override val addressMode: AddressMode, override val cycles: Int) : Instruction() {
    override val opcode = "CPX"

    override fun execute(cpu: Cpu): Int {
        val (address, additionalCycles) = addressMode.execute(cpu)

        val xInt = cpu.x.toInt()
        val valueInt = cpu.read(address).toInt()
        val subtractionInt = xInt - valueInt

        setZeroAndNegativeFlags(cpu.statusRegister, subtractionInt.toUByte())
        cpu.statusRegister.carryFlag = xInt >= valueInt

        return cycles + additionalCycles
    }
}

class CPY(override val addressMode: AddressMode, override val cycles: Int) : Instruction() {
    override val opcode = "CPY"

    override fun execute(cpu: Cpu): Int {
        val (address, additionalCycles) = addressMode.execute(cpu)

        val yInt = cpu.y.toInt()
        val valueInt = cpu.read(address).toInt()
        val subtractionInt = yInt - valueInt

        setZeroAndNegativeFlags(cpu.statusRegister, subtractionInt.toUByte())
        cpu.statusRegister.carryFlag = yInt >= valueInt

        return cycles + additionalCycles
    }
}

class BCC(override val addressMode: AddressMode, override val cycles: Int) : Instruction() {
    override val opcode = "BCC"

    override fun execute(cpu: Cpu): Int {
        val (address, _) = addressMode.execute(cpu)

        return cycles + if (cpu.statusRegister.carryFlag) 0 else {
            var extraCycles = 1

            val newProgramCounter = (cpu.programCounter + address).toShort()

            if (crossesPageBoundary(cpu.programCounter, newProgramCounter)) {
                extraCycles++
            }

            cpu.programCounter = newProgramCounter

            extraCycles
        }
    }
}

class BCS(override val addressMode: AddressMode, override val cycles: Int) : Instruction() {
    override val opcode = "BCS"

    override fun execute(cpu: Cpu): Int {
        val (address, _) = addressMode.execute(cpu)

        return cycles + if (!cpu.statusRegister.carryFlag) 0 else {
            var extraCycles = 1

            val newProgramCounter = (cpu.programCounter + address).toShort()

            if (crossesPageBoundary(cpu.programCounter, newProgramCounter)) {
                extraCycles++
            }

            cpu.programCounter = newProgramCounter

            extraCycles
        }
    }
}

class BEQ(override val addressMode: AddressMode, override val cycles: Int) : Instruction() {
    override val opcode = "BEQ"

    override fun execute(cpu: Cpu): Int {
        val (address, _) = addressMode.execute(cpu)

        return cycles + if (!cpu.statusRegister.zeroFlag) 0 else {
            var extraCycles = 1

            val newProgramCounter = (cpu.programCounter + address).toShort()

            if (crossesPageBoundary(cpu.programCounter, newProgramCounter)) {
                extraCycles++
            }

            cpu.programCounter = newProgramCounter

            extraCycles
        }
    }
}

class BMI(override val addressMode: AddressMode, override val cycles: Int) : Instruction() {
    override val opcode = "BMI"

    override fun execute(cpu: Cpu): Int {
        val (address, _) = addressMode.execute(cpu)

        return cycles + if (!cpu.statusRegister.negativeFlag) 0 else {
            var extraCycles = 1

            val newProgramCounter = (cpu.programCounter + address).toShort()

            if (crossesPageBoundary(cpu.programCounter, newProgramCounter)) {
                extraCycles++
            }

            cpu.programCounter = newProgramCounter

            extraCycles
        }
    }
}

class BNE(override val addressMode: AddressMode, override val cycles: Int) : Instruction() {
    override val opcode = "BNE"

    override fun execute(cpu: Cpu): Int {
        val (address, _) = addressMode.execute(cpu)

        return cycles + if (cpu.statusRegister.zeroFlag) 0 else {
            var extraCycles = 1

            val newProgramCounter = (cpu.programCounter + address).toShort()

            if (crossesPageBoundary(cpu.programCounter, newProgramCounter)) {
                extraCycles++
            }

            cpu.programCounter = newProgramCounter

            extraCycles
        }
    }
}

class BPL(override val addressMode: AddressMode, override val cycles: Int) : Instruction() {
    override val opcode = "BPL"

    override fun execute(cpu: Cpu): Int {
        val (address, _) = addressMode.execute(cpu)

        return cycles + if (cpu.statusRegister.negativeFlag) 0 else {
            var extraCycles = 1

            val newProgramCounter = (cpu.programCounter + address).toShort()

            if (crossesPageBoundary(cpu.programCounter, newProgramCounter)) {
                extraCycles++
            }

            cpu.programCounter = newProgramCounter

            extraCycles
        }
    }
}

class BVC(override val addressMode: AddressMode, override val cycles: Int) : Instruction() {
    override val opcode = "BVC"

    override fun execute(cpu: Cpu): Int {
        val (address, _) = addressMode.execute(cpu)

        return cycles + if (cpu.statusRegister.overflowFlag) 0 else {
            var extraCycles = 1

            val newProgramCounter = (cpu.programCounter + address).toShort()

            if (crossesPageBoundary(cpu.programCounter, newProgramCounter)) {
                extraCycles++
            }

            cpu.programCounter = newProgramCounter

            extraCycles
        }
    }
}

class BVS(override val addressMode: AddressMode, override val cycles: Int) : Instruction() {
    override val opcode = "BVS"

    override fun execute(cpu: Cpu): Int {
        val (address, _) = addressMode.execute(cpu)

        return cycles + if (!cpu.statusRegister.overflowFlag) 0 else {
            var extraCycles = 1

            val newProgramCounter = (cpu.programCounter + address).toShort()

            if (crossesPageBoundary(cpu.programCounter, newProgramCounter)) {
                extraCycles++
            }

            cpu.programCounter = newProgramCounter

            extraCycles
        }
    }
}

class JMP(override val addressMode: AddressMode, override val cycles: Int) : Instruction() {
    override val opcode = "JMP"

    override fun execute(cpu: Cpu): Int {
        val (address, _) = addressMode.execute(cpu)

        cpu.programCounter = address

        return cycles
    }
}

class JSR(override val addressMode: AddressMode, override val cycles: Int) : Instruction() {
    override val opcode = "JSR"

    override fun execute(cpu: Cpu): Int {
        val (address, _) = addressMode.execute(cpu)

        val (lo, hi) = Utils.getLoAndHiBytes((cpu.programCounter - 1).toShort())

        cpu.writeStack(hi)
        cpu.writeStack(lo)

        cpu.programCounter = address

        return cycles
    }
}

class RTS(override val addressMode: AddressMode, override val cycles: Int) : Instruction() {
    override val opcode = "RTS"

    override fun execute(cpu: Cpu): Int {
        val (_, _) = addressMode.execute(cpu)

        val lo = cpu.readStack()
        val hi = cpu.readStack()

        cpu.programCounter = (Utils.convertBytesToAddress(lo, hi) + 1).toShort()

        return cycles
    }
}

class BRK(override val addressMode: AddressMode, override val cycles: Int) : Instruction() {
    override val opcode = "BRK"

    override fun execute(cpu: Cpu): Int {
        val (_, _) = addressMode.execute(cpu)

        val (lo, hi) = Utils.getLoAndHiBytes((cpu.programCounter + 1).toShort())
        cpu.writeStack(hi)
        cpu.writeStack(lo)

        val flags = cpu.statusRegister.copy()
        flags.breakFlag = true
        flags.ignoredFlag = true

        cpu.writeStack(flags.flags)

        cpu.statusRegister.interruptDisabledFlag = true

        val irqVecLo = cpu.read(Cpu.IRQ_VECTOR)
        val irqVecHi = cpu.read((Cpu.IRQ_VECTOR + 1).toShort())
        cpu.programCounter = Utils.convertBytesToAddress(irqVecLo, irqVecHi)

        return cycles
    }
}

class RTI(override val addressMode: AddressMode, override val cycles: Int) : Instruction() {
    override val opcode = "RTI"

    override fun execute(cpu: Cpu): Int {
        val (_, _) = addressMode.execute(cpu)

        cpu.statusRegister.flags = cpu.readStack()
        cpu.statusRegister.breakFlag = true
        cpu.statusRegister.ignoredFlag = true

        val lo = cpu.readStack()
        val hi = cpu.readStack()

        cpu.programCounter = Utils.convertBytesToAddress(lo, hi)

        return cycles
    }
}

class NOP(override val addressMode: AddressMode, override val cycles: Int) : Instruction() {
    override val opcode = "NOP"

    override fun execute(cpu: Cpu): Int {
        val (_, _) = addressMode.execute(cpu)

        return cycles
    }
}

class BIT(override val addressMode: AddressMode, override val cycles: Int) : Instruction() {
    override val opcode = "BIT"

    override fun execute(cpu: Cpu): Int {
        val (address, _) = addressMode.execute(cpu)

        val operand = cpu.read(address)
        val andResult = cpu.a and operand

        cpu.statusRegister.negativeFlag = Utils.getBit(operand, StatusRegister.NEGATIVE_BIT_POSITION)
        cpu.statusRegister.overflowFlag = Utils.getBit(operand, StatusRegister.OVERFLOW_BIT_POSITION)
        cpu.statusRegister.zeroFlag = andResult == 0x00.toUByte()

        return cycles
    }
}