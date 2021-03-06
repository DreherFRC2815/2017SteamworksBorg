package org.usfirst.frc.team2815.robot.commands;

import org.usfirst.frc.team2815.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class OperateLoader extends Command {
	private boolean negated;
    public OperateLoader() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	negated = false;
    	requires(Robot.loader);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if(Robot.oi.getFlight5()){
    		Robot.loader.setNegateValue(-1);
    		negated = true;
    	}else{
    		Robot.loader.setNegateValue(1);
    		negated = false;
    	}
    	
    	
    	if(Robot.oi.getFlight2()){
    		Robot.loader.load(true);
    	}else{
    		Robot.loader.load(false);
    	}
    	
    	SmartDashboard.putBoolean("isLoaderNegated", negated);
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
