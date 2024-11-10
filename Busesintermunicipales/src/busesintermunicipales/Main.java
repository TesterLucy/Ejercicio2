package busesintermunicipales;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author Bocetos
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // Crear un JFrame para contener la tabla
        JFrame frame = new JFrame("Tabla de Ventas de Vehículos");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Pedir al usuario la cantidad de vehďż˝culos mediante una ventana de diďż˝logo
        String valor = JOptionPane.showInputDialog(frame, "Ingresa la cantidad de vehículos:");
        int cantidadVehiculos = Integer.parseInt(valor);

        Buses mibusfun = new Buses(cantidadVehiculos);
        mibusfun.inicializarVentana();
        mibusfun.mostrarVentanaEdicion();
    }

}
