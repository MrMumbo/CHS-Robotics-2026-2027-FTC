package org.firstinspires.ftc.teamcode.Auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Hardware.Hardware;

@Autonomous(name="Basic Auto", group="Autonomous")
public class Auto extends LinearOpMode {

    Hardware hw = new Hardware();

    int autonNumber = 1;

    public void runOpMode() {
        while (!opModeIsActive())
        {
            if (gamepad1.dpadRightWasPressed())
            {
                autonNumber++;
            }
            if (gamepad1.dpadLeftWasPressed())
            {
                autonNumber--;
            }
            telemetry.addData("Auto Program: ", autonNumber);
            telemetry.update();
        }
        hw.declareHardware(hardwareMap);

        if (autonNumber == 1)
        {
            // Shoot and Park
            hw.autoShoot(800, telemetry);
            hw.driveTime(-1, 0.2F, 0, 0.8F);
            hw.driveTime(-0.85F, 1, 0, 1.05F);
            hw.driveTime(0, 0.6F, 0, 0.9F);
        }

        if (autonNumber == 2)
        {
            // Shoot, Intake, Shoot, and Park
            hw.autoShoot(800, telemetry);
            hw.autoIntakeStartStop(true);
            hw.driveTime(-1,.0F,0,1.3F);
            hw.autoIntakeStartStop(false);
            hw.driveTime(0,.2F,0,0.8F);
            hw.driveTime(-0F, 1, 0, 1.05F);
            hw.driveTime(0, 0.6f, 0.9F,1);
            hw.driveTime(-1, 0f, 0F,1.3F);
            hw.driveTime(0, -.7f, 0F,.7f);



        }    }
}
