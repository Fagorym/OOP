package ru.nsu.fit.oop.veber.checker;

import ru.nsu.fit.oop.veber.model.StudentResults;
import ru.nsu.fit.oop.veber.model.Task;

import java.util.List;

public interface Executor {

    void execute(List<StudentResults> results, Task task);

    String getName();

}
