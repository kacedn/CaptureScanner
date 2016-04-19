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

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javax.swing.JPanel;


public class PreviewCanvas extends JPanel implements MouseMotionListener, MouseListener, ComponentListener, PropertyChangeListener
{
  private Image originalImage=null;
  private BufferedImage compositeImage, image;
  private Rectangle p=new Rectangle();
  private Rectangle top=new Rectangle();
  private Rectangle bottom=new Rectangle();
  private Rectangle left=new Rectangle();
  private Rectangle right=new Rectangle();
  private Rectangle leftTop=new Rectangle();
  private Rectangle rightTop=new Rectangle();
  private Rectangle leftBottom=new Rectangle();
  private Rectangle rightBottom=new Rectangle();
  private Point firstClick;
  private Rectangle firstLocation=new Rectangle();
  
  static final int DRAG_MODE_IN=0;
  static final int DRAG_MODE_HORIZONTAL=1;
  static final int DRAG_MODE_VERTICAL=2;
  static final int DRAG_MODE_CORNER=3;
  private int dragMode=DRAG_MODE_IN;
  
  static final int BORDER=10;
  static final int CORNER=15;
  private int originalImageWidth, originalImageHeight, imageWidth, imageHeight;
  private Capability tl_xCapability;
  private Capability tl_yCapability;
  private Capability br_xCapability;
  private Capability br_yCapability;
  private boolean composite;
  
  public PreviewCanvas(Capability tl_xCapability, Capability tl_yCapability, Capability br_xCapability, Capability br_yCapability)
  {
    this.tl_xCapability=tl_xCapability;
    this.tl_yCapability=tl_yCapability;
    this.br_xCapability=br_xCapability;
    this.br_yCapability=br_yCapability;
    
    if(null!=tl_xCapability)
      tl_xCapability.addListener(this);
    if(null!=tl_yCapability)
      tl_yCapability.addListener(this);
    if(null!=br_xCapability)
      br_xCapability.addListener(this);
    if(null!=br_yCapability)
      br_yCapability.addListener(this);
    
    addMouseMotionListener(this);
    addMouseListener(this);
    addComponentListener(this);
  }
  
  private void adjustImageSize()
  {
    if(originalImage!=null)
    {
      double min=Math.min((double)getWidth()/(double)originalImageWidth, (double)getHeight()/(double)originalImageHeight);
      imageWidth=(int)(min*originalImageWidth);
      imageHeight=(int)(min*originalImageHeight);
//      System.out.println("PreviewCanvas.adjustImageSize()imageWidth="+imageWidth+", imageHeight="+imageHeight);
      compositeImage=new BufferedImage(imageWidth, imageHeight, BufferedImage.TYPE_INT_RGB);
      Graphics2D g2d=compositeImage.createGraphics();
      g2d.drawImage(originalImage, 0, 0, imageWidth, imageHeight, null);
      g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,.5f));
      g2d.setPaint(Color.GRAY);
      g2d.fillRect(0,0,imageWidth, imageHeight);
      
