public interface PerfectHashing {
    boolean insert(String key);
    boolean delete(String key);
    boolean search(String key);
    int getInserted();
}
