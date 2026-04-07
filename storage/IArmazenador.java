package storage;

import model.Aluno;

/**
 * Interface que define os mandatos e contratos de armazenamento e persistência
 * na simulação do banco de dados (array de alunos).
 * 
 * @author Kaua Bezerra, Liam Vedovato, Raul Kolaric, Rodrigo Ward
 * @version 24/03/2026
 */
public interface IArmazenador {
    /**
     * Insere um aluno caso haja espaço disponível.
     * 
     * @param a O aluno que será inserido.
     * @return true se inserido, false caso não exista espaço.
     */
    public boolean inserir(Aluno a);

    /**
     * Remove um aluno dado seu registro do aluno (RA).
     * 
     * @param ra String contendo a matrícula que será removida.
     * @return true se foi encontrado o RA e removido, false se não existir.
     */
    public boolean remover(String ra);

    /**
     * Retorna uma String com todos os alunos contidos no banco formatados em
     * listagem.
     * 
     * @param formatoBiblio Booleano para usar a Formatação Bibliográfica em vez do
     *                      modelo padrão.
     * @return Uma concatenação em linha unificada com os detalhes de todos os
     *         alunos.
     */
    public String listar(boolean formatoBiblio);

    /**
     * Consulta auxiliar para avaliar duplicatas na base de dados.
     * 
     * @param ra Número de matrícula em texto.
     * @return true se o ra já estiver alocado dentro de algum registro, false do
     *         contrário.
     */
    public boolean existeRa(String ra);

    /**
     * Adiciona funcionalidade estrita de CRUD para substituir (Update) um Aluno
     * pelo seu RA.
     * 
     * @param ra Matrícula do alvo a ser alterado.
     * @param a  Novo objeto Aluno que substituirá o antigo.
     * @return true se a operação teve sucesso.
     */
    public boolean atualizar(String ra, Aluno a);

    /**
     * Analisa se o vetor atingiu o máximo de posições sem dar OverflowException.
     * 
     * @return true se completamente cheio.
     */
    public boolean isCheio();
}
