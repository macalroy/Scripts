package scripts.utils;

import org.osbot.rs07.api.map.Area;
import org.osbot.rs07.script.MethodProvider;
import scripts.gui.LootItem;

import java.io.File;
import java.util.ArrayList;


/**
 * Created by macalroy on 8/27/16.
 */
public class Data extends MethodProvider {

    public final Area[] LOCATIONS = {
            new Area(3225, 3301, 3237, 3287),
            new Area(new int[][]{
                    {3241, 3299},
                    {3256, 3299},
                    {3257, 3300},
                    {3261, 3300},
                    {3262, 3299},
                    {3264, 3299},
                    {3266, 3297},
                    {3266, 3255},
                    {3253, 3255},
                    {3253, 3272},
                    {3251, 3274},
                    {3251, 3276},
                    {3249, 3278},
                    {3246, 3278},
                    {3244, 3280},
                    {3244, 3281},
                    {3240, 3285},
                    {3240, 3287},
                    {3241, 3288},
                    {3241, 3289},
                    {3242, 3290},
                    {3242, 3293},
                    {3241, 3294},
                    {3241, 3295},
                    {3240, 3296},
                    {3240, 3298}
                    }),
            new Area(new int[][]{
                    {3145, 3216},
                    {3148, 3214},
                    {3153, 3214},
                    {3160, 3207},
                    {3186, 3207},
                    {3200, 3206},
                    {3198, 3216},
                    {3196, 3218},
                    {3199, 3221},
                    {3199, 3232},
                    {3201, 3235},
                    {3204, 3238},
                    {3207, 3238},
                    {3205, 3248},
                    {3201, 3252},
                    {3191, 3250},
                    {3174, 3240},
                    {3153, 3226},
                    {3142, 3226}
                    }),
            new Area(new int[][]{
                    {3234, 3141},
                    {3236, 3141},
                    {3238, 3142},
                    {3241, 3142},
                    {3242, 3143},
                    {3242, 3144},
                    {3240, 3146},
                    {3240, 3148},
                    {3241, 3149},
                    {3243, 3149},
                    {3244, 3150},
                    {3244, 3151},
                    {3245, 3152},
                    {3245, 3153},
                    {3246, 3154},
                    {3246, 3156},
                    {3246, 3158},
                    {3247, 3159},
                    {3240, 3159},
                    {3236, 3153}
                    })
    };

    public final String[] FOOD_NAMES = { "Bread", "Anchovies", "Shrimp", "Pike", "Trout",
        "Cod", "Salmon", "Shark", "Monkfish", "Lobster", "Bass", "Manta Ray" };


    public final String[] CHICKEN_LOOT = { "Feather" };

    public final String[] COW_LOOT = { "Cowhide" };

    public final String[] BANKABLE_ITEMS = { "Cowhide", "Feathers" };

    public final String[] WEAPON_NAMES = { "Bronze",
            "Iron", "Steel",
            "Black", "Mithril",
            "Adamant", "Rune",
            "Dragon" };

    public String status = "";

    public static boolean isCows;

    public static long currentTime;

    public static int attackType;

    public static ArrayList<LootItem> lootedItems = new ArrayList<>();

    public static final String dataLocation = System.getProperty("user.home")
            + File.separator
            + "OSBot"
            + File.separator
            + "Data"
            + File.separator
            + "lumAIO"
            + File.separator;

    public boolean getIsCows() {
        return isCows;
    }
}
