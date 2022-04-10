// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.swervedrivespecialties.swervelib.Mk3SwerveModuleHelper;
import com.swervedrivespecialties.swervelib.SdsModuleConfigurations;
import com.swervedrivespecialties.swervelib.SwerveModule;

import edu.wpi.first.math.controller.ProfiledPIDController;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.math.kinematics.SwerveDriveKinematics;
import edu.wpi.first.math.kinematics.SwerveDriveOdometry;
import edu.wpi.first.math.kinematics.SwerveModuleState;
import edu.wpi.first.wpilibj.shuffleboard.BuiltInLayouts;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import static frc.robot.Constants.*;


// Navx imports
import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.SPI;


public class Drivetrain extends SubsystemBase {
  // Added from #5804
  public ProfiledPIDController thetaController =
  new ProfiledPIDController(
      kPThetaController, 0, 0, kThetaControllerConstraints);

  /**
   * The maximum voltage that will be delivered to the drive motors.
   * This can be reduced to cap the robot's maximum speed. Typically, this is useful during initial testing of the robot.
   */
  public static final double MAX_VOLTAGE = 12.0;
  // Measure the drivetrain's maximum velocity or calculate the theoretical.
  //  The formula for calculating the theoretical maximum velocity is:
  //   <Motor free speed RPM> / 60 * <Drive reduction> * <Wheel diameter meters> * pi
  //  By default this value is setup for a Mk3 standard module using Falcon500s to drive.
  //  An example of this constant for a Mk4 L2 module with NEOs to drive is:
  //   5880.0 / 60.0 / SdsModuleConfigurations.MK4_L2.getDriveReduction() * SdsModuleConfigurations.MK4_L2.getWheelDiameter() * Math.PI
  /**
   * The maximum velocity of the robot in meters per second.
   * This is a measure of how fast the robot should be able to drive in a straight line.
   */

  // Our value is 6380, #5804 uses 5000
  public static final double MAX_VELOCITY_METERS_PER_SECOND = 6380.0 / 60.0 * 
          SdsModuleConfigurations.MK3_STANDARD.getDriveReduction() *
          SdsModuleConfigurations.MK3_STANDARD.getWheelDiameter() * Math.PI;
  /**
   * The maximum angular velocity of the robot in radians per second.
   * This is a measure of how fast the robot can rotate in place.
   */
  // Here we calculate the theoretical maximum angular velocity. You can also replace this with a measured amount.
  public static final double MAX_ANGULAR_VELOCITY_RADIANS_PER_SECOND = MAX_VELOCITY_METERS_PER_SECOND /
          Math.hypot(DRIVETRAIN_TRACKWIDTH_METERS / 2.0, DRIVETRAIN_WHEELBASE_METERS / 2.0);
  
  // Added from #5804
  public final SwerveDriveKinematics m_kinematics = new SwerveDriveKinematics(
    // Front left
    new Translation2d(DRIVETRAIN_TRACKWIDTH_METERS / 2.0, DRIVETRAIN_WHEELBASE_METERS / 2.0),
    // Front right
    new Translation2d(DRIVETRAIN_TRACKWIDTH_METERS / 2.0, -DRIVETRAIN_WHEELBASE_METERS / 2.0),
    // Back left
    new Translation2d(-DRIVETRAIN_TRACKWIDTH_METERS / 2.0, DRIVETRAIN_WHEELBASE_METERS / 2.0),
    // Back right
    new Translation2d(-DRIVETRAIN_TRACKWIDTH_METERS / 2.0, -DRIVETRAIN_WHEELBASE_METERS / 2.0)
    );

  public static final double MAX_ACCELERATION_METERS_SECOND_SQUARED = MAX_VELOCITY_METERS_PER_SECOND / 
    Math.hypot(DRIVETRAIN_TRACKWIDTH_METERS / 2.0, DRIVETRAIN_WHEELBASE_METERS / 2.0);

 
  // Test this on our robot
  // The important thing about how you configure your gyroscope is that rotating the robot counter-clockwise should
  // cause the angle reading to increase until it wraps back over to zero.
  
  // NavX connected over MXP
  private final AHRS m_navx = new AHRS(SPI.Port.kMXP, (byte) 200); 

  //creating an odometer for auton
  private final SwerveDriveOdometry odometer = 
    new SwerveDriveOdometry(m_kinematics, new Rotation2d(0));
  
  // These are our modules. We initialize them in the constructor.
  private final SwerveModule m_frontLeftModule;
  private final SwerveModule m_frontRightModule;
  private final SwerveModule m_backLeftModule;
  private final SwerveModule m_backRightModule;
  
  // Added from #5804
  Pose2d targetPose;

  public double target = (getGyroscopeRotation().getDegrees());
  // 

