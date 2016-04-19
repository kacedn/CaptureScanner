import SK.gnome.morena.Morena;
import SK.gnome.morena.MorenaException;
import SK.gnome.morena.MorenaImage;
import SK.gnome.morena.MorenaSource;
import SK.gnome.twain.TwainManager;
import SK.gnome.twain.TwainSource;

public class HelloWorld
{ public static void main(String[] args) throws MorenaException
  { MorenaSource source=Morena.selectSource(null);
    System.err.println("Selected source is "+source);
    if (source!=null)
    { //source.maskUnsupportedCapabilityException(false); // Lesson 3
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
