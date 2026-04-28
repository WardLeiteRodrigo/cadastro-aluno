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

    public void inserir(Aluno a) throws RaDuplicadoException, CadastroCheioException {
        arm.inserir(a);
    }

    public void remover(String ra) throws RaInexistenteException {
        arm.remover(ra);
    }

    public void atualizar(String ra, Aluno novoAluno) throws RaInexistenteException {
        arm.atualizar(ra, novoAluno);
    }

    public boolean existe(String ra) {
        return arm.existe(ra);
    }

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
