package sample;

public class CalculateAndFormat {
    private double practiseGrade;

    public String formatAnswer(String toFormat){
        int lastSpace = 0;
        if(toFormat.length() >= 75) {
            for (int i = 0; i < toFormat.length(); i++) {
                if (toFormat.charAt(i) == ' ' && i < 75) {
                    lastSpace = i;
                }
            }
            return toFormat.replaceAll("(.{"+ (lastSpace + 1) + "})", "$1\n");
        }
        return toFormat;
    }
    public String formatQuestion(String toFormat){
        int lastSpace = 0;
        if(toFormat.length() >= 50) {
            for (int i = 0; i < toFormat.length(); i++) {
                if (toFormat.charAt(i) == ' ' && i < 50) {
                    lastSpace = i;
                }
            }
            return toFormat.replaceAll("(.{"+ (lastSpace + 1) + "})", "$1\n");
        }
        return toFormat;
    }

}
