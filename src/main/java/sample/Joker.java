package sample;

import javafx.scene.control.Button;

public class Joker{
    /*
    "halveAvailableAnswers" checks which button has the right answer and disables 2 others.
    "cheat" checks which button has the right answer and highlights it green.
    "replace" is just one line so we kept it in Main class.
     */
    public void halveAvailableAnswers(String rightAnswer, Button answerA, Button answerB, Button answerC, Button answerD){
        if(answerA.getText().equals(rightAnswer)){
            answerB.setText("");
            answerB.setDisable(true);
            answerC.setText("");
            answerC.setDisable(true);
        }
        else if(answerB.getText().equals(rightAnswer)){
            answerC.setText("");
            answerC.setDisable(true);
            answerD.setText("");
            answerD.setDisable(true);
        }
        else if(answerC.getText().equals(rightAnswer)){
            answerD.setText("");
            answerD.setDisable(true);
            answerA.setText("");
            answerA.setDisable(true);
        }
        else{
            answerA.setText("");
            answerA.setDisable(true);
            answerB.setText("");
            answerB.setDisable(true);
        }
    }
    public void cheat(String rightAnswer, Button answerA, Button answerB, Button answerC, Button answerD, Button joker3){
        if(answerA.getText().equals(rightAnswer)){
            answerA.setStyle("-fx-background-color: #00ff00; ");
        }
        if(answerB.getText().equals(rightAnswer)){
            answerB.setStyle("-fx-background-color: #00ff00; ");
        }
        if(answerC.getText().equals(rightAnswer)){
            answerC.setStyle("-fx-background-color: #00ff00; ");
        }
        if(answerD.getText().equals(rightAnswer)){
            answerD.setStyle("-fx-background-color: #00ff00; ");
        }
        joker3.setDisable(true);
    }
}
