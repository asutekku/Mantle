package mantle.collection;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AssetTest {

    @Test
    void main() {
        Asset newAsset = new Asset();
        newAsset.setFilename("Assetti");
        System.out.println(newAsset.toString());
    }
}