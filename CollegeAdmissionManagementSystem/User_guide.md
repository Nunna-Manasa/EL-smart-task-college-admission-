## College Admission Management System

The College Admission Management System is a Java-based project that helps students register, apply for courses, and allows admins to manage applications and export selected student lists. It is built using Java, JDBC, and MySQL.

This system helps college admins manage student registrations and course applications based on merit.

----

**System Requirements**

- Java JDK 17+
- MySQL Server
- MySQL JDBC Connector (mysql-connector-j-9.3.0.jar)
- VS Code / IntelliJ
- MySQL Workbench 

**How to Run the Project**

1. **Setup MySQL Database**

- Open MySQL Workbench 

- Run the provided db_schema.sql file to create all required tables:

    - Students

    - Courses

    - Applications

2. **Compile Java Files**
- Open VS Code terminal and run:

`javac -cp ".;libs/mysql-connector-j-9.3.0.jar" CollegeAdmissionManagementSystem/*.java`

3. **Run the application**

`java -cp ".;libs/mysql-connector-j-9.3.0.jar" CollegeAdmissionManagementSystem.Main`

---


**Features**

Once you run the program, you'll see this menu:

![alt text](<../Output files/Home menu.PNG>)

**Option 1: Register Student**
- Enter name, email, and marks.
- Student is added to the students table.

**Option 2: View Courses**

- Displays all available courses from the courses table.

**Option 3: Apply for Course**

- Enter student ID and course ID.

- Validates marks against course cutoff.

- Application saved to the applications table.

**Option 4: View Applications (Admin)**

- Admin can view all student applications and statuses.

**Option 5: Export Admission List**

- Exports selected student list to CSV file admission_list.csv.

**Option 6: Exit**

- Exits the application.

___
**Notes**

- Make sure MySQL is running.

- Don’t forget to update your DB username/password in DBConnection.java.


