package Colecciones;

import Model.Pokemons;

public class ListaNexoDoble {
    /**
     * La cabeza o inicio del nodo
     */
    private NodoDoble cabeza;
    /**
     * La cola o final del nodo
     */
    private NodoDoble cola;
    /**
     * La cantidad de nodos que hay
     */
    private int cantElementos;

    /**
     * Contructor de la lista Nodo Doble
     */
    public ListaNexoDoble() {
        this.cabeza = null;
        this.cola = null;
        this.cantElementos = 0;
    }
    /**
     *
     * @param id
     * @return Retorna el pokemon buscado por su id
     */
    public Pokemons obtenerPokemonsId(int id){
        if(this.cabeza== null){
            return null;
        }
        if(this.cola.getPokemons().getIdPokemon() == id){
            return this.cola.getPokemons();
        }

        NodoDoble aux = this.cabeza;
        while(aux != this.cola && aux != null){
            if(aux.getPokemons().getIdPokemon() == id){
                return aux.getPokemons();
            }
            aux = aux.getSiguiente();
        }
        return null;
    }
    /**
     *
     * @param nombre
     * @return Retorna el pokemon buscado por su nombre
     */
    public Pokemons obtenerPokemonsNombre(String nombre){
        if(this.cabeza== null){
            return null;
        }
        if(this.cola.getPokemons().getNombrePokemon().equals(nombre)){
            return this.cola.getPokemons();
        }

        NodoDoble aux = this.cabeza;
        while(aux != this.cola && aux != null){
            if(aux.getPokemons().getNombrePokemon().equals(nombre)){
                return aux.getPokemons();
            }
            aux = aux.getSiguiente();
        }
        return null;
    }
    /**
     *
     * @param pokemons
     * agrega el pokemon al nodo doble
     */
    public boolean agregarPokemon(Pokemons pokemons){
        NodoDoble nuevoNodo = new NodoDoble(pokemons);

        if(this.cabeza == null){
            this.cabeza = nuevoNodo;
            this.cola = nuevoNodo;
            this.cantElementos++;
            return true;
        }

        if(obtenerPokemonsId(pokemons.getIdPokemon()) != null ){
            return false;
        }

        this.cola.setSiguiente(nuevoNodo);
        nuevoNodo.setAnterior(this.cola);
        this.cola= nuevoNodo;
        this.cantElementos++;
        return true;

    }
    public Pokemons obtenerPokemonPos(int index){
        if(this.cabeza == null){
            return null;
        }
        if(index < 0 || index >= this.cantElementos){
            return null;
        }
        NodoDoble aux = this.cabeza;
        for(int i = 0; i < index; i++){
            aux = aux.getSiguiente();
        }

        if(aux != null){
            return aux.getPokemons();
        }
        return null;
    }
    /**
     *
     * @return Retorna la cantidad de elementos
     */
    public int getCantElementos() {
        return cantElementos;
    }
}
