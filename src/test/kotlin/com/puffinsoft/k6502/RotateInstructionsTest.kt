package com.puffinsoft.k6502

import com.puffinsoft.k6502.inbuilt.DefaultMemory
import org.assertj.core.api.Assertions
import org.assertj.core.api.SoftAssertions
import org.junit.jupiter.api.Test

class RotateInstructionsTest : BaseInstructionsTest() {
    @Test
    fun `test ROL Accumulator (0x2a) 0-0x00 becomes 0-0x00`() {
        val instruction = instructionSet.instructions[0x2a]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("ROL")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("ACC")
            it.assertThat(instruction.cycles).isEqualTo(2)
        }

        val memory = Array<UByte>(65536) { 0u }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0x2au

        val cpu = createCpu(DefaultMemory(memory))
        cpu.a = 0x00u
        cpu.statusRegister.carryFlag = false

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(cpu.a).isEqualTo(0x00.toUByte())
            it.assertThat(cpu.statusRegister.zeroFlag).isTrue
            it.assertThat(cpu.statusRegister.negativeFlag).isFalse
            it.assertThat(cpu.statusRegister.carryFlag).isFalse
        }
    }

    @Test
    fun `test ROL Accumulator (0x2a) 0-0x80 becomes 1-0x00`() {
        val instruction = instructionSet.instructions[0x2a]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("ROL")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("ACC")
            it.assertThat(instruction.cycles).isEqualTo(2)
        }

        val memory = Array<UByte>(65536) { 0u }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0x2au

        val cpu = createCpu(DefaultMemory(memory))
        cpu.a = 0x80u
        cpu.statusRegister.carryFlag = false

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
    fun `test ROL Accumulator (0x2a) 1-0x40 becomes 0-0x81`() {
        val instruction = instructionSet.instructions[0x2a]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("ROL")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("ACC")
            it.assertThat(instruction.cycles).isEqualTo(2)
        }

        val memory = Array<UByte>(65536) { 0u }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0x2au

        val cpu = createCpu(DefaultMemory(memory))
        cpu.a = 0x40u
        cpu.statusRegister.carryFlag = true

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(cpu.a).isEqualTo(0x81.toUByte())
            it.assertThat(cpu.statusRegister.zeroFlag).isFalse
            it.assertThat(cpu.statusRegister.negativeFlag).isTrue
            it.assertThat(cpu.statusRegister.carryFlag).isFalse
        }
    }

    @Test
    fun `test ROL Accumulator (0x2a) 0-0xc0 becomes 1-0x80`() {
        val instruction = instructionSet.instructions[0x2a]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("ROL")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("ACC")
            it.assertThat(instruction.cycles).isEqualTo(2)
        }

        val memory = Array<UByte>(65536) { 0u }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0x2au

        val cpu = createCpu(DefaultMemory(memory))
        cpu.a = 0xc0u
        cpu.statusRegister.carryFlag = false

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(cpu.a).isEqualTo(0x80.toUByte())
            it.assertThat(cpu.statusRegister.zeroFlag).isFalse
            it.assertThat(cpu.statusRegister.negativeFlag).isTrue
            it.assertThat(cpu.statusRegister.carryFlag).isTrue
        }
    }

    @Test
    fun `test ROL Accumulator (0x2a) 0-0x38 becomes 0-0x70`() {
        val instruction = instructionSet.instructions[0x2a]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("ROL")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("ACC")
            it.assertThat(instruction.cycles).isEqualTo(2)
        }

        val memory = Array<UByte>(65536) { 0u }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0x2au

        val cpu = createCpu(DefaultMemory(memory))
        cpu.a = 0x38u
        cpu.statusRegister.carryFlag = false

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(cpu.a).isEqualTo(0x70.toUByte())
            it.assertThat(cpu.statusRegister.zeroFlag).isFalse
            it.assertThat(cpu.statusRegister.negativeFlag).isFalse
            it.assertThat(cpu.statusRegister.carryFlag).isFalse
        }
    }

    @Test
    fun `test ROL Accumulator (0x2a) 0-0xb8 becomes 1-0x70`() {
        val instruction = instructionSet.instructions[0x2a]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("ROL")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("ACC")
            it.assertThat(instruction.cycles).isEqualTo(2)
        }

        val memory = Array<UByte>(65536) { 0u }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0x2au

        val cpu = createCpu(DefaultMemory(memory))
        cpu.a = 0xb8u
        cpu.statusRegister.carryFlag = false

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(cpu.a).isEqualTo(0x70.toUByte())
            it.assertThat(cpu.statusRegister.zeroFlag).isFalse
            it.assertThat(cpu.statusRegister.negativeFlag).isFalse
            it.assertThat(cpu.statusRegister.carryFlag).isTrue
        }
    }

    @Test
    fun `test ROL Zero Page (0x26) 0-0x00 becomes 0-0x00`() {
        val instruction = instructionSet.instructions[0x26]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("ROL")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("ZPP")
            it.assertThat(instruction.cycles).isEqualTo(5)
        }

        val memory = Array<UByte>(65536) { 0x7fu }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0x26u
        memory[0x8001] = 0x00u
        memory[0x0000] = 0x00u

        val cpu = createCpu(DefaultMemory(memory))
        cpu.statusRegister.carryFlag = false

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(memory[0x0000]).isEqualTo(0x00.toUByte())
            it.assertThat(cpu.statusRegister.zeroFlag).isTrue
            it.assertThat(cpu.statusRegister.negativeFlag).isFalse
            it.assertThat(cpu.statusRegister.carryFlag).isFalse
        }
    }

    @Test
    fun `test ROL Zero Page (0x26) 0-0x80 becomes 1-0x00`() {
        val instruction = instructionSet.instructions[0x26]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("ROL")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("ZPP")
            it.assertThat(instruction.cycles).isEqualTo(5)
        }

        val memory = Array<UByte>(65536) { 0u }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0x26u
        memory[0x8001] = 0x00u
        memory[0x0000] = 0x80u

        val cpu = createCpu(DefaultMemory(memory))
        cpu.statusRegister.carryFlag = false

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(memory[0x0000]).isEqualTo(0x00.toUByte())
            it.assertThat(cpu.statusRegister.zeroFlag).isTrue
            it.assertThat(cpu.statusRegister.negativeFlag).isFalse
            it.assertThat(cpu.statusRegister.carryFlag).isTrue
        }
    }

    @Test
    fun `test ROL Zero Page (0x26) 1-0x40 becomes 0-0x81`() {
        val instruction = instructionSet.instructions[0x26]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("ROL")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("ZPP")
            it.assertThat(instruction.cycles).isEqualTo(5)
        }

        val memory = Array<UByte>(65536) { 0u }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0x26u
        memory[0x8001] = 0x00u
        memory[0x0000] = 0x40u

        val cpu = createCpu(DefaultMemory(memory))
        cpu.statusRegister.carryFlag = true

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(memory[0x0000]).isEqualTo(0x81.toUByte())
            it.assertThat(cpu.statusRegister.zeroFlag).isFalse
            it.assertThat(cpu.statusRegister.negativeFlag).isTrue
            it.assertThat(cpu.statusRegister.carryFlag).isFalse
        }
    }

    @Test
    fun `test ROL Zero Page (0x26) 0-0xc0 becomes 1-0x80`() {
        val instruction = instructionSet.instructions[0x26]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("ROL")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("ZPP")
            it.assertThat(instruction.cycles).isEqualTo(5)
        }

        val memory = Array<UByte>(65536) { 0u }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0x26u
        memory[0x8001] = 0x00u
        memory[0x0000] = 0xc0u

        val cpu = createCpu(DefaultMemory(memory))
        cpu.statusRegister.carryFlag = false

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(memory[0x0000]).isEqualTo(0x80.toUByte())
            it.assertThat(cpu.statusRegister.zeroFlag).isFalse
            it.assertThat(cpu.statusRegister.negativeFlag).isTrue
            it.assertThat(cpu.statusRegister.carryFlag).isTrue
        }
    }

    @Test
    fun `test ROL Zero Page (0x26) 0-0x38 becomes 0-0x70`() {
        val instruction = instructionSet.instructions[0x26]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("ROL")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("ZPP")
            it.assertThat(instruction.cycles).isEqualTo(5)
        }

        val memory = Array<UByte>(65536) { 0u }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0x26u
        memory[0x8001] = 0x00u
        memory[0x0000] = 0x38u

        val cpu = createCpu(DefaultMemory(memory))
        cpu.statusRegister.carryFlag = false

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(memory[0x0000]).isEqualTo(0x70.toUByte())
            it.assertThat(cpu.statusRegister.zeroFlag).isFalse
            it.assertThat(cpu.statusRegister.negativeFlag).isFalse
            it.assertThat(cpu.statusRegister.carryFlag).isFalse
        }
    }

    @Test
    fun `test ROL Zero Page (0x26) 0-0xb8 becomes 1-0x70`() {
        val instruction = instructionSet.instructions[0x26]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("ROL")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("ZPP")
            it.assertThat(instruction.cycles).isEqualTo(5)
        }

        val memory = Array<UByte>(65536) { 0u }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0x26u
        memory[0x8001] = 0x00u
        memory[0x0000] = 0xb8u

        val cpu = createCpu(DefaultMemory(memory))
        cpu.statusRegister.carryFlag = false

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(memory[0x0000]).isEqualTo(0x70.toUByte())
            it.assertThat(cpu.statusRegister.zeroFlag).isFalse
            it.assertThat(cpu.statusRegister.negativeFlag).isFalse
            it.assertThat(cpu.statusRegister.carryFlag).isTrue
        }
    }

    @Test
    fun `test ROL Zero Page,X (0x36) 0-0x00 becomes 0-0x00`() {
        val instruction = instructionSet.instructions[0x36]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("ROL")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("ZPX")
            it.assertThat(instruction.cycles).isEqualTo(6)
        }

        val memory = Array<UByte>(65536) { 0x7fu }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0x36u
        memory[0x8001] = 0x00u
        memory[0x0001] = 0x00u

        val cpu = createCpu(DefaultMemory(memory))
        cpu.x = 0x01u
        cpu.statusRegister.carryFlag = false

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(memory[0x0001]).isEqualTo(0x00.toUByte())
            it.assertThat(cpu.statusRegister.zeroFlag).isTrue
            it.assertThat(cpu.statusRegister.negativeFlag).isFalse
            it.assertThat(cpu.statusRegister.carryFlag).isFalse
        }
    }

    @Test
    fun `test ROL Zero Page,X (0x36) 0-0x80 becomes 1-0x00`() {
        val instruction = instructionSet.instructions[0x36]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("ROL")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("ZPX")
            it.assertThat(instruction.cycles).isEqualTo(6)
        }

        val memory = Array<UByte>(65536) { 0u }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0x36u
        memory[0x8001] = 0x00u
        memory[0x0001] = 0x80u

        val cpu = createCpu(DefaultMemory(memory))
        cpu.x = 0x01u
        cpu.statusRegister.carryFlag = false

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(memory[0x0001]).isEqualTo(0x00.toUByte())
            it.assertThat(cpu.statusRegister.zeroFlag).isTrue
            it.assertThat(cpu.statusRegister.negativeFlag).isFalse
            it.assertThat(cpu.statusRegister.carryFlag).isTrue
        }
    }

    @Test
    fun `test ROL Zero Page,X (0x36) 1-0x40 becomes 0-0x81`() {
        val instruction = instructionSet.instructions[0x36]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("ROL")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("ZPX")
            it.assertThat(instruction.cycles).isEqualTo(6)
        }

        val memory = Array<UByte>(65536) { 0u }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0x36u
        memory[0x8001] = 0x00u
        memory[0x0001] = 0x40u

        val cpu = createCpu(DefaultMemory(memory))
        cpu.x = 0x01u
        cpu.statusRegister.carryFlag = true

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(memory[0x0001]).isEqualTo(0x81.toUByte())
            it.assertThat(cpu.statusRegister.zeroFlag).isFalse
            it.assertThat(cpu.statusRegister.negativeFlag).isTrue
            it.assertThat(cpu.statusRegister.carryFlag).isFalse
        }
    }

    @Test
    fun `test ROL Zero Page,X (0x36) 0-0xc0 becomes 1-0x80`() {
        val instruction = instructionSet.instructions[0x36]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("ROL")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("ZPX")
            it.assertThat(instruction.cycles).isEqualTo(6)
        }

        val memory = Array<UByte>(65536) { 0u }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0x36u
        memory[0x8001] = 0x00u
        memory[0x0001] = 0xc0u

        val cpu = createCpu(DefaultMemory(memory))
        cpu.x = 0x01u
        cpu.statusRegister.carryFlag = false

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(memory[0x0001]).isEqualTo(0x80.toUByte())
            it.assertThat(cpu.statusRegister.zeroFlag).isFalse
            it.assertThat(cpu.statusRegister.negativeFlag).isTrue
            it.assertThat(cpu.statusRegister.carryFlag).isTrue
        }
    }

    @Test
    fun `test ROL Zero Page,X (0x36) 0-0x38 becomes 0-0x70`() {
        val instruction = instructionSet.instructions[0x36]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("ROL")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("ZPX")
            it.assertThat(instruction.cycles).isEqualTo(6)
        }

        val memory = Array<UByte>(65536) { 0u }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0x36u
        memory[0x8001] = 0x00u
        memory[0x0001] = 0x38u

        val cpu = createCpu(DefaultMemory(memory))
        cpu.x = 0x01u
        cpu.statusRegister.carryFlag = false

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(memory[0x0001]).isEqualTo(0x70.toUByte())
            it.assertThat(cpu.statusRegister.zeroFlag).isFalse
            it.assertThat(cpu.statusRegister.negativeFlag).isFalse
            it.assertThat(cpu.statusRegister.carryFlag).isFalse
        }
    }

    @Test
    fun `test ROL Zero Page,X (0x36) 0-0xb8 becomes 1-0x70`() {
        val instruction = instructionSet.instructions[0x36]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("ROL")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("ZPX")
            it.assertThat(instruction.cycles).isEqualTo(6)
        }

        val memory = Array<UByte>(65536) { 0u }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0x36u
        memory[0x8001] = 0x00u
        memory[0x0001] = 0xb8u

        val cpu = createCpu(DefaultMemory(memory))
        cpu.x = 0x01u
        cpu.statusRegister.carryFlag = false

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(memory[0x0001]).isEqualTo(0x70.toUByte())
            it.assertThat(cpu.statusRegister.zeroFlag).isFalse
            it.assertThat(cpu.statusRegister.negativeFlag).isFalse
            it.assertThat(cpu.statusRegister.carryFlag).isTrue
        }
    }

    @Test
    fun `test ROL Absolute (0x2e) 0-0x00 becomes 0-0x00`() {
        val instruction = instructionSet.instructions[0x2e]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("ROL")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("ABS")
            it.assertThat(instruction.cycles).isEqualTo(6)
        }

        val memory = Array<UByte>(65536) { 0x7fu }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0x2eu
        memory[0x8001] = 0x00u
        memory[0x8002] = 0x02u
        memory[0x0200] = 0x00u

        val cpu = createCpu(DefaultMemory(memory))
        cpu.statusRegister.carryFlag = false

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(memory[0x0200]).isEqualTo(0x00.toUByte())
            it.assertThat(cpu.statusRegister.zeroFlag).isTrue
            it.assertThat(cpu.statusRegister.negativeFlag).isFalse
            it.assertThat(cpu.statusRegister.carryFlag).isFalse
        }
    }

    @Test
    fun `test ROL Absolute (0x2e) 0-0x80 becomes 1-0x00`() {
        val instruction = instructionSet.instructions[0x2e]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("ROL")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("ABS")
            it.assertThat(instruction.cycles).isEqualTo(6)
        }

        val memory = Array<UByte>(65536) { 0u }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0x2eu
        memory[0x8001] = 0x00u
        memory[0x8002] = 0x02u
        memory[0x0200] = 0x80u

        val cpu = createCpu(DefaultMemory(memory))
        cpu.statusRegister.carryFlag = false

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(memory[0x0200]).isEqualTo(0x00.toUByte())
            it.assertThat(cpu.statusRegister.zeroFlag).isTrue
            it.assertThat(cpu.statusRegister.negativeFlag).isFalse
            it.assertThat(cpu.statusRegister.carryFlag).isTrue
        }
    }

    @Test
    fun `test ROL Absolute (0x2e) 1-0x40 becomes 0-0x81`() {
        val instruction = instructionSet.instructions[0x2e]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("ROL")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("ABS")
            it.assertThat(instruction.cycles).isEqualTo(6)
        }

        val memory = Array<UByte>(65536) { 0u }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0x2eu
        memory[0x8001] = 0x00u
        memory[0x8002] = 0x02u
        memory[0x0200] = 0x40u

        val cpu = createCpu(DefaultMemory(memory))
        cpu.statusRegister.carryFlag = true

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(memory[0x0200]).isEqualTo(0x81.toUByte())
            it.assertThat(cpu.statusRegister.zeroFlag).isFalse
            it.assertThat(cpu.statusRegister.negativeFlag).isTrue
            it.assertThat(cpu.statusRegister.carryFlag).isFalse
        }
    }

    @Test
    fun `test ROL Absolute (0x2e) 0-0xc0 becomes 1-0x80`() {
        val instruction = instructionSet.instructions[0x2e]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("ROL")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("ABS")
            it.assertThat(instruction.cycles).isEqualTo(6)
        }

        val memory = Array<UByte>(65536) { 0u }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0x2eu
        memory[0x8001] = 0x00u
        memory[0x8002] = 0x02u
        memory[0x0200] = 0xc0u

        val cpu = createCpu(DefaultMemory(memory))
        cpu.statusRegister.carryFlag = false

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(memory[0x0200]).isEqualTo(0x80.toUByte())
            it.assertThat(cpu.statusRegister.zeroFlag).isFalse
            it.assertThat(cpu.statusRegister.negativeFlag).isTrue
            it.assertThat(cpu.statusRegister.carryFlag).isTrue
        }
    }

    @Test
    fun `test ROL Absolute (0x2e) 0-0x38 becomes 0-0x70`() {
        val instruction = instructionSet.instructions[0x2e]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("ROL")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("ABS")
            it.assertThat(instruction.cycles).isEqualTo(6)
        }

        val memory = Array<UByte>(65536) { 0u }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0x2eu
        memory[0x8001] = 0x00u
        memory[0x8002] = 0x02u
        memory[0x0200] = 0x38u

        val cpu = createCpu(DefaultMemory(memory))
        cpu.statusRegister.carryFlag = false

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(memory[0x0200]).isEqualTo(0x70.toUByte())
            it.assertThat(cpu.statusRegister.zeroFlag).isFalse
            it.assertThat(cpu.statusRegister.negativeFlag).isFalse
            it.assertThat(cpu.statusRegister.carryFlag).isFalse
        }
    }

    @Test
    fun `test ROL Absolute (0x2e) 0-0xb8 becomes 1-0x70`() {
        val instruction = instructionSet.instructions[0x2e]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("ROL")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("ABS")
            it.assertThat(instruction.cycles).isEqualTo(6)
        }

        val memory = Array<UByte>(65536) { 0u }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0x2eu
        memory[0x8001] = 0x00u
        memory[0x8002] = 0x02u
        memory[0x0200] = 0xb8u

        val cpu = createCpu(DefaultMemory(memory))
        cpu.statusRegister.carryFlag = false

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(memory[0x0200]).isEqualTo(0x70.toUByte())
            it.assertThat(cpu.statusRegister.zeroFlag).isFalse
            it.assertThat(cpu.statusRegister.negativeFlag).isFalse
            it.assertThat(cpu.statusRegister.carryFlag).isTrue
        }
    }

    @Test
    fun `test ROL Absolute,X (0x3e) 0-0x00 becomes 0-0x00`() {
        val instruction = instructionSet.instructions[0x3e]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("ROL")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("ABX")
            it.assertThat(instruction.cycles).isEqualTo(7)
        }

        val memory = Array<UByte>(65536) { 0x7fu }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0x3eu
        memory[0x8001] = 0x00u
        memory[0x8002] = 0x02u
        memory[0x0201] = 0x00u

        val cpu = createCpu(DefaultMemory(memory))
        cpu.x = 0x01u
        cpu.statusRegister.carryFlag = false

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(memory[0x0201]).isEqualTo(0x00.toUByte())
            it.assertThat(cpu.statusRegister.zeroFlag).isTrue
            it.assertThat(cpu.statusRegister.negativeFlag).isFalse
            it.assertThat(cpu.statusRegister.carryFlag).isFalse
        }
    }

    @Test
    fun `test ROL Absolute,X (0x3e) 0-0x80 becomes 1-0x00`() {
        val instruction = instructionSet.instructions[0x3e]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("ROL")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("ABX")
            it.assertThat(instruction.cycles).isEqualTo(7)
        }

        val memory = Array<UByte>(65536) { 0u }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0x3eu
        memory[0x8001] = 0x00u
        memory[0x8002] = 0x02u
        memory[0x0201] = 0x80u

        val cpu = createCpu(DefaultMemory(memory))
        cpu.x = 0x01u
        cpu.statusRegister.carryFlag = false

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(memory[0x0201]).isEqualTo(0x00.toUByte())
            it.assertThat(cpu.statusRegister.zeroFlag).isTrue
            it.assertThat(cpu.statusRegister.negativeFlag).isFalse
            it.assertThat(cpu.statusRegister.carryFlag).isTrue
        }
    }

    @Test
    fun `test ROL Absolute,X (0x3e) 1-0x40 becomes 0-0x81`() {
        val instruction = instructionSet.instructions[0x3e]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("ROL")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("ABX")
            it.assertThat(instruction.cycles).isEqualTo(7)
        }

        val memory = Array<UByte>(65536) { 0u }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0x3eu
        memory[0x8001] = 0x00u
        memory[0x8002] = 0x02u
        memory[0x0201] = 0x40u

        val cpu = createCpu(DefaultMemory(memory))
        cpu.x = 0x01u
        cpu.statusRegister.carryFlag = true

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(memory[0x0201]).isEqualTo(0x81.toUByte())
            it.assertThat(cpu.statusRegister.zeroFlag).isFalse
            it.assertThat(cpu.statusRegister.negativeFlag).isTrue
            it.assertThat(cpu.statusRegister.carryFlag).isFalse
        }
    }

    @Test
    fun `test ROL Absolute,X (0x3e) 0-0xc0 becomes 1-0x80`() {
        val instruction = instructionSet.instructions[0x3e]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("ROL")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("ABX")
            it.assertThat(instruction.cycles).isEqualTo(7)
        }

        val memory = Array<UByte>(65536) { 0u }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0x3eu
        memory[0x8001] = 0x00u
        memory[0x8002] = 0x02u
        memory[0x0201] = 0xc0u

        val cpu = createCpu(DefaultMemory(memory))
        cpu.x = 0x01u
        cpu.statusRegister.carryFlag = false

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(memory[0x0201]).isEqualTo(0x80.toUByte())
            it.assertThat(cpu.statusRegister.zeroFlag).isFalse
            it.assertThat(cpu.statusRegister.negativeFlag).isTrue
            it.assertThat(cpu.statusRegister.carryFlag).isTrue
        }
    }

    @Test
    fun `test ROL Absolute,X (0x3e) 0-0x38 becomes 0-0x70`() {
        val instruction = instructionSet.instructions[0x3e]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("ROL")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("ABX")
            it.assertThat(instruction.cycles).isEqualTo(7)
        }

        val memory = Array<UByte>(65536) { 0u }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0x3eu
        memory[0x8001] = 0x00u
        memory[0x8002] = 0x02u
        memory[0x0201] = 0x38u

        val cpu = createCpu(DefaultMemory(memory))
        cpu.x = 0x01u
        cpu.statusRegister.carryFlag = false

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(memory[0x0201]).isEqualTo(0x70.toUByte())
            it.assertThat(cpu.statusRegister.zeroFlag).isFalse
            it.assertThat(cpu.statusRegister.negativeFlag).isFalse
            it.assertThat(cpu.statusRegister.carryFlag).isFalse
        }
    }

    @Test
    fun `test ROL Absolute,X (0x3e) 0-0xb8 becomes 1-0x70`() {
        val instruction = instructionSet.instructions[0x3e]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("ROL")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("ABX")
            it.assertThat(instruction.cycles).isEqualTo(7)
        }

        val memory = Array<UByte>(65536) { 0u }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0x3eu
        memory[0x8001] = 0x00u
        memory[0x8002] = 0x02u
        memory[0x0201] = 0xb8u

        val cpu = createCpu(DefaultMemory(memory))
        cpu.x = 0x01u
        cpu.statusRegister.carryFlag = false

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(memory[0x0201]).isEqualTo(0x70.toUByte())
            it.assertThat(cpu.statusRegister.zeroFlag).isFalse
            it.assertThat(cpu.statusRegister.negativeFlag).isFalse
            it.assertThat(cpu.statusRegister.carryFlag).isTrue
        }
    }

    @Test
    fun `test ROR Accumulator (0x6a) 0-0x00 becomes 0-0x00`() {
        val instruction = instructionSet.instructions[0x6a]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("ROR")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("ACC")
            it.assertThat(instruction.cycles).isEqualTo(2)
        }

        val memory = Array<UByte>(65536) { 0u }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0x6au

        val cpu = createCpu(DefaultMemory(memory))
        cpu.a = 0x00u
        cpu.statusRegister.carryFlag = false

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(cpu.a).isEqualTo(0x00.toUByte())
            it.assertThat(cpu.statusRegister.zeroFlag).isTrue
            it.assertThat(cpu.statusRegister.negativeFlag).isFalse
            it.assertThat(cpu.statusRegister.carryFlag).isFalse
        }
    }

    @Test
    fun `test ROR Accumulator (0x6a) 1-0x00 becomes 0-0x80`() {
        val instruction = instructionSet.instructions[0x6a]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("ROR")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("ACC")
            it.assertThat(instruction.cycles).isEqualTo(2)
        }

        val memory = Array<UByte>(65536) { 0u }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0x6au

        val cpu = createCpu(DefaultMemory(memory))
        cpu.a = 0x00u
        cpu.statusRegister.carryFlag = true

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(cpu.a).isEqualTo(0x80.toUByte())
            it.assertThat(cpu.statusRegister.zeroFlag).isFalse
            it.assertThat(cpu.statusRegister.negativeFlag).isTrue
            it.assertThat(cpu.statusRegister.carryFlag).isFalse
        }
    }

    @Test
    fun `test ROR Accumulator (0x6a) 0-0x81 becomes 1-0x40`() {
        val instruction = instructionSet.instructions[0x6a]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("ROR")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("ACC")
            it.assertThat(instruction.cycles).isEqualTo(2)
        }

        val memory = Array<UByte>(65536) { 0u }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0x6au

        val cpu = createCpu(DefaultMemory(memory))
        cpu.a = 0x81u
        cpu.statusRegister.carryFlag = false

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(cpu.a).isEqualTo(0x40.toUByte())
            it.assertThat(cpu.statusRegister.zeroFlag).isFalse
            it.assertThat(cpu.statusRegister.negativeFlag).isFalse
            it.assertThat(cpu.statusRegister.carryFlag).isTrue
        }
    }

    @Test
    fun `test ROR Accumulator (0x6a) 1-0x80 becomes 0-0xc0`() {
        val instruction = instructionSet.instructions[0x6a]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("ROR")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("ACC")
            it.assertThat(instruction.cycles).isEqualTo(2)
        }

        val memory = Array<UByte>(65536) { 0u }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0x6au

        val cpu = createCpu(DefaultMemory(memory))
        cpu.a = 0x80u
        cpu.statusRegister.carryFlag = true

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(cpu.a).isEqualTo(0xc0.toUByte())
            it.assertThat(cpu.statusRegister.zeroFlag).isFalse
            it.assertThat(cpu.statusRegister.negativeFlag).isTrue
            it.assertThat(cpu.statusRegister.carryFlag).isFalse
        }
    }

    @Test
    fun `test ROR Accumulator (0x6a) 0-0x70 becomes 0-0x38`() {
        val instruction = instructionSet.instructions[0x6a]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("ROR")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("ACC")
            it.assertThat(instruction.cycles).isEqualTo(2)
        }

        val memory = Array<UByte>(65536) { 0u }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0x6au

        val cpu = createCpu(DefaultMemory(memory))
        cpu.a = 0x70u
        cpu.statusRegister.carryFlag = false

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(cpu.a).isEqualTo(0x38.toUByte())
            it.assertThat(cpu.statusRegister.zeroFlag).isFalse
            it.assertThat(cpu.statusRegister.negativeFlag).isFalse
            it.assertThat(cpu.statusRegister.carryFlag).isFalse
        }
    }

    @Test
    fun `test ROR Accumulator (0x6a) 1-0x70 becomes 0-0xb8`() {
        val instruction = instructionSet.instructions[0x6a]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("ROR")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("ACC")
            it.assertThat(instruction.cycles).isEqualTo(2)
        }

        val memory = Array<UByte>(65536) { 0u }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0x6au

        val cpu = createCpu(DefaultMemory(memory))
        cpu.a = 0x70u
        cpu.statusRegister.carryFlag = true

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(cpu.a).isEqualTo(0xb8.toUByte())
            it.assertThat(cpu.statusRegister.zeroFlag).isFalse
            it.assertThat(cpu.statusRegister.negativeFlag).isTrue
            it.assertThat(cpu.statusRegister.carryFlag).isFalse
        }
    }

    @Test
    fun `test ROR Zero Page (0x66) 0-0x00 becomes 0-0x00`() {
        val instruction = instructionSet.instructions[0x66]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("ROR")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("ZPP")
            it.assertThat(instruction.cycles).isEqualTo(5)
        }

        val memory = Array<UByte>(65536) { 0x7fu }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0x66u
        memory[0x8001] = 0x00u
        memory[0x0000] = 0x00u

        val cpu = createCpu(DefaultMemory(memory))
        cpu.statusRegister.carryFlag = false

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(memory[0x0000]).isEqualTo(0x00.toUByte())
            it.assertThat(cpu.statusRegister.zeroFlag).isTrue
            it.assertThat(cpu.statusRegister.negativeFlag).isFalse
            it.assertThat(cpu.statusRegister.carryFlag).isFalse
        }
    }

    @Test
    fun `test ROR Zero Page (0x66) 1-0x00 becomes 0-0x80`() {
        val instruction = instructionSet.instructions[0x66]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("ROR")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("ZPP")
            it.assertThat(instruction.cycles).isEqualTo(5)
        }

        val memory = Array<UByte>(65536) { 0u }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0x66u
        memory[0x8001] = 0x00u
        memory[0x0000] = 0x00u

        val cpu = createCpu(DefaultMemory(memory))
        cpu.statusRegister.carryFlag = true

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(memory[0x0000]).isEqualTo(0x80.toUByte())
            it.assertThat(cpu.statusRegister.zeroFlag).isFalse
            it.assertThat(cpu.statusRegister.negativeFlag).isTrue
            it.assertThat(cpu.statusRegister.carryFlag).isFalse
        }
    }

    @Test
    fun `test ROR Zero Page (0x66) 0-0x81 becomes 1-0x40`() {
        val instruction = instructionSet.instructions[0x66]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("ROR")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("ZPP")
            it.assertThat(instruction.cycles).isEqualTo(5)
        }

        val memory = Array<UByte>(65536) { 0u }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0x66u
        memory[0x8001] = 0x00u
        memory[0x0000] = 0x81u

        val cpu = createCpu(DefaultMemory(memory))
        cpu.statusRegister.carryFlag = false

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(memory[0x0000]).isEqualTo(0x40.toUByte())
            it.assertThat(cpu.statusRegister.zeroFlag).isFalse
            it.assertThat(cpu.statusRegister.negativeFlag).isFalse
            it.assertThat(cpu.statusRegister.carryFlag).isTrue
        }
    }

    @Test
    fun `test ROR Zero Page (0x66) 1-0x80 becomes 0-0xc0`() {
        val instruction = instructionSet.instructions[0x66]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("ROR")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("ZPP")
            it.assertThat(instruction.cycles).isEqualTo(5)
        }

        val memory = Array<UByte>(65536) { 0u }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0x66u
        memory[0x8001] = 0x00u
        memory[0x0000] = 0x80u

        val cpu = createCpu(DefaultMemory(memory))
        cpu.statusRegister.carryFlag = true

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(memory[0x0000]).isEqualTo(0xc0.toUByte())
            it.assertThat(cpu.statusRegister.zeroFlag).isFalse
            it.assertThat(cpu.statusRegister.negativeFlag).isTrue
            it.assertThat(cpu.statusRegister.carryFlag).isFalse
        }
    }

    @Test
    fun `test ROR Zero Page (0x66) 0-0x70 becomes 0-0x38`() {
        val instruction = instructionSet.instructions[0x66]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("ROR")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("ZPP")
            it.assertThat(instruction.cycles).isEqualTo(5)
        }

        val memory = Array<UByte>(65536) { 0u }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0x66u
        memory[0x8001] = 0x00u
        memory[0x0000] = 0x70u

        val cpu = createCpu(DefaultMemory(memory))
        cpu.statusRegister.carryFlag = false

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(memory[0x0000]).isEqualTo(0x38.toUByte())
            it.assertThat(cpu.statusRegister.zeroFlag).isFalse
            it.assertThat(cpu.statusRegister.negativeFlag).isFalse
            it.assertThat(cpu.statusRegister.carryFlag).isFalse
        }
    }

    @Test
    fun `test ROR Zero Page (0x66) 1-0x70 becomes 0-0xb8`() {
        val instruction = instructionSet.instructions[0x66]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("ROR")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("ZPP")
            it.assertThat(instruction.cycles).isEqualTo(5)
        }

        val memory = Array<UByte>(65536) { 0u }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0x66u
        memory[0x8001] = 0x00u
        memory[0x0000] = 0x70u

        val cpu = createCpu(DefaultMemory(memory))
        cpu.statusRegister.carryFlag = true

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(memory[0x0000]).isEqualTo(0xb8.toUByte())
            it.assertThat(cpu.statusRegister.zeroFlag).isFalse
            it.assertThat(cpu.statusRegister.negativeFlag).isTrue
            it.assertThat(cpu.statusRegister.carryFlag).isFalse
        }
    }

    @Test
    fun `test ROR Zero Page,X (0x76) 0-0x00 becomes 0-0x00`() {
        val instruction = instructionSet.instructions[0x76]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("ROR")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("ZPX")
            it.assertThat(instruction.cycles).isEqualTo(6)
        }

        val memory = Array<UByte>(65536) { 0x7fu }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0x76u
        memory[0x8001] = 0x00u
        memory[0x0001] = 0x00u

        val cpu = createCpu(DefaultMemory(memory))
        cpu.x = 0x01u
        cpu.statusRegister.carryFlag = false

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(memory[0x0001]).isEqualTo(0x00.toUByte())
            it.assertThat(cpu.statusRegister.zeroFlag).isTrue
            it.assertThat(cpu.statusRegister.negativeFlag).isFalse
            it.assertThat(cpu.statusRegister.carryFlag).isFalse
        }
    }

    @Test
    fun `test ROR Zero Page,X (0x76) 1-0x00 becomes 0-0x80`() {
        val instruction = instructionSet.instructions[0x76]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("ROR")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("ZPX")
            it.assertThat(instruction.cycles).isEqualTo(6)
        }

        val memory = Array<UByte>(65536) { 0u }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0x76u
        memory[0x8001] = 0x00u
        memory[0x0001] = 0x00u

        val cpu = createCpu(DefaultMemory(memory))
        cpu.x = 0x01u
        cpu.statusRegister.carryFlag = true

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(memory[0x0001]).isEqualTo(0x80.toUByte())
            it.assertThat(cpu.statusRegister.zeroFlag).isFalse
            it.assertThat(cpu.statusRegister.negativeFlag).isTrue
            it.assertThat(cpu.statusRegister.carryFlag).isFalse
        }
    }

    @Test
    fun `test ROR Zero Page,X (0x76) 0-0x81 becomes 1-0x40`() {
        val instruction = instructionSet.instructions[0x76]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("ROR")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("ZPX")
            it.assertThat(instruction.cycles).isEqualTo(6)
        }

        val memory = Array<UByte>(65536) { 0u }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0x76u
        memory[0x8001] = 0x00u
        memory[0x0001] = 0x81u

        val cpu = createCpu(DefaultMemory(memory))
        cpu.x = 0x01u
        cpu.statusRegister.carryFlag = false

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(memory[0x0001]).isEqualTo(0x40.toUByte())
            it.assertThat(cpu.statusRegister.zeroFlag).isFalse
            it.assertThat(cpu.statusRegister.negativeFlag).isFalse
            it.assertThat(cpu.statusRegister.carryFlag).isTrue
        }
    }

    @Test
    fun `test ROR Zero Page,X (0x76) 1-0x80 becomes 0-0xc0`() {
        val instruction = instructionSet.instructions[0x76]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("ROR")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("ZPX")
            it.assertThat(instruction.cycles).isEqualTo(6)
        }

        val memory = Array<UByte>(65536) { 0u }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0x76u
        memory[0x8001] = 0x00u
        memory[0x0001] = 0x80u

        val cpu = createCpu(DefaultMemory(memory))
        cpu.x = 0x01u
        cpu.statusRegister.carryFlag = true

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(memory[0x0001]).isEqualTo(0xc0.toUByte())
            it.assertThat(cpu.statusRegister.zeroFlag).isFalse
            it.assertThat(cpu.statusRegister.negativeFlag).isTrue
            it.assertThat(cpu.statusRegister.carryFlag).isFalse
        }
    }

    @Test
    fun `test ROR Zero Page,X (0x76) 0-0x70 becomes 0-0x38`() {
        val instruction = instructionSet.instructions[0x76]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("ROR")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("ZPX")
            it.assertThat(instruction.cycles).isEqualTo(6)
        }

        val memory = Array<UByte>(65536) { 0u }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0x76u
        memory[0x8001] = 0x00u
        memory[0x0001] = 0x70u

        val cpu = createCpu(DefaultMemory(memory))
        cpu.x = 0x01u
        cpu.statusRegister.carryFlag = false

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(memory[0x0001]).isEqualTo(0x38.toUByte())
            it.assertThat(cpu.statusRegister.zeroFlag).isFalse
            it.assertThat(cpu.statusRegister.negativeFlag).isFalse
            it.assertThat(cpu.statusRegister.carryFlag).isFalse
        }
    }

    @Test
    fun `test ROR Zero Page,X (0x76) 1-0x70 becomes 0-0xb8`() {
        val instruction = instructionSet.instructions[0x76]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("ROR")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("ZPX")
            it.assertThat(instruction.cycles).isEqualTo(6)
        }

        val memory = Array<UByte>(65536) { 0u }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0x76u
        memory[0x8001] = 0x00u
        memory[0x0001] = 0x70u

        val cpu = createCpu(DefaultMemory(memory))
        cpu.x = 0x01u
        cpu.statusRegister.carryFlag = true

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(memory[0x0001]).isEqualTo(0xb8.toUByte())
            it.assertThat(cpu.statusRegister.zeroFlag).isFalse
            it.assertThat(cpu.statusRegister.negativeFlag).isTrue
            it.assertThat(cpu.statusRegister.carryFlag).isFalse
        }
    }

    @Test
    fun `test ROR Absolute (0x6e) 0-0x00 becomes 0-0x00`() {
        val instruction = instructionSet.instructions[0x6e]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("ROR")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("ABS")
            it.assertThat(instruction.cycles).isEqualTo(6)
        }

        val memory = Array<UByte>(65536) { 0x7fu }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0x6eu
        memory[0x8001] = 0x00u
        memory[0x8002] = 0x02u
        memory[0x0200] = 0x00u

        val cpu = createCpu(DefaultMemory(memory))
        cpu.statusRegister.carryFlag = false

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(memory[0x0200]).isEqualTo(0x00.toUByte())
            it.assertThat(cpu.statusRegister.zeroFlag).isTrue
            it.assertThat(cpu.statusRegister.negativeFlag).isFalse
            it.assertThat(cpu.statusRegister.carryFlag).isFalse
        }
    }

    @Test
    fun `test ROR Absolute (0x6e) 1-0x00 becomes 0-0x80`() {
        val instruction = instructionSet.instructions[0x6e]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("ROR")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("ABS")
            it.assertThat(instruction.cycles).isEqualTo(6)
        }

        val memory = Array<UByte>(65536) { 0u }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0x6eu
        memory[0x8001] = 0x00u
        memory[0x8002] = 0x02u
        memory[0x0200] = 0x00u

        val cpu = createCpu(DefaultMemory(memory))
        cpu.statusRegister.carryFlag = true

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(memory[0x0200]).isEqualTo(0x80.toUByte())
            it.assertThat(cpu.statusRegister.zeroFlag).isFalse
            it.assertThat(cpu.statusRegister.negativeFlag).isTrue
            it.assertThat(cpu.statusRegister.carryFlag).isFalse
        }
    }

    @Test
    fun `test ROR Absolute (0x6e) 0-0x81 becomes 1-0x40`() {
        val instruction = instructionSet.instructions[0x6e]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("ROR")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("ABS")
            it.assertThat(instruction.cycles).isEqualTo(6)
        }

        val memory = Array<UByte>(65536) { 0u }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0x6eu
        memory[0x8001] = 0x00u
        memory[0x8002] = 0x02u
        memory[0x0200] = 0x81u

        val cpu = createCpu(DefaultMemory(memory))
        cpu.statusRegister.carryFlag = false

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(memory[0x0200]).isEqualTo(0x40.toUByte())
            it.assertThat(cpu.statusRegister.zeroFlag).isFalse
            it.assertThat(cpu.statusRegister.negativeFlag).isFalse
            it.assertThat(cpu.statusRegister.carryFlag).isTrue
        }
    }

    @Test
    fun `test ROR Absolute (0x6e) 1-0x80 becomes 0-0xc0`() {
        val instruction = instructionSet.instructions[0x6e]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("ROR")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("ABS")
            it.assertThat(instruction.cycles).isEqualTo(6)
        }

        val memory = Array<UByte>(65536) { 0u }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0x6eu
        memory[0x8001] = 0x00u
        memory[0x8002] = 0x02u
        memory[0x0200] = 0x80u

        val cpu = createCpu(DefaultMemory(memory))
        cpu.statusRegister.carryFlag = true

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(memory[0x0200]).isEqualTo(0xc0.toUByte())
            it.assertThat(cpu.statusRegister.zeroFlag).isFalse
            it.assertThat(cpu.statusRegister.negativeFlag).isTrue
            it.assertThat(cpu.statusRegister.carryFlag).isFalse
        }
    }

    @Test
    fun `test ROR Absolute (0x6e) 0-0x70 becomes 0-0x38`() {
        val instruction = instructionSet.instructions[0x6e]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("ROR")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("ABS")
            it.assertThat(instruction.cycles).isEqualTo(6)
        }

        val memory = Array<UByte>(65536) { 0u }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0x6eu
        memory[0x8001] = 0x00u
        memory[0x8002] = 0x02u
        memory[0x0200] = 0x70u

        val cpu = createCpu(DefaultMemory(memory))
        cpu.statusRegister.carryFlag = false

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(memory[0x0200]).isEqualTo(0x38.toUByte())
            it.assertThat(cpu.statusRegister.zeroFlag).isFalse
            it.assertThat(cpu.statusRegister.negativeFlag).isFalse
            it.assertThat(cpu.statusRegister.carryFlag).isFalse
        }
    }

    @Test
    fun `test ROR Absolute (0x6e) 1-0x70 becomes 0-0xb8`() {
        val instruction = instructionSet.instructions[0x6e]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("ROR")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("ABS")
            it.assertThat(instruction.cycles).isEqualTo(6)
        }

        val memory = Array<UByte>(65536) { 0u }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0x6eu
        memory[0x8001] = 0x00u
        memory[0x8002] = 0x02u
        memory[0x0200] = 0x70u

        val cpu = createCpu(DefaultMemory(memory))
        cpu.statusRegister.carryFlag = true

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(memory[0x0200]).isEqualTo(0xb8.toUByte())
            it.assertThat(cpu.statusRegister.zeroFlag).isFalse
            it.assertThat(cpu.statusRegister.negativeFlag).isTrue
            it.assertThat(cpu.statusRegister.carryFlag).isFalse
        }
    }

    @Test
    fun `test ROL Absolute,X (0x7e) 0-0x00 becomes 0-0x00`() {
        val instruction = instructionSet.instructions[0x7e]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("ROR")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("ABX")
            it.assertThat(instruction.cycles).isEqualTo(7)
        }

        val memory = Array<UByte>(65536) { 0x7fu }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0x7eu
        memory[0x8001] = 0x00u
        memory[0x8002] = 0x02u
        memory[0x0201] = 0x00u

        val cpu = createCpu(DefaultMemory(memory))
        cpu.x = 0x01u
        cpu.statusRegister.carryFlag = false

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(memory[0x0201]).isEqualTo(0x00.toUByte())
            it.assertThat(cpu.statusRegister.zeroFlag).isTrue
            it.assertThat(cpu.statusRegister.negativeFlag).isFalse
            it.assertThat(cpu.statusRegister.carryFlag).isFalse
        }
    }

    @Test
    fun `test ROR Absolute,X (0x7e) 1-0x00 becomes 0-0x80`() {
        val instruction = instructionSet.instructions[0x7e]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("ROR")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("ABX")
            it.assertThat(instruction.cycles).isEqualTo(7)
        }

        val memory = Array<UByte>(65536) { 0u }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0x7eu
        memory[0x8001] = 0x00u
        memory[0x8002] = 0x02u
        memory[0x0201] = 0x00u

        val cpu = createCpu(DefaultMemory(memory))
        cpu.x = 0x01u
        cpu.statusRegister.carryFlag = true

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(memory[0x0201]).isEqualTo(0x80.toUByte())
            it.assertThat(cpu.statusRegister.zeroFlag).isFalse
            it.assertThat(cpu.statusRegister.negativeFlag).isTrue
            it.assertThat(cpu.statusRegister.carryFlag).isFalse
        }
    }

    @Test
    fun `test ROR Absolute,X (0x7e) 0-0x81 becomes 1-0x40`() {
        val instruction = instructionSet.instructions[0x7e]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("ROR")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("ABX")
            it.assertThat(instruction.cycles).isEqualTo(7)
        }

        val memory = Array<UByte>(65536) { 0u }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0x7eu
        memory[0x8001] = 0x00u
        memory[0x8002] = 0x02u
        memory[0x0201] = 0x81u

        val cpu = createCpu(DefaultMemory(memory))
        cpu.x = 0x01u
        cpu.statusRegister.carryFlag = false

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(memory[0x0201]).isEqualTo(0x40.toUByte())
            it.assertThat(cpu.statusRegister.zeroFlag).isFalse
            it.assertThat(cpu.statusRegister.negativeFlag).isFalse
            it.assertThat(cpu.statusRegister.carryFlag).isTrue
        }
    }

    @Test
    fun `test ROR Absolute,X (0x7e) 1-0x80 becomes 0-0xc0`() {
        val instruction = instructionSet.instructions[0x7e]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("ROR")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("ABX")
            it.assertThat(instruction.cycles).isEqualTo(7)
        }

        val memory = Array<UByte>(65536) { 0u }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0x7eu
        memory[0x8001] = 0x00u
        memory[0x8002] = 0x02u
        memory[0x0201] = 0x80u

        val cpu = createCpu(DefaultMemory(memory))
        cpu.x = 0x01u
        cpu.statusRegister.carryFlag = true

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(memory[0x0201]).isEqualTo(0xc0.toUByte())
            it.assertThat(cpu.statusRegister.zeroFlag).isFalse
            it.assertThat(cpu.statusRegister.negativeFlag).isTrue
            it.assertThat(cpu.statusRegister.carryFlag).isFalse
        }
    }

    @Test
    fun `test ROR Absolute,X (0x7e) 0-0x70 becomes 0-0x38`() {
        val instruction = instructionSet.instructions[0x7e]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("ROR")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("ABX")
            it.assertThat(instruction.cycles).isEqualTo(7)
        }

        val memory = Array<UByte>(65536) { 0u }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0x7eu
        memory[0x8001] = 0x00u
        memory[0x8002] = 0x02u
        memory[0x0201] = 0x70u

        val cpu = createCpu(DefaultMemory(memory))
        cpu.x = 0x01u
        cpu.statusRegister.carryFlag = false

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(memory[0x0201]).isEqualTo(0x38.toUByte())
            it.assertThat(cpu.statusRegister.zeroFlag).isFalse
            it.assertThat(cpu.statusRegister.negativeFlag).isFalse
            it.assertThat(cpu.statusRegister.carryFlag).isFalse
        }
    }

    @Test
    fun `test ROR Absolute,X (0x7e) 1-0x70 becomes 0-0xb8`() {
        val instruction = instructionSet.instructions[0x7e]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("ROR")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("ABX")
            it.assertThat(instruction.cycles).isEqualTo(7)
        }

        val memory = Array<UByte>(65536) { 0u }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0x7eu
        memory[0x8001] = 0x00u
        memory[0x8002] = 0x02u
        memory[0x0201] = 0x70u

        val cpu = createCpu(DefaultMemory(memory))
        cpu.x = 0x01u
        cpu.statusRegister.carryFlag = true

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(memory[0x0201]).isEqualTo(0xb8.toUByte())
            it.assertThat(cpu.statusRegister.zeroFlag).isFalse
            it.assertThat(cpu.statusRegister.negativeFlag).isTrue
            it.assertThat(cpu.statusRegister.carryFlag).isFalse
        }
    }
}