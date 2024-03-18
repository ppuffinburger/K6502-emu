package com.puffinsoft.k6502

import com.puffinsoft.k6502.inbuilt.DefaultMemory
import org.assertj.core.api.Assertions
import org.assertj.core.api.SoftAssertions
import org.junit.jupiter.api.Test

class CompareAccumulatorInstructionsTest : BaseInstructionsTest() {
    @Test
    fun `test CMP Immediate (0xc9) 0x00 equals 0x00`() {
        val instruction = instructionSet.instructions[0xc9]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("CMP")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("IMM")
            it.assertThat(instruction.cycles).isEqualTo(2)
        }

        val memory = Array<UByte>(65536) { 0x7fu }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0xc9u
        memory[0x8001] = 0x00u

        val cpu = createCpu(DefaultMemory(memory))
        cpu.a = 0x00u

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(cpu.a).isEqualTo(0x00.toUByte())
            it.assertThat(cpu.statusRegister.zeroFlag).isTrue
            it.assertThat(cpu.statusRegister.negativeFlag).isFalse
            it.assertThat(cpu.statusRegister.carryFlag).isTrue
        }
    }

    @Test
    fun `test CMP Immediate (0xc9) 0x00 less than 0x01`() {
        val instruction = instructionSet.instructions[0xc9]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("CMP")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("IMM")
            it.assertThat(instruction.cycles).isEqualTo(2)
        }

        val memory = Array<UByte>(65536) { 0x7fu }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0xc9u
        memory[0x8001] = 0x01u

        val cpu = createCpu(DefaultMemory(memory))
        cpu.a = 0x00u

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(cpu.a).isEqualTo(0x00.toUByte())
            it.assertThat(cpu.statusRegister.zeroFlag).isFalse
            it.assertThat(cpu.statusRegister.negativeFlag).isTrue
            it.assertThat(cpu.statusRegister.carryFlag).isFalse
        }
    }

    @Test
    fun `test CMP Immediate (0xc9) 0x01 greater than 0x00`() {
        val instruction = instructionSet.instructions[0xc9]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("CMP")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("IMM")
            it.assertThat(instruction.cycles).isEqualTo(2)
        }

        val memory = Array<UByte>(65536) { 0x7fu }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0xc9u
        memory[0x8001] = 0x00u

        val cpu = createCpu(DefaultMemory(memory))
        cpu.a = 0x01u

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(cpu.a).isEqualTo(0x01.toUByte())
            it.assertThat(cpu.statusRegister.zeroFlag).isFalse
            it.assertThat(cpu.statusRegister.negativeFlag).isFalse
            it.assertThat(cpu.statusRegister.carryFlag).isTrue
        }
    }

    @Test
    fun `test CMP Zero Page (0xc5) 0x00 equals 0x00`() {
        val instruction = instructionSet.instructions[0xc5]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("CMP")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("ZPP")
            it.assertThat(instruction.cycles).isEqualTo(3)
        }

        val memory = Array<UByte>(65536) { 0x7fu }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0xc5u
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
            it.assertThat(cpu.statusRegister.carryFlag).isTrue
        }
    }

    @Test
    fun `test CMP Zero Page (0xc5) 0x00 less than 0x01`() {
        val instruction = instructionSet.instructions[0xc5]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("CMP")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("ZPP")
            it.assertThat(instruction.cycles).isEqualTo(3)
        }

        val memory = Array<UByte>(65536) { 0x7fu }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0xc5u
        memory[0x8001] = 0x00u
        memory[0x0000] = 0x01u

        val cpu = createCpu(DefaultMemory(memory))
        cpu.a = 0x00u

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(cpu.a).isEqualTo(0x00.toUByte())
            it.assertThat(cpu.statusRegister.zeroFlag).isFalse
            it.assertThat(cpu.statusRegister.negativeFlag).isTrue
            it.assertThat(cpu.statusRegister.carryFlag).isFalse
        }
    }

    @Test
    fun `test CMP Zero Page (0xc5) 0x01 greater than 0x00`() {
        val instruction = instructionSet.instructions[0xc5]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("CMP")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("ZPP")
            it.assertThat(instruction.cycles).isEqualTo(3)
        }

        val memory = Array<UByte>(65536) { 0x7fu }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0xc5u
        memory[0x8001] = 0x00u
        memory[0x0000] = 0x00u

        val cpu = createCpu(DefaultMemory(memory))
        cpu.a = 0x01u

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(cpu.a).isEqualTo(0x01.toUByte())
            it.assertThat(cpu.statusRegister.zeroFlag).isFalse
            it.assertThat(cpu.statusRegister.negativeFlag).isFalse
            it.assertThat(cpu.statusRegister.carryFlag).isTrue
        }
    }

    @Test
    fun `test CMP Zero Page,X (0xd5) 0x00 equals 0x00`() {
        val instruction = instructionSet.instructions[0xd5]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("CMP")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("ZPX")
            it.assertThat(instruction.cycles).isEqualTo(4)
        }

        val memory = Array<UByte>(65536) { 0x7fu }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0xd5u
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
            it.assertThat(cpu.statusRegister.carryFlag).isTrue
        }
    }

    @Test
    fun `test CMP Zero Page,X (0xd5) 0x00 less than 0x01`() {
        val instruction = instructionSet.instructions[0xd5]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("CMP")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("ZPX")
            it.assertThat(instruction.cycles).isEqualTo(4)
        }

        val memory = Array<UByte>(65536) { 0x7fu }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0xd5u
        memory[0x8001] = 0x00u
        memory[0x0001] = 0x01u

        val cpu = createCpu(DefaultMemory(memory))
        cpu.a = 0x00u
        cpu.x = 0x01u

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(cpu.a).isEqualTo(0x00.toUByte())
            it.assertThat(cpu.statusRegister.zeroFlag).isFalse
            it.assertThat(cpu.statusRegister.negativeFlag).isTrue
            it.assertThat(cpu.statusRegister.carryFlag).isFalse
        }
    }

    @Test
    fun `test CMP Zero Page,X (0xd5) 0x01 greater than 0x00`() {
        val instruction = instructionSet.instructions[0xd5]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("CMP")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("ZPX")
            it.assertThat(instruction.cycles).isEqualTo(4)
        }

        val memory = Array<UByte>(65536) { 0x7fu }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0xd5u
        memory[0x8001] = 0x00u
        memory[0x0001] = 0x00u

        val cpu = createCpu(DefaultMemory(memory))
        cpu.a = 0x01u
        cpu.x = 0x01u

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(cpu.a).isEqualTo(0x01.toUByte())
            it.assertThat(cpu.statusRegister.zeroFlag).isFalse
            it.assertThat(cpu.statusRegister.negativeFlag).isFalse
            it.assertThat(cpu.statusRegister.carryFlag).isTrue
        }
    }

    @Test
    fun `test CMP Absolute (0xcd) 0x00 equals 0x00`() {
        val instruction = instructionSet.instructions[0xcd]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("CMP")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("ABS")
            it.assertThat(instruction.cycles).isEqualTo(4)
        }

        val memory = Array<UByte>(65536) { 0x7fu }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0xcdu
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
            it.assertThat(cpu.statusRegister.carryFlag).isTrue
        }
    }

    @Test
    fun `test CMP Absolute (0xcd) 0x00 less than 0x01`() {
        val instruction = instructionSet.instructions[0xcd]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("CMP")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("ABS")
            it.assertThat(instruction.cycles).isEqualTo(4)
        }

        val memory = Array<UByte>(65536) { 0x7fu }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0xcdu
        memory[0x8001] = 0x00u
        memory[0x8002] = 0x02u
        memory[0x0200] = 0x01u

        val cpu = createCpu(DefaultMemory(memory))
        cpu.a = 0x00u

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(cpu.a).isEqualTo(0x00.toUByte())
            it.assertThat(cpu.statusRegister.zeroFlag).isFalse
            it.assertThat(cpu.statusRegister.negativeFlag).isTrue
            it.assertThat(cpu.statusRegister.carryFlag).isFalse
        }
    }

    @Test
    fun `test CMP Absolute (0xcd) 0x01 greater than 0x00`() {
        val instruction = instructionSet.instructions[0xcd]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("CMP")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("ABS")
            it.assertThat(instruction.cycles).isEqualTo(4)
        }

        val memory = Array<UByte>(65536) { 0x7fu }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0xcdu
        memory[0x8001] = 0x00u
        memory[0x8002] = 0x02u
        memory[0x0200] = 0x00u

        val cpu = createCpu(DefaultMemory(memory))
        cpu.a = 0x01u

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(cpu.a).isEqualTo(0x01.toUByte())
            it.assertThat(cpu.statusRegister.zeroFlag).isFalse
            it.assertThat(cpu.statusRegister.negativeFlag).isFalse
            it.assertThat(cpu.statusRegister.carryFlag).isTrue
        }
    }

    @Test
    fun `test CMP Absolute,X (0xdd) 0x00 equals 0x00`() {
        val instruction = instructionSet.instructions[0xdd]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("CMP")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("ABX")
            it.assertThat(instruction.cycles).isEqualTo(4)
        }

        val memory = Array<UByte>(65536) { 0x7fu }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0xddu
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
            it.assertThat(cpu.statusRegister.carryFlag).isTrue
        }
    }

    @Test
    fun `test CMP Absolute,X (0xdd) 0x00 less than 0x01`() {
        val instruction = instructionSet.instructions[0xdd]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("CMP")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("ABX")
            it.assertThat(instruction.cycles).isEqualTo(4)
        }

        val memory = Array<UByte>(65536) { 0x7fu }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0xddu
        memory[0x8001] = 0x00u
        memory[0x8002] = 0x02u
        memory[0x0201] = 0x01u

        val cpu = createCpu(DefaultMemory(memory))
        cpu.a = 0x00u
        cpu.x = 0x01u

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(cpu.a).isEqualTo(0x00.toUByte())
            it.assertThat(cpu.statusRegister.zeroFlag).isFalse
            it.assertThat(cpu.statusRegister.negativeFlag).isTrue
            it.assertThat(cpu.statusRegister.carryFlag).isFalse
        }
    }

    @Test
    fun `test CMP Absolute,X (0xdd) 0x01 greater than 0x00`() {
        val instruction = instructionSet.instructions[0xdd]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("CMP")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("ABX")
            it.assertThat(instruction.cycles).isEqualTo(4)
        }

        val memory = Array<UByte>(65536) { 0x7fu }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0xddu
        memory[0x8001] = 0x00u
        memory[0x8002] = 0x02u
        memory[0x0201] = 0x00u

        val cpu = createCpu(DefaultMemory(memory))
        cpu.a = 0x01u
        cpu.x = 0x01u

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(cpu.a).isEqualTo(0x01.toUByte())
            it.assertThat(cpu.statusRegister.zeroFlag).isFalse
            it.assertThat(cpu.statusRegister.negativeFlag).isFalse
            it.assertThat(cpu.statusRegister.carryFlag).isTrue
        }
    }

    @Test
    fun `test CMP Absolute,X (0xdd) 0x00 equals 0x00 with cross page`() {
        val instruction = instructionSet.instructions[0xdd]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("CMP")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("ABX")
            it.assertThat(instruction.cycles).isEqualTo(4)
        }

        val memory = Array<UByte>(65536) { 0x7fu }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0xddu
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
            it.assertThat(cpu.statusRegister.carryFlag).isTrue
        }
    }

    @Test
    fun `test CMP Absolute,X (0xdd) 0x00 less than 0x01 with cross page`() {
        val instruction = instructionSet.instructions[0xdd]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("CMP")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("ABX")
            it.assertThat(instruction.cycles).isEqualTo(4)
        }

        val memory = Array<UByte>(65536) { 0x7fu }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0xddu
        memory[0x8001] = 0xffu
        memory[0x8002] = 0x02u
        memory[0x0300] = 0x01u

        val cpu = createCpu(DefaultMemory(memory))
        cpu.a = 0x00u
        cpu.x = 0x01u

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles + 1)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(cpu.a).isEqualTo(0x00.toUByte())
            it.assertThat(cpu.statusRegister.zeroFlag).isFalse
            it.assertThat(cpu.statusRegister.negativeFlag).isTrue
            it.assertThat(cpu.statusRegister.carryFlag).isFalse
        }
    }

    @Test
    fun `test CMP Absolute,X (0xdd) 0x01 greater than 0x00 with cross page`() {
        val instruction = instructionSet.instructions[0xdd]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("CMP")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("ABX")
            it.assertThat(instruction.cycles).isEqualTo(4)
        }

        val memory = Array<UByte>(65536) { 0x7fu }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0xddu
        memory[0x8001] = 0xffu
        memory[0x8002] = 0x02u
        memory[0x0300] = 0x00u

        val cpu = createCpu(DefaultMemory(memory))
        cpu.a = 0x01u
        cpu.x = 0x01u

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles + 1)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(cpu.a).isEqualTo(0x01.toUByte())
            it.assertThat(cpu.statusRegister.zeroFlag).isFalse
            it.assertThat(cpu.statusRegister.negativeFlag).isFalse
            it.assertThat(cpu.statusRegister.carryFlag).isTrue
        }
    }

    @Test
    fun `test CMP Absolute,Y (0xd9) 0x00 equals 0x00`() {
        val instruction = instructionSet.instructions[0xd9]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("CMP")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("ABY")
            it.assertThat(instruction.cycles).isEqualTo(4)
        }

        val memory = Array<UByte>(65536) { 0x7fu }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0xd9u
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
            it.assertThat(cpu.statusRegister.carryFlag).isTrue
        }
    }

    @Test
    fun `test CMP Absolute,Y (0xd9) 0x00 less than 0x01`() {
        val instruction = instructionSet.instructions[0xd9]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("CMP")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("ABY")
            it.assertThat(instruction.cycles).isEqualTo(4)
        }

        val memory = Array<UByte>(65536) { 0x7fu }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0xd9u
        memory[0x8001] = 0x00u
        memory[0x8002] = 0x02u
        memory[0x0201] = 0x01u

        val cpu = createCpu(DefaultMemory(memory))
        cpu.a = 0x00u
        cpu.y = 0x01u

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(cpu.a).isEqualTo(0x00.toUByte())
            it.assertThat(cpu.statusRegister.zeroFlag).isFalse
            it.assertThat(cpu.statusRegister.negativeFlag).isTrue
            it.assertThat(cpu.statusRegister.carryFlag).isFalse
        }
    }

    @Test
    fun `test CMP Absolute,Y (0xd9) 0x01 greater than 0x00`() {
        val instruction = instructionSet.instructions[0xd9]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("CMP")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("ABY")
            it.assertThat(instruction.cycles).isEqualTo(4)
        }

        val memory = Array<UByte>(65536) { 0x7fu }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0xd9u
        memory[0x8001] = 0x00u
        memory[0x8002] = 0x02u
        memory[0x0201] = 0x00u

        val cpu = createCpu(DefaultMemory(memory))
        cpu.a = 0x01u
        cpu.y = 0x01u

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(cpu.a).isEqualTo(0x01.toUByte())
            it.assertThat(cpu.statusRegister.zeroFlag).isFalse
            it.assertThat(cpu.statusRegister.negativeFlag).isFalse
            it.assertThat(cpu.statusRegister.carryFlag).isTrue
        }
    }

    @Test
    fun `test CMP Absolute,Y (0xd9) 0x00 equals 0x00 with cross page`() {
        val instruction = instructionSet.instructions[0xd9]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("CMP")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("ABY")
            it.assertThat(instruction.cycles).isEqualTo(4)
        }

        val memory = Array<UByte>(65536) { 0x7fu }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0xd9u
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
            it.assertThat(cpu.statusRegister.carryFlag).isTrue
        }
    }

    @Test
    fun `test CMP Absolute,Y (0xd9) 0x00 less than 0x01 with cross page`() {
        val instruction = instructionSet.instructions[0xd9]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("CMP")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("ABY")
            it.assertThat(instruction.cycles).isEqualTo(4)
        }

        val memory = Array<UByte>(65536) { 0x7fu }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0xd9u
        memory[0x8001] = 0xffu
        memory[0x8002] = 0x02u
        memory[0x0300] = 0x01u

        val cpu = createCpu(DefaultMemory(memory))
        cpu.a = 0x00u
        cpu.y = 0x01u

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles + 1)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(cpu.a).isEqualTo(0x00.toUByte())
            it.assertThat(cpu.statusRegister.zeroFlag).isFalse
            it.assertThat(cpu.statusRegister.negativeFlag).isTrue
            it.assertThat(cpu.statusRegister.carryFlag).isFalse
        }
    }

    @Test
    fun `test CMP Absolute,Y (0xd9) 0x01 greater than 0x00 with cross page`() {
        val instruction = instructionSet.instructions[0xd9]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("CMP")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("ABY")
            it.assertThat(instruction.cycles).isEqualTo(4)
        }

        val memory = Array<UByte>(65536) { 0x7fu }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0xd9u
        memory[0x8001] = 0xffu
        memory[0x8002] = 0x02u
        memory[0x0300] = 0x00u

        val cpu = createCpu(DefaultMemory(memory))
        cpu.a = 0x01u
        cpu.y = 0x01u

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles + 1)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(cpu.a).isEqualTo(0x01.toUByte())
            it.assertThat(cpu.statusRegister.zeroFlag).isFalse
            it.assertThat(cpu.statusRegister.negativeFlag).isFalse
            it.assertThat(cpu.statusRegister.carryFlag).isTrue
        }
    }

    @Test
    fun `test CMP Indirect,X (0xc1) 0x00 equals 0x00`() {
        val instruction = instructionSet.instructions[0xc1]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("CMP")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("IZX")
            it.assertThat(instruction.cycles).isEqualTo(6)
        }

        val memory = Array<UByte>(65536) { 0x7fu }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0xc1u
        memory[0x8001] = 0xfdu
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
            it.assertThat(cpu.statusRegister.carryFlag).isTrue
        }
    }

    @Test
    fun `test CMP Indirect,X (0xc1) 0x00 less than 0x01`() {
        val instruction = instructionSet.instructions[0xc1]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("CMP")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("IZX")
            it.assertThat(instruction.cycles).isEqualTo(6)
        }

        val memory = Array<UByte>(65536) { 0x7fu }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0xc1u
        memory[0x8001] = 0xfdu
        memory[0x00fe] = 0x00u
        memory[0x00ff] = 0x02u
        memory[0x0200] = 0x01u

        val cpu = createCpu(DefaultMemory(memory))
        cpu.a = 0x00u
        cpu.x = 0x01u

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(cpu.a).isEqualTo(0x00.toUByte())
            it.assertThat(cpu.statusRegister.zeroFlag).isFalse
            it.assertThat(cpu.statusRegister.negativeFlag).isTrue
            it.assertThat(cpu.statusRegister.carryFlag).isFalse
        }
    }

    @Test
    fun `test CMP Indirect,X (0xc1) 0x01 greater than 0x00`() {
        val instruction = instructionSet.instructions[0xc1]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("CMP")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("IZX")
            it.assertThat(instruction.cycles).isEqualTo(6)
        }

        val memory = Array<UByte>(65536) { 0x7fu }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0xc1u
        memory[0x8001] = 0xfdu
        memory[0x00fe] = 0x00u
        memory[0x00ff] = 0x02u
        memory[0x0200] = 0x00u

        val cpu = createCpu(DefaultMemory(memory))
        cpu.a = 0x01u
        cpu.x = 0x01u

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(cpu.a).isEqualTo(0x01.toUByte())
            it.assertThat(cpu.statusRegister.zeroFlag).isFalse
            it.assertThat(cpu.statusRegister.negativeFlag).isFalse
            it.assertThat(cpu.statusRegister.carryFlag).isTrue
        }
    }

    @Test
    fun `test CMP Indirect,X (0xc1) 0x00 equals 0x00 with wrap`() {
        val instruction = instructionSet.instructions[0xc1]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("CMP")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("IZX")
            it.assertThat(instruction.cycles).isEqualTo(6)
        }

        val memory = Array<UByte>(65536) { 0x7fu }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0xc1u
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
            it.assertThat(cpu.statusRegister.carryFlag).isTrue
        }
    }

    @Test
    fun `test CMP Indirect,X (0xc1) 0x00 less than 0x01 with wrap`() {
        val instruction = instructionSet.instructions[0xc1]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("CMP")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("IZX")
            it.assertThat(instruction.cycles).isEqualTo(6)
        }

        val memory = Array<UByte>(65536) { 0x7fu }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0xc1u
        memory[0x8001] = 0xfeu
        memory[0x00ff] = 0x00u
        memory[0x0000] = 0x02u
        memory[0x0200] = 0x01u

        val cpu = createCpu(DefaultMemory(memory))
        cpu.a = 0x00u
        cpu.x = 0x01u

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(cpu.a).isEqualTo(0x00.toUByte())
            it.assertThat(cpu.statusRegister.zeroFlag).isFalse
            it.assertThat(cpu.statusRegister.negativeFlag).isTrue
            it.assertThat(cpu.statusRegister.carryFlag).isFalse
        }
    }

    @Test
    fun `test CMP Indirect,X (0xc1) 0x01 greater than 0x00 with wrap`() {
        val instruction = instructionSet.instructions[0xc1]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("CMP")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("IZX")
            it.assertThat(instruction.cycles).isEqualTo(6)
        }

        val memory = Array<UByte>(65536) { 0x7fu }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0xc1u
        memory[0x8001] = 0xfeu
        memory[0x00ff] = 0x00u
        memory[0x0000] = 0x02u
        memory[0x0200] = 0x00u

        val cpu = createCpu(DefaultMemory(memory))
        cpu.a = 0x01u
        cpu.x = 0x01u

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(cpu.a).isEqualTo(0x01.toUByte())
            it.assertThat(cpu.statusRegister.zeroFlag).isFalse
            it.assertThat(cpu.statusRegister.negativeFlag).isFalse
            it.assertThat(cpu.statusRegister.carryFlag).isTrue
        }
    }

    @Test
    fun `test CMP Indirect,Y (0xd1) 0x00 equals 0x00`() {
        val instruction = instructionSet.instructions[0xd1]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("CMP")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("IZY")
            it.assertThat(instruction.cycles).isEqualTo(5)
        }

        val memory = Array<UByte>(65536) { 0x7fu }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0xd1u
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
            it.assertThat(cpu.statusRegister.carryFlag).isTrue
        }
    }

    @Test
    fun `test CMP Indirect,Y (0xd1) 0x00 less than 0x01`() {
        val instruction = instructionSet.instructions[0xd1]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("CMP")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("IZY")
            it.assertThat(instruction.cycles).isEqualTo(5)
        }

        val memory = Array<UByte>(65536) { 0x7fu }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0xd1u
        memory[0x8001] = 0xfeu
        memory[0x00fe] = 0x00u
        memory[0x00ff] = 0x02u
        memory[0x0201] = 0x01u

        val cpu = createCpu(DefaultMemory(memory))
        cpu.a = 0x00u
        cpu.y = 0x01u

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(cpu.a).isEqualTo(0x00.toUByte())
            it.assertThat(cpu.statusRegister.zeroFlag).isFalse
            it.assertThat(cpu.statusRegister.negativeFlag).isTrue
            it.assertThat(cpu.statusRegister.carryFlag).isFalse
        }
    }

    @Test
    fun `test CMP Indirect,Y (0xd1) 0x01 greater than 0x00`() {
        val instruction = instructionSet.instructions[0xd1]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("CMP")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("IZY")
            it.assertThat(instruction.cycles).isEqualTo(5)
        }

        val memory = Array<UByte>(65536) { 0x7fu }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0xd1u
        memory[0x8001] = 0xfeu
        memory[0x00fe] = 0x00u
        memory[0x00ff] = 0x02u
        memory[0x0201] = 0x00u

        val cpu = createCpu(DefaultMemory(memory))
        cpu.a = 0x01u
        cpu.y = 0x01u

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(cpu.a).isEqualTo(0x01.toUByte())
            it.assertThat(cpu.statusRegister.zeroFlag).isFalse
            it.assertThat(cpu.statusRegister.negativeFlag).isFalse
            it.assertThat(cpu.statusRegister.carryFlag).isTrue
        }
    }

    @Test
    fun `test CMP Indirect,Y (0xd1) 0x00 equals 0x00 with wrap`() {
        val instruction = instructionSet.instructions[0xd1]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("CMP")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("IZY")
            it.assertThat(instruction.cycles).isEqualTo(5)
        }

        val memory = Array<UByte>(65536) { 0x7fu }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0xd1u
        memory[0x8001] = 0xffu
        memory[0x00ff] = 0x00u
        memory[0x0000] = 0x02u
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
            it.assertThat(cpu.statusRegister.carryFlag).isTrue
        }
    }

    @Test
    fun `test CMP Indirect,Y (0xd1) 0x00 less than 0x01 with wrap`() {
        val instruction = instructionSet.instructions[0xd1]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("CMP")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("IZY")
            it.assertThat(instruction.cycles).isEqualTo(5)
        }

        val memory = Array<UByte>(65536) { 0x7fu }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0xd1u
        memory[0x8001] = 0xfeu
        memory[0x00fe] = 0x00u
        memory[0x00ff] = 0x02u
        memory[0x0201] = 0x01u

        val cpu = createCpu(DefaultMemory(memory))
        cpu.a = 0x00u
        cpu.y = 0x01u

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(cpu.a).isEqualTo(0x00.toUByte())
            it.assertThat(cpu.statusRegister.zeroFlag).isFalse
            it.assertThat(cpu.statusRegister.negativeFlag).isTrue
            it.assertThat(cpu.statusRegister.carryFlag).isFalse
        }
    }

    @Test
    fun `test CMP Indirect,Y (0xd1) 0x01 greater than 0x00 with wrap`() {
        val instruction = instructionSet.instructions[0xd1]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("CMP")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("IZY")
            it.assertThat(instruction.cycles).isEqualTo(5)
        }

        val memory = Array<UByte>(65536) { 0x7fu }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0xd1u
        memory[0x8001] = 0xfeu
        memory[0x00fe] = 0x00u
        memory[0x00ff] = 0x02u
        memory[0x0201] = 0x00u

        val cpu = createCpu(DefaultMemory(memory))
        cpu.a = 0x01u
        cpu.y = 0x01u

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(cpu.a).isEqualTo(0x01.toUByte())
            it.assertThat(cpu.statusRegister.zeroFlag).isFalse
            it.assertThat(cpu.statusRegister.negativeFlag).isFalse
            it.assertThat(cpu.statusRegister.carryFlag).isTrue
        }
    }

    @Test
    fun `test CMP Indirect,Y (0xd1) 0x00 equals 0x00 with cross page`() {
        val instruction = instructionSet.instructions[0xd1]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("CMP")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("IZY")
            it.assertThat(instruction.cycles).isEqualTo(5)
        }

        val memory = Array<UByte>(65536) { 0x7fu }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0xd1u
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
            it.assertThat(cpu.statusRegister.carryFlag).isTrue
        }
    }

    @Test
    fun `test CMP Indirect,Y (0xd1) 0x00 less than 0x01 with cross page`() {
        val instruction = instructionSet.instructions[0xd1]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("CMP")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("IZY")
            it.assertThat(instruction.cycles).isEqualTo(5)
        }

        val memory = Array<UByte>(65536) { 0x7fu }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0xd1u
        memory[0x8001] = 0xfeu
        memory[0x00fe] = 0xffu
        memory[0x00ff] = 0x02u
        memory[0x0300] = 0x01u

        val cpu = createCpu(DefaultMemory(memory))
        cpu.a = 0x00u
        cpu.y = 0x01u

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles + 1)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(cpu.a).isEqualTo(0x00.toUByte())
            it.assertThat(cpu.statusRegister.zeroFlag).isFalse
            it.assertThat(cpu.statusRegister.negativeFlag).isTrue
            it.assertThat(cpu.statusRegister.carryFlag).isFalse
        }
    }

    @Test
    fun `test CMP Indirect,Y (0xd1) 0x01 greater than 0x00 with cross page`() {
        val instruction = instructionSet.instructions[0xd1]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("CMP")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("IZY")
            it.assertThat(instruction.cycles).isEqualTo(5)
        }

        val memory = Array<UByte>(65536) { 0x7fu }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0xd1u
        memory[0x8001] = 0xfeu
        memory[0x00fe] = 0xffu
        memory[0x00ff] = 0x02u
        memory[0x0300] = 0x00u

        val cpu = createCpu(DefaultMemory(memory))
        cpu.a = 0x01u
        cpu.y = 0x01u

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles + 1)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(cpu.a).isEqualTo(0x01.toUByte())
            it.assertThat(cpu.statusRegister.zeroFlag).isFalse
            it.assertThat(cpu.statusRegister.negativeFlag).isFalse
            it.assertThat(cpu.statusRegister.carryFlag).isTrue
        }
    }
}