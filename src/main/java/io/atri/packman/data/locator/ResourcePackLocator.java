package io.atri.packman.data.locator;

import java.io.File;
import java.io.InputStream;

public class ResourcePackLocator implements ResourceLocator {
    private final InputStream packLocation;
    private final File saveLocation = new File("./packman/pack/");

    public ResourcePackLocator(InputStream packLocation) {
        this.packLocation = packLocation;
    }

    @Override
    public InputStream getResource() {
        return packLocation;
    }

    @Override
    public File saveLocation() {
        return saveLocation;
    }
}
