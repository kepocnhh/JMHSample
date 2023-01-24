package test.jmh.sample

import org.openjdk.jmh.annotations.Benchmark
import org.openjdk.jmh.annotations.BenchmarkMode
import org.openjdk.jmh.annotations.Fork
import org.openjdk.jmh.annotations.Mode

open class FooBenchmark {
    @Benchmark
    @Fork(value = 1, warmups = 2)
    @BenchmarkMode(Mode.SingleShotTime)
    fun doWorkBenchmark() {
        Foo.doWork()
    }
}
