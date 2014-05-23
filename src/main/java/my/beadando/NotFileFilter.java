package my.beadando;

import java.io.File;
import java.io.FileFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * A {@link FileFilter} interfész implementációja, ami pontosan akkor fogad el
 * egy állománynevet, ha azt az eredeti FileFilter nem fogadja el.
 */
public class NotFileFilter implements FileFilter {
    
    /**
    * Naplózó objektum.
    */
    private static Logger logger = LoggerFactory.getLogger(NotFileFilter.class);
    
    /**
     * A negálandó FileFilter.
     */
    private FileFilter filter;

    
    /**
     * Az osztály konstruktora.
     * 
     * @param filter a negálandó FileFilter
     */
    public NotFileFilter(FileFilter filter)
    {
        logger.trace("New NotFileFilter object constructed.");
        this.filter = filter;
    }
    
    /**
     * Visszatér a negálandó FileFilterrel.
     * 
     * @return a negálandó FileFilter
     */
    public FileFilter getFilter() {
        logger.trace("NotFileFilter getter used.");
        return filter;
    }

    /**
     * Megvizsgálja, hogy a paraméterként kapott állomány neve illeszkedik-e
     * a használandó FileFilterre és az eredmény negáltját adja vissza.
     * 
     * @param pathname a vizsgálandó állomány
     *
     * @return true, ha az eredeti FileFilter nem fogadja el, false ellenkezőleg
     */
    @Override
    public boolean accept(File pathname) {
        logger.info("pathname: "+pathname);
        return(!this.filter.accept(pathname));
    }
    
}
