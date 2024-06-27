package com.nosh;

import eu.pb4.polymer.core.api.block.PolymerBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.PoweredRailBlock;
import net.minecraft.util.math.BlockPos;

public class FastRail extends PoweredRailBlock implements PolymerBlock {
    public FastRail(Settings settings) {
        super(settings);
    }

    @Override
    public BlockState getPolymerBlockState(BlockState state) {
        return Blocks.POWERED_RAIL.getDefaultState();
    }


}
