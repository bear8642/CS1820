import lejos.hardware.Button;
import lejos.hardware.lcd.LCD;
import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.port.MotorPort;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.robotics.RegulatedMotor;
import lejos.robotics.SampleProvider;
import lejos.utility.Delay;

public class RedLightTest {

	public static void main(String[] args) {
		float[] level = new float[1];
		float intial = 0.0f;
		final int defaultSpeed = 400;
		
		RegulatedMotor mLeft = new EV3LargeRegulatedMotor ( MotorPort . B );
		RegulatedMotor mRight = new EV3LargeRegulatedMotor ( MotorPort . A );
		mLeft.setSpeed(defaultSpeed);
		mRight.setSpeed(defaultSpeed);
		mLeft . synchronizeWith ( new RegulatedMotor [] { mRight });
		
		EV3ColorSensor ss = new EV3ColorSensor ( SensorPort . S4 );
		ss.setFloodlight(true);
		SampleProvider redSampler = ss.getRedMode ();
		
		LCD.drawString("Place on line and press go!", 0, 0);
		Button.ENTER.waitForPressAndRelease();
		redSampler.fetchSample(level, 0);
		intial = level[0];
		LCD.clear();
		
		mLeft.startSynchronization();
		mLeft.forward();
		mRight.forward();
		mLeft.endSynchronization();		
		
		while(true) {
			if (Button.ENTER.isDown())
			{
				ss.close();
				mLeft.close();
				mRight.close();
				System.exit(0);
			}
			
			redSampler.fetchSample(level, 0);
			
			if(level[0] > intial) {
				LCD.drawString("Grey", 0, 0);
				//Delay.msDelay(1000);
				//LCD.clear();
				mLeft.setSpeed(defaultSpeed + defaultSpeed/10);
				mRight.setSpeed(defaultSpeed);
			}
			
			if(level[0] < intial) {
				LCD.drawString("Black", 0, 0);
				//Delay.msDelay(1000);
				//LCD.clear();
				mRight.setSpeed(defaultSpeed + defaultSpeed/10);
				mLeft.setSpeed(defaultSpeed);
			
			//Delay.msDelay(100);
				
			}			
			
		}

	}

}
