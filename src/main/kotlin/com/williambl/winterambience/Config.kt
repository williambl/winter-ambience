package com.williambl.winterambience

import net.minecraftforge.common.ForgeConfigSpec
import com.electronwill.nightconfig.core.io.WritingMode
import com.electronwill.nightconfig.core.file.CommentedFileConfig
import java.nio.file.Path

object Config {

    val SERVER_BUILDER = ForgeConfigSpec.Builder()

    val SERVER_SPEC: ForgeConfigSpec
    val SERVER_CONFIG: ServerConfig

    var doSnowPiling = true
    var doSnowMelting = true
    var snowPilesInfinitely = false
    var snowPileRate = 32

    init {
        SERVER_CONFIG = ServerConfig(SERVER_BUILDER)

        SERVER_SPEC = SERVER_BUILDER.build()
    }

    fun refreshServer() {
        doSnowPiling = SERVER_CONFIG.doSnowPiling.get()
        doSnowMelting = SERVER_CONFIG.doSnowMelting.get()
        snowPilesInfinitely = SERVER_CONFIG.snowPilesInfinitely.get()
        snowPileRate = SERVER_CONFIG.snowPileRate.get()
    }

    fun loadConfig(spec: ForgeConfigSpec, path: Path) {
        val configData = CommentedFileConfig.builder(path)
                .sync()
                .autosave()
                .writingMode(WritingMode.REPLACE)
                .build()
        configData.load()
        spec.setConfig(configData)
    }

    class ServerConfig internal constructor(builder: ForgeConfigSpec.Builder) {
        val doSnowPiling: ForgeConfigSpec.ConfigValue<Boolean>
        var snowPileRate: ForgeConfigSpec.ConfigValue<Int>
        val snowPilesInfinitely: ForgeConfigSpec.ConfigValue<Boolean>
        val doSnowMelting: ForgeConfigSpec.ConfigValue<Boolean>

        init {
            builder.push("snow")
            doSnowPiling = builder
                    .comment("Pile snow [default:true]")
                    .translation("config.pile_snow.enable")
                    .define("pile_snow.enable", true)
            snowPileRate = builder
                    .comment("Rate of snow piling (smaller numbers are faster)")
                    .translation("config.pile_snow.rate")
                    .defineInRange("pile_snow.rate", 32, 1, 128)
            snowPilesInfinitely = builder
                    .comment("Make snow pile infinitely [default:false]")
                    .translation("config.pile_snow.infinite")
                    .define("pile_snow.infinite", false)
            doSnowMelting = builder
                    .comment("Melt snow in the sunlight [default:true]")
                    .translation("config.melt_snow.enable")
                    .define("melt_snow.enable", true)

            builder.pop()
        }
    }

}
