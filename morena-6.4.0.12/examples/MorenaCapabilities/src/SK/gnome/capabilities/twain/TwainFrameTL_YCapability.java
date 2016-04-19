package SK.gnome.capabilities.twain;

import java.awt.event.ActionEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Enumeration;
import java.util.Properties;
import java.util.Vector;

import javax.swing.JComboBox;

import SK.gnome.capabilities.Capabilities;
import SK.gnome.capabilities.Capability;
import SK.gnome.twain.TwainException;

public class TwainFrameTL_YCapability extends Capability
{
  private TwainActivity twainActivity;
  private Object value;
  private TwainFrameCapability frameCapability;
  
  public TwainFrameTL_YCapability(TwainActivity twainActivity, Properties properties, TwainFrameCapability frameCapability)
  {
    this.twainActivity = twainActivity;
    this.frameCapability=frameCapability;
    
    name="tl-y";
    value=getDefaultValue();
    
    if (properties.containsKey(name))
    {
      value = Double.valueOf(properties.getProperty(getName(),String.valueOf(getDefaultValue())));
      selected=true;
    }
  }

  public Object getDefaultValue()
  {
    return 0.;
  }

  public Object[] getSupportedValues()
  {
    Object[] supportedValues=null;
    double min=0, max=0;
    
    try
    {
      max=twainActivity.getSource().getPhysicalHeight();
    } catch (TwainException e)
    {
      e.printStackTrace();
    }
    
    double quant =computeQuant(min, max);
    Vector<String> v = new Vector<String>();
    v.add(String.valueOf(min));
    double value = ((int)min/(int)quant)*(int)quant+(int)quant;
    while (value < max)
    {
      v.add(String.valueOf(value));
      value+=quant;
    }
    v.add(String.valueOf(max));
    supportedValues=  v.toArray();
    return supportedValues;
  }

  public Object getValue()
  {
//    System.out.println("TwainFrameTL_YCapability.getValue()="+value);
    return value;
  }

  public boolean isSetMethodSupported()
  {
    return true;
  }

  public boolean isSupported()
  {
    return true;
  }

  public void setValue(String argument) throws Exception
  {
    value=Double.valueOf(argument);
    frameCapability.setTL_YValue(Double.parseDouble(argument));
    for (Enumeration e2 = listeners.elements() ; e2.hasMoreElements() ;)
      ((PropertyChangeListener)e2.nextElement()).propertyChange(new PropertyChangeEvent(this,null,null,null));
  }

  public String getTitle()
  {
    return "Frame top left y";
  }
}
