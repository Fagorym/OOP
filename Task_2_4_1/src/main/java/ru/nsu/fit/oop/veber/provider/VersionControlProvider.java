package ru.nsu.fit.oop.veber.provider;

import org.eclipse.jgit.api.Git;
import ru.nsu.fit.oop.veber.model.Lesson;
import ru.nsu.fit.oop.veber.model.Student;
import ru.nsu.fit.oop.veber.model.StudentResults;

import java.util.Collection;
import java.util.List;

public interface VersionControlProvider {
    StudentResults cloneRepository(Student student);

    List<StudentResults> cloneRepository(Collection<Student> students);

    boolean checkLessonAttendance(Lesson lesson, Git git);
}