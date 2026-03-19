/**
 * @author Kaua Bezerra, Liam Vedovato, Raul Kolaric, Rodrigo Ward 
 * @version 19/03/2026
 */

import javax.swing.JOptionPane;

public class TestaNomePessoa {
    public static void main(String[] args) {
        String nomePessoa = JOptionPane.showInputDialog("Forneça um nome: ");

        NomePessoa nome = new NomePessoa(nomePessoa);

        System.out.println("Nome              : " + nome.getNome() + " (" + nome.getQtdePalavras()+ " palavras)");
        System.out.println("Nome invertido    : " + nome.getNomeInvertido());
        System.out.println("Nome bibliografico: " + nome.getNomeBiblio());
    }
}