package my.beadando;

import java.io.File;
import java.io.FileFilter;
import java.util.HashSet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * A {@link CompositeFileFilter} interfész implementációja, ami <strong>és</strong> kapcsolatot 
 * teremt a különböző FileFilterek között.
 * 
 * <p><strong>Példa</strong>
 * 
 * <p>Az alábbi példakód bemutatja a {@link WildcardFileFilter} osztály segítségével
 * egy olyan <code>AndFileFilter</code> objektum létrehozását ill. használatát, 
 * amely elfogadja mindazon állományokat, melyeknek a neve neve illeszkedik a következő
 * minták mindegyikére: <i>?lma.txt</i>, <i>a*.tx?</i>, <i>a*t</i>.
 * 
 * <pre> WildcardFileFilter f1 = new WildcardFileFilter("?lma.txt");
 * WildcardFileFilter f2 = new WildcardFileFilter("a*.tx?");
 * WildcardFileFilter f3 = new WildcardFileFilter("a*t");
 * 
 * AndFileFilter af = new AndFileFilter();
 * af.addFileFilter(f1);
 * af.addFileFilter(f2);
 * af.addFileFilter(f3);
 * 
 * File file1 = new File("alma.txt");
 * System.out.println(af.accept(file1)?"illeszkedik":"nem illeszkedik"); 
 * File file2 = new File("atka.txp");
 * System.out.println(af.accept(file2)?"illeszkedik":"nem illeszkedik");
 * File file3 = new File("korte.txt");
 * System.out.println(af.accept(file3)?"illeszkedik":"nem illeszkedik");</pre>
 * 
 * <p>Ezen program kimenete:
 * 
 * <pre> illeszkedik
 * nem illeszkedik
 * nem illeszkedik</pre>
 * 
 */
public class AndFileFilter implements CompositeFileFilter {

    /**
    * Naplózó objektum.
    */
    private static Logger logger = LoggerFactory.getLogger(AndFileFilter.class);
    
    /**
     * A szűrőket tartalmazó {@link HashSet}.
     */
    private HashSet<FileFilter> filterSet = new HashSet();

    /**
     * Az osztály konstruktora.
     */
    public AndFileFilter() {
        logger.trace("New AndFileFilter object constructed.");
    }

    /**
     * Visszaadja a használandó szűrők halmazát.
     * 
     * @return a használandó szűrők halmaza
     */    
    public HashSet<FileFilter> getFilterSet() {
        logger.trace("AndFileFilter FilterSet getter used.");
        return filterSet;
    }
    
    /**
     * Hozzáad egy szűrőt a használandó szűrők halmazához.
     *
     * @param filter a hozzáadandó szűrő
     */
    public void addFileFilter(FileFilter filter) {
        logger.trace("New filter add to filterSet");
        filterSet.add(filter);
        logger.info("Count: %d.", filterSet.size());
    }

     /**
     * Kitöröl egy szűrőt a szűrők halmazából.
     *
     * @param filter a kitörlendő szűrő
     */
    public void removeFileFilter(FileFilter filter) {
        logger.trace("remove a filter from the FilterSet");
        filterSet.remove(filter);
    }

    /**
     * Megvizsgálja, hogy az adott állomány nevét az összes használandó szűrő
     * elfogadja-e.
     * 
     * @param pathname a vizsgálandó állomány
     *
     * @return illik-e az összes szűrőre az állomány neve
     * 
     * @throws IllegalStateException kivétel keletkezik, ha a szűrők halmaza üres
     */    
    public boolean accept(File pathname) throws IllegalStateException {
        if (filterSet.isEmpty()) {
            logger.error("Exception: empty filterSet.");
            throw new IllegalStateException();
        } else {
            boolean result = true;
            for (FileFilter f : filterSet) {
                result = result && f.accept(pathname);
                logger.info("filter result: "+f.accept(pathname));
            }
            return result;
        }
    }
}
