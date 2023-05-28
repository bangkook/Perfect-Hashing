import org.junit.Test;
import java.awt.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.Assert.assertTrue;

public class DictionaryTest {
    @Test
    public void testSingleInsert() {
        int size = 1;
        // test with N_Linear
        Dictionary dict1 = new Dictionary(new N_linear(size),size);
        assertTrue(dict1.insert("Hello"));
        assertFalse(dict1.insert("Hello"));

        // test with N_Square
        Dictionary dict2 = new Dictionary(new N_Square(size),size);
        assertTrue(dict2.insert("Hello"));
        assertFalse(dict2.insert("Hello"));
    }

    @Test
    public void testSingleDelete(){
        int size = 1;
        // test with N_Linear
        Dictionary dict1 = new Dictionary(new N_linear(size),size);
        dict1.insert("Hello");
        dict1.insert("CSED");
        assertTrue(dict1.delete("Hello"));
        assertFalse(dict1.delete("Hello"));

        // test with N_Square
        Dictionary dict2 = new Dictionary(new N_Square(size),size);
        dict2.insert("Hello");
        assertTrue(dict2.delete("Hello"));
        assertFalse(dict2.delete("Hello"));
    }
    @Test
    public void testSearch(){
        int size = 5;
        // test with N_Linear
        Dictionary dict1 = new Dictionary(new N_linear(size),size);
        dict1.insert("Maria");
        dict1.insert("Mariam");
        dict1.insert("Sara");
        dict1.insert("Nancy");
        dict1.insert("Eman");

        assertTrue(dict1.search("Maria"));
        assertTrue(dict1.search("Mariam"));
        assertTrue(dict1.search("Eman"));
        assertTrue(dict1.search("Nancy"));
        assertTrue(dict1.search("Sara"));
        assertFalse(dict1.search("Maro"));
        assertFalse(dict1.search("Neso"));
        assertFalse(dict1.search("Ema"));
        assertFalse(dict1.search("Saro"));

        // test with N_Square
        Dictionary dict2 = new Dictionary(new N_Square(size),size);
        dict2.insert("Maria");
        dict2.insert("Mariam");
        dict2.insert("Sara");
        dict2.insert("Nancy");
        dict2.insert("Eman");

        assertTrue(dict2.search("Maria"));
        assertTrue(dict2.search("Mariam"));
        assertTrue(dict2.search("Eman"));
        assertTrue(dict2.search("Nancy"));
        assertTrue(dict2.search("Sara"));
        assertFalse(dict2.search("Maro"));
        assertFalse(dict2.search("Neso"));
        assertFalse(dict2.search("Ema"));
        assertFalse(dict2.search("Saro"));
    }

    @Test
    public void testBatchInsertAndDeleteN_linear(){
        int size = 1000;
        // test with N_Linear
        Dictionary dict1 = new Dictionary(new N_linear(size),size);
        Point inserted1 = dict1.batchInsert("D:\\CSE25\\Second year\\Second term\\Data structure 2\\Projects\\Perfect-Hashing\\src\\test\\random.txt");
        System.out.println("Inserted: " + inserted1.x + " , not inserted: "+inserted1.y);
        assertEquals(988,inserted1.x);
        assertEquals(12,inserted1.y);
        Point deleted1 = dict1.batchDelete("D:\\CSE25\\Second year\\Second term\\Data structure 2\\Projects\\Perfect-Hashing\\src\\test\\random_delete.txt");
        System.out.println("Deleted: " + deleted1.x + " , not deleted: "+deleted1.y);
        assertEquals(32,deleted1.x);
        assertEquals(15,deleted1.y);
    }

    @Test
    public void testBatchInsertAndDeleteN_Square(){
        int size = 1000;
        // test with N_Square
        Dictionary dict2 = new Dictionary(new N_Square(size),size);
        Point inserted1 = dict2.batchInsert("D:\\CSE25\\Second year\\Second term\\Data structure 2\\Projects\\Perfect-Hashing\\src\\test\\random.txt");
        System.out.println("Inserted: " + inserted1.x + " , not inserted: "+inserted1.y);
        assertEquals(988,inserted1.x);
        assertEquals(12,inserted1.y);
        Point deleted1 = dict2.batchDelete("D:\\CSE25\\Second year\\Second term\\Data structure 2\\Projects\\Perfect-Hashing\\src\\test\\random_delete.txt");
        System.out.println("Deleted: " + deleted1.x + " , not deleted: "+deleted1.y);
        assertEquals(32,deleted1.x);
        assertEquals(15,deleted1.y);

    }


}