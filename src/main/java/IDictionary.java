import java.awt.*;

public interface IDictionary {
    boolean insert(String key);

    boolean delete(String key);

    boolean search(String key);

    Point batchInsert(String fileName);

    Point batchDelete(String fileName);
}
