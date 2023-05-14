public class N_Square implements PerfectHashing{
    int N;
    String []hash;
    MatHash matHash;

    public N_Square(int n) {
        this.N = n;
        int M = N*N; // M = N^2
        matHash = new MatHash((int)(Math.log(M)/Math.log(2)));
        hash = new String[N*N];
    }

    @Override
    public boolean insert(String key) {
        return false;
    }

    @Override
    public boolean delete(String key) {
        return false;
    }

    @Override
    public boolean search(String key) {
        return false;
    }
}
