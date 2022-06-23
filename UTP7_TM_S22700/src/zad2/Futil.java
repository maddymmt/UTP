package zad2;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.List;

import static java.lang.System.out;

public class Futil {
    public static void processDir(String startingDirectory, String resultFileName) {
        File resultFile = new File(resultFileName);
        Path startingDirectoryPath = Paths.get(startingDirectory);
        if(resultFile.exists() && resultFile.isFile()){
            out.println(resultFile.delete());
        }
        try {
            Files.walkFileTree(startingDirectoryPath, new SimpleFileVisitor<Path>() {
                        @Override
                        public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                            out.println("visitFile: " + file);
                            if (file.toString().endsWith(".txt")) {
                                List<String> lines = Files.readAllLines(file, Charset.forName("CP1250"));
                                Files.write(resultFile.toPath(), lines, StandardCharsets.UTF_8, StandardOpenOption.APPEND, StandardOpenOption.CREATE,StandardOpenOption.WRITE);
                            }
                            return FileVisitResult.CONTINUE;
                        }
                    }
            );
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
