import java.util.Random;

public class AskTheAudience {

    private Random random = new Random();
 //variables of AskTheAudience
    private String answer;
    private String[] choices;
    private int correctAnswerPercentage;
    private int remainingPercentage;
    private int incorrectChoice1;
    private int incorrectChoice2;
    private int incorrectChoice3;
    private String[] choiceLabels = Question.choiceLabels;

    public AskTheAudience(Question question) {
        answer = question.getAnswer();
        choices = question.getChoices();
    }

    public void display() {
        correctAnswerPercentage = random.nextInt(21) + 60; // 60-80% right answer

        remainingPercentage = 100 - correctAnswerPercentage;
        incorrectChoice1 = random.nextInt(remainingPercentage);
        incorrectChoice2 = random.nextInt(remainingPercentage - incorrectChoice1);
        incorrectChoice3 = remainingPercentage - incorrectChoice1 - incorrectChoice2;
        int[] incorrectChoices = { incorrectChoice1, incorrectChoice2, incorrectChoice3 };

        System.out.println("\nYou use Ask the Audience lifeline.");

        int choiceLabelsIndex = 0;
        int incorrectChoiceIndex = 0;

        for (String choice : choices) {
            if (answer.equals(choice)) {
                System.out.println(choiceLabels[choiceLabelsIndex] + correctAnswerPercentage + "%");
            } else {
                System.out.println(choiceLabels[choiceLabelsIndex] + incorrectChoices[incorrectChoiceIndex] + "%");
                incorrectChoiceIndex++;
            }
            choiceLabelsIndex++;
        }
    }
}
