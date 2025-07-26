## Smart Task Scheduler with Priority Queues

The Smart Task Scheduler helps users manage tasks based on priority and due date. It supports adding, editing, deleting, filtering, and reminding tasks through a simple GUI.

**Technologies Used**

- Java

- Java Swing (for GUI)

- Java Collections (PriorityQueue)

- Java Timer

- File I/O 

---

**Features**

1. Add Task
    - Allows user to input

        - Task Name

        - Due Date

        - Priority (High/Medium/Low)

     - Click “Add Task” button to save the task to the queue and the GUI list.
2.  View Tasks

Tasks are displayed in sorted order based on priority.

3. Delete Task

Select the task you want to delete and then click on "Delete" button.

4. Edit Task

    - Click "Edit" to modify the task fields.

    - Update the fields and click “Save” to reflect changes.
5. Reminders

    - Uses Java Timer to alert the user for upcoming or due tasks.

    - Pop-up/reminder shown when task due date matches current date

6. Filters
    - Dropdown allows:

        - View All Tasks

        - View Today’s Tasks

        - View High Priority Tasks

---

**How to Use**

1. Running the Application
    - Open the project folder in VS Code or your Java IDE.

    - Compile all .java files:

    `javac SmartTaskScheduler/*.java`

2. Run the main class
           
`java SmartTaskScheduler.TaskSchedulerUI`


                