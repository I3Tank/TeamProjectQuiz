package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
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

        q.setFont(Font.font("Comic Sans MS", FontWeight.BOLD, 40));
        q.setTranslateX(-100);
        q.setTranslateY(-150);

        a.setTranslateX(-100);
        a.setTranslateY(50);
        a.setFont(Font.font("Comic Sans MS", FontWeight.BOLD, 20));
        a.setPrefWidth(800);

        b.setTranslateX(-100);
        b.setTranslateY(125);
        b.setFont(Font.font("Comic Sans MS", FontWeight.BOLD, 20));
        b.setPrefWidth(800);

        c.setTranslateX(-100);
        c.setTranslateY(200);
        c.setFont(Font.font("Comic Sans MS", FontWeight.BOLD, 20));
        c.setPrefWidth(800);

        d.setTranslateX(-100);
        d.setTranslateY(275);
        d.setFont(Font.font("Comic Sans MS", FontWeight.BOLD, 20));
        d.setPrefWidth(800);

        Label joker = new Label("Joker:");
        joker.setTranslateY(50);
        joker.setTranslateX(500);
        joker.setFont(Font.font("Comic Sans MS", FontWeight.BOLD, 30));

        j1.setTranslateX(500);
        j1.setTranslateY(125);
        j1.setText("50 : 50");
        j1.setFont(Font.font("Comic Sans MS", FontWeight.BOLD, 20));
        j1.setPrefWidth(150);

        j2.setTranslateX(500);
        j2.setTranslateY(200);
        j2.setText("Replace");
        j2.setFont(Font.font("Comic Sans MS", FontWeight.BOLD, 20));
        j2.setPrefWidth(150);

        j3.setTranslateX(500);
        j3.setTranslateY(275);
        j3.setText("Cheat");
        j3.setFont(Font.font("Comic Sans MS", FontWeight.BOLD, 20));
        j3.setPrefWidth(150);

        score.setTranslateY(-200);
        score.setTranslateX(500);
        score.setFont(Font.font("Comic Sans MS", FontWeight.BOLD, 30));

        backToMainMenu.setTranslateX(500);
        backToMainMenu.setTranslateY(-75);
        backToMainMenu.setText("Menu");
        backToMainMenu.setFont(Font.font("Comic Sans MS", FontWeight.BOLD, 20));
        backToMainMenu.setPrefWidth(125);

        progress.setTranslateY(-300);
        progress.setTranslateX(500);
        progress.setFont(Font.font("Comic Sans MS", FontWeight.BOLD, 30));

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
        endText.setTranslateY(-100);
        endText.setFont(Font.font("Comic Sans MS", FontWeight.BOLD, 40));

        mainMenu.setText("Main menu");
        mainMenu.setTranslateX(0);
        mainMenu.setTranslateY(175);
        mainMenu.setFont(Font.font("Comic Sans MS", FontWeight.BOLD, 20));
        mainMenu.setPrefWidth(300);

        exit.setText("Exit");
        exit.setTranslateY(275);
        exit.setTranslateX(0);
        exit.setFont(Font.font("Comic Sans MS", FontWeight.BOLD, 20));

        StackPane endLayout = new StackPane();
        endLayout.getChildren().addAll(endText, mainMenu, exit);
        return new Scene(endLayout, 1000, 675);
    }
    public Scene endOfPractiseMode(Button mainMenu, Button exit, Button retry){
        Label endText = new Label((qC.getQuestionLength()- sample.Main.getWrong()) +" out of " + qC.getQuestionLength() + " correct answers, that are " + String.format("%.2f" , calculateGrade(sample.Main.getWrong())) + "% \nYour Grade: " + finalGrade());
        endText.setTextAlignment(TextAlignment.CENTER);
        endText.setTranslateY(-100);
        endText.setFont(Font.font("Comic Sans MS", FontWeight.BOLD, 40));

        mainMenu.setText("Main menu");
        mainMenu.setTranslateX(0);
        mainMenu.setTranslateY(175);
        mainMenu.setFont(Font.font("Comic Sans MS", FontWeight.BOLD, 20));
        mainMenu.setPrefWidth(300);

        exit.setText("Exit");
        exit.setTranslateY(275);
        exit.setTranslateX(0);
        exit.setFont(Font.font("Comic Sans MS", FontWeight.BOLD, 20));

        retry.setText("Retry");
        retry.setTranslateY(75);
        retry.setFont(Font.font("Comic Sans MS", FontWeight.BOLD, 20));

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
        endText.setTranslateY(-100);
        endText.setFont(Font.font("Comic Sans MS", FontWeight.BOLD, 40));

        mainMenu.setText("Main menu");
        mainMenu.setTranslateY(175);
        mainMenu.setTranslateX(0);
        mainMenu.setFont(Font.font("Comic Sans MS", FontWeight.BOLD, 20));
        mainMenu.setPrefWidth(300);

        exit.setText("Exit");
        exit.setTranslateY(275);
        exit.setTranslateX(0);
        exit.setFont(Font.font("Comic Sans MS", FontWeight.BOLD, 20));

        StackPane endLayout = new StackPane();
        endLayout.getChildren().addAll(endText, mainMenu, exit);
        return new Scene(endLayout, 1000, 675);
    }
    public Scene startScreen(Button startExamMode, Button startPractiseMode, Button exit){
        ObservableList<String> modes = getModeList();

        Label title = new Label("The ????");
        title.setFont(Font.font("Comic Sans MS", FontWeight.BOLD, 40));
        title.setTranslateY(-125);

        Label clickText = new Label("Click to start:");
        clickText.setFont(Font.font("Comic Sans MS", FontWeight.BOLD, 20));

        startExamMode.setText("Exam mode");
        startExamMode.setFont(Font.font("Comic Sans MS", FontWeight.BOLD, 20));
        startExamMode.setTranslateY(50);
        startExamMode.setPrefWidth(200);

        startPractiseMode.setText("Practise mode");
        startPractiseMode.setFont(Font.font("Comic Sans MS", FontWeight.BOLD, 20));
        startPractiseMode.setTranslateY(100);
        startPractiseMode.setPrefWidth(200);

        exit.setText("Exit");
        exit.setFont(Font.font("Comic Sans MS", FontWeight.BOLD, 20));
        exit.setTranslateX(200);
        exit.setTranslateY(100);

        Label topic = new Label("Topic:");
        topic.setFont(Font.font("Comic Sans MS", FontWeight.BOLD, 20));
        topic.setTranslateX(-200);

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
}
