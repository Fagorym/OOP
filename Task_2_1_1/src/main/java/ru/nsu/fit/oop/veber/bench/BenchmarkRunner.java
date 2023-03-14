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
                .threads(8)
                .warmupForks(1)
                .warmupIterations(5)
                .warmupBatchSize(1)
                .result("target/jmh-reports/result.json")
                .resultFormat(ResultFormatType.JSON)
                .mode(Mode.Throughput)
                .timeUnit(TimeUnit.MILLISECONDS)
                .build();

        new Runner(opt).run();
    }

    @State(Scope.Benchmark)
    public static class sizeExecutionPlan {

        @Param({"5", "20", "100", "5000", "100000"})
        public int size;

        @Param({"true", "false"})
        public boolean earlyExit;

    }

    @State(Scope.Benchmark)
    public static class threadCountExecutionPlan {
        @Param({"1", "2", "4", "8", "16"})
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
            Integer[] array = Arrays.stream("637093 "
                            .repeat(plan.size).
                            split(" ")
                    )
                    .map(Integer::parseInt)
                    .toArray(Integer[]::new);
            if (plan.earlyExit) {
                array[array.length - 1] = 4;
            }
            return array;

        }
    }

}
