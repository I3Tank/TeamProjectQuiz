package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Labeled;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;

public class Interface {
    /*
    Initialize strings used in this class, ComboBox "modeSelect" is used for the topic dropdown menu
     */
    private final QuestionCatalog qC = new QuestionCatalog();
    private final CalculateAndFormat cAndF = new CalculateAndFormat();
    private String questionText;
    private String rightAnswer;
    private String wrongAnswer1;
    private String wrongAnswer2;
    private String wrongAnswer3;
    private double practiseGrade;
    ComboBox<String> modeSelect = new ComboBox<>();
    Label score = new Label("Grade:");

    /*
    "newQuestion" a string array with the split up parts. [0] is the question, [1] is our "rightAnswer", [2-4] are the "wrongAnswer"
    "setupScene" is formats our scene layout, question/button placement is determined by "setTranslateX/Y". We chose comic sans on purpose! Returns a complete Scene
    with buttons, labels and lines.
     */
    public void newQuestion(){
        String[] parts = qC.getQuestion();
        questionText = cAndF.formatQuestion(parts[0]);
        rightAnswer = cAndF.formatAnswer(parts[1]);
        wrongAnswer1 = cAndF.formatAnswer(parts[2]);
        wrongAnswer2 = cAndF.formatAnswer(parts[3]);
        wrongAnswer3 = cAndF.formatAnswer(parts[4]);
    }
    public Scene setupScene(Label q, Label progress, Button a, Button b, Button c, Button d, Button j1, Button j2, Button j3, Button backToMainMenu){
        Label joker = new Label("Joker:");

        setLayout(q, -100, -150, 40);
        setLayout(a, -100, 50, 20, 800);
        setLayout(b, -100, 125, 20, 800);
        setLayout(c, -100, 200, 20, 800);
        setLayout(d, -100, 275, 20, 800);

        j1.setText("50 : 50");
        j2.setText("Replace");
        j3.setText("Cheat");
        backToMainMenu.setText("Menu");

        setLayout(joker, 500, 50, 30);
        setLayout(j1, 500, 125, 20, 150);
        setLayout(j2, 500, 200, 20, 150);
        setLayout(j3, 500, 275, 20, 150);
        setLayout(score, 500, -200, 30);
        setLayout(backToMainMenu, 500, -75, 20, 125);
        setLayout(progress, 500, -300, 30);

        Line line = new Line();
        line.setStartY(675);
        line.setTranslateX(400);
        line.setStrokeWidth(5);

        Line line2 = new Line();
        line2.setStartX(200);
        line2.setTranslateX(500);
        line2.setStrokeWidth(5);

        StackPane layout = new StackPane();
        layout.getChildren().addAll(q,a,b,c,d,line, line2, score, j1, j2, j3, joker, backToMainMenu, progress);

        return new Scene(layout, 1200, 675);
    }

