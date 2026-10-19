package org.firstinspires.ftc.teamcode.Vision;

import com.qualcomm.hardware.limelightvision.LLResult;
import com.qualcomm.hardware.limelightvision.LLResultTypes;
import com.qualcomm.hardware.limelightvision.Limelight3A;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.navigation.Pose3D;

public class LimeLight {

    /* Useful methods:
       getTargetPoseRobotSpace()
       getRobotPoseFieldSpace()
       uploadFieldmap(fieldMap, null)
     */
    public Limelight3A limelight3A;

    private LLResult result;

    public void initLL(HardwareMap hwMap)
    {
        limelight3A = hwMap.get(Limelight3A.class, "limelight");
        limelight3A.pipelineSwitch(8);
    }

    public void updateVision()
    {
        result = limelight3A.getLatestResult();
    }

    public LLResult getTagValues(int ID)
    {
        if (result != null && result.isValid())
        {
            for (LLResultTypes.FiducialResult tag : result.getFiducialResults())
            {
                if (tag.getFiducialId() == ID)
                {
                    return result;
                }
            }
        }

        return null;
    }

    public double getAprilTagPoseY(int ID)
    {
        if (result != null && result.isValid()) {
            for (LLResultTypes.FiducialResult tag : result.getFiducialResults()) {

                int id = tag.getFiducialId();

                Pose3D tagPose = tag.getTargetPoseRobotSpace();

                double y = tagPose.getPosition().y;

                if (id == ID)
                {
                    return y;
                }
            }
        }
        return 0;
    }

    public void startCamera()
    {
        limelight3A.start();
    }

    public void stopCamera()
    {
        limelight3A.stop();
    }
}
