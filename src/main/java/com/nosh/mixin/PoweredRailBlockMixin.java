package com.nosh.mixin;


import com.nosh.MinecartImprovements;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.PoweredRailBlock;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(PoweredRailBlock.class)
public class PoweredRailBlockMixin {

    @Redirect(method = "isPoweredByOtherRails", at = @At(value = "INVOKE", target = "Lnet/minecraft/block/BlockState;isOf(Lnet/minecraft/block/Block;)Z"))
    public boolean isOfMixin0(BlockState instance, Block block){
        if(instance.isOf(block) || instance.isOf(MinecartImprovements.FAST_RAIL)){
            return true;
        }
        return false;
    }



}
