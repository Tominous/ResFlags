package me.kangarko.ResFlags.Flags;

import me.kangarko.ResFlags.ResFlags;

import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;

import com.bekvon.bukkit.residence.Residence;
import com.bekvon.bukkit.residence.protection.ClaimedResidence;

public class Trade implements Listener {

	@EventHandler(ignoreCancelled=true)
	public void onPlayerInteract(PlayerInteractEntityEvent e) {
		
		Entity entity = e.getRightClicked();
		
		if (entity.getType() == EntityType.VILLAGER) {
			
			Player player = e.getPlayer();
			ClaimedResidence res = Residence.getResidenceManager().getByLoc(e.getPlayer().getLocation());
			boolean resadmin = Residence.isResAdminOn(player);
			String playername = player.getName();
			
			if (res != null) {
				if (!res.getPermissions().playerHas(playername, "trade", true) && !resadmin) {
					ResFlags.sendMsg(e.getPlayer(), "trade");
					e.setCancelled(true);
				}
			}
		}
	}
}
