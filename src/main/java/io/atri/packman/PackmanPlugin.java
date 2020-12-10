package io.atri.packman;

import io.atri.packman.data.exception.ResourcePathException;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.InputStream;
import java.util.zip.ZipInputStream;

public abstract class PackmanPlugin extends JavaPlugin {

    public String getResourceLocation() {
        return null;
    }

    public String getDataPackLocation() {
        return null;
    }

    public final ZipInputStream zipResources() {
        return createZip(getResourceLocation());
    }

    public final ZipInputStream zipData() {
        return createZip(getDataPackLocation());
    }

    private ZipInputStream createZip(String path) {
        InputStream res = getResource(path);
        if (res == null)
            throw new ResourcePathException(path);
        return new ZipInputStream(res);
    }
}
