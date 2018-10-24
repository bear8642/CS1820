import lejos.hardware.Button;
import lejos.hardware.lcd.LCD;
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

public class PilotTest {
	final static float WHEEL_DIAMETER = 56;
	final static float AXLE_LENGTH = 120;
	final static float ANGULAR_SPEED = 300;
	final static float LINEAR_SPEED = 300;
	
	public static void main(String[] args) {
		RegulatedMotor mL = new EV3LargeRegulatedMotor(MotorPort.B);
		Wheel wheelLeft = WheeledChassis.modelWheel(mL, WHEEL_DIAMETER).offset(-AXLE_LENGTH / 2);
		RegulatedMotor mR = new EV3LargeRegulatedMotor(MotorPort.A);
		Wheel wheelRight = WheeledChassis.modelWheel(mR, WHEEL_DIAMETER).offset(AXLE_LENGTH / 2);
		Chassis chassis = new WheeledChassis(new Wheel[] {wheelRight,wheelLeft},
				WheeledChassis.TYPE_DIFFERENTIAL);
		MovePilot plt = new MovePilot(chassis);
		PoseProvider poseProvider = new OdometryPoseProvider(plt);
		plt.setLinearSpeed(LINEAR_SPEED);
		plt.setAngularSpeed(ANGULAR_SPEED);
		
		Navigator navigator = new Navigator(plt, poseProvider);
		Path path = new Path();
		path.add(new Waypoint(100,0));
		path.add(new Waypoint(100,100));
		path.add(new Waypoint(0,100));
		path.add(new Waypoint(0,0));
		
		Button.ENTER.waitForPressAndRelease();
		
		navigator.followPath(path);
		navigator.waitForStop();
		LCD.drawString(poseProvider.getPose().toString(),0,0);
		Button.ENTER.waitForPressAndRelease();
        
	}

}
