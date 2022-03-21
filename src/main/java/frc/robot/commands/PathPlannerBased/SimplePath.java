// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.PathPlannerBased;

import java.util.List;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.controller.ProfiledPIDController;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.trajectory.Trajectory;
import edu.wpi.first.math.trajectory.TrajectoryConfig;
import edu.wpi.first.math.trajectory.TrajectoryGenerator;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.SwerveControllerCommand;
import frc.robot.Constants;
import frc.robot.subsystems.Drivetrain;

public class SimplePath extends CommandBase {
  /** Creates a new SimplePath. */
  private final Drivetrain autoDrive = new Drivetrain();

   // Configure default commands
   /** 
   autoDrive.setDefaultCommand(
    // The left stick controls translation of the robot.
    // Turning is controlled by the X axis of the right stick.
    new RunCommand(
        () ->
            m_robotDrive.drive(
                m_driverController.getLeftY(),
                m_driverController.getLeftX(),
                m_driverController.getRightX(),
                false),
        autoDrive));
}
*/

  public SimplePath(Drivetrain drivetrain) {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(drivetrain);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
      TrajectoryConfig config =
          new TrajectoryConfig(
                  // Constants.kMaxSpeedMetersPerSecond,
                  Drivetrain.MAX_VELOCITY_METERS_PER_SECOND,
                  // Constants.kMaxAccelerationMetersPerSecondSquared)
                  Drivetrain.MAX_ANGULAR_VELOCITY_RADIANS_PER_SECOND);
                  // setKinematics(Constants.m_kinematics);
              
  
      // An example trajectory to follow.  All units in meters.
      Trajectory exampleTrajectory =
          TrajectoryGenerator.generateTrajectory(
              // Start at the origin facing the +X direction
              new Pose2d(0, 0, new Rotation2d(0)),
              // Pass through these two interior waypoints, making an 's' curve path
              List.of(new Translation2d(1, 1), new Translation2d(2, -1)),
              // End 3 meters straight ahead of where we started, facing forward
              new Pose2d(3, 0, new Rotation2d(0)),
              config);
  
      var thetaController =
          new ProfiledPIDController(
              Constants.kPThetaController, 0, 0, Constants.kThetaControllerConstraints);
      thetaController.enableContinuousInput(-Math.PI, Math.PI);
  
      SwerveControllerCommand swerveControllerCommand =
          new SwerveControllerCommand(
              exampleTrajectory,
              autoDrive::getPose, // Functional interface to feed supplier
              Constants.m_kinematics,
  
              // Position controllers
              new PIDController(Constants.kPXController, 0, 0),
              new PIDController(Constants.kPYController, 0, 0),
              thetaController,
              autoDrive::setModuleStates,
              autoDrive);
  
      // Reset odometry to the starting pose of the trajectory.
    autoDrive.resetOdometry(exampleTrajectory.getInitialPose());
  
      // Run path following command, then stop at the end.
   // return swerveControllerCommand.andThen(() -> m_robotDrive.drive(0, 0, 0, false));
return;

    }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
