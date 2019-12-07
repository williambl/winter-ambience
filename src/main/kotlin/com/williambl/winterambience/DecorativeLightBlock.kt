package com.williambl.winterambience

import net.minecraft.block.Block
import net.minecraft.block.BlockState
import net.minecraft.block.material.Material
import net.minecraft.state.StateContainer
import net.minecraft.state.properties.BlockStateProperties
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.shapes.ISelectionContext
import net.minecraft.util.math.shapes.VoxelShape
import net.minecraft.world.IBlockReader
import java.awt.Color

class DecorativeLightBlock(properties: Properties = Properties.create(Material.IRON)) : Block(properties) {

    init {
        defaultState = stateContainer.baseState.with(BlockStateProperties.POWERED, false)
    }

    override fun getShape(p_220053_1_: BlockState, p_220053_2_: IBlockReader, p_220053_3_: BlockPos, p_220053_4_: ISelectionContext): VoxelShape {
        return makeCuboidShape(6.0, 12.0, 6.0, 10.0, 16.0, 10.0)
    }

    override fun getLightValue(state: BlockState): Int {
        return if (state.get(BlockStateProperties.POWERED)) 12 else 0
    }

    override fun fillStateContainer(builder: StateContainer.Builder<Block, BlockState>) {
        super.fillStateContainer(builder)
        builder.add(BlockStateProperties.POWERED)
    }
}