/**
 * @author Turczyn Magdalena S22700
 */

package zad2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.stream.Collectors;

public class Anagrams {

    List<String> words = new ArrayList<>();

    public Anagrams(String pathName) throws FileNotFoundException {
        Scanner scan = new Scanner(new File(pathName));
        while (scan.hasNext()) {
            words.add(scan.next());
        }
        scan.close();
    }

    public List<List<String>> getSortedByAnQty() {
        List<List<String>> anagrams = new ArrayList<>();
        List<String> sublist = new ArrayList<>();
        for (int i = 0; i < words.size(); i++) {
            for (String word : words) {
                if (isAnagram(words.get(i), word)) {
                    sublist.add(word);
                }
            }
            anagrams.add(sublist.stream()
                    .sorted()
                    .collect(Collectors.toList()));
            sublist.clear();
        }
        //sortowanie listy po ilosci elementow
        anagrams = anagrams.stream()
                .sorted((list1, list2) -> {
                    if (list1.size() == list2.size()) {
                        return list1.get(0).compareTo(list2.get(0));
                    } else {
                        return (Integer.compare(list2.size(), list1.size()));
                    }
                })
                .distinct()
                .collect(Collectors.toList());
        return anagrams;
    }

    public String getAnagramsFor(String word) {
        List<String> sublist = new ArrayList<>();
        for (String s : words) {
            if (isAnagram(word, s) && !word.equals(s)) {
                sublist.add(s);
            }
        }
        sublist = sublist.stream()
                .sorted()
                .collect(Collectors.toList());
        if (sublist.isEmpty()) {
            return word + ": " + sublist;
        }
        if (!words.contains(word)) {
            return word + ": " + "null";
        }
        return word + ": " + sublist;
    }

    static boolean isAnagram(String word1, String word2) {
        if (word1 == null || word2 == null || word1.equals("") || word2.equals("")){
            throw new IllegalArgumentException();
        }

        if (word1.length() != word2.length()){
            return false;
        }

        word1 = word1.toLowerCase();
        word2 = word2.toLowerCase();
        Map<Character, Integer> map = new HashMap<>();

        for (int i = 0; i < word1.length(); i++) {
            char letter = word1.charAt(i);
            if (!map.containsKey(letter)) {
                map.put(letter, 1);
            } else {
                Integer frequency = map.get(letter);
                map.put(letter, ++frequency);
            }
        }

        //sprawdzanie liter word2 względem data w mapie
        for (int i = 0; i < word2.length(); i++) {
            char letter = word2.charAt(i);
            if (!map.containsKey(letter)){
                return false;
            }
            Integer frequency = map.get(letter);
            if (frequency == 0){
                return false;
            } else{
                map.put(letter, --frequency);
            }
        }
        return true;
    }
}  
