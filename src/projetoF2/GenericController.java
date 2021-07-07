package projetoF2;
import edu.princeton.cs.algs4.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.util.converter.IntegerStringConverter;
import projeto_1.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import projeto_1.Classroom;
import projeto_1.Date;

public class GenericController {
    private static final int radius = 25;
    public TableColumn<Student, Date> registrationCol;
    public TableColumn<Student, String> nameCol;
    public TableColumn<Student, Integer> numberCol;
    public TableColumn<Student, String> emailCol;
    public TableColumn<Student, String> groupClassColS;
    public TableColumn<Teacher, String> usernameCol;
    public TableColumn<Teacher, String> nameColTeacher;
    public TableColumn<Teacher, String> emailColT;
    public TableColumn<Teacher, String> groupClassT;
    public TableColumn<Classroom, Integer> numberColC;
    public TableColumn<Classroom, Integer> socketsCol;
    public TableColumn<Classroom, Integer> floorCol;
    public TableColumn<Discipline, String> nameColD;
    public TableColumn<Discipline, String> groupClassCol;
    public TableColumn<GroupClass, String> nameColG;
    public TableColumn<GroupClass, String> disciplineCol;
    public TableColumn<GroupClass, String> teacherCol;
    public TableColumn<GroupClass, String> studentsCol;
    public TableColumn<Student, Double> cordsTableS;
    public TableColumn<Student, Double> cordstableSy;
    public TableColumn<Schedule, String> startDateCol;
    public TableColumn<Schedule,String> endDateCol;
    public TableColumn<Schedule,String> dayofTheWeekCol;
    public TableColumn<Schedule, Integer> classroomColSchedule;
    public TableColumn<Schedule, String> groupclassColSchedule;
    public TableColumn<Schedule,String> teacherColSchedule;
    private BuildingMap buildingMap;
    public ComboBox<String> floorComboBox;
    public ComboBox<String> placeComboBox;
    public ComboBox<Student> studentComboBox;
    public ComboBox<Teacher> teacherComboBox;
    public ComboBox<Node> destinationNodeCombobox;
    public Group graphGroup;
    public University u;
    public TableView<Student> studentsTable;
    public TableView<Teacher> teachersTable;
    public TableView<Classroom> classroomsTable;
    public TableView<Discipline> disciplinesTable;
    public TableView<GroupClass> groupClassTable;
    public TableView<Schedule> schedulesTable;
    public TextField teacherCordsTextField;
    public TextField studentCordsTextField;
    public TextField teacherUsernameFieldAdd;
    public TextField teacherNameFieldAdd;
    public TextField teacherEmailFieldAdd;
    public TextField teacherGroupClassFIeldAdd;
    public TextField teacherUsernameField;
    public TextField disciplineNameField;
    public TextField classroomsNumberField;
    public TextField classroomsFloorField;
    public TextField classroomsOcupationField;
    public TextField studentNumberTextField;
    public TextField studentGroupclassField;
    public TextField studentNameTextField;
    public TextField studentEmailTextField;
    public TextField studentRegistrationTextField;
    public TextField classroomNumberField;
    public TextField classroomSocketsField;
    public TextField classroomFloorField;
    public TextField disciplineNameFieldadd;
    public TextField groupclassDisciplineField;
    public TextField groupClassAddField;
    public TextField groupClassDisciplineAddField;
    public TextField groupClassTeacherAddField;
    public TextField groupClassStudentsAddField;
    public TextField searchRoom2DatesTextField;
    public TextArea displayClassroomFloor;
    public TextArea displayTeacherGroupClasses;
    public TextArea displayDisciplinesTeacher;
    public TextArea displayClassroomSockets;
    public TextArea displayClassroomOcupaion;
    public TextArea displayRooms2Dates;
    private SeparateChainingHashST<String, University> universityST = new SeparateChainingHashST<>();

    public GenericController(BuildingMap<Node> buildingMap) {
        this.buildingMap = buildingMap;
    }

    /**
     * Inicia o controlador/colunas das tabelas / e o mapa
     */
    @FXML
    public void initialize() {
        fillFloorComboBox();
        createGraphGroup(this.buildingMap.getAllNodes());
        fillPlaceComboBox();
        fillNodeComboBox();
        checkGraphConnected();
        //Node n = buildingMap.getNode(0);
        //ArrayList<Node> d = new ArrayList<>();
        //d.add(n);
        //avoidSetOfVertices(d);
        //Student
        registrationCol.setCellValueFactory(new PropertyValueFactory<>("registration"));
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        numberCol.setCellValueFactory(new PropertyValueFactory<>("number"));
        emailCol.setCellValueFactory(new PropertyValueFactory<>("email"));
        groupClassColS.setCellValueFactory(new PropertyValueFactory<>("groupclass"));
        cordsTableS.setCellValueFactory(new PropertyValueFactory<>("x"));
        cordstableSy.setCellValueFactory(new PropertyValueFactory<>("y"));
        //Edit Student
        nameCol.setCellFactory(TextFieldTableCell.forTableColumn()); //Para conseguir editar qlq elemento editavel
        numberCol.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        emailCol.setCellFactory(TextFieldTableCell.forTableColumn());
        //Teacher
        usernameCol.setCellValueFactory(new PropertyValueFactory<>("username"));
        nameColTeacher.setCellValueFactory(new PropertyValueFactory<>("name"));
        emailColT.setCellValueFactory(new PropertyValueFactory<>("email"));
        groupClassT.setCellValueFactory(new PropertyValueFactory<>("groupclass"));
        //Edit teacher
        usernameCol.setCellFactory(TextFieldTableCell.forTableColumn());
        nameColTeacher.setCellFactory(TextFieldTableCell.forTableColumn());
        emailColT.setCellFactory(TextFieldTableCell.forTableColumn());
        //ClassRooms
        numberColC.setCellValueFactory(new PropertyValueFactory<>("number"));
        socketsCol.setCellValueFactory(new PropertyValueFactory<>("sockets"));
        floorCol.setCellValueFactory(new PropertyValueFactory<>("level"));
        //classrooms edit
        numberColC.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        socketsCol.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        floorCol.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        //Discipline
        nameColD.setCellValueFactory(new PropertyValueFactory<>("name"));
        groupClassCol.setCellValueFactory(new PropertyValueFactory<>("groupclass"));
        //Edit Discipline
        nameColD.setCellFactory(TextFieldTableCell.forTableColumn());
        //GroupClass
        nameColG.setCellValueFactory(new PropertyValueFactory<>("name"));
        disciplineCol.setCellValueFactory(new PropertyValueFactory<>("discipline"));
        teacherCol.setCellValueFactory(new PropertyValueFactory<>("teacher"));
        studentsCol.setCellValueFactory(new PropertyValueFactory<>("studentsGroupClass"));
        //Edit Grouclass
        nameColG.setCellFactory(TextFieldTableCell.forTableColumn());
        //Schedule
        startDateCol.setCellValueFactory(new PropertyValueFactory<>("startdate"));
        endDateCol.setCellValueFactory(new PropertyValueFactory<>("enddate"));
        dayofTheWeekCol.setCellValueFactory(new PropertyValueFactory<>("dayoftheweek"));
        teacherColSchedule.setCellValueFactory(new PropertyValueFactory<>("Teacher"));
        classroomColSchedule.setCellValueFactory(new PropertyValueFactory<>("classroom"));
        groupclassColSchedule.setCellValueFactory(new PropertyValueFactory<>("groupClass"));

    }

    /**
     * preenche a combo box dos andares
     */
    private void fillFloorComboBox() {
        ArrayList<Node> nodes = buildingMap.getAllNodes();
        floorComboBox.getItems().add("All Floors");


        for (Node n : nodes) {
            if (!floorComboBox.getItems().contains(Integer.toString(n.getFloor()))) {
                floorComboBox.getItems().add(Integer.toString(n.getFloor()));
            }
        }
    }

    /**
     * preenche a combo box dos nós
     */
    private void fillNodeComboBox() {
        ArrayList<Node> nodes = buildingMap.getAllNodes();
        for(Node n : nodes) {
            if(!destinationNodeCombobox.getItems().contains(n)) {
                destinationNodeCombobox.getItems().add(n);
            }
        }
    }

    /**
     * preenche a combo box dos professores
     */
    private void fillTeacherComboBox() {
        u = universityST.get("UFP");

        ArrayList<Teacher> teachers = u.getAllTeachers();
        for (Teacher t : teachers) {
            if (!teacherComboBox.getItems().contains(t.toString())) {
                teacherComboBox.getItems().add(t);
            }
        }

    }

    /**
     * preenche a combobox dos lugares
     */
    private void fillPlaceComboBox() {

        placeComboBox.getItems().add("Indoor");
        placeComboBox.getItems().add("Outdoor");
        //placeComboBox.getItems().add("Both");

    }

    /**
     * preenche a combo box dos estudantes
     */
    private void fillStudentComboBox() {
        u = universityST.get("UFP");
        ArrayList<Student> studentsArrayList = u.getAllStudents();
        if (!studentsArrayList.isEmpty()) {
            for (Student s : studentsArrayList) {
                if (!studentComboBox.getItems().contains(s.toString())) {
                    studentComboBox.getItems().add(s);
                }
            }
        }
    }

    /**
     * encarrega-se de quando clicamos no no open text file de ler dos ficheiros a informaçao e preencher as tableas
     * @param actionEvent
     * @throws IOException
     */
    public void handleOpenOnAction(ActionEvent actionEvent) throws IOException {
        u = universityST.get("UFP");
        ArrayList<GroupClass> groupClassArrayList = readGroupClassFromFile(".//data//groupclass.txt", universityST);
        ArrayList<Student> studentArrayList = readStudentFromFile(universityST);
        ArrayList<Teacher> teachersArrayList = readTeacherFromFile(".//data//teacher.txt", universityST);
        ArrayList<Classroom> classroomArrayList = readClassroomFromFile(".//data//classroom.txt", universityST);
        ArrayList<Discipline> disciplinesArrayList = readDisciplineFromFile(universityST, ".//data//discipline.txt");
        ArrayList<Schedule> scheduleArrayList = readSchedulesFromFile(universityST);

        studentsTable.getItems().addAll(studentArrayList);
        teachersTable.getItems().addAll(teachersArrayList);
        classroomsTable.getItems().addAll(classroomArrayList);
        disciplinesTable.getItems().addAll(disciplinesArrayList);
        groupClassTable.getItems().addAll(groupClassArrayList);
        schedulesTable.getItems().addAll(scheduleArrayList);

        fillStudentComboBox();
        fillTeacherComboBox();
    }

