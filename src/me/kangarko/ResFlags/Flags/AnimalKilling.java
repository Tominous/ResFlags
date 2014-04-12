package me.kangarko.ResFlags.Flags;

import me.kangarko.ResFlags.ResFlags;

import org.bukkit.entity.Animals;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.IronGolem;
import org.bukkit.entity.Player;
import org.bukkit.entity.Snowman;
import org.bukkit.entity.Villager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import com.bekvon.bukkit.residence.Residence;
import com.bekvon.bukkit.residence.protection.ClaimedResidence;

public class AnimalKilling implements Listener {


	@EventHandler(ignoreCancelled=true)
	public void onEntityDamageByEntityEvent(EntityDamageByEntityEvent e) {
		
		Player pl;
		Entity damager = e.getDamager();
		
		if (!(damager instanceof Arrow) && !(damager instanceof Player)) {
			return;
		}
		
		if (damager instanceof Arrow && !(((Arrow) damager).getShooter() instanceof Player)) {
			return;
		} else if (damager instanceof Player) {
			pl = (Player) damager;
		} else {
			pl = (Player) ((Arrow) damager).getShooter();
		}
		
		if (Residence.isResAdminOn(pl)) {
			return;
		}
		
		Entity entity = e.getEntity();
		ClaimedResidence res = Residence.getResidenceManager().getByLoc(entity.getLocation());

		if (res != null && !res.getPermissions().playerHas(pl.getName().toString(), "animalkilling", true)) {
			if (entity instanceof Animals || entity instanceof IronGolem || entity instanceof Snowman) {
				ResFlags.sendMsg(pl, "animalkilling");
				e.setCancelled(true);
			} else if (entity instanceof Villager) {
				ResFlags.sendMsg(pl, "villagerkilling");
				e.setCancelled(true);
			}
		}
	}
}
