package mantle.util;

import mantle.collection.Collection;

/**
 * @author akko
 * @version 5 Apr 2018
 * 
 * Session saves the program's session data
 *
 */
public class Session {

    private static Session current = new Session();
    private Collection collection = new Collection();

    /**
     * @return Collection
     */
    public static Collection getCollection() {
        return current.collection;
    }

    /**
     * @param collection Session's collection
     */
    public void setCollection(Collection collection) {
        current.collection = collection;
    }

    /**
     * @param name Collection's name
     */
    public static void setCollectionName(String name) {
        current.collection.setCollectionName(name);
    }

    /**
     * @return Collection's name
     */
    public static String getCollectionName() {
        return current.collection.getCollectionName();
    }
}