      image=new BufferedImage(imageWidth, imageHeight, BufferedImage.TYPE_INT_RGB);
      g2d=image.createGraphics();
      g2d.drawImage(originalImage, 0, 0, imageWidth, imageHeight,null);
      repaint();
    }
  }
  
  protected void paintComponent(Graphics g)
  {
    super.paintComponent(g);
    if(composite)
    {
      g.drawImage(compositeImage, 0, 0, this);
      g.drawImage(image, p.x, p.y, p.x+p.width, p.y+p.height, p.x, p.y, p.x+p.width, p.y+p.height, this);
      if((null==firstClick)&&(null!=image))
      {
        g.setXORMode(Color.WHITE);
        g.drawRect(p.x, p.y, p.width, p.height);
      }
    }
    else
    {
      g.drawImage(image, 0, 0, this);
    }
    
  }
  
  public void mouseDragged(MouseEvent e)
  {
    if(null!=firstClick)
    {
      Point mouseLocation=e.getPoint();
      int xOffset=mouseLocation.x-firstClick.x;
      int yOffset=mouseLocation.y-firstClick.y;
      
      int newWidth,newHeight;
      switch (dragMode)
      {
      case DRAG_MODE_IN:
        int newX=firstLocation.x+xOffset;
        int newY=firstLocation.y+yOffset;
        if(((newX>=0)&&(newX<=imageWidth))&&(newX+p.width>=0)&&(newX+p.width<=imageWidth))
          p.x=newX;
        if(((newY>=0)&&(newY<=imageHeight))&&(newY+p.height>=0)&&(newY+p.height<=imageHeight))
          p.y=newY;
        break;
      case DRAG_MODE_HORIZONTAL:
        newHeight=firstLocation.height+yOffset;
        if((p.y+newHeight>=0)&&(p.y+newHeight<=imageHeight))
            p.height=newHeight;
        break;
      case DRAG_MODE_VERTICAL:
        newWidth=firstLocation.width+xOffset;
        if((p.x+newWidth>=0)&&(p.x+newWidth<=imageWidth))
          p.width=newWidth;
        break;
      case DRAG_MODE_CORNER:
        newWidth=firstLocation.width+xOffset;
        if((p.x+newWidth>=0)&&(p.x+newWidth<=imageWidth))
          p.width=newWidth;
        
        newHeight=firstLocation.height+yOffset;
        if((p.y+newHeight>=0)&&(p.y+newHeight<=imageHeight))
            p.height=newHeight;
        break;
      default:
        break;
      }
      repaint();
    }
    
  }

  private void adjustBorder()
  {
    top.setBounds(p.x+CORNER,p.y,p.width-2*CORNER,BORDER);
    bottom.setBounds(p.x+CORNER,p.y+p.height-BORDER,p.width-2*CORNER,BORDER);
    left.setBounds(p.x,p.y+CORNER,BORDER,p.height-2*CORNER);
    right.setBounds(p.x+p.width-BORDER,p.y+CORNER,BORDER,p.height-2*CORNER);
    leftTop.setBounds(p.x,p.y,CORNER,CORNER);
    rightTop.setBounds(p.x+p.width-CORNER,p.y,CORNER,CORNER);
    leftBottom.setBounds(p.x,p.y+p.height-CORNER,CORNER,CORNER);
    rightBottom.setBounds(p.x+p.width-CORNER,p.y+p.height-CORNER,CORNER,CORNER);
  }
  
  public void mousePressed(MouseEvent e)
  {
//    System.out.println("PreviewCanvas.mousePressed()");
    Point mouseClick=e.getPoint();
    firstClick=mouseClick;
    composite=true;
    
    if(p.contains(mouseClick))
    {
      if(top.contains(mouseClick))
      {
        dragMode=DRAG_MODE_HORIZONTAL;
        p.y+=p.height;
        p.height=-p.height;
      }
      else if(bottom.contains(mouseClick))
      {
        dragMode=DRAG_MODE_HORIZONTAL;
      }
      else if(left.contains(mouseClick))
      {
        dragMode=DRAG_MODE_VERTICAL;
        p.x+=p.width;
        p.width=-p.width;
      }
      else if(right.contains(mouseClick))
      {
        dragMode=DRAG_MODE_VERTICAL;
      }
      else if(leftTop.contains(mouseClick))
      {
        dragMode=DRAG_MODE_CORNER;
        p.x+=p.width;
        p.y+=p.height;
        p.width=-p.width;
        p.height=-p.height;
      }
      else if(rightTop.contains(mouseClick))
      {
        dragMode=DRAG_MODE_CORNER;
        p.y+=p.height;
        p.height=-p.height;
      }
      else if(leftBottom.contains(mouseClick))
      {
        dragMode=DRAG_MODE_CORNER;
        p.x+=p.width;
        p.width=-p.width;
      }
      else if(rightBottom.contains(mouseClick))
      {
        dragMode=DRAG_MODE_CORNER;
      }
      else
      {
        dragMode=DRAG_MODE_IN;
      }
    }
    else
    {
      dragMode=DRAG_MODE_CORNER;
      p.x=mouseClick.x;
      p.y=mouseClick.y;
      p.width=0;
      p.height=0;
    }
    firstLocation.setBounds(p);
  }
  
  public void mouseMoved(MouseEvent e)
  {
    Point mouseLocation=e.getPoint();
    if(top.contains(mouseLocation))
    {
      setCursor(Cursor.getPredefinedCursor(Cursor.N_RESIZE_CURSOR));
    }
    else if(bottom.contains(mouseLocation))
    {
      setCursor(Cursor.getPredefinedCursor(Cursor.S_RESIZE_CURSOR));
    }
    else if(left.contains(mouseLocation))
    {
      setCursor(Cursor.getPredefinedCursor(Cursor.W_RESIZE_CURSOR));
    }
    else if(right.contains(mouseLocation))
    {
      setCursor(Cursor.getPredefinedCursor(Cursor.E_RESIZE_CURSOR));
    }
    else if(leftTop.contains(mouseLocation))
    {
      setCursor(Cursor.getPredefinedCursor(Cursor.NW_RESIZE_CURSOR));
    }
    else if(rightTop.contains(mouseLocation))
    {
      setCursor(Cursor.getPredefinedCursor(Cursor.NE_RESIZE_CURSOR));
    }
    else if(leftBottom.contains(mouseLocation))
    {
      setCursor(Cursor.getPredefinedCursor(Cursor.SW_RESIZE_CURSOR));
    }
    else if(rightBottom.contains(mouseLocation))
    {
      setCursor(Cursor.getPredefinedCursor(Cursor.SE_RESIZE_CURSOR));
    }
    else if(p.contains(mouseLocation))
    {
      setCursor(Cursor.getPredefinedCursor(Cursor.MOVE_CURSOR));
    }
    else 
    {
      setCursor(null);
    }
  }

  public void mouseClicked(MouseEvent e)
  {
//    System.out.println("PreviewCanvas.mouseClicked()");
    if(!p.contains(e.getPoint()))
    {
      Object[] supported = br_xCapability.getSupportedValues();
      double maxWidth=Double.parseDouble((String)supported[supported.length-1]);
      supported = br_yCapability.getSupportedValues();
      double maxHeight=Double.parseDouble((String)supported[supported.length-1]);
      
      try
      {
        tl_xCapability.setValue("0");
      } catch (Exception e1)
      {
      }
      try
      {
        tl_yCapability.setValue("0");
      } catch (Exception e1)
      {
      }
      try
      {
        br_xCapability.setValue(String.valueOf(maxWidth));
      } catch (Exception e1)
      {
      }
      try
      {
        br_yCapability.setValue(String.valueOf(maxHeight));
      } catch (Exception e1)
      {
      }
      
      p.x=0;
      p.y=0;
      p.width=0;
      p.height=0;
      
      adjustBorder();
      composite=false;
    }
  }

  public void mouseEntered(MouseEvent e)
  {
  }

  public void mouseExited(MouseEvent e)
  {
  }

  public void mouseReleased(MouseEvent e)
  {
    firstClick=null;
    
    if(p.width<0)
    {
      p.x+=p.width;
      p.width=-p.width;
    }
      
    if(p.height<0)
    {
      p.y+=p.height;
      p.height=-p.height;
    }
    
    //System.out.println("PreviewCanvas.mouseReleased()p="+p);
    
    tl_xCapability.setSelected(true);
    tl_yCapability.setSelected(true);
    br_xCapability.setSelected(true);
    br_yCapability.setSelected(true);
    
    Object[] supported = br_xCapability.getSupportedValues();
    double maxWidth=Double.parseDouble((String)supported[supported.length-1]);
    //System.out.println("PreviewCanvas.mouseReleased()maxWidth="+maxWidth);
    supported = br_yCapability.getSupportedValues();
    double maxHeight=Double.parseDouble((String)supported[supported.length-1]);
    //System.out.println("PreviewCanvas.mouseReleased()maxHeight="+maxHeight);
    
    try
    {
      //System.out.println("PreviewCanvas.mouseReleased().setValue="+(p.x*maxWidth/imageWidth));
      tl_xCapability.setValue(String.valueOf(p.x*maxWidth/imageWidth));
    } catch (Exception e1)
    {
      if(Capabilities.debug)
        System.out.println("PreviewCanvas.mouseReleased()"+e1.getMessage()+" caused by "+e1.getCause());
    }
    try
    {
      //System.out.println("PreviewCanvas.mouseReleased().setValue="+(p.y*maxHeight/imageHeight));
      tl_yCapability.setValue(String.valueOf(p.y*maxHeight/imageHeight));
    } catch (Exception e1)
    {
      if(Capabilities.debug)
        System.out.println("PreviewCanvas.mouseReleased()"+e1.getMessage()+" caused by "+e1.getCause());
    }
    try
    {
      //System.out.println("PreviewCanvas.mouseReleased().setValue="+((p.x+p.width)*maxWidth/imageWidth));
      br_xCapability.setValue(String.valueOf((p.x+p.width)*maxWidth/imageWidth));
    } catch (Exception e1)
    {
      if(Capabilities.debug)
        System.out.println("PreviewCanvas.mouseReleased()"+e1.getMessage()+" caused by "+e1.getCause());
    }
    try
    {
      //System.out.println("PreviewCanvas.mouseReleased().setValue="+((p.y+p.height)*maxHeight/imageHeight));
      br_yCapability.setValue(String.valueOf((p.y+p.height)*maxHeight/imageHeight));
    } catch (Exception e1)
    {
      if(Capabilities.debug)
        System.out.println("PreviewCanvas.mouseReleased()"+e1.getMessage()+" caused by "+e1.getCause());
    }
    
    adjustBorder();
    repaint();
  }

  public void componentHidden(ComponentEvent arg0)
  {
  }

  public void componentMoved(ComponentEvent e)
  {
  }

  public void componentResized(ComponentEvent e)
  {
    dragMode=DRAG_MODE_CORNER;
    p.x=0;
    p.y=0;
    p.width=0;
    p.height=0;
    repaint();
    if(originalImage!=null)
    {
      adjustImageSize();
      composite=false;
    }
     
  }

  public void componentShown(ComponentEvent e)
  {
  }

  public void setImage(Image image)
  {
    if(null!=image)
    {
      originalImage=image;
      originalImageWidth=originalImage.getWidth(this);
      originalImageHeight=originalImage.getHeight(this);
      //System.out.println("PreviewCanvas.setImage()originalImageWidth="+originalImageWidth+", originalImageHeight="+originalImageHeight);
      
      Object[] supported = br_xCapability.getSupportedValues();
      double maxWidth=Double.parseDouble((String)supported[supported.length-1]);
      supported = br_yCapability.getSupportedValues();
      double maxHeight=Double.parseDouble((String)supported[supported.length-1]);
      
      double tl_x=(Double)tl_xCapability.getValue();
      double tl_y=(Double)tl_yCapability.getValue();
      double br_x=(Double)br_xCapability.getValue();
      double br_y=(Double)br_yCapability.getValue();
      //System.out.println("PreviewCanvas.setImage()tl_x="+tl_x+", tl_y="+tl_y+", br_x="+br_x+", br_y="+br_y);
      
      if((tl_x>0.01)&&(tl_y>0.01)&&((maxWidth-br_x)>0.01)&&((maxHeight-br_y)>0.01))
      {
        adjustImageSize();
        composite=true;
        p.x=(int) (imageWidth*tl_x/maxWidth+0.5);
        p.y=(int) (imageHeight*tl_y/maxHeight+0.5);
        p.width=(int) (imageWidth*br_x/maxWidth-p.x+0.5);
        p.height=(int) (imageHeight*br_y/maxHeight-p.y+0.5);
        adjustBorder();
      }
      else
      {
        adjustImageSize();
        composite=false;
      }
        
    }
  }

  public void propertyChange(PropertyChangeEvent evt)
  {
    Capability capability=(Capability)evt.getSource();
    String propertyName=capability.getName();
    double value=(Double)capability.getValue();
    
    Object[] supported = br_xCapability.getSupportedValues();
    double maxWidth=Double.parseDouble((String)supported[supported.length-1]);
    supported = br_yCapability.getSupportedValues();
    double maxHeight=Double.parseDouble((String)supported[supported.length-1]);
    int temp;
    if(propertyName.equals("tl-x"))
    {
      //System.out.println("PreviewCanvas.propertyChange()."+propertyName+"="+value+", imageHeight="+imageHeight+", maxHeight="+maxHeight);
      temp=p.x+p.width;
      p.x=(int) (imageWidth*value/maxWidth+0.5);
      p.width=temp-p.x;
    } 
    else if (propertyName.equals("br-x"))
    {
      //System.out.println("PreviewCanvas.propertyChange()."+propertyName+"="+value+", imageHeight="+imageHeight+", maxHeight="+maxHeight);
      p.width=(int) (imageWidth*value/maxWidth-p.x+0.5);
    } 
    else if (propertyName.equals("tl-y"))
    {
      //System.out.println("PreviewCanvas.propertyChange()."+propertyName+"="+value+", imageHeight="+imageHeight+", maxHeight="+maxHeight);
      temp=p.y+p.height;
      p.y=(int) (imageHeight*value/maxHeight+0.5);
      p.height=temp-p.y;
    } 
    else if (propertyName.equals("br-y"))
    {
      //System.out.println("PreviewCanvas.propertyChange()."+propertyName+"="+value+", imageHeight="+imageHeight+", maxHeight="+maxHeight);
      p.height=(int) (imageHeight*value/maxHeight-p.y+0.5);
    }
    //System.out.println("PreviewCanvas.propertyChange().p="+p);
    adjustBorder();
    repaint();
  }
}
