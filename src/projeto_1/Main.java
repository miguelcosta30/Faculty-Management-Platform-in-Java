package projeto_1;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.SeparateChainingHashST;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Main {
    private static String PATH = ".//data//remove.txt";
    public static void main(String[] args) throws IOException {
        SeparateChainingHashST<String, University> universityST = new SeparateChainingHashST<>();
        SeparateChainingHashST<Date, Schedule> scheduleST = new SeparateChainingHashST<>();

        readGroupClassFromFile(".//data//groupclass.txt",universityST);
        readStudentFromFile(universityST, ".//data//students.txt");
        readTeacherFromFile(".//data//teacher.txt",universityST);
        readClassroomFromFile(".//data//classroom.txt",universityST);
        readSchedulesFromFile(universityST,scheduleST,".//data//schedule.txt");
        readDisciplineFromFile(universityST,".//data//discipline.txt");

        //testEdit(groupclassST,universityST);
        //RequisitoR5(teacherST,groupclassST,universityST);
        //requisitoR8(groupclassST,universityST);
        University u = universityST.get("UFP");
        u.printStudents();
        u.printGroupClasses();
        u.printClassrooms();
        u.printClassroomsSchedule();
        System.out.println(u.getGroupclass().get("BHT").getDiscipline());
        printTeachersGroupClass(universityST);
        //saveTXT(".//data//students.txt",".//data//teacher.txt",".//data//discipline.txt",".//data//schedule.txt",".//data//groupclass.txt",".//data//classroom.txt",universityST);
    }

    /**
     * tests the R5 requisit
     * @param teacherST teachers st
     * @param groupclassST groupclass ST
     * @param universityST university ST
     * @throws IOException
     */
    private static void RequisitoR5(SeparateChainingHashST<String, Teacher> teacherST ,SeparateChainingHashST<String, GroupClass> groupclassST,SeparateChainingHashST<String, University> universityST ) throws IOException {
        /*GroupClass g1 = groupclassST.get("BHT"); //Get the GroupClass that we introduced from reading the file
        GroupClass g2 = groupclassST.get("BHU");
        University u = universityST.get(g1.getUniversity().getName());

        System.out.println(g1);
        g1.printTeacher();
        g1.printStudentInGrouclass();

        Date startdate = new Date(10,2,2020,16,30);
        Date enddate = new Date(10,2,2020,18,30);
        Discipline d = new Discipline("LP2");
        Schedule s = new Schedule("Segunda-Feira",startdate,enddate);
        Classroom c = new Classroom(305,(short)10,3);

        u.getGroupclass().get(g1.getName()).addSchedule(s);
        u.getGroupclass().get(g1.getName()).setDiscipline(d);
        u.getGroupclass().get(g1.getName()).printDiscipline();
        u.getGroupclass().get(g1.getName()).getSchedule().associateClassroom(c);
        System.out.println("------------------------//------------------------");
        u.getGroupclass().get(g1.getName()).printTeacher();
        u.getGroupclass().get(g1.getName()).getUniversity().printTeachers();
        s.printTeacher();

        System.out.println("------------------------//------------------------");

        u.removeATeacher("ricsilv");
        s.printTeacher();
        u.getGroupclass().get(g1.getName()).printTeacher();
        u.getGroupclass().get(g1.getName()).getUniversity().printTeachers();
        System.out.println("------------------------//------------------------");
/*
        u.removeAStudent(38099);
        g1.printStudentInGrouclass();
        g2.printStudentInGrouclass();

        System.out.println("------------------------//------------------------");
        u.removeAGroupClass("BHT");
        u.printGroupClasses();
        teacherST.get("ricsilv").printGroupclasses();
        System.out.println("------------------------//------------------------");

        System.out.println("------------------------//------------------------");
        u.removeADiscipline("LP2");
        g1.printDiscipline();
        System.out.println("------------------------//------------------------");
        u.removeAClassroom(305);
        u.getGroupclass().get(g1.getName()).getSchedule().printClassroom();
         */


    }

    /**
     * testing the edit functionallity
     * @param universityST university ST
     */
    private static void testEdit(SeparateChainingHashST<String, University> universityST) {
      /*  GroupClass g1 = groupclassST.get("BHA"); //Get the GroupClass that we introduced from reading the file
        GroupClass g2 = groupclassST.get("BHT");
        University u = universityST.get(g1.getUniversity().getName());

        System.out.println(g1);
        g1.printTeacher();
        g1.printStudentInGrouclass();

        Date startdate = new Date(10,2,2020,16,30);
        Date enddate = new Date(10,2,2020,18,30);
        Discipline d = new Discipline("LP2");
        Schedule s = new Schedule("Segunda-Feira",startdate,enddate);
        Classroom c = new Classroom(305,(short)10,3);

        u.getGroupclass().get(g1.getName()).addSchedule(s);
        u.getGroupclass().get(g1.getName()).setDiscipline(d);
        u.getGroupclass().get(g1.getName()).printDiscipline();
        u.getGroupclass().get(g1.getName()).getSchedule().associateClassroom(c);

       /* System.out.println("------------------------//------------------------");
        //u.editTeacher("josped","josped","José Pedro", "josep@edu.ufp.pt"); //username wont change
        u.editTeacher("josped","josepedr", "José Pedro","josep@edu.ufp.pt"); //changes the username
        s.printTeacher();
        u.getGroupclass().get(g1.getName()).printTeacher();
        u.printTeachers();
       System.out.println("------------------------//------------------------");
        */
        /*System.out.println("------------------------//------------------------");
        u.editStudent(38099,38099,"José Rodrigues","38098@edu.ufp.pt",enddate);
        u.editStudent(38099,38098,"José Rodrigues","38098@edu.ufp.pt",enddate);
        u.printStudents();
        g1.printStudentInGrouclass();
        g2.printStudentInGrouclass();
       System.out.println("------------------------//------------------------");
        */
       /* u.editClassroom(305,205,11,2);
        u.getGroupclass().get(g1.getName()).getSchedule().printClassroom();
        u.printClassrooms();
        */
        /*u.editDiscipline("LP2","LP1",6,(short)2,(short)1);
        g1.printDiscipline();

        System.out.println("------------------------//------------------------");
        u.editGroupClass("BHA","BHJ");
        u.printGroupClasses();
        System.out.println();
        u.getGroupclass().get("BHJ").getTeacher().printGroupclasses();
        */
    }

    /**
     * teste function for Requisito8 e outros
     * @param universityST university ST
     * @throws IOException
     */
    private static void requisitoR8(SeparateChainingHashST<String, University> universityST) throws IOException {
        //All the teachers from a UC
        /* GroupClass g1 = groupclassST.get("BHA"); //Get the GroupClass that we introduced from reading the file
        GroupClass g2 = groupclassST.get("BHT");
        University u = universityST.get(g1.getUniversity().getName());

        Discipline d = new Discipline("LP2");

        Classroom c = new Classroom(305,(short)10,3);
        Classroom c1 = new Classroom(205,(short)5,2);
        Classroom c2 = new Classroom(104,(short)10,(short)1);
        u.addclassroom(c1);

        Date startdate = new Date(11,2,2020,16,30);
        Date enddate = new Date(11,2,2020,18,30);
        Schedule s = new Schedule("Segunda-Feira",startdate,enddate);

        Date d1 = new Date(6,5,2020,11,11);
        Date d2 = new Date(6,5,2020,14,30);
        Schedule s1 = new Schedule("Sexta-Feira",d1,d2);

        u.getGroupclass().get(g2.getName()).addSchedule(s1);
        u.getGroupclass().get(g2.getName()).getSchedule().associateClassroom(c2);
        System.out.println("------------------------//------------------------");
        //u.getGroupclass().get(g2.getName()).addSchedule(s);
        //u.getGroupclass().get(g2.getName()).getSchedule().associateClassroom(c1);
        u.getGroupclass().get(g1.getName()).addSchedule(s);
        u.getGroupclass().get(g1.getName()).setDiscipline(d);
        u.getGroupclass().get(g2.getName()).setDiscipline(d);
        u.getGroupclass().get(g1.getName()).getSchedule().associateClassroom(c);
        System.out.println("------------------------//------------------------");
        //System.out.println("------------------------//------------------------");
        //u.searchAllTeachersDiscipline("LP2");
        //System.out.println("------------------------//------------------------");
        //u.searchClassroomsByLevel(2);
        //u.searchClassroomsBySockets(10);
        //u.searchClassroomsByOcupation(true);
        //u.searchRoomsFreeBetweenDates(d1,d2);
        //u.searchTeachersGroupClasses("ricsilv");
        //u.now();
*/
    }

    private static void readGroupClassFromFile(String path, SeparateChainingHashST<String, University> universityST) {
        In in = new In(path);
        in.readLine();
        while(!in.isEmpty()) {
            String line = in.readLine();
            String [] fields = line.split(";");
            GroupClass g = new GroupClass(fields[0]);
            University u = new University(fields[1]);
            Discipline d = new Discipline(fields[2]);
            if(!universityST.contains(u.getName())) { //Se esta univerisdade não existir na ST das universidades
                universityST.put(u.getName(),u); //Vou adicionar esta universidade
                universityST.get(u.getName()).addGroupClass(g);//
                g.setUniversity(universityST.get(u.getName()));
                g.associateDisciplie(d);
                d.associateGroupClassToDiscipline(g);
            }
            else {
                universityST.get(u.getName()).addGroupClass(g);
                g.setUniversity(universityST.get(u.getName()));
                g.associateDisciplie(d);
                d.associateGroupClassToDiscipline(g);
            }
        }
    }
    private static void readDisciplineFromFile(SeparateChainingHashST <String, University> universitST, String path) {
        In in = new In(path);
        in.readLine();
        while(!in.isEmpty()) {
            String line = in.readLine();
            String [] fields = line.split(";");
            Discipline d = new Discipline(fields[0]);
            String [] f = fields[1].split("-");
            for (String value : f) {
                GroupClass g = new GroupClass(value);
                for (String s : universitST.keys()) {
                    if (universitST.get(s).getGroupclass().contains(g.getName())) {
                        g.associateDisciplie(d);
                        d.associateGroupClassToDiscipline(g);
                    }
                }
            }
        }
    }
    private static void readStudentFromFile(SeparateChainingHashST<String, University> universityST, String path) {
        In in = new In(path);
        in.readLine();
        while (!in.isEmpty()) {
            String line = in.readLine();
            String[] fields = line.split(";");
            String name = fields[0];
            String number = fields[1];
            String email = fields[2];
            String[] dateString = fields[3].split(" ");
            String[] dateStringaux = dateString[0].split("/");
            String[] timeString = dateString[1].split(":");
            String[] GroupClassList = fields[5].split("-");
            String[] x = fields[4].split(" ");
            Date date = new Date(Integer.parseInt(dateStringaux[0]), Integer.parseInt(dateStringaux[1]), Integer.parseInt(dateStringaux[2]), Integer.parseInt(timeString[0]), Integer.parseInt(timeString[1]));
            Student s = new Student(Integer.parseInt(number), date, email, name,Double.parseDouble(x[0]),Double.parseDouble(x[1]),Integer.parseInt(x[2]));
            for (String info : GroupClassList) {
                for (String si : universityST.keys()) {
                    for(String sl : universityST.get(si).getGroupclass().keys()) {
                        GroupClass g = universityST.get(si).getGroupclass().get(sl);
                        if (g.getName().compareTo(info) == 0) {
                            g.addStudent(s);
                            s.associateGroupclass(g);
                        }
                    }
                }
            }
        }
    }

    private static void readTeacherFromFile( String path, SeparateChainingHashST<String, University> universityST) {
        In in = new In(path);
        in.readLine();
        while (!in.isEmpty()) {
            String line = in.readLine();
            String[] fields = line.split(";");
            String name = fields[0];
            String email = fields[1];
            String username = fields[2];
            String[] GroupClassList = fields[4].split("-");
            String[] d = fields[3].split(" ");
            Teacher t = new Teacher(name, email, username,Double.parseDouble(d[0]),Double.parseDouble(d[1]),Integer.parseInt(d[2]));
            for (String info : GroupClassList) {
                for (String si : universityST.keys()) {
                    for(String s : universityST.get(si).getGroupclass().keys()) {
                        GroupClass g = universityST.get(si).getGroupclass().get(s);
                        if (g.getName().compareTo(info) == 0) {
                            g.associateTeacher(t);
                            t.associateTeacher2GroupClass(g);
                        }
                    }
                }
            }
        }
    }

    private static void printTeachersGroupClass(SeparateChainingHashST<String, University> universityST) {
        if(!universityST.isEmpty()) {
            for(String s : universityST.keys()) {
                if(!universityST.get(s).getGroupclass().isEmpty()) {
                    for (String si : universityST.get(s).getGroupclass().keys()) {
                        GroupClass c = universityST.get(s).getGroupclass().get(si);
                        System.out.println(c);
                        c.printTeacher();
                        System.out.println();
                    }
                }
            }
        }
    }

    /**
     * reads a classroom from file
     * @param path path to classroom file
     * @param universityST  where classroom is going to be stored
     */
    private static void readClassroomFromFile(String path,SeparateChainingHashST <String, University> universityST) {
        In in = new In(path);
        in.readLine();
        while (!in.isEmpty()) {
            String line = in.readLine();
            String[] fields = line.split(";");
            String number = fields[0];
            String sockets = fields[1];
            String level = fields[2];
            University u = new University(fields[3]);
            Classroom c = new Classroom(Integer.parseInt(number), Short.parseShort(sockets), Integer.parseInt(level));
            if(!universityST.contains(u.getName())) {
                universityST.put(u.getName(), u);
            }
            if(!universityST.get(u.getName()).getClassroom().contains(c.getNumber())) {
                universityST.get(u.getName()).getClassroom().put(c.getNumber(),c);
            }
        }
    }

    /**
     * reads a schedule from a file
     * @param universityST to save the schedule, teacher, discipline
     * @param scheduleST to save de scheduke
     * @param path path to schedule file
     */
    private static void readSchedulesFromFile( SeparateChainingHashST<String, University> universityST ,SeparateChainingHashST<Date, Schedule> scheduleST, String path) { //VOLTAR AQUI AMANHA
        In in = new In(path);
        in.readLine();
        while(!in.isEmpty()) {
            String line = in.readLine();
            String [] fields = line.split(";");
            String[] dateAux = fields[0].split("/");
            String[] hourAux = fields[1].split (":");
            String[] dateAux2 = fields[2].split("/");
            String[] hourAux2 = fields[3].split(":");
            Date d1 = new Date (Integer.parseInt(dateAux[0]),Integer.parseInt(dateAux[1]),Integer.parseInt(dateAux[2]),Integer.parseInt(hourAux[0]), Integer.parseInt(hourAux[1]));
            Date d2 = new Date (Integer.parseInt(dateAux2[0]),Integer.parseInt(dateAux2[1]),Integer.parseInt(dateAux2[2]),Integer.parseInt(hourAux2[0]), Integer.parseInt(hourAux2[1]));
            Schedule s = new Schedule(fields[4],d1,d2);
            scheduleST.put(s.getStartdate(),s);
            int number = Integer.parseInt(fields[5]); //Sala
            GroupClass g = new GroupClass(fields[6]); //Turma
            University u = new University(fields[8]);
            String teacher = fields[7];
            u.addGroupClass(g);
            g.setUniversity(u);
            if(!universityST.contains(u.getName())) {
                universityST.put(u.getName(),u);
            }
            else {
                if(universityST.get(u.getName()).getTeachers().contains(teacher) && universityST.get(u.getName()).getGroupclass().contains(g.getName()) && universityST.get(u.getName()).getClassroom().contains(number)) {
                    universityST.get(u.getName()).getTeachers().get(teacher).associateTeacher2GroupClass(g);
                    universityST.get(u.getName()).getGroupclass().get(g.getName()).addSchedule(s);
                    universityST.get(u.getName()).getGroupclass().get(g.getName()).associateTeacher(universityST.get(u.getName()).getTeachers().get(teacher));
                    universityST.get(u.getName()).getClassroom().get(number).addSchedule(s);
                    s.associateClassroom(universityST.get(u.getName()).getClassroom().get(number));
                    s.setGroupClass(g);
                    s.setTeacher(universityST.get(u.getName()).getGroupclass().get(g.getName()).getTeacher());
                    g.associateTeacher(universityST.get(u.getName()).getTeachers().get(teacher));
            }
            }
        }
    }

    /**
     * saves all the information to txt files
     * @param std path to student file
     * @param tch   path to teacher file
     * @param dis path do discipline file
     * @param sch path do schedule file
     * @param group path do groupclass file
     * @param cls path to classroom file
     * @param universityST where the info is to get saved
     */
    private static void saveTXT(String std, String tch, String dis, String sch, String group, String cls,SeparateChainingHashST<String, University> universityST) {
        try {
            for(String si : universityST.keys()) {
                if(!universityST.get(si).getTeachers().isEmpty()) {
                    for(String s : universityST.get(si).getTeachers().keys()) {
                        BufferedWriter writer = new BufferedWriter(new FileWriter(tch, true)); //append true
                        writer.write(universityST.get(si).getTeachers().get(s).getName());
                        writer.write(";");
                        writer.write(universityST.get(si).getTeachers().get(s).getEmail());
                        writer.write(";");
                        writer.write(universityST.get(si).getTeachers().get(s).getUsername());
                        writer.write(";");
                        if(!universityST.get(si).getTeachers().get(s).getGroupclass().isEmpty()) {
                            for (String sa : universityST.get(si).getTeachers().get(s).getGroupclass().keys()) {
                                writer.write(universityST.get(si).getTeachers().get(s).getGroupclass().get(sa).getName());
                                writer.write("-");
                            }
                        }
                        writer.newLine();
                        writer.close();
                    }
                }
                if(!universityST.get(si).getStudents().isEmpty()) {
                    for(Integer n : universityST.get(si).getStudents().keys()) {
                        BufferedWriter writer = new BufferedWriter(new FileWriter(std, true)); //append true
                        writer.write(universityST.get(si).getStudents().get(n).getName());
                        writer.write(";");
                        writer.write(String.valueOf(universityST.get(si).getStudents().get(n).getNumber()));
                        writer.write(";");
                        writer.write(universityST.get(si).getStudents().get(n).getEmail());
                        writer.write(";");
                        writer.write(String.valueOf(universityST.get(si).getStudents().get(n).getRegistration().getDay()));
                        writer.write("/");
                        writer.write(String.valueOf(universityST.get(si).getStudents().get(n).getRegistration().getMonth()));
                        writer.write("/");
                        writer.write(String.valueOf(universityST.get(si).getStudents().get(n).getRegistration().getYear()));
                        writer.write(" ");
                        writer.write(String.valueOf(universityST.get(si).getStudents().get(n).getRegistration().getHour()));
                        writer.write(":");
                        writer.write(String.valueOf(universityST.get(si).getStudents().get(n).getRegistration().getMinute()));
                        writer.write(";");
                        if(!universityST.get(si).getStudents().get(n).getGroupclass().isEmpty()) {
                            for(String s : universityST.get(si).getStudents().get(n).getGroupclass().keys()) {
                                writer.write(universityST.get(si).getStudents().get(n).getGroupclass().get(s).getName());
                                writer.write("-");
                            }
                        }
                        writer.newLine();
                        writer.close();
                    }
            }
                if(!universityST.get(si).getClassroom().isEmpty()) {
                    for(Integer n : universityST.get(si).getClassroom().keys()) {
                        BufferedWriter writer = new BufferedWriter(new FileWriter(cls, true)); //append true
                        Classroom c = universityST.get(si).getClassroom().get(n);
                        writer.write(String.valueOf(c.getNumber()));
                        writer.write(";");
                        writer.write(String.valueOf(c.getSockets()));
                        writer.write(";");
                        writer.write(String.valueOf(c.getLevel()));
                        writer.write(";");
                        writer.write(universityST.get(si).getName());
                        writer.newLine();
                        writer.close();
                    }
                }
                if(!universityST.get(si).getGroupclass().isEmpty()) {
                    for(String s : universityST.get(si).getGroupclass().keys()) {
                        BufferedWriter writer = new BufferedWriter(new FileWriter(group, true)); //append true
                        GroupClass g = universityST.get(si).getGroupclass().get(s);
                        writer.write(g.getName());
                        writer.write(";");
                        writer.write(g.getUniversity().getName());
                        writer.write(";");
                        writer.write(g.getDiscipline().getName());
                        writer.newLine();
                        writer.close();
                    }
                }
                if(!universityST.get(si).getGroupclass().isEmpty()){
                    for(String s: universityST.get(si).getGroupclass().keys()) {
                        if(universityST.get(si).getGroupclass().get(s).getSchedule() != null) {
                            BufferedWriter writer = new BufferedWriter(new FileWriter(sch, true)); //append true
                            Schedule sd = universityST.get(si).getGroupclass().get(s).getSchedule();
                            writer.write(String.valueOf(sd.getStartdate().getDay()));
                            writer.write("/");
                            writer.write(String.valueOf(sd.getStartdate().getMonth()));
                            writer.write("/");
                            writer.write(String.valueOf(sd.getStartdate().getYear()));
                            writer.write(";");
                            writer.write(String.valueOf(sd.getStartdate().getHour()));
                            writer.write(":");
                            writer.write(String.valueOf(sd.getStartdate().getMinute()));
                            writer.write(";");
                            writer.write(String.valueOf(sd.getEnddate().getDay()));
                            writer.write("/");
                            writer.write(String.valueOf(sd.getEnddate().getMonth()));
                            writer.write("/");
                            writer.write(String.valueOf(sd.getEnddate().getYear()));
                            writer.write(";");
                            writer.write(String.valueOf(sd.getEnddate().getHour()));
                            writer.write(":");
                            writer.write(String.valueOf(sd.getEnddate().getMinute()));
                            writer.write(";");
                            writer.write(sd.getDayoftheweek());
                            writer.write(";");
                            writer.write(String.valueOf(sd.getClassroom().getNumber()));
                            writer.write(";");
                            writer.write(sd.getGroupClass().getName());
                            writer.write(";");
                            writer.write(sd.getTeacher().getUsername());
                            writer.write(";");
                            writer.write(sd.getGroupClass().getUniversity().getName());
                            writer.newLine();
                            writer.close();
                        }
                    }
                }
                if(!universityST.get(si).getGroupclass().isEmpty()) {
                    for(String s : universityST.get(si).getGroupclass().keys()) {
                        if (universityST.get(si).getGroupclass().get(s).getDiscipline() != null) {
                            BufferedWriter writer = new BufferedWriter(new FileWriter(dis, true)); //append true
                            writer.write(universityST.get(si).getGroupclass().get(s).getDiscipline().getName());
                            writer.write(";");
                            writer.write(universityST.get(si).getGroupclass().get(s).getName());
                            writer.write(";");
                            writer.newLine();
                            writer.close();
                        }
                    }
                }
        }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    }

