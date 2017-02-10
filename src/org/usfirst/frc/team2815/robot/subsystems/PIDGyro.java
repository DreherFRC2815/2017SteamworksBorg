package org.usfirst.frc.team2815.robot.subsystems;

import org.usfirst.frc.team2815.robot.Robot;

import edu.wpi.first.wpilibj.command.PIDSubsystem;

/**
 *
 */
public class PIDGyro extends PIDSubsystem {

    // Initialize your subsystem here
    public PIDGyro() {
    	super("gyro", .2, 0, 2);
        // Use these to get going:
        // setSetpoint() -  Sets where the PID controller should move the system
        //                  to
        // enable() - Enables the PID controller.
    }

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public void setPointZero(){
    	setSetpoint(0);
    }
    
    public void setPointRight60(){
    	setSetpoint(60);
    }
    
    public void setPointleft60(){
    	setSetpoint(-60);
    }
    
    protected double returnPIDInput() {
        // Return your input value for the PID loop
        // e.g. a sensor, like a potentiometer:
        // yourPot.getAverageVoltage() / kYourMaxVoltage;
        return Robot.driveTrain.getGyroAngle();
    }

    protected void usePIDOutput(double output) {
        // Use output to drive your system, like a motor
        // e.g. yourMotor.set(output);
    	Robot.driveTrain.setAnglePIDOut(output);
    }
}
