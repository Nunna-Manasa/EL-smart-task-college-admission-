package CollegeAdmissionManagementSystem;

import java.sql.*;

class CourseService {
    public static void viewCourses() {
        try (Connection conn = DBConnection.getConnection()) {
            String query = "SELECT id, name, cutoff FROM Courses";
            ResultSet rs = conn.createStatement().executeQuery(query);
            System.out.println("--- Available Courses ---");
            while (rs.next()) {
                System.out.println("ID: " + rs.getInt("id")
                        + ", Name: " + rs.getString("name")
                        + ", Cutoff: " + rs.getInt("cutoff"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
