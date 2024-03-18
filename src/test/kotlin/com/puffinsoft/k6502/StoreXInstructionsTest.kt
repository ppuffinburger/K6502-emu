package com.puffinsoft.k6502

import com.puffinsoft.k6502.inbuilt.DefaultMemory
import org.assertj.core.api.Assertions
import org.assertj.core.api.SoftAssertions
import org.junit.jupiter.api.Test

class StoreXInstructionsTest : BaseInstructionsTest() {
    @Test
    fun `test STX Zero Page (0x86)`() {
        val instruction = instructionSet.instructions[0x86]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("STX")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("ZPP")
            it.assertThat(instruction.cycles).isEqualTo(3)
        }

        val memory = Array<UByte>(65536) { 0u }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0x86u
        memory[0x8001] = 0xffu

        val cpu = createCpu(DefaultMemory(memory))
        cpu.x = 0x42u

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(memory[0x00ff]).isEqualTo(0x42.toUByte())
        }
    }

    @Test
    fun `test STX Zero Page,Y (0x96)`() {
        val instruction = instructionSet.instructions[0x96]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("STX")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("ZPY")
            it.assertThat(instruction.cycles).isEqualTo(4)
        }

        val memory = Array<UByte>(65536) { 0u }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0x96u
        memory[0x8001] = 0x0fu

        val cpu = createCpu(DefaultMemory(memory))
        cpu.x = 0x42u
        cpu.y = 0xf0u

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(memory[0x00ff]).isEqualTo(0x42.toUByte())
        }
    }

    @Test
    fun `test STX Absolute (0x8e)`() {
        val instruction = instructionSet.instructions[0x8e]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("STX")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("ABS")
            it.assertThat(instruction.cycles).isEqualTo(4)
        }

        val memory = Array<UByte>(65536) { 0u }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0x8eu
        memory[0x8001] = 0x00u
        memory[0x8002] = 0x02u

        val cpu = createCpu(DefaultMemory(memory))
        cpu.x = 0x42u

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(memory[0x0200]).isEqualTo(0x42.toUByte())
        }
    }
}