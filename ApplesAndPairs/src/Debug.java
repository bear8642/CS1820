import lejos.hardware.lcd.LCD;
import lejos.robotics.subsumption.Behavior;

public class Debug implements Behavior {
	float[] samples = new float[1];
	@Override
	public boolean takeControl() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public void action() {
		LCD.drawInt(ApplesAndPairs.stage,0,2);
		ApplesAndPairs.touchSampler.fetchSample(samples,0);
		LCD.drawString(Float.toString(samples[0]), 0, 3);
		ApplesAndPairs.distanceSampler.fetchSample(samples,0);
		LCD.drawString(Float.toString(samples[0]), 0, 4);

	}

	@Override
	public void suppress() {
		// TODO Auto-generated method stub

	}

}
