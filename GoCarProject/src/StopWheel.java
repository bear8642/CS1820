import lejos.hardware.Button;
import lejos.hardware.Sound;
import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.port.MotorPort;
import lejos.robotics.RegulatedMotor;
import lejos.utility.Delay;

public class StopWheel {

	public static void main(String[] args) {
		RegulatedMotor mLeft = new EV3LargeRegulatedMotor ( MotorPort . A );
		mLeft . setSpeed (100);
		while (true)
		{
			mLeft.forward();
			if (Button.ENTER.isDown())
			{
				mLeft.close();
				System.exit(0);
			}
			if (mLeft.isStalled())
			{
				mLeft.close();
				Sound.beep();
				Delay.msDelay(500);
				System.exit(0);
			}
			
		}
	}

}
