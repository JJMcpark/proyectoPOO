package utp.poo.proyecto.generics;

import java.util.List;
import java.util.function.Predicate;

public class ServicioBusqueda<T> {
    public T buscar(List<T> lista, Predicate<T> criterio) {
        for (T elemento : lista) {
            if (criterio.test(elemento)) {
                return elemento;
            }
        }
        return null;
    }
}
