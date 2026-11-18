package org.firstinspires.ftc.teamcode.Teleop;

import com.qualcomm.robotcore.hardware.Gamepad;


public class ButtonMapping {
    public float axialMovementX;

    public float axialMovementY;

    public float rotationalMovement;

    public float shootKey;

    public float intakeKey;

    public int flipperKey;

    public boolean toggleLLAuto;

    public String name;

    public boolean switchAlliance;
    public void UpdateButtonMap(Gamepad controller, int driver)
    {
        if (driver == 0)
        {
            name = "Barret";

            axialMovementX = controller.left_stick_x;

            axialMovementY = controller.left_stick_y;

            rotationalMovement = controller.right_stick_x;

            shootKey = controller.right_bumper ? 1.0f : 0.0f;

            intakeKey = controller.left_bumper ? 1.0f : 0.0f;

            flipperKey = controller.left_stick_button ? 0 : 1;

            switchAlliance = controller.xWasPressed();

            toggleLLAuto = controller.aWasPressed();
        }
        if (driver == 1)
        {
            name = "Manraj";

            axialMovementX = controller.left_stick_x;

            axialMovementY = controller.left_stick_y;

            rotationalMovement = controller.right_stick_x;

            shootKey = controller.right_trigger;

            intakeKey = controller.left_trigger;

            flipperKey = controller.right_bumper ? 0 : 1;

            switchAlliance = controller.xWasPressed();

            toggleLLAuto = controller.aWasPressed();
        }
    }
}
