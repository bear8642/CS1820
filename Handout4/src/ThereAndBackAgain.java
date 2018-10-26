import lejos.hardware.Button;
import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.port.MotorPort;
import lejos.robotics.RegulatedMotor;
import lejos.robotics.chassis.Chassis;
import lejos.robotics.chassis.Wheel;
import lejos.robotics.chassis.WheeledChassis;
import lejos.robotics.localization.OdometryPoseProvider;
import lejos.robotics.localization.PoseProvider;
import lejos.robotics.navigation.MovePilot;
import lejos.robotics.navigation.Navigator;
import lejos.robotics.navigation.Waypoint;
import lejos.robotics.pathfinding.Path;

public class ThereAndBackAgain {
	final static float WHEEL_DIAMETER = 56;
	final static float AXLE_LENGTH = 120;
	final static float ANGULAR_SPEED = 300;
	final static float LINEAR_SPEED = 300;
	static RegulatedMotor mL = new EV3LargeRegulatedMotor(MotorPort.B);
	static RegulatedMotor mR = new EV3LargeRegulatedMotor(MotorPort.A);
	static Wheel wL = WheeledChassis.modelWheel(mL, WHEEL_DIAMETER).offset(-AXLE_LENGTH / 2);
	static Wheel wR = WheeledChassis.modelWheel(mR, WHEEL_DIAMETER).offset(AXLE_LENGTH / 2);
	
	static Chassis chassis = new WheeledChassis(new Wheel[] {wR,wL}, WheeledChassis.TYPE_DIFFERENTIAL);
	static MovePilot plt = new MovePilot(chassis);
	static PoseProvider poseProvider = new OdometryPoseProvider(plt);
	static Navigator nav = new Navigator(plt,poseProvider);

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Path path = new Path();
		path.add(new Waypoint(1000,0));
		path.add(new Waypoint(0,0));
		Button.ENTER.waitForPressAndRelease();
		nav.followPath(path);
		nav.waitForStop();

	}

}
