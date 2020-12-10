package io.atri.packman.data.locator;


import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.io.InputStream;
import java.util.Optional;

public class PluginResources {

    @Nullable
    private final ResourcePackLocator resourcePack;
    @Nullable
    private final InputStream dataPack;

    public PluginResources(@Nullable InputStream resourcePath, @Nullable InputStream dataPath) {
        this.resourcePack = new ResourcePackLocator(resourcePath);
        this.dataPack = dataPath;
    }

    @Nonnull
    public Optional<ResourceLocator> getResourcePack() {
        return resourcePack != null ? Optional.of(resourcePack) : Optional.empty();
    }

    @Nonnull
    public Optional<DataPackLocator> getDataPack(String worldName) {
        return dataPack != null ? Optional.of(new DataPackLocator(dataPack, worldName)) : Optional.empty();
    }
}
