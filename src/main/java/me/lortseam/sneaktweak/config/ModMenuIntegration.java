package me.lortseam.sneaktweak.config;

import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;
import dev.isxander.yacl3.api.*;
import dev.isxander.yacl3.api.controller.FloatSliderControllerBuilder;
import dev.isxander.yacl3.api.controller.TickBoxControllerBuilder;
import net.minecraft.text.Text;

public class ModMenuIntegration implements ModMenuApi {
    @Override
    public ConfigScreenFactory<?> getModConfigScreenFactory() {
        return parent -> YetAnotherConfigLib.create(ModConfig.configInstance, (defaults, config, builder) -> builder
                        .title(Text.literal("SneakTweak Config."))
                        .category(ConfigCategory.createBuilder()
                                .name(Text.literal("SneakTweak Settings"))
                                .option(Option.<Boolean>createBuilder()
                                        .name(Text.literal("Smoothing enabled"))
                                        .binding(defaults.isSmoothingEnabled(), config::isSmoothingEnabled, config::setSmoothingEnabled)
                                        .controller(TickBoxControllerBuilder::create)
                                        .build())
                                .option(Option.<Float>createBuilder()
                                        .name(Text.literal("Speed modifier"))
                                        .description(OptionDescription.of(Text.literal("Changes long the sneak animation lasts")))
                                        .binding(defaults.getSpeedModifier(), config::getSpeedModifier, config::setSpeedModifier)
                                        .controller(opt -> FloatSliderControllerBuilder.create(opt)
                                                .range(0.3f, 2.0f)
                                                .step(0.1f))
                                        .build())
                                .group(OptionGroup.createBuilder()
                                        .name(Text.literal("Sneak Height"))
                                        .option(Option.<Boolean>createBuilder()
                                                .name(Text.literal("Increase Sneak Height"))
                                                .binding(defaults.increaseSneakingHeight(), config::increaseSneakingHeight, config::increaseSneakingHeight)
                                                .controller(TickBoxControllerBuilder::create)
                                                .build())
                                        .option(Option.<Float>createBuilder()
                                                .description(OptionDescription.of(Text.literal("Pre 1.9: 1.54\n1.9 - 1.13: 1.42")))
                                                .name(Text.literal("New Sneak Height"))
                                                .binding(defaults.getSneakHeightModifier(), config::getSneakHeightModifier, config::setSneakHeightModifier)
                                                .controller(opt -> FloatSliderControllerBuilder.create(opt)
                                                                .range(1.27f, 1.54f)
                                                                .step(0.01f)
                                                        .formatValue(value -> Text.of(String.format("%.2f", value))))
                                                .build())
                                        .build())
                                .build())
                        .save(ModConfig.configInstance.serializer()::save))
                .generateScreen(parent);
    }
}