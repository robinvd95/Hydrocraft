package hydrocraft.blocks;

import hydrocraft.BaseHydrocraft;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.Icon;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockHydraulicPiston extends Block{

	private Icon pistonSide, pistonBottom, pistonTop;
	
	public BlockHydraulicPiston(int par1, Material par2Material) {
		super(par1, par2Material);
		this.setCreativeTab(BaseHydrocraft.hydroCraftTab);
	}
	
	public Icon getIcon(int par1, int par2){
		if(par1 == 0){
			return this.pistonBottom;
		}else if(par1 == 1){
			return this.pistonTop;
		}
		return pistonSide;
	}
	
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister par1){
		this.pistonSide = par1.registerIcon("hydrocraft:pistonSide");
		this.pistonTop = par1.registerIcon("hydrocraft:pistonTop");
		this.pistonBottom = par1.registerIcon("hydrocraft:pistonBottom");
	}
}