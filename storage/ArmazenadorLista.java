package storage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import model.Aluno;

/**
 * Implementacao de {@link IArmazenador} usando {@link ArrayList} de
 * {@link Aluno}. Diferente de {@link Armazenador}, esta versao tem
 * capacidade elastica (cresce indefinidamente) e nunca lanca
 * {@link CadastroCheioException}.
 *
 * @author Kaua Bezerra, Liam Vedovato, Raul Kolaric, Rodrigo Ward
 * @version 1.0 2026/04/27
 */
public class ArmazenadorLista implements IArmazenador {

    private ArrayList<Aluno> alunos;

    public ArmazenadorLista() {
        this.alunos = new ArrayList<Aluno>();
    }

    public void inserir(Aluno a) throws RaDuplicadoException, CadastroCheioException {
        if (existe(a.getRa())) {
            throw new RaDuplicadoException();
        }
        this.alunos.add(a);
    }

    public void remover(String ra) throws RaInexistenteException {
        Iterator<Aluno> it = this.alunos.iterator();
        while (it.hasNext()) {
            Aluno a = it.next();
            if (a.getRa().equals(ra)) {
                it.remove();
                return;
            }
        }
        throw new RaInexistenteException();
    }

    public void atualizar(String ra, Aluno novoAluno) throws RaInexistenteException {
        for (int i = 0; i < this.alunos.size(); i++) {
            if (this.alunos.get(i).getRa().equals(ra)) {
                this.alunos.set(i, novoAluno);
                return;
            }
        }
        throw new RaInexistenteException();
    }

    public boolean existe(String ra) {
        if (ra == null) return false;
        String alvo = ra.trim();
        for (Aluno a : this.alunos) {
            if (a.getRa().equals(alvo)) {
                return true;
            }
        }
        return false;
    }

    public String listar(boolean formatoBibliografico) {
        if (this.alunos.isEmpty()) {
            return "Nenhum aluno cadastrado.";
        }

        StringBuilder sb = new StringBuilder();
        for (Aluno a : this.alunos) {
            if (formatoBibliografico) {
                sb.append(a.getNomeBiblio())
                  .append(" - RA: ").append(a.getRa())
                  .append(" - Curso: ").append(a.getCurso())
                  .append(" - Semestre: ").append(a.getSemestre())
                  .append("\n");
            } else {
                sb.append(a.toString()).append("\n");
                sb.append("-----------------------------\n");
            }
        }
        return sb.toString();
    }

    /**
     * Salva a lista de alunos em arquivo binario.
     */
    public void salvar(String nomeArq) throws IOException {
        ArquivoBinario ab = new ArquivoBinario(nomeArq);
        ab.gravarObj(this.alunos);
    }

    /**
     * Carrega uma lista de alunos a partir de arquivo binario, substituindo
     * o conteudo atual. Se o arquivo contiver outro tipo de objeto, lanca
     * IOException com mensagem amigavel.
     */
    @SuppressWarnings("unchecked")
    public void carregar(String nomeArq) throws IOException, ClassNotFoundException {
        ArquivoBinario ab = new ArquivoBinario(nomeArq);
        Object lido = ab.lerObj();
        if (!(lido instanceof ArrayList)) {
            throw new IOException("Formato de arquivo incompativel: esperado lista de Aluno.");
        }
        this.alunos = (ArrayList<Aluno>) lido;
    }
}
