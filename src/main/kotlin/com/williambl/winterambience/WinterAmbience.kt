package com.williambl.winterambience

import com.williambl.winterambience.block.DecorativeLightControllerBlock
import com.williambl.winterambience.block.SoftSnowBlock
import com.williambl.winterambience.tileentity.DecorativeLightControllerTileEntity
import net.alexwells.kottle.KotlinEventBusSubscriber
import net.minecraft.block.Block
import net.minecraft.block.SoundType
import net.minecraft.block.material.Material
import net.minecraft.item.BlockItem
import net.minecraft.item.Item
import net.minecraft.item.ItemGroup
import net.minecraft.tileentity.TileEntityType
import net.minecraft.world.server.ChunkManager
import net.minecraftforge.event.RegistryEvent
import net.minecraftforge.eventbus.api.SubscribeEvent
import net.minecraftforge.fml.common.Mod
import net.minecraftforge.fml.common.ObfuscationReflectionHelper
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent
import net.minecraftforge.fml.event.server.FMLServerStartingEvent
import java.lang.reflect.Method
import java.util.function.Supplier

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
    lateinit var glowstoneBaubleBlock: BaubleBlock

    lateinit var whiteDecorativeLightBlock: DecorativeLightBlock
    lateinit var orangeDecorativeLightBlock: DecorativeLightBlock
    lateinit var magentaDecorativeLightBlock: DecorativeLightBlock
    lateinit var lightBlueDecorativeLightBlock: DecorativeLightBlock
    lateinit var yellowDecorativeLightBlock: DecorativeLightBlock
    lateinit var limeDecorativeLightBlock: DecorativeLightBlock
    lateinit var pinkDecorativeLightBlock: DecorativeLightBlock
    lateinit var grayDecorativeLightBlock: DecorativeLightBlock
    lateinit var lightGrayDecorativeLightBlock: DecorativeLightBlock
    lateinit var cyanDecorativeLightBlock: DecorativeLightBlock
    lateinit var purpleDecorativeLightBlock: DecorativeLightBlock
    lateinit var blueDecorativeLightBlock: DecorativeLightBlock
    lateinit var brownDecorativeLightBlock: DecorativeLightBlock
    lateinit var greenDecorativeLightBlock: DecorativeLightBlock
    lateinit var redDecorativeLightBlock: DecorativeLightBlock
    lateinit var blackDecorativeLightBlock: DecorativeLightBlock

    lateinit var decorativeLightControllerBlock: DecorativeLightControllerBlock
    lateinit var decorativeLightControllerTileEntityType: TileEntityType<DecorativeLightControllerTileEntity>

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
        redstoneBaubleBlock = BaubleBlock(isRedstone = true)
        glowstoneBaubleBlock = BaubleBlock(Block.Properties.create(Material.GLASS).lightValue(12))
        whiteDecorativeLightBlock = DecorativeLightBlock()
        orangeDecorativeLightBlock = DecorativeLightBlock()
        magentaDecorativeLightBlock = DecorativeLightBlock()
        lightBlueDecorativeLightBlock = DecorativeLightBlock()
        yellowDecorativeLightBlock = DecorativeLightBlock()
        limeDecorativeLightBlock = DecorativeLightBlock()
        pinkDecorativeLightBlock = DecorativeLightBlock()
        grayDecorativeLightBlock = DecorativeLightBlock()
        lightGrayDecorativeLightBlock = DecorativeLightBlock()
        cyanDecorativeLightBlock = DecorativeLightBlock()
        purpleDecorativeLightBlock = DecorativeLightBlock()
        blueDecorativeLightBlock = DecorativeLightBlock()
        brownDecorativeLightBlock = DecorativeLightBlock()
        greenDecorativeLightBlock = DecorativeLightBlock()
        redDecorativeLightBlock = DecorativeLightBlock()
        blackDecorativeLightBlock = DecorativeLightBlock()
        decorativeLightControllerBlock = DecorativeLightControllerBlock()

        event.registry.registerAll(
                softSnowBlock.setRegistryName("minecraft", "snow"),
                diamondBaubleBlock.setRegistryName("diamond_bauble"),
                emeraldBaubleBlock.setRegistryName("emerald_bauble"),
                goldBaubleBlock.setRegistryName("gold_bauble"),
                ironBaubleBlock.setRegistryName("iron_bauble"),
                lapisBaubleBlock.setRegistryName("lapis_bauble"),
                redstoneBaubleBlock.setRegistryName("redstone_bauble"),
                glowstoneBaubleBlock.setRegistryName("glowstone_bauble"),
                whiteDecorativeLightBlock.setRegistryName("white_decorative_light"),
                orangeDecorativeLightBlock.setRegistryName("orange_decorative_light"),
                magentaDecorativeLightBlock.setRegistryName("magenta_decorative_light"),
                lightBlueDecorativeLightBlock.setRegistryName("light_blue_decorative_light"),
                yellowDecorativeLightBlock.setRegistryName("yellow_decorative_light"),
                limeDecorativeLightBlock.setRegistryName("lime_decorative_light"),
                pinkDecorativeLightBlock.setRegistryName("pink_decorative_light"),
                grayDecorativeLightBlock.setRegistryName("gray_decorative_light"),
                lightGrayDecorativeLightBlock.setRegistryName("light_gray_decorative_light"),
                cyanDecorativeLightBlock.setRegistryName("cyan_decorative_light"),
                purpleDecorativeLightBlock.setRegistryName("purple_decorative_light"),
                blueDecorativeLightBlock.setRegistryName("blue_decorative_light"),
                brownDecorativeLightBlock.setRegistryName("brown_decorative_light"),
                greenDecorativeLightBlock.setRegistryName("green_decorative_light"),
                redDecorativeLightBlock.setRegistryName("red_decorative_light"),
                blackDecorativeLightBlock.setRegistryName("black_decorative_light"),
                decorativeLightControllerBlock.setRegistryName("decorative_light_controller")
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
                BlockItem(redstoneBaubleBlock, Item.Properties().group(ItemGroup.DECORATIONS)).setRegistryName("redstone_bauble"),
                BlockItem(glowstoneBaubleBlock, Item.Properties().group(ItemGroup.DECORATIONS)).setRegistryName("glowstone_bauble"),
                BlockItem(whiteDecorativeLightBlock, Item.Properties().group(ItemGroup.DECORATIONS)).setRegistryName("white_decorative_light"),
                BlockItem(orangeDecorativeLightBlock, Item.Properties().group(ItemGroup.DECORATIONS)).setRegistryName("orange_decorative_light"),
                BlockItem(magentaDecorativeLightBlock, Item.Properties().group(ItemGroup.DECORATIONS)).setRegistryName("magenta_decorative_light"),
                BlockItem(lightBlueDecorativeLightBlock, Item.Properties().group(ItemGroup.DECORATIONS)).setRegistryName("light_blue_decorative_light"),
                BlockItem(yellowDecorativeLightBlock, Item.Properties().group(ItemGroup.DECORATIONS)).setRegistryName("yellow_decorative_light"),
                BlockItem(limeDecorativeLightBlock, Item.Properties().group(ItemGroup.DECORATIONS)).setRegistryName("lime_decorative_light"),
                BlockItem(pinkDecorativeLightBlock, Item.Properties().group(ItemGroup.DECORATIONS)).setRegistryName("pink_decorative_light"),
                BlockItem(grayDecorativeLightBlock, Item.Properties().group(ItemGroup.DECORATIONS)).setRegistryName("gray_decorative_light"),
                BlockItem(lightGrayDecorativeLightBlock, Item.Properties().group(ItemGroup.DECORATIONS)).setRegistryName("light_gray_decorative_light"),
                BlockItem(cyanDecorativeLightBlock, Item.Properties().group(ItemGroup.DECORATIONS)).setRegistryName("cyan_decorative_light"),
                BlockItem(purpleDecorativeLightBlock, Item.Properties().group(ItemGroup.DECORATIONS)).setRegistryName("purple_decorative_light"),
                BlockItem(blueDecorativeLightBlock, Item.Properties().group(ItemGroup.DECORATIONS)).setRegistryName("blue_decorative_light"),
                BlockItem(brownDecorativeLightBlock, Item.Properties().group(ItemGroup.DECORATIONS)).setRegistryName("brown_decorative_light"),
                BlockItem(greenDecorativeLightBlock, Item.Properties().group(ItemGroup.DECORATIONS)).setRegistryName("green_decorative_light"),
                BlockItem(redDecorativeLightBlock, Item.Properties().group(ItemGroup.DECORATIONS)).setRegistryName("red_decorative_light"),
                BlockItem(blackDecorativeLightBlock, Item.Properties().group(ItemGroup.DECORATIONS)).setRegistryName("black_decorative_light"),
                BlockItem(decorativeLightControllerBlock, Item.Properties().group(ItemGroup.REDSTONE)).setRegistryName("decorative_light_controller")
        )
    }

    @SubscribeEvent
    fun registerTileEntityTypes(event: RegistryEvent.Register<TileEntityType<*>>) {
        decorativeLightControllerTileEntityType = TileEntityType.Builder.create(Supplier { DecorativeLightControllerTileEntity() }, decorativeLightControllerBlock).build(null)
        event.registry.registerAll(
                decorativeLightControllerTileEntityType.setRegistryName("decorative_light_controller")
        )
    }

}