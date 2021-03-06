package org.usfirst.frc.team5957.robot.subsystems;

import static org.usfirst.frc.team5957.robot.RobotMap.*;
import org.usfirst.frc.team5957.robot.commands.drivetrain.DriveTrainBrake;

import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * This subsystem contains the motors used to drive the robot.
 * 
 * @see Subsystem
 */
public class DriveTrain extends Subsystem {

	private VictorSP m_leftFront;
	private VictorSP m_rightFront;
	private VictorSP m_leftBack;
	private VictorSP m_rightBack;
	RobotDrive roboDrive;

	/**
	 * Initializes the robot's drive motors. Call in
	 * {@link org.usfirst.frc.team5957.robot.Robot#robotInit()
	 * Robot.robotInit()}.
	 */
	public void init() {
		m_leftFront = new VictorSP(LEFT_FRONT_MOTOR);
		m_rightFront = new VictorSP(RIGHT_FRONT_MOTOR);
		m_leftBack = new VictorSP(LEFT_BACK_MOTOR);
		m_rightBack = new VictorSP(RIGHT_BACK_MOTOR);

		roboDrive = new RobotDrive(m_leftFront, m_leftBack, m_rightFront, m_rightBack);
	}

	/**
	 * This method stops the robot from moving.
	 */
	public void brake() {
		roboDrive.drive(0, 0);
	}

	/**
	 * Drives the robot like {@link RobotDrive#drive(double, double)
	 * RobotDrive.drive()}
	 * 
	 * @param power
	 *            The speed setting for the outside wheel in a turn, forward or
	 *            backwards, +1 to -1.
	 * @param turn
	 *            The rate of turn, constant for different forward speeds. Set
	 *            curve < 0 for left turn or curve > 0 for right turn. Set curve
	 *            = e^(-r/w) to get a turn radius r for wheelbase w of your
	 *            robot. Conversely, turn radius r = -ln(curve)*w for a given
	 *            value of curve and wheelbase w.
	 */
	public void drive(double power, double turn) {
		roboDrive.drive(power, turn);
	}

	/**
	 * Implements a tank drive system for the robot. Can be used the vertical
	 * values from two joysticks.
	 * 
	 * @param leftPow
	 *            The speed setting for the left side of the drive train, +1 to
	 *            -1. (Negative is forward, as with a joystick's Y axis)
	 * @param rightPow
	 *            The speed setting for the right side of the drive train, +1 to
	 *            -1. (Negative is forward, as with a joystick's Y axis)
	 * 
	 * @see RobotDrive#tankDrive(double, double)
	 */
	public void tankDrive(double leftPow, double rightPow) {
		roboDrive.tankDrive(leftPow, rightPow);
	}

	/**
	 * Implements an arcade drive. Can be used with a the horizontal and
	 * vertical values from a single joystick.
	 * 
	 * @param moveValue
	 *            The value to be used for forwards/backwards, +1 to -1.
	 *            (Negative is forward, as with a joystick's Y axis)
	 * @param rotateValue
	 *            The value to be used for right/left, +1 to -1.
	 * 
	 * @see RobotDrive#arcadeDrive(double, double)
	 */
	public void arcadeDrive(double moveValue, double rotateValue) {
		roboDrive.arcadeDrive(moveValue, rotateValue);
	}

	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		setDefaultCommand(new DriveTrainBrake());
	}
}
