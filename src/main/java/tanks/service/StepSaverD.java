package tanks.service;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class StepSaverD {

    String fileName = "stepOfGameD.txt";
    private List<TankAction>stepDefaunder;

    public StepSaverD( List<TankAction>stepDefaunder) {

        this.stepDefaunder = stepDefaunder;

    }

    public void save() throws IOException {

        File file = new File(fileName);

        if (!file.exists()) {

            file.createNewFile();
        }

        try {

            PrintWriter pw = new PrintWriter(file);

            for ( int i = 0; i < stepDefaunder.size(); i++) {
                pw.println();
                pw.print(stepDefaunder.get(i));
            }

            pw.close();

        } catch (Exception e) {

            e.printStackTrace();

        }
    }


    public void cleanFile() {
        File file = new File(fileName);
        file.delete();
    }

}