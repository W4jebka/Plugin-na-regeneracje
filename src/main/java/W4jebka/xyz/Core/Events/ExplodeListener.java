package W4jebka.xyz.Core.Events;

import W4jebka.xyz.Core.Helper.Terrain;
import W4jebka.xyz.Core.Main;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityExplodeEvent;

import java.util.ArrayList;
import java.util.List;

public class ExplodeListener implements Listener {
    public ExplodeListener(Main plugin) {
        plugin.getServer().getPluginManager().registerEvents(this, Main.getInstance());
    }
    private List<Terrain> toRegenerate = new ArrayList<Terrain>();

    @EventHandler
    public void onExplode(final EntityExplodeEvent event) {
        List<BlockState> states = new ArrayList<BlockState>();
        for (Block block : event.blockList())
            states.add(block.getState());

        Terrain terrain = new Terrain(states, event.getLocation(), System.currentTimeMillis());
        toRegenerate.add(terrain);

    }
}
