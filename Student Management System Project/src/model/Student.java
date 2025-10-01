package model;

public class Student {
    private int id;
    private String name;
    private int age;
    private double marks;

    public Student(int id, String name, int age, double marks) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.marks = marks;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public int getAge() { return age; }
    public double getMarks() { return marks; }

    public void setName(String name) { this.name = name; }
    public void setAge(int age) { this.age = age; }
    public void setMarks(double marks) { this.marks = marks; }

    @Override
    public String toString() {
        return id + "," + name + "," + age + "," + marks;
    }

    public static Student fromString(String line) {
        String[] parts = line.split(",");
        return new Student(Integer.parseInt(parts[0]), parts[1],
                           Integer.parseInt(parts[2]), Double.parseDouble(parts[3]));
    }
}
