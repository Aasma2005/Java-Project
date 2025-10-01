package utils;

import java.io.*;
import java.util.*;

import model.Student;

public class FileHelper {
    private static final String FILE_PATH = "data/students.txt";

    public static List<Student> loadStudents() {
        List<Student> students = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = br.readLine()) != null) {
                students.add(Student.fromString(line));
            }
        } catch (FileNotFoundException e) {
            System.out.println("Data file not found, creating new one...");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return students;
    }

    public static void saveStudents(List<Student> students) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_PATH))) {
            for (Student s : students) {
                bw.write(s.toString());
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
