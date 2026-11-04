
package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

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
            robot.straight(20, 0.3);
            robot.rotate(90, 0.3);
            robot.straight(110, 0.3);
            robot.rotate(90, -0.3);
            robot.straight(50, 0.3);
            robot.straight(50, -0.3);
            robot.side(20, 0.3);
            robot.rotate(180, -0.3);
            robot.straight(240, 0.3);
            robot.rotate(90, 0.3);
            robot.straight(50, 0.3);
            robot.rotate(90, 0.3);
            sleep(500);
            robot.rotate(90, 0.3);
            robot.straight(90, 0.3);
        }
    }
}
