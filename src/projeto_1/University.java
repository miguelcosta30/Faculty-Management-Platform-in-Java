package projeto_1;
import edu.princeton.cs.algs4.SeparateChainingHashST;
import edu.princeton.cs.algs4.SequentialSearchST;
import java.io.Serializable;
import java.util.ArrayList;

public class University implements Serializable {
    private static String PATH = ".//data//remove.txt";
    private String name; // Univeristy Name
    private RedBlackBST_AED2<Integer, Classroom> classroom = new RedBlackBST_AED2<>(); // All the Classrooms in this University
    private SequentialSearchST<Integer, Student> students = new SequentialSearchST<>(); // All the Students in This University
    private SeparateChainingHashST<String, Teacher> teachers = new SeparateChainingHashST<>(); // All the Teachers in this Univeristy
    private SeparateChainingHashST<String, GroupClass> groupclass = new SeparateChainingHashST<>();

    /**
     * add's a groupclass to the university
     * @param g groupclass do add
     */
    public void addGroupClass(GroupClass g) {
        if (this.groupclass.contains(g.getName())) {
            System.out.println("University - addGroupClass() - GroupClass Already Exists: " + g);
            return;
        }
        this.groupclass.put(g.getName(), g);
        g.setUniversity(this);
    }

    /**
     * remove's a groupclass from the univeristy
     * @param name name of the groupclass to remove
     * @return
     */
    public GroupClass removeGroupClass(String name) {
        GroupClass g = this.groupclass.get(name);
        if (g != null) {
            System.out.println("Univeristy - removeGroupClass() - GroupClass removed: " + g.getName());
            this.groupclass.delete(name);
            g.setUniversity(null);
            return g;
        }
        return null;
    }

    /**
     * adds a student to the university
     * @param s student to add
     */
    public void addStudent(Student s) {
        if (this.students.contains(s.getNumber())) {
            System.out.println("University - addStudent() - Students Already Exists: " + s);
            return;
        }
        this.students.put(s.getNumber(), s);
    }

    /**
     * removes a student from university
     * @param s number of student to be removed
     * @return
     */
    public Student removeStudent(int s) {
        if (this.students.contains(s)) {
            System.out.println("University - removeStudent () - Student Removed: " + this.students.get(s));
            Student si = this.students.get(s);
            this.students.delete(s);
            return si;
        }
        return null;
    }

    /**
     * add's a classroom to the university
     * @param c classroom to add
     */
    public void addclassroom(Classroom c) {
        if(this.classroom.contains(c.getNumber())) {
            System.out.println("Classroom already exists " + c);
            return;
        }
        this.classroom.put(c.getNumber(),c);
    }

    /**
     * removes a classroom to be removed
     * @param number number of classroom to remove
     * @return clasroom removed
     */
    public Classroom removeclassroom(int number) {
        if (this.classroom.contains(number)) {
            Classroom ci = this.classroom.get(number);
            System.out.println("Classroom Removed: " + ci);
            this.classroom.delete(number);
            return ci;
        }
        return null;
    }

    /**
     * prints all the classrooms
     */
    public void printClassrooms() {
        if (this.classroom.isEmpty()) {
            System.out.println("There are no Classrooms");
            return;
        } else {
            for (Integer ci : this.classroom.keys()) {
                System.out.println("Classroom: " + this.classroom.get(ci));
            }
        }
    }

    /**
     * prints students
     */
    public void printStudents() {
        if (this.students.isEmpty()) {
            System.out.println("There are no Students in this Univeristy ");
            return;
        }
        for (Integer li : this.students.keys()) {
            System.out.println("Student: " + this.students.get(li));
        }
    }

    /**
     * adds a teacher to university
     * @param t teacher to be added
     */
    public void addTeacher(Teacher t) {
        if (this.teachers.contains(t.getUsername())) {
            return;
        }
        this.teachers.put(t.getUsername(), t);
    }

    /**
     * remove a teacher
     * @param username teacher username to be removed
     * @return
     */
    public Teacher removeTeacher(String username) {
        Teacher t = this.teachers.get(username);
        if (t != null) {
            System.out.println("University - removeTeacher() - Teacher removed: " + t);
            this.teachers.delete(username);
            return t;
        }
        return null;
    }

    /**
     * prints all teachers
     */
    public void printTeachers() {
        if (this.teachers.isEmpty()) {
            System.out.println("There are no Teachers in this University ");
            return;
        }
        for (String si : this.teachers.keys()) {
            System.out.println("University: Teacher: " + this.teachers.get(si));
        }
    }

