package io.atri.packman.data.locator;

import java.io.File;
import java.io.InputStream;

public class DataPackLocator implements ResourceLocator {
    private final InputStream packLocation;
    private final File saveLocation;

    public DataPackLocator(InputStream packLocation, String worldName) {
        this.packLocation = packLocation;
        this.saveLocation = new File(String.format("../%s/datapacks/", worldName));
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
