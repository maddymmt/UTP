package zad1;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Collections;

public class Futil {

    public static void processDir(String startingDirectory, String resultFileName) {

        File resultFile = new File(resultFileName);

        try {
            Files.walk(Paths.get(startingDirectory))
                    .filter(path -> path.toString().endsWith(".txt"))
                    .forEach(path -> {
                                try {
                                    Files.lines(path, Charset.forName("CP1250"))
                                            .forEach(line -> {
                                                try {
                                                    Files.write(resultFile.toPath(), Collections.singleton(line), StandardCharsets.UTF_8, StandardOpenOption.APPEND, StandardOpenOption.CREATE, StandardOpenOption.WRITE);
                                                } catch (IOException e) {
                                                    e.printStackTrace();
                                                }
                                            });
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
                    );
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
