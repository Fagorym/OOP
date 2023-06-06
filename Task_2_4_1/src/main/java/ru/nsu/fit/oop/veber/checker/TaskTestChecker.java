package ru.nsu.fit.oop.veber.checker;

import lombok.extern.slf4j.Slf4j;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
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
            report.setWasTested(makeTask(result, "test", task));
            getTestCoverageThroughJacoco(result, task);
            report.setTaskId(task.getId());
        });
    }

    private void getTestCoverageThroughJacoco(StudentResults result, Task task) {
        makeTask(result, "jacocoTestReport", task);
        String testResultPath = result.getPath() + '/' + task.getId() + "/build/reports/jacoco/test/html";
        File file = new File(testResultPath);
        File[] files = file.listFiles();
        if (files != null) {

            for (File value : files) {
                if (value.getName().matches("index.html")) {
                    try {
                        DocumentBuilder documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
                        Document document = documentBuilder.parse(value);
                        NodeList nodes = document.getElementsByTagName("td");
                        for (int i = 0; i < nodes.getLength(); i++) {
                            Node node = nodes.item(i);
                            NamedNodeMap map = node.getAttributes();
                            for (int j = 0; j < map.getLength(); j++) {
                                if ("class".equals(map.item(j).getNodeName()) && "ctr2".equals(map.item(j).getNodeValue())) {
                                    result.getTestCoverage().put(task.getId(), node.getTextContent());
                                }
                            }
                        }
                    } catch (ParserConfigurationException | IOException | SAXException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }
    }

    @Override
    public String getName() {
        return name;
    }
}
