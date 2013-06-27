package hydrocraft.api.utils;

public enum RotateDirection {

	CLOCKWISE(1),
	
	ANTICLOCKWISE(-1),
	
	UNKNOWN(0);
	
	private final int rotationOffset;
	
	private RotateDirection(int offset){
		this.rotationOffset = offset;
	}
	
	public int getRotationOffset(){
		return this.rotationOffset;
	}
	
}
