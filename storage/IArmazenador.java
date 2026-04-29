package storage;

import java.io.IOException;

import model.Aluno;

/**
 * Interface que define o contrato de uma estrutura de armazenamento de
 * alunos. Permite que o cadastro use diferentes estruturas de dados
 * (vetor, lista encadeada, etc.) sem alterar o restante do sistema.
 *
 * @author Kaua Bezerra, Liam Vedovato, Raul Kolaric, Rodrigo Ward
 * @version 1.0 2026/04/07
 */
public interface IArmazenador {

    /**
     * Insere um novo aluno na estrutura de armazenamento.
     *
     * @param a O aluno a ser inserido.
     * @throws RaDuplicadoException   se ja existir um aluno com o mesmo RA.
     * @throws CadastroCheioException se a estrutura ja estiver na capacidade maxima.
     */
    void inserir(Aluno a) throws RaDuplicadoException, CadastroCheioException;

    /**
     * Remove um aluno do armazenamento pelo RA.
     *
     * @param ra O registro academico do aluno a ser removido.
     * @throws RaInexistenteException se nao existir aluno com o RA informado.
     */
    void remover(String ra) throws RaInexistenteException;

    /**
     * Atualiza os dados de um aluno existente.
     *
     * @param ra        O RA do aluno a ser substituido.
     * @param novoAluno O novo objeto Aluno com os dados atualizados.
     * @throws RaInexistenteException se nao existir aluno com o RA informado.
     */
    void atualizar(String ra, Aluno novoAluno) throws RaInexistenteException;

    /**
     * Verifica se um aluno com o RA especificado existe.
     *
     * @param ra O RA a ser buscado.
     * @return true se existir, false caso contrario.
     */
    boolean existe(String ra);

    /**
     * Lista todos os alunos armazenados.
     *
     * @param formatoBibliografico Se true, formata os nomes em estilo bibliografico.
     * @return Uma string com a listagem formatada (ou aviso, se vazio).
     */
    String listar(boolean formatoBibliografico);

    /**
     * Persiste o conteudo do armazenador em um arquivo binario.
     *
     * @param nomeArq Caminho completo do arquivo de destino.
     * @throws IOException em caso de falha de I/O.
     */
    void salvar(String nomeArq) throws IOException;

    /**
     * Carrega o conteudo do armazenador a partir de um arquivo binario.
     * Substitui completamente os dados atualmente em memoria.
     *
     * @param nomeArq Caminho completo do arquivo de origem.
     * @throws IOException            em caso de falha de I/O.
     * @throws ClassNotFoundException se a classe dos objetos serializados nao for encontrada.
     */
    void carregar(String nomeArq) throws IOException, ClassNotFoundException;
}
