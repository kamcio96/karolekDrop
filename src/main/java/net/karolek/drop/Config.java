package net.karolek.drop;

import org.bukkit.configuration.file.FileConfiguration;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;

public final class Config {

    private static final String pathPrefix = "config.";

    public static String GUI_NAME = "&4&lDROP Z KAMIENIA";
    public static List<String> GUI_ICON_LORE = Arrays.asList("&8> &aszansa: &7{CHANCE}", "&8> &adoswiadczenie: &7{EXP}", "&8> &azaklecie fortune: &7{FORTUNE}", "&8> &awypada na poziomie: &7{HEIGHT}", "&8> &awypada w ilosci: &7{AMOUNT}", "&8> &awydobyc mozna: &7{TOOLS}", "&8> &aaktywny: &7{ACTIVE}");


    public static boolean GAMEMODE_DROP = false;
    public static String MESSAGES_CANCELDROP = "&7Drop z tego bloku jest wylaczony! :(";
    public static String FORMAT_GUI_NAME = "&4&lDROP Z KAMIENIA";
    public static String FORMAT_GUI_LORE = "  &8>> &a{KEY}: &7{VALUE}";

    public static String FORMAT_GUI$NAME = "&4&lDROP Z KAMIENIA";
    public static String FORMAT_GUI$LORE = "  &8>> &a{KEY}: &7{VALUE}";

    public static boolean BONUS_ENABLED = true;
    public static double BONUS_MULTIPLIER = 0.5D;

    public static int STONE_EXP = 10;
    public static double FORTUNE_1_PERCENT = 30D;
    public static String FORTUNE_1_AMOUNT = "1-2";
    public static double FORTUNE_2_PERCENT = 20D;
    public static String FORTUNE_2_AMOUNT = "1-3";
    public static double FORTUNE_3_PERCENT = 10D;
    public static String FORTUNE_3_AMOUNT = "1-4";

    private Config() {
    }

    public static void loadConfig() {
        try {
            KarolekDrop.getPlugin().saveDefaultConfig();
            FileConfiguration f = KarolekDrop.getPlugin().getConfig();
            for (Field field : Config.class.getFields()) {
                if ((Modifier.isStatic(field.getModifiers())) && (Modifier.isPublic(field.getModifiers()))) {
                    String path = pathPrefix + field.getName().toLowerCase().replace("$", "-").replace("_", ".");
                    if (f.isSet(path))
                        field.set(null, f.get(path));
                }
            }
        } catch (Exception e) {
            KarolekDrop.getPlugin().getLogger().log(Level.WARNING, "An error occured while loading config!", e);
        }
    }

    public static void saveConfig() {
        try {
            KarolekDrop.getPlugin().saveDefaultConfig();
            FileConfiguration f = KarolekDrop.getPlugin().getConfig();
            for (Field field : Config.class.getFields()) {
                if ((Modifier.isStatic(field.getModifiers())) && (Modifier.isPublic(field.getModifiers()))) {
                    String path = pathPrefix + field.getName().toLowerCase().replace("$", "-").replace("_", ".");
                    f.set(path, field.get(null));
                }
            }
            KarolekDrop.getPlugin().saveConfig();
        } catch (Exception e) {
            KarolekDrop.getPlugin().getLogger().log(Level.WARNING, "An error occured while saving config!", e);
        }
    }

    public static void reloadConfig() {
        KarolekDrop.getPlugin().reloadConfig();
        loadConfig();
        saveConfig();
    }

}
