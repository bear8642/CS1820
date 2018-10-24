import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.port.MotorPort;
import lejos.robotics.RegulatedMotor;
import lejos.utility.Delay;

public class SquareCar {

	public static void main(String[] args) {
		int rightAngleTurnTime = 456;
		
		RegulatedMotor mLeft = new EV3LargeRegulatedMotor ( MotorPort . A );
		RegulatedMotor mRight = new EV3LargeRegulatedMotor ( MotorPort . B );
		mLeft . setSpeed (360); // 2 Revolutions Per Second ( RPS )
		mRight . setSpeed (360);
		
		for (int i=0; i<4; i++)
		{
			mLeft . synchronizeWith ( new RegulatedMotor [] { mRight });
			mLeft . startSynchronization ();
			mLeft . forward ();
			mRight . forward ();
			mLeft . endSynchronization ();
			Delay.msDelay(1000);
			mLeft . startSynchronization ();
			mLeft . stop ();
			mRight . stop ();
			mLeft . endSynchronization ();
			Delay.msDelay(500);
			mLeft . startSynchronization ();
			mLeft . forward ();
			mRight . backward ();
			mLeft . endSynchronization ();
			Delay.msDelay(rightAngleTurnTime);
			mLeft . startSynchronization ();
			mLeft . stop ();
			mRight . stop ();
			mLeft . endSynchronization ();
		}
		mLeft . close ();
		mRight . close ();

	}

}