    /**
     * cria a interface grafica para representar os nós da universidade
     * @param nodes
     */
    private void createGraphGroup(ArrayList<Node> nodes) {
        graphGroup.getChildren().clear();
        for (Node n : nodes) {
            Iterable<Edge> edges = buildingMap.getAllEdgesFromNode(n); //Gets all the adj edges from the node.
            double posX = n.getX();
            double posY = n.getY();
            Circle c = new Circle(posX, posY, radius);
            Text text = new Text(n.getName());
            if (edges.iterator().hasNext()) {
                for (Edge e : edges) {
                    if (n.getName().contains("Hall")) { //For the halls
                        StackPane stackPane = new StackPane();
                        stackPane.setLayoutX(posX - radius);
                        stackPane.setLayoutY(posY - radius);
                        c.setFill(Color.LIGHTCYAN);
                        if (n.compareTo(buildingMap.getNode(e.getV())) != 0) { //saber onde começa o desnho da linha (n v)
                            StackPane stackPanenew = new StackPane();
                            Line l = new Line(n.getX(), n.getY(), buildingMap.getNode(e.getV()).getX(), buildingMap.getNode(e.getV()).getY());
                            l.setStrokeWidth(2.0);
                            graphGroup.getChildren().addAll(stackPanenew, l);
                        } else {                    //n ao w
                            StackPane stackPanenew = new StackPane();
                            Line l = new Line(buildingMap.getNode(e.getV()).getX(), buildingMap.getNode(e.getV()).getY(), buildingMap.getNode(e.getW()).getX(), buildingMap.getNode(e.getW()).getY());
                            l.setStrokeWidth(2.0);
                            graphGroup.getChildren().addAll(stackPanenew, l);
                        }
                        stackPane.getChildren().addAll(c, text);
                        graphGroup.getChildren().add(stackPane);
                    }
                    else if (n.getName().contains("Classroom")) { //classrooms etc
                        c.setFill(Color.LIGHTBLUE);
                        StackPane stackPane = new StackPane();
                        stackPane.setLayoutX(posX - radius);
                        stackPane.setLayoutY(posY - radius);
                        if (n.compareTo(buildingMap.getNode(e.getV())) != 0) {
                            StackPane stackPanenew = new StackPane();
                            Line l = new Line(n.getX(), n.getY(), buildingMap.getNode(e.getV()).getX(), buildingMap.getNode(e.getV()).getY());
                            l.setStrokeWidth(2.0);
                            graphGroup.getChildren().addAll(stackPanenew, l);
                        } else {
                            StackPane stackPanenew = new StackPane();
                            Line l = new Line(buildingMap.getNode(e.getV()).getX(), buildingMap.getNode(e.getV()).getY(), buildingMap.getNode(e.getW()).getX(), buildingMap.getNode(e.getW()).getY());
                            l.setStrokeWidth(2.0);
                            graphGroup.getChildren().addAll(stackPanenew, l);
                        }
                        stackPane.getChildren().addAll(c, text);
                        graphGroup.getChildren().add(stackPane);
                    } else if (n.getName().contains("CP")) {
                        c.setFill(Color.LIGHTGREEN);
                        StackPane stackPane = new StackPane();
                        stackPane.setLayoutX(posX - radius);
                        stackPane.setLayoutY(posY - radius);
                        if (n.compareTo(buildingMap.getNode(e.getV())) != 0) {
                            StackPane stackPanenew = new StackPane();
                            Line l = new Line(n.getX(), n.getY(), buildingMap.getNode(e.getV()).getX(), buildingMap.getNode(e.getV()).getY());
                            l.setStrokeWidth(2.0);
                            graphGroup.getChildren().addAll(stackPanenew, l);
                        } else {
                            StackPane stackPanenew = new StackPane();
                            Line l = new Line(buildingMap.getNode(e.getV()).getX(), buildingMap.getNode(e.getV()).getY(), buildingMap.getNode(e.getW()).getX(), buildingMap.getNode(e.getW()).getY());
                            l.setStrokeWidth(2.0);
                            graphGroup.getChildren().addAll(stackPanenew, l);
                        }
                        stackPane.getChildren().addAll(c, text);
                        graphGroup.getChildren().add(stackPane);
                    } else if (n.getName().contains("WC")) {
                        c.setFill(Color.LIGHTPINK);
                        StackPane stackPane = new StackPane();
                        stackPane.setLayoutX(posX - radius);
                        stackPane.setLayoutY(posY - radius);
                        if (n.compareTo(buildingMap.getNode(e.getV())) != 0) {
                            StackPane stackPanenew = new StackPane();
                            Line l = new Line(n.getX(), n.getY(), buildingMap.getNode(e.getV()).getX(), buildingMap.getNode(e.getV()).getY());
                            l.setStrokeWidth(2.0);
                            graphGroup.getChildren().addAll(stackPanenew, l);
                        } else {
                            StackPane stackPanenew = new StackPane();
                            Line l = new Line(buildingMap.getNode(e.getV()).getX(), buildingMap.getNode(e.getV()).getY(), buildingMap.getNode(e.getW()).getX(), buildingMap.getNode(e.getW()).getY());
                            l.setStrokeWidth(2.0);
                            graphGroup.getChildren().addAll(stackPanenew, l);
                        }
                        stackPane.getChildren().addAll(c, text);
                        graphGroup.getChildren().add(stackPane);
                    } else if (n.getName().contains("Exit")) {
                        c.setFill(Color.PALEVIOLETRED);
                        StackPane stackPane = new StackPane();
                        stackPane.setLayoutX(posX - radius);
                        stackPane.setLayoutY(posY - radius);
                        if (n.compareTo(buildingMap.getNode(e.getV())) != 0) {
                            StackPane stackPanenew = new StackPane();
                            Line l = new Line(n.getX(), n.getY(), buildingMap.getNode(e.getV()).getX(), buildingMap.getNode(e.getV()).getY());
                            l.setStrokeWidth(2.0);
                            graphGroup.getChildren().addAll(stackPanenew, l);
                        } else {
                            StackPane stackPanenew = new StackPane();
                            Line l = new Line(buildingMap.getNode(e.getV()).getX(), buildingMap.getNode(e.getV()).getY(), buildingMap.getNode(e.getW()).getX(), buildingMap.getNode(e.getW()).getY());
                            l.setStrokeWidth(2.0);
                            graphGroup.getChildren().addAll(stackPanenew, l);
                        }
                        stackPane.getChildren().addAll(c, text);
                        graphGroup.getChildren().add(stackPane);
                    } else if (n.getName().contains("Stairs")) {
                        c.setFill(Color.LIGHTGOLDENRODYELLOW);
                        StackPane stackPane = new StackPane();
                        stackPane.setLayoutX(posX - radius);
                        stackPane.setLayoutY(posY - radius);
                        if (n.compareTo(buildingMap.getNode(e.getV())) != 0) {
                            StackPane stackPanenew = new StackPane();
                            Line l = new Line(n.getX(), n.getY(), buildingMap.getNode(e.getV()).getX(), buildingMap.getNode(e.getV()).getY());
                            l.setStrokeWidth(2.0);
                            graphGroup.getChildren().addAll(stackPanenew, l);
                        } else {
                            StackPane stackPanenew = new StackPane();
                            Line l = new Line(buildingMap.getNode(e.getV()).getX(), buildingMap.getNode(e.getV()).getY(), buildingMap.getNode(e.getW()).getX(), buildingMap.getNode(e.getW()).getY());
                            l.setStrokeWidth(2.0);
                            graphGroup.getChildren().addAll(stackPanenew, l);
                        }
                        stackPane.getChildren().addAll(c, text);
                        graphGroup.getChildren().add(stackPane);
                    }
                }
            } else {
                StackPane stackPane = new StackPane();
                if (n.getName().contains("Hall")) {
                    c.setFill(Color.LIGHTCYAN);
                    stackPane.setLayoutX(posX - radius);
                    stackPane.setLayoutY(posY - radius);
                    stackPane.getChildren().addAll(c, text);
                    graphGroup.getChildren().add(stackPane);
                } else if (n.getName().contains("Classroom")) {
                    c.setFill(Color.LIGHTBLUE);
                    stackPane.setLayoutX(posX - radius);
                    stackPane.setLayoutY(posY - radius);
                    stackPane.getChildren().addAll(c, text);
                    graphGroup.getChildren().add(stackPane);
                } else if (n.getName().contains("CP")) {
                    c.setFill(Color.LIGHTGREEN);
                    stackPane.setLayoutX(posX - radius);
                    stackPane.setLayoutY(posY - radius);
                    stackPane.getChildren().addAll(c, text);
                    graphGroup.getChildren().add(stackPane);
                } else if (n.getName().contains("WC")) {
                    c.setFill(Color.LIGHTPINK);
                    stackPane.setLayoutX(posX - radius);
                    stackPane.setLayoutY(posY - radius);
                    stackPane.getChildren().addAll(c, text);
                    graphGroup.getChildren().add(stackPane);
                } else if(n.getName().contains("Exit")) {
                    c.setFill(Color.PALEVIOLETRED);
                    stackPane.setLayoutX(posX - radius);
                    stackPane.setLayoutY(posY - radius);
                    stackPane.getChildren().addAll(c, text);
                    graphGroup.getChildren().add(stackPane);
                } else if(n.getName().contains("Stairs")) {
                    c.setFill(Color.LIGHTGOLDENRODYELLOW);
                    stackPane.setLayoutX(posX - radius);
                    stackPane.setLayoutY(posY - radius);
                    stackPane.getChildren().addAll(c, text);
                    graphGroup.getChildren().add(stackPane);
                }
            }
        }
    }

    /**
     * cria o grafo para um certo andar
     * @param nodes nos desse andar
     */
    public void createGraphGroupFloor(ArrayList<Node> nodes) {
        graphGroup.getChildren().clear();
        for (Node n : nodes) {
            System.out.println(n);
            String floor = floorComboBox.getValue();
            Iterable<Edge> edges = buildingMap.getAllEdgesFromNodeInFloor(n, Integer.parseInt(floor)); //Gets all the adj edges from the node.
            double posX = n.getX();
            double posY = n.getY();
            Circle c = new Circle(posX, posY, radius);
            Text text = new Text(n.getName());
            if (edges.iterator().hasNext()) {
                for (Edge e : edges) {
                    if (n.getName().contains("Hall")) { //For the halls
                        StackPane stackPane = new StackPane();
                        stackPane.setLayoutX(posX - radius);
                        stackPane.setLayoutY(posY - radius);
                        c.setFill(Color.LIGHTCYAN);
                        if (n.compareTo(buildingMap.getNode(e.getV())) != 0) {
                            StackPane stackPanenew = new StackPane();
                            Line l = new Line(n.getX(), n.getY(), buildingMap.getNode(e.getV()).getX(), buildingMap.getNode(e.getV()).getY());
                            l.setStrokeWidth(2.0);
                            graphGroup.getChildren().addAll(stackPanenew, l);
                        } else {
                            StackPane stackPanenew = new StackPane();
                            Line l = new Line(buildingMap.getNode(e.getV()).getX(), buildingMap.getNode(e.getV()).getY(), buildingMap.getNode(e.getW()).getX(), buildingMap.getNode(e.getW()).getY());
                            l.setStrokeWidth(2.0);
                            graphGroup.getChildren().addAll(stackPanenew, l);
                        }
                        stackPane.getChildren().addAll(c, text);
                        graphGroup.getChildren().add(stackPane);
                    } else if (n.getName().contains("Classroom")) { //classrooms etc
                        c.setFill(Color.LIGHTBLUE);
                        StackPane stackPane = new StackPane();
                        stackPane.setLayoutX(posX - radius);
                        stackPane.setLayoutY(posY - radius);
                        if (n.compareTo(buildingMap.getNode(e.getV())) != 0) {
                            StackPane stackPanenew = new StackPane();
                            Line l = new Line(n.getX(), n.getY(), buildingMap.getNode(e.getV()).getX(), buildingMap.getNode(e.getV()).getY());
                            l.setStrokeWidth(2.0);
                            graphGroup.getChildren().addAll(stackPanenew, l);
                        } else {
                            StackPane stackPanenew = new StackPane();
                            Line l = new Line(buildingMap.getNode(e.getV()).getX(), buildingMap.getNode(e.getV()).getY(), buildingMap.getNode(e.getW()).getX(), buildingMap.getNode(e.getW()).getY());
                            l.setStrokeWidth(2.0);
                            graphGroup.getChildren().addAll(stackPanenew, l);
                        }
                        stackPane.getChildren().addAll(c, text);
                        graphGroup.getChildren().add(stackPane);
                    } else if (n.getName().contains("CP")) {
                        c.setFill(Color.LIGHTGREEN);
                        StackPane stackPane = new StackPane();
                        stackPane.setLayoutX(posX - radius);
                        stackPane.setLayoutY(posY - radius);
                        if (n.compareTo(buildingMap.getNode(e.getV())) != 0) {
                            StackPane stackPanenew = new StackPane();
                            Line l = new Line(n.getX(), n.getY(), buildingMap.getNode(e.getV()).getX(), buildingMap.getNode(e.getV()).getY());
                            l.setStrokeWidth(2.0);
                            graphGroup.getChildren().addAll(stackPanenew, l);
                        } else {
                            StackPane stackPanenew = new StackPane();
                            Line l = new Line(buildingMap.getNode(e.getV()).getX(), buildingMap.getNode(e.getV()).getY(), buildingMap.getNode(e.getW()).getX(), buildingMap.getNode(e.getW()).getY());
                            l.setStrokeWidth(2.0);
                            graphGroup.getChildren().addAll(stackPanenew, l);
                        }
                        stackPane.getChildren().addAll(c, text);
                        graphGroup.getChildren().add(stackPane);
                    } else if (n.getName().contains("WC")) {
                        c.setFill(Color.LIGHTPINK);
                        StackPane stackPane = new StackPane();
                        stackPane.setLayoutX(posX - radius);
                        stackPane.setLayoutY(posY - radius);
                        if (n.compareTo(buildingMap.getNode(e.getV())) != 0) {
                            StackPane stackPanenew = new StackPane();
                            Line l = new Line(n.getX(), n.getY(), buildingMap.getNode(e.getV()).getX(), buildingMap.getNode(e.getV()).getY());
                            l.setStrokeWidth(2.0);
                            graphGroup.getChildren().addAll(stackPanenew, l);
                        } else {
                            StackPane stackPanenew = new StackPane();
                            Line l = new Line(buildingMap.getNode(e.getV()).getX(), buildingMap.getNode(e.getV()).getY(), buildingMap.getNode(e.getW()).getX(), buildingMap.getNode(e.getW()).getY());
                            l.setStrokeWidth(2.0);
                            graphGroup.getChildren().addAll(stackPanenew, l);
                        }
                        stackPane.getChildren().addAll(c, text);
                        graphGroup.getChildren().add(stackPane);
                    } else if (n.getName().contains("Exit")) {
                        c.setFill(Color.PALEVIOLETRED);
                        StackPane stackPane = new StackPane();
                        stackPane.setLayoutX(posX - radius);
                        stackPane.setLayoutY(posY - radius);
                        if (n.compareTo(buildingMap.getNode(e.getV())) != 0) {
                            StackPane stackPanenew = new StackPane();
                            Line l = new Line(n.getX(), n.getY(), buildingMap.getNode(e.getV()).getX(), buildingMap.getNode(e.getV()).getY());
                            l.setStrokeWidth(2.0);
                            graphGroup.getChildren().addAll(stackPanenew, l);
                        } else {
                            StackPane stackPanenew = new StackPane();
                            Line l = new Line(buildingMap.getNode(e.getV()).getX(), buildingMap.getNode(e.getV()).getY(), buildingMap.getNode(e.getW()).getX(), buildingMap.getNode(e.getW()).getY());
                            l.setStrokeWidth(2.0);
                            graphGroup.getChildren().addAll(stackPanenew, l);
                        }
                        stackPane.getChildren().addAll(c, text);
                        graphGroup.getChildren().add(stackPane);
                    } else if (n.getName().contains("Stairs")) {
                        c.setFill(Color.LIGHTGOLDENRODYELLOW);
                        StackPane stackPane = new StackPane();
                        stackPane.setLayoutX(posX - radius);
                        stackPane.setLayoutY(posY - radius);
                        if (n.compareTo(buildingMap.getNode(e.getV())) != 0) {
                            StackPane stackPanenew = new StackPane();
                            Line l = new Line(n.getX(), n.getY(), buildingMap.getNode(e.getV()).getX(), buildingMap.getNode(e.getV()).getY());
                            l.setStrokeWidth(2.0);
                            graphGroup.getChildren().addAll(stackPanenew, l);
                        } else {
                            StackPane stackPanenew = new StackPane();
                            Line l = new Line(buildingMap.getNode(e.getV()).getX(), buildingMap.getNode(e.getV()).getY(), buildingMap.getNode(e.getW()).getX(), buildingMap.getNode(e.getW()).getY());
                            l.setStrokeWidth(2.0);
                            graphGroup.getChildren().addAll(stackPanenew, l);
                        }
                        stackPane.getChildren().addAll(c, text);
                        graphGroup.getChildren().add(stackPane);
                    }
                }
            } else {
                if (n.getName().contains("Hall")) {
                    StackPane stackPane = new StackPane();
                    c.setFill(Color.LIGHTCYAN);
                    stackPane.setLayoutX(posX - radius);
                    stackPane.setLayoutY(posY - radius);
                    stackPane.getChildren().addAll(c, text);
                    graphGroup.getChildren().add(stackPane);
                } else if (n.getName().contains("Classroom")) {
                    StackPane stackPane = new StackPane();
                    c.setFill(Color.LIGHTBLUE);
                    stackPane.setLayoutX(posX - radius);
                    stackPane.setLayoutY(posY - radius);
                    stackPane.getChildren().addAll(c, text);
                    graphGroup.getChildren().add(stackPane);
                } else if (n.getName().contains("CP")) {
                    StackPane stackPane = new StackPane();
                    c.setFill(Color.LIGHTGREEN);
                    stackPane.setLayoutX(posX - radius);
                    stackPane.setLayoutY(posY - radius);
                    stackPane.getChildren().addAll(c, text);
                    graphGroup.getChildren().add(stackPane);
                } else if (n.getName().contains("WC")) {
                    StackPane stackPane = new StackPane();
                    c.setFill(Color.LIGHTPINK);
                    stackPane.setLayoutX(posX - radius);
                    stackPane.setLayoutY(posY - radius);
                    stackPane.getChildren().addAll(c, text);
                    graphGroup.getChildren().add(stackPane);
                } else if (n.getName().contains("Exit")) {
                    StackPane stackPane = new StackPane();
                    c.setFill(Color.PALEVIOLETRED);
                    stackPane.setLayoutX(posX - radius);
                    stackPane.setLayoutY(posY - radius);
                    stackPane.getChildren().addAll(c, text);
                    graphGroup.getChildren().add(stackPane);
                } else if (n.getName().contains("Stairs")) {
                    StackPane stackPane = new StackPane();
                    c.setFill(Color.LIGHTGOLDENRODYELLOW);
                    stackPane.setLayoutX(posX - radius);
                    stackPane.setLayoutY(posY - radius);
                    stackPane.getChildren().addAll(c, text);
                    graphGroup.getChildren().add(stackPane);
                }
            }
        }
    }

