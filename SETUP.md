# Setup and Installation Guide

## Prerequisites: Installing Java

### Step 1: Download Java

Visit one of these options:

**Option A: Oracle Java JDK (Recommended)**
- Go to: https://www.oracle.com/java/technologies/downloads/
- Download "Java SE 21 LTS" for Windows x64
- Choose the .exe installer

**Option B: Eclipse Temurin (Free, Open Source)**
- Go to: https://adoptium.net/
- Download "Eclipse Temurin 21 LTS" for Windows x64
- Choose the .msi or .zip file

### Step 2: Install Java

**Using the .exe installer:**
1. Open the downloaded .exe file
2. Click "Next" through the wizard
3. It will install to `C:\Program Files\Java\jdk-21` (or similar)
4. Finish the installation

**Using the .msi installer (Temurin):**
1. Open the .msi file
2. Follow the installer wizard
3. Remember the installation path

### Step 3: Add Java to System PATH

This allows you to run `java` and `javac` commands from anywhere.

**Windows 10 & 11:**

1. Open File Explorer
2. Right-click "This PC" or "My Computer" → Properties
3. Click "Advanced system settings"
4. Click "Environment Variables"
5. Under "System variables", click "New"
6. Variable name: `JAVA_HOME`
7. Variable value: `C:\Program Files\Java\jdk-21` (adjust version number if different)
8. Click OK

9. Find "Path" in System variables, select it, click "Edit"
10. Click "New"
11. Add: `%JAVA_HOME%\bin`
12. Click OK on all dialogs

### Step 4: Verify Installation

Open PowerShell or Command Prompt and type:
```
java -version
javac -version
```

You should see Java version information. If you do, you're ready to go!

---

## Compiling the Application

After Java is installed, navigate to the Student Grade Tracker folder:

**Option 1: Using the Batch Script (Easiest)**
```
cd "C:\Users\Ruchitha\OneDrive\Student grade tracker"
run.bat
```
This will automatically compile and run the application.

**Option 2: Using PowerShell Script**
```
cd "C:\Users\Ruchitha\OneDrive\Student grade tracker"
powershell -ExecutionPolicy Bypass -File run.ps1
```

**Option 3: Manual Compilation**
```
cd "C:\Users\Ruchitha\OneDrive\Student grade tracker"
javac Student.java GradeManager.java GradeTrackerApp.java
java GradeTrackerApp
```

---

## Troubleshooting

**Problem: "javac is not recognized"**
- Solution: Java is not installed or PATH is not configured correctly
- Re-follow the installation steps above
- Restart your terminal after configuring PATH

**Problem: "Exception in thread "main""**
- Solution: Make sure all three .java files are in the same directory
- Re-compile: `javac *.java`

**Problem: "ClassNotFoundException"**
- Solution: Run from the directory containing the .class files
- Change to: `cd "C:\Users\Ruchitha\OneDrive\Student grade tracker"`

---

## Quick Start Summary

1. Install Java (download from oracle.com or adoptium.net)
2. Add Java to PATH (Environment Variables)
3. Navigate to project folder
4. Run: `run.bat` (or `java GradeTrackerApp` if already compiled)
5. Follow the menu prompts

---

## Need Help?

- Java Installation Help: https://docs.oracle.com/en/java/javase/21/install/
- Troubleshooting: Check that `java -version` and `javac -version` work in terminal
