package dev.obscuria.fragmentum.core.v1.client;

import dev.obscuria.fragmentum.core.v1.common.AllOfTooltipComponent;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.tooltip.ClientTooltipComponent;
import net.minecraft.client.renderer.MultiBufferSource;
import org.jetbrains.annotations.ApiStatus;
import org.joml.Matrix4f;

import java.util.List;

@ApiStatus.Internal
public record AllOfClientTooltipComponent(List<ClientTooltipComponent> components) implements ClientTooltipComponent
{
    public AllOfClientTooltipComponent(AllOfTooltipComponent source)
    {
        this(source.components().stream().map(ClientTooltipComponent::create).toList());
    }

    @Override
    public int getHeight()
    {
        return components().stream()
                .mapToInt(ClientTooltipComponent::getHeight)
                .sum();
    }

    @Override
    public int getWidth(Font font)
    {
        return components().stream()
                .mapToInt(component -> component.getWidth(font))
                .max().orElse(0);
    }

    @Override
    public void renderText(Font font, int mouseX, int mouseY, Matrix4f matrix4f,
                           MultiBufferSource.BufferSource bufferSource)
    {
        var offset = 0;
        for (final var component : components())
        {
            component.renderText(font, mouseX, mouseY + offset, matrix4f, bufferSource);
            offset += component.getHeight();
        }
    }

    @Override
    public void renderImage(Font font, int x, int y, GuiGraphics guiGraphics)
    {
        var offset = 0;
        for (final var component : components())
        {
            component.renderImage(font, x, y + offset, guiGraphics);
            offset += component.getHeight();
        }
    }
}
