// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj2.command.button.Trigger;

/** Add your docs here. */
public class TriggerR2Button extends Trigger {
  @Override
  public boolean get() {
    return RobotContainer.controller.getRawAxis(2) > 0.5;
  }
}
