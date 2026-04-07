/**
 * Classe responsável por gerir as interações e atuar como conector entre a interface
 * de entrada da aplicação (App) e o gerenciador lógico e funcional dos dados (IArmazenador).
 * 
 * @author Kaua Bezerra, Liam Vedovato, Raul Kolaric, Rodrigo Ward 
 * @version 24/03/2026
 */
public class CadastroAlunos {
    IArmazenador arm; 
    
    /**
     * Inicia a base em memória.
     * @param qtde Número de alunos previstos no armazenamento.
     */
    CadastroAlunos (int qtde) {
        arm = new Armazenador(qtde);
    }
    
    /**
     * Pede ao armazenador para guardar um novo aluno.
     * @param a O aluno à ser cadastrado.
     * @return booleano refletindo sucesso.
     */
    public boolean inserir(Aluno a) {
        return arm.inserir(a);
    }
    
    /**
     * Exclui os detalhes salvos sob o RA.
     * @param ra Matrícula.
     * @return booleano refletindo sucesso.
     */
    public boolean remover(String ra) {
        return arm.remover(ra);
    }
    
    /**
     * Fornece o resumo estruturado e total dos participantes do array de alunos.
     * @param formatoBiblio Variável definidora se haverá redução no nome pro padrão bibliográfico.
     * @return O conteúdo visual para a interface renderizar os contatos existentes.
     */
    public String listar(boolean formatoBiblio) {
        return arm.listar(formatoBiblio);
    }

    /**
     * Determina se a identificação já está atrelada e retém alunos duplos.
     * @param ra Identificador (Matrícula).
     * @return Falso se o local for livre.
     */
    public boolean existeRa(String ra) {
        return arm.existeRa(ra);
    }

    /**
     * Atualiza todas as métricas associadas com esse RA.
     * @param ra O registro para sobrescrever.
     * @param a Novo grupo de traços e características contidos numa classe Aluno.
     * @return Retorna verdadeiro se o local existia e foi modificado.
     */
    public boolean atualizar(String ra, Aluno a) {
        return arm.atualizar(ra, a);
    }

    /**
     * Indica transbordamento ou impossibilidade de entrada.
     * @return Verdadeiro se limite alcançado.
     */
    public boolean isCheio() {
        return arm.isCheio();
    }
}