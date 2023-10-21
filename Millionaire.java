import java.util.Random;
import java.util.Scanner;

public class Millionaire {

    private Scanner scanner = new Scanner(System.in);
    private Random random = new Random();
    private Player player = new Player();

    // Easy difficulty questions
    private Question question1 = new Question("What is the largest planet in our solar system?",
            new String[] { "Earth", "Mars", "Jupiter", "Saturn" }, "Jupiter");
    private Question question2 = new Question("Who wrote the famous play \"Romeo and Juliet\"?",
            new String[] { "Charles Dickens", "William Shakespeare", "Jane Austen", "Mark Twain" },
            "William Shakespeare");
    private Question question3 = new Question("Which gas do plants absorb from the atmosphere during photosynthesis?",
            new String[] { "Oxygen", "Carbon dioxide", "Nitrogen", "Hydrogen" }, "Carbon dioxide");
    private Question question4 = new Question("What is the chemical symbol for gold?",
            new String[] { "Au", "Ag", "Fe", "Cu" }, "Au");
    private Question question5 = new Question("What is the largest mammal on Earth?",
            new String[] { "African elephant", "Blue whale", "Giraffe", "Polar bear" },
            "Blue whale");

    // Medium difficulty questions
    private Question question6 = new Question("How many years before the next leap year occurs?",
            new String[] { "3", "4", "2", "1" },
            "4");
    private Question question7 = new Question("What is the native language of the Philippines?",
            new String[] { "Tagalog", "Pilipino", "English", "Filipino" },
            "Filipino");
    private Question question8 = new Question("In mythology, who is the god of war?",
            new String[] { "Zeus", "Hermes", "Ares", "Apollo" },
            "Ares");
    private Question question9 = new Question("If you are born male, what is your last pair of chromosomes?",
            new String[] { "XX", "XY", "YY", "XZ" },
            "XY");
    private Question question10 = new Question("Which month has the shortest number of days?",
            new String[] { "February", "March", "April", "May" },
            "February");

    // Hard difficulty questions
    private Question question11 = new Question("Which of the following is not one of the fundamental forces in nature?",
            new String[] { "Gravity", "Electromagnetism", "Strong Nuclear Force", "Friction" },
            "Friction");
    private Question question12 = new Question(
            "What is the approximate age of the universe, according to current scientific estimates?",
            new String[] { "4.5 billion years", "13.8 billion years", "100 million years", "1 trillion years" },
            "13.8 billion years");
    private Question question13 = new Question(
            "Which chemical element has the atomic number 92 and is commonly used as fuel in nuclear reactors?",
            new String[] { "Uranium (U)", "Plutonium (Pu)", "Thorium (Th)", "Neptunium (Np)" },
            "Uranium (U)");
    private Question question14 = new Question("What is the smallest prime number greater than 50?",
            new String[] { "53", "47", "51", "57" },
            "53");
    private Question question15 = new Question("Which of the following mathematical constants is an irrational number?",
            new String[] { "\u03C0 (Pi)", "e (Euler's number)", "\u221A2 (Square root of 2)", "0 (Zero)" },
            "\u221A2 (Square root of 2)");

    // variable questions
    private final int QUESTIONS_PER_ROUND = 5;
    private Question[] easyQuestions = { question1, question2, question3, question4, question5 };
    private Question[] mediumQuestions = { question6, question7, question8, question9, question10 };
    private Question[] hardQuestions = { question11, question12, question13, question14, question15 };

    private String playerName;
    private String convertedChoice;
    private int[] winningAmount = { 0, 100, 200, 300, 500, 1_000, 2_000, 4_000, 8_000, 16_000, 32_000, 64_000, 125_000,
            250_000, 500_000, 1_000_000 };
    private int safeZone1Amount = winningAmount[5];
    private int safeZone2Amount = winningAmount[10];
    private int prize;
    private char choice;
    private int round = 1;
    private int winAmount;
    private char finalAnswerChoice;
    private char continueChoice;

    //lifelines variables
    private String lifeline1 = "1) 50/50";
    private String lifeline2 = "2) Ask the Audience";
    private String lifeline3 = "3) Phone a Friend";
    private int lifelineChoice;

    //if lifelines use they will vanished
    private boolean lifeline1Used = false;
    private boolean lifeline2Used = false;
    private boolean lifeline3Used = false;

    private FiftyFifty fiftyFifty;
    private PhoneAFriend phoneAFriend;
    private AskTheAudience askTheAudience;

    public Millionaire() {
    }

