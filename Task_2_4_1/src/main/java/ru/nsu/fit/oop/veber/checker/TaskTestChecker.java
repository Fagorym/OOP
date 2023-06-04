package ru.nsu.fit.oop.veber.checker;

import lombok.extern.slf4j.Slf4j;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import ru.nsu.fit.oop.veber.model.Report;
import ru.nsu.fit.oop.veber.model.StudentResults;
import ru.nsu.fit.oop.veber.model.Task;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.List;

@Slf4j
public class TaskTestChecker extends TaskExecutor {

    private final String name = "Test Checker";

    public void execute(
            List<StudentResults> results,
            Task task) {
        results.forEach(result -> {
            Report report = result.getTaskReports().get(task.getId());
            report.setWasTested(makeTask(result, "jacocoTestReport", task));
            String testResultPath = result.getPath() + '/' + task.getId() + "/build/test-results/test/";
            File file = new File(testResultPath);
            File[] files = file.listFiles();
            if (files != null) {

                for (File value : files) {
                    if (value.getName().matches("(.)*.xml")) {
                        try {
                            DocumentBuilder documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
                            Document document = documentBuilder.parse(value);
                            NodeList nodeList = document.getElementsByTagName("testcase");
                            int totalTestCount = nodeList.getLength();
                            log.info("Total tests count: {}", totalTestCount);
                            nodeList = document.getElementsByTagName("failure");
                            log.info("Success test count: {}", totalTestCount - nodeList.getLength());
                            log.info("Failure test count: {}", nodeList.getLength());
                        } catch (ParserConfigurationException | IOException | SAXException e) {
                            throw new RuntimeException(e);
                        }
                        report.setTaskId(task.getId());
                    }
                }
            }
        });
    }

    @Override
    public String getName() {
        return name;
    }
}
