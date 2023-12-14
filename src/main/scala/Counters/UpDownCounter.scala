package Counters

import chisel3._
import chisel3.util._
import chisel3.stage.ChiselGeneratorAnnotation
//import chiseltest.simulator.Firrtl2AnnotationWrapper
import chiseltest.formal._

class UpDownCounter(val max: UInt) extends Module {
    val io = IO(new Bundle {
        val upOrDown = Input(Bool())
        val reset    = Input(Bool())
        val out   = Output(UInt(max.getWidth.W))
    })
    
    val count = RegInit(0.U(max.getWidth.W))
    
    when(io.reset) {
        count := 0.U 
    } .elsewhen(io.upOrDown) { 
        count := Mux(count === max - 1.U, 0.U, count + 1.U)
    } .otherwise {
        count := Mux(count === 0.U, max - 1.U, count - 1.U)
    }
    
    

    //assume(io.upOrDown === 1.U)
    assert(count < max)
    assert(io.out < max)
    
    //assert output/count never excedes max + 1
    assert(io.out =/= (max + 1.U))
    assert(count >= 0.U)
    assert(io.out >= 0.U)
//linear regression

//mother testbench
//vpi-c

    // use an assertion to makesure the counter is not skipping any values
    // Formal Verification Using past asserts
    when(past(io.reset)) {
        // If reset was asserted in the previous cycle, the counter should be 0 now
        assert(count === 0.U)
    } .elsewhen (past(io.upOrDown)) {
        // If counting up in the previous cycle, the counter should have incremented by 1 or wrapped to 0
        assert(count === Mux(past(count) === max - 1.U, 0.U, past(count) + 1.U))
    } .elsewhen (!past(io.upOrDown)) {
        // If counting down in the previous cycle, the counter should have decremented by 1 or wrapped to max - 1
        assert(count === Mux(past(count) === 0.U, max - 1.U, past(count) - 1.U))
    }

    printf("%b\n", io.out)
    io.out := count
}



object UpDownCounter extends App {
  println("Generating the hardware")
  emitVerilog(new UpDownCounter(32.U), Array("--target-dir", "generated"))
}