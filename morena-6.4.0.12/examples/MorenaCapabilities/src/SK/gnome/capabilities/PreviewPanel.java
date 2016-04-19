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

import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import SK.gnome.morena.MorenaConstants;
import SK.gnome.morena.MorenaImage;

public class PreviewPanel extends JPanel implements ActionListener
{
  private PreviewCanvas previewCanvas;
  private Capabilities capabilities;

  public PreviewPanel(Capabilities capabilities)
  {
    this.capabilities=capabilities;
    setLayout(new BorderLayout());
    setBorder(BorderFactory.createTitledBorder(""));
    previewCanvas = new PreviewCanvas(capabilities.getCapability("tl-x"),capabilities.getCapability("tl-y"),capabilities.getCapability("br-x"),capabilities.getCapability("br-y"));
    add(previewCanvas, BorderLayout.CENTER);
    
    JPanel bottomPanel=new JPanel();
    JButton previewButton = new JButton(MorenaConstants.MORENA_MESSAGES.getString("PREVIEW"));
    previewButton.setActionCommand("previewButton");
    previewButton.addActionListener(this);
    bottomPanel.add(previewButton);
    
    JPanel temp3=new JPanel();
    temp3.add(bottomPanel);
    temp3.setAlignmentX(CENTER_ALIGNMENT);
    add(temp3,BorderLayout.PAGE_END);
    setPreferredSize(new Dimension(475, 535));
  }

  public void actionPerformed(ActionEvent e)
  {
    if (e.getActionCommand().equals("previewButton"))
    {
      Cursor previousCursor=getTopLevelAncestor().getCursor();
      setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
      MorenaImage morenaImage;
      try 
      {
        morenaImage = capabilities.getPreviewImage();
        if((0<morenaImage.getWidth())&&(0<morenaImage.getHeight()))
        {
          Image image = Toolkit.getDefaultToolkit().createImage(morenaImage);
          previewCanvas.setImage(image);
        }
      } catch (Exception e1) 
      {
        JOptionPane.showMessageDialog(this, e1.getMessage());
        e1.printStackTrace();
      }
      setCursor(previousCursor);
    }
  }

}
