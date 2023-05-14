public class N_Square implements PerfectHashing{
    private int N;
    private String []hash;
    private MatHash matHash;
    private int M; // M = N^2

    public N_Square(int n) {
        this.N = n;
        this.M = N*N;
        this.matHash = new MatHash((int)(Math.log(M)/Math.log(2)));
        this.hash = new String[M];
    }

    @Override
    public boolean insert(String key) {
        return insert(key,hash);
    }

    boolean insert(String key, String[] hash2){
        int index = matHash.hash(key);
        if(hash2[index] == null){ //empty, we can insert
            System.out.println("inserted");
            hash2[index] = key;
        }else if(hash2[index].equals(key)){

            // found same key
            printHash(hash2);
            return false; // already inserted
        }else{
            System.out.println("Collide");
            // collide
            String[] h = rehash(key, hash2);
            System.out.println("h = ");
            printHash(h);
            hash2 = h;
//            String[] rehashed = rehash(key);
//            // recursively call insert with the rehashed array
//            return insert(key, rehashed);
        }
        System.out.println("hash2 = ");
        printHash(hash2);
        return true;
    }

    private String[] rehash(String key, String[] oldhash) {
        System.out.println("in rehash");
        String []newHash = new String[M];
        matHash = new MatHash((int)(Math.log(M)/Math.log(2)));
        for(String str : oldhash){
            if(str != null){
                insert(str,newHash);
            }
        }
        System.out.println("Inserting key in rehash");
        insert(key,newHash);
        return newHash;
    }

    public void printHash(String[] h){
        for(String s : h){
            System.out.print(s +" , ");
        }
        System.out.println("");
    }

    @Override
    public boolean delete(String key) {
        return false;
    }

    @Override
    public boolean search(String key) {
        return false;
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
    }
}
