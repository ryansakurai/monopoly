package modelo.cartas;

import modelo.enums.DescricaoDeCarta;

/**
 *  Carta que causará movimentação do jogador pelo espaço
 * 
 * 	@author Caike dos Santos
 * 	@author Guilherme Bortoletto
 * 	@author Marcelo Pirro
 * 	@author Ryan Sakurai
 * 	@author Vinicius Castro
 */
public abstract class CartaDeMovimento extends Carta {

	public CartaDeMovimento(DescricaoDeCarta descricao) {
        super(descricao);
    }

}
