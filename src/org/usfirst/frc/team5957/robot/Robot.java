
package org.usfirst.frc.team5957.robot;

import org.usfirst.frc.team5957.robot.OI.ControllerType;
import org.usfirst.frc.team5957.robot.commands.DriveTrainAimAtLift;
import org.usfirst.frc.team5957.robot.commands.DriveTrainTurnToGear;
import org.usfirst.frc.team5957.robot.commands.DriveTrainArcadeDrive;
import org.usfirst.frc.team5957.robot.commands.DriveTrainAutonomousGroup;
import org.usfirst.frc.team5957.robot.commands.DriveTrainBrake;
import org.usfirst.frc.team5957.robot.commands.DriveTrainTankDrive;

import org.usfirst.frc.team5957.robot.commands.DrivetrainDriveAndTurn;
import org.usfirst.frc.team5957.robot.commands.DrivetrainDriveForward;
import org.usfirst.frc.team5957.robot.commands.DrivetrainTurn;

import org.usfirst.frc.team5957.robot.subsystems.DriveTrain;

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

	public static OI oi;
    public static DriveTrain driveTrain = new DriveTrain();
    public static Vision vision = new Vision();

	Command teleopCommand;
	Command autonomousCommand;
	SendableChooser<ControllerType> joystick;
	SendableChooser<Command> autoChooser;
	SendableChooser<Command> teleChooser;

    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {
		oi = new OI();
        driveTrain.init();
        
        joystick = new SendableChooser<ControllerType>();
        joystick.addObject("Gamepad", ControllerType.kGamepad);
        joystick.addDefault("Flight Sticks", ControllerType.kFlightStick);
        SmartDashboard.putData("Joystick Choice", joystick);
	}

	/**
     * This function is called once each time the robot enters Disabled mode.
     * You can use it to reset any subsystem information you want to clear when
	 * the robot is disabled.
     */
    public void disabledInit(){

    }
	
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
		oi.dashboardUpdate();
        oi.changeJoystick(joystick.getSelected());
	}

	/**
	 * This autonomous (along with the autoChooser code above) shows how to select between different autonomous modes
	 * using the dashboard. The sendable autoChooser code works with the Java SmartDashboard. If you prefer the LabVIEW
	 * Dashboard, remove all of the autoChooser code and uncomment the getString code to get the auto name from the text box
	 * below the Gyro
	 *
	 * You can add additional auto modes by adding additional commands to the autoChooser code above (like the commented example)
	 * or additional comparisons to the switch structure below with additional strings & commands.
	 */
    public void autonomousInit() {
        autonomousCommand = (Command) autoChooser.getSelected();
        
		/* String autoSelected = SmartDashboard.getString("Auto Selector", "Default");
		switch(autoSelected) {
		case "My Auto":
			autonomousCommand = new MyAutoCommand();
			break;
		case "Default Auto":
		default:
			autonomousCommand = new ExampleCommand();
			break;
		} */
    	
    	// schedule the autonomous command (example)
        if (autonomousCommand != null) { autonomousCommand.start(); }
    }

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {
        Scheduler.getInstance().run();
        oi.dashboardUpdate();
        oi.changeJoystick(joystick.getSelected());
    }

    public void teleopInit() {
		// This makes sure that the autonomous stops running when
        // teleop starts running. If you want the autonomous to 
        // continue until interrupted by another command, remove
        // this line or comment it out.
        if (autonomousCommand != null) { autonomousCommand.cancel(); }
        teleopCommand = (Command) teleChooser.getSelected();
        if (teleopCommand != null) { teleopCommand.start(); }
    }

    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {
        Scheduler.getInstance().run();
        oi.dashboardUpdate();
        oi.changeJoystick(joystick.getSelected());
    }
    
    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
        LiveWindow.run();
        oi.dashboardUpdate();
    }
}
