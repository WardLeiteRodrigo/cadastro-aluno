package storage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * Utilitario de gravacao e leitura de objetos em arquivo binario, usando
 * ObjectOutputStream / ObjectInputStream. Generico: trabalha com qualquer
 * objeto Serializable. Os erros sao relancados como IOException /
 * ClassNotFoundException para que a camada de UI possa exibir mensagens
 * amigaveis.
 *
 * @author julio (original), adaptada por Kaua Bezerra, Liam Vedovato, Raul Kolaric, Rodrigo Ward
 */
public class ArquivoBinario {

    private final String nomeArq;

    /**
     * @param nomeArq Caminho completo do arquivo a ser usado nas operacoes.
     */
    public ArquivoBinario(String nomeArq) {
        this.nomeArq = nomeArq;
    }

    /**
     * Grava um objeto serializavel em arquivo.
     *
     * @param objeto Objeto a ser gravado (deve implementar Serializable).
     * @throws IOException em caso de falha de I/O.
     */
    public void gravarObj(Object objeto) throws IOException {
        File file = new File(this.nomeArq);
        try (ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream(file))) {
            output.writeObject(objeto);
        }
    }

    /**
     * Le um objeto serializavel previamente gravado em arquivo.
     *
     * @return O objeto lido (cabe ao chamador fazer o cast).
     * @throws IOException            em caso de falha de I/O.
     * @throws ClassNotFoundException se a classe do objeto nao for encontrada.
     */
    public Object lerObj() throws IOException, ClassNotFoundException {
        File file = new File(this.nomeArq);
        try (ObjectInputStream input = new ObjectInputStream(new FileInputStream(file))) {
            return input.readObject();
        }
    }
}
