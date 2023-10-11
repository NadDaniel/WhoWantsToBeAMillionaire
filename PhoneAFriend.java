public class PhoneAFriend {

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
    private final String[] PROMPTS = {
            "Your Friend Says: Its Jupiter",
            "Your Friend Says: Of Course Its William Shakespeare",
            "Your Friend Says: Its Carbon dioxide Man",
            "Your Friend Says: I Think Its AU",
            "Your Friend Says: Of Course Its Blue Whale Dont You Know The Size Of A Blue Whale",
            "Your Friend Says: Im Sure Its 4",
            "Your Friend Says: Im Sure Its Filipino",
            "Your Friend Says: Of Course Its Ares",
            "Your Friend Says: I Believe Its XY",
            "Your Friend Says: Its February Try To Look In Calendar Next Time ",
            "Your Friend Says: Its Friction",
            "Your Friend Says: 13.8 billion years ",
            "Your Friend Says: Uranium (U) ",
            "Your Friends Says: 53 ",
            "Your Friend Says: \u221A2 (Square root of 2)" };

    private int answerIndex;
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

        prompt = PROMPTS[answerIndex];
        System.out.println("\nYou use Phone a Friend lifeline.");
        System.out.println(prompt);
    }

}
