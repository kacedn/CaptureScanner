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

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.FocusEvent;
import java.io.FileInputStream;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Properties;

import javax.swing.JOptionPane;

import SK.gnome.capabilities.Capabilities;
import SK.gnome.capabilities.Capability;
import SK.gnome.capabilities.CapabilityDialog;
import SK.gnome.morena.MorenaImage;
import SK.gnome.twain.TwainConstants;
import SK.gnome.twain.TwainException;
import SK.gnome.twain.TwainManager;
import SK.gnome.twain.TwainSource;


public class TwainActivity extends Capabilities
{
  static final int INT=0;
  static final int DOUBLE=1;
  static final int BOOLEAN=2;
  static final int STRING=3;
  
  static final String CAPABILITY_NAMES[][]=
  { 
    { "Alarms",  "int", "TWAL"},
    { "AlarmVolume", "int", null}, 
    { "AudioFileFormat",  "int", "TWAF"},
    { "AudioTransferMech",  "int", null},
    { "Author",  "string", null}, 
    { "AutoBright",  "boolean", null}, 
    { "AutoFeed", "boolean", null}, 
    { "AutomaticBorderDetection", "boolean", null}, 
    { "AutomaticCapture", "int", null}, 
    { "AutomaticDeskew",  "boolean", null}, 
    { "AutomaticRotate",  "boolean", null}, 
    { "AutoScan",  "boolean", null}, 
    { "BarCodeDetectionEnabled",  "boolean", null}, 
    { "BarCodeMaxRetries",  "int", null}, 
    { "BarCodeMaxSearchPriorities", "int", null}, 
    { "BarCodeSearchMode",  "int", "TWBD"}, 
    { "BarCodeSearchPriorities",  "int", "TWBT"},
    { "BarCodeTimeout",  "int", null}, 
    { "BatteryMinutes",  "int", null}, 
    { "BatteryPercentage",  "int", null}, 
    { "BitDepth",  "int", null}, 
    { "BitDepthReduction",  "int", "TWBR"}, 
    { "BitOrder",  "int", "TWBO"}, 
    { "BitOrderCodes", "int", "TWBO"}, 
    { "Brightness", "double", null}, 
    { "CameraPreviewUI", "boolean", null}, 
    { "Caption", "string", null}, 
    { "CCITTKFactor",  "int", null}, 
    { "ClearBuffers", "int", "TWCB"}, 
    { "Compression",  "int", "TWCP"}, 
    { "Contrast",  "double", null}, 
    { "CustHalfTone", "int", null},
    { "CustomDSData",  "boolean", null}, 
    { "DeviceEvent",  "int", "TWDE"},
    { "DeviceOnLine",  "boolean", null}, 
    { "DeviceTimeDate", "string", null}, 
    { "Duplex",  "int", "TWDX"}, 
    { "DuplexEnabled",  "boolean", null}, 
    { "EnableDSUIOnly",  "boolean", null}, 
    { "Endorser",  "int", null}, 
    { "ExposureTime", "double", null}, 
    { "ExtendedCaps","int", null},
    { "ExtImageInfo",  "boolean", null}, 
    { "FeederAlignment",  "int", "TWFA"}, 
    { "FeederEnabled",  "boolean", null}, 
    { "FeederLoaded",  "boolean", null}, 
    { "FeederOrder", "int", "TWFO"}, 
    { "Filter",  "int", "TWFT"}, 
    { "FlashUsed",  "int", "TWFL"}, 
    { "FlipRotation",  "int", "TWFR"}, 
    { "Gamma",  "double", null}, 
    { "Halftones",  "string", null}, 
    { "Highlight", "double", null}, 
    { "ImageDataSet",  "int", null}, 
    { "ImageFileFormat",  "int", "TWFF"}, 
    { "ImageFilter",  "int", "TWIF"}, 
    { "Indicators", "boolean", null}, 
    { "JobControl",  "int", "TWJC"}, 
    { "JPEGPixelType",  "int", "TWPT"}, 
    { "JPEGQuality", "int", "TWJQ"}, 
    { "LampState",  "boolean", null}, 
    { "Language",  "int", "TWLG"}, 
    { "LightPath",  "int", "TWLP"}, 
    { "LightSource", "int", "TWLS"}, 
    { "MaxBatchBuffers",  "int", null}, 
    { "MinimumHeight",  "double", null}, 
    { "MinimumWidth", "double", null}, 
    { "NoiseFilter",  "int", "TWNF"}, 
    { "Orientation",  "int", "TWOR"}, 
    { "Overscan",  "int", "TWOV"}, 
    { "PaperDetectable", "boolean", null}, 
    { "PatchCodeDetectionEnabled",  "boolean", null}, 
    { "PatchCodeMaxRetries",  "int", null}, 
    { "PatchCodeMaxSearchPriorities",  "int", null}, 
    { "PatchCodeSearchMode",  "int", "TWBD"}, 
    { "PatchCodeSearchPriorities",  "int", null},
    { "PatchCodeTimeout",  "int", null}, 
    { "PhysicalHeight",  "double", null}, 
    { "PhysicalWidth",  "double", null}, 
    { "PixelFlavor",  "int", "TWPF"}, 
    { "PixelFlavorCodes", "int", "TWPF"}, 
    { "PixelType",  "int", "TWPT"}, 
    { "PlanarChunky",  "int", "TWPC"}, 
    { "PowerSupply",  "int", null}, 
    { "Printer", "int", "TWPR"}, 
    { "PrinterEnabled", "boolean", null}, 
    { "PrinterIndex",  "int", null}, 
    { "PrinterMode",  "int", null}, 
    { "PrinterString",  "string", null}, 
    { "PrinterSuffix", "string", null}, 
    { "ReacquireAllowed",  "int", null}, 
    { "Rotation",  "double", null}, 
    { "SerialNumber",  "string", null}, 
    { "Shadow",  "double", null}, 
    { "SupportedBarCodeTypes",  "int", "TWBT"},
    { "SupportedCaps",  "int", null},
    { "SupportedPathCodeTypes", "int", null},
    { "SupportedSizes",  "int", "TWSS"}, 
    { "Threshold", "double", null}, 
    { "ThumbnailsEnabled", "boolean", null}, 
    { "TimeBeforeFirstCapture", "int", null}, 
    { "TimeBetweenCaptures", "int", null}, 
    { "TimeDate",  "string", null}, 
    { "TimeFill",  "int", null}, 
    { "TransferCount",  "int", null}, 
    { "TransferMech",  "int", null}, 
    { "UIControllable",  "boolean", null}, 
    { "UndefinedImageSize",  "boolean", null},
    { "Units",  "int", "TWUN"}, 
    { "XNativeResolution",  "double", null}, 
    { "XResolution",  "double", null}, 
    { "XScaling",  "double", null}, 
    { "YNativeResolution",  "double", null}, 
    { "YResolution",  "double", null}, 
    { "YScaling", "double", null}, 
    { "ZoomFactor", "int", null},
  };
  
