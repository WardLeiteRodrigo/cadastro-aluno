package main;

/**
 * Classe principal do aplicativo que inicializa o sistema de cadastro e o menu.
 * 
 * @author Kaua Bezerra, Liam Vedovato, Raul Kolaric, Rodrigo Ward 
 * @version 24/03/2026
 */
 

import model.Aluno;
import ui.IMenu;
import ui.MenuGrafico;
import ui.MenuTexto;

import javax.swing.JOptionPane;
import java.util.Scanner;

public class App {

    private static String lerString(String msg, int tipoMenu, Scanner sc) {
        String input;
        do {
            if (tipoMenu == 1) {
                input = JOptionPane.showInputDialog(msg);
                if (input == null) return null; // Cancela a operacao
            } else {
                System.out.print(msg + " (ou digite 'cancelar' para abortar): ");
                input = sc.nextLine();
                if (input.trim().equalsIgnoreCase("cancelar")) return null; // Cancela a operacao
            }
            
            if (input.trim().isEmpty()) {
                mostrarMensagem("Entrada invalida. O valor nao pode ser vazio.", tipoMenu);
            }
        } while (input.trim().isEmpty());
        
        return input;
    }

    private static void mostrarMensagem(String msg, int tipoMenu) {
        if (tipoMenu == 1) {
            javax.swing.JTextArea textArea = new javax.swing.JTextArea(msg);
            textArea.setEditable(false);
            javax.swing.JScrollPane scrollPane = new javax.swing.JScrollPane(textArea);
            scrollPane.setPreferredSize(new java.awt.Dimension(400, 300));
            JOptionPane.showMessageDialog(null, scrollPane, "Mensagem", JOptionPane.INFORMATION_MESSAGE);
        } else {
            System.out.println(msg);
        }
    }

    private static boolean lerConfirmacao(String msg, int tipoMenu, Scanner sc) {
        if (tipoMenu == 1) {
            int resp = JOptionPane.showConfirmDialog(null, msg, "Confirmacao", JOptionPane.YES_NO_OPTION);
            return resp == JOptionPane.YES_OPTION;
        } else {
            System.out.print(msg + " (S/N): ");
            String resp = sc.nextLine();
            return resp.trim().equalsIgnoreCase("S");
        }
    }

