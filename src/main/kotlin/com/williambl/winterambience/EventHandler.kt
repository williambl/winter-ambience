package com.williambl.winterambience

import net.alexwells.kottle.KotlinEventBusSubscriber
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
            (WinterAmbience.getLoadedChunksIterable.invoke(world.chunkProvider.chunkManager) as Iterable<ChunkHolder>).forEach {
            }
        }
    }
}