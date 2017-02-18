package org.usfirst.frc.team2815.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;


/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	Joystick xbox = new Joystick(0);
	Joystick flight = new Joystick(1);

	public double getLeftYValue() {
		if (Math.abs(xbox.getRawAxis(1)) >= .05) {
			return (xbox.getRawAxis(1));
		} else
			return 0;
	}
	
	public double getLeftXValue() {
		if (Math.abs(xbox.getRawAxis(0)) >= .05) {
			return (-xbox.getRawAxis(0));
		} else
			return 0;
	}

	public double getRightYValue() {
		if (Math.abs(xbox.getRawAxis(5)) >= .05) {
			return (xbox.getRawAxis(5));
		} else
			return 0;
	}

	public double getRightXValue() {
		if (Math.abs(xbox.getRawAxis(4)) >= .1) {
			return (xbox.getRawAxis(4));
		} else
			return 0;
	}

	public boolean getAButton() {
		return xbox.getRawButton(1);
	}

	public boolean getXButton() {
		return xbox.getRawButton(3);
	}

	public double getRightTrigger() {
		return xbox.getRawAxis(3);
	}

	public double getLeftTrigger() {
		return xbox.getRawAxis(2);
	}

	public boolean getFlight1() {
		return flight.getRawButton(1);
	}

	public boolean getFlight2() {
		return flight.getRawButton(2);
	}

	public boolean getFlight3() {
		return flight.getRawButton(3);
	}

	public boolean getFlight4() {
		return flight.getRawButton(4);
	}

	public double getFlightY() {
		if (Math.abs(flight.getRawAxis(1)) >= .1) {
			return (flight.getRawAxis(1));
		} else
			return 0;
	}
}
