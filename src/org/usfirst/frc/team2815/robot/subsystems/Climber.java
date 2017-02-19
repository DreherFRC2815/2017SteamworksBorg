package org.usfirst.frc.team2815.robot.subsystems;

import org.usfirst.frc.team2815.robot.RobotMap;

import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */


public class Climber extends Subsystem {

	//max percent bus to be output to motor
    final int MAX_PCBUS = 1;
    
    //motor decleration
    private Spark[] climbMotors;
    
    
    //incremented value for the motors setpoint
    private double setPoint;
    private double incVal;
    
	public Climber(){
		setPoint = 1;
		incVal = 0;
		//motor initialization
		climbMotors[0] = new Spark(RobotMap.climbMotorPorts[0]);
		climbMotors[1] = new Spark(RobotMap.climbMotorPorts[1]);
	}
	
	public void climb(boolean active){
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
		climbMotors[0].set(-1*incVal);
		climbMotors[1].set(incVal);
	}
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

