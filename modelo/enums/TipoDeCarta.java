package modelo.enums;

/**
 *  @author Caike dos Santos
 * 	@author Guilherme Bortoletto
 * 	@author Marcelo Pirro
 * 	@author Ryan Sakurai
 * 	@author Vinicius Castro
 */
public enum TipoDeCarta {

    COFRE("Cofre"),
    SORTE("Sorte");
	

    private final String tipo;

    private TipoDeCarta(String tipo) {
        this.tipo = tipo;
    }

    @Override
    public String toString() {
        return tipo;
    }

}
