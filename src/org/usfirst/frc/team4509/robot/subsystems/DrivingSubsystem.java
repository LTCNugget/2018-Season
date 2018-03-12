package org.usfirst.frc.team4509.robot.subsystems;


import org.usfirst.frc.team4509.robot.RobotMap;

import com.ctre.phoenix.motorcontrol.NeutralMode;

import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.command.Subsystem;


/**
 * Controls the drive platform and slide drive talon
 * 
 * @author FRC Team 4509
 */
public class DrivingSubsystem extends Subsystem {

	public void initDefaultCommand() {}

	/**
	 * @param leftYAxis used to drive the left side of the robot
	 * @param rightYAxis used to drive the right side of the robot
	 */
	public void tankDriving(double leftYAxis, double rightYAxis) {
		double baseSpeed = Preferences.getInstance().getDouble("BASE_DRIVE_SPEED", 0.75);
		RobotMap.drive.tankDrive(baseSpeed * leftYAxis, baseSpeed * rightYAxis);
	}
	
	public void drive(double ySpeed, double rotation, double xSpeed) {
		double baseSpeed = Preferences.getInstance().getDouble("BASE_DRIVE_SPEED", 0.75);
		RobotMap.drive.arcadeDrive(baseSpeed * ySpeed, baseSpeed * rotation);
	}
		
	public void drive(double speed) {
		this.drive(speed, 0, 0);
	}
	
	/**
	 * @param direction the direction to turn. -1 is left, 1 is right
	 */
	public void turn(double direction) {
		this.drive(0, direction, 0);
	}
	
	public void stop() {
		RobotMap.leftFrontDriveTalon.set(0);
		RobotMap.rightFrontDriveTalon.set(0);
	}
	
	/**
	 * Changes the drive talons' neutral modes to the given mode.
	 * See {@link com.ctre.phoenix.motorcontrol.NeutralMode}
	 * @param mode the neutral mode to set to
	 */
	public void setNeutralMode(NeutralMode mode) {
		RobotMap.leftFrontDriveTalon.setNeutralMode(mode);
		RobotMap.leftBackDriveTalon.setNeutralMode(mode);
		RobotMap.rightFrontDriveTalon.setNeutralMode(mode);
		RobotMap.rightBackDriveTalon.setNeutralMode(mode);
	}
	
	public int getRightEncoderTicks() {
		return RobotMap.rightDriveEncoder.get();
	}
	
	public int getLeftEncoderTicks() {
		return RobotMap.leftDriveEncoder.get();
	}
	
	public double getAverageEncoderTicks() {
		return (RobotMap.leftDriveEncoder.get() + RobotMap.rightDriveEncoder.get()) / 2;
	}
	
	public void resetEncoders() {
		RobotMap.leftDriveEncoder.reset();
		RobotMap.rightDriveEncoder.reset();
	}
	
}