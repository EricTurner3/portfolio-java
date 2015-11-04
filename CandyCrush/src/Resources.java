import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by Eric Turner on 11/18/2014.
 */
public class Resources {

    //Gem Images
    public static ImageIcon amethyst;
    public static ImageIcon banana;
    public static ImageIcon emerald;
    //Images for when Gems are hovered over
    public static ImageIcon amethyst_glow;
    public static ImageIcon banana_glow;
    public static ImageIcon emerald_glow;

    //Misc files and Images
    public static BufferedImage background;
    public static ImageIcon scoreLabel;
    public static Font font;
    public static Font Dark_Crystal;


    public Resources() {
        try //Load Images
        {
            amethyst = new ImageIcon(this.getClass().getResource("Gems/amethyst.png"));
            banana = new ImageIcon(this.getClass().getResource("Gems/banana.png"));
            emerald = new ImageIcon(this.getClass().getResource("Gems/emerald.png"));
            amethyst_glow = new ImageIcon(this.getClass().getResource("Gems/amethyst_glow.png"));
            banana_glow = new ImageIcon(this.getClass().getResource("Gems/banana_glow.png"));
            emerald_glow = new ImageIcon(this.getClass().getResource("Gems/emerald_glow.png"));
            background = ImageIO.read(getClass().getClassLoader().getResourceAsStream("Images/background.png"));
            scoreLabel = new ImageIcon(this.getClass().getResource("Images/score.gif"));

        } catch (Exception localException) {
        }

        try //Load Custom Font
        {
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();

            font = Font.createFont(Font.TRUETYPE_FONT, new File("Fonts/dc_s.ttf"));

            Dark_Crystal = font.deriveFont(36);

            ge.registerFont(font);
        } catch (IOException | FontFormatException e) {
        }
    }


}
