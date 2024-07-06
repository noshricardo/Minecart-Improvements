package com.nosh.mixin;


import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.item.MinecartItem;
import net.minecraft.registry.tag.TagKey;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(MinecartItem.class)
public class MinecartItemMixin {


    @Redirect(method = "useOnBlock", at = @At(value = "INVOKE", target = "Lnet/minecraft/block/BlockState;isIn(Lnet/minecraft/registry/tag/TagKey;)Z"))
    public boolean isInMixin(BlockState state, TagKey<Block> tag){
        return true;
    }





}
