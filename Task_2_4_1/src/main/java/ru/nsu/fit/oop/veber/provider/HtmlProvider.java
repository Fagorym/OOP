package ru.nsu.fit.oop.veber.provider;

import lombok.Data;
import ru.nsu.fit.oop.veber.model.Report;

import java.io.File;
import java.io.FileWriter;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Data
public class HtmlProvider {
    public void generateHtml(Map<String, Map<String, Report>> reports) {
        StringBuilder sb = new StringBuilder();
        Set<String> students = reports.values().stream()
                .map(Map::keySet)
                .findFirst()
                .orElse(Collections.emptySet());
        Map<String, Integer> totalScoreForStudent = new HashMap<>();
        sb.append("<html>");
        String styles = "<style>" +
                "table {border-collapse: collapse; width: 100%;}" +
                "th, td {border: 1px solid #dddddd; text-align: center; padding: 8px;}" +
                "th {background-color: #f2f2f2;}" +
                "tr:nth-child(even) {background-color: #f2f2f2;}" +
                "</style>";
        sb.append(styles);
        sb.append("<head>");
        sb.append("</head>");
        sb.append("<table border=\"1\">\n");
        sb.append("<tr>\n");
        sb.append("<th rowspan=\"2\">Student</th>\n");

        int numberOfTasks = reports.size();
        for (var taskEntry : reports.entrySet()) {
            sb.append("<th colspan=\"4\">").append(taskEntry.getKey()).append("</th>\n");
        }
        sb.append("</tr>\n");

        sb.append("<tr>\n");
        for (int i = 1; i <= numberOfTasks; i++) {
            sb.append("<th>Build</th>\n");
            sb.append("<th>Test</th>\n");
            sb.append("<th>Javadoc</th>\n");
            sb.append("<th>Score</th>\n");
        }
        sb.append("</tr>\n");
        for (String student : students) {
            sb.append("<tr>\n");
            sb.append("<td>").append(student).append("</td>\n");

            for (String taskName : reports.keySet()) {
                Report report = reports.get(taskName).get(student);
                sb.append("<td>").append(report.isWasBuilt() ? "+" : "-").append("</td>\n");
                sb.append("<td>").append(report.isWasTested() ? "+" : "-").append("</td>\n");
                sb.append("<td>").append(report.isHasDocs() ? "+" : "-").append("</td>\n");
                sb.append("<td>").append(report.getScore()).append("</td>\n");
                if (!totalScoreForStudent.containsKey(student)) {
                    totalScoreForStudent.put(student, report.getScore());
                } else {
                    int curScore = totalScoreForStudent.get(student);
                    totalScoreForStudent.put(student, curScore + report.getScore());
                }
            }

            sb.append("</tr>\n");
        }
        sb.append("</table>");
        sb.append("\n\n<table>");
        sb.append("<tr>");
        sb.append("<th>Student</th>\n");
        sb.append("<th>Total</th>\n");
        for (String student : students) {
            sb.append("<tr>\n");
            sb.append("<td>").append(student).append("</td>\n");
            sb.append("<td>").append(totalScoreForStudent.get(student)).append("</td>\n");
        }

        sb.append("</body>");
        sb.append("</html>");
        try {
            File file = new File("./Task_2_4_1/build/students/report.html");
            if (file.createNewFile() || file.exists()) {
                FileWriter fileWriter = new FileWriter(file.getPath());
                fileWriter.write(sb.toString());
                fileWriter.flush();
                fileWriter.close();
            } else {
                throw new RuntimeException();
            }
        } catch (Exception e) {
            System.out.println("Problem with generating report.");
        }
    }
}
