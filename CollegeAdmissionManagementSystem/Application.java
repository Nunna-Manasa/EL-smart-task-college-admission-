package CollegeAdmissionManagementSystem;

class Application {
    public int id;
    public int studentId;
    public int courseId;
    public String status;

    public Application(int studentId, int courseId, String status) {
        this.studentId = studentId;
        this.courseId = courseId;
        this.status = status;
    }
}
