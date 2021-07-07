package projeto_1;
import edu.princeton.cs.algs4.SeparateChainingHashST;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.nio.file.DirectoryIteratorException;

public class GroupClass implements Serializable {
    private University university;
    private String name; //GroupClass name
    private Discipline discipline; //This groupclass discipline
    private Teacher teacher;
    private SeparateChainingHashST <Integer,Student> studentsGroupClass = new SeparateChainingHashST<>(); //All the students from this groupclass
    private Schedule schedule;

    /**
     * wirtes a groupclass to file
     * @param g groupclass to be writen
     * @param path path to file
     */
    public void writeGroupClassToFile(GroupClass g, String path) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(path, true)); //append true
            writer.write(g.getName());
            writer.write(";");
            writer.newLine();
            writer.close();
        }
        catch (IOException | DirectoryIteratorException x) {
            System.out.println(x);
        }
    }

    /**
     * prints all student in groupclass
     */
    public void printStudentInGrouclass() {
        if (this.studentsGroupClass.isEmpty()) {
            System.out.println("There are no Students in this GroupClass");
        } else {
            for (Integer di: this.studentsGroupClass.keys()) {
                System.out.println("GroupClass: " + this.getName() + ":" + this.studentsGroupClass.get(di));
            }
        }
    }

    /**
     * add student to groupclass
     * @param s student to be added
     */
    public void addStudent(Student s) {
        this.university.addStudent(s); //checks if the student is in the global student st
        if (this.studentsGroupClass.contains(s.getNumber())) {
            System.out.println("GroupClass - addStudent() - Student Already in this GroupClass: " + s);
            return;
        }
        this.studentsGroupClass.put(s.getNumber(),s);
        s.associateGroupclass(this);
    }

    /**
     * removes a student from groupclass
     * @param number number of student to be removed
     * @return student removed
     */
    public Student removeStudent(Integer number) {
            if(this.studentsGroupClass.contains(number)) {
                Student si = this.studentsGroupClass.get(number);
                System.out.println("GroupClass - removeStudent() - Student Removed: " + this.studentsGroupClass.get(number));
                this.studentsGroupClass.get(number).desassociateGroupClass(this.getName()); //Desassociating the groupclass from the student
                this.studentsGroupClass.delete(number); // desassociating the student from the groupclass
                return si;
        }
        return null;
    }

    /**
     * associates a teacher do groupclass
     * @param t teacher to be asscoaited
     */
    public void associateTeacher(Teacher t) {
        this.university.addTeacher(t);
        this.teacher = t;
        if (this.getSchedule() != null) {
            t.addSchedule(this.getSchedule());
        }
    }

    /**
     * desassciates a teacher from discpline
     */
    public void desasscoiateTeacher() {
        this.teacher.removeSchedule(this.getSchedule().getStartdate());
        this.teacher = null;
    }
    /**
     * prints teacher
     */
    public void printTeacher() {
        if(this.teacher != null) {
            System.out.println(this.teacher);
        }
        else {
            System.out.println("No Teacher in this GroupClass");
        }
    }

    /**
     * prints discipline
     */
    public void printDiscipline() {
        if(this.discipline != null) {
            System.out.println(this.discipline);
        }
        else {
            System.out.println("There isn't a discipline in this GroupClass");
        }
    }

    /**
     * adds a schedule
      * @param s schedule to be added
     */
    public void addSchedule(Schedule s) {
        if(this.teacher != null) {
            this.teacher.addSchedule(s);
        }
        this.setSchedule(s);
        this.getSchedule().setGroupClass(this);
    }

    /**
     * associates a discipline
     * @param d discipline to be associated
     */
    public void associateDisciplie(Discipline d) {
        if(this.getDiscipline() == null) {
            this.setDiscipline(d);
            d.associateGroupClassToDiscipline(this);
        }
    }


    /**
     * desassociates a discipline
     * @param name discipline name
     */
    public void desassociateDiscipline(String name) {
        if(this.discipline.getName().compareTo(name) == 0) {
            this.getDiscipline().desassociateGroupClass(this.getName());
            this.setDiscipline(null);

        }
    }

    @Override
    public String toString() {
        return  name ;
    }

    public Schedule getSchedule() {
        return schedule;
    }
    public void setSchedule(Schedule schedule) {
        this.schedule = schedule;
    }
    public GroupClass(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Teacher getTeacher() {
        return teacher;
    }
    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }
    public SeparateChainingHashST<Integer, Student> getStudentsGroupClass() {
        return studentsGroupClass;
    }
    public Discipline getDiscipline() {
        return discipline;
    }
    public void setDiscipline(Discipline discipline) {
        this.discipline = discipline;
    }

    public University getUniversity() {
        return university;
    }
    public void setUniversity(University university) {
        this.university = university;
    }
}