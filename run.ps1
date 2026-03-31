# Student Grade Tracker - PowerShell Build and Run Script

Write-Host ""
Write-Host "===============================================" -ForegroundColor Cyan
Write-Host "   Student Grade Tracker - Build and Run" -ForegroundColor Cyan
Write-Host "===============================================" -ForegroundColor Cyan
Write-Host ""

# Check if Java is installed
try {
    $javaVersion = java -version 2>&1
    Write-Host "[OK] Java detected successfully" -ForegroundColor Green
} catch {
    Write-Host "[ERROR] Java is not installed or not in PATH" -ForegroundColor Red
    Write-Host "Please install Java and add it to your PATH" -ForegroundColor Red
    Read-Host "Press Enter to exit"
    exit 1
}

Write-Host ""
Write-Host "[COMPILING] Compiling Java files..." -ForegroundColor Yellow

# Compile the Java files
javac Student.java GradeManager.java GradeTrackerApp.java

if ($LASTEXITCODE -ne 0) {
    Write-Host ""
    Write-Host "[ERROR] Compilation failed!" -ForegroundColor Red
    Read-Host "Press Enter to exit"
    exit 1
}

Write-Host "[OK] Compilation successful!" -ForegroundColor Green
Write-Host ""
Write-Host "[RUNNING] Starting Student Grade Tracker..." -ForegroundColor Yellow
Write-Host ""

# Run the application
java GradeTrackerApp

Write-Host ""
Read-Host "Press Enter to exit"
