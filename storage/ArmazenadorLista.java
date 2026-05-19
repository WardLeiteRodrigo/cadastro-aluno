package storage;

import java.io.IOException;
import java.io.Serializable;

import model.Aluno;

/**
 * Implementacao de {@link IArmazenador} usando uma lista simplesmente
 * ligada construida manualmente. Cada elemento e armazenado em um no
 * interno ({@link No}) que referencia o proximo, sem necessidade de
 * capacidade pre-definida.
 *
 * A classe interna {@link No} e privada pois e um detalhe de implementacao
 * desta estrutura: nenhuma outra classe precisa conhece-la.
 *
 * @author Kaua Bezerra, Liam Vedovato, Raul Kolaric, Rodrigo Ward
 * @version 2.0 2026/05/19
 */
public class ArmazenadorLista implements IArmazenador {

    // ------------------------------------------------------------------ //
    //  Classe interna: No da lista ligada                                 //
    // ------------------------------------------------------------------ //

    /**
     * No da lista simplesmente ligada. Encapsula um aluno e a referencia
     * para o proximo no da cadeia. Implementa {@link Serializable} para
     * que a lista possa ser persistida em arquivo binario.
     */
    private static class No implements Serializable {

        private static final long serialVersionUID = 1L;

        /** Aluno armazenado neste no. */
        Aluno dado;

        /** Referencia para o proximo no (null = fim da lista). */
        No proximo;

        /**
         * Cria um no com o aluno informado e sem proximo.
         *
         * @param dado Aluno a ser armazenado.
         */
        No(Aluno dado) {
            this.dado = dado;
            this.proximo = null;
        }
    }

    // ------------------------------------------------------------------ //
    //  Atributos da lista                                                 //
    // ------------------------------------------------------------------ //

    /** Primeiro no da lista (cabeca). {@code null} indica lista vazia. */
    private No cabeca;

    /** Quantidade de alunos atualmente na lista. */
    private int tamanho;

    // ------------------------------------------------------------------ //
    //  Construtor                                                         //
    // ------------------------------------------------------------------ //

    /**
     * Cria uma lista ligada vazia.
     */
    public ArmazenadorLista() {
        this.cabeca = null;
        this.tamanho = 0;
    }

    // ------------------------------------------------------------------ //
    //  Operacoes da interface IArmazenador                                //
    // ------------------------------------------------------------------ //

    /**
     * Insere um aluno no inicio da lista (operacao O(1)).
     * Antes de inserir, verifica se o RA ja existe na lista.
     *
     * @param a O aluno a ser inserido.
     * @throws RaDuplicadoException   se ja existir um aluno com o mesmo RA.
     * @throws CadastroCheioException nunca lancada nesta implementacao
     *                                (lista elastica); presente apenas para
     *                                satisfazer o contrato da interface.
     */
    public void inserir(Aluno a) throws RaDuplicadoException, CadastroCheioException {
        if (existe(a.getRa())) {
            throw new RaDuplicadoException();
        }

        // Insere na cabeca: o novo no aponta para o antigo primeiro no
        No novo = new No(a);
        novo.proximo = this.cabeca;
        this.cabeca = novo;
        this.tamanho++;
    }

    /**
     * Remove um aluno da lista pelo RA, percorrendo os nos.
     *
     * @param ra RA do aluno a ser removido.
     * @throws RaInexistenteException se nao existir aluno com o RA informado.
     */
    public void remover(String ra) throws RaInexistenteException {
        if (this.cabeca == null) {
            throw new RaInexistenteException();
        }

        // Caso especial: aluno esta na cabeca da lista
        if (this.cabeca.dado.getRa().equals(ra)) {
            this.cabeca = this.cabeca.proximo;
            this.tamanho--;
            return;
        }

        // Percorre procurando o no anterior ao que sera removido
        No anterior = this.cabeca;
        while (anterior.proximo != null) {
            if (anterior.proximo.dado.getRa().equals(ra)) {
                anterior.proximo = anterior.proximo.proximo;
                this.tamanho--;
                return;
            }
            anterior = anterior.proximo;
        }

        throw new RaInexistenteException();
    }

