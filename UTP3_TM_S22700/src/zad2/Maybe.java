package zad2;

import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

public class Maybe <T>{

    private final T value;

    private Maybe (T value){
        this.value=value;
    }

    public static <T> Maybe<T> of (T value){
        return new Maybe<>(value);
    }

    public void ifPresent(Consumer<T> cons) {
        if (value != null) {
            cons.accept(value);
        }
    }

    public <U> Maybe<U> map(Function<T,U> func){
        Objects.requireNonNull(func);
        if (!isPresent()) {
            return new Maybe<>(null);
        } else {
            return new Maybe<>(func.apply(value));
        }
    }

    public T get(){
        if (value == null) {
            throw new NoSuchElementException("maybe is empty");
        }
        return value;
    }

    public boolean isPresent(){
        return value != null;
    }

    public T orElse(T defVal){
        return value != null ? value : defVal;
    }

    public Maybe<T> filter(Predicate<? super T> predicate){
        Objects.requireNonNull(predicate);
        if (!isPresent()) {
            return this;
        } else {
            return predicate.test(value) ? this : new Maybe<>(null);
        }
    }

    @Override
    public String toString() {
        if (value == null){
            return "Maybe is empty";
        }else{
            return "Maybe has value " + value;
        }
    }
}
