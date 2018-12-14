import lejos.robotics.subsumption.Behavior;

public class Fold implements Behavior {

	@Override
	public boolean takeControl() {
		return (ApplesAndPairs.stage == 3 && !ApplesAndPairs.extender.isMoving());
	}

	@Override
	public void action() {
		ApplesAndPairs.extender.setSpeed(ApplesAndPairs.BASE_SPEED);
		ApplesAndPairs.extender.backward();

	}

	@Override
	public void suppress() {
		// TODO Auto-generated method stub

	}

}
