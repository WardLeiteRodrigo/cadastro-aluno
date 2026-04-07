package main;

import model.Aluno;
import ui.IMenu;
import ui.MenuGrafico;
import ui.MenuTexto;

import javax.swing.JOptionPane;
import java.util.Scanner;

/**
 * A classe principal da aplicação de gestão de alunos e currículos.
 * Permite através de interfaces via Terminal ou UI (Pop-ups) o tratamento e registro (CRUD completo).
 * 
 * @author Kaua Bezerra, Liam Vedovato, Raul Kolaric, Rodrigo Ward 
 * @version 24/03/2026
 */
public class App {
    /**
     * Utilitário scanner unificado exposto para mitigar problemas relacionados com reabertura,
     * falência de buffering e vazamento da entrada original do console.
     */
    public static final Scanner scanner = new Scanner(System.in);

    /**
     * O ponto de ingresso primordial estático na máquina JVM.
     * Orquestra o recebimento inicial e desdobra o menu de ações repetidamente.
     * @param args Argumentos da linha terminal não utilizados por design.
     */
    public static void main(String args[]) {
        int menu = lerMenuInicial();
        if (menu == -1) return;
        
        int qtde = lerInt("Forneça a quantidade máxima de alunos (1 a 1000): ", 1, 1000, menu);
        if (qtde == -1) return;

        CadastroAlunos ca = new CadastroAlunos(qtde);

        IMenu mn = (menu == 1) ? new MenuGrafico() : new MenuTexto();
        int opcao = 0;
        String[] itensMenu = { "1 - inserir", "2 - remover", "3 - listar", "4 - atualizar", "5 - sair" };

        do {
            try {
                opcao = mn.criarMenu(itensMenu);
            } catch (Exception e) {
                exibirMensagem("Opção inválida. Digite o número da opção.", menu);
                opcao = 0;
                continue;
            }

            switch (opcao) {
                case 1:  // Inserção
                    if (ca.isCheio()) {
                        exibirMensagem("O cadastro está cheio!", menu);
                        break;
                    }
                    String ra = lerString("RA: ", menu);
                    if (ra == null) break;
                    
                    if (ca.existeRa(ra)) {
                        exibirMensagem("RA já cadastrado!", menu);
                        break;
                    }

                    Aluno a = lerDadosAluno(ra, menu);
                    if (a != null) {
                        if(ca.inserir(a)) exibirMensagem("Aluno inserido com sucesso!", menu);
                        else exibirMensagem("Erro ao inserir.", menu);
                    }
                    break;
                case 2: // Remoção
                    String ra_remover = lerString("RA a ser removido: ", menu);
                    if (ra_remover == null) break;
                    if (ca.remover(ra_remover)) {
                        exibirMensagem("Aluno removido com sucesso!", menu);
                    } else {
                        exibirMensagem("RA não encontrado.", menu);
                    }
                    break;
                case 3: // Listagem Parametrizada
                    int form = lerInt("Qual o formato de listagem?\n1 - Normal\n2 - Bibliografia", 1, 2, menu);
                    if(form != -1) {
                        String lista = ca.listar(form == 2);
                        exibirMensagem(lista, menu);
                    }
                    break;
                case 4: // Atualização de Contato
                    String ra_atualizar = lerString("RA do aluno a ser atualizado: ", menu);
                    if (ra_atualizar == null) break;
                    
                    if (!ca.existeRa(ra_atualizar)) {
                        exibirMensagem("RA não encontrado.", menu);
                        break;
                    }
                    
                    exibirMensagem("Forneça os novos dados para o dono do RA " + ra_atualizar, menu);
                    Aluno novo = lerDadosAluno(ra_atualizar, menu);
                    if (novo != null) {
                        if(ca.atualizar(ra_atualizar, novo)) exibirMensagem("Dados atualizados com sucesso!", menu);
                        else exibirMensagem("Erro ao atualizar.", menu);
                    }
                    break;
                case 5: // Escapar
                    exibirMensagem("Saindo do sistema...", menu);
                    break;
                default:
                    if(opcao != 5) exibirMensagem("Opção inválida, tente novamente.", menu);
            }
        } while (opcao != 5);
        scanner.close(); // Fecha com segurança a pipeline no desligamento 
    }
    
