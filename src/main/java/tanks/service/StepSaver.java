package tanks.service;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class StepSaver {

    String fileName = "stepOfGame.txt";
    private List<String>stepTank;

    public StepSaver( List<String>stepTank) {

        this.stepTank = stepTank;

    }

    public void save() throws IOException {

        File file = new File(fileName);

        if (!file.exists()) {

            file.createNewFile();
        }

        try {

            PrintWriter pw = new PrintWriter(file);

            for ( int i = 0; i < stepTank.size(); i++) {

                pw.println();
                pw.print(stepTank.get(i));
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

