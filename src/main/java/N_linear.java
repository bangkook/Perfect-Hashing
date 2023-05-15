import java.util.ArrayList;

public class N_linear implements PerfectHashing {
    int N;
    String[] hash;
    ArrayList<String>[] Level2Hash;
    MatHash matHash;
    MatHash[] matHashes;

    public N_linear(int n) {
        this.N = n;
        int M = N; // M = N
        matHash = new MatHash((int) (Math.log(M) / Math.log(2))); // hash level 1 function
        hash = new String[N];
        Level2Hash = new ArrayList[N];
        for (int i = 0; i < N; i++) {
            Level2Hash[i] = new ArrayList<String>();
        }
        matHashes = new MatHash[N];
    }

    @Override
    public boolean insert(String key) {
        int index = matHash.hash(key); // get key of the inserted element
        if(search(key)){//TODO
            System.out.println("cant be inserted");
            return false;
        }
        if (hash[index] == null) {
            hash[index] = key;
        } else {
            System.out.println("size at beginning = "+Level2Hash[index].size());
            int count = (int) Math.sqrt(Level2Hash[index].size());
            count++;
            count = count * count;
            System.out.println("count= "+ count);
            matHashes[index] = new MatHash((int) (Math.log(count) / Math.log(2)));
            ArrayList<String> ReHach = new ArrayList<String>(count);
            for (int i = 0; i < count; i++) {
                ReHach.add(i, null);
            }
            for (int i = 0; i < Level2Hash[index].size(); i++) {//rehaching the past elements
                String element = Level2Hash[index].get(i);
                if(element != null){
                    ReHach.set(matHashes[index].hash(element), element); // Sets element at the specified index
                    System.out.println("matHashes = "+matHashes[index].hash(element));
                }
            }
            Level2Hash[index] = ReHach;
            int index2 = matHashes[index].hash(key); // get key of the inserted element
            Level2Hash[index].set(index2, key); // Sets element at the specified index
            System.out.println("size" + Level2Hash[index].size());

        }
        return true;
    }

    @Override
    public boolean delete(String key) {
        return false;
    }

    @Override
    public boolean search(String key) {
        int index = matHash.hash(key);
        if(hash[index] != null && (key.equals(hash[index]) || (matHashes[index] != null && Level2Hash[index].get(matHashes[index].hash(key)) != null &&(Level2Hash[index].get(matHashes[index].hash(key))).equals(key)))){
            System.out.println("1");
            return true;
        }
        return false;
    }
    private void printHash(){
        for (int i = 0; i < N; i++) {
            System.out.print(hash[i] +">>");
            for (int j = 0; j < Level2Hash[i].size(); j++) {
                System.out.print("["+ Level2Hash[i].get(j) +"]" + " ");
            }
            System.out.println("");
        }
    }
    public static void main(String[] args) {
        N_linear test = new N_linear(6);
        test.insert("nancy");
        test.insert("sara");
        test.insert("saraaaa");
        test.insert("saraaaaaaaaa");
        test.insert("saraaaaaaaaaaaa");
        System.out.println(test.search("saro"));
        test.insert("saraaaaaaaaaaaa");


        test.printHash();
    }
}
