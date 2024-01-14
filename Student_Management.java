import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.io.*;
import java.util.ArrayList;
import java.util.Optional;

public class Student_Management extends Application {
    private ArrayList<Student> students = new ArrayList<>();
    private static final String FILENAME = "students.dat";

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Student Management System");

        BorderPane borderPane = new BorderPane();
        VBox vbox = new VBox();
        TextArea outputTextArea = new TextArea();
        outputTextArea.setEditable(false);

        TextField nameTextField = new TextField();
        nameTextField.setPromptText("Name");
        TextField rollNumberTextField = new TextField();
        rollNumberTextField.setPromptText("Roll Number");
        TextField gradeTextField = new TextField();
        gradeTextField.setPromptText("Grade");

        Button addButton = new Button("Add Student");
        Button removeButton = new Button("Remove Student");
        Button searchButton = new Button("Search Student");
        Button displayButton = new Button("Display All Students");
        Button saveButton = new Button("Save to File");
        Button loadButton = new Button("Load from File");

        addButton.setOnAction(e -> {
            if (validateInput(nameTextField.getText(), rollNumberTextField.getText(), gradeTextField.getText())) {
                addStudent(new Student(nameTextField.getText(), rollNumberTextField.getText(), gradeTextField.getText()));
                clearFields(nameTextField, rollNumberTextField, gradeTextField);
            } else {
                showAlert("Error", "All fields are required!");
            }
        });

        removeButton.setOnAction(e -> {
            String rollNumber = promptForInput("Remove Student", "Enter Roll Number:");
            if (rollNumber != null && !rollNumber.isEmpty()) {
                removeStudent(rollNumber);
            }
        });

        searchButton.setOnAction(e -> {
            String rollNumber = promptForInput("Search Student", "Enter Roll Number:");
            if (rollNumber != null && !rollNumber.isEmpty()) {
                searchStudent(rollNumber);
            }
        });

        displayButton.setOnAction(e -> displayAllStudents(outputTextArea));

        saveButton.setOnAction(e -> saveToFile());

        loadButton.setOnAction(e -> loadFromFile());

        vbox.getChildren().addAll(nameTextField, rollNumberTextField, gradeTextField, addButton, removeButton, searchButton, displayButton, saveButton, loadButton);
        vbox.setSpacing(10);
        vbox.setPadding(new Insets(10));

        borderPane.setTop(vbox);
        borderPane.setCenter(outputTextArea);

        primaryStage.setScene(new Scene(borderPane, 400, 400));
        primaryStage.show();
    }

    private void addStudent(Student student) {
        students.add(student);
        showAlert("Success", "Student added successfully!");
    }

    private void removeStudent(String rollNumber) {
        students.removeIf(student -> student.getRollNumber().equals(rollNumber));
        showAlert("Success", "Student removed successfully!");
    }

    private void searchStudent(String rollNumber) {
        Optional<Student> foundStudent = students.stream()
                .filter(student -> student.getRollNumber().equals(rollNumber))
                .findFirst();

        if (foundStudent.isPresent()) {
            showAlert("Student Found", foundStudent.get().toString());
        } else {
            showAlert("Student Not Found", "No student found with Roll Number: " + rollNumber);
        }
    }

    private void displayAllStudents(TextArea outputTextArea) {
        outputTextArea.clear();
        for (Student student : students) {
            outputTextArea.appendText(student.toString() + "\n");
        }
    }

    private void saveToFile() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILENAME))) {
            oos.writeObject(students);
            showAlert("Success", "Data saved to file.");
        } catch (IOException e) {
            e.printStackTrace();
            showAlert("Error", "Error saving data to file.");
        }
    }

    private void loadFromFile() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILENAME))) {
            Object obj = ois.readObject();
            if (obj instanceof ArrayList) {
                students = (ArrayList<Student>) obj;
                showAlert("Success", "Data loaded from file.");
            } else {
                showAlert("Error", "Invalid data in file.");
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            showAlert("Error", "Error loading data from file.");
        }
    }

    private boolean validateInput(String name, String rollNumber, String grade) {
        return !name.isEmpty() && !rollNumber.isEmpty() && !grade.isEmpty();
    }

    private void clearFields(TextField... textFields) {
        for (TextField textField : textFields) {
            textField.clear();
        }
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private String promptForInput(String title, String message) {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle(title);
        dialog.setHeaderText(null);
        dialog.setContentText(message);
        Optional<String> result = dialog.showAndWait();
        return result.orElse(null);
    }
}

class Student implements Serializable {
    private String name;
    private String rollNumber;
    private String grade;

    public Student(String name, String rollNumber, String grade) {
        this.name = name;
        this.rollNumber = rollNumber;
        this.grade = grade;
    }

    // Getters and setters

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRollNumber() {
        return rollNumber;
    }

    public void setRollNumber(String rollNumber) {
        this.rollNumber = rollNumber;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    @Override
    public String toString() {
        return "Name: " + name + ", Roll Number: " + rollNumber + ", Grade: " + grade;
    }
}
