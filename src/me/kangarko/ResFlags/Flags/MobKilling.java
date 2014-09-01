package me.kangarko.ResFlags.Flags;

import me.kangarko.ResFlags.ResFlags;

import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Monster;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import com.bekvon.bukkit.residence.Residence;
import com.bekvon.bukkit.residence.protection.ClaimedResidence;

public class MobKilling implements Listener {
	
	@EventHandler(ignoreCancelled = true)
	public void Mobkilling(EntityDamageByEntityEvent e) {
		Entity damager = e.getDamager();
		Entity entity = e.getEntity();

		if (!(entity instanceof Monster))
			return;
		if (!(damager instanceof Arrow) && !(damager instanceof Player))
			return;
		if (damager instanceof Arrow && !(((Arrow) damager).getShooter() instanceof Player))
			return;
		
		Player player;
		
		if (damager instanceof Player) {
			player = (Player) damager;
		} else {
			player = (Player) ((Arrow) damager).getShooter();
		}

		boolean resadmin = Residence.isResAdminOn(player);

		if (resadmin)
			return;

		ClaimedResidence res = Residence.getResidenceManager().getByLoc(entity.getLocation());

		if (res != null && !res.getPermissions().playerHas(player.getName().toString(), "mobkilling", true) && entity instanceof Monster) {
			ResFlags.sendMsg(player, "mobkilling");
			e.setCancelled(true);
		}
	}
}