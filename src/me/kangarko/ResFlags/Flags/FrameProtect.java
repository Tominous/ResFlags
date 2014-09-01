package me.kangarko.ResFlags.Flags;

import me.kangarko.ResFlags.ResFlags;

import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import com.bekvon.bukkit.residence.Residence;
import com.bekvon.bukkit.residence.protection.ClaimedResidence;

public class FrameProtect implements Listener {
	@EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
	public void onEntityDamage(EntityDamageByEntityEvent e) {
		if (e.getEntityType() != EntityType.ITEM_FRAME)
			return;

		Entity dmgr = e.getDamager();
		Player pl;
		if (e.getDamager() instanceof Player) {
			pl = (Player) e.getDamager();
		} else {
			if (dmgr instanceof Projectile && ((Projectile) dmgr).getShooter() instanceof Player) {
				pl = (Player) ((Projectile) dmgr).getShooter();
			} else
				return;
		}

		if (Residence.isResAdminOn(pl))
			return;

		ClaimedResidence res = Residence.getResidenceManager().getByLoc(e.getEntity().getLocation());

		if (res != null && !res.getPermissions().playerHas(pl.getName().toString(), "build", true)) {
			ResFlags.sendMsg(pl, "frameprotect");
			e.setCancelled(true);
		}
	}
}