package my.beadando;

import java.io.File;
import org.junit.Test;
import static org.junit.Assert.*;

public class WildcardFileFilterTest {
   
    @Test
    public void testGetterSetter() {
        WildcardFileFilter f = new WildcardFileFilter("alma.txt");  
    
        f.setWildcard("korte.txt");
        assertEquals(f.getWildcard(),"korte.txt");
    }
    
    @Test
    public void testAcceptAsterisk() {
        WildcardFileFilter f = new WildcardFileFilter("a*lma.txt");  
    
        File file1 = new File("alma.txt");
        assertTrue(f.accept(file1)); 
        File file2 = new File("allma.txt");
        assertTrue(f.accept(file2));
        File file3 = new File("alllma.txt");
        assertTrue(f.accept(file3));
        File file4 = new File("korte.txt");
        assertFalse(f.accept(file4));
    }
    
    
    @Test
    public void testAcceptQuestionMark() {
        WildcardFileFilter f = new WildcardFileFilter("a?lma.txt");  
        
        File file1 = new File("alma.txt");
        assertFalse(f.accept(file1)); 
        File file2 = new File("allma.txt");
        assertTrue(f.accept(file2));
        File file3 = new File("alllma.txt");
        assertFalse(f.accept(file3));
        File file4 = new File("korte.txt");
        assertFalse(f.accept(file4));
    }
   
    @Test
    public void testAcceptSpecialCaracters() {
        WildcardFileFilter f1 = new WildcardFileFilter("a(l)m|a.txt");
        File file1a = new File("a(l)m|a.txt");
        assertTrue(f1.accept(file1a)); 
        File file1b = new File("a(l(m|a.txt");
        assertFalse(f1.accept(file1b));
        File file1c = new File("a)l)m|a.txt");
        assertFalse(f1.accept(file1c));
        File file1d = new File("a(l)|ma.txt");
        assertFalse(f1.accept(file1d));
        
        
        WildcardFileFilter f2 = new WildcardFileFilter("a[l]m^a.txt");  
        File file2a = new File("a[l]m^a.txt");
        assertTrue(f2.accept(file2a));
        File file2b = new File("a[l[m^a.txt");
        assertFalse(f2.accept(file2b));
        File file2c = new File("a]l]m^a.txt");
        assertFalse(f2.accept(file2c));
        File file2d = new File("a[l]^ma.txt");
        assertFalse(f2.accept(file2d));
        
        
        
        WildcardFileFilter f3 = new WildcardFileFilter("a{l}m$a.txt"); 
        File file3a = new File("a{l}m$a.txt");
        assertTrue(f3.accept(file3a));
        File file3b = new File("a{l{m$a.txt");
        assertFalse(f3.accept(file3b));
        File file3c = new File("a}l}m$a.txt");
        assertFalse(f3.accept(file3c));
        File file3d = new File("a{l}$ma.txt");
        assertFalse(f3.accept(file3d));
        
        
    }
    
}
