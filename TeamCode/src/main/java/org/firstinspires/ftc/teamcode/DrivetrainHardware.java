package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.IMU;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;

public class DrivetrainHardware {

    private DcMotor leftFront;
    private DcMotor leftBack;
    private DcMotor rightFront;
    private DcMotor rightBack;

    private IMU imu;

    private LinearOpMode mode;

    private final double MOTOR_TICKS = 537.7;
    private final double WHEEL_DIAMETER_CM = 10.4;
    private final double WHEEL_CIRCUMFERENCE = WHEEL_DIAMETER_CM * Math.PI;
    private final double TICKS_PER_CM = MOTOR_TICKS / WHEEL_CIRCUMFERENCE;

    public DrivetrainHardware() {
    }

    // Initialize all hardware
    public void init(HardwareMap ahwMap, LinearOpMode mode) {
        this.mode = mode;

        leftFront = ahwMap.get(DcMotor.class, "leftFront");
        leftBack = ahwMap.get(DcMotor.class, "leftBack");
        rightFront = ahwMap.get(DcMotor.class, "rightFront");
        rightBack = ahwMap.get(DcMotor.class, "rightBack");

        imu = ahwMap.get(IMU.class, "imu");

        // Set motor directions
        leftFront.setDirection(DcMotor.Direction.REVERSE);
        leftBack.setDirection(DcMotor.Direction.REVERSE);
        rightFront.setDirection(DcMotor.Direction.FORWARD);
        rightBack.setDirection(DcMotor.Direction.FORWARD);

        // Initialize IMU orientation
        RevHubOrientationOnRobot orientation =
                new RevHubOrientationOnRobot(
                        RevHubOrientationOnRobot.LogoFacingDirection.UP,
                        RevHubOrientationOnRobot.UsbFacingDirection.BACKWARD
                );

        IMU.Parameters parameters = new IMU.Parameters(orientation);

        imu.initialize(parameters);
        imu.resetYaw();

        reset();

        stopMotors();
    }

    public void straight(double distance, double power) {
        reset();

        double targetTicks = Math.abs(distance * TICKS_PER_CM);
        double drivePower = Math.abs(power);

        if (distance < 0) {
            drivePower = -drivePower;
        }

        setDrivePower(
                drivePower, drivePower,
                drivePower, drivePower
        );

        while (mode.opModeIsActive()
                && Math.abs(leftFront.getCurrentPosition()) < targetTicks) {
        }

        stopMotors();
        mode.sleep(500);
    }

    public void reset() {
        leftFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftBack.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightBack.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        leftFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        leftBack.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightBack.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    public void rotate(double angle, double power) {
        imu.resetYaw();

        double targetAngle = Math.abs(angle);
        double turnPower = Math.abs(power);

        if (angle < 0) {
            turnPower = -turnPower;
        }

        setDrivePower(
                turnPower, turnPower,
                -turnPower, -turnPower
        );

        while (mode.opModeIsActive()
                && Math.abs(imu.getRobotYawPitchRollAngles()
                .getYaw(AngleUnit.DEGREES)) < targetAngle) {
        }

        stopMotors();
        mode.sleep(500);
    }

    public void side(double distance, double power) {
        reset();

        double targetTicks = Math.abs(distance * TICKS_PER_CM);
        double strafePower = Math.abs(power);

        if (distance < 0) {
            strafePower = -strafePower;
        }

        setDrivePower(
                strafePower, -strafePower,
                -strafePower, strafePower
        );

        while (mode.opModeIsActive()
                && Math.abs(leftFront.getCurrentPosition()) < targetTicks) {
            // Wait for the encoder to reach the target
        }

        stopMotors();
        mode.sleep(500);
    }

    private void setDrivePower(
            double leftFrontPower,
            double leftBackPower,
            double rightFrontPower,
            double rightBackPower) {

        leftFront.setPower(leftFrontPower);
        leftBack.setPower(leftBackPower);
        rightFront.setPower(rightFrontPower);
        rightBack.setPower(rightBackPower);
    }

    public void stopMotors() {
        setDrivePower(0, 0, 0, 0);
    }
}
