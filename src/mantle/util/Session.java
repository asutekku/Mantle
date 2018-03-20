package mantle.util;

import mantle.collection.Collection;

public class Session {

    private static Session current = new Session();
    private Collection collection = new Collection();

    public static Collection getCollection() {
        return current.collection;
    }

    public void setCollection(Collection collection) {
        current.collection = collection;
    }

    public static void setCollectionName(String name) {
        current.collection.setCollectionName(name);
    }

    public static String getCollectionName() {
        return current.collection.getCollectionName();
    }
}
