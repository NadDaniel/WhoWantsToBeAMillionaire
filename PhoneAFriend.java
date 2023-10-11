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
    	    "Your friend confidently suggests: Is it Jupiter",
    	    "Your friend's firm answer is: It's William Shakespeare, no doubt.",
    	    "Your friend asserts: Carbon dioxide is the correct answer.",
    	    "Your friend states: It must be Au (Gold).",
    	    "Your friend is convinced: The answer is the Blue Whale, the largest mammal.",
    	    "Your friend's response is: It's definitely 4.",
    	    "Your friend's recommendation is: It's Filipino, I'm certain.",
    	    "Your friend confidently claims: Ares is the right choice.",
    	    "Your friend's opinion is: I believe it's XY.",
    	    "Your friend advises: It's February, check the calendar next time.",
    	    "Your friend's response is: It's undoubtedly Friction.",
    	    "Your friend states: The age of the universe is 13.8 billion years.",
    	    "Your friend suggests: The answer is Uranium (U).",
    	    "Your friend firmly asserts: It's 53, no doubt about it.",
    	    "Your friend's answer is: It's the square root of 2 (√2)."
    	};

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
