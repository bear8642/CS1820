import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.motor.EV3MediumRegulatedMotor;
import lejos.hardware.port.MotorPort;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3TouchSensor;
import lejos.hardware.sensor.EV3UltrasonicSensor;
import lejos.robotics.RegulatedMotor;
import lejos.robotics.SampleProvider;
import lejos.robotics.subsumption.Arbitrator;
import lejos.robotics.subsumption.Behavior;

public class ApplesAndPairs {
	//define constants
	final static int BASE_SPEED = 100;
	
	
	//create motor objects
	static RegulatedMotor extender = new EV3MediumRegulatedMotor(MotorPort.A);
	static RegulatedMotor driver = new EV3LargeRegulatedMotor(MotorPort.D);
	static RegulatedMotor riser = new EV3LargeRegulatedMotor(MotorPort.C);
	static RegulatedMotor riser2 = new EV3LargeRegulatedMotor(MotorPort.C);
	
	//create sensor objects
	static EV3UltrasonicSensor us = new EV3UltrasonicSensor(SensorPort.S1);
	static SampleProvider distanceSampler = us.getDistanceMode();
	static EV3TouchSensor ts = new EV3TouchSensor(SensorPort.S2);
	static SampleProvider touchSampler = ts.getTouchMode();
	
	//create global variables
	public static int stage;
	public static float distFromWall;
	public static int riseHeight;
	
	public static void main(String[] args) {
		//approach, slow, rise, extend (+stop rise), lift, fold, approach...
		//0,0,0->1,1
		riser.synchronizeWith(new RegulatedMotor[]{riser2});
		Behavior debug = new Debug();
		Behavior b1 = new Approach();
		Behavior b2 = new Slow();
		Behavior b3 = new Rise();
		Behavior b4 = new Extend();
		Behavior b5 = new Lift();
		Behavior b6 = new Fold();	
		Behavior[] behaviors = {debug,b1,b2,b3,b4,b5,b6};
		Arbitrator ab = new Arbitrator(behaviors);
		
		stage = 0;
		ab.go();

	}

}
