package model;

public class Classroom {
    private static int INDEX = 1;
    private final int id;
    private String name;
    private String schoolName;

    public Classroom(String name, String schoolName) {
        this.id = INDEX;
        this.name = name;
        this.schoolName = schoolName;
        INDEX++;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    public void display() {
        System.out.printf("%-15s%-15s%s", id, name, schoolName + "\n");
    }
}
