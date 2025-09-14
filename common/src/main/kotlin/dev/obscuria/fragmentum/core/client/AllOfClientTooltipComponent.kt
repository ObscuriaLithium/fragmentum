package dev.obscuria.fragmentum.core.client

import dev.obscuria.fragmentum.core.world.AllOfTooltipComponent
import net.minecraft.client.gui.Font
import net.minecraft.client.gui.GuiGraphics
import net.minecraft.client.gui.screens.inventory.tooltip.ClientTooltipComponent
import net.minecraft.client.renderer.MultiBufferSource
import org.joml.Matrix4f

class AllOfClientTooltipComponent(source: AllOfTooltipComponent) : ClientTooltipComponent
{
    private val components: List<ClientTooltipComponent> =
        source.components.stream().map(ClientTooltipComponent::create).toList()

    override fun getHeight(): Int = components.stream()
        .mapToInt { obj: ClientTooltipComponent -> obj.height }
        .sum()

    override fun getWidth(font: Font): Int = components.stream()
        .mapToInt { component: ClientTooltipComponent -> component.getWidth(font) }
        .max().orElse(0)

    override fun renderText(
        font: Font, mouseX: Int, mouseY: Int, matrix4f: Matrix4f,
        bufferSource: MultiBufferSource.BufferSource
    )
    {
        var offset = 0
        for (component in components)
        {
            component.renderText(font, mouseX, mouseY + offset, matrix4f, bufferSource)
            offset += component.height
        }
    }

    override fun renderImage(font: Font, x: Int, y: Int, graphics: GuiGraphics)
    {
        var offset = 0
        for (component in components)
        {
            component.renderImage(font, x, y + offset, graphics)
            offset += component.height
        }
    }
}