    /**
     * prints groupclasses
     */
    public void printGroupClasses() {
        if (this.groupclass.isEmpty()) {
            System.out.println("There are no GroupClass in this University");
            return;
        }
        for (String si : this.groupclass.keys()) {
            System.out.println("GroupClass: " + this.groupclass.get(si));
        }
    }

    /**
     * removes a teacher from all mensions in every st
     * @param username teacher username to be removed
     */
    //This method will Remove Every Mension of this teacher in every ST.
    public void removeATeacher(String username) {
        Teacher t = this.teachers.get(username);
        if (t != null) { //Checks if the teacher is in the global teachers st
            for(String si : this.groupclass.keys()) { //Goes trough all the groupclasses
                if(this.groupclass.get(si).getTeacher() != null) { //If theres a teacher with this username it will be set to null
                    if (this.groupclass.get(si).getTeacher().getUsername().compareTo(username) == 0) {
                        this.groupclass.get(si).setTeacher(null); //teacher set to null
                    }
                }
            }
            for (String si : this.groupclass.keys()) {
                if(this.groupclass.get(si).getSchedule() != null) {
                if(this.groupclass.get(si).getSchedule().getTeacher() != null) {
                    if (this.groupclass.get(si).getSchedule().getTeacher().getUsername().compareTo(username) == 0) { //if the teachers is in any schedule
                        this.groupclass.get(si).getSchedule().setTeacher(null);
                    }
                }
                }
            }
            t.WriteTeacherToFile(t, PATH);
            this.teachers.delete(username);
            //AO FAZER DELETE AQUI METER NO FICHEIRO..............................
        }
    }

    /**
     * remove every mension of this student in every st
     * @param number number of student to be removed
     */
    public void removeAStudent(Integer number){ //exception because of write to file
        Student s = this.students.get(number);
        if (s != null) {
            for (String si : this.groupclass.keys()) {
                if (this.groupclass.get(si).getStudentsGroupClass().contains(number)) {
                    this.groupclass.get(si).getStudentsGroupClass().delete(number);
                }
            }
            s.WriteStudentToFile(s, PATH);
            this.students.delete(number);
        }
    }

    /**
     * removes a groupclass from every mension everywhere
     * @param name name of groupclass to be removed
     */
    public void removeAGroupClass(String name) {
        GroupClass g = this.groupclass.get(name);
        if (g != null) {
            //for the schedule
            for (String si : this.groupclass.keys()) {
                if(this.groupclass.get(si).getSchedule() != null) {
                    if (this.groupclass.get(si).getSchedule().getGroupClass() != null) {
                        if (this.groupclass.get(si).getSchedule().getGroupClass().getName().compareTo(name) == 0) { //checks if the groupclass is in any schedule
                            this.groupclass.get(si).getSchedule().setGroupClass(null);
                        }
                    }
                }
            }
            for (String si : this.groupclass.keys()) {
                if(this.groupclass.get(si).getDiscipline() != null) {
                    if (this.groupclass.get(si).getDiscipline().getGroupclass().contains(name)) { //checks if this groupclass is present in the discipline
                        this.groupclass.get(si).getDiscipline().getGroupclass().delete(name); //apaga a turma da disciplinea
                    }
                }
            }//for the students
            for (Integer i : this.students.keys()) {
                if(this.students.get(i).getGroupclass() !=null) {
                    if (this.students.get(i).getGroupclass().contains(name)) {
                        this.students.get(i).desassociateGroupClass(name);
                    }
                }
            }
            for (String si : this.teachers.keys()) {
                if(this.teachers.get(si).getGroupclass() != null) {
                    if (this.teachers.get(si).getGroupclass().contains(name)) {
                        this.teachers.get(si).desassociateTeacherGroupclass(name);
                    }
                }
            }
            g.writeGroupClassToFile(g, PATH);
            this.removeGroupClass(name);
        }
    }

