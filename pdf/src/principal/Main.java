/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package principal;
import GUI.Marco;
import java.awt.Dimension;
import javax.swing.JFrame;

/**
 *
 * @author lokua
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       Marco gui = new Marco();
        gui.pack();
        gui.setMinimumSize(new Dimension(600, 600));
        gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gui.setVisible(true);
    }
    
}
