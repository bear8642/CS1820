import lejos.robotics.subsumption.Behavior;

public class Slow implements Behavior{
	float[] samples = new float[1];
	@Override
	public boolean takeControl() {
		// TODO Auto-generated method stub
		ApplesAndPairs.distanceSampler.fetchSample(samples,0);
		return (ApplesAndPairs.stage == 0 && samples[0] < 0.2f && ApplesAndPairs.driver.getSpeed() != ApplesAndPairs.BASE_SPEED/2);
	}

	@Override
	public void action() {
		// TODO Auto-generated method stub
		ApplesAndPairs.driver.setSpeed(ApplesAndPairs.BASE_SPEED/2);
	}

	@Override
	public void suppress() {
		// TODO Auto-generated method stub
		
	}

}
