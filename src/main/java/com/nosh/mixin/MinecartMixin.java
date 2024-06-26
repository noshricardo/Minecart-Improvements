package com.nosh.mixin;

import net.minecraft.block.Blocks;
import net.minecraft.entity.vehicle.AbstractMinecartEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(AbstractMinecartEntity.class)
public class MinecartMixin {
    @Redirect(method = "moveOnRail", at = @At(value = "INVOKE", target = "getMaxSpeed()D"))
    private double changeMaxSpeed(AbstractMinecartEntity instance){
        if (instance.getWorld().getBlockState(instance.getBlockPos()).isOf(Blocks.POWERED_RAIL)){
            return (instance.isTouchingWater() ? 4.0 : 8.0) * 10;
        }
        return (instance.isTouchingWater() ? 4.0 : 8.0) * 10;
    }

    @Redirect(method = "moveOffRail", at = @At(value = "INVOKE", target = "getMaxSpeed()D"))
    private double changeMaxOffRailSpeed(AbstractMinecartEntity instance){
        return (instance.isTouchingWater() ? 4.0 : 8.0) * 10;
    }


}
