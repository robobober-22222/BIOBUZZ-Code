package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

@Autonomous(name = "Auto Test Improved")
public class AutoGardenDrivetrain extends LinearOpMode {

    private final DrivetrainHardware robot = new DrivetrainHardware();

    public static final double SPEED_FACTOR = 0.6;

    @Override
    public void runOpMode() {

        robot.init(hardwareMap, this);

        while (opModeInInit()) {
            telemetry.addLine("Robot initialized");
            telemetry.addLine("Waiting for start...");
            telemetry.update();
        }
        waitForStart();

        // Autonomous movement sequence
        if (opModeIsActive()) {

            robot.straight(20, SPEED_FACTOR);
            sleep(500);
            robot.rotate(90, -SPEED_FACTOR);
            robot.straight(110, SPEED_FACTOR);
            robot.rotate(90, -SPEED_FACTOR);
            robot.straight(50, SPEED_FACTOR);
            robot.straight(50, -SPEED_FACTOR);
            robot.side(20, SPEED_FACTOR);
            robot.rotate(180, -SPEED_FACTOR);
            robot.straight(240, SPEED_FACTOR);
            robot.rotate(90, SPEED_FACTOR);
            robot.straight(50, SPEED_FACTOR);
            robot.rotate(90, SPEED_FACTOR);
            sleep(500);
            robot.rotate(90, SPEED_FACTOR);
            robot.straight(90, SPEED_FACTOR);
        }
        robot.stopMotors();
    }
}