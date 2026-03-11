import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class TicTacToeGUI extends JFrame implements ActionListener {

    JButton[] buttons = new JButton[9];
    char currentPlayer = 'X';
    JLabel statusLabel;

    TicTacToeGUI() {
        setTitle("Tic Tac Toe Game");
        setSize(400, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Status label
        statusLabel = new JLabel("Player X Turn", JLabel.CENTER);
        statusLabel.setFont(new Font("Arial", Font.BOLD, 20));
        add(statusLabel, BorderLayout.NORTH);

        // Panel for buttons
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 3));

        for (int i = 0; i < 9; i++) {
            buttons[i] = new JButton("");
            buttons[i].setFont(new Font("Arial", Font.BOLD, 40));
            buttons[i].addActionListener(this);
            panel.add(buttons[i]);
        }

        add(panel, BorderLayout.CENTER);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        JButton btn = (JButton) e.getSource();

        if (btn.getText().equals("")) {
            btn.setText(String.valueOf(currentPlayer));

            if (checkWin()) {
                statusLabel.setText("Player " + currentPlayer + " Wins!");
                disableButtons();
            } else if (isDraw()) {
                statusLabel.setText("Game Draw!");
            } else {
                currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
                statusLabel.setText("Player " + currentPlayer + " Turn");
            }
        }
    }

    boolean checkWin() {
        int[][] winPositions = {
            {0,1,2},{3,4,5},{6,7,8},
            {0,3,6},{1,4,7},{2,5,8},
            {0,4,8},{2,4,6}
        };

        for (int[] pos : winPositions) {
            if (buttons[pos[0]].getText().equals(String.valueOf(currentPlayer)) &&
                buttons[pos[1]].getText().equals(String.valueOf(currentPlayer)) &&
                buttons[pos[2]].getText().equals(String.valueOf(currentPlayer))) {
                return true;
            }
        }
        return false;
    }

    boolean isDraw() {
        for (JButton btn : buttons) {
            if (btn.getText().equals(""))
                return false;
        }
        return true;
    }

    void disableButtons() {
        for (JButton btn : buttons) {
            btn.setEnabled(false);
        }
    }

    public static void main(String[] args) {
        new TicTacToeGUI();
    }
}