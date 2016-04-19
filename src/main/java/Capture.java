import SK.gnome.morena.Morena;
import SK.gnome.morena.MorenaException;
import SK.gnome.morena.MorenaImage;
import SK.gnome.morena.MorenaSource;
import SK.gnome.sane.SaneConnection;
import SK.gnome.twain.TwainManager;
import SK.gnome.twain.TwainSource;

/**
 * Created by nka on 19/04/2016.
 */
public class Capture {
    public static void main(String[] args) throws MorenaException{
        System.out.println("Start capture!!");

// Selection de la source de capture TWAIN ou sane thanks to Morena framework
//        MorenaSource source=Morena.selectSource(null); // show dialog Box with two choice Twain or Sane
//        SaneConnection.selectSource(null);
        MorenaSource source=TwainManager.selectSource(null);

        System.err.println("Selected source is "+source);

        if (source!=null){
            //source.maskUnsupportedCapabilityException(false); // Lesson 3
            //source.maskBadValueException(false);              // Lesson 3
            source.setVisible(false);                         // Lesson 2
            source.setColorMode();                            // Lesson 2
            source.setResolution(300);                        // Lesson 2
            ((TwainSource)source).setUnits(TwainSource.TWUN_CENTIMETERS);
            source.setFrame(0, 0, 7.8, 10.5);
            System.err.println("Image resolution is "+source.getResolution());
            MorenaImage image=new MorenaImage(source);
            System.err.println("Size of acquired image is "
                    +image.getWidth()+" x "
                    +image.getHeight()+" x "
                    +image.getPixelSize());
        }
        Morena.close();
    }
}