    /**
     * searchs all the classrooms free between 2 dates
     * @param startDate startdate
     * @param endDate enddate
     */
    public ArrayList<Classroom> searchRoomsFreeBetweenDates(Date startDate, Date endDate) {
        ArrayList<Classroom> classrooms = new ArrayList<>();
        for (Integer i : this.classroom.keys()) { //This goes through all the classrooms
            for (Date di : this.classroom.get(i).getSchedule().keys()) { //This goes through all of the schedules of the rooms
                if ((this.classroom.get(i).getSchedule().get(di).getStartdate().compareTo(startDate) >= 0 && this.classroom.get(i).getSchedule().get(di).getStartdate().compareTo(endDate) <= 0) || (this.classroom.get(i).getSchedule().get(di).getEnddate().compareTo(startDate) >= 0 && this.classroom.get(i).getSchedule().get(di).getEnddate().compareTo(endDate) <= 0)  && !this.classroom.get(i).getSchedule().get(di).isClassroomOcupation()) {
                    classrooms.add(this.classroom.get(i));
                }
            }
        }
        return classrooms;
    }

    /**
     * searches all of the teachers groupclasses
     * @param username
     */
    public ArrayList<GroupClass> searchTeachersGroupClasses(String username) {
        ArrayList<GroupClass> groupClasses = new ArrayList<>();
        for (String si : this.teachers.keys()) { //Goes trough all the teachers
            if(this.teachers.get(si).getUsername().compareTo(username) == 0) {
                for (String s : this.teachers.get(si).getGroupclass().keys()) {
                    groupClasses.add(teachers.get(si).getGroupclass().get(s));
                }
            }
        }
        return groupClasses;
    }

    /**
     * removes a discipline from every st
     * @param name
     */
    public void removeADiscipline(String name)  {
        for (String si : this.groupclass.keys()) {
            if(this.groupclass.get(si).getDiscipline()!= null) {
                if (this.groupclass.get(si).getDiscipline().getName().compareTo(name) == 0) {
                    this.groupclass.get(si).getDiscipline().WriteDisciplineToFile(this.groupclass.get(si).getDiscipline(), PATH);
                    this.groupclass.get(si).setDiscipline(null); //para o ficheiro
                }
            }
        }
    }

    /**
     * removes a classroom from every st
     * @param number
     */
    public void removeAClassroom(int number) {
        Classroom c = this.classroom.get(number);
        if (c != null) {
            for (String si : this.groupclass.keys()) {
                if(this.groupclass.get(si).getSchedule() != null) {
                    if(this.groupclass.get(si).getSchedule().getGroupClass() != null) {
                        if (this.groupclass.get(si).getSchedule().getClassroom().getNumber() == number) {
                            this.groupclass.get(si).getSchedule().setClassroom(null);

                        }
                    }
                }
            }
            c.writeClassroomToFile(c, PATH);
            this.removeclassroom(number); //FICHEIRO---------
        }
    }

    /**
     * edits a student in every st
     * @param number number of student to be edited
     * @param nnumber new number if wanted
     * @param name new name
     * @param email new email
     * @param registration new registrartion
     */
    public void editStudent(int number, int nnumber, String name, String email, Date registration) {
        Student s = this.students.get(number);
        if (s != null) { // checks in the global st if the student is present.
            for (String si : this.groupclass.keys()) {
                if (this.groupclass.get(si).getStudentsGroupClass().contains(number)) { //if any of the class's contains the student we want to edit
                    if (number == nnumber) { //if the number isnt altered we can just replace the old info with the new one
                        this.groupclass.get(si).getStudentsGroupClass().get(number).setName(name);
                        this.groupclass.get(si).getStudentsGroupClass().get(number).setEmail(email);
                        this.groupclass.get(si).getStudentsGroupClass().get(number).setRegistration(registration);
                    } else {
                        this.groupclass.get(si).getStudentsGroupClass().delete(number);
                        s.setEmail(email);
                        s.setRegistration(registration);
                        s.setName(name);
                        s.setNumber(nnumber);
                        this.groupclass.get(si).getStudentsGroupClass().put(s.getNumber(), s);
                    }
                }
            }
            if (number == nnumber) {
                this.students.get(number).setName(name);
                this.students.get(number).setEmail(email);
                this.students.get(number).setRegistration(registration);
            } else {
                Student s1 = new Student(nnumber, registration, email, name,s.getX(),s.getY(),s.getFloor());
                for(String a : this.getStudents().get(number).getGroupclass().keys()) { //Isto vai percorrer todas as groupclasses que o aluno anteirormente tinha
                   s1.associateGroupclass(this.groupclass.get(a));
                   this.groupclass.get(a).removeStudent(nnumber);
                   this.groupclass.get(a).addStudent(s1);
                }
                    this.removeStudent(number);
                    this.addStudent(s1);
                }
            }
        }
    /**
     * prints all of the schedules of a classroom
     */
    public void printClassroomsSchedule() {
        if(!this.classroom.isEmpty()) {
            for(Integer i : this.classroom.keys()) {
                for (Date di : this.classroom.get(i).getSchedule().keys()) {
                    System.out.println(this.classroom.get(i));
                    System.out.println(this.classroom.get(i).getSchedule().get(di));
                }
            }
        }
    }