    /**
     * Substitui os dados de um aluno existente, localizando-o pelo RA.
     *
     * @param ra        RA do aluno a ser substituido.
     * @param novoAluno Novo objeto Aluno com os dados atualizados.
     * @throws RaInexistenteException se nao existir aluno com o RA informado.
     */
    public void atualizar(String ra, Aluno novoAluno) throws RaInexistenteException {
        No atual = this.cabeca;
        while (atual != null) {
            if (atual.dado.getRa().equals(ra)) {
                atual.dado = novoAluno;
                return;
            }
            atual = atual.proximo;
        }
        throw new RaInexistenteException();
    }

    /**
     * Verifica se existe um aluno com o RA informado percorrendo a lista.
     *
     * @param ra RA a ser buscado.
     * @return true se existir, false caso contrario.
     */
    public boolean existe(String ra) {
        if (ra == null) return false;
        String alvo = ra.trim();
        No atual = this.cabeca;
        while (atual != null) {
            if (atual.dado.getRa().equals(alvo)) {
                return true;
            }
            atual = atual.proximo;
        }
        return false;
    }

    /**
     * Lista todos os alunos percorrendo a lista do inicio ao fim.
     *
     * @param formatoBibliografico Se true, exibe nomes em formato bibliografico.
     * @return String com a listagem formatada (ou aviso, se vazia).
     */
    public String listar(boolean formatoBibliografico) {
        if (this.cabeca == null) {
            return "Nenhum aluno cadastrado.";
        }

        StringBuilder sb = new StringBuilder();
        No atual = this.cabeca;
        while (atual != null) {
            if (formatoBibliografico) {
                sb.append(atual.dado.getNomeBiblio())
                  .append(" - RA: ").append(atual.dado.getRa())
                  .append(" - Curso: ").append(atual.dado.getCurso())
                  .append(" - Semestre: ").append(atual.dado.getSemestre())
                  .append("\n");
            } else {
                sb.append(atual.dado.toString()).append("\n");
                sb.append("-----------------------------\n");
            }
            atual = atual.proximo;
        }
        return sb.toString();
    }

    /**
     * Salva a lista de alunos em arquivo binario convertendo-a para um
     * vetor ({@code Aluno[]}), formato compativel com {@link ArquivoBinario}.
     *
     * @param nomeArq Caminho completo do arquivo de destino.
     * @throws IOException em caso de falha de I/O.
     */
    public void salvar(String nomeArq) throws IOException {
        // Converte a lista ligada para vetor antes de serializar
        Aluno[] vetor = new Aluno[this.tamanho];
        No atual = this.cabeca;
        for (int i = 0; i < this.tamanho; i++) {
            vetor[i] = atual.dado;
            atual = atual.proximo;
        }
        ArquivoBinario ab = new ArquivoBinario(nomeArq);
        ab.gravarObj(vetor);
    }

    /**
     * Carrega alunos a partir de arquivo binario, reconstruindo a lista
     * ligada e substituindo completamente os dados em memoria.
     * A ordem original dos alunos e preservada.
     *
     * @param nomeArq Caminho completo do arquivo de origem.
     * @throws IOException            em caso de falha de I/O.
     * @throws ClassNotFoundException se a classe dos objetos serializados
     *                                nao for encontrada.
     */
    public void carregar(String nomeArq) throws IOException, ClassNotFoundException {
        ArquivoBinario ab = new ArquivoBinario(nomeArq);
        Object lido = ab.lerObj();
        if (!(lido instanceof Aluno[])) {
            throw new IOException("Formato de arquivo incompativel: esperado vetor de Aluno.");
        }

        // Reinicia a lista e reconstroi a partir do vetor carregado.
        // Insere em ordem reversa para preservar a ordem original,
        // ja que inserimos sempre na cabeca.
        this.cabeca = null;
        this.tamanho = 0;
        Aluno[] vetor = (Aluno[]) lido;
        for (int i = vetor.length - 1; i >= 0; i--) {
            if (vetor[i] != null) {
                No novo = new No(vetor[i]);
                novo.proximo = this.cabeca;
                this.cabeca = novo;
                this.tamanho++;
            }
        }
    }
}
