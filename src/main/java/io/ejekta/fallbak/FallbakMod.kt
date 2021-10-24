package io.ejekta.fallbak

import io.ejekta.kambrik.Kambrik
import net.fabricmc.api.ModInitializer

class FallbakMod : ModInitializer {

    internal companion object {
        const val ID = "fallbak"
    }

    private val logger = Kambrik.Logging.createLogger(ID)

    override fun onInitialize() {
        logger.info("Fallbak Mod Says Hello!")
    }

}