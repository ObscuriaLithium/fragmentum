package dev.obscuria.fragmentum.content.world.tooltip;

import net.minecraft.network.chat.Component;
import org.apache.commons.lang3.NotImplementedException;
import org.jetbrains.annotations.Nullable;

import java.util.List;

@SuppressWarnings("unused")
public class Tooltips {

    public static List<Component> process(Component input) {
        throw new NotImplementedException();
    }

    public static List<Component> process(Component input, Object source) {
        throw new NotImplementedException();
    }

    public static List<Component> process(Component input, Object source, TooltipOptions options) {
        throw new NotImplementedException();
    }

    public static List<Component> process(String input, @Nullable Object source, TooltipOptions options) {
        throw new NotImplementedException();
    }
}