package me.kangarko.ResFlags.Flags;

import me.kangarko.ResFlags.ResFlags;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

import com.bekvon.bukkit.residence.Residence;
import com.bekvon.bukkit.residence.protection.ClaimedResidence;

public class Cake implements Listener {
	
	@EventHandler(ignoreCancelled=false, priority=EventPriority.HIGHEST)
	public void onPlayerStealCake(PlayerInteractEvent e) {
		Player player = e.getPlayer();
		
		if (player != null && e.getClickedBlock() != null && e.getClickedBlock().getType() == Material.CAKE_BLOCK) {
			
			if (Residence.isResAdminOn(player))
				return;
			
			ClaimedResidence res = Residence.getResidenceManager().getByLoc(e.getPlayer().getLocation());

			if (res != null && !res.getPermissions().playerHas(player.getName(), "use", true)) {
				ResFlags.sendMsg(player, "cake");
				e.setCancelled(true);
			}
		}
	}
}