package my.beadando;

import java.io.File;
import java.io.FileFilter;
import java.util.regex.Pattern;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * A {@link FileFilter} interfész implementációja, melynek segítségével adott
 * mintára illeszkedő állománynevek fogadtathatók el.
 */
public class WildcardFileFilter implements FileFilter {
    
    /**
     * Naplózó objektum.
     */
    private static Logger logger = LoggerFactory.getLogger(WildcardFileFilter.class);
    
    /**
     * Az osztály által használt minta.
     */
    private String wildcard;

    /**
     * Az osztály konstruktora.
     * 
     * @param wildcard az osztály által használandó minta
     */
    public WildcardFileFilter(String wildcard) {
        this.wildcard = wildcard;
        logger.trace("New WildcardFileFilter object constructed, wildcard: "+wildcard);
    }
    
    /**
     * A használt minta lekérdezése.
     *
     * @return a minta értéke
     */
    public String getWildcard() {
        logger.trace("WildcardFileFilter getter used.");
        return wildcard; 
    }

    /**
     * A használandó minta beállítása.
     *
     * @param wildcard a minta értéke
     */
    public void setWildcard(String wildcard) {
        logger.trace("WildcardFileFilter setter used, wildcard: "+wildcard);
        this.wildcard = wildcard;
    }

    /**
     * A minta átalakítása reguláris kifejezéssé.
     *
     * @return a reguláris kifejezés értéke
     */
    private String convertWildcardToRegex() {
        logger.info("convert wildcard to regex, wildcard: "+this.wildcard);
        StringBuilder sb = new StringBuilder();
        sb.append('^');
        for (int i = 0; i < this.wildcard.length(); i++) {
            if( this.wildcard.charAt(i)=='*'){
                sb.append(".*");
            }
            else if(this.wildcard.charAt(i)=='?'){
                sb.append(".");
            }
            else if("(){}[]$^.|".contains(this.wildcard.charAt(i)+"")){
                sb.append("\\");
                    sb.append(this.wildcard.charAt(i));
            }
            else{
                sb.append(this.wildcard.charAt(i));
            }
        }
        sb.append('$');
        logger.info("convert wildcard to regex, regex: "+sb.toString());
        return(sb.toString());
    }    
    
    /**
     * Ellenőrzi, hogy az adott állománynév illeszkedik-e a megadott mintára.
     * 
     * @param pathname a vizsgálandó állomány
     *
     * @return visszatér azzal, hogy az állomány neve illeszkedik-e a mintára
     */
    @Override
    public boolean accept(File pathname) {
        logger.info("pathname: "+pathname);
        return(Pattern.matches(this.convertWildcardToRegex(), pathname.getName()));
    }

}
