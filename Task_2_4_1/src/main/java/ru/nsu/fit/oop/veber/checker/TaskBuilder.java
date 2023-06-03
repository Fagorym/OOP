package ru.nsu.fit.oop.veber.checker;

import ru.nsu.fit.oop.veber.model.Report;
import ru.nsu.fit.oop.veber.model.StudentResults;
import ru.nsu.fit.oop.veber.model.Task;

import java.util.List;

public class TaskBuilder extends TaskExecutor {

    public void buildProject(
            List<StudentResults> results,
            Task task
    ) {
        results.forEach(result -> {
            Report report = result.getTaskReports().get(task.getId());
            report.setWasBuilt(makeTask(result, "build", task));
            report.setTaskId(task.getId());
        });
    }
}
