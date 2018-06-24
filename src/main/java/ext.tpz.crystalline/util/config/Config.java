package ext.tpz.crystalline.util.config;

import ext.tpz.crystalline.Crystalline;
import ext.tpz.crystalline.proxy.CommonProxy;
import net.minecraftforge.common.config.Configuration;
import org.apache.logging.log4j.Level;

public class Config {

    public static final String CATEGORY_CRYSTALS = "crystals";
    public static final String CATEGORY_INSANITY = "insanity";
    public static final String CATEGORY_GENERAL = "general";

    // Crystals
    public static boolean enableCrystalAdmin = true;
    public static String  descEnableCrystalAdmin = "This option enables the Administration Crystal. Either 'true' or 'false'. Default: 'true'";

    public static boolean enableCrystalLife = true;
    public static String  descEnableCrystalLife = "This option enables the Life Crystal. Either 'true' or 'false'. Default: 'true'";

    public static boolean enableCrystalRift = true;
    public static String  descEnableCrystalRift = "This option enables the Rift Crystal. Either 'true' or 'false'. Default: 'true'";

    public static boolean enableCrystalCleansing = true;
    public static String  descEnableCrystalCleansing = "This option enables the Cleansing Crystal. Either 'true' or 'false'. Default: 'true'";

    public static boolean enableCrystalCustom = true;
    public static String  descEnableCrystalCustom = "This option enables customized crystals. Either 'true' or 'false'. Default: 'true'";

    public static boolean doesCrystalDrainAdmin = true;
    public static String  descDoesCrystalDrainAdmin = "This option determines whether the Administration Crystal's essence drains. Either 'true' or 'false'. Default: 'true'.";

    public static boolean doesCrystalDrainLife = true;
    public static String  descDoesCrystalDrainLife = "This option determines whether the Life Crystal's essence drains. Either 'true' or 'false'. Default: 'true'.";

    public static boolean doesCrystalDrainRift = false;
    public static String  descDoesCrystalDrainRift = "This option determines whether the Rift Crystal's essence drains. Either 'true' or 'false'. Default: 'false'.";

    public static boolean doesCrystalDrainKnowledge = false;
    public static String  descDoesCrystalDrainKnowledge = "This option determines whether the Knowledge Crystal's essence drains. Either 'true' or 'false'. Default: 'false'.";

    public static boolean doesCrystalDrainCustom = true;
    public static String  descDoesCrystalDrainCustom = "This option determines whether customized crystals' essences drain. Either 'true' or 'false'. Default: 'true'";

    public static boolean doesCrystalCausePermInsanityRift = true;
    public static String  descDoesCrystalCausePermInsanityRift = "This option determines whether the Rift Crystal causes permanent insanity. Either 'true' or 'false'. Default: 'true'";

    // Insanity

    // Stage 1 = 10% - 29%
    public static int mpeInsanityStage1 = 36000;
    public static String descMpeInsanityStage1 = "This option determines how many ticks it takes for one insanity event to happen at 10% to 29% insanity. 20 ticks = 1 second. Default: 36000. Set to -1 to disable.";

    // Stage 2 = 30% - 49%
    public static int mpeInsanityStage2 = 27000;
    public static String descMpeInsanityStage2 = "This option determines how many ticks it takes for one insanity event to happen at 30% to 49% insanity. 20 ticks = 1 second. Default: 27000. Set to -1 to disable.";

    // Stage 3 = 50% - 69%
    public static int mpeInsanityStage3 = 20100;
    public static String descMpeInsanityStage3 = "This option determines how many ticks it takes for one insanity event to happen at 50% to 69% insanity. 20 ticks = 1 second. Default: 20100. Set to -1 to disable.";

    // Stage 4 = 70% - 89%
    public static int mpeInsanityStage4 = 14400;
    public static String descMpeInsanityStage4 = "This option determines how many ticks it takes for one insanity event to happen at 70% to 89% insanity. 20 ticks = 1 second. Default: 14400. Set to -1 to disable.";

    // Stage 5 = 90% - 100%
    public static int mpeInsanityStage5 = 10800;
    public static String descMpeInsanityStage5 = "This option determines how many ticks it takes for one insanity event to happen at 30% to 49% insanity. 20 ticks = 1 second. Default: 10800. Set to -1 to disable.";

    // Stage 6 = 100%
    public static int mpeInsanityStage6 = 8700;
    public static String descMpeInsanityStage6 = "This option determines how many ticks it takes for one insanity event to happen at 100% insanity. 20 ticks = 1 second. Default: 8700. Set to -1 to disable.";

    // Perm = permanent
    public static int mpeInsanityPerm = 6600;
    public static String descMpeInsanityPerm = "This option determines how many ticks it takes for one insanity event to happen at permanent insanity. 20 ticks = 1 second. Default: 6600. Set to -1 to disable.";

    public static boolean enableTempInsanity = true;
    public static String  descEnableTempInsanity = "This option determines whether temporary insanity is enabled or not. Either 'true' or 'false'. Default: 'true'.";

    public static boolean enablePermInsanity = true;
    public static String  descEnablePermInsanity = "This option determines whether permanent insanity is enabled or not. Either 'true' or 'false'. Default: 'true'.";

    // General

    public static boolean enableRebinding = true;
    public static String  descEnableRebinding = "This option determines whether rebinding a crystal is enabled or not. Either 'true' or 'false'. Default: 'true'.";

    public static boolean enableRestoration = true;
    public static String  descEnableRestoration = "This option determines whether restoring (refilling the essence) a crystal is enabled or not. Either 'true' or 'false'. Default: 'true'.";

