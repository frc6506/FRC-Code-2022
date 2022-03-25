// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Sled extends SubsystemBase {
  // Motor Controller Objects
  private TalonSRX sledMotorMain = new TalonSRX(Constants.MOTOR_CLIMBER_SLED_LEFT_ID);
  private VictorSPX sledMotorRight = new VictorSPX(Constants.MOTOR_CLIMBER_SLED_RIGHT_ID);

  /** Creates a new Sled. */
  public Sled() {
    // Set second one to follow, non inverted
    //sledMotorRight.follow(sledMotorMain);
    //sledMotorRight.setInverted(true);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  /**
   * Slidey slidey sled! - BSI
   *
   * @param speed decimal percent [-1.0, 1.0] FWD and Rev Limit switch enforced
   */
  public void slideSled(double speed) {
    sledMotorMain.set(ControlMode.PercentOutput, speed * 0.75); // One side was faster, not sure how this coresponds to this controllers
    sledMotorRight.set(ControlMode.PercentOutput, -1 * speed);
  }

  /**
   * @return true if the FWD limit swithc is closed See also:
   *     https://docs.ctre-phoenix.com/en/stable/ch13_MC.html?highlight=limit%20switch#limit-switch-as-digital-inputs
   */
  public boolean getFWDLimitState() {
    return sledMotorMain.getSensorCollection().isFwdLimitSwitchClosed();
  }

  /**
   * @return true if the FWD limit swithc is closed See
   *     also:https://docs.ctre-phoenix.com/en/stable/ch13_MC.html?highlight=limit%20switch#limit-switch-as-digital-inputs
   */
  public boolean getRevLimitState() {
    return sledMotorMain.getSensorCollection().isRevLimitSwitchClosed();
  }
}
