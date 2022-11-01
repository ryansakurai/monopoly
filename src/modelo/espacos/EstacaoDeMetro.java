package modelo.espacos;

import modelo.enums.NomeDeEspaco;

/**
 *  @author Caike dos Santos
 * 	@author Guilherme Bortoletto
 * 	@author Marcelo Pirro
 * 	@author Ryan Sakurai
 * 	@author Vinicius Castro
 */
public class EstacaoDeMetro extends Propriedade {

    /**
     *  @param nome
     *  @param preco - preco de compra da propriedade
     */
    public EstacaoDeMetro(NomeDeEspaco nome, int preco) {
        super(nome, preco);
    }

    /**
     *  @param valor - não vai ser usado
     */
    @Override
    public int calcularAluguel(Integer valor) {
    	//começa em 25 e dobra a cada nova estação comprada pelo jogador
        return 25 * (int) Math.pow(2, this.getDono().getEstacoes().size() - 1);
    }

}
