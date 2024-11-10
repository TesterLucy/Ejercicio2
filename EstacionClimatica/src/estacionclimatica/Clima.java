package estacionclimatica;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class Clima {
    private static JFrame frame;
    private static JTable tabla;
    private static String[][] tablaData;

    public Clima(int cantidadDias) {
        tablaData = new String[cantidadDias][3];
    }
    

    public void inicializarVentana() {
        // Crear un JFrame para contener la tabla
        frame = new JFrame("Tabla día por temperaturas");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Solicitar al usuario la cantidad de días mediante una ventana de diálogo
        String valor = JOptionPane.showInputDialog(frame, "Ingrese la Cantidad de Días");
        int cantidadDias = Integer.parseInt(valor);

        // Crear una matriz bidimensional para almacenar los datos de la tabla
        tablaData = new String[cantidadDias][3];

        // Solicitar al usuario los datos de temperatura para cada día
        for (int i = 0; i < tablaData.length; i++) {
            String dia = JOptionPane.showInputDialog(frame, "Ingrese el día: ");
            tablaData[i][0] = dia;

            String TemM = JOptionPane.showInputDialog(frame, "Ingrese la temperatura máxima del día " + dia);
            tablaData[i][1] = TemM;
            while (Integer.parseInt(tablaData[i][1])>40) {
                String TemM2 = JOptionPane.showInputDialog(frame, "Ingrese una temperatura máxima válida (menor a 40)");
                tablaData[i][1]=TemM2;
            }

            String TemMi = JOptionPane.showInputDialog(frame, "Ingrese la temperatura mínima del día " + dia);
            tablaData[i][2] = TemMi;
            while (Integer.parseInt(tablaData[i][1])<(40*-1)) {
                String TemM2 = JOptionPane.showInputDialog(frame, "Ingrese una temperatura mínima válida (mayor a -40)");
                tablaData[i][2]=TemM2;
            }        
        }

        // Crear un modelo de tabla
        DefaultTableModel modelo = new DefaultTableModel(tablaData, new String[]{"Día", "Temperatura Máxima", "Temperatura Mínima"});

        // Crear una JTable y asignar el modelo
        tabla = new JTable(modelo);
        // Deshabilitar la reordenación de columnas con el mouse
        tabla.getTableHeader().setReorderingAllowed(false);

        // Crear un JScrollPane y agregar la JTable
        JScrollPane scrollPane = new JScrollPane(tabla);

        // Agregar el JScrollPane al JFrame
        frame.add(scrollPane, BorderLayout.CENTER);

        // Mostrar el JFrame
        frame.pack();
        frame.setVisible(true);
    }

    public void mostrarVentanaEdicion() {
        String textoMenu = "Selecciona lo que deseas hacer:\n" +
                "1. Días y temperatura, cuyas temperaturas máximas estén entre 15 y 20 grados.\n" +
                "2. Días y temperatura, cuyas temperaturas mínimas estén por debajo de 0 grados.\n" +
                "3. Calcular las medias máxima y mínima de temperaturas.\n" +
                "4. Salir: para salir del programa.";

        Object[] options = {"1", "2", "3", "Salir"};

        int choice = JOptionPane.showOptionDialog(frame,
                new Object[]{textoMenu},
                "Tabla la temperatuda por dia",
                JOptionPane.INFORMATION_MESSAGE,
                JOptionPane.PLAIN_MESSAGE,
                null,
                options,
                options[0]);

        switch (choice) {
            case 0:
                sapa();
                break;
            case 1:
                perra();
                break;
            case 2:
                estupido();
                break;
            case 3:
                // Salir del programa
                System.exit(0);
                break;
        }

        // Mostrar nuevamente la ventana de edici�n despu�s de realizar la acci�n seleccionada
        mostrarVentanaEdicion();
    }

    public void sapa() {
        StringBuilder resultado = new StringBuilder();

        // Iterar sobre los datos de temperatura almacenados
        for (String[] dia : tablaData) {
            int temperaturaMaxima = Integer.parseInt(dia[1]);
            if (temperaturaMaxima >= 15 && temperaturaMaxima <= 20) {
                resultado.append("Día: ").append(dia[0]).append(", Temperatura Máxima: ").append(dia[1]).append(", Temperatura Mínima: ").append(dia[2]).append("\n");
            }
        }

        // Mostrar los resultados en un cuadro de diálogo
        JOptionPane.showMessageDialog(frame, resultado.toString(), "Temperaturas entre 15 y 20 grados", JOptionPane.INFORMATION_MESSAGE);
    } 
    
    public void perra(){
        StringBuilder resultado = new StringBuilder();
        // Iterar sobre los datos de temperatura almacenados
        for (String[] dia : tablaData) {
            int temperaturaMinima = Integer.parseInt(dia[2]);
            if (temperaturaMinima < 0) {
                resultado.append("Día: ").append(dia[0]).append(", Temperatura Máxima: ").append(dia[1]).append(", Temperatura Mínima: ").append(dia[2]).append("\n");
            }
        }

        // Mostrar los resultados en un cuadro de diálogo
        JOptionPane.showMessageDialog(frame, resultado.toString(), "Temperaturas por debajo de 0 grados", JOptionPane.INFORMATION_MESSAGE);
    }

    public void estupido(){
        int totalTemperaturasMaximas = 0;
        int totalTemperaturasMinimas = 0;

        // Iterar sobre los datos de temperatura almacenados
        for (String[] dia : tablaData) {
            // Sumar las temperaturas máximas y mínimas
            totalTemperaturasMaximas += Integer.parseInt(dia[1]);
            totalTemperaturasMinimas += Integer.parseInt(dia[2]);
        }

        // Calcular las medias
        double mediaMaxima = (double) totalTemperaturasMaximas / tablaData.length;
        double mediaMinima = (double) totalTemperaturasMinimas / tablaData.length;

        // Mostrar las medias
        JOptionPane.showMessageDialog(frame,
                "Media de temperaturas máximas: " + mediaMaxima + "\n" +
                "Media de temperaturas mínimas: " + mediaMinima,
                "Medias de temperaturas",
                JOptionPane.INFORMATION_MESSAGE);
    }   
}
    
    