package CollegeAdmissionManagementSystem;

import java.sql.*;

class AdminService {
    public static void viewApplications() {
        try (Connection conn = DBConnection.getConnection()) {
            String query = "SELECT a.id, s.name, c.name, a.status FROM Applications a JOIN Students s ON a.student_id = s.id JOIN Courses c ON a.course_id = c.id";
            ResultSet rs = conn.createStatement().executeQuery(query);
            System.out.println("Applications:");
            while (rs.next()) {
                System.out.println("ID: " + rs.getInt(1) + ", Student: " + rs.getString(2) + ", Course: "
                        + rs.getString(3) + ", Status: " + rs.getString(4));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
