package me.lortseam.sneaktweak.mixin;

import com.mojang.authlib.GameProfile;
import me.lortseam.sneaktweak.config.ModConfig;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityPose;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(ClientPlayerEntity.class)
public class ClientPlayerEntityMixin extends AbstractClientPlayerEntity {

    private ClientPlayerEntityMixin(ClientWorld world, GameProfile profile) {
        super(world, profile);
    }

    @Override
    public float getActiveEyeHeight(EntityPose pose, EntityDimensions dimensions) {
        float height = super.getActiveEyeHeight(pose, dimensions);
        if(!ModConfig.getConfig().increaseSneakingHeight()) return height;
        if (pose != EntityPose.CROUCHING || !wouldNotSuffocateInPose(EntityPose.STANDING)) return height;
        return ModConfig.getConfig().getSneakHeightModifier();
    }

}