    public static boolean enableDistillation = true;
    public static String  descEnableDistillation = "This option determines whether distillation (creating new crystals) is enabled or not. Either 'true' or 'false'. Default: 'true'.";

    public static void initCrystalConfig(Configuration cfg) {
        cfg.addCustomCategoryComment(CATEGORY_CRYSTALS, "This section determines different settings regarding crystals.");

        enableCrystalAdmin = cfg.getBoolean("enableCrystalAdmin", CATEGORY_CRYSTALS, enableCrystalAdmin, descEnableCrystalAdmin);
        enableCrystalLife = cfg.getBoolean("enableCrystalLife", CATEGORY_CRYSTALS, enableCrystalLife, descEnableCrystalLife);
        enableCrystalRift = cfg.getBoolean("enableCrystalRift", CATEGORY_CRYSTALS, enableCrystalRift, descEnableCrystalRift);
        enableCrystalCleansing = cfg.getBoolean("enableCrystalCleansing", CATEGORY_CRYSTALS, enableCrystalCleansing, descEnableCrystalCleansing);
        enableCrystalCustom = cfg.getBoolean("enableCrystalCustom", CATEGORY_CRYSTALS, enableCrystalCustom, descEnableCrystalCustom);

        doesCrystalDrainAdmin = cfg.getBoolean("doesCrystalDrainAdmin", CATEGORY_CRYSTALS, doesCrystalDrainAdmin, descDoesCrystalDrainAdmin);
        doesCrystalDrainLife = cfg.getBoolean("doesCrystalDrainLife", CATEGORY_CRYSTALS, doesCrystalDrainLife, descDoesCrystalDrainLife);
        doesCrystalDrainRift = cfg.getBoolean("doesCrystalDrainRift", CATEGORY_CRYSTALS, doesCrystalDrainRift, descDoesCrystalDrainRift);
        doesCrystalDrainKnowledge = cfg.getBoolean("doesCrystalDrainKnowledge", CATEGORY_CRYSTALS, doesCrystalDrainKnowledge, descDoesCrystalDrainKnowledge);
        doesCrystalDrainCustom = cfg.getBoolean("doesCrystalDrainCustom", CATEGORY_CRYSTALS, doesCrystalDrainCustom, descDoesCrystalDrainCustom);
        doesCrystalDrainCustom = cfg.getBoolean("doesCrystalDrainCustom", CATEGORY_CRYSTALS, doesCrystalDrainCustom, descDoesCrystalDrainCustom);

        doesCrystalCausePermInsanityRift = cfg.getBoolean("doesCrystalCausePermInsanityRift", CATEGORY_CRYSTALS, doesCrystalCausePermInsanityRift, descDoesCrystalCausePermInsanityRift);
    }

    public static void initInsanityConfig(Configuration cfg) {
        cfg.addCustomCategoryComment(CATEGORY_INSANITY, "This section determines how insanity functions.");

        mpeInsanityStage1 = cfg.getInt("mpeInsanityStage1", CATEGORY_INSANITY, mpeInsanityStage1, -1, Integer.MAX_VALUE, descMpeInsanityStage1);
        mpeInsanityStage2 = cfg.getInt("mpeInsanityStage2", CATEGORY_INSANITY, mpeInsanityStage2, -1, Integer.MAX_VALUE, descMpeInsanityStage2);
        mpeInsanityStage3 = cfg.getInt("mpeInsanityStage3", CATEGORY_INSANITY, mpeInsanityStage3, -1, Integer.MAX_VALUE, descMpeInsanityStage3);
        mpeInsanityStage4 = cfg.getInt("mpeInsanityStage4", CATEGORY_INSANITY, mpeInsanityStage4, -1, Integer.MAX_VALUE, descMpeInsanityStage4);
        mpeInsanityStage5 = cfg.getInt("mpeInsanityStage5", CATEGORY_INSANITY, mpeInsanityStage5, -1, Integer.MAX_VALUE, descMpeInsanityStage5);
        mpeInsanityStage6 = cfg.getInt("mpeInsanityStage6", CATEGORY_INSANITY, mpeInsanityStage6, -1, Integer.MAX_VALUE, descMpeInsanityStage6);
        mpeInsanityPerm = cfg.getInt("mpeInsanityPerm", CATEGORY_INSANITY, mpeInsanityPerm, -1, Integer.MAX_VALUE, descMpeInsanityPerm);

        enableTempInsanity = cfg.getBoolean("enableTempInsanity", CATEGORY_INSANITY, enableTempInsanity, descEnableTempInsanity);
        enablePermInsanity = cfg.getBoolean("enablePermInsanity", CATEGORY_INSANITY, enablePermInsanity, descEnablePermInsanity);
    }

    public static void initGeneralConfig(Configuration cfg) {
        cfg.addCustomCategoryComment(CATEGORY_GENERAL, "This section determines general settings for this mod.");

        enableRebinding = cfg.getBoolean("enableRebinding", CATEGORY_GENERAL, enableRebinding, descEnableRebinding);
        enableRestoration = cfg.getBoolean("enableRestoration", CATEGORY_GENERAL, enableRestoration, descEnableRestoration);
        enableDistillation = cfg.getBoolean("enableDistillation", CATEGORY_GENERAL, enableDistillation, descEnableDistillation);
    }

    public static void readConfig() {
        Configuration cfg = CommonProxy.config;
        try {
            cfg.load();
            initCrystalConfig(cfg);
            initInsanityConfig(cfg);
            initGeneralConfig(cfg);
        } catch (Exception e1) {
            Crystalline.logger.log(Level.ERROR, "Problem loading config file!", e1);
        } finally {
            if (cfg.hasChanged()) {
                cfg.save();
            }
        }
    }

}
