package mantle.collection;

public class Tag {
    private int tagID;
    private int assetID;
    private String tagName;
    private int nextValue;

    public Tag() {

    }

    public Tag(String tagName) {
        this.tagName = tagName;
    }

    public int register() {
        tagID = nextValue;
        nextValue++;
        return tagID;
    }

    public int getTagID() {
        return tagID;
    }

    public void setTagID(int tagID) {
        this.tagID = tagID;
    }

    @Override
    public String toString(){
        return this.tagName;
    }

    public int getAssetID() {
        return assetID;
    }

    public void setAssetID(int assetID) {
        this.assetID = assetID;
    }
}
