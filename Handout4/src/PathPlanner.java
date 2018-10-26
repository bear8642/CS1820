import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.port.MotorPort;
import lejos.robotics.RegulatedMotor;
import lejos.robotics.chassis.Chassis;
import lejos.robotics.chassis.Wheel;
import lejos.robotics.chassis.WheeledChassis;
import lejos.robotics.geometry.Line;
import lejos.robotics.geometry.Rectangle;
import lejos.robotics.localization.OdometryPoseProvider;
import lejos.robotics.localization.PoseProvider;
import lejos.robotics.mapping.LineMap;
import lejos.robotics.navigation.DestinationUnreachableException;
import lejos.robotics.navigation.MovePilot;
import lejos.robotics.navigation.Navigator;
import lejos.robotics.navigation.Pose;
import lejos.robotics.navigation.Waypoint;
import lejos.robotics.pathfinding.Path;
import lejos.robotics.pathfinding.PathFinder;
import lejos.robotics.pathfinding.ShortestPathFinder;

public class PathPlanner {
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
	
	public static void main(String[] args) throws DestinationUnreachableException {

		Line[] lines = new Line[4];
		lines [0] = new Line(190, 200, 480, 200);
		lines [1] = new Line(200,190, 200, 400);
		lines [2] = new Line(190, 390, 480, 390);
		lines [3] = new Line(470, 190, 470, 400);
		
		Rectangle bounds = new Rectangle(-50,-50,700,700);
		LineMap map = new LineMap(lines,bounds);
		
		PathFinder planner = new ShortestPathFinder(map);
		Path route = planner.findRoute(new Pose(0,0, 90), new Waypoint(600,450));
		nav.followPath(route);
		nav.waitForStop();
	}

}
