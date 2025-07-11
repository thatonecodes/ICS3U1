# ICS3U1 
This is the ICS3U1 repository, which contains all the source code for the course.
---
The repository is organized by units and each unit has its own folder.
Each folder contains the source code for the unit and a README file that explains the content of the unit.
The source code is written in `Java`, the langauge used in the course.

## Directory Structure
The directory structure contains files that contain packages of homework organized by week (week1, week2, etc.).  
It also includes the culminating project, which is built as Javafx Maven project.

If running the code inside the ICS3U1 folder, your `.vscode/settings.json` file should look something like this:
```json
{
    "java.project.sourcePaths": [
        "Homework",
        //"Extras/java-design-patterns",
        "Assignments",
    ]
}
```
Adding this to the `settings.json` file prevents `file not in source path` errors in your IDE.

## Note
The `Extras` directory contains extra code written in Java that was NOT part of the course, but some of which I found useful when I was programming.
