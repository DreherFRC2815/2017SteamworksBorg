package org.usfirst.frc.team2815.robot.subsystems;

import com.ctre.CANTalon;
import com.ctre.CANTalon.FeedbackDevice;
import com.ctre.CANTalon.TalonControlMode;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.RobotDrive.MotorType;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class DriveTrain extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	private RobotDrive mecanum;
	
	private ADXRS450_Gyro gyro;
	
	private double P,I,D,F;
	private double pP, pI, pD;
	private int izone;
	private int profile;
	
	public CANTalon[] SRXMotors = new CANTalon[4];
	
	private String controlMode;
	
	boolean drivePolar = false;
	// 0 3
	// 1 2
	public DriveTrain(){
		
		gyro = new ADXRS450_Gyro();
		gyro.reset();
		
		P = 5.845;  //5.845
		I = 0;
		D = 58.45;
		F = 4.092;	//3.41	
		izone = 75;		
		profile = 0;
		
		pP = .576875;
		pI = .00576875;
		pD = 5.76875;
		
		SRXMotors[0] = new CANTalon(1);
		SRXMotors[1] = new CANTalon(0);
		SRXMotors[2] = new CANTalon(3);
		SRXMotors[3] = new CANTalon(2);
		
		//SRXMotors[2].reverseSensor(true);
		//SRXMotors[3].reverseSensor(true);
				
		SRXMotors[0].reset();
		SRXMotors[1].reset();
		SRXMotors[2].reset();
		SRXMotors[3].reset();
		
		SRXMotors[0].changeControlMode(TalonControlMode.Speed);
		SRXMotors[1].changeControlMode(TalonControlMode.Speed);
		SRXMotors[2].changeControlMode(TalonControlMode.Speed);
		SRXMotors[3].changeControlMode(TalonControlMode.Speed);
		
		SRXMotors[0].set(0);
		SRXMotors[1].set(0);
		SRXMotors[2].set(0);
		SRXMotors[3].set(0);
		
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
		
		SRXMotors[0].setVoltageRampRate(18);
		SRXMotors[1].setVoltageRampRate(18);
		SRXMotors[2].setVoltageRampRate(18);
		SRXMotors[3].setVoltageRampRate(18);
		
		SRXMotors[0].setPID(P, I, D);
		SRXMotors[1].setPID(P, I, D);
		SRXMotors[2].setPID(P, I, D);
		SRXMotors[3].setPID(P, I, D);
		
		SRXMotors[0].setF(F);
		SRXMotors[1].setF(F);
		SRXMotors[2].setF(F);
		SRXMotors[3].setF(F);
					
		mecanum = new RobotDrive(SRXMotors[0], SRXMotors[1], SRXMotors[3], SRXMotors[2]);
		mecanum.setMaxOutput(270);
		mecanum.setInvertedMotor(MotorType.kFrontRight, true);
		mecanum.setInvertedMotor(MotorType.kRearRight, true);
		mecanum.setSafetyEnabled(false);
		
		controlMode = "velocity";
	}
	
	public void driveMecanumGyro(double y, double x, double zTurn){
		//mecanum.mecanumDrive_Cartesian(x, y, zTurn, gyro.getAngle());
		if(drivePolar){
			mecanum.mecanumDrive_Polar(y, x, zTurn);
		}else{
			mecanum.mecanumDrive_Cartesian(x, y, zTurn, -1*gyro.getAngle());
		}
		
		//mecanum.mecanumDrive_Polar(y, x, zTurn);
		
	}
	
	public void prepareForVelocityControl(){
		SRXMotors[0].reset();
		SRXMotors[1].reset();
		SRXMotors[2].reset();
		SRXMotors[3].reset();
		
		SRXMotors[0].changeControlMode(TalonControlMode.Speed);
		SRXMotors[1].changeControlMode(TalonControlMode.Speed);
		SRXMotors[2].changeControlMode(TalonControlMode.Speed);
		SRXMotors[3].changeControlMode(TalonControlMode.Speed);
		
		SRXMotors[0].set(0);
		SRXMotors[1].set(0);
		SRXMotors[2].set(0);
		SRXMotors[3].set(0);
		
		SRXMotors[0].setPID(P, I, D);
		SRXMotors[1].setPID(P, I, D);
		SRXMotors[2].setPID(P, I, D);
		SRXMotors[3].setPID(P, I, D);
		
		SRXMotors[0].setF(F);
		SRXMotors[1].setF(F);
		SRXMotors[2].setF(F);
		SRXMotors[3].setF(F);
		
		SRXMotors[0].enableControl();
		SRXMotors[1].enableControl();
		SRXMotors[2].enableControl();
		SRXMotors[3].enableControl();
		
		mecanum.setMaxOutput(300);
		
		controlMode = "Veloctiy";
	}
	
	public void prepareForDistanceControl(){
		SRXMotors[0].reset();
		SRXMotors[1].reset();
		SRXMotors[2].reset();
		SRXMotors[3].reset();
		
		SRXMotors[0].changeControlMode(TalonControlMode.Position);
		SRXMotors[1].changeControlMode(TalonControlMode.Position);
		SRXMotors[2].changeControlMode(TalonControlMode.Position);
		SRXMotors[3].changeControlMode(TalonControlMode.Position);
		
		SRXMotors[0].setEncPosition(0);
		SRXMotors[1].setEncPosition(0);
		SRXMotors[2].setEncPosition(0);
		SRXMotors[3].setEncPosition(0);
		
		SRXMotors[0].setEncPosition(SRXMotors[0].getPulseWidthPosition() & 0xFFF);
		SRXMotors[1].setEncPosition(SRXMotors[1].getPulseWidthPosition() & 0xFFF);
		SRXMotors[2].setEncPosition(SRXMotors[2].getPulseWidthPosition() & 0xFFF);
		SRXMotors[3].setEncPosition(SRXMotors[3].getPulseWidthPosition() & 0xFFF);		
		/*
		SRXMotors[0].set(0);
		SRXMotors[1].set(0);
		SRXMotors[2].set(0);
		SRXMotors[3].set(0);
		*/
		SRXMotors[0].setAllowableClosedLoopErr(0);
		SRXMotors[1].setAllowableClosedLoopErr(0);
		SRXMotors[2].setAllowableClosedLoopErr(0);
		SRXMotors[3].setAllowableClosedLoopErr(0);
		
		SRXMotors[0].setPID(pP, pI, pD);
		SRXMotors[1].setPID(pP, pI, pD);
		SRXMotors[2].setPID(pP, pI, pD);
		SRXMotors[3].setPID(pP, pI, pD);
		
		SRXMotors[0].setIZone(izone);
		SRXMotors[1].setIZone(izone);
		SRXMotors[2].setIZone(izone);
		SRXMotors[3].setIZone(izone);
		
		SRXMotors[0].enableControl();
		SRXMotors[1].enableControl();
		SRXMotors[2].enableControl();
		SRXMotors[3].enableControl();
		
		controlMode = "Position";
	}
	
	public void prepareForVoltageControl(){
		
		SRXMotors[0].changeControlMode(TalonControlMode.Voltage);
		SRXMotors[1].changeControlMode(TalonControlMode.Voltage);
		SRXMotors[2].changeControlMode(TalonControlMode.Voltage);
		SRXMotors[3].changeControlMode(TalonControlMode.Voltage);
		
		SRXMotors[0].set(0);
		SRXMotors[1].set(0);
		SRXMotors[2].set(0);
		SRXMotors[3].set(0);
		
		SRXMotors[0].setCloseLoopRampRate(24);
		SRXMotors[1].setCloseLoopRampRate(24);
		SRXMotors[2].setCloseLoopRampRate(24);
		SRXMotors[3].setCloseLoopRampRate(24);
		
		mecanum.setMaxOutput(12);
		
		controlMode = "Voltage";
		
		drivePolar = true;
	}
	
	public void resetGyro(){
		gyro.reset();
		
		
	}
	
	public double getGyroAngle(){
		return gyro.getAngle();
	}
	
	public void driveDistance(double Ldistance, double Rdistance){
		SRXMotors[0].set(Ldistance);
		SRXMotors[1].set(Ldistance);
		SRXMotors[2].set(Rdistance);
		SRXMotors[3].set(Rdistance);
	}
	
	
	public String getControlMode(){
		return controlMode;
	}
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
}

