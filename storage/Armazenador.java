/**
 * Implementa em memória a persistência e manutenção de alunos usando
 * vetores (Arrays) definidos com capacidade limitante.
 * 
 * @author Kaua Bezerra, Liam Vedovato, Raul Kolaric, Rodrigo Ward 
 * @version 24/03/2026
 */
public class Armazenador implements IArmazenador {
    Aluno[] arm;
    
    /**
     * O Construtor constrói a limitação base de memória e declara a matriz de dados.
     * @param qtde Máximo de aberturas de registro.
     */
    Armazenador(int qtde) {
        arm = new Aluno[qtde];
    }

    /**
     * Tenta introduzir o aluno num espaço do array de indicação igual a `null` (vazio).
     */
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
        return ctrl;
    }

    /**
     * Busca um estudante por seu registro (RA) e anula (libera memória) a sua poição, caso encontre.
     */
    public boolean remover(String ra) {
        for (int i = 0; i < arm.length; i++) {
            if (arm[i] != null && arm[i].getRa().equals(ra)) {
                arm[i] = null;
                return true;
            }
        }
        return false;
    }
    
    /**
     * Monta uma representação visual consolidada (string única) com todos os contatos preenchidos.
     */
    public String listar(boolean formatoBiblio) {
        StringBuilder sb = new StringBuilder();
        boolean vazio = true;
        for (int i = 0; i < arm.length; i++) {
            if (arm[i] != null) {
                vazio = false;
                if(formatoBiblio) {
                    sb.append("Nome: ").append(arm[i].getNomeBiblio()).append("\n");
                    sb.append("RA: ").append(arm[i].getRa()).append("\n");
                    sb.append("Curso: ").append(arm[i].curso).append(" (Semestre: ").append(arm[i].semestre).append(")\n\n");
                } else {
                    sb.append(arm[i].toString()).append("\n\n");
                }
            }
        }
        if (vazio) {
            return "Nenhum aluno cadastrado.";
        }
        return sb.toString();
    }

    /**
     * Lê todos os cadastros validando se já exíste tal identificador para impedir duas
     * pessoas sob o mesmo acesso final.
     */
    public boolean existeRa(String ra) {
        for (int i = 0; i < arm.length; i++) {
            if (arm[i] != null && arm[i].getRa().equals(ra)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Refaz o objeto no mesmo referencial mantendo o aluno caso decida mudar suas infs. (como a idade).
     */
    public boolean atualizar(String ra, Aluno a) {
        for (int i = 0; i < arm.length; i++) {
            if (arm[i] != null && arm[i].getRa().equals(ra)) {
                arm[i] = a;
                return true;
            }
        }
        return false;
    }

    /**
     * Verifica proativamente se nenhum ponteiro nulo restou no vetor inteiro.
     */
    public boolean isCheio() {
        for (int i = 0; i < arm.length; i++) {
            if (arm[i] == null) return false;
        }
        return true;
    }
}