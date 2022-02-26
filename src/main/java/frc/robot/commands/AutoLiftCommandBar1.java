package frc.robot.commands;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.Lift;

public class AutoLiftCommandBar1 extends CommandBase{
 
	// The subsystem the command runs on
	private final Lift subsystem;

 	private double speed;

	// encoder value for bar 1 height of left arm
	private int bar1HeightLeft = 79202; 
	// encoder value for bar 1 height of right arm  
	private int bar1HeightRight = 79856;  
	
	private final WPI_TalonFX leftLiftMotor = new WPI_TalonFX(Constants.LEFT_LIFT_MOTOR);
	private final WPI_TalonFX rightLiftMotor = new WPI_TalonFX(Constants.RIGHT_LIFT_MOTOR);

 	public AutoLiftCommandBar1(Lift subsystem, double speed) {
  	this.subsystem = subsystem;
  	this.speed = speed;
   	addRequirements(subsystem);
 	}

 @Override
 public void initialize() {
  	subsystem.startLift(speed);
 }

 /** 
 @Override
 public void end(boolean interrupted){ 
	// if motor encoder values are greater than or the encoder value of the height of bar #1, stop motors.
	//FIXME this did not stop the motors, kept going forever.
   	if (leftLiftMotor.getSelectedSensorPosition() >= bar1HeightLeft && 
	   rightLiftMotor.getSelectedSensorPosition() >= bar1HeightRight) {
			subsystem.stopLift();
	   }
 }*/

 @Override
 	public boolean isFinished() {
		if (leftLiftMotor.getSelectedSensorPosition() >= bar1HeightLeft && 
	   	rightLiftMotor.getSelectedSensorPosition() >= bar1HeightRight) {
  			return true;
		}
		else {
			return false;
		}
 }

} // End class
