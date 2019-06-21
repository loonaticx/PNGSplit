import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ImageFilter;
import java.awt.image.RGBImageFilter;
import java.io.File;
import java.io.IOException;

/*
* Resources used:
* https://alvinalexander.com/blog/post/java/getting-rgb-values-for-each-pixel-in-image-using-java-bufferedi
* https://www.javaworld.com/article/2074105/making-white-image-backgrounds-transparent-with-java-2d-groovy.html
*/

//make new image with same dimensions as input
//printpixel --> make new pixel for new image
//export as *_a.rgb

public class convertImage implements importFile, exportFile {

    private File fileLoc;
    private BufferedImage importImage;
    private int width;
    private int height;
    private String fileName;

    public convertImage() throws IOException {
        fileLoc = new File("test1.png");
        importImage = ImageIO.read(fileLoc);
        importFile(importImage);
    }


    public convertImage(File fileLoc) throws IOException {
        System.out.println("start");
        this.fileLoc = fileLoc;
        this.fileName = fileLoc.getName();
        importImage = ImageIO.read(fileLoc);
        //importImage = ImageIO.read(this.getClass().getResource(fileName));
        importFile(importImage);
    }

    public BufferedImage importFile(BufferedImage image) {
        System.out.println("imported file");
        export(getAlphaGrid(image));
        return image;
    }

    public boolean export(BufferedImage outputImage) {
        boolean check = false;
        File outputFile = new File("saved.png");
        try {
            ImageIO.write(outputImage, "png", outputFile);
            check = true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return check;
    }

    public int getAlpha(int pixel) {
        return (pixel >> 24) & 0xff;
    }

    private BufferedImage getAlphaGrid(BufferedImage image) {
        width = image.getWidth();
        height = image.getHeight();
        int[][] AlphaGrid = new int[height][width];
        //System.out.println("width, height: " + width + ", " + height);

        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                AlphaGrid[row][col] = getAlpha(image.getRGB(col, row)); //ok so we have the alpha value, all we need to do is make R, G, B set to the same value, alpha
                //System.out.println(AlphaGrid[row][col]);
                Color Alpha = new Color(AlphaGrid[row][col], AlphaGrid[row][col], AlphaGrid[row][col]);
                image.setRGB(col, row, Alpha.getRGB());
            }
        }
        return image;
    }
}
