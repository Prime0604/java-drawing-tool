package gui.Shapes;
import java.awt.geom.Line2D;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import gui.Main.MyDrawing;

public class LineDrawing extends MyDrawing {
    transient protected Line2D.Double from, to;

    private void writeObject(ObjectOutputStream out) throws IOException {
        out.writeDouble(from.x1);
        out.writeDouble(from.y1);
        out.writeDouble(to.x1);
        out.writeDouble(to.y1);
    }

    private void readObject(ObjectInputStream in) throws IOException {
        double fromx = in.readDouble();
        double fromy = in.readDouble();
        double tox = in.readDouble();
        double toy = in.readDouble();

        from = new Line2D.Double(fromx, fromy, tox, toy);
        to = new Line2D.Double(tox, toy, fromx, fromy);
    }
}
