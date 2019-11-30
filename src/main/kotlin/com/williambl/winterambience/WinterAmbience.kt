package com.williambl.winterambience

import net.alexwells.kottle.KotlinEventBusSubscriber
import net.minecraft.world.server.ChunkManager
import net.minecraftforge.eventbus.api.SubscribeEvent
import net.minecraftforge.fml.common.Mod
import net.minecraftforge.fml.common.ObfuscationReflectionHelper
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent
import net.minecraftforge.fml.event.server.FMLServerStartingEvent
import java.lang.reflect.Method

@Mod(WinterAmbience.MODID)
@KotlinEventBusSubscriber(modid = WinterAmbience.MODID, bus = KotlinEventBusSubscriber.Bus.MOD)
object WinterAmbience {

    const val MODID = "winterambience"

    lateinit var getLoadedChunksIterable: Method

    @SubscribeEvent
    fun setup(event: FMLCommonSetupEvent) {
        getLoadedChunksIterable = ObfuscationReflectionHelper.findMethod(ChunkManager::class.java, "func_223491_f")
    }

    @SubscribeEvent
    fun clientSetup(event: FMLClientSetupEvent) {
    }

    @SubscribeEvent
    fun serverStart(event: FMLServerStartingEvent) {
    }

}