package org.firstinspires.ftc.teamcode.hardware;

import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.IMU;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;

public class DrivetrainHardware {

    // ====== HARDWARE OBJECTS ======
    private DcMotor leftFront;
    private DcMotor leftBack;
    private DcMotor rightFront;
    private DcMotor rightBack;
    private IMU imu;

    // ====== MECANUM CONSTANTS ======
    private final double MOTOR_TICKS = 537.7;
    private final double WHEEL_DIAMETER_CM = 10.4;

    private final double WHEEL_CIRCUMFERENCE = WHEEL_DIAMETER_CM * Math.PI;

    private final double TICKS_PER_CM = MOTOR_TICKS / WHEEL_CIRCUMFERENCE;

    private LinearOpMode opMode; // This lets us use OpMode features like sleep()
    public DrivetrainHardware(LinearOpMode opMode) {
        this.opMode = opMode;
    }

    public void initialize() {

        // ====== HARDWARE INIT ======
        leftFront = opMode.hardwareMap.get(DcMotor.class, "leftFront");
        leftBack = opMode.hardwareMap.get(DcMotor.class, "leftBack");
        rightFront = opMode.hardwareMap.get(DcMotor.class, "rightFront");
        rightBack = opMode.hardwareMap.get(DcMotor.class, "rightBack");

        imu = opMode.hardwareMap.get(IMU.class, "imu");

        // ------ DcMotor Modes ------
        leftFront.setDirection(DcMotor.Direction.REVERSE);
        leftBack.setDirection(DcMotor.Direction.REVERSE);
        rightFront.setDirection(DcMotor.Direction.FORWARD);
        rightBack.setDirection(DcMotor.Direction.FORWARD);

        leftFront.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        leftBack.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightFront.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightBack.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        // ------ IMU Init ------
        RevHubOrientationOnRobot.LogoFacingDirection logoDirection = RevHubOrientationOnRobot.LogoFacingDirection.UP;
        RevHubOrientationOnRobot.UsbFacingDirection usbDirection = RevHubOrientationOnRobot.UsbFacingDirection.BACKWARD;
        IMU.Parameters parameters = new IMU.Parameters(new RevHubOrientationOnRobot(logoDirection, usbDirection));

        // ------ Reset Values ------
        imu.initialize(parameters);
        imu.resetYaw();
        resetEncoders();
    }

    // ====== STRAIGHT DRIVE ======
    public void driveStraight(double distance, double power) {

        resetEncoders();

        power = Math.abs(power);
        if(distance < 0 ){
            power *= -1;
        }
        setDrivePower(power, power, power, power);

        while (opMode.opModeIsActive() && Math.abs(leftFront.getCurrentPosition()) < Math.abs(distance * TICKS_PER_CM)) {
            opMode.idle();
        }
        stopMotors();
        opMode.sleep(500);
    }

    // ====== ROTATE DRIVE ======
    public void driveRotate(double angle, double power) {

        imu.resetYaw();

        power = Math.abs(power);
        if(angle < 0 ){
            power *= -1;
        }
        // Left and right sides move in opposite directions
        setDrivePower(power, power, -power, -power);// set angle to positive to turn right set angle to negative to go left

        while (opMode.opModeIsActive() && Math.abs(imu.getRobotYawPitchRollAngles().getYaw(AngleUnit.DEGREES)) < Math.abs(angle)) {
            opMode.idle();
        }
        stopMotors();
        opMode.sleep(500);
    }
    // ====== STRAFE DRIVE ======
    public void driveSide(double distance, double power) {
        resetEncoders();

        power = Math.abs(power);
        if(distance < 0 ){
            power *= -1;
        }
        setDrivePower(power, -power, -power, power); //set distance to negative to go left positive to go right

        while (opMode.opModeIsActive() && Math.abs(leftFront.getCurrentPosition()) < Math.abs(distance) * TICKS_PER_CM) {
            opMode.idle();
        }
        stopMotors();
        opMode.sleep(500);
    }

    // ====== RESET ENCODERS ======
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

    // ====== STOP MOTORS ======
    public void stopMotors() {
        setDrivePower(0, 0, 0, 0);
    }

    // ====== SET DRIVE POWER ======
    public void setDrivePower(double leftFrontPower, double leftBackPower, double rightFrontPower, double rightBackPower) {
        leftFront.setPower(leftFrontPower);
        leftBack.setPower(leftBackPower);
        rightFront.setPower(rightFrontPower);
        rightBack.setPower(rightBackPower);
    }

}
