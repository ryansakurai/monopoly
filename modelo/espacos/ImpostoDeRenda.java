package modelo.espacos;

import modelo.entidades.Jogador;
import modelo.enums.NomeDeEspaco;

/**
 *  Espaço que faz o jogador pagar o imposto de renda
 * 
 * 	@author Caike dos Santos
 * 	@author Guilherme Bortoletto
 * 	@author Marcelo Pirro
 * 	@author Ryan Sakurai
 * 	@author Vinicius Castro
 */
public class ImpostoDeRenda extends Espaco {

    private final int valorFixo = 200;

    public ImpostoDeRenda(NomeDeEspaco nome) {
        super(nome);
    }

    /**
     *  Método para se o jogador optar por pagar um valor fixo
     */
    public int getValor() {
        return valorFixo;
    }

    /**
     *  Método para se o jogador optar por pagar 10% de sua fortuna total
     * 
     *  @param j - jogador que irá pagar
     */
    public int getValor(Jogador j) {
    	int fortuna = 0;

        fortuna += j.getSaldo();

        for(Propriedade x : j.getPropriedades()) {
            fortuna += x.getPreco();

            if(x instanceof Lote) {
            	Lote lote = (Lote) x;
            	if(lote.temConstrucao())
            		fortuna += lote.getPrecoDeConstrucao();
            }
        }

        return (int) (fortuna * 0.1);
    }

}
