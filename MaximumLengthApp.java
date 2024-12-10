import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MaximumLengthApp {
    public static void main(String[] args) {
        // Create the main frame
        JFrame frame = new JFrame("Maximum Length Finder");
        frame.setSize(400, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);

        // Label for instruction
        JLabel instructionLabel = new JLabel("Enter a substring:");
        instructionLabel.setBounds(20, 20, 150, 25);
        frame.add(instructionLabel);

        // Text field for input
        JTextField inputField = new JTextField();
        inputField.setBounds(150, 20, 200, 25);
        frame.add(inputField);

        // Button to calculate result
        JButton calculateButton = new JButton("Calculate");
        calculateButton.setBounds(150, 60, 100, 30);
        frame.add(calculateButton);

        // Label to display the result
        JLabel resultLabel = new JLabel("Result: ");
        resultLabel.setBounds(20, 110, 350, 25);
        frame.add(resultLabel);

        // Add action listener to the button
        calculateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String input = inputField.getText();
                if (input.isEmpty()) {
                    resultLabel.setText("Result: Please enter a valid substring.");
                } else {
                    int result = maximumLength(input);
                    resultLabel.setText("Result: " + (result == -1 ? "No valid length found" : result));
                }
            }
        });

        // Set frame visibility
        frame.setVisible(true);
    }

    // Method to calculate maximum length
    public static int maximumLength(String s) {
        int[][] lookup = new int[26][3];
        int result = 0;

        for (int i = 0, cnt = 0; i < s.length(); ++i) {
            ++cnt;

            if (i + 1 < s.length() && s.charAt(i + 1) == s.charAt(i)) {
                continue;
            }

            int[] curr = lookup[s.charAt(i) - 'a'];

            for (int j = 0; j < 3; ++j) {
                if (curr[j] < cnt) {
                    int temp = cnt;
                    cnt = curr[j];
                    curr[j] = temp;
                }
            }

            cnt = 0;
            int max1 = curr[0] - 2;
            int max2 = Math.min(curr[0] - 1, curr[1]);
            int max3 = curr[2];
            result = Math.max(result, Math.max(max1, Math.max(max2, max3)));
        }

        return result == 0 ? -1 : result;
    }
}
