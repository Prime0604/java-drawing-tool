package gui.Main;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Vector;

import javax.swing.JFileChooser;

import gui.Shapes.MyImage;

public class FileInput_OutputStream {
    private Mediator mediator;

    public FileInput_OutputStream(Mediator mediator) {
        this.mediator = mediator;
    }

    public void loadDrawingsFromFile() {
        JFileChooser fileChooser = new JFileChooser();
        int returnValue = fileChooser.showOpenDialog(null);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(file))) {
                Vector<MyDrawing> v = (Vector<MyDrawing>) in.readObject();
                System.out.println("Loaded drawings: " + v.size());
                mediator.setDrawings(v);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    public void saveDrawingsToFile() {
        JFileChooser fileChooser = new JFileChooser();
        int returnValue = fileChooser.showSaveDialog(null);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file))) {
                out.writeObject(mediator.getDrawings());
                out.flush();
                System.out.println("Saved drawings: " + mediator.getDrawings().size());
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    public void imageChooser() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Choose an image file");
        fileChooser.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter("Image files", "jpg", "png", "gif"));
        int returnValue = fileChooser.showOpenDialog(null);

        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            String filePath = file.getAbsolutePath();
            MyImage myImage = new MyImage(filePath, 100, 100);
            mediator.addDrawing(myImage);
        }
    }
}
