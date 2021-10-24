package io.ejekta.fallbak

import net.minecraft.item.ItemStack
import net.minecraft.nbt.NbtCompound
import net.minecraft.util.Identifier
import net.minecraft.util.registry.Registry

data class ReplacementItem(
    val newId: String,
    val keepNbt: Boolean = false,
) {
    fun exists() = Identifier(newId) in Registry.ITEM.ids

    fun replace(oldNbt: NbtCompound): ItemStack {
        return if (exists()) {
            ItemStack(Registry.ITEM[Identifier(newId)]).apply {
                if (keepNbt) {
                    nbt = oldNbt
                }
            }
        } else {
            ItemStack.EMPTY
        }
    }

}