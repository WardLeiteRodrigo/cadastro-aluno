/**
 * @author Kaua Bezerra, Liam Vedovato, Raul Kolaric, Rodrigo Ward 
 * @version 19/03/2026
 */
import javax.swing.JOptionPane;

public class App {
    public static void main(String args[]) {
        int qtde = Integer.parseInt(JOptionPane.showInputDialog("Quantidade de alunos: "));

        CadastroAlunos ca = new CadastroAlunos(qtde);
        
        // menu (em loop)
        // switch case
        
    }
}