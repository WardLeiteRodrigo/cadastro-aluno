import javax.swing.JOptionPane;
//import java.util.Scanner;

/**
 * Testa a classe Texto.
 * 
 * @author Julio Arakaki 
 * @version 1.0 (19/03/2025)
 */
public class TestaTexto {
    public static void main(String[] args) {
        // cria objeto para entrada pelo teclado (standard input) 
        //Scanner sc = new Scanner(System.in); 
  
        //System.out.println("Forneca um texto: ");
        //String texto = sc.nextLine(); 
         
        // Entrada de um nome (atraves do teclado)
        String texto = JOptionPane.showInputDialog("Forneça um texto: ");

        // Criacao de uma instancia da classe nome (criacao do objeto)
        Texto txt = new Texto(texto); // Chama o metodo construtor com o texto fornecido

        // Mostra os dados
        System.out.println("Texto              : " + txt.getTxt() + " (" + txt.getQtdePalavras()+ " palavras)");
        System.out.println("Texto invertido    : " + txt.inverterTexto());
    }
}