    /**
     * cria a interface grafica por lugar
     * @param nodes nós por lugar
     */
    public void createGraphGroupPlace(ArrayList<Node> nodes) {
        graphGroup.getChildren().clear();
        for (Node n : nodes) {
            String place = placeComboBox.getValue();
            Iterable<Edge> edges = buildingMap.getAllEdgesFromNodeInPlace(n, place); //Gets all the adj edges from the node.
            double posX = n.getX();
            double posY = n.getY();
            if(edges.iterator().hasNext()) {
                Circle c = new Circle(posX, posY, radius);
                Text text = new Text(n.getName());
                for (Edge e : edges) {
                    if (n.getName().contains("Hall")) { //For the halls
                        StackPane stackPane = new StackPane();
                        stackPane.setLayoutX(posX - radius);
                        stackPane.setLayoutY(posY - radius);
                        c.setFill(Color.LIGHTCYAN);
                        if (n.compareTo(buildingMap.getNode(e.getV())) != 0) {
                            StackPane stackPanenew = new StackPane();
                            Line l = new Line(n.getX(), n.getY(), buildingMap.getNode(e.getV()).getX(), buildingMap.getNode(e.getV()).getY());
                            l.setStrokeWidth(2.0);
                            graphGroup.getChildren().addAll(stackPanenew, l);
                        } else {
                            StackPane stackPanenew = new StackPane();
                            Line l = new Line(buildingMap.getNode(e.getV()).getX(), buildingMap.getNode(e.getV()).getY(), buildingMap.getNode(e.getW()).getX(), buildingMap.getNode(e.getW()).getY());
                            l.setStrokeWidth(2.0);
                            graphGroup.getChildren().addAll(stackPanenew, l);
                        }
                        stackPane.getChildren().addAll(c, text);
                        graphGroup.getChildren().add(stackPane);
                    } else if (n.getName().contains("Classroom")) { //classrooms etc
                        c.setFill(Color.LIGHTBLUE);
                        StackPane stackPane = new StackPane();
                        stackPane.setLayoutX(posX - radius);
                        stackPane.setLayoutY(posY - radius);
                        if (n.compareTo(buildingMap.getNode(e.getV())) != 0) {
                            StackPane stackPanenew = new StackPane();
                            Line l = new Line(n.getX(), n.getY(), buildingMap.getNode(e.getV()).getX(), buildingMap.getNode(e.getV()).getY());
                            l.setStrokeWidth(2.0);
                            graphGroup.getChildren().addAll(stackPanenew, l);
                        } else {
                            StackPane stackPanenew = new StackPane();
                            Line l = new Line(buildingMap.getNode(e.getV()).getX(), buildingMap.getNode(e.getV()).getY(), buildingMap.getNode(e.getW()).getX(), buildingMap.getNode(e.getW()).getY());
                            l.setStrokeWidth(2.0);
                            graphGroup.getChildren().addAll(stackPanenew, l);
                        }
                        stackPane.getChildren().addAll(c, text);
                        graphGroup.getChildren().add(stackPane);
                    } else if (n.getName().contains("CP")) {
                        c.setFill(Color.LIGHTGREEN);
                        StackPane stackPane = new StackPane();
                        stackPane.setLayoutX(posX - radius);
                        stackPane.setLayoutY(posY - radius);
                        if (n.compareTo(buildingMap.getNode(e.getV())) != 0) {
                            StackPane stackPanenew = new StackPane();
                            Line l = new Line(n.getX(), n.getY(), buildingMap.getNode(e.getV()).getX(), buildingMap.getNode(e.getV()).getY());
                            l.setStrokeWidth(2.0);
                            graphGroup.getChildren().addAll(stackPanenew, l);
                        } else {
                            StackPane stackPanenew = new StackPane();
                            Line l = new Line(buildingMap.getNode(e.getV()).getX(), buildingMap.getNode(e.getV()).getY(), buildingMap.getNode(e.getW()).getX(), buildingMap.getNode(e.getW()).getY());
                            l.setStrokeWidth(2.0);
                            graphGroup.getChildren().addAll(stackPanenew, l);
                        }
                        stackPane.getChildren().addAll(c, text);
                        graphGroup.getChildren().add(stackPane);
                    } else if (n.getName().contains("WC")) {
                        c.setFill(Color.LIGHTPINK);
                        StackPane stackPane = new StackPane();
                        stackPane.setLayoutX(posX - radius);
                        stackPane.setLayoutY(posY - radius);
                        if (n.compareTo(buildingMap.getNode(e.getV())) != 0) {
                            StackPane stackPanenew = new StackPane();
                            Line l = new Line(n.getX(), n.getY(), buildingMap.getNode(e.getV()).getX(), buildingMap.getNode(e.getV()).getY());
                            l.setStrokeWidth(2.0);
                            graphGroup.getChildren().addAll(stackPanenew, l);
                        } else {
                            StackPane stackPanenew = new StackPane();
                            Line l = new Line(buildingMap.getNode(e.getV()).getX(), buildingMap.getNode(e.getV()).getY(), buildingMap.getNode(e.getW()).getX(), buildingMap.getNode(e.getW()).getY());
                            l.setStrokeWidth(2.0);
                            graphGroup.getChildren().addAll(stackPanenew, l);
                        }
                        stackPane.getChildren().addAll(c, text);
                        graphGroup.getChildren().add(stackPane);
                    } else if (n.getName().contains("Exit")) {
                        c.setFill(Color.PALEVIOLETRED);
                        StackPane stackPane = new StackPane();
                        stackPane.setLayoutX(posX - radius);
                        stackPane.setLayoutY(posY - radius);
                        if (n.compareTo(buildingMap.getNode(e.getV())) != 0) {
                            StackPane stackPanenew = new StackPane();
                            Line l = new Line(n.getX(), n.getY(), buildingMap.getNode(e.getV()).getX(), buildingMap.getNode(e.getV()).getY());
                            l.setStrokeWidth(2.0);
                            graphGroup.getChildren().addAll(stackPanenew, l);
                        } else {
                            StackPane stackPanenew = new StackPane();
                            Line l = new Line(buildingMap.getNode(e.getV()).getX(), buildingMap.getNode(e.getV()).getY(), buildingMap.getNode(e.getW()).getX(), buildingMap.getNode(e.getW()).getY());
                            l.setStrokeWidth(2.0);
                            graphGroup.getChildren().addAll(stackPanenew, l);
                        }
                        stackPane.getChildren().addAll(c, text);
                        graphGroup.getChildren().add(stackPane);
                    } else if (n.getName().contains("Stairs")) {
                        c.setFill(Color.LIGHTGOLDENRODYELLOW);
                        StackPane stackPane = new StackPane();
                        stackPane.setLayoutX(posX - radius);
                        stackPane.setLayoutY(posY - radius);
                        if (n.compareTo(buildingMap.getNode(e.getV())) != 0) {
                            StackPane stackPanenew = new StackPane();
                            Line l = new Line(n.getX(), n.getY(), buildingMap.getNode(e.getV()).getX(), buildingMap.getNode(e.getV()).getY());
                            l.setStrokeWidth(2.0);
                            graphGroup.getChildren().addAll(stackPanenew, l);
                        } else {
                            StackPane stackPanenew = new StackPane();
                            Line l = new Line(buildingMap.getNode(e.getV()).getX(), buildingMap.getNode(e.getV()).getY(), buildingMap.getNode(e.getW()).getX(), buildingMap.getNode(e.getW()).getY());
                            l.setStrokeWidth(2.0);
                            graphGroup.getChildren().addAll(stackPanenew, l);
                        }
                        stackPane.getChildren().addAll(c, text);
                        graphGroup.getChildren().add(stackPane);
                    }
                }
            } else {
                Circle c = new Circle(posX, posY, radius);
                Text text = new Text("" + n.getName());
                StackPane stackPane = new StackPane();
                if (n.getName().contains("Hall")) {
                    c.setFill(Color.LIGHTCYAN);
                    stackPane.setLayoutX(posX - radius);
                    stackPane.setLayoutY(posY - radius);
                    stackPane.getChildren().addAll(c, text);
                    graphGroup.getChildren().add(stackPane);
                } else if (n.getName().contains("Classroom")) {
                    c.setFill(Color.LIGHTBLUE);
                    stackPane.setLayoutX(posX - radius);
                    stackPane.setLayoutY(posY - radius);
                    stackPane.getChildren().addAll(c, text);
                    graphGroup.getChildren().add(stackPane);
                } else if (n.getName().contains("CP")) {
                    c.setFill(Color.LIGHTGREEN);
                    stackPane.setLayoutX(posX - radius);
                    stackPane.setLayoutY(posY - radius);
                    stackPane.getChildren().addAll(c, text);
                    graphGroup.getChildren().add(stackPane);
                } else if (n.getName().contains("WC")) {
                    c.setFill(Color.LIGHTPINK);
                    stackPane.setLayoutX(posX - radius);
                    stackPane.setLayoutY(posY - radius);
                    stackPane.getChildren().addAll(c, text);
                    graphGroup.getChildren().add(stackPane);
                } else if(n.getName().contains("Exit")) {
                    c.setFill(Color.PALEVIOLETRED);
                    stackPane.setLayoutX(posX - radius);
                    stackPane.setLayoutY(posY - radius);
                    stackPane.getChildren().addAll(c, text);
                    graphGroup.getChildren().add(stackPane);
                } else if(n.getName().contains("Stairs")) {
                    c.setFill(Color.LIGHTGOLDENRODYELLOW);
                    stackPane.setLayoutX(posX - radius);
                    stackPane.setLayoutY(posY - radius);
                    stackPane.getChildren().addAll(c, text);
                    graphGroup.getChildren().add(stackPane);
                }
            }
        }
    }

