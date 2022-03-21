// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

/** FRC 3603 Robot Container
 *
 */
package frc.robot;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import static edu.wpi.first.wpilibj.XboxController.Button;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
// Subsystem imports
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Index;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Launcher;
import frc.robot.subsystems.Lift;

// Command imports
import frc.robot.commands.DriveCommand;
import frc.robot.commands.IndexSpeed;
import frc.robot.commands.IntakeSpeed;
import frc.robot.commands.XLaunch;
import frc.robot.commands.Launcher.LaunchSpeed;
import frc.robot.commands.Lift.AutoLiftCommandBar1;
import frc.robot.commands.Lift.AutoLiftCommandBar2;
import frc.robot.commands.Lift.LiftCommand;
import frc.robot.commands.auton.Ball2PlusAuton;
import frc.robot.commands.auton.Ball2Auton;
import frc.robot.commands.auton.Ball3Auton;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */

public class RobotContainer {
 
  // Main driver controller
  private final XboxController driverController = new XboxController(0);
  // Second operator controller
  private final XboxController operatorController = new XboxController(1);

  // The robot's subsystems and commands are defined here...
  //// Drivetrain
  private final Drivetrain m_drivetrain = new Drivetrain();
  
  //// Intake
  private final Intake intakeMotor = new Intake();
  
  //// Launcher
  private final Index indexMotors = new Index();
  private final Launcher launcher = new Launcher();
  
  //// Lift 
  private final Lift liftMotors = new Lift();
  // private final Lift rightLiftMotor = new Lift();
  // private final Lift leftLiftMotor = new Lift();
  // private final LiftPivot liftPivotMotors = new LiftPivot();

  //// Limelight
  private final Limelight limelight = new Limelight();
  
  //// Autonomous
  SendableChooser<Command> autonChooser = new SendableChooser<>();

  private final Command driveCommand; 
  
  // The container for the robot. Contains subsystems, OI devices, and commands.
  public RobotContainer() {

    // Configure the button bindings
    configureButtonBindings();

    // Set up the default command for the drivetrain.
    // The controls are for field-oriented driving:
    // Left stick Y axis -> forward and backwards movement
    // Left stick X axis -> left and right movement
    // Right stick X axis -> rotation
    driveCommand = new DriveCommand(
      m_drivetrain,
      () -> -modifyAxis(driverController.getLeftY()) * Drivetrain.MAX_VELOCITY_METERS_PER_SECOND,
      () -> -modifyAxis(driverController.getLeftX()) * Drivetrain.MAX_VELOCITY_METERS_PER_SECOND,
      () -> -modifyAxis(driverController.getRightX()) * Drivetrain.MAX_ANGULAR_VELOCITY_RADIANS_PER_SECOND);
    
    m_drivetrain.setDefaultCommand(driveCommand);
    
    // AUTONOMOUS chooser

    // Launches high goal inside tarmac, drives out with launch sequence operating
    //autonChooser.addOption("1 Ball & Stay",
      //new CG_1Ball(indexMotors, intakeMotor, launcher));
    
    //autonChooser.addOption("1 Ball & Pickup 2nd",
       //new CG_1BallPLUS(m_drivetrain, indexMotors, intakeMotor, launcher));
    
    autonChooser.addOption("2 Ball + Drive straight",
      new Ball2Auton(m_drivetrain, indexMotors, intakeMotor, launcher));
    
    autonChooser.addOption("2 Ball + Pickup 3rd",
      new Ball2PlusAuton(m_drivetrain, indexMotors, intakeMotor, launcher));
   
    autonChooser.setDefaultOption("3 Ball",
      new Ball3Auton(m_drivetrain, indexMotors, intakeMotor, launcher));
   
    //autonChooser.addOption("DEV TESTING",
      // new CG_DEV(m_drivetrain, indexMotors, intakeMotor, launcher));

    // Puts the chooser on the dashboard
    Shuffleboard.getTab("Auton").add(autonChooser).withSize(2, 4);
  
    
    // DEBUGGING CODE:
    // System.out.println("subsystem requirements for autonShortDrive");
    // autonShortDrive.getRequirements().forEach((x) -> System.out.println(x));

    // System.out.println("subsystem requirements for DriveCommand");
    // driveCommand.getRequirements().forEach((x) -> System.out.println(x));
  }

  public void debugMethod () {
    // SmartDashboard.putBoolean("Short Drive", autonShortDrive.isScheduled());
    // SmartDashboard.putBoolean("DriveCommand", driveCommand.isScheduled());
  }

