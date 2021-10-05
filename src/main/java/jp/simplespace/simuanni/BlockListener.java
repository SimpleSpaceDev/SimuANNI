package jp.simplespace.simuanni;

import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class BlockListener implements Listener {
    private static Map<Block,Material> map = new HashMap<>();
    @EventHandler
    public void onBlockBreak(BlockBreakEvent e){
        Player p = e.getPlayer();
        Block block = e.getBlock();
        if(p.getGameMode().equals(GameMode.CREATIVE)){
            return;
        }
        if(block.getType().equals(Material.COBBLESTONE)||block.getType().equals(Material.STONE)){
            e.setCancelled(true);
        }
        if(block.getType().equals(Material.IRON_ORE)){
            e.setCancelled(true);
            anniOreBreak(Material.IRON_INGOT,block,Material.IRON_ORE,p,10);
        }
        if(block.getType().equals(Material.REDSTONE_ORE)||block.getType().equals(Material.GLOWING_REDSTONE_ORE)){
            e.setCancelled(true);
            anniOreBreak(new ItemStack(Material.REDSTONE,new Random().nextInt(2)+4),block,Material.REDSTONE_ORE,p,10);
            p.giveExp(new Random().nextInt(5)+1);
        }
        if(block.getType().equals(Material.COAL_ORE)){
            e.setCancelled(true);
            anniOreBreak(new ItemStack(Material.COAL,new Random().nextInt(3)+1),block,Material.COAL_ORE,p,10);
            p.giveExp(new Random().nextInt(3));
        }
        if(block.getType().equals(Material.GOLD_ORE)){
            e.setCancelled(true);
            anniOreBreak(Material.GOLD_INGOT,block,Material.GOLD_ORE,p,10);
        }
        if(block.getType().equals(Material.EMERALD_ORE)){
            e.setCancelled(true);
            anniOreBreak(Material.EMERALD,block,Material.EMERALD_ORE,p,10);
            p.giveExp(new Random().nextInt(5)+3);
        }
        if(block.getType().equals(Material.DIAMOND_ORE)){
            e.setCancelled(true);
            anniOreBreak(Material.DIAMOND,block,Material.DIAMOND_ORE,p,10);
            p.giveExp(new Random().nextInt(5)+3);
        }
        if(block.getType().equals(Material.LAPIS_ORE)){
            e.setCancelled(true);
            anniOreBreak(new ItemStack(Material.INK_SACK,new Random().nextInt(6)+4,(short)4),block,Material.LAPIS_ORE,p,10);
            p.giveExp(new Random().nextInt(4)+2);
        }
    }
    public static void anniOreBreak(ItemStack item,Block block,Material replace,Player p,int sec){
        block.setType(Material.COBBLESTONE);
        p.getInventory().addItem(new ItemStack(item));
        p.playSound(p.getLocation(), Sound.ITEM_PICKUP,1f,1f);
        map.put(block,replace);
        BukkitRunnable task = new BukkitRunnable(){
            public void run(){
                this.cancel();
                block.setType(replace);
                map.remove(block);
            }
        };
        long second = sec*20L;
        task.runTaskTimer(SimuANNI.getPlugin(),second,0L);
    }
    public static void anniOreBreak(Material item,Block block,Material replace,Player p,int sec){
        anniOreBreak(new ItemStack(item),block,replace,p,sec);
    }
    public static void anniOreBreak(Material item,int amount,Block block,Material replace,Player p,int sec){
        anniOreBreak(new ItemStack(item,amount),block,replace,p,sec);
    }
    public static void resetAllBlock(){
        for(Block block : map.keySet()){
            block.setType(map.get(block));
        }
    }
}
