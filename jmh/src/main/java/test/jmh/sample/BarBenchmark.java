package test.jmh.sample;

import org.openjdk.jmh.annotations.*;
import java.util.concurrent.TimeUnit;

/**
 *
 * <pre>
 * @Fork(value = A, warmups = B)
 * @Measurement(iterations = C)
 * @Warmup(iterations = D)
 * </pre>
 *
 * <pre>
 * + warmup #1
 *  \
 *   + warmup iteration #1
 *   + ...
 *   + warmup iteration #D
 *    \
 *     + (pre fork) iteration #1
 *     + ...
 *     + (pre fork) iteration #C
 *    /
 *  /
 * + warmup #2
 * + ...
 * + warmup #B
 * |
 * - fork #1
 *  \
 *   + warmup iteration #1
 *   + ...
 *   + warmup iteration #D
 *    \
 *     + iteration #1
 *     + ...
 *     + iteration #C
 *    /
 *  /
 * + fork #2
 * + ...
 * + fork #A
 * </pre>
 *
 */
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@Fork(value = 1, warmups = 0)
@Measurement(iterations = 1, time = 1)
@Warmup(iterations = 0)
public class BarBenchmark {
    @Benchmark
    public void mySqrtBenchmark() {
        Bar.INSTANCE.mySqrt(16.0);
    }
}
