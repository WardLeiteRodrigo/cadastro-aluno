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
 * (lista ligada), garantindo que ambas implementacoes respeitam o contrato.
 *
 * O teste de cadastro cheio so e aplicavel ao vetor (a lista e elastica)
 * e e pulado quando rodando contra a lista.
 *
 * @author Kaua Bezerra, Liam Vedovato, Raul Kolaric, Rodrigo Ward
 * @version 2.0 2026/04/27
 */
public class TestaCadastro {

    /**
     * Classe de teste manual: nao deve ser instanciada.
     */
    private TestaCadastro() {
    }

    /** Contador de assercoes que passaram durante a execucao das baterias. */
    private static int passou = 0;
    /** Contador de assercoes que falharam durante a execucao das baterias. */
    private static int falhou = 0;

    /**
     * Registra o resultado de uma assercao, atualizando os contadores
     * {@link #passou} ou {@link #falhou} e imprimindo o desfecho no console.
     *
     * @param nome     Descricao curta do caso de teste sendo verificado.
     * @param condicao Resultado da assercao: {@code true} para sucesso,
     *                 {@code false} para falha.
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
     * Fabrica um {@link Aluno} valido pronto para uso nos testes, evitando
     * repeticao dos blocos try/catch em cada caso. Usa dados ficticios e
     * apenas varia o RA fornecido.
     *
     * @param ra RA a ser atribuido ao aluno de teste.
     * @return Aluno valido com nome, idade, curso e semestre padrao.
     */
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

    /**
     * Verifica que a insercao de um aluno valido grava o registro no
     * cadastro (consulta {@link CadastroAlunos#existe(String)} apos inserir).
     *
     * @param fab Fabrica de cadastros usada para criar a instancia sob teste.
     */
    private static void testInserirOk(FabricaCadastro fab) {
        CadastroAlunos ca = fab.novo(3);
        try {
            ca.inserir(criar("RA1"));
            verificar("inserir aluno valido", ca.existe("RA1"));
        } catch (Exception e) {
            verificar("inserir aluno valido (excecao inesperada: " + e.getMessage() + ")", false);
        }
    }

    /**
     * Verifica que inserir um aluno com RA ja existente lanca
     * {@link RaDuplicadoException}.
     *
     * @param fab Fabrica de cadastros usada para criar a instancia sob teste.
     */
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

    /**
     * Verifica que inserir alem da capacidade de uma estrutura de tamanho
     * fixo (vetor) lanca {@link CadastroCheioException}. Aplicavel apenas
     * ao {@link Armazenador} (a lista e elastica).
     *
     * @param fab Fabrica de cadastros usada para criar a instancia sob teste.
     */
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

    /**
     * Verifica que a remocao de um aluno existente retira efetivamente o
     * registro do cadastro.
     *
     * @param fab Fabrica de cadastros usada para criar a instancia sob teste.
     */
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

    /**
     * Verifica que tentar remover um RA inexistente lanca
     * {@link RaInexistenteException}.
     *
     * @param fab Fabrica de cadastros usada para criar a instancia sob teste.
     */
    private static void testRemoverInexistente(FabricaCadastro fab) {
        CadastroAlunos ca = fab.novo(3);
        try {
            ca.remover("RA999");
            verificar("remover RA inexistente deveria lancar excecao", false);
        } catch (RaInexistenteException e) {
            verificar("remover RA inexistente lanca RaInexistenteException", true);
        }
    }

    /**
     * Verifica que a atualizacao de um aluno existente substitui os dados
     * antigos pelos novos (confirmado via {@link CadastroAlunos#listar(boolean)}).
     *
     * @param fab Fabrica de cadastros usada para criar a instancia sob teste.
     */
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

    /**
     * Verifica que tentar atualizar um RA inexistente lanca
     * {@link RaInexistenteException}.
     *
     * @param fab Fabrica de cadastros usada para criar a instancia sob teste.
     */
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

    /**
     * Verifica que valores de idade fora do intervalo permitido
     * ({@link Aluno#IDADE_MIN} a {@link Aluno#IDADE_MAX}) provocam
     * {@link IdadeInvalidaException} na construcao do aluno. Testa tanto
     * idade negativa quanto idade acima do limite superior.
     */
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

    /**
     * Verifica que valores de semestre fora do intervalo permitido
     * ({@link Aluno#SEMESTRE_MIN} a {@link Aluno#SEMESTRE_MAX}) provocam
     * {@link SemestreInvalidoException} na construcao do aluno.
     */
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

    /**
     * Verifica a listagem do cadastro nos dois formatos (comum e
     * bibliografico) e tambem a mensagem retornada para um cadastro vazio.
     *
     * @param fab Fabrica de cadastros usada para criar a instancia sob teste.
     */
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

    /**
     * Ponto de entrada dos testes manuais. Executa primeiro a validacao do
     * modelo (idade e semestre) e em seguida roda a bateria completa contra
     * as duas implementacoes de {@link IArmazenador}: {@link Armazenador}
     * (vetor) e {@link ArmazenadorLista} (lista ligada). Ao final, imprime
     * o total de assercoes que passaram e falharam.
     *
     * @param args Argumentos de linha de comando (nao utilizados).
     */
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

        // Bateria contra ArmazenadorLista (lista ligada, elastica)
        executarBateria("ArmazenadorLista (lista ligada)", new FabricaCadastro() {
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
