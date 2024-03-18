package com.puffinsoft.k6502

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class InstructionSetTest {
    private val instructionSet = InstructionSet()

    @Test
    fun `test instructions array`() {
        val instructions = instructionSet.instructions

        assertThat(instructions).hasSize(256)

        val opcodeCounts = instructions.groupingBy { it.opcode }.eachCount()
        assertThat(opcodeCounts["XXX"]).isEqualTo(105)
        assertThat(opcodeCounts["LDA"]).isEqualTo(8)
        assertThat(opcodeCounts["LDX"]).isEqualTo(5)
        assertThat(opcodeCounts["LDY"]).isEqualTo(5)
        assertThat(opcodeCounts["STA"]).isEqualTo(7)
        assertThat(opcodeCounts["STX"]).isEqualTo(3)
        assertThat(opcodeCounts["STY"]).isEqualTo(3)
        assertThat(opcodeCounts["TAX"]).isEqualTo(1)
        assertThat(opcodeCounts["TAY"]).isEqualTo(1)
        assertThat(opcodeCounts["TSX"]).isEqualTo(1)
        assertThat(opcodeCounts["TXA"]).isEqualTo(1)
        assertThat(opcodeCounts["TXS"]).isEqualTo(1)
        assertThat(opcodeCounts["TYA"]).isEqualTo(1)
        assertThat(opcodeCounts["PHA"]).isEqualTo(1)
        assertThat(opcodeCounts["PHP"]).isEqualTo(1)
        assertThat(opcodeCounts["PLA"]).isEqualTo(1)
        assertThat(opcodeCounts["PLP"]).isEqualTo(1)
        assertThat(opcodeCounts["DEC"]).isEqualTo(4)
        assertThat(opcodeCounts["DEX"]).isEqualTo(1)
        assertThat(opcodeCounts["DEY"]).isEqualTo(1)
        assertThat(opcodeCounts["INC"]).isEqualTo(4)
        assertThat(opcodeCounts["INX"]).isEqualTo(1)
        assertThat(opcodeCounts["INY"]).isEqualTo(1)
        assertThat(opcodeCounts["ADC"]).isEqualTo(8)
        assertThat(opcodeCounts["SBC"]).isEqualTo(8)
        assertThat(opcodeCounts["AND"]).isEqualTo(8)
        assertThat(opcodeCounts["EOR"]).isEqualTo(8)
        assertThat(opcodeCounts["ORA"]).isEqualTo(8)
        assertThat(opcodeCounts["ASL"]).isEqualTo(5)
        assertThat(opcodeCounts["LSR"]).isEqualTo(5)
        assertThat(opcodeCounts["ROL"]).isEqualTo(5)
        assertThat(opcodeCounts["ROR"]).isEqualTo(5)
        assertThat(opcodeCounts["CLC"]).isEqualTo(1)
        assertThat(opcodeCounts["CLD"]).isEqualTo(1)
        assertThat(opcodeCounts["CLI"]).isEqualTo(1)
        assertThat(opcodeCounts["CLV"]).isEqualTo(1)
        assertThat(opcodeCounts["SEC"]).isEqualTo(1)
        assertThat(opcodeCounts["SED"]).isEqualTo(1)
        assertThat(opcodeCounts["SEI"]).isEqualTo(1)
        assertThat(opcodeCounts["CMP"]).isEqualTo(8)
        assertThat(opcodeCounts["CPX"]).isEqualTo(3)
        assertThat(opcodeCounts["CPY"]).isEqualTo(3)
        assertThat(opcodeCounts["BCC"]).isEqualTo(1)
        assertThat(opcodeCounts["BCS"]).isEqualTo(1)
        assertThat(opcodeCounts["BEQ"]).isEqualTo(1)
        assertThat(opcodeCounts["BMI"]).isEqualTo(1)
        assertThat(opcodeCounts["BNE"]).isEqualTo(1)
        assertThat(opcodeCounts["BPL"]).isEqualTo(1)
        assertThat(opcodeCounts["BVC"]).isEqualTo(1)
        assertThat(opcodeCounts["BVS"]).isEqualTo(1)
        assertThat(opcodeCounts["JMP"]).isEqualTo(2)
        assertThat(opcodeCounts["JSR"]).isEqualTo(1)
        assertThat(opcodeCounts["RTS"]).isEqualTo(1)
        assertThat(opcodeCounts["BRK"]).isEqualTo(1)
        assertThat(opcodeCounts["RTI"]).isEqualTo(1)
        assertThat(opcodeCounts["NOP"]).isEqualTo(1)
        assertThat(opcodeCounts["BIT"]).isEqualTo(2)
    }
}