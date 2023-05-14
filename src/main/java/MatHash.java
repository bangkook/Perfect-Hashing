import java.util.Random;

public class MatHash {
    Random R = new Random();
    int[] hashMatrix; // h
    int size;

    public MatHash(int size) {
        this.size = size;
        this.hashMatrix = new int[size];
        for(int i = 0; i < size; i++) {
            hashMatrix[i] = R.nextInt(); //32
        }
    }

    public int hash(int key) {
        int hx = 0;
        for(int i = 0; i < size; i++) {
            hx <<= 1;
            int x =  getParity(key & hashMatrix[i]);
            System.out.println("x= "+x);
            hx |= x;
            System.out.println("hx= "+hx);
        }
        return hx;
    }

    public int hash(String key) {
        return hash(key.hashCode());
    }

    private int getParity(int n)
    {
        return Integer.bitCount(n) % 2;
    }
    public static void main(String[] args){
        int M = 1000; // suppose N is 100 where M = N ^ 2
        // 2^b = M --> b = log2(M) = log(M)/log(2)
        MatHash matHash = new MatHash((int)(Math.log(M)/Math.log(2)));
        System.out.println(matHash.hash("Hello"));
        System.out.println(matHash.hash("Happy"));
        System.out.println(matHash.hash("Dappy"));
        System.out.println(matHash.hash("Samy"));
    }
}