    /*
    "reachedMillion" formats our winning scene for the exam mode (if you are >=60%) with statistics of total correct answers and your % and grade. Is called "reachedMillion" for the "who wants to be
    a millionaire" theme.
    "endOfPractiseMode" is used for the practise mode(any %) when you have answered all questions of the chosen topic. Has a retry button.
     */
    public Scene reachedMillion(Button mainMenu, Button exit){
        Label endText = new Label("You have passed the exam!\n" + (15- sample.Main.getWrong()) +" out of " + 15 + " correct answers, that are " + String.format("%.2f" , calculateGrade(sample.Main.getWrong())) + "% \nYour Grade: " + finalGrade());
        endText.setTextAlignment(TextAlignment.CENTER);
        mainMenu.setText("Main menu");
        exit.setText("Exit");

        setLayout(endText, 0, -100, 40);
        setLayout(mainMenu, 0, 175, 20, 300);
        setLayout(exit, 0, 275, 20);

        StackPane endLayout = new StackPane();
        endLayout.getChildren().addAll(endText, mainMenu, exit);
        return new Scene(endLayout, 1000, 675);
    }
    public Scene endOfPractiseMode(Button mainMenu, Button exit, Button retry){
        Label endText = new Label((qC.getQuestionLength()- sample.Main.getWrong()) +" out of " + qC.getQuestionLength() + " correct answers, that are " + String.format("%.2f" , calculateGrade(sample.Main.getWrong())) + "% \nYour Grade: " + finalGrade());
        endText.setTextAlignment(TextAlignment.CENTER);
        mainMenu.setText("Main menu");
        exit.setText("Exit");
        retry.setText("Retry");

        setLayout(endText, 0, -100, 40);
        setLayout(mainMenu, 0, 175, 20, 300);
        setLayout(exit, 0, 275, 20);
        setLayout(retry, 0, 75, 20);

        StackPane endLayout = new StackPane();
        endLayout.getChildren().addAll(endText, mainMenu, exit, retry);
        return new Scene(endLayout, 1000, 675);
    }
    /*
    "lost" is the scene used in exam mode if you cant reach over 60% anymore.
    "startScreen" is the scene used to chose the topic and mode in the beginning. Displays the name of the program.
     */
    public Scene lost(Button mainMenu, Button exit){
        Label endText = new Label("You have failed the exam!\n" + "You did not achieve over 60% correct answers \n Keep practising!");
        endText.setTextAlignment(TextAlignment.CENTER);
        mainMenu.setText("Main menu");
        exit.setText("Exit");

        setLayout(endText, 0, -100, 40);
        setLayout(mainMenu, 0, 175, 20, 300);
        setLayout(exit, 0, 275, 20);

        StackPane endLayout = new StackPane();
        endLayout.getChildren().addAll(endText, mainMenu, exit);
        return new Scene(endLayout, 1000, 675);
    }
    public Scene startScreen(Button startExamMode, Button startPractiseMode, Button exit){
        ObservableList<String> modes = getModeList();

        Label title = new Label("The ????");
        Label clickText = new Label("Click to start:");
        startExamMode.setText("Exam mode");
        startPractiseMode.setText("Practise mode");
        exit.setText("Exit");
        Label topic = new Label("Topic:");

        setLayout(title, 0, -125, 40);
        setLayout(clickText, 0, 0, 20);
        setLayout(startExamMode, 0, 50, 20, 200);
        setLayout(startPractiseMode, 0, 100, 20, 200);
        setLayout(exit, 200, 100, 20);
        setLayout(topic, -200, 0, 20);

        modeSelect.setItems(modes);
        modeSelect.setValue("MATH");
        modeSelect.setStyle("-fx-font: 20 \"Comic Sans MS\";");
        modeSelect.setTranslateX(-200);
        modeSelect.setTranslateY(50);

        StackPane endLayout = new StackPane();
        endLayout.getChildren().addAll(title, startExamMode, startPractiseMode, clickText, exit, topic, modeSelect);
        return new Scene(endLayout, 640, 480);
    }
    /*
    "getModeList" is used for the ComboBox "modeSelect" content(topics).
    "formatText" is our algorithm to find the "lastSpace"(' ') within 40 chars, then it adds a new line there, so the text fits in our window/buttons and isn't cut off.
    (not perfect yet, the very long questions are still messed up).
    "finalGrade" is used to return the grade depending on %.
     */
    public static ObservableList<String> getModeList(){
        return FXCollections.observableArrayList("MATH","BSYS", "TEAM");
    }
    public double calculateGrade(int wrong){
        double grade = ((15.0 - wrong)/15.0) * 100;
        score.setText(String.format("Grade: \n%.2f", grade));
        return grade;
    }
    public void calculatePractiseGrade(double wrong, double counter){
        this.practiseGrade =  ((counter - wrong)/counter) * 100;
        score.setTextAlignment(TextAlignment.CENTER);
        score.setText("Grade: \n" + finalGrade());
    }
    public int finalGrade(){
        double grade;
        if(sample.Main.getPractiseMode()){
            grade = this.practiseGrade;
        }
        else {
            grade = calculateGrade(sample.Main.getWrong());
        }
        if(grade >= 90){
            return 1;
        }
        else if(grade >= 80){
            return 2;
        }
        else if(grade >= 70){
            return 3;
        }
        else if(grade >= 60){
            return 4;
        }
        return 5;
    }
    public String getSelectedTopic(){
        return modeSelect.getValue();
    }
    public String getQuestionText() {
        return questionText;
    }
    public String getRightAnswer() {
        return rightAnswer;
    }
    public String getWrongAnswer1() {
        return wrongAnswer1;
    }
    public String getWrongAnswer2() {
        return wrongAnswer2;
    }
    public String getWrongAnswer3() {
        return wrongAnswer3;
    }

    public <T extends Labeled> void setLayout(T element, int x, int y, int size){
        element.setTranslateX(x);
        element.setTranslateY(y);
        element.setFont(Font.font("Comic Sans MS", FontWeight.BOLD, size));
    }
    public <T extends Labeled> void setLayout(T element, int x, int y, int size, int prefWidth){
        element.setTranslateX(x);
        element.setTranslateY(y);
        element.setFont(Font.font("Comic Sans MS", FontWeight.BOLD, size));
        element.setPrefWidth(prefWidth);
    }
}
