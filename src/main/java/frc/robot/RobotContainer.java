// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
// import frc.robot.subsystems.ExampleSubsystem;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.commands.ArcadeDrive;
import frc.robot.commands.AutoDrive;
import frc.robot.commands.BringInOutAndIn;
import frc.robot.commands.ExtendInAndOut;
import frc.robot.commands.LowerClimb;
import frc.robot.commands.MoveSled;
import frc.robot.commands.RaiseClimb;
import frc.robot.commands.Shoot;
import frc.robot.commands.SpinFeedWheel;
import frc.robot.commands.SpinIntakeWheel;
import frc.robot.commands.StartFlywheel;
import frc.robot.commands.StopFlywheel;
import frc.robot.subsystems.ClimbRaiser;
// import frc.robot.commands.ExampleCommand;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.InAndOut2;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Outtake;
import frc.robot.subsystems.Sled;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  // private final ExampleSubsystem m_exampleSubsystem = new ExampleSubsystem();

  // private final ExampleCommand m_autoCommand = new ExampleCommand(m_exampleSubsystem);

  // Controls
  public static final XboxController controller = new XboxController(Constants.CONTROLLER_PORT_ID);
  // It's recomend ot use the provided enum to look button ids - see
  // https://docs.wpilib.org/en/stable/docs/software/commandbased/binding-commands-to-triggers.html,
  // https://first.wpi.edu/wpilib/allwpilib/docs/release/java/edu/wpi/first/wpilibj/XboxController.Button.html,and https://first.wpi.edu/wpilib/allwpilib/docs/release/java/src-html/edu/wpi/first/wpilibj/XboxController.Button.html#line.24
  public static final JoystickButton shootButton =
      new JoystickButton(controller, Constants.JOYSTICK_BUTTON_SHOOT_ID);
  public static final TriggerL2Button intakeButton = new TriggerL2Button();
  public static final TriggerDPadUp extendInAndOutButton = new TriggerDPadUp();
  public static final TriggerDPadDown bringInOutAndInButton = new TriggerDPadDown();

  public static final JoystickButton climbRaiserButton =
      new JoystickButton(controller, Constants.JOYSTICK_BUTTON_CLIMB_RAISE_ID);
  public static final JoystickButton climbLowerButton =
      new JoystickButton(controller, Constants.JOYSTICK_BUTTON_CLIMB_LOWER_ID);

  // Subystems
  public static Drivetrain drivetrain = new Drivetrain(); // Drivetrain

  // Shintake
  public static Intake intake = new Intake(); // Intake Assymbly
  public static Outtake outtake = new Outtake(); // Outtake Assymbly - Fleywheel and feedwheel
  public static InAndOut2 inAndOut2 = new InAndOut2(); // Linear actuator for Shintake Assybemly

  // Climber
  public static ClimbRaiser climbRaiser = new ClimbRaiser(); // Linear acutoars for climber
  public static Sled sled = new Sled(); // Slidey sled for climb!

  // Commands

  // Drive!
  public static ArcadeDrive arcadeDrive = new ArcadeDrive(drivetrain);
  public static AutoDrive autoDrive = new AutoDrive(drivetrain);

  // Shintake
  public static ExtendInAndOut extendInAndOut =
      new ExtendInAndOut(inAndOut2); // Extend linear actuator for Shintake Assybemly
  public static final SpinIntakeWheel spinIntakeWheel =
      new SpinIntakeWheel(intake); // Spin intake wheel
  public static final SpinFeedWheel spinFeedWheel =
      new SpinFeedWheel(outtake); // Spin feed wheel for shooter
  public static StartFlywheel startFlywheel = new StartFlywheel(outtake); // Start flywheel
  public static StopFlywheel stopFlywheel = new StopFlywheel(outtake); // Start flywheel
  public static Shoot shoot = new Shoot(); // Start flywheel, run feed wheel when up to speed
  public static BringInOutAndIn bringInOutAndIn =
      new BringInOutAndIn(inAndOut2); // Retract linear actuator for Shintake Assybemly

  // Climber
  public static RaiseClimb raiseClimb =
      new RaiseClimb(climbRaiser); // Raise climber up and maybe a little down
  public static LowerClimb lowerClimb = new LowerClimb(climbRaiser);
  public static MoveSled moveSled = new MoveSled(sled);

  // Command m_autonomousCommand; // Place holder

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    // setting default commands
    drivetrain.setDefaultCommand(arcadeDrive);
    sled.setDefaultCommand(moveSled);

    // Configure the button bindings
    configureButtonBindings();
  }

  /**
   * Use this method to define your button->command mappings. Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a {@link
   * edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
    intakeButton.whileActiveContinuous(spinIntakeWheel);
    shootButton.whileHeld(shoot);
    climbRaiserButton.whileHeld(raiseClimb);
    climbLowerButton.whileHeld(lowerClimb);
    extendInAndOutButton.whileActiveContinuous(extendInAndOut);
    bringInOutAndInButton.whileActiveContinuous(bringInOutAndIn);
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous
    return autoDrive;
  }
}
