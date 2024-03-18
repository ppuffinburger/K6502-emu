package com.puffinsoft.k6502

import com.puffinsoft.k6502.inbuilt.DefaultMemory
import org.assertj.core.api.Assertions
import org.assertj.core.api.SoftAssertions
import org.junit.jupiter.api.Test

class ShiftInstructionsTest : BaseInstructionsTest() {
    @Test
    fun `test ASL Accumulator (0x0a) 1-0x00 becomes 0-0x00`() {
        val instruction = instructionSet.instructions[0x0a]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("ASL")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("ACC")
            it.assertThat(instruction.cycles).isEqualTo(2)
        }

        val memory = Array<UByte>(65536) { 0u }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0x0au

        val cpu = createCpu(DefaultMemory(memory))
        cpu.a = 0x00u
        cpu.statusRegister.carryFlag = true

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
    fun `test ASL Accumulator (0x0a) 0-0x80 becomes 1-0x00`() {
        val instruction = instructionSet.instructions[0x0a]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("ASL")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("ACC")
            it.assertThat(instruction.cycles).isEqualTo(2)
        }

        val memory = Array<UByte>(65536) { 0u }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0x0au

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
    fun `test ASL Accumulator (0x0a) 0-0xff becomes 1-0xfe`() {
        val instruction = instructionSet.instructions[0x0a]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("ASL")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("ACC")
            it.assertThat(instruction.cycles).isEqualTo(2)
        }

        val memory = Array<UByte>(65536) { 0u }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0x0au

