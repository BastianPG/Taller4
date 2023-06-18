package Services;
import java.io.IOException;

public interface Sistema {
    /**
     * Este metodo va a corregir el archivo txt inicial
     */
    void corregirArchivo() throws IOException;
    /**
     * Este metodo a leer el archivo corregido
     */
    void leerArchivo() throws IOException;
    /**
     * Este metodo va a desplegar a los Pokemons por un rango de ID especificado por el usuario en orden creciente
     */
    void desplegarPorId();
    /**
     * Este metodo va a desplegar a los Pokemons en orden alfabético
     */
    void desplegarAlfabeticamente();
    /**
     * Este metodo va a desplegar a los Pokemons por su tipo especificado por el usuario
     */
    void desplegarTipo();
    /**
     * Este metodo va a desplegar a solo los Pokemons de etapa primera evolucion en orden decreciente
     */
    void desplegarPrimeraEvolucion();
    /**
     * Este metodo va a navegar entre lineas evolutivas de Pokemons
     */
    void navegarEvolucionesid(int id);
    /**
     * Este metodo va a desplegar el menu de eleccion de búsqueda y llamará al metodo anterior
     */
    void desplegarNavegacion();
    }
