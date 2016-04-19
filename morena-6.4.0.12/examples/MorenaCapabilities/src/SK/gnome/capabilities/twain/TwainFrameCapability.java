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

public class TwainFrameCapability
{
  private TwainActivity twainActivity;
  private double tl_x, tl_y, br_x, br_y;
  
  public TwainFrameCapability(TwainActivity twainActivity)
  {
    this.twainActivity = twainActivity;
  }
  
  public void setTL_XValue(double value) throws Exception
  {
    tl_x=value;
//    System.out.println("TwainFrameCapability.setTL_XValue()="+value);
    setFrame(tl_x, tl_y, br_x, br_y);
  }
  
  public void setTL_YValue(double value) throws Exception
  {
    tl_y=value;
//    System.out.println("TwainFrameCapability.setTL_YValue()="+value);
    setFrame(tl_x, tl_y, br_x, br_y);
  }
  
  public void setBR_XValue(double value) throws Exception
  {
    br_x=value;
//    System.out.println("TwainFrameCapability.setBR_XValue()="+value);
    setFrame(tl_x, tl_y, br_x, br_y);
  }
  
  public void setBR_YValue(double value) throws Exception
  {
    br_y=value;
//    System.out.println("TwainFrameCapability.setBR_YValue()="+value);
    setFrame(tl_x, tl_y, br_x, br_y);
  }
  
  public void setFrame(double tl_x, double tl_y, double br_x, double br_y) throws Exception
  {
      if((br_x>tl_x)&&(br_y>tl_y))
      {
        twainActivity.getSource().setFrame(tl_x, tl_y, br_x, br_y);
//        System.out.println("TwainFrameCapability.setFrame()"+tl_x+", "+tl_y+", "+br_x+", "+br_y);
      }
  }
}
