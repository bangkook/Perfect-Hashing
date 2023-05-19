import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Dictionary implements IDictionary{
    private PerfectHashing perfectHash;
    long start, end;

    public Dictionary(PerfectHashing perfectHash) {
        this.perfectHash = perfectHash;
    }
    public Dictionary() {
    }

    @Override
    public boolean insert(String key) {
        return perfectHash.insert(key);
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


    public static void main(String[] args) {
        String redColor = "\u001B[31m";
        String resetColor = "\u001B[0m";
        String greenColor = "\u001B[32m";
        String yellowColor = "\u001B[33m";
        String blueColor = "\u001B[34m";
        String MatColor = "\u001B[35m";
        String cyanColor = "\u001B[36m";
        String whiteColor = "\u001B[37m";
        System.out.println(redColor +" _____           __          _     _    _           _     _   "+ resetColor);
        System.out.println(yellowColor + "|  __ \\         / _|        | |   | |  | |         | |   (_)"+ resetColor);
        System.out.println(cyanColor + "| |__) |__ _ __| |_ ___  ___| |_  | |__| | __ _ ___| |__  _ "+ resetColor);
        System.out.println(MatColor +"|  ___/ _ \\ '__|  _/ _ \\/ __| __| |  __  |/ _` / __| '_ \\| | '_ \\ / _` |"+ resetColor);
        System.out.println(whiteColor + "| |  |  __/ |  | ||  __/ (__| |_  | |  | | (_| \\__ \\ | | | | | | | (_| |"+ resetColor);
        System.out.println(redColor +"|_|   \\___|_|  |_| \\___|\\___|\\__| |_|  |_|\\__,_|___/_| |_|_|_| |_|\\__, |"+ resetColor);
        System.out.println(yellowColor +"                                                                   __/ |"+ resetColor);
        System.out.println(greenColor +"                                                                  |___/ "+ resetColor);
        label:
        while(true){
            Dictionary dic = null;
            Scanner sc= new Scanner(System.in);
            System.out.println("------------------------------------------------------------------------------------------------");
            System.out.println("Choose tree type\n"+"1) O(N)-Space Solution\n" +"2) O(N^2)-Space Solution\n"+"3) Exit");
            System.out.print(whiteColor+"Select the tree or exit: "+resetColor);
            Scanner scanner = new Scanner(System.in);
            String input = scanner.nextLine();
            String operations="";
            System.out.println("Enter the Size of the HashTable");
            int size = sc.nextInt();

            switch (input) {
                case "1":
                    dic = new Dictionary(new N_linear(size));
                    System.out.println("------------------------------------------------------------------------------------------------");
                    System.out.println("Choose operation \n" + "1) Insert\n" + "2) Delete\n" + "3) Search\n" + "4) Batch insert\n" + "5) Batch delete\n" + "6) Size\n" + "7) Height\n" + "8) Return to tree selection");
                    System.out.print(whiteColor + "Select your operation: " + resetColor);
                    operations = sc.nextLine();
                    break;
                case "2":
                    dic = new Dictionary(new N_Square(size));
                    System.out.println("------------------------------------------------------------------------------------------------");
                    System.out.println("Choose operation \n" + "1) Insert\n" + "2) Delete\n" + "3) Search\n" + "4) Batch insert\n" + "5) Batch delete\n" + "6) Size\n" + "7) Height\n" + "8) Return to tree selection");
                    System.out.print(whiteColor + "Select your operation: " + resetColor);
                    operations = sc.nextLine();
                    break;
                case "3":
                    System.out.print(whiteColor + "Thank You ;)" + resetColor);
                    break label;
                default:
                    System.out.println(redColor + "Invalid input" + resetColor);
                    break;
            }
            //sc.nextLine();
            label1:
            while (input.equals("1")||input.equals("2")){
                switch (operations) {
                    case "1": {
                        System.out.print(whiteColor + "Choose your number: " + resetColor);
                        String str = sc.nextLine();
                        if (dic.insert(str)) {
                            System.out.println(greenColor + "Added successfully" + resetColor);
                        } else {
                            System.out.println(yellowColor + "Already exists" + resetColor);
                        }
                        break;
                    }
                    case "2": {
                        System.out.print(whiteColor + "Choose your number: " + resetColor);
                        String str = sc.nextLine();
                        if (dic.delete(str)) {
                            System.out.println(greenColor + "Deleted successfully" + resetColor);
                        } else {
                            System.out.println(yellowColor + "Do not exist" + resetColor);
                        }
                        break;
                    }
                    case "3": {
                        System.out.print(whiteColor + "Choose your number: " + resetColor);
                        String str = sc.nextLine();
                        if (dic.search(str)) {
                            System.out.println(greenColor + "Word found" + resetColor);
                        } else {
                            System.out.println(yellowColor + "Do not exist" + resetColor);
                        }
                        break;
                    }
                    case "4": {
                        System.out.print(whiteColor + "Enter your file name to insert: " + resetColor);
                        String str = sc.nextLine();
                        File file = new File(str);
                        if (file.exists()) {
                            Point p = dic.batchInsert(str);
                            System.out.println(greenColor + "inserted elements: " + p.getX() + resetColor+ yellowColor+", not inserted elements: " + p.getY() + resetColor);
                        } else {
                            System.out.println(yellowColor + "File not found." + resetColor);
                        }
                        break;
                    }
                    case "5": {
                        System.out.print(whiteColor + "Enter your file name to delete: " + resetColor);
                        String str = sc.nextLine();
                        File file = new File(str);
                        if (file.exists()) {
                            Point p = dic.batchDelete(str);
                            System.out.println(greenColor + "deleted elements: " + p.getX() + resetColor+ yellowColor+", not deleted elements: " + p.getY() + resetColor);

                        } else {
                            System.out.println(yellowColor + "File not found." + resetColor);
                        }
                        break;
                    }
                    case "6":
//                        System.out.println(greenColor + "Size of the tree : " + dic.size() + resetColor);
                        break;
                    case "7":
//                        System.out.println(greenColor + "Height of the tree : " + dic.height() + resetColor);
                        break;
                    case "8":
                        break label1;
                    default:
                        System.out.println(redColor + "Invalid input" + resetColor);
                        break;
                }
                System.out.println("------------------------------------------------------------------------------------------------");
                System.out.println("Choose operation \n"+"1) Insert\n" +"2) Delete\n"+"3) Search\n"+"4) Batch insert\n"+"5) Batch delete\n"+"6) Size\n"+"7) Height\n"+"8) Return to tree selection");
                System.out.print(whiteColor+"Select your operation: "+resetColor);
                operations = sc.nextLine();
                //sc.nextLine();
            }
        }
    }
}
