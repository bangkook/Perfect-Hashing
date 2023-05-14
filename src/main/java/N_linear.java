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
        System.out.println("index" + index);
        if(search(key)){//TODO
            return false;
        }
        if (hash[index] == null) {
            hash[index] = key;
        } else {
            System.out.println(Level2Hash[index].size());
            int count = (int) Math.sqrt(Level2Hash[index].size());
            count++;
            count = count * count;
            matHashes[index] = new MatHash((int) (Math.log(count) / Math.log(2)));
            ArrayList<String> ReHach = new ArrayList<String>(count);
            for (int i = 0; i < count; i++) {
                ReHach.add(i, null);
            }
            for (int i = 0; i < Level2Hash[index].size(); i++) {//rehaching the past elements
                String element = Level2Hash[index].get(i);
                if(element != null)
                    ReHach.add(matHashes[index].hash(element), element); // Add element at the specified index
            }
            Level2Hash[index] = ReHach;
            int index2 = matHashes[index].hash(key); // get key of the inserted element
            Level2Hash[index].add(index2, key); // Add element at the specified index
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
        N_linear test = new N_linear(20);
        test.insert("nancy");
        test.insert("sara");
        test.insert("saafasdfra");
        test.insert("ssdfsdara");
        test.insert("safdsfra");
        test.insert("saefwqera");
        test.insert("sfdsara");
        test.insert("sardfsdfdsfsdfa");


        test.printHash();
    }
}
