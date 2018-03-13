package mantle.util.preferences;

import mantle.collection.Categories;
import mantle.collection.Category;
import mantle.collection.HandleException;

public class CategoryPreferences {

    private static final Categories categories = new Categories(4);
    private static Category basic = new Category("Basic");
    private static Category material = new Category("Material");
    private static Category staticMesh = new Category("Static Mesh");
    private static Category texture = new Category("Texture");

    public Category[] getCategoryArray() {
        return categories.getCategoryArray();
    }

    public Categories getCategories() {
        return categories;
    }

    public static void addCategories() {
        try {
            categories.addNew(basic);
            categories.addNew(material);
            categories.addNew(staticMesh);
            categories.addNew(texture);
        } catch (HandleException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        addCategories();
        System.out.println(categories.getCategoryArray().toString());
    }
}
