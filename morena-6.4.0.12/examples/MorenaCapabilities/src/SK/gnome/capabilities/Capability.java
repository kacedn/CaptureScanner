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

package SK.gnome.capabilities;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.Vector;

import javax.swing.JComboBox;
import javax.swing.JTextField;

public abstract class Capability  implements ActionListener, FocusListener
{
  protected Vector listeners=new Vector();
  protected boolean selected = false;
  protected String name;
  protected String title;
  
  public abstract void setValue(String argument) throws Exception;
  public abstract Object getValue();
  public abstract Object[] getSupportedValues();
  public abstract boolean isSetMethodSupported();
  public abstract boolean isSupported();

  public Object getDefaultValue() throws Exception
  {
    return null;
  }
  
  public boolean isSelected()
  {
    return selected;
  }
  
  public void setSelected(boolean selected)
  {
    this.selected=selected;
  }
  
  public void addListener(Object o)
  {
    listeners.add(o);
  }
  
  public String getName()
  {
    return name;
  }
  
  public String getTitle()
  {
    return title;
  }
  
  public double computeQuant(double min, double max)
  {
    //Human readable quants
    final int[] HRQ={0,1,2,5,10,25,50,100,250,500,1000,2000,5000};
    double quant=(max-min)/50;
    for (int i = 1; i < HRQ.length; i++)
    {
      if((quant>HRQ[i-1])&&(quant<=HRQ[i]))
      {
        quant=HRQ[i];
        break;
      }
    }
    return quant;
  }
  
  public void actionPerformed(ActionEvent e)
  {
    String actionCommand = e.getActionCommand();
    String value=null;
    if (actionCommand.equals("tableComboBox"))
    {
      JComboBox comboBox = (JComboBox) e.getSource();
      //value=(String) comboBox.getSelectedItem();
      value=String.valueOf(comboBox.getSelectedItem());
    }
    else if (actionCommand.equals("tableTextField"))
    {
      JTextField textField = (JTextField) e.getSource();
      value=(String) textField.getText();
      //System.out.println("Capability.actionPerformed()value="+value);
    }
    
    try
    {
      setValue(value);
    } catch (Exception e1)
    {
      if(Capabilities.debug)
      {
        System.out.println("Capability.actionPerformed().setValue("+value+") failed: "+e1.getMessage()+" caused by "+e1.getCause());
      }
    }
  }

  public void focusLost(FocusEvent e)
  {
    JTextField textField = (JTextField) e.getSource();
    String value=(String) textField.getText();

    try
    {
      setValue(value);
    } catch (Exception e1)
    {
      if(Capabilities.debug)
      {
        System.out.println("Capability.focusLost().setValue("+value+") failed: "+e1.getMessage()+" caused by "+e1.getCause());
      }
    }
  }
  public void focusGained(FocusEvent e){}
}
