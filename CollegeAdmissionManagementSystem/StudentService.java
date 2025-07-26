package CollegeAdmissionManagementSystem;

import java.sql.*;

class StudentService {
    public static void registerStudent(Student student) {
        try (Connection conn = DBConnection.getConnection()) {
            String query = "INSERT INTO Students(name, email, marks) VALUES (?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, student.name);
            stmt.setString(2, student.email);
            stmt.setInt(3, student.marks);
            stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                int studentId = rs.getInt(1);
                System.out.println("Student Registered ID: " + studentId);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void applyForCourse(int studentId, int courseId) {
        try (Connection conn = DBConnection.getConnection()) {
            // Get student marks
            PreparedStatement s = conn.prepareStatement("SELECT marks FROM Students WHERE id = ?");
            s.setInt(1, studentId);
            ResultSet rs1 = s.executeQuery();
            if (!rs1.next()) {
                System.out.println(" Student with ID " + studentId + " not found.");
                return;
            }
            int marks = rs1.getInt("marks");

            // Get course cutoff
            PreparedStatement c = conn.prepareStatement("SELECT cutoff FROM Courses WHERE id = ?");
            c.setInt(1, courseId);
            ResultSet rs2 = c.executeQuery();

            if (!rs2.next()) {
                System.out.println(" Course with ID " + courseId + " not found.");
                return;
            }
            int cutoff = rs2.getInt("cutoff");

            String status = (marks >= cutoff) ? "Approved" : "Rejected";

            PreparedStatement app = conn
                    .prepareStatement("INSERT INTO Applications(student_id, course_id, status) VALUES (?, ?, ?)");
            app.setInt(1, studentId);
            app.setInt(2, courseId);
            app.setString(3, status);
            app.executeUpdate();

            System.out.println("Application Submitted: " + status);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
