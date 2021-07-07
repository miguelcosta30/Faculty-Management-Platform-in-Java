package projeto_1;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.nio.file.DirectoryIteratorException;

public class Discipline implements Serializable {
    private String name; //Discipline Name
    private RedBlackBST_AED2<String,GroupClass> groupclass = new RedBlackBST_AED2<>(); //Groupclasses with this discipline
    /**
     * asscoaites a groupclass to a discipline
     * @param g groupclass to be associated
     */

    public void associateGroupClassToDiscipline(GroupClass g) {
        if (this.groupclass.contains(g.getName())) {
            System.out.println("Discipline - associateGroupClassToDiscipline() - This GroupClass is already Associated" + g);
        } else {
            this.groupclass.put(g.getName(), g);
            g.setDiscipline(this);
        }
    }
    /**
     * desassociates a groupclass from the discipline
     * @param name name of the groupclass to be desassociated
     * @return
     */

    public GroupClass desassociateGroupClass(String name) {
        GroupClass g = this.groupclass.get(name);
        if(g!=null) {
            System.out.println("GroupClass desassociated : " + g);
            this.groupclass.delete(name);
            g.setDiscipline(null);
            return g;
        }
        return null;
    }

    /**
     * prints all groupclasses in discipline
     */
    public void printGroupClasses() {
        if(this.groupclass.isEmpty()) {
            System.out.println("No GroupClasses in this discipline");
            return;
        }
        for(String si : this.groupclass.keys()) {
            System.out.println(this.groupclass.get(si));
        }
    }

    /**
     * writes discpçine to file
     * @param d discipline to be written
     * @param path path to file
     * @throws IOException
     */
    public void WriteDisciplineToFile(Discipline d, String path) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(path, true)); //append true
            writer.write(d.getName());
            writer.write(";");
            writer.newLine();
            writer.close();
        }
        catch (IOException | DirectoryIteratorException x) {
            System.out.println(x);
        }
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
    public Discipline(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Name - '" + name + '\'' ;

    }


}