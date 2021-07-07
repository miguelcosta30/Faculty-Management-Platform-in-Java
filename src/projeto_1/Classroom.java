package projeto_1;
import edu.princeton.cs.algs4.RedBlackBST;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.nio.file.DirectoryIteratorException;

public class Classroom implements Serializable { //Serializable if for saving in bin file
    private int number; // Classroom Number
    private int sockets; //Number of Sockets
    private int level;
    private boolean classroomOcupation;
    private RedBlackBST<Date, Schedule> schedule = new RedBlackBST<>(); //Horário desta sala

    /**
     * adds a schedule
     * @param s schedule to be added
     */
    public void addSchedule(Schedule s) {
        if(this.getSchedule().contains(s.getStartdate())) {
            System.out.println("Schedule Already Exists");
            return;
        }
        this.schedule.put(s.getStartdate(),s);
    }

    /**
     * removes a schedule
     * @param startdate key of the schedule to be removed
     */
    public void removeSchedule(Date startdate) {
        if(this.getSchedule().contains(startdate)) {
            this.schedule.delete(startdate);
        }
    }

    /**
     * writes a classroom to file
     * @param c classroom to be written
     * @param path path to file
     */
    public void writeClassroomToFile(Classroom c, String path) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(path, true)); //append true
            writer.write(String.valueOf(c.getNumber()));
            writer.write(";");
            writer.write(String.valueOf(c.getSockets()));
            writer.write(";");
            writer.write(String.valueOf(c.getLevel()));
            writer.write(";");
            writer.newLine();
            writer.close();
        }
        catch (IOException | DirectoryIteratorException x) {
            System.out.println(x);
        }
    }

    public int getNumber() {
        return number;
    }
    public RedBlackBST<Date, Schedule> getSchedule() {
        return schedule;
    }
    public void setNumber(int number) {
        this.number = number;
    }
    public int isSockets() {
        return sockets;
    }
    public void setSockets(short sockets) {
        this.sockets = sockets;
    }
    public int getSockets() {
        return sockets;
    }
    public void setSockets(int sockets) {
        this.sockets = sockets;
    }
    @Override
    public String toString() {
        return "Number: " + number +
                "\t Sockets: " + sockets +
                "\t Level: " + level;
    }

    public Classroom(int number, short sockets,int level) {
        this.number = number;
        this.sockets = sockets;

        this.level = level;
    }
    public int getLevel() {
        return level;
    }
    public void setLevel(int level) {
        this.level = level;
    }
}