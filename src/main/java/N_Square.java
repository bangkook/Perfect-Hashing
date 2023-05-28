public class N_Square implements PerfectHashing{
    private int N;
    private int collisions = 0;
    private String[] hash;
    private MatHash matHash;
    private int M; // M = N^2
    private int noOfInserted = 0;
    int reh = 0;

    private static int nearestGreaterPowerOfTwo(int num) {
        return (int)Math.pow(2, Math.ceil(Math.log(num) / Math.log(2)));
    }
    public N_Square(int n) {
        this.N = n;
        int N_sqBefore= N*N;
        this.M = nearestGreaterPowerOfTwo(N_sqBefore);
        this.matHash = new MatHash((int)(Math.log(M)/Math.log(2)));
        this.hash = new String[M];
    }

    @Override
    public boolean insert(String key) {
        if(noOfInserted >= N)
            return false;
        noOfInserted++;
        return insert(key,hash);
    }

    boolean insert(String key, String[] hash2){
        int index = matHash.hash(key);
        if (hash2[index] == null) { //empty, we can insert
            hash2[index] = key;
        } else if (hash2[index].equals(key)) {
            return false; // already inserted
        } else {
            collisions++;
            // collide
            String[] h = rehash(key, hash2);
            System.arraycopy(h, 0, hash2, 0,M);
        }
        return true;
    }

    private String[] rehash(String key, String[] oldhash) {
        reh++;
        String []newHash = new String[M];
        matHash = new MatHash((int)(Math.log(M)/Math.log(2)));
        for(String str : oldhash){
            if(str != null){
                insert(str,newHash);
            }
        }
        insert(key,newHash);
        reh--;
        return newHash;
    }

    public void printHash(String[] h){
        for(String s : h){
            System.out.print(s +" , ");
        }
        System.out.println("\n------------------");
    }
    public void printHashog(){
        for(String s : hash){
            System.out.print(s +" , ");
        }
        System.out.println("\n------------------");
    }

    @Override
    public boolean delete(String key) {
        if(search(key)){
            int index = matHash.hash(key);
            hash[index]=null;
            noOfInserted--;
            return true;
        }
        return false;
    }

    @Override
    public boolean search(String key) {
        int index = matHash.hash(key);
        return hash[index] != null && hash[index].equals(key);
    }


    public int getCollisions() {
        return collisions;
    }

    public int getSize() {
        return M;
    }

    public static void main(String[] args) {
        N_Square n2 = new N_Square(2);
        boolean res1 = n2.insert("Maria");
        boolean res2 = n2.insert("Mari");
        boolean res3 = n2.insert("Eman");
        boolean res4 = n2.insert("Mariam");

        System.out.println(res1);
        System.out.println(res2);
        System.out.println(res3);
        System.out.println(res4);
        n2.printHashog();
        System.out.println(n2.search("Ema"));
        System.out.println(n2.delete("Ema"));
        n2.printHashog();
        System.out.println(n2.delete("Maria"));
        n2.printHashog();
        System.out.println(n2.delete("Maria"));
        n2.printHashog();
        System.out.println(n2.delete("Mari"));
        n2.printHashog();
        System.out.println(n2.delete("Eman"));
        n2.printHashog();
        System.out.println(n2.delete("Mariam"));
        n2.printHashog();
        System.out.println(n2.delete("Maria"));
        n2.printHashog();
        System.out.println("yyyyy");

    }
}
