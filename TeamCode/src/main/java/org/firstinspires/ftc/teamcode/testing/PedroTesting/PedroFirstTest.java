package org.firstinspires.ftc.teamcode.testing.PedroTesting;

import static com.pedropathing.api.Paths.*;
import com.pedropathing.api.Paths;

import com.pedropathing.api.PoseFactory;
import com.pedropathing.follower.Follower;
import com.pedropathing.math.Pose;
import com.pedropathing.paths.Path;
import com.pedropathing.ivy.Command;
import com.pedropathing.ivy.Scheduler;
import static com.pedropathing.ivy.Scheduler.schedule;
import static com.pedropathing.ivy.commands.Commands.*;
import static com.pedropathing.ivy.groups.Groups.sequential;
import static com.pedropathing.ivy.pedro.PedroCommands.follow;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.hardware.PedroPathingConstants;

@Autonomous(name = "Pedro First Test", group = "Autonomous")
public class PedroFirstTest extends LinearOpMode {

    private Follower follower;

    private final PoseFactory poseFactory = PoseFactory.degrees();

    private final Pose start = poseFactory.of(78.6999, 78.4043, 90);
    private final Pose test = poseFactory.of(116.4947, 80.9389, -90);
    private final Pose testControl1 = poseFactory.of(79.6049, 130.159, 0);
    private final Pose testControl2 = poseFactory.of(111.9315, 127.0441, 0);

    // Autonomous routine
    public Command autoRoutine() {
        return sequential(
                follow(follower, test())
        );
    }

    @Override
    public void runOpMode() {
        Scheduler.reset();
        follower = PedroPathingConstants.create(hardwareMap);
        follower.setPose(start);
        follower.update();

        waitForStart();
        schedule(autoRoutine());

        while (opModeIsActive()) {
            follower.update();
            Scheduler.execute();

            telemetry.addData("x", follower.pose().x());
            telemetry.addData("y", follower.pose().y());
            telemetry.addData("heading", follower.pose().heading());

            if (follower.currentPath() != null) {
                telemetry.addData("Current path distance remaining", follower.distanceToEndpoint());
                telemetry.addData("Path number", follower.pathIndex());
            }

            telemetry.update();
        }
    }

    public Path test() {
        return Paths.curve(start, testControl1, testControl2, test).linear(start, test);
    }
}
