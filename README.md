Ez a csomag FileFilter interfészt megvalósító és kiterjesztő osztályokat ill. interfészt tartalmaz. A csomagban található osztályok ill. interfész: AndFileFilter, NotFileFilter, OrFileFilter, WildcardFileFilter, CompositeFileFilter.

A WildcardFileFilter osztály úgy működik, hogy átalakítja a kapott mintát reguláris kifejezéssé, majd a Pattern osztály segítségével ellenőrzni, hogy a megadott File neve illeszkedik-e a megadott mintára.

A CompositeFileFilter interfész általánosságban alkalmas összetett szűrők létrehozására. Ennek két megvalósítása a OrFileFilter osztály és a AndFileFilter osztály.

A NotFileFilter osztály segítségével bármely FileFilter ellentettje hozható létre, azaz egy olyan szűrő, ami pontosan azokat az állományokat fogadja el, melyeket az eredeti szűrő nem.