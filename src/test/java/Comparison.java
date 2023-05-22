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
        int avg = 5;
        long start, end;
        for(int i = 8; i <= 8192; i *= 2) {
            // [space, time, collision]
            long[] totL = {0, 0, 0}, totQ = {0, 0, 0};
            for(int j = 0; j < avg; j++) {
                N_linear linear = new N_linear(i);

                ArrayList<String> words = generateRandomWords(i);
                start = System.nanoTime();
                for(String word : words) {
                    linear.insert(word);
                }
                end = System.nanoTime();
                totL[0] += linear.getSize();
                totL[1] += (end - start) / avg;
                totL[2] += linear.getCollisions();

                N_Square quad = new N_Square(i);

                start = System.nanoTime();
                for(String word : words) {
                    quad.insert(word);
                }
                end = System.nanoTime();
                totQ[0] += quad.getSize();
                totQ[1] += (end - start) / avg;
                totQ[2] += quad.getCollisions();

            }
            System.out.println("At Size = " + i);
            System.out.println("Space: Linear = " + totL[0] / avg + ", Quad = " + totQ[0] / avg);
            System.out.println("Time: Linear = " + totL[1] + ", Quad = " + totQ[1]);
            System.out.println("Collisions: Linear = " + totL[2] / avg + ", Quad = " + Math.round(1.0 * totQ[2] / avg));
        }
    }
    public static void main(String[] args) {
        Comparison comp = new Comparison();
        comp.compare();
    }
}
