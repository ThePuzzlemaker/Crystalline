package ext.tpz.crystalline.common.util;

import ext.tpz.crystalline.api.CStatic;
import net.minecraftforge.common.config.Config;

@Config(modid = CStatic.MODID, name = "Crystalline", type = Config.Type.INSTANCE)
public class CConfig {

    public static Insanity insanity = new Insanity();

    public static class Insanity {

        @Config.Name("Enable Insanity")
        @Config.Comment("Enables or disables insanity")
        @Config.RequiresMcRestart
        public boolean enabled = true;

        @Config.Name("Crystal Insanity Blacklist")
        @Config.Comment("Disables insanity for crystal IDs.")
        public String[] blacklist = new String[] {};

        @Config.Name("Crystal Insanity Whitelist")
        @Config.Comment("Enables insanity for crystal IDs.")
        public String[] whitelist = new String[] {};

        @Config.Name("Insanity Chance")
        @Config.Comment("Percent chance of an insanity event after an insanity stage timer.")
        @Config.RangeInt(min = 0, max = 100)
        @Config.SlidingOption
        public int chance = 50;

        public Stages stages = new Stages();

        public static class Stages {

            @Config.Name("Stage 1 Timer")
            @Config.Comment("The time in ticks between insanity event timers. Stage 1: 0% to 9%")
            @Config.RangeInt(min = 0)
            @Config.RequiresWorldRestart
            public int s1 = 72000;

            @Config.Name("Stage 2 Timer")
            @Config.Comment("The time in ticks between insanity event timers. Stage 2: 10% to 9%")
            @Config.RangeInt(min = 0)
            @Config.RequiresWorldRestart
            public int s2 = 54000;

            @Config.Name("Stage 3 Timer")
            @Config.Comment("The time in ticks between insanity event timers. Stage 3: 20% to 29%")
            @Config.RangeInt(min = 0)
            @Config.RequiresWorldRestart
            public int s3 = 48000;

            @Config.Name("Stage 4 Timer")
            @Config.Comment("The time in ticks between insanity event timers. Stage 4: 30% to 39%")
            @Config.RangeInt(min = 0)
            @Config.RequiresWorldRestart
            public int s4 = 42000;

            @Config.Name("Stage 5 Timer")
            @Config.Comment("The time in ticks between insanity event timers. Stage 5: 40% to 49%")
            @Config.RangeInt(min = 0)
            @Config.RequiresWorldRestart
            public int s5 = 36000;

            @Config.Name("Stage 6 Timer")
            @Config.Comment("The time in ticks between insanity event timers. Stage 6: 50% to 59%")
            @Config.RangeInt(min = 0)
            @Config.RequiresWorldRestart
            public int s6 = 30000;

            @Config.Name("Stage 7 Timer")
            @Config.Comment("The time in ticks between insanity event timers. Stage 7: 60% to 69%")
            @Config.RangeInt(min = 0)
            @Config.RequiresWorldRestart
            public int s7 = 24000;

            @Config.Name("Stage 8 Timer")
            @Config.Comment("The time in ticks between insanity event timers. Stage 8: 70% to 79%")
            @Config.RangeInt(min = 0)
            @Config.RequiresWorldRestart
            public int s8 = 18000;

            @Config.Name("Stage 9 Timer")
            @Config.Comment("The time in ticks between insanity event timers. Stage 9: 80% to 89%")
            @Config.RangeInt(min = 0)
            @Config.RequiresWorldRestart
            public int s9 = 12000;

            @Config.Name("Stage 10 Timer")
            @Config.Comment("The time in ticks between insanity event timers. Stage 10: 90% to 99%")
            @Config.RangeInt(min = 0)
            @Config.RequiresWorldRestart
            public int s10 = 6000;

            @Config.Name("Permanent Timer")
            @Config.Comment("The time in ticks between insanity event timers. Permanent: 100%")
            @Config.RangeInt(min = 0)
            @Config.RequiresWorldRestart
            public int sP = 3000;

        }


    }

    public static Crystals crystals = new Crystals();

    public static class Crystals {

        @Config.Name("Crystal Blacklist")
        @Config.Comment("Disables crystal IDs")
        public String[] blacklist = new String[] {};

        @Config.Name("Mode Blacklist")
        @Config.Comment("Disables crystal mode IDs")
        public String[] modeBlacklist = new String[] {};

        @Config.Name("Administration Crystal Strength")
        @Config.Comment("Strength of the Administration Crystal's push")
        @Config.RangeDouble(min=0, max=1)
        @Config.SlidingOption
        public double adminStrength = 0.5d;

        @Config.Name("Atmosburst Crystal Strength")
        @Config.Comment("Strength of the Atmosburst Crystal's push")
        @Config.RangeDouble(min=0, max=1)
        @Config.SlidingOption
        public double atmosStrength = 0.2d;

        @Config.Name("Dirtshield Crystal Length")
        @Config.Comment("Length of the Dirtshield effect in ticks")
        @Config.RangeInt(min=0,max=999999)
        public int dirtLength = 100;

        @Config.Name("Dirtshield Damage Reduction")
        @Config.Comment("How much percentage of damage is blocked by the Dirtshield effect")
        @Config.RangeInt(min=0,max=100)
        @Config.SlidingOption
        public int dirtReduction = 75;

        @Config.Name("Life Crystal Regeneration Length")
        @Config.Comment("Length of the Life Crystal's regeneration effect in ticks")
        @Config.SlidingOption
        @Config.RangeInt(min=0,max=999999)
        public int lifeLength = 3000;

        @Config.Name("Life Crystal Regeneration Modifier")
        @Config.Comment("The potion multiplier of the Life Crystal's regeneration effect")
        @Config.RangeInt(min=0, max=255)
        @Config.SlidingOption
        public int lifeModifier = 1;

    }

}
