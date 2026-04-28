package storage;

import java.io.IOException;

import model.Aluno;

/**
 * Implementacao de {@link IArmazenador} usando um vetor (array) de
 * tamanho fixo definido na construcao.
 *
 * Erros de dominio (RA duplicado, RA inexistente, cadastro cheio) sao
 * sinalizados por excecoes especificas, permitindo que a camada de
 * apresentacao trate cada caso com mensagem amigavel.
 *
 * @author Kaua Bezerra, Liam Vedovato, Raul Kolaric, Rodrigo Ward
 * @version 1.0 2026/04/07
 */
public class Armazenador implements IArmazenador {

    /** Vetor de alunos. Posicoes nulas representam espacos livres. */
    private Aluno[] alunos;

    /**
     * Cria um armazenador com capacidade fixa.
     *
     * @param qtde Capacidade maxima do armazenador.
     */
    public Armazenador(int qtde) {
        alunos = new Aluno[qtde];
    }

    /**
     * Insere um aluno no primeiro espaco livre do vetor.
     *
     * @param a O aluno a ser inserido.
     * @throws RaDuplicadoException   se ja existir um aluno com o mesmo RA.
     * @throws CadastroCheioException se nao houver espaco disponivel.
     */
    public void inserir(Aluno a) throws RaDuplicadoException, CadastroCheioException {
        // Verifica se ja existe um aluno com o mesmo RA
        if (existe(a.getRa())) {
            throw new RaDuplicadoException();
        }

        // Procura a primeira posicao livre
        for (int i = 0; i < alunos.length; i++) {
            if (alunos[i] == null) {
                alunos[i] = a;
                return;
            }
        }

        // Nenhuma posicao livre encontrada
        throw new CadastroCheioException();
    }

    /**
     * Remove um aluno buscando pelo RA.
     *
     * @param ra RA do aluno a ser removido.
     * @throws RaInexistenteException se nao existir aluno com o RA informado.
     */
    public void remover(String ra) throws RaInexistenteException {
        for (int i = 0; i < alunos.length; i++) {
            if (alunos[i] != null && alunos[i].getRa().equals(ra)) {
                alunos[i] = null;
                return;
            }
        }
        throw new RaInexistenteException();
    }

    /**
     * Substitui um aluno existente por um novo objeto Aluno.
     *
     * @param ra        RA do aluno a ser substituido.
     * @param novoAluno Novo objeto Aluno com os dados atualizados.
     * @throws RaInexistenteException se nao existir aluno com o RA informado.
     */
    public void atualizar(String ra, Aluno novoAluno) throws RaInexistenteException {
        for (int i = 0; i < alunos.length; i++) {
            if (alunos[i] != null && alunos[i].getRa().equals(ra)) {
                alunos[i] = novoAluno;
                return;
            }
        }
        throw new RaInexistenteException();
    }

    /**
     * Verifica se existe um aluno com o RA informado.
     *
     * @param ra RA a ser buscado.
     * @return true se existir, false caso contrario.
     */
    public boolean existe(String ra) {
        if (ra == null) return false;
        String alvo = ra.trim();
        for (int i = 0; i < alunos.length; i++) {
            if (alunos[i] != null && alunos[i].getRa().equals(alvo)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Lista todos os alunos cadastrados.
     *
     * @param formatoBibliografico Se true, exibe nomes em formato bibliografico.
     * @return String com a listagem formatada (ou aviso, se vazio).
     */
    public String listar(boolean formatoBibliografico) {
        StringBuilder sb = new StringBuilder();
        boolean temAlunos = false;

        for (int i = 0; i < alunos.length; i++) {
            if (alunos[i] != null) {
                temAlunos = true;
                if (formatoBibliografico) {
                    sb.append(alunos[i].getNomeBiblio())
                      .append(" - RA: ").append(alunos[i].getRa())
                      .append(" - Curso: ").append(alunos[i].getCurso())
                      .append(" - Semestre: ").append(alunos[i].getSemestre())
                      .append("\n");
                } else {
                    sb.append(alunos[i].toString()).append("\n");
                    sb.append("-----------------------------\n");
                }
            }
        }

        if (!temAlunos) {
            return "Nenhum aluno cadastrado.";
        }

        return sb.toString();
    }

    /**
     * Salva o vetor de alunos em arquivo binario.
     */
    public void salvar(String nomeArq) throws IOException {
        ArquivoBinario ab = new ArquivoBinario(nomeArq);
        ab.gravarObj(this.alunos);
    }

    /**
     * Carrega um vetor de alunos a partir de arquivo binario, substituindo
     * o conteudo atual. Se o arquivo contiver outro tipo de objeto, lanca
     * IOException com mensagem amigavel.
     */
    public void carregar(String nomeArq) throws IOException, ClassNotFoundException {
        ArquivoBinario ab = new ArquivoBinario(nomeArq);
        Object lido = ab.lerObj();
        if (!(lido instanceof Aluno[])) {
            throw new IOException("Formato de arquivo incompativel: esperado vetor de Aluno.");
        }
        this.alunos = (Aluno[]) lido;
    }
}
