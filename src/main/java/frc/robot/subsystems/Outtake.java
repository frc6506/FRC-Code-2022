// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Outtake extends SubsystemBase {
  // Motor Controller Objects
  private CANSparkMax flyWheelMotor =
      new CANSparkMax(Constants.MOTOR_FLYWHEEL_ID, MotorType.kBrushless);
  private VictorSPX feedWheelMotor = new VictorSPX(Constants.MOTOR_FEEDWHEEL_ID);

  /** Creates a new Outtake. */
  public Outtake() {}

  /**
   * @param speed, -1.00 <= speed, 1.00
   */
  public void spinFeedWheel(double speed) {
    feedWheelMotor.set(ControlMode.PercentOutput, speed);
  }

  /**
   * @param speed, decimal percent [-1.0, 1.0]
   */
  public void spinFlywheel(double speed) {
    flyWheelMotor.set(speed);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
