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
    private Spark climbMotor;
    
    //incremented value for the motors setpoint
    private int setPoint;
    
	public Climber(){
		setPoint = 0;
		
		//motor initialization
		climbMotor = new Spark(RobotMap.climbMotorPort);
	}
	
	public void climb(boolean active){
		if(active){
			if(MAX_PCBUS > setPoint){
				setPoint += .01;
			}
		}else{
			setPoint -= .01;
		}
		
		climbMotor.set(setPoint);
	}
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

