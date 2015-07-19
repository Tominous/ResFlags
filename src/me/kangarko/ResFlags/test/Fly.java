package me.kangarko.ResFlags.test;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerToggleFlightEvent;

import com.bekvon.bukkit.residence.Residence;
import com.bekvon.bukkit.residence.event.ResidenceChangedEvent;
import com.bekvon.bukkit.residence.protection.ClaimedResidence;

public class Fly implements Listener {

	@EventHandler
	public void onResidenceEnter(ResidenceChangedEvent e) {
		Player pl = e.getPlayer();

		ClaimedResidence from = e.getFrom();
		ClaimedResidence to = e.getTo();
		
		if (to != null) { // leaving a residence
			pl.setAllowFlight(false);
			pl.sendMessage(ChatColor.RED + "> No flying zone here!");
		
		} else if (from != null) { // entering a residence
			pl.setAllowFlight(true);
			pl.sendMessage(ChatColor.GREEN + "> You are now allowed to fly!");
		}
	}
	
	@EventHandler
	public void onFlyToggle(PlayerToggleFlightEvent e) {
		Player pl = e.getPlayer();
		ClaimedResidence res = Residence.getResidenceManager().getByLoc(pl.getLocation());
		
		if (res != null) {
			pl.setAllowFlight(false);
			pl.sendMessage(ChatColor.GOLD + "> You are still not permitted to fly!");
		}
	}
}
