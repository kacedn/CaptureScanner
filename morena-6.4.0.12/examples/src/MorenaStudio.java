/*
 * $Id$
 *
 * Copyright (c) 1999-2007 Gnome spol. s r.o. All Rights Reserved.
 *
 * This software is the confidential and proprietary information of
 * Gnome spol. s r.o. You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms
 * of the license agreement you entered into with Gnome.
 */
  
/**
 *  SimpleExample demonstrates use of the Morena Framework in both
 *  application and applet environment. Upload action cant be used
 *  if it is invoked from local filesystem.
 *  
 *  Requirements:
 *  1. Java2 1.4 or newer
 *  2. Morena 6 for image acquisition
 *  
*/

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.awt.image.ImageConsumer;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.AbstractAction;
import javax.swing.JApplet;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.WindowConstants;
import javax.swing.border.LineBorder;
import javax.swing.filechooser.FileFilter;

import SK.gnome.morena.Morena;
import SK.gnome.morena.MorenaImage;
import SK.gnome.morena.MorenaSource;
import SK.gnome.twain.TwainSource;
import SK.gnome.twain.TwainSource.Frame;


public class MorenaStudio extends JApplet 
{ private static class MainPanel extends JPanel
  { private JTextField status = new JTextField();
    private ImagePanel selected=null;
    private SaveImageAction saveImageAction;
    private UploadImageAction uploadImageAction;
    private MouseListener mouseListener=new MouseListener();
    private boolean hasServer=false;
    private URL documentBase=null;

    private class RemoveAllAction extends AbstractAction implements Runnable
    { RemoveAllAction()
      { super("remove all");
      }
    
      public synchronized void actionPerformed(ActionEvent event)
      { new Thread(this).start();
      }

      public synchronized void run()
      { removeAll();
        select(null);
        repaint();
      }
    }

    private class AcquireImageAction extends AbstractAction implements Runnable
    { AcquireImageAction()
      { super("acquire image");
      }
    
      public synchronized void actionPerformed(ActionEvent event)
      { new Thread(this).start();
      }

      public synchronized void run()
      { try
        { status.setText("Working ...");
          MorenaSource source=Morena.selectSource(MainPanel.this);
          if (source!=null)
          { source.setColorMode();
            source.setResolution(100);
            while (true)
            { MorenaImage morenaImage=new MorenaImage(source);
              int imageStatus=morenaImage.getStatus();
              if (imageStatus==ImageConsumer.STATICIMAGEDONE)
              { int imageWidth=morenaImage.getWidth();
                int imageHeight=morenaImage.getHeight();
                int imagePixelSize=morenaImage.getPixelSize();
                ImagePanel image=new ImagePanel(Toolkit.getDefaultToolkit().createImage(morenaImage));
                MainPanel.this.add(image);
                select(image);
                int size=(int)Math.round(Math.sqrt(getComponentCount()));
                setLayout(new GridLayout(size, size));
                
                if (TwainSource.class.isInstance(source))
                {
                  Frame frame=((TwainSource)source).getFrame();
                  status.setText("Done - actual image frame is ["+Math.round(100*frame.left)/100.0+", "+Math.round(100*frame.top)/100.0+"] - ["+Math.round(100*frame.right)/100.0+", "+Math.round(100*frame.bottom)/100.0+"] ...");
                }
                else
                  status.setText("Done - actual image size is "+imageWidth+" x "+imageHeight+" x "+imagePixelSize+" ...");
                validate();
                if (TwainSource.class.isInstance(source) && ((TwainSource)source).hasMoreImages())
                  continue;
              }
              else if (imageStatus==ImageConsumer.IMAGEABORTED)
                status.setText("Aborted, try again ...");
              else if (imageStatus==ImageConsumer.IMAGEERROR)
                status.setText("Failed, try again ...");
              break;
            }
          }
          else
            status.setText("Failed, try again ...");
        }
        catch (NoSuchMethodError error)
        { JOptionPane.showMessageDialog(MainPanel.this, "Previous version of Morena is installed in "+System.getProperty("java.home")+".\nYou have to remove it first.\nClick OK to terminate browser.", "Error", JOptionPane.ERROR_MESSAGE);
          System.exit(0);
        }
        catch (Throwable exception)
        { JOptionPane.showMessageDialog(MainPanel.this, exception.toString(), "Error", JOptionPane.ERROR_MESSAGE);
          exception.printStackTrace();
          status.setText("Failed, try again ...");
        }
        finally
        { try
          { Morena.close();
          }
          catch (Exception exception)
          { exception.printStackTrace();
          }
        }
      }
    }
    
    private class SaveImageAction extends AbstractAction implements Runnable
    { private class Filter extends FileFilter
      { String type;
        
        Filter(String type)
        { this.type=type;
        }
        
        public boolean accept(File file)
        { return file.getName().endsWith(type);
        }

        public String getDescription()
        { return type+" Files";
        } 
      }

      SaveImageAction()
      { super("save to file");
      }
    
      public void actionPerformed(ActionEvent event)
      { new Thread(this).start();
      }

      public synchronized void run()
      { try
        { status.setText("Working ...");
          Image image=selected.getImage();
          BufferedImage bufferedImage=new BufferedImage(image.getWidth(null), image.getHeight(null), BufferedImage.TYPE_INT_RGB);
          bufferedImage.createGraphics().drawImage(image, 0, 0, null);
          JFileChooser chooser=new JFileChooser();
//        Java 1.6 introduced a comfortable ImageIO.getWriterFileSuffixes() method.
          String e[] = ImageIO.getWriterFormatNames();
          for (int i=0; i<e.length; i++)
            chooser.addChoosableFileFilter(new Filter(e[i]));
          int result=chooser.showSaveDialog(MainPanel.this);
          if (result==JFileChooser.APPROVE_OPTION)
          { String ext=chooser.getFileFilter().getDescription();
            ext=ext.substring(0, ext.indexOf(' ')).toLowerCase();
            File file=chooser.getSelectedFile();
            String name=file.getName();
            if (!name.endsWith(ext))
              file=new File(file.getParentFile(), name+"."+ext);
            ImageIO.write(bufferedImage, ext, file);
          }
        }
        catch (Throwable exception)
        { JOptionPane.showMessageDialog(MainPanel.this, exception.toString(), "Error", JOptionPane.ERROR_MESSAGE);
          exception.printStackTrace();
          status.setText("Failed, try again ...");
        }
      }

      public boolean isEnabled()
      { return selected != null;
      }
    }

    private class UploadImageAction extends AbstractAction implements Runnable
    { UploadImageAction()
      { super("upload to server");
      }
    
      public void actionPerformed(ActionEvent event)
      { new Thread(this).start();
      }

      public synchronized void run()
      { try
        { status.setText("Working ...");
          Image image=selected.getImage();
          ByteArrayOutputStream tmp=new ByteArrayOutputStream();
          BufferedImage bufferedImage=new BufferedImage(image.getWidth(null), image.getHeight(null), BufferedImage.TYPE_INT_RGB);
          bufferedImage.createGraphics().drawImage(image, 0, 0, null);
          ImageIO.write(bufferedImage, "jpg", tmp);
          tmp.close();
          int contentLength=tmp.size();
          if (contentLength>1024*1024)
            throw new Exception("Image is too big to upload");
          URL uploadURL=new URL(documentBase, "upload.php");
          HttpURLConnection connection=(HttpURLConnection)uploadURL.openConnection();
          connection.setRequestMethod("POST");
          connection.setDoOutput(true);
          connection.setUseCaches(false);
          connection.setDefaultUseCaches(false);
          connection.setRequestProperty("content-type", "img/jpeg");
          connection.setRequestProperty("content-length", String.valueOf(contentLength));
          OutputStream out=connection.getOutputStream();
          out.write(tmp.toByteArray());
          out.close();
          InputStream in=connection.getInputStream();
          int c;
          while ((c=in.read())!=-1)
            System.err.write(c);
          in.close();
          URL imageURL=new URL(documentBase, connection.getHeaderField("file-name"));
          status.setText("Done - image is uploaded to "+imageURL+" (for at least 5 minutes) ...");
        }
        catch (Throwable exception)
        { JOptionPane.showMessageDialog(MainPanel.this, exception.toString(), "Error", JOptionPane.ERROR_MESSAGE);
          exception.printStackTrace();
          status.setText("Failed, try again ...");
        }
      }

      public boolean isEnabled()
      { return hasServer && selected != null;
      }
    }

    private class MouseListener extends MouseAdapter
    { public void mouseClicked(MouseEvent event)
      { select((ImagePanel)event.getComponent());
      } 
    }
    
    private class ImagePanel extends JPanel
    { private Image image;
      int imageWidth;
      int imageHeight;
      
      ImagePanel(Image image)
      { this.image=image;
        imageWidth=image.getWidth(null);
        imageHeight=image.getHeight(null);
        addMouseListener(mouseListener);
      }
      
      public Image getImage()
      { return image;
      }
      
      public void paint(Graphics g)
      { super.paint(g);
        int panelWidth=getWidth()-6;
        int panelHeight=getHeight()-6;
        double horizontalRatio=(double)panelWidth/imageWidth;
        double verticalRatio=(double)panelHeight/imageHeight;
        if (horizontalRatio>verticalRatio)
          g.drawImage(image, (int)(panelWidth-imageWidth*verticalRatio)/2+3, 3, (int)(imageWidth*verticalRatio), (int)(imageHeight*verticalRatio), this);
        else
          g.drawImage(image, 3, 3, (int)(imageWidth*horizontalRatio), (int)(imageHeight*horizontalRatio), this);
      }

    }
    
    private class ToolBar extends JToolBar
    { ToolBar()
      { add(new RemoveAllAction());
        addSeparator();
        add(new AcquireImageAction());
        addSeparator();
        add(saveImageAction=new SaveImageAction());
        saveImageAction.setEnabled(false);
        addSeparator();
        add(uploadImageAction=new UploadImageAction());
        uploadImageAction.setEnabled(false);
        setMargin(new Insets(4, 2, 2, 2));
      }
    }
    
    private class StatusBar extends JToolBar
    { StatusBar()
      { add(status);
        status.setText("Ready ...");
      }
    }
    
    void select(ImagePanel image)
    { if(selected != null)
      selected.setBorder(null);
      selected = image;
      if(selected != null)
      {
        selected.setBorder(new LineBorder(Color.blue, 1));
        saveImageAction.setEnabled(true);
        uploadImageAction.setEnabled(hasServer);
      } else
      {
        saveImageAction.setEnabled(false);
        uploadImageAction.setEnabled(false);
      }
    }
    
    MainPanel(Container container, URL documentBase)
    { this.documentBase=documentBase;
      status.setEditable(false);
      hasServer=documentBase!=null && documentBase.getProtocol().indexOf("http")!=-1;
      container.add(new ToolBar(), BorderLayout.NORTH);
      container.add(this, BorderLayout.CENTER);
      container.add(status, BorderLayout.SOUTH);
      setLayout(new GridLayout(1, 1));
    }
  }
  
  public void init()
  { new MainPanel(getContentPane(), getDocumentBase());
  }
  
  public static void main(String args[])
  { JFrame frame=new JFrame("Morena Studio");
    frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    new MainPanel(frame.getContentPane(), null);
    frame.setBounds(100, 100, 600, 400);
    frame.setVisible(true);
  }
}