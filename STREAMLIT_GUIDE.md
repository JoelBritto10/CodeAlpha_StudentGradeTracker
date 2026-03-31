# Running the Streamlit Web Application

## Prerequisites

You need to have Python 3.8 or higher installed on your system.

**Check if Python is installed:**
```powershell
python --version
```

If not installed, download from: https://www.python.org/downloads/

---

## Installation & Setup

### Step 1: Navigate to Project Directory
```powershell
cd "C:\Users\Ruchitha\OneDrive\Student grade tracker"
```

### Step 2: Create Virtual Environment (Recommended)

**Windows PowerShell:**
```powershell
python -m venv venv
.\venv\Scripts\Activate.ps1
```

**Windows Command Prompt:**
```cmd
python -m venv venv
venv\Scripts\activate.bat
```

### Step 3: Install Required Packages

```powershell
pip install -r requirements.txt
```

This will install:
- **streamlit** - Web app framework
- **pandas** - Data manipulation
- **plotly** - Interactive charts

---

## Running the Application

### Start the Streamlit App
```powershell
streamlit run app.py
```

The app will automatically open in your default browser at: `http://localhost:8501`

If it doesn't open automatically, manually visit: http://localhost:8501

---

## Features of the Web App

### 📊 Dashboard
- Overview of all class statistics
- Top and bottom performers
- Grade distribution chart
- Key metrics (averages, totals)

### ➕ Add Student
- Add new students with unique IDs
- Simple form interface

### 📝 Add Grade
- Add grades to existing students
- Dropdown to select student
- Automatic validation (0-100)

### 👤 View Student
- See individual student details
- View all grades
- Interactive graph showing grade trends
- Personal statistics

### 🗑️ Remove Student
- Delete students from the system
- Confirmation before removal

### 📈 Analytics
- Visual comparisons across students
- Bar charts of averages
- Grade range visualization
- Summary statistics

### 📋 All Students
- Table view of all students
- Export as CSV
- Download grades data

---

## Keyboard Shortcuts in Streamlit

- **Ctrl+C** - Stop the server (in terminal)
- **R** - Rerun app
- The app auto-updates existing browser window when code changes

---

## Browser Compatibility

Works on:
- ✅ Chrome
- ✅ Firefox
- ✅ Edge
- ✅ Safari

---

## Troubleshooting

### Problem: "streamlit command not found"
**Solution:** 
- Make sure you activated the virtual environment
- Or install streamlit: `pip install streamlit`

### Problem: "ModuleNotFoundError: No module named 'streamlit'"
**Solution:**
```powershell
pip install -r requirements.txt
```

### Problem: Port 8501 already in use
**Solution:**
```powershell
streamlit run app.py --server.port 8502
```

### Problem: Data disappears after refresh
**Note:** This is normal - Streamlit loads from the beginning on each interaction. To persist data, consider adding database integration.

---

## Deactivating Virtual Environment

When you're done, deactivate the virtual environment:

```powershell
deactivate
```

---

## Advanced Options

### Change the theme:

```powershell
streamlit run app.py --theme.primaryColor="#FF0000"
```

### Run in development mode:

```powershell
streamlit run app.py --logger.level=debug
```

### Access from other computers on your network:

```powershell
streamlit run app.py --server.address 0.0.0.0
```

Then access from another computer at: `http://YOUR_COMPUTER_IP:8501`

---

## Next Steps

1. Install Python (if needed)
2. Create virtual environment
3. Install requirements: `pip install -r requirements.txt`
4. Run: `streamlit run app.py`
5. Start managing grades through the web interface!

Enjoy your new web-based Student Grade Tracker! 🚀
