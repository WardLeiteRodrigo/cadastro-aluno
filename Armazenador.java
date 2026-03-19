/**
 * @author Kaua Bezerra, Liam Vedovato, Raul Kolaric, Rodrigo Ward 
 * @version 19/03/2026
 */
public class Armazenador implements IArmazenador {
	// initialize Aluno vector 
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

    public boolean remover(String ra) {
    	// itera sobre o armazenador
    	for (int i = 0; i < arm.length; i++) {
    		// checa se, p/ aquela posição, o aluno existe e se o RA é igual ao RA buscado.
    		if (arm[i] != null && arm[i].getRa().equals(ra)) {
    			// se for, atribui null
    			arm[i] = null;
    			// retorna true
    			return true;
    		}
    	}
    // retorna falso por padrão
    return false;
    }
}
