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

import java.awt.Component;
import java.awt.Cursor;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import SK.gnome.capabilities.Capabilities;
import SK.gnome.capabilities.Capability;
import SK.gnome.capabilities.CapabilityDialog;
import SK.gnome.morena.MorenaImage;
import SK.gnome.sane.SaneConnection;
import SK.gnome.sane.SaneException;
import SK.gnome.sane.SaneOptionDescriptor;
import SK.gnome.sane.SaneSource;

public class SaneActivity  extends Capabilities
{
  private String host="localhost";
  private int port=6566;
  private String userName="";
  private SaneConnection sc = null;
  private SaneSource source;

  public SaneActivity(Component centerPanel,String sourceDescription)
  {
    this.centerPanel=centerPanel;
    
    Cursor previousCursor=SwingUtilities.windowForComponent(centerPanel).getCursor();
    SwingUtilities.windowForComponent(centerPanel).setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
    
    int separator1 = sourceDescription.indexOf(';');
    sourceName=sourceDescription.substring(0, separator1);
    int separator2 = sourceDescription.indexOf(';', separator1+1);
    host=sourceDescription.substring(separator1+1,separator2);
    int separator3 = sourceDescription.indexOf(';', separator2+1);
    port=Integer.parseInt(sourceDescription.substring(separator2+1,separator3));
    userName=sourceDescription.substring(separator3+1);
//    System.out.println("SaneActivity.SaneActivity()sourceName="+this.sourceName+", host="+host+", port="+port+", userName="+userName);
    
    openSaneSource(sourceName, host, port, userName);
    
    if (null != source)
    {
      allCapabilities=new ArrayList<Capability>();
      properties=new Properties();
      
      sourceValidFileName = "SANE_"+makeFileNameValid(getSource().toString())+".properties";
//      System.out.println("SaneActivity.SaneActivity()sourceValidFileName="+sourceValidFileName);
      try
      {
        properties.load(new FileInputStream(System.getProperty("user.home")+"/.morena/"+sourceValidFileName));
      } catch (Exception e)
      {
        if(Capabilities.debug)
          System.out.println("SaneActivity.SaneActivity()"+e.getMessage()+" caused by "+e.getCause());
      }
      
      try 
      {
        SaneOptionDescriptor[] descriptor= getSource().getOptionDescriptors();
        SaneOptionDescriptor option;
        for (int i = 1; i < descriptor.length; i++) 
        {
          option = descriptor[i];
          allCapabilities.add( new SaneCapability(this, option, properties));
        }
      }
      catch (Exception e) 
      {
        if(Capabilities.debug)
          System.out.println("SaneActivity.SaneActivity()"+e.getMessage()+" caused by "+e.getCause());
      }
    }
    restartAfterPreview = Boolean.parseBoolean(properties.getProperty("restartAfterPreview","true"));
    sleepTimeBeforeRestart = Integer.parseInt(properties.getProperty("sleepTimeBeforeRestart","3000"));
    SwingUtilities.windowForComponent(centerPanel).setCursor(previousCursor);
  }

  private void openSaneSource(String sourceName, String host, int port, String userName)
  {
//    System.err.println("SaneActivity.openSaneSource() "+sourceName+", host="+host+", port="+port+", userName="+userName);
    try
    {
      sc = SaneConnection.connect(host, port, userName);
    } catch (SaneException e)
    {
      e.printStackTrace();
    } catch (IOException e)
    {
      e.printStackTrace();
    }
    SaneSource[] sourceArray = sc.listSources();
    if(null==sourceArray)
      JOptionPane.showMessageDialog(centerPanel, "Empty list of Sane sources.");
    if (sourceArray != null)
    {
      source = null; 
      SaneSource temp;
      for (int i = 0; i < sourceArray.length; i++)
      {
        temp = sourceArray[i];
//        System.err.println("SaneActivity.openSaneSource()"+temp);
        if (temp.toString().equals(sourceName))
        {
          source = temp;
          break;
        }
      }
    }
    if(null==source)
      JOptionPane.showMessageDialog(centerPanel, "No Sane source.");
//    System.err.println("SaneActivity.openSaneSource()===================================="+source);
  }

  private void openSaneSource()
  {
    openSaneSource(sourceName, host, port, userName);
  }

