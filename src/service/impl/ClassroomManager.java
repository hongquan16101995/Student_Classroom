package service.impl;

import model.Classroom;
import service.ICrudManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ClassroomManager implements ICrudManager<Classroom> {
    private final ArrayList<Classroom> classrooms;

    public ClassroomManager() {
        classrooms = new ArrayList<>();
    }

    public ArrayList<Classroom> getClassrooms() {
        return classrooms;
    }

    @Override
    public Classroom create(Scanner scanner) {
        System.out.println("Enter class name: ");
        String name = scanner.nextLine();
        System.out.println("Enter school name: ");
        String schoolName = scanner.nextLine();
        return new Classroom(name, schoolName);
    }

    @Override
    public void save(Classroom classroom) {
        classrooms.add(classroom);
        System.out.println("Add classroom successfully!");
        title();
        classroom.display();
    }

    @Override
    public void update(Scanner scanner) {
        Classroom classroom = findById(scanner);
        if (classroom != null) {
            System.out.println("Enter new class name: ");
            String name = scanner.nextLine();
            if (!name.equals("")) {
                classroom.setName(name);
            }
            System.out.println("Enter new school name: ");
            String schoolName = scanner.nextLine();
            if (!schoolName.equals("")) {
                classroom.setSchoolName(schoolName);
            }
            System.out.println("Update classroom successfully!");
            title();
            classroom.display();
        } else {
            System.out.println("Not exist classroom have this id!");
        }
    }

    @Override
    public void deleteById(Scanner scanner) {
        Classroom classroom = findById(scanner);
        if (classroom != null) {
            classrooms.remove(classroom);
            System.out.println("Delete classroom successfully!");
            title();
            classroom.display();
        } else {
            System.out.println("Not exist classroom have this id!");
        }
    }

    @Override
    public Classroom findById(Scanner scanner) {
        System.out.println("Enter id classroom: ");
        int id = Integer.parseInt(scanner.nextLine());
        for (Classroom classroom : classrooms) {
            if (classroom.getId() == (id)) {
                return classroom;
            }
        }
        return null;
    }

    public Classroom getById(int classroomId) {
        for (Classroom classroom : classrooms) {
            if (classroom.getId() == classroomId) {
                return classroom;
            }
        }
        return null;
    }

    @Override
    public void displayAll(List<Classroom> classrooms) {
        if (!classrooms.isEmpty()) {
            System.out.println("List classroom: ");
            title();
            for (Classroom classroom : classrooms) {
                classroom.display();
            }
        } else {
            System.out.println("List classroom haven't element!");
        }
    }

    private void title() {
        System.out.printf("%-15s%-15s%s", "ID", "NAME", "SCHOOL_NAME\n");
    }
}
