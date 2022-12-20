import model.Classroom;
import model.Student;
import service.impl.ClassroomManager;
import service.impl.StudentManager;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ClassroomManager classroomManager = new ClassroomManager();
        StudentManager studentManager = new StudentManager(classroomManager);
        menuStudent(studentManager, scanner);
    }

    private static void menuStudent(StudentManager studentManager, Scanner scanner) {
        int choice;
        do {
            System.out.println("MENU:");
            System.out.println("1. Add new student");
            System.out.println("2. Update student by id");
            System.out.println("3. Delete student by id");
            System.out.println("4. Display student by id");
            System.out.println("5. Display all student");
            System.out.println("6. Display all student by classroom");
            System.out.println("7. Search all student by name");
            System.out.println("8. Menu of student");
            System.out.println("0. Exit");
            System.out.println("Enter your choice: ");
            choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1:
                    Student student = studentManager.create(scanner);
                    studentManager.save(student);
                    break;
                case 2:
                    studentManager.update(scanner);
                    break;
                case 3:
                    studentManager.deleteById(scanner);
                    break;
                case 4:
                    studentManager.displayStudentById(scanner);
                    break;
                case 5:
                    studentManager.displayAll(studentManager.getStudents());
                    break;
                case 6:
                    studentManager.displayStudentByClassroom(scanner);
                    break;
                case 7:
                    studentManager.searchStudentByName(scanner);
                    break;
                case 8:
                    menuClassroom(studentManager.getClassroomManager(), scanner);
                    break;
            }
        } while (choice != 0);
    }

    private static void menuClassroom(ClassroomManager classroomManager, Scanner scanner) {
        int choice;
        do {
            System.out.println("MENU:");
            System.out.println("1. Add new classroom");
            System.out.println("2. Update classroom by id");
            System.out.println("3. Delete classroom by id");
            System.out.println("4. Display all classroom");
            System.out.println("0. Exit");
            System.out.println("Enter your choice: ");
            choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1:
                    Classroom classroom = classroomManager.create(scanner);
                    classroomManager.save(classroom);
                    break;
                case 2:
                    classroomManager.update(scanner);
                    break;
                case 3:
                    classroomManager.deleteById(scanner);
                    break;
                case 4:
                    classroomManager.displayAll(classroomManager.getClassrooms());
                    break;
            }
        } while (choice != 0);
    }
}
