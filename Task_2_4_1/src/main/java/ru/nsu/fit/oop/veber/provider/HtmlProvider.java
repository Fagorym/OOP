package ru.nsu.fit.oop.veber.provider;

import lombok.Data;
import ru.nsu.fit.oop.veber.model.Report;

import java.io.File;
import java.io.FileWriter;
import java.util.Map;

@Data
public class HtmlProvider {
    public void generateHtml(Map<String, Map<String, Report>> reports) {
        StringBuilder sb = new StringBuilder();
        sb.append("<html>");
        sb.append("<style>\n")
                .append("table, th, td {\n")
                .append("  border:1px solid black;\n")
                .append("}\n")
                .append("</style>");
        sb.append("<head>");
        sb.append("</head>");
        for (var entry : reports.entrySet()) {
            sb.append("<h1>").append(entry.getKey()).append("</h1>");
            sb.append("<table>");
            sb.append("<th> Student </th>");
            sb.append("<th> Built </th>");
            sb.append("<th> Docs </th>");
            sb.append("<th> Tests </th>");
            for (var taskStudentEntry : entry.getValue().entrySet()) {
                sb.append("<tr>");
                sb.append("<td>").append(taskStudentEntry.getKey()).append("</td>");
                sb.append("<td>").append(taskStudentEntry.getValue().isWasBuilt()).append("</td>");
                sb.append("<td>").append(taskStudentEntry.getValue().isHasDocs()).append("</td>");
                sb.append("<td>").append(taskStudentEntry.getValue().isWasTested()).append("</td>");
                sb.append("</tr>");
            }
            sb.append("</table>");
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
