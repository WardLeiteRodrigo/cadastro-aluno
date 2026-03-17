/**
 * @author Kaua Bezerra, Liam Vedovato, Raul Kolaric, Rodrigo Ward 
 * @version 17/03/2026
 */

import javax.swing.JOptionPane;

public class TestaTexto {
    public static void main(String[] args) {
        String texto = JOptionPane.showInputDialog("Forneça um texto: ");

        Texto txt = new Texto(texto);

        System.out.println("Texto              : " + txt.getTxt() + " (" + txt.getQtdePalavras()+ " palavras)");
        System.out.println("Texto invertido    : " + txt.inverterTexto());
    }
}