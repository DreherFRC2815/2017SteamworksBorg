package org.usfirst.frc.team2815.robot.commands;

import org.usfirst.frc.team2815.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class OperateShooter extends Command {
	double shootSpeed;
	
	boolean negate;
	boolean hasChanged;
	
    public OperateShooter() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	shootSpeed = -.7;
    	requires(Robot.shooter);
    	
    	negate = false;
    	hasChanged = false;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if(Robot.oi.getFlight5()){
    		negate = true;
    		shootSpeed = .35;
    		hasChanged = true;
    	}else{
    		negate = false;
    		
    		if(hasChanged){
    			shootSpeed = -.75;
    		}
    		hasChanged = false;
    	}
    	
    	
    	if(Robot.oi.getFlight1()){
    		Robot.shooter.shoot(true, shootSpeed);
    	}else{
    		Robot.shooter.shoot(false, 0);
    	}
    	
    	shootSpeed += .01*Robot.oi.getFlightY();
    	
    	SmartDashboard.putBoolean("isShooterNegated", negate);
    	SmartDashboard.putNumber("rate",shootSpeed);
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
