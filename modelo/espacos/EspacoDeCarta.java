package modelo.espacos;

import modelo.enums.NomeDeEspaco;
import modelo.enums.TipoDeCarta;

/**
 *  Espaço que fará o jogador retirar uma carta
 * 
 * 	@author Caike dos Santos
 * 	@author Guilherme Bortoletto
 * 	@author Marcelo Pirro
 * 	@author Ryan Sakurai
 * 	@author Vinicius Castro
 */
public class EspacoDeCarta extends Espaco {

    private final TipoDeCarta tipo;

    /**
     *  @param nome
     *  @param tipo - tipo da carta que será retirada
     */
    public EspacoDeCarta(NomeDeEspaco nome, TipoDeCarta tipo) {
        super(nome);
        this.tipo = tipo;
    }

    /**
     *  @return Tipo da carta que será retirada
     */
    public TipoDeCarta getTipo() {
        return tipo;
    }

}
