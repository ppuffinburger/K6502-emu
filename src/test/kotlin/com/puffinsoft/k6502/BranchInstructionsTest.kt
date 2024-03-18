package com.puffinsoft.k6502

import com.puffinsoft.k6502.inbuilt.DefaultMemory
import org.assertj.core.api.Assertions
import org.assertj.core.api.SoftAssertions
import org.junit.jupiter.api.Test

class BranchInstructionsTest : BaseInstructionsTest() {
    @Test
    fun `test BCC (0x90) no branch`() {
        val instruction = instructionSet.instructions[0x90]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("BCC")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("REL")
            it.assertThat(instruction.cycles).isEqualTo(2)
        }

        val memory = Array<UByte>(65536) { 0x7fu }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0x90u
        memory[0x8001] = 0x7fu

        val cpu = createCpu(DefaultMemory(memory))
        cpu.statusRegister.carryFlag = true

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(cpu.programCounter).isEqualTo(0x8002.toShort())
        }
    }

    @Test
    fun `test BCC (0x90) branch forward`() {
        val instruction = instructionSet.instructions[0x90]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("BCC")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("REL")
            it.assertThat(instruction.cycles).isEqualTo(2)
        }

        val memory = Array<UByte>(65536) { 0x7fu }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0x90u
        memory[0x8001] = 0x7eu

        val cpu = createCpu(DefaultMemory(memory))
        cpu.statusRegister.carryFlag = false

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles + 1)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(cpu.programCounter).isEqualTo(0x8080.toShort())
        }
    }

    @Test
    fun `test BCC (0x90) branch backward`() {
        val instruction = instructionSet.instructions[0x90]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("BCC")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("REL")
            it.assertThat(instruction.cycles).isEqualTo(2)
        }

        val memory = Array<UByte>(65536) { 0x7fu }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0x90u
        memory[0x8001] = 0xffu

        val cpu = createCpu(DefaultMemory(memory))
        cpu.statusRegister.carryFlag = false

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles + 1)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(cpu.programCounter).isEqualTo(0x8001.toShort())
        }
    }

    @Test
    fun `test BCC (0x90) branch forward with cross page`() {
        val instruction = instructionSet.instructions[0x90]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("BCC")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("REL")
            it.assertThat(instruction.cycles).isEqualTo(2)
        }

        val memory = Array<UByte>(65536) { 0x7fu }
        memory[0xfffc] = 0xfdu
        memory[0xfffd] = 0x80u
        memory[0x80fd] = 0x90u
        memory[0x80fe] = 0x01u

        val cpu = createCpu(DefaultMemory(memory))
        cpu.statusRegister.carryFlag = false

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles + 2)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(cpu.programCounter).isEqualTo(0x8100.toShort())
        }
    }

    @Test
    fun `test BCC (0x90) branch backward with cross page`() {
        val instruction = instructionSet.instructions[0x90]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("BCC")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("REL")
            it.assertThat(instruction.cycles).isEqualTo(2)
        }

        val memory = Array<UByte>(65536) { 0x7fu }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0x90u
        memory[0x8001] = 0xfdu

        val cpu = createCpu(DefaultMemory(memory))
        cpu.statusRegister.carryFlag = false

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles + 2)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(cpu.programCounter).isEqualTo(0x7fff.toShort())
        }
    }

    @Test
    fun `test BCS (0xb0) no branch`() {
        val instruction = instructionSet.instructions[0xb0]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("BCS")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("REL")
            it.assertThat(instruction.cycles).isEqualTo(2)
        }

        val memory = Array<UByte>(65536) { 0x7fu }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0xb0u
        memory[0x8001] = 0x7fu

        val cpu = createCpu(DefaultMemory(memory))
        cpu.statusRegister.carryFlag = false

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(cpu.programCounter).isEqualTo(0x8002.toShort())
        }
    }

    @Test
    fun `test BCS (0xb0) branch forward`() {
        val instruction = instructionSet.instructions[0xb0]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("BCS")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("REL")
            it.assertThat(instruction.cycles).isEqualTo(2)
        }

        val memory = Array<UByte>(65536) { 0x7fu }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0xb0u
        memory[0x8001] = 0x7eu

        val cpu = createCpu(DefaultMemory(memory))
        cpu.statusRegister.carryFlag = true

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles + 1)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(cpu.programCounter).isEqualTo(0x8080.toShort())
        }
    }

    @Test
    fun `test BCS (0xb0) branch backward`() {
        val instruction = instructionSet.instructions[0xb0]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("BCS")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("REL")
            it.assertThat(instruction.cycles).isEqualTo(2)
        }

        val memory = Array<UByte>(65536) { 0x7fu }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0xb0u
        memory[0x8001] = 0xffu

        val cpu = createCpu(DefaultMemory(memory))
        cpu.statusRegister.carryFlag = true

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles + 1)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(cpu.programCounter).isEqualTo(0x8001.toShort())
        }
    }

    @Test
    fun `test BCS (0xb0) branch forward with cross page`() {
        val instruction = instructionSet.instructions[0xb0]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("BCS")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("REL")
            it.assertThat(instruction.cycles).isEqualTo(2)
        }

        val memory = Array<UByte>(65536) { 0x7fu }
        memory[0xfffc] = 0xfdu
        memory[0xfffd] = 0x80u
        memory[0x80fd] = 0xb0u
        memory[0x80fe] = 0x01u

        val cpu = createCpu(DefaultMemory(memory))
        cpu.statusRegister.carryFlag = true

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles + 2)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(cpu.programCounter).isEqualTo(0x8100.toShort())
        }
    }

    @Test
    fun `test BCS (0xb0) branch backward with cross page`() {
        val instruction = instructionSet.instructions[0xb0]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("BCS")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("REL")
            it.assertThat(instruction.cycles).isEqualTo(2)
        }

        val memory = Array<UByte>(65536) { 0x7fu }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0xb0u
        memory[0x8001] = 0xfdu

        val cpu = createCpu(DefaultMemory(memory))
        cpu.statusRegister.carryFlag = true

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles + 2)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(cpu.programCounter).isEqualTo(0x7fff.toShort())
        }
    }

    @Test
    fun `test BEQ (0xf0) no branch`() {
        val instruction = instructionSet.instructions[0xf0]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("BEQ")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("REL")
            it.assertThat(instruction.cycles).isEqualTo(2)
        }

        val memory = Array<UByte>(65536) { 0x7fu }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0xf0u
        memory[0x8001] = 0x7fu

        val cpu = createCpu(DefaultMemory(memory))
        cpu.statusRegister.zeroFlag = false

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(cpu.programCounter).isEqualTo(0x8002.toShort())
        }
    }

    @Test
    fun `test BEQ (0xf0) branch forward`() {
        val instruction = instructionSet.instructions[0xf0]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("BEQ")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("REL")
            it.assertThat(instruction.cycles).isEqualTo(2)
        }

        val memory = Array<UByte>(65536) { 0x7fu }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0xf0u
        memory[0x8001] = 0x7eu

        val cpu = createCpu(DefaultMemory(memory))
        cpu.statusRegister.zeroFlag = true

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles + 1)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(cpu.programCounter).isEqualTo(0x8080.toShort())
        }
    }

    @Test
    fun `test BEQ (0xf0) branch backward`() {
        val instruction = instructionSet.instructions[0xf0]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("BEQ")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("REL")
            it.assertThat(instruction.cycles).isEqualTo(2)
        }

        val memory = Array<UByte>(65536) { 0x7fu }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0xf0u
        memory[0x8001] = 0xffu

        val cpu = createCpu(DefaultMemory(memory))
        cpu.statusRegister.zeroFlag = true

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles + 1)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(cpu.programCounter).isEqualTo(0x8001.toShort())
        }
    }

    @Test
    fun `test BEQ (0xf0) branch forward with cross page`() {
        val instruction = instructionSet.instructions[0xf0]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("BEQ")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("REL")
            it.assertThat(instruction.cycles).isEqualTo(2)
        }

        val memory = Array<UByte>(65536) { 0x7fu }
        memory[0xfffc] = 0xfdu
        memory[0xfffd] = 0x80u
        memory[0x80fd] = 0xf0u
        memory[0x80fe] = 0x01u

        val cpu = createCpu(DefaultMemory(memory))
        cpu.statusRegister.zeroFlag = true

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles + 2)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(cpu.programCounter).isEqualTo(0x8100.toShort())
        }
    }

    @Test
    fun `test BEQ (0xf0) branch backward with cross page`() {
        val instruction = instructionSet.instructions[0xf0]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("BEQ")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("REL")
            it.assertThat(instruction.cycles).isEqualTo(2)
        }

        val memory = Array<UByte>(65536) { 0x7fu }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0xf0u
        memory[0x8001] = 0xfdu

        val cpu = createCpu(DefaultMemory(memory))
        cpu.statusRegister.zeroFlag = true

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles + 2)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(cpu.programCounter).isEqualTo(0x7fff.toShort())
        }
    }

    @Test
    fun `test BMI (0x30) no branch`() {
        val instruction = instructionSet.instructions[0x30]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("BMI")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("REL")
            it.assertThat(instruction.cycles).isEqualTo(2)
        }

        val memory = Array<UByte>(65536) { 0x7fu }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0x30u
        memory[0x8001] = 0x7fu

        val cpu = createCpu(DefaultMemory(memory))
        cpu.statusRegister.negativeFlag = false

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(cpu.programCounter).isEqualTo(0x8002.toShort())
        }
    }

    @Test
    fun `test BMI (0x30) branch forward`() {
        val instruction = instructionSet.instructions[0x30]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("BMI")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("REL")
            it.assertThat(instruction.cycles).isEqualTo(2)
        }

        val memory = Array<UByte>(65536) { 0x7fu }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0x30u
        memory[0x8001] = 0x7eu

        val cpu = createCpu(DefaultMemory(memory))
        cpu.statusRegister.negativeFlag = true

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles + 1)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(cpu.programCounter).isEqualTo(0x8080.toShort())
        }
    }

    @Test
    fun `test BMI (0x30) branch backward`() {
        val instruction = instructionSet.instructions[0x30]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("BMI")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("REL")
            it.assertThat(instruction.cycles).isEqualTo(2)
        }

        val memory = Array<UByte>(65536) { 0x7fu }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0x30u
        memory[0x8001] = 0xffu

        val cpu = createCpu(DefaultMemory(memory))
        cpu.statusRegister.negativeFlag = true

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles + 1)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(cpu.programCounter).isEqualTo(0x8001.toShort())
        }
    }

    @Test
    fun `test BMI (0x30) branch forward with cross page`() {
        val instruction = instructionSet.instructions[0x30]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("BMI")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("REL")
            it.assertThat(instruction.cycles).isEqualTo(2)
        }

        val memory = Array<UByte>(65536) { 0x7fu }
        memory[0xfffc] = 0xfdu
        memory[0xfffd] = 0x80u
        memory[0x80fd] = 0x30u
        memory[0x80fe] = 0x01u

        val cpu = createCpu(DefaultMemory(memory))
        cpu.statusRegister.negativeFlag = true

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles + 2)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(cpu.programCounter).isEqualTo(0x8100.toShort())
        }
    }

    @Test
    fun `test BMI (0x30) branch backward with cross page`() {
        val instruction = instructionSet.instructions[0x30]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("BMI")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("REL")
            it.assertThat(instruction.cycles).isEqualTo(2)
        }

        val memory = Array<UByte>(65536) { 0x7fu }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0x30u
        memory[0x8001] = 0xfdu

        val cpu = createCpu(DefaultMemory(memory))
        cpu.statusRegister.negativeFlag = true

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles + 2)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(cpu.programCounter).isEqualTo(0x7fff.toShort())
        }
    }

    @Test
    fun `test BNE (0xd0) no branch`() {
        val instruction = instructionSet.instructions[0xd0]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("BNE")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("REL")
            it.assertThat(instruction.cycles).isEqualTo(2)
        }

        val memory = Array<UByte>(65536) { 0x7fu }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0xd0u
        memory[0x8001] = 0x7fu

        val cpu = createCpu(DefaultMemory(memory))
        cpu.statusRegister.zeroFlag = true

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(cpu.programCounter).isEqualTo(0x8002.toShort())
        }
    }

    @Test
    fun `test BNE (0xd0) branch forward`() {
        val instruction = instructionSet.instructions[0xd0]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("BNE")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("REL")
            it.assertThat(instruction.cycles).isEqualTo(2)
        }

        val memory = Array<UByte>(65536) { 0x7fu }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0xd0u
        memory[0x8001] = 0x7eu

        val cpu = createCpu(DefaultMemory(memory))
        cpu.statusRegister.zeroFlag = false

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles + 1)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(cpu.programCounter).isEqualTo(0x8080.toShort())
        }
    }

    @Test
    fun `test BNE (0xd0) branch backward`() {
        val instruction = instructionSet.instructions[0xd0]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("BNE")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("REL")
            it.assertThat(instruction.cycles).isEqualTo(2)
        }

        val memory = Array<UByte>(65536) { 0x7fu }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0xd0u
        memory[0x8001] = 0xffu

        val cpu = createCpu(DefaultMemory(memory))
        cpu.statusRegister.zeroFlag = false

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles + 1)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(cpu.programCounter).isEqualTo(0x8001.toShort())
        }
    }

    @Test
    fun `test BNE (0xd0) branch forward with cross page`() {
        val instruction = instructionSet.instructions[0xd0]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("BNE")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("REL")
            it.assertThat(instruction.cycles).isEqualTo(2)
        }

        val memory = Array<UByte>(65536) { 0x7fu }
        memory[0xfffc] = 0xfdu
        memory[0xfffd] = 0x80u
        memory[0x80fd] = 0xd0u
        memory[0x80fe] = 0x01u

        val cpu = createCpu(DefaultMemory(memory))
        cpu.statusRegister.zeroFlag = false

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles + 2)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(cpu.programCounter).isEqualTo(0x8100.toShort())
        }
    }

    @Test
    fun `test BNE (0xd0) branch backward with cross page`() {
        val instruction = instructionSet.instructions[0xd0]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("BNE")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("REL")
            it.assertThat(instruction.cycles).isEqualTo(2)
        }

        val memory = Array<UByte>(65536) { 0x7fu }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0xd0u
        memory[0x8001] = 0xfdu

        val cpu = createCpu(DefaultMemory(memory))
        cpu.statusRegister.zeroFlag = false

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles + 2)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(cpu.programCounter).isEqualTo(0x7fff.toShort())
        }
    }

    @Test
    fun `test BPL (0x10) no branch`() {
        val instruction = instructionSet.instructions[0x10]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("BPL")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("REL")
            it.assertThat(instruction.cycles).isEqualTo(2)
        }

        val memory = Array<UByte>(65536) { 0x7fu }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0x10u
        memory[0x8001] = 0x7fu

        val cpu = createCpu(DefaultMemory(memory))
        cpu.statusRegister.negativeFlag = true

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(cpu.programCounter).isEqualTo(0x8002.toShort())
        }
    }

    @Test
    fun `test BPL (0x10) branch forward`() {
        val instruction = instructionSet.instructions[0x10]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("BPL")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("REL")
            it.assertThat(instruction.cycles).isEqualTo(2)
        }

        val memory = Array<UByte>(65536) { 0x7fu }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0x10u
        memory[0x8001] = 0x7eu

        val cpu = createCpu(DefaultMemory(memory))
        cpu.statusRegister.negativeFlag = false

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles + 1)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(cpu.programCounter).isEqualTo(0x8080.toShort())
        }
    }

    @Test
    fun `test BPL (0x10) branch backward`() {
        val instruction = instructionSet.instructions[0x10]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("BPL")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("REL")
            it.assertThat(instruction.cycles).isEqualTo(2)
        }

        val memory = Array<UByte>(65536) { 0x7fu }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0x10u
        memory[0x8001] = 0xffu

        val cpu = createCpu(DefaultMemory(memory))
        cpu.statusRegister.negativeFlag = false

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles + 1)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(cpu.programCounter).isEqualTo(0x8001.toShort())
        }
    }

    @Test
    fun `test BPL (0x10) branch forward with cross page`() {
        val instruction = instructionSet.instructions[0x10]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("BPL")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("REL")
            it.assertThat(instruction.cycles).isEqualTo(2)
        }

        val memory = Array<UByte>(65536) { 0x7fu }
        memory[0xfffc] = 0xfdu
        memory[0xfffd] = 0x80u
        memory[0x80fd] = 0x10u
        memory[0x80fe] = 0x01u

        val cpu = createCpu(DefaultMemory(memory))
        cpu.statusRegister.negativeFlag = false

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles + 2)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(cpu.programCounter).isEqualTo(0x8100.toShort())
        }
    }

    @Test
    fun `test BPL (0x10) branch backward with cross page`() {
        val instruction = instructionSet.instructions[0x10]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("BPL")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("REL")
            it.assertThat(instruction.cycles).isEqualTo(2)
        }

        val memory = Array<UByte>(65536) { 0x7fu }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0x10u
        memory[0x8001] = 0xfdu

        val cpu = createCpu(DefaultMemory(memory))
        cpu.statusRegister.negativeFlag = false

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles + 2)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(cpu.programCounter).isEqualTo(0x7fff.toShort())
        }
    }

    @Test
    fun `test BVC (0x50) no branch`() {
        val instruction = instructionSet.instructions[0x50]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("BVC")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("REL")
            it.assertThat(instruction.cycles).isEqualTo(2)
        }

        val memory = Array<UByte>(65536) { 0x7fu }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0x50u
        memory[0x8001] = 0x7fu

        val cpu = createCpu(DefaultMemory(memory))
        cpu.statusRegister.overflowFlag = true

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(cpu.programCounter).isEqualTo(0x8002.toShort())
        }
    }

    @Test
    fun `test BVC (0x50) branch forward`() {
        val instruction = instructionSet.instructions[0x50]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("BVC")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("REL")
            it.assertThat(instruction.cycles).isEqualTo(2)
        }

        val memory = Array<UByte>(65536) { 0x7fu }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0x50u
        memory[0x8001] = 0x7eu

        val cpu = createCpu(DefaultMemory(memory))
        cpu.statusRegister.overflowFlag = false

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles + 1)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(cpu.programCounter).isEqualTo(0x8080.toShort())
        }
    }

    @Test
    fun `test BVC (0x50) branch backward`() {
        val instruction = instructionSet.instructions[0x50]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("BVC")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("REL")
            it.assertThat(instruction.cycles).isEqualTo(2)
        }

        val memory = Array<UByte>(65536) { 0x7fu }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0x50u
        memory[0x8001] = 0xffu

        val cpu = createCpu(DefaultMemory(memory))
        cpu.statusRegister.overflowFlag = false

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles + 1)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(cpu.programCounter).isEqualTo(0x8001.toShort())
        }
    }

    @Test
    fun `test BVC (0x50) branch forward with cross page`() {
        val instruction = instructionSet.instructions[0x50]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("BVC")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("REL")
            it.assertThat(instruction.cycles).isEqualTo(2)
        }

        val memory = Array<UByte>(65536) { 0x7fu }
        memory[0xfffc] = 0xfdu
        memory[0xfffd] = 0x80u
        memory[0x80fd] = 0x50u
        memory[0x80fe] = 0x01u

        val cpu = createCpu(DefaultMemory(memory))
        cpu.statusRegister.overflowFlag = false

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles + 2)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(cpu.programCounter).isEqualTo(0x8100.toShort())
        }
    }

    @Test
    fun `test BVC (0x50) branch backward with cross page`() {
        val instruction = instructionSet.instructions[0x50]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("BVC")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("REL")
            it.assertThat(instruction.cycles).isEqualTo(2)
        }

        val memory = Array<UByte>(65536) { 0x7fu }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0x50u
        memory[0x8001] = 0xfdu

        val cpu = createCpu(DefaultMemory(memory))
        cpu.statusRegister.overflowFlag = false

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles + 2)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(cpu.programCounter).isEqualTo(0x7fff.toShort())
        }
    }

    @Test
    fun `test BVS (0x70) no branch`() {
        val instruction = instructionSet.instructions[0x70]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("BVS")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("REL")
            it.assertThat(instruction.cycles).isEqualTo(2)
        }

        val memory = Array<UByte>(65536) { 0x7fu }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0x70u
        memory[0x8001] = 0x7fu

        val cpu = createCpu(DefaultMemory(memory))
        cpu.statusRegister.overflowFlag = false

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(cpu.programCounter).isEqualTo(0x8002.toShort())
        }
    }

    @Test
    fun `test BVS (0x70) branch forward`() {
        val instruction = instructionSet.instructions[0x70]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("BVS")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("REL")
            it.assertThat(instruction.cycles).isEqualTo(2)
        }

        val memory = Array<UByte>(65536) { 0x7fu }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0x70u
        memory[0x8001] = 0x7eu

        val cpu = createCpu(DefaultMemory(memory))
        cpu.statusRegister.overflowFlag = true

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles + 1)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(cpu.programCounter).isEqualTo(0x8080.toShort())
        }
    }

    @Test
    fun `test BVS (0x70) branch backward`() {
        val instruction = instructionSet.instructions[0x70]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("BVS")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("REL")
            it.assertThat(instruction.cycles).isEqualTo(2)
        }

        val memory = Array<UByte>(65536) { 0x7fu }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0x70u
        memory[0x8001] = 0xffu

        val cpu = createCpu(DefaultMemory(memory))
        cpu.statusRegister.overflowFlag = true

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles + 1)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(cpu.programCounter).isEqualTo(0x8001.toShort())
        }
    }

    @Test
    fun `test BVS (0x70) branch forward with cross page`() {
        val instruction = instructionSet.instructions[0x70]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("BVS")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("REL")
            it.assertThat(instruction.cycles).isEqualTo(2)
        }

        val memory = Array<UByte>(65536) { 0x7fu }
        memory[0xfffc] = 0xfdu
        memory[0xfffd] = 0x80u
        memory[0x80fd] = 0x70u
        memory[0x80fe] = 0x01u

        val cpu = createCpu(DefaultMemory(memory))
        cpu.statusRegister.overflowFlag = true

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles + 2)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(cpu.programCounter).isEqualTo(0x8100.toShort())
        }
    }

    @Test
    fun `test BVS (0x70) branch backward with cross page`() {
        val instruction = instructionSet.instructions[0x70]

        SoftAssertions.assertSoftly {
            it.assertThat(instruction.opcode).isEqualTo("BVS")
            it.assertThat(instruction.addressMode.opcode).isEqualTo("REL")
            it.assertThat(instruction.cycles).isEqualTo(2)
        }

        val memory = Array<UByte>(65536) { 0x7fu }
        memory[0xfffc] = 0x00u
        memory[0xfffd] = 0x80u
        memory[0x8000] = 0x70u
        memory[0x8001] = 0xfdu

        val cpu = createCpu(DefaultMemory(memory))
        cpu.statusRegister.overflowFlag = true

        Assertions.assertThat(executeStepCount(cpu, instruction.cycles + 2)).isTrue

        SoftAssertions.assertSoftly {
            it.assertThat(cpu.cycles).isEqualTo(0)
            it.assertThat(cpu.programCounter).isEqualTo(0x7fff.toShort())
        }
    }

}