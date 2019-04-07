import java.util.Scanner;
public class Juego {
	
	//Variables Globales
	public static Carta cartas[] = new Carta[108];  //Se declara el arreglo cartas
	public static Carta cartasVolteadas[] = new Carta[108];  //Se declara el arreglo de cartas volteadas
	public static Jugador jugadores[] = new Jugador[3]; //Se declaran los jugadores y se les da 7 cartas
	public static int NumeroDeJugadores = 0;         //Inicializamos el numero de jugadores
	public static int NumeroDeJugador = 0;           //Inicializamos el numero de jugadore
	public static int cartaEntrada;                  //Creamos carta entrada
	
	public static void main(String[] args){
		Scanner entrada = new Scanner(System.in);      //Se declara el objeto Scanner
		
		boolean ganador = false;                       //El juego inicia sin ganador
		boolean comprobarCarta = true;                //Variable que compruebas si la carta se puede poner
		int cartaEntrada;                              //Variable para ingresar la carta
		int nDeCartaVolteada = 0;                      //Variable que controla el indice de cartasVolteadas
		int colorElegido;                              //Variable para elegir el color de las cartas Cambio De Color
		
		cartas = barajarCartas();                      //Se declaran todas las cartas y se barajean
		cartasVolteadas = cartasEnJuego();             //Inicia el juego con una carta volteada
		
		//Inicia el juego
		imprimirBienvenida();                          //Imprime la bienvenida
		NumeroDeJugadores = numeroDeJugadores();              //Se declara el numero de jugadores

		jugadores = crearJugadores();              //Se declaran los jugadores y se les da 7 cartas
		
		limpiarConsola();
		while(ganador == false){
			for(NumeroDeJugador = 0; NumeroDeJugador < jugadores.length; NumeroDeJugador++){
				
				System.out.println("Carta en juego:");
				System.out.println(cartasVolteadas[nDeCartaVolteada]);//Imprime la carta en juego
				
				
				comprobarCarta = comprobarCartas(NumeroDeJugador, nDeCartaVolteada);
				
				
				
				if(comprobarCarta == true || cartasVolteadas[nDeCartaVolteada].color == "Todos"){
					if(cartasVolteadas[nDeCartaVolteada].efecto == "Normal"){
						System.out.println("Turno de " + jugadores[NumeroDeJugador].nombre + "\nTus cartas: ");
						imprimirCartasJugador(NumeroDeJugador, jugadores);
						
						System.out.println("Ingrese una carta ");//Pide una carta al jugador
						cartaEntrada=entrada.nextInt() - 1;

						//Si la carta no es la correcta, le vuelve a pedir otra al jugador
						//BUG con los comodines corregido
						if(jugadores[NumeroDeJugador].mazo[cartaEntrada].numero != 10){
							while(jugadores[NumeroDeJugador].mazo[cartaEntrada].color != cartasVolteadas[nDeCartaVolteada].color && 
									  jugadores[NumeroDeJugador].mazo[cartaEntrada].numero != cartasVolteadas[nDeCartaVolteada].numero){
									System.out.println("No puedes poner esa carta, pon otra");
									cartaEntrada = entrada.nextInt() - 1;
								}
						}

						//Agrega la carta que eligio el jugador, a el mazo cartasVolteadas		
						cartasVolteadas[nDeCartaVolteada + 1]=jugadores[NumeroDeJugador].mazo[cartaEntrada];
						//Elimina la carta que eligio el jugador, de su mano
						jugadores[NumeroDeJugador].mazo[cartaEntrada] = null;

						//Recorre de posicion las cartas de la mano del jugador
						for(int i = cartaEntrada; true; i++){
							jugadores[NumeroDeJugador].mazo[i] = jugadores[NumeroDeJugador].mazo[i+1];
								if(jugadores[NumeroDeJugador].mazo[i] == null)
									break;
							}
						//nDeCartaVolteada++;  //Agrega +1 para acceder al siguente indice de cartasVolteadas
						limpiarConsola();
						}
					
					if(cartasVolteadas[nDeCartaVolteada].efecto == "RobaDos"){
						System.out.println("RobaDos. ¡Ahora tienes dos cartas más!");
						

						for(int h = 0; h < 2; h++){

							for(int i = 0; jugadores[NumeroDeJugador].mazo[i] != null; i++ ){

								if(jugadores[NumeroDeJugador].mazo[i+1] == null){

										for(int k = 107; cartas[k] == null || k>0; k--){

											if(cartas[k] != null){
												jugadores[NumeroDeJugador].mazo[i+1] = cartas[k];
												cartas[k] = null;
												break;
											}
										}
										break;
								}		
							}
							
						}
						
						System.out.println("Turno de " + jugadores[NumeroDeJugador].nombre + "\nTus cartas: ");
						imprimirCartasJugador(NumeroDeJugador, jugadores);
						
						System.out.println("Ingrese una carta ");//Pide una carta al jugador
						cartaEntrada=entrada.nextInt() - 1;

						//Si la carta no es la correcta, le vuelve a pedir otra al jugador
						//BUG con los comodines corregido
						if(jugadores[NumeroDeJugador].mazo[cartaEntrada].numero != 10){
							while(jugadores[NumeroDeJugador].mazo[cartaEntrada].color != cartasVolteadas[nDeCartaVolteada].color && 
									  jugadores[NumeroDeJugador].mazo[cartaEntrada].numero != cartasVolteadas[nDeCartaVolteada].numero){
									System.out.println("No puedes poner esa carta, pon otra");
									cartaEntrada = entrada.nextInt() - 1;
								}
						}

						//Agrega la carta que eligio el jugador, a el mazo cartasVolteadas		
						cartasVolteadas[nDeCartaVolteada + 1]=jugadores[NumeroDeJugador].mazo[cartaEntrada];
						//Elimina la carta que eligio el jugador, de su mano
						jugadores[NumeroDeJugador].mazo[cartaEntrada] = null;

						//Recorre de posicion las cartas de la mano del jugador
						for(int i = cartaEntrada; true; i++){
							jugadores[NumeroDeJugador].mazo[i] = jugadores[NumeroDeJugador].mazo[i+1];
								if(jugadores[NumeroDeJugador].mazo[i] == null)
									break;
							}
						//nDeCartaVolteada++;  //Agrega +1 para acceder al siguente indice de cartasVolteadas
						limpiarConsola();
						}
					
					if(cartasVolteadas[nDeCartaVolteada].efecto == "CambioSentido"){
						System.out.print("CambioSentido");
				
						for(int i = 0; jugadores[i] != null; i++){
							if(jugadores[i+1] == null){
								jugadores[i+1] = jugadores[0];
								jugadores[0] = jugadores[NumeroDeJugador-1];
								jugadores[NumeroDeJugador-1] = jugadores[1];
								jugadores[NumeroDeJugador-1] = jugadores[i];
								jugadores[i+1] = null;
							}
						}
					}
					
					if(cartasVolteadas[nDeCartaVolteada].efecto == "PierdeTurno"){
						
						System.out.println(jugadores[NumeroDeJugador].nombre + " Pierde su turno");
						NumeroDeJugador++;
						
						System.out.println("Turno de " + jugadores[NumeroDeJugador].nombre + "\nTus cartas: ");
						imprimirCartasJugador(NumeroDeJugador, jugadores);
						
						System.out.println("Ingrese una carta ");//Pide una carta al jugador
						cartaEntrada=entrada.nextInt() - 1;

						//Si la carta no es la correcta, le vuelve a pedir otra al jugador
						//BUG con los comodines corregido
						if(jugadores[NumeroDeJugador].mazo[cartaEntrada].numero != 10){
							while(jugadores[NumeroDeJugador].mazo[cartaEntrada].color != cartasVolteadas[nDeCartaVolteada].color && 
									  jugadores[NumeroDeJugador].mazo[cartaEntrada].numero != cartasVolteadas[nDeCartaVolteada].numero){
									System.out.println("No puedes poner esa carta, pon otra");
									cartaEntrada = entrada.nextInt() - 1;
								}
						}

						//Agrega la carta que eligio el jugador, a el mazo cartasVolteadas		
						cartasVolteadas[nDeCartaVolteada + 1]=jugadores[NumeroDeJugador].mazo[cartaEntrada];
						//Elimina la carta que eligio el jugador, de su mano
						jugadores[NumeroDeJugador].mazo[cartaEntrada] = null;

						//Recorre de posicion las cartas de la mano del jugador
						for(int i = cartaEntrada; true; i++){
							jugadores[NumeroDeJugador].mazo[i] = jugadores[NumeroDeJugador].mazo[i+1];
								if(jugadores[NumeroDeJugador].mazo[i] == null)
									break;
							}
						//nDeCartaVolteada++;  //Agrega +1 para acceder al siguente indice de cartasVolteadas
						limpiarConsola();
					}
					
					if(cartasVolteadas[nDeCartaVolteada].efecto == "C-CambioColor"){
						
						while(true){
							System.out.println("Elija el color. 1.- Azul, 2.- Amarillo, 3.- Verde, 4.- Rojo");
							colorElegido = entrada.nextInt();
							if(colorElegido == 1){
								cartasVolteadas[nDeCartaVolteada].color = "Azul";
								break;
							}
							if(colorElegido == 2){
								cartasVolteadas[nDeCartaVolteada].color = "Amarillo";
								break;
							}
							if(colorElegido == 3){
								cartasVolteadas[nDeCartaVolteada].color = "Verde";
								break;
							}
							if(colorElegido == 4){
								cartasVolteadas[nDeCartaVolteada].color = "Rojo";
								break;
							}
							if(colorElegido == 0 || colorElegido > 4){
								System.out.println("Solo puedes meter los colores del 1 a 0, intenta otra vez");
							}
						}
						limpiarConsola();
						
						System.out.println(cartasVolteadas[nDeCartaVolteada]);//Imprime la carta en juego
						System.out.println("Cambio de color. El color ha sido cambiado por: " + cartasVolteadas[nDeCartaVolteada].color);
						
						System.out.println("Turno de " + jugadores[NumeroDeJugador].nombre + "\nTus cartas: ");
						imprimirCartasJugador(NumeroDeJugador, jugadores);
						
						System.out.println("Ingrese una carta ");//Pide una carta al jugador
						cartaEntrada=entrada.nextInt() - 1;

						//Si la carta no es la correcta, le vuelve a pedir otra al jugador
						//BUG con los comodines corregido
						if(jugadores[NumeroDeJugador].mazo[cartaEntrada].numero != 10){
							while(jugadores[NumeroDeJugador].mazo[cartaEntrada].color != cartasVolteadas[nDeCartaVolteada].color && 
									  jugadores[NumeroDeJugador].mazo[cartaEntrada].numero != cartasVolteadas[nDeCartaVolteada].numero){
									System.out.println("No puedes poner esa carta, pon otra");
									cartaEntrada = entrada.nextInt() - 1;
								}
						}

						//Agrega la carta que eligio el jugador, a el mazo cartasVolteadas		
						cartasVolteadas[nDeCartaVolteada + 1]=jugadores[NumeroDeJugador].mazo[cartaEntrada];
						//Elimina la carta que eligio el jugador, de su mano
						jugadores[NumeroDeJugador].mazo[cartaEntrada] = null;

						//Recorre de posicion las cartas de la mano del jugador
						for(int i = cartaEntrada; true; i++){
							jugadores[NumeroDeJugador].mazo[i] = jugadores[NumeroDeJugador].mazo[i+1];
								if(jugadores[NumeroDeJugador].mazo[i] == null)
									break;
							}
						//nDeCartaVolteada++;  //Agrega +1 para acceder al siguente indice de cartasVolteadas
						limpiarConsola();
						
					}
					
					if(cartasVolteadas[nDeCartaVolteada].efecto == "C-CambioColor4"){
						while(true){
							System.out.println("Elija el color. 1.- Azul, 2.- Amarillo, 3.- Verde, 4.- Rojo");
							colorElegido = entrada.nextInt();
							if(colorElegido == 1){
								cartasVolteadas[nDeCartaVolteada].color = "Azul";
								break;
							}
							if(colorElegido == 2){
								cartasVolteadas[nDeCartaVolteada].color = "Amarillo";
								break;
							}
							if(colorElegido == 3){
								cartasVolteadas[nDeCartaVolteada].color = "Verde";
								break;
							}
							if(colorElegido == 4){
								cartasVolteadas[nDeCartaVolteada].color = "Rojo";
								break;
							}
							if(colorElegido == 0 || colorElegido > 4){
								System.out.println("Solo puedes meter los colores del 1 a 0, intenta otra vez");
							}
						}
						limpiarConsola();
						
						for(int h = 0; h < 4; h++){

							for(int i = 0; jugadores[NumeroDeJugador].mazo[i] != null; i++ ){

								if(jugadores[NumeroDeJugador].mazo[i+1] == null){

										for(int k = 107; cartas[k] == null || k>0; k--){

											if(cartas[k] != null){
												jugadores[NumeroDeJugador].mazo[i+1] = cartas[k];
												cartas[k] = null;
												break;
											}
										}
										break;
								}		
							}
							
						}
						
						System.out.println(cartasVolteadas[nDeCartaVolteada]);//Imprime la carta en juego
						System.out.println("Cambio de color +4. El color ha sido cambiado por: " + cartasVolteadas[nDeCartaVolteada].color 
								+ ". ¡Y ahora tienes 4 cartas más!");
						
						System.out.println("Turno de " + jugadores[NumeroDeJugador].nombre + "\nTus cartas: ");
						imprimirCartasJugador(NumeroDeJugador, jugadores);
						
						System.out.println("Ingrese una carta ");//Pide una carta al jugador
						cartaEntrada=entrada.nextInt() - 1;

						//Si la carta no es la correcta, le vuelve a pedir otra al jugador
						//BUG con los comodines corregido
						if(jugadores[NumeroDeJugador].mazo[cartaEntrada].numero != 10){
							while(jugadores[NumeroDeJugador].mazo[cartaEntrada].color != cartasVolteadas[nDeCartaVolteada].color && 
									  jugadores[NumeroDeJugador].mazo[cartaEntrada].numero != cartasVolteadas[nDeCartaVolteada].numero){
									System.out.println("No puedes poner esa carta, pon otra");
									cartaEntrada = entrada.nextInt() - 1;
								}
						}

						//Agrega la carta que eligio el jugador, a el mazo cartasVolteadas		
						cartasVolteadas[nDeCartaVolteada + 1]=jugadores[NumeroDeJugador].mazo[cartaEntrada];
						//Elimina la carta que eligio el jugador, de su mano
						jugadores[NumeroDeJugador].mazo[cartaEntrada] = null;

						//Recorre de posicion las cartas de la mano del jugador
						for(int i = cartaEntrada; true; i++){
							jugadores[NumeroDeJugador].mazo[i] = jugadores[NumeroDeJugador].mazo[i+1];
								if(jugadores[NumeroDeJugador].mazo[i] == null)
									break;
							}
						//nDeCartaVolteada++;  //Agrega +1 para acceder al siguente indice de cartasVolteadas
						limpiarConsola();
						
					}
				
					nDeCartaVolteada++;
				}else{
					System.out.print("No tienes ninguna carta valida");
					ganador = true;
				}
			}
		}
	}

