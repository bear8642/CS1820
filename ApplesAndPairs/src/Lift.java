import lejos.robotics.subsumption.Behavior;

public class Lift implements Behavior {

	@Override
	public boolean takeControl() {
		return(ApplesAndPairs.stage==2 && !ApplesAndPairs.riser.isMoving());
	}

	@Override
	public void action() {
		ApplesAndPairs.riser.startSynchronization();
		ApplesAndPairs.riser.setSpeed(ApplesAndPairs.BASE_SPEED);
		ApplesAndPairs.riser2.setSpeed(ApplesAndPairs.BASE_SPEED);
		ApplesAndPairs.riser.rotate(ApplesAndPairs.riseHeight);
		ApplesAndPairs.riser2.rotate(ApplesAndPairs.riseHeight);
		
		ApplesAndPairs.riser.endSynchronization();
		ApplesAndPairs.stage = 3;

	}

	@Override
	public void suppress() {
		// TODO Auto-generated method stub

	}

}
