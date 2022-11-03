package ch.njol.unofficialmonumentamod;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.ingame.GenericContainerScreen;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;

import java.util.List;

public abstract class Utils {

    private Utils() {
    }

    /**
     * Gets the plain display name of an items. This is used by Monumenta to distinguish items.
     *
     * @param itemStack An item stack
     * @return The plain display name of the item, i.e. the value of NBT node plain.display.Name.
     */
    public static String getPlainDisplayName(ItemStack itemStack) {
        return itemStack.getNbt() == null ? null : itemStack.getNbt().getCompound("plain").getCompound("display").getString("Name");
    }

    public static float smoothStep(float f) {
        if (f <= 0) return 0;
        if (f >= 1) return 1;
        return f * f * (3 - 2 * f);
    }

    public static List<Text> getTooltip(ItemStack stack) {
        return stack.getTooltip(MinecraftClient.getInstance().player, TooltipContext.Default.NORMAL);
    }

}
