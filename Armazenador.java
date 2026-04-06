/**
 * @author Kaua Bezerra, Liam Vedovato, Raul Kolaric, Rodrigo Ward 
 * @version 24/03/2026
 */
public class Armazenador implements IArmazenador {
    Aluno[] arm;
    
    Armazenador(int qtde) {
        arm = new Aluno[qtde];
    }

    public boolean inserir(Aluno a) {
        // Verifica se ja existe um aluno com o mesmo RA
        for (int j = 0; j < arm.length; j++) {
            if (arm[j] != null && arm[j].getRa().equals(a.getRa())) {
                return false; // RA duplicado
            }
        }

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
        return ctrl; // Retorna true se inseriu, false se o vetor estiver cheio
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
    
    public boolean atualizar(String ra, Aluno novoAluno) {
        for (int i = 0; i < arm.length; i++) {
            if (arm[i] != null && arm[i].getRa().equals(ra)) {
                arm[i] = novoAluno;
                return true;
            }
        }
        return false;
    }
    
    public void listar(boolean formatoBibliografico) {
        for (int i = 0; i < arm.length; i++) {
            if (arm[i] != null) {
                if (formatoBibliografico) {
                    System.out.println(arm[i].getNomeBiblio() + " - RA: " + arm[i].getRa() + " - Curso: " + arm[i].curso + " - Semestre: " + arm[i].semestre);
                } else {
                    System.out.println(arm[i].toString());
                    System.out.println("-----------------------------");
                }
            }
        }
    }
}