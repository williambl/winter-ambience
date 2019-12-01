package com.williambl.winterambience

import net.minecraft.block.BlockState
import net.minecraft.block.SnowBlock
import net.minecraft.entity.Entity
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.shapes.ISelectionContext
import net.minecraft.util.math.shapes.VoxelShape
import net.minecraft.world.IBlockReader
import net.minecraft.world.World

class SoftSnowBlock(properties: Properties) : SnowBlock(properties) {

    override fun getCollisionShape(state: BlockState, world: IBlockReader, pos: BlockPos, context: ISelectionContext): VoxelShape {
        return SHAPES[0]
    }

    override fun onEntityCollision(state: BlockState, world: World, pos: BlockPos, entity: Entity) {
        if (state.get(LAYERS) > 4)
            entity.motion = entity.motion.mul(0.6, 1.0, 0.6)
        super.onEntityCollision(state, world, pos, entity)
    }
}