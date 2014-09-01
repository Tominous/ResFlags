package me.kangarko.ResFlags.Flags;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockFormEvent;

import com.bekvon.bukkit.residence.Residence;
import com.bekvon.bukkit.residence.protection.ClaimedResidence;

public class Form implements Listener {
	@EventHandler(priority = EventPriority.LOW, ignoreCancelled = true)
	public void onBlockFormEvent(BlockFormEvent e) {
		ClaimedResidence res = Residence.getResidenceManager().getByLoc(e.getBlock().getLocation());

		if (res != null) {
			if (!res.getPermissions().has("form", true)) {
				e.setCancelled(true);
			}
		} else if (!Residence.getWorldFlags().getPerms(e.getBlock().getWorld().getName()).has("form", true)) {
			e.setCancelled(true);
		}
	}
}