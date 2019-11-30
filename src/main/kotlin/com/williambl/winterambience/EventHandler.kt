package com.williambl.winterambience

import net.alexwells.kottle.KotlinEventBusSubscriber
import net.minecraft.block.Blocks
import net.minecraft.block.SnowBlock
import net.minecraft.util.Direction
import net.minecraft.world.biome.Biome
import net.minecraft.world.gen.Heightmap
import net.minecraft.world.server.ChunkHolder
import net.minecraft.world.server.ServerWorld
import net.minecraftforge.event.TickEvent
import net.minecraftforge.eventbus.api.SubscribeEvent
import net.minecraftforge.fml.LogicalSide

@KotlinEventBusSubscriber(bus = KotlinEventBusSubscriber.Bus.FORGE)
object EventHandler {

    @SubscribeEvent
    fun pileSnow(event: TickEvent.WorldTickEvent) {
        if (event.side == LogicalSide.CLIENT)
            return
        else {
            val world = event.world as ServerWorld
            (WinterAmbience.getLoadedChunksIterable.invoke(world.chunkProvider.chunkManager) as Iterable<ChunkHolder>).forEach { chunkHolder ->
                chunkHolder.func_219297_b().getNow(ChunkHolder.UNLOADED_CHUNK).left().ifPresent {
                    if (world.dimension.canDoRainSnowIce(it) && world.isRaining && world.rand.nextInt(32) == 0) {
                        val pos = world.getHeight(Heightmap.Type.MOTION_BLOCKING, it.pos.asBlockPos().add(world.rand.nextInt(16), 0, world.rand.nextInt(16)))
                        if (world.getBiome(pos).precipitation == Biome.RainType.SNOW && world.isAreaLoaded(pos, 1)) {
                            val currentState = world.getBlockState(pos)
                            if (currentState.block == Blocks.SNOW) {
                                if (currentState.get(SnowBlock.LAYERS) < 8)
                                    world.setBlockState(pos, currentState.with(SnowBlock.LAYERS, currentState.get(SnowBlock.LAYERS) + 1))
                                else
                                    world.setBlockState(pos, Blocks.SNOW_BLOCK.defaultState)
                            }
                        }
                    }
                }
            }
        }
    }
}