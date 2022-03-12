// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class InAndOut2 extends SubsystemBase {
  private TalonSRX extendMotor = new TalonSRX(Constants.MOTOR_SHINTAKE_EXTENDER_ID);

  /** Creates a new InAndOut2. */

  public InAndOut2() {}

  @Override
  public void periodic() {
    // This method will be called once per scheduler run

  }
  // This method is called when 
  public void InAndOutExtend(double speed) {
    extendMotor.set(ControlMode.PercentOutput, speed);
  }

  /**
   * 
   * @return true if the FWD limit swithc is closed
   * See also: https://docs.ctre-phoenix.com/en/stable/ch13_MC.html?highlight=limit%20switch#limit-switch-as-digital-inputs
   */
  public boolean getFWDLimitState() {
    return extendMotor.getSensorCollection().isFwdLimitSwitchClosed();
  }

  /**
   * 
   * @return true if the FWD limit swithc is closed
   * See also: https://docs.ctre-phoenix.com/en/stable/ch13_MC.html?highlight=limit%20switch#limit-switch-as-digital-inputs
   */
  public boolean getRevLimitState() {
    return extendMotor.getSensorCollection().isRevLimitSwitchClosed();
  }

}
