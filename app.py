"""
Student Grade Tracker - Streamlit Web Application
A modern web interface for managing student grades
"""

import streamlit as st
import pandas as pd
import plotly.graph_objects as go
import plotly.express as px
from datetime import datetime

# Set page configuration
st.set_page_config(
    page_title="Student Grade Tracker",
    page_icon="📚",
    layout="wide",
    initial_sidebar_state="expanded"
)

# Custom CSS for better styling
st.markdown("""
    <style>
    .main {
        padding-top: 0rem;
    }
    .metric-card {
        background-color: #f0f2f6;
        padding: 1.5rem;
        border-radius: 0.5rem;
        border-left: 5px solid #1f77b4;
    }
    </style>
    """, unsafe_allow_html=True)

# Initialize session state
if 'students' not in st.session_state:
    st.session_state.students = {}

# ============== Helper Functions ==============

class Student:
    """Class to represent a student and their grades"""
    
    def __init__(self, name, student_id):
        self.name = name
        self.id = student_id
        self.grades = []
    
    def add_grade(self, grade):
        """Add a grade to the student's record"""
        if 0 <= grade <= 100:
            self.grades.append(grade)
            return True
        return False
    
    def get_average(self):
        """Calculate average grade"""
        if not self.grades:
            return 0
        return sum(self.grades) / len(self.grades)
    
    def get_highest(self):
        """Get highest grade"""
        return max(self.grades) if self.grades else 0
    
    def get_lowest(self):
        """Get lowest grade"""
        return min(self.grades) if self.grades else 0
    
    def get_letter_grade(self):
        """Get letter grade based on average"""
        avg = self.get_average()
        if avg >= 90:
            return "A"
        elif avg >= 80:
            return "B"
        elif avg >= 70:
            return "C"
        elif avg >= 60:
            return "D"
        else:
            return "F"
    
    def to_dict(self):
        """Convert to dictionary for display"""
        return {
            'ID': self.id,
            'Name': self.name,
            'Grades': len(self.grades),
            'Average': round(self.get_average(), 2),
            'Highest': self.get_highest(),
            'Lowest': self.get_lowest(),
            'Letter': self.get_letter_grade()
        }


class GradeManager:
    """Manages all students and their grades"""
    
    def __init__(self, students_dict):
        self.students = students_dict
    
    def add_student(self, name, student_id):
        """Add a new student"""
        if student_id in self.students:
            return False
        self.students[student_id] = Student(name, student_id)
        return True
    
    def get_student(self, student_id):
        """Get a student by ID"""
        return self.students.get(student_id)
    
    def remove_student(self, student_id):
        """Remove a student"""
        if student_id in self.students:
            del self.students[student_id]
            return True
        return False
    
    def get_class_average(self):
        """Get class average"""
        if not self.students:
            return 0
        averages = [s.get_average() for s in self.students.values() if s.grades]
        return sum(averages) / len(averages) if averages else 0
    
    def get_top_student(self):
        """Get top performing student"""
        if not self.students:
            return None
        students_with_grades = [s for s in self.students.values() if s.grades]
        return max(students_with_grades, key=lambda s: s.get_average()) if students_with_grades else None
    
    def get_bottom_student(self):
        """Get bottom performing student"""
        if not self.students:
            return None
        students_with_grades = [s for s in self.students.values() if s.grades]
        return min(students_with_grades, key=lambda s: s.get_average()) if students_with_grades else None
    
    def get_grade_distribution(self):
        """Get distribution of letter grades"""
        distribution = {'A': 0, 'B': 0, 'C': 0, 'D': 0, 'F': 0}
        for student in self.students.values():
            if student.grades:
                distribution[student.get_letter_grade()] += 1
        return distribution
    
    def get_highest_average(self):
        """Get highest student average"""
        if not self.students:
            return 0
        averages = [s.get_average() for s in self.students.values() if s.grades]
        return max(averages) if averages else 0
    
    def get_lowest_average(self):
        """Get lowest student average"""
        if not self.students:
            return 0
        averages = [s.get_average() for s in self.students.values() if s.grades]
        return min(averages) if averages else 0


# ============== Main App ==============

