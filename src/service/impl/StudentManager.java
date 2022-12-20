package service.impl;

import model.Classroom;
import model.Student;
import service.ICrudManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class StudentManager implements ICrudManager<Student> {
    private final ArrayList<Student> students;
    private final ClassroomManager classroomManager;

    public StudentManager(ClassroomManager classroomManager) {
        students = new ArrayList<>();
        this.classroomManager = classroomManager;
    }

    public ClassroomManager getClassroomManager() {
        return classroomManager;
    }

    public ArrayList<Student> getStudents() {
        return students;
    }

    @Override
    public Student create(Scanner scanner) {
        System.out.println("Enter student name: ");
        String name = scanner.nextLine();
        System.out.println("Enter student age: ");
        int age = Integer.parseInt(scanner.nextLine());
        System.out.println("Enter student gender: ");
        String gender = choiceGender(scanner);
        System.out.println("Enter student address: ");
        String address = scanner.nextLine();
        classroomManager.displayAll(classroomManager.getClassrooms());
        Classroom classroom = choiceClassroom(scanner);
        return new Student(name, age, gender, address, classroom);
    }

    private Classroom choiceClassroom(Scanner scanner) {
        Classroom classroom;
        System.out.println("Enter choice classroom by id: (Enter 0 for create new)");
        int idClassroom = Integer.parseInt(scanner.nextLine());
        if (idClassroom == 0) {
            classroom = classroomManager.create(scanner);
            classroomManager.save(classroom);
        } else {
            classroom = classroomManager.getById(idClassroom);
        }
        if (classroom != null) {
            return classroom;
        } else {
            return choiceClassroom(scanner);
        }
    }

    private String choiceGender(Scanner scanner) {
        int choice;
        String gender = "";
        do {
            System.out.println("1. Male");
            System.out.println("2. Female");
            System.out.println("3. Other");
            System.out.println("Enter choice: (1 -> 3) ");
            choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1:
                    gender = "Male";
                    break;
                case 2:
                    gender = "Female";
                    break;
                case 3:
                    gender = "Other";
            }
        } while (choice < 0 || choice > 3);
        return gender;
    }

    @Override
    public void save(Student student) {
        students.add(student);
        System.out.println("Add student successfully!");
        title();
        student.display();
    }

    @Override
    public void update(Scanner scanner) {
        Student student = findById(scanner);
        if (student != null) {
            System.out.println("Enter new student name: (Enter to skip)");
            String name = scanner.nextLine();
            if (!name.equals("")) {
                student.setName(name);
            }
            System.out.println("Enter new student age: (Enter to skip)");
            String age = scanner.nextLine();
            if (!age.equals("")) {
                student.setAge(Integer.parseInt(age));
            }
            System.out.println("Do you want to change the gender?");
            System.out.println("Enter Y to update and any keyword to skip: ");
            String choiceG = scanner.nextLine();
            if (choiceG.equalsIgnoreCase("Y")) {
                String gender = choiceGender(scanner);
                student.setGender(gender);
            }
            System.out.println("Enter new student address: (Enter to skip)");
            String address = scanner.nextLine();
            if (!address.equals("")) {
                student.setAddress(address);
            }
            System.out.println("Do you want to change the classroom?");
            System.out.println("Enter Y to update and any keyword to skip: ");
            String choiceC = scanner.nextLine();
            if (choiceC.equalsIgnoreCase("Y")) {
                classroomManager.displayAll(classroomManager.getClassrooms());
                Classroom classroom = choiceClassroom(scanner);
                student.setClassroom(classroom);
            }
            System.out.println("Update student successfully!");
            title();
            student.display();
        } else {
            System.out.println("Not exist student have this id!");
        }
    }

    @Override
    public void deleteById(Scanner scanner) {
        Student student = findById(scanner);
        if (student != null) {
            students.remove(student);
            System.out.println("Delete student successfully!");
            title();
            student.display();
        } else {
            System.out.println("Not exist student have this id!");
        }
    }

    @Override
    public Student findById(Scanner scanner) {
        System.out.println("Enter id student: ");
        int id = Integer.parseInt(scanner.nextLine());
        for (Student student : students) {
            if (student.getId() == (id)) {
                return student;
            }
        }
        return null;
    }

    @Override
    public void displayAll(List<Student> students) {
        if (!students.isEmpty()) {
            System.out.println("List student: ");
            title();
            for (Student student : students) {
                student.display();
            }
        } else {
            System.out.println("Not exist student in list!");
        }
    }

    public void displayStudentById(Scanner scanner) {
        Student student = findById(scanner);
        if (student != null) {
            title();
            student.display();
        } else {
            System.out.println("Not exist student have this id!");
        }
    }

    public void displayStudentByClassroom(Scanner scanner) {
        ArrayList<Student> studentsByClassroom = new ArrayList<>();
        classroomManager.displayAll(classroomManager.getClassrooms());
        System.out.println("Enter choice classroom by id: ");
        int idClassroom = Integer.parseInt(scanner.nextLine());
        Classroom classroom = classroomManager.getById(idClassroom);
        if (classroom != null) {
            for (Student student : students) {
                if (student.getClassroom().equals(classroom)) {
                    studentsByClassroom.add(student);
                }
            }
            displayAll(studentsByClassroom);
        } else {
            System.out.println("Not exist classroom have this id!");
        }
    }

    public void searchStudentByName(Scanner scanner) {
        ArrayList<Student> studentsByName = new ArrayList<>();
        System.out.println("Enter name student: ");
        String nameSearch = scanner.nextLine();
        boolean check = false;
        for (Student student : students) {
            if (student.getName().toLowerCase().contains(nameSearch.toLowerCase())) {
                studentsByName.add(student);
                check = true;
            }
        }
        if (!check) {
            System.out.println("Not exist student have name contain this text!");
        } else {
            displayAll(studentsByName);
        }
    }

    private void title() {
        System.out.printf("%-15s%-15s%-15s%-15s%-15s%s",
                "ID", "NAME", "AGE", "GENDER", "ADDRESS", "CLASSROOM\n");
    }
}
