package mantle.collection;

public class Categories {
    private Category[] categories;
    int count = 0;

    public Categories(int size) {
        categories = new Category[size];
    }

    public void addNew(Category category) throws HandleException {
        if (count >= categories.length) throw new HandleException("Too much assets");
        categories[count] = category;
        count++;
    }

    public Category[] getCategoryArray(){
        return categories;
    }

    public int length(){
        return categories.length;
    }

    public String toString(int index) {
        return categories[index].toString();
    }

}
