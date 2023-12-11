package Counters

import chisel3._
import chisel3.stage.ChiselOptions._
import chisel3.util._

class UpDownCounter(val max: UInt) extends Module {
    val io = IO(new Bundle {

        val upOrDown = Input(Bool())
        val reset    = Input(Bool())
        val out   = Output(UInt(max.getWidth.W))
    })
    assume(io.upOrDown === 1.U)
    val count = RegInit(0.U(max.getWidth.W))
    
    when(io.reset) {
        count := 0.U 
    } .elsewhen(io.upOrDown) { 
        count := Mux(count === max - 1.U, 0.U, count + 1.U)
    } .otherwise {
        count := Mux(count === 0.U, max - 1.U, count - 1.U)
    }

    io.out := count
    assert(count < max)
    assert(io.out < max)
    assert(io.out =/= (max + 1.U))
}

object UpDownCounter extends App {
  println("Generating the hardware")
  emitVerilog(new UpDownCounter(16.U), Array("--target-dir", "generated"))
}