  private ChassisSpeeds m_chassisSpeeds = new ChassisSpeeds(0.0, 0.0, 0.0);

  // Added from #5804
  SwerveDriveOdometry odometry =   // Needed for swerve drive
    new SwerveDriveOdometry(m_kinematics, getGyroscopeRotation());;
  //

  public Drivetrain() {
    ShuffleboardTab driveTab = Shuffleboard.getTab("Drivetrain");
    // Added from #5804
    thetaController.enableContinuousInput(-Math.PI, Math.PI);


    // Setup motor configuration
    m_frontLeftModule = Mk3SwerveModuleHelper.createFalcon500(
            // This parameter is optional, but will allow you to see the current state of the module on the dashboard.
            driveTab.getLayout("Front Left Module", BuiltInLayouts.kList)
                    .withSize(2, 4)
                    .withPosition(0, 0),
            // This can either be STANDARD or FAST depending on your gear configuration
            Mk3SwerveModuleHelper.GearRatio.STANDARD,
            // This is the ID of the drive motor
            FRONT_LEFT_MODULE_DRIVE_MOTOR,
            // This is the ID of the steer motor
            FRONT_LEFT_MODULE_STEER_MOTOR,
            // This is the ID of the steer encoder
            FRONT_LEFT_MODULE_STEER_ENCODER,
            // This is how much the steer encoder is offset from true zero (In our case, zero is facing straight forward)
            FRONT_LEFT_MODULE_STEER_OFFSET
    );

    // We will do the same for the other modules
    m_frontRightModule = Mk3SwerveModuleHelper.createFalcon500(
            driveTab.getLayout("Front Right Module", BuiltInLayouts.kList)
                    .withSize(2, 4)
                    .withPosition(2, 0),
            Mk3SwerveModuleHelper.GearRatio.STANDARD,
            FRONT_RIGHT_MODULE_DRIVE_MOTOR,
            FRONT_RIGHT_MODULE_STEER_MOTOR,
            FRONT_RIGHT_MODULE_STEER_ENCODER,
            FRONT_RIGHT_MODULE_STEER_OFFSET
    );

    m_backLeftModule = Mk3SwerveModuleHelper.createFalcon500(
            driveTab.getLayout("Back Left Module", BuiltInLayouts.kList)
                    .withSize(2, 4)
                    .withPosition(4, 0),
            Mk3SwerveModuleHelper.GearRatio.STANDARD,
            BACK_LEFT_MODULE_DRIVE_MOTOR,
            BACK_LEFT_MODULE_STEER_MOTOR,
            BACK_LEFT_MODULE_STEER_ENCODER,
            BACK_LEFT_MODULE_STEER_OFFSET
    );

    m_backRightModule = Mk3SwerveModuleHelper.createFalcon500(
            driveTab.getLayout("Back Right Module", BuiltInLayouts.kList)
                .withSize(2, 4)
                .withPosition(6, 0),
            Mk3SwerveModuleHelper.GearRatio.STANDARD,
            BACK_RIGHT_MODULE_DRIVE_MOTOR,
            BACK_RIGHT_MODULE_STEER_MOTOR,
            BACK_RIGHT_MODULE_STEER_ENCODER,
            BACK_RIGHT_MODULE_STEER_OFFSET
    );
    
  }
  
  //method to stop motors, used for auton
  /** This block was not in #5804 code
  public void stop() {
        m_frontLeftModule.set(0, 0);
        m_frontRightModule.set(0, 0);
        m_backLeftModule.set(0, 0);
        m_backRightModule.set(0, 0);
    }
    */

  
  public boolean IsMoving()
  {
    return this.m_frontLeftModule.getDriveVelocity() != 0;
  }
  public void resetGyro() {
    m_navx.zeroYaw();
  }

  // Added from #5804
  public double getRawRoation() {
    return m_navx.getRotation2d().getDegrees();
}

  public Rotation2d getGyroscopeRotation() {
    if (m_navx.isMagnetometerCalibrated()) {
      // We will only get valid fused headings if the magnetometer is calibrated
      return Rotation2d.fromDegrees(m_navx.getFusedHeading());
    }

    // We have to invert the angle of the NavX so that rotating the robot counter-clockwise makes the angle increase.
    return Rotation2d.fromDegrees(360.0 - m_navx.getYaw());
  }


  //gets location of robot from odometer
  public Pose2d getPose(){
    return odometer.getPoseMeters();
  }

  //resets odometer to a new location
  public void resetOdometry(Pose2d pose) {
    odometer.resetPosition(pose, getGyroscopeRotation());
  }

  public void drive(ChassisSpeeds chassisSpeeds) {
    m_chassisSpeeds = chassisSpeeds;
  }

