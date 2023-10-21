import java.util.Random;

public class FiftyFifty {

    private Random random = new Random();

    //variables of 50/50
    private String[] woRightAnswer;
    private String[] updatedChoices;
    private int answerIndex;
    private int randomChoiceIndex;
    private String[] choices;
    private String answer;
    private String randomChoice;
    private String choice1;
    private String choice2;
    private String[] choiceLabels = Question.choiceLabels;

    public FiftyFifty(Question question) {
        choices = question.getChoices();
        answer = question.getAnswer();

        //run both of them
        removeRightAnswer();
        setNewChoices();
    }

    public String[] getWoRightAnswer() {
        return woRightAnswer;
    }

    
    public String[] getUpdatedChoices() {
        return updatedChoices;
    }
    
    private void removeRightAnswer() {
        woRightAnswer = new String[choices.length - 1];
        int choicesIndex = 0;

        // forloop to remove right answer in the array
        for (String choice : choices) {
            if (!choice.equals(answer)) {
                woRightAnswer[choicesIndex] = choice;
                choicesIndex++;
            }
        }
    }

    private void setNewChoices() {
        updatedChoices = new String[choices.length - 2];
        randomChoice = getRandomChoice();
        updatedChoices[0] = answer;
        updatedChoices[1] = randomChoice;
    }

    private String getRandomChoice() {
        int randomIndex = random.nextInt(woRightAnswer.length);
        return woRightAnswer[randomIndex];
    }

    public void display() {
        answerIndex = getIndex(choices, answer);
        randomChoiceIndex = getIndex(choices, randomChoice);

        if (answerIndex > randomChoiceIndex) {
            choice1 = choiceLabels[randomChoiceIndex] + randomChoice;
            choice2 = choiceLabels[answerIndex] + answer;
        } else {
            choice1 = choiceLabels[answerIndex] + answer;
            choice2 = choiceLabels[randomChoiceIndex] + randomChoice;
        }

        System.out.println("\nYou use 50/50 lifeline.");
        System.out.println(choice1);
        System.out.println(choice2);
    }

    private int getIndex(String[] choices, String choice) {
        for (int i = 0; i < choices.length; i++) {
            if (choices[i].equals(choice)) {
                return i;
            }
        }
        return -1;
    }

}
