import SK.gnome.morena.*;
import SK.gnome.twain.*;

public class ADFTwainExample
{ public static void main(String[] args) throws MorenaException
  { TwainSource source=TwainManager.selectSource(null);
    System.err.println("Selected source is "+source);
    if (source!=null)
    { source.setFeederEnabled(true);
      source.setAutoFeed(true);
      source.setTransferCount(5);
      int count=1;
      do
      { MorenaImage image=new MorenaImage(source);
        System.err.println("Size of acquired image "+(count++)+" is "
             +image.getWidth()+" x "
             +image.getHeight()+" x "
             +image.getPixelSize());
      }
      while (source.hasMoreImages());
    }
    TwainManager.close();
  }
}
