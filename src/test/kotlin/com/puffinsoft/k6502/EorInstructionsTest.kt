package com.puffinsoft.k6502

import com.puffinsoft.k6502.inbuilt.DefaultMemory
import org.assertj.core.api.Assertions
import org.assertj.core.api.SoftAssertions
import org.junit.jupiter.api.Test

class EorInstructionsTest : BaseInstructionsTest() {
    @Test
    fun `test EOR Immediate (0x49) 0x00 ^ 0x00 = 0x00`() {
        val instruction = instructionSet.instructions[0x49]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("EOR")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("IMM")
            it.assertThat(instruction.cycles).isEqualTo(2)
        }

        val memory = Array<UByte>(65536) { 0u }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0x49u
        memory[0x8001] = 0x00u

        val cpu = createCpu(DefaultMemory(memory))
        cpu.a = 0x00u

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(cpu.a).isEqualTo(0x00.toUByte())
            it.assertThat(cpu.statusRegister.zeroFlag).isTrue
            it.assertThat(cpu.statusRegister.negativeFlag).isFalse
        }
    }

    @Test
    fun `test EOR Immediate (0x49) 0xf0 ^ 0x0f = 0xff`() {
        val instruction = instructionSet.instructions[0x49]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("EOR")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("IMM")
            it.assertThat(instruction.cycles).isEqualTo(2)
        }

        val memory = Array<UByte>(65536) { 0u }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0x49u
        memory[0x8001] = 0x0fu

        val cpu = createCpu(DefaultMemory(memory))
        cpu.a = 0xf0u

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(cpu.a).isEqualTo(0xff.toUByte())
            it.assertThat(cpu.statusRegister.zeroFlag).isFalse
            it.assertThat(cpu.statusRegister.negativeFlag).isTrue
        }
    }

    @Test
    fun `test EOR Immediate (0x49) 0x0f ^ 0xf0 = 0xff`() {
        val instruction = instructionSet.instructions[0x49]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("EOR")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("IMM")
            it.assertThat(instruction.cycles).isEqualTo(2)
        }

        val memory = Array<UByte>(65536) { 0u }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0x49u
        memory[0x8001] = 0xf0u

        val cpu = createCpu(DefaultMemory(memory))
        cpu.a = 0x0fu

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(cpu.a).isEqualTo(0xff.toUByte())
            it.assertThat(cpu.statusRegister.zeroFlag).isFalse
            it.assertThat(cpu.statusRegister.negativeFlag).isTrue
        }
    }

    @Test
    fun `test EOR Immediate (0x49) 0xff ^ 0xff = 0x00`() {
        val instruction = instructionSet.instructions[0x49]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("EOR")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("IMM")
            it.assertThat(instruction.cycles).isEqualTo(2)
        }

        val memory = Array<UByte>(65536) { 0u }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0x49u
        memory[0x8001] = 0xffu

        val cpu = createCpu(DefaultMemory(memory))
        cpu.a = 0xffu

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(cpu.a).isEqualTo(0x00.toUByte())
            it.assertThat(cpu.statusRegister.zeroFlag).isTrue
            it.assertThat(cpu.statusRegister.negativeFlag).isFalse
        }
    }

    @Test
    fun `test EOR Zero Page (0x45) 0x00 ^ 0x00 = 0x00`() {
        val instruction = instructionSet.instructions[0x45]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("EOR")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("ZPP")
            it.assertThat(instruction.cycles).isEqualTo(3)
        }

        val memory = Array<UByte>(65536) { 0u }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0x45u
        memory[0x8001] = 0x00u
        memory[0x0000] = 0x00u

        val cpu = createCpu(DefaultMemory(memory))
        cpu.a = 0x00u

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(cpu.a).isEqualTo(0x00.toUByte())
            it.assertThat(cpu.statusRegister.zeroFlag).isTrue
            it.assertThat(cpu.statusRegister.negativeFlag).isFalse
        }
    }

    @Test
    fun `test EOR Zero Page (0x45) 0xf0 ^ 0x0f = 0xff`() {
        val instruction = instructionSet.instructions[0x45]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("EOR")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("ZPP")
            it.assertThat(instruction.cycles).isEqualTo(3)
        }

        val memory = Array<UByte>(65536) { 0u }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0x45u
        memory[0x8001] = 0x00u
        memory[0x0000] = 0x0fu

        val cpu = createCpu(DefaultMemory(memory))
        cpu.a = 0xf0u

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(cpu.a).isEqualTo(0xff.toUByte())
            it.assertThat(cpu.statusRegister.zeroFlag).isFalse
            it.assertThat(cpu.statusRegister.negativeFlag).isTrue
        }
    }

    @Test
    fun `test EOR Zero Page (0x45) 0x0f ^ 0xf0 = 0xff`() {
        val instruction = instructionSet.instructions[0x45]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("EOR")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("ZPP")
            it.assertThat(instruction.cycles).isEqualTo(3)
        }

        val memory = Array<UByte>(65536) { 0u }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0x45u
        memory[0x8001] = 0x00u
        memory[0x0000] = 0xf0u

        val cpu = createCpu(DefaultMemory(memory))
        cpu.a = 0x0fu

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(cpu.a).isEqualTo(0xff.toUByte())
            it.assertThat(cpu.statusRegister.zeroFlag).isFalse
            it.assertThat(cpu.statusRegister.negativeFlag).isTrue
        }
    }

    @Test
    fun `test EOR Zero Page (0x45) 0xff ^ 0xff = 0x00`() {
        val instruction = instructionSet.instructions[0x45]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("EOR")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("ZPP")
            it.assertThat(instruction.cycles).isEqualTo(3)
        }

        val memory = Array<UByte>(65536) { 0u }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0x45u
        memory[0x8001] = 0x00u
        memory[0x0000] = 0xffu

        val cpu = createCpu(DefaultMemory(memory))
        cpu.a = 0xffu

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(cpu.a).isEqualTo(0x00.toUByte())
            it.assertThat(cpu.statusRegister.zeroFlag).isTrue
            it.assertThat(cpu.statusRegister.negativeFlag).isFalse
        }
    }

    @Test
    fun `test EOR Zero Page,X (0x55) 0x00 ^ 0x00 = 0x00`() {
        val instruction = instructionSet.instructions[0x55]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("EOR")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("ZPX")
            it.assertThat(instruction.cycles).isEqualTo(4)
        }

        val memory = Array<UByte>(65536) { 0u }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0x55u
        memory[0x8001] = 0x00u
        memory[0x0001] = 0x00u

        val cpu = createCpu(DefaultMemory(memory))
        cpu.a = 0x00u
        cpu.x = 0x01u

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(cpu.a).isEqualTo(0x00.toUByte())
            it.assertThat(cpu.statusRegister.zeroFlag).isTrue
            it.assertThat(cpu.statusRegister.negativeFlag).isFalse
        }
    }

    @Test
    fun `test EOR Zero Page,X (0x55) 0xf0 ^ 0x0f = 0xff`() {
        val instruction = instructionSet.instructions[0x55]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("EOR")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("ZPX")
            it.assertThat(instruction.cycles).isEqualTo(4)
        }

        val memory = Array<UByte>(65536) { 0u }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0x55u
        memory[0x8001] = 0x00u
        memory[0x0001] = 0x0fu

        val cpu = createCpu(DefaultMemory(memory))
        cpu.a = 0xf0u
        cpu.x = 0x01u

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(cpu.a).isEqualTo(0xff.toUByte())
            it.assertThat(cpu.statusRegister.zeroFlag).isFalse
            it.assertThat(cpu.statusRegister.negativeFlag).isTrue
        }
    }

    @Test
    fun `test EOR Zero Page,X (0x55) 0x0f ^ 0xf0 = 0xff`() {
        val instruction = instructionSet.instructions[0x55]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("EOR")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("ZPX")
            it.assertThat(instruction.cycles).isEqualTo(4)
        }

        val memory = Array<UByte>(65536) { 0u }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0x55u
        memory[0x8001] = 0x00u
        memory[0x0001] = 0xf0u

        val cpu = createCpu(DefaultMemory(memory))
        cpu.a = 0x0fu
        cpu.x = 0x01u

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(cpu.a).isEqualTo(0xff.toUByte())
            it.assertThat(cpu.statusRegister.zeroFlag).isFalse
            it.assertThat(cpu.statusRegister.negativeFlag).isTrue
        }
    }

    @Test
    fun `test EOR Zero Page,X (0x55) 0xff ^ 0xff = 0x00`() {
        val instruction = instructionSet.instructions[0x55]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("EOR")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("ZPX")
            it.assertThat(instruction.cycles).isEqualTo(4)
        }

        val memory = Array<UByte>(65536) { 0u }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0x55u
        memory[0x8001] = 0x00u
        memory[0x0001] = 0xffu

        val cpu = createCpu(DefaultMemory(memory))
        cpu.a = 0xffu
        cpu.x = 0x01u

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(cpu.a).isEqualTo(0x00.toUByte())
            it.assertThat(cpu.statusRegister.zeroFlag).isTrue
            it.assertThat(cpu.statusRegister.negativeFlag).isFalse
        }
    }

    @Test
    fun `test EOR Absolute (0x4d) 0x00 ^ 0x00 = 0x00`() {
        val instruction = instructionSet.instructions[0x4d]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("EOR")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("ABS")
            it.assertThat(instruction.cycles).isEqualTo(4)
        }

        val memory = Array<UByte>(65536) { 0u }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0x4du
        memory[0x8001] = 0x00u
        memory[0x8002] = 0x02u
        memory[0x0200] = 0x00u

        val cpu = createCpu(DefaultMemory(memory))
        cpu.a = 0x00u

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(cpu.a).isEqualTo(0x00.toUByte())
            it.assertThat(cpu.statusRegister.zeroFlag).isTrue
            it.assertThat(cpu.statusRegister.negativeFlag).isFalse
        }
    }

    @Test
    fun `test EOR Absolute (0x4d) 0xf0 ^ 0x0f = 0xff`() {
        val instruction = instructionSet.instructions[0x4d]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("EOR")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("ABS")
            it.assertThat(instruction.cycles).isEqualTo(4)
        }

        val memory = Array<UByte>(65536) { 0u }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0x4du
        memory[0x8001] = 0x00u
        memory[0x8002] = 0x02u
        memory[0x0200] = 0x0fu

        val cpu = createCpu(DefaultMemory(memory))
        cpu.a = 0xf0u

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(cpu.a).isEqualTo(0xff.toUByte())
            it.assertThat(cpu.statusRegister.zeroFlag).isFalse
            it.assertThat(cpu.statusRegister.negativeFlag).isTrue
        }
    }

    @Test
    fun `test EOR Absolute (0x4d) 0x0f ^ 0xf0 = 0xff`() {
        val instruction = instructionSet.instructions[0x4d]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("EOR")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("ABS")
            it.assertThat(instruction.cycles).isEqualTo(4)
        }

        val memory = Array<UByte>(65536) { 0u }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0x4du
        memory[0x8001] = 0x00u
        memory[0x8002] = 0x02u
        memory[0x0200] = 0xf0u

        val cpu = createCpu(DefaultMemory(memory))
        cpu.a = 0x0fu

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(cpu.a).isEqualTo(0xff.toUByte())
            it.assertThat(cpu.statusRegister.zeroFlag).isFalse
            it.assertThat(cpu.statusRegister.negativeFlag).isTrue
        }
    }

    @Test
    fun `test EOR Absolute (0x4d) 0xff ^ 0xff = 0x00`() {
        val instruction = instructionSet.instructions[0x4d]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("EOR")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("ABS")
            it.assertThat(instruction.cycles).isEqualTo(4)
        }

        val memory = Array<UByte>(65536) { 0u }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0x4du
        memory[0x8001] = 0x00u
        memory[0x8002] = 0x02u
        memory[0x0200] = 0xffu

        val cpu = createCpu(DefaultMemory(memory))
        cpu.a = 0xffu

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(cpu.a).isEqualTo(0x00.toUByte())
            it.assertThat(cpu.statusRegister.zeroFlag).isTrue
            it.assertThat(cpu.statusRegister.negativeFlag).isFalse
        }
    }

    @Test
    fun `test EOR Absolute,X (0x5d) 0x00 ^ 0x00 = 0x00`() {
        val instruction = instructionSet.instructions[0x5d]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("EOR")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("ABX")
            it.assertThat(instruction.cycles).isEqualTo(4)
        }

        val memory = Array<UByte>(65536) { 0u }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0x5du
        memory[0x8001] = 0x00u
        memory[0x8002] = 0x02u
        memory[0x0201] = 0x00u

        val cpu = createCpu(DefaultMemory(memory))
        cpu.a = 0x00u
        cpu.x = 0x01u

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(cpu.a).isEqualTo(0x00.toUByte())
            it.assertThat(cpu.statusRegister.zeroFlag).isTrue
            it.assertThat(cpu.statusRegister.negativeFlag).isFalse
        }
    }

    @Test
    fun `test EOR Absolute,X (0x5d) 0xf0 ^ 0x0f = 0xff`() {
        val instruction = instructionSet.instructions[0x5d]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("EOR")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("ABX")
            it.assertThat(instruction.cycles).isEqualTo(4)
        }

        val memory = Array<UByte>(65536) { 0u }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0x5du
        memory[0x8001] = 0x00u
        memory[0x8002] = 0x02u
        memory[0x0201] = 0x0fu

        val cpu = createCpu(DefaultMemory(memory))
        cpu.a = 0xf0u
        cpu.x = 0x01u

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(cpu.a).isEqualTo(0xff.toUByte())
            it.assertThat(cpu.statusRegister.zeroFlag).isFalse
            it.assertThat(cpu.statusRegister.negativeFlag).isTrue
        }
    }

    @Test
    fun `test EOR Absolute,X (0x5d) 0x0f ^ 0xf0 = 0xff`() {
        val instruction = instructionSet.instructions[0x5d]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("EOR")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("ABX")
            it.assertThat(instruction.cycles).isEqualTo(4)
        }

        val memory = Array<UByte>(65536) { 0u }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0x5du
        memory[0x8001] = 0x00u
        memory[0x8002] = 0x02u
        memory[0x0201] = 0xf0u

        val cpu = createCpu(DefaultMemory(memory))
        cpu.a = 0x0fu
        cpu.x = 0x01u

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(cpu.a).isEqualTo(0xff.toUByte())
            it.assertThat(cpu.statusRegister.zeroFlag).isFalse
            it.assertThat(cpu.statusRegister.negativeFlag).isTrue
        }
    }

    @Test
    fun `test EOR Absolute,X (0x5d) 0xff ^ 0xff = 0x00`() {
        val instruction = instructionSet.instructions[0x5d]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("EOR")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("ABX")
            it.assertThat(instruction.cycles).isEqualTo(4)
        }

        val memory = Array<UByte>(65536) { 0u }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0x5du
        memory[0x8001] = 0x00u
        memory[0x8002] = 0x02u
        memory[0x0201] = 0xffu

        val cpu = createCpu(DefaultMemory(memory))
        cpu.a = 0xffu
        cpu.x = 0x01u

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(cpu.a).isEqualTo(0x00.toUByte())
            it.assertThat(cpu.statusRegister.zeroFlag).isTrue
            it.assertThat(cpu.statusRegister.negativeFlag).isFalse
        }
    }

    @Test
    fun `test EOR Absolute,X (0x5d) 0x00 ^ 0x00 = 0x00 with cross page`() {
        val instruction = instructionSet.instructions[0x5d]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("EOR")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("ABX")
            it.assertThat(instruction.cycles).isEqualTo(4)
        }

        val memory = Array<UByte>(65536) { 0u }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0x5du
        memory[0x8001] = 0xffu
        memory[0x8002] = 0x02u
        memory[0x0300] = 0x00u

        val cpu = createCpu(DefaultMemory(memory))
        cpu.a = 0x00u
        cpu.x = 0x01u

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles + 1)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(cpu.a).isEqualTo(0x00.toUByte())
            it.assertThat(cpu.statusRegister.zeroFlag).isTrue
            it.assertThat(cpu.statusRegister.negativeFlag).isFalse
        }
    }

    @Test
    fun `test EOR Absolute,X (0x5d) 0xf0 ^ 0x0f = 0xff with cross page`() {
        val instruction = instructionSet.instructions[0x5d]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("EOR")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("ABX")
            it.assertThat(instruction.cycles).isEqualTo(4)
        }

        val memory = Array<UByte>(65536) { 0u }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0x5du
        memory[0x8001] = 0xffu
        memory[0x8002] = 0x02u
        memory[0x0300] = 0x0fu

        val cpu = createCpu(DefaultMemory(memory))
        cpu.a = 0xf0u
        cpu.x = 0x01u

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles + 1)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(cpu.a).isEqualTo(0xff.toUByte())
            it.assertThat(cpu.statusRegister.zeroFlag).isFalse
            it.assertThat(cpu.statusRegister.negativeFlag).isTrue
        }
    }

    @Test
    fun `test EOR Absolute,X (0x5d) 0x0f ^ 0xf0 = 0xff with cross page`() {
        val instruction = instructionSet.instructions[0x5d]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("EOR")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("ABX")
            it.assertThat(instruction.cycles).isEqualTo(4)
        }

        val memory = Array<UByte>(65536) { 0u }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0x5du
        memory[0x8001] = 0xffu
        memory[0x8002] = 0x02u
        memory[0x0300] = 0xf0u

        val cpu = createCpu(DefaultMemory(memory))
        cpu.a = 0x0fu
        cpu.x = 0x01u

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles + 1)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(cpu.a).isEqualTo(0xff.toUByte())
            it.assertThat(cpu.statusRegister.zeroFlag).isFalse
            it.assertThat(cpu.statusRegister.negativeFlag).isTrue
        }
    }

    @Test
    fun `test EOR Absolute,X (0x5d) 0xff ^ 0xff = 0x00 with cross page`() {
        val instruction = instructionSet.instructions[0x5d]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("EOR")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("ABX")
            it.assertThat(instruction.cycles).isEqualTo(4)
        }

        val memory = Array<UByte>(65536) { 0u }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0x5du
        memory[0x8001] = 0xffu
        memory[0x8002] = 0x02u
        memory[0x0300] = 0xffu

        val cpu = createCpu(DefaultMemory(memory))
        cpu.a = 0xffu
        cpu.x = 0x01u

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles + 1)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(cpu.a).isEqualTo(0x00.toUByte())
            it.assertThat(cpu.statusRegister.zeroFlag).isTrue
            it.assertThat(cpu.statusRegister.negativeFlag).isFalse
        }
    }

    @Test
    fun `test EOR Absolute,Y (0x59) 0x00 ^ 0x00 = 0x00`() {
        val instruction = instructionSet.instructions[0x59]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("EOR")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("ABY")
            it.assertThat(instruction.cycles).isEqualTo(4)
        }

        val memory = Array<UByte>(65536) { 0u }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0x59u
        memory[0x8001] = 0x00u
        memory[0x8002] = 0x02u
        memory[0x0201] = 0x00u

        val cpu = createCpu(DefaultMemory(memory))
        cpu.a = 0x00u
        cpu.y = 0x01u

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(cpu.a).isEqualTo(0x00.toUByte())
            it.assertThat(cpu.statusRegister.zeroFlag).isTrue
            it.assertThat(cpu.statusRegister.negativeFlag).isFalse
        }
    }

    @Test
    fun `test EOR Absolute,Y (0x59) 0xf0 ^ 0x0f = 0xff`() {
        val instruction = instructionSet.instructions[0x59]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("EOR")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("ABY")
            it.assertThat(instruction.cycles).isEqualTo(4)
        }

        val memory = Array<UByte>(65536) { 0u }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0x59u
        memory[0x8001] = 0x00u
        memory[0x8002] = 0x02u
        memory[0x0201] = 0x0fu

        val cpu = createCpu(DefaultMemory(memory))
        cpu.a = 0xf0u
        cpu.y = 0x01u

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(cpu.a).isEqualTo(0xff.toUByte())
            it.assertThat(cpu.statusRegister.zeroFlag).isFalse
            it.assertThat(cpu.statusRegister.negativeFlag).isTrue
        }
    }

    @Test
    fun `test EOR Absolute,Y (0x59) 0x0f ^ 0xf0 = 0xff`() {
        val instruction = instructionSet.instructions[0x59]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("EOR")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("ABY")
            it.assertThat(instruction.cycles).isEqualTo(4)
        }

        val memory = Array<UByte>(65536) { 0u }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0x59u
        memory[0x8001] = 0x00u
        memory[0x8002] = 0x02u
        memory[0x0201] = 0xf0u

        val cpu = createCpu(DefaultMemory(memory))
        cpu.a = 0x0fu
        cpu.y = 0x01u

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(cpu.a).isEqualTo(0xff.toUByte())
            it.assertThat(cpu.statusRegister.zeroFlag).isFalse
            it.assertThat(cpu.statusRegister.negativeFlag).isTrue
        }
    }

    @Test
    fun `test EOR Absolute,Y (0x59) 0xff ^ 0xff = 0x00`() {
        val instruction = instructionSet.instructions[0x59]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("EOR")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("ABY")
            it.assertThat(instruction.cycles).isEqualTo(4)
        }

        val memory = Array<UByte>(65536) { 0u }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0x59u
        memory[0x8001] = 0x00u
        memory[0x8002] = 0x02u
        memory[0x0201] = 0xffu

        val cpu = createCpu(DefaultMemory(memory))
        cpu.a = 0xffu
        cpu.y = 0x01u

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(cpu.a).isEqualTo(0x00.toUByte())
            it.assertThat(cpu.statusRegister.zeroFlag).isTrue
            it.assertThat(cpu.statusRegister.negativeFlag).isFalse
        }
    }

    @Test
    fun `test EOR Absolute,Y (0x59) 0x00 ^ 0x00 = 0x00 with cross page`() {
        val instruction = instructionSet.instructions[0x59]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("EOR")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("ABY")
            it.assertThat(instruction.cycles).isEqualTo(4)
        }

        val memory = Array<UByte>(65536) { 0u }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0x59u
        memory[0x8001] = 0xffu
        memory[0x8002] = 0x02u
        memory[0x0300] = 0x00u

        val cpu = createCpu(DefaultMemory(memory))
        cpu.a = 0x00u
        cpu.y = 0x01u

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles + 1)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(cpu.a).isEqualTo(0x00.toUByte())
            it.assertThat(cpu.statusRegister.zeroFlag).isTrue
            it.assertThat(cpu.statusRegister.negativeFlag).isFalse
        }
    }

    @Test
    fun `test EOR Absolute,Y (0x59) 0xf0 ^ 0x0f = 0xff with cross page`() {
        val instruction = instructionSet.instructions[0x59]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("EOR")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("ABY")
            it.assertThat(instruction.cycles).isEqualTo(4)
        }

        val memory = Array<UByte>(65536) { 0u }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0x59u
        memory[0x8001] = 0xffu
        memory[0x8002] = 0x02u
        memory[0x0300] = 0x0fu

        val cpu = createCpu(DefaultMemory(memory))
        cpu.a = 0xf0u
        cpu.y = 0x01u

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles + 1)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(cpu.a).isEqualTo(0xff.toUByte())
            it.assertThat(cpu.statusRegister.zeroFlag).isFalse
            it.assertThat(cpu.statusRegister.negativeFlag).isTrue
        }
    }

    @Test
    fun `test EOR Absolute,Y (0x59) 0x0f ^ 0xf0 = 0xff with cross page`() {
        val instruction = instructionSet.instructions[0x59]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("EOR")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("ABY")
            it.assertThat(instruction.cycles).isEqualTo(4)
        }

        val memory = Array<UByte>(65536) { 0u }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0x59u
        memory[0x8001] = 0xffu
        memory[0x8002] = 0x02u
        memory[0x0300] = 0xf0u

        val cpu = createCpu(DefaultMemory(memory))
        cpu.a = 0x0fu
        cpu.y = 0x01u

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles + 1)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(cpu.a).isEqualTo(0xff.toUByte())
            it.assertThat(cpu.statusRegister.zeroFlag).isFalse
            it.assertThat(cpu.statusRegister.negativeFlag).isTrue
        }
    }

    @Test
    fun `test EOR Absolute,Y (0x59) 0xff ^ 0xff = 0x00 with cross page`() {
        val instruction = instructionSet.instructions[0x59]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("EOR")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("ABY")
            it.assertThat(instruction.cycles).isEqualTo(4)
        }

        val memory = Array<UByte>(65536) { 0u }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0x59u
        memory[0x8001] = 0xffu
        memory[0x8002] = 0x02u
        memory[0x0300] = 0xffu

        val cpu = createCpu(DefaultMemory(memory))
        cpu.a = 0xffu
        cpu.y = 0x01u

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles + 1)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(cpu.a).isEqualTo(0x00.toUByte())
            it.assertThat(cpu.statusRegister.zeroFlag).isTrue
            it.assertThat(cpu.statusRegister.negativeFlag).isFalse
        }
    }

    @Test
    fun `test EOR Indirect,X (0x41) 0x00 ^ 0x00 = 0x00`() {
        val instruction = instructionSet.instructions[0x41]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("EOR")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("IZX")
            it.assertThat(instruction.cycles).isEqualTo(6)
        }

        val memory = Array<UByte>(65536) { 0u }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0x41u
        memory[0x8001] = 0xfeu
        memory[0x00fe] = 0x00u
        memory[0x00ff] = 0x02u
        memory[0x0200] = 0x00u

        val cpu = createCpu(DefaultMemory(memory))
        cpu.a = 0x00u
        cpu.x = 0x01u

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(cpu.a).isEqualTo(0x00.toUByte())
            it.assertThat(cpu.statusRegister.zeroFlag).isTrue
            it.assertThat(cpu.statusRegister.negativeFlag).isFalse
        }
    }

    @Test
    fun `test EOR Indirect,X (0x41) 0xf0 ^ 0x0f = 0xff`() {
        val instruction = instructionSet.instructions[0x41]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("EOR")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("IZX")
            it.assertThat(instruction.cycles).isEqualTo(6)
        }

        val memory = Array<UByte>(65536) { 0u }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0x41u
        memory[0x8001] = 0xfdu
        memory[0x00fe] = 0x00u
        memory[0x00ff] = 0x02u
        memory[0x0200] = 0x0fu

        val cpu = createCpu(DefaultMemory(memory))
        cpu.a = 0xf0u
        cpu.x = 0x01u

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(cpu.a).isEqualTo(0xff.toUByte())
            it.assertThat(cpu.statusRegister.zeroFlag).isFalse
            it.assertThat(cpu.statusRegister.negativeFlag).isTrue
        }
    }

    @Test
    fun `test EOR Indirect,X (0x41) 0x0f ^ 0xf0 = 0xff`() {
        val instruction = instructionSet.instructions[0x41]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("EOR")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("IZX")
            it.assertThat(instruction.cycles).isEqualTo(6)
        }

        val memory = Array<UByte>(65536) { 0u }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0x41u
        memory[0x8001] = 0xfdu
        memory[0x00fe] = 0x00u
        memory[0x00ff] = 0x02u
        memory[0x0200] = 0xf0u

        val cpu = createCpu(DefaultMemory(memory))
        cpu.a = 0x0fu
        cpu.x = 0x01u

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(cpu.a).isEqualTo(0xff.toUByte())
            it.assertThat(cpu.statusRegister.zeroFlag).isFalse
            it.assertThat(cpu.statusRegister.negativeFlag).isTrue
        }
    }

    @Test
    fun `test EOR Indirect,X (0x41) 0xff ^ 0xff = 0x00`() {
        val instruction = instructionSet.instructions[0x41]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("EOR")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("IZX")
            it.assertThat(instruction.cycles).isEqualTo(6)
        }

        val memory = Array<UByte>(65536) { 0u }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0x41u
        memory[0x8001] = 0xfdu
        memory[0x00fe] = 0x00u
        memory[0x00ff] = 0x02u
        memory[0x0200] = 0xffu

        val cpu = createCpu(DefaultMemory(memory))
        cpu.a = 0xffu
        cpu.x = 0x01u

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(cpu.a).isEqualTo(0x00.toUByte())
            it.assertThat(cpu.statusRegister.zeroFlag).isTrue
            it.assertThat(cpu.statusRegister.negativeFlag).isFalse
        }
    }

    @Test
    fun `test EOR Indirect,X (0x41) 0x00 ^ 0x00 = 0x00 with wrap`() {
        val instruction = instructionSet.instructions[0x41]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("EOR")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("IZX")
            it.assertThat(instruction.cycles).isEqualTo(6)
        }

        val memory = Array<UByte>(65536) { 0u }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0x41u
        memory[0x8001] = 0xfeu
        memory[0x00ff] = 0x00u
        memory[0x0000] = 0x02u
        memory[0x0200] = 0x00u

        val cpu = createCpu(DefaultMemory(memory))
        cpu.a = 0x00u
        cpu.x = 0x01u

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(cpu.a).isEqualTo(0x00.toUByte())
            it.assertThat(cpu.statusRegister.zeroFlag).isTrue
            it.assertThat(cpu.statusRegister.negativeFlag).isFalse
        }
    }

    @Test
    fun `test EOR Indirect,X (0x41) 0xf0 ^ 0x0f = 0xff with wrap`() {
        val instruction = instructionSet.instructions[0x41]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("EOR")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("IZX")
            it.assertThat(instruction.cycles).isEqualTo(6)
        }

        val memory = Array<UByte>(65536) { 0u }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0x41u
        memory[0x8001] = 0xfeu
        memory[0x00ff] = 0x00u
        memory[0x0000] = 0x02u
        memory[0x0200] = 0x0fu

        val cpu = createCpu(DefaultMemory(memory))
        cpu.a = 0xf0u
        cpu.x = 0x01u

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(cpu.a).isEqualTo(0xff.toUByte())
            it.assertThat(cpu.statusRegister.zeroFlag).isFalse
            it.assertThat(cpu.statusRegister.negativeFlag).isTrue
        }
    }

    @Test
    fun `test EOR Indirect,X (0x41) 0x0f ^ 0xf0 = 0xff with wrap`() {
        val instruction = instructionSet.instructions[0x41]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("EOR")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("IZX")
            it.assertThat(instruction.cycles).isEqualTo(6)
        }

        val memory = Array<UByte>(65536) { 0u }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0x41u
        memory[0x8001] = 0xfeu
        memory[0x00ff] = 0x00u
        memory[0x0000] = 0x02u
        memory[0x0200] = 0xf0u

        val cpu = createCpu(DefaultMemory(memory))
        cpu.a = 0x0fu
        cpu.x = 0x01u

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(cpu.a).isEqualTo(0xff.toUByte())
            it.assertThat(cpu.statusRegister.zeroFlag).isFalse
            it.assertThat(cpu.statusRegister.negativeFlag).isTrue
        }
    }

    @Test
    fun `test EOR Indirect,X (0x41) 0xff ^ 0xff = 0x00 with wrap`() {
        val instruction = instructionSet.instructions[0x41]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("EOR")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("IZX")
            it.assertThat(instruction.cycles).isEqualTo(6)
        }

        val memory = Array<UByte>(65536) { 0u }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0x41u
        memory[0x8001] = 0xfeu
        memory[0x00ff] = 0x00u
        memory[0x0000] = 0x02u
        memory[0x0200] = 0xffu

        val cpu = createCpu(DefaultMemory(memory))
        cpu.a = 0xffu
        cpu.x = 0x01u

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(cpu.a).isEqualTo(0x00.toUByte())
            it.assertThat(cpu.statusRegister.zeroFlag).isTrue
            it.assertThat(cpu.statusRegister.negativeFlag).isFalse
        }
    }

    @Test
    fun `test EOR Indirect,Y (0x51) 0x00 ^ 0x00 = 0x00`() {
        val instruction = instructionSet.instructions[0x51]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("EOR")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("IZY")
            it.assertThat(instruction.cycles).isEqualTo(5)
        }

        val memory = Array<UByte>(65536) { 0u }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0x51u
        memory[0x8001] = 0xfeu
        memory[0x00fe] = 0x00u
        memory[0x00ff] = 0x02u
        memory[0x0201] = 0x00u

        val cpu = createCpu(DefaultMemory(memory))
        cpu.a = 0x00u
        cpu.y = 0x01u

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(cpu.a).isEqualTo(0x00.toUByte())
            it.assertThat(cpu.statusRegister.zeroFlag).isTrue
            it.assertThat(cpu.statusRegister.negativeFlag).isFalse
        }
    }

    @Test
    fun `test EOR Indirect,Y (0x51) 0xf0 ^ 0x0f = 0xff`() {
        val instruction = instructionSet.instructions[0x51]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("EOR")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("IZY")
            it.assertThat(instruction.cycles).isEqualTo(5)
        }

        val memory = Array<UByte>(65536) { 0u }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0x51u
        memory[0x8001] = 0xfeu
        memory[0x00fe] = 0x00u
        memory[0x00ff] = 0x02u
        memory[0x0201] = 0x0fu

        val cpu = createCpu(DefaultMemory(memory))
        cpu.a = 0xf0u
        cpu.y = 0x01u

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(cpu.a).isEqualTo(0xff.toUByte())
            it.assertThat(cpu.statusRegister.zeroFlag).isFalse
            it.assertThat(cpu.statusRegister.negativeFlag).isTrue
        }
    }

    @Test
    fun `test EOR Indirect,Y (0x51) 0x0f ^ 0xf0 = 0xff`() {
        val instruction = instructionSet.instructions[0x51]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("EOR")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("IZY")
            it.assertThat(instruction.cycles).isEqualTo(5)
        }

        val memory = Array<UByte>(65536) { 0u }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0x51u
        memory[0x8001] = 0xfeu
        memory[0x00fe] = 0x00u
        memory[0x00ff] = 0x02u
        memory[0x0201] = 0xf0u

        val cpu = createCpu(DefaultMemory(memory))
        cpu.a = 0x0fu
        cpu.y = 0x01u

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(cpu.a).isEqualTo(0xff.toUByte())
            it.assertThat(cpu.statusRegister.zeroFlag).isFalse
            it.assertThat(cpu.statusRegister.negativeFlag).isTrue
        }
    }

    @Test
    fun `test EOR Indirect,Y (0x51) 0xff ^ 0xff = 0x00`() {
        val instruction = instructionSet.instructions[0x51]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("EOR")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("IZY")
            it.assertThat(instruction.cycles).isEqualTo(5)
        }

        val memory = Array<UByte>(65536) { 0u }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0x51u
        memory[0x8001] = 0xfeu
        memory[0x00fe] = 0x00u
        memory[0x00ff] = 0x02u
        memory[0x0201] = 0xffu

        val cpu = createCpu(DefaultMemory(memory))
        cpu.a = 0xffu
        cpu.y = 0x01u

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(cpu.a).isEqualTo(0x00.toUByte())
            it.assertThat(cpu.statusRegister.zeroFlag).isTrue
            it.assertThat(cpu.statusRegister.negativeFlag).isFalse
        }
    }

    @Test
    fun `test EOR Indirect,Y (0x51) 0x00 ^ 0x00 = 0x00 with wrap`() {
        val instruction = instructionSet.instructions[0x51]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("EOR")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("IZY")
            it.assertThat(instruction.cycles).isEqualTo(5)
        }

        val memory = Array<UByte>(65536) { 0u }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0x51u
        memory[0x8001] = 0xffu
        memory[0x00ff] = 0x00u
        memory[0x0000] = 0x02u
        memory[0x0200] = 0x00u

        val cpu = createCpu(DefaultMemory(memory))
        cpu.a = 0x00u
        cpu.y = 0x01u

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(cpu.a).isEqualTo(0x00.toUByte())
            it.assertThat(cpu.statusRegister.zeroFlag).isTrue
            it.assertThat(cpu.statusRegister.negativeFlag).isFalse
        }
    }

    @Test
    fun `test EOR Indirect,Y (0x51) 0xf0 ^ 0x0f = 0xff with wrap`() {
        val instruction = instructionSet.instructions[0x51]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("EOR")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("IZY")
            it.assertThat(instruction.cycles).isEqualTo(5)
        }

        val memory = Array<UByte>(65536) { 0u }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0x51u
        memory[0x8001] = 0xffu
        memory[0x00ff] = 0x00u
        memory[0x0000] = 0x02u
        memory[0x0201] = 0x0fu

        val cpu = createCpu(DefaultMemory(memory))
        cpu.a = 0xf0u
        cpu.y = 0x01u

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(cpu.a).isEqualTo(0xff.toUByte())
            it.assertThat(cpu.statusRegister.zeroFlag).isFalse
            it.assertThat(cpu.statusRegister.negativeFlag).isTrue
        }
    }

    @Test
    fun `test EOR Indirect,Y (0x51) 0x0f ^ 0xf0 = 0xff with wrap`() {
        val instruction = instructionSet.instructions[0x51]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("EOR")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("IZY")
            it.assertThat(instruction.cycles).isEqualTo(5)
        }

        val memory = Array<UByte>(65536) { 0u }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0x51u
        memory[0x8001] = 0xffu
        memory[0x00ff] = 0x00u
        memory[0x0000] = 0x02u
        memory[0x0201] = 0xf0u

        val cpu = createCpu(DefaultMemory(memory))
        cpu.a = 0x0fu
        cpu.y = 0x01u

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(cpu.a).isEqualTo(0xff.toUByte())
            it.assertThat(cpu.statusRegister.zeroFlag).isFalse
            it.assertThat(cpu.statusRegister.negativeFlag).isTrue
        }
    }

    @Test
    fun `test EOR Indirect,Y (0x51) 0xff ^ 0xff = 0x00 with wrap`() {
        val instruction = instructionSet.instructions[0x51]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("EOR")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("IZY")
            it.assertThat(instruction.cycles).isEqualTo(5)
        }

        val memory = Array<UByte>(65536) { 0u }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0x51u
        memory[0x8001] = 0xffu
        memory[0x00ff] = 0x00u
        memory[0x0000] = 0x02u
        memory[0x0201] = 0xffu

        val cpu = createCpu(DefaultMemory(memory))
        cpu.a = 0xffu
        cpu.y = 0x01u

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(cpu.a).isEqualTo(0x00.toUByte())
            it.assertThat(cpu.statusRegister.zeroFlag).isTrue
            it.assertThat(cpu.statusRegister.negativeFlag).isFalse
        }
    }

    @Test
    fun `test EOR Indirect,Y (0x51) 0x00 ^ 0x00 = 0x00 with cross page`() {
        val instruction = instructionSet.instructions[0x51]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("EOR")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("IZY")
            it.assertThat(instruction.cycles).isEqualTo(5)
        }

        val memory = Array<UByte>(65536) { 0u }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0x51u
        memory[0x8001] = 0xfeu
        memory[0x00fe] = 0xffu
        memory[0x00ff] = 0x02u
        memory[0x0300] = 0x00u

        val cpu = createCpu(DefaultMemory(memory))
        cpu.a = 0x00u
        cpu.y = 0x01u

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles + 1)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(cpu.a).isEqualTo(0x00.toUByte())
            it.assertThat(cpu.statusRegister.zeroFlag).isTrue
            it.assertThat(cpu.statusRegister.negativeFlag).isFalse
        }
    }

    @Test
    fun `test EOR Indirect,Y (0x51) 0xf0 ^ 0x0f = 0xff with cross page`() {
        val instruction = instructionSet.instructions[0x51]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("EOR")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("IZY")
            it.assertThat(instruction.cycles).isEqualTo(5)
        }

        val memory = Array<UByte>(65536) { 0u }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0x51u
        memory[0x8001] = 0xfeu
        memory[0x00fe] = 0xffu
        memory[0x00ff] = 0x02u
        memory[0x0300] = 0x0fu

        val cpu = createCpu(DefaultMemory(memory))
        cpu.a = 0xf0u
        cpu.y = 0x01u

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles + 1)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(cpu.a).isEqualTo(0xff.toUByte())
            it.assertThat(cpu.statusRegister.zeroFlag).isFalse
            it.assertThat(cpu.statusRegister.negativeFlag).isTrue
        }
    }

    @Test
    fun `test EOR Indirect,Y (0x51) 0x0f ^ 0xf0 = 0xff with cross page`() {
        val instruction = instructionSet.instructions[0x51]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("EOR")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("IZY")
            it.assertThat(instruction.cycles).isEqualTo(5)
        }

        val memory = Array<UByte>(65536) { 0u }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0x51u
        memory[0x8001] = 0xfeu
        memory[0x00fe] = 0xffu
        memory[0x00ff] = 0x02u
        memory[0x0300] = 0xf0u

        val cpu = createCpu(DefaultMemory(memory))
        cpu.a = 0x0fu
        cpu.y = 0x01u

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles + 1)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(cpu.a).isEqualTo(0xff.toUByte())
            it.assertThat(cpu.statusRegister.zeroFlag).isFalse
            it.assertThat(cpu.statusRegister.negativeFlag).isTrue
        }
    }

    @Test
    fun `test EOR Indirect,Y (0x51) 0xff ^ 0xff = 0x00 with cross page`() {
        val instruction = instructionSet.instructions[0x51]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("EOR")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("IZY")
            it.assertThat(instruction.cycles).isEqualTo(5)
        }

        val memory = Array<UByte>(65536) { 0u }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0x51u
        memory[0x8001] = 0xfeu
        memory[0x00fe] = 0xffu
        memory[0x00ff] = 0x02u
        memory[0x0300] = 0xffu

        val cpu = createCpu(DefaultMemory(memory))
        cpu.a = 0xffu
        cpu.y = 0x01u

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles + 1)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(cpu.a).isEqualTo(0x00.toUByte())
            it.assertThat(cpu.statusRegister.zeroFlag).isTrue
            it.assertThat(cpu.statusRegister.negativeFlag).isFalse
        }
    }
}