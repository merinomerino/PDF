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
                save();//Llamada al metodo guardar
            }
        });
        open = new JMenuItem("Abrir");//Se agrega un botton tipo JMenu con en el nombre abrir al panel interno
        open.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                open();//Llamada al metodo abrir
            }
        });
        saveAs = new JMenuItem("Guardar como");//Se agrega un botton tipo JMenu con en el nombre guardar como al panel interno
        saveAs.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                rutaT = ruta;
                ruta = null;
                save();//Llamada al metodo guardar 
            }
        });
        archivo.add(open);//Se agregan a la variable archivo "abrir"
        archivo.add(save);//Se agregan a la variable archivo "guardar"
        archivo.add(saveAs);//Se agregan a la variable archivo  "guardar como"
        menu.add(archivo);//Todo lo que hay en la variable archivo se agrega al menu
        add(menu, BorderLayout.NORTH);
    }

    public void nuevo() {//Metodo para crear un nuevo pdf
        editor = new JTextArea();//Se crea un JTextArea para poder escribir
        scroll = new JScrollPane(editor);//Se crea el JScrollPane para habilitar el editor
        panel.add(scroll);//Se agrega el JScrollPane al panel
    }

    public String open() {//Metodo para abrir un pdf
        String nombre = "";
        elegir = new JFileChooser();//Se crea el JFileChooser para poder hacer la seleccion de archivo a abrir
        if (elegir.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {// Si apretamos en aceptar ocurrirá esto
            try {
                ruta = elegir.getSelectedFile();//Se guarda la ruta del archivo en la variable ruta despues de haber sido seleccionado
                String texto = getContenido();//el contenido del pdf se guarda en texto
                editor.setText(texto);//Editar el texto que contiene el pdf
                nombre = elegir.getSelectedFile().getName();//Extrae el nombre del pdf
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "alert", "alert", JOptionPane.ERROR_MESSAGE);
            }
        }
        return nombre;//Retorna el nombre del pdf
    }

    public void save() {//Metodo para guardar el pdf
        try {
            elegir = new JFileChooser();//Se crea el JFileChooser para poder hacer la seleccion de archivo a abrir
            if (ruta == null) {
                if (elegir.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {// Si apretamos en aceptar ocurrirá esto
                    elegir.setDialogTitle("Elegir el archivo");
                    ruta = elegir.getSelectedFile();//Seleccina la ruta
                    Etiquetas guardar = new Etiquetas(String.valueOf(editor.getText()),ruta);//Se guardan la etiquetas y los cambios a ellas
                    guardar.etiquetas();//guarda etiquetas
                    saveAs.setEnabled(true);
                    JOptionPane.showMessageDialog(null, "Documento Guardado");//Aparece cuando se haya guardado
                } else {//Si se guardo en la misma ruta
                    ruta = rutaT;
                }
            } else if (ruta != null) {
                Etiquetas guardar = new Etiquetas(String.valueOf(editor.getText()),ruta);
                guardar.etiquetas();
                JOptionPane.showMessageDialog(null, "Documento Guardado");
            }
        } catch (Exception e) {
        }
    }

    public String getContenido() {//Metodo para obtener el contenido del pdf
        String contenido = "";
        FileReader fr;//Lee los caracteres
        BufferedReader br = null;//Lee el texto de una secuencia de entrada de caracteres
        try {
            fr = new FileReader(ruta);//Lee los caracteres la ruta
            br = new BufferedReader(fr);//Lee el texto del archivo una vez que se obtubo la ruta
            String linea;
            while ((linea = br.readLine()) != null) {//Empieza a obtener el texto linea por linea del pdf
                contenido += linea + "\n";
            }
        } catch (Exception e) {
        } finally {
            try {
                br.close();
            } catch (Exception ex) {
            }
        }
        return contenido;//Retorna el contenido del pdf
    }

}
