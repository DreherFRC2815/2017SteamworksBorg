package org.usfirst.frc.team2815.robot.autocommands;

import org.usfirst.frc.team2815.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class TurnLeftAuto extends Command {
	boolean finnishLock;
	public TurnLeftAuto() {
        // Use requires() here to declare subsystem dependencies
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
    	Robot.driveTrain.driveDistance(-1176.7, -1176.7);
    	
    	if(Math.abs(Robot.driveTrain.SRXMotors[0].getClosedLoopError()) > 20 &&
        		Math.abs(Robot.driveTrain.SRXMotors[1].getClosedLoopError()) > 20 &&
        		Math.abs(Robot.driveTrain.SRXMotors[2].getClosedLoopError()) > 20 &&
        		Math.abs(Robot.driveTrain.SRXMotors[3].getClosedLoopError()) > 20
        	){
        	finnishLock = false;
        }
    	
    	SmartDashboard.putNumber("Turn left error for 0", Robot.driveTrain.SRXMotors[0].getClosedLoopError());
        SmartDashboard.putNumber("Turn left error for 1", Robot.driveTrain.SRXMotors[1].getClosedLoopError());
        SmartDashboard.putNumber("Turn left error for 2", Robot.driveTrain.SRXMotors[2].getClosedLoopError());
        SmartDashboard.putNumber("Turn left error for 3", Robot.driveTrain.SRXMotors[3].getClosedLoopError());
        
        SmartDashboard.putBoolean("leftTurnLock", finnishLock);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return Math.abs(Robot.driveTrain.SRXMotors[0].getClosedLoopError()) < 450 &&
        		Math.abs(Robot.driveTrain.SRXMotors[1].getClosedLoopError()) < 450 &&
        		Math.abs(Robot.driveTrain.SRXMotors[2].getClosedLoopError()) < 450 &&
        		Math.abs(Robot.driveTrain.SRXMotors[3].getClosedLoopError()) < 450 && finnishLock == false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	SmartDashboard.putBoolean("isTurnLeftEnd", this.isFinished());
    	
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
