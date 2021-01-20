package sample;

public class CalculateAndFormat {

    //"formatAnswer"/"formatQuestion are our algorithm to find the "lastSpace"(' ') within "toCheck" chars,
    // then it adds a new line there, so the text fits in our window/buttons and isn't cut off.


    public String formatAnswer(String toFormat){
        int lastSpace = 0;
        int toCheck = 75;
        char[] charArray = toFormat.toCharArray();

        if(toFormat.length() >= 50) {
            for(int j = 0; j < (toFormat.length()/75); j++) {
                for (int i = 0; i < toFormat.length(); i++) {
                    if (toFormat.charAt(i) == ' ' && i < toCheck) {
                        lastSpace = i;
                    }
                }
                charArray[lastSpace] = '\n';
                toCheck += 75;
            }
            return String.valueOf(charArray);
        }
        return toFormat;
    }
    public String formatQuestion(String toFormat){
        int lastSpace = 0;
        int toCheck = 45;
        char[] charArray = toFormat.toCharArray();

        if(toFormat.length() >= 50) {
            for(int j = 0; j < (toFormat.length()/45); j++) {
                for (int i = 0; i < toFormat.length(); i++) {
                    if (toFormat.charAt(i) == ' ' && i < toCheck) {
                        lastSpace = i;
                    }
                }
                charArray[lastSpace] = '\n';
                toCheck += 45;
            }
            return String.valueOf(charArray);
        }
        return toFormat;
    }
}
