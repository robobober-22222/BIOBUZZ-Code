
package org.firstinspires.ftc.teamcode.hardware;

import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.IMU;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;

public class IntakeHardware {


    private DcMotor intake;

    private LinearOpMode opMode; // This lets us use OpMode features like sleep.


    public IntakeHardware(LinearOpMode opMode) {
        this.opMode = opMode;
    }

    public void initialize() {

        // TODO:sure? leftFront -- if Intake is not random name, then remove this TODO
        intake = opMode.hardwareMap.get(DcMotor.class, "Intake");


        intake.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);


        resetEncoders();
    }



    public void intakeStart(double power) {

        resetEncoders();

        intake.setPower(power);

    }

    public void intakeStop(){
        resetEncoders();

        intake.setPower(0);
    }

    public void resetEncoders() {

        intake.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        intake.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

}
