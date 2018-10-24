import lejos.hardware.Button;
import lejos.hardware.lcd.LCD;
import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.port.MotorPort;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3UltrasonicSensor;
import lejos.hardware.sensor.NXTSoundSensor;
import lejos.robotics.RegulatedMotor;
import lejos.robotics.SampleProvider;
import lejos.utility.Delay;

public class SimpleChap {

	static void waitForClap(double factor){
		float background = 0;
		float[] level = new float[1];
		level[0] = 0;
		LCD.drawString("gathering Background noise", 0, 0);
		NXTSoundSensor ss = new NXTSoundSensor(SensorPort.S3);
		SampleProvider sound = ss.getDBAMode();
		for(int i = 0; i<10; i++) 
		{
			LCD.drawInt(i,i,1);
			sound.fetchSample(level, 0);
			background += level[0];
			Delay.msDelay(100);
		}
		background /= 10;
		
		LCD.drawString("BG noise gathered", 0, 0);
		do
		{
			sound.fetchSample(level, 0);
			LCD.drawString(Float.toString(level[0]),0,3);
			LCD.drawString(Double.toString(factor*background + background),0,2);
		}while(level[0] <= factor*background + background && !Button.ENTER.isDown());
		//LCD.clear();
		ss.close();
		
	}

	public static void main(String[] args) {
		
		//init section
		RegulatedMotor mLeft = new EV3LargeRegulatedMotor ( MotorPort . B );
		RegulatedMotor mRight = new EV3LargeRegulatedMotor ( MotorPort . A );
		mLeft . setSpeed (720); // 2 Revolutions Per Second ( RPS )
		mRight . setSpeed (720);
		mLeft . synchronizeWith ( new RegulatedMotor [] { mRight });
		
		float[] distance = new float[1];
		EV3UltrasonicSensor us = new EV3UltrasonicSensor(SensorPort.S2);
		us.enable();
		SampleProvider ds  = us.getDistanceMode();
		
		//start section
		waitForClap(2);
		LCD.clear();
		mLeft.startSynchronization();
		mLeft.forward();
		mRight.forward();
		mLeft.endSynchronization();
		Delay.msDelay(500);
		
		//turn section
		waitForClap(0.1);
		mLeft.startSynchronization();
		mLeft.stop();
		mRight.stop();
		mLeft.endSynchronization();
		Delay.msDelay(100);
		mLeft.rotate(390);
		mLeft.startSynchronization();
		mLeft.forward();
		mRight.forward();
		mLeft.endSynchronization();		
		
		//stopping section
		
		do{
			ds.fetchSample(distance, 0);
			//LCD.drawString(Float.toString(distance[0]), 0, 0);
		}while(distance[0] > 0.5 && !Button.ENTER.isDown());
		//end stopping section
		mLeft.startSynchronization();
		mLeft.stop();
		mRight.stop();
		mLeft.endSynchronization();
		
		mLeft.close();
		mRight.close();
		us.disable();
		us.close();
		

	}

}
