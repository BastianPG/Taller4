package Model;

public class PrimeraEvolucion extends Pokemons {
    /**
     * Evolucion siguiente del Pokemon etapa primera evolucion si es que la tiene
     */
    private String evolucionSiguiente;
    /**
     * Evolucion previa del Pokemon etapa primera evolucion
     */
    private String evolucionPrevia;
    /**
     *
     * @param idPokemon
     * @param nombrePokemon
     * @param etapa
     * @param evolucionSiguiente
     * @param evolucionPrevia
     * @param tipo1
     * @param tipo2
     *
     * Constructor de la clase Primera evolucion
     */
    public PrimeraEvolucion(int idPokemon, String nombrePokemon, String etapa, String evolucionSiguiente, String evolucionPrevia, String tipo1, String tipo2) {
        super(idPokemon, nombrePokemon, etapa, tipo1, tipo2);
        this.evolucionSiguiente = evolucionSiguiente;
        this.evolucionPrevia = evolucionPrevia;
    }
    /**
     *
     * @return Retorna la evolucion siguiente del Pokemon de etapa primera evolucion si es que la tiene
     */
    public String getEvolucionSiguiente() {
        return evolucionSiguiente;
    }
    /**
     *
     * @return Retorna la evolucion previa del Pokemon de etapa primera evolucion
     */
    public String getEvolucionPrevia() {
        return evolucionPrevia;
    }
    /**
     *
     * @return Retorna los datos del pokemon
     */
    public String toString(){
        if (evolucionSiguiente == null){
            String datos1raEvol = this.getIdPokemon()+" - "+this.getNombrePokemon()+" - "+this.getEtapa()+" - "+this.evolucionPrevia+" - "+this.getTipo1()+" - "+this.getTipo2();
            return datos1raEvol;
        }else{

            String datos1raEvol = this.getIdPokemon()+" - "+this.getNombrePokemon()+" - "+this.getEtapa()+" - "+this.evolucionSiguiente+" - "+this.evolucionPrevia+" - "+this.getTipo1()+" - "+this.getTipo2();
            return datos1raEvol;
        }
    }
}
