import lejos.hardware.Button;
import lejos.hardware.lcd.LCD;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.NXTSoundSensor;
import lejos.robotics.SampleProvider;
import lejos.utility.Delay;

public class ClapClapCar {

	public static void main(String[] args) {
		float[] level = new float[1]; 
		float maxSoundLevel=(float) 0.5, minSoundLevel= (float) 0.5;
		float percentage;
		NXTSoundSensor ss = new NXTSoundSensor ( SensorPort . S3 );
		SampleProvider sound = ss.getDBAMode ();
		
		
		while(true) {
			if (Button.ENTER.isDown())
			{
				System.exit(0);
			}
			sound.fetchSample(level, 0);
			if (level[0] > maxSoundLevel) {
				maxSoundLevel = level[0];
			}
			if (level[0] < minSoundLevel) {
				minSoundLevel = level[0];
			}
			Delay.msDelay(500);
			percentage = ((level[0]-minSoundLevel)/(maxSoundLevel-minSoundLevel))*100;
			
			LCD.drawString("Max: " + Float.toString(maxSoundLevel), 0, 0);
			LCD.drawString("Min: " + Float.toString(minSoundLevel), 0, 1);
			LCD.drawString("Current: " + Float.toString(level[0]), 0, 2);
			LCD.drawString("Percentage: " + Integer.toString((int) percentage), 0, 3);

			
		}

	}

}
