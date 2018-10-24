import lejos.hardware.Button;
import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.port.MotorPort;
import lejos.robotics.RegulatedMotor;
import lejos.utility.Delay;

public class CarRotate {

	public static void main(String[] args) {
		Button . ENTER . waitForPressAndRelease ();
		int sideAngle = (int) (360*5.5);
		RegulatedMotor mLeft = new EV3LargeRegulatedMotor ( MotorPort . A );
		RegulatedMotor mRight = new EV3LargeRegulatedMotor ( MotorPort . B );
		// Tell JVM what the left motor is synchronized with .
		mLeft . synchronizeWith ( new RegulatedMotor [] { mRight });
		mLeft . setSpeed (720); // 2 Revolutions Per Second ( RPS )
		mRight . setSpeed (720);
		// Do a for loop here to run FOUR times
		for (int i = 0; i < 4; i++) {
			mRight . rotate (390); // one motor turns to go around a corner
			//mRight . waitComplete ();
			//Delay.msDelay(1000);
			
			mLeft . startSynchronization ();
			mLeft . rotate (sideAngle,true);
			mRight . rotate (sideAngle,true);
			mLeft . endSynchronization ();
			
			mLeft . waitComplete ();
			mRight . waitComplete (); // wait for both motors to finish their jobs
			//Delay.msDelay(1000);
		}
		mLeft . close ();
		mRight . close ();

	}

}
