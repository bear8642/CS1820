import lejos.robotics.subsumption.Behavior;

public class Rise implements Behavior{
	float[] samples = new float[1];
	@Override
	public boolean takeControl() {
		// TODO Auto-generated method stub
		ApplesAndPairs.touchSampler.fetchSample(samples,0);
		return (ApplesAndPairs.stage == 0 && samples[0] == 1.0f);
	}

	@Override
	public void action() {
		ApplesAndPairs.stage = 1;
		ApplesAndPairs.driver.stop();
		//ApplesAndPairs.driver.rotate(-180);
		ApplesAndPairs.distFromWall = samples[0];
		ApplesAndPairs.riser.startSynchronization();
		ApplesAndPairs.riser.setSpeed(300);
		ApplesAndPairs.riser2.setSpeed(300);
		ApplesAndPairs.riser.backward();
		ApplesAndPairs.riser2.backward();
		
		ApplesAndPairs.riser.endSynchronization();
		
	}

	@Override
	public void suppress() {
		// TODO Auto-generated method stub
		
	}

}
