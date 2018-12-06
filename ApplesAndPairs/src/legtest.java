import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.port.MotorPort;
import lejos.robotics.RegulatedMotor;
import lejos.utility.Delay;

public class legtest {

	public static void main(String[] args) {
		EV3LargeRegulatedMotor motor1 = new EV3LargeRegulatedMotor(MotorPort.C);
		EV3LargeRegulatedMotor motor2 = new EV3LargeRegulatedMotor(MotorPort.B);
		motor1.synchronizeWith(new RegulatedMotor[]{motor2});
		motor1.setSpeed(300);
		motor2.setSpeed(300);
		motor1.startSynchronization();
		motor1.backward();
		motor2.backward();
		motor1.endSynchronization();
		
		
		Delay.msDelay(5000);
		motor1.startSynchronization();
		motor1.stop();
		motor2.stop();
		motor1.endSynchronization();

		motor1.close();
	}

}
