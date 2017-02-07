package org.usfirst.frc.team2815.robot.autocommands;

import org.usfirst.frc.team2815.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class TurnRightAuto extends Command {

    public TurnRightAuto() {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.driveTrain);
    }
    boolean allowOff = false;

    // Called just before this Command runs the first time
    protected void initialize() {
    	
    	
    	
    	
    	Robot.driveTrain.prepareForDistanceControl();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.driveTrain.driveDistance(-1176.7, 1176.7, true);
    	
    	allowOff = true;
    	SmartDashboard.putNumber("Turn error for 0", Robot.driveTrain.SRXMotors[0].getClosedLoopError());
        SmartDashboard.putNumber("Turn error for 1", Robot.driveTrain.SRXMotors[1].getClosedLoopError());
        SmartDashboard.putNumber("Turn error for 2", Robot.driveTrain.SRXMotors[2].getClosedLoopError());
        SmartDashboard.putNumber("Turn error for 3", Robot.driveTrain.SRXMotors[3].getClosedLoopError());
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return Math.abs(Robot.driveTrain.SRXMotors[0].getClosedLoopError()) < 155 &&
        		Math.abs(Robot.driveTrain.SRXMotors[1].getClosedLoopError()) < 300 &&
        		Math.abs(Robot.driveTrain.SRXMotors[2].getClosedLoopError()) < 135 &&
        		Math.abs(Robot.driveTrain.SRXMotors[3].getClosedLoopError()) < 295;
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
