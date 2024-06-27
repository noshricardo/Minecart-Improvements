package com.nosh.mixin;

import com.nosh.FastRail;
import net.fabricmc.fabric.api.block.v1.FabricBlock;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.vehicle.AbstractMinecartEntity;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.Property;
import org.jetbrains.annotations.NotNull;
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

    @Redirect(method = "moveOnRail", at = @At(value = "INVOKE", target = "Lnet/minecraft/block/BlockState;isOf(Lnet/minecraft/block/Block;)Z"))
    private boolean allowFastRails(BlockState state, Block block){
        return  state.isOf(Blocks.POWERED_RAIL) || state.isOf(new FastRail(AbstractBlock.Settings.create().noCollision()));
    }


}
