package me.lortseam.sneaktweak.config;

import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;
import dev.isxander.yacl.api.ConfigCategory;
import dev.isxander.yacl.api.Option;
import dev.isxander.yacl.api.OptionGroup;
import dev.isxander.yacl.api.YetAnotherConfigLib;
import dev.isxander.yacl.gui.controllers.TickBoxController;
import dev.isxander.yacl.gui.controllers.slider.FloatSliderController;
import net.minecraft.text.Text;

public class ModMenuIntegration implements ModMenuApi {
    @Override
    public ConfigScreenFactory<?> getModConfigScreenFactory() {
        return parent -> YetAnotherConfigLib.create(ModConfig.configInstance, (defaults, config, builder) -> builder
                        .title(Text.literal("SneakTweak Config."))
                        .category(ConfigCategory.createBuilder()
                                .name(Text.literal("SneakTweak Settings"))
                                .option(Option.createBuilder(boolean.class)
                                        .name(Text.literal("Smoothing enabled"))
                                        .binding(defaults.isSmoothingEnabled(), config::isSmoothingEnabled, config::setSmoothingEnabled)
                                        .controller(TickBoxController::new)
                                        .build())
                                .option(Option.createBuilder(float.class)
                                        .name(Text.literal("Speed modifier"))
                                        .binding(defaults.getSpeedModifier(), config::getSpeedModifier, config::setSpeedModifier)
                                        .controller(opt -> new FloatSliderController(opt, 0.3f, 2.0f, 0.1f))
                                        .build())
                                .group(OptionGroup.createBuilder()
                                        .name(Text.literal("Sneak Height"))
                                        .option(Option.createBuilder(boolean.class)
                                                .name(Text.literal("Increase Sneak Height"))
                                                .binding(defaults.increaseSneakingHeight, config::increaseSneakingHeight, config::increaseSneakingHeight)
                                                .controller(TickBoxController::new)
                                                .build())
                                        .option(Option.createBuilder(float.class)
                                                .name(Text.literal("New Sneak Height"))
                                                .binding(defaults.getSneakHeightModifier(), config::getSneakHeightModifier, config::setSneakHeightModifier)
                                                .controller(opt -> new FloatSliderController(opt, 1.27f, 1.50f, 0.01f, value -> Text.of(String.format("%.2f", value))))
                                                .build())
                                        .build())
                                .build())
                        .save(ModConfig.configInstance::save))
                .generateScreen(parent);
    }
}