package nl.warofeurope.event.utils.factories;

import net.minecraft.server.v1_12_R1.NBTTagCompound;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_12_R1.inventory.CraftItemStack;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static nl.warofeurope.event.utils.Colors.color;

public class ItemstackFactory extends ItemStack {
    public ItemstackFactory(ItemStack itemStack){
        super(itemStack);
    }

    public ItemstackFactory(Material type) {
        super(type);
    }

    public ItemstackFactory(Material type, int amount, int data){
        super(type, amount, (byte) data);
    }

    public ItemstackFactory(Material type, int amount, int data, short damage){
        super(type, amount, damage, (byte) data);
    }

    public ItemstackFactory(Material type, int amount, short damage) {
        super(type, amount, damage);
    }

    public ItemstackFactory(Material type, int amount) {
        super(type, amount);
    }

    public ItemstackFactory setName(String name){
        ItemMeta itemMeta = super.getItemMeta();
        itemMeta.setDisplayName(color(name));
        super.setItemMeta(itemMeta);

        return this;
    }

    public ItemstackFactory addEnchantmentUnsafe(Enchantment enchantment, int level){
        this.addUnsafeEnchantment(enchantment, level);
        return this;
    }

    public boolean hasNbtTag(String tag){
        return this.getNbtTagCompound().hasKey(tag);
    }

    public ItemstackFactory setNbtTagCompound(NBTTagCompound nbtTagCompound){
        net.minecraft.server.v1_12_R1.ItemStack itemStack = CraftItemStack.asNMSCopy(this);
        itemStack.setTag(nbtTagCompound);

        return new ItemstackFactory(CraftItemStack.asBukkitCopy(itemStack));
    }

    public NBTTagCompound getNbtTagCompound(){
        net.minecraft.server.v1_12_R1.ItemStack itemStack = CraftItemStack.asNMSCopy(this);
        NBTTagCompound nbtTagCompound = itemStack.getTag();

        if (nbtTagCompound == null)
            nbtTagCompound = new NBTTagCompound();

        return nbtTagCompound;
    }

    public ItemstackFactory addLore(String line){
        List<String> lore = this.getLore();
        lore.add(color(line));
        this.setLore(lore);
        return this;
    }

    public List<String> getLore(){
        if (!super.hasItemMeta()){
            return new ArrayList<>();
        } else {
            ItemMeta itemMeta = super.getItemMeta();
            if (!itemMeta.hasLore()){
                return new ArrayList<>();
            } else {
                return itemMeta.getLore();
            }
        }
    }

    public ItemstackFactory setUnbreakable(boolean unbreakable){
        ItemMeta itemMeta = super.getItemMeta();
        itemMeta.setUnbreakable(unbreakable);
        super.setItemMeta(itemMeta);

        return this;
    }

    public boolean isUnbreakable(){
        return super.hasItemMeta() && super.getItemMeta().isUnbreakable();
    }

    public ItemstackFactory addItemFlags(ItemFlag... itemFlags){
        ItemMeta itemMeta = super.getItemMeta();
        itemMeta.addItemFlags(itemFlags);
        super.setItemMeta(itemMeta);

        return this;
    }

    public ItemstackFactory setLore(List<String> lore){
        List<String> newLore = new ArrayList<>();

        lore.forEach(line -> {
            newLore.add(color(line));
        });

        ItemMeta itemMeta = super.getItemMeta();
        itemMeta.setLore(newLore);
        super.setItemMeta(itemMeta);

        return this;
    }

    public ItemstackFactory setLore(String... lines){
        return this.setLore(Arrays.asList(lines));
    }
}
