package dev.obscuria.fragmentum.client;

import dev.obscuria.fragmentum.content.world.tooltip.GroupTooltip;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.tooltip.ClientTooltipComponent;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public record ClientGroupTooltip(List<ClientTooltipComponent> components) implements ClientTooltipComponent {

    public static ClientGroupTooltip create(GroupTooltip tooltip) {
        return new ClientGroupTooltip(tooltip.components().stream().map(ClientTooltipComponent::create).toList());
    }

    @SuppressWarnings("unchecked")
    public static <T extends ClientTooltipComponent> @Nullable T findFirst(List<ClientTooltipComponent> components, Class<T> type) {
        for (var component : components) {
            if (type.isInstance(component)) {
                return (T) component;
            }
            if (component instanceof ClientGroupTooltip(List<ClientTooltipComponent> groupComponents)) {
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
    public void renderImage(Font font, int x, int y, int width, int height, GuiGraphics graphics) {
        var offset = 0;
        for (var component : components) {
            component.renderImage(font, x, y + offset, width, height, graphics);
            offset += component.getHeight(font);
        }
    }

    @Override
    public void renderText(GuiGraphics graphics, Font font, int x, int y) {
        var offset = 0;
        for (var component : components) {
            component.renderText(graphics, font, x, y + offset);
            offset += component.getHeight(font);
        }
    }
}
