package projeto_1;


import java.io.Serializable;

public class Schedule implements Serializable {
    private String dayoftheweek;
    private Date startdate;
    private Date enddate;
    private Teacher Teacher; //This will be the Teacher with this schedule
    private Classroom classroom;
    private boolean classroomOcupation = false; //true - ocupad , false - n ocupado
    private GroupClass groupClass; //This will be the GroupClass with this schedule

    /**
     * associates a classrom
     * @param c classrom to be associated
     */
    public void associateClassroom(Classroom c) {
        this.getGroupClass().getUniversity().addclassroom(c);
        this.setClassroom(c);
        c.addSchedule(this);
        c.getSchedule().get(this.getStartdate()).setClassroomOcupation(true); // on the associated setting the time of this schedule to occupied
    }

    /**
     * desassociates a classroom
     * @param number classroom number
     */
    public void desassociateClassroom(int number) {
        if(this.classroom.getNumber() == number) {
            this.classroom.removeSchedule(this.startdate);
            this.setClassroom(null);
        }
    }

    /**
     * prints a teacher
     */
    public void printTeacher() {
        if (this.getTeacher() != null) {
            System.out.println(this.Teacher);
        } else {
            System.out.println("There isn't a teacher in this Schedule");
        }
    }

    /**
     * prints a classroom
     */
    public void printClassroom() {
        if(this.getClassroom() != null) {
            System.out.println(this.classroom);
        }
        else {
            System.out.println("There isn't a classroom in this Schedule");
        }
    }
    public GroupClass getGroupClass() {
        return groupClass;
    }
    public Classroom getClassroom() {
        return classroom;
    }
    public void setClassroom(Classroom classroom) {
        this.classroom = classroom;
    }
    public void setGroupClass(GroupClass groupClass) {
        this.groupClass = groupClass;
    }
    public String getDayoftheweek() {
        return dayoftheweek;
    }
    public Date getStartdate() {
        return startdate;
    }
    public Date getEnddate() {
        return enddate;
    }
    public Teacher getTeacher() {
        return Teacher;
    }
    public void setTeacher(Teacher teacher) {
        Teacher = teacher;
    }
    public boolean isClassroomOcupation() {
        return classroomOcupation;
    }

    public void setClassroomOcupation(boolean classroomOcupation) {
        this.classroomOcupation = classroomOcupation;
    }
    public Schedule(String dayoftheweek, Date startdate, Date enddate) {
        this.dayoftheweek = dayoftheweek;
        this.startdate = startdate;
        this.enddate = enddate;
    }

    @Override
    public String toString() {
        return "Day Of the Week: " + dayoftheweek + '\'' +
                " StartDate: " + startdate +
                " EndDate: " + enddate;
    }
}