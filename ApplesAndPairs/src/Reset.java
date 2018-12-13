import lejos.robotics.subsumption.Behavior;

public class Reset implements Behavior{
	float[] samples = new float[1];
	@Override
	public boolean takeControl() {
		// TODO Auto-generated method stub
		return (ApplesAndPairs.stage == 3 && ApplesAndPairs.extender.isStalled());
	}

	@Override
	public void action() {
		ApplesAndPairs.extender.stop();
		ApplesAndPairs.stage == 0;
	}

	@Override
	public void suppress() {
		// TODO Auto-generated method stub
		
	}

}
