package modelo.espacos;

import modelo.enums.NomeDeEspaco;

/**
 *  @author Caike dos Santos
 * 	@author Guilherme Bortoletto
 * 	@author Marcelo Pirro
 * 	@author Ryan Sakurai
 * 	@author Vinicius Castro
 */
public class PontoDePartida extends Espaco {

    private final int valor = 200;

    public PontoDePartida(NomeDeEspaco nome) {
        super(nome);
    }

    /**
     *  @return Valor que o jogador irá receber quando passar pelo espaço
     */
    public int getValor() {
        return valor;
    }
    
}
