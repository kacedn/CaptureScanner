/*
 * $Id$
 *
 * Copyright (c) 2009 Gnome spol. s r.o. All Rights Reserved.
 *
 * This software is the confidential and proprietary information of
 * Gnome spol. s r.o. You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms
 * of the license agreement you entered into with Gnome.
 */

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

public class TwainFrameTL_XCapability extends Capability
{
  private TwainActivity twainActivity;
  private Double value;
  private TwainFrameCapability frameCapability;
  
  public TwainFrameTL_XCapability(TwainActivity twainActivity, Properties properties, TwainFrameCapability frameCapability)
  {
    this.twainActivity = twainActivity;
    this.frameCapability=frameCapability;
    
    name="tl-x";
    value=(Double) getDefaultValue();
//    System.out.println("TwainFrameTL_XCapability.TwainFrameTL_XCapability()value="+value);
    if (properties.containsKey(name))
    {
      value = Double.valueOf(properties.getProperty(getName(), String.valueOf(getDefaultValue())));
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
      max=twainActivity.getSource().getPhysicalWidth();
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
//    System.out.println("TwainFrameTL_XCapability.getValue()="+value);
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

  public void setValue(String argument) throws NumberFormatException, Exception
  {
//    System.out.println("TwainFrameTL_XCapability.setValue()value="+value);
    value=Double.valueOf(argument);
    frameCapability.setTL_XValue(Double.parseDouble(argument));
    for (Enumeration e2 = listeners.elements() ; e2.hasMoreElements() ;)
      ((PropertyChangeListener)e2.nextElement()).propertyChange(new PropertyChangeEvent(this,null,null,null));
  }
  
  public String getTitle()
  {
    return "Frame top left x";
  }

}
