package main;

import javax.swing.JOptionPane;

import model.Aluno;
import model.IdadeInvalidaException;
import storage.RaDuplicadoException;
import storage.RaInexistenteException;
import storage.CadastroCheioException;
import ui.IIO;
import ui.IOGrafico;
import ui.IOTexto;
import ui.IMenu;
import ui.MenuGrafico;
import ui.MenuTexto;

/**
 * Classe principal do sistema de cadastro de alunos. Pergunta o modo
 * de interacao desejado (grafico ou texto), instancia a interface de
 * I/O correspondente ({@link IIO}) e oferece um menu com as operacoes
 * de inserir, remover, listar, atualizar e sair.
 *
 * Toda operacao e protegida por try/catch para garantir que o usuario
 * nunca veja stack traces ou mensagens tecnicas, atendendo aos
 * requisitos nao funcionais do enunciado.
 *
 * @author Kaua Bezerra, Liam Vedovato, Raul Kolaric, Rodrigo Ward
 * @version 1.0 2026/04/07
 */
public class App {

    /**
     * Pergunta inicial (sempre grafica) para escolher o modo de interacao.
     *
     * @return 1 para grafico, 2 para texto, ou -1 para cancelar.
     */
    private static int escolherModo() {
        while (true) {
            try {
                String input = JOptionPane.showInputDialog(
                    "Qual o tipo de menu desejado? (Grafico = 1 ou Texto = 2): ");
                if (input == null) return -1;
                int v = Integer.parseInt(input.trim());
                if (v == 1 || v == 2) return v;
                JOptionPane.showMessageDialog(null,
                    "Valor invalido. Digite 1 para Grafico ou 2 para Texto.");
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null,
                    "Entrada invalida. Por favor, digite um numero valido.");
            }
        }
    }

    /**
     * Pre-popula o cadastro com alunos ficticios para facilitar testes.
     * Falhas sao ignoradas silenciosamente (cadastro pequeno demais, etc.).
     *
     * @param ca Cadastro a ser populado.
     * @param io Interface de saida para reportar avisos.
     */
    private static void prepopular(CadastroAlunos ca, IIO io) {
        try {
            ca.inserir(new Aluno("Alan Mathison Turing", 41, "RA1001", "Ciencia da Computacao", 5));
            ca.inserir(new Aluno("Ada Augusta Lovelace", 36, "RA1002", "Matematica", 8));
            ca.inserir(new Aluno("Grace Murray Hopper", 85, "RA1003", "Sistemas de Informacao", 6));
        } catch (CadastroCheioException e) {
            io.mostrar("Aviso: cadastro pequeno demais para os alunos de exemplo.");
        } catch (RaDuplicadoException e) {
            // ignorado: pode ocorrer se chamado mais de uma vez
        } catch (IdadeInvalidaException e) {
            // dados internos validos; nao deve ocorrer
        }
    }

    /**
     * Operacao de inserir aluno: le os dados do usuario, valida e
     * trata todas as excecoes possiveis.
     *
     * @param ca Cadastro alvo.
     * @param io Interface de I/O.
     */
    private static void operacaoInserir(CadastroAlunos ca, IIO io) {
        String nome = io.lerTexto("Nome:");
        if (nome == null) return;

        Integer idade = io.lerInteiro("Idade:", Aluno.IDADE_MIN, Aluno.IDADE_MAX);
        if (idade == null) return;

        String ra = io.lerTexto("RA:");
        if (ra == null) return;

        String curso = io.lerTexto("Curso:");
        if (curso == null) return;

        Integer semestre = io.lerInteiro("Semestre:", 1, 20);
        if (semestre == null) return;

        try {
            Aluno a = new Aluno(nome, idade, ra, curso, semestre);
            ca.inserir(a);
            io.mostrar("Aluno inserido com sucesso!");
        } catch (RaDuplicadoException e) {
            io.mostrar("Erro: ja existe um aluno cadastrado com este RA.");
        } catch (CadastroCheioException e) {
            io.mostrar("Erro: o cadastro atingiu sua capacidade maxima.");
        } catch (IdadeInvalidaException e) {
            io.mostrar("Erro: " + e.getMessage());
        } catch (Exception e) {
            io.mostrar("Erro inesperado ao inserir o aluno. Tente novamente.");
        }
    }

    /**
     * Operacao de remover aluno: pede o RA e trata as excecoes.
     *
     * @param ca Cadastro alvo.
     * @param io Interface de I/O.
     */
    private static void operacaoRemover(CadastroAlunos ca, IIO io) {
        String ra = io.lerTexto("RA a ser removido:");
        if (ra == null) return;

        try {
            ca.remover(ra);
            io.mostrar("Aluno removido com sucesso!");
        } catch (RaInexistenteException e) {
            io.mostrar("Erro: RA nao encontrado.\n\nAlunos disponiveis:\n" + ca.listar(false));
        } catch (Exception e) {
            io.mostrar("Erro inesperado ao remover o aluno. Tente novamente.");
        }
    }

    /**
     * Operacao de listar alunos. Pergunta se deve usar formato bibliografico.
     *
     * @param ca Cadastro alvo.
     * @param io Interface de I/O.
     */
    private static void operacaoListar(CadastroAlunos ca, IIO io) {
        try {
            boolean biblio = io.lerConfirmacao("Deseja listar no formato bibliografico?");
            io.mostrar(ca.listar(biblio));
        } catch (Exception e) {
            io.mostrar("Erro inesperado ao listar os alunos.");
        }
    }

    /**
     * Operacao de atualizar aluno: localiza pelo RA e substitui os
     * demais campos. Trata todas as excecoes de dominio.
     *
     * @param ca Cadastro alvo.
     * @param io Interface de I/O.
     */
    private static void operacaoAtualizar(CadastroAlunos ca, IIO io) {
        String ra = io.lerTexto("RA do aluno a ser atualizado:");
        if (ra == null) return;

        if (!ca.existe(ra)) {
            io.mostrar("Erro: RA nao encontrado.\n\nAlunos disponiveis:\n" + ca.listar(false));
            return;
        }

        String nome = io.lerTexto("Novo nome:");
        if (nome == null) return;

        Integer idade = io.lerInteiro("Nova idade:", Aluno.IDADE_MIN, Aluno.IDADE_MAX);
        if (idade == null) return;

        String curso = io.lerTexto("Novo curso:");
        if (curso == null) return;

        Integer semestre = io.lerInteiro("Novo semestre:", 1, 20);
        if (semestre == null) return;

        try {
            Aluno novo = new Aluno(nome, idade, ra, curso, semestre);
            ca.atualizar(ra, novo);
            io.mostrar("Aluno atualizado com sucesso!");
        } catch (RaInexistenteException e) {
            io.mostrar("Erro: RA nao encontrado para atualizacao.");
        } catch (IdadeInvalidaException e) {
            io.mostrar("Erro: " + e.getMessage());
        } catch (Exception e) {
            io.mostrar("Erro inesperado ao atualizar o aluno. Tente novamente.");
        }
    }

    /**
     * Ponto de entrada do programa. Configura modo de I/O, capacidade
     * do cadastro, pre-popula dados de exemplo e roda o loop do menu.
     *
     * @param args Argumentos de linha de comando (nao utilizados).
     */
    public static void main(String[] args) {
        // 1) Escolha do modo de interacao (sempre via JOptionPane, pois
        //    ainda nao sabemos qual IO o usuario quer)
        int modo = escolherModo();
        if (modo == -1) return;

        // 2) Instancia da interface de I/O conforme o modo escolhido
        IIO io = (modo == 1) ? new IOGrafico() : new IOTexto();

        // 3) Capacidade do cadastro
        Integer qtde = io.lerInteiro("Forneca a quantidade de alunos:", 1, 1000);
        if (qtde == null) {
            io.fechar();
            return;
        }

        CadastroAlunos ca = new CadastroAlunos(qtde);
        prepopular(ca, io);

        // 4) Menu correspondente ao modo (recebe a interface de I/O por injecao)
        IMenu mn = (modo == 1) ? new MenuGrafico(io) : new MenuTexto(io);

        String[] itensMenu = {
            "1 - inserir",
            "2 - remover",
            "3 - listar",
            "4 - atualizar",
            "5 - sair"
        };

        int opcao = 0;
        do {
            try {
                opcao = mn.criarMenu(itensMenu);
                switch (opcao) {
                    case 1: operacaoInserir(ca, io);   break;
                    case 2: operacaoRemover(ca, io);   break;
                    case 3: operacaoListar(ca, io);    break;
                    case 4: operacaoAtualizar(ca, io); break;
                    case 5: break;
                    default:
                        io.mostrar("Opcao invalida. Escolha entre 1 e 5.");
                }
            } catch (Exception e) {
                // Rede de seguranca: jamais expor stack trace ao usuario
                io.mostrar("Ocorreu um erro inesperado. A operacao foi abortada.");
            }
        } while (opcao != 5);

        io.fechar();
    }
}
