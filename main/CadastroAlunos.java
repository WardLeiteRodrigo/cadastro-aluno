package main;

import model.Aluno;
import storage.IArmazenador;
import storage.Armazenador;
import storage.RaDuplicadoException;
import storage.RaInexistenteException;
import storage.CadastroCheioException;

/**
 * Fachada que expoe as operacoes de cadastro de alunos delegando ao
 * armazenador subjacente. Mantem o codigo do App desacoplado da
 * estrutura de dados escolhida.
 *
 * @author Kaua Bezerra, Liam Vedovato, Raul Kolaric, Rodrigo Ward
 * @version 1.0 2026/04/07
 */
public class CadastroAlunos {

    /** Estrutura de armazenamento (programada para a interface). */
    private IArmazenador arm;

    /**
     * Cria um cadastro com capacidade fixa.
     *
     * @param qtde Capacidade maxima do cadastro.
     */
    public CadastroAlunos(int qtde) {
        this.arm = new Armazenador(qtde);
    }

    /**
     * Insere um aluno no cadastro.
     *
     * @param a Aluno a ser inserido.
     * @throws RaDuplicadoException   se ja existir um aluno com o mesmo RA.
     * @throws CadastroCheioException se o cadastro estiver cheio.
     */
    public void inserir(Aluno a) throws RaDuplicadoException, CadastroCheioException {
        arm.inserir(a);
    }

    /**
     * Remove um aluno do cadastro.
     *
     * @param ra RA do aluno a ser removido.
     * @throws RaInexistenteException se nao existir aluno com o RA informado.
     */
    public void remover(String ra) throws RaInexistenteException {
        arm.remover(ra);
    }

    /**
     * Atualiza os dados de um aluno existente.
     *
     * @param ra        RA do aluno a ser atualizado.
     * @param novoAluno Novo objeto Aluno com os dados atualizados.
     * @throws RaInexistenteException se nao existir aluno com o RA informado.
     */
    public void atualizar(String ra, Aluno novoAluno) throws RaInexistenteException {
        arm.atualizar(ra, novoAluno);
    }

    /**
     * Verifica se um aluno com o RA especificado existe no cadastro.
     *
     * @param ra RA a ser buscado.
     * @return true se existir, false caso contrario.
     */
    public boolean existe(String ra) {
        return arm.existe(ra);
    }

    /**
     * Lista os alunos cadastrados.
     *
     * @param formatoBibliografico Se true, formata os nomes bibliograficamente.
     * @return String com a listagem.
     */
    public String listar(boolean formatoBibliografico) {
        return arm.listar(formatoBibliografico);
    }
}
