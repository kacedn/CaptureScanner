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

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Properties;
import SK.gnome.capabilities.Capabilities;
import SK.gnome.capabilities.Capability;

public class TwainCapability extends Capability
{
  private TwainActivity twainActivity;
  private int type;
  protected boolean supported = true;
  private String constantAbbreviation = null;
  private String defaultValue = null;
  private String[] supportedValues = null;
  private boolean setMethodSupported = false;
  
  public TwainCapability(TwainActivity twainActivity, String name, int type, String constantAbbreviation, Properties properties)
  {
    this.twainActivity = twainActivity;
    this.name = name;
    title=name;
    this.type = type;
    this.constantAbbreviation = constantAbbreviation;

    // Is capability supported by the source?
    String value = getValue();
    if (null == value)
      supported = false;

    if (supported)
    {
      // Get default value from Twain driver.
      try
      {
        defaultValue = getDefaultValue();
      }
      catch (Exception e)
      {
        if(Capabilities.debug)
          System.out.println("TwainCapability.TwainCapability().getDefaultValue() failed: "+e.getMessage()+" caused by "+e.getCause());
      }

      // Get supported values from Twain driver.
      supportedValues = getSupportedValues();
      
      //Test if capability supports setting method.
      try
      {
        setValue(value);
        setMethodSupported=true;
      } catch (Exception e)
      {
        //System.out.println("TwainCapability.TwainCapability()."+name+".setMethodSupported="+setMethodSupported);
      }
      
      if ((setMethodSupported)&&(properties.containsKey(name)))
      {
        value = properties.getProperty(getName(), defaultValue);
        try
        {
          setValue(value);
        } catch (Exception e)
        {
          if(Capabilities.debug)
            System.out.println("TwainCapability.TwainCapability().setValue("+value+") failed: "+e.getMessage()+" caused by "+e.getCause());
        }
        selected = true;
      }
    }
  }

  public String getDefaultValue() throws Exception
  {
    String defaultValue = null;
    String methodName = "getDefault" + name;
    Class[] types = new Class[] {};
    Method method = twainActivity.getSource().getClass().getMethod(methodName, types);
    switch (type)
    {
    case TwainActivity.INT:
      Integer object = (Integer) method.invoke(twainActivity.getSource(), new Object[0]);
      defaultValue = String.valueOf(object);
      break;
    case TwainActivity.BOOLEAN:
      Boolean object2 = (Boolean) method.invoke(twainActivity.getSource(), new Object[0]);
      defaultValue = String.valueOf(object2);
      break;
    case TwainActivity.DOUBLE:
      Double object3 = (Double) method.invoke(twainActivity.getSource(), new Object[0]);
      defaultValue = String.valueOf(object3);
      break;
    case TwainActivity.STRING:
      defaultValue = (String) method.invoke(twainActivity.getSource(), new Object[0]);
      break;
    }
    if ((null != defaultValue) && (null != constantAbbreviation))
    {
      Hashtable<String, String> ht = TwainActivity.twainConstantsValues.get(constantAbbreviation);
      return ht.get(defaultValue);
    } else
      return defaultValue;
  }

  public void setValue(String argument) throws Exception
  {
    String methodName = "set" + name;
//    System.out.println("TwainCapability.setValue()"+methodName+": "+argument);
    if ((null != argument) && (null != constantAbbreviation))
    {
      Hashtable<String, String> ht = TwainActivity.twainConstants.get(constantAbbreviation);
      argument = ht.get(argument);
    }

    Class[] types;
    Method method;
    try
    {
      switch (type)
      {
      case TwainActivity.INT:
        types = new Class[] { int.class };
        method = twainActivity.getSource().getClass().getMethod(methodName, types);
        method.invoke(twainActivity.getSource(), new Object[] { new Integer(argument) });
        break;
      case TwainActivity.BOOLEAN:
        types = new Class[] { boolean.class };
        method = twainActivity.getSource().getClass().getMethod(methodName, types);
        method.invoke(twainActivity.getSource(), new Object[] { new Boolean(argument) });
        break;
      case TwainActivity.DOUBLE:
        types = new Class[] { double.class };
        method = twainActivity.getSource().getClass().getMethod(methodName, types);
        method.invoke(twainActivity.getSource(), new Object[] { new Double(argument) });
        break;
      case TwainActivity.STRING:
        types = new Class[] { String.class };
        method = twainActivity.getSource().getClass().getMethod(methodName, types);
        method.invoke(twainActivity.getSource(), new Object[] { new String(argument) });
        break;
      default:
        break;
      }
      
      for (Enumeration e2 = listeners.elements() ; e2.hasMoreElements() ;)
      {
        PropertyChangeListener listener=(PropertyChangeListener)e2.nextElement();
        listener.propertyChange(new PropertyChangeEvent(this,null,null,null));
      }
    } catch (Exception e)
    {
      for (Enumeration e2 = listeners.elements() ; e2.hasMoreElements() ;)
      {
        PropertyChangeListener listener=(PropertyChangeListener)e2.nextElement();
        listener.propertyChange(new PropertyChangeEvent(this,null,null,null));
      }
      throw e;
    } 
  }

