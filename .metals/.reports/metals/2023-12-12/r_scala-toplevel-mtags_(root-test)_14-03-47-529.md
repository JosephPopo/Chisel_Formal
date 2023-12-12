error id: file://<WORKSPACE>/src/test/scala/gcd/Formal_UpDownCounter.scala:[254..255) in Input.VirtualFile("file://<WORKSPACE>/src/test/scala/gcd/Formal_UpDownCounter.scala", "package Counters

import chisel3._
import chiseltest._
import chiseltest.formal._
import chiseltest.experimental._
import org.scalatest.flatspec.AnyFlatSpec

class Formal_UpDownCounter extends AnyFlatSpec with ChiselScalatestTester with Formal {
    def (
        "UpDownCounter" should "pass" in {
            verify(new UpDownCounter(64.U), Seq(BoundedCheck(100)))
    }
    )
    
}

class UpDownCounter_Test extends AnyFlatSpec with ChiselScalatestTester {
    "UpDownCounter" should "pass" in {
        test(new UpDownCounter(64.U)) { dut =>
            dut.io.upOrDown.poke(1.U)
            dut.io.reset.poke(0.U)
            dut.clock.step(50)
            dut.io.upOrDown.poke(0.U)
            dut.clock.step(50)
        }
    }
}")
file://<WORKSPACE>/src/test/scala/gcd/Formal_UpDownCounter.scala
file://<WORKSPACE>/src/test/scala/gcd/Formal_UpDownCounter.scala:10: error: expected identifier; obtained lparen
    def (
        ^
#### Short summary: 

expected identifier; obtained lparen