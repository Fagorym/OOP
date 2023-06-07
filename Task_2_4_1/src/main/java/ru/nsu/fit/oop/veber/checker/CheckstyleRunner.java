package ru.nsu.fit.oop.veber.checker;

import lombok.extern.slf4j.Slf4j;
import ru.nsu.fit.oop.veber.checkstyle.CustomChecker;
import ru.nsu.fit.oop.veber.model.StudentResults;
import ru.nsu.fit.oop.veber.model.Task;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Stream;

@Slf4j
public class CheckstyleRunner implements Executor {

    @Override
    public void execute(List<StudentResults> results, Task task) {
        for (StudentResults result : results) {
            CustomChecker checker = new CustomChecker(
                    result.getStudent().getNickname(),
                    task.getId()
            );

            try (Stream<Path> walk = Files.walk(Path.of(result.getPath() + '/' + task.getId()))) {
                List<File> files = walk
                        .filter(file -> file.getFileName().toString().endsWith(".java"))
                        .map(path -> new File(path.toUri()))
                        .toList();

                checker.checkCodestyle(files);
                result.getCheckStyleReport().put(
                        task.getId(), "result_" + result.getStudent().getNickname() + "_" + task.getId()
                );

            } catch (Exception e) {
                log.warn("Cannot check style for student " + result.getStudent().getNickname());
                log.warn(e.getMessage());
            }
        }
    }

    @Override
    public String getName() {
        return "styleChecker";
    }
}
