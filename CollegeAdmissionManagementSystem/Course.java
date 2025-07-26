package CollegeAdmissionManagementSystem;

class Course {
    public int id;
    public String name;
    public int cutoff;

    public Course(String name, int cutoff) {
        this.name = name;
        this.cutoff = cutoff;
    }
}