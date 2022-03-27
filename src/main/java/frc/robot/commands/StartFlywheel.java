// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.math.util.Units;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.subsystems.Outtake;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class StartFlywheel extends InstantCommand {
  private final Outtake outtake;

  public StartFlywheel(Outtake takeout) {
    outtake = takeout;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(takeout);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    outtake.setFlyWheelO(
        Units.rotationsPerMinuteToRadiansPerSecond(5676)
            * 1.33
            * .14); // Rev NEO in Rev/s to Rad/s * 1.33 step up gear * 75% output level
  }
}