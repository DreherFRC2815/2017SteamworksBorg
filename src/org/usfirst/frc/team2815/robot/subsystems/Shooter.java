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
    private double incVal = 0;
	private double setPoint = -.65;
    public Shooter(){
    	shootMotor = new Spark(RobotMap.shootMotorPort);
    }
	
	public void shoot(boolean active){
		if(active){
    		if(incVal != setPoint)
    			if(incVal > setPoint)
    				incVal -= .1;
    			if(incVal < setPoint)
    				incVal += .1;
			
		}else{
			if(incVal != 0)
    			if(incVal > 0)
    				incVal -= .1;
    			if(incVal < 0)
    				incVal += .1;
		}
		shootMotor.set(incVal);
	}
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