    public void run() {
        displayTitle();
        displayEasyQuestion();
    }
// Welcome Prompt
    private void displayTitle() {
        playerName = player.getPlayerName();

        System.out.println("\nHi " + playerName
                + ". Welcome to Who Wants to be a Millionaire! As I'm sure you know, you will have to answer 15 questions correctly in a row to be a Millionaire.");
        System.out.println("But you have 3 lifelines to work with: 50/50, ask the audience and call your friend.");
        System.out.println("So " + playerName + " let's begin!\n");
    }

    private void displayQuestion(Question[] questions, int offset) {
        String[] choiceLabels = Question.choiceLabels;

        for (int q = 0; q < 5; q++) {
            if (round == 5 || round == 10) {
                if (round == 5) {
                    prize = safeZone1Amount;
                } else {
                    prize = safeZone2Amount;
                }
                System.out.println(
                        "If you answer this question correctly, you'll reach the 'safe zone' and secure a $"
                                + prize + " prize.\n");
            }
            
// randomized question print
            int randomQuestionIndex = random.nextInt(QUESTIONS_PER_ROUND - q);
            Question selectedQuestion = questions[randomQuestionIndex];
            System.out.println((q + offset) + ". " + selectedQuestion.getQuestion());
            
// print choices
            for (int i = 0; i < choiceLabels.length; i++) {
                System.out.println(choiceLabels[i] + selectedQuestion.getChoices()[i]);
            }
            
// print lifelines if not used
            if (!lifeline1Used || !lifeline2Used || !lifeline3Used) {
                System.out.println("E) Use lifeline");
            }
            
// switch into check method
            check(selectedQuestion.getChoices(), selectedQuestion);
            if (offset == 6 || offset == 11) {
                askToContinue();
            }
            
//removed selected question in the array
            for (int i = randomQuestionIndex; i < QUESTIONS_PER_ROUND - q - 1; i++) {
                questions[i] = questions[i + 1];
            }
            questions[QUESTIONS_PER_ROUND - q - 1] = null;

            round++;
        }
    }

    private void displayEasyQuestion() {
        displayQuestion(easyQuestions, 1);
        displayMediumQuestion();
    }

    private void displayMediumQuestion() {
        displayQuestion(mediumQuestions, 6);
        displayHardQuestion();
    }

    private void displayHardQuestion() {
        displayQuestion(hardQuestions, 11);
    }
    
// check method
    private void check(String[] choices, Question answer) {
        while (true) {
            System.out.print("Choice: ");
            String input = scanner.nextLine().toUpperCase();

            if (input.isEmpty()) {
                System.out.println("Please enter a letter.");
                continue;
            } else {
                choice = input.charAt(0);
            }

            if (choice == 'A' || choice == 'B' || choice == 'C' || choice == 'D' || choice == 'E') {
                if (choice == 'E' && lifeline1Used && lifeline2Used && lifeline3Used) {
                    System.out.println("You already used all of your lifelines.");
                    continue;
                }
                if (!askIfFinalAnswer()) {
                    continue;
                }
                break;
                // error handler
            } else {
                System.out.println("Invalid choice. Choose between A-E only.");
                continue;
            }

        }

        convertedChoice = convertChoice(choice, choices, answer);
        winAmount = setWinAmount();
        
// E choices
        if (convertedChoice == "50/50"
                || convertedChoice == "Ask the Audience"
            || convertedChoice == "Phone a Friend") {
            //recursion loop in line 167
            check(choices, answer);

            //recursion loop in line 167 with Exiting lifeline menu
        } else if (convertedChoice.isEmpty()) {
            System.out.println("\nExiting lifeline menu.");
            check(choices, answer);
            
        } else {
            // if reach question 5 and 10
            if (round == 5 || round == 10) {
                System.out.println(
                        "\nCongratulations! You've just reached the 'safe zone' and secured a guaranteed prize of $"
                                + prize
                                + "! No matter what happens from this point forward, you won't leave with less than $"
                                + prize + ". Well done!");
            }

            if (answer.isCorrect(convertedChoice)) {
                player.setBalance(winAmount);

                // Check if the player has won the game
                if (round == 15) {
                    System.out.println(
                            "\nCongratulations! You've answered all 15 questions correctly and become a millionaire!");
                    System.out.println("You've won $" + winAmount + "! Well done, " + playerName + "!");
                    System.exit(0); // Exit the game
                }

                System.out.println("\nThat's correct!");
                System.out.println("\nCongratulations on advancing to the next round!");
                System.out.println("You've made it to Round " + (round + 1) + " with $" +
                        winAmount
                        + " in your pocket. Well done!\n");

            } else {
                if (round > 5 && round <= 10) { // 6-10
                    player.setBalance(safeZone1Amount); // 1k
                } else if (round >= 11 && round < 15) {// 11-14
                    player.setBalance(safeZone2Amount);// 32k
                }

                if (round > 1) {
                    System.out.println(
                            "\nYou answered incorrectly, but you're still walking away with $" + player.getBalance()
                                    + "!");
                } else {
                    System.out.println("\nYou didn't win any money this time. Better luck next time!");
                }
                System.exit(0);
            }
        }
    }

