package ru.nsu.fit.oop.veber;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;
import org.openjdk.jmh.results.format.ResultFormatType;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;


public class BenchmarkRunner {

    public static void main(String[] args) throws Exception {
        Path path = Paths.get("target/jmh-reports/");
        Files.createDirectories(path);
        Path filepath = Paths.get("target/jmh-reports/result.json");
        Files.createFile(filepath);
        Options opt = new OptionsBuilder()
                .include(TestPrimeNumberFinders.class.getSimpleName())
                .forks(1)
                .warmupForks(1)
                .warmupIterations(5)
                .warmupBatchSize(1)
                .result("target/jmh-reports/result.json")
                .resultFormat(ResultFormatType.JSON)
                .mode(Mode.AverageTime)
                .timeUnit(TimeUnit.MILLISECONDS)
                .build();

        new Runner(opt).run();
    }

    @State(Scope.Benchmark)
    public static class executionPlan {
        @Param({"1", "2", "4", "6"})
        private int threadCount;

        @Param({"5", "10", "20", "100", "500", "1000", "5000", "10000", "100000"})
        private int size;

    }

    public static class TestPrimeNumberFinders {


        @Benchmark
        public void sequential(Blackhole blackhole, executionPlan plan) throws InterruptedException {
            Integer[] arr = parseArray(plan);
            PrimeNumberFinder finder = new PrimeNumberFinderImpl(arr);
            blackhole.consume(finder.haveNotPrime());

        }

        @Benchmark
        public void threads(Blackhole blackhole, executionPlan plan) throws InterruptedException {
            Integer[] arr = parseArray(plan);
            PrimeNumberFinder finder = new ThreadPrimeNumberFinder(arr, plan.threadCount);
            blackhole.consume(finder.haveNotPrime());

        }

        @Benchmark
        public void parallel(Blackhole blackhole, executionPlan plan) throws InterruptedException {
            Integer[] arr = parseArray(plan);
            PrimeNumberFinder finder = new ParallelStreamPrimeNumberFinder(arr);
            blackhole.consume(finder.haveNotPrime());

        }


        private Integer[] parseArray(executionPlan plan) {
            return (Integer[]) Arrays.stream("637093 "
                            .repeat(plan.size).
                            split(" ")
                    )
                    .map(Integer::parseInt)
                    .toArray();


        }
    }

}

