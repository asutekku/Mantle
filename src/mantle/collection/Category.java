package mantle.collection;

public class Category {
    private int typeID;
    private String catName;
    private int nextValue;

    public Category() {

    }

    public int getTypeID() {
        return typeID;
    }

    public void setTypeID(int typeID) {
        this.typeID = typeID;
    }

    public Category(String categoryName) {
        this.catName = categoryName;
    }

    @Override
    public String toString(){
        return this.catName;
    }

    public int register() {
        typeID = nextValue;
        nextValue++;
        return typeID;
    }
}
