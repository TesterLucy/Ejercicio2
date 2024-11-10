package arreglo.cadenas;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ArregloCadenas extends JFrame implements ActionListener {
    private JTextField textField;
    private JLabel label;

    public ArregloCadenas() {
        setLayout(new FlowLayout());

        textField = new JTextField(10);
        add(textField);

        label = new JLabel("Carácter: ");
        add(label);

        textField.addActionListener(this);

        setTitle("Leer y visualizar caracteres Java");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(300, 100);
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String texto = textField.getText();
        if (!texto.isEmpty()) {
            char caracter = texto.charAt(0);
            label.setText("Carácter: " + caracter);
        } else {
            label.setText("Carácter: ");
        }
        textField.selectAll();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(ArregloCadenas::new);
    }
}