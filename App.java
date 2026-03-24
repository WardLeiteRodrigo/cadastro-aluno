/**
 * @author Kaua Bezerra, Liam Vedovato, Raul Kolaric, Rodrigo Ward 
 * @version 24/03/2026
 */
import javax.swing.JOptionPane;

public class App {
    public static void main(String args[]) {
        int qtde = Integer.parseInt(JOptionPane.showInputDialog("Forneça a quantidade de alunos: "));

        CadastroAlunos ca = new CadastroAlunos(qtde);

        IMenu mn; 
        int menu;

        String [] itensMenu = {"1 - inserir", "2 - remover", "3 - listar", "4 - sair"};

        int opcao = 0;
        
        do {
            menu = Integer.parseInt(JOptionPane.showInputDialog("Qual o tipo de menu desejado? (Gráfico = 1 ou Texto = 2): "));
            
            if (menu != 1 && menu != 2) {
                System.out.println("Valor inválido");
                return;
            }
            
            if (menu == 1) {
                mn = new MenuGrafico();
            } else {
                mn = new MenuTexto(); 
            }
            
            opcao = mn.criarMenu(itensMenu);
            
            switch(opcao) {
                case 1: 
                    String nome; int idade;
                    String ra; String curso; int semestre;
                    
                    nome = JOptionPane.showInputDialog("Nome: ");
                    idade = Integer.parseInt(JOptionPane.showInputDialog("Idade: "));
                    ra = JOptionPane.showInputDialog("RA: ");
                    curso = JOptionPane.showInputDialog("Curso: ");
                    semestre = Integer.parseInt(JOptionPane.showInputDialog("Semestre: "));
                    
                    Aluno a = new Aluno(nome, idade, ra, curso, semestre);
                    ca.inserir(a);
                    
                    break;
                case 2:
                    String ra_remover = JOptionPane.showInputDialog("RA a ser removido: ");
                    ca.remover(ra_remover);
                    
                    break;
                case 3: 
                    ca.listar();
                    break; 
                case 4: break;
                default:
            }
        } while(opcao != 4);
    }
}