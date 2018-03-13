package mantle.util.preferences;

import mantle.collection.Categories;
import mantle.collection.Category;
import mantle.collection.HandleException;

public class category {
    private final Categories categories = new Categories();

    {
        Category basic = new Category("Basic");
        Category material = new Category("Material");
        Category staticMesh = new Category("Static Mesh");
        Category texture = new Category("Texture");
        try {
            categories.addNew(basic);
            categories.addNew(material);
            categories.addNew(staticMesh);
            categories.addNew(texture);
        } catch (HandleException e) {
            e.printStackTrace();
        }
    }
}
