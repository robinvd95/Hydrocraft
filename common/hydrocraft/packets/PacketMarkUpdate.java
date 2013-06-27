package hydrocraft.packets;

import hydrocraft.IOrientation;
import hydrocraft.packets.PacketBase.ProtocolException;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteArrayDataOutput;

import cpw.mods.fml.relauncher.Side;

public class PacketMarkUpdate extends PacketBase{

	private int x,y,z;
	private int orientation;
	
	public PacketMarkUpdate(int x, int y, int z){
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	public PacketMarkUpdate(){}
	
	@Override
	public void write(ByteArrayDataOutput out) {
		out.writeInt(x);
		out.writeInt(y);
		out.writeInt(z);
	}

	@Override
	public void read(ByteArrayDataInput in) throws ProtocolException {
		x = in.readInt();
		y = in.readInt();
		z = in.readInt();
	}

	@Override
	public void execute(EntityPlayer player, Side side) throws ProtocolException {
		if(side == Side.CLIENT){
			throw new ProtocolException("The packet by the name of "+this.getClass().getName()+ " should not have been received by the server");
		}else{
			player.worldObj.markBlockForUpdate(x, y, z);
		}
	}

}
