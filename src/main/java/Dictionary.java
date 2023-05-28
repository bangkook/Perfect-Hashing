import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;

public class Dictionary implements IDictionary{
    private PerfectHashing perfectHash;
    private int N;
    long start, end;

    public Dictionary(PerfectHashing perfectHash,int size) {
        this.perfectHash = perfectHash;
        this.N = size;
    }
    public Dictionary() {
    }

    @Override
    public boolean insert(String key) {
        if(!perfectHash.insert(key)){
            System.out.println("Can't insert!");
            return false;
        }
        return true;
    }

    @Override
    public boolean delete(String key) {
        return perfectHash.delete(key);
    }

    @Override
    public boolean search(String key) {
        return perfectHash.search(key);
    }

    @Override
    public Point batchInsert(String fileName) {
        List<String> words = readFile(fileName);
        int inserted = 0;
        start = System.nanoTime();
        for (String word : words)
            if (this.perfectHash.insert(word))
                inserted = inserted + 1;
        end = System.nanoTime();
        return new Point(inserted, words.size() - inserted);
    }

    @Override
    public Point batchDelete(String fileName) {
        List<String> words = readFile(fileName);
        int deleted = 0;
        start = System.nanoTime();
        for (String word : words)
            if (this.perfectHash.delete(word))
                deleted = deleted + 1;
        end = System.nanoTime();
        return new Point(deleted, words.size() - deleted);
    }

    private List<String> readFile(String fileName) {
        List<String> words = Collections.emptyList();
        try {
            words = Files.readAllLines(Paths.get(fileName), StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return words;
    }
    public long getTime() {
        return end - start;
    }
}
