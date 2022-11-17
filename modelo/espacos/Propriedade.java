package modelo.espacos;

import modelo.entidades.Jogador;
import modelo.enums.NomeDeEspaco;

/**
 *  Espaço que pode ser comprado pelos jogadores
 * 
 * 	@author Caike dos Santos
 * 	@author Guilherme Bortoletto
 * 	@author Marcelo Pirro
 * 	@author Ryan Sakurai
 * 	@author Vinicius Castro
 */
public abstract class Propriedade extends Espaco {

    private final int preco;
    private Jogador dono;

    public Propriedade(NomeDeEspaco nome, int preco) {
        super(nome);
        
        if(preco >= 0)
        	this.preco = preco;
        else
        	throw new IllegalArgumentException("Preços não podem ser negativos!");
    }

    public int getPreco() {
        return preco;
    }

    public Jogador getDono() {
        return dono;
    }

    public void setDono(Jogador dono) {
        this.dono = dono;
    }

    public void removerDono() {
        this.dono = null;
    }

    /**
     * 	@return Valor do aluguel a ser pago
     */
    public abstract int calcularAluguel(Integer valor);

}
