package org.usfirst.frc.team2815.robot.autocommands;

import org.usfirst.frc.team2815.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class SlightDriveForwardAuto extends Command {
	
	boolean finnishLock;
	
    public SlightDriveForwardAuto() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.driveTrain);
    	finnishLock = true;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.driveTrain.prepareForDistanceControl();
    	
    	finnishLock = true;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.driveTrain.driveDistance(1320, -1320);
    	
    	if(Math.abs(Robot.driveTrain.SRXMotors[0].getClosedLoopError()) > 30 &&
        		Math.abs(Robot.driveTrain.SRXMotors[1].getClosedLoopError()) > 30 &&
        		Math.abs(Robot.driveTrain.SRXMotors[2].getClosedLoopError()) > 30 &&
        		Math.abs(Robot.driveTrain.SRXMotors[3].getClosedLoopError()) > 30
        	){
        	finnishLock = false;
        }
    	
    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	return Math.abs(Robot.driveTrain.SRXMotors[0].getClosedLoopError()) < 100 &&
        		Math.abs(Robot.driveTrain.SRXMotors[1].getClosedLoopError()) < 100 &&
        		Math.abs(Robot.driveTrain.SRXMotors[2].getClosedLoopError()) < 100 &&
        		Math.abs(Robot.driveTrain.SRXMotors[3].getClosedLoopError()) < 100 && finnishLock == false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	//Robot.driveTrain.prepareForDistanceControl();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
