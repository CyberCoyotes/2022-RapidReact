// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;

import frc.robot.subsystems.Drivetrain;

public class ResetGyro extends CommandBase {
	private final Drivetrain drivetrain;

	/**
	 * @param subsystem The drive subsystem this command will run on.
	 */

	public ResetGyro(Drivetrain subsystem) {
	  drivetrain = subsystem;
	  addRequirements(drivetrain);
	}
  
	@Override
	public void end(boolean interrupted) {
	  drivetrain.resetGyro();
	}
  
	@Override
	public boolean isFinished() {
	  return true;
	}
  }