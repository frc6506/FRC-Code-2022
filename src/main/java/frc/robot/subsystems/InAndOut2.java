// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.LimitSwitchNormal;
import com.ctre.phoenix.motorcontrol.LimitSwitchSource;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class InAndOut2 extends SubsystemBase {
  // Motor Controller Objects
  private TalonSRX extendMotor = new TalonSRX(Constants.MOTOR_SHINTAKE_EXTENDER_ID);

  // Limit Switch Objects
  private DigitalInput rEVLimit = new DigitalInput(Constants.SWITCH_SHINTAKE_EXTENDER_REV_ID);


  /** Creates a new InAndOut2. */
  public InAndOut2() {
    extendMotor.setInverted(true);
    extendMotor.configForwardLimitSwitchSource(LimitSwitchSource.FeedbackConnector, LimitSwitchNormal.NormallyOpen);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    //System.out.println(getFWDLimitState());
  }
  /**
   * @param speed decimal percent [-1.0, 1.0] FWD and Rev Limit switch enforced
   */
  public void InAndOutExtend(double speed) {
    System.out.println("Running in and out extend method with speed: " + speed + ", FWD limit state: " + getFWDLimitState() + ", and REV limit state: " + getRevLimitState());
    System.out.println("Running in and out extend method");
    if(speed > 0 && !getFWDLimitState()) {
      System.out.println("Extending shooter out");
      extendMotor.set(ControlMode.PercentOutput, speed);
    }
    else if(speed < 0 && !getRevLimitState()) {
      extendMotor.set(ControlMode.PercentOutput, speed);
    }
    else {
      extendMotor.set(ControlMode.PercentOutput, 0);
    }
  }

  /**
   * @return true if the FWD limit swithc is tripped See also:
   *     https://docs.ctre-phoenix.com/en/stable/ch13_MC.html?highlight=limit%20switch#limit-switch-as-digital-inputs
   */
  public boolean getFWDLimitState() {
    return extendMotor.getSensorCollection().isFwdLimitSwitchClosed();
  }

  /**
   * @return true if the REV limit swithc is tripped See also:
   *     https://docs.ctre-phoenix.com/en/stable/ch13_MC.html?highlight=limit%20switch#limit-switch-as-digital-inputs
   */
  public boolean getRevLimitState() {
    return rEVLimit.get();
  }
  
}
