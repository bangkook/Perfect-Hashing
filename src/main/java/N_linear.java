import java.util.ArrayList;

public class N_linear implements PerfectHashing{
    int N;
    ArrayList<String>[] hash;
    MatHash matHash;

    public N_linear(int n) {
        this.N = n;
        int M = N; // M = N
        matHash = new MatHash((int)(Math.log(M)/Math.log(2)));
        hash = new ArrayList[N];
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
