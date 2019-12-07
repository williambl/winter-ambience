package com.williambl.winterambience

import net.minecraft.block.Block
import net.minecraft.block.BlockState
import net.minecraft.block.material.Material
import net.minecraft.util.Direction
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.shapes.ISelectionContext
import net.minecraft.util.math.shapes.VoxelShape
import net.minecraft.world.IBlockReader

class BaubleBlock(properties: Properties = Properties.create(Material.IRON), val isRedstone: Boolean = false) : Block(properties) {
    override fun getShape(p_220053_1_: BlockState, p_220053_2_: IBlockReader, p_220053_3_: BlockPos, p_220053_4_: ISelectionContext): VoxelShape {
        return makeCuboidShape(6.0, 12.0, 6.0, 10.0, 16.0, 10.0)
    }

    override fun getWeakPower(state: BlockState, world: IBlockReader, pos: BlockPos, direction: Direction): Int {
        return if (isRedstone) 15 else super.getWeakPower(state, world, pos, direction)
    }

    override fun canProvidePower(state: BlockState): Boolean {
        return isRedstone
    }

}