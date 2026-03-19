/**
 * @author Kaua Bezerra, Liam Vedovato, Raul Kolaric, Rodrigo Ward 
 * @version 19/03/2026
 */
public class CadastroAlunos {
    IArmazenador arm; 
    
    CadastroAlunos (int qtde) {
        arm = new Armazenador(qtde);
    }
    
    public void inserir(Aluno a) {
        arm.inserir(a);
    }
    
    public void remover(String ra) {
        arm.remover(ra);
    }
    
    public void listar() {
        arm.listar();
    }
}