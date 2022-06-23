/**
 * @author Turczyn Magdalena S22700
 */

package zad1;


import java.io.*;

public class Finder {

    private final String path;
    public Finder(String file){
        path = file;
    }

    public int getIfCount() throws IOException {
        InputStream inputStream = new FileInputStream(path);
        Reader reader = new BufferedReader(new InputStreamReader(inputStream));
        StreamTokenizer streamTokenizer = new StreamTokenizer(reader);
        streamTokenizer.slashSlashComments(true);
        streamTokenizer.slashStarComments(true);
        int value = 0;
        int currentToken = streamTokenizer.nextToken();

        while (currentToken != StreamTokenizer.TT_EOF) {
            if ((streamTokenizer.ttype == StreamTokenizer.TT_WORD) && streamTokenizer.sval.equals("if")) {
                value++;
            }
            currentToken = streamTokenizer.nextToken();
        }
        return value;
    }

    public int getStringCount(String word) throws IOException {
        InputStream inputStream = new FileInputStream(path);
        Reader reader = new BufferedReader(new InputStreamReader(inputStream));
        int DOUBLE_QUOTE_CHARACTER = '"';
        StreamTokenizer streamTokenizer = new StreamTokenizer(reader);
        streamTokenizer.slashSlashComments(true);
        streamTokenizer.slashStarComments(true);
        int value = 0;
        int currentToken = streamTokenizer.nextToken();

        while (currentToken != StreamTokenizer.TT_EOF) {
            if ((streamTokenizer.ttype == DOUBLE_QUOTE_CHARACTER) && streamTokenizer.sval.equals(word)) {
                value++;
            }
            currentToken = streamTokenizer.nextToken();
        }
        return value;
    }
}