    /**
     * edits a teacher in every st
     * @param username username
     * @param nusername new username if wanted
     * @param name new name
     * @param email new email
     */
    public void editTeacher(String username, String nusername, String name, String email) {
        Teacher t = this.teachers.get(username);
        if (t != null) {
            for (String si : this.groupclass.keys()) {
                if (this.groupclass.get(si).getTeacher() != null) {
                    System.out.println(this.groupclass.get(si).getTeacher().getUsername());
                    if (this.groupclass.get(si).getTeacher().getUsername().compareTo(username) == 0) {
                        if (username.compareTo(nusername) == 0) {
                            this.groupclass.get(si).getTeacher().setName(name);
                            this.groupclass.get(si).getTeacher().setEmail(email);
                        } else {
                            t.setUsername(nusername);
                            t.setEmail(email);
                            t.setName(name);
                            this.groupclass.get(si).setTeacher(null);
                            this.groupclass.get(si).setTeacher(t);
                        }
                    }
                }
            }
            for (String si : this.groupclass.keys()) {
                if (this.groupclass.get(si).getSchedule() != null) {
                    if (this.groupclass.get(si).getSchedule().getTeacher() != null)
                        if (this.groupclass.get(si).getSchedule().getTeacher().getUsername().compareTo(username) == 0) {
                            if (username.compareTo(nusername) == 0) {
                                this.groupclass.get(si).getSchedule().getTeacher().setName(name);
                                this.groupclass.get(si).getSchedule().getTeacher().setEmail(email);
                            } else {
                                t.setEmail(email);
                                t.setName(name);
                                t.setUsername(nusername);
                                this.groupclass.get(si).getSchedule().setTeacher(null);
                                this.groupclass.get(si).getSchedule().setTeacher(t);
                            }
                        }
                }
            }
            if (username.compareTo(nusername) == 0) {
                this.teachers.get(username).setName(name);
                this.teachers.get(username).setEmail(email);
            } else {
                Teacher t1 = new Teacher(name, email, nusername,t.getX(),t.getY(),t.getFloor());
                if(this.getTeachers().get(username) != null) {
                    for (String a : this.getTeachers().get(username).getGroupclass().keys()) {
                        t1.associateTeacher2GroupClass(this.getTeachers().get(username).getGroupclass().get(a));
                        this.groupclass.get(a).setTeacher(null);
                        this.groupclass.get(a).setTeacher(t1);

                    }
                }
                this.removeTeacher(username);
                this.addTeacher(t1);
            }
        }
    }

    /**
     * edits a groupclass
     * @param name name to be edited
     * @param nnmae new name
     */
    public void editGroupClass(String name, String nnmae) {
        GroupClass g = this.groupclass.get(name);
        if (g != null) { //for the discipline
            //for the schedule
            for (String si : this.groupclass.keys()) {
                if (this.groupclass.get(si).getSchedule() != null) {
                    if (this.groupclass.get(si).getSchedule().getGroupClass() != null) {
                        if (this.groupclass.get(si).getSchedule().getGroupClass().getName().compareTo(name) == 0) { //checks if the groupclass is in any schedule
                            if (name.compareTo(nnmae) != 0) { // se os nomes forem diferentes
                                g.setName(nnmae);
                                this.groupclass.get(si).getSchedule().setGroupClass(null);
                                this.groupclass.get(si).getSchedule().setGroupClass(g);
                            }
                        }
                    }
                }
            }
            for (String si : this.groupclass.keys()) {
                if (this.groupclass.get(si).getDiscipline() != null) {
                    if (this.groupclass.get(si).getDiscipline().getGroupclass() != null) {
                        if (this.groupclass.get(si).getDiscipline().getGroupclass().contains(name)) {
                            if (name.compareTo(nnmae) != 0) {
                                g.setName(nnmae);
                                this.groupclass.get(si).getDiscipline().getGroupclass().delete(name);
                                this.groupclass.get(si).getDiscipline().getGroupclass().put(g.getName(), g);
                            }
                        }
                    }
                }
            }
            for (Integer i : this.students.keys()) {
                if (this.students.get(i).getGroupclass() != null) {
                    if (this.students.get(i).getGroupclass().contains(name)) {
                        if (name.compareTo(nnmae) != 0) {
                            g.setName(nnmae);
                            this.students.get(i).getGroupclass().delete(name);
                            this.students.get(i).getGroupclass().put(g.getName(), g);
                        }
                    }
                }
            }
            for (String si : this.teachers.keys()) {
                if (this.teachers.get(si).getGroupclass() != null) {
                    if (this.teachers.get(si).getGroupclass().contains(name)) {
                        if (name.compareTo(nnmae) != 0) {
                            g.setName(nnmae);
                            this.teachers.get(si).getGroupclass().delete(name);
                            this.teachers.get(si).getGroupclass().put(g.getName(), g);
                        }
                    }
                }
            }
            if (name.compareTo(nnmae) != 0) {
                GroupClass g1 = new GroupClass(nnmae);
                if (this.groupclass.get(name) != null) {
                    g1.setDiscipline(this.groupclass.get(name).getDiscipline());
                    this.groupclass.get(name).getDiscipline().desassociateGroupClass(name);
                    this.groupclass.get(name).getDiscipline().associateGroupClassToDiscipline(g1);
                }
                this.removeGroupClass(name);
                this.addGroupClass(g1);
            }
        }
    }