def main():
    # Header
    st.markdown("# 📚 Student Grade Tracker")
    st.markdown("*A comprehensive platform for managing and analyzing student grades*")
    st.divider()
    
    # Create sidebar menu
    with st.sidebar:
        st.markdown("## Navigation")
        page = st.radio(
            "Select an option:",
            ["📊 Dashboard", "➕ Add Student", "📝 Add Grade", "👤 View Student", 
             "🗑️ Remove Student", "📈 Analytics", "📋 All Students"]
        )
    
    # Initialize GradeManager with session state
    manager = GradeManager(st.session_state.students)
    
    # ============== 📊 DASHBOARD ==============
    if page == "📊 Dashboard":
        st.markdown("## Dashboard Overview")
        
        if not st.session_state.students:
            st.info("👋 No students added yet. Start by adding a student!")
        else:
            # Key metrics
            col1, col2, col3, col4, col5 = st.columns(5)
            
            with col1:
                st.metric("Total Students", len(st.session_state.students))
            
            with col2:
                st.metric("Class Average", f"{manager.get_class_average():.2f}")
            
            with col3:
                st.metric("Highest Average", f"{manager.get_highest_average():.2f}")
            
            with col4:
                st.metric("Lowest Average", f"{manager.get_lowest_average():.2f}")
            
            with col5:
                total_grades = sum(len(s.grades) for s in st.session_state.students.values())
                st.metric("Total Grades", total_grades)
            
            st.divider()
            
            # Top and Bottom Performers
            col1, col2 = st.columns(2)
            
            with col1:
                st.subheader("🏆 Top Performer")
                top = manager.get_top_student()
                if top:
                    st.success(f"**{top.name}** (ID: {top.id})")
                    st.metric("Average", f"{top.get_average():.2f}", delta=f"Grade: {top.get_letter_grade()}")
                else:
                    st.info("No grades recorded yet")
            
            with col2:
                st.subheader("📉 Bottom Performer")
                bottom = manager.get_bottom_student()
                if bottom:
                    st.warning(f"**{bottom.name}** (ID: {bottom.id})")
                    st.metric("Average", f"{bottom.get_average():.2f}", delta=f"Grade: {bottom.get_letter_grade()}")
                else:
                    st.info("No grades recorded yet")
            
            st.divider()
            
            # Grade Distribution Chart
            st.subheader("📊 Grade Distribution")
            distribution = manager.get_grade_distribution()
            
            fig = go.Figure(data=[
                go.Bar(x=list(distribution.keys()), y=list(distribution.values()),
                       marker_color=['#2ecc71', '#3498db', '#f39c12', '#e67e22', '#e74c3c'])
            ])
            fig.update_layout(
                title="Students by Letter Grade",
                xaxis_title="Grade",
                yaxis_title="Number of Students",
                hovermode="x unified",
                height=400
            )
            st.plotly_chart(fig, use_container_width=True)
    
    # ============== ➕ ADD STUDENT ==============
    elif page == "➕ Add Student":
        st.markdown("## Add New Student")
        
        col1, col2 = st.columns(2)
        
        with col1:
            student_name = st.text_input("Student Name", placeholder="e.g., Alice Smith")
        
        with col2:
            student_id = st.text_input("Student ID", placeholder="e.g., S001")
        
        if st.button("➕ Add Student", type="primary", use_container_width=True):
            if not student_name or not student_id:
                st.error("❌ Please enter both name and ID!")
            elif manager.add_student(student_name, student_id):
                st.session_state.students = manager.students
                st.success(f"✅ Student '{student_name}' added successfully!")
                st.balloons()
            else:
                st.error(f"❌ A student with ID '{student_id}' already exists!")
    
    # ============== 📝 ADD GRADE ==============
    elif page == "📝 Add Grade":
        st.markdown("## Add Grade to Student")
        
        if not st.session_state.students:
            st.warning("⚠️ No students found. Please add a student first!")
        else:
            # Select student
            student_ids = list(st.session_state.students.keys())
            selected_id = st.selectbox("Select Student", student_ids, 
                                       format_func=lambda x: f"{st.session_state.students[x].name} (ID: {x})")
            
            # Enter grade
            grade = st.number_input("Grade (0-100)", min_value=0.0, max_value=100.0, step=0.5)
            
            if st.button("📝 Add Grade", type="primary", use_container_width=True):
                student = st.session_state.students[selected_id]
                if student.add_grade(grade):
                    st.success(f"✅ Grade {grade} added to {student.name}!")
                    st.balloons()
                else:
                    st.error("❌ Invalid grade!")
    
    # ============== 👤 VIEW STUDENT ==============
    elif page == "👤 View Student":
        st.markdown("## View Student Details")
        
        if not st.session_state.students:
            st.warning("⚠️ No students found!")
        else:
            student_ids = list(st.session_state.students.keys())
            selected_id = st.selectbox("Select Student", student_ids,
                                       format_func=lambda x: f"{st.session_state.students[x].name} (ID: {x})")
            
            student = st.session_state.students[selected_id]
            
            # Student info
            st.subheader(f"📌 {student.name}")
            
            col1, col2, col3, col4, col5 = st.columns(5)
            with col1:
                st.metric("ID", student.id)
            with col2:
                st.metric("Total Grades", len(student.grades))
            with col3:
                st.metric("Average", f"{student.get_average():.2f}")
            with col4:
                st.metric("Highest", f"{student.get_highest():.0f}")
            with col5:
                st.metric("Lowest", f"{student.get_lowest():.0f}")
            
            st.divider()
            
            if student.grades:
                col1, col2 = st.columns(2)
                
                with col1:
                    st.subheader("Grades List")
                    grades_df = pd.DataFrame({
                        'Grade': student.grades,
                        'Number': range(1, len(student.grades) + 1)
                    })
                    st.dataframe(grades_df, use_container_width=True, hide_index=True)
                
                with col2:
                    st.subheader("Grade Visualization")
                    fig = go.Figure(data=[
                        go.Scatter(y=student.grades, mode='lines+markers',
                                   name='Grades',
                                   line=dict(color='#3498db', width=3),
                                   marker=dict(size=10))
                    ])
                    fig.add_hline(y=student.get_average(), line_dash="dash", 
                                 line_color="red", annotation_text="Average")
                    fig.update_layout(
                        title="Student Grade Trend",
                        xaxis_title="Grade Number",
                        yaxis_title="Score",
                        height=400,
                        hovermode="x unified"
                    )
                    st.plotly_chart(fig, use_container_width=True)
                
                st.divider()
                st.markdown(f"**Letter Grade:** `{student.get_letter_grade()}`")
            else:
                st.info("ℹ️ No grades recorded for this student yet.")
    
    # ============== 🗑️ REMOVE STUDENT ==============
    elif page == "🗑️ Remove Student":
        st.markdown("## Remove Student")
        
        if not st.session_state.students:
            st.warning("⚠️ No students found!")
        else:
            student_ids = list(st.session_state.students.keys())
            selected_id = st.selectbox("Select Student to Remove", student_ids,
                                       format_func=lambda x: f"{st.session_state.students[x].name} (ID: {x})")
            
            student_name = st.session_state.students[selected_id].name
            
            if st.button("🗑️ Remove Student", type="secondary", use_container_width=True):
                if manager.remove_student(selected_id):
                    st.session_state.students = manager.students
                    st.success(f"✅ Student '{student_name}' removed successfully!")
                else:
                    st.error("❌ Failed to remove student!")
    
    # ============== 📈 ANALYTICS ==============
    elif page == "📈 Analytics":
        st.markdown("## Analytics & Reports")
        
        if not st.session_state.students:
            st.info("👋 No data to analyze yet. Add students and grades first!")
        else:
            # Create student data for visualization
            students_data = []
            for student in st.session_state.students.values():
                if student.grades:
                    students_data.append({
                        'Name': student.name,
                        'ID': student.id,
                        'Average': student.get_average(),
                        'Highest': student.get_highest(),
                        'Lowest': student.get_lowest(),
                        'Letter': student.get_letter_grade()
                    })
            
            if students_data:
                df = pd.DataFrame(students_data)
                
                col1, col2 = st.columns(2)
                
                with col1:
                    st.subheader("Student Averages")
                    fig = px.bar(df, x='Name', y='Average', color='Letter',
                                color_discrete_map={'A': '#2ecc71', 'B': '#3498db', 
                                                   'C': '#f39c12', 'D': '#e67e22', 'F': '#e74c3c'},
                                title="Average Grades by Student")
                    st.plotly_chart(fig, use_container_width=True)
                
                with col2:
                    st.subheader("Grade Range by Student")
                    fig = go.Figure()
                    fig.add_trace(go.Scatter(
                        x=df['Name'], y=df['Highest'],
                        name='Highest', mode='markers+lines',
                        marker=dict(size=10, color='green')
                    ))
                    fig.add_trace(go.Scatter(
                        x=df['Name'], y=df['Lowest'],
                        name='Lowest', mode='markers+lines',
                        marker=dict(size=10, color='red')
                    ))
                    fig.update_layout(title="Grade Range", hovermode="x unified")
                    st.plotly_chart(fig, use_container_width=True)
                
                st.divider()
                
                # Summary statistics
                st.subheader("Summary Statistics")
                col1, col2, col3 = st.columns(3)
                
                with col1:
                    st.metric("Class Average", f"{df['Average'].mean():.2f}")
                with col2:
                    st.metric("Highest Score", f"{df['Highest'].max():.0f}")
                with col3:
                    st.metric("Lowest Score", f"{df['Lowest'].min():.0f}")
            else:
                st.info("No grades recorded yet")
    
    # ============== 📋 ALL STUDENTS ==============
    elif page == "📋 All Students":
        st.markdown("## All Students")
        
        if not st.session_state.students:
            st.info("👋 No students found!")
        else:
            # Create table with all students
            students_list = [student.to_dict() for student in st.session_state.students.values()]
            df = pd.DataFrame(students_list)
            
            st.dataframe(df, use_container_width=True, hide_index=True)
            
            # Export options
            st.divider()
            col1, col2 = st.columns(2)
            
            with col1:
                csv = df.to_csv(index=False)
                st.download_button(
                    label="📥 Download as CSV",
                    data=csv,
                    file_name=f"grades_{datetime.now().strftime('%Y%m%d_%H%M%S')}.csv",
                    mime="text/csv"
                )
            
            with col2:
                # Summary stats
                st.metric("Total Students", len(st.session_state.students))


if __name__ == "__main__":
    main()
