import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JPanel;


public class ImagePanel extends JPanel
{
  private Image image;
  int imageWidth;
  int imageHeight;

  ImagePanel(Image image)
  {
    this.image = image;
    imageWidth = image.getWidth(null);
    imageHeight = image.getHeight(null);
  }

  public Image getImage()
  {
    return image;
  }

  public void paint(Graphics g)
  {
    super.paint(g);
    int panelWidth = getWidth() - 6;
    int panelHeight = getHeight() - 6;
    double horizontalRatio = (double) panelWidth / imageWidth;
    double verticalRatio = (double) panelHeight / imageHeight;
    if (horizontalRatio > verticalRatio)
      g.drawImage(image, (int) (panelWidth - imageWidth * verticalRatio) / 2 + 3, 3,
          (int) (imageWidth * verticalRatio), (int) (imageHeight * verticalRatio), this);
    else
      g.drawImage(image, 3, 3, (int) (imageWidth * horizontalRatio), (int) (imageHeight * horizontalRatio), this);
  }

}
