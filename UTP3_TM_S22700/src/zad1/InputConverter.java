package zad1;

import java.util.function.Function;

public class InputConverter <T> {

    T value;
    public <S> S convertBy (Function... f){
        T tmp = value;
        for (Function function : f) {
            tmp = (T) function.apply(tmp);
        }
        return (S) tmp;
    }
    public InputConverter(T new_value) {
        this.value = new_value;
    }
}
