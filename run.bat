@echo off
REM Student Grade Tracker Compilation and Execution Script

setlocal enabledelayedexpansion

echo.
echo ===============================================
echo   Student Grade Tracker - Build and Run
echo ===============================================
echo.

REM Check if Java is installed
java -version >nul 2>&1
if errorlevel 1 (
    echo [ERROR] Java is not installed or not in PATH
    echo Please install Java and add it to your PATH
    pause
    exit /b 1
)

echo [INFO] Java detected successfully
echo.

REM Compile the Java files
echo [COMPILING] Compiling Java files...
javac Student.java GradeManager.java GradeTrackerApp.java

if errorlevel 1 (
    echo.
    echo [ERROR] Compilation failed!
    pause
    exit /b 1
)

echo [OK] Compilation successful!
echo.

REM Run the application
echo [RUNNING] Starting Student Grade Tracker...
echo.
java GradeTrackerApp

REM Pause to see the final message
echo.
pause
