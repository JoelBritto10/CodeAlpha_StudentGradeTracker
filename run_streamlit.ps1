# Student Grade Tracker - Streamlit Web App Launcher (PowerShell)

Write-Host ""
Write-Host "===============================================" -ForegroundColor Cyan
Write-Host "   Student Grade Tracker - Streamlit Web App" -ForegroundColor Cyan
Write-Host "===============================================" -ForegroundColor Cyan
Write-Host ""

# Check if Python is installed
try {
    $pythonVersion = python --version 2>&1
    Write-Host "[OK] Python detected successfully: $pythonVersion" -ForegroundColor Green
} catch {
    Write-Host "[ERROR] Python is not installed or not in PATH" -ForegroundColor Red
    Write-Host "Please install Python from https://www.python.org/downloads/" -ForegroundColor Red
    Read-Host "Press Enter to exit"
    exit 1
}

Write-Host ""

# Check if virtual environment exists, if not create it
if (-Not (Test-Path "venv")) {
    Write-Host "[CREATING] Virtual environment..." -ForegroundColor Yellow
    python -m venv venv
}

# Activate virtual environment
Write-Host "[ACTIVATING] Virtual environment..." -ForegroundColor Yellow
& .\venv\Scripts\Activate.ps1

# Check if requirements are installed
Write-Host "[CHECKING] Required packages..." -ForegroundColor Yellow
pip install -q -r requirements.txt

if ($LASTEXITCODE -ne 0) {
    Write-Host "[ERROR] Failed to install requirements!" -ForegroundColor Red
    Read-Host "Press Enter to exit"
    exit 1
}

Write-Host "[OK] All requirements installed!" -ForegroundColor Green
Write-Host ""
Write-Host "[RUNNING] Starting Streamlit app..." -ForegroundColor Yellow
Write-Host ""
Write-Host "Opening http://localhost:8501 in your browser..." -ForegroundColor Cyan
Write-Host ""

# Run Streamlit app
streamlit run app.py

Write-Host ""
Read-Host "Press Enter to exit"
