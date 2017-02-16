package org.usfirst.frc.team2815.robot.autocommands;

import org.usfirst.frc.team2815.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class ForwardAuto extends Command {
	private int error0 = 0;
	private int error1 = 0;
	private int error2 = 0;
	private int error3 = 0;
	
	public boolean finnishLock;
	
	
    public ForwardAuto() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.driveTrain);
    	
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.driveTrain.prepareForDistanceControl();
    	//Robot.pidGyro.enable();
    	finnishLock = true;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	
    	Robot.driveTrain.driveDistance(2996.75,-2996.75);
    	
    	SmartDashboard.putNumber("forward error for 0", Robot.driveTrain.SRXMotors[0].getClosedLoopError());
        SmartDashboard.putNumber("forward error for 1", Robot.driveTrain.SRXMotors[1].getClosedLoopError());
        SmartDashboard.putNumber("forward error for 2", Robot.driveTrain.SRXMotors[2].getClosedLoopError());
        SmartDashboard.putNumber("forward error for 3", Robot.driveTrain.SRXMotors[3].getClosedLoopError());
        
        SmartDashboard.putNumber("int error for 0", error0);
        SmartDashboard.putNumber("int error for 1", error1);
        SmartDashboard.putNumber("int error for 2", error2);
        SmartDashboard.putNumber("int error for 3", error3);
        
        error0 = Robot.driveTrain.SRXMotors[0].getClosedLoopError();
        error1 = Robot.driveTrain.SRXMotors[1].getClosedLoopError();
        error2 = Robot.driveTrain.SRXMotors[2].getClosedLoopError();
        error3 = Robot.driveTrain.SRXMotors[3].getClosedLoopError();
        
        if(Math.abs(error1) > 20 &&
        		Math.abs(error1) > 20 &&
        		Math.abs(error1) > 20 &&
        		Math.abs(error1) > 20
        	){
        	finnishLock = false;
        }
        SmartDashboard.putBoolean("finnishLock", finnishLock);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return error0 < Math.abs(200) &&
        		error1 < Math.abs(200) &&
        		error2< Math.abs(200) &&
        		error3 < Math.abs(200) && finnishLock == false;
        
        
        
    }

    // Called once after isFinished returns true
    protected void end() {
    	
    	
    	
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
