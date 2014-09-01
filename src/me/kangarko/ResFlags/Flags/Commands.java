package me.kangarko.ResFlags.Flags;

import me.kangarko.ResFlags.ResFlags;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

import com.bekvon.bukkit.residence.Residence;
import com.bekvon.bukkit.residence.protection.ClaimedResidence;

public class Commands implements Listener {
	@EventHandler(ignoreCancelled = true)
	public void onPlayerCommand(PlayerCommandPreprocessEvent e) {
		Player player = e.getPlayer();
		Location loc = e.getPlayer().getLocation();
		boolean resadmin = Residence.isResAdminOn(player);

		ClaimedResidence res = Residence.getResidenceManager().getByLoc(loc);
		String playername = player.getName();

		if (res != null && !res.getPermissions().playerHas(playername, "commands", true) && !resadmin) {
			ResFlags.sendMsg(e.getPlayer(), "commands");
			e.setCancelled(true);
		}
	}
}