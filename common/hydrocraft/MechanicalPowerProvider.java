package hydrocraft;

import cpw.mods.fml.common.network.PacketDispatcher;
import hydrocraft.api.IMechanicMachine;
import hydrocraft.api.utils.Utils;
import hydrocraft.api.utils.Vector3;
import hydrocraft.packets.PacketRequestRotation;
import hydrocraft.tileentity.base.TileHydrocraft;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;

public abstract class MechanicalPowerProvider extends TileHydrocraft implements IOrientation{

	public ForgeDirection direction = ForgeDirection.UNKNOWN;

	public float rotation;

	public boolean isTracing = false;

	public int power;

	public enum TransferState {
		None, Input, Output
	}

	public void init(){
		if(!this.worldObj.isRemote){

		}else{
			if(this.hasRotation()){
				PacketDispatcher.sendPacketToServer(new PacketRequestRotation(this.getPosition()).makePacket());
			}
		}
	}

	public void update(){
		if(this.canProvidePower()){
			this.traceOtherStocks(this.direction, this.getProducingPower());
		}
	}

	public void traceOtherStocks(ForgeDirection par1, int par2) {

		for(int i = 1; i < 16; i++){
			int x = xCoord+(par1.offsetX*i);
			int y = yCoord+(par1.offsetY*i);
			int z = zCoord+(par1.offsetZ*i);

			int var1 = this.worldObj.getBlockId(x, y, z);
			if(var1 == 0){
				return;
			}
			TileEntity var6 = this.worldObj.getBlockTileEntity(x, y, z);

			if(var6 instanceof MechanicalPowerProvider){
				MechanicalPowerProvider var2 = (MechanicalPowerProvider) var6;
				if(!var2.canIAcceptPower(par1)){
					return;
				}
				var2.rotateBlock(this);
				if(var2.isTransferBlock()){

				}
				else if(var2 instanceof GearBox){
					GearBox var3 = (GearBox) var2;
					var3.setInput(par1);
					var3.redirectPower(par2, par1);
				}
			}

			else if(var6 instanceof IMechanicMachine){
				IMechanicMachine var2 = (IMechanicMachine) var6;
				ForgeDirection[] var4 = var2.mechanicInput();
				if(var4 == null){
					return;
				}
				for(ForgeDirection var3 : var4){
					if(var3.getOpposite() == par1){
						var2.runMachine(par2);
					}
				}
			}
		}

	}

	/*public void traceOtherStocks(ForgeDirection par1, int par2){
		this.isTracing = true;
		float transferLos = 0;

		for(int i = 1; i < 16; i++){
			int x = xCoord+(par1.offsetX*i);
			int y = yCoord+(par1.offsetY*i);
			int z = zCoord+(par1.offsetZ*i);

			int var1 = this.worldObj.getBlockId(x, y, z);
			TileEntity var6 = this.worldObj.getBlockTileEntity(x, y, z);
			if(var1 == 0){
				return;
			}
			Block var2 = Block.blocksList[var1];
			if(var2 instanceof BlockMechanicalPower){
				BlockMechanicalPower var3 = (BlockMechanicalPower)var2;
				BlockType var4 = var3.getBlockType();
				MechanicalPowerProvider var8 = (MechanicalPowerProvider) var6;
				if(var8.isTracing){
					//return;
				}
				if(var8.direction == par1){
					var8.rotateBlock(this);
				}
				if(var4 == BlockType.Provider){
					this.destoryBlock(this.worldObj, xCoord, yCoord, zCoord, var2);
					return;
				}else if(var4 == BlockType.Distributor){
					if(var6 instanceof GearBox){
						GearBox var7 = (GearBox) var6;
						var7.redirectPower(par2, par1);
					}
					return;
				}else if(var4 == BlockType.Transfer){
					int var5 = this.worldObj.getBlockMetadata(x, y, z);
					if(par1 != ForgeDirection.getOrientation(var5)){
						return;
					}
				}
			}
			else if(var2 instanceof BlockContainer){
				if(var6 instanceof IMechanicMachine){
					IMechanicMachine var4 = (IMechanicMachine) var6;
					int var5 = par2 - var4.getRequiredPower();
					if(var5 > 0){
						var4.runMachine(var5);
					}
				}
				return;

			}else{
				return;
			}
		}
		this.isTracing = false;
	}*/

	public boolean canIAcceptPower(ForgeDirection powerFlowDirection){
		if(powerFlowDirection == this.direction){
			return true;
		}
		return false;

	}

	public void destoryBlock(World par1, int par2, int par3, int par4, Block par5){
		par1.newExplosion((Entity)null, par2, par3, par4, 1.5f, false, false);
		par1.destroyBlock(par2, par3, par4, true);
	}

	public boolean doIHaveEnoughEnergyToPowerTheMachine(int par1){
		return this.power > par1;
	}

	public boolean hasRotation(){
		return true;
	}

	public boolean canPowerPassTrough(){
		return false;
	}

	public boolean isTransferBlock(){
		return false;
	}

	public abstract boolean canBlockBeplacedVertical();

	public void rotateBlock(MechanicalPowerProvider par1){
		if(par1.direction == ForgeDirection.WEST || par1.direction == ForgeDirection.SOUTH){
			this.rotation = -par1.rotation;
		}else{
			this.rotation = par1.rotation;
		}
	}
	
	public void writeToNBT(NBTTagCompound compound){
		super.writeToNBT(compound);
		if(this.hasRotation()){
			compound.setInteger("orientation", this.direction.ordinal());
		}
	}

	public void readFromNBT(NBTTagCompound compound){
		super.readFromNBT(compound);
		if(this.hasRotation()){
			this.direction = ForgeDirection.getOrientation(compound.getInteger("orientation"));
		}
	}

	public int getProducingPower(){
		return 0;
	}

	public void setDirection(int par1){
		int var1 = par1;
		if(!this.canBlockBeplacedVertical() && var1 < 2){
			var1 = 2;
		}
		this.direction = ForgeDirection.getOrientation(var1);
	}

	public ForgeDirection getDirection(){
		return this.direction;
	}

	public boolean canProvidePower(){
		return false;
	}
	
	@Override
	public ForgeDirection getOrientation() {
		return this.direction;
	}

	@Override
	public void setOrientation(ForgeDirection par1) {
		this.direction = par1;
	}

	@Override
	public Vector3 getPosition() {
		return new Vector3(this.xCoord, this.yCoord, this.zCoord);
	}
}
