package test.jmh.sample

import org.openjdk.jmh.annotations.Benchmark
import org.openjdk.jmh.annotations.BenchmarkMode
import org.openjdk.jmh.annotations.Fork
import org.openjdk.jmh.annotations.Level
import org.openjdk.jmh.annotations.Mode
import org.openjdk.jmh.annotations.Param
import org.openjdk.jmh.annotations.Scope
import org.openjdk.jmh.annotations.Setup
import org.openjdk.jmh.annotations.State
import java.util.concurrent.atomic.AtomicInteger

@State(Scope.Benchmark)
open class BarBenchmark {
    private var paramBenchmark = AtomicInteger(0)
    private var paramIteration = AtomicInteger(0)
    private var paramInvocation = AtomicInteger(0)

//    @Param(value = ["4.0", "16.0", "256.0"])
    @Param(value = ["4.0"])
    var value: Double = 0.0

    @Setup(Level.Trial)
    fun runEveryBenchmark() {
        paramBenchmark.incrementAndGet()
        println("\n\n\tbenchmark: ${paramBenchmark.get()}\n\n")
    }

    @Setup(Level.Iteration)
    fun runEveryIteration() {
        paramIteration.incrementAndGet()
        println("\n\n\titeration: ${paramIteration.get()}\n\n")
    }

    @Setup(Level.Invocation)
    fun runEveryInvocation() {
        paramInvocation.incrementAndGet()
        println("invocation: " + paramInvocation.get())
    }

    @Benchmark
    @BenchmarkMode(Mode.Throughput)
    @Fork(value = 1, warmups = 2)
    fun mySqrtBenchmark() {
        Bar.mySqrt(value)
    }
}
