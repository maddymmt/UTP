/**
 *
 *  @author Turczyn Magdalena S22700
 *
 */

package zad1;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Main {
  public static void main(String[] args) {

    Function <String,List<String>> flines = s -> {
      List<String> lines = null;
      try {
        lines = Files.readAllLines(Paths.get(s));
      } catch (IOException e) {
        e.printStackTrace();
      }
      return lines;
    };

    Function <List<String>,String> join = s -> {
        StringBuilder str = new StringBuilder("");
        for (String value : s) {
            str.append(value);
        }
        return str.toString();
    };

    Function <String,List<Integer>> collectInts = s -> {
        List<Integer> list = new ArrayList<>();

        Pattern pat = Pattern.compile("-?\\d+");
        Matcher mat = pat.matcher(s);

        while (mat.find())  {
            list.add(Integer.parseInt(mat.group()));
        }
        return list;
    };
    Function <List<Integer>,Integer> sum = s -> {
        int total = 0;
        for (int i : s) {
            total += i;
        }
        return total;
    };

    /*<--
     *  definicja operacji w postaci lambda-wyrażeń:
     *  - flines - zwraca listę wierszy z pliku tekstowego
     *  - join - łączy napisy z listy (zwraca napis połączonych ze sobą elementów listy napisów)
     *  - collectInts - zwraca listę liczb całkowitych zawartych w napisie
     *  - sum - zwraca sumę elmentów listy liczb całkowitych
     */

    String fname = System.getProperty("user.home") + "/LamComFile.txt";
    InputConverter<String> fileConv = new InputConverter<>(fname);
    List<String> lines = fileConv.convertBy(flines);
    String text = fileConv.convertBy(flines, join);
    List<Integer> ints = fileConv.convertBy(flines, join, collectInts);
    Integer sumints = fileConv.convertBy(flines, join, collectInts, sum);

    System.out.println(lines);
    System.out.println(text);
    System.out.println(ints);
    System.out.println(sumints);

    List<String> arglist = Arrays.asList(args);
    InputConverter<List<String>> slistConv = new InputConverter<>(arglist);
    sumints = slistConv.convertBy(join, collectInts, sum);
    System.out.println(sumints);

  }
}
