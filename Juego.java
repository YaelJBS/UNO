import java.util.Scanner;

public class Juego {
	public static void main(String[] args){
		Scanner entrada = new Scanner(System.in);      //Se declara el objeto Scanner
		
		Carta cartas[] = barajearCartas();             //Se declaran todas las cartas ya barajeadas
		//int jugadores = numeroDeJugadores();           //Se declara el numero de jugadores
		Jugador jugador[] = mazoDeJugadores();
		boolean ganador = false;                       //El juego inicia sin ganador
		
		//Imprimir cartas	
		for(int i = 0; i < 7;i++){
			System.out.println(jugador[0].mazo[i]);
		}
		for(int i = 0; i < 108; i++){
			System.out.print(cartas[i]);
			System.out.println(i);
		}
	}
	
	//Funcion que modela todas las cartas
	public static Carta[] declararCartas(){
		Carta cartas[] = new Carta[108];
		int numeroDeCarta = 0;
		
		//Genera cartas de colores del 1 al 9
		for(int i = 0; i < 2;i++)
			for(int numero = 1;numero <= 9; numero++){
				cartas[numeroDeCarta] = new Carta("Azul", numero, "Normal");
				numeroDeCarta++;
				cartas[numeroDeCarta] = new Carta("Verde", numero, "Normal");
				numeroDeCarta++;
				cartas[numeroDeCarta] = new Carta("Rojo", numero, "Normal");
				numeroDeCarta++;
				cartas[numeroDeCarta] = new Carta("Amarillo", numero, "Normal");
				numeroDeCarta++;
			}
		//Genera una carta con el nunmero 0 por cada color
		cartas[72] = new Carta("Azul", 0, "Normal");
		cartas[73] = new Carta("Verde", 0, "Normal");
		cartas[74] = new Carta("Rojo", 0, "Normal");
		cartas[75] = new Carta("Amarillo", 0, "Normal");
		
		//Genera 2 cartas RobaDos por cada color
		numeroDeCarta = 76;
		for(int i = 0; i < 2;i++){
			cartas[numeroDeCarta] = new Carta("Azul", 10, "RobaDos");
			numeroDeCarta++;
			cartas[numeroDeCarta] = new Carta("Verde", 10, "RobaDos");
			numeroDeCarta++;
			cartas[numeroDeCarta] = new Carta("Rojo", 10, "RobaDos");
			numeroDeCarta++;
			cartas[numeroDeCarta] = new Carta("Amarillo", 10, "RobaDos");
			numeroDeCarta++;
		}
		
		//Genera 2 cartas CambioSentido por cada color
		for(int i = 0; i < 2;i++){
			cartas[numeroDeCarta] = new Carta("Azul", 10, "CambioSentido");
			numeroDeCarta++;
			cartas[numeroDeCarta] = new Carta("Verde", 10, "CambioSentido");
			numeroDeCarta++;
			cartas[numeroDeCarta] = new Carta("Rojo", 10, "CambioSentido");
			numeroDeCarta++;
			cartas[numeroDeCarta] = new Carta("Amarillo", 10, "CambioSentido");
			numeroDeCarta++;
		}
		
		//Genera 2 cartas PierdeTurno por cada color
		for(int i = 0; i < 2;i++){
			cartas[numeroDeCarta] = new Carta("Azul", 10, "PierdeTurno");
			numeroDeCarta++;
			cartas[numeroDeCarta] = new Carta("Verde", 10, "PierdeTurno");
			numeroDeCarta++;
			cartas[numeroDeCarta] = new Carta("Rojo", 10, "PierdeTurno");
			numeroDeCarta++;
			cartas[numeroDeCarta] = new Carta("Amarillo", 10, "PierdeTurno");
			numeroDeCarta++;
		}
		
		//Genera 4 cartas C-CambioColor
		for(int i = 1; i <= 4; i++){
			cartas[numeroDeCarta] = new Carta("Todos", 10, "C-CambioColor");
			numeroDeCarta++;
		}
		
		//Genera 4 cartas C-CambioColor4
		for(int i = 1; i <= 4; i++){
			cartas[numeroDeCarta] = new Carta("Todos", 10, "C-CambioColor4");
			numeroDeCarta++;
		}
		
		
		return cartas;	//Regresa el arreglo cartas[]
	}
	
	//Funcion que limpia la consola
	public static void limpiarConsola(){
		for(int i = 0; i < 50; i++)
			System.out.println();
	}
	
	//Funcion para definir el numero de jugadores
	public static int numeroDeJugadores(){
		Scanner entrada = new Scanner(System.in);
		int jugadores = 0; 
		
		while(jugadores > 4 || jugadores < 2){
			System.out.println("Numero de jugadores: ");
			jugadores = entrada.nextInt();
			
			if(jugadores > 4 || jugadores < 2){
				limpiarConsola();
				System.out.println("ERROR: Numero de jugadores ivalido.\n"
						+ "Recuerde que solo puede haber de 2 a 4 jugadores.\n");
			}			
		}
		
		return jugadores;
	}
	
	//Funcion para barajear las cartas
	public static Carta[] barajearCartas(){
		Carta cartas[] = declararCartas();//Arreglo Cartas ordenadas
		Carta cartasRevueltas[] = new Carta[108];//Nuevo Arreglo Cartas barajeadas
		
		//Pasamos las cartas de un arreglo a otro de forma aleatoria
		for(int i = 0; i < cartas.length; i++){
			cartasRevueltas[i] = cartas[(int)(Math.random()*(cartas.length))];
			
			//Revision de que las cartas no sean repetidas
			for(int j = 0; i>0 && j < i; j++)
				
				//Sí una carta es repetida con la ultima, se le vuelve a asignar otra a esta
				if(cartasRevueltas[i] == cartasRevueltas[j])
					i--;
		}
		
		return cartasRevueltas;
	}
	
	//Funcion para asignar las primeras 7 cartas a cada jugador
	public static Jugador[] mazoDeJugadores(){
		Carta cartas[] = barajearCartas();
		
		int jugadores = numeroDeJugadores();
		
		Jugador jugador[] = new Jugador[jugadores];
		
		for(int i = 0; i < jugadores; i++){
			jugador[i] = new Jugador(i+1);
		}
		
		int cont=107;
		int cont2=0;
		for(int i = 0; i < 7; i++){
			jugador[0].mazo[cont2] = cartas[cont];
			
			cont--;
			
			jugador[1].mazo[cont2] = cartas[cont];
			cont--;
			
			if(jugadores>2){
				jugador[2].mazo[cont2] = cartas[cont];
				cont--;
				
				if(jugadores>3){
					jugador[3].mazo[cont2] = cartas[cont];
					cont--;
				}
			}
			cont2++;
		}
	
		return jugador;
	}
	
}
