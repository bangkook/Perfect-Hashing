import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Random;
import java.util.stream.Stream;

public class Comparison {
    private ArrayList<String> generateRandomWords(int count) {
        final String rootPath = "C:\\Users\\cyber\\Desktop\\Perfect-Hashing\\src\\test";
        Random rand = new Random();

        ArrayList<String> insertWords = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            try (Stream<String> lines = Files.lines(Paths.get(rootPath + "\\" + "dictionary.txt"))) {
                int toSkip = rand.nextInt(127142);
                String line = lines.skip(toSkip).findFirst().get();
                insertWords.add(line);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return insertWords;
    }

    public void compare() {
        int N = 8;
        int cnt = 15;
        int avg = 5;
        long start, end;
        for(int i = 0; i < cnt; i++) {
            // [space, time, collision]
            long[] totL = {0, 0, 0}, totQ = {0, 0, 0};
            for(int j = 0; j < avg; j++) {
                N_linear linear = new N_linear(N);
                N_Square quad = new N_Square(N);

                ArrayList<String> words = generateRandomWords(N);
                start = System.nanoTime();
                for(String word : words) {
                    linear.insert(word);
                }
                end = System.nanoTime();
                totL[0] += linear.getSize();
                totL[1] += end - start;
                totL[2] += linear.getCollisions();

                start = System.nanoTime();
                for(String word : words) {
                    quad.insert(word);
                }
                end = System.nanoTime();
                totQ[0] += quad.getSize();
                totQ[1] += end - start;
                totQ[2] += quad.getCollisions();
            }
            System.out.println("At Size = " + N);
            System.out.println("Space: Linear = " + totL[0] / avg + ", Quad = " + totQ[0] / avg);
            System.out.println("Time: Linear = " + totL[1] / avg + ", Quad = " + totQ[1] / avg);
            System.out.println("Collisions: Linear = " + totL[2] / avg + ", Quad = " + totQ[2] / avg);
            N *= 2;
        }
    }
    public static void main(String[] args) {
        Comparison comp = new Comparison();
        comp.compare();
    }
}
