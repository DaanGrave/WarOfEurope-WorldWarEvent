package nl.warofeurope.event.utils.factories;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import java.lang.reflect.Field;
import java.util.UUID;

public class SkullFactory extends ItemstackFactory {
    public SkullFactory(ItemStack itemStack) {
        super(itemStack);
    }

    public SkullFactory(Material type) {
        super(type);
    }

    public SkullFactory(Material type, int amount, int data) {
        super(type, amount, data);
    }

    public SkullFactory(Material type, int amount, int data, short damage) {
        super(type, amount, data, damage);
    }

    public SkullFactory(Material type, int amount, short damage) {
        super(type, amount, damage);
    }

    public SkullFactory(Material type, int amount) {
        super(type, amount);
    }

    public SkullFactory setOwner(Player player){
        SkullMeta skullMeta = (SkullMeta) super.getItemMeta();
        skullMeta.setOwningPlayer(player);
        super.setItemMeta(skullMeta);

        return this;
    }

    public SkullFactory setValue(String value) {
        GameProfile gameProfile = new GameProfile(UUID.randomUUID(), null);
        gameProfile.getProperties().put("textures", new Property("textures", value));

        SkullMeta skullMeta = (SkullMeta) super.getItemMeta();

        Field profileField;
        try {
            profileField = skullMeta.getClass().getDeclaredField("profile");
        } catch (NoSuchFieldException e){
            e.printStackTrace();
        }

        super.setItemMeta(skullMeta);
        return this;
    }
}
