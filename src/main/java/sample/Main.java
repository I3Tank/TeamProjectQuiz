package sample;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Main extends Application {

    public static void main(String[] args){
        launch(args);
    }
    /*
    Initializing the elements needed for our program. "counter" is used to track the answered questions. "wrong" is used to track the answers you got wrong and to
    calculate your grade. "practiseMode" used to determine which mode u chose.
     */
    Button backToMainMenu, exitGame, retry, startPractiseMode, startExamMode, answerA, answerB, answerC, answerD, joker1, joker2, joker3;
    Label question, progress;
    StackPane layout;
    Scene start, questionAndAnswer;
    Interface anInterface;

    static int counter = 0;
    static int wrong = 0;
    static boolean practiseMode = false;
    QuestionCatalog qc = new QuestionCatalog();

    @Override
    public void start(Stage primaryStage) throws Exception {
        anInterface = new Interface();
        Joker joker = new Joker();

        layout = new StackPane();
        question = new Label();
        progress = new Label();
        answerA = new Button();
        answerB = new Button();
        answerC = new Button();
        answerD = new Button();
        joker1 = new Button();
        joker2 = new Button();
        joker3 = new Button();
        startExamMode = new Button();
        startPractiseMode = new Button();
        exitGame = new Button();
        backToMainMenu = new Button();
        retry = new Button();

        /*
        Configuring the layout for our start screen. "The name of the button.setonAction" defines what happens when u click on the button. In this case resetting
        "counter", "wrong", joker and receiving new questions to start a new game. Sets the scene to our "questionAndAnswer" Scene and displays it. "nextQuestion"
        fills our question and answers with text.
        */
        start = anInterface.startScreen(startExamMode, startPractiseMode, exitGame);
        primaryStage.setScene(start); //Needed to display the start scene at the beginning
        primaryStage.setResizable(false);
        primaryStage.show();

        startExamMode.setOnAction(e -> {
            practiseMode = false;
            setupNewGame(primaryStage);
        });
        startPractiseMode.setOnAction(e -> {
            practiseMode = true;
            setupNewGame(primaryStage);
        });
        exitGame.setOnAction(e -> {
            primaryStage.close();
        });
        /*
        "answerA-D" compares the text written on the button with our "rightAnswer", if it is correct "nextQuestion" provides the next question, if it wrong, "wrong" will be
        increased. If u are in "practiseMode" and the answer is wrong, it will disable the button and you have another chance to answer. The "grade" will be calculated and
        displayed
         */
        answerA.setOnAction(e -> {
            buttonFunction(answerA, primaryStage);
        });
        answerB.setOnAction(e -> {
            buttonFunction(answerB, primaryStage);
        });
        answerC.setOnAction(e -> {
            buttonFunction(answerC, primaryStage);
        });
        answerD.setOnAction(e -> {
            buttonFunction(answerD, primaryStage);
        });
        /*
        "joker1" is the 50:50 joker, that disables 2 wrong answers. "joker2" is our replace joker, it replaces the question with another one and corrects the counter.
        "joker3" is our cheat joker, it highlights the right answer. Every joker disables itself after used. The functionality of these are in the class "Joker"
        "backToMainMenu" displays the start screen. "retry" starts the practise mode again after you are done with it.
         */
        joker1.setOnAction(e -> {
            joker.halveAvailableAnswers(anInterface.getRightAnswer(), answerA, answerB, answerC, answerD);
            joker1.setDisable(true);
        });
        joker2.setOnAction(e -> {
            --counter;
            nextQuestion();
            joker2.setDisable(true);
        });
        joker3.setOnAction(e -> {
            joker.cheat(anInterface.getRightAnswer(), answerA, answerB, answerC, answerD, joker3);
        });
        backToMainMenu.setOnAction(e -> {
            primaryStage.setScene(anInterface.startScreen(startExamMode, startPractiseMode, exitGame));
            setupWindow(primaryStage);
        });
        retry.setOnAction(e -> {
            practiseMode = true;
            setupNewGame(primaryStage);
        });
    }
    /*
    Uses the strings from our "Interface" class and sets the text of the question and answers correctly and randomizes the buttons. Checks if you are in practise or
    exam mode. If you are in exam mode, only 15 questions will be displayed. In practise mode all questions of the chosen topic will be displayed. Enables buttons that got
    disabled by the 50:50 joker. setStyle(null) is used to reset the color of the button. Calls the method used to calculate the grade and displays it. Same with the "counter"
     */
    private void nextQuestion(){
        if(qc.getCounter() <= 15 || practiseMode) {
            anInterface.newQuestion();

            String[] answers = new String[4];

            answers[0] = anInterface.getRightAnswer();
            answers[1] = anInterface.getWrongAnswer1();
            answers[2] = anInterface.getWrongAnswer2();
            answers[3] = anInterface.getWrongAnswer3();

            List<String> stringList = Arrays.asList(answers);
            Collections.shuffle(stringList);
            stringList.toArray(answers);

            question.setText(anInterface.getQuestionText());
            answerA.setText(answers[0]);
            answerB.setText(answers[1]);
            answerC.setText(answers[2]);
            answerD.setText(answers[3]);

            setupButton(answerA);
            setupButton(answerB);
            setupButton(answerC);
            setupButton(answerD);

            if (practiseMode) {
                anInterface.calculatePractiseGrade(wrong, counter);
                progress.setText(+ (counter+1) + "/" + qc.getQuestionLength());
            } else {
                anInterface.calculateGrade(wrong);
                progress.setText(+ (counter+1) + "/15");
            }
        }
        counter++;
    }
    /*
    Checks if you are on the last question and if you can still reach over 60%(exam mode). Displays the "reachedMillion" or "lost" or "endOfPractiseMode" Scene.
     */
    public void checkAnswer(Stage primaryStage){
        if(!practiseMode) {
            if (anInterface.calculateGrade(wrong) < 60.2f) {
                primaryStage.setScene(anInterface.lost(backToMainMenu, exitGame));
                setupWindow(primaryStage);
            } else if (counter == 16) {
                primaryStage.setScene(anInterface.reachedMillion(backToMainMenu, exitGame));
                setupWindow(primaryStage);
            }
        }
        else{
            if(counter == qc.getQuestionLength()){
                primaryStage.setScene(anInterface.endOfPractiseMode(backToMainMenu, exitGame, retry));
                setupWindow(primaryStage);
            }
        }
    }
    public void setupNewGame(Stage primaryStage){
        wrong = 0;
        counter = 0;
        resetJoker();
        qc.resetQuestionCatalog(anInterface.getSelectedTopic());
        questionAndAnswer = anInterface.setupScene(question, progress, answerA, answerB, answerC, answerD, joker1, joker2, joker3, backToMainMenu);
        primaryStage.setScene(questionAndAnswer);
        setupWindow(primaryStage);
        nextQuestion();
    }
    public void setupButton(Button answer){
        answer.setDisable(false);
        answer.setStyle(null);
    }
    public void setupWindow(Stage primaryStage){
        primaryStage.centerOnScreen();
        primaryStage.show();
    }
    public void buttonFunction(Button answer, Stage primaryStage){
        if(!practiseMode) {
            if (!answer.getText().equals(anInterface.getRightAnswer())) {
                wrong++;
            }
            nextQuestion();
        }
        else{
            if(answer.getText().equals(anInterface.getRightAnswer())){
                nextQuestion();
            }
            else{
                answer.setDisable(true);
                wrong++;
                anInterface.calculatePractiseGrade(wrong, counter);
            }
        }
        checkAnswer(primaryStage);
    }
    public void resetJoker(){
        joker1.setDisable(false);
        joker2.setDisable(false);
        joker3.setDisable(false);
    }
    public static boolean getPractiseMode(){
        return practiseMode;
    }
    public static int getWrong() {
        return wrong;
    }
}

