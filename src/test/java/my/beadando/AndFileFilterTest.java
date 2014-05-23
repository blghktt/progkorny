package my.beadando;

import java.io.File;
import org.junit.Test;
import static org.junit.Assert.*;

public class AndFileFilterTest {

    @Test
    public void testAddFileFilter() {
        WildcardFileFilter f1 = new WildcardFileFilter("?a*ma.*");
        WildcardFileFilter f2 = new WildcardFileFilter("k*or*t?e.*");
        
        AndFileFilter andFilter = new AndFileFilter();
        
        andFilter.addFileFilter(f1);
        andFilter.addFileFilter(f2);
        
        assertEquals(andFilter.getFilterSet().size(), 2);
        
        assertTrue(andFilter.getFilterSet().contains(f1));
        assertTrue(andFilter.getFilterSet().contains(f2));
        
        andFilter.addFileFilter(f1);
        assertEquals(andFilter.getFilterSet().size(), 2);
        
        
        WildcardFileFilter f3 = new WildcardFileFilter("b*ar?ck.*");
        andFilter.addFileFilter(f3);
        assertEquals(andFilter.getFilterSet().size(), 3);
        
        
    }

    @Test
    public void testRemoveFileFilter() {
        WildcardFileFilter f1 = new WildcardFileFilter("?a*ma.*");
        WildcardFileFilter f2 = new WildcardFileFilter("k*or*t?e.*");
        WildcardFileFilter f3 = new WildcardFileFilter("b*ar?ck.*");
        AndFileFilter andFilter = new AndFileFilter();
        
        andFilter.addFileFilter(f1);
        andFilter.addFileFilter(f2);
         andFilter.addFileFilter(f3);
        
        andFilter.removeFileFilter(f1);
        
        assertEquals(andFilter.getFilterSet().size(), 2);
        assertFalse(andFilter.getFilterSet().contains(f1));
        assertTrue(andFilter.getFilterSet().contains(f2));
        assertTrue(andFilter.getFilterSet().contains(f3));
        
        andFilter.removeFileFilter(f2);
        
        assertFalse(andFilter.getFilterSet().contains(f2));
        assertTrue(andFilter.getFilterSet().contains(f3));
       
    }

    @Test
    public void testAccept() {
        WildcardFileFilter f1 = new WildcardFileFilter("?a*ma?*.*");
        WildcardFileFilter f2 = new WildcardFileFilter("kama*.*");
        
        
        AndFileFilter andFilter = new AndFileFilter();
        
        File testf1 = new File("kamak.jpg");
        File testf2 = new File("ala.txt");
        File testf3 = new File("kamaka.txt");
        File testf4 = new File("korte");
        File testf5 = new File("barck.txt");
        
        
        boolean result = false;
        try
        {
            andFilter.accept(testf1);
        } catch (IllegalStateException e)
        {
            result = true;
        }
        assertTrue(result);
        
        
        andFilter.addFileFilter(f1);
        andFilter.addFileFilter(f2);
        
        
        assertTrue(andFilter.accept(testf1));
        assertFalse(andFilter.accept(testf2));
        assertTrue(andFilter.accept(testf3));
        assertFalse(andFilter.accept(testf4));
        assertFalse(andFilter.accept(testf5));
        
    }
}
