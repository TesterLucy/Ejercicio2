package numeros.enteros;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NumerosEnteros extends JFrame {
    private JTextField textField;
    private JLabel label;

    public NumerosEnteros() {
        setLayout(new FlowLayout());

        textField = new JTextField(10);
        add(textField);

        label = new JLabel(":");
        add(label);

        JButton button = new JButton("Convertir a número");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String text = textField.getText();
                try {
                    int number = Integer.parseInt(text);
                    label.setText(": " + number);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(NumerosEnteros.this,
                            "Por favor, ingrese un número entero válido.",
                            "Error",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        add(button);

        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Conversor de números enteros escritos");
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static void main(String[] args) {
        new NumerosEnteros();
    }
}