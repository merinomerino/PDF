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

    }
}
