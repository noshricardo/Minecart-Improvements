package com.nosh;

import eu.pb4.polymer.core.api.block.PolymerBlock;
import net.minecraft.block.*;
import net.minecraft.block.enums.RailShape;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.chunk.BlockColumn;

public class FastRail extends PoweredRailBlock implements PolymerBlock {
    public FastRail(AbstractBlock.Settings settings) {
        super(settings);
    }





    @Override
    public BlockState getPolymerBlockState(BlockState state) {
        return Blocks.POWERED_RAIL.getDefaultState().with(SHAPE, state.get(SHAPE));
    }




    /*
    @Override
    protected boolean isPoweredByOtherRails(World world, BlockPos pos, BlockState state, boolean bl, int distance){

        return(super.isPoweredByOtherRails(world,pos, state, bl, distance));
    }
    */

}
