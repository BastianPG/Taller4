package Colecciones;
import Model.Pokemons;

public final class NodoDoble {
    private final Pokemons pokemons;
    /**
     * Apunta al siguiente del nodo
     */
    private NodoDoble siguiente;
    /**
     * Apunta al anterior del nodo
     */
    private NodoDoble anterior;

    /**
     *
     * @param pokemons
     */
    public NodoDoble(Pokemons pokemons) {
        this.pokemons = pokemons ;
        this.siguiente = null;
        this.anterior = null;
    }

    /**
     *
     * @return Retorna el pokemon del nodo
     */
    public Pokemons getPokemons() {
        return pokemons;
    }

    /**
     *
     * @return Retorna el nodo siguiente
     */
    public NodoDoble getSiguiente() {
        return siguiente;
    }

    /**
     *
     * @param siguiente
     * Asigna un valor al nodo siguiente
     */
    public void setSiguiente(NodoDoble siguiente) {
        this.siguiente = siguiente;
    }

    /**
     *
     * @param anterior
     * Asigna un valor al nodo anterior
     */
    public void setAnterior(NodoDoble anterior) {
        this.anterior = anterior;
    }
}