    /**
     * cria interfacegrafica para lugar e andar ao mesmo tempo
     * @param nodes nós com aquele andar e aquele lugar
     */
        public void createGraphGroupFloorAndPlace(ArrayList<Node> nodes) {
            graphGroup.getChildren().clear();
            for (Node n : nodes) {
                String floor = floorComboBox.getValue();
                String place = placeComboBox.getValue();
                Iterable<Edge> edges = buildingMap.getAllEdgesFromNodeInFloorAndPlace(n,Integer.parseInt(floor),place); //Gets all the adj edges from the node.
                double posX = n.getX();
                double posY = n.getY();
                Circle c = new Circle(posX, posY, radius);
                Text text = new Text(n.getName());
                if(edges.iterator().hasNext()) {
                    for (Edge e : edges) {
                        if (n.getName().contains("Hall")) { //For the halls
                            StackPane stackPane = new StackPane();
                            stackPane.setLayoutX(posX - radius);
                            stackPane.setLayoutY(posY - radius);
                            c.setFill(Color.LIGHTCYAN);
                            if (n.compareTo(buildingMap.getNode(e.getV())) != 0) { /////////////////HELP NESTA LINHA
                                StackPane stackPanenew = new StackPane();
                                Line l = new Line(n.getX(), n.getY(), buildingMap.getNode(e.getV()).getX(), buildingMap.getNode(e.getV()).getY());
                                l.setStrokeWidth(2.0);
                                graphGroup.getChildren().addAll(stackPanenew, l);
                            } else {
                                StackPane stackPanenew = new StackPane();
                                Line l = new Line(buildingMap.getNode(e.getV()).getX(), buildingMap.getNode(e.getV()).getY(), buildingMap.getNode(e.getW()).getX(), buildingMap.getNode(e.getW()).getY());
                                l.setStrokeWidth(2.0);
                                graphGroup.getChildren().addAll(stackPanenew, l);
                            }
                            stackPane.getChildren().addAll(c, text);
                            graphGroup.getChildren().add(stackPane);
                        } else if (n.getName().contains("Classroom")) { //classrooms etc
                            c.setFill(Color.LIGHTBLUE);
                            StackPane stackPane = new StackPane();
                            stackPane.setLayoutX(posX - radius);
                            stackPane.setLayoutY(posY - radius);
                            if (n.compareTo(buildingMap.getNode(e.getV())) != 0) {
                                StackPane stackPanenew = new StackPane();
                                Line l = new Line(n.getX(), n.getY(), buildingMap.getNode(e.getV()).getX(), buildingMap.getNode(e.getV()).getY());
                                l.setStrokeWidth(2.0);
                                graphGroup.getChildren().addAll(stackPanenew, l);
                            } else {
                                StackPane stackPanenew = new StackPane();
                                Line l = new Line(buildingMap.getNode(e.getV()).getX(), buildingMap.getNode(e.getV()).getY(), buildingMap.getNode(e.getW()).getX(), buildingMap.getNode(e.getW()).getY());
                                l.setStrokeWidth(2.0);
                                graphGroup.getChildren().addAll(stackPanenew, l);
                            }
                            stackPane.getChildren().addAll(c, text);
                            graphGroup.getChildren().add(stackPane);
                        } else if (n.getName().contains("CP")) {
                            c.setFill(Color.LIGHTGREEN);
                            StackPane stackPane = new StackPane();
                            stackPane.setLayoutX(posX - radius);
                            stackPane.setLayoutY(posY - radius);
                            if (n.compareTo(buildingMap.getNode(e.getV())) != 0) {
                                StackPane stackPanenew = new StackPane();
                                Line l = new Line(n.getX(), n.getY(), buildingMap.getNode(e.getV()).getX(), buildingMap.getNode(e.getV()).getY());
                                l.setStrokeWidth(2.0);
                                graphGroup.getChildren().addAll(stackPanenew, l);
                            } else {
                                StackPane stackPanenew = new StackPane();
                                Line l = new Line(buildingMap.getNode(e.getV()).getX(), buildingMap.getNode(e.getV()).getY(), buildingMap.getNode(e.getW()).getX(), buildingMap.getNode(e.getW()).getY());
                                l.setStrokeWidth(2.0);
                                graphGroup.getChildren().addAll(stackPanenew, l);
                            }
                            stackPane.getChildren().addAll(c, text);
                            graphGroup.getChildren().add(stackPane);
                        } else if (n.getName().contains("WC")) {
                            c.setFill(Color.LIGHTPINK);
                            StackPane stackPane = new StackPane();
                            stackPane.setLayoutX(posX - radius);
                            stackPane.setLayoutY(posY - radius);
                            if (n.compareTo(buildingMap.getNode(e.getV())) != 0) {
                                StackPane stackPanenew = new StackPane();
                                Line l = new Line(n.getX(), n.getY(), buildingMap.getNode(e.getV()).getX(), buildingMap.getNode(e.getV()).getY());
                                l.setStrokeWidth(2.0);
                                graphGroup.getChildren().addAll(stackPanenew, l);
                            } else {
                                StackPane stackPanenew = new StackPane();
                                Line l = new Line(buildingMap.getNode(e.getV()).getX(), buildingMap.getNode(e.getV()).getY(), buildingMap.getNode(e.getW()).getX(), buildingMap.getNode(e.getW()).getY());
                                l.setStrokeWidth(2.0);
                                graphGroup.getChildren().addAll(stackPanenew, l);
                            }
                            stackPane.getChildren().addAll(c, text);
                            graphGroup.getChildren().add(stackPane);
                        } else if (n.getName().contains("Exit")) {
                            c.setFill(Color.PALEVIOLETRED);
                            StackPane stackPane = new StackPane();
                            stackPane.setLayoutX(posX - radius);
                            stackPane.setLayoutY(posY - radius);
                            if (n.compareTo(buildingMap.getNode(e.getV())) != 0) {
                                StackPane stackPanenew = new StackPane();
                                Line l = new Line(n.getX(), n.getY(), buildingMap.getNode(e.getV()).getX(), buildingMap.getNode(e.getV()).getY());
                                l.setStrokeWidth(2.0);
                                graphGroup.getChildren().addAll(stackPanenew, l);
                            } else {
                                StackPane stackPanenew = new StackPane();
                                Line l = new Line(buildingMap.getNode(e.getV()).getX(), buildingMap.getNode(e.getV()).getY(), buildingMap.getNode(e.getW()).getX(), buildingMap.getNode(e.getW()).getY());
                                l.setStrokeWidth(2.0);
                                graphGroup.getChildren().addAll(stackPanenew, l);
                            }
                            stackPane.getChildren().addAll(c, text);
                            graphGroup.getChildren().add(stackPane);
                        } else if (n.getName().contains("Stairs")) {
                            c.setFill(Color.LIGHTGOLDENRODYELLOW);
                            StackPane stackPane = new StackPane();
                            stackPane.setLayoutX(posX - radius);
                            stackPane.setLayoutY(posY - radius);
                            if (n.compareTo(buildingMap.getNode(e.getV())) != 0) {
                                StackPane stackPanenew = new StackPane();
                                Line l = new Line(n.getX(), n.getY(), buildingMap.getNode(e.getV()).getX(), buildingMap.getNode(e.getV()).getY());
                                l.setStrokeWidth(2.0);
                                graphGroup.getChildren().addAll(stackPanenew, l);
                            } else {
                                StackPane stackPanenew = new StackPane();
                                Line l = new Line(buildingMap.getNode(e.getV()).getX(), buildingMap.getNode(e.getV()).getY(), buildingMap.getNode(e.getW()).getX(), buildingMap.getNode(e.getW()).getY());
                                l.setStrokeWidth(2.0);
                                graphGroup.getChildren().addAll(stackPanenew, l);
                            }
                            stackPane.getChildren().addAll(c, text);
                            graphGroup.getChildren().add(stackPane);
                        }
                    }
                } else {
                    StackPane stackPane = new StackPane();
                    if (n.getName().contains("Hall")) {
                        c.setFill(Color.LIGHTCYAN);
                        stackPane.setLayoutX(posX - radius);
                        stackPane.setLayoutY(posY - radius);
                        stackPane.getChildren().addAll(c, text);
                        graphGroup.getChildren().add(stackPane);
                    } else if (n.getName().contains("Classroom")) {
                        c.setFill(Color.LIGHTBLUE);
                        stackPane.setLayoutX(posX - radius);
                        stackPane.setLayoutY(posY - radius);
                        stackPane.getChildren().addAll(c, text);
                        graphGroup.getChildren().add(stackPane);
                    } else if (n.getName().contains("CP")) {
                        c.setFill(Color.LIGHTGREEN);
                        stackPane.setLayoutX(posX - radius);
                        stackPane.setLayoutY(posY - radius);
                        stackPane.getChildren().addAll(c, text);
                        graphGroup.getChildren().add(stackPane);
                    } else if (n.getName().contains("WC")) {
                        c.setFill(Color.LIGHTPINK);
                        stackPane.setLayoutX(posX - radius);
                        stackPane.setLayoutY(posY - radius);
                        stackPane.getChildren().addAll(c, text);
                        graphGroup.getChildren().add(stackPane);
                    } else if(n.getName().contains("Exit")) {
                        c.setFill(Color.PALEVIOLETRED);
                        stackPane.setLayoutX(posX - radius);
                        stackPane.setLayoutY(posY - radius);
                        stackPane.getChildren().addAll(c, text);
                        graphGroup.getChildren().add(stackPane);
                    } else if(n.getName().contains("Stairs")) {
                        c.setFill(Color.LIGHTGOLDENRODYELLOW);
                        stackPane.setLayoutX(posX - radius);
                        stackPane.setLayoutY(posY - radius);
                        stackPane.getChildren().addAll(c, text);
                        graphGroup.getChildren().add(stackPane);
                    }
                }
            }
        }

    /**
     * cuida da escolha da floor combobox e tem em cuidado a placecombobox
     * @param actionEvent
     */
    public void handleFloorChoiceAction(ActionEvent actionEvent) {
        String floor = floorComboBox.getValue();
        String place = placeComboBox.getValue();
        System.out.println(place);
        if (place != null) {
            if (floor.compareTo("All Floors") == 0) {
                createGraphGroupPlace(buildingMap.getallNodesByPlace(place));
            } else {
                createGraphGroupFloorAndPlace(buildingMap.getNodesByPlaceandFloor(Integer.parseInt(floor), place));
            }
        } else { //se nao selecionar nada no place
            if (floor.compareTo("All Floors") == 0) {
                    createGraphGroup(buildingMap.getAllNodes());
            } else {
                createGraphGroupFloor(buildingMap.getAllNodesByfloor(Integer.parseInt(floor))); //outras opçoes
            }
        }
    }

