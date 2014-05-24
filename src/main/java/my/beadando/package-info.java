/**
 * <p>Ez a csomag {@code FileFilter} interfészt megvalósító és kiterjesztő
 * osztályokat ill. interfészt tartalmaz. A csomagban található osztályok ill. interfész: {@code AndFileFilter}, 
 * {@code NotFileFilter}, {@code OrFileFilter}, {@code WildcardFileFilter}, {@code CompositeFileFilter}.
 * 
 * <p>A {@code WildcardFileFilter} osztály úgy működik, hogy átalakítja a kapott mintát reguláris kifejezéssé,
 * majd a {@link java.util.regex.Pattern} osztály segítségével ellenőrzni, hogy a megadott {@link java.io.File} neve
 * illeszkedik-e a megadott mintára. 
 * 
 * <p>A {@code CompositeFileFilter} interfész általánosságban alkalmas összetett szűrők létrehozására.
 * Ennek két megvalósítása a {@code OrFileFilter} osztály és a {@code AndFileFilter} osztály. 
 * 
 * <p>A {@code NotFileFilter} osztály segítségével bármely {@code FileFilter} ellentettje hozható létre,
 * azaz egy olyan szűrő, ami pontosan azokat az állományokat fogadja el, melyeket az eredeti szűrő nem.
 * 
 * @author blghktt
 * @version 1.0
 */
package my.beadando;
