package ru.nsu.fit.oop.veber.provider;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ru.nsu.fit.oop.veber.checker.TaskBuilder;
import ru.nsu.fit.oop.veber.checker.TaskDocsGenerator;
import ru.nsu.fit.oop.veber.checker.TaskTestChecker;

@Slf4j
@RequiredArgsConstructor
public class GradleProvider {
    private final TaskBuilder taskBuilder;
    private final TaskTestChecker taskTestChecker;
    private final TaskDocsGenerator taskDocsGenerator;
}
