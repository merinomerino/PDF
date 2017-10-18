package GUI;

import Etiquetas.Etiquetas;
import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import javax.swing.*;

public class Panel extends JPanel {

    private final JPanel panel;
    private JTextArea editor;
    private JScrollPane scroll;
    private final JMenuBar menu;
    private final JMenu archivo;
    private final JMenuItem save;
    private final JMenuItem saveAs;
    private final JMenuItem open;
    private JFileChooser elegir;
    private File ruta;
    private File rutaT;

    public Panel() {

        BorderLayout layout = new BorderLayout(10, 10);//Se crea el borderLayout con cordenadas 10,10
        archivo = new JMenu("Archivo");//Se agrega un botton tipo JMenu con en el nombre archivo al panel interno
        setLayout(layout);
        panel = new JPanel();
        panel.setLayout(new GridLayout(1, 1));
        add(panel, BorderLayout.CENTER);
        menu = new JMenuBar();
        save = new JMenuItem("Guardar");//Se agrega un botton tipo JMenu con en el nombre guardar al panel interno
        save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                save();
            }
        });
        open = new JMenuItem("Abrir");//Se agrega un botton tipo JMenu con en el nombre abrir al panel interno
        open.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                open();
            }
        });
        saveAs = new JMenuItem("Guardar como");//Se agrega un botton tipo JMenu con en el nombre guardar como al panel interno
        saveAs.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                rutaT = ruta;
                ruta = null;
//                save();
            }
        });
        archivo.add(open);//Se agregan a la variable archivo "abrir"
        archivo.add(save);//Se agregan a la variable archivo "guardar"
        archivo.add(saveAs);//Se agregan a la variable archivo  "guardar como"
        menu.add(archivo);//Todo lo que hay en la variable archivo se agrega al menu
        add(menu, BorderLayout.NORTH);
    }

}
