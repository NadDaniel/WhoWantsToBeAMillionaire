public class Question {

    // variabiles question
    private String question;
    private String[] choices;
    private String answer;
    public static String[] choiceLabels = { "A) ", "B) ", "C) ", "D) " };

    public Question() {
    }

    //assign parameters to variabile
    public Question(String question, String[] choices, String answer) {
        this.question = question;
        this.choices = choices;
        this.answer = answer;
    }

    public String getQuestion() {
        return question;
    }

    public String[] getChoices() {
        return choices;
    }

    public String getAnswer() {
        return answer;
    }

    public String[] getChoiceLabels() {
        return choiceLabels;
    }

    // ternary operator
    public boolean isCorrect(String userAnswer) {
        return (userAnswer.equals(answer)) ? true : false;
    }
}
