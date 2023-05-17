import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;

public class UniversalHashTest {
    @Test
    // Assert hash value don't exceed hash table size
    public void testKeysInRange() {
        int M = 1024; // suppose N is 100 where M = N ^ 2 rounded to nearest power of 2
        // 2^b = M --> b = log2(M) = log(M)/log(2)
        MatHash matHash = new MatHash((int) (Math.log(M) / Math.log(2)));

        // test for 300 words
        List<String> testWords = Collections.emptyList();
        final String rootPath = "C:\\Users\\cyber\\Desktop\\Perfect-Hashing\\src\\test";
        try {
            testWords = Files.readAllLines(Paths.get(rootPath + "\\insert2"), StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (String word : testWords) {
            int hx = matHash.hash(word);
            Assert.assertTrue(hx >= 0 && hx <= M);
        }
    }
}
