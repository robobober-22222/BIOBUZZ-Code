package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

@Autonomous(name = "Auto Test Improved")
public class AutoGardenDrivetrain extends LinearOpMode {

    // Refer to DrivetrainHardware class. Creates robot object to call for shared methods.
    private final DrivetrainHardware robot = new DrivetrainHardware();

    public static final double SPEED_FACTOR = 0.6; // Master constant for robot speed

    @Override
    public void runOpMode() {

        // Connects the motors and IMU. Passing "this" gives the helper class access to this OpMode
        robot.init(hardwareMap, this);

        while (opModeInInit()) {
            telemetry.addLine("Robot initialized");
            telemetry.addLine("Waiting for start...");
            telemetry.update();
        }
        waitForStart();

        // Autonomous movement sequence
        if (opModeIsActive()) {

            // Intake (front side of the robot) faces the wall at start.
            // Shooter away from the wall and facing the HIVE (accomplished with a turret-type shooter)
            robot.straight(-20, SPEED_FACTOR);
            sleep(500); // shoot (can be before the first move). In this case a solid-state shooter would be mounted opposite the intake. Doesn't matter with a turret-type shooter.
            robot.side(120, SPEED_FACTOR); // move towards GARDEN (right strafe)
            robot.straight(-20, SPEED_FACTOR); // move to GARDEN
            sleep(300); // collect GARDEN
            robot.side(-60, SPEED_FACTOR); // strafe left
            robot.straight(-290, SPEED_FACTOR); // move to opposite side of the field
            robot.side(-60, SPEED_FACTOR); // move towards shooting position (left strafe)
            robot.rotate(180, SPEED_FACTOR); // rotate so that the shooter faces the HIVE. Unnecessary with a turret-type shooter.
            // NOTE: when a turret-type shooter is built the directions below will have ot be reversed, as the robot.rotate() above will not have happened.
            sleep(500); // shoot
            robot.side(-22, SPEED_FACTOR); // move towards the FLOWER (left strafe)
            robot.straight(10, SPEED_FACTOR); // move to FLOWER. Here the robot doesn't touch the FLOWER, as no sufficient intake exists yet.
            sleep(300); // collect FLOWER
            robot.straight(-10, SPEED_FACTOR); // move to shooting position Y
            robot.side(22, SPEED_FACTOR); // move to shooting position X (right strafe)
            sleep(500); // shoot
            robot.side(-120, SPEED_FACTOR); // move towards the PARKING ZONE (left strafe)
            robot.straight(-45, SPEED_FACTOR); // move to PARKING ZONE
        }
        robot.stopMotors(); // stop all motors
        sleep(100); // final sleep so everything shuts down properly
    }
}