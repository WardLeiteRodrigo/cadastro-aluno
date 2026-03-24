/**
 * @author Kaua Bezerra, Liam Vedovato, Raul Kolaric, Rodrigo Ward 
 * @version 24/03/2026
 */
import javax.swing.JOptionPane;

public class App {
    public static void main(String args[]) {
        int qtde = Integer.parseInt(JOptionPane.showInputDialog("Forneça a quantidade de alunos: "));

        CadastroAlunos ca = new CadastroAlunos(qtde);

        IMenu mn = new MenuGrafico(); 
        //IMenu mn = new MenuTexto(); 

        String [] itensMenu = {"1 - inserir", "2 - remover", "3 - listar", "4 - sair"};

        int opcao = 0;
        
        do {
            opcao = mn.criarMenu(itensMenu);

        } while(opcao != 4);
    }
}