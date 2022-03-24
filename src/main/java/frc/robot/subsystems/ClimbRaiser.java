// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class ClimbRaiser extends SubsystemBase {
  // Motor Controller Objects
  private TalonSRX extendMotorMain = new TalonSRX(Constants.MOTOR_CLIMBER_EXTENDER_LEFT_ID);
  private VictorSPX extendMotorRight = new VictorSPX(Constants.MOTOR_CLIMBER_EXTENDER_RIGHT_ID);

  /** Creates a new ClimbRaiser. */
  public ClimbRaiser() {
    // Set second one to follow, non inverted
    extendMotorRight.follow(extendMotorMain);
    extendMotorMain.setInverted(true);
    extendMotorRight.setInverted(true);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  /**
   * @param speed decimal percent [-1.0, 1.0] Be careful running bakcwards!! No Rev. limit switch
   *     FWD Limit switch enforced
   */
  public void climbExtend(double speed) {
    extendMotorMain.set(ControlMode.PercentOutput, speed);
  }

  /**
   * @return true if the FWD limit swithc is closed See also:
   *     https://docs.ctre-phoenix.com/en/stable/ch13_MC.html?highlight=limit%20switch#limit-switch-as-digital-inputs
   */
  public boolean getFWDLimitState() {
    return extendMotorMain.getSensorCollection().isFwdLimitSwitchClosed();
  }
}
