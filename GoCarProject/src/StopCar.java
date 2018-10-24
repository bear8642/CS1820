import lejos.hardware.Button;
import lejos.hardware.lcd.LCD;
import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.port.MotorPort;
import lejos.robotics.RegulatedMotor;

public class StopCar {

	public static void main(String[] args) {
		RegulatedMotor mLeft = new EV3LargeRegulatedMotor ( MotorPort . A );
		RegulatedMotor mRight = new EV3LargeRegulatedMotor ( MotorPort . B );
		mLeft . setSpeed (720); // 2 Revolutions Per Second ( RPS )
		mRight . setSpeed (720);
		mLeft . forward ();
		mRight . forward ();
		Button . ENTER . waitForPressAndRelease ();
		mLeft . stop ();
		mRight . stop ();
		LCD . drawInt ( mLeft . getTachoCount () ,0 ,0);
		Button . ENTER . waitForPressAndRelease ();
		mLeft . close ();
		mRight . close ();
		


	}

}
