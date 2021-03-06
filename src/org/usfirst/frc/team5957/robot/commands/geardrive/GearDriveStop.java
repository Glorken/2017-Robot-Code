package org.usfirst.frc.team5957.robot.commands.geardrive;

import org.usfirst.frc.team5957.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 * Stops the gear conveyor from moving.
 * 
 * @author Jakob Burgos
 */
public class GearDriveStop extends Command {

	public GearDriveStop() {
		requires(Robot.gearDrive);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		Robot.gearDrive.stop();
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		Robot.gearDrive.stop();
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return false;
	}

	// Called once after isFinished returns true
	protected void end() {
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
	}
}
