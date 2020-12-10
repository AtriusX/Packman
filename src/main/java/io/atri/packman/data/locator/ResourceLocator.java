package io.atri.packman.data.locator;

import java.io.File;
import java.io.InputStream;

public interface ResourceLocator {

    InputStream getResource();

    File saveLocation();
}

