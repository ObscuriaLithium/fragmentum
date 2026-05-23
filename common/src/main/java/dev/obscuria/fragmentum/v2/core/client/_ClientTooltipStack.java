package dev.obscuria.fragmentum.v2.core.client;

import dev.obscuria.fragmentum.v2.api.client.ClientTooltipStack;
import dev.obscuria.fragmentum.v2.api.common.TooltipStack;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.screens.inventory.tooltip.ClientTooltipComponent;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public record _ClientTooltipStack(List<ClientTooltipComponent> components) implements ClientTooltipStack {

    public _ClientTooltipStack(TooltipStack tooltip) {
         this(tooltip.components().stream().map(ClientTooltipComponent::create).toList());
    }

    @SuppressWarnings("unchecked")
    public static <T extends ClientTooltipComponent> @Nullable T findFirst(List<ClientTooltipComponent> components, Class<T> type) {
        for (var component : components) {
            if (type.isInstance(component)) {
                return (T) component;
            }
            if (component instanceof _ClientTooltipStack(List<ClientTooltipComponent> groupComponents)) {
                final @Nullable var result = findFirst(groupComponents, type);
                if (result != null) return result;
            }
        }
        return null;
    }

    @Override
    public int getHeight(Font font) {
        var result = 0;
        for (var component : components) {
            result += component.getHeight(font);
        }
        return result;
    }

    @Override
    public int getWidth(Font font) {
        var result = 0;
        for (var component : components) {
            result += component.getWidth(font);
        }
        return result;
    }

    @Override
    public void extractImage(Font font, int x, int y, int width, int height, GuiGraphicsExtractor extractor) {
        var offset = 0;
        for (var component : components) {
            component.extractImage(font, x, y + offset, width, height, extractor);
            offset += component.getHeight(font);
        }
    }

    @Override
    public void extractText(GuiGraphicsExtractor extractor, Font font, int x, int y) {
        var offset = 0;
        for (var component : components) {
            component.extractText(extractor, font, x, y + offset);
            offset += component.getHeight(font);
        }
    }
}