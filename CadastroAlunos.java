/**
 * Classe que gerencia as operacoes de cadastro de alunos utilizando um armazenador.
 * 
 * @author Kaua Bezerra, Liam Vedovato, Raul Kolaric, Rodrigo Ward 
 * @version 24/03/2026
 */
public class CadastroAlunos {
    IArmazenador arm; 
    
    /**
     * Construtor para objetos da classe CadastroAlunos.
     * 
     * @param qtde A capacidade de alunos a serem cadastrados.
     */
    CadastroAlunos (int qtde) {
        arm = new Armazenador(qtde);
    }
    
    /**
     * Insere um aluno no cadastro.
     * 
     * @param a O aluno a ser inserido.
     * @return true se inserido com sucesso, false caso contrario.
     */
    public boolean inserir(Aluno a) {
        return arm.inserir(a);
    }
    
    /**
     * Remove um aluno do cadastro atraves do RA.
     * 
     * @param ra O registro academico do aluno a ser removido.
     * @return true se removido com sucesso, false caso contrario.
     */
    public boolean remover(String ra) {
        return arm.remover(ra);
    }
    
    /**
     * Atualiza as informacoes de um aluno existente no cadastro.
     * 
     * @param ra O registro academico do aluno a ser atualizado.
     * @param novoAluno O novo aluno com os dados atualizados.
     * @return true se a atualizacao for bem-sucedida, false caso contrario.
     */
    public boolean atualizar(String ra, Aluno novoAluno) {
        return arm.atualizar(ra, novoAluno);
    }
    
    /**
     * Lista os alunos cadastrados.
     * 
     * @param formatoBibliografico Se true, formata os nomes bibliograficamente.
     */
    public void listar(boolean formatoBibliografico) {
        arm.listar(formatoBibliografico);
    }
}