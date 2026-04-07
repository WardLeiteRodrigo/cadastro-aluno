package model;

/**
 * Representa o nome de uma pessoa utilizando a classe utilitária Texto.
 * Fornece métodos para obter nomes padronizados e em formato bibliográfico.
 * 
 * @author Kaua Bezerra, Liam Vedovato, Raul Kolaric, Rodrigo Ward 
 * @version 24/03/2026
 */
public class NomePessoa {
    private Texto nome;

    /**
     * Construtor da classe NomePessoa.
     * @param nome Nome completo a ser armazenado.
     */
    public NomePessoa(String nome) {
        setNome(nome);
    }

    /**
     * Obtém o nome formatado (espaços residuais retirados pela classe Texto).
     * @return String contendo o nome realocado.
     */
    public String getNome() {
        return this.nome.getTxt();
    }

    /**
     * Configura o nome da pessoa encapsulando-o na classe Texto.
     * @param nome Nome propriamente dito.
     */
    protected void setNome(String nome) {
        this.nome = new Texto(nome);
    }

    /**
     * Retorna o número de palavras que formam o nome.
     * @return Total de palavras do nome.
     */
    public int getQtdePalavras() {
        return this.nome.getQtdePalavras();
    }
    
    /**
     * Traz o nome lido de trás para frente (inverso).
     * @return String invertida componente por componente.
     */
    public String getNomeInvertido() {
        return this.nome.inverterTexto();
    }

    /**
     * Gera o identificador num formato bibliográfico (ex: Silva, J. ).
     * Ignora preposições como "da", "de" na abreviação das iniciais.
     * @return Nome estruturado como bibliografia.
     */
    public String getNomeBiblio() {
        String vts[] = this.nome.getTxt().split(" ");
        int qtd = vts.length;

        String sBib = vts[qtd-1] + ", "; 
        for (int i=0; i < (qtd-1); i++){
            String pal = vts[i].toLowerCase(); 
            if(!verificaStr(pal)) { 
                sBib = sBib + vts[i].toUpperCase().charAt(0) + ". ";
            }
        }
        return sBib;
    }

    /**
     * Verifica se uma string corresponde a uma preposição comum não capitalizável
     * em bibliografias.
     * @param s A partícula do nome avaliada.
     * @return Verdadeiro se for conector; falso do contrário.
     */
    private boolean verificaStr(String s) {
        final String sRet[] = {"da", "de", "do", "di", "das", "dos", "e",};

        for (int i = 0; i < sRet.length; i++){
            if(sRet[i].equals(s)){
                return true;
            }
        }
        return false;
    }

    /**
     * Retorna a string do nome internamente estocada.
     * @return O dado completo em formato original aparado.
     */
    public String toString() {
        return this.nome.toString();
    }
}
