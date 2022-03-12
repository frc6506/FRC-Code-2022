// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
/**
 * From RobotMap.java ((c) 2019)
 * The RobotMap is a mapping from the ports sensors and actuators are wired into to a variable name.
 * This provides flexibility changing wiring, makes checking the wiring easier and significantly
 * reduces the number of magic numbers floating around.
 */
public final class Constants {
  // Moved from old RobotMap.java and OI.java (except for Joystick Access method to comply with new template (and as shown in Practice Project 2021).  Thanks to @spacejunkl (CBS) for helpings.
  // Controls
  public static final int CONTROLLER_PORT_ID = 0; // Joystick

  // Left joystick
  public static final int JOYSTICK_DRIVE_SPEED_ID = 1; // Y-axis
  public static final int JOYSTICK_DRIVE_ROTATION_ID = 0; // X-axis

  // Drivetrain
  public static final int MOTOR_RIGHT_FRONT_ID = 10;
  public static final int MOTOR_RIGHT_BACK_ID = 11;
  public static final int MOTOR_LEFT_FRONT_ID = 12;
  public static final int MOTOR_LEFT_BACK_ID = 13;

  // Shintake
  public static final int MOTOR_SHINTAKE_EXTENDER_ID = 20;
  
  // Outtake
  public static final int MOTOR_INTAKE_ID = 21;
  public static final int MOTOR_FLYWHEEL_ID = 23;
  public static final int MOTOR_FEEDWHEEL_ID = 22;
  
}
