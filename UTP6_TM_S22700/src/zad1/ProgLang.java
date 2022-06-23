package zad1;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class ProgLang {

    Map<String, List<String>> languageMap = new LinkedHashMap<>();
    Map<String, List<String>> programmersMap = new LinkedHashMap<>();

    //konstruktor ProgLang(String nazwaPliku), w którym następuje wczytanie pliku o podanej nazwie,
    public ProgLang(String pathName) {
        try {
            Files.readAllLines(Paths.get(pathName)).forEach(line -> {
                String[] words = line.split("\t");
                List<String> progamersList = Arrays.asList(words).subList(1, words.length).stream()
                        .distinct()
                        .collect(Collectors.toList());
                languageMap.put(words[0], progamersList);

                progamersList.forEach(name -> {
                    if (programmersMap.containsKey(name)) {
                        programmersMap.get(name).add(words[0]);
                    } else {
                        List<String> languages = new ArrayList<>();
                        languages.add(words[0]);
                        programmersMap.put(name, languages);
                    }
                });
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //metodę getLangsMap() - zwracająca mapę, w której pod kluczem nazwa języka znajduje się kolekcja programistów tego języka,
    public Map<String, List<String>> getLangsMap() {
        return languageMap;
    }

    //metodę getProgsMap() - zwracającą mapę, w której pod kluczem nazwisko programisty znajduje się kolekcja języków, w których programuje,
    public Map<String, List<String>> getProgsMap() {
        return programmersMap;
    }

    //metodę getLangsMapSortedByNumOfProgs()  - zwracającą mapę z wejściami  język -> kolekcja programistów. uporządkowaną malejąco według liczby osób znających poszczególne języki, w przypadku równej liczbu porządek jest alfabetyczny wg nazw języków,
    public Map<String, List<String>> getLangsMapSortedByNumOfProgs() {
        return sorted(languageMap, (map1, map2) -> {
            if (map1.getValue().size() == map2.getValue().size()) {
                return map1.getKey().compareTo(map2.getKey());
            } else {
                return (Integer.compare(map2.getValue().size(), map1.getValue().size()));
            }
        });
    }

    //metodę getProgsMapSortedByNumOfLangs() - zwracającą mapę z wejścimi programista -> kolekcja językow, uporządkowaną malejąco wg liczby języków znanych programiści; w przypadku równej liczby porządek jest alfabetyczny wg nazwisk,
    public Map<String, List<String>> getProgsMapSortedByNumOfLangs() {
        return sorted(programmersMap, (map1, map2) -> {
            if (map1.getValue().size() == map2.getValue().size()) {
                return map1.getKey().compareTo(map2.getKey());
            } else {
                return (Integer.compare(map2.getValue().size(), map1.getValue().size()));
            }
        });
    }

    //metodę getProgsMapForNumOfLangsGreaterThan(int n) - zwracającą mapę z wejściami programista -> kolekcja języków, dla ktorych liczba języków jest większa od podanego n.
    public Map<String, List<String>> getProgsMapForNumOfLangsGreaterThan(int n) {
        return filtered(programmersMap, x -> x.getValue().size() > n);
    }

    //metodę sorted(...), wołaną z argumentami mapa i lambda-wyrażenie. Metoda zwraca posortowaną wersję dowolnej mapy przekazanej jako piewrszy argument, a porządek sortowania jest określony przez lambda wyrażenia, podane jako drugi argument,
    //utp prezentacja
    static <K, V> Map<K, List<V>> sorted(Map<K, List<V>> map, Comparator<Map.Entry<K, List<V>>> comparator) {
        List<Map.Entry<K, List<V>>> entries = new ArrayList<>(map.entrySet());
        entries.sort(comparator);
        LinkedHashMap<K, List<V>> linkedMap = new LinkedHashMap<>();
        entries.forEach(e -> linkedMap.put(e.getKey(), e.getValue()));
        return linkedMap;
    }

    //metodę filtered(...) z argumentami: dowolna mapa i  lambda. Metoda zwraca  mapę, która zawiera tylko te wejścia z przekazanej jako pierwszy argument mapy, które spelniają warunek podany jako drugi argument (lambda z wynikiem typu boolean).
    static <K, V> Map<K, List<V>> filtered(Map<K, List<V>> map, Predicate<Map.Entry<K, List<V>>> predicate) {
        List<Map.Entry<K, List<V>>> entries = new ArrayList<>(map.entrySet());
        entries = entries.stream().filter(predicate).collect(Collectors.toList());
        LinkedHashMap<K, List<V>> linkedMap = new LinkedHashMap<>();
        entries.forEach(e -> linkedMap.put(e.getKey(), e.getValue()));
        return linkedMap;
    }
    //Metod sorted(...) lub filtered(...) użyć w oprogramowaniu innych, odpowiednich, metod klasy. Mają one jednak ogólniejsze znaczenia, bo mogą być używane dla dowolnych innych map  z warunkami sortowania czy filtrowania, zadawanymi przez własściwe w danych przypadkach lambdy.
    //Uwaga: uniwersalność metod sorted i filtered )możliwość ich zasobędzie sprawdzana
}
