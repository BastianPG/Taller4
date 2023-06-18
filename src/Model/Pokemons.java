package Model;

public abstract class Pokemons {
    /**
     * ID unioo del pokemon
     */
    private int idPokemon;
    /**
     * Nombre del pokemon
     */
    private String nombrePokemon;
    /**
     * Etapa actual del pokemon
     */
    private String etapa;
    /**
     * el tipo 1 del pokemon
     */
    private String tipo1;
    /**
     * el tipo 2 del pokemon
     */
    private String tipo2;
    /**
     *
     * @param idPokemon
     * @param nombrePokemon
     * @param etapa
     * @param tipo1
     * @param tipo2
     *
     * Constructor de la clase Pokemon
     */
    public Pokemons(int idPokemon, String nombrePokemon, String etapa, String tipo1, String tipo2) {
        this.idPokemon = idPokemon;
        this.nombrePokemon = nombrePokemon;
        this.etapa = etapa;
        this.tipo1 = tipo1;
        this.tipo2 = tipo2;
    }
    /**
     *
     * @return Retorna el ID del Pokemon
     */
    public int getIdPokemon() {
        return idPokemon;
    }
    /**
     *
     * @return Retorna el nombre del Pokemon
     */
    public String getNombrePokemon() {
        return nombrePokemon;
    }
    /**
     *
     * @return Retorna la etapa actual del Pokemon
     */
    public String getEtapa() {
        return etapa;
    }
    /**
     *
     * @return Retorna el tipo 1 del Pokemon
     */
    public String getTipo1() {
        return tipo1;
    }
    /**
     *
     * @return Retorna el tipo 2 del Pokemon
     */
    public String getTipo2() {
        return tipo2;
    }
    /**
     *
     * @return Retorna los datos del pokemon
     */
    public abstract String toString();{
    }
}
