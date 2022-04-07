
// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;
import edu.wpi.first.wpilibj.shuffleboard.BuiltInWidgets;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;

public class Launcher extends SubsystemBase {

  // Falcon 500 is controlled by TalonFX
  // These are the two motors for launching the cargo

  private final TalonFX backLauncherMotor = new TalonFX(Constants.Launcher.BACK_LAUNCHER_MOTOR);
  private final TalonFX frontLauncherMotor = new TalonFX(Constants.Launcher.FRONT_LAUNCHER_MOTOR);
 
  public Launcher() {
 
    backLauncherMotor.setInverted(true);
    backLauncherMotor.setNeutralMode(NeutralMode.Coast);
    frontLauncherMotor.setNeutralMode(NeutralMode.Coast);
    frontLauncherMotor.setInverted(true);

    /** 
    backLauncherMotor.configNominalOutputForward(0, Constants.kTimeoutMs);
    frontLauncherMotor.configNominalOutputForward(0, Constants.kTimeoutMs);
    backLauncherMotor.configNominalOutputReverse(0, Constants.kTimeoutMs);
    frontLauncherMotor.configNominalOutputReverse(0, Constants.kTimeoutMs);
    */

    /** 
    backLauncherMotor.getSelectedSensorVelocity();
    System.out.println(backLauncherMotor);
    frontLauncherMotor.getSelectedSensorVelocity();
    System.out.println(frontLauncherMotor);
    */

    // TODO See if this outputs to Shuffleboard properly
    Shuffleboard.getTab("Launcher")
      .add("Back Velocity", backLauncherMotor.getSelectedSensorVelocity())
      .withWidget(BuiltInWidgets.kNumberSlider)
      .getEntry();

    Shuffleboard.getTab("Launcher") 
      .add("Front Velocity", frontLauncherMotor.getSelectedSensorVelocity())
      .withWidget(BuiltInWidgets.kNumberSlider)
      .getEntry();
  }

  /* Launches the Cargo with speed set for low hub
  *  Eventually the absolute value could potentially be replaced with sensor-driven values
  */

  public static double speedFront;
  public static double speedBack;


  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  // Set VARIABLE goal speed
  public void setLauncherSpeed(double speedFront, double speedBack) {
    frontLauncherMotor.set(ControlMode.PercentOutput, speedFront);
    backLauncherMotor.set(ControlMode.PercentOutput, speedBack);
  }
  
  // Set Pre Launch Speed
  public void setPreLaunch(){
    frontLauncherMotor.set(ControlMode.PercentOutput, 0.30);
    backLauncherMotor.set(ControlMode.PercentOutput, 0.35);
  }

  // Set for back bumper on ball spot
  /** public void setExtendedLaunch() {
    frontLauncherMotor.set(ControlMode.PercentOutput, 0.60);
    backLauncherMotor.set(ControlMode.PercentOutput, 0.30);
  }
*/ 
    // Set for bumper on outside tarmac
    public void setLaunch2() {
      frontLauncherMotor.set(ControlMode.PercentOutput, 0.60);
      backLauncherMotor.set(ControlMode.PercentOutput, 0.30);
    }

    // Set for 3rd ball pickup spot
    public void setLaunch3() {
      frontLauncherMotor.set(ControlMode.PercentOutput, 0.68); //0.65 hit the front rim of Hub, changing to 0.68
      backLauncherMotor.set(ControlMode.PercentOutput, 0.30);
    }
  
  // Set HIGH goal speed. Approximate line up is to have robot "split the tarmac tape; half in, half out"
  public void setHighLaunch() {
    frontLauncherMotor.set(ControlMode.PercentOutput, 0.40);
    backLauncherMotor.set(ControlMode.PercentOutput, 0.35);
  }
  
  // Set inside Tarmac speed. Robot lined up approximately with intake bumper at the edge of the tarmac tape
  public void setTarmacLaunch() {
    frontLauncherMotor.set(ControlMode.PercentOutput, 0.36);
    backLauncherMotor.set(ControlMode.PercentOutput, 0.42);
  }

  // Set LOW goal speed
  public void setLowLaunch() {
    backLauncherMotor.set(ControlMode.PercentOutput, 0.20);
    frontLauncherMotor.set(ControlMode.PercentOutput, 0.20);
  }
  
  public void stopLauncher() {
    backLauncherMotor.set(ControlMode.PercentOutput, 0);
    frontLauncherMotor.set(ControlMode.PercentOutput, 0);
  }

  public double getIntakeSpeed() {
    return backLauncherMotor.getMotorOutputPercent(); // add another for Top if desired
  }
  
  @Override
  public void simulationPeriodic() {
    // This method will be called once per scheduler run during simulation
  }


} // End class