        /**
         * edits a discipline
         * @param name name do be edited
         * @param nname new name if wanted
         */
        public void editDiscipline (String name, String nname){
            Discipline d = new Discipline(nname);
            for (String si : this.groupclass.keys()) {
                if (this.groupclass.get(si).getDiscipline() != null) {
                    if (this.groupclass.get(si).getDiscipline().getName().compareTo(name) == 0) {
                        if (name.compareTo(nname) == 0) {
                            this.groupclass.get(si).getDiscipline().setName(name);
                        } else {
                            this.groupclass.get(si).setDiscipline(null);
                            this.groupclass.get(si).setDiscipline(d);
                        }
                    }
                }
            }
        }


    /**
     * searchs all teachers for a discipline
     * @param name discipline name
     */
    public ArrayList<Teacher> searchAllTeachersDiscipline(String name) {
        ArrayList<Teacher>teachers1 = new ArrayList<>();
        for (String si : this.groupclass.keys()) {
            if(this.groupclass.get(si).getDiscipline() != null) {
                if (this.groupclass.get(si).getDiscipline().getName().compareTo(name) == 0) {
                    System.out.println(this.groupclass.get(si).getTeacher());
                    teachers1.add(this.groupclass.get(si).getTeacher());
                }
            }
        }
        return teachers1;
    }

    /**
     * searches a classroom by number of sockets
     * @param sockets
     */
    public ArrayList<Classroom> searchClassroomsBySockets(int sockets) {
        ArrayList<Classroom> classrooms = new ArrayList<>();
        if (!this.classroom.isEmpty()) {
            for (int i : this.classroom.keys()) {
                if (this.classroom.get(i).getSockets() == sockets) {
                    System.out.println(this.classroom.get(i));
                    classrooms.add(this.classroom.get(i));
                }
            }
        }
        return classrooms;
    }

    /**
     * searchs classroom by floor
     * @param floor
     */
    public ArrayList<Classroom>  searchClassroomsByLevel(int floor) {
        ArrayList<Classroom> classrooms = new ArrayList<>();
        if (!this.classroom.isEmpty()) {
            for (int i : this.classroom.keys()) {
                if (this.classroom.get(i).getLevel() == floor) {
                    System.out.println(this.classroom.get(i));
                    classrooms.add(this.classroom.get(i));
                }
            }
        }
        return classrooms;
    }

    /**
     * searchs classrooms occupied or not
     * @param b
     */
    public ArrayList<Classroom> searchClassroomsByOcupation(boolean b) {
        ArrayList<Classroom> classrooms = new ArrayList<>();
        if (!this.classroom.isEmpty()) {
            for (int i : this.classroom.keys()) {
                for (Date di : this.classroom.get(i).getSchedule().keys()) {
                    if (this.classroom.get(i).getSchedule().get(di).isClassroomOcupation() == b) {
                        System.out.println(this.classroom.get(i));
                        classrooms.add(this.classroom.get(i));
                    }
                }
            }
        }
        return classrooms;
        }

