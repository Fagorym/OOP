package ru.nsu.fit.oop.veber.bench;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;
import org.openjdk.jmh.results.format.ResultFormatType;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;
import ru.nsu.fit.oop.veber.ParallelStreamPrimeNumberFinder;
import ru.nsu.fit.oop.veber.PrimeNumberFinder;
import ru.nsu.fit.oop.veber.PrimeNumberFinderImpl;
import ru.nsu.fit.oop.veber.ThreadPrimeNumberFinder;

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
                .measurementIterations(3)
                .warmupIterations(1)
                .warmupBatchSize(3)
                .result("target/jmh-reports/result.json")
                .resultFormat(ResultFormatType.JSON)
                .mode(Mode.Throughput)
                .timeUnit(TimeUnit.SECONDS)
                .build();

        new Runner(opt).run();
    }

    @State(Scope.Benchmark)
    public static class sizeExecutionPlan {

        @Param({"1", "5", "20", "100", "600", "2000", "5000", "25000", "100000"})
        public int size;

        @Param({"true", "false"})
        public boolean earlyExit;

    }

    @State(Scope.Benchmark)
    public static class threadCountExecutionPlan {
        @Param({"1", "2", "3", "4", "8", "16"})
        public int threadCount;
    }

    public static class TestPrimeNumberFinders {


        @Benchmark
        public void sequential(Blackhole blackhole, sizeExecutionPlan plan) throws InterruptedException {
            Integer[] arr = parseArray(plan);
            PrimeNumberFinder finder = new PrimeNumberFinderImpl(arr);
            blackhole.consume(finder.haveNotPrime());

        }

        @Benchmark
        public void threads(Blackhole blackhole, sizeExecutionPlan plan, threadCountExecutionPlan threadPlan) throws InterruptedException {
            Integer[] arr = parseArray(plan);
            PrimeNumberFinder finder = new ThreadPrimeNumberFinder(arr, threadPlan.threadCount);
            blackhole.consume(finder.haveNotPrime());

        }

        @Benchmark
        public void parallel(Blackhole blackhole, sizeExecutionPlan plan) throws InterruptedException {
            Integer[] arr = parseArray(plan);
            PrimeNumberFinder finder = new ParallelStreamPrimeNumberFinder(arr);
            blackhole.consume(finder.haveNotPrime());

        }


        private Integer[] parseArray(sizeExecutionPlan plan) {
            Integer[] array = Arrays.stream("6998009 "
                            .repeat(plan.size).
                            split(" ")
                    )
                    .map(Integer::parseInt)
                    .toArray(Integer[]::new);
            if (plan.earlyExit) {
                array[array.length / 2 - 1] = 4;
            }
            return array;

        }
    }

}

