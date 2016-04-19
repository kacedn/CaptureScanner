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

package SK.gnome.capabilities.sane;

import java.awt.event.ActionEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Enumeration;
import java.util.Properties;
import java.util.Vector;

import javax.swing.JComboBox;

import SK.gnome.capabilities.Capabilities;
import SK.gnome.capabilities.Capability;
import SK.gnome.morena.MorenaOptionsDescriptor.DoubleRange;
import SK.gnome.morena.MorenaOptionsDescriptor.IntRange;
import SK.gnome.sane.SaneConstants;
import SK.gnome.sane.SaneOptionDescriptor;

public class SaneCapability extends Capability
{
  protected SaneActivity saneActivity;
  private SaneOptionDescriptor optionDescriptor;
  
  public SaneCapability(SaneActivity saneActivity, SaneOptionDescriptor optionDescriptor, Properties properties)
  {
    this.saneActivity = saneActivity;
    this.optionDescriptor = optionDescriptor;
    
    name = optionDescriptor.name;
    title = optionDescriptor.title;
    
    if ((null!=name)&&(properties.containsKey(name)))
    {
      String value = properties.getProperty(name);
      try
      {
        setValue(value);
      } catch (Exception e)
      {
        if(Capabilities.debug)
          System.out.println("SaneCapability.SaneCapability().setValue("+value+") failed: "+e.getMessage()+" caused by "+e.getCause());
      }
      selected = true;
    }
  }

  public Object[] getSupportedValues()
  {
    Object[] supportedValues = null;
    
    if (null != optionDescriptor.constraint)
    {
      if (String[].class.isInstance(optionDescriptor.constraint))
      {
        supportedValues = (String[]) optionDescriptor.constraint;
      } else if (int[].class.isInstance(optionDescriptor.constraint))
      {
        int[] v = (int[]) optionDescriptor.constraint;
        supportedValues = new String[v.length];
        for (int j = 0; j < v.length; j++)
        {
          if (optionDescriptor.type == SaneConstants.SANE_TYPE_FIXED)
            supportedValues[j] = String.valueOf((double) v[j] / SaneConstants.UNFIX_CONST);
          else
            supportedValues[j] = String.valueOf(v[j]);
        }
      } else if (IntRange.class.isInstance(optionDescriptor.constraint))
      {
        IntRange intRange = (IntRange) optionDescriptor.constraint;
        int min = intRange.min;
        int max = intRange.max;
        int quant = intRange.quant;
        if (quant < 0.01)
          quant =(int) computeQuant(min, max);
        Vector<String> v = new Vector<String>();
        int j = 0;
        int value = min;
        while (value <= max)
        {
          v.add(String.valueOf(value));
          j++;
          value = j * quant + min;
        }
        supportedValues = v.toArray();
      } else if (DoubleRange.class.isInstance(optionDescriptor.constraint))
      {
        DoubleRange doubleRange = (DoubleRange) optionDescriptor.constraint;
        double min = doubleRange.min;
        double max = doubleRange.max;
        double quant = doubleRange.quant;
        if (quant < 0.01)
          quant =computeQuant(min, max);
        Vector<String> v = new Vector<String>();
        int j = 0;
        double value = min;
        while (value <= max)
        {
          v.add(String.valueOf(value));
          j++;
          value = j * quant + min;
        }
        supportedValues = v.toArray();
      }
    }
    return supportedValues;
  }

  public Object getValue()
  {
    try
    {
//      System.out.println("SaneCapability.getValue()"+getName()+"="+saneActivity.getSource().getOption(name));
      return saneActivity.getSource().getOption(name);
    } catch (Exception e)
    {
//      if(Commons.debug)
//        System.out.println("SaneCapability.getValue() failed: "+e.getMessage()+" caused by "+e.getCause());
      return null;
    } 
  }

  public boolean isSetMethodSupported()
  {
    return true;
  }

  public boolean isSupported()
  {
    return true;
  }

  public void setValue(String value) throws Exception
  {
    if(Capabilities.debug)
        System.out.println("SaneCapability.setValue()."+name+"="+value);
      
    try
    {
      switch (optionDescriptor.type)
      { case SaneConstants.SANE_TYPE_BOOL:
          saneActivity.getSource().setOption(name, Boolean.valueOf(value));
          break;
        case SaneConstants.SANE_TYPE_INT:
          saneActivity.getSource().setOption(name, Integer.valueOf(value));
          break;
        case SaneConstants.SANE_TYPE_FIXED:
          saneActivity.getSource().setOption(name, Double.valueOf(value));
          break;
        case SaneConstants.SANE_TYPE_STRING:
          saneActivity.getSource().setOption(name, value);
          break;
        default:
      }
      
      if(Capabilities.debug)
        System.out.println("SaneCapability.setValue()."+name+".getOption()="+saneActivity.getSource().getOption(name));
      
      for (Enumeration e2 = listeners.elements() ; e2.hasMoreElements() ;)
        ((PropertyChangeListener)e2.nextElement()).propertyChange(new PropertyChangeEvent(this,null,null,null));
      
    } catch (Exception e)
    {
      for (Enumeration e2 = listeners.elements() ; e2.hasMoreElements() ;)
        ((PropertyChangeListener)e2.nextElement()).propertyChange(new PropertyChangeEvent(this,null,null,null));
      throw e;
    }
  }

  public int getType()
  {
    return optionDescriptor.type;
  }
  
  public int getCap()
  {
    return optionDescriptor.cap;
  }

  public String getTitle()
  {
    return optionDescriptor.title;
  }
}
