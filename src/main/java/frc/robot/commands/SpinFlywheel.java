// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Outtake;

public class SpinFlywheel extends CommandBase {
  private final Outtake outtake;

  /** Creates a new SpinFlywheel. */
  public SpinFlywheel(Outtake takeout) {
    outtake = takeout;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(takeout);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    outtake.spinFlywheel(.75);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    outtake.spinFlywheel(0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
