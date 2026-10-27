package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.IMU;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;

// Helper class that owns all drivetrain hardware and movement code.
// It is not an OpMode, so it cannot be selected on the Driver Station.
// An OpMode creates one of these and calls init() to set it up.
public class DrivetrainHardware {

    // ==== Hardware declarations ====
    // Set in init(), not here, because hardwareMap is not available yet.
    private DcMotor leftFront;
    private DcMotor leftBack;
    private DcMotor rightFront;
    private DcMotor rightBack;
    private IMU imu;

    // Private reference to the OpMode this helper works for.
    private LinearOpMode mode;

    // ====== MECANUM CONSTANTS ======
    private final double MOTOR_TICKS = 537.7; // encoder ticks per motor revolution
    private final double WHEEL_DIAMETER_CM = 10.4; // wheel diameter in cm
    private final double WHEEL_CIRCUMFERENCE = WHEEL_DIAMETER_CM * Math.PI; // cm per wheel revolution
    private final double TICKS_PER_CM = MOTOR_TICKS / WHEEL_CIRCUMFERENCE;  // ticks to move 1 cm

    // Empty constructor. Hardware is connected later in init().
    public DrivetrainHardware() {
    }

    // Connects to the motors and IMU and sets their configuration.
    // Must be called from the OpMode's runOpMode().
    public void init(HardwareMap ahwMap, LinearOpMode mode) {
        this.mode = mode; // save the OpMode so we can check opModeIsActive() later

        // ====== HARDWARE INIT ======
        leftFront = ahwMap.get(DcMotor.class, "leftFront");
        leftBack = ahwMap.get(DcMotor.class, "leftBack");
        rightFront = ahwMap.get(DcMotor.class, "rightFront");
        rightBack = ahwMap.get(DcMotor.class, "rightBack");

        imu = ahwMap.get(IMU.class, "imu");

        // ====== MOTOR CONFIG ======
        leftFront.setDirection(DcMotor.Direction.REVERSE);
        leftBack.setDirection(DcMotor.Direction.REVERSE);
        rightFront.setDirection(DcMotor.Direction.FORWARD);
        rightBack.setDirection(DcMotor.Direction.FORWARD);

        leftFront.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        leftBack.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightFront.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightBack.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        // Configure IMU orientation (logo up, USB pointing backward)
        RevHubOrientationOnRobot orientation =
                new RevHubOrientationOnRobot(
                        RevHubOrientationOnRobot.LogoFacingDirection.UP,
                        RevHubOrientationOnRobot.UsbFacingDirection.BACKWARD
                );
        IMU.Parameters parameters = new IMU.Parameters(orientation);
        imu.initialize(parameters); // IMU init

        // Reset all values to default before start
        imu.resetYaw();  // set the current heading to 0 degrees
        resetEncoders(); // reset and re-enable the encoders
        stopMotors(); // make sure nothing is moving
    }


    // ====== DRIVE STRAIGHT (forward or backward) ======
    // Negative distance drives backward. Power is always used as a magnitude.
    public void straight(double distance, double power) {
        resetEncoders();

        double targetTicks = Math.abs(distance * TICKS_PER_CM); // ticks needed for this distance
        double drivePower = Math.abs(power); // use the magnitude of power

        // Determine drive direction
        if (distance < 0) {
            drivePower *= -1;
        }

        // Set motor powers
        setDrivePower(drivePower, drivePower, drivePower, drivePower);

        // Wait until leftFront has moved far enough
        // (all wheels move the same, so the exact wheel doesn't matter)
        while (mode.opModeIsActive() && Math.abs(leftFront.getCurrentPosition()) < targetTicks) {
            // Do nothing until the condition is satisfied
        }
        stopMotors();
        mode.sleep(250); // short pause so the robot settles
    }


    // ====== ROTATE BY DEGREE VALUE ======
    // Positive angle turns right, negative angle turns left.
    public void rotate(double angle, double power) {
        imu.resetYaw(); // reset IMU yaw (y-axis rotation)

        double targetAngle = Math.abs(angle); // degrees to turn
        double turnPower = Math.abs(power);   // speed magnitude

        // Determine turn direction
        if (angle < 0) {
            turnPower *= -1;
        }

        // Set motor powers
        setDrivePower(turnPower, turnPower, -turnPower, -turnPower);

        // Keep turning until the absolute yaw reaches the target angle
        while (mode.opModeIsActive() && Math.abs(imu.getRobotYawPitchRollAngles().getYaw(AngleUnit.DEGREES)) < targetAngle) {
            // Do nothing until the condition is satisfied
        }
        stopMotors();
        mode.sleep(250);
    }

    
    // ====== SIDE MOVEMENT ======
    // Strafes sideways a given distance in cm. Positive distance strafes right, negative strafes left.
    public void side(double distance, double power) {
        resetEncoders();

        double targetTicks = Math.abs(distance * TICKS_PER_CM);
        double strafePower = Math.abs(power);

        // Determine drive direction
        if (distance < 0) {
            strafePower *= -1;
        }

        // Set motor powers
        setDrivePower(strafePower, -strafePower, -strafePower, strafePower);

        // Wait until leftFront has moved far enough
        while (mode.opModeIsActive() && Math.abs(leftFront.getCurrentPosition()) < targetTicks) { 
            // do nothing until the condition is satisfied
        }
        stopMotors();
        mode.sleep(250);
    }

    // ====== RESET MOTOR ENCODERS ======
    public void resetEncoders() {
        leftFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftBack.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightBack.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        leftFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        leftBack.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightBack.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    // Set motor power. Used by every movement method above.
    private void setDrivePower(double leftFrontPower, double leftBackPower, double rightFrontPower, double rightBackPower) {
        leftFront.setPower(leftFrontPower);
        leftBack.setPower(leftBackPower);
        rightFront.setPower(rightFrontPower);
        rightBack.setPower(rightBackPower);
    }

    // Stop all drivetrain motors
    public void stopMotors() {
        setDrivePower(0, 0, 0, 0);
    }
}