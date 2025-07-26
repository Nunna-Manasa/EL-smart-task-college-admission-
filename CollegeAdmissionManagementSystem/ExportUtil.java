package CollegeAdmissionManagementSystem;

import java.sql.*;
import java.io.*;

class ExportUtil {
    public static void exportToCSV() {
        try (Connection conn = DBConnection.getConnection();
                FileWriter fw = new FileWriter("admission_list.csv")) {
            String query = "SELECT s.name, s.email, c.name AS course, a.status FROM Applications a JOIN Students s ON a.student_id = s.id JOIN Courses c ON a.course_id = c.id";
            ResultSet rs = conn.createStatement().executeQuery(query);
            fw.write("Name, Email, Course, Status\n");

            while (rs.next()) {
                fw.write(rs.getString("name") + ", " +
                        rs.getString("email") + ", " +
                        rs.getString("course") + ", " +
                        rs.getString("status") + "\n");
            }

            System.out.println("Exported to admission_list.csv");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
