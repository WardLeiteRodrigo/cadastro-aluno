package test;

import main.CadastroAlunos;
import model.Aluno;
import model.IdadeInvalidaException;
import model.SemestreInvalidoException;
import storage.Armazenador;
import storage.ArmazenadorLista;
import storage.IArmazenador;
import storage.RaDuplicadoException;
import storage.RaInexistenteException;
import storage.CadastroCheioException;

/**
 * Testes manuais para a classe {@link CadastroAlunos}, exercitando as
 * principais regras de negocio. A bateria e executada duas vezes: uma
 * com {@link Armazenador} (vetor) e outra com {@link ArmazenadorLista}
 * (ArrayList), garantindo que ambas implementacoes respeitam o contrato.
 *
 * O teste de cadastro cheio so e aplicavel ao vetor (a lista e elastica)
 * e e pulado quando rodando contra a lista.
 *
 * @author Kaua Bezerra, Liam Vedovato, Raul Kolaric, Rodrigo Ward
 * @version 2.0 2026/04/27
 */
public class TestaCadastro {

    private static int passou = 0;
    private static int falhou = 0;

    private static void verificar(String nome, boolean condicao) {
        if (condicao) {
            passou++;
            System.out.println("[OK]      " + nome);
        } else {
            falhou++;
            System.out.println("[FALHOU]  " + nome);
        }
    }

    private static Aluno criar(String ra) {
        try {
            return new Aluno("Fulano de Tal", 25, ra, "Computacao", 3);
        } catch (IdadeInvalidaException e) {
            throw new RuntimeException("Dados de teste invalidos", e);
        } catch (SemestreInvalidoException e) {
            throw new RuntimeException("Dados de teste invalidos", e);
        }
    }

    /**
     * Fabrica de cadastros para cada bateria. Recebe a capacidade desejada
     * (usada apenas pelo vetor) e devolve um cadastro pronto.
     */
    private interface FabricaCadastro {
        CadastroAlunos novo(int qtde);
    }

    private static void testInserirOk(FabricaCadastro fab) {
        CadastroAlunos ca = fab.novo(3);
        try {
            ca.inserir(criar("RA1"));
            verificar("inserir aluno valido", ca.existe("RA1"));
        } catch (Exception e) {
            verificar("inserir aluno valido (excecao inesperada: " + e.getMessage() + ")", false);
        }
    }

