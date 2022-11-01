package modelo.entidades;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;
import modelo.cartas.*;
import modelo.enums.*;
import modelo.espacos.*;

public class Jogo {
    
	private final String[] jogadores;
	private final Scanner scanner;
	
    private Tabuleiro tabuleiro;
    private ArrayList<Jogador> jogadoresRestantes;
    private Dado[] dados;
    private Banco banco;
    private DeckDeCartas deckCofre;
    private DeckDeCartas deckSorte;
    private Jogador jogadorDaVez;
    
    
    /**
     * 	@param jogadores - nome dos jogadores
     * 	@param scanner - scanner a ser usado na interação com o usuário
     */
    public Jogo(String[] jogadores, Scanner scanner) {
    	if(jogadores.length >= 2)
    		this.jogadores = jogadores;
    	else
        	throw new IllegalArgumentException("O jogo precisa ter pelo menos 2 jogadores!");
    	this.scanner = scanner;
    }
    
    
    /**
     * 	Inicia uma nova partida
     */
    public void iniciar() {
    	inicializarAtributos();
    	System.out.println();
    	
    	for(Jogador x: jogadoresRestantes)
    		tabuleiro.avancarPara(x, NomeDeEspaco.PONTO_PARTIDA);
        System.out.printf( "O jogo começou! Todos os jogadores estão em: %s\n", NomeDeEspaco.PONTO_PARTIDA );
        System.out.println();
        
        for(int i=0; i<jogadoresRestantes.size(); ) {
            jogadorDaVez = jogadoresRestantes.get(i);

            printarStatus(jogadorDaVez);
            System.out.println();
            
            /* jogador ganhou */
            if( validarVitoria() ) {
            	System.out.print( "Você venceu o jogo, parabéns!!!\n" );
            	return;
            }

            /* jogador preso */
            if( jogadorDaVez.isPreso() ) {
                if( tentarSoltura() ) {
                	System.out.println();
                	System.out.print( "Você foi solto!\n" );
                	System.out.println();
                }
                else {
                	System.out.println();
                	System.out.print( "Você não foi solto\n" );
                	System.out.println();
                	
                	if( jogadorDaVez.faliu() ) {
                		declararFalencia();
                		continue;
                	}
                }     	
            }
            
            if( !jogadorDaVez.isPreso() ) {
            	String comando = "[1] Lançar dados";
                lerOpcao(comando, 1);
                System.out.println();
                
            	int resultado1 = jogadorDaVez.lancarDado(dados[0]);
                int resultado2 = jogadorDaVez.lancarDado(dados[1]);
                System.out.printf( "Os resultados foram: %d e %d\n", resultado1, resultado2 );
                System.out.println();
            
                /* deu dupla */
                if (resultado1 == resultado2) {
                    if(jogadorDaVez.deuTresDuplas()) {
                    	System.out.print( "Você tirou três duplas seguidas e foi preso\n" );
                        jogadorDaVez.prender();
                        tabuleiro.avancarPara(jogadorDaVez, NomeDeEspaco.CADEIA);
                    }
                    else {
                        System.out.print( "Você tirou uma dupla e jogará de novo na próxima rodada!\n" );
                        System.out.println();
                        
                        i--;	//anula o i++ no final do loop e volta para o jogador atual
                    }
                }
                
                if( !jogadorDaVez.isPreso() ) {	//se o jogador for preso, a rodada termina ali
                	/* move jogador */
                	if ( tabuleiro.avancar(jogadorDaVez, resultado1+resultado2) ) {
                		voltaNoTabuleiro();
                		System.out.println();
                    }
                    System.out.printf( "Você se moveu para: %s\n", jogadorDaVez.getPosicao() );
                    System.out.println();
                    
                    while(true) {
                    	/* interage com espaço em que caiu */
                        if (jogadorDaVez.getPosicao() instanceof EspacoDeCarta) {
                        	if( interagirComEspacoDeCarta() ) {
                        		System.out.println();
                        		continue;
                        	}
                        }
                        else if (jogadorDaVez.getPosicao() instanceof ImpostoDeRenda) {
                        	interagirComImpostoDeRenda();
                        }
                        else if (jogadorDaVez.getPosicao() instanceof TaxaDeRiqueza) {
                        	interagirComTaxaDeRiqueza();
                        }
                        else if (jogadorDaVez.getPosicao() instanceof VaParaCadeia) {
                        	interagirComVaParaCadeia();
                        }
                        else if (jogadorDaVez.getPosicao() instanceof Propriedade) {
                        	interagirComPropriedade(resultado1+resultado2);
                        }
                        else {
                        	break;
                        }
                        
                        System.out.println();
                        break;
                    }
                }
            }
            
            /* jogador ganhou */
            if( !jogadorDaVez.faliu() && validarVitoria() ) {
            	System.out.println();
            	System.out.print( "Você venceu, parabéns!!!\n" );
            	return;
            }

            /* iteração circular */
            if( !jogadorDaVez.faliu() )	//jogadores falidos são removidos da lista
            	i = ++i % jogadoresRestantes.size();
            System.out.print("-----------------------------------------\n");
            System.out.println();
        }
    }
    
    
    /**
     * 	Inicializa os atributos e decide a ordem dos jogadores
     */
    private void inicializarAtributos() {
    	tabuleiro = new Tabuleiro();
    	jogadoresRestantes = new ArrayList<>();
    	dados = new Dado[2];
        dados[0] = new Dado();
        dados[1] = new Dado();
        banco = new Banco();
        deckCofre = new DeckDeCartas(TipoDeCarta.COFRE);
        deckSorte = new DeckDeCartas(TipoDeCarta.SORTE);

        ArrayList<Jogador> temp = new ArrayList<>();
        for(String x : jogadores)
        	temp.add( new Jogador(x) );

        System.out.print( "Hora de decidir a ordem de jogada\n" );
        System.out.println();

        /* joga dados para decidir ordem de jogada */
        ArrayList<Integer> resultados = new ArrayList<>();
        for (int i=0; i < temp.size(); i++){
            Jogador jogador = temp.get(i);
            String comando = String.format( "Jogador: %s\n", jogador );
            comando += "[1] Lançar dados";
            lerOpcao(comando, 1);
            System.out.println();
            
            int result1 = jogador.lancarDado(dados[0]);
            int result2 = jogador.lancarDado(dados[1]);
            System.out.printf( "Os resultados foram: %d e %d\n", result1, result2 );

            jogador.limparHistoricoDeDuplas();	//duplas ainda não contam
            
            if(!resultados.contains(result1+result2)) {
                resultados.add(result1+result2);
            } 
            else {	//ordenação impossibilita a ordenação completa
            	System.out.println();
                System.out.print( "Outro jogador já tirou o mesmo resultado, jogue novamente!\n" );
                i--;
            }
            
            System.out.println();
        }

        ArrayList<Integer> resultadosOrdenados = new ArrayList<>(resultados);
        resultadosOrdenados.sort(Comparator.reverseOrder());
        
        /* preenche o atributo com os jogadores ordenados pelo resultado dos dados */
        for (Integer x: resultadosOrdenados){
            jogadoresRestantes.add( temp.get( resultados.indexOf(x) ) );
        }
        
        System.out.printf( "Ordem: %s\n", jogadoresRestantes );
        System.out.println();
        
        System.out.print("-----------------------------------------\n");
    }
    
    
    /**
     * 	@return Se o jogador da vez é o vencedor
     */
    private boolean validarVitoria() {
    	/*
    	 * 	Condições de vitória:
    	 * 	- único restante na partida
    	 * 	- 2 monopólios
    	 * 	- 1 monopólio com hotel
    	 */
    	
    	if(jogadoresRestantes.size() == 1 || jogadorDaVez.getQtDeMonopolios() >= 2) {
    		return true;
    	}
    	else {
    		for( Lote x: jogadorDaVez.getLotes() )
    			if( x.temHotel() )
    				return true;
    		
    		return false;
    	}
    }
    
    
    /**
     * 	@return Se o jogador da vez conseguiu sair da cadeia
     */
    private boolean tentarSoltura() {
    	System.out.print( "Você está preso\n" );
    	System.out.println();
    	
    	int fianca = ((Cadeia) jogadorDaVez.getPosicao()).getFianca();
    	
    	//1ª rodada preso: pode pagar fiança ou tentar tirar dupla
    	//2ª rodada preso: pode pagar fiança ou tentar tirar dupla
    	//3ª rodada preso: deverá tentar tirar dupla
    	//4ª rodada preso: deverá pagar fiança (se não for possível, o jogador falirá)
    	
    	if(jogadorDaVez.getQtDeRodadasPreso() < 3) {
            if (jogadorDaVez.getQtDeRodadasPreso() < 2) {
            	while(true) {
            		String comando = String.format( "[1] Pagar fiança de $%d\n", fianca );
                    comando += "[2] Lançar dados";
                    int op = lerOpcao(comando, 2);
                    System.out.println();
                    
                    if (op == 1) {
                        if (jogadorDaVez.getSaldo() >= fianca){
                            banco.receber(jogadorDaVez, fianca);
                            System.out.printf( "Você pagou a fiança com sucesso (saldo atualizado: $%d)\n", jogadorDaVez.getSaldo() );
                            jogadorDaVez.soltar();
                            return true;
                        }
                        else {
                            System.out.print( "Saldo insuficiente para pagar a fiança\n" );
                            System.out.println();
                        }
                    }
                    else {
                    	break;
                    }
            	}
            }
            else {
            	String comando = "[1] Lançar dados";
            	lerOpcao(comando, 1);
            	System.out.println();
            }
            
            int d1 = jogadorDaVez.lancarDado(dados[0]);
            int d2 = jogadorDaVez.lancarDado(dados[1]);
            System.out.printf( "Os resultados foram: %d e %d\n", d1, d2 );
            
            if (d1==d2) {
            	System.out.println();
            	System.out.print( "Você tirou uma dupla!\n" );
                jogadorDaVez.soltar();
                return true;
            }
            else {
            	jogadorDaVez.aumentarQtDeRodadasPreso();
            	return false;
            }
        }
    	else {
    		String comando = String.format( "[1] Pagar fiança de $%d", fianca );
    		lerOpcao(comando, 1);
    		System.out.println();
    		
    		banco.receber(jogadorDaVez, 50);
    		
            if( !jogadorDaVez.faliu() ) {
                System.out.printf( "Você pagou a fiança com sucesso (saldo atualizado: $%d)\n", jogadorDaVez.getSaldo() );
                jogadorDaVez.soltar();
                return true;
            }
            else {
                System.out.print( "Você não pôde pagar a fiança\n" );
                return false;
            } 
        }
    }
    
    
    /**
     * 	@return Se é preciso interagir com o espaço mais uma vez
     */
    private boolean interagirComEspacoDeCarta() {
    	EspacoDeCarta posicao = (EspacoDeCarta) jogadorDaVez.getPosicao();

        String comando = "[1] Retirar carta";
        lerOpcao(comando, 1);
        System.out.println();

        /* retira carta */
        Carta temp;
        if(posicao.getNome() == NomeDeEspaco.COFRE)
            temp = jogadorDaVez.retirarCarta(deckCofre);
        else	//sorte
            temp = jogadorDaVez.retirarCarta(deckSorte);
        System.out.printf( "Carta retirada: %s\n", temp );
        System.out.println();    
        
        boolean interagirDeNovo = false;
        
        if (temp instanceof CartaDeDinheiro){
            CartaDeDinheiro carta = (CartaDeDinheiro) temp;
            int valorAbsoluto = Math.abs(carta.getValor());
            
            if(carta.getValor() > 0) {
            	String comando_ = String.format( "[1] Receber $%d", valorAbsoluto );
                lerOpcao(comando_, 1);
                System.out.println();
                
                banco.pagar(jogadorDaVez, valorAbsoluto);
                System.out.printf( "Você recebeu $%d! (saldo atualizado: $%d)\n", valorAbsoluto, jogadorDaVez.getSaldo() );
            } 
            else {
            	String comando_ = String.format( "[1] Pagar $%d", valorAbsoluto );
                lerOpcao(comando_, 1);
                System.out.println();
            	
            	banco.receber( jogadorDaVez, valorAbsoluto );
                if( !jogadorDaVez.faliu() ) 
                    System.out.printf( "Você pagou $%d (saldo atualizado: $%d)\n", valorAbsoluto, jogadorDaVez.getSaldo() );
                else
                    declararFalencia();
            }
        }
        else if(temp instanceof CartaVaParaCadeia) {
        	jogadorDaVez.prender();
            tabuleiro.avancarPara(jogadorDaVez, NomeDeEspaco.CADEIA);
            System.out.print( "Você foi preso\n" );
        }
        else if(temp instanceof CartaDeMovimentoAbsoluto) {
        	CartaDeMovimentoAbsoluto carta = (CartaDeMovimentoAbsoluto) temp;
        	
        	String comando_ = String.format( "[1] Avançar para %s", carta.getDestino() );
            lerOpcao(comando_, 1);
            System.out.println();
        	
            if( tabuleiro.avancarPara( jogadorDaVez, carta.getDestino() ) ) {
        		voltaNoTabuleiro();
        		System.out.println();
        	}
            
            System.out.printf( "Você avançou para %s\n", jogadorDaVez );
            
        	interagirDeNovo = true;
        }
        else if(temp instanceof CartaDeMovimentoRelativo) {
        	CartaDeMovimentoRelativo carta = (CartaDeMovimentoRelativo) temp;
        	int qt = Math.abs(carta.getDeslocamento());
        	
        	if(carta.getDeslocamento() > 0) {
        		String comando_ = String.format( "[1] Avançar %d casas", qt );
        		lerOpcao(comando_, 1);
        		System.out.println();
        		
        		if( tabuleiro.avancar(jogadorDaVez, qt) ) {
            		voltaNoTabuleiro();
            		System.out.println();
            	}
        		
        		System.out.printf( "Você avançou %d casas e agora está em: %s\n", qt, jogadorDaVez.getPosicao() );
        	}
        	else {
        		String comando_ = String.format( "[1] Voltar %d casas", qt );
        		lerOpcao(comando_, 1);
        		System.out.println();
        		
        		tabuleiro.voltar(jogadorDaVez, qt);
        		System.out.printf( "Você voltou %d casas e agora está em: %s\n", qt, jogadorDaVez.getPosicao() );
        	}
        	
        	interagirDeNovo = true;
        }
        else {	//CartaDeMovimentoEspecial
        	CartaDeMovimentoEspecial carta = (CartaDeMovimentoEspecial) temp;
        	
        	String comando_ = "[1] Avançar";
            lerOpcao(comando_, 1);
            System.out.println();
        	
            if( tabuleiro.avancarParaOProximo(jogadorDaVez, carta.getTipoDeEspaco()) ) {
        		voltaNoTabuleiro();
        		System.out.println();
        	}
            
        	System.out.printf( "Você avançou e agora está em: %s\n", jogadorDaVez.getPosicao() );
        	
        	interagirDeNovo = true;
        }
        
        return interagirDeNovo;
    }
    
    
    private void interagirComImpostoDeRenda() {
    	ImpostoDeRenda posicao = (ImpostoDeRenda) jogadorDaVez.getPosicao();
        String comando = String.format( "[1] Pagar $%d\n", posicao.getValor() );
        comando += "[2] Pagar 10% de sua fortuna total";
        int op = lerOpcao(comando, 2);
        System.out.println();
        
        int valor;
        if (op==1)
        	valor = posicao.getValor();
        else
            valor = posicao.getValor(jogadorDaVez);
        
        banco.receber(jogadorDaVez, valor);        
        if ( !jogadorDaVez.faliu() ) {
        	System.out.printf( "Você pagou o imposto de renda de $%d com sucesso (saldo atualizado: $%d)\n", valor, jogadorDaVez.getSaldo() );
        }
        else {
        	System.out.printf( "Você não pôde pagar o imposto de renda de $%d\n", valor );
        	System.out.println();
            declararFalencia();
        }
    }
    
    
    private void interagirComTaxaDeRiqueza() {
    	TaxaDeRiqueza posicao = (TaxaDeRiqueza) jogadorDaVez.getPosicao();
    	
    	String comando = String.format( "[1] Pagar taxa de riqueza ($%d)", posicao.getValor() );
    	lerOpcao(comando, 1);
    	System.out.println();
    	
    	banco.receber( jogadorDaVez, posicao.getValor() );
    	
        if ( !jogadorDaVez.faliu() ) {
        	System.out.printf( "Você pagou a taxa de riqueza com sucesso (saldo atualizado: $%d)\n", jogadorDaVez.getSaldo() );
        }
        else {
        	System.out.print( "Você não pôde pagar a taxa de riqueza\n" );
        	System.out.println();
            declararFalencia();
        }
    }
    
    
    private void interagirComVaParaCadeia() {
    	jogadorDaVez.prender();
        tabuleiro.avancarPara(jogadorDaVez, NomeDeEspaco.CADEIA);
        System.out.print( "Você foi preso\n" );
    }
    
    
    private void interagirComPropriedade(int resultadoDosDados) {
    	Propriedade posicao = (Propriedade) jogadorDaVez.getPosicao();
    	
    	if(posicao.getDono() == null) {
            String comando = String.format( "A propriedade está a venda por $%d, deseja comprar?\n", posicao.getPreco() );
        	comando += "[1] Sim\n";
            comando += "[2] Não";
            int op = lerOpcao(comando, 2);
            
            if (op==1){
            	System.out.println();
                if( jogadorDaVez.getSaldo() >= posicao.getPreco() ) {
                    banco.receber( jogadorDaVez, posicao.getPreco() );
                    jogadorDaVez.adicionarPropriedade(posicao);
                    posicao.setDono(jogadorDaVez);
                    System.out.printf( "Você adquiriu a propriedade com sucesso! (saldo atualizado: $%d)\n", jogadorDaVez.getSaldo() );  
                
                    if(posicao instanceof Lote) {
                    	CorDeLote cor = ((Lote) posicao).getCor();
                    	if( validarMonopolio(cor) ) {
                    		jogadorDaVez.adicionarMonopolio(cor);
                    		System.out.println();
                            System.out.printf( "Você conquistou o monopólio dos lotes da cor %s!\n", cor );
                    	}
                    }
                } 
                else {
                	System.out.print( "Seu saldo é insuficiente para adquirir a propriedade\n" );
                }
            }             
        }
    	else if ( posicao instanceof Lote && jogadorDaVez.temMonopolio( ((Lote) posicao).getCor() ) ) {
    		Lote lote = (Lote) posicao;
    		
    		if( !( lote.temCasa() || lote.temHotel() ) ) {
    			String comando = "Construção de casa disponível no lote, deseja construir?\n";
            	comando += "[1] Sim\n";
                comando += "[2] Não";
                int op = lerOpcao(comando, 2);
                System.out.println();
                
                if (op==1) {
                    if (jogadorDaVez.getSaldo() >= lote.getPrecoDeConstrucao()) {
                        banco.receber( jogadorDaVez, lote.getPrecoDeConstrucao() );
                        lote.construirCasa();
                        System.out.printf( "Você construiu uma casa com sucesso! (saldo atualizado: $%d)\n", jogadorDaVez.getSaldo() );  
                    } 
                    else {
                    	System.out.print( "Seu saldo é insuficiente para construir uma casa\n" );
                    }
                }
    		}
    		else if( !lote.temHotel() ) {
    			boolean todosTemCasa = true;	//se todos os lotes do monopólio tem casa
    			for( Lote x: jogadorDaVez.getLotes() )
    				if( x.getCor() == lote.getCor() && !x.temCasa() )
    					todosTemCasa = false;
    			
    			if(todosTemCasa) {
    				String comando = "Construção de hotel disponível no lote, deseja construir?\n";
                	comando += "[1] Sim\n";
                    comando += "[2] Não";
                    int op = lerOpcao(comando, 2);
                    
                    if (op==1) {
                    	System.out.println();
                        if (jogadorDaVez.getSaldo() >= lote.getPrecoDeConstrucao()) {
                            banco.receber( jogadorDaVez, lote.getPrecoDeConstrucao() );
                            lote.destruirCasa();
                            lote.construirHotel();
                            System.out.printf( "Você construiu um hotel com sucesso! Ele substituiu a casa do lote (saldo atualizado: $%d)\n", jogadorDaVez.getSaldo() );  
                        } 
                        else {
                        	System.out.print( "Seu saldo é insuficiente para construir um hotel\n" );
                        }
                    }
    			}
    		}
        }
        else {	//propriedade de outra pessoa
        	System.out.printf( "Propriedade de: %s\n", posicao.getDono() );
        	System.out.println();
        	
        	if( posicao.getDono().isPreso() ) {
        		System.out.print( "O dono da propriedade está preso e não poderá receber aluguel\n" );
        	}
        	else {
        		//o monopólio faz o aluguel dos lotes dobrar
            	int aluguel = posicao.calcularAluguel(resultadoDosDados);
            	if( posicao instanceof Lote && jogadorDaVez.temMonopolio( ((Lote) posicao).getCor() ) )
            		aluguel *= 2;
            	
                String comando = String.format( "[1] Pagar aluguel de $%d", aluguel );
                lerOpcao(comando, 1);
                System.out.println();
                
                banco.transferir(posicao.getDono(), jogadorDaVez, aluguel);
                if ( !jogadorDaVez.faliu() ) {
                	System.out.printf( "Você pagou o aluguel com sucesso (saldo atualizado: $%d)\n", jogadorDaVez.getSaldo() );
                }
                else{
                	System.out.print( "O aluguel não pôde ser pago\n" );
                	System.out.println();
                    declararFalencia();
                    return;
                }
        	}
        	System.out.println();
            
            while(true) {
            	String comando = String.format("[%s] Deseja fazer uma oferta sobre a propriedade?\n", jogadorDaVez);
            	comando += "[1] Sim\n";
            	comando += "[2] Não";
            	int opcao = lerOpcao(comando, 2);
                
        		if(opcao == 1) {
        			System.out.println();
        			if(jogadorDaVez.getSaldo() <= 0) {
                		System.out.print( "Você não tem saldo suficiente para fazer uma oferta!\n" );
                		break;
                	}
        			else if(negociarPropriedade(posicao)) {
        				break;
        			}
        			else {
        				System.out.println();
        			}
        		}
        		else {
        			break;
        		}
            }
        }
    }
    
    
    private boolean negociarPropriedade(Propriedade prop) {
    	Jogador vendedor = prop.getDono();
    	Jogador comprador = jogadorDaVez;
    	int valor;
    	
    	while(true) {
    		try {
    			System.out.printf( "[%s] Valor da oferta: ", comprador );
    	    	valor = Integer.parseInt( scanner.nextLine() );
    	    	
    	    	if(valor < 0)
    	    		System.out.print( "O valor não pode ser negativo!\n" );
    	    	else if(valor > comprador.getSaldo())
    	    		System.out.print( "O valor não pode ser maior que seu saldo!\n" );
    	    	else
    	    		break;
    		}
    		catch(NumberFormatException e) {
    			System.out.print("O valor precisa ser um número inteiro!\n");
    		}
    		
    		System.out.println();
    	}
    	
    	System.out.println();
    	String comando = String.format( "[%s] Você aceita a oferta de $%d pela propriedade?\n", vendedor, valor );
    	comando += "[1] Sim\n";
        comando += "[2] Não";
        int opcao = lerOpcao(comando, 2);
        System.out.println();
        
        if(opcao == 1) {
        	banco.venderPropriedade(prop, comprador, vendedor, valor);
        	System.out.printf( "[%s] Você vendeu a propriedade com sucesso! (saldo atualizado: $%d)\n", vendedor, vendedor.getSaldo() );
        	
        	if(prop instanceof Lote) {
        		CorDeLote cor = ((Lote) prop).getCor();
        		if( vendedor.temMonopolio(cor) ) {
        			vendedor.removerMonopolio(cor);
        			System.out.printf( "[%s] Você perdeu o monopólio dos lotes da cor %s\n", vendedor, cor );
        		}
        	}
        	
        	System.out.println();
        	System.out.printf( "[%s] Você comprou a propriedade com sucesso! (saldo atualizado: $%d)\n", comprador, comprador.getSaldo() );
        	
        	if(prop instanceof Lote) {
        		CorDeLote cor = ((Lote) prop).getCor();
        		if( validarMonopolio(cor) ) {
            		comprador.adicionarMonopolio(cor);
                    System.out.printf( "[%s] Você conquistou o monopólio dos lotes da cor %s!\n", comprador, cor );
        		}
        	}
        	
        	return true;
        }
        else {
        	System.out.printf( "[%s] Sua oferta foi recusada\n", comprador );
        	return false;
        }
    }
    
    
    /**
     * 	@param comando - o que será pedido do usuário
     * 	@param qtOpcoes
     * 	@return Opção lida
     */
    private int lerOpcao(String comando, int qtOpcoes) {
        int opcao;

        while(true) {
        	System.out.println( comando );
            System.out.print( "Opção: " );

            try {
            	opcao = Integer.parseInt( scanner.nextLine() );
                if(opcao > 0 && opcao <= qtOpcoes)
                    break;
                else
                    System.out.print( "Opção errada!\n" );
            }
            catch(NumberFormatException e){
                System.out.print( "A entrada precisa ser um número inteiro!\n" );
            }
            System.out.println();
        }

        return opcao;
    }
    
    
    private boolean validarMonopolio(CorDeLote cor) {
    	ArrayList<Lote> grupoDeLotes = tabuleiro.getLotes(cor);
        
        boolean monopolio = true;
        for(Lote x : grupoDeLotes)
            if(x.getDono() != jogadorDaVez)
                monopolio = false;
                
        return monopolio;
    }
    
    
    /**
     * 	Recompensa o jogador que deu volta no tabuleiro
     */
    private void voltaNoTabuleiro() {
    	PontoDePartida pdp = (PontoDePartida) tabuleiro.getEspaco(NomeDeEspaco.PONTO_PARTIDA);
		banco.pagar(jogadorDaVez, pdp.getValor());
		System.out.printf( "Você deu uma volta no tabuleiro e recebeu $%d! (saldo atualizado: $%d)\n", pdp.getValor(), jogadorDaVez.getSaldo() );
    }
    
    
    private void printarStatus(Jogador jog) {
    	/* printa status do jogador */
        System.out.printf( "Jogador: %s\n", jog );
        System.out.printf( "Saldo atual: $%d\n", jog.getSaldo() );
        System.out.print( "Propriedades: " );
    	if(jogadorDaVez.getPropriedades().size() > 0) {
    		System.out.println(jogadorDaVez.getPropriedades());
        	mostrarProgressosDeMonopolio();
    	}
    	else {
    		System.out.print( "nenhuma\n" );
    	}
    	System.out.printf( "Posição atual: %s\n", jog.getPosicao() );
    }
    
    
    /**
     * 	Mostra o quão próximo o jogador da vez está de atingir os monopólios
     */
    private void mostrarProgressosDeMonopolio() {
    	ArrayList<String> progressos = new ArrayList<>();
    	
    	CorDeLote cor = CorDeLote.AMARELO;
    	ArrayList<Lote> posses = jogadorDaVez.getLotes(cor);
    	ArrayList<Lote> todosLotes = tabuleiro.getLotes(cor);
    	if(posses.size() > 0)
    		progressos.add( String.format("%s (%d/%d)", cor, posses.size(), todosLotes.size()) );
    	
    	cor = CorDeLote.AZUL;
    	posses = jogadorDaVez.getLotes(cor);
    	todosLotes = tabuleiro.getLotes(cor);
    	if(posses.size() > 0)
    		progressos.add( String.format("%s (%d/%d)", cor, posses.size(), todosLotes.size()) );
    	
    	cor = CorDeLote.AZUL_CLARO;
    	posses = jogadorDaVez.getLotes(cor);
    	todosLotes = tabuleiro.getLotes(cor);
    	if(posses.size() > 0)
    		progressos.add( String.format("%s (%d/%d)", cor, posses.size(), todosLotes.size()) );
    	
    	cor = CorDeLote.LARANJA;
    	posses = jogadorDaVez.getLotes(cor);
    	todosLotes = tabuleiro.getLotes(cor);
    	if(posses.size() > 0)
    		progressos.add( String.format("%s (%d/%d)", cor, posses.size(), todosLotes.size()) );
    	
    	cor = CorDeLote.MARROM;
    	posses = jogadorDaVez.getLotes(cor);
    	todosLotes = tabuleiro.getLotes(cor);
    	if(posses.size() > 0)
    		progressos.add( String.format("%s (%d/%d)", cor, posses.size(), todosLotes.size()) );
    	
    	cor = CorDeLote.ROSA;
    	posses = jogadorDaVez.getLotes(cor);
    	todosLotes = tabuleiro.getLotes(cor);
    	if(posses.size() > 0)
    		progressos.add( String.format("%s (%d/%d)", cor, posses.size(), todosLotes.size()) );
    	
    	cor = CorDeLote.VERDE;
    	posses = jogadorDaVez.getLotes(cor);
    	todosLotes = tabuleiro.getLotes(cor);
    	if(posses.size() > 0)
    		progressos.add( String.format("%s (%d/%d)", cor, posses.size(), todosLotes.size()) );
    	
    	cor = CorDeLote.VERMELHO;
    	posses = jogadorDaVez.getLotes(cor);
    	todosLotes = tabuleiro.getLotes(cor);
    	if(posses.size() > 0)
    		progressos.add( String.format("%s (%d/%d)", cor, posses.size(), todosLotes.size()) );
    	
    	if(progressos.size() > 0)
    		System.out.printf( "Progressos de monopólio: %s\n", progressos );
    }
    
    
    private void declararFalencia() {
    	System.out.print( "Você faliu!\n" );
		jogadoresRestantes.remove(jogadorDaVez);
    }
    
}
