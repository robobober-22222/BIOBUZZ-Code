package org.firstinspires.ftc.teamcode.testing.intake;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.hardware.IntakeHardware;

@TeleOp(name = "TeleOp Intake")
public class TeleOpIntake extends LinearOpMode {

    IntakeHardware robot;

    @Override
    public void runOpMode() {
        robot = new IntakeHardware(this);
        robot.intakeInitialize();

        waitForStart();
        while (opModeIsActive()){
            double intakeRotate = -gamepad1.left_stick_y;
            robot.intakeStart(intakeRotate*0.6);
        }
    }
}
