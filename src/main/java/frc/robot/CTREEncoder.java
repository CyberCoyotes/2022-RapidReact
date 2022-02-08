package frc.robot;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.kinematics.SwerveModuleState;

public class CTREEncoder {

    WPI_TalonFX talon;
    boolean invert;
    double distancePerPulse;

    public CTREEncoder(WPI_TalonFX _talon, boolean invert, double distancePerPulse) {
        talon = _talon;
        talon.getSensorCollection();
        this.invert = invert;
        this.distancePerPulse = distancePerPulse;
    }

    public double getRate() {
        return talon.getSelectedSensorVelocity() * distancePerPulse; //this doesn't smell right
    }

    public double getDistance() {
        return talon.getSelectedSensorPosition() * distancePerPulse;
    }

    public double get() {
        return invert ? -talon.getSelectedSensorPosition() : talon.getSelectedSensorPosition();
    }

    public double getVelocity() {
        return invert ? -talon.getSelectedSensorVelocity() : talon.getSelectedSensorVelocity();
    }

    public void setPosition(double pos) {
        talon.setSelectedSensorPosition(pos);
    }
    

    public void reset() {
        setPosition(0);
    }

//added getState class from example code
public SwerveModuleState getState() {
    return new SwerveModuleState(getVelocity(), new Rotation2d(get()));
}

}