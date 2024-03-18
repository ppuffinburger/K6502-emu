package com.puffinsoft.k6502

import com.puffinsoft.k6502.inbuilt.DefaultMemory
import org.assertj.core.api.Assertions
import org.assertj.core.api.SoftAssertions
import org.junit.jupiter.api.Test

class StoreYInstructionsTest : BaseInstructionsTest() {
    @Test
    fun `test STY Zero Page (0x84)`() {
        val instruction = instructionSet.instructions[0x84]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("STY")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("ZPP")
            it.assertThat(instruction.cycles).isEqualTo(3)
        }

        val memory = Array<UByte>(65536) { 0u }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0x84u
        memory[0x8001] = 0xffu

        val cpu = createCpu(DefaultMemory(memory))
        cpu.y = 0x42u

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(memory[0x00ff]).isEqualTo(0x42.toUByte())
        }
    }

    @Test
    fun `test STY Zero Page,X (0x94)`() {
        val instruction = instructionSet.instructions[0x94]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("STY")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("ZPX")
            it.assertThat(instruction.cycles).isEqualTo(4)
        }

        val memory = Array<UByte>(65536) { 0u }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0x94u
        memory[0x8001] = 0x0fu

        val cpu = createCpu(DefaultMemory(memory))
        cpu.y = 0x42u
        cpu.x = 0xf0u

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(memory[0x00ff]).isEqualTo(0x42.toUByte())
        }
    }

    @Test
    fun `test STY Absolute (0x8c)`() {
        val instruction = instructionSet.instructions[0x8c]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("STY")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("ABS")
            it.assertThat(instruction.cycles).isEqualTo(4)
        }

        val memory = Array<UByte>(65536) { 0u }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0x8cu
        memory[0x8001] = 0x00u
        memory[0x8002] = 0x02u

        val cpu = createCpu(DefaultMemory(memory))
        cpu.y = 0x42u

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(memory[0x0200]).isEqualTo(0x42.toUByte())
        }
    }
}