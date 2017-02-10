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
	private RobotDrive mecanum;
	
	private ADXRS450_Gyro gyro;
	double gyroPIDIN;
	
	private double P,I,D,F;
	private double pP, pI, pD;
	private int izone;
	private int profile;
	
	public CANTalon[] SRXMotors = new CANTalon[4];
	
	private String controlMode;
	// 0 2
	// 1 3
	public DriveTrain(){
		
		gyro = new ADXRS450_Gyro();
		gyro.reset();
		gyroPIDIN = 0;
		
		P = 5.845;  //5.845
		I = .000;
		D = 58.45;
		F = 4.092;	//3.41	
		izone = 400;		
		profile = 0;
		
		pP = .576875;
		pI = 0;
		pD = 5.76875;
		
		SRXMotors[0] = new CANTalon(0);
		SRXMotors[1] = new CANTalon(1);
		SRXMotors[2] = new CANTalon(2);
		SRXMotors[3] = new CANTalon(3);
		
		SRXMotors[0].reverseSensor(true);
		SRXMotors[1].reverseSensor(true);
				
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
		
		SRXMotors[0].setVoltageRampRate(24);
		SRXMotors[1].setVoltageRampRate(24);
		SRXMotors[2].setVoltageRampRate(24);
		SRXMotors[3].setVoltageRampRate(24);
		
		SRXMotors[0].setPID(P, I, D);
		SRXMotors[1].setPID(P, I, D);
		SRXMotors[2].setPID(P, I, D);
		SRXMotors[3].setPID(P, I, D);
		
		SRXMotors[0].setF(F);
		SRXMotors[1].setF(F);
		SRXMotors[2].setF(F);
		SRXMotors[3].setF(F);
					
		mecanum = new RobotDrive(SRXMotors[0], SRXMotors[1], SRXMotors[2], SRXMotors[3]);
		mecanum.setMaxOutput(270);
		mecanum.setSafetyEnabled(false);
		
		controlMode = "velocity";
	}
	
	public void driveMecanumGyro(double y, double x, double zTurn){
		//mecanum.mecanumDrive_Cartesian(x, y, zTurn, gyro.getAngle());
		mecanum.mecanumDrive_Cartesian(x, y, zTurn, gyro.getAngle());
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
		
		controlMode = "Veloctiy";
	}
	
	public void prepareForDistanceControl(){
		SRXMotors[0].changeControlMode(TalonControlMode.Position);
		SRXMotors[1].changeControlMode(TalonControlMode.Position);
		SRXMotors[2].changeControlMode(TalonControlMode.Position);
		SRXMotors[3].changeControlMode(TalonControlMode.Position);
		
		SRXMotors[0].setEncPosition(SRXMotors[0].getPulseWidthPosition() & 0xFFF);
		SRXMotors[1].setEncPosition(SRXMotors[1].getPulseWidthPosition() & 0xFFF);
		SRXMotors[2].setEncPosition(SRXMotors[2].getPulseWidthPosition() & 0xFFF);
		SRXMotors[3].setEncPosition(SRXMotors[3].getPulseWidthPosition() & 0xFFF);		
		
		SRXMotors[0].set(0);
		SRXMotors[1].set(0);
		SRXMotors[2].set(0);
		SRXMotors[3].set(0);
		
		SRXMotors[0].setAllowableClosedLoopErr(0);
		SRXMotors[1].setAllowableClosedLoopErr(0);
		SRXMotors[2].setAllowableClosedLoopErr(0);
		SRXMotors[3].setAllowableClosedLoopErr(0);
		
		SRXMotors[0].setPID(pP, pI, pD);
		SRXMotors[1].setPID(pP, pI, pD);
		SRXMotors[2].setPID(pP, pI, pD);
		SRXMotors[3].setPID(pP, pI, pD);
		
		SRXMotors[0].enableControl();
		SRXMotors[1].enableControl();
		SRXMotors[2].enableControl();
		SRXMotors[3].enableControl();
		
		controlMode = "Position";
	}
	
	public void prepareForVoltageControl(){
		SRXMotors[0].reset();
		SRXMotors[1].reset();
		SRXMotors[2].reset();
		SRXMotors[3].reset();
		
		SRXMotors[0].changeControlMode(TalonControlMode.Voltage);
		SRXMotors[1].changeControlMode(TalonControlMode.Voltage);
		SRXMotors[2].changeControlMode(TalonControlMode.Voltage);
		SRXMotors[3].changeControlMode(TalonControlMode.Voltage);
		
		SRXMotors[0].set(0);
		SRXMotors[1].set(0);
		SRXMotors[2].set(0);
		SRXMotors[3].set(0);
		
		mecanum.setMaxOutput(12);
		
		controlMode = "Voltage";
	}
	
	public void resetGyro(){
		gyro.reset();
		gyro.calibrate();
	}
	
	public double getGyroAngle(){
		return gyro.getAngle();
	}
	
	public double getGyroPID(){
		return gyroPIDIN;
	}
	
	public void setAnglePIDOut(double PIDin){
		gyroPIDIN = PIDin;
	}
	
	public void driveDistance(double Ldistance, double Rdistance){
		SRXMotors[0].set(Ldistance + gyroPIDIN);
		SRXMotors[1].set(Ldistance + gyroPIDIN);
		SRXMotors[2].set(Rdistance + gyroPIDIN);
		SRXMotors[3].set(Rdistance + gyroPIDIN);
	}
	
	public void forTesting(double lf, double lb, double rf, double rb){
		SRXMotors[0].set(lf);
		SRXMotors[1].set(lf);
		SRXMotors[2].set(lf);
		SRXMotors[3].set(lf);
	}
	
	public String getControlMode(){
		return controlMode;
	}
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
}

