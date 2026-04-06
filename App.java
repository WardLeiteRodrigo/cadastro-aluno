/**
 * @author Kaua Bezerra, Liam Vedovato, Raul Kolaric, Rodrigo Ward 
 * @version 24/03/2026
 */
import javax.swing.JOptionPane;

public class App {
    public static void main(String args[]) {
        int qtde = 0;
        boolean entradaValida = false;
        
        do {
            try {
                String input = JOptionPane.showInputDialog("Forneça a quantidade de alunos: ");
                if (input == null) return; // Encerra o programa se cancelar
                qtde = Integer.parseInt(input);
                if (qtde > 0) {
                    entradaValida = true;
                } else {
                    JOptionPane.showMessageDialog(null, "A quantidade deve ser maior que zero.");
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Entrada invalida. Por favor, digite um numero valido.");
            }
        } while (!entradaValida);

        CadastroAlunos ca = new CadastroAlunos(qtde);

        IMenu mn = null; 
        int menu = 0;

        String [] itensMenu = {"1 - inserir", "2 - remover", "3 - listar", "4 - sair (antigo)", "5 - atualizar", "6 - sair"};

        int opcao = 0;
        
        do {
            entradaValida = false;
            do {
                try {
                    String input = JOptionPane.showInputDialog("Qual o tipo de menu desejado? (Gráfico = 1 ou Texto = 2): ");
                    if (input == null) return; // Encerra o programa se cancelar
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
            
            if (menu == 1) {
                mn = new MenuGrafico();
            } else {
                mn = new MenuTexto(); 
            }
            
            opcao = mn.criarMenu(itensMenu);
            
            switch(opcao) {
                case 1: 
                    String nome; int idade = 0;
                    String ra; String curso; int semestre = 0;
                    
                    nome = JOptionPane.showInputDialog("Nome: ");
                    if (nome == null) break; // Retorna ao menu
                    
                    entradaValida = false;
                    do {
                        try {
                            String input = JOptionPane.showInputDialog("Idade: ");
                            if (input == null) break;
                            idade = Integer.parseInt(input);
                            if (idade >= 16 && idade <= 120) {
                                entradaValida = true;
                            } else {
                                JOptionPane.showMessageDialog(null, "Idade invalida. Digite uma idade entre 16 e 120.");
                            }
                        } catch (NumberFormatException e) {
                            JOptionPane.showMessageDialog(null, "Entrada invalida. Por favor, digite um numero valido.");
                        }
                    } while (!entradaValida);
                    if (!entradaValida) break; // Aborta a insercao se cancelou

                    ra = JOptionPane.showInputDialog("RA: ");
                    if (ra == null) break;
                    
                    curso = JOptionPane.showInputDialog("Curso: ");
                    if (curso == null) break;
                    
                    entradaValida = false;
                    do {
                        try {
                            String input = JOptionPane.showInputDialog("Semestre: ");
                            if (input == null) break;
                            semestre = Integer.parseInt(input);
                            entradaValida = true;
                        } catch (NumberFormatException e) {
                            JOptionPane.showMessageDialog(null, "Entrada invalida. Por favor, digite um numero valido.");
                        }
                    } while (!entradaValida);
                    if (!entradaValida) break;

                    Aluno a = new Aluno(nome, idade, ra, curso, semestre);
                    boolean inseriu = ca.inserir(a);
                    
                    if (inseriu) {
                        JOptionPane.showMessageDialog(null, "Aluno inserido com sucesso!");
                    } else {
                        JOptionPane.showMessageDialog(null, "Erro ao inserir: RA ja cadastrado ou limite de alunos atingido.");
                    }
                    
                    break;
                case 2:
                    String ra_remover = JOptionPane.showInputDialog("RA a ser removido: ");
                    if (ra_remover != null) {
                        boolean removeu = ca.remover(ra_remover);
                        if (removeu) {
                            JOptionPane.showMessageDialog(null, "Aluno removido com sucesso!");
                        } else {
                            JOptionPane.showMessageDialog(null, "Erro: RA nao encontrado.");
                        }
                    }
                    break;
                case 3: 
                    int tipoListagem = JOptionPane.showConfirmDialog(null, "Deseja listar no formato bibliografico?", "Formato de Listagem", JOptionPane.YES_NO_OPTION);
                    boolean formatoBibliografico = (tipoListagem == JOptionPane.YES_OPTION);
                    ca.listar(formatoBibliografico);
                    break; 
                case 4:
                    // Mantido vazio apenas para não quebrar caso alguem digite 4 por engano
                    break;
                case 5:
                    String ra_atualizar = JOptionPane.showInputDialog("RA do aluno a ser atualizado: ");
                    if (ra_atualizar == null) break;
                    
                    String novoNome; int novaIdade = 0;
                    String novoCurso; int novoSemestre = 0;
                    
                    novoNome = JOptionPane.showInputDialog("Novo Nome: ");
                    if (novoNome == null) break; 
                    
                    entradaValida = false;
                    do {
                        try {
                            String input = JOptionPane.showInputDialog("Nova Idade: ");
                            if (input == null) break;
                            novaIdade = Integer.parseInt(input);
                            if (novaIdade >= 16 && novaIdade <= 120) {
                                entradaValida = true;
                            } else {
                                JOptionPane.showMessageDialog(null, "Idade invalida. Digite uma idade entre 16 e 120.");
                            }
                        } catch (NumberFormatException e) {
                            JOptionPane.showMessageDialog(null, "Entrada invalida. Por favor, digite um numero valido.");
                        }
                    } while (!entradaValida);
                    if (!entradaValida) break;

                    novoCurso = JOptionPane.showInputDialog("Novo Curso: ");
                    if (novoCurso == null) break;
                    
                    entradaValida = false;
                    do {
                        try {
                            String input = JOptionPane.showInputDialog("Novo Semestre: ");
                            if (input == null) break;
                            novoSemestre = Integer.parseInt(input);
                            entradaValida = true;
                        } catch (NumberFormatException e) {
                            JOptionPane.showMessageDialog(null, "Entrada invalida. Por favor, digite um numero valido.");
                        }
                    } while (!entradaValida);
                    if (!entradaValida) break;

                    Aluno novoAluno = new Aluno(novoNome, novaIdade, ra_atualizar, novoCurso, novoSemestre);
                    boolean atualizou = ca.atualizar(ra_atualizar, novoAluno);
                    
                    if (atualizou) {
                        JOptionPane.showMessageDialog(null, "Aluno atualizado com sucesso!");
                    } else {
                        JOptionPane.showMessageDialog(null, "Erro: RA nao encontrado para atualizacao.");
                    }
                    
                    break;
                case 6: break;
                default:
            }
        } while(opcao != 6);
    }
}