    /**
     * cuida da escolha da place combo box e tem em cuidado a floorcombobox
     * @param actionEvent
     */
    public void handlePlaceChoiceAction(ActionEvent actionEvent) {
        String floor = floorComboBox.getValue();
        String place = placeComboBox.getValue();
        System.out.println(place);
        if (floor != null) { //if the other combo box has a value it will have to be checked
            if (floor.compareTo("All Floors") == 0) {
                createGraphGroupPlace(buildingMap.getallNodesByPlace(place));
            } else if (place.compareTo("Indoor") == 0) {
                 createGraphGroupFloorAndPlace(buildingMap.getNodesByPlaceandFloor(Integer.parseInt(floor), "Indoor"));
            } else {
                createGraphGroupFloorAndPlace(buildingMap.getNodesByPlaceandFloor(Integer.parseInt(floor), "Outdoor"));
            }
        } else {
            if (place.compareTo("Indoor") == 0) {
                createGraphGroupPlace(buildingMap.getallNodesByPlace("Indoor"));
            } else {
                createGraphGroupPlace(buildingMap.getallNodesByPlace("Outdoor"));
            }
        }
    }

    /**
     * cuida da procura de salas entre duas datas, dá split ás datas e ás horas
     * @param actionEvent
     */
    public void handleSearchRooms2Dates(ActionEvent actionEvent) {
        u = universityST.get("UFP");
        String x = searchRoom2DatesTextField.getText();
        String[] dates = x.split(" ");
        String[] date1 = dates[0].split("/");
        String[] hour1 = dates[1].split(":");
        Date d = new Date(Integer.parseInt(date1[0]), Integer.parseInt(date1[1]), Integer.parseInt(date1[2]), Integer.parseInt(hour1[0]), Integer.parseInt(hour1[1]));
        String[] date2 = dates[2].split("/");
        String[] hour2 = dates[3].split(":");
        Date d1 = new Date(Integer.parseInt(date2[0]), Integer.parseInt(date2[1]), Integer.parseInt(date2[2]), Integer.parseInt(hour2[0]), Integer.parseInt(hour2[1]));
        ArrayList<Classroom> classroomArrayList = u.searchRoomsFreeBetweenDates(d, d1);
        if (!classroomArrayList.isEmpty()) {
            String s = " ";
            for (Classroom c : classroomArrayList) {
                s = c.toString().concat("\r\n" + s);
            }
            displayRooms2Dates.setText(s);
        } else {
            displayRooms2Dates.setText("Not Found");
        }
    }

    /**
     * muda o nome do student
     * @param studentStringCellEditEvent evento de quando se dá doubleclick para editar
     */
    public void handleChangeStudentNameAction(TableColumn.CellEditEvent<Student, String> studentStringCellEditEvent) {
        u = universityST.get("UFP");
        studentStringCellEditEvent.getRowValue().setName(studentStringCellEditEvent.getNewValue());
        u.editStudent(studentStringCellEditEvent.getRowValue().getNumber(), studentStringCellEditEvent.getRowValue().getNumber(), studentStringCellEditEvent.getNewValue(), studentStringCellEditEvent.getRowValue().getEmail(), studentStringCellEditEvent.getRowValue().getRegistration());
    }

    /**
     * muda o numero da classroom
     * @param cellEditEvent evento de quando dá douleclick para editar
     */
    public void handleChangeClassroomNumberAction(TableColumn.CellEditEvent <Classroom,Integer> cellEditEvent) {
        u = universityST.get("UFP");
        int n = cellEditEvent.getRowValue().getNumber();
        cellEditEvent.getRowValue().setNumber(cellEditEvent.getRowValue().getNumber());
        u.editClassroom(n,cellEditEvent.getNewValue(),cellEditEvent.getRowValue().getSockets(),cellEditEvent.getRowValue().getLevel());
    }
    /**
     * muda o numero de sockets da classroom
     * @param cellEditEvent evento de quando dá douleclick para editar
     */
    public void handleChangeClassroomSocketsAction(TableColumn.CellEditEvent <Classroom,Integer> cellEditEvent) {
        u = universityST.get("UFP");
        int n = cellEditEvent.getNewValue();
        cellEditEvent.getRowValue().setSockets(cellEditEvent.getNewValue());
        u.editClassroom(cellEditEvent.getRowValue().getNumber(),cellEditEvent.getRowValue().getNumber(),n,cellEditEvent.getRowValue().getLevel());
    }
    /**
     * muda o andar da classroom
     * @param cellEditEvent evento de quando dá douleclick para editar
     */
    public void handleChangeClassroomFloorAction(TableColumn.CellEditEvent<Classroom, Integer> cellEditEvent) {
        u = universityST.get("UFP");
        int n = cellEditEvent.getNewValue();
        cellEditEvent.getRowValue().setLevel(cellEditEvent.getNewValue());
        u.editClassroom(cellEditEvent.getRowValue().getNumber(),cellEditEvent.getRowValue().getNumber(),cellEditEvent.getRowValue().getSockets(),n);
    }
    /**
     * muda o email da estudatne
     * @param studentcellEditEvent evento de quando dá douleclick para editar
     */
    public void handleChangeStudentEmailAction(TableColumn.CellEditEvent<Student, Integer> studentcellEditEvent) {
        u = universityST.get("UFP");
        studentcellEditEvent.getRowValue().setNumber(studentcellEditEvent.getNewValue());
        u.editStudent(studentcellEditEvent.getRowValue().getNumber(), studentcellEditEvent.getRowValue().getNumber(), studentcellEditEvent.getRowValue().getName(), studentcellEditEvent.getRowValue().getEmail(), studentcellEditEvent.getRowValue().getRegistration());

    }
    /**
     * muda o numero do estudante - refresh a tabela da groupclass pois contem estudantes
     * @param cellEditEvent evento de quando dá douleclick para editar
     */
    public void handleChangeStudentNumberAction(TableColumn.CellEditEvent<Student, Integer> cellEditEvent) {
        u = universityST.get("UFP");
        int n = cellEditEvent.getNewValue();
        int v = cellEditEvent.getRowValue().getNumber();
        cellEditEvent.getRowValue().setNumber(cellEditEvent.getNewValue());
        u.editStudent(v, n, cellEditEvent.getRowValue().getName(), cellEditEvent.getRowValue().getEmail(), cellEditEvent.getRowValue().getRegistration());
        groupClassTable.refresh();

    }
    /**
     * muda o username do professor
     * @param teacherStringCellEditEventcell evento de quando dá douleclick para editar
     */
    public void handleChangeTeacherUsernameAction(TableColumn.CellEditEvent<Teacher, String> teacherStringCellEditEventcell) {
        u = universityST.get("UFP");
        String username = teacherStringCellEditEventcell.getRowValue().getUsername();
        String nunsername = teacherStringCellEditEventcell.getNewValue();
        teacherStringCellEditEventcell.getRowValue().setUsername(teacherStringCellEditEventcell.getNewValue());
        u.editTeacher(username, nunsername, teacherStringCellEditEventcell.getRowValue().getName(), teacherStringCellEditEventcell.getRowValue().getEmail());
        groupClassTable.refresh();
    }
    /**
     * muda o nome do professor
     * @param teacherStringCellEditEventcell evento de quando dá douleclick para editar
     */
    public void handleChangeTeacherNameAction(TableColumn.CellEditEvent<Teacher, String> teacherStringCellEditEventcell) {
        u = universityST.get("UFP");
        String username = teacherStringCellEditEventcell.getRowValue().getUsername();
        teacherStringCellEditEventcell.getRowValue().setName(teacherStringCellEditEventcell.getNewValue());
        u.editTeacher(username, username, teacherStringCellEditEventcell.getNewValue(), teacherStringCellEditEventcell.getRowValue().getEmail());
        groupClassTable.refresh();
        teachersTable.refresh();
    }
    /**
     * muda o email do professor
     * @param teacherStringCellEditEventcell evento de quando dá douleclick para editar
     */
    public void handleChangeTeacherEmailAction(TableColumn.CellEditEvent<Teacher, String> teacherStringCellEditEventcell) {
        u = universityST.get("UFP");
        String username = teacherStringCellEditEventcell.getRowValue().getUsername();
        teacherStringCellEditEventcell.getRowValue().setEmail(teacherStringCellEditEventcell.getNewValue());
        u.editTeacher(username, username, teacherStringCellEditEventcell.getRowValue().getName(), teacherStringCellEditEventcell.getNewValue());
        groupClassTable.refresh();
        teachersTable.refresh();
    }
    /**
     * muda o nome da disciplina
     * @param disciplineStringCellEditEvent evento de quando dá douleclick para editar
     */
    public void handleChangeDisciplineName(TableColumn.CellEditEvent<Discipline, String> disciplineStringCellEditEvent) {
        u = universityST.get("UFP");
        String name = disciplineStringCellEditEvent.getRowValue().getName();
        disciplineStringCellEditEvent.getRowValue().setName(disciplineStringCellEditEvent.getNewValue());
        u.editDiscipline(name, disciplineStringCellEditEvent.getNewValue());
        disciplinesTable.refresh();
        groupClassTable.refresh();
    }
    /**
     * muda o nome da turma
     * @param groupClassStringCellEditEvent evento de quando dá douleclick para editar
     */
    public void handleChangeGroupClassNameAction(TableColumn.CellEditEvent<GroupClass, String> groupClassStringCellEditEvent) {
        u = universityST.get("UFP");
        String name = groupClassStringCellEditEvent.getRowValue().getName();
        groupClassStringCellEditEvent.getRowValue().setName(groupClassStringCellEditEvent.getNewValue());
        u.editGroupClass(name,groupClassStringCellEditEvent.getNewValue());
        disciplinesTable.refresh();
        groupClassTable.refresh();
        studentsTable.refresh();
        teachersTable.refresh();
    }

    /**
     * adiciona uma groupclass á tabela e universidade
     * @param actionEvent
     */
    public void handleAddGroupClass(ActionEvent actionEvent) {
        u = universityST.get("UFP");
        try {
            String groupClass = groupClassAddField.getText(); //Vai buscar o nome da groupclass que queremos adicionar
            GroupClass rem = u.getGroupclass().get(groupClass);
            String teacherUsername = groupClassTeacherAddField.getText(); //vai buscar o username do professor que queremos adicionar
            String discipline = groupClassDisciplineAddField.getText();
            ArrayList<Integer> studentsNumber = new ArrayList<>();
            String studentaux = groupClassStudentsAddField.getText(); //Vai buscar os numeros dos aluno que queremos adicionar que vao estar divididos por espaços
            if (!studentaux.isEmpty()) {
                String[] x = studentaux.split(" ");
                for (int i = 0; i < x.length; i++) {
                    studentsNumber.add(Integer.parseInt(x[i]));
                }
            }
            //Começar a associar umas coisas ás outas e ir buscar á universidade
            GroupClass g = u.getGroupclass().get(groupClass);
            Teacher t = u.getTeachers().get(teacherUsername);
            GroupClass g1 = new GroupClass(groupClass);
            Discipline d = u.searchDiscipline(discipline);
            d.printGroupClasses();
            ArrayList<Student> studentsArrayList = new ArrayList<>();
            for (Integer i : studentsNumber) {
                studentsArrayList.add(u.getStudents().get(i));
            }
            if (g == null) {
                u.addGroupClass(g1);
                g1.associateDisciplie(d);
                d.associateGroupClassToDiscipline(g1);
                if (t != null) {
                    t.associateTeacher2GroupClass(g1);
                    g1.associateTeacher(t);
                    if (!studentsArrayList.isEmpty()) {
                        for (Student s : studentsArrayList) {
                            g1.addStudent(s);
                            s.associateGroupclass(g1);

                        }
                    }
                }
                d.printGroupClasses();
                groupClassTable.getItems().add(g1);
            } else {
                g.associateDisciplie(d);
                d.associateGroupClassToDiscipline(g);
                if (t != null) {
                    t.associateTeacher2GroupClass(g);
                    g.associateTeacher(t);
                    if (!studentsArrayList.isEmpty()) {
                        for (Student s : studentsArrayList) {
                            g.addStudent(s);
                            s.associateGroupclass(g);
                        }
                    }
                }
                groupClassTable.getItems().remove(rem);
                groupClassTable.getItems().add(g);
            }
            groupClassTable.refresh();
            teachersTable.refresh();
            studentsTable.refresh();
            disciplinesTable.refresh();
        } catch (RuntimeException e) {
            System.out.println("Please fill every Space, or your Student/Teacher/Discipline doesn't exist in the University");
        }
    }

