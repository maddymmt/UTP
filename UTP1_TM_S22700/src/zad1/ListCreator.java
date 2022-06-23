/**
 *
 *  @author Turczyn Magdalena S22700
 *
 */

package zad1;


import java.util.ArrayList;
import java.util.List;

public class ListCreator <T> { // Uwaga: klasa musi być sparametrtyzowana

    // lub: class ListCreator <T> extends ArrayList<T>
    List <T> lista = new ArrayList<T>();

    static <T> ListCreator<T> collectFrom(List<T> listaNowa) {
        ListCreator<T> listaWynikowa = new ListCreator<T>();
        listaWynikowa.lista = listaNowa;
        return listaWynikowa;
    }

    ListCreator<T> when(Selector<T> sel) {

        List<T> listaWynikowa = new ArrayList<T>();
        for (int i = 0; i < lista.size(); i++){
            if(sel.select(lista.get(i))) {
                listaWynikowa.add(lista.get(i));
            }
        }
        lista = listaWynikowa;
        return this;
    }

	<S> List<S> mapEvery(Mapper<T, S> map) {
        List<S> listaWynikowa = new ArrayList<S>();
        for (int i = 0; i < lista.size(); i++){
            listaWynikowa.add(map.map(lista.get(i)));
        }
        return listaWynikowa;
    }
}  
