package modelo.cartas;

import modelo.enums.DescricaoDeCarta;
import modelo.espacos.Espaco;

/**
 * 	Carta que moverá o jogador até o próximo espaço de um certo tipo
 * 	(exemplo: mover o jogador até a Utilidade mais próxima)
 * 
 * 	@author Caike dos Santos
 * 	@author Guilherme Bortoletto
 * 	@author Marcelo Pirro
 * 	@author Ryan Sakurai
 * 	@author Vinicius Castro
 */
public class CartaDeMovimentoEspecial extends CartaDeMovimento {

	private final Class<? extends Espaco> tipoDeEspaco;

	/**
	 * 	@param descricao
	 * 	@param tipoDeEspaco - tipo do espaco a qual o jogador será movido
	 */
	public CartaDeMovimentoEspecial(DescricaoDeCarta descricao, Class<? extends Espaco> tipoDeEspaco) {
		super(descricao);
		this.tipoDeEspaco = tipoDeEspaco;
	}

	/**
	 * 	@return Tipo do espaco a qual o jogador será movido
	 */
	public Class<? extends Espaco> getTipoDeEspaco() {
		return tipoDeEspaco;
	}
	
}
