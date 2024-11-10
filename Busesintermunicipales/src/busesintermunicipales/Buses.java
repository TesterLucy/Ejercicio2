package busesintermunicipales;

import java.awt.BorderLayout;
import java.util.Scanner;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Bocetos
 */
public class Buses {

    private JFrame frame;
    private JTable tabla;
    private int cantidadVehiculos;
    private String[][] tablaData;
    private String[] columnas = {"Bus #", "Lunes", "Martes", "Miércoles", "Jueves", "Viernes", "Sábado", "Domingo"};
    private Scanner teclado;

    public Buses(int cantidadVehiculos) {
        this.cantidadVehiculos = cantidadVehiculos;
        this.tablaData = new String[cantidadVehiculos][8];
        this.teclado = new Scanner(System.in);
        this.frame = new JFrame("Tabla de Ventas de Vehículos");
    }

    public void inicializarVentana() {

        // Solicitar las ventas de cada vehďż˝culo y dďż˝a de la semana
        for (int i = 0; i < cantidadVehiculos; i++) {
            for (int j = 0; j < 8; j++) {
                if (j == 0) {
                    tablaData[i][j] = "Bus #" + (i + 1);
                } else {
                    // Pedir las ventas del dďż˝a mediante una ventana de diďż˝logo
                    String ventas = JOptionPane.showInputDialog(frame, "Ingrese las ventas del día " + columnas[j] + " del vehículo #" + (i + 1) + ":");
                    tablaData[i][j] = ventas;
                }
            }
        }

        // Crear un modelo de tabla
        DefaultTableModel modelo = new DefaultTableModel(tablaData, columnas);

        // Crear un JTable y asignarle el modelo
        tabla = new JTable(modelo);

        // Deshabilitar el movimiento de columnas con el ratďż˝n
        tabla.getTableHeader().setReorderingAllowed(false);

        // Crear un JScrollPane y agregar el JTable
        JScrollPane scrollPane = new JScrollPane(tabla);

        // Agregar el JScrollPane al JFrame
        frame.add(scrollPane, BorderLayout.CENTER);

        // Mostrar el JFrame
        frame.pack();
        frame.setVisible(true);
    }

    public void mostrarVentanaEdicion() {
        String textoMenu = "Selecciona lo que deseas hacer:\n"
                + "1. Hallar mayor y menor.\n"
                + "2. Mostrar por cada bus el dia que más vendió.\n"
                + "3. Mostrar tabla ajustada con el 20% más.\n"
                + "Salir: para salir del programa.";

        Object[] options = {"1", "2", "3", "Salir"};

        int choice = JOptionPane.showOptionDialog(frame,
                new Object[]{textoMenu},
                "Tabla de Ventas de Vehículos",
                JOptionPane.INFORMATION_MESSAGE,
                JOptionPane.PLAIN_MESSAGE,
                null,
                options,
                options[0]);

        switch (choice) {
            case 0:
                calcularMayorYMenor();
                break;
            case 1:
                mostrarDiaConMayorVenta();
                break;
            case 2:
                incrementarValoresBajosPromedio();
                break;
            case 3:
                // Salir del programa
                System.exit(0);
                break;
        }
        mostrarVentanaEdicion();
    }

    public void calcularMayorYMenor() {
        int mayorSuma = Integer.MIN_VALUE; // Inicializar con el valor mďż˝nimo posible
        int menorSuma = Integer.MAX_VALUE; // Inicializar con el valor mďż˝ximo posible
        int lineaMayor = -1;
        int lineaMenor = -1;

        // Iterar sobre el arreglo
        for (int i = 0; i < tablaData.length; i++) {
            int suma = 0;
            for (int j = 1; j < tablaData[i].length; j++) { // Comenzar desde j = 1 para omitir la primera columna
                suma += Integer.parseInt(tablaData[i][j]);
            }
            // Comparar la suma actual con la mayor y menor encontradas hasta el momento
            if (suma > mayorSuma) {
                mayorSuma = suma;
                lineaMayor = i;
            }
            if (suma < menorSuma) {
                menorSuma = suma;
                lineaMenor = i;
            }
        }

        // Crear un mensaje con los resultados
        String mensajeResultado = "El bus que más vendió en la semana fue el bus #" + (lineaMayor + 1) + " con un total de " + mayorSuma + "\n" + "El bus que más vendió en la semana fue el bus #" + (lineaMenor + 1) + " con un total de " + menorSuma;

        // Mostrar el mensaje utilizando un JOptionPane
        JOptionPane.showMessageDialog(frame, mensajeResultado, "Resultados", JOptionPane.PLAIN_MESSAGE);
    }

