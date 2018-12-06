import lejos.robotics.subsumption.Behavior;

public class Approach implements Behavior{

	@Override
	public boolean takeControl() {
		// TODO Auto-generated method stub
		return (ApplesAndPairs.stage == 0 && !ApplesAndPairs.driver.isMoving());
	}

	@Override
	public void action() {
		ApplesAndPairs.driver.setSpeed(ApplesAndPairs.BASE_SPEED);
		ApplesAndPairs.driver.forward();
		
	}

	@Override
	public void suppress() {
		// TODO Auto-generated method stub
		
	}

}
