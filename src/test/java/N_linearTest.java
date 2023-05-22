import org.junit.Test;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

public class N_linearTest {
    @Test
    public void  TestInsert(){
        N_linear hash=new N_linear(3);
        assertTrue(hash.insert("Eman"));
        assertTrue(hash.insert("Sara"));
        assertFalse(hash.insert("Sara"));
        assertTrue(hash.insert("Nancy"));
        assertFalse(hash.insert("Nancy"));
    }

    @Test
    public void  TestSearch(){
        N_linear hash=new N_linear(3);
        assertTrue(hash.insert("Eman"));
        assertTrue(hash.insert("Sara"));
        assertTrue(hash.search("Eman"));
        assertFalse(hash.search("Ema"));
    }

    @Test
    public void  TestDelete(){
        N_linear hash=new N_linear(3);
        assertTrue(hash.insert("Maria"));
        assertTrue(hash.insert("Ema"));
        assertTrue(hash.delete("Ema"));
        assertFalse(hash.delete("Sara"));
        assertFalse(hash.search("Ema"));
    }

    @Test
    public void  TestEmpty(){
        N_linear hash=new N_linear(3);
        assertFalse(hash.delete("Sara"));
        assertFalse(hash.search("Nancy"));
        assertFalse(hash.search("Eman"));
    }

}