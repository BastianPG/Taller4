package Model;

public class SegundoEvolucion extends Pokemons {
    /**
     * Evolucion previa 1 del Pokemon de etapa segunda evolucion
     */
    private String evolucionPrevia1;
    /**
     * Evolucion previa 2 del Pokemon de etapa segunda evolucion
     */
    private String evolucionPrevia2;

    /**
     *
     * @param idPokemon
     * @param nombrePokemon
     * @param etapa
     * @param evolucionPrevia1
     * @param evolucionPrevia2
     * @param tipo1
     * @param tipo2
     *
     * Constructor de la clase Segunda evolucion
     */
    public SegundoEvolucion(int idPokemon, String nombrePokemon, String etapa, String evolucionPrevia1, String evolucionPrevia2, String tipo1, String tipo2) {
        super(idPokemon, nombrePokemon, etapa, tipo1, tipo2);
        this.evolucionPrevia1 = evolucionPrevia1;
        this.evolucionPrevia2 = evolucionPrevia2;
    }
    /**
     *
     * @return Retorna la evolucion previa 1 del Pokemon etapa segunda evolucion
     */
    public String getEvolucionPrevia1() {
        return evolucionPrevia1;
    }
    /**
     *
     * @return Retorna la evolucion previa 2 del Pokemon etapa segunda evolucion
     */
    public String getEvolucionPrevia2() {
        return evolucionPrevia2;
    }
    /**
     *
     * @return Retorna los datos del pokemon
     */
    public String toString(){

        String datos2daEvol = this.getIdPokemon()+" - "+this.getNombrePokemon()+" - "+this.getEtapa()+" - "+this.evolucionPrevia1+" - "+this.evolucionPrevia2+" - "+this.getTipo1()+" - "+this.getTipo2();
        return datos2daEvol;
    }

}
