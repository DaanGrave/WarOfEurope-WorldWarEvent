package nl.warofeurope.event.utils;

import org.bukkit.entity.Player;

public class TabHeaders {
    public static void setPlayer(Player player, String header, String footer){
//        if (header == null && footer == null)
//            return;
//        if (player == null){
//            return;
//        }
//
//        if (header == null) header = "";
//        if (footer == null) footer = "";
//
//        header = color(header);
//        footer = color(footer);
//
//        try {
//            PacketPlayOutPlayerListHeaderFooter packetPlayOutPlayerListHeaderFooter = new PacketPlayOutPlayerListHeaderFooter();
//            IChatBaseComponent iChatBaseComponentHeader = IChatBaseComponent.ChatSerializer.a("{\"text\":\"" + header + "\"}");
//            IChatBaseComponent iChatBaseComponentFooter = IChatBaseComponent.ChatSerializer.a("{\"text\":\"" + footer + "\"}");
//
//            Field headerField = packetPlayOutPlayerListHeaderFooter.getClass().getDeclaredField("a");
//            Field footerField = packetPlayOutPlayerListHeaderFooter.getClass().getDeclaredField("b");
//            headerField.setAccessible(true);
//            footerField.setAccessible(true);
//            headerField.set(packetPlayOutPlayerListHeaderFooter, iChatBaseComponentHeader);
//            footerField.set(packetPlayOutPlayerListHeaderFooter, iChatBaseComponentFooter);
//            headerField.setAccessible(false);
//            footerField.setAccessible(false);
//
//            CraftPlayer craftPlayer = (CraftPlayer) player;
//            craftPlayer.getHandle().playerConnection.sendPacket(packetPlayOutPlayerListHeaderFooter);
//        } catch (Exception e){
//            e.printStackTrace();
//        }
    }
}