    /**
     * cuida da escolha do aluno
     * @param actionEvent
     */
    public void handleStudentChoiceAction(ActionEvent actionEvent) {
        Student s = studentComboBox.getValue(); //Vai buscar o aluno selecionado
        if (s != null) {
            Node n = findClosestNode(s.getX(), s.getY(), s.getFloor());
            if (destinationNodeCombobox.getValue() != null) {
                findShortestPath(n, destinationNodeCombobox.getValue());
               // findFastesPath(n, destinationNodeCombobox.getValue());
            }
        }
    }

    /**
     * cuida da escolha do professor na combobox
     * @param actionEvent
     */
    public void handleTeacherComboBoxChoice(ActionEvent actionEvent) {
        Teacher t = teacherComboBox.getValue();
        if(t != null) {
            Node n = findClosestNode(t.getX(),t.getY(),t.getFloor());
            if(destinationNodeCombobox.getValue() != null) {
               // findShortestPath(n,destinationNodeCombobox.getValue());

                findFastesPath(n, destinationNodeCombobox.getValue());
            }
        }
    }

    /**
     * cuida da escolha do nó destino
     * @param actionEvent
     */
    public void handleDestionationNodeChoice(ActionEvent actionEvent) {
        Student s = studentComboBox.getValue(); //Vai buscar o aluno selecionado
        graphGroup.getChildren().clear();
        createGraphGroup(buildingMap.getAllNodes()); //para desaparecer as linhas anteriores verdes que representam o caminho curto
        if (s != null) {
            Node n = findClosestNode(s.getX(), s.getY(), s.getFloor());
            if (destinationNodeCombobox.getValue() != null) {
                findShortestPath(n, destinationNodeCombobox.getValue());
                //findFastesPath(n, destinationNodeCombobox.getValue());
            }
        } else {
            Teacher t = teacherComboBox.getValue();
            if (t != null) {
                Node n = findClosestNode(t.getX(), t.getY(), t.getFloor());
                if (destinationNodeCombobox.getValue() != null) {
                    //findShortestPath(n, destinationNodeCombobox.getValue());
                    findFastesPath(n, destinationNodeCombobox.getValue());
                }
            }
        }
    }

    /**
     * cuida da adicoa do estudante á table a e Universidade
     * @param actionEvent
     */
    public void handleAddStudentAction(ActionEvent actionEvent) {
        u = universityST.get("UFP");
        try {
            String a = studentRegistrationTextField.getText(); //data de registo
            String[] c = a.split(" ");
            Date d = new Date(Integer.parseInt(c[0]), Integer.parseInt(c[1]), Integer.parseInt(c[2]), Integer.parseInt(c[3]), Integer.parseInt(c[4]));
            String z = studentCordsTextField.getText();
            String[] w = z.split(" ");
            Student s = new Student(Integer.parseInt(studentNumberTextField.getText()), d, studentEmailTextField.getText(), studentNameTextField.getText(), Double.parseDouble(w[0]), Double.parseDouble(w[1]),Integer.parseInt(w[2]));
            String x = studentGroupclassField.getText(); //Get the groupclasses of the student
            u.addStudent(s);
            if (!x.isEmpty()) { // se contiver alguma groupclass
                String[] s1 = x.split(" "); //as diferentes turmas são divididas por espaço
                for (String l : s1) {
                    GroupClass g = new GroupClass(l);
                    if (u.getGroupclass().get(l) != null) { //Se a turma que quero adicionar já existe na Universidade
                        if (groupClassTable.getItems().contains(u.getGroupclass().get(l))) { //Se a tabela de turmas já contiver a turma que quero adicionar
                            groupClassTable.getItems().remove(u.getGroupclass().get(l)); //vou querer remover a turma anterior
                            u.getGroupclass().get(l).addStudent(s); // adiciono o student há turma já existente
                            s.associateGroupclass(u.getGroupclass().get(l)); //adiciono a turma ao student
                            groupClassTable.getItems().add(u.getGroupclass().get(l)); //adiciono a nova turma á tabela de turmas
                        }
                    } else {
                        u.addGroupClass(g);
                        g.addStudent(s);
                        s.associateGroupclass(g);
                        groupClassTable.getItems().add(g);
                    }
                }
            }
            studentsTable.getItems().add(s);
            groupClassTable.refresh();
        } catch (RuntimeException e) {
            System.out.println("Please Fill Every Space");
        }
    }

    /**
     * cuida da adiçao do professor
     * @param actionEvent
     */
    public void handleAddTeacher(ActionEvent actionEvent) {
        u = universityST.get("UFP");
        try {
            String a = teacherCordsTextField.getText();
            String[] z = a.split(" ");
            Teacher t = new Teacher(teacherNameFieldAdd.getText(), teacherEmailFieldAdd.getText(), teacherUsernameFieldAdd.getText(),Double.parseDouble(z[0]), Double.parseDouble(z[1]),Integer.parseInt(z[2]));
            u.addTeacher(t);
            String x = teacherGroupClassFIeldAdd.getText();
            if (!x.isEmpty()) { // se contiver alguma groupclass
                String[] s1 = x.split(" "); //as diferentes turmas são divididas por espaço
                for (String l : s1) {
                    GroupClass g = new GroupClass(l);
                    if (u.getGroupclass().get(l) != null) { //Se a turma que quero adicionar já existe na Universidade
                        if (groupClassTable.getItems().contains(u.getGroupclass().get(l))) { //Se a tabela de turmas já contiver a turma que quero adicionar
                            groupClassTable.getItems().remove(u.getGroupclass().get(l)); //vou querer remover a turma anterior
                            u.getGroupclass().get(l).setTeacher(t);
                            t.associateTeacher2GroupClass(u.getGroupclass().get(l));
                            groupClassTable.getItems().add(u.getGroupclass().get(l));
                            //apenas era necessario um refresh
                        }
                    } else {
                        u.addGroupClass(g);
                        g.setTeacher(t);
                        t.associateTeacher2GroupClass(g);
                        groupClassTable.getItems().add(g);
                    }
                }
            }
            teachersTable.getItems().add(t);
            groupClassTable.refresh();
        } catch (RuntimeException e) {
            System.out.println("Please Fill Every Space"); //exception for when one of the fields is empty
        }
    }

    /**
     * cuida da adiçao da disciplina
     * @param actionEvent
     */
    public void handleAddDisciplineAction(ActionEvent actionEvent) {
        u = universityST.get("UFP");
        try {
            Discipline d = new Discipline(disciplineNameFieldadd.getText());
            String x = groupclassDisciplineField.getText();
            if (!x.isEmpty()) {
                String[] s = x.split(" ");
                for (String l : s) {
                    GroupClass g = new GroupClass(l);
                    g.associateDisciplie(d);
                    d.associateGroupClassToDiscipline(g);
                    if (u.getGroupclass().get(l) != null) {
                        if (groupClassTable.getItems().contains(u.getGroupclass().get(l))) {
                            u.getGroupclass().get(l).associateDisciplie(d);
                            d.associateGroupClassToDiscipline(u.getGroupclass().get(l));
                            groupClassTable.getItems().add(u.getGroupclass().get(l));
                        }
                    } else {
                        u.addGroupClass(g);
                        g.associateDisciplie(d);
                        d.associateGroupClassToDiscipline(g);
                        groupClassTable.getItems().add(g);
                        groupClassTable.refresh();
                    }
                }
            }
            disciplinesTable.getItems().add(d);
            disciplinesTable.refresh();
            groupClassTable.refresh();
        } catch (RuntimeException e) {
            System.out.println("Please Fill every Space");
        }
    }

    /**
     * cuida da adiçao da sala
     * @param actionEvent
     */
    public void handleAddClassroom(ActionEvent actionEvent) {
        u = universityST.get("UFP");
        try {
            Classroom c = new Classroom(Integer.parseInt(classroomNumberField.getText()), Short.parseShort(classroomSocketsField.getText()), Integer.parseInt(classroomFloorField.getText()));
            u.addclassroom(c);
            classroomsTable.getItems().addAll(c);

        } catch (RuntimeException e) {
            System.out.println("Please Fill every Space");
        }
    }

    /**
     * cuida da procura as turmas dos professores
     * @param actionEvent
     */
    public void handleSearchTeacherGroupClass(ActionEvent actionEvent) {
        u = universityST.get("UFP");
        ArrayList<GroupClass> groupClasses = u.searchTeachersGroupClasses(teacherUsernameField.getText());
        displayTeacherGroupClasses.clear();
        String s = " ";
        if (groupClasses != null) {
            if (!groupClasses.isEmpty()) {
                for (GroupClass g : groupClasses) {
                    s = g.getName().concat("\r\n" + s);
                }
                System.out.println(s);
                displayTeacherGroupClasses.setText(s);
            } else {
                displayTeacherGroupClasses.setText("Not Found");
            }
        }
    }

    /**
     * cuida da procura de todos os professores de uma disciplina
     * @param actionEvent
     */
    public void handleSearchAllTeachersDiscipline(ActionEvent actionEvent) {
        u = universityST.get("UFP");
        displayDisciplinesTeacher.clear();
        ArrayList<Teacher> teachers = u.searchAllTeachersDiscipline(disciplineNameField.getText());
        if (teachers != null) {
            if (!teachers.isEmpty()) {
                String s = " ";
                for (Teacher e : teachers) {
                    s = e.toString().concat("\r\n" + s);
                }
                displayDisciplinesTeacher.setText(s);
            } else {
                displayDisciplinesTeacher.setText("Not Found");
            }
        }

    }

    /**
     * cuida da procura de uma sala pelo numero de tomadas
     * @param actionEvent
     */
    public void handleSearchClasrromBySockets(ActionEvent actionEvent) {
        u = universityST.get("UFP");
        displayClassroomSockets.clear();
        ArrayList<Classroom> classrooms = u.searchClassroomsBySockets(Integer.parseInt(classroomsNumberField.getText()));
        if (classrooms != null) {
            if (!classrooms.isEmpty()) {
                String s = " ";
                for (Classroom c : classrooms) {
                    s = c.toString().concat("\r\n" + s);
                }
                displayClassroomSockets.setText(s);
            } else {
                displayClassroomSockets.setText("Not Found");
            }
        }
    }

    /**
     * cuida da procura de salas por andar
     * @param actionEvent
     */
    public void handleSearchClasrromByFloor(ActionEvent actionEvent) {
        u = universityST.get("UFP");
        displayClassroomFloor.clear();
        ArrayList<Classroom> classrooms = u.searchClassroomsByLevel(Integer.parseInt(classroomsFloorField.getText()));
        if (classrooms != null) {
            if (!classrooms.isEmpty()) {
                String s = " ";
                for (Classroom c : classrooms) {
                    s = c.toString().concat("\r\n" + s);
                }
                displayClassroomFloor.setText(s);
            } else {
                displayClassroomFloor.setText("Not Found");
            }
        }
    }

    /**
     * cuida da procura da sala por ocupaçap
     * @param actionEvent
     */
    public void handleSearchClasrromByOcupation(ActionEvent actionEvent) {
        u = universityST.get("UFP");
        displayClassroomOcupaion.clear();
        ArrayList<Classroom> classroomArrayList = u.searchClassroomsByOcupation(Boolean.parseBoolean(classroomsOcupationField.getText()));
        if (classroomArrayList != null) {
            if (!classroomArrayList.isEmpty()) {
                String s = " ";
                for (Classroom c : classroomArrayList) {
                    s = c.toString().concat("\r\n" + s);
                }
                displayClassroomOcupaion.setText(s);
            } else {
                displayClassroomOcupaion.setText("Not Found");
            }
        }
    }

    /**
     * cuida da remoçao de um estudante
     * @param actionEvent
     */
    public void handleRemoveStudentAction(ActionEvent actionEvent) {
        u = universityST.get("UFP");
        Student s = studentsTable.getSelectionModel().getSelectedItem();
        studentsTable.getItems().remove(studentsTable.getSelectionModel().getSelectedItem());
        u.removeAStudent(s.getNumber());
        studentsTable.refresh();
        groupClassTable.refresh();
    }

    /**
     * cuida da remoçao do professor
     * @param actionEvent
     */
    public void handleRemoveTeacherAction(ActionEvent actionEvent) {
        u = universityST.get("UFP");
        Teacher s = teachersTable.getSelectionModel().getSelectedItem();
        teachersTable.getItems().remove(teachersTable.getSelectionModel().getSelectedItem());
        u.removeATeacher(s.getUsername());
        teachersTable.refresh();
        groupClassTable.refresh();
    }

