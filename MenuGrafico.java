/**
 * Classe que implementa a interface IMenu fornecendo uma interface grafica.
 * 
 * @author Kaua Bezerra, Liam Vedovato, Raul Kolaric, Rodrigo Ward 
 * @version 24/03/2026
 */
import javax.swing.JOptionPane;

public class MenuGrafico implements IMenu {
    /**
     * Cria um menu grafico utilizando JOptionPane.
     * 
     * @param opcoes Array de strings com as opcoes do menu.
     * @return A opcao numerica escolhida pelo usuario.
     */
    public int criarMenu(String opcoes[]) {
        int opcao;
        
        String itens = "";
        for (int i = 0; i < opcoes.length; i++){
            itens = itens + "\n" + opcoes[i];
        }
        
        itens = itens + "\n\nSelecione a opcao: ";
        boolean entradaValida = false;
        opcao = 0;
        
        do {
            try {
                String input = JOptionPane.showInputDialog(itens);
                if (input == null) {
                    return 6; // Se o usuario cancelar, encerra o loop assumindo 'sair'
                }
                opcao = Integer.parseInt(input);
                entradaValida = true;
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Entrada invalida. Por favor, digite um numero valido.");
            }
        } while (!entradaValida);
        
        return opcao;
    }
}