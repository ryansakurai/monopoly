package modelo.cartas;

import modelo.enums.DescricaoDeCarta;

/**
 * 	Carta que fará com que o jogador seja movido para frente ou pra trás
 * 
 * 	@author Caike dos Santos
 * 	@author Guilherme Bortoletto
 * 	@author Marcelo Pirro
 * 	@author Ryan Sakurai
 * 	@author Vinicius Castro
 */
public class CartaDeMovimentoRelativo extends CartaDeMovimento {
	
	private final int deslocamento;

	/**
	 * 	@param descricao
	 * 	@param deslocamento - quantidade de casas em que o jogador irá ser deslocado (positivo = para frente / negativo = para trás)
	 */
	public CartaDeMovimentoRelativo(DescricaoDeCarta descricao, int deslocamento) {
		super(descricao);

		if(deslocamento == 0)
			throw new IllegalArgumentException("Deslocamento igual 0");

		this.deslocamento = deslocamento;
	}

	/**
	 * 	@return Quantidade de casas em que o jogador irá ser deslocado
	 * 	(positivo = para frente / negativo = para trás)
	 */
	public int getDeslocamento() {
		return deslocamento;
	}

}