    private boolean askIfFinalAnswer() {
        if (choice != 'E') {
            while (true) {
                System.out.print("Is that your final answer? (Y/N) ");
                String inputFinalAnswerChoice = scanner.nextLine().toUpperCase();

                // error handler
                if (inputFinalAnswerChoice.isEmpty()) {
                    System.out.println("Please enter 'Y' for Yes or 'N' for No.");
                    continue;
                } else {
                    finalAnswerChoice = inputFinalAnswerChoice.charAt(0);
                }

                if (finalAnswerChoice == 'N') {
                    return false;
                } else if (finalAnswerChoice == 'Y') {
                    return true;
                } else {
                    System.out.println("Please enter 'Y' for Yes or 'N' for No.");
                }
            }
        } else {
            return true;
        }
    }

    private void askToContinue() {
        while (true) {
            System.out.println(
                    "You've done incredibly well, reaching Round " + round
                            + ", and now you're at a pivotal moment.");
            System.out.print("Do you want to continue and play for the million-dollar prize? (Y/N) ");
            String inputContinueChoice = scanner.nextLine().toUpperCase();

            if (inputContinueChoice.isEmpty()) {
                System.out.println("Please enter 'Y' for Yes or 'N' for No.");
                continue;
            } else {
                continueChoice = inputContinueChoice.charAt(0);
            }

            if (continueChoice == 'N') {
                System.out.println(
                        "\nThat's perfectly fine. You've done exceptionally well, and you're walking away with $"
                                + player.getBalance() + ". Congratulations on your impressive performance!");
                System.exit(0);
            } else if (continueChoice == 'Y') {
                System.out.println();
                break;
            } else {
                System.out.println("Please enter 'Y' for Yes or 'N' for No.");
            }
        }
    }

    private int setWinAmount() {
        return winningAmount[round];
    }

    private String convertChoice(char choice, String[] choices, Question question) {
        // switch statement
        switch (choice) {
            case 'A':
                return choices[0];
            case 'B':
                return choices[1];
            case 'C':
                return choices[2];
            case 'D':
                return choices[3];
            default:
                return pickLifeline(question);
        }
    }

    private String pickLifeline(Question question) {
        fiftyFifty = new FiftyFifty(question);
        askTheAudience = new AskTheAudience(question);
        phoneAFriend = new PhoneAFriend(question);

        // print the available lifelines
        System.out.println("\nLifelines:");
        if (!lifeline1Used) {
            System.out.println(lifeline1);
        }
        if (!lifeline2Used) {
            System.out.println(lifeline2);
        }
        if (!lifeline3Used) {
            System.out.println(lifeline3);
        }
        System.out.println("4) Exit");

        while (true) {
            System.out.print("Choice: ");
            String input = scanner.nextLine();

            if (input.isEmpty()) {
                System.out.println("Please enter a number.");
                continue;
            }

            try {
                lifelineChoice = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Please enter a number.");
                continue;
            }

            // Check if the number is between 1 to 4
            switch (lifelineChoice) {
                case 1:
                    if (lifeline1Used) {
                        System.out.println("You already used 50/50.");
                        continue;
                    }

                    fiftyFifty.display();
                    lifeline1Used = true;
                    lifeline1 = "1) You already used 50/50";
                    return "50/50";

                case 2:
                    if (lifeline2Used) {
                        System.out.println("You already used Ask the Audience.");
                        continue;
                    }

                    lifeline2 = "2) You already used Ask the Audience";
                    lifeline2Used = true;
                    askTheAudience.display();
                    return "Ask the Audience";

                case 3:
                    if (lifeline3Used) {
                        System.out.println("You already used Phone a Friend.");
                        continue;
                    }

                    lifeline3 = "3) You already used Phone a Friend";
                    lifeline3Used = true;
                    phoneAFriend.display();
                    return "Phone a Friend";

                case 4:
                    return "";
                default:
                    System.out.println("Invalid choice. Choose between 1 and 4 only.");
                    continue;
            }
        }
    }
}
