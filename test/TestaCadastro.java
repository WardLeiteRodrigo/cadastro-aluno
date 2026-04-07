package test;

import main.CadastroAlunos;
import model.Aluno;
import model.IdadeInvalidaException;
import storage.RaDuplicadoException;
import storage.RaInexistenteException;
import storage.CadastroCheioException;

/**
 * Testes manuais para a classe {@link CadastroAlunos}, exercitando as
 * principais regras de negocio: insercao, remocao, atualizacao,
 * RA duplicado, RA inexistente, cadastro cheio e idade invalida.
 *
 * Cada teste imprime [OK] ou [FALHOU] no console. Pode ser executado
 * diretamente pelo BlueJ (right-click > void main).
 *
 * @author Kaua Bezerra, Liam Vedovato, Raul Kolaric, Rodrigo Ward
 * @version 1.0 2026/04/07
 */
public class TestaCadastro {

    /** Contador de testes que passaram. */
    private static int passou = 0;
    /** Contador de testes que falharam. */
    private static int falhou = 0;

    /**
     * Marca um teste como passou ou falhou e imprime o resultado.
     *
     * @param nome      Nome descritivo do teste.
     * @param condicao  true se passou, false caso contrario.
     */
    private static void verificar(String nome, boolean condicao) {
        if (condicao) {
            passou++;
            System.out.println("[OK]      " + nome);
        } else {
            falhou++;
            System.out.println("[FALHOU]  " + nome);
        }
    }

    /**
     * Cria um aluno valido para uso nos testes, ignorando a excecao
     * (que nao deve ocorrer com dados validos).
     *
     * @param ra RA do aluno.
     * @return Objeto Aluno construido.
     */
    private static Aluno criar(String ra) {
        try {
            return new Aluno("Fulano de Tal", 25, ra, "Computacao", 3);
        } catch (IdadeInvalidaException e) {
            throw new RuntimeException("Dados de teste invalidos", e);
        }
    }

    /**
     * Testa a insercao basica de um aluno.
     */
    private static void testInserirOk() {
        CadastroAlunos ca = new CadastroAlunos(3);
        try {
            ca.inserir(criar("RA1"));
            verificar("inserir aluno valido", ca.existe("RA1"));
        } catch (Exception e) {
            verificar("inserir aluno valido (excecao inesperada: " + e.getMessage() + ")", false);
        }
    }

    /**
     * Testa que inserir RA duplicado lanca {@link RaDuplicadoException}.
     */
    private static void testInserirDuplicado() {
        CadastroAlunos ca = new CadastroAlunos(3);
        try {
            ca.inserir(criar("RA1"));
            ca.inserir(criar("RA1"));
            verificar("inserir RA duplicado deveria lancar excecao", false);
        } catch (RaDuplicadoException e) {
            verificar("inserir RA duplicado lanca RaDuplicadoException", true);
        } catch (Exception e) {
            verificar("inserir RA duplicado (excecao errada: " + e.getClass().getSimpleName() + ")", false);
        }
    }

    /**
     * Testa que insercao em cadastro cheio lanca {@link CadastroCheioException}.
     */
    private static void testCadastroCheio() {
        CadastroAlunos ca = new CadastroAlunos(2);
        try {
            ca.inserir(criar("RA1"));
            ca.inserir(criar("RA2"));
            ca.inserir(criar("RA3"));
            verificar("inserir em cadastro cheio deveria lancar excecao", false);
        } catch (CadastroCheioException e) {
            verificar("inserir em cadastro cheio lanca CadastroCheioException", true);
        } catch (Exception e) {
            verificar("cadastro cheio (excecao errada: " + e.getClass().getSimpleName() + ")", false);
        }
    }

    /**
     * Testa a remocao de um aluno existente.
     */
    private static void testRemoverOk() {
        CadastroAlunos ca = new CadastroAlunos(3);
        try {
            ca.inserir(criar("RA1"));
            ca.remover("RA1");
            verificar("remover aluno existente", !ca.existe("RA1"));
        } catch (Exception e) {
            verificar("remover aluno existente (excecao: " + e.getMessage() + ")", false);
        }
    }

