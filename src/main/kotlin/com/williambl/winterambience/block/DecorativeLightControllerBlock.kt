package com.williambl.winterambience.block

import com.williambl.winterambience.tileentity.DecorativeLightControllerTileEntity
import net.minecraft.block.Block
import net.minecraft.block.BlockState
import net.minecraft.block.ITileEntityProvider
import net.minecraft.block.material.Material
import net.minecraft.state.StateContainer
import net.minecraft.state.properties.BlockStateProperties
import net.minecraft.tileentity.TileEntity
import net.minecraft.util.math.BlockPos
import net.minecraft.world.IBlockReader
import net.minecraft.world.World
import java.util.*

class DecorativeLightControllerBlock(properties: Properties = Block.Properties.create(Material.IRON)) : Block(properties), ITileEntityProvider {

    init {
        defaultState = stateContainer.baseState.with(BlockStateProperties.POWERED, false)
    }

    override fun createNewTileEntity(worldIn: IBlockReader): TileEntity? {
        return DecorativeLightControllerTileEntity()
    }

    override fun fillStateContainer(builder: StateContainer.Builder<Block, BlockState>) {
        super.fillStateContainer(builder)
        builder.add(BlockStateProperties.POWERED)
    }

    override fun neighborChanged(state: BlockState, world: World, pos: BlockPos, block: Block, neighborPos: BlockPos, isMove: Boolean) {
        if (!world.isRemote) {
            val isPowered = state.get(BlockStateProperties.POWERED)
            if (isPowered != world.isBlockPowered(pos)) {
                if (isPowered) {
                    world.pendingBlockTicks.scheduleTick(pos, this, 4)
                } else {
                    world.setBlockState(pos, state.cycle(BlockStateProperties.POWERED), 2)
                    (world.getTileEntity(pos) as DecorativeLightControllerTileEntity).enableLights()
                }
            }
        }
    }

    override fun tick(state: BlockState, world: World, pos: BlockPos, rand: Random) {
        if (!world.isRemote) {
            if (state.get(BlockStateProperties.POWERED) && !world.isBlockPowered(pos)) {
                world.setBlockState(pos, state.cycle(BlockStateProperties.POWERED), 2)
                (world.getTileEntity(pos) as DecorativeLightControllerTileEntity).disableLights()
            }
        }
    }

}