    /**
     * cuida da remoçao da sala
     * @param actionEvent
     */
    public void handleRemoveClassroomAction(ActionEvent actionEvent) {
        u = universityST.get("UFP");
        Classroom c = classroomsTable.getSelectionModel().getSelectedItem();
        classroomsTable.getItems().remove(classroomsTable.getSelectionModel().getSelectedItem());
        u.removeAClassroom(c.getNumber());
        classroomsTable.refresh();
    }

    /**
     * cuida da remoçao da disciplina
     * @param actionEvent
     */
    public void handleRemoveDisciplineAction(ActionEvent actionEvent) {
        u = universityST.get("UFP");
        Discipline d = disciplinesTable.getSelectionModel().getSelectedItem();
        disciplinesTable.getItems().remove(disciplinesTable.getSelectionModel().getSelectedItem());
        u.removeADiscipline(d.getName());
        disciplinesTable.refresh();
        groupClassTable.refresh();
    }

    /**
     * desliga o programa
     * @param actionEvent
     */
    public void handleExitAction(ActionEvent actionEvent) {
        System.exit(0);
    }

    /**
     * cuida de salvar para txt qnd clicamos no botao do menu
     * @param actionEvent
     */
    public void handleSaveTXTAction(ActionEvent actionEvent) {
        saveTXT(universityST);
        saveGraphTxtFile();
    }

    /**
     * cuida de salvar para binario qnd clicamos no botao do menu
     * @param actionEvent
     */
    public void handleSaveBinAction(ActionEvent actionEvent) {
        saveToBinFile();
        saveGraphBinFile();
    }

    /**
     * cuida de abrir o ficheiro binario e de preencher as coisas
     * @param actionEvent
     */
    public void handleOpenBinOnAction(ActionEvent actionEvent) {
        readFromBinFile();
        studentsTable.getItems().clear();
        for (Integer i : u.getStudents().keys()) {
            studentsTable.getItems().add(u.getStudents().get(i));
        }
        teachersTable.getItems().clear();
        for(String s : u.getTeachers().keys()) {
            teachersTable.getItems().add(u.getTeachers().get(s));
        }
        groupClassTable.getItems().clear();
        for(String s : u.getGroupclass().keys()) {
            groupClassTable.getItems().add(u.getGroupclass().get(s));
        }
        disciplinesTable.getItems().clear();
        for(String s : u.getGroupclass().keys()) {
          if(!disciplinesTable.getItems().contains(u.getGroupclass().get(s).getDiscipline())) {
              disciplinesTable.getItems().add(u.getGroupclass().get(s).getDiscipline());
          }
        }
        classroomsTable.getItems().clear();
        for(Integer i : u.getClassroom().keys()) {
            classroomsTable.getItems().add(u.getClassroom().get(i));
        }
        fillStudentComboBox();
        fillTeacherComboBox();
    }

