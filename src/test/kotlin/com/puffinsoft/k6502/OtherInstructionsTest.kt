package com.puffinsoft.k6502

import com.puffinsoft.k6502.inbuilt.DefaultMemory
import org.assertj.core.api.Assertions
import org.assertj.core.api.SoftAssertions
import org.junit.jupiter.api.Test

class OtherInstructionsTest : BaseInstructionsTest() {
    @Test
    fun `test NOP Implied (0xea)`() {
        val instruction = instructionSet.instructions[0xea]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("NOP")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("IMP")
            it.assertThat(instruction.cycles).isEqualTo(2)
        }

        val memory = Array<UByte>(65536) { 0x7fu }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0xeau

        val cpu = createCpu(DefaultMemory(memory))

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(cpu.programCounter).isEqualTo(0x8001.toShort())
        }
    }

    @Test
    fun `test BIT Zero Page (0x24) 0x00 bit 0x00 (Zno)`() {
        val instruction = instructionSet.instructions[0x24]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("BIT")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("ZPP")
            it.assertThat(instruction.cycles).isEqualTo(3)
        }

        val memory = Array<UByte>(65536) { 0x7fu }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0x24u
        memory[0x8001] = 0x00u
        memory[0x0000] = 0x00u

        val cpu = createCpu(DefaultMemory(memory))
        cpu.a = 0x00u

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(cpu.statusRegister.zeroFlag).isTrue
            it.assertThat(cpu.statusRegister.negativeFlag).isFalse
            it.assertThat(cpu.statusRegister.overflowFlag).isFalse
        }
    }

    @Test
    fun `test BIT Zero Page (0x24) 0x04 bit 0x84 (zNo)`() {
        val instruction = instructionSet.instructions[0x24]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("BIT")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("ZPP")
            it.assertThat(instruction.cycles).isEqualTo(3)
        }

        val memory = Array<UByte>(65536) { 0x7fu }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0x24u
        memory[0x8001] = 0x00u
        memory[0x0000] = 0x84u

        val cpu = createCpu(DefaultMemory(memory))
        cpu.a = 0x04u

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(cpu.statusRegister.zeroFlag).isFalse
            it.assertThat(cpu.statusRegister.negativeFlag).isTrue
            it.assertThat(cpu.statusRegister.overflowFlag).isFalse
        }
    }

    @Test
    fun `test BIT Zero Page (0x24) 0x04 bit 0x74 (znO)`() {
        val instruction = instructionSet.instructions[0x24]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("BIT")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("ZPP")
            it.assertThat(instruction.cycles).isEqualTo(3)
        }

        val memory = Array<UByte>(65536) { 0x7fu }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0x24u
        memory[0x8001] = 0x00u
        memory[0x0000] = 0x74u

        val cpu = createCpu(DefaultMemory(memory))
        cpu.a = 0x04u

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(cpu.statusRegister.zeroFlag).isFalse
            it.assertThat(cpu.statusRegister.negativeFlag).isFalse
            it.assertThat(cpu.statusRegister.overflowFlag).isTrue
        }
    }

    @Test
    fun `test BIT Absolute (0x2c) 0x00 bit 0x00 (Zno)`() {
        val instruction = instructionSet.instructions[0x2c]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("BIT")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("ABS")
            it.assertThat(instruction.cycles).isEqualTo(4)
        }

        val memory = Array<UByte>(65536) { 0x7fu }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0x2cu
        memory[0x8001] = 0x00u
        memory[0x8002] = 0xffu
        memory[0xff00] = 0x00u

        val cpu = createCpu(DefaultMemory(memory))
        cpu.a = 0x00u

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(cpu.statusRegister.zeroFlag).isTrue
            it.assertThat(cpu.statusRegister.negativeFlag).isFalse
            it.assertThat(cpu.statusRegister.overflowFlag).isFalse
        }
    }

    @Test
    fun `test BIT Absolute (0x2c) 0x04 bit 0x84 (zNo)`() {
        val instruction = instructionSet.instructions[0x2c]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("BIT")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("ABS")
            it.assertThat(instruction.cycles).isEqualTo(4)
        }

        val memory = Array<UByte>(65536) { 0x7fu }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0x2cu
        memory[0x8001] = 0x00u
        memory[0x8002] = 0xffu
        memory[0xff00] = 0x84u

        val cpu = createCpu(DefaultMemory(memory))
        cpu.a = 0x04u

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(cpu.statusRegister.zeroFlag).isFalse
            it.assertThat(cpu.statusRegister.negativeFlag).isTrue
            it.assertThat(cpu.statusRegister.overflowFlag).isFalse
        }
    }

    @Test
    fun `test BIT Absolute (0x2c) 0x04 bit 0x74 (znO)`() {
        val instruction = instructionSet.instructions[0x2c]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("BIT")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("ABS")
            it.assertThat(instruction.cycles).isEqualTo(4)
        }

        val memory = Array<UByte>(65536) { 0x7fu }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0x2cu
        memory[0x8001] = 0x00u
        memory[0x8002] = 0xffu
        memory[0xff00] = 0x74u

        val cpu = createCpu(DefaultMemory(memory))
        cpu.a = 0x04u

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(cpu.statusRegister.zeroFlag).isFalse
            it.assertThat(cpu.statusRegister.negativeFlag).isFalse
            it.assertThat(cpu.statusRegister.overflowFlag).isTrue
        }
    }
}