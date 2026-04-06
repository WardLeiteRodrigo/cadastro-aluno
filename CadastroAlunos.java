/**
 * @author Kaua Bezerra, Liam Vedovato, Raul Kolaric, Rodrigo Ward 
 * @version 24/03/2026
 */
public class CadastroAlunos {
    IArmazenador arm; 
    
    CadastroAlunos (int qtde) {
        arm = new Armazenador(qtde);
    }
    
    public boolean inserir(Aluno a) {
        return arm.inserir(a);
    }
    
    public boolean remover(String ra) {
        return arm.remover(ra);
    }
    
    public boolean atualizar(String ra, Aluno novoAluno) {
        return arm.atualizar(ra, novoAluno);
    }
    
    public void listar() {
        arm.listar();
    }
}