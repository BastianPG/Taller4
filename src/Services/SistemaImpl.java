package Services;
import Colecciones.ListaNexoDoble;
import Model.Basico;
import Model.Pokemons;
import Model.PrimeraEvolucion;
import Model.SegundoEvolucion;
import ucn.*;
import java.io.IOException;
import java.io.*;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;
public class SistemaImpl implements Sistema {
    public ListaNexoDoble listaNexoDoble;
    public SistemaImpl() throws IOException {
        this.listaNexoDoble = new ListaNexoDoble();
        this.corregirArchivo();
        this.leerArchivo();

    }
    /**
     * Este metodo va a corregir el archivo txt inicial
     */
    @Override
    public void corregirArchivo() throws IOException {

        //INGRESE EL TXT ORIGINAL AQUI
        //PARA CORREGIRLO
        File inputFile = new File("C:\\Users\\benja\\Downloads\\taller 4 disc\\kanto.txt");

        File outputFile = new File("kantoPrueba"); //Se guardará un kanto sin espacios para luego eliminar las líneas blancas y trabajar con ese txt corregido

        BufferedReader reader = new BufferedReader(new FileReader(inputFile));
        BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile));

        String line;
        while ((line = reader.readLine()) != null) {
            if (!line.trim().equals("")) {
                writer.write(line);
                writer.newLine();
            }
        }
        reader.close();
        writer.close();

        ArchivoSalida arch1 = new ArchivoSalida("kantoActualizado");
        String archivo = "kantoPrueba"; //Direccion donde se guarda kantoPrueba
        String linea;
        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            while ((linea = br.readLine()) != null) {
                linea = linea.replaceAll("\\s+", "");
                // Aquí puedes hacer lo que necesites con cada campo
                Registro regSal = new Registro(1);
                regSal.agregarCampo(linea);
                arch1.writeRegistro(regSal);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * Este metodo a leer el archivo corregido
     */
    @Override
    public void leerArchivo() throws IOException {
        ArchivoEntrada arch1 = new ArchivoEntrada("C:\\Users\\benja\\Downloads\\taller 4 disc\\kantoActualizado");

        while (!arch1.isEndFile()) {

            Registro regEnt = arch1.getRegistro();
            int idPokemon = regEnt.getInt();
            String nombrePokemon = regEnt.getString();
            String etapa = regEnt.getString();

            etapa = Normalizer.normalize(etapa, Normalizer.Form.NFD);
            etapa = etapa.replaceAll("[^\\p{ASCII}]", "");
            String convertir = etapa.toLowerCase();

            switch (convertir) {
                // En caso de que la etapa sea basico, entra aquí y los guarda dependiendo su primera o evolucion siguiente
                case "basico" -> {
                    String primeraEvolucion1 = regEnt.getString();
                    String primeraEvolucion2 = regEnt.getString();
                    String primeraEvolucion3 = regEnt.getString();
                    String tipo1 = regEnt.getString();
                    String tipo2 = regEnt.getString();

                    // Verifica si no tiene evoluciones
                    if (primeraEvolucion3 == null && tipo1 == null && tipo2 == null) {
                        tipo1 = primeraEvolucion1;
                        primeraEvolucion1 = null;
                        tipo2 = primeraEvolucion2;
                        primeraEvolucion2 = null;

                        // Guarda el Pokemon de etapa basica
                        Pokemons agregarBasico = new Basico(idPokemon, nombrePokemon, etapa, primeraEvolucion1, primeraEvolucion2, primeraEvolucion3, tipo1, tipo2);
                        this.listaNexoDoble.agregarPokemon(agregarBasico);
                        break;
                    }
                    // Verifica si solo tiene una primera evolucion
                    if (tipo1 == null && tipo2 == null) {
                        tipo1 = primeraEvolucion2;
                        primeraEvolucion2 = null;
                        tipo2 = primeraEvolucion3;
                        primeraEvolucion3 = null;

                        // Guarda el Pokemon de etapa basica
                        Pokemons agregarBasico = new Basico(idPokemon, nombrePokemon, etapa, primeraEvolucion1, primeraEvolucion2, primeraEvolucion3, tipo1, tipo2);
                        this.listaNexoDoble.agregarPokemon(agregarBasico);
                        break;
                    }
                    // Verifica si no tiene 3ra evolucion
                    if (tipo2 == null) {
                        tipo2 = tipo1;
                        tipo1 = primeraEvolucion3;
                        primeraEvolucion3 = null;

                        // Guarda el Pokemon de etapa basica
                        Pokemons agregarBasico = new Basico(idPokemon, nombrePokemon, etapa, primeraEvolucion1, primeraEvolucion2, primeraEvolucion3, tipo1, tipo2);
                        this.listaNexoDoble.agregarPokemon(agregarBasico);

                    } else {
                        // Guarda el Pokemon de etapa basica
                        Pokemons agregarBasico = new Basico(idPokemon, nombrePokemon, etapa, primeraEvolucion1, primeraEvolucion2, primeraEvolucion3, tipo1, tipo2);
                        this.listaNexoDoble.agregarPokemon(agregarBasico);
                    }
                }
                // En caso de que la etapa sea primera evolucion, entra aquí y los guarda dependiendo ai tiene o no evolucion siguiente
                case "primeraevolucion" -> {
                    String evolucionSiguiente = regEnt.getString();
                    String evolucionPrevia = regEnt.getString();
                    String tipo1 = regEnt.getString();
                    String tipo2 = regEnt.getString();

                    // Verifica que no tiene evolucion Siguiente
                    if (tipo2 == null) {
                        tipo2 = tipo1;
                        tipo1 = evolucionPrevia;
                        evolucionPrevia = evolucionSiguiente;
                        evolucionSiguiente = null;

                        // Guarda el Pokemon de etapa primera evolucion
                        Pokemons agregarPrimeraEvolucion = new PrimeraEvolucion(idPokemon, nombrePokemon, etapa, evolucionSiguiente, evolucionPrevia, tipo1, tipo2);
                        this.listaNexoDoble.agregarPokemon(agregarPrimeraEvolucion);

                    } else {
                        // Guarda el Pokemon de etapa primera evolucion
                        Pokemons agregarPrimeraEvolucion = new PrimeraEvolucion(idPokemon, nombrePokemon, etapa, evolucionSiguiente, evolucionPrevia, tipo1, tipo2);
                        this.listaNexoDoble.agregarPokemon(agregarPrimeraEvolucion);
                    }

                }
                // En caso de que la etapa sea segunda evolucion, entra aquí y lo guarda
                case "segundaevolucion" -> {
                    String evolucionPrevia1 = regEnt.getString();
                    String evolucionPrevia2 = regEnt.getString();
                    String tipo1 = regEnt.getString();
                    String tipo2 = regEnt.getString();

                    // Guarda el Pokemon de etapa segunda evolucion
                    Pokemons agregarSegundaEvolucion = new SegundoEvolucion(idPokemon, nombrePokemon, etapa, evolucionPrevia1, evolucionPrevia2, tipo1, tipo2);
                    this.listaNexoDoble.agregarPokemon(agregarSegundaEvolucion);
                }
            }
        }
    }
    /**
     * Este metodo va a desplegar a los Pokemons por un rango de ID especificado por el usuario en orden creciente
     */
    @Override
    public void desplegarPorId() {
        String aux;
        try {
            StdOut.println("Desde que id quiere desplegar los pokemons (1-151) (ingrese *salir* para regresar)");
            aux = StdIn.readString();
            // Por si el usuario quiere salir de esta opcion y volver al menu
            if (aux.equalsIgnoreCase("salir")) {
                System.out.println("Regresando...");
            } else {
                int num = Integer.parseInt(aux);
                // Verifica que el ID de inicio no sea menor a la cantidad de Pokemons
                if (num <= 0) {
                    StdOut.println("El Id ingresado no es valido vuelva a intentarlo");

                    desplegarPorId();
                } else {
                    StdOut.println("hasta que id quiere desplegar los pokemons");
                    aux = StdIn.readString();
                    int aux1 = Integer.parseInt(aux);
                    // Verifica que el ID de término no sea mayor a la cantidad de Pokemons
                    if (aux1 > this.listaNexoDoble.getCantElementos()) {
                        StdOut.println("El Id ingresado no es valido vuelva a intentarlo");

                        desplegarPorId();
                    } else {
                        for (int i = num; i <= aux1; i++) {
                            // Recorre los nodos dependiendo el ID ingresado y los imprime dependiendo el rango elegido por el usuario
                            Pokemons pokemons = this.listaNexoDoble.obtenerPokemonsId(i);
                            System.out.println(pokemons.toString());
                        }
                    }
                }
            }
            // En caso de que se ingresa un valor String en vez de un valor entero salta este catch
        } catch (NumberFormatException e) {
            System.out.println("""
                    ======================================================
                    ¡ERROR! Ingrese un numero entero y valido.
                    ======================================================
                    """);
            desplegarPorId();
        }
    }
    /**
     * Este metodo va a desplegar a los Pokemons en orden alfabético
     */
    @Override
    public void desplegarAlfabeticamente() {
        // Creamos un vector del tamaño de la cantidad de elementos
        String[] vector = new String[listaNexoDoble.getCantElementos()];

        // Recorremos los nodos y los vamos guardando en el vector
        for (int i = 1; i <= this.listaNexoDoble.getCantElementos(); i++) {

            Pokemons pokemons = this.listaNexoDoble.obtenerPokemonsId(i);
            vector[i - 1] = pokemons.getNombrePokemon();
        }
        // Creamos un ArrayList y guardamos el vector en este mismo
        ArrayList<String> lista = new ArrayList<>();
        Collections.addAll(lista, vector);
        // Ordenamos alfabéticamente la lista
        Collections.sort(lista);

        // Imprimimos el ArrayList
        for (String elemento : lista) {
            System.out.println(elemento);
        }
    }
    /**
     * Este metodo va a desplegar a los Pokemons por su tipo especificado por el usuario
     */
    @Override
    public void desplegarTipo() {
        StdOut.println("Ingrese el tipo de pokemon que quiere desplegar (ingrese 2 para regresar)");
        String tipoDes = StdIn.readString();

        // En caso de ingresar 2 vuelve al menu principal
        if (!Objects.equals(tipoDes, "2")) {
            tipoDes = Normalizer.normalize(tipoDes, Normalizer.Form.NFD);
            tipoDes = tipoDes.replaceAll("[^\\p{ASCII}]", "");
            String convertir = tipoDes.toLowerCase();
            boolean encontrado = false;

            for (int i = 1; i <= this.listaNexoDoble.getCantElementos(); i++) {
                Pokemons pokemons = listaNexoDoble.obtenerPokemonsId(i);

                if (convertir.equalsIgnoreCase(pokemons.getTipo1()) || convertir.equalsIgnoreCase(pokemons.getTipo2())) {
                    StdOut.println(pokemons);
                    encontrado = true;
                }
            }
            if (!encontrado) {
                System.out.println("Error!, tipo no encontrado");
                desplegarTipo();
            }
        } else {
            System.out.println("Regreando...");
        }
    }
    /**
     * Este metodo va a desplegar a solo los Pokemons de etapa primera evolucion en orden decreciente
     */
    @Override
    public void desplegarPrimeraEvolucion() {
        // Creamos esta variable para después poder imprimir de manera decreciente
        int aux = this.listaNexoDoble.getCantElementos();

        for (int i = 1; i <= this.listaNexoDoble.getCantElementos(); i++) {

            Pokemons pokemons = listaNexoDoble.obtenerPokemonsId(aux);

            //
            if (pokemons.getEtapa().equalsIgnoreCase("primeraevolucion")) {
                StdOut.println(pokemons);
            }
            aux--;
        }
    }
    /**
     * Este metodo va a navegar entre lineas evolutivas de Pokemons
     */
    @Override
    public void navegarEvolucionesid(int id) {
        Pokemons pokemons = listaNexoDoble.obtenerPokemonsId(id);
        String etapaAct = pokemons.getEtapa();

        etapaAct = Normalizer.normalize(etapaAct, Normalizer.Form.NFD);
        etapaAct = etapaAct.replaceAll("[^\\p{ASCII}]", "");
        String convertir = etapaAct.toLowerCase();

        switch (convertir) {
            case "basico" -> {

                System.out.println(pokemons);
                Basico basico = (Basico) listaNexoDoble.obtenerPokemonsId(id);
                if (basico.getEvolucionSiguiente1() != null && basico.getEvolucionSiguiente2() != null && basico.getEvolucionSiguiente3() != null) {
                    int opcion = 0;
                    while (opcion != 4) {
                        String aux = "";
                        try {
                        StdOut.println("""
                                ¿Desea saber sobre alguna de las siguientes evoluciones?
                                [1] Primera evolucion 1
                                [2] Primera evolucion 2
                                [3] Primera evolucion 3
                                [4] Salir
                                """);
                        aux = StdIn.readString();
                        opcion = Integer.parseInt(aux);
                        switch (opcion) {
                            case 1 -> {
                                System.out.println("La primera evolucion de " + basico.getNombrePokemon() + " es " + basico.getEvolucionSiguiente1());
                                pokemons = listaNexoDoble.obtenerPokemonsNombre(basico.getEvolucionSiguiente1());
                                id = pokemons.getIdPokemon();
                                navegarEvolucionesid(id);
                                opcion = 4;
                            }
                            case 2 -> {
                                System.out.println("La segunda evolucion de " + basico.getNombrePokemon() + " es " + basico.getEvolucionSiguiente2());
                                pokemons = listaNexoDoble.obtenerPokemonsNombre(basico.getEvolucionSiguiente2());
                                id = pokemons.getIdPokemon();
                                navegarEvolucionesid(id);
                                opcion = 4;
                            }
                            case 3 -> {
                                System.out.println("La tercera evolucion de " + basico.getNombrePokemon() + " es " + basico.getEvolucionSiguiente3());
                                pokemons = listaNexoDoble.obtenerPokemonsNombre(basico.getEvolucionSiguiente3());
                                id = pokemons.getIdPokemon();
                                navegarEvolucionesid(id);
                                opcion = 4;
                            }
                            case 4 -> {
                                System.out.println("Regresando...");
                                desplegarNavegacion();
                            }
                            default -> System.out.println("Ingrese una opcion valida");
                        }
                    }catch (NumberFormatException e) {
                            System.out.println("Error! ingrese un valor valiido");
                        }
                    }
                } else if (basico.getEvolucionSiguiente1() != null && basico.getEvolucionSiguiente2() != null) {
                    int opcion = 0;
                    while (opcion != 3) {
                        String aux = "";
                        try {

                        StdOut.println("""
                                ¿Desea saber sobre alguna de las siguientes evoluciones?
                                           
                                [1] Desplegar primera evolucion
                                [2] Desplegar segunda evolucion
                                [3] Salir
                                """);
                        aux = StdIn.readString();
                        opcion = Integer.parseInt(aux);

                        switch (opcion) {
                            case 1 -> {
                                System.out.println("La primera evolucion de " + basico.getNombrePokemon() + " es " + basico.getEvolucionSiguiente1());
                                pokemons = listaNexoDoble.obtenerPokemonsNombre(basico.getEvolucionSiguiente1());
                                id = pokemons.getIdPokemon();
                                navegarEvolucionesid(id);
                                opcion = 3;

                            }
                            case 2 -> {
                                System.out.println("La segunda evolucion de " + basico.getNombrePokemon() + " es " + basico.getEvolucionSiguiente2());
                                pokemons = listaNexoDoble.obtenerPokemonsNombre(basico.getEvolucionSiguiente2());
                                id = pokemons.getIdPokemon();
                                navegarEvolucionesid(id);
                                opcion = 3;
                            }
                            case 3 -> {
                                System.out.println("Regresando...");
                                desplegarNavegacion();
                            }
                            default -> System.out.println("Ingrese una opcion valida");
                        }
                    }catch (NumberFormatException e) {
                            System.out.println("Error! ingrese un valor valiido");
                        }
                    }
                } else if (basico.getEvolucionSiguiente1() != null) {
                    int opcion = 0;
                    while (opcion != 2) {
                        String aux = "";
                        try {
                        StdOut.println("""
                                ¿Desea saber sobre alguna de las siguientes evoluciones?
                                           
                                [1] Desplegar primera evolucion
                                [2] Salir
                                """);
                        aux = StdIn.readString();
                        opcion = Integer.parseInt(aux);

                        switch (opcion) {
                            case 1 -> {
                                System.out.println("La primera evolucion de " + basico.getNombrePokemon() + " es " + basico.getEvolucionSiguiente1());
                                pokemons = listaNexoDoble.obtenerPokemonsNombre(basico.getEvolucionSiguiente1());
                                id = pokemons.getIdPokemon();
                                navegarEvolucionesid(id);
                                opcion = 2;
                            }
                            case 2 -> {
                                System.out.println("Regresando...");
                                desplegarNavegacion();
                            }
                            default -> System.out.println("Ingrese una opcion valida");
                        }
                    }catch (NumberFormatException e) {
                        System.out.println("Error! ingrese un valor valiido");
                    }
                    }
                } else {
                    System.out.println(basico.getNombrePokemon() + " no tiene ninguna envolucion.");
                }
            }

            case "primeraevolucion" -> {

                System.out.println(pokemons);
                PrimeraEvolucion primeraEvolucion = (PrimeraEvolucion) listaNexoDoble.obtenerPokemonsId(id);

                if (primeraEvolucion.getEvolucionSiguiente() != null) {
                    int opcion = 0;
                    while (opcion != 3) {
                        String aux = "";
                        try {
                        StdOut.println("""
                                 ¿Desea saber sobre alguna de las siguientes evoluciones?
                                          
                                 [1] Desplegar evolucion previa
                                 [2] Desplegar segunda evolucion
                                 [3] Salir
                                                                                           
                                """);
                        aux = StdIn.readString();
                        opcion = Integer.parseInt(aux);

                        switch (opcion) {

                            case 1 -> {
                                System.out.println("La evolucion previa de " + primeraEvolucion.getNombrePokemon() + " es " + primeraEvolucion.getEvolucionPrevia());
                                pokemons = listaNexoDoble.obtenerPokemonsNombre(primeraEvolucion.getEvolucionPrevia());
                                id = pokemons.getIdPokemon();
                                navegarEvolucionesid(id);
                                opcion = 3;
                            }
                            case 2 -> {
                                System.out.println("La segunda evolucion de " + primeraEvolucion.getNombrePokemon() + " es " + primeraEvolucion.getEvolucionSiguiente());
                                pokemons = listaNexoDoble.obtenerPokemonsNombre(primeraEvolucion.getEvolucionSiguiente());
                                id = pokemons.getIdPokemon();
                                navegarEvolucionesid(id);
                                opcion = 3;
                            }
                            case 3 -> {
                                System.out.println("Regresando...");
                                desplegarNavegacion();
                            }
                            default -> System.out.println("Ingrese una opcion valida");
                        }
                    }catch (NumberFormatException e) {
                        System.out.println("Error! ingrese un valor valiido");
                    }
                    }
                } else {
                    int opcion = 0;
                    while (opcion != 2) {
                        String aux = "";
                        try {
                        StdOut.println("""
                                ¿Desea saber sobre alguna de las siguientes evoluciones?
                                          
                                [1] Desplegar evolucion previa
                                [2] Salir
                                """);
                        aux = StdIn.readString();
                        opcion = Integer.parseInt(aux);
                        switch (opcion) {
                            case 1 -> {
                                System.out.println("La evolucion previa de " + primeraEvolucion.getNombrePokemon() + " es " + primeraEvolucion.getEvolucionPrevia());
                                pokemons = listaNexoDoble.obtenerPokemonsNombre(primeraEvolucion.getEvolucionPrevia());
                                id = pokemons.getIdPokemon();
                                navegarEvolucionesid(id);
                                opcion = 2;
                            }
                            case 2 -> {
                                System.out.println("Regresando...");
                                desplegarNavegacion();
                            }
                            default -> System.out.println("Ingrese una opcion valida");
                        }
                    }catch (NumberFormatException e) {
                        System.out.println("Error! ingrese un valor valiido");
                    }
                    }
                }
            }
            case "segundaevolucion" -> {
                System.out.println(pokemons);
                SegundoEvolucion segundoEvolucion = (SegundoEvolucion) listaNexoDoble.obtenerPokemonsId(id);

                int opcion = 0;
                while (opcion != 3) {
                    String aux = "";
                    try {
                    StdOut.println("""
                            Desea saber sobre alguna de las siguientes evoluciones?
                                    
                             [1] Desplegar evolucion previa 1
                             [2] Desplegar evolucion previa 2
                             [3] Salir
                                                                                     
                             """);
                    aux = StdIn.readString();
                    opcion = Integer.parseInt(aux);

                    switch (opcion) {
                        case 1 -> {
                            System.out.println("La evolucion previa 1 de " + segundoEvolucion.getNombrePokemon() + " es " + segundoEvolucion.getEvolucionPrevia1());
                            pokemons = listaNexoDoble.obtenerPokemonsNombre(segundoEvolucion.getEvolucionPrevia1());
                            id = pokemons.getIdPokemon();
                            navegarEvolucionesid(id);
                            opcion = 3;
                        }
                        case 2 -> {
                            System.out.println("La evolucion previa 2 de " + segundoEvolucion.getNombrePokemon() + " es " + segundoEvolucion.getEvolucionPrevia2());
                            pokemons = listaNexoDoble.obtenerPokemonsNombre(segundoEvolucion.getEvolucionPrevia2());
                            id = pokemons.getIdPokemon();
                            navegarEvolucionesid(id);
                            opcion = 3;
                        }
                        case 3 -> {
                            System.out.println("Regresando...");
                            desplegarNavegacion();
                        }
                        default -> System.out.println("Ingrese una opcion valida");
                    }
                }catch (NumberFormatException e) {
                    System.out.println("Error! ingrese un valor valiido");
                }
                }
            }
        }
    }
    /**
     * Este metodo va a desplegar el menu de eleccion de búsqueda y llamará al metodo anterior
     */
    @Override
    public void desplegarNavegacion() {
        int opcion = 0;
        while (opcion != 3) {
            String aux;
            try {
                StdOut.println("""
                        ======================================================
                                   ¿Como desea buscar a su Pokemon?
                        ======================================================
                                [1] Por Id
                                [2] Por nombre
                                [3] Volver al menu
                        """);
                aux = StdIn.readString();
                opcion = Integer.parseInt(aux);

                switch (opcion) {
                    case 1 -> {
                        StdOut.println("Ingrese el Id del Pokemon que desea desplegar");
                        int idDes = StdIn.readInt();

                        if (idDes <= 0 || idDes > listaNexoDoble.getCantElementos()) {
                            System.out.println("Error! ID no valido");
                        } else {
                            navegarEvolucionesid(idDes);
                            opcion = 3;
                        }
                    }
                    case 2 -> {
                        StdOut.println("Ingrese el nombre del Pokemon que desa desplegar");
                        String nombrePokemon = StdIn.readString();

                        nombrePokemon = Normalizer.normalize(nombrePokemon, Normalizer.Form.NFD);
                        nombrePokemon = nombrePokemon.replaceAll("[^\\p{ASCII}]", "");
                        String convertir = nombrePokemon.toLowerCase();
                        boolean encontrado = false;

                        for (int i = 1; i <= this.listaNexoDoble.getCantElementos(); i++) {
                            Pokemons pokemons = listaNexoDoble.obtenerPokemonsId(i);

                            if (convertir.equalsIgnoreCase(pokemons.getNombrePokemon())) {
                                int idDes = listaNexoDoble.obtenerPokemonsId(i).getIdPokemon();
                                navegarEvolucionesid(idDes);
                                opcion = 3;
                                encontrado = true;
                            }
                        }
                        if (!encontrado) {
                            System.out.println("Error!, Pokemon no encontrado");

                        }
                    }
                    case 3 -> StdOut.println("Regresando al menu");
                    default -> StdOut.println("Opcion no valida");
                }
            } catch (NumberFormatException e) {
                System.out.println("Error! ingrese un valor valido");
            }
        }
    }
}