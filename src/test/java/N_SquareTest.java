import org.junit.Test;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

public class N_SquareTest {

    @Test
    public void  TestInsert(){
        N_Square hash=new N_Square(3);
        assertTrue(hash.insert("Maria"));
        assertTrue( hash.insert("Mari"));
        assertFalse( hash.insert("Mari"));
    }

    @Test
    public void  TestSearch(){
        N_Square hash=new N_Square(3);
        assertTrue(hash.insert("Maria"));
        assertTrue( hash.insert("Mari"));
        assertTrue( hash.search("Mari"));
        assertFalse( hash.search("Eman"));
    }

    @Test
    public void  TestDelete(){
        N_Square hash=new N_Square(3);
        assertTrue(hash.insert("Maria"));
        assertTrue( hash.insert("Mari"));
        assertTrue( hash.delete("Mari"));
        assertFalse( hash.delete("Eman"));
        assertFalse( hash.search("Mari"));
    }

    @Test
    public void  TestEmpty(){
        N_Square hash=new N_Square(3);
        assertFalse( hash.delete("Mari"));
        assertFalse( hash.search("Eman"));
    }

}