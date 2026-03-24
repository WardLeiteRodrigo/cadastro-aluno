import java.util.Scanner;

public class MenuTexto implements IMenu {
    public int criarMenu(String opcoes[]) {
        int opcao;
        
        String itens = "";
        for (int i = 0; i < opcoes.length; i++){
            itens = itens + "\n" + opcoes[i];
            
        }
        
        itens = itens + "\n\nSelecione a opcao: ";

        Scanner sc = new Scanner(System.in);

        System.out.print(itens);

        String s = sc.nextLine();

        sc.close();

        opcao = Integer.parseInt(s);
        return opcao;
    }
}