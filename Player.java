import java.util.Scanner;

public class Player {

    private Scanner scanner = new Scanner(System.in);

    private int balance = 0;
    private String playerName;

    public Player() {
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public String getPlayerName() {
        while (true) {
            System.out.print("Enter your name: ");
            playerName = scanner.nextLine().trim();

            // Check if the trainer entered their name
            if (playerName.isEmpty()) {
                System.out.println("Please enter your name. It cannot be empty.");
                continue;
            } else {
                return playerName;
            }
        }
    }

}