    public void mostrarDiaConMayorVenta() {
        // Crear un arreglo para almacenar el dďż˝a con mayor venta de cada vehďż˝culo
        String[] diasConMayorVenta = new String[tablaData.length];

        // Iterar sobre el arreglo
        for (int i = 0; i < tablaData.length; i++) {
            int mayorVenta = Integer.MIN_VALUE;
            int indiceDelDiaConMayorVenta = -1;

            for (int j = 1; j < tablaData[i].length; j++) {
                int venta = Integer.parseInt(tablaData[i][j]);
                if (venta > mayorVenta) {
                    mayorVenta = venta;
                    indiceDelDiaConMayorVenta = j - 1;
                }
            }

            // Obtener el nombre del dďż˝a de la semana correspondiente al ďż˝ndice
            String[] diasDeLaSemana = {"Lunes", "Martes", "Miércoles", "Jueves", "Viernes", "Sábado", "Domingo"};
            diasConMayorVenta[i] = diasDeLaSemana[indiceDelDiaConMayorVenta];
        }

        // Crear un mensaje con los resultados
        StringBuilder mensajeResultado = new StringBuilder();
        for (int i = 0; i < tablaData.length; i++) {
            mensajeResultado.append("El vehículo #").append(i + 1).append(" tuvo su mayor venta el día ").append(diasConMayorVenta[i]).append(".\n");
        }

        // Mostrar el mensaje utilizando un JOptionPane
        JOptionPane.showMessageDialog(frame, mensajeResultado.toString(), "Días con Mayor Venta", JOptionPane.PLAIN_MESSAGE);
    }

    public void incrementarValoresBajosPromedio() {
        double sumaTotal = 0;
        int contadorValores = 0;
        String tabla2[][] = new String[tablaData.length][tablaData[0].length];

        for (int i = 0; i < tablaData.length; i++) {
            for (int j = 1; j < tablaData[i].length; j++) { // Empezamos desde j = 1 para excluir la primera columna
                sumaTotal += Integer.parseInt(tablaData[i][j]);
                contadorValores++;
            }
        }

        double promedio = sumaTotal / contadorValores; // Promedio de todos los valores

        for (int i = 0; i < tablaData.length; i++) {
            for (int j = 0; j < tablaData[i].length; j++) {
                if (j != 0 && Integer.parseInt(tablaData[i][j]) < promedio) {
                    double resultado = Integer.parseInt(tablaData[i][j]) * 1.2;
                    // Limitar los valores a dos decimales
                    resultado = Math.round(resultado * 100.0) / 100.0;
                    tabla2[i][j] = String.format("%.2f", resultado);
                } else {
                    tabla2[i][j] = tablaData[i][j];
                }
            }
        }

        // Crear la cadena de texto para la tabla
        StringBuilder tableString = new StringBuilder();
        for (String columnName : columnas) {
            tableString.append(columnName).append("\t");
        }
        tableString.append("\n");
        for (Object[] rowData : tabla2) {
            for (Object cellData : rowData) {
                tableString.append(cellData).append("\t");
            }
            tableString.append("\n");
        }

        // Construir el mensaje adicional con el promedio
        String mensaje = "El promedio es: " + String.format("%.2f", promedio) + ". Aquí está la tabla con los valores por debajo del promedio aumentados en un 20%.";

        // Crear JTextArea para el mensaje
        JTextArea mensajeTextArea = new JTextArea(mensaje);
        mensajeTextArea.setEditable(false);

        // Crear JTextArea para la tabla
        JTextArea tablaTextArea = new JTextArea(tableString.toString());
        tablaTextArea.setEditable(false);

        // Crear JPanel y establecer BorderLayout
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(mensajeTextArea, BorderLayout.NORTH);
        panel.add(new JScrollPane(tablaTextArea), BorderLayout.CENTER);

        // Mostrar el JOptionPane con el JPanel que contiene el mensaje y la tabla
        JOptionPane.showMessageDialog(frame, panel, "Tabla con 20% aumentos", JOptionPane.PLAIN_MESSAGE);
    }

}
