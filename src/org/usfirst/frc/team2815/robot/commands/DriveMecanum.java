package org.usfirst.frc.team2815.robot.commands;

import org.usfirst.frc.team2815.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class DriveMecanum extends Command {

    public DriveMecanum() {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.driveTrain);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.driveTrain.prepareForDistanceControl();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	//Robot.driveTrain.driveMecanumGyro(Robot.oi.getLeftYValue(), Robot.oi.getLeftXValue(),
    	//		Robot.oi.getRightXValue());
    	//Robot.driveTrain.forTesting(Robot.oi.getLeftYValue(), Robot.oi.getLeftYValue(), Robot.oi.getLeftYValue(), Robot.oi.getLeftYValue());
    	//SmartDashboard.putNumber("supposed set", Robot.oi.getLeftYValue());
    	//SmartDashboard.putNumber("speed", Robot.driveTrain.getSpeed());
    	//if(Robot.oi.getAButton()){
    	//	Robot.driveTrain.resetEverything();
    	//}
    	Robot.driveTrain.driveDistance(2996.75,2996.75, true);
    	
    	SmartDashboard.putBoolean("isFinnished", Robot.driveTrain.SRXMotors[0].getClosedLoopError() < 135 &&
        		Robot.driveTrain.SRXMotors[1].getClosedLoopError() < 135 &&
        		Robot.driveTrain.SRXMotors[2].getClosedLoopError() < 135 &&
        		Robot.driveTrain.SRXMotors[3].getClosedLoopError() < 135);
        SmartDashboard.putNumber("error for 0", Robot.driveTrain.SRXMotors[0].getClosedLoopError());
        SmartDashboard.putNumber("error for 1", Robot.driveTrain.SRXMotors[1].getClosedLoopError());
        SmartDashboard.putNumber("error for 2", Robot.driveTrain.SRXMotors[2].getClosedLoopError());
        SmartDashboard.putNumber("error for 3", Robot.driveTrain.SRXMotors[3].getClosedLoopError());
    	//Robot.driveTrain.driveDistance(-1176.7,1176.7, true);
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
    	//Robot.driveTrain.prepareForDistanceControl();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
