import java.io.IOException;
import SK.gnome.morena.*;
import SK.gnome.sane.*;

public class ListSaneOptionsExample
{ public static void main(String[] args) throws MorenaException, IOException
  { SaneSource source=SaneConnection.selectSource(null);
    System.err.println("Selected source is "+source);
    if (source!=null)
    { SaneOptionDescriptor descriptors[]=source.getOptionDescriptors();
      for (int i=0; i<descriptors.length; i++)
      { System.err.println();
        System.err.println("Descriptor "+i+" - \""+descriptors[i].title+"\"");
        System.out.println("name=\""+descriptors[i].name
            +"\", type="+descriptors[i].type
            +", cap="+ descriptors[i].cap
            +", size="+descriptors[i].size
            +", unit="+descriptors[i].unit);
      }
    }
  }
}
