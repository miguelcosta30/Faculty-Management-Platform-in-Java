package projeto_1;
import edu.princeton.cs.algs4.SeparateChainingHashST;
import java.io.*;
import java.nio.file.DirectoryIteratorException;

public class Student implements Serializable{
    private String name; // Stundets Name
    private int number; //Student Number
    private Date registration; //Register Date
    private String email; // Students email
    private double x;
    private double y;
    private int floor;
    private SeparateChainingHashST<String, GroupClass> groupclass = new SeparateChainingHashST<>();

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
     * associates a groupclass
     * @param g groupclass
     */
    public void associateGroupclass(GroupClass g) {
        if(this.groupclass.contains(g.getName())) {
            System.out.println("Student already associated with this GroupClass " + g);
            return;
        }
        this.groupclass.put(g.getName(),g);
    }

    /**
     * desassociates a groupclass
     * @param name name of groupclass
     * @return
     */
    public GroupClass desassociateGroupClass(String name) {
        GroupClass g = this.groupclass.get(name);
        if(g != null) {
            this.groupclass.delete(name);
            return g;
        }
        return null;
    }

    /**
     * writes a student to file
     * @param s student to be written
     * @param path path to file
     * @throws IOException
     */
    public void WriteStudentToFile(Student s, String path) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(path, true)); //append true
            writer.write(s.getName());
            writer.write(";");
            writer.write(String.valueOf(s.getNumber()));
            writer.write(";");
            writer.write(s.getEmail());
            writer.write(";");
            writer.write(String.valueOf(s.getRegistration().getDay()));
            writer.write("/");
            writer.write(String.valueOf(s.getRegistration().getMonth()));
            writer.write("/");
            writer.write(String.valueOf(s.getRegistration().getYear()));
            writer.write(" ");
            writer.write(String.valueOf(s.getRegistration().getHour()));
            writer.write(":");
            writer.write(String.valueOf(s.getRegistration().getMinute()));
            writer.write(";");
            writer.newLine();
            writer.close();
        } catch (IOException | DirectoryIteratorException x) {
            System.out.println(x);
        }
    }


    public int getNumber() {
        return number;
    }
    public void setNumber(int number) {
        this.number = number;
    }
    public Date getRegistration() {
        return registration;
    }
    public void setRegistration(Date registration) {
        this.registration = registration;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public SeparateChainingHashST<String, GroupClass> getGroupclass() {
        return groupclass;
    }
    public Student(int number, Date registration, String email, String name, double x, double y,int floor) {
        this.number = number;
        this.registration = registration;
        this.email = email;
        this.name = name;
        this.x = x;
        this.y = y;
        this.floor = floor;
    }

    @Override
    public String toString() {
        return "Name - '" + name + '\'' +
                " Number -" + number +
                " Registration - " + registration +
                " Email - '" + email + '\'' ;

    }
}