package com.puffinsoft.k6502

import com.puffinsoft.k6502.inbuilt.DefaultMemory
import org.assertj.core.api.Assertions
import org.assertj.core.api.SoftAssertions
import org.junit.jupiter.api.Test

class LoadAccumulatorInstructionsTest : BaseInstructionsTest() {
    @Test
    fun `test LDA Immediate (0xa9) no flags set`() {
        val instruction = instructionSet.instructions[0xa9]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("LDA")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("IMM")
            it.assertThat(instruction.cycles).isEqualTo(2)
        }

        val memory = Array<UByte>(65536) { 0u }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0xa9u
        memory[0x8001] = 0x42u

        val cpu = createCpu(DefaultMemory(memory))

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(cpu.a).isEqualTo(0x42.toUByte())
            it.assertThat(cpu.statusRegister.zeroFlag).isFalse
            it.assertThat(cpu.statusRegister.negativeFlag).isFalse
        }
    }

    @Test
    fun `test LDA Immediate (0xa9) zero flag set`() {
        val instruction = instructionSet.instructions[0xa9]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("LDA")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("IMM")
            it.assertThat(instruction.cycles).isEqualTo(2)
        }

        val memory = Array<UByte>(65536) { 0x7fu }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0xa9u
        memory[0x8001] = 0x00u

        val cpu = createCpu(DefaultMemory(memory))

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(cpu.a).isEqualTo(0x00.toUByte())
            it.assertThat(cpu.statusRegister.zeroFlag).isTrue
            it.assertThat(cpu.statusRegister.negativeFlag).isFalse
        }
    }

    @Test
    fun `test LDA Immediate (0xa9) negative flag set`() {
        val instruction = instructionSet.instructions[0xa9]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("LDA")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("IMM")
            it.assertThat(instruction.cycles).isEqualTo(2)
        }

        val memory = Array<UByte>(65536) { 0u }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0xa9u
        memory[0x8001] = 0x8fu

        val cpu = createCpu(DefaultMemory(memory))

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(cpu.a).isEqualTo(0x8f.toUByte())
            it.assertThat(cpu.statusRegister.zeroFlag).isFalse
            it.assertThat(cpu.statusRegister.negativeFlag).isTrue
        }
    }

    @Test
    fun `test LDA Zero Page (0xa5) no flags set`() {
        val instruction = instructionSet.instructions[0xa5]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("LDA")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("ZPP")
            it.assertThat(instruction.cycles).isEqualTo(3)
        }

        val memory = Array<UByte>(65536) { 0u }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0xa5u
        memory[0x8001] = 0xffu
        memory[0x00ff] = 0x42u

        val cpu = createCpu(DefaultMemory(memory))

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(cpu.a).isEqualTo(0x42.toUByte())
            it.assertThat(cpu.statusRegister.zeroFlag).isFalse
            it.assertThat(cpu.statusRegister.negativeFlag).isFalse
        }
    }

    @Test
    fun `test LDA Zero Page (0xa5) zero flag set`() {
        val instruction = instructionSet.instructions[0xa5]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("LDA")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("ZPP")
            it.assertThat(instruction.cycles).isEqualTo(3)
        }

        val memory = Array<UByte>(65536) { 0x7fu }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0xa5u
        memory[0x8001] = 0xffu
        memory[0x00ff] = 0x00u

        val cpu = createCpu(DefaultMemory(memory))

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(cpu.a).isEqualTo(0x00.toUByte())
            it.assertThat(cpu.statusRegister.zeroFlag).isTrue
            it.assertThat(cpu.statusRegister.negativeFlag).isFalse
        }
    }

    @Test
    fun `test LDA Zero Page (0xa5) negative flag set`() {
        val instruction = instructionSet.instructions[0xa5]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("LDA")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("ZPP")
            it.assertThat(instruction.cycles).isEqualTo(3)
        }

        val memory = Array<UByte>(65536) { 0u }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0xa5u
        memory[0x8001] = 0xffu
        memory[0x00ff] = 0x8fu

        val cpu = createCpu(DefaultMemory(memory))

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(cpu.a).isEqualTo(0x8f.toUByte())
            it.assertThat(cpu.statusRegister.zeroFlag).isFalse
            it.assertThat(cpu.statusRegister.negativeFlag).isTrue
        }
    }

    @Test
    fun `test LDA Zero Page,X (0xb5) no flags set`() {
        val instruction = instructionSet.instructions[0xb5]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("LDA")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("ZPX")
            it.assertThat(instruction.cycles).isEqualTo(4)
        }

        val memory = Array<UByte>(65536) { 0u }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0xb5u
        memory[0x8001] = 0x0fu
        memory[0x00ff] = 0x42u

        val cpu = createCpu(DefaultMemory(memory))
        cpu.x = 0xf0u

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(cpu.a).isEqualTo(0x42.toUByte())
            it.assertThat(cpu.statusRegister.zeroFlag).isFalse
            it.assertThat(cpu.statusRegister.negativeFlag).isFalse
        }
    }

    @Test
    fun `test LDA Zero Page,X (0xb5) zero flag set`() {
        val instruction = instructionSet.instructions[0xb5]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("LDA")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("ZPX")
            it.assertThat(instruction.cycles).isEqualTo(4)
        }

        val memory = Array<UByte>(65536) { 0x7fu }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0xb5u
        memory[0x8001] = 0x0fu
        memory[0x00ff] = 0x00u

        val cpu = createCpu(DefaultMemory(memory))
        cpu.x = 0xf0u

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(cpu.a).isEqualTo(0x00.toUByte())
            it.assertThat(cpu.statusRegister.zeroFlag).isTrue
            it.assertThat(cpu.statusRegister.negativeFlag).isFalse
        }
    }

    @Test
    fun `test LDA Zero Page,X (0xb5) negative flag set`() {
        val instruction = instructionSet.instructions[0xb5]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("LDA")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("ZPX")
            it.assertThat(instruction.cycles).isEqualTo(4)
        }

        val memory = Array<UByte>(65536) { 0u }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0xb5u
        memory[0x8001] = 0x0fu
        memory[0x00ff] = 0x8fu

        val cpu = createCpu(DefaultMemory(memory))
        cpu.x = 0xf0u

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(cpu.a).isEqualTo(0x8f.toUByte())
            it.assertThat(cpu.statusRegister.zeroFlag).isFalse
            it.assertThat(cpu.statusRegister.negativeFlag).isTrue
        }
    }

    @Test
    fun `test LDA Absolute (0xad) no flags set`() {
        val instruction = instructionSet.instructions[0xad]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("LDA")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("ABS")
            it.assertThat(instruction.cycles).isEqualTo(4)
        }

        val memory = Array<UByte>(65536) { 0u }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0xadu
        memory[0x8001] = 0x00u
        memory[0x8002] = 0x02u
        memory[0x0200] = 0x42u

        val cpu = createCpu(DefaultMemory(memory))

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(cpu.a).isEqualTo(0x42.toUByte())
            it.assertThat(cpu.statusRegister.zeroFlag).isFalse
            it.assertThat(cpu.statusRegister.negativeFlag).isFalse
        }
    }

    @Test
    fun `test LDA Absolute (0xad) zero flag set`() {
        val instruction = instructionSet.instructions[0xad]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("LDA")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("ABS")
            it.assertThat(instruction.cycles).isEqualTo(4)
        }

        val memory = Array<UByte>(65536) { 0x7fu }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0xadu
        memory[0x8001] = 0x00u
        memory[0x8002] = 0x02u
        memory[0x0200] = 0x00u

        val cpu = createCpu(DefaultMemory(memory))

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(cpu.a).isEqualTo(0x00.toUByte())
            it.assertThat(cpu.statusRegister.zeroFlag).isTrue
            it.assertThat(cpu.statusRegister.negativeFlag).isFalse
        }
    }

    @Test
    fun `test LDA Absolute (0xad) negative flag set`() {
        val instruction = instructionSet.instructions[0xad]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("LDA")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("ABS")
            it.assertThat(instruction.cycles).isEqualTo(4)
        }

        val memory = Array<UByte>(65536) { 0u }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0xadu
        memory[0x8001] = 0x00u
        memory[0x8002] = 0x02u
        memory[0x0200] = 0x8fu

        val cpu = createCpu(DefaultMemory(memory))

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(cpu.a).isEqualTo(0x8f.toUByte())
            it.assertThat(cpu.statusRegister.zeroFlag).isFalse
            it.assertThat(cpu.statusRegister.negativeFlag).isTrue
        }
    }

    @Test
    fun `test LDA Absolute,X (0xbd) no flags set`() {
        val instruction = instructionSet.instructions[0xbd]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("LDA")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("ABX")
            it.assertThat(instruction.cycles).isEqualTo(4)
        }

        val memory = Array<UByte>(65536) { 0u }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0xbdu
        memory[0x8001] = 0x00u
        memory[0x8002] = 0x02u
        memory[0x0201] = 0x42u

        val cpu = createCpu(DefaultMemory(memory))
        cpu.x = 0x01u

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(cpu.a).isEqualTo(0x42.toUByte())
            it.assertThat(cpu.statusRegister.zeroFlag).isFalse
            it.assertThat(cpu.statusRegister.negativeFlag).isFalse
        }
    }

    @Test
    fun `test LDA Absolute,X (0xbd) zero flag set`() {
        val instruction = instructionSet.instructions[0xbd]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("LDA")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("ABX")
            it.assertThat(instruction.cycles).isEqualTo(4)
        }

        val memory = Array<UByte>(65536) { 0x7fu }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0xbdu
        memory[0x8001] = 0x00u
        memory[0x8002] = 0x02u
        memory[0x0201] = 0x00u

        val cpu = createCpu(DefaultMemory(memory))
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
    fun `test LDA Absolute,X (0xbd) negative flag set`() {
        val instruction = instructionSet.instructions[0xbd]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("LDA")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("ABX")
            it.assertThat(instruction.cycles).isEqualTo(4)
        }

        val memory = Array<UByte>(65536) { 0u }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0xbdu
        memory[0x8001] = 0x00u
        memory[0x8002] = 0x02u
        memory[0x0201] = 0x8fu

        val cpu = createCpu(DefaultMemory(memory))
        cpu.x = 0x01u

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(cpu.a).isEqualTo(0x8f.toUByte())
            it.assertThat(cpu.statusRegister.zeroFlag).isFalse
            it.assertThat(cpu.statusRegister.negativeFlag).isTrue
        }
    }

    @Test
    fun `test LDA Absolute,X (0xbd) no flags set with cross page`() {
        val instruction = instructionSet.instructions[0xbd]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("LDA")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("ABX")
            it.assertThat(instruction.cycles).isEqualTo(4)
        }

        val memory = Array<UByte>(65536) { 0u }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0xbdu
        memory[0x8001] = 0xffu
        memory[0x8002] = 0x02u
        memory[0x0300] = 0x42u

        val cpu = createCpu(DefaultMemory(memory))
        cpu.x = 0x01u

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles + 1)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(cpu.a).isEqualTo(0x42.toUByte())
            it.assertThat(cpu.statusRegister.zeroFlag).isFalse
            it.assertThat(cpu.statusRegister.negativeFlag).isFalse
        }
    }

    @Test
    fun `test LDA Absolute,X (0xbd) zero flag set with cross page`() {
        val instruction = instructionSet.instructions[0xbd]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("LDA")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("ABX")
            it.assertThat(instruction.cycles).isEqualTo(4)
        }

        val memory = Array<UByte>(65536) { 0x7fu }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0xbdu
        memory[0x8001] = 0xffu
        memory[0x8002] = 0x02u
        memory[0x0300] = 0x00u

        val cpu = createCpu(DefaultMemory(memory))
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
    fun `test LDA Absolute,X (0xbd) negative flag set with cross page`() {
        val instruction = instructionSet.instructions[0xbd]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("LDA")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("ABX")
            it.assertThat(instruction.cycles).isEqualTo(4)
        }

        val memory = Array<UByte>(65536) { 0u }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0xbdu
        memory[0x8001] = 0xffu
        memory[0x8002] = 0x02u
        memory[0x0300] = 0x8fu

        val cpu = createCpu(DefaultMemory(memory))
        cpu.x = 0x01u

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles + 1)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(cpu.a).isEqualTo(0x8f.toUByte())
            it.assertThat(cpu.statusRegister.zeroFlag).isFalse
            it.assertThat(cpu.statusRegister.negativeFlag).isTrue
        }
    }

    @Test
    fun `test LDA Absolute,Y (0xb9) no flags set`() {
        val instruction = instructionSet.instructions[0xb9]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("LDA")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("ABY")
            it.assertThat(instruction.cycles).isEqualTo(4)
        }

        val memory = Array<UByte>(65536) { 0u }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0xb9u
        memory[0x8001] = 0x00u
        memory[0x8002] = 0x02u
        memory[0x0201] = 0x42u

        val cpu = createCpu(DefaultMemory(memory))
        cpu.y = 0x01u

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(cpu.a).isEqualTo(0x42.toUByte())
            it.assertThat(cpu.statusRegister.zeroFlag).isFalse
            it.assertThat(cpu.statusRegister.negativeFlag).isFalse
        }
    }

    @Test
    fun `test LDA Absolute,Y (0xb9) zero flag set`() {
        val instruction = instructionSet.instructions[0xb9]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("LDA")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("ABY")
            it.assertThat(instruction.cycles).isEqualTo(4)
        }

        val memory = Array<UByte>(65536) { 0x7fu }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0xb9u
        memory[0x8001] = 0x00u
        memory[0x8002] = 0x02u
        memory[0x0201] = 0x00u

        val cpu = createCpu(DefaultMemory(memory))
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
    fun `test LDA Absolute,Y (0xb9) negative flag set`() {
        val instruction = instructionSet.instructions[0xb9]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("LDA")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("ABY")
            it.assertThat(instruction.cycles).isEqualTo(4)
        }

        val memory = Array<UByte>(65536) { 0u }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0xb9u
        memory[0x8001] = 0x00u
        memory[0x8002] = 0x02u
        memory[0x0201] = 0x8fu

        val cpu = createCpu(DefaultMemory(memory))
        cpu.y = 0x01u

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(cpu.a).isEqualTo(0x8f.toUByte())
            it.assertThat(cpu.statusRegister.zeroFlag).isFalse
            it.assertThat(cpu.statusRegister.negativeFlag).isTrue
        }
    }

    @Test
    fun `test LDA Absolute,Y (0xb9) no flags set with cross page`() {
        val instruction = instructionSet.instructions[0xb9]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("LDA")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("ABY")
            it.assertThat(instruction.cycles).isEqualTo(4)
        }

        val memory = Array<UByte>(65536) { 0u }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0xb9u
        memory[0x8001] = 0xffu
        memory[0x8002] = 0x02u
        memory[0x0300] = 0x42u

        val cpu = createCpu(DefaultMemory(memory))
        cpu.y = 0x01u

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles + 1)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(cpu.a).isEqualTo(0x42.toUByte())
            it.assertThat(cpu.statusRegister.zeroFlag).isFalse
            it.assertThat(cpu.statusRegister.negativeFlag).isFalse
        }
    }

    @Test
    fun `test LDA Absolute,Y (0xb9) zero flag set with cross page`() {
        val instruction = instructionSet.instructions[0xb9]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("LDA")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("ABY")
            it.assertThat(instruction.cycles).isEqualTo(4)
        }

        val memory = Array<UByte>(65536) { 0x7fu }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0xb9u
        memory[0x8001] = 0xffu
        memory[0x8002] = 0x02u
        memory[0x0300] = 0x00u

        val cpu = createCpu(DefaultMemory(memory))
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
    fun `test LDA Absolute,Y (0xb9) negative flag set with cross page`() {
        val instruction = instructionSet.instructions[0xb9]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("LDA")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("ABY")
            it.assertThat(instruction.cycles).isEqualTo(4)
        }

        val memory = Array<UByte>(65536) { 0u }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0xb9u
        memory[0x8001] = 0xffu
        memory[0x8002] = 0x02u
        memory[0x0300] = 0x8fu

        val cpu = createCpu(DefaultMemory(memory))
        cpu.y = 0x01u

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles + 1)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(cpu.a).isEqualTo(0x8f.toUByte())
            it.assertThat(cpu.statusRegister.zeroFlag).isFalse
            it.assertThat(cpu.statusRegister.negativeFlag).isTrue
        }
    }

    @Test
    fun `test LDA Indirect,X (0xa1) no flags set`() {
        val instruction = instructionSet.instructions[0xa1]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("LDA")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("IZX")
            it.assertThat(instruction.cycles).isEqualTo(6)
        }

        val memory = Array<UByte>(65536) { 0u }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0xa1u
        memory[0x8001] = 0x01u
        memory[0x00f1] = 0x00u
        memory[0x00f2] = 0x02u
        memory[0x0200] = 0x42u

        val cpu = createCpu(DefaultMemory(memory))
        cpu.x = 0xf0u

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(cpu.a).isEqualTo(0x42.toUByte())
            it.assertThat(cpu.statusRegister.zeroFlag).isFalse
            it.assertThat(cpu.statusRegister.negativeFlag).isFalse
        }
    }

    @Test
    fun `test LDA Indirect,X (0xa1) zero flag set`() {
        val instruction = instructionSet.instructions[0xa1]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("LDA")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("IZX")
            it.assertThat(instruction.cycles).isEqualTo(6)
        }

        val memory = Array<UByte>(65536) { 0x7fu }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0xa1u
        memory[0x8001] = 0x01u
        memory[0x00f1] = 0x00u
        memory[0x00f2] = 0x02u
        memory[0x0200] = 0x00u

        val cpu = createCpu(DefaultMemory(memory))
        cpu.x = 0xf0u

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(cpu.a).isEqualTo(0x00.toUByte())
            it.assertThat(cpu.statusRegister.zeroFlag).isTrue
            it.assertThat(cpu.statusRegister.negativeFlag).isFalse
        }
    }

    @Test
    fun `test LDA Indirect,X (0xa1) negative flag set`() {
        val instruction = instructionSet.instructions[0xa1]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("LDA")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("IZX")
            it.assertThat(instruction.cycles).isEqualTo(6)
        }

        val memory = Array<UByte>(65536) { 0u }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0xa1u
        memory[0x8001] = 0x01u
        memory[0x00f1] = 0x00u
        memory[0x00f2] = 0x02u
        memory[0x0200] = 0x8fu

        val cpu = createCpu(DefaultMemory(memory))
        cpu.x = 0xf0u

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(cpu.a).isEqualTo(0x8f.toUByte())
            it.assertThat(cpu.statusRegister.zeroFlag).isFalse
            it.assertThat(cpu.statusRegister.negativeFlag).isTrue
        }
    }

    @Test
    fun `test LDA Indirect,X (0xa1) no flags set with wrap`() {
        val instruction = instructionSet.instructions[0xa1]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("LDA")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("IZX")
            it.assertThat(instruction.cycles).isEqualTo(6)
        }

        val memory = Array<UByte>(65536) { 0u }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0xa1u
        memory[0x8001] = 0x10u
        memory[0x00ff] = 0x00u
        memory[0x0000] = 0x02u
        memory[0x0200] = 0x42u

        val cpu = createCpu(DefaultMemory(memory))
        cpu.x = 0xefu

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(cpu.a).isEqualTo(0x42.toUByte())
            it.assertThat(cpu.statusRegister.zeroFlag).isFalse
            it.assertThat(cpu.statusRegister.negativeFlag).isFalse
        }
    }

    @Test
    fun `test LDA Indirect,X (0xa1) zero flag set with wrap`() {
        val instruction = instructionSet.instructions[0xa1]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("LDA")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("IZX")
            it.assertThat(instruction.cycles).isEqualTo(6)
        }

        val memory = Array<UByte>(65536) { 0x7fu }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0xa1u
        memory[0x8001] = 0x10u
        memory[0x00ff] = 0x00u
        memory[0x0000] = 0x02u
        memory[0x0200] = 0x00u

        val cpu = createCpu(DefaultMemory(memory))
        cpu.x = 0xefu

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(cpu.a).isEqualTo(0x00.toUByte())
            it.assertThat(cpu.statusRegister.zeroFlag).isTrue
            it.assertThat(cpu.statusRegister.negativeFlag).isFalse
        }
    }

    @Test
    fun `test LDA Indirect,X (0xa1) negative flag set with wrap`() {
        val instruction = instructionSet.instructions[0xa1]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("LDA")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("IZX")
            it.assertThat(instruction.cycles).isEqualTo(6)
        }

        val memory = Array<UByte>(65536) { 0u }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0xa1u
        memory[0x8001] = 0x10u
        memory[0x00ff] = 0x00u
        memory[0x0000] = 0x02u
        memory[0x0200] = 0x8fu

        val cpu = createCpu(DefaultMemory(memory))
        cpu.x = 0xefu

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(cpu.a).isEqualTo(0x8f.toUByte())
            it.assertThat(cpu.statusRegister.zeroFlag).isFalse
            it.assertThat(cpu.statusRegister.negativeFlag).isTrue
        }
    }

    @Test
    fun `test LDA Indirect,Y (0xb1) no flags set`() {
        val instruction = instructionSet.instructions[0xb1]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("LDA")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("IZY")
            it.assertThat(instruction.cycles).isEqualTo(5)
        }

        val memory = Array<UByte>(65536) { 0u }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0xb1u
        memory[0x8001] = 0x10u
        memory[0x0010] = 0x00u
        memory[0x0011] = 0x02u
        memory[0x02ff] = 0x42u

        val cpu = createCpu(DefaultMemory(memory))
        cpu.y = 0xffu

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(cpu.a).isEqualTo(0x42.toUByte())
            it.assertThat(cpu.statusRegister.zeroFlag).isFalse
            it.assertThat(cpu.statusRegister.negativeFlag).isFalse
        }
    }

    @Test
    fun `test LDA Indirect,Y (0xb1) zero flag set`() {
        val instruction = instructionSet.instructions[0xb1]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("LDA")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("IZY")
            it.assertThat(instruction.cycles).isEqualTo(5)
        }

        val memory = Array<UByte>(65536) { 0x7fu }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0xb1u
        memory[0x8001] = 0x10u
        memory[0x0010] = 0x00u
        memory[0x0011] = 0x02u
        memory[0x02ff] = 0x00u

        val cpu = createCpu(DefaultMemory(memory))
        cpu.y = 0xffu

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(cpu.a).isEqualTo(0x00.toUByte())
            it.assertThat(cpu.statusRegister.zeroFlag).isTrue
            it.assertThat(cpu.statusRegister.negativeFlag).isFalse
        }
    }

    @Test
    fun `test LDA Indirect,Y (0xb1) negative flag set`() {
        val instruction = instructionSet.instructions[0xb1]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("LDA")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("IZY")
            it.assertThat(instruction.cycles).isEqualTo(5)
        }

        val memory = Array<UByte>(65536) { 0u }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0xb1u
        memory[0x8001] = 0x10u
        memory[0x0010] = 0x00u
        memory[0x0011] = 0x02u
        memory[0x02ff] = 0x8fu

        val cpu = createCpu(DefaultMemory(memory))
        cpu.y = 0xffu

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(cpu.a).isEqualTo(0x8f.toUByte())
            it.assertThat(cpu.statusRegister.zeroFlag).isFalse
            it.assertThat(cpu.statusRegister.negativeFlag).isTrue
        }
    }

    @Test
    fun `test LDA Indirect,Y (0xb1) no flags set with wrap`() {
        val instruction = instructionSet.instructions[0xb1]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("LDA")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("IZY")
            it.assertThat(instruction.cycles).isEqualTo(5)
        }

        val memory = Array<UByte>(65536) { 0u }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0xb1u
        memory[0x8001] = 0xffu
        memory[0x00ff] = 0x00u
        memory[0x0000] = 0x02u
        memory[0x02ff] = 0x42u

        val cpu = createCpu(DefaultMemory(memory))
        cpu.y = 0xffu

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(cpu.a).isEqualTo(0x42.toUByte())
            it.assertThat(cpu.statusRegister.zeroFlag).isFalse
            it.assertThat(cpu.statusRegister.negativeFlag).isFalse
        }
    }

    @Test
    fun `test LDA Indirect,Y (0xb1) zero flag set with wrap`() {
        val instruction = instructionSet.instructions[0xb1]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("LDA")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("IZY")
            it.assertThat(instruction.cycles).isEqualTo(5)
        }

        val memory = Array<UByte>(65536) { 0x7fu }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0xb1u
        memory[0x8001] = 0xffu
        memory[0x00ff] = 0x00u
        memory[0x0000] = 0x02u
        memory[0x02ff] = 0x00u

        val cpu = createCpu(DefaultMemory(memory))
        cpu.y = 0xffu

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(cpu.a).isEqualTo(0x00.toUByte())
            it.assertThat(cpu.statusRegister.zeroFlag).isTrue
            it.assertThat(cpu.statusRegister.negativeFlag).isFalse
        }
    }

    @Test
    fun `test LDA Indirect,Y (0xb1) negative flag set with wrap`() {
        val instruction = instructionSet.instructions[0xb1]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("LDA")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("IZY")
            it.assertThat(instruction.cycles).isEqualTo(5)
        }

        val memory = Array<UByte>(65536) { 0u }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0xb1u
        memory[0x8001] = 0xffu
        memory[0x00ff] = 0x00u
        memory[0x0000] = 0x02u
        memory[0x02ff] = 0x8fu

        val cpu = createCpu(DefaultMemory(memory))
        cpu.y = 0xffu

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(cpu.a).isEqualTo(0x8f.toUByte())
            it.assertThat(cpu.statusRegister.zeroFlag).isFalse
            it.assertThat(cpu.statusRegister.negativeFlag).isTrue
        }
    }

    @Test
    fun `test LDA Indirect,Y (0xb1) no flags set with cross page`() {
        val instruction = instructionSet.instructions[0xb1]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("LDA")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("IZY")
            it.assertThat(instruction.cycles).isEqualTo(5)
        }

        val memory = Array<UByte>(65536) { 0u }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0xb1u
        memory[0x8001] = 0x10u
        memory[0x0010] = 0xffu
        memory[0x0011] = 0x02u
        memory[0x0300] = 0x42u

        val cpu = createCpu(DefaultMemory(memory))
        cpu.y = 0x01u

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles + 1)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(cpu.a).isEqualTo(0x42.toUByte())
            it.assertThat(cpu.statusRegister.zeroFlag).isFalse
            it.assertThat(cpu.statusRegister.negativeFlag).isFalse
        }
    }

    @Test
    fun `test LDA Indirect,Y (0xb1) zero flag set with cross page`() {
        val instruction = instructionSet.instructions[0xb1]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("LDA")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("IZY")
            it.assertThat(instruction.cycles).isEqualTo(5)
        }

        val memory = Array<UByte>(65536) { 0x7fu }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0xb1u
        memory[0x8001] = 0x10u
        memory[0x0010] = 0xffu
        memory[0x0011] = 0x02u
        memory[0x0300] = 0x00u

        val cpu = createCpu(DefaultMemory(memory))
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
    fun `test LDA Indirect,Y (0xb1) negative flag set with cross page`() {
        val instruction = instructionSet.instructions[0xb1]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("LDA")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("IZY")
            it.assertThat(instruction.cycles).isEqualTo(5)
        }

        val memory = Array<UByte>(65536) { 0u }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0xb1u
        memory[0x8001] = 0x10u
        memory[0x0010] = 0xffu
        memory[0x0011] = 0x02u
        memory[0x0300] = 0x8fu

        val cpu = createCpu(DefaultMemory(memory))
        cpu.y = 0x01u

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles + 1)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(cpu.a).isEqualTo(0x8f.toUByte())
            it.assertThat(cpu.statusRegister.zeroFlag).isFalse
            it.assertThat(cpu.statusRegister.negativeFlag).isTrue
        }
    }
}