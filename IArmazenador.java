/**
 * @author Kaua Bezerra, Liam Vedovato, Raul Kolaric, Rodrigo Ward 
 * @version 19/03/2026
 */
public interface IArmazenador {
    public boolean inserir(Aluno a);
    
    public boolean remover(String ra);
    
    public void listar();
}