package hydrocraft.packets;

import hydrocraft.IOrientation;
import hydrocraft.api.utils.Utils;
import hydrocraft.api.utils.Vector3;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteArrayDataOutput;

import cpw.mods.fml.common.network.PacketDispatcher;
import cpw.mods.fml.common.network.Player;
import cpw.mods.fml.relauncher.Side;

public class PacketRequestRotation extends PacketBase{

	private Vector3 vec;
	
	public PacketRequestRotation(){}
	
	public PacketRequestRotation(Vector3 par1){
		this.vec = par1;
	}
	
	@Override
	public void write(ByteArrayDataOutput out) {
		out.writeInt((int)vec.getX());
		out.writeInt((int)vec.getY());
		out.writeInt((int)vec.getZ());
	}

	@Override
	public void read(ByteArrayDataInput in) throws ProtocolException {
		this.vec = new Vector3(in.readInt(), in.readInt(), in.readInt());
	}

	@Override
	public void execute(EntityPlayer player, Side side) throws ProtocolException {
		TileEntity tile = Utils.getTileEntity(player.worldObj, vec);
		if(side == Side.SERVER && tile != null){
			PacketDispatcher.sendPacketToPlayer(new PacketRotation((IOrientation) tile, tile).makePacket(), (Player)player);
		}else{
			throw new ProtocolException("Error when receiving packet with id 2");
		}
	}

}
