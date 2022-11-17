package modelo.cartas;

import modelo.enums.DescricaoDeCarta;
import modelo.enums.NomeDeEspaco;

/**
 * 	Carta que fará com que o jogador seja movido para um espaço específico do tabuleiro
 * 
 * 	@author Caike dos Santos
 * 	@author Guilherme Bortoletto
 * 	@author Marcelo Pirro
 * 	@author Ryan Sakurai
 * 	@author Vinicius Castro
 */
public class CartaDeMovimentoAbsoluto extends CartaDeMovimento {
	
	private final NomeDeEspaco destino;

	public CartaDeMovimentoAbsoluto(DescricaoDeCarta descricao, NomeDeEspaco destino) {
		super(descricao);
		this.destino = destino;
	}

	public NomeDeEspaco getDestino() {
		return destino;
	}

}
