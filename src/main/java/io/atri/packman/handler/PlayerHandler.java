package io.atri.packman.handler;

import io.atri.packman.data.FileHash;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.io.File;

public class PlayerHandler implements Listener  {

    private final File pack;
    private final byte[] hash;

    public PlayerHandler(File pack) {
        this.pack = pack;
        this.hash = new FileHash(pack).getHash();
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        if (!pack.exists())
            return;
        Player player = event.getPlayer();
        player.setResourcePack(pack.getPath(), hash);
    }
}