    /**
     * Intermedie a escolha entre interface Gráfica e Texto num Loop com base no Dialog System default. 
     * @return 1=Gráfico, 2=Texto, -1=Aberto ou Cancela
     */
    private static int lerMenuInicial() {
        while(true) {
            String input = JOptionPane.showInputDialog(null, "Qual o tipo de menu desejado? (Gráfico = 1 ou Texto = 2): ");
            if (input == null) return -1;
            try {
                int val = Integer.parseInt(input);
                if (val == 1 || val == 2) return val;
                JOptionPane.showMessageDialog(null, "Opção inválida. Digite 1 ou 2.", "Erro", JOptionPane.ERROR_MESSAGE);
            } catch(NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Entrada inválida. Digite 1 ou 2.", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    /**
     * Utilitário sequencial para pedir a ficha geral dum estudante sem replicar grandes laços no switch da main.
     * @param ra A matrícula já aceita nas validações anteriores e que interligará esse discente.
     * @param menu Indicador de Interface (1 ou 2)
     * @return O Estudante construído, ou nulo se houve cancelamento num campo.
     */
    private static Aluno lerDadosAluno(String ra, int menu) {
        String nome = lerString("Nome: ", menu);
        if (nome == null) return null;
        int idade = lerInt("Idade (1 a 120): ", 1, 120, menu);
        if (idade == -1) return null;
        String curso = lerString("Curso: ", menu);
        if (curso == null) return null;
        int semestre = lerInt("Semestre (1 a 20): ", 1, 20, menu);
        if (semestre == -1) return null;

        return new Aluno(nome, idade, ra, curso, semestre);
    }

    /**
     * Processa interações requerendo descrições (String) livres de String vazia e/ou interrupção.
     * @param msg A interpelação amigável mostrada na tela.
     * @param menu 1 = SWING Visual / 2 = Terminal Out
     * @return Retorna a String se válida, ou um null em caso voluntário do encerramento ("cancelar").
     */
    private static String lerString(String msg, int menu) {
        while(true) {
            if (menu == 1) {
                String input = JOptionPane.showInputDialog(msg);
                if (input == null) return null;
                if (!input.trim().isEmpty()) return input.trim();
                JOptionPane.showMessageDialog(null, "Entrada vazia. Tente novamente.", "Erro", JOptionPane.ERROR_MESSAGE);
            } else {
                System.out.print("\n" + msg);
                String input = scanner.nextLine();
                if (input.equalsIgnoreCase("cancelar")) return null;
                if (!input.trim().isEmpty()) return input.trim();
                System.out.println("Entrada vazia. Tente novamente.");
            }
        }
    }

    /**
     * Trata o processamento numérico, impossibilitando quedas no sistema pelo NumberFormatException
     * @param msg Instinto amigável exibido requisitando algo numérico.
     * @param min A regra de limitação temporal ou física inferior.
     * @param max A maior possibilidade realista cabível.
     * @param menu 1 ou 2 para designar renderizador.
     * @return O Int consolidado e validado logicamente, -1 se rejeitado externamente.
     */
    private static int lerInt(String msg, int min, int max, int menu) {
        while(true) {
            String str = lerString(msg, menu);
            if (str == null) return -1;
            try {
                int val = Integer.parseInt(str);
                if (val >= min && val <= max) return val;
                exibirMensagem("O valor deve estar entre " + min + " e " + max + ".", menu);
            } catch(NumberFormatException e) {
                exibirMensagem("Número inválido. Tente novamente.", menu);
            }
        }
    }

    /**
     * Encapsula em um único lugar a lógica de printar visualizações, com respeito à tecnologia solicitada.
     * Evita repetição da bifurcação "if menu = 1".
     * @param msg Texto de apresentação principal.
     * @param menu 1 = GUI | 2 = Terminal.
     */
    private static void exibirMensagem(String msg, int menu) {
        if (menu == 1) {
            JOptionPane.showMessageDialog(null, msg);
        } else {
            System.out.println("\n----------------------------------------");
            System.out.println(msg);
            System.out.println("----------------------------------------\n");
        }
    }
}
