package ru.nsu.fit.oop.veber;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

public class BenchmarkRunner {

    public static void main(String[] args) throws Exception {
        Options opt = new OptionsBuilder()
                .include(TestPrimeNumberFinders.class.getSimpleName())
                .build();

        new Runner(opt).run();
    }

    @State(Scope.Benchmark)
    public static class executionPlan {
        @Param({"4 6997927 6997937 6997967 6998009 6998029 6998039 6998051 6998053",
                "6997927 6997937 6997967 6998009 4 6998029 6998039 6998051 6998053",
                "6997927 6997937 6997967 6998009 6998029 6998039 6998051 6998053 4",
                "7 6997927 6997937 6997967 6998009 6998029 6998039 6998051 6998053",
                "7"})
        public String arr;
    }

    public static class TestPrimeNumberFinders {
        @Benchmark
        @BenchmarkMode(Mode.AverageTime)
        @OutputTimeUnit(TimeUnit.MILLISECONDS)
        @Fork(value = 1, warmups = 1)
        @Warmup(batchSize = 1)
        public void sequential(Blackhole blackhole, executionPlan plan) {
            Integer[] arr = parseArray(plan);
            PrimeNumberFinder finder = new PrimeNumberFinderImpl(arr);
            blackhole.consume(finder.haveNotPrime());

        }

        @Benchmark
        @BenchmarkMode(Mode.AverageTime)
        @OutputTimeUnit(TimeUnit.MILLISECONDS)
        @Fork(value = 1, warmups = 1)
        @Warmup(batchSize = 1)
        public void threads(Blackhole blackhole, executionPlan plan) {
            Integer[] arr = parseArray(plan);
            PrimeNumberFinder finder = new ThreadPrimeNumberFinder(arr);
            blackhole.consume(finder.haveNotPrime());

        }

        @Benchmark
        @BenchmarkMode(Mode.AverageTime)
        @OutputTimeUnit(TimeUnit.MILLISECONDS)
        @Fork(value = 1, warmups = 1)
        @Warmup(batchSize = 1)
        public void parallel(Blackhole blackhole, executionPlan plan) {
            Integer[] arr = parseArray(plan);
            PrimeNumberFinder finder = new ParallelStreamPrimeNumberFinder(arr);
            blackhole.consume(finder.haveNotPrime());

        }

        private Integer[] parseArray(executionPlan plan) {
            return Arrays.stream(plan.arr.split(" "))
                    .map(Integer::parseInt)
                    .toArray(Integer[]::new);


        }
    }

}

