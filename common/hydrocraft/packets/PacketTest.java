package hydrocraft.packets;

import net.minecraft.entity.player.EntityPlayer;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteArrayDataOutput;

import cpw.mods.fml.relauncher.Side;

public class PacketTest extends PacketBase{

	private String text;

	public PacketTest(String text) {
		this.text = text;
	}
	
	public PacketTest(){}

	@Override
	public void write(ByteArrayDataOutput out) {
		out.writeUTF(text);
	}

	@Override
	public void read(ByteArrayDataInput in) throws ProtocolException{
		text = in.readUTF();
	}

	@Override
	public void execute(EntityPlayer player, Side side) throws ProtocolException {
		if (side.isClient()) {
			player.addChatMessage(text);
		} else {
			throw new ProtocolException("Cannot send this packet to the server!");
		}
	}

}
