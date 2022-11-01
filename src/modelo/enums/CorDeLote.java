package modelo.enums;

/**
 *  @author Caike dos Santos
 * 	@author Guilherme Bortoletto
 * 	@author Marcelo Pirro
 * 	@author Ryan Sakurai
 * 	@author Vinicius Castro
 */
public enum CorDeLote {

    MARROM("Marrom"),
    AZUL_CLARO("Azul claro"),
    ROSA("Rosa"),
    LARANJA("Laranja"),
    VERMELHO("Vermelho"),
    AMARELO("Amarelo"),
    VERDE("Verde"),
    AZUL("Azul");

	
    private final String cor;

    private CorDeLote(String cor) {
        this.cor = cor;
    }

    @Override
    public String toString() {
        return cor;
    }

}
