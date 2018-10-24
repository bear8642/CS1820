import lejos.hardware.lcd.LCD;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.robotics.SampleProvider;

public class SimpleClap {

	public static void main(String[] args) {
		float[] level = new float[1];
		EV3ColorSensor ss = new EV3ColorSensor ( SensorPort . S4 );
		SampleProvider redSampler = ss.getRedMode ();
		redSampler.fetchSample(level, 0);
		
		LCD.drawString(Integer.toString((int)level[0]), 0, 0);
		
		ss.close();

	}

}
