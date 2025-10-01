package service;

import model.Student;
import utils.FileHelper;
import java.util.*;

public class StudentService {
    private List<Student> students;
    private int lastId = 0;

    public StudentService() {
        students = FileHelper.loadStudents();
        for (Student s : students) {
            if (s.getId() > lastId) lastId = s.getId();
        }
    }

    public void addStudent(String name, int age, double marks) {
        Student s = new Student(++lastId, name, age, marks);
        students.add(s);
        FileHelper.saveStudents(students);
    }

    public void updateStudent(int id, String name, int age, double marks) {
        for (Student s : students) {
            if (s.getId() == id) {
                s.setName(name);
                s.setAge(age);
                s.setMarks(marks);
                break;
            }
        }
        FileHelper.saveStudents(students);
    }

    public void deleteStudent(int id) {
        students.removeIf(s -> s.getId() == id);
        FileHelper.saveStudents(students);
    }

    public List<Student> getAllStudents() {
        return new ArrayList<>(students);
    }

    public List<Student> sortByName() {
        students.sort(Comparator.comparing(Student::getName));
        return students;
    }

    public List<Student> sortByMarks() {
        students.sort(Comparator.comparingDouble(Student::getMarks).reversed());
        return students;
    }
}
