package org.usfirst.frc.team2815.robot.subsystems;

import com.ctre.CANTalon;
import com.ctre.CANTalon.FeedbackDevice;
import com.ctre.CANTalon.TalonControlMode;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class DriveTrain extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	RobotDrive mecanum;
	
	ADXRS450_Gyro gyro;
	
	double P,I,D,F;
	int izone;
	int profile;
	
	public CANTalon[] SRXMotors = new CANTalon[4];
	// 0 1
	// 2 3
	public DriveTrain(){
		gyro = new ADXRS450_Gyro();
		gyro.reset();
		
		P = .3;
		I = .003;
		D = 3;
		F = .0003;
		
		izone = 7;
		
		profile = 0;
		SRXMotors[0] = new CANTalon(0);
		SRXMotors[1] = new CANTalon(1);
		SRXMotors[2] = new CANTalon(2);
		SRXMotors[3] = new CANTalon(3);
		
		SRXMotors[0].changeControlMode(TalonControlMode.Speed);
		SRXMotors[1].changeControlMode(TalonControlMode.Speed);
		SRXMotors[2].changeControlMode(TalonControlMode.Speed);
		SRXMotors[3].changeControlMode(TalonControlMode.Speed);
		
		SRXMotors[0].setFeedbackDevice(FeedbackDevice.QuadEncoder);
		SRXMotors[1].setFeedbackDevice(FeedbackDevice.QuadEncoder);
		SRXMotors[2].setFeedbackDevice(FeedbackDevice.QuadEncoder);
		SRXMotors[3].setFeedbackDevice(FeedbackDevice.QuadEncoder);
		
		SRXMotors[0].configNominalOutputVoltage(+0f, -0f);
		SRXMotors[1].configNominalOutputVoltage(+0f, -0f);
		SRXMotors[2].configNominalOutputVoltage(+0f, -0f);
		SRXMotors[3].configNominalOutputVoltage(+0f, -0f);
		
		SRXMotors[0].configPeakOutputVoltage(+12f, -12f);
		SRXMotors[1].configPeakOutputVoltage(+12f, -12f);
		SRXMotors[2].configPeakOutputVoltage(+12f, -12f);
		SRXMotors[3].configPeakOutputVoltage(+12f, -12f);
		
		SRXMotors[0].setCloseLoopRampRate(2);
		SRXMotors[1].setCloseLoopRampRate(2);
		SRXMotors[2].setCloseLoopRampRate(2);
		SRXMotors[3].setCloseLoopRampRate(2);
		
		SRXMotors[0].setVoltageRampRate(2);
		SRXMotors[1].setVoltageRampRate(2);
		SRXMotors[2].setVoltageRampRate(2);
		SRXMotors[3].setVoltageRampRate(2);
		
		SRXMotors[0].setPID(P, I, D, F, izone, 2, profile);
		SRXMotors[1].setPID(P, I, D, F, izone, 2, profile);
		SRXMotors[2].setPID(P, I, D, F, izone, 2, profile);
		SRXMotors[3].setPID(P, I, D, F, izone, 2, profile);
	}
	
	public void driveMecanumGyro(double y, double x, double zTurn){
		mecanum.mecanumDrive_Cartesian(x, y, zTurn, gyro.getAngle());
	}
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
}

