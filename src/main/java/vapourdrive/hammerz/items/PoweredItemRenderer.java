package vapourdrive.hammerz.items;

import java.awt.Color;

import org.lwjgl.opengl.GL11;

import com.enderio.core.client.render.ColorUtil;
import com.enderio.core.client.render.RenderUtil;
import com.enderio.core.common.vecmath.Vector4f;

import cofh.api.energy.IEnergyContainerItem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.IItemRenderer;

public class PoweredItemRenderer implements IItemRenderer
{

	private RenderItem ri = new RenderItem();

	@Override
	public boolean handleRenderType(ItemStack item, ItemRenderType type)
	{
		return type == ItemRenderType.INVENTORY;
	}

	@Override
	public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item, ItemRendererHelper helper)
	{
		return false;
	}

	@Override
	public void renderItem(ItemRenderType type, ItemStack item, Object... data)
	{
		if (data != null && data.length > 0)
		{
			renderToInventory(item, (RenderBlocks) data[0]);
		}
	}

	public void renderToInventory(ItemStack item, RenderBlocks renderBlocks)
	{

		Minecraft mc = Minecraft.getMinecraft();
		ri.renderItemIntoGUI(mc.fontRenderer, mc.getTextureManager(), item, 0, 0, true);
		GL11.glDisable(GL11.GL_LIGHTING);

		boolean hasEnergyUpgrade = DarkHammer.isEmpowered(item);
		int y = hasEnergyUpgrade ? 12 : 13;
		int bgH = hasEnergyUpgrade ? 4 : 2;
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		if (item.getItemDamageForDisplay() != 0)
		{
			RenderUtil.renderQuad2D(2, y, 0, 13, bgH, ColorUtil.getRGB(Color.black));
		}

		double maxDam = item.getMaxDamage();
		double dispDamage = item.getItemDamageForDisplay();
		y = hasEnergyUpgrade ? 14 : 13;
		if(item.getItemDamageForDisplay() != 0)
		{
			renderBar(y, maxDam, dispDamage, Color.green, Color.red);
		}

		if (hasEnergyUpgrade)
		{
			IEnergyContainerItem armor = (IEnergyContainerItem) item.getItem();
			maxDam = armor.getMaxEnergyStored(item);
			dispDamage = armor.getEnergyStored(item);
			y = 12;
			Color color = new Color(0x2D, 0xCE, 0xFA); // electric blue
			renderBar2(y, maxDam, maxDam - dispDamage, color, color);
		}

		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		GL11.glEnable(GL11.GL_LIGHTING);
	}

	private void renderBar2(int y, double maxDam, double dispDamage, Color full, Color empty)
	{
		double ratio = dispDamage / maxDam;
		Vector4f fg = ColorUtil.toFloat(full);
		Vector4f ec = ColorUtil.toFloat(empty);
		fg.interpolate(ec, (float) ratio);
		Vector4f bg = ColorUtil.toFloat(Color.black);
		bg.interpolate(fg, 0.15f);

		int barLength = (int) Math.round(13.0 * (1 - ratio));

		RenderUtil.renderQuad2D(2, y, 0, 13, 1, bg);
		RenderUtil.renderQuad2D(2, y, 0, barLength, 1, fg);
	}

	private void renderBar(int y, double maxDam, double dispDamage, Color full, Color empty)
	{
		double ratio = dispDamage / maxDam;
		Vector4f fg = ColorUtil.toFloat(full);
		Vector4f ec = ColorUtil.toFloat(empty);

		fg.interpolate(ec, (float) ratio);

		Vector4f bg = new Vector4f(0.17, 0.3, 0.1, 0);

		int barLength = (int) Math.round(13.0 * (1 - ratio));
		RenderUtil.renderQuad2D(2, y, 0, 13, 1, bg);
		RenderUtil.renderQuad2D(2, y, 0, barLength, 1, fg);
	}
}
