package model;

public class Student {
    private static int INDEX = 1;
    private final int id;
    private String name;
    private int age;
    private String gender;
    private String address;
    private Classroom classroom;

    public Student(String name, int age, String gender, String address, Classroom classroom) {
        this.id = INDEX;
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.address = address;
        this.classroom = classroom;
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

    public void setAge(int age) {
        this.age = age;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Classroom getClassroom() {
        return classroom;
    }

    public void setClassroom(Classroom classroom) {
        this.classroom = classroom;
    }

    public void display() {
        System.out.printf("%-15s%-15s%-15s%-15s%-15s%s",
                id, name, age, gender, address, classroom.getName() + "\n");
    }
}
