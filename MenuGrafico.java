/**
 * @author Kaua Bezerra, Liam Vedovato, Raul Kolaric, Rodrigo Ward 
 * @version 24/03/2026
 */
import javax.swing.JOptionPane;

public class MenuGrafico implements IMenu {
    public int criarMenu(String opcoes[]) {
        int opcao;
        
        String itens = "";
        for (int i = 0; i < opcoes.length; i++){
            itens = itens + "\n" + opcoes[i];
        }
        
        itens = itens + "\n\nSelecione a opcao: ";
        opcao = Integer.parseInt(JOptionPane.showInputDialog(itens));
        return opcao;
    }
}