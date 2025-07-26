package CollegeAdmissionManagementSystem;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n--- College Admission System ---");
            System.out.println("1. Register Student");
            System.out.println("2. View Courses");
            System.out.println("3. Apply for Course");
            System.out.println("4. View Applications (Admin)");
            System.out.println("5. Export Admission List");
            System.out.println("6. Exit");
            System.out.print("Choose: ");

            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Name: ");
                    String name = sc.nextLine();
                    System.out.print("Email: ");
                    String email = sc.nextLine();
                    System.out.print("Marks: ");
                    int marks = sc.nextInt();
                    sc.nextLine();
                    StudentService.registerStudent(new Student(name, email, marks));
                    break;

                case 2:
                    CourseService.viewCourses();
                    break;

                case 3:
                    System.out.print("Student ID: ");
                    int sid = sc.nextInt();
                    System.out.print("Course ID: ");
                    int cid = sc.nextInt();
                    StudentService.applyForCourse(sid, cid);
                    break;

                case 4:
                    AdminService.viewApplications();
                    break;

                case 5:
                    ExportUtil.exportToCSV();
                    break;

                case 6:
                    System.out.println("Goodbye!");
                    sc.close();
                    return;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
