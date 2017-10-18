/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Etiquetas;

import com.itextpdf.text.BaseColor;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import javax.swing.text.Document;
import com.itextpdf.text.Font;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;



public class Etiquetas {

    private final String texto;
    private final File file;
    private Document doc;

    public Etiquetas(String t, File f) {
        texto = t;
        file = f;
    }

    public void etiquetas() throws FileNotFoundException {
        Font font = new Font();
        char c;
        boolean etiq = false;
        boolean dir = false;
        String sobra = "";
        String x = "";
        try {
            FileOutputStream archivo = new FileOutputStream(file.toString()
                    + ".pdf");
            doc = new Document(PageSize.LETTER) {
            };
            PdfWriter.getInstance(doc, archivo);
            doc.open();
            for (int i = 0; i < texto.length(); i++) {
                c = texto.charAt(i);

                //{T} Esta etiqueta nos sirve para producir un Título
                if (c == '{' && texto.charAt(i + 1) == 'T'
                        && texto.charAt(i + 2) == '}') {
                    i = i + 3;
                    c = texto.charAt(i);
                    etiq = true;  //inicio de la etiqueta

                } else if (c == '{' && texto.charAt(i + 1) == '#'
                        && texto.charAt(i + 2) == 'T' && texto.charAt(i + 3) == '}') {
                    c = ' ';
                    i = i + 3;
                    x += c;

                    //formato del titulo
                    font.setSize(30);
                    font.setFamily(FontFamily.TIMES_ROMAN.toString());
                    font.setStyle(Font.BOLD);
                    font.setColor(BaseColor.BLACK);
                    Paragraph title = new Paragraph(x, font);
                    title.setAlignment(Paragraph.ALIGN_CENTER);
                    doc.add(title);
                    x = "";
                    etiq = false; //fin de la etiqueta

                } //cierre de la etiqueta titulo
                
                
                doc.close();
            }
        } catch (Exception e) {}
        
    
}
    
