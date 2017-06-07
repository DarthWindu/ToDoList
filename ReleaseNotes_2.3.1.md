# Release Notes 2.3.1
-----------------------------------
**MAJOR** CHANGES since v1.0
- Changed the save file from a Serializable-Java file to an XML extension file. This will allow the file to be readable and editable.
- Fixed the creation of a new task to allow for two tasks or more tasks of the same name.

**FIXED** since v1.0
- When trying to change the name of a task in the edit action screen to a name of another task that already exists a pop up message saying that the task already exists pops up twice (only when exiting while name text field is in focus)
- The order of tasks in the same priority reverse order every time an edit action item screen is closed. This has been fixed so that the order does not change.
- If two comments have the same text and the user tries to delete the older comment the program will delete the newer comment with the same text instead.
- When there are only two items and you drag one item up the font doesn’t change(priority still changes correctly, just does not update font correctly)
- The To Do List no longer randomly changes order when a task is double-clicked.
- The date-changing function no longer allows the user to change a date of elevation to the past.

**IMPROVEMENTS** since v.1.0
- Changed the .vbs/.bat file startup to a simple .exe startup for ease of use. It also slightly improved the load time.
- The font-changing was improved so that the different priorities are distinguishable.
- An inactive task’s date was moved onto a different line from the task name, improving the readability of the task.