  public String getValue()
  {
    String value = null;
    String methodName = "get" + name;
    Class[] types = new Class[] {};
    Method method;
    try
    {
      method = twainActivity.getSource().getClass().getMethod(methodName, types);
      switch (type)
      {
      case TwainActivity.INT:
        Integer object = (Integer) method.invoke(twainActivity.getSource(), new Object[0]);
        value = String.valueOf(object);
        break;
      case TwainActivity.BOOLEAN:
        Boolean object2 = (Boolean) method.invoke(twainActivity.getSource(), new Object[0]);
        value = String.valueOf(object2);
        break;
      case TwainActivity.DOUBLE:
        Double object3 = (Double) method.invoke(twainActivity.getSource(), new Object[0]);
        value = String.valueOf(object3);
        break;
      case TwainActivity.STRING:
        value = (String) method.invoke(twainActivity.getSource(), new Object[0]);
        break;
      }
    } catch (NoSuchMethodException e)
    {
//      if (Commons.debug)
//        System.err.println(methodName + ": NoSuchMethodException");
    } catch (SecurityException e)
    {
//      if (Commons.debug)
//        System.err.println(methodName + ": SecurityException");
    } catch (IllegalArgumentException e)
    {
//      if (Commons.debug)
//        System.err.println(methodName + ": IllegalArgumentException");
    } catch (IllegalAccessException e)
    {
//      if (Commons.debug)
//        System.err.println(methodName + ": IllegalAccessException");
    } catch (InvocationTargetException e)
    {
//      if (Commons.debug)
//        System.err.println(methodName + ": " + e.getCause());
    }
    if ((null != value) && (null != constantAbbreviation))
    {
      Hashtable<String, String> ht = TwainActivity.twainConstantsValues.get(constantAbbreviation);
      return ht.get(value);
    } else
      return value;
  }

  public String[] getSupportedValues()
  {
    String methodName = "getSupported" + name;
    try
    {
      supportedValues = null;
      Class[] types = new Class[] {};
      Method method = twainActivity.getSource().getClass().getMethod(methodName, types);
      switch (type)
      {
      case TwainActivity.INT:
        int[] object = (int[]) method.invoke(twainActivity.getSource(), new Object[0]);
        supportedValues = new String[object.length];
        for (int i = 0; i < object.length; i++)
          supportedValues[i] = String.valueOf(object[i]);
        break;
      case TwainActivity.BOOLEAN:
        boolean[] object2 = (boolean[]) method.invoke(twainActivity.getSource(), new Object[0]);
        supportedValues = new String[object2.length];
        for (int i = 0; i < object2.length; i++)
          supportedValues[i] = String.valueOf(object2[i]);
        break;
      case TwainActivity.DOUBLE:
        double[] object3 = (double[]) method.invoke(twainActivity.getSource(), new Object[0]);
        supportedValues = new String[object3.length];
        for (int i = 0; i < object3.length; i++)
          supportedValues[i] = String.valueOf(object3[i]);
        break;
      case TwainActivity.STRING:
        supportedValues = (String[]) method.invoke(twainActivity.getSource(), new Object[0]);
        break;
      }
    } catch (NoSuchMethodException e)
    {
//      if (Commons.debug)
//        System.err.println(methodName + ": NoSuchMethodException");
    } catch (SecurityException e)
    {
//      if (Commons.debug)
//        System.err.println(methodName + ": SecurityException");
    } catch (IllegalArgumentException e1)
    {
//      if (Commons.debug)
//        System.err.println(methodName + ": IllegalArgumentException");
    } catch (IllegalAccessException e)
    {
//      if (Commons.debug)
//        System.err.println(methodName + ": IllegalAccessException");
    } catch (InvocationTargetException e)
    {
//      if (Commons.debug)
//        System.err.println(methodName + ": " + e.getCause());
    }
    
    if ((null != supportedValues)&&(null != constantAbbreviation))
    {
      Hashtable<String, String> ht = TwainActivity.twainConstantsValues.get(constantAbbreviation);
      String[] sv = new String[supportedValues.length];
      for (int i = 0; i < sv.length; i++)
      {
        sv[i] = ht.get(supportedValues[i]);
      }
      return sv;
    }
    return supportedValues;
  }

  public boolean isSetMethodSupported()
  {
    return setMethodSupported;
  }

  public boolean isSupported()
  {
    return supported;
  }
  
}
