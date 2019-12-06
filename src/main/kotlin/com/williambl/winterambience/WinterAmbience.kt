package com.williambl.winterambience

import com.williambl.winterambience.block.SoftSnowBlock
import net.alexwells.kottle.KotlinEventBusSubscriber
import net.minecraft.block.Block
import net.minecraft.block.BlockState
import net.minecraft.block.SnowBlock
import net.minecraft.block.SoundType
import net.minecraft.block.material.Material
import net.minecraft.item.BlockItem
import net.minecraft.item.Item
import net.minecraft.item.ItemGroup
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.shapes.ISelectionContext
import net.minecraft.util.math.shapes.VoxelShape
import net.minecraft.world.IBlockReader
import net.minecraft.world.server.ChunkManager
import net.minecraftforge.event.RegistryEvent
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

    lateinit var softSnowBlock: SoftSnowBlock
    lateinit var diamondBaubleBlock: BaubleBlock
    lateinit var emeraldBaubleBlock: BaubleBlock
    lateinit var goldBaubleBlock: BaubleBlock
    lateinit var ironBaubleBlock: BaubleBlock
    lateinit var lapisBaubleBlock: BaubleBlock
    lateinit var redstoneBaubleBlock: BaubleBlock

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
        diamondBaubleBlock = BaubleBlock()
        emeraldBaubleBlock = BaubleBlock()
        goldBaubleBlock = BaubleBlock()
        ironBaubleBlock = BaubleBlock()
        lapisBaubleBlock = BaubleBlock()
        redstoneBaubleBlock = BaubleBlock()

        event.registry.registerAll(
                softSnowBlock.setRegistryName("minecraft", "snow"),
                diamondBaubleBlock.setRegistryName("diamond_bauble"),
                emeraldBaubleBlock.setRegistryName("emerald_bauble"),
                goldBaubleBlock.setRegistryName("gold_bauble"),
                ironBaubleBlock.setRegistryName("iron_bauble"),
                lapisBaubleBlock.setRegistryName("lapis_bauble"),
                redstoneBaubleBlock.setRegistryName("redstone_bauble")
        )
    }

    @SubscribeEvent
    fun registerItems(event: RegistryEvent.Register<Item>) {
        event.registry.registerAll(
                BlockItem(softSnowBlock, Item.Properties().group(ItemGroup.DECORATIONS)).setRegistryName("minecraft", "snow"),
                BlockItem(diamondBaubleBlock, Item.Properties().group(ItemGroup.DECORATIONS)).setRegistryName("diamond_bauble"),
                BlockItem(emeraldBaubleBlock, Item.Properties().group(ItemGroup.DECORATIONS)).setRegistryName("emerald_bauble"),
                BlockItem(goldBaubleBlock, Item.Properties().group(ItemGroup.DECORATIONS)).setRegistryName("gold_bauble"),
                BlockItem(ironBaubleBlock, Item.Properties().group(ItemGroup.DECORATIONS)).setRegistryName("iron_bauble"),
                BlockItem(lapisBaubleBlock, Item.Properties().group(ItemGroup.DECORATIONS)).setRegistryName("lapis_bauble"),
                BlockItem(redstoneBaubleBlock, Item.Properties().group(ItemGroup.DECORATIONS)).setRegistryName("redstone_bauble")
        )
    }

}