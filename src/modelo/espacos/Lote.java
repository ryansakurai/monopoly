package modelo.espacos;

import modelo.enums.CorDeLote;
import modelo.enums.NomeDeEspaco;

/**
 * 	@author Caike dos Santos
 * 	@author Guilherme Bortoletto
 * 	@author Marcelo Pirro
 * 	@author Ryan Sakurai
 * 	@author Vinicius Castro
 */
public class Lote extends Propriedade {

    private final CorDeLote cor;
    private final int precoDeConstrucao;
    private final int aluguelSemConstrucao;
    private final int aluguelComConstrucao;
    private boolean temCasa;
    private boolean temHotel;

    /**
     *  @param nome
     *  @param preco
     *  @param cor
     *  @param precoDeConstrucao - preço de construção de casa/hotel
     *  @param aluguelSemConstrucao
     *  @param aluguelComConstrucao
     */
    public Lote(NomeDeEspaco nome, int preco, CorDeLote cor, int precoDeConstrucao, int aluguelSemConstrucao, int aluguelComConstrucao) {
        super(nome, preco);
        
        this.cor = cor;
        
        if(precoDeConstrucao >= 0 && aluguelSemConstrucao >=0 && aluguelComConstrucao >= 0) {
        	this.precoDeConstrucao = precoDeConstrucao;
            this.aluguelSemConstrucao = aluguelSemConstrucao;
            this.aluguelComConstrucao = aluguelComConstrucao;
        }
        else {
        	throw new IllegalArgumentException("Preços não podem ser negativos!");
        }
    }

    public CorDeLote getCor() {
        return cor;
    }

    /**
     *  @return Preço de construção de casa/hotel
     */
    public int getPrecoDeConstrucao() {
        return precoDeConstrucao;
    }

    public boolean temCasa() {
		return temCasa;
	}

	public boolean temHotel() {
		return temHotel;
	}
	
	/**
	 * 	@return Se o lote possui casa ou hotel
	 */
	public boolean temConstrucao() {
		return temCasa() || temHotel();
	}

	public void construirCasa() {
        temCasa = true;
    }

    public void destruirCasa() {
        temCasa = false;
    }
    
    public void construirHotel() {
        temHotel = true;
    }

    public void destruirHotel() {
        temHotel = false;
    }

    /**
     *  @param preco - não vai ser usado
     */
    @Override
    public int calcularAluguel(Integer valor) {
        if( temCasa() || temHotel() )
            return aluguelComConstrucao;
        else
            return aluguelSemConstrucao;
    }
    
    /**
     * 	Nome do lote, sua cor e se ele tem casa ou hotel
     */
    @Override
    public String toString() {
    	String output = String.format( "%s (%s", super.toString(), getCor() );
    	
    	if(temCasa)
    		output += " - com casa";
    	else if(temHotel)
    		output += " - com hotel";
    	
		output += ")";
    	
    	return output;
    }

}
