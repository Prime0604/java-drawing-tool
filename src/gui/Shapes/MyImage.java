package gui.Shapes;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import javax.imageio.ImageIO;

import gui.Main.MyDrawing;

public class MyImage extends MyDrawing implements Serializable {
    private static final long serialVersionUID = 1L;
    private transient BufferedImage image;
    private String imagePath;
    private Rectangle region;

    public MyImage(String filePath, int x, int y) {
        this.imagePath = filePath;
        loadImage();
        setLocation(x, y);
        if (image != null) {
            setSize(image.getWidth()/10, image.getHeight()/10);
        }
        setisFill(false);
    }

    private void loadImage() {
        try {
            image = ImageIO.read(new File(imagePath));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void draw(Graphics2D g) {
        super.draw(g);
        if (image != null) {
            g.drawImage(image, getX(), getY(), getW(), getH(), null);
        }
        setRegion();
    }

    @Override
    public void setRegion() {
        if (image != null) {
            region = new Rectangle(getX(), getY(), getW(), getH());
        }
    }

    @Override
    public boolean intersect(MyRectangle rect) {
        int x = rect.getX();
        int y = rect.getY();
        int w = rect.getW();
        int h = rect.getH();

        if (w < 0) {
            x += w;
            w *= -1;
        }
        if (h < 0) {
            y += h;
            h *= -1;
        }
        return region != null && region.intersects(x, y, w, h);
    }

    @Override
    public boolean contains(int x, int y) {
        return region != null && region.contains(x, y);
    }

    private void writeObject(ObjectOutputStream out) throws IOException {
        out.defaultWriteObject();
    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
        loadImage();
    }
}
