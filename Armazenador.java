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
        int i = 0;
        int tam = arm.length;
        boolean ctrl = false;
        
        while(ctrl != true && i < tam) {
            
            if(arm[i] == null) {
                arm[i] = a;
                ctrl = true;
            }
            
            i++;
        }
        
        
        return true;
    }

    public boolean remover (String ra) {
        // TODO
        return true;
    }
}