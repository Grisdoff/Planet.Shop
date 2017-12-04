package at.htl.planetshop.logic;

import javax.imageio.ImageIO;
import javax.xml.bind.DatatypeConverter;
import java.awt.*;
import java.awt.image.RenderedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class Converter {

    private Converter() {

    }

    public static String convertImage(Image image) throws IOException {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        ImageIO.write((RenderedImage)image, "png", (OutputStream)stream);
        return DatatypeConverter.printBase64Binary(stream.toByteArray());
    }
}
