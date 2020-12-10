package io.atri.packman;

import io.atri.packman.handler.PlayerHandler;
import io.atri.packman.service.PackmanService;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.logging.Logger;

public final class Packman extends JavaPlugin {

    private static final Logger logger = Logger.getLogger("PM");
    private final File storageLoc = new File(getDataFolder(), "pack.zip");

    @Override
    public void onLoad() {
        saveResource("pack.zip", false);
    }

    @Override
    public void onEnable() {
        getServer().getScheduler().runTaskLater(this, () -> PackmanService.merge(storageLoc), 1L);
        getServer().getPluginManager().registerEvents(new PlayerHandler(storageLoc), this);
    }

    public static Logger getPackLogger() {
        return logger;
    }
}
