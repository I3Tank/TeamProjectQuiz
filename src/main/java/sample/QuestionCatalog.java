package sample;

import java.io.*;
import java.net.URL;
import java.util.*;
import java.util.Collections;
import java.util.List;

/*
"counter" is used to iterate through the chosen questions.
"arrayLength" is used to create a new array for the practiseMode. Depends on the length of the different(topic) arrays, so the length equals to the number of
all questions of that topic. (team questions coming soon). Minimum of 16 questions needed in each topic. 15 + one questions to replace(joker2).
The format of our questions is: "question#rightAnswer#wrongAnswer1#wrongAnswer2#wrongAnswer3"
 */

public class QuestionCatalog {
    static int counter = 0;
    static int arrayLength = 0;
    static String[] shuffledQuestions = new String[16];

    /*
    "QuestionCatalog" first creates a list of our question array. Then it shuffles it and chooses the first 16 questions.
    Then it saves those into the "shuffledQuestions" array.
    Only used for the exam mode at the moment.
    "getQuestion" splits one of our questions into 5 parts. # is used to divide those.
    "resetQuestionCatalog" chooses which question array to use depending on the topic chosen. The chosen array is saved
    in "shuffledQuestions" Sets our "arrayLength" for
    the practise mode. Resets "counter" to 0.
     */
    public String bsysFile = "src/main/resources/BSYS_Fragen.txt";
    public String mathFile = "src/main/resources/MATH_Fragen.txt";
    public String teamFile = "src/main/resources/TEAM_Fragen.txt";

    private String[] bsys = fileToList(bsysFile);
    private String[] math = fileToList(mathFile);
    private String[] team = fileToList(teamFile);

    public String[] fileToList(String filename) {
        File file = new File(filename);
        List<String> fileList = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            for (String line = reader.readLine(); line != null; line = reader.readLine()) {
                fileList.add(line);
            }
        } catch (IOException e) {
            System.err.format("Could not read %s: %s%n", file, e);
        }
        return fileList.toArray(new String[]{});
    }


    public void resetQuestionCatalog(String chosenTopic){
        counter = 0;
        List<String> stringList = new ArrayList<>();
        switch(chosenTopic){
            case "MATH":
                stringList = Arrays.asList(math);
                arrayLength = math.length;
                break;
            case "BSYS":
                stringList = Arrays.asList(bsys);
                arrayLength = bsys.length;
                break;
            case "TEAM":
                stringList = Arrays.asList(team);
                arrayLength = team.length;
                break;
        }
        String[] practiseQuestions = new String[arrayLength];
        Collections.shuffle(stringList);
        if(sample.Main.getPractiseMode()){
            stringList.toArray(practiseQuestions);
            shuffledQuestions = practiseQuestions;
        }
        else {
            ArrayList<String> stringList2 = new ArrayList<>(stringList.subList(0, 16));
            stringList2.toArray(shuffledQuestions);
        }
    }
    public String[] getQuestion(){
        String s = shuffledQuestions[counter];
        counter++;

        return s.split("#");
    }
    public int getQuestionLength(){
        return arrayLength;
    }
    public int getCounter() {
        return counter;
    }
}
