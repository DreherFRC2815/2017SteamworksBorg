package org.usfirst.frc.team2815.robot.subsystems;

import org.usfirst.frc.team2815.robot.RobotMap;

import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Loader extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	//max percent bus to be output to motor
    final int MAX_PCBUS = 1;
    
    //motor decleration
    private Spark loadMotor;
    
    //incremented value for the motors setpoint
    private double incVal;
    private double setPoint;
    public Loader(){
    	loadMotor = new Spark(RobotMap.loadMotorPort);
    	incVal = 0;
    	setPoint = -.60;
    }
    
    public void load(boolean active){
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
    	loadMotor.set(incVal);
	}
    
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