    private static void testInserirDuplicado(FabricaCadastro fab) {
        CadastroAlunos ca = fab.novo(3);
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

    private static void testCadastroCheio(FabricaCadastro fab) {
        CadastroAlunos ca = fab.novo(2);
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

    private static void testRemoverOk(FabricaCadastro fab) {
        CadastroAlunos ca = fab.novo(3);
        try {
            ca.inserir(criar("RA1"));
            ca.remover("RA1");
            verificar("remover aluno existente", !ca.existe("RA1"));
        } catch (Exception e) {
            verificar("remover aluno existente (excecao: " + e.getMessage() + ")", false);
        }
    }

    private static void testRemoverInexistente(FabricaCadastro fab) {
        CadastroAlunos ca = fab.novo(3);
        try {
            ca.remover("RA999");
            verificar("remover RA inexistente deveria lancar excecao", false);
        } catch (RaInexistenteException e) {
            verificar("remover RA inexistente lanca RaInexistenteException", true);
        }
    }

    private static void testAtualizarOk(FabricaCadastro fab) {
        CadastroAlunos ca = fab.novo(3);
        try {
            ca.inserir(criar("RA1"));
            Aluno novo = new Aluno("Beltrano Silva", 30, "RA1", "Engenharia", 5);
            ca.atualizar("RA1", novo);
            String lista = ca.listar(false);
            verificar("atualizar aluno existente", lista.contains("Beltrano") && lista.contains("Engenharia"));
        } catch (Exception e) {
            verificar("atualizar aluno existente (excecao: " + e.getMessage() + ")", false);
        }
    }

    private static void testAtualizarInexistente(FabricaCadastro fab) {
        CadastroAlunos ca = fab.novo(3);
        try {
            ca.atualizar("RA999", criar("RA999"));
            verificar("atualizar RA inexistente deveria lancar excecao", false);
        } catch (RaInexistenteException e) {
            verificar("atualizar RA inexistente lanca RaInexistenteException", true);
        } catch (Exception e) {
            verificar("atualizar inexistente (excecao errada: " + e.getClass().getSimpleName() + ")", false);
        }
    }

    private static void testIdadeInvalida() {
        try {
            new Aluno("Negativo", -1, "RA1", "Curso", 1);
            verificar("idade < 0 deveria lancar excecao", false);
        } catch (IdadeInvalidaException e) {
            verificar("idade < 0 lanca IdadeInvalidaException", true);
        } catch (SemestreInvalidoException e) {
            verificar("idade < 0 (excecao errada)", false);
        }

        try {
            new Aluno("Matusalem", 200, "RA2", "Curso", 1);
            verificar("idade > 120 deveria lancar excecao", false);
        } catch (IdadeInvalidaException e) {
            verificar("idade > 120 lanca IdadeInvalidaException", true);
        } catch (SemestreInvalidoException e) {
            verificar("idade > 120 (excecao errada)", false);
        }
    }

    private static void testSemestreInvalido() {
        try {
            new Aluno("Fulano", 20, "RA1", "Curso", 0);
            verificar("semestre < 1 deveria lancar excecao", false);
        } catch (SemestreInvalidoException e) {
            verificar("semestre < 1 lanca SemestreInvalidoException", true);
        } catch (IdadeInvalidaException e) {
            verificar("semestre < 1 (excecao errada)", false);
        }

        try {
            new Aluno("Fulano", 20, "RA2", "Curso", 25);
            verificar("semestre > 12 deveria lancar excecao", false);
        } catch (SemestreInvalidoException e) {
            verificar("semestre > 12 lanca SemestreInvalidoException", true);
        } catch (IdadeInvalidaException e) {
            verificar("semestre > 12 (excecao errada)", false);
        }
    }

    private static void testListar(FabricaCadastro fab) {
        CadastroAlunos ca = fab.novo(3);
        try {
            ca.inserir(new Aluno("Alan Mathison Turing", 41, "RA1", "Computacao", 5));
            String comum = ca.listar(false);
            String biblio = ca.listar(true);
            verificar("listar comum contem nome", comum.contains("Alan Mathison Turing"));
            verificar("listar bibliografico contem sobrenome", biblio.contains("Turing"));
        } catch (Exception e) {
            verificar("listar (excecao: " + e.getMessage() + ")", false);
        }

        CadastroAlunos vazio = fab.novo(3);
        verificar("listar cadastro vazio", vazio.listar(false).contains("Nenhum"));
    }

    /**
     * Roda a bateria de testes contra uma fabrica de cadastros especifica.
     *
     * @param nomeED       Nome da estrutura (apenas para o cabecalho).
     * @param fab          Fabrica que cria o cadastro.
     * @param suportaCheio true se a estrutura tem capacidade fixa (vetor).
     */
    private static void executarBateria(String nomeED, FabricaCadastro fab, boolean suportaCheio) {
        System.out.println("\n--- Bateria: " + nomeED + " ---");
        testInserirOk(fab);
        testInserirDuplicado(fab);
        if (suportaCheio) {
            testCadastroCheio(fab);
        } else {
            System.out.println("[PULADO]  cadastro cheio (lista e elastica)");
        }
        testRemoverOk(fab);
        testRemoverInexistente(fab);
        testAtualizarOk(fab);
        testAtualizarInexistente(fab);
        testListar(fab);
    }

    public static void main(String[] args) {
        System.out.println("=== TestaCadastro ===");

        // Testes de modelo (independentes da ED)
        System.out.println("\n--- Validacao do modelo ---");
        testIdadeInvalida();
        testSemestreInvalido();

        // Bateria contra Armazenador (vetor, com capacidade fixa)
        executarBateria("Armazenador (vetor)", new FabricaCadastro() {
            public CadastroAlunos novo(int qtde) {
                IArmazenador arm = new Armazenador(qtde);
                return new CadastroAlunos(arm);
            }
        }, true);

        // Bateria contra ArmazenadorLista (ArrayList, elastica)
        executarBateria("ArmazenadorLista (ArrayList)", new FabricaCadastro() {
            public CadastroAlunos novo(int qtde) {
                IArmazenador arm = new ArmazenadorLista();
                return new CadastroAlunos(arm);
            }
        }, false);

        System.out.println("\n=== Resumo ===");
        System.out.println("Passaram: " + passou);
        System.out.println("Falharam: " + falhou);
    }
}
