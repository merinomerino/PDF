/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Etiquetas;


import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Font;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.*;
import javax.swing.JOptionPane;

public class Etiquetas {

    private final String texto;
    private final File file;
    private Document doc;

    public Etiquetas(String t, File f) {
        texto = t;
        file = f;
    }

    public void etiquetas() {
        Font font = new Font(); //metodo para poder trabajar con el texto 
        char leerEtiqueta;
        boolean etiq = false;   
        boolean dir = false;
        String sobra = "";
        String x = "";
        try {
            FileOutputStream archivo = new FileOutputStream(file.toString()
                    + ".pdf");
            
            doc = new Document(PageSize.LETTER);
            PdfWriter.getInstance(doc, archivo);
            doc.open();
            
            for (int i = 0; i < texto.length(); i++) {
                leerEtiqueta = texto.charAt(i);

                //{T} Esta etiqueta nos sirve para producir un Título
                
                if (leerEtiqueta == '{' && texto.charAt(i + 1) == 'T'
                        && texto.charAt(i + 2) == '}') {
                    i = i + 3;
                    leerEtiqueta = texto.charAt(i);
                    etiq = true;  //inicio de la etiqueta
                
                } else if (leerEtiqueta == '{' && texto.charAt(i + 1) == '#'
                        && texto.charAt(i + 2) == 'T' && texto.charAt(i + 3) == '}') {
                    leerEtiqueta = ' ';
                    i = i + 3;
                    x += leerEtiqueta;
                    
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
                
                //{C} Esta etiqueta nos sirve para producir un título que indique el inicio de un capítulo

                if (leerEtiqueta == '{' && texto.charAt(i + 1) == 'C'
                        && texto.charAt(i + 2) == '}') {
                    i = i + 3;
                    leerEtiqueta = texto.charAt(i);
                    etiq = true;
                } else if (leerEtiqueta == '{' && texto.charAt(i + 1) == '#'
                        && texto.charAt(i + 2) == 'C' && texto.charAt(i + 3) == '}') {
                    leerEtiqueta = ' ';
                    i = i + 3;
                    x += leerEtiqueta;
                    
                    //formato del subtitulo
                    
                    font.setSize(15);
                    font.setFamily(FontFamily.TIMES_ROMAN.toString());
                    font.setStyle(Font.BOLD);
                    Paragraph cap = new Paragraph("\n");
                    cap.setFont(new Font(font));
                    cap.add(x);
                    cap.setAlignment(Paragraph.ALIGN_LEFT);
                    doc.add(cap);
                    x = "";
                    etiq = false;
                
                }//P} cierre de la etiqueta subtitulo o capitulo
                
                //{S} Esta etiqueta nos sirve para producir un título que indique el inicio de una sección 
                
                if (leerEtiqueta == '{' && texto.charAt(i + 1) == 'S'
                        && texto.charAt(i + 2) == '}') {
                    i = i + 3;
                    leerEtiqueta = texto.charAt(i);
                    etiq = true;
                } else if (leerEtiqueta == '{' && texto.charAt(i + 1) == '#'
                        && texto.charAt(i + 2) == 'S' && texto.charAt(i + 3) == '}') {
                    leerEtiqueta = ' ';
                    i = i + 3;
                    x += leerEtiqueta;
                    
                    // titulo seccion 
                    
                    font.setSize(14);
                    font.setFamily(FontFamily.TIMES_ROMAN.toString());
                    Paragraph cap = new Paragraph(x, font);
                    cap.setAlignment(Paragraph.ALIGN_LEFT);
                    doc.add(cap);
                    x = "";
                    etiq = false;
                    
                }//cierre de la etiqueta {S}
                
                // {P} Esta etiqueta nos sirve para producir un Párrafo
                else if (leerEtiqueta == '{' && texto.charAt(i + 1) == 'P'
                        && texto.charAt(i + 2) == '}') {
                    i = i + 3;
                    leerEtiqueta = texto.charAt(i);
                    etiq = true;
                } else if (leerEtiqueta == '{' && texto.charAt(i + 1) == '#'
                        && texto.charAt(i + 2) == 'P' && texto.charAt(i + 3) == '}') {
                    leerEtiqueta = ' ';
                    i = i + 3;
                    x += leerEtiqueta;
                   //formato del parrafo
                   
                    Paragraph p = new Paragraph();
                    p.setAlignment(Paragraph.ALIGN_LEFT);
                    x = etiquetasForma(x, p);
                    p.setFont(new Font());
                    p.add(x);
                    doc.add(p);
                    x = "";
                    etiq = false;
                    
                    //	{I} Esta etiqueta nos sirve para agregar una imagen al documento
                } else if (leerEtiqueta == '{' && texto.charAt(i + 1) == 'I'
                        && texto.charAt(i + 2) == '}') {
                    i = i + 3;
                    leerEtiqueta = texto.charAt(i);
                    dir = true;
                    
                } else if (leerEtiqueta == '{' && texto.charAt(i + 1) == '#'
                        && texto.charAt(i + 2) == 'I' && texto.charAt(i + 3) == '}') {
                    i = i + 3;
                    
                    Paragraph image = new Paragraph();
                    Image im = Image.getInstance(x);
                    im.setAlignment(Image.ALIGN_CENTER);
                    if (im.getScaledHeight() >= 1000 && im.getScaledWidth() >= 1000) {
                        im.scaleAbsolute(im.getScaledWidth() / 8, im.getScaledHeight() / 8);
                    }
                    
                    image.add(im);
                    doc.add(image);
                    x = "";
                    dir = false;
                }
                if (etiq) {
                    x += leerEtiqueta;
                } else {
                    sobra += leerEtiqueta;
                }
                if (dir && leerEtiqueta != ' ') {
                    x += leerEtiqueta;
                }
                
            }//llave del for
            
            doc.close();
        } catch (Exception e) {
        
        }//fin de la excepcion
   
    }
    
}//fin de la clase Etiquetas
