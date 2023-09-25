package me.lortseam.sneaktweak.config;

import lombok.Getter;
import dev.isxander.yacl.config.ConfigEntry;
import dev.isxander.yacl.config.GsonConfigInstance;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.nio.file.Path;

public final class ModConfig {
    public static GsonConfigInstance<ModConfig> configInstance = GsonConfigInstance.createBuilder(ModConfig.class)
            .setPath(Path.of("./config/sneaktweak-yacl.json"))
            .build();
    public static ModConfig getConfig() {
        return ModConfig.configInstance.getConfig();
    }

    @Getter
    @Setter
    @ConfigEntry
    public boolean smoothingEnabled = true;

    @Getter
    @Setter
    @ConfigEntry
    public float speedModifier = 1.0f;

    @Accessors(fluent = true)
    @Getter
    @Setter
    @ConfigEntry
    public boolean increaseSneakingHeight = false;

    @Getter
    @Setter
    @ConfigEntry
    public float sneakHeightModifier = 1.42f;

    @Getter
    @Setter
    @ConfigEntry
    public boolean modifyThirdPersonSneakingEyeHeight = false;

    public enum SneakingEyeHeightType {

        DEFAULT,
        PRE_1_14,
        PRE_1_9,
        CUSTOM

    }

}
