
package org.firstinspires.ftc.teamcode.testing.drivetrain;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.hardware.DrivetrainHardware;

@Autonomous(name = "Auto Path By Methods")
public class AutoGardenDrivetrain extends LinearOpMode {
    DrivetrainHardware robot; // We here introduce a variable robot and connect it to our hardware class
    // Now we can use all the variables that we added in our drivetrain hardware

    @Override
    public void runOpMode() {

        // Connect and prepare the robot hardware
        robot = new DrivetrainHardware(this);
        robot.initialize();

        waitForStart();

        // ====== Auto Path ======
        if (opModeIsActive()) {
            robot.driveStraight(20, 0.3);
            robot.driveRotate(90, 0.3);
            robot.driveStraight(110, 0.3);
            robot.driveRotate(-90, 0.3);
            robot.driveStraight(50, 0.3);
            robot.driveStraight(-50, 0.3);
            robot.driveSide(20, 0.3);
            // TODO: 
            robot.driveRotate(-180, 0.3);
            robot.driveStraight(240, 0.3);
            robot.driveRotate(90, 0.3);
            robot.driveStraight(50, 0.3);
            robot.driveRotate(90, 0.3);
            sleep(500);
            robot.driveRotate(90, 0.3);
            robot.driveStraight(90, 0.3);
        }
    }
}
