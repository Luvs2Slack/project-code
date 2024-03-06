
/**
 * Write a description of class gamegui2 here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameGUI {
    private JFrame frame;
    private JPanel panel;
    private JButton singlePlayerButton;
    private JButton multiPlayerButton;
    private JButton registerButton;
    private JButton loginButton;
    private JButton viewScoresButton;
    private JButton quitButton;
    private Game game;  // Reference to the Game class

    public GameGUI(Game game) { // Pass the Game instance to the constructor
        this.game = game; // Store the Game instance

        // Initialize the JFrame and JPanel
        frame = new JFrame("Rock-Paper-Scissors-Lizard-Spock Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1500, 1200);

        panel = new JPanel();
        panel.setLayout(new GridLayout(6, 1));

        // Create buttons
        singlePlayerButton = new JButton("Single Player");
        multiPlayerButton = new JButton("Multiplayer");
        registerButton = new JButton("Register");
        loginButton = new JButton("Login");
        viewScoresButton = new JButton("View Scores");
        quitButton = new JButton("Quit");

        // Add buttons to the panel
        panel.add(singlePlayerButton);
        panel.add(multiPlayerButton);
        panel.add(registerButton);
        panel.add(loginButton);
        panel.add(viewScoresButton);
        panel.add(quitButton);
        
         // Add AI difficulty level selection
        String[] difficultyOptions = {"Easy", "Medium", "Hard"};
        JComboBox<String> difficultyComboBox = new JComboBox<>(difficultyOptions);
        panel.add(difficultyComboBox);
        // Add action listeners for buttons
        singlePlayerButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Handle single-player game logic
                game.startSinglePlayerGame();
            }
        });

        multiPlayerButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Handle multiplayer game logic
                game.startMultiPlayerGame();
            }
        });

        registerButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Handle registration logic
                game.registerUser();
            }
        });

        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Handle login logic
                game.loginUser();
            }
        });

        viewScoresButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Handle view scores logic
                game.viewScores();
            }
        });

        quitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.dispose(); // Close the application
            }
        });

        // Add the panel to the frame
        frame.add(panel);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        Game game = new Game(); // Create an instance of the Game class
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new GameGUI(game); // Pass the Game instance to the GUI constructor
            }
        });
    }
}
