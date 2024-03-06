
/**
 * Write a description of class game2 here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
import java.util.HashMap;
import javax.swing.JOptionPane;

public class Game {
    private HashMap<String, Integer> userScores = new HashMap<>();
    private String currentUser = null;
    private String[] choices = {"Rock", "Paper", "Scissors", "Lizard", "Spock"};
    
    private enum Difficulty { EASY, MEDIUM, HARD }
    private Difficulty computerDifficulty = Difficulty.HARD;  
    private String getComputerChoice; 
    
    public void startSinglePlayerGame() {
        if (currentUser == null) {
            showErrorMessage("Please log in to play in single-player mode.");
            return;
        }
        
        
        // Prompt the user to select the difficulty level
        String[] difficultyOptions = { "Easy", "Medium", "Hard" };
        String selectedDifficulty = (String) JOptionPane.showInputDialog(
            null,
            "Select computer difficulty level:",
            "Single Player Game",
            JOptionPane.PLAIN_MESSAGE,
            null,
            difficultyOptions,
            difficultyOptions[0]
        );

        if (selectedDifficulty == null) {
            // User canceled the selection
            return;
        }

        // Set the computer difficulty level
        if (selectedDifficulty.equals("Easy")) {
            computerDifficulty = Difficulty.EASY;
        } else if (selectedDifficulty.equals("Medium")) {
            computerDifficulty = Difficulty.MEDIUM;
        } else {
            computerDifficulty = Difficulty.HARD;
        }
        // Prompt the user to make a choice
        String[] choices = {"Rock", "Paper", "Scissors", "Lizard", "Spock"};
        String playerChoice = (String) JOptionPane.showInputDialog(
            null,
            "Select your move:",
            "Single Player Game",
            JOptionPane.PLAIN_MESSAGE,
            null,
            choices,
            choices[0]
        );

        if (playerChoice == null) {
            // User canceled the selection
            return;
        }

        // Generate a random computer choice
        String getComputerChoice = choices[(int) (Math.random() * choices.length)];

        // Determine the winner
        int simulateGameResult = simulateGameResult(playerChoice,getComputerChoice);

        // Update and display the score
        updateScore(simulateGameResult);
        showMessage("Computer chose " + getComputerChoice + ". " + getResultMessage(simulateGameResult));
    }
    
    public void startMultiPlayerGame() {
        if (currentUser == null) {
            showErrorMessage("Please log in to play in multiplayer mode.");
            return;
        }

        // Prompt both players to make choices (simulated here)
        String[] choices = {"Rock", "Paper", "Scissors", "Lizard", "Spock"};
        String player1Choice = (String) JOptionPane.showInputDialog(
            null,
            currentUser + ", select your move:",
            "Multiplayer Game",
            JOptionPane.PLAIN_MESSAGE,
            null,
            choices,
            choices[0]
        );

        if (player1Choice == null) {
            // User canceled the selection
            return;
        }

        String player2Choice = (String) JOptionPane.showInputDialog(
            null,
            "Player 2, select your move:",
            "Multiplayer Game",
            JOptionPane.PLAIN_MESSAGE,
            null,
            choices,
            choices[0]
        );

        if (player2Choice == null) {
            // User canceled the selection
            return;
        }

        // Determine the winner
        int result = simulateGameResult(player1Choice, player2Choice);

        // Update and display the score
        updateScore(result);
        showMessage(getResultMessage(result));
    }

    public void registerUser() {
        // Implement user registration logic here (e.g., using JOptionPane)
        String username = JOptionPane.showInputDialog("Enter a username:");
        String password = JOptionPane.showInputDialog("Enter a password:");
        if (username != null && !username.isEmpty()) {
            userScores.put(username, 0);
            showMessage("User registered: " + username);
        } else {
            showErrorMessage("Registration failed. Please enter a valid username.");
        }
        if (password != null && !password.isEmpty()) {
            userScores.put(password, 0);
        } else {
            showErrorMessage("Registration failed. Please enter your password.");
        }
    }

    public void loginUser() {
        // Implement user login logic here (e.g., using JOptionPane)
        String username = JOptionPane.showInputDialog("Enter your username:");
        String password= JOptionPane.showInputDialog("Enter your password:");
        if (userScores.containsKey(username )) {
            
            currentUser = username;
        } else {
            showErrorMessage("Login failed. User not found.");
        }
        if (userScores.containsKey(password )) {
            
            currentUser = username;
        } else {
            showErrorMessage("Login failed. User not found.");
        }
        if (userScores.containsKey(username) && userScores.containsKey(password)) {
            showMessage("login successful, welcome " + username);
        } else {
            showErrorMessage("login failed, please enter a valid username and password");
        
        }
    }
    
    public void viewScores() {
        // Display user scores using JOptionPane
        StringBuilder scoresText = new StringBuilder("User Scores:\n");
        for (String username : userScores.keySet()) {
            scoresText.append(username).append(": ").append(userScores.get(username)).append("\n");
        }
        showMessage(scoresText.toString());
    }

    

    private void updateScore(int result) {
        if (result == 1) {
            userScores.put(currentUser, userScores.get(currentUser) + 1);
        } else if (result == 2) {
            userScores.put(currentUser, userScores.get(currentUser) - 1);
        }
    }
    private int simulateGameResult(String choice1, String choice2) {
    if (choice1.equals(choice2)) {
        return 0; // It's a tie
    } else if (
        (choice1.equals("Rock") && (choice2.equals("Scissors") || choice2.equals("Lizard"))) ||
        (choice1.equals("Paper") && (choice2.equals("Rock") || choice2.equals("Spock"))) ||
        (choice1.equals("Scissors") && (choice2.equals("Paper") || choice2.equals("Lizard"))) ||
        (choice1.equals("Lizard") && (choice2.equals("Spock") || choice2.equals("Paper"))) ||
        (choice1.equals("Spock") && (choice2.equals("Scissors") || choice2.equals("Rock")))
    ) {
        return 1; // Player 1 wins
    } else {
        return 2; // Player 2 wins
    }
    }

    private String getResultMessage(int simulateGameResult) {
        if (simulateGameResult == 1) {
            return "Player1 wins";
        } else if (simulateGameResult == 2) {
            return "Player2 /computer wins";
        } else {
            return "It's a tie!";
        }
    }
     private String getComputerChoice(int level, String playerChoice) {
    if (level == 0) { // Easy level (random choice)
        return choices[(int) (Math.random() * choices.length)];
    } else if (level == 1) { // Medium level (picks the choice that beats the user's last choice)
        String[] choicesToBeat = {"Paper", "Scissors", "Rock", "Spock", "Lizard"};
        return choicesToBeat[(int) (Math.random() * choicesToBeat.length)];
    } else if (level == 2) { // Hard level (counter the user's choice)
        String counterChoice = null;

        // Implement logic to counter the player's choice effectively
        switch (playerChoice) {
            case "Rock":
                counterChoice = "Paper";
                counterChoice = "Spock";
                break;
            case "Paper":
                counterChoice = "Scissors";
                counterChoice = "Lizard";
                break;
            case "Scissors":
                counterChoice = "Rock";
                counterChoice = "Spock";
                break;
            case "Lizard":
                counterChoice = "Scissors";
                counterChoice = "Rock";
                break;
            case "Spock":
                counterChoice = "paper";
                counterChoice = "Lizard";
                break;
        }

        return counterChoice;
    }

    return null; // Invalid level
 }

    
    
        private void showMessage(String message) {
        JOptionPane.showMessageDialog(null, message);
    }

    private void showErrorMessage(String message) {
        JOptionPane.showMessageDialog(null, message, "Error", JOptionPane.ERROR_MESSAGE);
   }
}


