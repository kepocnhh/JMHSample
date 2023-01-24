package test.jmh.sample

import org.openjdk.jmh.annotations.Benchmark

open class FooBenchmark {
    @Benchmark
    fun doWorkBenchmark() {
        Foo.doWork()
    }
}
