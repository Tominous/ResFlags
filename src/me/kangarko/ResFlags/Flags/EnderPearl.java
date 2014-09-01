package me.kangarko.ResFlags.Flags;

import me.kangarko.ResFlags.ResFlags;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerTeleportEvent;

import com.bekvon.bukkit.residence.Residence;
import com.bekvon.bukkit.residence.protection.ClaimedResidence;

public class EnderPearl implements Listener {
	@EventHandler(ignoreCancelled = true)
	public void onPlayerTeleport(PlayerTeleportEvent e) {
		Location loc = e.getTo();
		Player player = e.getPlayer();
		ClaimedResidence res = Residence.getResidenceManager().getByLoc(loc);
		boolean resadmin = Residence.isResAdminOn(player);

		String playername = player.getName();
		if (res != null && e.getCause() == PlayerTeleportEvent.TeleportCause.ENDER_PEARL && !res.getPermissions().playerHas(playername, "enderpearl", true) && !resadmin) {
			ResFlags.sendMsg(e.getPlayer(), "enderpearl");
			e.setCancelled(true);
		}
	}
}