/**
 * Interface que define os metodos para armazenamento de alunos.
 * 
 * @author Kaua Bezerra, Liam Vedovato, Raul Kolaric, Rodrigo Ward 
 * @version 24/03/2026
 */
public interface IArmazenador {
    /**
     * Insere um novo aluno no armazenamento.
     * 
     * @param a O aluno a ser inserido.
     * @return true se inserido com sucesso, false caso contrario.
     */
    public boolean inserir(Aluno a);
    
    /**
     * Remove um aluno do armazenamento pelo RA.
     * 
     * @param ra O registro academico do aluno a ser removido.
     * @return true se removido com sucesso, false caso contrario.
     */
    public boolean remover(String ra);
    
    /**
     * Atualiza os dados de um aluno no armazenamento.
     * 
     * @param ra O registro academico do aluno a ser atualizado.
     * @param novoAluno O novo objeto Aluno contendo os dados atualizados.
     * @return true se atualizado com sucesso, false caso contrario.
     */
    public boolean atualizar(String ra, Aluno novoAluno);
    
    /**
     * Lista todos os alunos armazenados.
     * 
     * @param formatoBibliografico Se true, lista os nomes em formato bibliografico.
     */
    public void listar(boolean formatoBibliografico);
}