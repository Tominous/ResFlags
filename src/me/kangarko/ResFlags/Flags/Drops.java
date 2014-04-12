package me.kangarko.ResFlags.Flags;

import me.kangarko.ResFlags.ResFlags;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;

import com.bekvon.bukkit.residence.Residence;
import com.bekvon.bukkit.residence.protection.ClaimedResidence;

public class Drops implements Listener {

	@EventHandler(ignoreCancelled=true)
	public void onPlayerDropItem(PlayerDropItemEvent e) {
		
		Player player = e.getPlayer();
		boolean resadmin = Residence.isResAdminOn(player);
		
		ClaimedResidence res = Residence.getResidenceManager().getByLoc(e.getItemDrop().getLocation());
		String playername = player.getName();
		
		if (res != null) {
			if (!res.getPermissions().playerHas(playername, "drops", true) && !resadmin) {
				ResFlags.sendMsg(e.getPlayer(), "drops");
				e.setCancelled(true);
			}
		}
	}
}
