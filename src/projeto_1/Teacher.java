package projeto_1;
import edu.princeton.cs.algs4.RedBlackBST;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.nio.file.DirectoryIteratorException;

public class Teacher implements Serializable {
    private String name; //Teachers Name
    private String email; // Teachers Email
    private String username; // Teachers Username
    private Schedule appointment;
    private double x;
    private double y;
    private int floor;
    private RedBlackBST_AED2<String, GroupClass> groupclass = new RedBlackBST_AED2<>(); // All the groupclasses teacher
    private RedBlackBST <Date, Schedule> schedule = new RedBlackBST<>(); //Teachers Schedule

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public int getFloor() {
        return floor;
    }
    /**
     * writes a teacher to file
     * @param t teacher to be written
     * @param path path to file
     * @throws IOException
     */
    public void WriteTeacherToFile(Teacher t, String path) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(path, true)); //append true
            writer.write(t.getName());
            writer.write(";");
            writer.write(t.getEmail());
            writer.write(";");
            writer.write(t.getUsername());
            writer.write(";");
            writer.newLine();
            writer.close();
        }
        catch (IOException | DirectoryIteratorException x) {
            System.out.println(x);
        }
    }

    /**
     * adds an appointment schedule
     * @param d
     */
    public void addAppointment(Schedule d) {
        if(this.appointment != null) {
            System.out.println("Appointment already exists");
            return;
        }
        this.setAppointment(d);

    }

    /**
     * adds a schedule
     * @param d schedule to be added
     */
    public void addSchedule(Schedule d) {
        if(this.schedule.contains(d.getStartdate())) {
            System.out.println("Teacher - This Schedule Already Exists:  " + d);
            return;
        }
        this.schedule.put(d.getStartdate(),d);
        d.setTeacher(this);
    }

    /**
     * removes a schedule
     * @param startDate key of schedule to be removed
     * @return
     */
    public Schedule removeSchedule (Date startDate) {
       Schedule d =  this.schedule.get(startDate);
       if(d != null) {
           System.out.println("Teacher - This Schedule has been removed");
           this.schedule.get(startDate).setTeacher(null);
           this.schedule.delete(startDate);
           return d;
       }
       return null;
    }

    /**
     * desasscoiates groupclass
     * @param name name of groupclass to desassociate
     * @return
     */
    public GroupClass desassociateTeacherGroupclass (String name) {
        if(this.groupclass.contains(name)) {
            System.out.println("Teacher - This Groupcalss has been desassociated: " + this.groupclass.get(name));
            GroupClass g = this.groupclass.get(name);
            g.setTeacher(null);
            this.removeSchedule(g.getSchedule().getStartdate());
            return g;
        }
        return null;
    }

    /**
     * associates a teacher to groupclass
     * @param g
     */
    public void associateTeacher2GroupClass(GroupClass g) {
        if (this.groupclass.contains(g.getName())) {
            System.out.println("Teacher - This Discipline Already Exists " + g);
            return;
        } else {
            this.groupclass.put(g.getName(), g);
            g.associateTeacher(this);
        }
        if (g.getSchedule() != null) {
            this.addSchedule(g.getSchedule()); //adding the groupclass schedule to the teacher's schedule
        }
    }

    /**
     * prints schedule
     */
    public void printSchedule() {
        if(!this.schedule.isEmpty()) {
            for(Date di : this.schedule.keys()) {
                System.out.println(this.schedule.get(di));
            }
        }
    }

    /**
     * prints groupclass
     */
    public void printGroupclasses() {
        if(!this.groupclass.isEmpty()) {
            for(String si: this.groupclass.keys()) {
                System.out.println(this.groupclass.get(si));
            }
        }
    }
    public void setAppointment(Schedule appointment) {
        this.appointment = appointment;
    }
    public RedBlackBST_AED2<String, GroupClass> getGroupclass() {
        return groupclass;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public Teacher(String name, String email, String username,double x, double y, int floor) {
        this.name = name;
        this.email = email;
        this.username = username;
        this.x = x;
        this.y = y;
        this.floor = floor;
    }

    @Override
    public String toString() {
        return "Name - '" + name + '\'' +
                ", email - '" + email + '\'' +
                ", username - '" + username + '\'';
    }
}