	//Comprueba si el jugador tiene cartas para poner
	public static boolean comprobarCartas(int indiceJugador, int indiceCartaEnJuego){
		boolean respuesta;
		int contador = 0;
		
		//Comprueba si tiene un numero o un color para poner en cartas en juego
		if(cartasVolteadas[indiceCartaEnJuego].efecto == "Normal"){
			for(int i = 0; jugadores[indiceJugador].mazo[i] != null; i++){
				if(cartasVolteadas[indiceCartaEnJuego].color == jugadores[indiceJugador].mazo[i].color ||
				   cartasVolteadas[indiceCartaEnJuego].numero == jugadores[indiceJugador].mazo[i].numero){
					contador++;
				}		
			}
		}
		
		//Comprueba si tiene un color para poner en cartas en juego
		if(cartasVolteadas[indiceCartaEnJuego].efecto == "RobaDos" ||
		   cartasVolteadas[indiceCartaEnJuego].efecto == "CambioSentido" ||
		   cartasVolteadas[indiceCartaEnJuego].efecto == "PierdeTurno"){
			for(int i = 0; jugadores[indiceJugador].mazo[i] != null; i++){
				if(cartasVolteadas[indiceCartaEnJuego].color == jugadores[indiceJugador].mazo[i].color){
					contador++;
				}		
			}
		}
		
		//Cuando la carta en juego es un comodin comprieba si tienes otro comodin
		if(cartasVolteadas[indiceCartaEnJuego].efecto == "C-CambioColor" ||
				   cartasVolteadas[indiceCartaEnJuego].efecto == "C-CambioColor4"){
			//Comprueba si tiene un comodin
			for(int i = 0; jugadores[indiceJugador].mazo[i] != null; i++){
				if(jugadores[indiceJugador].mazo[i].efecto == "C-CambioColor" ||
						jugadores[indiceJugador].mazo[i].efecto == "C-CambioColor4"){
					contador++;
				}		
			}
		}
		
		//Comprueba si tiene un comodin
		for(int i = 0; jugadores[indiceJugador].mazo[i] != null; i++){
			if(jugadores[indiceJugador].mazo[i].efecto == "C-CambioColor" ||
					jugadores[indiceJugador].mazo[i].efecto == "C-CambioColor4"){
				contador++;
			}		
		}
			
		if(contador != 0)
			respuesta = true;
		else
			respuesta = false;
		
		return respuesta;
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
		
	//Funcion para definir el numero de jugadores
	public static int numeroDeJugadores(){
		Scanner entrada = new Scanner(System.in);
		int jugadores = 0; 
		
		while(jugadores > 4 || jugadores < 2){
			System.out.println("Numero de jugadores: ");
			jugadores = entrada.nextInt();
			
			if(jugadores > 4 || jugadores < 2){
				System.out.println("\nERROR: Numero de jugadores ivalido.\n"
						+ "Recuerde que solo puede haber de 2 a 4 jugadores.\n");
			}			
		}
		
		return jugadores;
	}
	
	//Funcion para barajar las cartas
	public static Carta[] barajarCartas(){
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

	//Funcion para crear los jugadores y asignar las primeras 7 cartas a cada jugador
	public static Jugador[] crearJugadores(){ //Se pasan las cartas y numero de jugador declararado en el main
		Scanner entrada = new Scanner(System.in);      //Se declara el objeto Scanner
		Jugador crearJugadgores[] = new Jugador[NumeroDeJugadores];//Instanciamos un arreglo de objetos del objeto Jugador, con el numero de jugadores
		
		String nombre = "";
		
		//Declaramos el numero de jugadores del arreglo Jugador
		for(int i = 0; i < NumeroDeJugadores; i++){
			System.out.println("Nombre del jugador " + (i+1));
			nombre = entrada.nextLine();
			crearJugadgores[i] = new Jugador(i , nombre);
		}
		
		int ultimoIndiceCartas = IndiceUltimoElemento(cartas);//Numero de objeto del arreglo cartas
		int ultimoIndiceMazoJugador = 0;//Numero de objeto del mazo, dentro del arreglo jugador
		//Le damos cartas a cada jugador
		for(int i = 1; i <= 7; i++){
			for(int j = 0; j < crearJugadgores.length; j++){
				crearJugadgores[j].mazo[ultimoIndiceMazoJugador] = cartas[ultimoIndiceCartas];
				cartas[ultimoIndiceCartas] = null;
				ultimoIndiceCartas--;
			}
			ultimoIndiceMazoJugador++;
		}

		return crearJugadgores;
	}
	
	//Funcion para poner la primera carta del arreglo cartas barajadas, al arreglo de cartas volteadas para empezar a jugar
	public static Carta[] cartasEnJuego() {

		Carta cartasVolteadas[] = new Carta[108];  //Arreglo de cartas volteadas
		int indice = IndiceUltimoElemento(cartas); //Obtenemos el ultimo indice de Cartas
		
		cartasVolteadas[0] = cartas[indice];       //Agregamos el objeto Carta de ese indice a cartasVolteadas
		cartas[indice] = null;                     //Borramos ese indice

		return cartasVolteadas;
	}

	//Da el indice donde se aloja el ultimo elemento
	public static int IndiceUltimoElemento(Carta arreglo[]){
		int indice = 0;
		
		for(int i = 107; arreglo[i] == null || i >= 0; i--){
			if(arreglo[i] != null){
				indice = i;
				break;
			}
		}
		
		return indice;
	}
	
	//Funcion que limpia la consola
	public static void limpiarConsola(){
		for(int i = 0; i < 50; i++)
			System.out.println();
	}
	
	//Imrime las cartas del jugador
	public static void imprimirCartasJugador(int jugadorNumero, Jugador jugadores[]){
		int cont = 0;
		
		for(int i = 0; jugadores[jugadorNumero].mazo[i] != null; i++){//Imprime las cartas del jugador en turno
			cont++;
		}

		while(true){

			for(int j = 0; j < cont; j++){
				System.out.print(" ________   ");
				
				if(j == 9){
					break;
				}
			}
			
			System.out.println();
			
			for(int j = 0; j < cont; j++){
				
				if(jugadores[NumeroDeJugador].mazo[cartaEntrada].efecto != "CambioSentido")
				 	System.out.print("|        |  ");
					
				if(jugadores[NumeroDeJugador].mazo[cartaEntrada].efecto == "CambioSentido")
					System.out.print("|   ^    |  ");
					
				if(j == 9){
					break;
				}
				
				cartaEntrada++;
			}
			
			cartaEntrada = 0;
			System.out.println();
			
			for(int j = 0; j < cont; j++){
				
				if(jugadores[NumeroDeJugador].mazo[cartaEntrada].efecto == "Normal")
					System.out.print("|   "+jugadores[NumeroDeJugador].mazo[cartaEntrada].numero+"    |  ");
					
				if(jugadores[NumeroDeJugador].mazo[cartaEntrada].efecto == "RobaDos")
					System.out.print("|   +2   |  ");
					
				if(jugadores[NumeroDeJugador].mazo[cartaEntrada].efecto == "CambioSentido")
					System.out.print("|   ||   |  ");
					
				if(jugadores[NumeroDeJugador].mazo[cartaEntrada].efecto == "PierdeTurno")
					System.out.print("|  (/0)  |  ");
					
				if(jugadores[NumeroDeJugador].mazo[cartaEntrada].efecto == "C-CambioColor")
					System.out.print("| Cambio |  ");
					
				if(jugadores[NumeroDeJugador].mazo[cartaEntrada].efecto == "C-CambioColor4")
					System.out.print("| Cambio |  ");
					
				if(j == 9){
					break;
				}
				
				cartaEntrada++;
			}
			
			cartaEntrada = 0;
			System.out.println();
			
			for(int j = 0; j < cont; j++){
				
				if(jugadores[NumeroDeJugador].mazo[cartaEntrada].efecto == "Normal" 
						|| jugadores[NumeroDeJugador].mazo[cartaEntrada].efecto == "RobaDos" 
						|| jugadores[NumeroDeJugador].mazo[cartaEntrada].efecto == "PierdeTurno")
					System.out.print("|        |  ");
					
				if(jugadores[NumeroDeJugador].mazo[cartaEntrada].efecto == "CambioSentido")
					System.out.print("|    v   |  ");
					
				if(jugadores[NumeroDeJugador].mazo[cartaEntrada].efecto == "C-CambioColor")
					System.out.print("| Color  |  ");
					
				if(jugadores[NumeroDeJugador].mazo[cartaEntrada].efecto == "C-CambioColor4")
					System.out.print("| Color  |  ");
					
				if(j == 9){
					break;
				}
				
				cartaEntrada++;	
			}
			
			cartaEntrada = 0;
			System.out.println();
			
			
			for(int j = 0; j < cont; j++){
				
				if(jugadores[NumeroDeJugador].mazo[cartaEntrada].efecto == "Normal"){
					
					if(jugadores[NumeroDeJugador].mazo[cartaEntrada].color == "Azul"){
						System.out.print("|  "+"Azul"+"  |  ");
					}
					if(jugadores[NumeroDeJugador].mazo[cartaEntrada].color == "Amarillo"){
						System.out.print("|"+"Amarillo"+"|  ");
					}
					if(jugadores[NumeroDeJugador].mazo[cartaEntrada].color == "Verde"){
						System.out.print("| "+"Verde"+"  |  ");
					}
					if(jugadores[NumeroDeJugador].mazo[cartaEntrada].color == "Rojo"){
						System.out.print("|  "+"Rojo"+"  |  ");
					}
					
				}
				
				if(jugadores[NumeroDeJugador].mazo[cartaEntrada].efecto == "RobaDos"){
					
					if(jugadores[NumeroDeJugador].mazo[cartaEntrada].color == "Azul"){
						System.out.print("|  "+"Azul"+"  |  ");
					}
					if(jugadores[NumeroDeJugador].mazo[cartaEntrada].color == "Amarillo"){
						System.out.print("|"+"Amarillo"+"|  ");
					}
					if(jugadores[NumeroDeJugador].mazo[cartaEntrada].color == "Verde"){
						System.out.print("| "+"Verde"+"  |  ");
					}
					if(jugadores[NumeroDeJugador].mazo[cartaEntrada].color == "Rojo"){
						System.out.print("|  "+"Rojo"+"  |  ");
					}
					
				}
				
				if(jugadores[NumeroDeJugador].mazo[cartaEntrada].efecto == "CambioSentido"){
					
					if(jugadores[NumeroDeJugador].mazo[cartaEntrada].color == "Azul"){
						System.out.print("|  "+"Azul"+"  |  ");
					}
					if(jugadores[NumeroDeJugador].mazo[cartaEntrada].color == "Amarillo"){
						System.out.print("|"+"Amarillo"+"|  ");
					}
					if(jugadores[NumeroDeJugador].mazo[cartaEntrada].color == "Verde"){
						System.out.print("| "+"Verde"+"  |  ");
					}
					if(jugadores[NumeroDeJugador].mazo[cartaEntrada].color == "Rojo"){
						System.out.print("|  "+"Rojo"+"  |  ");
					}
					
				}
				
				if(jugadores[NumeroDeJugador].mazo[cartaEntrada].efecto == "PierdeTurno"){
					
					if(jugadores[NumeroDeJugador].mazo[cartaEntrada].color == "Azul"){
						System.out.print("|  "+"Azul"+"  |  ");
					}
					if(jugadores[NumeroDeJugador].mazo[cartaEntrada].color == "Amarillo"){
						System.out.print("|"+"Amarillo"+"|  ");
					}
					if(jugadores[NumeroDeJugador].mazo[cartaEntrada].color == "Verde"){
						System.out.print("| "+"Verde"+"  |  ");
					}
					if(jugadores[NumeroDeJugador].mazo[cartaEntrada].color == "Rojo"){
						System.out.print("|  "+"Rojo"+"  |  ");
					}
					
				}
				
				if(jugadores[NumeroDeJugador].mazo[cartaEntrada].efecto == "C-CambioColor"){
					System.out.print("|        |  ");
				}
				
				if(jugadores[NumeroDeJugador].mazo[cartaEntrada].efecto == "C-CambioColor4"){
					System.out.print("|   +4   |  ");
				}
			
				if(j == 9){
					break;
				}
				
				cartaEntrada++;
			}
			
			cartaEntrada = 0;
			System.out.println();
			
			for(int j = 0; j < cont; j++){
				System.out.print("|________|  ");
				
				if(j == 9){
					break;
				}
			}
			
			System.out.println();
			
			for(int j = 1; j <= cont; j++){
				
				if(j != 10){
					System.out.print("    "+j+"       ");
				}
				
				if(j == 10){
					System.out.print("    "+j+"      ");
				}
				
				if(j == 9){
					break;
				}	
			}
			
			System.out.println();
			
			cont = cont - 10;
			if(cont <= 0){
				break;
			}
		}

	}

	//Imprimir cartas del mazo principal
	public static void imprimirCartasPrincipales(){
		System.out.println("Cartas del mazo principal\n");
		for(int i = 0; i < cartas.length;i++)
			System.out.println(cartas[i]);
	}
	
	//Imprime la bienvenida
	public static void imprimirBienvenida(){
		System.out.print("Hola bienvenidos a:\n" +
						 "   __  ___   ______\n" +
						 "  / / / / | / / __ \n" +
				         " / / / /  |/ / / / /\n" +
						 "/ /_/ / /|  / /_/ /\n" +
				         "\\____/_/ |_/\\____/\n\n" +
						 "Por Yael y Erik\n\n");
	}
}
