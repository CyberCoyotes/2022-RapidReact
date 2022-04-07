package frc.robot.commands;

import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Drivetrain;

import java.util.function.DoubleSupplier;

public class DriveCommandPlus extends CommandBase {
    private final Drivetrain m_drivetrain;

    private final DoubleSupplier m_translationXSupplier;
    private final DoubleSupplier m_translationYSupplier;
    private DoubleSupplier targetAngle;
    private double speed;

    public DriveCommandPlus(Drivetrain drivetrain, 
                            DoubleSupplier translationXSupplier,
                            DoubleSupplier translationYSupplier,
                            DoubleSupplier targetAngle,
                            double speed)
        {
            this.m_drivetrain = drivetrain;
            this.m_translationXSupplier = translationXSupplier;
            this.m_translationYSupplier = translationYSupplier;
            this.targetAngle = targetAngle;
            this.speed = speed; 
            addRequirements(drivetrain);
        }

    private double GetRotationSpeed() {
        if (Math.abs( targetAngle.getAsDouble() - m_drivetrain.getRawRoation()) < 1.0){
            return 0;
        }
        else{
            return speed;
        }
    }
    @Override
    public void execute() {
        // You can use `new ChassisSpeeds(...)` for robot-oriented movement instead of field-oriented movement
        m_drivetrain.drive(
                ChassisSpeeds.fromFieldRelativeSpeeds(
                        m_translationXSupplier.getAsDouble(),
                        m_translationYSupplier.getAsDouble(),
                        Math.signum(targetAngle.getAsDouble() - m_drivetrain.getRawRoation())*GetRotationSpeed(),//how?
                        m_drivetrain.getGyroscopeRotation()
                )
        );
    }

   

    @Override
    public void end(boolean interrupted) {
        m_drivetrain.drive(new ChassisSpeeds(0.0, 0.0, 0.0));
    }
}
