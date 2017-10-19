package GUI;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

//inicio de clase frame
public class Marco extends JFrame{
   private final JDesktopPane contenedor; //Contenedor del frame interno
    private Panel[] panel = new Panel[50];//Creacion del arreglo        
    private int x = 0;                    //Variable que manejara el arreglo
    private int pos;
    private boolean v = false;     
    private final JMenuBar menu;          //Barra de la ventana
    private final JMenu archivo;          //etiqueta
    private final JMenuItem nuevo;        //item menu
    private JInternalFrame internal;      //Frame Interno

    
    public Marco() {
        super("Lector de PDF");
        contenedor = new JDesktopPane();
        add(contenedor);
        nuevo = new JMenuItem("Nuevo");//Menu para crear una ventana nueva
        nuevo.setForeground(Color.blue);
        //Inicio clase accionListener
       nuevo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (pos > 0 || v == true) {
                    internal = new JInternalFrame("Nuevo ", true, true, true,
                            true);
                    panel[pos] = new Panel();
                    panel[pos].nuevo();
                    internal.setName(String.valueOf(pos));
                    añadirFrameInterno(panel[pos]);
                    pos = 0;
                    v = false;
                } else if (panel[x] == null) {
                    internal = new JInternalFrame("Nuevo ", true, true, true,
                            true);
                    panel[x] = new Panel();
                    panel[x].nuevo();
                    internal.setName(String.valueOf(x));
                    añadirFrameInterno(panel[x]);
                }
                x++;//contador
            }
        });
       //Termino de metodo accion
        
        archivo = new JMenu("Archivo");//Nombre de etiqueta
        archivo.setBackground(Color.DARK_GRAY);
        archivo.setForeground(Color.blue);
        archivo.add(nuevo);
        menu = new JMenuBar();
        menu.add(archivo); //Agregar el archivo a la barra
        setJMenuBar(menu);
    }
   //inicio del frameinterno
    public void añadirFrameInterno(Panel p) {
        internal.add(p);
        internal.setSize(300, 300);
        internal.setClosable(true);
        internal.setMaximizable(true);
        internal.setIconifiable(true);
        internal.setMaximizable(true);
        internal.setVisible(true);
        contenedor.add(internal);
        
    }//Termino  internalframe
}
//Termino de la clase frame