  public void setModuleStates(SwerveModuleState[] desiredStates) {
      // Changed to states -> desiredStates  
      // Ensures we aren't going past the speed that we should be going
        SwerveDriveKinematics.desaturateWheelSpeeds(desiredStates, MAX_VELOCITY_METERS_PER_SECOND);
        odometry.update(getGyroscopeRotation(), desiredStates);
        
        //these seem to maintain the same movement as the robot continues
        //This part is for AUTON
        m_frontLeftModule.set(desiredStates[0].speedMetersPerSecond / MAX_VELOCITY_METERS_PER_SECOND * MAX_VOLTAGE,
          desiredStates[0].angle.getRadians());
        m_frontRightModule.set(desiredStates[1].speedMetersPerSecond / MAX_VELOCITY_METERS_PER_SECOND * MAX_VOLTAGE,
          desiredStates[1].angle.getRadians());
        m_backLeftModule.set(desiredStates[2].speedMetersPerSecond / MAX_VELOCITY_METERS_PER_SECOND * MAX_VOLTAGE,
          desiredStates[2].angle.getRadians());
        m_backRightModule.set(desiredStates[3].speedMetersPerSecond / MAX_VELOCITY_METERS_PER_SECOND * MAX_VOLTAGE, 
          desiredStates[3].angle.getRadians());
        
        desiredStates[0].speedMetersPerSecond = Math.abs(m_frontLeftModule.getDriveVelocity());
        desiredStates[1].speedMetersPerSecond = Math.abs(m_frontRightModule.getDriveVelocity());
        desiredStates[2].speedMetersPerSecond = Math.abs(m_backLeftModule.getDriveVelocity());
        desiredStates[3].speedMetersPerSecond = Math.abs(m_backRightModule.getDriveVelocity());     

      // Appeared to be crashing code at Saturday practice
      // FIXME
       ShuffleboardTab driveTab = Shuffleboard.getTab("Drivetrain");
          driveTab.add("Current X", getPose().getX()); 
          driveTab.add("Current Y", getPose().getY()); 
          driveTab.add("Auton Angle", getPose().getRotation().getDegrees()); 
      // ShuffleboardTab driveTab = Shuffleboard.getTab("Drive Data");
      // ORIGINAL SmartDashboard.putNumber("Raw Angle", getRawRoation());
        // driveTab.add("Raw Angle", getRawRoation());
        // driveTab.add("Current Angle SDS", getGyroscopeRotation().getDegrees()); // From SDS default code
        // .withWidget(BuiltInWidgets.kGyro
        // SmartDashboard.putNumber("Current Angle", getPose().getRotation().getDegrees()); 
        // driveTab.add("Current X", getPose().getX()); 
        // driveTab.add("Current Y", getPose().getY());


  } // end of setModulesStates

        // https://github.com/5804/rapidReact2022Alpha/blob/master/src/main/java/frc/robot/subsystems/DrivetrainSubsystem.java

  @Override
  public void periodic() {

    //defining states - Repeatedly update
    /* Displays ChassisSpeed in Meters per Second  
    System.out.print("X m/s: " + m_chassisSpeeds.vxMetersPerSecond);
    System.out.print("Y m/s: " + m_chassisSpeeds.vyMetersPerSecond);
    System.out.println("Rot: " + m_chassisSpeeds.omegaRadiansPerSecond);
    **/

    SwerveModuleState[] states = m_kinematics.toSwerveModuleStates(m_chassisSpeeds);
    // removed a second param of MAX_VELOCITY_METERS_PER_SECOND, 
    // and changed the first param from itself(states) to the chassisspeeds object 
    
    // Updates the odometer constantly - removing for testing. Added back in
    odometer.update(getGyroscopeRotation(), states);
   
    //This part is for TELEOP
    SwerveDriveKinematics.desaturateWheelSpeeds(states, MAX_VELOCITY_METERS_PER_SECOND);
    m_frontLeftModule.set(states[0].speedMetersPerSecond / MAX_VELOCITY_METERS_PER_SECOND * MAX_VOLTAGE,
      states[0].angle.getRadians());
    m_frontRightModule.set(states[1].speedMetersPerSecond / MAX_VELOCITY_METERS_PER_SECOND * MAX_VOLTAGE,
      states[1].angle.getRadians());
    m_backLeftModule.set(states[2].speedMetersPerSecond / MAX_VELOCITY_METERS_PER_SECOND * MAX_VOLTAGE,
      states[2].angle.getRadians());
    m_backRightModule.set(states[3].speedMetersPerSecond / MAX_VELOCITY_METERS_PER_SECOND * MAX_VOLTAGE,
      states[3].angle.getRadians());

 
      

    } // End of periodic

} // END of class
