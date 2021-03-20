package nl.warofeurope.event.utils.factories;

import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.LeatherArmorMeta;

public class LeatherArmorFactory extends ItemstackFactory {
    public LeatherArmorFactory(ItemStack itemStack) {
        super(itemStack);
    }

    public LeatherArmorFactory(Material type) {
        super(type);
    }

    public LeatherArmorFactory(Material type, int amount, int data) {
        super(type, amount, data);
    }

    public LeatherArmorFactory(Material type, int amount, int data, short damage) {
        super(type, amount, data, damage);
    }

    public LeatherArmorFactory(Material type, int amount, short damage) {
        super(type, amount, damage);
    }

    public LeatherArmorFactory(Material type, int amount) {
        super(type, amount);
    }

    public LeatherArmorFactory setColor(Color color){
        LeatherArmorMeta leatherArmorMeta = (LeatherArmorMeta) super.getItemMeta();
        leatherArmorMeta.setColor(color);
        super.setItemMeta(leatherArmorMeta);

        return this;
    }
}
