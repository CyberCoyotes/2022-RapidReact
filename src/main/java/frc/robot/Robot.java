// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.util.net.PortForwarder;

// USB Camera Imports
// import edu.wpi.first.cameraserver.CameraServer;
// import edu.wpi.first.cscore.UsbCamera;
// import edu.wpi.first.cscore.VideoMode.PixelFormat;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.GenericHID.RumbleType;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import frc.robot.Constants.VisionRange;

/**
 * The VM is configured to automatically run this class, and to call the functions corresponding to
 * each mode, as described in the TimedRobot documentation. If you change the name of this class or
 * the package after creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
  private Command m_autonomousCommand;

  private RobotContainer m_robotContainer;

  NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight-back");
  NetworkTableEntry tx = table.getEntry("tx");
  NetworkTableEntry ty = table.getEntry("ty");
  NetworkTableEntry ta = table.getEntry("ta");

  private final XboxController driverController = new XboxController(0);

  /**
   * This function is run when the robot is first started up and should be used for any
   * initialization code.
   */
  @Override
  public void robotInit() {
    // Instantiate our RobotContainer.  This will perform all our button bindings, and put our
    // autonomous chooser on the dashboard.
    m_robotContainer = new RobotContainer();


    //add USB webcam view to dashboard
    /** Not needed this season 
    UsbCamera camera = CameraServer.startAutomaticCapture();
    // sets camera to MJPEG format, 720p resloution and 30 FPS drop resolution if over bandwidth cap
    camera.setVideoMode(PixelFormat.kMJPEG, 360, 240, 30);
    camera.setExposureManual(10);
    camera.setWhiteBalanceManual(50);
    */

  // Not entirely sure if this is the correct java file 

  // Make sure you only configure port forwarding once in your robot code.
  // Do not place these function calls in any periodic functions
  PortForwarder.add(5800, "limelight-back .local", 5800);
  PortForwarder.add(5801, "limelight-back .local", 5801);
  PortForwarder.add(5802, "limelight-back .local", 5802);
  PortForwarder.add(5803, "limelight-back .local", 5803);
  PortForwarder.add(5804, "limelight-back .local", 5804);
  PortForwarder.add(5805, "limelight-back .local", 5805);

  }

  /**
   * This function is called every robot packet, no matter the mode. Use this for items like
   * diagnostics that you want ran during disabled, autonomous, teleoperated and test.
   *
   * <p>This runs after the mode specific periodic functions, but before LiveWindow and
   * SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() {
    // Runs the Scheduler.  This is responsible for polling buttons, adding newly-scheduled
    // commands, running already-scheduled commands, removing finished or interrupted commands,
    // and running subsystem periodic() methods.  This must be called from the robot's periodic
    // block in order for anything in the Command-based framework to work.
    CommandScheduler.getInstance().run();
    m_robotContainer.debugMethod();

    

    

  } // End of robotPeriodic

  /** This function is called once each time the robot enters Disabled mode. */
  @Override
  public void disabledInit() {}

  @Override
  public void disabledPeriodic() {
    
  }

  /** This autonomous runs the autonomous command selected by your {@link RobotContainer} class. */
  @Override
  public void autonomousInit() {
    m_autonomousCommand = m_robotContainer.getAutonomousCommand();

    // schedule the autonomous command (example)
    if (m_autonomousCommand != null) {
      m_autonomousCommand.schedule();
    }

    
    
  }

  /** This function is called periodically during autonomous. */
  @Override
  public void autonomousPeriodic() {
    driverController.setRumble(RumbleType.kLeftRumble, 0.0);
    driverController.setRumble(RumbleType.kRightRumble, 0.0);
  }

  @Override
  public void teleopInit() {
    // This makes sure that the autonomous stops running when
    // teleop starts running. If you want the autonomous to
    // continue until interrupted by another command, remove
    // this line or comment it out.
    if (m_autonomousCommand != null) {
      m_autonomousCommand.cancel();
    }
  }

  /** This function is called periodically during operator control. */
  @Override
  public void teleopPeriodic() {
    double TX = tx.getDouble(0.0);
    double TY = ty.getDouble(0.0);
    double area = ta.getDouble(0.0);

    boolean targetLock = (VisionRange.txMin< TX && TX < VisionRange.txMax) && (VisionRange.tyMin< TY && TY < VisionRange.tyMax);
    
    SmartDashboard.putNumber(("tX"), TX);
    SmartDashboard.putNumber(("tY"), TY);
    SmartDashboard.putNumber("Area", area);
    SmartDashboard.putBoolean("Target Status", targetLock);
    
    // Vision & haptic feedback that vibrates when targeting x-value (left to right) is within shooting range.
    if (TX >= VisionRange.txMin && TX <= VisionRange.txMax && TX !=0) {
      driverController.setRumble(RumbleType.kLeftRumble, 1.0);
      driverController.setRumble(RumbleType.kRightRumble, 1.0);

    } 
    else {
      driverController.setRumble(RumbleType.kLeftRumble, 0.0);
      driverController.setRumble(RumbleType.kRightRumble, 0.0);
    } 
  }

  @Override
  public void testInit() {
    // Cancels all running commands at the start of test mode.
    CommandScheduler.getInstance().cancelAll();
  }

  /** This function is called periodically during test mode. */
  @Override
  public void testPeriodic() {}
}