  static final Hashtable<String, Hashtable<String, String>> twainConstants=new Hashtable<String, Hashtable<String, String>>();
  static final Hashtable<String, Hashtable<String, String>> twainConstantsValues=new Hashtable<String, Hashtable<String, String>>();
  
  {
    Field[] constants=TwainConstants.class.getFields();
    String key,s,s2=null;
    Hashtable<String, String> twainConstants2=null, twainConstantsValues2=null;
    
    for (int i = 0; i < constants.length; i++)
    {
      s= constants[i].getName();
      s2=null;
      if((s.startsWith("TW"))&&('_'==s.charAt(4)))
      {
        key=s.substring(0, 4);
        if(!twainConstants.containsKey(key))
        {
          twainConstants.put(key, twainConstants2=new Hashtable<String, String>());
          twainConstantsValues.put(key, twainConstantsValues2=new Hashtable<String, String>());
        }
        else
        {
          twainConstants2=twainConstants.get(key);
          twainConstantsValues2=twainConstantsValues.get(key);
        }
        s2=s.substring(5, s.length());
        
        try
        {
          String value=String.valueOf(constants[i].get(null));
          twainConstants2.put(s2, value);
          twainConstantsValues2.put(value,s2);
        } catch (IllegalArgumentException e)
        {
          e.printStackTrace();
        } catch (IllegalAccessException e)
        {
          e.printStackTrace();
        }
      }
      
    }
  }
  
  private TwainSource source;
  private TwainFrameCapability frameCapability;
  
