public class N_Square implements PerfectHashing{
    private int N;
    private int collisions = 0;
    private String[] hash;
    private MatHash matHash;
    private int M; // M = N^2
    private int noOfInserted = 0;
    int reh = 0;

    public N_Square(int n) {
        this.N = n;
        this.M = N*N;
        this.matHash = new MatHash((int)(Math.log(M)/Math.log(2)));
        this.hash = new String[M];
    }

    @Override
    public boolean insert(String key) {
      //  System.out.println("In insert of N_Square: N  = "+ N);
       // System.out.println("hash in main insert before= ");
     //   printHash(hash);
        noOfInserted++;
//        if(noOfInserted > N){
//            return false;
//        }
        return insert(key,hash);
    }

    boolean insert(String key, String[] hash2){


        int index = matHash.hash(key);
        if(hash2[index] == null){ //empty, we can insert
//            System.out.println("inserted = "+key);
            hash2[index] = key;
//            System.out.println("hash2 inserted= ");
//            printHash(hash2);
//            System.out.println("hash og = ");
//            printHash(hash);
        }else if(hash2[index].equals(key)){

            // found same key
           // System.out.println("found same key");
            //printHash(hash2);
            return false; // already inserted
        }else{
            collisions++;
//            System.out.println("Collide = "+key);
            // collide
//            System.out.println("going in rehash");
            String[] h = rehash(key, hash2);
//            System.out.println("after rehash");
//            System.out.println("h after rehash= ");
            //printHash(h);
//            hash2 = h;
//            hash=h;
            System.arraycopy(h, 0, hash2, 0,M);
//            System.out.println("hash2 after rehash = ");
//            printHash(hash2);
//            System.out.println("hash og = ");
//            printHash(hash);
        }

        return true;
    }

    private String[] rehash(String key, String[] oldhash) {
        reh++;
        //System.out.println("in rehash ="+reh);
        //System.out.println("oldHash in begin of rehash= ");
       // printHash(oldhash);
        String []newHash = new String[M];
        matHash = new MatHash((int)(Math.log(M)/Math.log(2)));
        for(String str : oldhash){
            if(str != null){
                //System.out.println(" str ="+str);
                insert(str,newHash);
            }
        }
       // System.out.println("Inserting key in rehash");
        insert(key,newHash);
        //System.out.println("newHash in end of rehash= ");
       // printHash(newHash);
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
            return true;
        }
        return false;
    }

    @Override
    public boolean search(String key) {
        int index = matHash.hash(key);
        return hash[index] != null && hash[index].equals(key);
    }

    @Override
    public int getInserted() {
        return noOfInserted;
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
