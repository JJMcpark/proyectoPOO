package utp.poo.proyecto.generics;

import java.util.ArrayList;
import java.util.List;

public class GestorLista<T> {
    private List<T> elementos = new ArrayList<>();

    public void agregar(T elemento) {
        elementos.add(elemento);
    }

    public void eliminar(T elemento) {
        elementos.remove(elemento);
    }

    public List<T> obtenerTodos() {
        return elementos;
    }

    public T buscarPorIndice(int indice) {
        return elementos.get(indice);
    }
}
