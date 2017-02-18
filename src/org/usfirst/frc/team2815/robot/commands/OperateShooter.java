package org.usfirst.frc.team2815.robot.commands;

import org.usfirst.frc.team2815.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class OperateShooter extends Command {
	double shootSpeed;
    public OperateShooter() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	shootSpeed = -.7;
    	requires(Robot.shooter);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	shootSpeed += .01*Robot.oi.getFlightY();
    	SmartDashboard.putNumber("rate",shootSpeed);
    	if(Robot.oi.getFlight1()){
    		Robot.shooter.shoot(true, shootSpeed);
    	}else{
    		Robot.shooter.shoot(false, 0);
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
