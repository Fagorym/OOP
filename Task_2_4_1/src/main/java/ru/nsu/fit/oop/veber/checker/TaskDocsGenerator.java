package ru.nsu.fit.oop.veber.checker;

import ru.nsu.fit.oop.veber.model.Project;
import ru.nsu.fit.oop.veber.model.Report;
import ru.nsu.fit.oop.veber.model.Task;

import java.util.Collection;
import java.util.Map;

public class TaskDocsGenerator extends TaskExecutor {
    public static void generateDocs(
            Collection<Project> projects,
            Task task,
            Map<String, Map<String, Report>> reports
    ) {
        projects.forEach(project -> {
            Report report = reports.get(task.getId()).get(project.getStudent().getNickname());
            makeTask(project, "javadoc", task, report);
        });
    }
}
