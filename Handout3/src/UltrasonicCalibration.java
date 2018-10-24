import lejos.hardware.Button;
import lejos.hardware.lcd.LCD;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3UltrasonicSensor;
import lejos.robotics.SampleProvider;

public class UltrasonicCalibration {
	public static void main(String[] args) {
		float[] distance = new float[1];
		EV3UltrasonicSensor us = new EV3UltrasonicSensor(SensorPort.S2);
		us.enable();
		SampleProvider ds  = us.getDistanceMode();
		
		do {
			if (Button.ENTER.isDown()) {
				ds.fetchSample(distance, 0);
				LCD.clear();
				LCD.drawString(Float.toString(distance[0]), 0, 0);
			}
		}while (!Button.ESCAPE.isDown());
		us.close();
		
	}
}
