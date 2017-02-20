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
	
	public double lTarg;
	public double rTarg;
    public ForwardAuto(double lT, double rT) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.driveTrain);
    	lTarg = lT;
    	rTarg = rT;
    	
    	finnishLock = true;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.driveTrain.prepareForDistanceControl();
    	//Robot.pidGyro.enable();
    	finnishLock = true;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	
    	Robot.driveTrain.driveDistance(lTarg,rTarg);
    	
    	SmartDashboard.putNumber("forward error for 0", Robot.driveTrain.SRXMotors[0].getClosedLoopError());
        SmartDashboard.putNumber("forward error for 1", Robot.driveTrain.SRXMotors[1].getClosedLoopError());
        SmartDashboard.putNumber("forward error for 2", Robot.driveTrain.SRXMotors[2].getClosedLoopError());
        SmartDashboard.putNumber("forward error for 3", Robot.driveTrain.SRXMotors[3].getClosedLoopError());
        
        if(Math.abs(Robot.driveTrain.SRXMotors[0].getClosedLoopError()) > 20 &&
        		Math.abs(Robot.driveTrain.SRXMotors[1].getClosedLoopError()) > 20 &&
        		Math.abs(Robot.driveTrain.SRXMotors[2].getClosedLoopError()) > 20 &&
        		Math.abs(Robot.driveTrain.SRXMotors[3].getClosedLoopError()) > 20
        	){
        	finnishLock = false;
        }
        SmartDashboard.putBoolean("finnishLock", finnishLock);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return Math.abs(Robot.driveTrain.SRXMotors[0].getClosedLoopError()) < 200 &&
        		Math.abs(Robot.driveTrain.SRXMotors[1].getClosedLoopError()) < 200 &&
        		Math.abs(Robot.driveTrain.SRXMotors[2].getClosedLoopError()) < 200 &&
        		Math.abs(Robot.driveTrain.SRXMotors[3].getClosedLoopError()) < 200 && finnishLock == false;
        
        
        
    }

    // Called once after isFinished returns true
    protected void end() {
    	
    	
    	
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
