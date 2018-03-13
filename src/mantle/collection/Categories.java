package mantle.collection;

public class Categories {
    int max_types = 16;
    private Category categories[] = new Category[max_types];
    int count = 0;

    public Categories() {
    }

    public void addNew(Category category) throws HandleException {
        if (count >= categories.length) throw new HandleException("Too much assets");
        categories[count] = category;
        count++;
    }


}
