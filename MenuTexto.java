/**
 * Classe que implementa a interface IMenu fornecendo uma interface de texto via console.
 * 
 * @author Kaua Bezerra, Liam Vedovato, Raul Kolaric, Rodrigo Ward 
 * @version 24/03/2026
 */
import java.util.Scanner;

public class MenuTexto implements IMenu {
    /**
     * Cria um menu de texto lendo a entrada do console.
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

        Scanner sc = new Scanner(System.in);
        boolean entradaValida = false;
        opcao = 0;

        do {
            System.out.print(itens);
            String s = sc.nextLine();
            try {
                opcao = Integer.parseInt(s);
                entradaValida = true;
            } catch (NumberFormatException e) {
                System.out.println("\nEntrada invalida. Por favor, digite um numero valido.\n");
            }
        } while (!entradaValida);

        return opcao;
    }
}