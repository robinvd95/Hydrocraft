package hydrocraft.blocks;

import hydrocraft.BaseHydrocraft;
import hydrocraft.MechanicalPowerProvider;
import hydrocraft.client.ClientProxy;
import hydrocraft.client.models.ModelGearBox;
import hydrocraft.client.models.ModelHydrocraft;
import hydrocraft.client.renderer.RenderInventory;
import hydrocraft.tileentity.TileEntityGearBox;
import hydrocraft.tileentity.TileEntityIronGearBox;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockGearBox extends BlockMechanicalPower{

	public enum GearBoxType{
		WOOD("gearBox.png"), IRON("ironGearBox.png");
		
		public String textureName;
		
		private GearBoxType(String par1){
			this.textureName = par1;
		}
	}
	
	private final GearBoxType gearBox;
	
	public BlockGearBox(int par1, Material par2Material, GearBoxType par3) {
		super(par1, par2Material, BlockType.Distributor);
		this.setCreativeTab(BaseHydrocraft.hydroCraftTab);
		this.setHardness(1.0f);
		this.gearBox = par3;
	}

	public boolean isOpaqueCube(){
		return false;
	}
	
	public boolean renderAsNormalBlock(){
		return false;
	}
	
	@SideOnly(Side.CLIENT)
	public int getRenderType(){
		return ClientProxy.BLOCK_RENDER_ID;
	}
	
	@SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister par1IconRegister){
		this.blockIcon = Block.planks.getBlockTextureFromSide(0);
	}

	@Override
	public MechanicalPowerProvider createNewProvider(World world) {
		if(this.gearBox == GearBoxType.WOOD){
			return new TileEntityGearBox();
		}else{
			return new TileEntityIronGearBox();
		}
	}

	@Override
	public boolean doesBlockHaveRotation() {
		return false;
	}
}