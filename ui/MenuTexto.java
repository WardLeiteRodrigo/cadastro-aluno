package ui;

import main.App;

/**
 * Implementação da interface de Menu para o uso via CLI (Console/Linha de Comando).
 * Pega as opções do sistema e formata-as em texto para serem capturadas pela entrada padrão (System.in).
 * 
 * @author Kaua Bezerra, Liam Vedovato, Raul Kolaric, Rodrigo Ward 
 * @version 24/03/2026
 */
public class MenuTexto implements IMenu {
    /**
     * Reestrutura as opções da aplicação com quebras de linha e recebe o input numérico do usuário.
     * Utiliza o utilitário scanner central instanciado pela própria aplicação base.
     * @param opcoes Array de strings com os títulos do menu.
     * @return O número em formato Integer referente a escolha.
     */
    public int criarMenu(String opcoes[]) {
        int opcao;
        
        String itens = "";
        for (int i = 0; i < opcoes.length; i++){
            itens = itens + "\n" + opcoes[i];
            
        }
        
        itens = itens + "\n\nSelecione a opcao: ";

        System.out.print(itens);

        String s = App.scanner.nextLine();

        opcao = Integer.parseInt(s);
        return opcao;
    }
}
