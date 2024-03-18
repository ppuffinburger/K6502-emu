package com.puffinsoft.k6502

import com.puffinsoft.k6502.inbuilt.DefaultMemory
import org.assertj.core.api.Assertions
import org.assertj.core.api.SoftAssertions
import org.junit.jupiter.api.Test

class FlagInstructionsTest : BaseInstructionsTest() {
    @Test
    fun `test CLC (0x18) 0 to 0`() {
        val instruction = instructionSet.instructions[0x18]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("CLC")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("IMP")
            it.assertThat(instruction.cycles).isEqualTo(2)
        }

        val memory = Array<UByte>(65536) { 0u }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0x18u

        val cpu = createCpu(DefaultMemory(memory))
        cpu.statusRegister.carryFlag = false

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(cpu.statusRegister.carryFlag).isFalse
        }
    }

    @Test
    fun `test CLC (0x18) 1 to 0`() {
        val instruction = instructionSet.instructions[0x18]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("CLC")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("IMP")
            it.assertThat(instruction.cycles).isEqualTo(2)
        }

        val memory = Array<UByte>(65536) { 0u }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0x18u

        val cpu = createCpu(DefaultMemory(memory))
        cpu.statusRegister.carryFlag = true

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(cpu.statusRegister.carryFlag).isFalse
        }
    }

    @Test
    fun `test CLD (0xd8) 0 to 0`() {
        val instruction = instructionSet.instructions[0xd8]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("CLD")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("IMP")
            it.assertThat(instruction.cycles).isEqualTo(2)
        }

        val memory = Array<UByte>(65536) { 0u }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0xd8u

        val cpu = createCpu(DefaultMemory(memory))
        cpu.statusRegister.decimalFlag = false

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(cpu.statusRegister.decimalFlag).isFalse
        }
    }

    @Test
    fun `test CLD (0xd8) 1 to 0`() {
        val instruction = instructionSet.instructions[0xd8]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("CLD")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("IMP")
            it.assertThat(instruction.cycles).isEqualTo(2)
        }

        val memory = Array<UByte>(65536) { 0u }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0xd8u

        val cpu = createCpu(DefaultMemory(memory))
        cpu.statusRegister.decimalFlag = true

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(cpu.statusRegister.decimalFlag).isFalse
        }
    }

    @Test
    fun `test CLI (0x58) 0 to 0`() {
        val instruction = instructionSet.instructions[0x58]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("CLI")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("IMP")
            it.assertThat(instruction.cycles).isEqualTo(2)
        }

        val memory = Array<UByte>(65536) { 0u }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0x58u

        val cpu = createCpu(DefaultMemory(memory))
        cpu.statusRegister.interruptDisabledFlag = false

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(cpu.statusRegister.interruptDisabledFlag).isFalse
        }
    }

    @Test
    fun `test CLI (0x58) 1 to 0`() {
        val instruction = instructionSet.instructions[0x58]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("CLI")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("IMP")
            it.assertThat(instruction.cycles).isEqualTo(2)
        }

        val memory = Array<UByte>(65536) { 0u }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0x58u

        val cpu = createCpu(DefaultMemory(memory))
        cpu.statusRegister.interruptDisabledFlag = true

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(cpu.statusRegister.interruptDisabledFlag).isFalse
        }
    }

    @Test
    fun `test CLV (0xb8) 0 to 0`() {
        val instruction = instructionSet.instructions[0xb8]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("CLV")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("IMP")
            it.assertThat(instruction.cycles).isEqualTo(2)
        }

        val memory = Array<UByte>(65536) { 0u }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0xb8u

        val cpu = createCpu(DefaultMemory(memory))
        cpu.statusRegister.overflowFlag = false

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(cpu.statusRegister.overflowFlag).isFalse
        }
    }

    @Test
    fun `test CLV (0xb8) 1 to 0`() {
        val instruction = instructionSet.instructions[0xb8]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("CLV")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("IMP")
            it.assertThat(instruction.cycles).isEqualTo(2)
        }

        val memory = Array<UByte>(65536) { 0u }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0xb8u

        val cpu = createCpu(DefaultMemory(memory))
        cpu.statusRegister.overflowFlag = true

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(cpu.statusRegister.overflowFlag).isFalse
        }
    }

    @Test
    fun `test SEC (0x38) 0 to 1`() {
        val instruction = instructionSet.instructions[0x38]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("SEC")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("IMP")
            it.assertThat(instruction.cycles).isEqualTo(2)
        }

        val memory = Array<UByte>(65536) { 0u }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0x38u

        val cpu = createCpu(DefaultMemory(memory))
        cpu.statusRegister.carryFlag = false

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(cpu.statusRegister.carryFlag).isTrue
        }
    }

    @Test
    fun `test SEC (0x38) 1 to 1`() {
        val instruction = instructionSet.instructions[0x38]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("SEC")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("IMP")
            it.assertThat(instruction.cycles).isEqualTo(2)
        }

        val memory = Array<UByte>(65536) { 0u }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0x38u

        val cpu = createCpu(DefaultMemory(memory))
        cpu.statusRegister.carryFlag = true

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(cpu.statusRegister.carryFlag).isTrue
        }
    }

    @Test
    fun `test SED (0xf8) 0 to 1`() {
        val instruction = instructionSet.instructions[0xf8]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("SED")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("IMP")
            it.assertThat(instruction.cycles).isEqualTo(2)
        }

        val memory = Array<UByte>(65536) { 0u }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0xf8u

        val cpu = createCpu(DefaultMemory(memory))
        cpu.statusRegister.decimalFlag = false

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(cpu.statusRegister.decimalFlag).isTrue
        }
    }

    @Test
    fun `test SED (0xf8) 1 to 1`() {
        val instruction = instructionSet.instructions[0xf8]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("SED")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("IMP")
            it.assertThat(instruction.cycles).isEqualTo(2)
        }

        val memory = Array<UByte>(65536) { 0u }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0xf8u

        val cpu = createCpu(DefaultMemory(memory))
        cpu.statusRegister.decimalFlag = true

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(cpu.statusRegister.decimalFlag).isTrue
        }
    }

    @Test
    fun `test SEI (0x78) 0 to 1`() {
        val instruction = instructionSet.instructions[0x78]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("SEI")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("IMP")
            it.assertThat(instruction.cycles).isEqualTo(2)
        }

        val memory = Array<UByte>(65536) { 0u }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0x78u

        val cpu = createCpu(DefaultMemory(memory))
        cpu.statusRegister.interruptDisabledFlag = false

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(cpu.statusRegister.interruptDisabledFlag).isTrue
        }
    }

    @Test
    fun `test SEI (0x78) 1 to 1`() {
        val instruction = instructionSet.instructions[0x78]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("SEI")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("IMP")
            it.assertThat(instruction.cycles).isEqualTo(2)
        }

        val memory = Array<UByte>(65536) { 0u }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0x78u

        val cpu = createCpu(DefaultMemory(memory))
        cpu.statusRegister.interruptDisabledFlag = true

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(cpu.statusRegister.interruptDisabledFlag).isTrue
        }
    }
}