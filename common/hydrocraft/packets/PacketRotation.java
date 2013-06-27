package hydrocraft.packets;

import hydrocraft.IOrientation;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.ForgeDirection;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteArrayDataOutput;

import cpw.mods.fml.relauncher.Side;

public class PacketRotation extends PacketBase{

	private int x,y,z;
	private int orientation;
	
	public PacketRotation(IOrientation par1, TileEntity par2){
		this.x = par2.xCoord;
		this.y = par2.yCoord;
		this.z = par2.zCoord;
		this.orientation = par1.getOrientation().ordinal();
	}
	
	public PacketRotation(){}
	
	@Override
	public void write(ByteArrayDataOutput out) {
		out.writeInt(x);
		out.writeInt(y);
		out.writeInt(z);
		out.writeByte(orientation);
	}

	@Override
	public void read(ByteArrayDataInput in) throws ProtocolException {
		this.x = in.readInt();
		this.y = in.readInt();
		this.z = in.readInt();
		this.orientation = in.readByte();
	}

	@Override
	public void execute(EntityPlayer player, Side side) throws ProtocolException {
		System.out.println("packetReceived");
		IOrientation var1 = (IOrientation) player.worldObj.getBlockTileEntity(x, y, z);
		if(var1 != null){
			var1.setOrientation(ForgeDirection.getOrientation(orientation));
		}else{
			throw new ProtocolException("No instance of IOrientation at "+x+", "+y+", "+z);
		}
		
	}

}