    /**
     * edits a classroom
     * @param number number
     * @param nnumber new number
     * @param sockets new sockets
     * @param floor new floor
     */
    public void editClassroom(int number, int nnumber, int sockets, int floor) {
        Classroom c = this.classroom.get(number);
        if (c != null) {
            c.setSockets(sockets);
            c.setLevel(floor);
            c.setNumber(nnumber);
            for (String si : this.groupclass.keys()) {
                if (this.groupclass.get(si).getSchedule() != null) {
                    if (this.groupclass.get(si).getSchedule().getClassroom() != null) {
                        if (this.groupclass.get(si).getSchedule().getClassroom().getNumber() == number) {
                            if (number == nnumber) {
                                this.groupclass.get(si).getSchedule().getClassroom().setLevel(floor);
                                this.groupclass.get(si).getSchedule().getClassroom().setSockets(sockets);
                            } else {
                                this.groupclass.get(si).getSchedule().setClassroom(null);
                                this.groupclass.get(si).getSchedule().setClassroom(c);
                            }
                        }
                    }
                }
            }
            this.removeclassroom(number);
            this.addclassroom(c);
        }
    }
    public Student searchStudent(int s) {
        return this.students.get(s);
    }
    public Discipline searchDiscipline(String dname) {
        for(String name : this.getGroupclass().keys()) {
            if(this.getGroupclass().get(name).getDiscipline().getName().compareTo(dname) == 0) {
                return this.getGroupclass().get(name).getDiscipline();
            }
        }
        return null;
    }
    public ArrayList<Student> getAllStudents() {
        ArrayList <Student> studentArrayList = new ArrayList<>();
        if(!students.isEmpty()) {
            for (Integer i : students.keys()) {
                studentArrayList.add(students.get(i));
            }
        }
        return studentArrayList;
    }

    public ArrayList<Teacher> getAllTeachers() {
        ArrayList<Teacher> teacherArrayList = new ArrayList<>();
        if(!teachers.isEmpty()) {
            for(String s: teachers.keys()) {
                teacherArrayList.add(teachers.get(s));
            }
        }
        return teacherArrayList ;
    }
    /**
     * goes trought the students schedule and the teachers appointment schedule and see if the student can go
     * @param username teachers username
     * @param snumber students number
     */
    /*
   public void appointmentFreeSchedule(String username, int snumber) {
        Student s = this.students.get(snumber);
        Teacher t = this.teachers.get(username);
        for(String si : s.getGroupclass().keys()) { //Going through all the groupclasses of this student
            if(t.getAppointment() != null) {
                if(s.getGroupclass().get(si).getSchedule() != null) {
                    if (t.getAppointment().getDayoftheweek().compareTo(s.getGroupclass().get(si).getSchedule().getDayoftheweek()) == 0) {
                        if ((s.getGroupclass().get(si).getSchedule().getEnddate().compareHourTo(t.getAppointment().getStartdate()) >= 0 && t.getAppointment().getEnddate().compareHourTo(s.getGroupclass().get(si).getSchedule().getEnddate()) >= 0) ||())
                    }
                }
            }
        }
   }
*/

    /**
     * shows what is happening in the university in that instance
     */
    public void now() {
        Date now = new Date(); //Gets the date of today
        for (String si : this.groupclass.keys()) {
            GroupClass g = this.groupclass.get(si);
            if(g.getSchedule() != null) {
                if (now.compareTo(g.getSchedule().getStartdate()) >= 0 && now.compareTo(g.getSchedule().getEnddate()) <= 0) {
                    System.out.println(this.groupclass.get(si));
                    System.out.println("Classroom " + this.groupclass.get(si).getSchedule().getClassroom() + " Occupied: " + this.groupclass.get(si).getSchedule().isClassroomOcupation() + " " + this.groupclass.get(si).getDiscipline() + " " + this.groupclass.get(si).getTeacher());
                }
           }
        }
    }

    public Teacher searchTeacher(String username) {
        return this.teachers.get(username);
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public University(String name) {
        this.name = name;
    }
    public SequentialSearchST<Integer, Student> getStudents() {
        return students;
    }
    public SeparateChainingHashST<String, Teacher> getTeachers() {
        return teachers;
    }
    @Override
    public String toString() {
        return name;
    }
    public RedBlackBST_AED2<Integer, Classroom> getClassroom() {
        return classroom;
    }
    public SeparateChainingHashST<String, GroupClass> getGroupclass() {
        return groupclass;
    }
}


