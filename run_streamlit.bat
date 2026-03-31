@echo off
REM Streamlit Web App Launcher

setlocal enabledelayedexpansion

echo.
echo ===============================================
echo   Student Grade Tracker - Streamlit Web App
echo ===============================================
echo.

REM Check if Python is installed
python --version >nul 2>&1
if errorlevel 1 (
    echo [ERROR] Python is not installed or not in PATH
    echo Please install Python from https://www.python.org/downloads/
    pause
    exit /b 1
)

echo [INFO] Python detected successfully
echo.

REM Check if virtual environment exists, if not create it
if not exist "venv" (
    echo [CREATING] Virtual environment...
    python -m venv venv
)

REM Activate virtual environment
echo [ACTIVATING] Virtual environment...
call venv\Scripts\activate.bat

REM Check if requirements are installed
echo [CHECKING] Required packages...
pip install -q -r requirements.txt

if errorlevel 1 (
    echo [ERROR] Failed to install requirements!
    pause
    exit /b 1
)

echo [OK] All requirements installed!
echo.
echo [RUNNING] Starting Streamlit app...
echo.
echo Opening http://localhost:8501 in your browser...
echo.

REM Run Streamlit app
streamlit run app.py

echo.
pause
