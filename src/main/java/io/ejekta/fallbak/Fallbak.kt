package io.ejekta.fallbak

import io.ejekta.kambrik.text.textLiteral
import net.minecraft.item.ItemStack
import net.minecraft.item.Items
import net.minecraft.nbt.NbtCompound
import net.minecraft.nbt.NbtString
import net.minecraft.util.Formatting
import net.minecraft.util.Identifier

object Fallbak {

    val replacements = mutableListOf(
        "minecraft:diamond" to ReplacementItem("minecraft:stick")
    )

    fun onLoadBadNbtItemstack(nbt: NbtCompound): ItemStack {
        println("Loading data for! $nbt")
        val id = nbt["id"] as? NbtString ?: return ItemStack.EMPTY
        val identifier = Identifier(id.asString())

//        if (identifier in Registry.ITEM.ids) {
//            return ItemStack(Registry.ITEM[identifier])
//        } else {
//
//        }

        val replacement = replacements.firstOrNull {
            it.first.toRegex().matchEntire(id.asString())?.value != null && it.second.exists()
        }

        return replacement?.second?.replace(nbt.getCompound("nbt")) ?: ItemStack(Items.BUCKET)

        // IOU
        return getIOU(identifier)
    }

    fun getIOU(id: Identifier) = ItemStack(Items.PAPER).apply {
        setCustomName(
            textLiteral("IOU - $id") {
                format(Formatting.DARK_RED, Formatting.ITALIC)
            }
        )
    }


}