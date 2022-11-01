package modelo.espacos;

import modelo.enums.NomeDeEspaco;

/**
 * 	Espaço para onde o jogador vai quando ele é preso
 * 
 * 	@author Caike dos Santos
 * 	@author Guilherme Bortoletto
 * 	@author Marcelo Pirro
 * 	@author Ryan Sakurai
 * 	@author Vinicius Castro
 */
public class Cadeia extends Espaco {

	private final int fianca = 50;

	public Cadeia(NomeDeEspaco nome) {
		super(nome);
	}

	public int getFianca() {
		return fianca;
	}

}
