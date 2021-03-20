package nl.warofeurope.event.utils.serializers;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.material.Stairs;

public class LocationSerializer {
    public static String serializeLocation(Location location){
        return (location.getWorld().getName() + ";" +
                location.getX() + ";" +
                location.getY() + ";" +
                location.getZ() + ";" +
                location.getYaw() + ";" +
                location.getPitch()).replaceAll("\\.", "_");
    }

    public static boolean isSerializedLocation(String serializedLocation){
        return serializedLocation.matches("^([a-zA-Z0-9_.]+);([0-9_.\\-]+);([0-9_.]+);([0-9_.\\-]+);([0-9_.]+);([0-9_.]+)$");
    }

    public static Location deserializeLocation(String serializedLocation){
        serializedLocation = serializedLocation.replaceAll("_", ".");
        String[] splittedLocation = serializedLocation.split(";");

        return new Location(
                Bukkit.getWorld(splittedLocation[0]),
                Double.parseDouble(splittedLocation[1]),
                Double.parseDouble(splittedLocation[2]),
                Double.parseDouble(splittedLocation[3]),
                Float.parseFloat(splittedLocation[4]),
                Float.parseFloat(splittedLocation[5])
        );
    }

    public static float getDescendingDirection(Stairs stairs){
        float direction = 0;

        switch (stairs.getDescendingDirection()){
            case NORTH:
                direction = 180;
                break;
            case EAST:
                direction = -90;
                break;
            case WEST:
                direction = 90;
                break;
            default:
                direction = 0;
                break;
        }

        return direction;
    }
}
