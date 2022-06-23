/**
 * @author Turczyn Magdalena S22700
 */

package zad2;

import java.io.*;
import java.net.URL;
import java.util.*;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) throws IOException {

        URL wikipediaTxt = new URL("http://wiki.puzzlers.org/pub/wordlists/unixdict.txt");
        BufferedReader reader = new BufferedReader(new InputStreamReader(wikipediaTxt.openStream()));

        Map<String, List<String>> anagrams = reader
                .lines()
                .collect(Collectors.groupingBy( //zbieram anagramy
                        word -> {
                            char[] chars = word.toLowerCase().toCharArray();
                            Arrays.sort(chars);  //kazde slowo ma ta sama liste posortowanych liter, czyli sa anagramami
                            return new String(chars);
                        },
                        LinkedHashMap::new,
                        Collectors.toList()
                ));

        Integer max = anagrams.values().stream()
                .map(List::size)
                .max(Comparator.comparingInt(x -> x)).get();

        anagrams.values().stream()
                .filter(e -> e.size() == max)
                .sorted()
                .forEach(set -> System.out.println(String.join(" ", set)));

    }
}
