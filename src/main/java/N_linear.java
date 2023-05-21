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

    private static int nearestGreaterPowerOfTwo(int num) {
        int power = 1;
        while (power <= num) {
            power *= 2;
        }
        return power;
    }

    public N_linear(int n) {
        this.N = nearestGreaterPowerOfTwo(n);
        this.M = N; // M = N
        System.out.println("Size of N table = "+ M);
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
        else {
            if(matHashes[index]!=null&& Level2Hash[index].get(matHashes[index].hash(key))==null){
                Level2Hash[index].set(matHashes[index].hash(key),key);
            }else{
                if(matHashes[index]!=null)System.out.println("here "+matHashes[index].hash(key));
                int count = ((int) Math.sqrt(Level2Hash[index].size()));
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
                                collisions++; // Collision happens
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

        }
        printHash();
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
                int index2 = matHashes[index].hash(key);
                if ((Level2Hash[index].get(index2)).equals(key)) {
                    Level2Hash[index].set(index2, null);
                    int count=0;
                    for(int i=0;i<Level2Hash[index].size();i++){
                        if(Level2Hash[index].get(i)!=null)count++;
                    }

//                    int size = (int) (Math.sqrt(Level2Hash[index].size()) - 1);
                    int size=(int)(Math.ceil(Math.sqrt(count)));
                    size*=size;
                    //System.out.println("size2: "+ size);
                    if(size !=Level2Hash[index].size() && count!=0) {
                        ArrayList<String> ReHach = rehashing(size, index);
                        Level2Hash[index]=ReHach;
                    }else if(count==0){
                    //    System.out.println("size ="+size);
                        Level2Hash[index].clear();
                        matHashes[index]=null;

                    }
                }
            }
//        }
        printHash();
        return true;
    }

    @Override
    public boolean search(String key) {
        int index = matHash.hash(key);
        // System.out.println("1");
        return  (matHashes[index] != null && Level2Hash[index].get(matHashes[index].hash(key)) != null && (Level2Hash[index].get(matHashes[index].hash(key))).equals(key));
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
        System.out.println("----------------------------------------------------------");
    }
    @Override
    public int getInserted(){return FirstLevelCount;}

    public static void main(String[] args) {
        N_linear test = new N_linear(6);
        test.insert("nancy");
        System.out.println("hash--------------");
//        test.printHash();
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
//        test.printHash();
    }
}