    /**
     * le os alunos dos ficheiro e guarda na universidade deles
     * @param universityST st de universidades para guardar universidade
     * @return retorna os alunos lidos do ficheiro
     */
    private static ArrayList<Student> readStudentFromFile(SeparateChainingHashST<String, University> universityST) {
        ArrayList<Student> students = new ArrayList<>();
        In in = new In(".//data//students.txt");
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
            String[] cords = fields[4].split("-");
            Date date = new Date(Integer.parseInt(dateStringaux[0]), Integer.parseInt(dateStringaux[1]), Integer.parseInt(dateStringaux[2]), Integer.parseInt(timeString[0]), Integer.parseInt(timeString[1]));
            Student s = new Student(Integer.parseInt(number), date, email, name, Double.parseDouble(cords[0]), Double.parseDouble(cords[1]),Integer.parseInt(cords[2]));
            for (String info : GroupClassList) {
                for (String si : universityST.keys()) {
                    for (String sl : universityST.get(si).getGroupclass().keys()) {
                        GroupClass g = universityST.get(si).getGroupclass().get(sl);
                        if (g.getName().compareTo(info) == 0) {
                            g.addStudent(s);
                            s.associateGroupclass(g);
                        }
                    }
                }
            }
            students.add(s);
        }
        return students;
    }

    /**
     *
     * @param path  caminho para o ficheiro das turmas
     * @param universityST universidade st para guardar universidade e as groupclasses
     * @return turmas adicionadas
     */
    private static ArrayList<GroupClass> readGroupClassFromFile(String path, SeparateChainingHashST<String, University> universityST) {
        ArrayList<GroupClass> groupClasses = new ArrayList<>();
        In in = new In(path);
        while (!in.isEmpty()) {
            String line = in.readLine();
            String[] fields = line.split(";");
            GroupClass g = new GroupClass(fields[0]);
            University u = new University(fields[1]);
            Discipline d = new Discipline(fields[2]);
            if (!universityST.contains(u.getName())) { //Se esta univerisdade não existir na ST das universidades
                universityST.put(u.getName(), u); //Vou adicionar esta universidade
                g.setUniversity(universityST.get(u.getName()));
                universityST.get(u.getName()).addGroupClass(g);
                g.associateDisciplie(d);
                d.associateGroupClassToDiscipline(g);
            } else {
                universityST.get(u.getName()).addGroupClass(g);
                g.setUniversity(universityST.get(u.getName()));
                g.associateDisciplie(d);
                d.associateGroupClassToDiscipline(g);
            }
            groupClasses.add(g);
        }
        return groupClasses;
    }

    /**
     * le os professores do ficheiro
     * @param path caminho para ficheiro professor
     * @param universityST st de universidade para guardar a universidade e os professores
     * @return professores lidos
     */
    private static ArrayList<Teacher> readTeacherFromFile(String path, SeparateChainingHashST<String, University> universityST) {
        ArrayList<Teacher> teachers = new ArrayList<>();
        In in = new In(path);
        while (!in.isEmpty()) {
            String line = in.readLine();
            String[] fields = line.split(";");
            String name = fields[0];
            String email = fields[1];
            String username = fields[2];
            String[] GroupClassList = fields[4].split("-");
            String[] x = fields[3].split("-");
            Teacher t = new Teacher(name, email, username,Double.parseDouble(x[0]),Double.parseDouble(x[1]),Integer.parseInt(x[2]));
            for (String info : GroupClassList) {
                for (String si : universityST.keys()) {
                    for (String s : universityST.get(si).getGroupclass().keys()) {
                        GroupClass g = universityST.get(si).getGroupclass().get(s);
                        if (g.getName().compareTo(info) == 0) {
                            g.associateTeacher(t);
                            t.associateTeacher2GroupClass(g);
                        }

                    }
                }
            }
            teachers.add(t);
        }
        return teachers;
    }

    /**
     * le as salas dos ficheiro
     * @param path caminho para as salas
     * @param universityST universidade para guardar as salas
     * @return salas lidas
     */
    private static ArrayList<Classroom> readClassroomFromFile(String path, SeparateChainingHashST<String, University> universityST) {
        ArrayList<Classroom> classrooms = new ArrayList<>();
        In in = new In(path);
        while (!in.isEmpty()) {
            String line = in.readLine();
            String[] fields = line.split(";");
            String number = fields[0];
            String sockets = fields[1];
            String level = fields[2];
            University u = new University(fields[3]);
            projeto_1.Classroom c = new Classroom(Integer.parseInt(number), Short.parseShort(sockets), Integer.parseInt(level));
            if (!universityST.contains(u.getName())) {
                universityST.put(u.getName(), u);
            }
            if (!universityST.get(u.getName()).getClassroom().contains(c.getNumber())) {
                universityST.get(u.getName()).getClassroom().put(c.getNumber(), c);
            }
            classrooms.add(c);
        }
        return classrooms;
    }

    /**
     * le as disciplinas do ficheiro
     * @param universitST universidade para guardar as diciplinas
     * @param path caminho para ficheiro
     * @return disciplinas lidas
     */
    private static ArrayList<Discipline> readDisciplineFromFile(SeparateChainingHashST<String, University> universitST, String path) {
        ArrayList<Discipline> disciplines = new ArrayList<>();
        In in = new In(path);
        while (!in.isEmpty()) {
            String line = in.readLine();
            String[] fields = line.split(";");
            Discipline d = new Discipline(fields[0]);
            String[] f = fields[1].split("-");
            for (String value : f) {
                GroupClass g = new GroupClass(value);
                for (String s : universitST.keys()) {
                    if (universitST.get(s).getGroupclass().contains(g.getName())) {
                        g.associateDisciplie(d);
                        d.associateGroupClassToDiscipline(g);
                    } else {
                        universitST.get(s).addGroupClass(g);
                        g.associateDisciplie(d);
                        d.associateGroupClassToDiscipline(g);
                    }
                }
            }
            disciplines.add(d);
        }
        return disciplines;
    }

    /**
     * guardar em ficheiro txt
     * @param universityST para guardar tudo no ficheiro
     */
    private static void saveTXT(SeparateChainingHashST<String, University> universityST) {
        try {
            for (String si : universityST.keys()) {
                if (!universityST.get(si).getTeachers().isEmpty()) {
                    BufferedWriter writer = new BufferedWriter(new FileWriter(".//data//teacher.txt//")); //append true
                    for (String s : universityST.get(si).getTeachers().keys()) {
                        writer.write(universityST.get(si).getTeachers().get(s).getName());
                        writer.write(";");
                        writer.write(universityST.get(si).getTeachers().get(s).getEmail());
                        writer.write(";");
                        writer.write(universityST.get(si).getTeachers().get(s).getUsername());
                        writer.write(";");
                        writer.write(String.valueOf(universityST.get(si).getTeachers().get(s).getX()));
                        writer.write("-");
                        writer.write(String.valueOf(universityST.get(si).getTeachers().get(s).getY()));
                        writer.write("-");
                        writer.write(String.valueOf(universityST.get(si).getTeachers().get(s).getFloor()));
                        writer.write(";");
                        if (!universityST.get(si).getTeachers().get(s).getGroupclass().isEmpty()) {
                            for (String sa : universityST.get(si).getTeachers().get(s).getGroupclass().keys()) {
                                writer.write(universityST.get(si).getTeachers().get(s).getGroupclass().get(sa).getName());
                                writer.write("-");
                            }
                        }
                        writer.newLine();

                    }
                    writer.close();
                }
                if (!universityST.get(si).getStudents().isEmpty()) {
                    BufferedWriter writer = new BufferedWriter(new FileWriter(".//data//students.txt")); //append true
                    for (Integer n : universityST.get(si).getStudents().keys()) {
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
                        writer.write(String.valueOf(universityST.get(si).getStudents().get(n).getX()));
                        writer.write("-");
                        writer.write(String.valueOf(universityST.get(si).getStudents().get(n).getY()));
                        writer.write("-");
                        writer.write(String.valueOf(universityST.get(si).getStudents().get(n).getFloor()));
                        writer.write(";");
                        if (!universityST.get(si).getStudents().get(n).getGroupclass().isEmpty()) {
                            for (String s : universityST.get(si).getStudents().get(n).getGroupclass().keys()) {
                                writer.write(universityST.get(si).getStudents().get(n).getGroupclass().get(s).getName());
                                writer.write("-");
                            }
                        }
                        writer.newLine();
                    }
                    writer.close();
                }
                if (!universityST.get(si).getClassroom().isEmpty()) {
                    BufferedWriter writer = new BufferedWriter(new FileWriter(".//data//classroom.txt")); //append true
                    for (Integer n : universityST.get(si).getClassroom().keys()) {
                        Classroom c = universityST.get(si).getClassroom().get(n);
                        writer.write(String.valueOf(c.getNumber()));
                        writer.write(";");
                        writer.write(String.valueOf(c.getSockets()));
                        writer.write(";");
                        writer.write(String.valueOf(c.getLevel()));
                        writer.write(";");
                        writer.write(universityST.get(si).getName());
                        writer.newLine();
                    }
                    writer.close();
                }
                if (!universityST.get(si).getGroupclass().isEmpty()) {
                    BufferedWriter writer = new BufferedWriter(new FileWriter(".//data//groupclass.txt")); //append true
                    for (String s : universityST.get(si).getGroupclass().keys()) {
                        GroupClass g = universityST.get(si).getGroupclass().get(s);
                        writer.write(g.getName());
                        writer.write(";");
                        writer.write(g.getUniversity().getName());
                        writer.write(";");
                        writer.write(g.getDiscipline().getName());
                        writer.newLine();
                    }
                    writer.close();
                }
                if (!universityST.get(si).getGroupclass().isEmpty()) {
                    BufferedWriter writer = new BufferedWriter(new FileWriter(".//data//schedule.txt")); //append true
                    for (String s : universityST.get(si).getGroupclass().keys()) {
                        if (universityST.get(si).getGroupclass().get(s).getSchedule() != null) {
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
                        }
                    }
                    writer.close();
                }
                ArrayList<String> dnames = new ArrayList<>();
                int mark = 0;
                if (!universityST.get(si).getGroupclass().isEmpty()) {
                    BufferedWriter writer = new BufferedWriter(new FileWriter(".//data//discipline.txt")); //append true
                    for (String s : universityST.get(si).getGroupclass().keys()) { //Percorrer todas as turmas da universidade
                        if (universityST.get(si).getGroupclass().get(s).getDiscipline() != null) { // se essa turma tiver uma disciplina
                            String dname = universityST.get(si).getGroupclass().get(s).getDiscipline().getName(); //ir buscar o nome da disciplina
                            if (!dnames.contains(dname)) {
                                writer.write(universityST.get(si).getGroupclass().get(s).getDiscipline().getName()); //escrever o nome da disciplina
                                writer.write(";"); //separador
                                for (String x : universityST.get(si).getGroupclass().keys()) {//percorrer novamente as turmas para encontrar as que tem esta disciplina
                                    if (dname.compareTo(universityST.get(si).getGroupclass().get(x).getDiscipline().getName()) == 0) {
                                        writer.write(universityST.get(si).getGroupclass().get(x).getName());
                                        writer.write("-");
                                        mark = 1;
                                    }
                                }
                            }
                            dnames.add(dname);
                        }
                        if (mark == 1) {
                            writer.newLine();
                        }
                        mark = 0;
                    }
                    writer.close();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * le horarios do ficheiro
     * @param universityST universidade st para carregar os schdules para
     * @return horarios adicionados
     */
    private static ArrayList<Schedule> readSchedulesFromFile( SeparateChainingHashST<String, University> universityST) { //VOLTAR AQUI AMANHA
        In in = new In(".//data//schedule.txt");
        ArrayList<Schedule> schedules = new ArrayList<>();
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
            int number = Integer.parseInt(fields[5]); //Sala
            GroupClass g = new GroupClass(fields[6]); //Turma
            University u = new University(fields[8]);
            String teacher = fields[7];
            u.addGroupClass(g);
            g.setUniversity(u);
            if(!universityST.contains(u.getName())) {
                universityST.put(u.getName(),u);
            }
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
                schedules.add(s);
        }
        return schedules;
    }

    /**
     * le a informçao do ficheiro binario
     */
    private void readFromBinFile() {
        File f = new File(".//data//univerisity.bin");
        try {
            FileInputStream fis = new FileInputStream(f);
            ObjectInputStream ois = new ObjectInputStream(fis);
            u = (University) ois.readObject();
            universityST.put(u.getName(),u); //ESQUECIMENTO .
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * guarda a info no ficheiro binario
     */
    private void saveToBinFile() {
        u = universityST.get("UFP");
        File f = new File(".//data//univerisity.bin");
        try {
            FileOutputStream fos = new FileOutputStream(f);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(u);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * encontra o caminho mais curto do node source até node s
     * @param source no de origem
     * @param s no de destino
     * @return retorna as edges do caminho mais curto encontrado
     */
    private ArrayList<Edge> findShortestPath(Node source, Node s) {
        ArrayList<Edge> edge = new ArrayList<>();
        for (int v = 0; v < this.buildingMap.getG().V(); v++) {
            if (source.compareTo(this.buildingMap.getNodes().nameOf(v)) == 0) { //Encontrar o nó(no symbol Graph) que queremos que seja o nó de partida
                for (int i = 0; i < this.buildingMap.getG().V(); i++) {
                    if (s.compareTo(this.buildingMap.getNodes().nameOf(i)) == 0) { // Encontrar o nó de destino, que queresmos encontrar
                        DijkstraUndirectedSP sp = new DijkstraUndirectedSP(this.buildingMap.getG(), v);
                        if (sp.hasPathTo(i)) {
                            for (Edge e : sp.pathTo(i)) {
                                System.out.println(e + " ");
                                Line l = new Line(buildingMap.getNode(e.getV()).getX(),buildingMap.getNode(e.getV()).getY(),buildingMap.getNode(e.getW()).getX(),buildingMap.getNode(e.getW()).getY());
                                l.setStrokeWidth(4.0);
                                l.setStroke(Color.LIGHTGREEN);
                                graphGroup.getChildren().add(l);
                                edge.add(e);
                            }
                        }
                    }
                }
            }
        }
        return edge;
    }

    /**
     * encontra o caminho mais rapido desde source até s
     * @param source no de origem
     * @param s no de destino
     * @return arraylist de edges
     */
    private ArrayList<Edge> findFastesPath(Node source, Node s) {
        ArrayList<Edge> edges = new ArrayList<>();
        for (int v = 0; v < this.buildingMap.getG().V(); v++) {
            if (source.compareTo(this.buildingMap.getNodes().nameOf(v)) == 0) { //Encontrar o nó(no symbol Graph) que queremos que seja o nó de partida
                for (int i = 0; i < this.buildingMap.getG().V(); i++) {
                    if (s.compareTo(this.buildingMap.getNodes().nameOf(i)) == 0) { // Encontrar o nó de destino, que queresmos encontrar
                        DijkstraUndirectedSP_W2 sp = new DijkstraUndirectedSP_W2(this.buildingMap.getG(), v);
                        if (sp.hasPathTo(i)) {      //Se ouver caminho
                            for(Edge e :sp.pathTo(i)) {         //percorre todas as aretas desse caminho
                                System.out.println(e + " ");
                                Line l = new Line(buildingMap.getNode(e.getV()).getX(),buildingMap.getNode(e.getV()).getY(),buildingMap.getNode(e.getW()).getX(),buildingMap.getNode(e.getW()).getY()); //linha verde
                                l.setStrokeWidth(4.0);
                                //l.setStroke(Color.LIGHTGREEN);
                                l.setStroke(Color.RED);
                                graphGroup.getChildren().add(l);
                                edges.add(e);
                            }
                        }
                    }
                }
            }
        }
        return edges;
    }

    /**
     * caminho mais curto para uma saida
     * @param source nó de origem
     */
    private void findShortestPathToOutdoor(Node source) {
        ArrayList<Double> disTo = new ArrayList<>(); //This will have all of the distances
        ArrayList<Edge> edges = new ArrayList<>(); //This will have all of the edges
        ArrayList<Integer> places = new ArrayList<>(); //This will have the number of edges
        int x = 0;
        for (int v = 0; v < this.buildingMap.getG().V(); v++) {
            if (source.compareTo(this.buildingMap.getNodes().nameOf(v)) == 0) { //Encontrar o nó(no symbol Graph) que queremos que seja o nó de partida
                for (int i = 0; i < this.buildingMap.getG().V(); i++) {
                    if (this.buildingMap.getNode(i).getName().contains("Exit")) {
                        DijkstraUndirectedSP sp = new DijkstraUndirectedSP(this.buildingMap.getG(), v);
                        if (sp.hasPathTo(i)) {
                            disTo.add(sp.distTo(i));
                            for (Edge e : sp.pathTo(i)) {
                                edges.add(e);
                                x++;
                            }
                            places.add(x);
                            x = 0;
                        }
                    }
                }
            }
        }
        int marked = 0;
        Double min = Collections.min(disTo);
        for (int i = 0; i < disTo.size(); i++) { //Dá me a posiçao que quero para is buscar ao array de numero de arestas
            if (min.equals(disTo.get(i))) {
                marked = i;
            }
        }
        int pos = 0;
        int edgenumber = places.get(marked); // numero de edges
        for (int i = 0; i < marked; i++) { //soma os numeros de arestas anteriores para que saiba a posicao a começar no array de arestas
            pos += places.get(i);
        }
        for (int i = pos; i < pos + edgenumber; i++) {
            System.out.println(edges.get(i) + " ");
            Line l = new Line(buildingMap.getNode(edges.get(i).getV()).getX(), buildingMap.getNode(edges.get(i).getV()).getY(), buildingMap.getNode(edges.get(i).getW()).getX(), buildingMap.getNode(edges.get(i).getW()).getY());
            l.setStrokeWidth(3.0);
            l.setStroke(Color.LIGHTGREEN);
            graphGroup.getChildren().add(l);
        }
    }

    /**
     * ve se o grafo é conexo
     */
    private void checkGraphConnected() {
        DepthFirstSearch_P search = new DepthFirstSearch_P(this.buildingMap.getG(), 0);
        if (search.count() != this.buildingMap.getG().V()) StdOut.println("NOT connected");
        else StdOut.println("connected");
    }

    /**
     * encontra o no mais proximo a estas cordenadas
     * @param x cordenada x
     * @param y cordenada y
     * @param floor andar que está
     * @return retorna o nó mais perto
     */

    private Node findClosestNode(double x, double y, int floor) {
        ArrayList<Node> nodes = buildingMap.getAllNodes(); //Gets all the nodes in the map.
        ArrayList<Node> nodesArr = new ArrayList<>();
        ArrayList<Double> doubles = new ArrayList<>();
        for (Node n : nodes) {
            if (n.getFloor() == floor) {
                doubles.add(Math.abs(Math.sqrt(Math.pow(n.getX() - x, 2) + Math.pow(n.getY() - y, 2)))); //formula matematica
                nodesArr.add(n);
            }
        }
        int mark = 0;
        double min = Collections.min(doubles);
        for (int i = 0; i < doubles.size(); i++) {
            if (min == doubles.get(i)) {
                mark = i;
            }
        }
        System.out.println(nodesArr.get(mark));
        return nodesArr.get(mark);
    }

    /**
     * para evitar os nós aumenta o peso bastante
     * @param nodes arraylist de nós a evitar
     */
    private void avoidSetOfVertices(ArrayList<Node> nodes) {
        for (int v = 0; v < this.buildingMap.getG().V(); v++) {
            for (Node node : nodes) {
                if (this.buildingMap.getNodes().nameOf(v).getName().compareTo(node.getName()) == 0) { //Se o nome for este, este é o nó que queremos evitar
                    for (Edge w : this.buildingMap.getG().adj(v)) { //vai buscar todas as edges adjacentes daquele nó
                        w.setWeight(10000000000.0); // põe o peso de todas as edges adjacentes aos nós que queremos a um peso muito elevado, para que o djikstra evite aquel nó.
                    }
                }
            }
        }
    }

    /**
     * guarda o grafo num ficheiro binario
     */
    private void saveGraphBinFile() {
        File f = new File(".//data//nodes.bin");
        try {
            FileOutputStream fos = new FileOutputStream(f);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(buildingMap);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * le o grafo do ficheiro binario
     */
    private void readGraphFromFile() {
        File f = new File(".//data//nodes.bin");
        try {
            FileInputStream fis = new FileInputStream(f);
            ObjectInputStream ois = new ObjectInputStream(fis);
            buildingMap = (BuildingMap) ois.readObject();
        } catch (IOException | ClassNotFoundException e ) {
            e.printStackTrace();
        }
    }

    /**
     * guarda o grafo num ficheiro de texto
     */
    private void saveGraphTxtFile() {
        ArrayList<Edge> edges = new ArrayList<>();
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(".//data//nodes.txt"));
            for(int i = 0; i < this.buildingMap.getG().V(); i++) {
                for (Edge e : this.buildingMap.getG().adj(i)) {
                    if (!edges.contains(e)) {
                        edges.add(e);
                        Node n = this.buildingMap.getNode(e.getV());
                        writer.write(String.valueOf(n.getFloor()));
                        writer.write(";");
                        writer.write(n.getName());
                        writer.write(";");
                        writer.write(String.valueOf(n.getX()));
                        writer.write(";");
                        writer.write(String.valueOf(n.getY()));
                        writer.write(";");
                        writer.write(n.getLocation());
                        writer.write("-");
                        Node n1 = this.buildingMap.getNode(e.getW());
                        writer.write(String.valueOf(n1.getFloor()));
                        writer.write(";");
                        writer.write(n1.getName());
                        writer.write(";");
                        writer.write(String.valueOf(n1.getX()));
                        writer.write(";");
                        writer.write(String.valueOf(n1.getY()));
                        writer.write(";");
                        writer.write(n1.getLocation());
                        writer.write("-");
                        writer.write(String.valueOf(e.weight()));
                        writer.write(";");
                        writer.write(String.valueOf(e.weight2()));
                        writer.newLine();
                    }
                }
            }
            writer.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
