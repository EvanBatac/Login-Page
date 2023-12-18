import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

public class LogIn {
    public static void main(String[] args) {
        UserNameAndPasswords userNameAndPasswords = new UserNameAndPasswords();
        new LoginPage(userNameAndPasswords.getLoginInfo());
    }
}

class UserNameAndPasswords {
    private HashMap<String, String> loginInfo = new HashMap<>();

    UserNameAndPasswords() {
        // Default admin login
        loginInfo.put("admin", "password");
    }

    public HashMap<String, String> getLoginInfo() {
        return loginInfo;
    }

    public void addLoginInfo(String username, String password) {
        loginInfo.put(username, password);
    }
}

class LoginPage implements ActionListener {

    private JFrame frame;
    private JLabel loginLabel, userNameLabel, passwordLabel, messageLabel;
    private JTextField userNamefield;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JButton registerButton; // New button for registration
    private JButton resetButton;

    private HashMap<String, String> loginInfo;

    LoginPage(HashMap<String, String> loginInfoOriginal) {
        this.loginInfo = loginInfoOriginal;

        frame = new JFrame();
        frame.getContentPane().setBackground(new Color(255, 204, 153)); // Light Orange background color

        loginLabel = new JLabel("LOG IN");
        loginLabel.setBounds(300, 100, 100, 50);
        loginLabel.setFont(new Font(null, Font.BOLD, 28));
        loginLabel.setForeground(new Color(153, 51, 0)); // Brown font color

        userNameLabel = new JLabel("User Name:");
        userNameLabel.setBounds(150, 200, 100, 30);
        userNameLabel.setFont(new Font(null, Font.BOLD, 15));
        userNameLabel.setForeground(new Color(153, 51, 0)); // Brown font color

        userNamefield = new JTextField();
        userNamefield.setBounds(300, 200, 200, 30);

        passwordLabel = new JLabel("Password: ");
        passwordLabel.setBounds(150, 250, 100, 30);
        passwordLabel.setFont(new Font(null, Font.BOLD, 15));
        passwordLabel.setForeground(new Color(153, 51, 0)); // Brown font color

        passwordField = new JPasswordField();
        passwordField.setBounds(300, 250, 200, 30);

        loginButton = new JButton("Login");
        loginButton.setBounds(280, 350, 100, 30);
        loginButton.addActionListener(this);
        loginButton.setBackground(new Color(204, 153, 102)); // Light Brown button color
        loginButton.setForeground(new Color(51, 25, 0)); // Dark Brown font color

        registerButton = new JButton("Register");
        registerButton.setBounds(400, 350, 100, 30);
        registerButton.addActionListener(this);
        registerButton.setBackground(new Color(204, 153, 102)); // Light Brown button color
        registerButton.setForeground(new Color(51, 25, 0)); // Dark Brown font color

        resetButton = new JButton("Reset");
        resetButton.setBounds(340, 400, 100, 30);
        resetButton.addActionListener(this);
        resetButton.setBackground(new Color(204, 153, 102)); // Light Brown button color
        resetButton.setForeground(new Color(51, 25, 0)); // Dark Brown font color

        messageLabel = new JLabel();
        messageLabel.setBounds(250, 450, 200, 30);
        messageLabel.setForeground(new Color(102, 51, 0)); // Dark Brown font color

        frame.add(loginLabel);
        frame.add(userNameLabel);
        frame.add(userNamefield);
        frame.add(passwordLabel);
        frame.add(passwordField);
        frame.add(loginButton);
        frame.add(registerButton);
        frame.add(resetButton);
        frame.add(messageLabel);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(700, 700);
        frame.setLayout(null);
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == resetButton) {
            userNamefield.setText("");
            passwordField.setText("");
            messageLabel.setText("");
        } else if (e.getSource() == loginButton) {
            performLogin();
        } else if (e.getSource() == registerButton) {
            performRegistration();
        }
    }

    private void performLogin() {
        String userID = userNamefield.getText();
        String password = String.valueOf(passwordField.getPassword());

        if (loginInfo.containsKey(userID)) {
            if (loginInfo.get(userID).equals(password)) {
                messageLabel.setForeground(new Color(102, 51, 0)); // Dark Brown font color
                messageLabel.setText("Login successful");
                new Menu(userID);
                frame.dispose();
            } else {
                messageLabel.setForeground(Color.red);
                messageLabel.setText("Wrong password");
            }
        } else {
            messageLabel.setForeground(Color.red);
            messageLabel.setText("Username not found");
        }
    }

    private void performRegistration() {
        String userID = userNamefield.getText();
        String password = String.valueOf(passwordField.getPassword());

        if (!userID.isEmpty() && !password.isEmpty()) {
            if (!loginInfo.containsKey(userID)) {
                loginInfo.put(userID, password);
                messageLabel.setForeground(new Color(0, 128, 0)); // Dark Green font color
                messageLabel.setText("Registration successful");
            } else {
                messageLabel.setForeground(Color.red);
                messageLabel.setText("Username already exists");
            }
        } else {
            messageLabel.setForeground(Color.red);
            messageLabel.setText("Please enter both username and password");
        }
    }
}