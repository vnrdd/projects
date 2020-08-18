package org.editor.files;
import org.editor.model.*;

import javax.swing.*;
import java.io.File;
import java.io.FileWriter;

public class FileWorker {
    public static void saveToFile(EditorStateModel es) throws Exception{
        StringBuilder tmp = new StringBuilder();
        int buf;
        for(int i = 0; i < es.getObs().size(); ++i){
            buf = es.getObs().get(i).getModel().getX() + es.getObs().get(i).getModel().getShift();
            tmp.append(buf);
            tmp.append(" ");
            tmp.append(es.getObs().get(i).getModel().getY());
            tmp.append(" ");
        } tmp.append("\n");

        for(int i = 0; i < es.getR().size(); ++i){
            buf = es.getR().get(i).getModel().getX() + es.getR().get(i).getModel().getShift();
            tmp.append(buf);
            tmp.append(" ");
            tmp.append(es.getR().get(i).getModel().getY());
            tmp.append(" ");
        } tmp.append("\n");

        for(int i = 0; i < es.getF().size(); ++i){
            buf = es.getF().get(i).getModel().getX() + es.getF().get(i).getModel().getShift();
            tmp.append(buf);
            tmp.append(" ");
            tmp.append(es.getF().get(i).getModel().getY());
            tmp.append(" ");
        } tmp.append("\n");

        for(int i = 0; i < es.getB().size(); ++i){
            buf = es.getB().get(i).getModel().getX() + es.getB().get(i).getModel().getShift();
            tmp.append(buf);
            tmp.append(" ");
            tmp.append(es.getB().get(i).getModel().getY());
            tmp.append(" ");
        } tmp.append("\n");

        for(int i = 0; i < es.getD().size(); ++i){
            buf = es.getD().get(i).getModel().getX() + es.getD().get(i).getModel().getShift();
            tmp.append(buf);
            tmp.append(" ");
            tmp.append(es.getD().get(i).getModel().getY());
            tmp.append(" ");
        } tmp.append("\n");

        JFileChooser fc = new JFileChooser();
        fc.showSaveDialog(null);
        File f = fc.getSelectedFile();
        if(f == null) return;
        es.fileToWrite = f.getAbsolutePath();
        try{
            FileWriter fw = new FileWriter(f);
            fw.write(tmp.toString());
            fw.close();
        } catch(Exception e){
            e.printStackTrace();
        }
    }
}
