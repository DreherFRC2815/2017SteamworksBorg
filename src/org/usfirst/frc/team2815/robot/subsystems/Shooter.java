package org.usfirst.frc.team2815.robot.subsystems;

import org.usfirst.frc.team2815.robot.RobotMap;

import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Shooter extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	final int MAX_PCBUS = 1;
    
    //motor decleration
    private Spark shootMotor;
    
    //incremented value for the motors setpoint
    double accel;
    private double incVal;
	private double setPoint;
	private double error;
	
	
    public Shooter(){
    	shootMotor = new Spark(RobotMap.shootMotorPort);
    	accel = .05;
    	
    	
    	
    	incVal = 0;
    	setPoint = .7;
    }
    
    
	
	public void shoot(boolean active, double rate){
		setPoint = rate;
		
		
		if(active){
    		if(incVal != setPoint)
    			if(incVal > setPoint)
    				incVal -= accel;
    			if(incVal < setPoint)
    				incVal += accel;
			
		}else{
			if(incVal != 0)
    			if(incVal > 0)
    				incVal -= accel;
    			if(incVal < 0)
    				incVal += accel;
		}
		shootMotor.set(incVal);
		
	}
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

