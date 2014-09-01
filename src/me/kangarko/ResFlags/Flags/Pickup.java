package me.kangarko.ResFlags.Flags;

import me.kangarko.ResFlags.ResFlags;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerPickupItemEvent;

import com.bekvon.bukkit.residence.Residence;
import com.bekvon.bukkit.residence.protection.ClaimedResidence;

public class Pickup implements Listener {
	@EventHandler(ignoreCancelled = true)
	public void PickupItem(PlayerPickupItemEvent e) {
		Player player = e.getPlayer();
		boolean resadmin = Residence.isResAdminOn(player);
		ClaimedResidence res = Residence.getResidenceManager().getByLoc(e.getItem().getLocation());
		String playername = player.getName();

		if (res != null && !res.getPermissions().playerHas(playername, "pickup", true) && !resadmin) {
			ResFlags.sendMsg(player, "pickup");
			e.setCancelled(true);
		}
	}
}