package me.lortseam.sneaktweak.config;

import dev.isxander.yacl3.config.v2.api.ConfigClassHandler;
import dev.isxander.yacl3.config.v2.api.SerialEntry;
import dev.isxander.yacl3.config.v2.api.serializer.GsonConfigSerializerBuilder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import net.fabricmc.loader.api.FabricLoader;

public final class ModConfig {
    public static ConfigClassHandler<ModConfig> configInstance = ConfigClassHandler.createBuilder(ModConfig.class)
            .serializer(config -> GsonConfigSerializerBuilder.create(config)
                    .setPath(FabricLoader.getInstance().getConfigDir().resolve("sneaktweak-yacl.json"))
                    .setJson5(true)
                    .build())
            .build();
    public static ModConfig getConfig() {
        return ModConfig.configInstance.instance();
    }

    @Getter
    @Setter
    @SerialEntry
    public boolean smoothingEnabled = true;

    @Getter
    @Setter
    @SerialEntry
    public float speedModifier = 1.0f;

    @Accessors(fluent = true)
    @Getter
    @Setter
    @SerialEntry
    public boolean increaseSneakingHeight = false;

    @Getter
    @Setter
    @SerialEntry
    public float sneakHeightModifier = 1.42f;
}
