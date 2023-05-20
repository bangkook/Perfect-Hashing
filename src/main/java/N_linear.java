import java.util.ArrayList;

public class N_linear implements PerfectHashing {
    private int N;
    private int M;
    private int collisions = 0;
    private String[] hash;
    private ArrayList<String>[] Level2Hash;
    private MatHash matHash;
    private MatHash[] matHashes;
    private int FirstLevelCount = 0;


    public N_linear(int n) {
        this.N = n;
        this.M = N; // M = N
        matHash = new MatHash((int) (Math.log(M) / Math.log(2))); // hash level 1 function
        hash = new String[N];
        Level2Hash = new ArrayList[N];
        for (int i = 0; i < N; i++) {
            Level2Hash[i] = new ArrayList<String>();
        }
        matHashes = new MatHash[N];
    }

    public boolean insert(String key) {
        int index = matHash.hash(key); // get key of the inserted element
        if(search(key)){
            return false;
        }
        if (hash[index] == null) {
            hash[index] = key;
            FirstLevelCount += 1 ;
        } else {
            collisions++;
            int count = ((int) Math.sqrt(Level2Hash[index].size())) == 0 ? 1 : (int) Math.sqrt(Level2Hash[index].size());
            count++;
            count = count * count;
            boolean flag = true;
            while (flag) {
                flag = false;
                matHashes[index] = new MatHash((int) (Math.log(count) / Math.log(2)));
                ArrayList<String> ReHach = new ArrayList<String>(count);
                for (int i = 0; i < count; i++) {
                    ReHach.add(i, null);
                }
                for (int i = 0; i < Level2Hash[index].size(); i++) {
                    String element = Level2Hash[index].get(i);
                    if (element != null) {
                        int temp=matHashes[index].hash(element);
                        if(ReHach.get(temp)==null) ReHach.set(temp, element);
                        else{
                            flag=true;
                            break;
                        }
                    }
                }
                if(flag){
                    continue;
                }
                Level2Hash[index] = ReHach;
                int index2 = matHashes[index].hash(key); // get key of the inserted element
                if (Level2Hash[index].get(index2) == null) {
                    Level2Hash[index].set(index2, key); // Sets element at the specified index
                    flag = false;
                }else{
                    flag=true;
                }
            }
        }
        printHash();
        return true;
    }

    public ArrayList<String> rehashing(int count, int index){
//        matHashes[index] = new MatHash((int) (Math.log(size) / Math.log(2)));
//        ArrayList<String> ReHach = new ArrayList<String>(size);
//        for (int i = 0; i < size; i++) {
//            ReHach.add(i, null);
//        }
//        for (int i = 0; i < Level2Hash[index].size(); i++) {//rehaching the past elements
//            String element = Level2Hash[index].get(i);
//            if(element != null){
//                ReHach.set(matHashes[index].hash(element), element); // Sets element at the specified index
//                //System.out.println("matHashes = "+matHashes[index].hash(element));
//            }
//        }
        ArrayList<String> ReHach=new ArrayList<String>(count);
        boolean flag = true;
        while (flag) {
            flag = false;
            matHashes[index] = new MatHash((int) (Math.log(count) / Math.log(2)));
            ReHach.clear();
            for (int i = 0; i < count; i++) {
                ReHach.add(i, null);
            }
            for (int i = 0; i < Level2Hash[index].size(); i++) {
                String element = Level2Hash[index].get(i);
                if (element != null) {
                    int temp=matHashes[index].hash(element);
                    if(ReHach.get(temp)==null) ReHach.set(temp, element);
                    else{
                        flag=true;
                        break;
                    }
                }
            }
            if(flag){
                continue;
            }
            Level2Hash[index] = ReHach;
        }
        return ReHach;
    }
    @Override
    public boolean delete(String key) {
        if(!search(key)){//TODO
            System.out.println("cant be deleted");
            return false;
        }else {
            int index = matHash.hash(key);
            if (hash[index].equals(key)) {
                if (Level2Hash[index].size() == 0) {
                    hash[index] = null;
                } else {
                    String element = null;
                    for (int i = 0; i < Level2Hash[index].size(); i++) { //searching for the first element in small hash table
                        if (Level2Hash[index].get(i) != null) {
                            element = Level2Hash[index].get(i);
                            Level2Hash[index].set(i, null);
                            break;
                        }
                    }
                    hash[index] = element;
                    int size = (int) (Math.sqrt(Level2Hash[index].size()) - 1);
                    //System.out.println("size1: "+ size);
                    if(size >1) {
                        ArrayList<String> ReHach = rehashing(size * size, index);
                        Level2Hash[index]=ReHach;
                    } else{
                      //  System.out.println("size ="+size);
                        Level2Hash[index].clear();
                        matHashes[index]=null;
                    }
                }
            } else {
                int index2 = matHashes[index].hash(key);
                if ((Level2Hash[index].get(index2)).equals(key)) {
                    Level2Hash[index].set(index2, null);
                    int size = (int) (Math.sqrt(Level2Hash[index].size()) - 1);
                    //System.out.println("size2: "+ size);
                    if(size >1) {
                        ArrayList<String> ReHach = rehashing(size * size, index);
                        Level2Hash[index]=ReHach;
                    } else{
                    //    System.out.println("size ="+size);
                        Level2Hash[index].clear();
                        matHashes[index]=null;

                    }
                }
            }
        }
        printHash();
        return true;
    }

    @Override
    public boolean search(String key) {
        int index = matHash.hash(key);
        // System.out.println("1");
        return hash[index] != null && (key.equals(hash[index]) || (matHashes[index] != null && Level2Hash[index].get(matHashes[index].hash(key)) != null && (Level2Hash[index].get(matHashes[index].hash(key))).equals(key)));
    }



    public int getCollisions() {
        return collisions;
    }

    public int getSize() {
        int size = 0;
        for (int i = 0; i < N; i++) {
            size += Math.max(1, Level2Hash[i].size());
        }
        return size;
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
    @Override
    public int getInserted(){return FirstLevelCount;}

    public static void main(String[] args) {
        N_linear test = new N_linear(6);
        test.insert("nancy");
        System.out.println("hash--------------");
        test.printHash();
        test.insert("sara");
        System.out.println("hash--------------");
        test.printHash();
        test.insert("saraa");
        System.out.println("hash--------------");
        test.printHash();
        test.insert("saraaa");
        System.out.println("hash--------------");
        test.printHash();
        test.insert("saraaaaa");
        System.out.println("hash-----finalllll----");
        test.printHash();
        test.delete("sara");
//        test.insert("saraaaaaaaaaaaa");
        System.out.println("hash-----delete----");
        test.printHash();
    }
}