  private void configureButtonBindings() {
    /// Declaring buttons on driver controller
    final JoystickButton d_backButton = new JoystickButton(driverController, Button.kBack.value);
    final JoystickButton d_ButtonA = new JoystickButton(driverController, Button.kA.value);
    final JoystickButton d_ButtonB = new JoystickButton(driverController, Button.kB.value);
    final JoystickButton d_ButtonX = new JoystickButton(driverController, Button.kX.value);
    final JoystickButton d_ButtonY = new JoystickButton(driverController, Button.kY.value);
    final JoystickButton d_RightBumper = new JoystickButton(driverController, Button.kRightBumper.value);
    final JoystickButton d_LeftBumper = new JoystickButton(driverController, Button.kLeftBumper.value);
    final JoystickButton d_Start = new JoystickButton(driverController, Button.kStart.value);
    // Declaring buttons on the operator controller
    final JoystickButton op_ButtonA = new JoystickButton(operatorController, Button.kA.value);
    // Not being used. final JoystickButton op_ButtonB = new JoystickButton(operatorController, Button.kB.value);
    // final JoystickButton op_ButtonX = new JoystickButton(operatorController, Button.kX.value);
    final JoystickButton op_ButtonY = new JoystickButton(operatorController, Button.kY.value);
    // final JoystickButton op_StartButton = new JoystickButton(operatorController, Button.kStart.value);
    // final JoystickButton op_BackButton = new JoystickButton(operatorController, Button.kBack.value);
    final JoystickButton op_RightBumper = new JoystickButton(operatorController, Button.kRightBumper.value);
    final JoystickButton op_LeftBumper = new JoystickButton(operatorController, Button.kLeftBumper.value);

    // Defining the actions associated with buttons

    // DRIVER Controller button commands

    
    //d_Start.whenPressed(new xmode(m_drivetrain));

    // Resets the gyroscope to 0 degrees when back button is pressed
    d_backButton.whenPressed(m_drivetrain::zeroGyroscope);

    // Hold Start to manually Advance cargo to the launcher, release to stop motors
    // d_Start.whenPressed(new IndexSpeed(indexMotors, 0.5));
    // d_Start.whenReleased(new IndexSpeed(indexMotors, 0));

      /**  LOW HOOP UP CLOSE LAUNCH SEQUENCE
       when A is held, run Launch motors by themselves for a second, then run Launch and Index motors for 0.5 seconds,
       then finally run all 3 motors at once. release to stop all motors */
      d_ButtonA.whenPressed(
       new SequentialCommandGroup(
        new LaunchSpeed(launcher, 0.20, 0.20).withTimeout(1),
          new SequentialCommandGroup(
            new LaunchSpeed(launcher, 0.20, 0.20).withTimeout(0.5).alongWith(
              new IndexSpeed(indexMotors, 0.5).withTimeout(0.5)),
                new ParallelCommandGroup (
                  new LaunchSpeed(launcher, 0.25, 0.25),
                  new IntakeSpeed(intakeMotor, 0.5),
                  new IndexSpeed(indexMotors, 0.5)))
       )
     );
      //stops all 3 motors when A button released
      d_ButtonA.whenReleased(new ParallelCommandGroup(
        new IntakeSpeed(intakeMotor, 0.0),
        new IndexSpeed(indexMotors, 0.0),
        new LaunchSpeed(launcher, 0.0, 0.0))
      );

     /**  HIGH HOOP EDGE OF TARMAC LAUNCH SEQUENCE
       when Y is held, run Launch motors by themselves for 0.75 seconds, then run Launch and Index motors for 0.25 seconds,
       then finally run all 3 motors at once. release button to stop all motors */
    d_ButtonY.whenPressed(    
    new SequentialCommandGroup(
        new LaunchSpeed(launcher, 0.35, 0.40).withTimeout(0.75),
          new SequentialCommandGroup(
            new LaunchSpeed(launcher, 0.35, 0.40).withTimeout(0.25).alongWith(
              new IndexSpeed(indexMotors, 0.5).withTimeout(0.5)),
            new ParallelCommandGroup (
              new LaunchSpeed(launcher, 0.36, 0.42),
              new IntakeSpeed(intakeMotor, 0.5),
              new IndexSpeed(indexMotors, 0.5)))
      ));
      //stops all 3 motors when Y button released
      d_ButtonY.whenReleased(new ParallelCommandGroup(
        new IntakeSpeed(intakeMotor, 0.0),
        new IndexSpeed(indexMotors, 0.0),
        new LaunchSpeed(launcher, 0.0, 0.0))

      );

      // FIXME missing whenReleased
      d_ButtonX.whenPressed(new XLaunch(m_drivetrain, indexMotors, intakeMotor, launcher));

    // Hold right bumper to manually Reverses cargo from the field, release to stop motors
    d_RightBumper.whenPressed(new IntakeSpeed(intakeMotor, -0.5));
    d_RightBumper.whenReleased(new IntakeSpeed(intakeMotor, 0.0));

    // Hold left bumper to manually Intake cargo back to the field, release to stop motors
    d_LeftBumper.whenPressed(new IntakeSpeed(intakeMotor, 0.5));
    d_LeftBumper.whenReleased(new IntakeSpeed(intakeMotor, 0.0));

    // Hold Start to manually Advance cargo to the launcher, release to stop motors
    d_Start.whenPressed(new XLaunch(m_drivetrain, indexMotors, intakeMotor, launcher));
    

    // Hold X to set launch speed according to Limelight
    // d_ButtonX.whenPressed(new LaunchAutomatic(launcher, limelight));
    // d_ButtonX.whenPressed(new LaunchSpeed(launcher, 0, 0));

    //Hold B to drive at slower speed, release to drive normal
    d_ButtonB.whenPressed(new DriveCommand(
      m_drivetrain,
      () -> -modifyAxis(driverController.getLeftY()) * Drivetrain.MAX_VELOCITY_METERS_PER_SECOND / 6,
      () -> -modifyAxis(driverController.getLeftX()) * Drivetrain.MAX_VELOCITY_METERS_PER_SECOND / 6,
      () -> -modifyAxis(driverController.getRightX()) * Drivetrain.MAX_ANGULAR_VELOCITY_RADIANS_PER_SECOND / 6
    ));
    d_ButtonB.whenReleased(new DriveCommand(
      m_drivetrain,
      () -> -modifyAxis(driverController.getLeftY()) * Drivetrain.MAX_VELOCITY_METERS_PER_SECOND,
      () -> -modifyAxis(driverController.getLeftX()) * Drivetrain.MAX_VELOCITY_METERS_PER_SECOND,
      () -> -modifyAxis(driverController.getRightX()) * Drivetrain.MAX_ANGULAR_VELOCITY_RADIANS_PER_SECOND
    ));


    // OPERATOR Controller commands
    
    // Press the Start button to make an x-lockout when shooting
    // op_StartButton.whenPressed(new xmode(m_drivetrain));
    // op_StartButton.whenReleased(new xmode(m_drivetrain));

    // press Start Button to auto lower both climbing arms to the encoder value of when the locking arms engage on bar #2
    //  op_StartButton.whenPressed(new LockLiftCommandBar2(liftMotors, -0.5));

    // press Back Button to auto lower both climbing arms to the encoder value of when the locking arms engage on bar #1
    //op_BackButton.whenPressed(new LockLiftCommandBar1(liftMotors, -0.5));

    //Hold X to rotationally align the robot (driver still has control of translational motion)
    /**
    op_ButtonX.whenPressed(new DriveCommand(
        m_drivetrain,
          () -> -modifyAxis(driverController.getLeftY()) * Drivetrain.MAX_VELOCITY_METERS_PER_SECOND,
          () -> -modifyAxis(driverController.getLeftX()) * Drivetrain.MAX_VELOCITY_METERS_PER_SECOND,
          () -> {return limelight.getX() * Constants.kPThetaLimelightController;}
      ));
      op_ButtonX.whenReleased(new DriveCommand(
          m_drivetrain,
          () -> -modifyAxis(driverController.getLeftY()) * Drivetrain.MAX_VELOCITY_METERS_PER_SECOND,
          () -> -modifyAxis(driverController.getLeftX()) * Drivetrain.MAX_VELOCITY_METERS_PER_SECOND,
          () -> -modifyAxis(driverController.getRightX()) * Drivetrain.MAX_ANGULAR_VELOCITY_RADIANS_PER_SECOND
      ));
      */

    // hold left bumper to manually raise both climbing arms, release to stop motors
    op_LeftBumper.whenPressed(new LiftCommand(liftMotors, 0.5));
    op_LeftBumper.whenReleased(new LiftCommand(liftMotors, 0.0));

    // hold right bumper to manually lower both climbing arms, release to stop motors
    op_RightBumper.whenPressed(new LiftCommand(liftMotors, -0.5));
    op_RightBumper.whenReleased(new LiftCommand(liftMotors, 0.0));

    // press A to auto raise both climbing arms to the encoder value of bar #1
    op_ButtonA.whenPressed(new AutoLiftCommandBar1(liftMotors, 0.5));

    // press Y to auto raise both climbing arms to encoder value of bar #2
    op_ButtonY.whenPressed(new AutoLiftCommandBar2(liftMotors, 0.5));
    
  } // End of operator controller

  private static double deadband(double value, double deadband) {
    if (Math.abs(value) > deadband) {
      if (value > 0.0) {
        return (value - deadband) / (1.0 - deadband);
      } else {
        return (value + deadband) / (1.0 - deadband);
      }
    } else {
      return 0.0;
    }
  }

  private static double modifyAxis(double value) {
    // Deadband
    value = deadband(value, 0.05);

    // Square the axis
    value = Math.copySign(value * value, value);

    return value;
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   * @return the command to run in autonomous
   */


  public Command getAutonomousCommand() {
//<>purpose
    //sequential(deadline(wait, sequential<launching>, xmode), parallel<stop all motors>)

    return autonChooser.getSelected();
    

  }; // end of getAutonomusCommand()

} // End of class