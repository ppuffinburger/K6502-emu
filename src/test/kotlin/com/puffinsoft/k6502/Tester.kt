@file:OptIn(ExperimentalUnsignedTypes::class)

package com.puffinsoft.k6502

import com.puffinsoft.k6502.inbuilt.DefaultMachine
import kotlinx.coroutines.*
import java.io.File

fun main() {
    Tester().run()
}

class Tester {
    fun run() {
        val program = File("/Users/philip/Development/Kotlin/K6502e/src/test/resources/6502_functional_test.bin").readBytes().toUByteArray()
        val machine = DefaultMachine()
        machine.load(program.toTypedArray(), 0)
        machine.cpu.programCounter = 0x0400.toShort()

        runBlocking {
            launch(Dispatchers.Default) {
                machine.execute()
            }
            launch(Dispatchers.Default) {
                while (true) {
                    delay(1000)
                    if (machine.cpu.programCounter == 0x3469.toShort()) {
                        println("Reached success jump position... Stopping execution")
                        machine.stopExecution = true
                        break
                    }
                }
            }
        }
    }
}