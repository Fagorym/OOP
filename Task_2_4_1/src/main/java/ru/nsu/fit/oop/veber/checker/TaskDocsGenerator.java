package ru.nsu.fit.oop.veber.checker;

import ru.nsu.fit.oop.veber.model.Report;
import ru.nsu.fit.oop.veber.model.StudentResults;
import ru.nsu.fit.oop.veber.model.Task;

import java.util.List;

public class TaskDocsGenerator extends TaskExecutor {

    private final String name = "Javadoc Generator";

    public void execute(
            List<StudentResults> results,
            Task task
    ) {
        results.forEach(result -> {
            Report report = result.getTaskReports().get(task.getId());
            report.setHasDocs(makeTask(result, "javadoc", task));
            report.setTaskId(task.getId());
        });
    }

    @Override
    public String getName() {
        return name;
    }
}
