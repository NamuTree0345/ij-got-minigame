package me.namutree0345.skillfight.events;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

import java.util.HashMap;

public class ClassSelectHandler implements Listener {

    public static HashMap<String, String> classSelectMap = new HashMap<>();

    void selectClass(String uuid, String classType, Player player) {
        if(classSelectMap.containsKey(uuid)) {
            classSelectMap.replace(uuid, classType);
        } else {
            classSelectMap.put(uuid, classType);
        }
        player.sendMessage(ChatColor.GREEN + "클래스가 선택되었습니다!");
    }

    @EventHandler
    public void OnClickInventory(InventoryClickEvent event) {
        if(event.getView().getTitle().equals("클래스 선택") && event.getCurrentItem() != null) {
            switch(event.getCurrentItem().getType()) {
                case DIAMOND_SWORD:
                    selectClass(event.getView().getPlayer().getUniqueId().toString(), "sword", ((Player) event.getView().getPlayer()));
                    break;
                case BOW:
                    selectClass(event.getView().getPlayer().getUniqueId().toString(), "bow", ((Player) event.getView().getPlayer()));
                    break;
                case STICK:
                    selectClass(event.getView().getPlayer().getUniqueId().toString(), "ninja", ((Player) event.getView().getPlayer()));
                    break;
                case HEART_OF_THE_SEA:
                    selectClass(event.getView().getPlayer().getUniqueId().toString(), "healer", ((Player) event.getView().getPlayer()));
                    break;
            }

            event.setCancelled(true);
            event.getView().close();
        }
    }

}