  public TwainActivity(Component centerPanel, String sourceName) throws Exception
  {
    this.centerPanel=centerPanel;
    this.sourceName=sourceName;
    
    openTwainSource(sourceName);
    
    allCapabilities=new ArrayList<Capability>();
    properties=new Properties();
    
    sourceValidFileName = "TWAIN_"+makeFileNameValid(source.toString())+".properties";
    try
    {
      properties.load(new FileInputStream(System.getProperty("user.home")+"/.morena/"+sourceValidFileName));
    } catch (Exception e)
    {
      if(Capabilities.debug)
        System.out.println("TwainActivity.TwainActivity()"+e.getMessage()+" caused by "+e.getCause());
    }
    
    boolean previousBadValueExceptionMask=true;
    boolean previousUnsupportedCapabilityException=true;
    try
    {
      // Beginning the version 6.4.0.4 Morena returns the previous value of these methods.
      // Replace next two lines with the following commented two lines in case of previous versions of Morena.
      previousBadValueExceptionMask=getSource().maskBadValueException(false);
      previousUnsupportedCapabilityException=getSource().maskUnsupportedCapabilityException(false);
      //getSource().maskBadValueException(false);
      //getSource().maskUnsupportedCapabilityException(false);
    } catch (TwainException e)
    {
      if(Capabilities.debug)
        System.out.println("TwainActivity.TwainActivity()"+e.getMessage()+" caused by "+e.getCause());
    }
    
    String textType, name, constantAbbreviation;
    int type;
    for (int i = 0; i < CAPABILITY_NAMES.length; i++)
    {
      name = CAPABILITY_NAMES[i][0];
      textType=CAPABILITY_NAMES[i][1];
      
      if(textType.equals("int"))
        type=INT;
      else if (textType.equals("double"))
        type=DOUBLE;
      else if (textType.equals("boolean"))
        type=BOOLEAN;
      else 
        type=STRING;
      
      constantAbbreviation=CAPABILITY_NAMES[i][2];
      
      allCapabilities.add( new TwainCapability(this, name, type, constantAbbreviation, properties));
    }
    
    frameCapability=new TwainFrameCapability(this);
    
    TwainFrameTL_XCapability tl_x;
    allCapabilities.add(tl_x=new TwainFrameTL_XCapability(this, properties, frameCapability));
    TwainFrameTL_YCapability tl_y;
    allCapabilities.add(tl_y=new TwainFrameTL_YCapability(this, properties, frameCapability));
    TwainFrameBR_XCapability br_x;
    allCapabilities.add(br_x=new TwainFrameBR_XCapability(this, properties, frameCapability));
    TwainFrameBR_YCapability br_y;
    allCapabilities.add(br_y=new TwainFrameBR_YCapability(this, properties, frameCapability));
    
    //Set Frame if it is selected.
    if (properties.containsKey("tl-x"))
    {
      try
      {
        frameCapability.setFrame((Double) tl_x.getValue(), (Double) tl_y.getValue(), (Double) br_x.getValue(),(Double) br_y.getValue());
      } catch (Exception e)
      {
        if(Capabilities.debug)
          System.out.println("TwainActivity.TwainActivity()"+e.getMessage()+" caused by "+e.getCause());
      }
    }
    
    restartAfterPreview = Boolean.parseBoolean(properties.getProperty("restartAfterPreview","true"));
    sleepTimeBeforeRestart = Integer.parseInt(properties.getProperty("sleepTimeBeforeRestart","3000"));
    
    try
    {
      getSource().maskBadValueException(previousBadValueExceptionMask);
      getSource().maskUnsupportedCapabilityException(previousUnsupportedCapabilityException);
    } catch (TwainException e)
    {
      if(Capabilities.debug)
        System.out.println("TwainActivity.TwainActivity()"+e.getMessage()+" caused by "+e.getCause());
    }
  }

  private void openTwainSource(String sourceName) throws Exception
  {
      if (sourceName.equals(""))
        source = TwainManager.getDefaultSource();
      else
      {
        TwainSource[] sources = TwainManager.listSources();
        if(null==sources)
          throw new Exception("No TWAIN Data Source installed.");
        	
        for (int i = 0; i < sources.length; i++)
        {
          if (sources[i].toString().equals(sourceName))
          {
            source = sources[i];
            break;
          }
        }
      }
      source.setVisible(false);
  }
  
  public int showDialogWindow()
  {
    boolean previousBadValueExceptionMask=true;
    boolean previousUnsupportedCapabilityException=true;
    try
    {
      // Beginning the version 6.4.0.4 Morena returns the previous value of these methods.
      // Replace next two lines with the following commented two lines in case of previous versions of Morena.
      previousBadValueExceptionMask=getSource().maskBadValueException(false);
      previousUnsupportedCapabilityException=getSource().maskUnsupportedCapabilityException(false);
      //getSource().maskBadValueException(false);
      //getSource().maskUnsupportedCapabilityException(false);
    } catch (TwainException e)
    {
      if(Capabilities.debug)
        System.out.println("TwainActivity.showDialogWindow()"+e.getMessage()+" caused by "+e.getCause());
    }
    frame = JOptionPane.getFrameForComponent(centerPanel);
    dialogWindow=new CapabilityDialog(frame, this);
    dialogWindow.setVisible(true);
    try
    {
      getSource().maskBadValueException(previousBadValueExceptionMask);
      getSource().maskUnsupportedCapabilityException(previousUnsupportedCapabilityException);
    } catch (TwainException e)
    {
      if(Capabilities.debug)
        System.out.println("TwainActivity.showDialogWindow()"+e.getMessage()+" caused by "+e.getCause());
    }
    
    return dialogWindow.getReturnValue();
  }
  
