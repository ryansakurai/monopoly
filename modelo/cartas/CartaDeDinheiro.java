package modelo.cartas;

import modelo.enums.DescricaoDeCarta;

/**
 *  Carta que fará o jogador receber ou perder dinheiro
 * 
 * 	@author Caike dos Santos
 * 	@author Guilherme Bortoletto
 * 	@author Marcelo Pirro
 * 	@author Ryan Sakurai
 * 	@author Vinicius Castro
 */
public class CartaDeDinheiro extends Carta {

    private final int valor;

    /**
     * 	@param descricao
     *  @param valor - valor a ser recebido ou pago pelo jogador
     */
    public CartaDeDinheiro(DescricaoDeCarta descricao, int valor) {
        super(descricao);
        this.valor = valor;
        	
    }

    /**
     *  @return Valor a ser recebido ou pago pelo jogador
     */
    public int getValor() {
        return valor;
    }

}
