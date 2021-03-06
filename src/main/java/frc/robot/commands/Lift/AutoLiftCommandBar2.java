package frc.robot.commands.Lift;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.Lift;

public class AutoLiftCommandBar2 extends CommandBase{
 
	// The subsystem the command runs on
	private final Lift subsystem;

 	private double speed;

	// encoder value for bar 1 height of left arm
	private int bar2HeightLeft = 220444;  
	// encoder value for bar 1 height of right arm 
	private int bar2HeightRight = 221345;  
	
	//calling climber motors
	private final WPI_TalonFX leftLiftMotor = new WPI_TalonFX(Constants.LEFT_LIFT_MOTOR);
	private final WPI_TalonFX rightLiftMotor = new WPI_TalonFX(Constants.RIGHT_LIFT_MOTOR);

	//command requirements
 	public AutoLiftCommandBar2(Lift subsystem, double speed) {
  		this.subsystem = subsystem;
  		this.speed = speed;
   		addRequirements(subsystem);
 	}

 //starts command
 @Override
 public void initialize() {
  	subsystem.startLift(speed);
 }

 //end command conditions
 @Override
 public boolean isFinished() {
	 /** Run climber motors until the encoder values are greater than the value of the height of bar #2
	     values definded in variable above */
	if (leftLiftMotor.getSelectedSensorPosition() >= bar2HeightLeft &&
	   rightLiftMotor.getSelectedSensorPosition() >= bar2HeightRight) {
		  return true;
	}
	else {
		return false;
	}
}
 
//ends command
 @Override
 public void end(boolean interrupted){ 
			subsystem.stopLift();
	   }

} // End class
