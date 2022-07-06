package ml.pkom.lunachat.mixin;

import com.github.ucchyocean.lc3.japanize.IMEConverter;
import com.github.ucchyocean.lc3.japanize.YukiKanaConverter;
import net.minecraft.server.filter.FilteredMessage;
import net.minecraft.server.filter.TextStream;
import net.minecraft.server.network.ServerPlayNetworkHandler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.function.Consumer;

@Mixin(ServerPlayNetworkHandler.class)
public class ChatMixin {

    @Inject(method = "filterText(Ljava/lang/String;Ljava/util/function/Consumer;)V", at = @At("HEAD"), cancellable = true)
    private void filterText(String text, Consumer<FilteredMessage<String>> consumer, CallbackInfo ci) {
        ServerPlayNetworkHandlerAccessor handlerAccessor = ((ServerPlayNetworkHandlerAccessor) this);
        if (text.startsWith("#")) {
            text = text.substring(1);
            handlerAccessor.invokeFilterText(replacer(text), consumer, TextStream::filterText);
            ci.cancel();
            return;
        }

        if (containsUnicode(text)) {
            handlerAccessor.invokeFilterText(replacer(text), consumer, TextStream::filterText);
            ci.cancel();
            return;
        }

        text = text + " &6(" + IMEConverter.convByGoogleIME(YukiKanaConverter.conv(replacerSys(text))) + ")";
        handlerAccessor.invokeFilterText(replacer(text), consumer, TextStream::filterText);
        ci.cancel();
    }

    private static boolean containsUnicode(String str) {
        for(int i = 0 ; i < str.length() ; i++) {
            char ch = str.charAt(i);
            Character.UnicodeBlock unicodeBlock = Character.UnicodeBlock.of(ch);

            if (Character.UnicodeBlock.HIRAGANA.equals(unicodeBlock))
                return true;

            if (Character.UnicodeBlock.KATAKANA.equals(unicodeBlock))
                return true;

            if (Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS.equals(unicodeBlock))
                return true;

            if (Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS.equals(unicodeBlock))
                return true;

            if (Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION.equals(unicodeBlock))
                return true;
        }
        return false;
    }

    private static String replacer(String string) {
        string = string.replace("&a", "§a");
        string = string.replace("&b", "§b");
        string = string.replace("&c", "§c");
        string = string.replace("&d", "§d");
        string = string.replace("&e", "§e");
        string = string.replace("&f", "§f");
        string = string.replace("&0", "§0");
        string = string.replace("&1", "§1");
        string = string.replace("&2", "§2");
        string = string.replace("&3", "§3");
        string = string.replace("&4", "§4");
        string = string.replace("&5", "§5");
        string = string.replace("&6", "§6");
        string = string.replace("&7", "§7");
        string = string.replace("&8", "§8");
        string = string.replace("&9", "§9");
        string = string.replace("&r", "§r");
        string = string.replace("&l", "§l");
        string = string.replace("&m", "§m");
        string = string.replace("&n", "§n");
        return string;
    }

    private static String replacerSys(String string) {
        string = string.replace("&a", "");
        string = string.replace("&b", "");
        string = string.replace("&c", "");
        string = string.replace("&d", "");
        string = string.replace("&e", "");
        string = string.replace("&f", "");
        string = string.replace("&0", "");
        string = string.replace("&1", "");
        string = string.replace("&2", "");
        string = string.replace("&3", "");
        string = string.replace("&4", "");
        string = string.replace("&5", "");
        string = string.replace("&6", "");
        string = string.replace("&7", "");
        string = string.replace("&8", "");
        string = string.replace("&9", "");
        string = string.replace("&r", "");
        string = string.replace("&l", "");
        string = string.replace("&m", "");
        string = string.replace("&n", "");
        return string;
    }
}