  public MorenaImage getPreviewImage() throws Exception
  {
    String xResolution=null, yResolution=null, tl_x=null, tl_y=null, br_x=null, br_y=null, maxWidth=null, maxHeight=null;
    properties.clear();
    String name;
    boolean setFrameSelected=false;
    
    for (Capability capability : getCapabilities())
    {
      name=capability.getName();
      
      if(null!=name)
      {
        if(capability.isSelected())
        {
          properties.put(name, String.valueOf(capability.getValue()));
          
          
          if (name.equals("tl-x"))
          {
            setFrameSelected=true;
            tl_x=String.valueOf(capability.getValue());
          }
          else if (name.equals("tl-y"))
            tl_y=String.valueOf(capability.getValue());
          else if (name.equals("br-x"))
          {
            br_x=String.valueOf(capability.getValue());
            Object[] supported = capability.getSupportedValues();
            maxWidth=(String)supported[supported.length-1];
          }
          else if (name.equals("br-y"))
          {
            br_y=String.valueOf(capability.getValue());
            Object[] supported = capability.getSupportedValues();
            maxHeight=(String)supported[supported.length-1];
          }
        }
        
        if(name.equals("XResolution"))
        {
          xResolution=(String)capability.getValue();
        }
        else if(name.equals("YResolution"))
        {
          yResolution=(String)capability.getValue();
        }
      }
    }
    
    source.setVisible(false);
    
    try
    {
      source.setXResolution(96);
      source.setYResolution(96);
    } catch (TwainException e)
    {
      if(Capabilities.debug)
        System.out.println("TwainActivity.getPreviewImage()"+e.getMessage()+" caused by "+e.getCause());
    }
    
    if(setFrameSelected)
    {
      try
      {
        source.setFrame(0, 0, Double.parseDouble(maxWidth), Double.parseDouble(maxHeight));
      } catch (TwainException e)
      {
        if(Capabilities.debug)
          System.out.println("TwainActivity.getPreviewImage()"+e.getMessage());;
      }
    }
    
    MorenaImage morenaImage= new MorenaImage(source);
    //Restart source if needed.
    if(restartAfterPreview)
    {
      close();
      
      try
      {
        Thread.sleep(sleepTimeBeforeRestart);
      } catch (InterruptedException e)
      {
        if(Capabilities.debug)
          System.out.println("TwainActivity.getPreviewImage()"+e.getMessage());
      }
      
      openTwainSource(sourceName);
    }
    //End of source restart.
    
    try
    {
      source.setXResolution(Double.parseDouble(xResolution));
      source.setYResolution(Double.parseDouble(yResolution));
    } catch (TwainException e1)
    {
      if(Capabilities.debug)
        System.out.println("TwainActivity.getPreviewImage()"+e1.getMessage()+" caused by "+e1.getCause());
    }
    
    if(setFrameSelected)
    {
      try
      {
        source.setFrame(Double.parseDouble(tl_x), Double.parseDouble(tl_y), Double.parseDouble(br_x), Double.parseDouble(br_y));
      } catch (TwainException e1)
      {
        if(Capabilities.debug)
          System.out.println("TwainActivity.getPreviewImage()"+e1.getMessage()+" caused by "+e1.getCause());
      }
    }
    
    for (Capability capability : allCapabilities)
    {
      name=capability.getName();
      if (capability.isSelected())
      {
        if (capability.isSetMethodSupported())
        {
          try
          {
            capability.setValue(properties.getProperty(name));
          } catch (Exception e)
          {
            if(Capabilities.debug)
              System.out.println("TwainActivity.getPreviewImage()"+e.getMessage()+" caused by "+e.getCause());
          }
        }
      }
    }
    
    return morenaImage; 
  }
  
  public String getSourceName()
  {
    return source.toString();
  }

  public void actionPerformed(ActionEvent e)
  {
    if (e.getActionCommand().equals("selectCapabilitiesButton"))
    {
      new TwainSelectCapabilitiesDialog(frame, this, dialogWindow);
    }
  }

  public TwainSource getSource()
  {
    return source;
  }

  public void close()
  {
      try
      {
        TwainManager.close();
      } catch (Exception exception)
      {
        exception.printStackTrace();
      }
  }

}
