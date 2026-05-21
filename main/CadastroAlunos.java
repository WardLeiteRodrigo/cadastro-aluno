package main;

import java.io.IOException;

import model.Aluno;
import storage.IArmazenador;
import storage.RaDuplicadoException;
import storage.RaInexistenteException;
import storage.CadastroCheioException;

/**
 * Fachada que expoe as operacoes de cadastro de alunos delegando ao
 * armazenador subjacente. A estrutura de dados e injetada por construtor,
 * permitindo trocar entre vetor (capacidade fixa) e ArrayList (elastica)
 * sem alterar o restante do sistema.
 *
 * @author Kaua Bezerra, Liam Vedovato, Raul Kolaric, Rodrigo Ward
 * @version 1.1 2026/04/27
 */
public class CadastroAlunos {

    private IArmazenador arm;

    /**
     * Cria um cadastro a partir de uma estrutura de armazenamento ja construida.
     *
     * @param arm Estrutura de armazenamento (vetor ou lista).
     */
    public CadastroAlunos(IArmazenador arm) {
        this.arm = arm;
    }

    /**
     * Insere um novo aluno no cadastro, delegando ao armazenador subjacente.
     *
     * @param a O aluno a ser inserido.
     * @throws RaDuplicadoException   se ja existir um aluno com o mesmo RA.
     * @throws CadastroCheioException se a estrutura ja estiver na capacidade maxima.
     */
    public void inserir(Aluno a) throws RaDuplicadoException, CadastroCheioException {
        arm.inserir(a);
    }

    /**
     * Remove do cadastro o aluno cujo RA foi informado.
     *
     * @param ra RA do aluno a ser removido.
     * @throws RaInexistenteException se nao existir aluno com o RA informado.
     */
    public void remover(String ra) throws RaInexistenteException {
        arm.remover(ra);
    }

    /**
     * Atualiza os dados do aluno identificado pelo RA, substituindo seu
     * registro pelo novo aluno fornecido.
     *
     * @param ra        RA do aluno a ser atualizado.
     * @param novoAluno Novo objeto Aluno que substituira o registro atual.
     * @throws RaInexistenteException se nao existir aluno com o RA informado.
     */
    public void atualizar(String ra, Aluno novoAluno) throws RaInexistenteException {
        arm.atualizar(ra, novoAluno);
    }

    /**
     * Verifica se existe um aluno cadastrado com o RA informado.
     *
     * @param ra RA a ser consultado.
     * @return {@code true} se o aluno existir no cadastro, {@code false} caso contrario.
     */
    public boolean existe(String ra) {
        return arm.existe(ra);
    }

    /**
     * Busca e retorna um aluno pelo RA.
     *
     * @param ra RA a ser buscado.
     * @return O aluno correspondente, ou {@code null} se nao encontrado.
     */
    public Aluno buscar(String ra) {
        return arm.buscar(ra);
    }

    /**
     * Gera uma listagem textual de todos os alunos cadastrados.
     *
     * @param formatoBibliografico Se {@code true}, os nomes sao exibidos no
     *                             formato bibliografico (ex.: SILVA, J. M.);
     *                             se {@code false}, no formato comum.
     * @return String contendo a lista formatada, ou mensagem indicando
     *         cadastro vazio quando nao houver alunos.
     */
    public String listar(boolean formatoBibliografico) {
        return arm.listar(formatoBibliografico);
    }

    /**
     * Salva o cadastro em um arquivo binario.
     *
     * @param nomeArq Caminho completo do arquivo de destino.
     */
    public void salvar(String nomeArq) throws IOException {
        arm.salvar(nomeArq);
    }

    /**
     * Carrega o cadastro a partir de um arquivo binario, substituindo os
     * dados em memoria.
     *
     * @param nomeArq Caminho completo do arquivo de origem.
     */
    public void carregar(String nomeArq) throws IOException, ClassNotFoundException {
        arm.carregar(nomeArq);
    }
}
