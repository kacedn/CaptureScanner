import SK.gnome.capabilities.twain.TwainActivity;
import SK.gnome.morena.Morena;
import SK.gnome.morena.MorenaException;
import SK.gnome.morena.MorenaImage;
import SK.gnome.morena.MorenaSource;
import SK.gnome.sane.SaneConnection;
import SK.gnome.twain.TwainManager;
import SK.gnome.twain.TwainSource;
import SK.gnome.capabilities.*;

import java.util.ArrayList;

/**
 * Created by nka on 19/04/2016.
 */
public class Capture {
    public static void main(String[] args) throws Exception {
        System.out.println("Capture started!!");

// Step 1 : Selection de la source
        // MorenaSource source=Morena.selectSource(null); // Affiche une fenêtre de dialogue, afin de choisir entre les protocoles Twain ou Sane
        // TwainManager.selectSource(null); // SaneConnection.selectSource(null);
        TwainSource[] sources = TwainManager.listSources();

        System.out.println("List of sources :");
        for (TwainSource s : sources) {
            System.out.println(s);
        }

        TwainSource sourceDefault=TwainManager.getDefaultSource();  // Pour utilisé la dernière source utilisée
        System.err.println("Default source is "+sourceDefault);

        TwainSource source=TwainManager.selectSource(null);
        System.err.println("Selected source is "+source);

        TwainActivity twActivity = new TwainActivity(null, source.toString());

        ArrayList<Capability> capabilities = twActivity.getCapabilities();

        System.err.println("Capabilities of source : "+source.toString());
        for (Capability cap:capabilities) {
            System.err.println("Capability name : "+cap.getName());
        }
        if (source!=null){
// Step 2 : Configuration de la source

            // Par défault, les exceptions de setting sont ignorées, les deux lignes ci desssous permettent de les activer.
            // source.maskUnsupportedCapabilityException(false);
            // source.maskBadValueException(false);

            source.setVisible(false); // désactive la fenetre de la source twain
            source.setColorMode();
            // source.setGrayScaleMode();
            source.setResolution(300);

            // Set specific frame of twain source
            // ((TwainSource)source).setUnits(TwainSource.TWUN_CENTIMETERS);
            // source.setFrame(0,0,20.99, 29.70);

            // Automatic frame
            // source.setUndefinedImageSize(true);
            // source.setAutomaticBorderDetection(true);

            // Set a specific size
            source.getSupportedSupportedSizes();
            source.setSupportedSizes(TwainSource.TWSS_A4);

            System.err.println("Image resolution is "+source.getResolution());

            // Functionnality "Duplex scanning" => scan recto verso
            int isDuplexAvailable = ((TwainSource)source).getDuplex(); // Gets CAP_DUPLEX capability value.
            System.out.println("CAP_DUPLEX : " + isDuplexAvailable);
            if(((TwainSource)source).getDuplexEnabled()){
                ((TwainSource)source).setDuplexEnabled(true);
            }

// Step 3 : Capture image

            // Automatic Document Feeder
            int numberLimitPageToScan = -1; // -1 pour tout scanner dans la bannette
            source.setFeederEnabled(true);
            source.setAutoFeed(true);
            source.setTransferCount(numberLimitPageToScan);

            int count=1;
            do
            {
                //scanning activity
                MorenaImage image=new MorenaImage(source);
                System.err.println("Size of acquired image "+(count++)+" is "
                        +image.getWidth()+" x "
                        +image.getHeight()+" x "
                        +image.getPixelSize());
            }
            while (source.hasMoreImages());
        }

// Step 4 : Close
        Morena.close();
    }
}
