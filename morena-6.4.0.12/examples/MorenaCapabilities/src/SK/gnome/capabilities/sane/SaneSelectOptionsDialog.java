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

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
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
import SK.gnome.sane.SaneConstants;

public class SaneSelectOptionsDialog extends JDialog implements ActionListener, MorenaConstants
{
  private CapabilityDialog saneDialog;
  private Capabilities capabilities;
  private JPanel tabPanels;
  private Frame frame;

  public SaneSelectOptionsDialog(Frame frame, Capabilities capabilities, CapabilityDialog saneDialog)
  {
    super(frame, capabilities.getSourceName(), true);
    this.frame=frame;
    this.saneDialog = saneDialog;
    this.capabilities=capabilities;

    Container contentPane = getContentPane();

    JPanel mainContainerPanel = new JPanel();
    mainContainerPanel.setLayout(new BoxLayout(mainContainerPanel, BoxLayout.Y_AXIS));
    
    tabPanels = new JPanel();
    tabPanels.setLayout(new BoxLayout(tabPanels, BoxLayout.X_AXIS));
    tabPanels.setAlignmentX(LEFT_ALIGNMENT);
    Border border=BorderFactory.createEmptyBorder(10,10,10,10);
    Font font=getFont();
    Font newFont = new Font(font.getFontName(),Font.BOLD,16);
    tabPanels.setBorder(BorderFactory.createTitledBorder(border, MORENA_MESSAGES.getString("SELECT_FROM_SUPPORTED_OPTIONS"), TitledBorder.CENTER, TitledBorder.TOP, newFont));
    
    JPanel tabPanel = new JPanel();
    tabPanel.setAlignmentY(TOP_ALIGNMENT);
    tabPanel.setLayout(new BoxLayout(tabPanel, BoxLayout.Y_AXIS));
    
    for (Capability capability : capabilities.getCapabilities())
    {
      SaneCapability saneOption=(SaneCapability) capability;
//      System.out.println("SaneSelectOptionsDialog.SaneSelectOptionsDialog()saneOption="+saneOption.getName()+", "+saneOption.getType());
      if (saneOption.getType() == SaneConstants.SANE_TYPE_GROUP)
      {
        //If the previous tab is'nt empty, add it to panel.
        if(0!=tabPanel.getComponentCount())
        {
          tabPanels.add(tabPanel);
          tabPanel = new JPanel();
          tabPanel.setLayout(new BoxLayout(tabPanel, BoxLayout.Y_AXIS));
          tabPanel.setAlignmentY(TOP_ALIGNMENT);
        }
        tabPanel.setBorder(BorderFactory.createTitledBorder(saneOption.getTitle()));
      } else
      {
        if (0 == (saneOption.getCap() & SaneConstants.SANE_CAP_INACTIVE))
        {
          String name=saneOption.getName();
          //if((!name.equals("tl-x"))&&(!name.equals("tl-y"))&&(!name.equals("br-x"))&&(!name.equals("br-y"))&&(!name.equals("preview")))
          if(!name.equals("preview"))
          {
            JCheckBox checkBox = new JCheckBox(saneOption.getTitle());
            checkBox.setName(name);
            checkBox.setSelected(saneOption.isSelected());
            tabPanel.add(checkBox);
          }
        }
        
      }
    }
    //If the last tab is'nt empty, add it to panel.
    if(0!=tabPanel.getComponentCount())
    {
      tabPanels.add(tabPanel);
      tabPanel = new JPanel();
      tabPanel.setLayout(new BoxLayout(tabPanel, BoxLayout.Y_AXIS));
      tabPanel.setAlignmentY(TOP_ALIGNMENT);
    }
    
    mainContainerPanel.add(tabPanels);
    mainContainerPanel.add(Box.createRigidArea(new Dimension(0,10)));

    JButton button = new JButton(MORENA_MESSAGES.getString("PREFERENCES"));
    button.setActionCommand("PreferencesButton");
    button.addActionListener(this);
    mainContainerPanel.add(button);
    
    button = new JButton("OK");
    button.setFont(new Font("Arial",Font.BOLD,18));
    button.setActionCommand("OKButton");
    button.addActionListener(this);
    JPanel panel2=new JPanel();
    panel2.add(button);
    contentPane.add(panel2, BorderLayout.PAGE_END);
    
    JScrollPane scrollPane = new JScrollPane(mainContainerPanel);
    contentPane.add(scrollPane);
    pack();
    validate();
    int width=getWidth();
    if(width>800)
      width=800;
    int height=getHeight();
    if(height>600)
      height=600;
    setSize(new Dimension(width+20, height+20));
    setLocationByPlatform(true);
    setVisible(true);
  }

  public void actionPerformed(ActionEvent e)
  {
    if(e.getActionCommand().equals("OKButton"))
    {
      Component[] tabPanelsArray=tabPanels.getComponents();
      for (int j = 0; j < tabPanelsArray.length; j++)
      {
        Component[] checkBoxArray=((JPanel)tabPanelsArray[j]).getComponents();
        JCheckBox checkBox;
        
        for (int i = 0; i < checkBoxArray.length; i++)
        {
          checkBox=(JCheckBox)checkBoxArray[i];
          String capabilityName=checkBox.getName();
          String name;
          
          for (Capability capability : capabilities.getCapabilities())
          {
            name=capability.getName();
            if(name!=null)
            {
              if(name.equals(capabilityName))
              {
                capability.setSelected(checkBox.isSelected());
                break;
              }
            }
            
          }
        }
      }
      
      saneDialog.updateCapabilitiesTable();
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
