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

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

import SK.gnome.capabilities.Capabilities;
import SK.gnome.capabilities.Capability;
import SK.gnome.capabilities.CapabilityDialog;
import SK.gnome.capabilities.MorenaCapabilities;
import SK.gnome.morena.MorenaConstants;


public class TwainSelectCapabilitiesDialog extends JDialog  implements ActionListener, MorenaConstants
{
  private CapabilityDialog capabilityDialog;
  private JPanel checkBoxPanel;
  private Capabilities capabilities;
  private Frame frame;
  
  public TwainSelectCapabilitiesDialog(Frame frame, Capabilities capabilities, CapabilityDialog capabilityDialog)
  {
    super(frame, capabilities.getSourceName(), true);
    this.frame=frame;
    this.capabilityDialog= capabilityDialog;
    this.capabilities=capabilities;
    
    Container contentPane = getContentPane();
    
    JPanel mainContainerPanel = new JPanel();
    mainContainerPanel.setLayout(new BoxLayout(mainContainerPanel, BoxLayout.Y_AXIS));
    Border border=BorderFactory.createEmptyBorder(10,10,10,10);
    Font font=getFont();
    Font newFont = new Font(font.getFontName(),Font.BOLD,16);
    mainContainerPanel.setBorder(BorderFactory.createTitledBorder(border, MORENA_MESSAGES.getString("SELECT_FROM_SUPPORTED_CAPABILITIES"), TitledBorder.CENTER, TitledBorder.TOP, newFont));
    
    checkBoxPanel=new JPanel();
    checkBoxPanel.setAlignmentX(LEFT_ALIGNMENT);
    
    GridLayout gridLayoutPane=new GridLayout(0,6);
    checkBoxPanel.setLayout(gridLayoutPane);
    JCheckBox checkBox;
    for (Capability capability : capabilities.getCapabilities())
    {
      if(capability.isSupported())
      {
        String name=capability.getName();
        if((!name.equals("tl-x"))&&(!name.equals("tl-y"))&&(!name.equals("br-x"))&&(!name.equals("br-y")))
        {
          checkBox=new JCheckBox(name,capability.isSelected());
          checkBoxPanel.add(checkBox);
        }
      }
    }
    checkBox=new JCheckBox("SetFrame",capabilities.getCapability("tl-x").isSelected());
    checkBoxPanel.add(checkBox);
    
    mainContainerPanel.add(checkBoxPanel);
    mainContainerPanel.add(Box.createRigidArea(new Dimension(0,10)));

    JButton button = new JButton(MORENA_MESSAGES.getString("PREFERENCES"));
    button.setAlignmentX(LEFT_ALIGNMENT);
    button.setActionCommand("PreferencesButton");
    button.addActionListener(this);
    mainContainerPanel.add(button);
    mainContainerPanel.add(Box.createRigidArea(new Dimension(0,5)));
    
    button=new JButton("OK");
    button.setActionCommand("OKButton");
    button.setFont(new Font("Arial",Font.BOLD,18));
    button.addActionListener(this);
    
    JPanel panel2=new JPanel();
    panel2.add(button);
    contentPane.add(panel2, BorderLayout.PAGE_END);
    
    JScrollPane scrollPane=new JScrollPane(mainContainerPanel);
    contentPane.add(scrollPane);
    
    pack();
    setLocationByPlatform(true);
    setVisible(true);
  }

  public void actionPerformed(ActionEvent e)
  {
    if(e.getActionCommand().equals("OKButton"))
    {
      Component[] checkBoxArray=checkBoxPanel.getComponents();
      JCheckBox checkBox;
      
      for (int i = 0; i < checkBoxArray.length; i++)
      {
        checkBox=(JCheckBox)checkBoxArray[i];
        String capabilityName=checkBox.getText();
        
        if(capabilityName.equals("SetFrame"))
        {
          capabilities.getCapability("tl-x").setSelected(checkBox.isSelected());
          capabilities.getCapability("tl-y").setSelected(checkBox.isSelected());
          capabilities.getCapability("br-x").setSelected(checkBox.isSelected());
          capabilities.getCapability("br-y").setSelected(checkBox.isSelected());
        }
        else
        {
          for (Capability capability : capabilities.getCapabilities())
          {
            if(capability.getName().equals(capabilityName))
            {
              capability.setSelected(checkBox.isSelected());
              break;
            }
          }
        }
      }
      capabilityDialog.updateCapabilitiesTable();
      dispose();
    }
    else if(e.getActionCommand().equals("PreferencesButton"))
    {
      class PreferencesOptionPane extends JPanel
      {
        private JCheckBox restartAfterPreviewCheckBox;
        private JTextField sleepTimeBeforeRestartTextField;
        
        public PreferencesOptionPane(boolean restartAfterPreview, int sleepTimeBeforeRestart)
        {
          setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
          setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
          setAlignmentX(Component.LEFT_ALIGNMENT);
          
          restartAfterPreviewCheckBox = new JCheckBox(MORENA_MESSAGES.getString("RESTART_AFTER_PREVIEW"));
          restartAfterPreviewCheckBox.setSelected(capabilities.isRestartAfterPreview());
          add(restartAfterPreviewCheckBox);
          
          JPanel restartPanel = new JPanel();
          restartPanel.setLayout(new BoxLayout(restartPanel, BoxLayout.X_AXIS));
          restartPanel.setAlignmentX(LEFT_ALIGNMENT);
          JLabel label=new JLabel(MORENA_MESSAGES.getString("SLEEP_TIME_BEFORE_NEW_START")+":  ");
          label.setAlignmentX(LEFT_ALIGNMENT);
          restartPanel.add(label);
          sleepTimeBeforeRestartTextField=new JTextField(String.valueOf(capabilities.getSleepTimeBeforeRestart()),6);
          sleepTimeBeforeRestartTextField.setAlignmentX(LEFT_ALIGNMENT);
          restartPanel.add(sleepTimeBeforeRestartTextField);
          add(restartPanel);
          
        }
        
        public boolean isRestartAfterPreview()
        {
          return restartAfterPreviewCheckBox.isSelected();
        }
  
        public int getSleepTimeBeforeRestart()
        {
          return Integer.parseInt(sleepTimeBeforeRestartTextField.getText());
        }
  
      }
      
      PreferencesOptionPane preferencesOptionPane=new PreferencesOptionPane(capabilities.isRestartAfterPreview(), capabilities.getSleepTimeBeforeRestart());
      JOptionPane.showMessageDialog(frame,preferencesOptionPane, MORENA_MESSAGES.getString("PREFERENCES"), JOptionPane.INFORMATION_MESSAGE);
      
      capabilities.setRestartAfterPreview(preferencesOptionPane.isRestartAfterPreview());
      capabilities.setSleepTimeBeforeRestart(preferencesOptionPane.getSleepTimeBeforeRestart());
      
    }
  }
}
