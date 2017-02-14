package org.usfirst.frc.team2815.robot.autocommands;

import org.usfirst.frc.team2815.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class ForwardAuto extends Command {

    public ForwardAuto() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.driveTrain);
    	requires(Robot.pidGyro);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.driveTrain.prepareForDistanceControl();
    	Robot.pidGyro.enable();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	
    	Robot.driveTrain.driveDistance(2996.75,-2996.75);
    	
    	SmartDashboard.putNumber("forward error for 0", Robot.driveTrain.SRXMotors[0].getClosedLoopError());
        SmartDashboard.putNumber("forward error for 1", Robot.driveTrain.SRXMotors[1].getClosedLoopError());
        SmartDashboard.putNumber("forward error for 2", Robot.driveTrain.SRXMotors[2].getClosedLoopError());
        SmartDashboard.putNumber("forward error for 3", Robot.driveTrain.SRXMotors[3].getClosedLoopError());
        
        
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return Robot.driveTrain.SRXMotors[0].getClosedLoopError() < 140 &&
        		Robot.driveTrain.SRXMotors[1].getClosedLoopError() < 195 &&
        		Robot.driveTrain.SRXMotors[2].getClosedLoopError() < 90 &&
        		Robot.driveTrain.SRXMotors[3].getClosedLoopError() < 180;
        
        
        
    }

    // Called once after isFinished returns true
    protected void end() {
    	SmartDashboard.putBoolean("isForwardEnd", this.isFinished());
    	
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
