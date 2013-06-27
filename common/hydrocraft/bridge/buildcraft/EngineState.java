package hydrocraft.bridge.buildcraft;

public enum EngineState {

	BLUE(0.1f, 0.01f), GREEN(0.12f, 0.02f), ORANGE(0.13f, 0.05f), RED(0.15f, 0.1f);
	
	public final float efficiency;
	public final float pistonSpeed;
	
	private EngineState(float par1, float par2){
		this.efficiency = par1;
		this.pistonSpeed = par2;
	}
}