    /**
     * Testa que remover RA inexistente lanca {@link RaInexistenteException}.
     */
    private static void testRemoverInexistente() {
        CadastroAlunos ca = new CadastroAlunos(3);
        try {
            ca.remover("RA999");
            verificar("remover RA inexistente deveria lancar excecao", false);
        } catch (RaInexistenteException e) {
            verificar("remover RA inexistente lanca RaInexistenteException", true);
        }
    }

    /**
     * Testa a atualizacao de um aluno existente.
     */
    private static void testAtualizarOk() {
        CadastroAlunos ca = new CadastroAlunos(3);
        try {
            ca.inserir(criar("RA1"));
            Aluno novo = new Aluno("Beltrano Silva", 30, "RA1", "Engenharia", 5);
            ca.atualizar("RA1", novo);
            // listar deveria conter "Beltrano"
            String lista = ca.listar(false);
            verificar("atualizar aluno existente", lista.contains("Beltrano") && lista.contains("Engenharia"));
        } catch (Exception e) {
            verificar("atualizar aluno existente (excecao: " + e.getMessage() + ")", false);
        }
    }

    /**
     * Testa que atualizar RA inexistente lanca {@link RaInexistenteException}.
     */
    private static void testAtualizarInexistente() {
        CadastroAlunos ca = new CadastroAlunos(3);
        try {
            ca.atualizar("RA999", criar("RA999"));
            verificar("atualizar RA inexistente deveria lancar excecao", false);
        } catch (RaInexistenteException e) {
            verificar("atualizar RA inexistente lanca RaInexistenteException", true);
        } catch (Exception e) {
            verificar("atualizar inexistente (excecao errada: " + e.getClass().getSimpleName() + ")", false);
        }
    }

    /**
     * Testa que idades fora do intervalo lancam {@link IdadeInvalidaException}.
     */
    private static void testIdadeInvalida() {
        try {
            new Aluno("Crianca", 10, "RA1", "Curso", 1);
            verificar("idade < 16 deveria lancar excecao", false);
        } catch (IdadeInvalidaException e) {
            verificar("idade < 16 lanca IdadeInvalidaException", true);
        }

        try {
            new Aluno("Matusalem", 200, "RA2", "Curso", 1);
            verificar("idade > 120 deveria lancar excecao", false);
        } catch (IdadeInvalidaException e) {
            verificar("idade > 120 lanca IdadeInvalidaException", true);
        }
    }

    /**
     * Testa a listagem em formato comum e bibliografico.
     */
    private static void testListar() {
        CadastroAlunos ca = new CadastroAlunos(3);
        try {
            ca.inserir(new Aluno("Alan Mathison Turing", 41, "RA1", "Computacao", 5));
            String comum = ca.listar(false);
            String biblio = ca.listar(true);
            verificar("listar comum contem nome", comum.contains("Alan Mathison Turing"));
            verificar("listar bibliografico contem sobrenome", biblio.contains("Turing"));
        } catch (Exception e) {
            verificar("listar (excecao: " + e.getMessage() + ")", false);
        }

        CadastroAlunos vazio = new CadastroAlunos(3);
        verificar("listar cadastro vazio", vazio.listar(false).contains("Nenhum"));
    }

    /**
     * Executa todos os testes e exibe o resumo final.
     *
     * @param args Argumentos de linha de comando (nao utilizados).
     */
    public static void main(String[] args) {
        System.out.println("=== TestaCadastro ===\n");

        testInserirOk();
        testInserirDuplicado();
        testCadastroCheio();
        testRemoverOk();
        testRemoverInexistente();
        testAtualizarOk();
        testAtualizarInexistente();
        testIdadeInvalida();
        testListar();

        System.out.println("\n=== Resumo ===");
        System.out.println("Passaram: " + passou);
        System.out.println("Falharam: " + falhou);
    }
}
