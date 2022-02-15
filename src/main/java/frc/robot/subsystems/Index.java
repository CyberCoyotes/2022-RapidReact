package frc.robot.subsystems;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

// Shuffleboard imports
import edu.wpi.first.wpilibj.shuffleboard.BuiltInLayouts;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;

// import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;


public class Index extends SubsystemBase {
  
  // variables
  // private final WPI_TalonFX frontIndexMotor;  
  // private final WPI_TalonFX backIndexMotor;
  // private frontIndexMotor = new WPI_TalonFX(Constants.FRONT_INDEX_MOTOR);
  // backIndexMotor = new WPI_TalonFX(Constants.BACK_INDEX_MOTOR);


  // NOTE you must use WPI_TalonFX subclass rather than TalonFX when grouping motors... 60 minutes of my life wasted
  private MotorControllerGroup indexMotors = new MotorControllerGroup(
    new WPI_TalonFX(Constants.FRONT_INDEX_MOTOR), 
    new WPI_TalonFX(Constants.BACK_INDEX_MOTOR));
  
 // private double speed; // schmaybe

  public Index() {
    // TODO Check direction of motors and determine if one needs to be reverse
  }

  public void startIndex(double speed){
      // frontIndexMotor.set(ControlMode.PercentOutput, speed); // Use individual motor lines of code if the group speed doesn't work
      // backIndexMotor.set(ControlMode.PercentOutput, speed);
      indexMotors.set(speed);
  }

  public void stopIndex() {
    // frontIndexMotor.set(ControlMode.PercentOutput, 0);
    // backIndexMotor.set(ControlMode.PercentOutput, 0);
    indexMotors.set(0);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  @Override
  public void simulationPeriodic() {
    // This method will be called once per scheduler run during simulation
  }

} // End of Class