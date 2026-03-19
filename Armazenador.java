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
        return true;
    }
    
    public void listar() {
        for (int i = 0; i < arm.length; i++) {
            if (arm[i] != null) {
                System.out.printf("%s\n", arm[i].ra);
            }
        }
    }
}
