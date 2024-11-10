package estacionclimatica;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
public class main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        JFrame frame = new JFrame("Tabla día por temperaturas");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Solicitar al usuario la cantidad de días mediante una ventana de diálogo
        String valor = JOptionPane.showInputDialog(frame, "Ingrese la Cantidad de Días");
        int lluvias = Integer.parseInt(valor);
        Clima lluvioso = new Clima(lluvias);
        
        lluvioso.inicializarVentana();
        lluvioso.mostrarVentanaEdicion();
    }
    
}   