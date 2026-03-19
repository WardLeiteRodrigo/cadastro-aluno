/**
 * @author Kaua Bezerra, Liam Vedovato, Raul Kolaric, Rodrigo Ward 
 * @version 19/03/2026
 */
public class Armazenador implements IArmazenador {
    Aluno[] arm;
    
    Armazenador(int qtde) {
        arm = new Aluno[qtde];
    }

    public boolean inserir(Aluno a) {
        // TODO
        return true;
    }

    public boolean remover (String ra) {
        // TODO
        return true;
    }
}