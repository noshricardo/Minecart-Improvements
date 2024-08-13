package com.nosh.mixin;


import com.nosh.FastRail;
import com.nosh.MinecartImprovements;
import net.minecraft.block.AbstractRailBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(AbstractRailBlock.class)
public class AbstractRailBlockMixin {

    @Redirect(method = "neighborUpdate", at = @At(value = "INVOKE", target = "Lnet/minecraft/block/BlockState;isOf(Lnet/minecraft/block/Block;)Z"))
    public boolean isOfMixin0(BlockState instance, Block block){
        if(instance.isOf(block) || instance.isOf(MinecartImprovements.FAST_RAIL)){
            return true;
        }
        return false;
    }

    @Redirect(method = "getOutlineShape", at = @At(value = "INVOKE", target = "Lnet/minecraft/block/BlockState;isOf(Lnet/minecraft/block/Block;)Z"))
    public boolean isOfMixin1(BlockState instance, Block block){
        if(instance.isOf(block) || instance.isOf(MinecartImprovements.FAST_RAIL)){
            return true;
        }
        return false;
    }

    @Redirect(method = "onBlockAdded", at = @At(value = "INVOKE", target = "Lnet/minecraft/block/BlockState;isOf(Lnet/minecraft/block/Block;)Z"))
    public boolean isOfMixin2(BlockState instance, Block block){
        if(instance.isOf(block) || instance.isOf(MinecartImprovements.FAST_RAIL)){
            return true;
        }
        return false;
    }





}
