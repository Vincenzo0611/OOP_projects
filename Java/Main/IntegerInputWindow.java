package Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class IntegerInputWindow extends JFrame {
    private JTextField textField1;
    private JTextField textField2;
    private JButton button;

    private int number1;
    private int number2;

    public IntegerInputWindow() {
        setTitle("Podaj wymiary swiata");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(5, 1));

        JLabel label1 = new JLabel("X:"); // Dodano etykietę dla pierwszego pola tekstowego
        JLabel label2 = new JLabel("Y:"); // Dodano etykietę dla drugiego pola tekstowego

        textField1 = new JTextField();
        textField2 = new JTextField();

        button = new JButton("Submit");
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    number1 = Integer.parseInt(textField1.getText());
                    number2 = Integer.parseInt(textField2.getText());
                    dispose(); // Close the window
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(IntegerInputWindow.this, "Invalid input! Please enter valid integers.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        add(label1); // Dodano etykietę do kontenera
        add(textField1);
        add(label2); // Dodano etykietę do kontenera
        add(textField2);
        add(button);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public int getNumber1() {
        return number1;
    }

    public int getNumber2() {
        return number2;
    }

}