import lejos.robotics.subsumption.Behavior;
import lejos.utility.Delay;

public class Extend implements Behavior{
	float[] samples = new float[1];
	@Override
	public boolean takeControl() {
		// TODO Auto-generated method stub
		ApplesAndPairs.distanceSampler.fetchSample(samples,0);
		return ApplesAndPairs.stage == 1 && samples[0] > 4*ApplesAndPairs.distFromWall;
	}

	@Override
	public void action() {
		// TODO Auto-generated method stub
		Delay.msDelay(100);
		ApplesAndPairs.riser.stop();
		ApplesAndPairs.extender.setSpeed(ApplesAndPairs.BASE_SPEED);
		ApplesAndPairs.extender.forward();
		while(!ApplesAndPairs.extender.isStalled()) {
			Delay.msDelay(100);
		}
		ApplesAndPairs.extender.stop();
		ApplesAndPairs.stage = 2;
		
	}

	@Override
	public void suppress() {
		// TODO Auto-generated method stub
		
	}

}