  public int showDialogWindow()
  {
//    System.out.println("SaneActivity.showDialogWindow()");
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
    } catch (Exception e)
    {
      if(Capabilities.debug)
        System.out.println("SaneActivity.showDialogWindow()"+e.getMessage()+" caused by "+e.getCause());
    }
    frame = JOptionPane.getFrameForComponent(centerPanel);
    dialogWindow=new CapabilityDialog(frame, this);
    dialogWindow.setVisible(true);
    try
    {
      getSource().maskBadValueException(previousBadValueExceptionMask);
      getSource().maskUnsupportedCapabilityException(previousUnsupportedCapabilityException);
      
    } catch (Exception e)
    {
      if(Capabilities.debug)
        System.out.println("SaneActivity.showDialogWindow()"+e.getMessage()+" caused by "+e.getCause());
    }
    
    return dialogWindow.getReturnValue();
  }

  public SaneConnection getSaneConnection()
  {
    return sc;
  }

  public SaneSource getSource()
  {
    return source;
  }

  public void close()
  {
//    System.err.println("SaneActivity.close()");
    if (null != sc)
    try
    {
      sc.close();
    } catch (Exception e)
    {
      e.printStackTrace();
    }
  }
  
  public MorenaImage getPreviewImage()
  {
    Object resolution=null;
    
    properties.clear();
    String name;
    for (Capability capability : getCapabilities())
    {
      name=capability.getName();
      
      if(null!=name)
      {
        try
        {
          if (capability.isSelected())
          {
            properties.put(name, String.valueOf(capability.getValue()));
            
            if (name.equals("tl-x"))
              capability.setValue("0");
            else if (name.equals("tl-y"))
              capability.setValue("0");
            else if (name.equals("br-x"))
            {
              Object[] supported = capability.getSupportedValues();
              double maxWidth=Double.parseDouble((String)supported[supported.length-1]);
              capability.setValue(String.valueOf(maxWidth));
            }
            else if (name.equals("br-y"))
            {
              Object[] supported = capability.getSupportedValues();
              double maxHeight=Double.parseDouble((String)supported[supported.length-1]);
              capability.setValue(String.valueOf(maxHeight));
            }
          }
        
        
          if(name.equals("preview"))
            capability.setValue("true");
          else if(name.equals("resolution"))
          {
            resolution=capability.getValue();
            capability.setValue("96");
          }
          
        } catch (Exception e)
        {
          if(Capabilities.debug)
            System.out.println("SaneActivity.getPreviewImage()"+e.getMessage()+" caused by "+e.getCause());
        }
      }
    }
    
    MorenaImage morenaImage= new MorenaImage(getSource());

    //Restart Sane source if needed.
    if(restartAfterPreview)
    {
      close();
      
      try
      {
        Thread.sleep(sleepTimeBeforeRestart);
      } catch (InterruptedException e)
      {
        if(Capabilities.debug)
          System.out.println("SaneActivity.getPreviewImage()"+e.getMessage()+" caused by "+e.getCause());
      }
      
      openSaneSource();
    }
    //End of Sane restart.
    
    for (Capability capability : allCapabilities)
    {
      name=capability.getName();
      if(null!=name)
      {
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
                System.out.println("SaneActivity.getPreviewImage()"+e.getMessage()+" caused by "+e.getCause());
            }
          }
        }
        
        if(name.equals("preview"))
          try
          {
            capability.setValue("false");
          } catch (Exception e)
          {
            if(Capabilities.debug)
              System.out.println("SaneActivity.getPreviewImage()"+e.getMessage()+" caused by "+e.getCause());
          }
        else if(name.equals("resolution"))
          try
          {
            capability.setValue(String.valueOf(resolution));
          } catch (Exception e)
          {
            if(Capabilities.debug)
              System.out.println("SaneActivity.getPreviewImage()"+e.getMessage()+" caused by "+e.getCause());
          }
      }
    }
    return morenaImage;
  }

  public String getSourceName()
  {
    try
    {
      return getSource().getName();
    } catch (SaneException e)
    {
      e.printStackTrace();
      return null;
    } catch (IOException e)
    {
      e.printStackTrace();
      return null;
    }
  }
  
  public void actionPerformed(ActionEvent e)
  {
    if (e.getActionCommand().equals("selectCapabilitiesButton"))
    {
      new SaneSelectOptionsDialog(frame, this, dialogWindow);
    }
  }

}
