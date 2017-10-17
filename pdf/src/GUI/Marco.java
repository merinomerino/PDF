
package GUI;

import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;


public class Marco extends JFrame{
   private final JDesktopPane contenedor;
    private Panel[] panel = new Panel[50];//Creacion del arreglo        
    private int x = 0;//Variable que manejara el arreglo
    private int pos;
    private boolean v = false;
    private final JMenuBar menu;
    private final JMenu archivo;
    private final JMenuItem nuevo;
    private JInternalFrame internal;

    
    public Marco() {
        
    }
    public void añadirFrameInterno(Panel p) {
        
    }
}
