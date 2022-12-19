package modelo.entidades;

import modelo.espacos.Lote;
import modelo.espacos.Propriedade;

/**
 * 	Controla as operações monetárias do jogo
 * 
 * 	@author Caike dos Santos
 * 	@author Guilherme Bortoletto
 * 	@author Marcelo Pirro
 * 	@author Ryan Sakurai
 * 	@author Vinicius Castro
 */
public class Banco {
	
	/**
	 * 	Dá dinheiro a um jogador
	 */
	public void pagar(Jogador j, int valor) {
		if(valor <= 0)
			throw new IllegalArgumentException("Valor menor ou igual a 0");
			
		j.aumentarSaldo(valor);	
	}
	
	/**
	 * 	Tira dinheiro de um jogador e retira sua fortuna em caso de falência
	 */
	public void receber(Jogador j, int valor) {
		if(valor <= 0)
			throw new IllegalArgumentException("Valor menor ou igual a 0");
		
		if(j.getSaldo() >= valor) {
			j.diminuirSaldo(valor);
		}
		else {
			j.diminuirSaldo( j.getSaldo() );

			while( !j.getPropriedades().isEmpty() ) {
				Propriedade temp = j.removerPropriedade(0);
				temp.removerDono();

				if(temp instanceof Lote) {
					Lote tempLote = (Lote) temp;
					tempLote.destruirCasa();
					tempLote.destruirHotel();
				}
			}
			
			j.falir();
		}
	}

	/**
	 * 	Transfere dinheiro de um jogador a outro e, em caso de falência, o resto de sua fortuna também
	 * 
	 * 	@param destino - jogador que receberá o dinheiro
	 * 	@param origem - jogador que dará o dinheiro
	 * 	@param valor
	 */
	public void transferir(Jogador destino, Jogador origem, int valor) {
		if(valor <= 0)
			throw new IllegalArgumentException("Valor menor ou igual a 0");
		
		if(origem.getSaldo() >= valor) {
			origem.diminuirSaldo(valor);
			destino.aumentarSaldo(valor);
		}
		else {
			destino.aumentarSaldo( origem.getSaldo() );
			origem.diminuirSaldo( origem.getSaldo() );

			while( !origem.getPropriedades().isEmpty() ) {
				Propriedade temp = origem.removerPropriedade(0);
				destino.adicionarPropriedade(temp);
				temp.setDono(destino);

				if(temp instanceof Lote) {
					Lote tempLote = (Lote) temp;
					tempLote.destruirCasa();
					tempLote.destruirHotel();
				}
			}

			origem.falir();
		}
	}
	
	/**
	 * 	@param prop - propriedade a ser vendida
	 * 	@param comprador
	 * 	@param vendedor
	 * 	@param oferta - valor por qual o comprador irá comprar a propriedade
	 */
	public void venderPropriedade(Propriedade prop, Jogador comprador, Jogador vendedor, int oferta) {
		transferir(vendedor, comprador, oferta);
		vendedor.removerPropriedade(prop);
		comprador.adicionarPropriedade(prop);
		prop.setDono(comprador);
    	
    	if(prop instanceof Lote) {
    		Lote lote = (Lote) prop;
    		lote.destruirCasa();
    		lote.destruirHotel();
    	}
	}
	
	/**
	 * 	@param prop - propriedade a ser vendida
	 * 	@param comprador
	 */
	public void venderPropriedade(Propriedade prop, Jogador comprador) {
		receber( comprador, prop.getPreco() );
		comprador.adicionarPropriedade(prop);
		prop.setDono(comprador);
	}

}
