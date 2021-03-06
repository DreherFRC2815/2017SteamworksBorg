package org.usfirst.frc.team2815.robot.autocommands;

import org.usfirst.frc.team2815.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ControlledPositionControl extends Command {

    public ControlledPositionControl() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.driveTrain);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.driveTrain.prepareForDistanceControl();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.driveTrain.driveDistance(1, 1);
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
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
