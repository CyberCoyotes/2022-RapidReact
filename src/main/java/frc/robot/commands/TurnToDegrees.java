// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Drivetrain;

public class TurnToDegrees extends CommandBase {
  
  private Drivetrain m_drivetrain;
  private double m_target_angle;
  private double m_speed;
  /** Creates a new TurnToDegrees. */
  public TurnToDegrees(Drivetrain drivetrain, double target_angle) {
    m_drivetrain = drivetrain;
    m_target_angle = target_angle;
    m_speed = 0.7; //TODO Set default turn speed
  }

  public TurnToDegrees(Drivetrain drivetrain, double target_angle, double speed) {
    m_drivetrain = drivetrain;
    m_target_angle = target_angle;
    m_speed = speed;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    m_drivetrain.drive(
                ChassisSpeeds.fromFieldRelativeSpeeds(
                        0,
                        0,
                        Math.signum(m_target_angle - m_drivetrain.getRawRoation())*m_speed, //TODO: This might need a negative
                        m_drivetrain.getGyroscopeRotation()
                )
        );
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_drivetrain.drive(new ChassisSpeeds(0.0, 0.0, 0.0));
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return Math.abs(m_target_angle - m_drivetrain.getRawRoation()) < 1.0; //1.0: Error threshold
  }
}