        val cpu = createCpu(DefaultMemory(memory))
        cpu.a = 0xffu
        cpu.statusRegister.carryFlag = false

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(cpu.a).isEqualTo(0xfe.toUByte())
            it.assertThat(cpu.statusRegister.zeroFlag).isFalse
            it.assertThat(cpu.statusRegister.negativeFlag).isTrue
            it.assertThat(cpu.statusRegister.carryFlag).isTrue
        }
    }

    @Test
    fun `test ASL Accumulator (0x0a) 1-0x7f becomes 0-0xfe`() {
        val instruction = instructionSet.instructions[0x0a]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("ASL")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("ACC")
            it.assertThat(instruction.cycles).isEqualTo(2)
        }

        val memory = Array<UByte>(65536) { 0u }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0x0au

        val cpu = createCpu(DefaultMemory(memory))
        cpu.a = 0x7fu
        cpu.statusRegister.carryFlag = true

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(cpu.a).isEqualTo(0xfe.toUByte())
            it.assertThat(cpu.statusRegister.zeroFlag).isFalse
            it.assertThat(cpu.statusRegister.negativeFlag).isTrue
            it.assertThat(cpu.statusRegister.carryFlag).isFalse
        }
    }

    @Test
    fun `test ASL Zero Page (0x06) 1-0x00 becomes 0-0x00`() {
        val instruction = instructionSet.instructions[0x06]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("ASL")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("ZPP")
            it.assertThat(instruction.cycles).isEqualTo(5)
        }

        val memory = Array<UByte>(65536) { 0x7fu }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0x06u
        memory[0x8001] = 0x00u
        memory[0x0000] = 0x00u

        val cpu = createCpu(DefaultMemory(memory))
        cpu.statusRegister.carryFlag = true

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
    fun `test ASL Zero Page (0x06) 0-0x80 becomes 1-0x00`() {
        val instruction = instructionSet.instructions[0x06]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("ASL")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("ZPP")
            it.assertThat(instruction.cycles).isEqualTo(5)
        }

        val memory = Array<UByte>(65536) { 0x7fu }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0x06u
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
    fun `test ASL Zero Page (0x06) 0-0xff becomes 1-0xfe`() {
        val instruction = instructionSet.instructions[0x06]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("ASL")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("ZPP")
            it.assertThat(instruction.cycles).isEqualTo(5)
        }

        val memory = Array<UByte>(65536) { 0x7fu }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0x06u
        memory[0x8001] = 0x00u
        memory[0x0000] = 0xffu

        val cpu = createCpu(DefaultMemory(memory))
        cpu.statusRegister.carryFlag = false

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(memory[0x0000]).isEqualTo(0xfe.toUByte())
            it.assertThat(cpu.statusRegister.zeroFlag).isFalse
            it.assertThat(cpu.statusRegister.negativeFlag).isTrue
            it.assertThat(cpu.statusRegister.carryFlag).isTrue
        }
    }

    @Test
    fun `test ASL Zero Page (0x06) 1-0x7f becomes 0-0xfe`() {
        val instruction = instructionSet.instructions[0x06]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("ASL")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("ZPP")
            it.assertThat(instruction.cycles).isEqualTo(5)
        }

        val memory = Array<UByte>(65536) { 0x7fu }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0x06u
        memory[0x8001] = 0x00u
        memory[0x0000] = 0x7fu

        val cpu = createCpu(DefaultMemory(memory))
        cpu.statusRegister.carryFlag = true

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(memory[0x0000]).isEqualTo(0xfe.toUByte())
            it.assertThat(cpu.statusRegister.zeroFlag).isFalse
            it.assertThat(cpu.statusRegister.negativeFlag).isTrue
            it.assertThat(cpu.statusRegister.carryFlag).isFalse
        }
    }

    @Test
    fun `test ASL Zero Page,X (0x16) 1-0x00 becomes 0-0x00`() {
        val instruction = instructionSet.instructions[0x16]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("ASL")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("ZPX")
            it.assertThat(instruction.cycles).isEqualTo(6)
        }

        val memory = Array<UByte>(65536) { 0x7fu }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0x16u
        memory[0x8001] = 0x00u
        memory[0x0001] = 0x00u

        val cpu = createCpu(DefaultMemory(memory))
        cpu.x = 0x01u
        cpu.statusRegister.carryFlag = true

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
    fun `test ASL Zero Page,X (0x16) 0-0x80 becomes 1-0x00`() {
        val instruction = instructionSet.instructions[0x16]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("ASL")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("ZPX")
            it.assertThat(instruction.cycles).isEqualTo(6)
        }

        val memory = Array<UByte>(65536) { 0x7fu }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0x16u
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
    fun `test ASL Zero Page,X (0x16) 0-0xff becomes 1-0xfe`() {
        val instruction = instructionSet.instructions[0x16]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("ASL")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("ZPX")
            it.assertThat(instruction.cycles).isEqualTo(6)
        }

        val memory = Array<UByte>(65536) { 0u }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0x16u
        memory[0x8001] = 0x00u
        memory[0x0001] = 0xffu

        val cpu = createCpu(DefaultMemory(memory))
        cpu.x = 0x01u
        cpu.statusRegister.carryFlag = false

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(memory[0x0001]).isEqualTo(0xfe.toUByte())
            it.assertThat(cpu.statusRegister.zeroFlag).isFalse
            it.assertThat(cpu.statusRegister.negativeFlag).isTrue
            it.assertThat(cpu.statusRegister.carryFlag).isTrue
        }
    }

    @Test
    fun `test ASL Zero Page,X (0x16) 1-0x7f becomes 0-0xfe`() {
        val instruction = instructionSet.instructions[0x16]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("ASL")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("ZPX")
            it.assertThat(instruction.cycles).isEqualTo(6)
        }

        val memory = Array<UByte>(65536) { 0u }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0x16u
        memory[0x8001] = 0x00u
        memory[0x0001] = 0x7fu

        val cpu = createCpu(DefaultMemory(memory))
        cpu.x = 0x01u
        cpu.statusRegister.carryFlag = true

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(memory[0x0001]).isEqualTo(0xfe.toUByte())
            it.assertThat(cpu.statusRegister.zeroFlag).isFalse
            it.assertThat(cpu.statusRegister.negativeFlag).isTrue
            it.assertThat(cpu.statusRegister.carryFlag).isFalse
        }
    }

    @Test
    fun `test ASL Absolute (0x0e) 1-0x00 becomes 0-0x00`() {
        val instruction = instructionSet.instructions[0x0e]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("ASL")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("ABS")
            it.assertThat(instruction.cycles).isEqualTo(6)
        }

        val memory = Array<UByte>(65536) { 0x7fu }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0x0eu
        memory[0x8001] = 0x00u
        memory[0x8002] = 0x02u
        memory[0x0200] = 0x00u

        val cpu = createCpu(DefaultMemory(memory))
        cpu.statusRegister.carryFlag = true

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
    fun `test ASL Absolute (0x0e) 0-0x80 becomes 1-0x00`() {
        val instruction = instructionSet.instructions[0x0e]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("ASL")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("ABS")
            it.assertThat(instruction.cycles).isEqualTo(6)
        }

        val memory = Array<UByte>(65536) { 0x7fu }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0x0eu
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
    fun `test ASL Absolute (0x0e) 0-0xff becomes 1-0xfe`() {
        val instruction = instructionSet.instructions[0x0e]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("ASL")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("ABS")
            it.assertThat(instruction.cycles).isEqualTo(6)
        }

        val memory = Array<UByte>(65536) { 0u }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0x0eu
        memory[0x8001] = 0x00u
        memory[0x8002] = 0x02u
        memory[0x0200] = 0xffu

        val cpu = createCpu(DefaultMemory(memory))
        cpu.statusRegister.carryFlag = false

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(memory[0x0200]).isEqualTo(0xfe.toUByte())
            it.assertThat(cpu.statusRegister.zeroFlag).isFalse
            it.assertThat(cpu.statusRegister.negativeFlag).isTrue
            it.assertThat(cpu.statusRegister.carryFlag).isTrue
        }
    }

    @Test
    fun `test ASL Absolute (0x0e) 1-0x7f becomes 0-0xfe`() {
        val instruction = instructionSet.instructions[0x0e]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("ASL")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("ABS")
            it.assertThat(instruction.cycles).isEqualTo(6)
        }

        val memory = Array<UByte>(65536) { 0u }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0x0eu
        memory[0x8001] = 0x00u
        memory[0x8002] = 0x02u
        memory[0x0200] = 0x7fu

        val cpu = createCpu(DefaultMemory(memory))
        cpu.statusRegister.carryFlag = true

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(memory[0x0200]).isEqualTo(0xfe.toUByte())
            it.assertThat(cpu.statusRegister.zeroFlag).isFalse
            it.assertThat(cpu.statusRegister.negativeFlag).isTrue
            it.assertThat(cpu.statusRegister.carryFlag).isFalse
        }
    }

    @Test
    fun `test ASL Absolute,X (0x1e) 1-0x00 becomes 0-0x00`() {
        val instruction = instructionSet.instructions[0x1e]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("ASL")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("ABX")
            it.assertThat(instruction.cycles).isEqualTo(7)
        }

        val memory = Array<UByte>(65536) { 0x7fu }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0x1eu
        memory[0x8001] = 0x00u
        memory[0x8002] = 0x02u
        memory[0x0201] = 0x00u

        val cpu = createCpu(DefaultMemory(memory))
        cpu.x = 0x01u
        cpu.statusRegister.carryFlag = true

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
    fun `test ASL Absolute,X (0x1e) 0-0x80 becomes 1-0x00`() {
        val instruction = instructionSet.instructions[0x1e]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("ASL")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("ABX")
            it.assertThat(instruction.cycles).isEqualTo(7)
        }

        val memory = Array<UByte>(65536) { 0x7fu }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0x1eu
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
    fun `test ASL Absolute,X (0x1e) 0-0xff becomes 1-0xfe`() {
        val instruction = instructionSet.instructions[0x1e]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("ASL")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("ABX")
            it.assertThat(instruction.cycles).isEqualTo(7)
        }

        val memory = Array<UByte>(65536) { 0u }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0x1eu
        memory[0x8001] = 0x00u
        memory[0x8002] = 0x02u
        memory[0x0201] = 0xffu

        val cpu = createCpu(DefaultMemory(memory))
        cpu.x = 0x01u
        cpu.statusRegister.carryFlag = false

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(memory[0x0201]).isEqualTo(0xfe.toUByte())
            it.assertThat(cpu.statusRegister.zeroFlag).isFalse
            it.assertThat(cpu.statusRegister.negativeFlag).isTrue
            it.assertThat(cpu.statusRegister.carryFlag).isTrue
        }
    }

    @Test
    fun `test ASL Absolute,X (0x1e) 1-0x7f becomes 0-0xfe`() {
        val instruction = instructionSet.instructions[0x1e]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("ASL")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("ABX")
            it.assertThat(instruction.cycles).isEqualTo(7)
        }

        val memory = Array<UByte>(65536) { 0u }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0x1eu
        memory[0x8001] = 0x00u
        memory[0x8002] = 0x02u
        memory[0x0201] = 0x7fu

        val cpu = createCpu(DefaultMemory(memory))
        cpu.x = 0x01u
        cpu.statusRegister.carryFlag = true

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(memory[0x0201]).isEqualTo(0xfe.toUByte())
            it.assertThat(cpu.statusRegister.zeroFlag).isFalse
            it.assertThat(cpu.statusRegister.negativeFlag).isTrue
            it.assertThat(cpu.statusRegister.carryFlag).isFalse
        }
    }

    @Test
    fun `test LSR Accumulator (0x4a) 0x00-1 becomes 0x00-0`() {
        val instruction = instructionSet.instructions[0x4a]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("LSR")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("ACC")
            it.assertThat(instruction.cycles).isEqualTo(2)
        }

        val memory = Array<UByte>(65536) { 0u }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0x4au

        val cpu = createCpu(DefaultMemory(memory))
        cpu.a = 0x00u
        cpu.statusRegister.carryFlag = true

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
    fun `test LSR Accumulator (0x4a) 0x01-0 becomes 0x00-1`() {
        val instruction = instructionSet.instructions[0x4a]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("LSR")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("ACC")
            it.assertThat(instruction.cycles).isEqualTo(2)
        }

        val memory = Array<UByte>(65536) { 0u }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0x4au

        val cpu = createCpu(DefaultMemory(memory))
        cpu.a = 0x01u
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
    fun `test LSR Accumulator (0x4a) 0xff-0 becomes 0x7f-1`() {
        val instruction = instructionSet.instructions[0x4a]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("LSR")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("ACC")
            it.assertThat(instruction.cycles).isEqualTo(2)
        }

        val memory = Array<UByte>(65536) { 0u }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0x4au

        val cpu = createCpu(DefaultMemory(memory))
        cpu.a = 0xffu
        cpu.statusRegister.carryFlag = false

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(cpu.a).isEqualTo(0x7f.toUByte())
            it.assertThat(cpu.statusRegister.zeroFlag).isFalse
            it.assertThat(cpu.statusRegister.negativeFlag).isFalse
            it.assertThat(cpu.statusRegister.carryFlag).isTrue
        }
    }

    @Test
    fun `test LSR Accumulator (0x4a) 0xfe-1 becomes 0x7f-0`() {
        val instruction = instructionSet.instructions[0x4a]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("LSR")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("ACC")
            it.assertThat(instruction.cycles).isEqualTo(2)
        }

        val memory = Array<UByte>(65536) { 0u }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0x4au

        val cpu = createCpu(DefaultMemory(memory))
        cpu.a = 0xfeu
        cpu.statusRegister.carryFlag = true

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(cpu.a).isEqualTo(0x7f.toUByte())
            it.assertThat(cpu.statusRegister.zeroFlag).isFalse
            it.assertThat(cpu.statusRegister.negativeFlag).isFalse
            it.assertThat(cpu.statusRegister.carryFlag).isFalse
        }
    }

    @Test
    fun `test LSR Zero Page (0x46) 0x00-1 becomes 0x00-0`() {
        val instruction = instructionSet.instructions[0x46]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("LSR")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("ZPP")
            it.assertThat(instruction.cycles).isEqualTo(5)
        }

        val memory = Array<UByte>(65536) { 0x7fu }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0x46u
        memory[0x8001] = 0x00u
        memory[0x0000] = 0x00u

        val cpu = createCpu(DefaultMemory(memory))
        cpu.statusRegister.carryFlag = true

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
    fun `test LSR Zero Page (0x46) 0x01-0 becomes 0x00-1`() {
        val instruction = instructionSet.instructions[0x46]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("LSR")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("ZPP")
            it.assertThat(instruction.cycles).isEqualTo(5)
        }

        val memory = Array<UByte>(65536) { 0x7fu }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0x46u
        memory[0x8001] = 0x00u
        memory[0x0000] = 0x01u

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
    fun `test LSR Zero Page (0x46) 0xff-0 becomes 0x7f-1`() {
        val instruction = instructionSet.instructions[0x46]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("LSR")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("ZPP")
            it.assertThat(instruction.cycles).isEqualTo(5)
        }

        val memory = Array<UByte>(65536) { 0x7fu }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0x46u
        memory[0x8001] = 0x00u
        memory[0x0000] = 0xffu

        val cpu = createCpu(DefaultMemory(memory))
        cpu.statusRegister.carryFlag = false

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(memory[0x0000]).isEqualTo(0x7f.toUByte())
            it.assertThat(cpu.statusRegister.zeroFlag).isFalse
            it.assertThat(cpu.statusRegister.negativeFlag).isFalse
            it.assertThat(cpu.statusRegister.carryFlag).isTrue
        }
    }

    @Test
    fun `test LSR Zero Page (0x46) 0xfe-1 becomes 0x7f-0`() {
        val instruction = instructionSet.instructions[0x46]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("LSR")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("ZPP")
            it.assertThat(instruction.cycles).isEqualTo(5)
        }

        val memory = Array<UByte>(65536) { 0x7fu }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0x46u
        memory[0x8001] = 0x00u
        memory[0x0000] = 0xfeu

        val cpu = createCpu(DefaultMemory(memory))
        cpu.statusRegister.carryFlag = true

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(memory[0x0000]).isEqualTo(0x7f.toUByte())
            it.assertThat(cpu.statusRegister.zeroFlag).isFalse
            it.assertThat(cpu.statusRegister.negativeFlag).isFalse
            it.assertThat(cpu.statusRegister.carryFlag).isFalse
        }
    }

    @Test
    fun `test LSR Zero Page,X (0x56) 0x00-1 becomes 0x00-0`() {
        val instruction = instructionSet.instructions[0x56]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("LSR")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("ZPX")
            it.assertThat(instruction.cycles).isEqualTo(6)
        }

        val memory = Array<UByte>(65536) { 0x7fu }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0x56u
        memory[0x8001] = 0x00u
        memory[0x0001] = 0x00u

        val cpu = createCpu(DefaultMemory(memory))
        cpu.x = 0x01u
        cpu.statusRegister.carryFlag = true

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
    fun `test LSR Zero Page,X (0x56) 0x01-0 becomes 0x00-1`() {
        val instruction = instructionSet.instructions[0x56]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("LSR")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("ZPX")
            it.assertThat(instruction.cycles).isEqualTo(6)
        }

        val memory = Array<UByte>(65536) { 0x7fu }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0x56u
        memory[0x8001] = 0x00u
        memory[0x0001] = 0x01u

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
    fun `test LSR Zero Page,X (0x56) 0xff-0 becomes 0x7f-1`() {
        val instruction = instructionSet.instructions[0x56]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("LSR")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("ZPX")
            it.assertThat(instruction.cycles).isEqualTo(6)
        }

        val memory = Array<UByte>(65536) { 0u }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0x56u
        memory[0x8001] = 0x00u
        memory[0x0001] = 0xffu

        val cpu = createCpu(DefaultMemory(memory))
        cpu.x = 0x01u
        cpu.statusRegister.carryFlag = false

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(memory[0x0001]).isEqualTo(0x7f.toUByte())
            it.assertThat(cpu.statusRegister.zeroFlag).isFalse
            it.assertThat(cpu.statusRegister.negativeFlag).isFalse
            it.assertThat(cpu.statusRegister.carryFlag).isTrue
        }
    }

    @Test
    fun `test LSR Zero Page,X (0x56) 0xfe-1 becomes 0x7f-0`() {
        val instruction = instructionSet.instructions[0x56]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("LSR")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("ZPX")
            it.assertThat(instruction.cycles).isEqualTo(6)
        }

        val memory = Array<UByte>(65536) { 0u }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0x56u
        memory[0x8001] = 0x00u
        memory[0x0001] = 0xfeu

        val cpu = createCpu(DefaultMemory(memory))
        cpu.x = 0x01u
        cpu.statusRegister.carryFlag = true

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(memory[0x0001]).isEqualTo(0x7f.toUByte())
            it.assertThat(cpu.statusRegister.zeroFlag).isFalse
            it.assertThat(cpu.statusRegister.negativeFlag).isFalse
            it.assertThat(cpu.statusRegister.carryFlag).isFalse
        }
    }

    @Test
    fun `test LSR Absolute (0x4e) 0x00-1 becomes 0x00-0`() {
        val instruction = instructionSet.instructions[0x4e]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("LSR")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("ABS")
            it.assertThat(instruction.cycles).isEqualTo(6)
        }

        val memory = Array<UByte>(65536) { 0x7fu }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0x4eu
        memory[0x8001] = 0x00u
        memory[0x8002] = 0x02u
        memory[0x0200] = 0x00u

        val cpu = createCpu(DefaultMemory(memory))
        cpu.statusRegister.carryFlag = true

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
    fun `test LSR Absolute (0x4e) 0x01-0 becomes 0x00-1`() {
        val instruction = instructionSet.instructions[0x4e]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("LSR")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("ABS")
            it.assertThat(instruction.cycles).isEqualTo(6)
        }

        val memory = Array<UByte>(65536) { 0x7fu }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0x4eu
        memory[0x8001] = 0x00u
        memory[0x8002] = 0x02u
        memory[0x0200] = 0x01u

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
    fun `test LSR Absolute (0x4e) 0xff-0 becomes 0x7f-1`() {
        val instruction = instructionSet.instructions[0x4e]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("LSR")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("ABS")
            it.assertThat(instruction.cycles).isEqualTo(6)
        }

        val memory = Array<UByte>(65536) { 0u }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0x4eu
        memory[0x8001] = 0x00u
        memory[0x8002] = 0x02u
        memory[0x0200] = 0xffu

        val cpu = createCpu(DefaultMemory(memory))
        cpu.statusRegister.carryFlag = false

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(memory[0x0200]).isEqualTo(0x7f.toUByte())
            it.assertThat(cpu.statusRegister.zeroFlag).isFalse
            it.assertThat(cpu.statusRegister.negativeFlag).isFalse
            it.assertThat(cpu.statusRegister.carryFlag).isTrue
        }
    }

    @Test
    fun `test LSR Absolute (0x4e) 0xfe-1 becomes 0x7f-0`() {
        val instruction = instructionSet.instructions[0x4e]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("LSR")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("ABS")
            it.assertThat(instruction.cycles).isEqualTo(6)
        }

        val memory = Array<UByte>(65536) { 0u }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0x4eu
        memory[0x8001] = 0x00u
        memory[0x8002] = 0x02u
        memory[0x0200] = 0xfeu

        val cpu = createCpu(DefaultMemory(memory))
        cpu.statusRegister.carryFlag = true

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(memory[0x0200]).isEqualTo(0x7f.toUByte())
            it.assertThat(cpu.statusRegister.zeroFlag).isFalse
            it.assertThat(cpu.statusRegister.negativeFlag).isFalse
            it.assertThat(cpu.statusRegister.carryFlag).isFalse
        }
    }

    @Test
    fun `test LSR Absolute,X (0x5e) 0x00-1 becomes 0x00-0`() {
        val instruction = instructionSet.instructions[0x5e]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("LSR")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("ABX")
            it.assertThat(instruction.cycles).isEqualTo(7)
        }

        val memory = Array<UByte>(65536) { 0x7fu }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0x5eu
        memory[0x8001] = 0x00u
        memory[0x8002] = 0x02u
        memory[0x0201] = 0x00u

        val cpu = createCpu(DefaultMemory(memory))
        cpu.x = 0x01u
        cpu.statusRegister.carryFlag = true

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
    fun `test LSR Absolute,X (0x5e) 0x01-0 becomes 0x00-1`() {
        val instruction = instructionSet.instructions[0x5e]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("LSR")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("ABX")
            it.assertThat(instruction.cycles).isEqualTo(7)
        }

        val memory = Array<UByte>(65536) { 0x7fu }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0x5eu
        memory[0x8001] = 0x00u
        memory[0x8002] = 0x02u
        memory[0x0201] = 0x01u

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
    fun `test LSR Absolute,X (0x5e) 0xff-0 becomes 0x7f-1`() {
        val instruction = instructionSet.instructions[0x5e]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("LSR")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("ABX")
            it.assertThat(instruction.cycles).isEqualTo(7)
        }

        val memory = Array<UByte>(65536) { 0u }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0x5eu
        memory[0x8001] = 0x00u
        memory[0x8002] = 0x02u
        memory[0x0201] = 0xffu

        val cpu = createCpu(DefaultMemory(memory))
        cpu.x = 0x01u
        cpu.statusRegister.carryFlag = false

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(memory[0x0201]).isEqualTo(0x7f.toUByte())
            it.assertThat(cpu.statusRegister.zeroFlag).isFalse
            it.assertThat(cpu.statusRegister.negativeFlag).isFalse
            it.assertThat(cpu.statusRegister.carryFlag).isTrue
        }
    }

    @Test
    fun `test LSR Absolute,X (0x5e) 0xfe-1 becomes 0x7f-0`() {
        val instruction = instructionSet.instructions[0x5e]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("LSR")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("ABX")
            it.assertThat(instruction.cycles).isEqualTo(7)
        }

        val memory = Array<UByte>(65536) { 0u }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0x5eu
        memory[0x8001] = 0x00u
        memory[0x8002] = 0x02u
        memory[0x0201] = 0xfeu

        val cpu = createCpu(DefaultMemory(memory))
        cpu.x = 0x01u
        cpu.statusRegister.carryFlag = true

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(memory[0x0201]).isEqualTo(0x7f.toUByte())
            it.assertThat(cpu.statusRegister.zeroFlag).isFalse
            it.assertThat(cpu.statusRegister.negativeFlag).isFalse
            it.assertThat(cpu.statusRegister.carryFlag).isFalse
        }
    }
}