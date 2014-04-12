package me.kangarko.ResFlags.Flags;

import me.kangarko.ResFlags.ResFlags;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerShearEntityEvent;

import com.bekvon.bukkit.residence.Residence;
import com.bekvon.bukkit.residence.protection.ClaimedResidence;

public class Shear implements Listener {


	@EventHandler(ignoreCancelled=true)
	public void onPlayerShearSheet(PlayerShearEntityEvent e) {
		
		Player player = e.getPlayer();
		ClaimedResidence res = Residence.getResidenceManager().getByLoc(e.getPlayer().getLocation());
		boolean resadmin = Residence.isResAdminOn(player);
		String playername = player.getName();
		
		if (res != null && !res.getPermissions().playerHas(playername, "shear", true)&& !resadmin) {
			ResFlags.sendMsg(e.getPlayer(), "shear");
			e.setCancelled(true);
		}
	}
}