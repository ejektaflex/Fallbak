package io.ejekta.fallbak.mixins;


import io.ejekta.fallbak.Fallbak;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ItemStack.class)
public class ItemLoadMixin {
    @Inject(
            method = "fromNbt(Lnet/minecraft/nbt/NbtCompound;)Lnet/minecraft/item/ItemStack;",
            at = @At("RETURN"), // Run at last return statement ("TAIL")
            cancellable = true
    )
    private static void loadEmptyItemStack(NbtCompound nbt, CallbackInfoReturnable<ItemStack> cir) {
        cir.setReturnValue(Fallbak.INSTANCE.onLoadBadNbtItemstack(nbt));
    }
}
