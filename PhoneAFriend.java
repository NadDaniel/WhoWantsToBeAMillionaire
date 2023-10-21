import java.util.Scanner;

public class PhoneAFriend {

    private Scanner scanner = new Scanner(System.in);

    private String answer;
    private final String[] ANSWERS = {
            "Jupiter",
            "William Shakespeare",
            "Carbon dioxide",
            "Au",
            "Blue whale",
            "4",
            "Filipino",
            "Ares",
            "XY",
            "February",
            "Friction",
            "13.8 billion years",
            "Uranium (U)",
            "53",
            "\u221A2 (Square root of 2)" };
    private final String[] FRIENDS_NAME = {
            "Maria",
            "Juan",
            "Andrea",
            "Jose",
            "Isabella"
    };
    private final String[] PROMPTS = {
            "Its Jupiter",
            "Of Course Its William Shakespeare",
            "Its Carbon dioxide Man",
            "I Think Its AU",
            "Of Course Its Blue Whale Dont You Know The Size Of A Blue Whale",
            "Im Sure Its 4",
            "Im Sure Its Filipino",
            "Of Course Its Ares",
            "I Believe Its XY",
            "Its February Try To Look In Calendar Next Time",
            "Its Friction",
            "13.8 billion years",
            "Uranium",
            "53",
            "\u221A2 (Square root of 2)" };

    // PhoneAFriend object
    private int answerIndex;
    private String inputFriendChoice;
    private String friendName;
    private int friendChoice;
    private String prompt;

    public PhoneAFriend(Question question) {
        answer = question.getAnswer();
    }

    public void display() {
        for (int i = 0; i < ANSWERS.length; i++) {
            if (answer.equals(ANSWERS[i])) {
                answerIndex = i;
            }
        }
        friendName = pickFriend();
        prompt = "Your friend " + friendName + " says: " + PROMPTS[answerIndex];
        System.out.println("\nYou use Phone a Friend lifeline.");
        System.out.println(prompt);
    }

    private String pickFriend() {
        System.out.println("\nChoose a friend to call:");
        for (int i = 0; i < FRIENDS_NAME.length; i++) {
            System.out.println((i + 1) + ") " + FRIENDS_NAME[i]);
        }

        while (true) {
            System.out.print("Choice: ");
            inputFriendChoice = scanner.nextLine();

            // error handler
            if (inputFriendChoice.isEmpty()) {
                System.out.println("Please enter a number.");
                continue;
            }

            try {
                friendChoice = Integer.parseInt(inputFriendChoice);
            } catch (NumberFormatException e) {
                System.out.println("Please enter a number.");
                continue;
            }

            if (friendChoice >= 1 && friendChoice <= 5) {
                return FRIENDS_NAME[friendChoice - 1];
            } else {
                System.out.println("Please enter between 1 to 5 only.");
                continue;
            }
        }
    }

}
