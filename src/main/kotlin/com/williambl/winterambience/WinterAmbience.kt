package com.williambl.winterambience

import com.williambl.winterambience.block.SoftSnowBlock
import net.alexwells.kottle.KotlinEventBusSubscriber
import net.minecraft.block.Block
import net.minecraft.block.SoundType
import net.minecraft.block.material.Material
import net.minecraft.item.BlockItem
import net.minecraft.item.Item
import net.minecraft.item.ItemGroup
import net.minecraft.world.server.ChunkManager
import net.minecraftforge.event.RegistryEvent
import net.minecraftforge.eventbus.api.SubscribeEvent
import net.minecraftforge.fml.common.Mod
import net.minecraftforge.fml.common.ObfuscationReflectionHelper
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent
import net.minecraftforge.fml.event.server.FMLServerStartingEvent
import java.lang.reflect.Method
import net.minecraftforge.fml.config.ModConfig
import net.minecraftforge.fml.ModLoadingContext



@Mod(WinterAmbience.MODID)
@KotlinEventBusSubscriber(modid = WinterAmbience.MODID, bus = KotlinEventBusSubscriber.Bus.MOD)
object WinterAmbience {

    const val MODID = "winterambience"

    lateinit var getLoadedChunksIterable: Method

    lateinit var softSnowBlock: SoftSnowBlock

    init {
        ModLoadingContext.get().registerConfig(ModConfig.Type.SERVER, Config.SERVER_SPEC)
    }

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

    @SubscribeEvent
    fun registerBlocks(event: RegistryEvent.Register<Block>) {
        softSnowBlock = SoftSnowBlock(Block.Properties.create(Material.SNOW).tickRandomly().hardnessAndResistance(0.1f).sound(SoundType.SNOW))

        event.registry.registerAll(
                softSnowBlock.setRegistryName("minecraft", "snow")
        )
    }

    @SubscribeEvent
    fun registerItems(event: RegistryEvent.Register<Item>) {
        event.registry.registerAll(
                BlockItem(softSnowBlock, Item.Properties().group(ItemGroup.DECORATIONS)).setRegistryName("minecraft", "snow")
        )
    }

    @SubscribeEvent
    fun config(event: ModConfig.ModConfigEvent) {
        if (event.config.spec == Config.SERVER_SPEC) {
            Config.loadConfig(event.config.spec, event.config.fullPath)
            Config.refreshServer()
        }
    }

}