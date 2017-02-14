package org.usfirst.frc.team2815.robot.autocommands;

import org.usfirst.frc.team2815.robot.Robot;
import org.usfirst.frc.team2815.robot.commands.DriveMecanum;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class LeftGearAuto extends CommandGroup {

    public LeftGearAuto() {
        // Add Commands here:
        // e.g. addSequential(new Command1());
        //      addSequential(new Command2());
        // these will run in order.
    	addSequential(new ForwardAuto());
    	
    	addSequential(new TurnRightAuto());
    	
    	addSequential(new SlightDriveForwardAuto());
        // To run multiple commands at the same time,
        // use addParallel()
        // e.g. addParallel(new Command1());
        //      addSequential(new Command2());
        // Command1 and Command2 will run in parallel.

        // A command group will require all of the subsystems that each member
        // would require.
        // e.g. if Command1 requires chassis, and Command2 requires arm,
        // a CommandGroup containing them would require both the chassis and the
        // arm.
    }
}
