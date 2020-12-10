package io.atri.packman.service;

import io.atri.packman.Packman;
import io.atri.packman.PackmanPlugin;
import io.atri.packman.data.ZipMerger;
import io.atri.packman.data.locator.PluginResources;
import org.bukkit.event.Listener;

import java.io.File;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

public class PackmanService implements Listener {
    private final static Map<PackmanPlugin, PluginResources> roots = new HashMap<>();
    private final static Logger logger = Packman.getPackLogger();

    @SuppressWarnings("unused") // External API
    public static void register(PackmanPlugin plugin) {
        logger.info(String.format("Registering resource paths for %s...", plugin));
        String resources = plugin.getResourceLocation(), data = plugin.getDataPackLocation();
        InputStream resourcePath = resources != null ? plugin.getResource(resources) : null;
        InputStream dataPath = data != null ? plugin.getResource(data) : null;
        roots.put(plugin, new PluginResources(resourcePath, dataPath));
    }

    public static void merge(File pack) {
        ZipMerger merger = new ZipMerger(pack);
        for (Map.Entry<PackmanPlugin, PluginResources> data : roots.entrySet()) {
            data.getValue().getResourcePack().ifPresent(r -> {
                PackmanPlugin plugin = data.getKey();
                if (!merger.merge(plugin.zipResources()))
                    logger.warning(String.format("An error occurred when loading %s...", plugin));
            });
        }
    }
}
