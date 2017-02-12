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
    private int setPoint;
    
    public Loader(){
    	loadMotor = new Spark(RobotMap.loadMotorPort);
    }
    
    public void load(boolean active){
    	if(active){
			if(MAX_PCBUS > setPoint){
				if(setPoint < 1){
					setPoint += .01;
				}
			}
		}else{
			if(setPoint > 0){
				setPoint -= .01;
			}
		}
		
		loadMotor.set(setPoint);
	}
    
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

