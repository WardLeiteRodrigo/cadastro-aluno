package main;

import java.io.IOException;

import javax.swing.JOptionPane;

import model.Aluno;
import model.IdadeInvalidaException;
import model.SemestreInvalidoException;
import storage.Armazenador;
import storage.ArmazenadorLista;
import storage.CadastroCheioException;
import storage.IArmazenador;
import storage.RaDuplicadoException;
import storage.RaInexistenteException;
import ui.IIO;
import ui.IMenu;
import ui.IOGrafico;
import ui.IOTexto;
import ui.MenuGrafico;
import ui.MenuTexto;

/**
 * Classe principal do sistema de cadastro de alunos. Pergunta o modo de
 * interacao (grafico/texto), a estrutura de dados (vetor/lista) e oferece
 * um menu com as operacoes de inserir, remover, listar, atualizar,
 * salvar/carregar arquivo e sair.
 *
 * @author Kaua Bezerra, Liam Vedovato, Raul Kolaric, Rodrigo Ward
 * @version 2.0 2026/04/27
 */
public class App {

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
     * Pergunta ao usuario qual estrutura de dados usar e ja constroi o
     * armazenador correspondente. Para o vetor pede a capacidade; para a
     * lista nao precisa, pois a capacidade e elastica.
     *
     * @return Armazenador pronto para uso, ou null se o usuario cancelar.
     */
    private static IArmazenador escolherED(IIO io) {
        Integer tipo = io.lerInteiro(
            "Estrutura de dados:\n1 - Vetor (capacidade fixa)\n2 - Lista (ArrayList - elastica)",
            1, 2);
        if (tipo == null) return null;

        if (tipo == 1) {
            Integer qtde = io.lerInteiro("Forneca a quantidade de alunos:", 1, 1000);
            if (qtde == null) return null;
            return new Armazenador(qtde);
        }
        return new ArmazenadorLista();
    }

    private static void prepopular(CadastroAlunos ca, IIO io) {
        try {
            ca.inserir(new Aluno("Alan Mathison Turing", 41, "RA1001", "Ciencia da Computacao", 5));
            ca.inserir(new Aluno("Ada Augusta Lovelace", 36, "RA1002", "Matematica", 8));
            ca.inserir(new Aluno("Grace Murray Hopper", 85, "RA1003", "Sistemas de Informacao", 6));
        } catch (CadastroCheioException e) {
            io.mostrar("Aviso: cadastro pequeno demais para os alunos de exemplo.");
        } catch (RaDuplicadoException e) {
            // ignorado
        } catch (IdadeInvalidaException e) {
            // dados internos validos; nao deve ocorrer
        } catch (SemestreInvalidoException e) {
            // dados internos validos; nao deve ocorrer
        }
    }

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

    private static void operacaoListar(CadastroAlunos ca, IIO io) {
        try {
            boolean biblio = io.lerConfirmacao("Deseja listar no formato bibliografico?");
            io.mostrar(ca.listar(biblio));
        } catch (Exception e) {
            io.mostrar("Erro inesperado ao listar os alunos.");
        }
    }

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

    public static void main(String[] args) {
        int modo = escolherModo();
        if (modo == -1) return;

        IIO io = (modo == 1) ? new IOGrafico() : new IOTexto();

        IArmazenador arm = escolherED(io);
        if (arm == null) {
            io.fechar();
            return;
        }

        CadastroAlunos ca = new CadastroAlunos(arm);
        prepopular(ca, io);

        IMenu mn = (modo == 1) ? new MenuGrafico(io) : new MenuTexto(io);

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
