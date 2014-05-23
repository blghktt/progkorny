package my.beadando;

import java.io.File;
import org.junit.Test;
import static org.junit.Assert.*;

public class NotFileFilterTest {
    
    @Test
    public void testAccept() {
        
        WildcardFileFilter f1 = new WildcardFileFilter("?a*ma.*");
        NotFileFilter notFilter1 = new NotFileFilter(f1);
        assertEquals(notFilter1.getFilter(),f1);
        
        File testf1 = new File("kalma.txt");
        assertEquals(notFilter1.accept(testf1),!f1.accept(testf1));
        
        File testf2 = new File("barack.txt");
        assertEquals(notFilter1.accept(testf2),!f1.accept(testf2));  
        
    }
    
}
