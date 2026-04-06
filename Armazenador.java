/**
 * Classe que implementa a interface IArmazenador para armazenar alunos em um vetor.
 * 
 * @author Kaua Bezerra, Liam Vedovato, Raul Kolaric, Rodrigo Ward 
 * @version 24/03/2026
 */
public class Armazenador implements IArmazenador {
    Aluno[] arm;
    
    /**
     * Construtor para objetos da classe Armazenador.
     * 
     * @param qtde A capacidade maxima de alunos no vetor.
     */
    Armazenador(int qtde) {
        arm = new Aluno[qtde];
    }

    /**
     * Insere um aluno no vetor de armazenamento, caso o RA nao seja duplicado e haja espaco.
     * 
     * @param a O aluno a ser inserido.
     * @return true se a insercao for bem-sucedida, false se o RA ja existir ou o vetor estiver cheio.
     */
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

    /**
     * Remove um aluno do armazenamento buscando pelo RA.
     * 
     * @param ra O RA do aluno a ser removido.
     * @return true se o aluno for encontrado e removido, false caso contrario.
     */
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
    
    /**
     * Atualiza um aluno no armazenamento com base no RA.
     * 
     * @param ra O RA do aluno a ser substituido.
     * @param novoAluno O novo objeto Aluno com as informacoes atualizadas.
     * @return true se o aluno for encontrado e atualizado, false caso contrario.
     */
    public boolean atualizar(String ra, Aluno novoAluno) {
        for (int i = 0; i < arm.length; i++) {
            if (arm[i] != null && arm[i].getRa().equals(ra)) {
                arm[i] = novoAluno;
                return true;
            }
        }
        return false;
    }
    
    /**
     * Lista todos os alunos armazenados no vetor.
     * 
     * @param formatoBibliografico Indica se os nomes devem ser impressos no formato bibliografico.
     */
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