    /**
     * Metodo principal que executa o sistema.
     * 
     * @param args Argumentos de linha de comando.
     */
    public static void main(String args[]) {
        boolean entradaValida = false;
        int menu = 0;
        Scanner sc = new Scanner(System.in);
        
        do {
            try {
                // A primeira pergunta sempre sera grafica, pois ainda nao sabemos o tipo desejado
                String input = JOptionPane.showInputDialog("Qual o tipo de menu desejado? (Gráfico = 1 ou Texto = 2): ");
                if (input == null) {
                    sc.close();
                    return; // Encerra o programa se cancelar
                }
                menu = Integer.parseInt(input);
                if (menu == 1 || menu == 2) {
                    entradaValida = true;
                } else {
                    JOptionPane.showMessageDialog(null, "Valor invalido. Digite 1 para Grafico ou 2 para Texto.");
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Entrada invalida. Por favor, digite um numero valido.");
            }
        } while (!entradaValida);

        int qtde = 0;
        entradaValida = false;
        
        do {
            try {
                String input = lerString("Forneça a quantidade de alunos: ", menu, sc);
                if (input == null) {
                    sc.close();
                    return; // Encerra o programa se cancelar
                }
                qtde = Integer.parseInt(input);
                if (qtde > 0) {
                    entradaValida = true;
                } else {
                    mostrarMensagem("A quantidade deve ser maior que zero.", menu);
                }
            } catch (NumberFormatException e) {
                mostrarMensagem("Entrada invalida. Por favor, digite um numero valido.", menu);
            }
        } while (!entradaValida);

        CadastroAlunos ca = new CadastroAlunos(qtde);

        // Pre-populando alunos na memoria para facilitar os testes (Mock data)
        ca.inserir(new Aluno("Alan Mathison Turing", 41, "RA1001", "Ciencia da Computacao", 5));
        ca.inserir(new Aluno("Ada Augusta Lovelace", 36, "RA1002", "Matematica", 8));
        ca.inserir(new Aluno("Grace Murray Hopper", 85, "RA1003", "Sistemas de Informacao", 6));

        IMenu mn = null; 
        if (menu == 1) {
            mn = new MenuGrafico();
        } else {
            mn = new MenuTexto(); 
        }

        String [] itensMenu = {"1 - inserir", "2 - remover", "3 - listar", "4 - atualizar", "5 - sair"};

        int opcao = 0;
        
        do {
            opcao = mn.criarMenu(itensMenu);
            
            switch(opcao) {
                case 1: 
                    String nome; int idade = 0;
                    String ra; String curso; int semestre = 0;
                    
                    nome = lerString("Nome: ", menu, sc);
                    if (nome == null) break; // Retorna ao menu
                    
                    entradaValida = false;
                    do {
                        try {
                            String input = lerString("Idade: ", menu, sc);
                            if (input == null) break;
                            idade = Integer.parseInt(input);
                            if (idade >= 16 && idade <= 120) {
                                entradaValida = true;
                            } else {
                                mostrarMensagem("Idade invalida. Digite uma idade entre 16 e 120.", menu);
                            }
                        } catch (NumberFormatException e) {
                            mostrarMensagem("Entrada invalida. Por favor, digite um numero valido.", menu);
                        }
                    } while (!entradaValida);
                    if (!entradaValida) break; // Aborta a insercao se cancelou

                    ra = lerString("RA: ", menu, sc);
                    if (ra == null) break;
                    
                    curso = lerString("Curso: ", menu, sc);
                    if (curso == null) break;
                    
                    entradaValida = false;
                    do {
                        try {
                            String input = lerString("Semestre: ", menu, sc);
                            if (input == null) break;
                            semestre = Integer.parseInt(input);
                            entradaValida = true;
                        } catch (NumberFormatException e) {
                            mostrarMensagem("Entrada invalida. Por favor, digite um numero valido.", menu);
                        }
                    } while (!entradaValida);
                    if (!entradaValida) break;

                    Aluno a = new Aluno(nome, idade, ra, curso, semestre);
                    boolean inseriu = ca.inserir(a);
                    
                    if (inseriu) {
                        mostrarMensagem("Aluno inserido com sucesso!", menu);
                    } else {
                        mostrarMensagem("Erro ao inserir: RA ja cadastrado ou limite de alunos atingido.", menu);
                    }
                    
                    break;
                case 2:
                    String ra_remover = lerString("RA a ser removido: ", menu, sc);
                    if (ra_remover != null) {
                        boolean removeu = ca.remover(ra_remover);
                        if (removeu) {
                            mostrarMensagem("Aluno removido com sucesso!", menu);
                        } else {
                            String alunosDisponiveis = ca.listar(false);
                            mostrarMensagem("Erro: RA nao encontrado.\n\nAlunos disponiveis:\n" + alunosDisponiveis, menu);
                        }
                    }
                    break;
                case 3: 
                    boolean formatoBibliografico = lerConfirmacao("Deseja listar no formato bibliografico?", menu, sc);
                    String lista = ca.listar(formatoBibliografico);
                    mostrarMensagem(lista, menu);
                    break; 
                case 4:
                    String ra_atualizar = lerString("RA do aluno a ser atualizado: ", menu, sc);
                    if (ra_atualizar == null) break;
                    
                    String novoNome; int novaIdade = 0;
                    String novoCurso; int novoSemestre = 0;
                    
                    novoNome = lerString("Novo Nome: ", menu, sc);
                    if (novoNome == null) break; 
                    
                    entradaValida = false;
                    do {
                        try {
                            String input = lerString("Nova Idade: ", menu, sc);
                            if (input == null) break;
                            novaIdade = Integer.parseInt(input);
                            if (novaIdade >= 16 && novaIdade <= 120) {
                                entradaValida = true;
                            } else {
                                mostrarMensagem("Idade invalida. Digite uma idade entre 16 e 120.", menu);
                            }
                        } catch (NumberFormatException e) {
                            mostrarMensagem("Entrada invalida. Por favor, digite um numero valido.", menu);
                        }
                    } while (!entradaValida);
                    if (!entradaValida) break;

                    novoCurso = lerString("Novo Curso: ", menu, sc);
                    if (novoCurso == null) break;
                    
                    entradaValida = false;
                    do {
                        try {
                            String input = lerString("Novo Semestre: ", menu, sc);
                            if (input == null) break;
                            novoSemestre = Integer.parseInt(input);
                            entradaValida = true;
                        } catch (NumberFormatException e) {
                            mostrarMensagem("Entrada invalida. Por favor, digite um numero valido.", menu);
                        }
                    } while (!entradaValida);
                    if (!entradaValida) break;

                    Aluno novoAluno = new Aluno(novoNome, novaIdade, ra_atualizar, novoCurso, novoSemestre);
                    boolean atualizou = ca.atualizar(ra_atualizar, novoAluno);
                    
                    if (atualizou) {
                        mostrarMensagem("Aluno atualizado com sucesso!", menu);
                    } else {
                        mostrarMensagem("Erro: RA nao encontrado para atualizacao.", menu);
                    }
                    
                    break;
                case 5: break;
                default:
            }
        } while(opcao != 5);
        
        sc.close();
    }
}
