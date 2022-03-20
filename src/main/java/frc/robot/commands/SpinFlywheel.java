// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Outtake;
import edu.wpi.first.math.util.Units;

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
    outtake.setFlyWheelO(Units.rotationsToRadians(5676) * 1.33 * .75); // Rev NEO in Rev/s to Rad/s * 1.33 step up gear * 75% output level
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    outtake.stopFlyWheel();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
