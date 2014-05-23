package my.beadando;

import java.io.File;
import java.io.FileFilter;
import java.util.HashSet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * A {@link CompositeFileFilter} interfész implementációja, ami és kapcsolatot 
 * teremt a különböző FileFilterek között.
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
    @Override
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
    @Override
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
    @Override
    public boolean accept(File pathname) throws IllegalStateException {
        if (filterSet.isEmpty()) {
            logger.error("Exception: empty filterSet.");
            throw new IllegalStateException();
        } else {
            boolean result = true;
            for (FileFilter f : filterSet) {
                result = (result && f.accept(pathname));
                logger.info("filter result: "+f.accept(pathname));
            }
            return (result);
        }
    }
}
