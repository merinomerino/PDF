
package GUI;

import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;


public class Marco extends JFrame{
   private final JDesktopPane contenedor; //Contenedor del frame interno
    private Panel[] panel = new Panel[50];//Creacion del arreglo        
    private int x = 0;                    //Variable que manejara el arreglo
    private int pos;
    private boolean v = false;     
    private final JMenuBar menu;
    private final JMenu archivo;
    private final JMenuItem nuevo;
    private JInternalFrame internal;      //Frame Interno

    
    public Marco() {
        contenedor = new JDesktopPane();
        add(contenedor);
        nuevo = new JMenuItem("Nuevo");//Menu para crear una ventana nueva
        
        //Agregar clase accionListener
        
        archivo = new JMenu("Archivo");//Nombre de etiqueta
        archivo.add(nuevo);
        menu = new JMenuBar();
        menu.add(archivo); //Agregar el archivo a la barra
        setJMenuBar(menu);
    }
    public void añadirFrameInterno(Panel p) {
        
    }
}
