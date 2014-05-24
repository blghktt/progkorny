package my.beadando;

import java.io.FileFilter;

/**
 * A {@link FileFilter} interfész kiterjesztése, amivel több FileFilter kombinálható.
 */
public interface CompositeFileFilter extends FileFilter {

    /**
     * Hozzáad egy FileFilter-t a használandó filterek halmazához.
     * 
     * @param filter a hozzáadandó FileFilter
     */
    void addFileFilter(FileFilter filter);

    /**
     * Eltávolít egy FileFilter-t a használandó filterek halmazából.
     * 
     * @param filter a törlendő FileFilter
     */
    void removeFileFilter(FileFilter filter);
    
}
