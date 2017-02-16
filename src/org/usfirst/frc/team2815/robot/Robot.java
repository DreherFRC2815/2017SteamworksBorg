
package org.usfirst.frc.team2815.robot;

import org.usfirst.frc.team2815.robot.autocommands.CenterAuto;
import org.usfirst.frc.team2815.robot.autocommands.LeftGearAuto;
import org.usfirst.frc.team2815.robot.autocommands.RightGearAuto;
import org.usfirst.frc.team2815.robot.commands.DriveMecanum;
import org.usfirst.frc.team2815.robot.commands.OperateClimber;
import org.usfirst.frc.team2815.robot.commands.OperateLoader;
import org.usfirst.frc.team2815.robot.commands.OperateShooter;
import org.usfirst.frc.team2815.robot.subsystems.Climber;
import org.usfirst.frc.team2815.robot.subsystems.DriveTrain;
import org.usfirst.frc.team2815.robot.subsystems.Loader;
import org.usfirst.frc.team2815.robot.subsystems.PIDGyro;
import org.usfirst.frc.team2815.robot.subsystems.Shooter;

import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;



/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {
	
	//OI DECLERATION
	public static OI oi;
	
	//SUBSYTEM DECLERATION
	public static DriveTrain driveTrain;
	public static Climber climber;
	public static Loader loader;
	public static Shooter shooter;
	public static PIDGyro pidGyro;
	
	//COMMAND DECLERATION
	Command driveMecanum;
	Command operateClimber;
	Command operateShooter;
	Command operateLoader;
	
	//AUTOCOMMAND DECLERATION
	Command autonomousCommand;
	SendableChooser chooser = new SendableChooser();
	
	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
		//OI INITIALIZATION
		oi = new OI();
		
		//SUBSYSTEM INITIALIZATION
		driveTrain = new DriveTrain();
		climber = new Climber();
		loader = new Loader();
		shooter = new Shooter();
		pidGyro = new PIDGyro();
		
		//COMMAND INITIALIZATION
		driveMecanum = new DriveMecanum();
		operateClimber = new OperateClimber();
		operateLoader = new OperateLoader();
		operateShooter = new OperateShooter();
		
		//AUTO COMMANDS
	    chooser.addDefault("Center Auto", new CenterAuto());
	    chooser.addObject("right auto", new RightGearAuto());
		chooser.addObject("left auto", new LeftGearAuto());
		SmartDashboard.putData("Auto mode", chooser);
		
		//ROBOT PREPERATION
		driveTrain.resetGyro();
		
	}

	/**
	 * This function is called once each time the robot enters Disabled mode.
	 * You can use it to reset any subsystem information you want to clear when
	 * the robot is disabled.
	 */
	@Override
	public void disabledInit() {

	}

	@Override
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

	/**
	 * This autonomous (along with the chooser code above) shows how to select
	 * between different autonomous modes using the dashboard. The sendable
	 * chooser code works with the Java SmartDashboard. If you prefer the
	 * LabVIEW Dashboard, remove all of the chooser code and uncomment the
	 * getString code to get the auto name from the text box below the Gyro
	 *
	 * You can add additional auto modes by adding additional commands to the
	 * chooser code above (like the commented example) or additional comparisons
	 * to the switch structure below with additional strings & commands.
	 */
	@Override
	public void autonomousInit() {
		
		autonomousCommand = (Command)chooser.getSelected();
		
		
		/*
		 * String autoSelected = SmartDashboard.getString("Auto Selector",
		 * "Default"); switch(autoSelected) { case "My Auto": autonomousCommand
		 * = new MyAutoCommand(); break; case "Default Auto": default:
		 * autonomousCommand = new ExampleCommand(); break; }
		 */

		// schedule the autonomous command (example)
		//driveTrain.prepareForDistanceControl();
		//driveTrain.driveDistance(1, 1);
		
		if (autonomousCommand != null){
			autonomousCommand.start();
		}
		Scheduler.getInstance().removeAll();
	}

	/**
	 * This function is called periodically during autonomous
	 */
	@Override
	public void autonomousPeriodic() {
		
		
		Scheduler.getInstance().run();
		
		SmartDashboard.putNumber("gyroPid", driveTrain.getGyroPID());
		SmartDashboard.putNumber("gyroAngle", driveTrain.getGyroAngle());
		
		
	}

	@Override
	public void teleopInit() {
		// This makes sure that the autonomous stops running when
		// teleop starts running. If you want the autonomous to
		// continue until interrupted by another command, remove
		// this line or comment it out.
		if (autonomousCommand != null)
			autonomousCommand.cancel();
		
		pidGyro.disable();
		driveTrain.prepareForVelocityControl();
		driveMecanum.start();
		operateClimber.start();
		operateLoader.start();
		operateShooter.start();
	}

	/**
	 * This function is called periodically during operator control
	 */
	@Override
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
		/*double centerX;
		synchronized (imgLock) {
			centerX = this.centerX;
		}
		double turn = centerX - (IMG_WIDTH / 2);*/
		//drive.arcadeDrive(-0.6, turn * 0.005);
	}

	/**
	 * This function is called periodically during test mode
	 */
	@Override
	public void testPeriodic() {
		LiveWindow.run();
	}
}
