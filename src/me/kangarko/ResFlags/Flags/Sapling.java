package me.kangarko.ResFlags.Flags;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

import com.bekvon.bukkit.residence.Residence;
import com.bekvon.bukkit.residence.protection.ClaimedResidence;

public class Sapling implements Listener {
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onBlockPlace(BlockPlaceEvent e) {
		if (e.isCancelled() && e.getBlock().getType() == Material.SAPLING) {
			Player player = e.getPlayer();
			if (Residence.isResAdminOn(player))
				return;
			ClaimedResidence res = Residence.getResidenceManager().getByLoc(e.getPlayer().getLocation());

			if (res != null && res.getPermissions().playerHas(player.getName(), "sapling", true)) {
				e.setCancelled(false);
			}
		}
	}
}