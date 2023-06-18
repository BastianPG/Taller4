package Model;

public class Basico extends Pokemons  {
    /**
     * Primera evolucion 1 del Pokemon con etapa basica si es que la tiene
     */
    private String evolucionSiguiente1;
    /**
     * Primera evolucion 2 del Pokemon con etapa basica si es que la tiene
     */
    private String evolucionSiguiente2;
    /**
     * Primera evolucion 3 del Pokemon con etapa basica si es que la tiene
     */
    private String evolucionSiguiente3;
    /**
     *
     * @param idPokemon
     * @param nombrePokemon
     * @param etapa
     * @param evolucionSiguiente1
     * @param evolucionSiguiente2
     * @param evolucionSiguiente3
     * @param tipo1
     * @param tipo2
     *
     * Constructor de la clase Basico
     */
    public Basico(int idPokemon, String nombrePokemon, String etapa, String evolucionSiguiente1, String evolucionSiguiente2, String evolucionSiguiente3, String tipo1, String tipo2) {
        super(idPokemon, nombrePokemon, etapa, tipo1, tipo2);
        this.evolucionSiguiente1 = evolucionSiguiente1;
        this.evolucionSiguiente2 = evolucionSiguiente2;
        this.evolucionSiguiente3 = evolucionSiguiente3;
    }
    /**
     *
     * @return Retorna la evolucion siguiente 1 del Pokemon etapa basica si es que la tiene
      */
    public String getEvolucionSiguiente1() {
        return evolucionSiguiente1;
    }
    /**
     *
     * @return Retorna la evolucion siguiente 2 del Pokemon etapa basica si es que la tiene
     */
    public String getEvolucionSiguiente2() {
        return evolucionSiguiente2;
    }
    /**
     *
     * @return Retorna la evolucion siguiente 3 del Pokemon etapa basica si es que la tiene
     */
    public String getEvolucionSiguiente3() {
        return evolucionSiguiente3;
    }
    /**
     *
     * @return Retorna los datos del pokemon
     */
    public String toString() {
        //Si no tiene evoluciones
        if (this.evolucionSiguiente1 == null && this.evolucionSiguiente2 == null && this.evolucionSiguiente3 == null){
            String datosBasico = this.getIdPokemon()+" - "+this.getNombrePokemon()+" - "+this.getEtapa()+ " - " +this.getTipo1()+" - "+this.getTipo2();
            return datosBasico;
        }
        // Si solo tiene una evolucion
        else if (this.evolucionSiguiente2 == null && evolucionSiguiente3 == null){
            String datosBasico = this.getIdPokemon() + " - " + this.getNombrePokemon() + " - " + this.getEtapa() + " - " + this.getEvolucionSiguiente1() + " - " + this.getTipo1() + " - " + this.getTipo2();
            return datosBasico;
        }
        // Si tiene 2 evoluciones
        else if (this.evolucionSiguiente3 == null){
            String datosBasico = this.getIdPokemon() + " - " + this.getNombrePokemon() + " - " + this.getEtapa() + " - " + this.getEvolucionSiguiente1() + " - " + this.getEvolucionSiguiente2() + " - " + this.getTipo1() + " - " + this.getTipo2();
            return datosBasico;
        }
        // Si tiene 3 evoluciones
        else {
            String datosBasico = this.getIdPokemon() + " - " + this.getNombrePokemon() + " - " + this.getEtapa() + " - " + this.getEvolucionSiguiente1() + " - " + this.getEvolucionSiguiente2() + " - " +  this.getEvolucionSiguiente3() + " - " + this.getTipo1() + " - " + this.getTipo2();
            return datosBasico;
        }
    }
}