package hydrocraft.bridge.buildcraft;

import hydrocraft.packets.PacketBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.common.ForgeDirection;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteArrayDataOutput;

import cpw.mods.fml.relauncher.Side;

public class PacketEngine extends PacketBase{

	private TileHydrocraftEngine tile;
	
	private int x, y, z;
	private int orientation;
	
	public PacketEngine(TileHydrocraftEngine par1){
		this.tile = par1;
	}
	
	public PacketEngine(){}
	
	@Override
	public void write(ByteArrayDataOutput out) {
		out.writeInt(tile.xCoord);
		out.writeInt(tile.yCoord);
		out.writeInt(tile.zCoord);
		out.writeByte(tile.orientation);
	}

	@Override
	public void read(ByteArrayDataInput in) throws ProtocolException {
		x = in.readInt();
		y = in.readInt();
		z = in.readInt();
		this.orientation = in.readByte();
	}

	@Override
	public void execute(EntityPlayer player, Side side) throws ProtocolException {
		if(side == Side.CLIENT){
			TileHydrocraftEngine engine = (TileHydrocraftEngine) player.worldObj.getBlockTileEntity(x, y, z);
			engine.orientation = this.orientation;
		}else{
			throw new ProtocolException("Cannot send this packet to the server!");
		}
	}

}
