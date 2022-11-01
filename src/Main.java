import java.util.Scanner;

import modelo.entidades.Jogo;

/**
 * 	Cria uma instância do jogo com entre 2 e 4 jogadores
 * 
 * 	@author Caike dos Santos
 * 	@author Guilherme Bortoletto
 * 	@author Marcelo Pirro
 * 	@author Ryan Sakurai
 * 	@author Vinicius Castro
 */
public class Main {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		System.out.print("$$\\      $$\\  $$$$$$\\  $$\\   $$\\  $$$$$$\\  $$$$$$$\\   $$$$$$\\  $$\\   $$\\     $$\\ \n");
		System.out.print("$$$\\    $$$ |$$  _$$\\  $$$\\  $$ |$$  _$$\\  $$  _$$\\  $$   _$$\\ $$ |  \\$$\\   $$  |\n");
		System.out.print("$$$$\\  $$$$ |$$ /  $$ |$$$$\\ $$ |$$ /  $$ |$$ |  $$ |$$ /  $$ |$$ |   \\$$\\ $$  / \n");
		System.out.print("$$\\$$\\$$ $$ |$$ |  $$ |$$ $$\\$$ |$$ |  $$ |$$$$$$$  |$$ |  $$ |$$ |    \\$$$$  /  \n");
		System.out.print("$$ \\$$$  $$ |$$ |  $$ |$$ \\$$$$ |$$ |  $$ |$$  __/   $$ |  $$ |$$ |     \\$$  /   \n");
		System.out.print("$$ |\\$  /$$ |$$ |  $$ |$$ |\\$$$ |$$ |  $$ |$$ |      $$ |  $$ |$$ |      $$ |    \n");
		System.out.print("$$ | \\_/ $$ | $$$$$$  |$$ | \\$$ | $$$$$$  |$$ |       $$$$$$  |$$$$$$$$\\ $$ |    \n\n");
		
		Jogo jogo;
		
		while(true) {
			int qt = lerNumeroNatural(scanner, "Quantidade de jogadores: ");
			
			if(qt >= 2 && qt <= 4) {
				String[] jogadores = new String[qt];

				for(int i=0; i<qt; i++) {
					System.out.printf("Jogador %d: ", i+1);
					jogadores[i] = scanner.nextLine();
				}
				
				jogo = new Jogo(jogadores, scanner);
				break;
			}
			else {
				System.out.print( "O jogo precisa ter entre 2 e 4 jogadores!\n" );
			}
		}
		
		while(true) {
			System.out.println();
			jogo.iniciar();
			System.out.println();
			
			System.out.print("Jogar mais uma vez?\n");
			System.out.print("[1] Sim\n");
			System.out.print("[2] Não\n");
			
			int opcao;
	        while(true) {
            	opcao = lerNumeroNatural(scanner, "Opção: ");
                if(opcao == 1 || opcao == 2)
                    break;
                else
                    System.out.print("Opção errada, insira novamente!\n");
	        }
	        
	        if(opcao == 2)
	        	break;
		}

		scanner.close();
	}
	
	private static int lerNumeroNatural(Scanner scanner, String comando) {
		while(true) {
			try {
				System.out.print(comando);
				int num = Integer.parseInt( scanner.nextLine() );
				if(num >= 0)
					return num;
				else
					System.out.print("Não é permitido números negativos!\n");
			}
			catch(NumberFormatException e) {
				System.out.print("Insira um número inteiro!\n");
			}
		}
	}

}
