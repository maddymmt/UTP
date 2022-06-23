package zad2;

import java.io.File;
import java.lang.reflect.*;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;

public class zad2 {

    public static void main(String[] args) {
p
        try {
            URL url = new File("./dane/").toURI().toURL();
            ClassLoader cl = new URLClassLoader(new URL[]{url});
            String classname = "SampleClass";
            Class<?> cls = cl.loadClass(classname);

            //a. wszystkich nadklasach podanej klasy,

            Class<?> tempCls = cl.loadClass(classname);
            while (tempCls != null) {
                System.out.println(tempCls.getName());
                tempCls = tempCls.getSuperclass();
            }
            System.out.println();

            //b. wszystkich konstruktorach (z co najmniej jednym parametrem) zadeklarowanych w tej klasie w formie: modyfikator nazwa(typy parametrów), np. public FirstClass(int, String),
            Constructor<?>[] constructorList = cls.getDeclaredConstructors();
            for (int i = 0; i < constructorList.length; i++) {
                Constructor<?> constructor = constructorList[i];
                Class<?>[] parameterTypes = constructor.getParameterTypes();
                if(parameterTypes.length==0){
                    continue;
                }
                System.out.print(Modifier.toString(constructor.getModifiers()) + " " + constructor.getName() + "(");
                for (int j = 0; j < parameterTypes.length; j++) {
                    System.out.print(parameterTypes[j]);
                    if (j == parameterTypes.length-1){
                        break;
                    }
                    System.out.print(", ");
                }
                System.out.print(")");
                System.out.println();
            }
            System.out.println();

            //c. wszystkich nie-prywatnych metodach zadeklarowanych w tej klasie w formie: modyfikator typWyniku nazwa(typyParametrów), np. public void set(boolean, int),
            Method[] methodList = cls.getDeclaredMethods();
            for (int i = 0; i < methodList.length; i++) {
                Method method = methodList[i];
                Class<?>[] parameterTypes = method.getParameterTypes();
                if (Modifier.isPrivate(method.getModifiers())){
                    continue;
                }
                System.out.print(Modifier.toString(method.getModifiers()) + " " + method.getReturnType() + " " + method.getName() +"(");
                for (int j = 0; j < parameterTypes.length; j++) {
                    System.out.print(parameterTypes[j]);
                    if (j == parameterTypes.length-1){
                        break;
                    }
                    System.out.print(", ");
                }
                System.out.print(")");
                System.out.println();
            }
            System.out.println();

            //d. wszystkich publicznych polach zadeklarowanych w tej klasie w formie: modyfikator typ nazwa, np. public static final java.awt.Color c,
            Field[] fieldList = cls.getDeclaredFields();
            for (int i = 0; i < fieldList.length; i++) {
                Field field = fieldList[i];
                if (Modifier.isPublic(field.getModifiers())){
                    System.out.println(Modifier.toString(field.getModifiers()) + " " + field.getType() + " " + field.getName());
                }
            }
            System.out.println();

            //e. wszystkich polach zadeklarowanych w (bezpośredniej) nadklasie tej klasy, które są dostępne w tej klasie,
            Field[] fieldInharitedList = cls.getSuperclass().getDeclaredFields();
            for (Field field: fieldInharitedList) {
                System.out.println(field);
            }
            System.out.println();

            //f. Następnie na podstawie w/w informacji utworzyć
            // nowy obiekt podanej klasy za pomocą jedngeo z uzyskanych konstruktorów
            // oraz wywołać na rzecz tego obiektu metodę tej klasy.
            // Dla uproszczenia można przyjąć, ze wybrany konstruktor oraz metoda przyjmują jeden argument typu String.

            Method sampleSentence = cls.getMethod("sentence", String.class);
            Object sampleObject = cls.getConstructor(String.class).newInstance("ok");
            System.out.println(sampleSentence.invoke(sampleObject, "raz 3 dwa "));

        } catch (Exception e) {
            System.err.println("Pewnie zla nazwa");
        }

    }


}
