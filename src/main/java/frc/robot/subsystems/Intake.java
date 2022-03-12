// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Intake extends SubsystemBase {

  // Motor Controller Objects
  private VictorSPX intakeMotor = new VictorSPX(Constants.MOTOR_INTAKE_ID);

  /** Creates a new Intake. */
  public Intake() {}

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public void spinIntakeWheel(double speed) {
    intakeMotor.set(ControlMode.PercentOutput, speed);
  }
}
