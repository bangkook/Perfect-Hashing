import java.util.ArrayList;

public class N_linear implements PerfectHashing {
    private int N;
    private int M;
    private int collisions = 0;
    private String[] hash;
    private ArrayList<String>[] HashTable;
    private MatHash matHash;
    private MatHash[] matHashes;
    private int FirstLevelCount = 0;

    private static int nearestGreaterPowerOfTwo(int num) {
        return (int)Math.pow(2, Math.ceil(Math.log(num) / Math.log(2)));
    }

    public N_linear(int n) {
        this.N = nearestGreaterPowerOfTwo(n);
        this.M = N; // M = N
        matHash = new MatHash((int) (Math.log(M) / Math.log(2))); // hash level 1 function
        HashTable = new ArrayList[N];
        for (int i = 0; i < N; i++) {
            HashTable[i] = new ArrayList<>();
        }
        matHashes = new MatHash[N];
    }

    public boolean insert(String key) {
        int index = matHash.hash(key); // get key of the inserted element
        if(search(key)){
            return false;
        }
        else {
            if(matHashes[index] != null && HashTable[index].get(matHashes[index].hash(key)) == null){//if there is a hash Function for this index and the position for the key is not null
                HashTable[index].set(matHashes[index].hash(key),key);
            } else{//first insertion or Collision
                int count = ((int) Math.sqrt(HashTable[index].size()));
                count++;
                count = count * count;
                boolean flag = true;
                while (flag) {
                    flag = false;
                    matHashes[index] = new MatHash((int) (Math.log(count) / Math.log(2)));
                    ArrayList<String> ReHach = new ArrayList<>(count);
                    for (int i = 0; i < count; i++) {
                        ReHach.add(i, null);
                    }
                    for (int i = 0; i < HashTable[index].size(); i++) {
                        String element = HashTable[index].get(i);
                        if (element != null) {
                            int temp = matHashes[index].hash(element);
                            if(ReHach.get(temp) == null) ReHach.set(temp, element);
                            else{
                                collisions++; // Collision happens
                                flag=true;
                                break;
                            }
                        }
                    }
                    if(flag){
                        continue;
                    }
                    HashTable[index] = ReHach;
                    int index2 = matHashes[index].hash(key); // get key of the inserted element
                    if (HashTable[index].get(index2) == null) {
                        HashTable[index].set(index2, key); // Sets element at the specified index
                        flag = false;
                    }else{
                        flag=true;
                        collisions++; // Collision happens
                    }
                }
            }

        }
        return true;
    }

    public ArrayList<String> rehashing(int count, int index){
        ArrayList<String> ReHach=new ArrayList<String>(count);
        boolean flag = true;
        while (flag) {
            flag = false;
            matHashes[index] = new MatHash((int) (Math.log(count) / Math.log(2)));
            ReHach.clear();
            for (int i = 0; i < count; i++) {
                ReHach.add(i, null);
            }
            for (int i = 0; i < HashTable[index].size(); i++) {
                String element = HashTable[index].get(i);
                if (element != null) {
                    int temp=matHashes[index].hash(element);
                    if(ReHach.get(temp) == null) ReHach.set(temp, element);
                    else{
                        flag=true;
                        break;
                    }
                }
            }
            if(flag){
                continue;
            }
            HashTable[index] = ReHach;
        }
        return ReHach;
    }
    @Override
    public boolean delete(String key) {
        if(!search(key)){
            return false;
        }else {
            int index = matHash.hash(key);
                int index2 = matHashes[index].hash(key);
                if ((HashTable[index].get(index2)).equals(key)) {
                    HashTable[index].set(index2, null);
                    int count=0;
                    for(int i = 0; i< HashTable[index].size(); i++){
                        if(HashTable[index].get(i)!=null)count++;
                    }
                    int size=(int)(Math.ceil(Math.sqrt(count)));
                    size*=size;
                    if(size != HashTable[index].size() && count!=0) {
                        ArrayList<String> ReHach = rehashing(size, index);
                        HashTable[index]=ReHach;
                    } else if(count==0) {
                        HashTable[index].clear();
                        matHashes[index]=null;
                    }
                }
            }
        return true;
    }

    @Override
    public boolean search(String key) {
        int index = matHash.hash(key);
        return  (matHashes[index] != null && HashTable[index].get(matHashes[index].hash(key)) != null && (HashTable[index].get(matHashes[index].hash(key))).equals(key));
    }



    public int getCollisions() {
        return collisions;
    }

    public int getSize() {
        int size = 0;
        for (int i = 0; i < N; i++) {
            size += Math.max(1, HashTable[i].size());
        }
        return size;
    }

    private void printHash(){
        for (int i = 0; i < N; i++) {
            System.out.print(i + ": ");
            for (int j = 0; j < HashTable[i].size(); j++) {
                System.out.print("["+ HashTable[i].get(j) +"]" + " ");
            }
            System.out.println("");
        }
        System.out.println("----------------------------------------------------------");
    }
    @Override
    public int getInserted(){return FirstLevelCount;}

    public static void main(String[] args) {
        N_linear test = new N_linear(6);
        test.insert("nancy");
        System.out.println("hash--------------");
//      test.printHash();
        test.insert("sara");
        System.out.println("hash--------------");
//        test.printHash();
        test.insert("saraa");
        System.out.println("hash--------------");
//        test.printHash();
        test.insert("saraaa");
        System.out.println("hash--------------");
//        test.printHash();
        test.insert("saraaaaa");
        System.out.println("hash-----finalllll----");
//        test.printHash();
        System.out.println(test.search("saraa"));
        test.delete("sara");
//        test.insert("saraaaaaaaaaaaa");
        System.out.println("hash-----delete----");
        test.printHash();
    }
}
