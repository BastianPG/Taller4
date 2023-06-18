import Services.SistemaImpl;
import ucn.StdIn;
import ucn.StdOut;

import java.io.IOException;
import java.util.Objects;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) throws IOException {

        SistemaImpl sistema = new SistemaImpl();

        menuPrincipal(sistema);

    }

    // TODO agreagr sistema al subprograma
    public static void menuPrincipal(SistemaImpl sistema) throws IOException {

        String aux = "";
        while (true) {

            try {


                StdOut.println("""
                        ======================================================
                                       Bienvenido a la Pokedex
                                      
                        ======================================================
                        [1] Desplegar Pokemons por rango Id
                        [2] Desplegar Pokemons alfabeticamente
                        [3] Desplegar Pokemons por su tipo
                        [4] Desplegar Pokemons de primera evolucion
                        [5] Busqueda personalizada
                        [6] Salir
                        ======================================================
                        Ingrese una opcion:""");

                aux = StdIn.readString();
                int opcion = Integer.parseInt(aux);

                switch (opcion) {
                    case 1 -> sistema.desplegarPorId();
                    case 2 -> sistema.desplegarAlfabeticamente();
                    case 3 -> sistema.desplegarTipo();
                    case 4 -> sistema.desplegarPrimeraEvolucion();
                    case 5 -> sistema.desplegarNavegacion();
                    case 6 -> System.exit(0);
                    default -> StdOut.println("Opcion no valida");
                }
            } catch (NumberFormatException e){
                System.out.println("""
                    
                    [*][*][*][*][*][*][*][*][*][*][*][*][*][*][*][*][*]
                    [*]                                             [*]
                    [*]  ¡ERROR! Ingrese un numero entero y valido. [*]       
                    [*]                                             [*]
                    [*][*][*][*][*][*][*][*][*][*][*][*][*][*][*][*][*]
                    
                    """);
                menuPrincipal(sistema);
            }
        }
    }
}
