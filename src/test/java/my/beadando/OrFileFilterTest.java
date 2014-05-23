package my.beadando;

import java.io.File;
import org.junit.Test;
import static org.junit.Assert.*;

public class OrFileFilterTest {
    
    @Test
    public void testAddFileFilter() {
        
        WildcardFileFilter f1 = new WildcardFileFilter("?a*ma.*");
        WildcardFileFilter f2 = new WildcardFileFilter("k*or*t?e.*");
        OrFileFilter orFilter = new OrFileFilter();
       
        orFilter.addFileFilter(f1);
        orFilter.addFileFilter(f2);
        
        assertEquals(orFilter.getFilterSet().size(), 2);
        
        assertTrue(orFilter.getFilterSet().contains(f1));
        assertTrue(orFilter.getFilterSet().contains(f2));
        
        orFilter.addFileFilter(f1);

        assertEquals(orFilter.getFilterSet().size(), 2);
        
        WildcardFileFilter f3 = new WildcardFileFilter("b*ar?ck.*");
        orFilter.addFileFilter(f3);
        assertEquals(orFilter.getFilterSet().size(), 3);
    }

    @Test
    public void testRemoveFileFilter() {
        WildcardFileFilter f1 = new WildcardFileFilter("?a*ma.*");
        WildcardFileFilter f2 = new WildcardFileFilter("k*or*t?e.*");
        WildcardFileFilter f3 = new WildcardFileFilter("b*ar?ck.*");
        
        OrFileFilter orFilter = new OrFileFilter();
        
        orFilter.addFileFilter(f1);
        orFilter.addFileFilter(f2);
        orFilter.addFileFilter(f3);
        
        orFilter.removeFileFilter(f1);
        
        assertEquals(orFilter.getFilterSet().size(), 2);
        
        assertFalse(orFilter.getFilterSet().contains(f1));
        assertTrue(orFilter.getFilterSet().contains(f2));
        assertTrue(orFilter.getFilterSet().contains(f3));
        
        orFilter.removeFileFilter(f3);
        
        assertEquals(orFilter.getFilterSet().size(), 1);
        
        assertFalse(orFilter.getFilterSet().contains(f1));
        assertTrue(orFilter.getFilterSet().contains(f2));
        assertFalse(orFilter.getFilterSet().contains(f3));
        
    }

    @Test
    public void testAccept() {
        WildcardFileFilter f1 = new WildcardFileFilter("?a*ma.*");
        WildcardFileFilter f2 = new WildcardFileFilter("k*or*t?e.*");
        WildcardFileFilter f3 = new WildcardFileFilter("b*ar?ck.*");
        OrFileFilter orFilter = new OrFileFilter();
        
        
        File testf1 = new File("sama.jpg");
        File testf2 = new File("ala.txt");
        File testf3 = new File("kkkkkortle.png");
        File testf4 = new File("orte");
        File testf5 = new File("barcck.txt");
        File testf6 = new File("barck.txt");
        
        boolean result = false;
        
        try
        {
            orFilter.accept(testf1);
        } catch (IllegalStateException e)
        {
            result = true;
        }
        
        assertTrue(result);
        
        orFilter.addFileFilter(f1);
        orFilter.addFileFilter(f2);
        orFilter.addFileFilter(f3);
        
        assertTrue(orFilter.accept(testf1));
        assertFalse(orFilter.accept(testf2));
        assertTrue(orFilter.accept(testf3));
        assertFalse(orFilter.accept(testf4));
        assertTrue(orFilter.accept(testf5));
        assertFalse(orFilter.accept(testf6));
    }
}
