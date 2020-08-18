package org.game.files;
import org.game.model.*;
import org.game.view.*;

import javax.swing.*;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Scanner;

public class FileWorker {
    public static void readFile(PlayStateModel ps) throws Exception{
        InputStreamReader isr = new InputStreamReader(
                new FileInputStream(ps.fileToRead));
        Scanner in = new Scanner(isr);
        String[] line = {""};
        if (in.hasNextLine())
            line = in.nextLine().split(" ");

        if(line.length != 1) {
            for (int i = 0; i < line.length; ++i) {
                ps.getObs().add(new Obstacle(Integer.parseInt(line[i]), Integer.parseInt(line[++i])));
                if(Integer.parseInt(line[i-1]) > ps.finishX) ps.finishX = Integer.parseInt(line[i-1]);
            }
        }

        if (in.hasNextLine())
            line = in.nextLine().split(" ");

        if (line.length != 1) {
            for (int i = 0; i < line.length; ++i) {
                ps.getR().add(new RedZone(Integer.parseInt(line[i]), Integer.parseInt(line[++i])));
                if(Integer.parseInt(line[i-1]) > ps.finishX) ps.finishX = Integer.parseInt(line[i-1]);
            }
        }

        if (in.hasNextLine())
            line = in.nextLine().split(" ");

        if (line.length != 1) {
            for (int i = 0; i < line.length; ++i) {
                ps.getF().add(new Fireguy(Integer.parseInt(line[i]), Integer.parseInt(line[++i]), ps));
                if(Integer.parseInt(line[i-1]) > ps.finishX) ps.finishX = Integer.parseInt(line[i-1]);
            }
        }

        if (in.hasNextLine())
            line = in.nextLine().split(" ");

        if (line.length != 1) {
            for (int i = 0; i < line.length; ++i) {
                ps.getB().add(new Bonus(Integer.parseInt(line[i]), Integer.parseInt(line[++i])));
                if(Integer.parseInt(line[i-1]) > ps.finishX) ps.finishX = Integer.parseInt(line[i-1]);
            }
        }

        if (in.hasNextLine())
            line = in.nextLine().split(" ");

        if (line.length != 1) {
            for (int i = 0; i < line.length; ++i) {
                ps.getD().add(new Diamond(Integer.parseInt(line[i]), Integer.parseInt(line[++i])));
                if(Integer.parseInt(line[i-1]) > ps.finishX) ps.finishX = Integer.parseInt(line[i-1]);
            }
        }
        System.out.println(ps.finishX);
        in.close();
        isr.close();
    }

    public static String getFileName(){
        JFileChooser fc = new JFileChooser();
        fc.showDialog(null, "Open");
        if(fc.getSelectedFile() == null) return "";
        return fc.getSelectedFile().getAbsolutePath();
    }
}
