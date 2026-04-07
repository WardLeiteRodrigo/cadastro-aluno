package ui;

import javax.swing.JOptionPane;

/**
 * Implementação da interface de Menu para ambiente gráfico.
 * Utiliza o Componente JOptionPane do balcão "Swing" para renderizar as opções em janela.
 * 
 * @author Kaua Bezerra, Liam Vedovato, Raul Kolaric, Rodrigo Ward 
 * @version 24/03/2026
 */
public class MenuGrafico implements IMenu {
    /**
     * Interage com o usuário com caixas de diálogo informando as opções do menu.
     * Valida automaticamente entradas literais inválidas protegendo contra NumberFormatException.
     * @param opcoes Array contendo as strings disponíveis de navegação do Software.
     * @return Opção Inteiramente validada para ser ingerida pelo switch interno. 
     */
    public int criarMenu(String opcoes[]) {
        int opcao;
        
        String itens = "";
        for (int i = 0; i < opcoes.length; i++){
            itens = itens + "\n" + opcoes[i];
        }
        
        itens = itens + "\n\nSelecione a opcao: ";
        while (true) {
            String input = JOptionPane.showInputDialog(null, itens, "Menu de Opções", JOptionPane.QUESTION_MESSAGE);
            if (input == null) return 5; // Se cancelar, sai do sistema (opção 5 agora)
            try {
                opcao = Integer.parseInt(input);
                return opcao;
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Por favor, insira um número correspondente a uma opção válida.", "Opção Inválida", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
