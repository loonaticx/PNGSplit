import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class main {
    public static void main(String[] args) throws IOException {
        File input = new File("src/test1.png");
        new convertImage(input); //this should get the name of the file, which is grabbed from main method.
    }
}
