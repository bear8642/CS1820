import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.port.MotorPort;
import lejos.robotics.RegulatedMotor;
import lejos.utility.Delay;

public class GoCar {

	public static void main(String[] args) {
		RegulatedMotor mLeft = new EV3LargeRegulatedMotor ( MotorPort . B );
		RegulatedMotor mRight = new EV3LargeRegulatedMotor ( MotorPort . A );
		mLeft . setSpeed (720); // 2 Revolutions Per Second ( RPS )
		mRight . setSpeed (720);
		mLeft . synchronizeWith ( new RegulatedMotor [] { mRight });
		mLeft . startSynchronization ();
		mLeft . forward ();
		mRight . forward ();
		mLeft . endSynchronization ();
		Delay . msDelay (1000);
		mLeft . startSynchronization ();
		mLeft . stop ();
		Delay . msDelay (100);
		mRight . stop ();
		mLeft . endSynchronization ();
		mLeft . close ();
		mRight . close ();

	}

}
