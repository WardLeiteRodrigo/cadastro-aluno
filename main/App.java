package main;

import java.io.IOException;

import model.Aluno;
import model.IdadeInvalidaException;
import model.SemestreInvalidoException;
import storage.ArmazenadorLista;
import storage.CadastroCheioException;
import storage.IArmazenador;
import storage.RaDuplicadoException;
import storage.RaInexistenteException;
import ui.IIO;
import ui.IMenu;
import ui.IOGrafico;
import ui.MenuGrafico;

/**
 * Classe principal do sistema de cadastro de alunos. Utiliza modo grafico
 * e lista ligada como estrutura de dados padrao. Oferece um menu com as
 * operacoes de inserir, remover, listar, atualizar, salvar/carregar
 * arquivo e sair.
 *
 * @author Kaua Bezerra, Liam Vedovato, Raul Kolaric, Rodrigo Ward
 * @version 3.0 2026/05/19
 */
public class App {

    /**
     * Pre-popula o cadastro com tres alunos de exemplo para facilitar
     * os testes durante a apresentacao.
     *
     * @param ca Cadastro a ser pre-populado.
     * @param io Interface de I/O para exibir avisos, se necessario.
     */
    private static void prepopular(CadastroAlunos ca, IIO io) {
        try {
            ca.inserir(new Aluno("Alan Mathison Turing", 41, "RA1001", "Ciencia da Computacao", 5));
            ca.inserir(new Aluno("Ada Augusta Lovelace", 36, "RA1002", "Matematica", 8));
            ca.inserir(new Aluno("Grace Murray Hopper", 85, "RA1003", "Sistemas de Informacao", 6));
        } catch (CadastroCheioException e) {
            io.mostrar("Aviso: cadastro pequeno demais para os alunos de exemplo.");
        } catch (RaDuplicadoException e) {
            // ignorado: dados de exemplo nao possuem RAs duplicados
        } catch (IdadeInvalidaException e) {
            // dados internos validos; nao deve ocorrer
        } catch (SemestreInvalidoException e) {
            // dados internos validos; nao deve ocorrer
        }
    }

    /**
     * Solicita ao usuario os dados de um novo aluno e o insere no cadastro.
     *
     * @param ca Cadastro onde o aluno sera inserido.
     * @param io Interface de I/O para leitura e exibicao de mensagens.
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

        Integer semestre = io.lerInteiro("Semestre:", Aluno.SEMESTRE_MIN, Aluno.SEMESTRE_MAX);
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
        } catch (SemestreInvalidoException e) {
            io.mostrar("Erro: " + e.getMessage());
        } catch (Exception e) {
            io.mostrar("Erro inesperado ao inserir o aluno. Tente novamente.");
        }
    }

    /**
     * Solicita um RA ao usuario e remove o aluno correspondente do cadastro.
     *
     * @param ca Cadastro de onde o aluno sera removido.
     * @param io Interface de I/O para leitura e exibicao de mensagens.
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
     * Lista todos os alunos cadastrados no formato escolhido pelo usuario.
     *
     * @param ca Cadastro a ser listado.
     * @param io Interface de I/O para leitura e exibicao de mensagens.
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
     * Solicita um RA e os novos dados ao usuario, atualizando o aluno
     * correspondente no cadastro.
     *
     * @param ca Cadastro onde o aluno sera atualizado.
     * @param io Interface de I/O para leitura e exibicao de mensagens.
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

        Integer semestre = io.lerInteiro("Novo semestre:", Aluno.SEMESTRE_MIN, Aluno.SEMESTRE_MAX);
        if (semestre == null) return;

        try {
            Aluno novo = new Aluno(nome, idade, ra, curso, semestre);
            ca.atualizar(ra, novo);
            io.mostrar("Aluno atualizado com sucesso!");
        } catch (RaInexistenteException e) {
            io.mostrar("Erro: RA nao encontrado para atualizacao.");
        } catch (IdadeInvalidaException e) {
            io.mostrar("Erro: " + e.getMessage());
        } catch (SemestreInvalidoException e) {
            io.mostrar("Erro: " + e.getMessage());
        } catch (Exception e) {
            io.mostrar("Erro inesperado ao atualizar o aluno. Tente novamente.");
        }
    }

    /**
     * Solicita um caminho de arquivo ao usuario e salva o cadastro atual.
     *
     * @param ca Cadastro a ser salvo.
     * @param io Interface de I/O para leitura e exibicao de mensagens.
     */
    private static void operacaoSalvar(CadastroAlunos ca, IIO io) {
        String caminho = io.escolherArquivo(true);
        if (caminho == null) return;
        try {
            ca.salvar(caminho);
            io.mostrar("Cadastro salvo com sucesso em:\n" + caminho);
        } catch (IOException e) {
            io.mostrar("Erro ao salvar o arquivo: " + e.getMessage());
        } catch (Exception e) {
            io.mostrar("Erro inesperado ao salvar o cadastro.");
        }
    }

    /**
     * Solicita um caminho de arquivo ao usuario e carrega o cadastro a
     * partir dele, substituindo os dados em memoria.
     *
     * @param ca Cadastro a ser substituido pelos dados do arquivo.
     * @param io Interface de I/O para leitura e exibicao de mensagens.
     */
    private static void operacaoCarregar(CadastroAlunos ca, IIO io) {
        String caminho = io.escolherArquivo(false);
        if (caminho == null) return;
        try {
            ca.carregar(caminho);
            io.mostrar("Cadastro carregado com sucesso de:\n" + caminho);
        } catch (IOException e) {
            io.mostrar("Erro ao abrir o arquivo: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            io.mostrar("Erro: arquivo em formato desconhecido.");
        } catch (ClassCastException e) {
            io.mostrar("Erro: o arquivo nao corresponde a estrutura de dados atual.");
        } catch (Exception e) {
            io.mostrar("Erro inesperado ao carregar o cadastro.");
        }
    }

    /**
     * Ponto de entrada da aplicacao. Inicializa o modo grafico e a lista
     * ligada como estrutura de dados, pre-popula o cadastro com exemplos
     * e exibe o menu principal ate o usuario optar por sair.
     *
     * @param args Argumentos de linha de comando (nao utilizados).
     */
    public static void main(String[] args) {
        // Modo grafico e lista ligada sao os padroes fixos do sistema
        IIO io = new IOGrafico();
        IArmazenador arm = new ArmazenadorLista();
        CadastroAlunos ca = new CadastroAlunos(arm);
        prepopular(ca, io);

        IMenu mn = new MenuGrafico(io);

        String[] itensMenu = {
            "1 - inserir",
            "2 - remover",
            "3 - listar",
            "4 - atualizar",
            "5 - salvar em arquivo",
            "6 - carregar de arquivo",
            "7 - sair"
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
                    case 5: operacaoSalvar(ca, io);    break;
                    case 6: operacaoCarregar(ca, io);  break;
                    case 7: break;
                    default:
                        io.mostrar("Opcao invalida. Escolha entre 1 e 7.");
                }
            } catch (Exception e) {
                io.mostrar("Ocorreu um erro inesperado. A operacao foi abortada.");
            }
        } while (opcao != 7);

        io.fechar();
    }
}
