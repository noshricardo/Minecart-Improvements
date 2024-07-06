package com.nosh.mixin;

import com.nosh.FastRail;
import com.nosh.MinecartImprovements;
import net.fabricmc.fabric.api.block.v1.FabricBlock;
import net.minecraft.block.*;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.vehicle.AbstractMinecartEntity;
import net.minecraft.registry.Registries;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.state.State;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.Property;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

//import static com.nosh.MinecartImprovements.LOGGER;

@Mixin(AbstractMinecartEntity.class)
public abstract class MinecartMixin extends Entity{


    public MinecartMixin(EntityType<?> type, World world) {
        super(type, world);
    }

    @Redirect(method = "moveOnRail", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/vehicle/AbstractMinecartEntity;getMaxSpeed()D"))
    private double changeMaxSpeed(AbstractMinecartEntity instance){
        if (AbstractRailBlock.isRail(instance.getWorld().getBlockState(instance.getBlockPos()))){
            //return (instance.isTouchingWater() ? 4.0 : 8.0) * 2;
            return (double) 1;
        }
        if(instance.getWorld().getBlockState(instance.getBlockPos()).isOf(MinecartImprovements.FAST_RAIL)){
            return (double) 0.4;
        }
        return (instance.isTouchingWater() ? 4.0 : 8.0) / 20;
        //return (double) 0.2;
    }

    @Redirect(method = "tick", at = @At(value = "INVOKE", target = "Lnet/minecraft/block/AbstractRailBlock;isRail(Lnet/minecraft/block/BlockState;)Z"))
    private boolean makeRail(BlockState blockState){
        return AbstractRailBlock.isRail(blockState) || blockState.isOf(MinecartImprovements.FAST_RAIL);
    }

    /*
    @Redirect(method = "moveOnRail", at = @At(value = "INVOKE", target = "Lnet/minecraft/block/BlockState;get(Lnet/minecraft/state/property/Property;)Ljava/lang/Comparable;", ordinal = 0))
    private <T extends Comparable<T>> T Redirect(BlockState instance, Property<T> property){

        return (T) Boolean.TRUE;
    }
    */
    /*
    @Redirect(method = "moveOnRail", at = @At(value = "INVOKE", target = "Lnet/minecraft/block/BlockState;get(Lnet/minecraft/state/property/Property;)Ljava/lang/Comparable;", ordinal = 1))
    private Comparable Redirect(BlockState instance, Property property){
        return (Comparable) Boolean.TRUE;
    }
    */

    //}
//    @Redirect(method = "moveOnRail", at = @At(value = "INVOKE", target = "Lnet/minecraft/state/State;get(Lnet/minecraft/state/property/Property;)Ljava/lang/compareable"))
//    private State<Block, BlockState> ChangePowered(BlockState state, BooleanProperty prop){
//
//        return null;
//    }

    //"Lnet/minecraft/state/State;get(Lnet/minecraft/state/property/Property;)Ljava/lang/Comparable"
    //"Lnet/minecraft/state/State;get(Lnet/minecraft/state/property/Property;)Ljava/lang/Comparable"
    /*
    @Redirect(method = "moveOnRail", at = @At(value = "INVOKE", target = "/state/get/"))
    private  <T extends Comparable<T>> T ChangePowered(BlockState state, Property<Boolean> prop){
        //Comparable<?> comparable = state.propertyMap.get(prop);
        //return (T) prop.getType().cast(comparable);
        return (T) Boolean.TRUE;
    }
    */
    /*
    @Inject(method = "moveOnRail", at = @At(value = "INVOKE", target = "Lnet/minecraft/block/BlockState;get(Lnet/minecraft/state/property/Property;)Ljava/lang/Comparable;", shift = At.Shift.AFTER))
    private void inject1(BlockPos pos, BlockState state, CallbackInfo ci){
        boolean bl = true;
    }
    */
    //@Accessor("bl")
    //public void setRailPowered(boolean b);
    //@Redirect(method = "moveOnRail", at = @At(value = "INVOKE", target = "Lnet/minecraft/block/state/State;get(Lnet/minecraft/state/property/BooleanProperty;)Lnet/minecraft/state/State"))
    //private State<D, T> ChangeState(BooleanProperty property{

    //}
    /*
    @Inject(method = "moveOnRail", at = @At(value = "INVOKE_ASSIGN", target = "Lnet/minecraft/block/state/State:get()Lnet/java/lang/Comparable"))
    private void ChangePowered(BlockPos pos, BlockState state, CallbackInfo ci){

    }
    */
    @Redirect(method = "moveOffRail", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/vehicle/AbstractMinecartEntity;getMaxSpeed()D"))
    private double changeMaxOffRailSpeed(AbstractMinecartEntity instance){
        return (instance.isTouchingWater() ? 4.0 : 8.0) / 20;
    }

    @Redirect(method = "moveOnRail", at = @At(value = "INVOKE", target = "Lnet/minecraft/block/BlockState;isOf(Lnet/minecraft/block/Block;)Z"))
    private boolean allowFastRails(BlockState state, Block block){
        //return true;
        return  state.isOf(Blocks.POWERED_RAIL) || state.isOf(MinecartImprovements.FAST_RAIL);
    }

    @Redirect(method = "moveOnRail", at = @At(value = "INVOKE", target = "Lnet/minecraft/util/math/Vec3d;add(DDD)Lnet/minecraft/util/math/Vec3d;", ordinal = 5))
    private Vec3d increaseAccelForNewRails(Vec3d vec, double x, double y, double z) {
        Vec3d newvec = vec.add(x, y, z);
        BlockState blockState = this.getWorld().getBlockState(this.getBlockPos());
        return newvec;
        /*
        if(blockState.isOf(Blocks.POWERED_RAIL)){
            return newvec.multiply(4);
        }

        //return newvec.multiply((double) 90 /8);
        return newvec.multiply((double) 2);

         */
    }


    /*
    @Redirect(method = "moveOnRail", at = @At(value = "INVOKE", target = "Ljava/lang/Math;min(DD)D"))
    private double increaseSpeedCap(double a, double b) {
        return Math.min(8.0, b);
    }
    */


}
