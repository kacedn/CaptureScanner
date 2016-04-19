/*
 * $Id$
 *
 * Copyright (c) 1999-2011 Gnome spol. s r.o. All Rights Reserved.
 *
 * This software is the confidential and proprietary information of
 * Gnome spol. s r.o. You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms
 * of the license agreement you entered into with Gnome.
 */

// Morena - Image Acquisition Framework version 6

import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.io.File;

import SK.gnome.twain.TwainException;
import SK.gnome.twain.TwainManager;
import SK.gnome.twain.TwainSource;

public class TwainAutomatedTest extends Frame
{ Image image;

  public void paint(Graphics g)  
  { if (null!=image)
      g.drawImage(image, 0, 0, this);
  }
  
  WindowListener windowAdapter=new WindowAdapter()
  { public void windowClosing(WindowEvent e)
    { System.exit(0);
    }
  };
  
  public TwainAutomatedTest()
  { try
    { addWindowListener(windowAdapter);
      setTitle("AutomatedTest Frame Application");
      TwainSource source=TwainManager.selectSource(this,null);
      System.err.println("Testing capabilities for '"+source+"' ...\n");
      source.maskUnsupportedCapabilityException(false);
      source.maskBadValueException(false);

  try
  { int value=source.getAudioFileFormat();
    System.err.println("    getAudioFileFormat()->"+value);
  try
  { source.setAudioFileFormat(value);
    System.err.println("    setAudioFileFormat("+value+")->OK");
  }
  catch (TwainException exception2)
  { System.err.println("    setAudioFileFormat("+value+")->Failed");
  }
  }
  catch (TwainException exception1)
  { System.err.println("    getAudioFileFormat()->Failed");
  }

  try
  { int value[]=source.getSupportedAudioFileFormat();
    System.err.print("    getSupportedAudioFileFormat()->{");
    if (value.length>0)
      System.err.print(value[0]);
    for (int i=1; i<value.length; i++)
      System.err.print(", "+value[i]);
    System.err.println("}");
    for (int i=0; i<value.length; i++)
      try
      { source.setAudioFileFormat(value[i]);
        System.err.println("    setAudioFileFormat("+value[i]+")->OK");
      }
      catch (TwainException exception2)
      { System.err.println("    setAudioFileFormat("+value[i]+")->Failed");
      }
  }
  catch (TwainException exception1)
  { System.err.println("    getSupportedAudioFileFormat()->Failed");
  }
  try
  { int value=source.getDefaultAudioFileFormat();
    System.err.println("    getDefaultAudioFileFormat()->"+value);
  try
  { source.setAudioFileFormat(value);
    System.err.println("    setAudioFileFormat("+value+")->OK");
  }
  catch (TwainException exception2)
  { System.err.println("    setAudioFileFormat("+value+")->Failed");
  }
  }
  catch (TwainException exception1)
  { System.err.println("    getDefaultAudioFileFormat()->Failed");
  }
  System.err.println();
  try
  { int value=source.getAudioTransferMech();
    System.err.println("    getAudioTransferMech()->"+value);
  try
  { source.setAudioTransferMech(value);
    System.err.println("    setAudioTransferMech("+value+")->OK");
  }
  catch (TwainException exception2)
  { System.err.println("    setAudioTransferMech("+value+")->Failed");
  }
  }
  catch (TwainException exception1)
  { System.err.println("    getAudioTransferMech()->Failed");
  }

  try
  { int value[]=source.getSupportedAudioTransferMech();
    System.err.print("    getSupportedAudioTransferMech()->{");
    if (value.length>0)
      System.err.print(value[0]);
    for (int i=1; i<value.length; i++)
      System.err.print(", "+value[i]);
    System.err.println("}");
    for (int i=0; i<value.length; i++)
      try
      { source.setAudioTransferMech(value[i]);
        System.err.println("    setAudioTransferMech("+value[i]+")->OK");
      }
      catch (TwainException exception2)
      { System.err.println("    setAudioTransferMech("+value[i]+")->Failed");
      }
  }
  catch (TwainException exception1)
  { System.err.println("    getSupportedAudioTransferMech()->Failed");
  }
  try
  { int value=source.getDefaultAudioTransferMech();
    System.err.println("    getDefaultAudioTransferMech()->"+value);
  try
  { source.setAudioTransferMech(value);
    System.err.println("    setAudioTransferMech("+value+")->OK");
  }
  catch (TwainException exception2)
  { System.err.println("    setAudioTransferMech("+value+")->Failed");
  }
  }
  catch (TwainException exception1)
  { System.err.println("    getDefaultAudioTransferMech()->Failed");
  }
  System.err.println();
  try
  { int value=source.getAlarmVolume();
    System.err.println("    getAlarmVolume()->"+value);
  try
  { source.setAlarmVolume(value);
    System.err.println("    setAlarmVolume("+value+")->OK");
  }
  catch (TwainException exception2)
  { System.err.println("    setAlarmVolume("+value+")->Failed");
  }
  }
  catch (TwainException exception1)
  { System.err.println("    getAlarmVolume()->Failed");
  }

  try
  { String value=source.getAuthor();
    System.err.println("    getAuthor()->"+value);
  try
  { source.setAuthor(value);
    System.err.println("    setAuthor("+value+")->OK");
  }
  catch (TwainException exception2)
  { System.err.println("    setAuthor("+value+")->Failed");
  }
  }
  catch (TwainException exception1)
  { System.err.println("    getAuthor()->Failed");
  }

  try
  { boolean value=source.getAutoFeed();
    System.err.println("    getAutoFeed()->"+value);
  try
  { source.setAutoFeed(value);
    System.err.println("    setAutoFeed("+value+")->OK");
  }
  catch (TwainException exception2)
  { System.err.println("    setAutoFeed("+value+")->Failed");
  }
  }
  catch (TwainException exception1)
  { System.err.println("    getAutoFeed()->Failed");
  }

  try
  { int value=source.getAutomaticCapture();
    System.err.println("    getAutomaticCapture()->"+value);
  try
  { source.setAutomaticCapture(value);
    System.err.println("    setAutomaticCapture("+value+")->OK");
  }
  catch (TwainException exception2)
  { System.err.println("    setAutomaticCapture("+value+")->Failed");
  }
  }
  catch (TwainException exception1)
  { System.err.println("    getAutomaticCapture()->Failed");
  }

  try
  { boolean value=source.getAutoScan();
    System.err.println("    getAutoScan()->"+value);
  try
  { source.setAutoScan(value);
    System.err.println("    setAutoScan("+value+")->OK");
  }
  catch (TwainException exception2)
  { System.err.println("    setAutoScan("+value+")->Failed");
  }
  }
  catch (TwainException exception1)
  { System.err.println("    getAutoScan()->Failed");
  }

  try
  { int value=source.getBatteryMinutes();
    System.err.println("    getBatteryMinutes()->"+value);
  }
  catch (TwainException exception1)
  { System.err.println("    getBatteryMinutes()->Failed");
  }

  try
  { int value=source.getBatteryPercentage();
    System.err.println("    getBatteryPercentage()->"+value);
  }
  catch (TwainException exception1)
  { System.err.println("    getBatteryPercentage()->Failed");
  }

  try
  { boolean value=source.getCameraPreviewUI();
    System.err.println("    getCameraPreviewUI()->"+value);
  }
  catch (TwainException exception1)
  { System.err.println("    getCameraPreviewUI()->Failed");
  }

  try
  { String value=source.getCaption();
    System.err.println("    getCaption()->"+value);
  try
  { source.setCaption(value);
    System.err.println("    setCaption("+value+")->OK");
  }
  catch (TwainException exception2)
  { System.err.println("    setCaption("+value+")->Failed");
  }
  }
  catch (TwainException exception1)
  { System.err.println("    getCaption()->Failed");
  }

  try
  { int value=source.getClearBuffers();
    System.err.println("    getClearBuffers()->"+value);
  try
  { source.setClearBuffers(value);
    System.err.println("    setClearBuffers("+value+")->OK");
  }
  catch (TwainException exception2)
  { System.err.println("    setClearBuffers("+value+")->Failed");
  }
  }
  catch (TwainException exception1)
  { System.err.println("    getClearBuffers()->Failed");
  }

  try
  { boolean value=source.getCustomDSData();
    System.err.println("    getCustomDSData()->"+value);
  }
  catch (TwainException exception1)
  { System.err.println("    getCustomDSData()->Failed");
  }

  try
  { boolean value=source.getDeviceOnLine();
    System.err.println("    getDeviceOnLine()->"+value);
  }
  catch (TwainException exception1)
  { System.err.println("    getDeviceOnLine()->Failed");
  }

  try
  { String value=source.getDeviceTimeDate();
    System.err.println("    getDeviceTimeDate()->"+value);
  try
  { source.setDeviceTimeDate(value);
    System.err.println("    setDeviceTimeDate("+value+")->OK");
  }
  catch (TwainException exception2)
  { System.err.println("    setDeviceTimeDate("+value+")->Failed");
  }
  }
  catch (TwainException exception1)
  { System.err.println("    getDeviceTimeDate()->Failed");
  }

  try
  { int value=source.getDuplex();
    System.err.println("    getDuplex()->"+value);
  }
  catch (TwainException exception1)
  { System.err.println("    getDuplex()->Failed");
  }

  try
  { boolean value=source.getDuplexEnabled();
    System.err.println("    getDuplexEnabled()->"+value);
  try
  { source.setDuplexEnabled(value);
    System.err.println("    setDuplexEnabled("+value+")->OK");
  }
  catch (TwainException exception2)
  { System.err.println("    setDuplexEnabled("+value+")->Failed");
  }
  }
  catch (TwainException exception1)
  { System.err.println("    getDuplexEnabled()->Failed");
  }

  try
  { boolean value=source.getEnableDSUIOnly();
    System.err.println("    getEnableDSUIOnly()->"+value);
  }
  catch (TwainException exception1)
  { System.err.println("    getEnableDSUIOnly()->Failed");
  }

  try
  { int value=source.getEndorser();
    System.err.println("    getEndorser()->"+value);
  try
  { source.setEndorser(value);
    System.err.println("    setEndorser("+value+")->OK");
  }
  catch (TwainException exception2)
  { System.err.println("    setEndorser("+value+")->Failed");
  }
  }
  catch (TwainException exception1)
  { System.err.println("    getEndorser()->Failed");
  }

  try
  { int value=source.getFeederAlignment();
    System.err.println("    getFeederAlignment()->"+value);
  try
  { source.setFeederAlignment(value);
    System.err.println("    setFeederAlignment("+value+")->OK");
  }
  catch (TwainException exception2)
  { System.err.println("    setFeederAlignment("+value+")->Failed");
  }
  }
  catch (TwainException exception1)
  { System.err.println("    getFeederAlignment()->Failed");
  }

  try
  { boolean value=source.getFeederEnabled();
    System.err.println("    getFeederEnabled()->"+value);
  try
  { source.setFeederEnabled(value);
    System.err.println("    setFeederEnabled("+value+")->OK");
  }
  catch (TwainException exception2)
  { System.err.println("    setFeederEnabled("+value+")->Failed");
  }
  }
  catch (TwainException exception1)
  { System.err.println("    getFeederEnabled()->Failed");
  }

  try
  { boolean value=source.getFeederLoaded();
    System.err.println("    getFeederLoaded()->"+value);
  }
  catch (TwainException exception1)
  { System.err.println("    getFeederLoaded()->Failed");
  }

  try
  { int value=source.getFeederOrder();
    System.err.println("    getFeederOrder()->"+value);
  try
  { source.setFeederOrder(value);
    System.err.println("    setFeederOrder("+value+")->OK");
  }
  catch (TwainException exception2)
  { System.err.println("    setFeederOrder("+value+")->Failed");
  }
  }
  catch (TwainException exception1)
  { System.err.println("    getFeederOrder()->Failed");
  }

  try
  { boolean value=source.getIndicators();
    System.err.println("    getIndicators()->"+value);
  try
  { source.setIndicators(value);
    System.err.println("    setIndicators("+value+")->OK");
  }
  catch (TwainException exception2)
  { System.err.println("    setIndicators("+value+")->Failed");
  }
  }
  catch (TwainException exception1)
  { System.err.println("    getIndicators()->Failed");
  }

  try
  { int value=source.getJobControl();
    System.err.println("    getJobControl()->"+value);
  try
  { source.setJobControl(value);
    System.err.println("    setJobControl("+value+")->OK");
  }
  catch (TwainException exception2)
  { System.err.println("    setJobControl("+value+")->Failed");
  }
  }
  catch (TwainException exception1)
  { System.err.println("    getJobControl()->Failed");
  }

  try
  { int value[]=source.getSupportedJobControl();
    System.err.print("    getSupportedJobControl()->{");
    if (value.length>0)
      System.err.print(value[0]);
    for (int i=1; i<value.length; i++)
      System.err.print(", "+value[i]);
    System.err.println("}");
    for (int i=0; i<value.length; i++)
      try
      { source.setJobControl(value[i]);
        System.err.println("    setJobControl("+value[i]+")->OK");
      }
      catch (TwainException exception2)
      { System.err.println("    setJobControl("+value[i]+")->Failed");
      }
  }
  catch (TwainException exception1)
  { System.err.println("    getSupportedJobControl()->Failed");
  }
  try
  { int value=source.getDefaultJobControl();
    System.err.println("    getDefaultJobControl()->"+value);
  try
  { source.setJobControl(value);
    System.err.println("    setJobControl("+value+")->OK");
  }
  catch (TwainException exception2)
  { System.err.println("    setJobControl("+value+")->Failed");
  }
  }
  catch (TwainException exception1)
  { System.err.println("    getDefaultJobControl()->Failed");
  }
  System.err.println();
  try
  { int value=source.getLanguage();
    System.err.println("    getLanguage()->"+value);
  try
  { source.setLanguage(value);
    System.err.println("    setLanguage("+value+")->OK");
  }
  catch (TwainException exception2)
  { System.err.println("    setLanguage("+value+")->Failed");
  }
  }
  catch (TwainException exception1)
  { System.err.println("    getLanguage()->Failed");
  }

  try
  { int value[]=source.getSupportedLanguage();
    System.err.print("    getSupportedLanguage()->{");
    if (value.length>0)
      System.err.print(value[0]);
    for (int i=1; i<value.length; i++)
      System.err.print(", "+value[i]);
    System.err.println("}");
    for (int i=0; i<value.length; i++)
      try
      { source.setLanguage(value[i]);
        System.err.println("    setLanguage("+value[i]+")->OK");
      }
      catch (TwainException exception2)
      { System.err.println("    setLanguage("+value[i]+")->Failed");
      }
  }
  catch (TwainException exception1)
  { System.err.println("    getSupportedLanguage()->Failed");
  }
  try
  { int value=source.getDefaultLanguage();
    System.err.println("    getDefaultLanguage()->"+value);
  try
  { source.setLanguage(value);
    System.err.println("    setLanguage("+value+")->OK");
  }
  catch (TwainException exception2)
  { System.err.println("    setLanguage("+value+")->Failed");
  }
  }
  catch (TwainException exception1)
  { System.err.println("    getDefaultLanguage()->Failed");
  }
  System.err.println();
  try
  { int value=source.getMaxBatchBuffers();
    System.err.println("    getMaxBatchBuffers()->"+value);
  try
  { source.setMaxBatchBuffers(value);
    System.err.println("    setMaxBatchBuffers("+value+")->OK");
  }
  catch (TwainException exception2)
  { System.err.println("    setMaxBatchBuffers("+value+")->Failed");
  }
  }
  catch (TwainException exception1)
  { System.err.println("    getMaxBatchBuffers()->Failed");
  }

  try
  { int value[]=source.getSupportedMaxBatchBuffers();
    System.err.print("    getSupportedMaxBatchBuffers()->{");
    if (value.length>0)
      System.err.print(value[0]);
    for (int i=1; i<value.length; i++)
      System.err.print(", "+value[i]);
    System.err.println("}");
    for (int i=0; i<value.length; i++)
      try
      { source.setMaxBatchBuffers(value[i]);
        System.err.println("    setMaxBatchBuffers("+value[i]+")->OK");
      }
      catch (TwainException exception2)
      { System.err.println("    setMaxBatchBuffers("+value[i]+")->Failed");
      }
  }
  catch (TwainException exception1)
  { System.err.println("    getSupportedMaxBatchBuffers()->Failed");
  }
  try
  { int value=source.getDefaultMaxBatchBuffers();
    System.err.println("    getDefaultMaxBatchBuffers()->"+value);
  try
  { source.setMaxBatchBuffers(value);
    System.err.println("    setMaxBatchBuffers("+value+")->OK");
  }
  catch (TwainException exception2)
  { System.err.println("    setMaxBatchBuffers("+value+")->Failed");
  }
  }
  catch (TwainException exception1)
  { System.err.println("    getDefaultMaxBatchBuffers()->Failed");
  }
  System.err.println();
  try
  { boolean value=source.getPaperDetectable();
    System.err.println("    getPaperDetectable()->"+value);
  }
  catch (TwainException exception1)
  { System.err.println("    getPaperDetectable()->Failed");
  }

  try
  { int value=source.getPowerSupply();
    System.err.println("    getPowerSupply()->"+value);
  }
  catch (TwainException exception1)
  { System.err.println("    getPowerSupply()->Failed");
  }

  try
  { int value[]=source.getSupportedPowerSupply();
    System.err.print("    getSupportedPowerSupply()->{");
    if (value.length>0)
      System.err.print(value[0]);
    for (int i=1; i<value.length; i++)
      System.err.print(", "+value[i]);
    System.err.println("}");
  }
  catch (TwainException exception1)
  { System.err.println("    getSupportedPowerSupply()->Failed");
  }
  try
  { int value=source.getDefaultPowerSupply();
    System.err.println("    getDefaultPowerSupply()->"+value);
  }
  catch (TwainException exception1)
  { System.err.println("    getDefaultPowerSupply()->Failed");
  }
  System.err.println();
  try
  { int value=source.getPrinter();
    System.err.println("    getPrinter()->"+value);
  try
  { source.setPrinter(value);
    System.err.println("    setPrinter("+value+")->OK");
  }
  catch (TwainException exception2)
  { System.err.println("    setPrinter("+value+")->Failed");
  }
  }
  catch (TwainException exception1)
  { System.err.println("    getPrinter()->Failed");
  }

  try
  { int value[]=source.getSupportedPrinter();
    System.err.print("    getSupportedPrinter()->{");
    if (value.length>0)
      System.err.print(value[0]);
    for (int i=1; i<value.length; i++)
      System.err.print(", "+value[i]);
    System.err.println("}");
    for (int i=0; i<value.length; i++)
      try
      { source.setPrinter(value[i]);
        System.err.println("    setPrinter("+value[i]+")->OK");
      }
      catch (TwainException exception2)
      { System.err.println("    setPrinter("+value[i]+")->Failed");
      }
  }
  catch (TwainException exception1)
  { System.err.println("    getSupportedPrinter()->Failed");
  }
  try
  { int value=source.getDefaultPrinter();
    System.err.println("    getDefaultPrinter()->"+value);
  try
  { source.setPrinter(value);
    System.err.println("    setPrinter("+value+")->OK");
  }
  catch (TwainException exception2)
  { System.err.println("    setPrinter("+value+")->Failed");
  }
  }
  catch (TwainException exception1)
  { System.err.println("    getDefaultPrinter()->Failed");
  }
  System.err.println();
  try
  { boolean value=source.getPrinterEnabled();
    System.err.println("    getPrinterEnabled()->"+value);
  try
  { source.setPrinterEnabled(value);
    System.err.println("    setPrinterEnabled("+value+")->OK");
  }
  catch (TwainException exception2)
  { System.err.println("    setPrinterEnabled("+value+")->Failed");
  }
  }
  catch (TwainException exception1)
  { System.err.println("    getPrinterEnabled()->Failed");
  }

  try
  { int value=source.getPrinterIndex();
    System.err.println("    getPrinterIndex()->"+value);
  try
  { source.setPrinterIndex(value);
    System.err.println("    setPrinterIndex("+value+")->OK");
  }
  catch (TwainException exception2)
  { System.err.println("    setPrinterIndex("+value+")->Failed");
  }
  }
  catch (TwainException exception1)
  { System.err.println("    getPrinterIndex()->Failed");
  }

  try
  { int value=source.getPrinterMode();
    System.err.println("    getPrinterMode()->"+value);
  try
  { source.setPrinterMode(value);
    System.err.println("    setPrinterMode("+value+")->OK");
  }
  catch (TwainException exception2)
  { System.err.println("    setPrinterMode("+value+")->Failed");
  }
  }
  catch (TwainException exception1)
  { System.err.println("    getPrinterMode()->Failed");
  }

  try
  { int value[]=source.getSupportedPrinterMode();
    System.err.print("    getSupportedPrinterMode()->{");
    if (value.length>0)
      System.err.print(value[0]);
    for (int i=1; i<value.length; i++)
      System.err.print(", "+value[i]);
    System.err.println("}");
    for (int i=0; i<value.length; i++)
      try
      { source.setPrinterMode(value[i]);
        System.err.println("    setPrinterMode("+value[i]+")->OK");
      }
      catch (TwainException exception2)
      { System.err.println("    setPrinterMode("+value[i]+")->Failed");
      }
  }
  catch (TwainException exception1)
  { System.err.println("    getSupportedPrinterMode()->Failed");
  }
  try
  { int value=source.getDefaultPrinterMode();
    System.err.println("    getDefaultPrinterMode()->"+value);
  try
  { source.setPrinterMode(value);
    System.err.println("    setPrinterMode("+value+")->OK");
  }
  catch (TwainException exception2)
  { System.err.println("    setPrinterMode("+value+")->Failed");
  }
  }
  catch (TwainException exception1)
  { System.err.println("    getDefaultPrinterMode()->Failed");
  }
  System.err.println();
  try
  { String value=source.getPrinterString();
    System.err.println("    getPrinterString()->"+value);
  try
  { source.setPrinterString(value);
    System.err.println("    setPrinterString("+value+")->OK");
  }
  catch (TwainException exception2)
  { System.err.println("    setPrinterString("+value+")->Failed");
  }
  }
  catch (TwainException exception1)
  { System.err.println("    getPrinterString()->Failed");
  }

  try
  { String value=source.getPrinterSuffix();
    System.err.println("    getPrinterSuffix()->"+value);
  try
  { source.setPrinterSuffix(value);
    System.err.println("    setPrinterSuffix("+value+")->OK");
  }
  catch (TwainException exception2)
  { System.err.println("    setPrinterSuffix("+value+")->Failed");
  }
  }
  catch (TwainException exception1)
  { System.err.println("    getPrinterSuffix()->Failed");
  }

  try
  { String value=source.getSerialNumber();
    System.err.println("    getSerialNumber()->"+value);
  }
  catch (TwainException exception1)
  { System.err.println("    getSerialNumber()->Failed");
  }

  try
  { int value=source.getTimeBeforeFirstCapture();
    System.err.println("    getTimeBeforeFirstCapture()->"+value);
  try
  { source.setTimeBeforeFirstCapture(value);
    System.err.println("    setTimeBeforeFirstCapture("+value+")->OK");
  }
  catch (TwainException exception2)
  { System.err.println("    setTimeBeforeFirstCapture("+value+")->Failed");
  }
  }
  catch (TwainException exception1)
  { System.err.println("    getTimeBeforeFirstCapture()->Failed");
  }

  try
  { int value=source.getTimeBetweenCaptures();
    System.err.println("    getTimeBetweenCaptures()->"+value);
  try
  { source.setTimeBetweenCaptures(value);
    System.err.println("    setTimeBetweenCaptures("+value+")->OK");
  }
  catch (TwainException exception2)
  { System.err.println("    setTimeBetweenCaptures("+value+")->Failed");
  }
  }
  catch (TwainException exception1)
  { System.err.println("    getTimeBetweenCaptures()->Failed");
  }

  try
  { String value=source.getTimeDate();
    System.err.println("    getTimeDate()->"+value);
  }
  catch (TwainException exception1)
  { System.err.println("    getTimeDate()->Failed");
  }

  try
  { boolean value=source.getThumbnailsEnabled();
    System.err.println("    getThumbnailsEnabled()->"+value);
  try
  { source.setThumbnailsEnabled(value);
    System.err.println("    setThumbnailsEnabled("+value+")->OK");
  }
  catch (TwainException exception2)
  { System.err.println("    setThumbnailsEnabled("+value+")->Failed");
  }
  }
  catch (TwainException exception1)
  { System.err.println("    getThumbnailsEnabled()->Failed");
  }

  try
  { boolean value=source.getUIControllable();
    System.err.println("    getUIControllable()->"+value);
  }
  catch (TwainException exception1)
  { System.err.println("    getUIControllable()->Failed");
  }

  try
  { int value=source.getTransferCount();
    System.err.println("    getTransferCount()->"+value);
  try
  { source.setTransferCount(value);
    System.err.println("    setTransferCount("+value+")->OK");
  }
  catch (TwainException exception2)
  { System.err.println("    setTransferCount("+value+")->Failed");
  }
  }
  catch (TwainException exception1)
  { System.err.println("    getTransferCount()->Failed");
  }

  try
  { boolean value=source.getAutomaticBorderDetection();
    System.err.println("    getAutomaticBorderDetection()->"+value);
  try
  { source.setAutomaticBorderDetection(value);
    System.err.println("    setAutomaticBorderDetection("+value+")->OK");
  }
  catch (TwainException exception2)
  { System.err.println("    setAutomaticBorderDetection("+value+")->Failed");
  }
  }
  catch (TwainException exception1)
  { System.err.println("    getAutomaticBorderDetection()->Failed");
  }

  try
  { boolean value=source.getAutoBright();
    System.err.println("    getAutoBright()->"+value);
  try
  { source.setAutoBright(value);
    System.err.println("    setAutoBright("+value+")->OK");
  }
  catch (TwainException exception2)
  { System.err.println("    setAutoBright("+value+")->Failed");
  }
  }
  catch (TwainException exception1)
  { System.err.println("    getAutoBright()->Failed");
  }

  try
  { boolean value=source.getAutomaticDeskew();
    System.err.println("    getAutomaticDeskew()->"+value);
  try
  { source.setAutomaticDeskew(value);
    System.err.println("    setAutomaticDeskew("+value+")->OK");
  }
  catch (TwainException exception2)
  { System.err.println("    setAutomaticDeskew("+value+")->Failed");
  }
  }
  catch (TwainException exception1)
  { System.err.println("    getAutomaticDeskew()->Failed");
  }

  try
  { boolean value=source.getAutomaticRotate();
    System.err.println("    getAutomaticRotate()->"+value);
  try
  { source.setAutomaticRotate(value);
    System.err.println("    setAutomaticRotate("+value+")->OK");
  }
  catch (TwainException exception2)
  { System.err.println("    setAutomaticRotate("+value+")->Failed");
  }
  }
  catch (TwainException exception1)
  { System.err.println("    getAutomaticRotate()->Failed");
  }

  try
  { boolean value=source.getBarCodeDetectionEnabled();
    System.err.println("    getBarCodeDetectionEnabled()->"+value);
  try
  { source.setBarCodeDetectionEnabled(value);
    System.err.println("    setBarCodeDetectionEnabled("+value+")->OK");
  }
  catch (TwainException exception2)
  { System.err.println("    setBarCodeDetectionEnabled("+value+")->Failed");
  }
  }
  catch (TwainException exception1)
  { System.err.println("    getBarCodeDetectionEnabled()->Failed");
  }

  try
  { int value=source.getBarCodeMaxRetries();
    System.err.println("    getBarCodeMaxRetries()->"+value);
  try
  { source.setBarCodeMaxRetries(value);
    System.err.println("    setBarCodeMaxRetries("+value+")->OK");
  }
  catch (TwainException exception2)
  { System.err.println("    setBarCodeMaxRetries("+value+")->Failed");
  }
  }
  catch (TwainException exception1)
  { System.err.println("    getBarCodeMaxRetries()->Failed");
  }

  try
  { int value[]=source.getSupportedBarCodeMaxRetries();
    System.err.print("    getSupportedBarCodeMaxRetries()->{");
    if (value.length>0)
      System.err.print(value[0]);
    for (int i=1; i<value.length; i++)
      System.err.print(", "+value[i]);
    System.err.println("}");
    for (int i=0; i<value.length; i++)
      try
      { source.setBarCodeMaxRetries(value[i]);
        System.err.println("    setBarCodeMaxRetries("+value[i]+")->OK");
      }
      catch (TwainException exception2)
      { System.err.println("    setBarCodeMaxRetries("+value[i]+")->Failed");
      }
  }
  catch (TwainException exception1)
  { System.err.println("    getSupportedBarCodeMaxRetries()->Failed");
  }
  try
  { int value=source.getDefaultBarCodeMaxRetries();
    System.err.println("    getDefaultBarCodeMaxRetries()->"+value);
  try
  { source.setBarCodeMaxRetries(value);
    System.err.println("    setBarCodeMaxRetries("+value+")->OK");
  }
  catch (TwainException exception2)
  { System.err.println("    setBarCodeMaxRetries("+value+")->Failed");
  }
  }
  catch (TwainException exception1)
  { System.err.println("    getDefaultBarCodeMaxRetries()->Failed");
  }
  System.err.println();
  try
  { int value=source.getBarCodeMaxSearchPriorities();
    System.err.println("    getBarCodeMaxSearchPriorities()->"+value);
  try
  { source.setBarCodeMaxSearchPriorities(value);
    System.err.println("    setBarCodeMaxSearchPriorities("+value+")->OK");
  }
  catch (TwainException exception2)
  { System.err.println("    setBarCodeMaxSearchPriorities("+value+")->Failed");
  }
  }
  catch (TwainException exception1)
  { System.err.println("    getBarCodeMaxSearchPriorities()->Failed");
  }

  try
  { int value[]=source.getSupportedBarCodeMaxSearchPriorities();
    System.err.print("    getSupportedBarCodeMaxSearchPriorities()->{");
    if (value.length>0)
      System.err.print(value[0]);
    for (int i=1; i<value.length; i++)
      System.err.print(", "+value[i]);
    System.err.println("}");
    for (int i=0; i<value.length; i++)
      try
      { source.setBarCodeMaxSearchPriorities(value[i]);
        System.err.println("    setBarCodeMaxSearchPriorities("+value[i]+")->OK");
      }
      catch (TwainException exception2)
      { System.err.println("    setBarCodeMaxSearchPriorities("+value[i]+")->Failed");
      }
  }
  catch (TwainException exception1)
  { System.err.println("    getSupportedBarCodeMaxSearchPriorities()->Failed");
  }
  try
  { int value=source.getDefaultBarCodeMaxSearchPriorities();
    System.err.println("    getDefaultBarCodeMaxSearchPriorities()->"+value);
  try
  { source.setBarCodeMaxSearchPriorities(value);
    System.err.println("    setBarCodeMaxSearchPriorities("+value+")->OK");
  }
  catch (TwainException exception2)
  { System.err.println("    setBarCodeMaxSearchPriorities("+value+")->Failed");
  }
  }
  catch (TwainException exception1)
  { System.err.println("    getDefaultBarCodeMaxSearchPriorities()->Failed");
  }
  System.err.println();
  try
  { int value=source.getBarCodeSearchMode();
    System.err.println("    getBarCodeSearchMode()->"+value);
  try
  { source.setBarCodeSearchMode(value);
    System.err.println("    setBarCodeSearchMode("+value+")->OK");
  }
  catch (TwainException exception2)
  { System.err.println("    setBarCodeSearchMode("+value+")->Failed");
  }
  }
  catch (TwainException exception1)
  { System.err.println("    getBarCodeSearchMode()->Failed");
  }

  try
  { int value[]=source.getSupportedBarCodeSearchMode();
    System.err.print("    getSupportedBarCodeSearchMode()->{");
    if (value.length>0)
      System.err.print(value[0]);
    for (int i=1; i<value.length; i++)
      System.err.print(", "+value[i]);
    System.err.println("}");
    for (int i=0; i<value.length; i++)
      try
      { source.setBarCodeSearchMode(value[i]);
        System.err.println("    setBarCodeSearchMode("+value[i]+")->OK");
      }
      catch (TwainException exception2)
      { System.err.println("    setBarCodeSearchMode("+value[i]+")->Failed");
      }
  }
  catch (TwainException exception1)
  { System.err.println("    getSupportedBarCodeSearchMode()->Failed");
  }
  try
  { int value=source.getDefaultBarCodeSearchMode();
    System.err.println("    getDefaultBarCodeSearchMode()->"+value);
  try
  { source.setBarCodeSearchMode(value);
    System.err.println("    setBarCodeSearchMode("+value+")->OK");
  }
  catch (TwainException exception2)
  { System.err.println("    setBarCodeSearchMode("+value+")->Failed");
  }
  }
  catch (TwainException exception1)
  { System.err.println("    getDefaultBarCodeSearchMode()->Failed");
  }
  System.err.println();
  try
  { int value=source.getBarCodeTimeout();
    System.err.println("    getBarCodeTimeout()->"+value);
  try
  { source.setBarCodeTimeout(value);
    System.err.println("    setBarCodeTimeout("+value+")->OK");
  }
  catch (TwainException exception2)
  { System.err.println("    setBarCodeTimeout("+value+")->Failed");
  }
  }
  catch (TwainException exception1)
  { System.err.println("    getBarCodeTimeout()->Failed");
  }

  try
  { int value[]=source.getSupportedBarCodeTimeout();
    System.err.print("    getSupportedBarCodeTimeout()->{");
    if (value.length>0)
      System.err.print(value[0]);
    for (int i=1; i<value.length; i++)
      System.err.print(", "+value[i]);
    System.err.println("}");
    for (int i=0; i<value.length; i++)
      try
      { source.setBarCodeTimeout(value[i]);
        System.err.println("    setBarCodeTimeout("+value[i]+")->OK");
      }
      catch (TwainException exception2)
      { System.err.println("    setBarCodeTimeout("+value[i]+")->Failed");
      }
  }
  catch (TwainException exception1)
  { System.err.println("    getSupportedBarCodeTimeout()->Failed");
  }
  try
  { int value=source.getDefaultBarCodeTimeout();
    System.err.println("    getDefaultBarCodeTimeout()->"+value);
  try
  { source.setBarCodeTimeout(value);
    System.err.println("    setBarCodeTimeout("+value+")->OK");
  }
  catch (TwainException exception2)
  { System.err.println("    setBarCodeTimeout("+value+")->Failed");
  }
  }
  catch (TwainException exception1)
  { System.err.println("    getDefaultBarCodeTimeout()->Failed");
  }
  System.err.println();
  try
  { int value=source.getBitDepth();
    System.err.println("    getBitDepth()->"+value);
  try
  { source.setBitDepth(value);
    System.err.println("    setBitDepth("+value+")->OK");
  }
  catch (TwainException exception2)
  { System.err.println("    setBitDepth("+value+")->Failed");
  }
  }
  catch (TwainException exception1)
  { System.err.println("    getBitDepth()->Failed");
  }

  try
  { int value[]=source.getSupportedBitDepth();
    System.err.print("    getSupportedBitDepth()->{");
    if (value.length>0)
      System.err.print(value[0]);
    for (int i=1; i<value.length; i++)
      System.err.print(", "+value[i]);
    System.err.println("}");
    for (int i=0; i<value.length; i++)
      try
      { source.setBitDepth(value[i]);
        System.err.println("    setBitDepth("+value[i]+")->OK");
      }
      catch (TwainException exception2)
      { System.err.println("    setBitDepth("+value[i]+")->Failed");
      }
  }
  catch (TwainException exception1)
  { System.err.println("    getSupportedBitDepth()->Failed");
  }
  try
  { int value=source.getDefaultBitDepth();
    System.err.println("    getDefaultBitDepth()->"+value);
  try
  { source.setBitDepth(value);
    System.err.println("    setBitDepth("+value+")->OK");
  }
  catch (TwainException exception2)
  { System.err.println("    setBitDepth("+value+")->Failed");
  }
  }
  catch (TwainException exception1)
  { System.err.println("    getDefaultBitDepth()->Failed");
  }
  System.err.println();
  try
  { int value=source.getBitDepthReduction();
    System.err.println("    getBitDepthReduction()->"+value);
  try
  { source.setBitDepthReduction(value);
    System.err.println("    setBitDepthReduction("+value+")->OK");
  }
  catch (TwainException exception2)
  { System.err.println("    setBitDepthReduction("+value+")->Failed");
  }
  }
  catch (TwainException exception1)
  { System.err.println("    getBitDepthReduction()->Failed");
  }

  try
  { int value[]=source.getSupportedBitDepthReduction();
    System.err.print("    getSupportedBitDepthReduction()->{");
    if (value.length>0)
      System.err.print(value[0]);
    for (int i=1; i<value.length; i++)
      System.err.print(", "+value[i]);
    System.err.println("}");
    for (int i=0; i<value.length; i++)
      try
      { source.setBitDepthReduction(value[i]);
        System.err.println("    setBitDepthReduction("+value[i]+")->OK");
      }
      catch (TwainException exception2)
      { System.err.println("    setBitDepthReduction("+value[i]+")->Failed");
      }
  }
  catch (TwainException exception1)
  { System.err.println("    getSupportedBitDepthReduction()->Failed");
  }
  try
  { int value=source.getDefaultBitDepthReduction();
    System.err.println("    getDefaultBitDepthReduction()->"+value);
  try
  { source.setBitDepthReduction(value);
    System.err.println("    setBitDepthReduction("+value+")->OK");
  }
  catch (TwainException exception2)
  { System.err.println("    setBitDepthReduction("+value+")->Failed");
  }
  }
  catch (TwainException exception1)
  { System.err.println("    getDefaultBitDepthReduction()->Failed");
  }
  System.err.println();
  try
  { int value=source.getBitOrder();
    System.err.println("    getBitOrder()->"+value);
  try
  { source.setBitOrder(value);
    System.err.println("    setBitOrder("+value+")->OK");
  }
  catch (TwainException exception2)
  { System.err.println("    setBitOrder("+value+")->Failed");
  }
  }
  catch (TwainException exception1)
  { System.err.println("    getBitOrder()->Failed");
  }

  try
  { int value[]=source.getSupportedBitOrder();
    System.err.print("    getSupportedBitOrder()->{");
    if (value.length>0)
      System.err.print(value[0]);
    for (int i=1; i<value.length; i++)
      System.err.print(", "+value[i]);
    System.err.println("}");
    for (int i=0; i<value.length; i++)
      try
      { source.setBitOrder(value[i]);
        System.err.println("    setBitOrder("+value[i]+")->OK");
      }
      catch (TwainException exception2)
      { System.err.println("    setBitOrder("+value[i]+")->Failed");
      }
  }
  catch (TwainException exception1)
  { System.err.println("    getSupportedBitOrder()->Failed");
  }
  try
  { int value=source.getDefaultBitOrder();
    System.err.println("    getDefaultBitOrder()->"+value);
  try
  { source.setBitOrder(value);
    System.err.println("    setBitOrder("+value+")->OK");
  }
  catch (TwainException exception2)
  { System.err.println("    setBitOrder("+value+")->Failed");
  }
  }
  catch (TwainException exception1)
  { System.err.println("    getDefaultBitOrder()->Failed");
  }
  System.err.println();
  try
  { int value=source.getBitOrderCodes();
    System.err.println("    getBitOrderCodes()->"+value);
  try
  { source.setBitOrderCodes(value);
    System.err.println("    setBitOrderCodes("+value+")->OK");
  }
  catch (TwainException exception2)
  { System.err.println("    setBitOrderCodes("+value+")->Failed");
  }
  }
  catch (TwainException exception1)
  { System.err.println("    getBitOrderCodes()->Failed");
  }

  try
  { int value[]=source.getSupportedBitOrderCodes();
    System.err.print("    getSupportedBitOrderCodes()->{");
    if (value.length>0)
      System.err.print(value[0]);
    for (int i=1; i<value.length; i++)
      System.err.print(", "+value[i]);
    System.err.println("}");
    for (int i=0; i<value.length; i++)
      try
      { source.setBitOrderCodes(value[i]);
        System.err.println("    setBitOrderCodes("+value[i]+")->OK");
      }
      catch (TwainException exception2)
      { System.err.println("    setBitOrderCodes("+value[i]+")->Failed");
      }
  }
  catch (TwainException exception1)
  { System.err.println("    getSupportedBitOrderCodes()->Failed");
  }
  try
  { int value=source.getDefaultBitOrderCodes();
    System.err.println("    getDefaultBitOrderCodes()->"+value);
  try
  { source.setBitOrderCodes(value);
    System.err.println("    setBitOrderCodes("+value+")->OK");
  }
  catch (TwainException exception2)
  { System.err.println("    setBitOrderCodes("+value+")->Failed");
  }
  }
  catch (TwainException exception1)
  { System.err.println("    getDefaultBitOrderCodes()->Failed");
  }
  System.err.println();
  try
  { double value=source.getBrightness();
    System.err.println("    getBrightness()->"+value);
  try
  { source.setBrightness(value);
    System.err.println("    setBrightness("+value+")->OK");
  }
  catch (TwainException exception2)
  { System.err.println("    setBrightness("+value+")->Failed");
  }
  }
  catch (TwainException exception1)
  { System.err.println("    getBrightness()->Failed");
  }

  try
  { double value[]=source.getSupportedBrightness();
    System.err.print("    getSupportedBrightness()->{");
    if (value.length>0)
      System.err.print(value[0]);
    for (int i=1; i<value.length; i++)
      System.err.print(", "+value[i]);
    System.err.println("}");
    for (int i=0; i<value.length; i++)
      try
      { source.setBrightness(value[i]);
        System.err.println("    setBrightness("+value[i]+")->OK");
      }
      catch (TwainException exception2)
      { System.err.println("    setBrightness("+value[i]+")->Failed");
      }
  }
  catch (TwainException exception1)
  { System.err.println("    getSupportedBrightness()->Failed");
  }
  try
  { double value=source.getDefaultBrightness();
    System.err.println("    getDefaultBrightness()->"+value);
  try
  { source.setBrightness(value);
    System.err.println("    setBrightness("+value+")->OK");
  }
  catch (TwainException exception2)
  { System.err.println("    setBrightness("+value+")->Failed");
  }
  }
  catch (TwainException exception1)
  { System.err.println("    getDefaultBrightness()->Failed");
  }
  System.err.println();
  try
  { int value=source.getCCITTKFactor();
    System.err.println("    getCCITTKFactor()->"+value);
  try
  { source.setCCITTKFactor(value);
    System.err.println("    setCCITTKFactor("+value+")->OK");
  }
  catch (TwainException exception2)
  { System.err.println("    setCCITTKFactor("+value+")->Failed");
  }
  }
  catch (TwainException exception1)
  { System.err.println("    getCCITTKFactor()->Failed");
  }

  try
  { int value=source.getCompression();
    System.err.println("    getCompression()->"+value);
  try
  { source.setCompression(value);
    System.err.println("    setCompression("+value+")->OK");
  }
  catch (TwainException exception2)
  { System.err.println("    setCompression("+value+")->Failed");
  }
  }
  catch (TwainException exception1)
  { System.err.println("    getCompression()->Failed");
  }

  try
  { int value[]=source.getSupportedCompression();
    System.err.print("    getSupportedCompression()->{");
    if (value.length>0)
      System.err.print(value[0]);
    for (int i=1; i<value.length; i++)
      System.err.print(", "+value[i]);
    System.err.println("}");
    for (int i=0; i<value.length; i++)
      try
      { source.setCompression(value[i]);
        System.err.println("    setCompression("+value[i]+")->OK");
      }
      catch (TwainException exception2)
      { System.err.println("    setCompression("+value[i]+")->Failed");
      }
  }
  catch (TwainException exception1)
  { System.err.println("    getSupportedCompression()->Failed");
  }
  try
  { int value=source.getDefaultCompression();
    System.err.println("    getDefaultCompression()->"+value);
  try
  { source.setCompression(value);
    System.err.println("    setCompression("+value+")->OK");
  }
  catch (TwainException exception2)
  { System.err.println("    setCompression("+value+")->Failed");
  }
  }
  catch (TwainException exception1)
  { System.err.println("    getDefaultCompression()->Failed");
  }
  System.err.println();
  try
  { double value=source.getContrast();
    System.err.println("    getContrast()->"+value);
  try
  { source.setContrast(value);
    System.err.println("    setContrast("+value+")->OK");
  }
  catch (TwainException exception2)
  { System.err.println("    setContrast("+value+")->Failed");
  }
  }
  catch (TwainException exception1)
  { System.err.println("    getContrast()->Failed");
  }

  try
  { double value[]=source.getSupportedContrast();
    System.err.print("    getSupportedContrast()->{");
    if (value.length>0)
      System.err.print(value[0]);
    for (int i=1; i<value.length; i++)
      System.err.print(", "+value[i]);
    System.err.println("}");
    for (int i=0; i<value.length; i++)
      try
      { source.setContrast(value[i]);
        System.err.println("    setContrast("+value[i]+")->OK");
      }
      catch (TwainException exception2)
      { System.err.println("    setContrast("+value[i]+")->Failed");
      }
  }
  catch (TwainException exception1)
  { System.err.println("    getSupportedContrast()->Failed");
  }
  try
  { double value=source.getDefaultContrast();
    System.err.println("    getDefaultContrast()->"+value);
  try
  { source.setContrast(value);
    System.err.println("    setContrast("+value+")->OK");
  }
  catch (TwainException exception2)
  { System.err.println("    setContrast("+value+")->Failed");
  }
  }
  catch (TwainException exception1)
  { System.err.println("    getDefaultContrast()->Failed");
  }
  System.err.println();
  try
  { double value=source.getExposureTime();
    System.err.println("    getExposureTime()->"+value);
  try
  { source.setExposureTime(value);
    System.err.println("    setExposureTime("+value+")->OK");
  }
  catch (TwainException exception2)
  { System.err.println("    setExposureTime("+value+")->Failed");
  }
  }
  catch (TwainException exception1)
  { System.err.println("    getExposureTime()->Failed");
  }

  try
  { double value[]=source.getSupportedExposureTime();
    System.err.print("    getSupportedExposureTime()->{");
    if (value.length>0)
      System.err.print(value[0]);
    for (int i=1; i<value.length; i++)
      System.err.print(", "+value[i]);
    System.err.println("}");
    for (int i=0; i<value.length; i++)
      try
      { source.setExposureTime(value[i]);
        System.err.println("    setExposureTime("+value[i]+")->OK");
      }
      catch (TwainException exception2)
      { System.err.println("    setExposureTime("+value[i]+")->Failed");
      }
  }
  catch (TwainException exception1)
  { System.err.println("    getSupportedExposureTime()->Failed");
  }
  try
  { double value=source.getDefaultExposureTime();
    System.err.println("    getDefaultExposureTime()->"+value);
  try
  { source.setExposureTime(value);
    System.err.println("    setExposureTime("+value+")->OK");
  }
  catch (TwainException exception2)
  { System.err.println("    setExposureTime("+value+")->Failed");
  }
  }
  catch (TwainException exception1)
  { System.err.println("    getDefaultExposureTime()->Failed");
  }
  System.err.println();
  try
  { boolean value=source.getExtImageInfo();
    System.err.println("    getExtImageInfo()->"+value);
  try
  { source.setExtImageInfo(value);
    System.err.println("    setExtImageInfo("+value+")->OK");
  }
  catch (TwainException exception2)
  { System.err.println("    setExtImageInfo("+value+")->Failed");
  }
  }
  catch (TwainException exception1)
  { System.err.println("    getExtImageInfo()->Failed");
  }

  try
  { int value=source.getFilter();
    System.err.println("    getFilter()->"+value);
  try
  { source.setFilter(value);
    System.err.println("    setFilter("+value+")->OK");
  }
  catch (TwainException exception2)
  { System.err.println("    setFilter("+value+")->Failed");
  }
  }
  catch (TwainException exception1)
  { System.err.println("    getFilter()->Failed");
  }

  try
  { int value=source.getFlashUsed();
    System.err.println("    getFlashUsed()->"+value);
  try
  { source.setFlashUsed(value);
    System.err.println("    setFlashUsed("+value+")->OK");
  }
  catch (TwainException exception2)
  { System.err.println("    setFlashUsed("+value+")->Failed");
  }
  }
  catch (TwainException exception1)
  { System.err.println("    getFlashUsed()->Failed");
  }

  try
  { int value[]=source.getSupportedFlashUsed();
    System.err.print("    getSupportedFlashUsed()->{");
    if (value.length>0)
      System.err.print(value[0]);
    for (int i=1; i<value.length; i++)
      System.err.print(", "+value[i]);
    System.err.println("}");
    for (int i=0; i<value.length; i++)
      try
      { source.setFlashUsed(value[i]);
        System.err.println("    setFlashUsed("+value[i]+")->OK");
      }
      catch (TwainException exception2)
      { System.err.println("    setFlashUsed("+value[i]+")->Failed");
      }
  }
  catch (TwainException exception1)
  { System.err.println("    getSupportedFlashUsed()->Failed");
  }
  try
  { int value=source.getDefaultFlashUsed();
    System.err.println("    getDefaultFlashUsed()->"+value);
  try
  { source.setFlashUsed(value);
    System.err.println("    setFlashUsed("+value+")->OK");
  }
  catch (TwainException exception2)
  { System.err.println("    setFlashUsed("+value+")->Failed");
  }
  }
  catch (TwainException exception1)
  { System.err.println("    getDefaultFlashUsed()->Failed");
  }
  System.err.println();
  try
  { int value=source.getFlipRotation();
    System.err.println("    getFlipRotation()->"+value);
  try
  { source.setFlipRotation(value);
    System.err.println("    setFlipRotation("+value+")->OK");
  }
  catch (TwainException exception2)
  { System.err.println("    setFlipRotation("+value+")->Failed");
  }
  }
  catch (TwainException exception1)
  { System.err.println("    getFlipRotation()->Failed");
  }

  try
  { double value=source.getGamma();
    System.err.println("    getGamma()->"+value);
  try
  { source.setGamma(value);
    System.err.println("    setGamma("+value+")->OK");
  }
  catch (TwainException exception2)
  { System.err.println("    setGamma("+value+")->Failed");
  }
  }
  catch (TwainException exception1)
  { System.err.println("    getGamma()->Failed");
  }

  try
  { String value=source.getHalftones();
    System.err.println("    getHalftones()->"+value);
  try
  { source.setHalftones(value);
    System.err.println("    setHalftones("+value+")->OK");
  }
  catch (TwainException exception2)
  { System.err.println("    setHalftones("+value+")->Failed");
  }
  }
  catch (TwainException exception1)
  { System.err.println("    getHalftones()->Failed");
  }

  try
  { double value=source.getHighlight();
    System.err.println("    getHighlight()->"+value);
  try
  { source.setHighlight(value);
    System.err.println("    setHighlight("+value+")->OK");
  }
  catch (TwainException exception2)
  { System.err.println("    setHighlight("+value+")->Failed");
  }
  }
  catch (TwainException exception1)
  { System.err.println("    getHighlight()->Failed");
  }

  try
  { double value[]=source.getSupportedHighlight();
    System.err.print("    getSupportedHighlight()->{");
    if (value.length>0)
      System.err.print(value[0]);
    for (int i=1; i<value.length; i++)
      System.err.print(", "+value[i]);
    System.err.println("}");
    for (int i=0; i<value.length; i++)
      try
      { source.setHighlight(value[i]);
        System.err.println("    setHighlight("+value[i]+")->OK");
      }
      catch (TwainException exception2)
      { System.err.println("    setHighlight("+value[i]+")->Failed");
      }
  }
  catch (TwainException exception1)
  { System.err.println("    getSupportedHighlight()->Failed");
  }
  try
  { double value=source.getDefaultHighlight();
    System.err.println("    getDefaultHighlight()->"+value);
  try
  { source.setHighlight(value);
    System.err.println("    setHighlight("+value+")->OK");
  }
  catch (TwainException exception2)
  { System.err.println("    setHighlight("+value+")->Failed");
  }
  }
  catch (TwainException exception1)
  { System.err.println("    getDefaultHighlight()->Failed");
  }
  System.err.println();
  try
  { int value=source.getImageDataSet();
    System.err.println("    getImageDataSet()->"+value);
  try
  { source.setImageDataSet(value);
    System.err.println("    setImageDataSet("+value+")->OK");
  }
  catch (TwainException exception2)
  { System.err.println("    setImageDataSet("+value+")->Failed");
  }
  }
  catch (TwainException exception1)
  { System.err.println("    getImageDataSet()->Failed");
  }

  try
  { int value=source.getImageFileFormat();
    System.err.println("    getImageFileFormat()->"+value);
  try
  { source.setImageFileFormat(value);
    System.err.println("    setImageFileFormat("+value+")->OK");
  }
  catch (TwainException exception2)
  { System.err.println("    setImageFileFormat("+value+")->Failed");
  }
  }
  catch (TwainException exception1)
  { System.err.println("    getImageFileFormat()->Failed");
  }

  try
  { int value[]=source.getSupportedImageFileFormat();
    System.err.print("    getSupportedImageFileFormat()->{");
    if (value.length>0)
      System.err.print(value[0]);
    for (int i=1; i<value.length; i++)
      System.err.print(", "+value[i]);
    System.err.println("}");
    for (int i=0; i<value.length; i++)
      try
      { source.setImageFileFormat(value[i]);
        System.err.println("    setImageFileFormat("+value[i]+")->OK");
      }
      catch (TwainException exception2)
      { System.err.println("    setImageFileFormat("+value[i]+")->Failed");
      }
  }
  catch (TwainException exception1)
  { System.err.println("    getSupportedImageFileFormat()->Failed");
  }
  try
  { int value=source.getDefaultImageFileFormat();
    System.err.println("    getDefaultImageFileFormat()->"+value);
  try
  { source.setImageFileFormat(value);
    System.err.println("    setImageFileFormat("+value+")->OK");
  }
  catch (TwainException exception2)
  { System.err.println("    setImageFileFormat("+value+")->Failed");
  }
  }
  catch (TwainException exception1)
  { System.err.println("    getDefaultImageFileFormat()->Failed");
  }
  System.err.println();
  try
  { int value=source.getImageFilter();
    System.err.println("    getImageFilter()->"+value);
  try
  { source.setImageFilter(value);
    System.err.println("    setImageFilter("+value+")->OK");
  }
  catch (TwainException exception2)
  { System.err.println("    setImageFilter("+value+")->Failed");
  }
  }
  catch (TwainException exception1)
  { System.err.println("    getImageFilter()->Failed");
  }

  try
  { int value[]=source.getSupportedImageFilter();
    System.err.print("    getSupportedImageFilter()->{");
    if (value.length>0)
      System.err.print(value[0]);
    for (int i=1; i<value.length; i++)
      System.err.print(", "+value[i]);
    System.err.println("}");
    for (int i=0; i<value.length; i++)
      try
      { source.setImageFilter(value[i]);
        System.err.println("    setImageFilter("+value[i]+")->OK");
      }
      catch (TwainException exception2)
      { System.err.println("    setImageFilter("+value[i]+")->Failed");
      }
  }
  catch (TwainException exception1)
  { System.err.println("    getSupportedImageFilter()->Failed");
  }
  try
  { int value=source.getDefaultImageFilter();
    System.err.println("    getDefaultImageFilter()->"+value);
  try
  { source.setImageFilter(value);
    System.err.println("    setImageFilter("+value+")->OK");
  }
  catch (TwainException exception2)
  { System.err.println("    setImageFilter("+value+")->Failed");
  }
  }
  catch (TwainException exception1)
  { System.err.println("    getDefaultImageFilter()->Failed");
  }
  System.err.println();
  try
  { int value=source.getJPEGPixelType();
    System.err.println("    getJPEGPixelType()->"+value);
  try
  { source.setJPEGPixelType(value);
    System.err.println("    setJPEGPixelType("+value+")->OK");
  }
  catch (TwainException exception2)
  { System.err.println("    setJPEGPixelType("+value+")->Failed");
  }
  }
  catch (TwainException exception1)
  { System.err.println("    getJPEGPixelType()->Failed");
  }

  try
  { int value[]=source.getSupportedJPEGPixelType();
    System.err.print("    getSupportedJPEGPixelType()->{");
    if (value.length>0)
      System.err.print(value[0]);
    for (int i=1; i<value.length; i++)
      System.err.print(", "+value[i]);
    System.err.println("}");
    for (int i=0; i<value.length; i++)
      try
      { source.setJPEGPixelType(value[i]);
        System.err.println("    setJPEGPixelType("+value[i]+")->OK");
      }
      catch (TwainException exception2)
      { System.err.println("    setJPEGPixelType("+value[i]+")->Failed");
      }
  }
  catch (TwainException exception1)
  { System.err.println("    getSupportedJPEGPixelType()->Failed");
  }
  try
  { int value=source.getDefaultJPEGPixelType();
    System.err.println("    getDefaultJPEGPixelType()->"+value);
  try
  { source.setJPEGPixelType(value);
    System.err.println("    setJPEGPixelType("+value+")->OK");
  }
  catch (TwainException exception2)
  { System.err.println("    setJPEGPixelType("+value+")->Failed");
  }
  }
  catch (TwainException exception1)
  { System.err.println("    getDefaultJPEGPixelType()->Failed");
  }
  System.err.println();
  try
  { int value=source.getJPEGQuality();
    System.err.println("    getJPEGQuality()->"+value);
  try
  { source.setJPEGQuality(value);
    System.err.println("    setJPEGQuality("+value+")->OK");
  }
  catch (TwainException exception2)
  { System.err.println("    setJPEGQuality("+value+")->Failed");
  }
  }
  catch (TwainException exception1)
  { System.err.println("    getJPEGQuality()->Failed");
  }

  try
  { int value[]=source.getSupportedJPEGQuality();
    System.err.print("    getSupportedJPEGQuality()->{");
    if (value.length>0)
      System.err.print(value[0]);
    for (int i=1; i<value.length; i++)
      System.err.print(", "+value[i]);
    System.err.println("}");
    for (int i=0; i<value.length; i++)
      try
      { source.setJPEGQuality(value[i]);
        System.err.println("    setJPEGQuality("+value[i]+")->OK");
      }
      catch (TwainException exception2)
      { System.err.println("    setJPEGQuality("+value[i]+")->Failed");
      }
  }
  catch (TwainException exception1)
  { System.err.println("    getSupportedJPEGQuality()->Failed");
  }
  try
  { int value=source.getDefaultJPEGQuality();
    System.err.println("    getDefaultJPEGQuality()->"+value);
  try
  { source.setJPEGQuality(value);
    System.err.println("    setJPEGQuality("+value+")->OK");
  }
  catch (TwainException exception2)
  { System.err.println("    setJPEGQuality("+value+")->Failed");
  }
  }
  catch (TwainException exception1)
  { System.err.println("    getDefaultJPEGQuality()->Failed");
  }
  System.err.println();
  try
  { boolean value=source.getLampState();
    System.err.println("    getLampState()->"+value);
  try
  { source.setLampState(value);
    System.err.println("    setLampState("+value+")->OK");
  }
  catch (TwainException exception2)
  { System.err.println("    setLampState("+value+")->Failed");
  }
  }
  catch (TwainException exception1)
  { System.err.println("    getLampState()->Failed");
  }

  try
  { boolean value[]=source.getSupportedLampState();
    System.err.print("    getSupportedLampState()->{");
    if (value.length>0)
      System.err.print(value[0]);
    for (int i=1; i<value.length; i++)
      System.err.print(", "+value[i]);
    System.err.println("}");
    for (int i=0; i<value.length; i++)
      try
      { source.setLampState(value[i]);
        System.err.println("    setLampState("+value[i]+")->OK");
      }
      catch (TwainException exception2)
      { System.err.println("    setLampState("+value[i]+")->Failed");
      }
  }
  catch (TwainException exception1)
  { System.err.println("    getSupportedLampState()->Failed");
  }
  try
  { boolean value=source.getDefaultLampState();
    System.err.println("    getDefaultLampState()->"+value);
  try
  { source.setLampState(value);
    System.err.println("    setLampState("+value+")->OK");
  }
  catch (TwainException exception2)
  { System.err.println("    setLampState("+value+")->Failed");
  }
  }
  catch (TwainException exception1)
  { System.err.println("    getDefaultLampState()->Failed");
  }
  System.err.println();
  try
  { int value=source.getLightPath();
    System.err.println("    getLightPath()->"+value);
  try
  { source.setLightPath(value);
    System.err.println("    setLightPath("+value+")->OK");
  }
  catch (TwainException exception2)
  { System.err.println("    setLightPath("+value+")->Failed");
  }
  }
  catch (TwainException exception1)
  { System.err.println("    getLightPath()->Failed");
  }

  try
  { int value[]=source.getSupportedLightPath();
    System.err.print("    getSupportedLightPath()->{");
    if (value.length>0)
      System.err.print(value[0]);
    for (int i=1; i<value.length; i++)
      System.err.print(", "+value[i]);
    System.err.println("}");
    for (int i=0; i<value.length; i++)
      try
      { source.setLightPath(value[i]);
        System.err.println("    setLightPath("+value[i]+")->OK");
      }
      catch (TwainException exception2)
      { System.err.println("    setLightPath("+value[i]+")->Failed");
      }
  }
  catch (TwainException exception1)
  { System.err.println("    getSupportedLightPath()->Failed");
  }
  try
  { int value=source.getDefaultLightPath();
    System.err.println("    getDefaultLightPath()->"+value);
  try
  { source.setLightPath(value);
    System.err.println("    setLightPath("+value+")->OK");
  }
  catch (TwainException exception2)
  { System.err.println("    setLightPath("+value+")->Failed");
  }
  }
  catch (TwainException exception1)
  { System.err.println("    getDefaultLightPath()->Failed");
  }
  System.err.println();
  try
  { int value=source.getLightSource();
    System.err.println("    getLightSource()->"+value);
  try
  { source.setLightSource(value);
    System.err.println("    setLightSource("+value+")->OK");
  }
  catch (TwainException exception2)
  { System.err.println("    setLightSource("+value+")->Failed");
  }
  }
  catch (TwainException exception1)
  { System.err.println("    getLightSource()->Failed");
  }

  try
  { int value[]=source.getSupportedLightSource();
    System.err.print("    getSupportedLightSource()->{");
    if (value.length>0)
      System.err.print(value[0]);
    for (int i=1; i<value.length; i++)
      System.err.print(", "+value[i]);
    System.err.println("}");
    for (int i=0; i<value.length; i++)
      try
      { source.setLightSource(value[i]);
        System.err.println("    setLightSource("+value[i]+")->OK");
      }
      catch (TwainException exception2)
      { System.err.println("    setLightSource("+value[i]+")->Failed");
      }
  }
  catch (TwainException exception1)
  { System.err.println("    getSupportedLightSource()->Failed");
  }
  try
  { int value=source.getDefaultLightSource();
    System.err.println("    getDefaultLightSource()->"+value);
  try
  { source.setLightSource(value);
    System.err.println("    setLightSource("+value+")->OK");
  }
  catch (TwainException exception2)
  { System.err.println("    setLightSource("+value+")->Failed");
  }
  }
  catch (TwainException exception1)
  { System.err.println("    getDefaultLightSource()->Failed");
  }
  System.err.println();
  try
  { double value=source.getMinimumHeight();
    System.err.println("    getMinimumHeight()->"+value);
  }
  catch (TwainException exception1)
  { System.err.println("    getMinimumHeight()->Failed");
  }

  try
  { double value=source.getMinimumWidth();
    System.err.println("    getMinimumWidth()->"+value);
  }
  catch (TwainException exception1)
  { System.err.println("    getMinimumWidth()->Failed");
  }

  try
  { int value=source.getNoiseFilter();
    System.err.println("    getNoiseFilter()->"+value);
  try
  { source.setNoiseFilter(value);
    System.err.println("    setNoiseFilter("+value+")->OK");
  }
  catch (TwainException exception2)
  { System.err.println("    setNoiseFilter("+value+")->Failed");
  }
  }
  catch (TwainException exception1)
  { System.err.println("    getNoiseFilter()->Failed");
  }

  try
  { int value[]=source.getSupportedNoiseFilter();
    System.err.print("    getSupportedNoiseFilter()->{");
    if (value.length>0)
      System.err.print(value[0]);
    for (int i=1; i<value.length; i++)
      System.err.print(", "+value[i]);
    System.err.println("}");
    for (int i=0; i<value.length; i++)
      try
      { source.setNoiseFilter(value[i]);
        System.err.println("    setNoiseFilter("+value[i]+")->OK");
      }
      catch (TwainException exception2)
      { System.err.println("    setNoiseFilter("+value[i]+")->Failed");
      }
  }
  catch (TwainException exception1)
  { System.err.println("    getSupportedNoiseFilter()->Failed");
  }
  try
  { int value=source.getDefaultNoiseFilter();
    System.err.println("    getDefaultNoiseFilter()->"+value);
  try
  { source.setNoiseFilter(value);
    System.err.println("    setNoiseFilter("+value+")->OK");
  }
  catch (TwainException exception2)
  { System.err.println("    setNoiseFilter("+value+")->Failed");
  }
  }
  catch (TwainException exception1)
  { System.err.println("    getDefaultNoiseFilter()->Failed");
  }
  System.err.println();
  try
  { int value=source.getOrientation();
    System.err.println("    getOrientation()->"+value);
  try
  { source.setOrientation(value);
    System.err.println("    setOrientation("+value+")->OK");
  }
  catch (TwainException exception2)
  { System.err.println("    setOrientation("+value+")->Failed");
  }
  }
  catch (TwainException exception1)
  { System.err.println("    getOrientation()->Failed");
  }

  try
  { int value[]=source.getSupportedOrientation();
    System.err.print("    getSupportedOrientation()->{");
    if (value.length>0)
      System.err.print(value[0]);
    for (int i=1; i<value.length; i++)
      System.err.print(", "+value[i]);
    System.err.println("}");
    for (int i=0; i<value.length; i++)
      try
      { source.setOrientation(value[i]);
        System.err.println("    setOrientation("+value[i]+")->OK");
      }
      catch (TwainException exception2)
      { System.err.println("    setOrientation("+value[i]+")->Failed");
      }
  }
  catch (TwainException exception1)
  { System.err.println("    getSupportedOrientation()->Failed");
  }
  try
  { int value=source.getDefaultOrientation();
    System.err.println("    getDefaultOrientation()->"+value);
  try
  { source.setOrientation(value);
    System.err.println("    setOrientation("+value+")->OK");
  }
  catch (TwainException exception2)
  { System.err.println("    setOrientation("+value+")->Failed");
  }
  }
  catch (TwainException exception1)
  { System.err.println("    getDefaultOrientation()->Failed");
  }
  System.err.println();
  try
  { int value=source.getOverscan();
    System.err.println("    getOverscan()->"+value);
  try
  { source.setOverscan(value);
    System.err.println("    setOverscan("+value+")->OK");
  }
  catch (TwainException exception2)
  { System.err.println("    setOverscan("+value+")->Failed");
  }
  }
  catch (TwainException exception1)
  { System.err.println("    getOverscan()->Failed");
  }

  try
  { int value[]=source.getSupportedOverscan();
    System.err.print("    getSupportedOverscan()->{");
    if (value.length>0)
      System.err.print(value[0]);
    for (int i=1; i<value.length; i++)
      System.err.print(", "+value[i]);
    System.err.println("}");
    for (int i=0; i<value.length; i++)
      try
      { source.setOverscan(value[i]);
        System.err.println("    setOverscan("+value[i]+")->OK");
      }
      catch (TwainException exception2)
      { System.err.println("    setOverscan("+value[i]+")->Failed");
      }
  }
  catch (TwainException exception1)
  { System.err.println("    getSupportedOverscan()->Failed");
  }
  try
  { int value=source.getDefaultOverscan();
    System.err.println("    getDefaultOverscan()->"+value);
  try
  { source.setOverscan(value);
    System.err.println("    setOverscan("+value+")->OK");
  }
  catch (TwainException exception2)
  { System.err.println("    setOverscan("+value+")->Failed");
  }
  }
  catch (TwainException exception1)
  { System.err.println("    getDefaultOverscan()->Failed");
  }
  System.err.println();
  try
  { boolean value=source.getPatchCodeDetectionEnabled();
    System.err.println("    getPatchCodeDetectionEnabled()->"+value);
  try
  { source.setPatchCodeDetectionEnabled(value);
    System.err.println("    setPatchCodeDetectionEnabled("+value+")->OK");
  }
  catch (TwainException exception2)
  { System.err.println("    setPatchCodeDetectionEnabled("+value+")->Failed");
  }
  }
  catch (TwainException exception1)
  { System.err.println("    getPatchCodeDetectionEnabled()->Failed");
  }

  try
  { int value=source.getPatchCodeMaxRetries();
    System.err.println("    getPatchCodeMaxRetries()->"+value);
  try
  { source.setPatchCodeMaxRetries(value);
    System.err.println("    setPatchCodeMaxRetries("+value+")->OK");
  }
  catch (TwainException exception2)
  { System.err.println("    setPatchCodeMaxRetries("+value+")->Failed");
  }
  }
  catch (TwainException exception1)
  { System.err.println("    getPatchCodeMaxRetries()->Failed");
  }

  try
  { int value[]=source.getSupportedPatchCodeMaxRetries();
    System.err.print("    getSupportedPatchCodeMaxRetries()->{");
    if (value.length>0)
      System.err.print(value[0]);
    for (int i=1; i<value.length; i++)
      System.err.print(", "+value[i]);
    System.err.println("}");
    for (int i=0; i<value.length; i++)
      try
      { source.setPatchCodeMaxRetries(value[i]);
        System.err.println("    setPatchCodeMaxRetries("+value[i]+")->OK");
      }
      catch (TwainException exception2)
      { System.err.println("    setPatchCodeMaxRetries("+value[i]+")->Failed");
      }
  }
  catch (TwainException exception1)
  { System.err.println("    getSupportedPatchCodeMaxRetries()->Failed");
  }
  try
  { int value=source.getDefaultPatchCodeMaxRetries();
    System.err.println("    getDefaultPatchCodeMaxRetries()->"+value);
  try
  { source.setPatchCodeMaxRetries(value);
    System.err.println("    setPatchCodeMaxRetries("+value+")->OK");
  }
  catch (TwainException exception2)
  { System.err.println("    setPatchCodeMaxRetries("+value+")->Failed");
  }
  }
  catch (TwainException exception1)
  { System.err.println("    getDefaultPatchCodeMaxRetries()->Failed");
  }
  System.err.println();
  try
  { int value=source.getPatchCodeMaxSearchPriorities();
    System.err.println("    getPatchCodeMaxSearchPriorities()->"+value);
  try
  { source.setPatchCodeMaxSearchPriorities(value);
    System.err.println("    setPatchCodeMaxSearchPriorities("+value+")->OK");
  }
  catch (TwainException exception2)
  { System.err.println("    setPatchCodeMaxSearchPriorities("+value+")->Failed");
  }
  }
  catch (TwainException exception1)
  { System.err.println("    getPatchCodeMaxSearchPriorities()->Failed");
  }

  try
  { int value[]=source.getSupportedPatchCodeMaxSearchPriorities();
    System.err.print("    getSupportedPatchCodeMaxSearchPriorities()->{");
    if (value.length>0)
      System.err.print(value[0]);
    for (int i=1; i<value.length; i++)
      System.err.print(", "+value[i]);
    System.err.println("}");
    for (int i=0; i<value.length; i++)
      try
      { source.setPatchCodeMaxSearchPriorities(value[i]);
        System.err.println("    setPatchCodeMaxSearchPriorities("+value[i]+")->OK");
      }
      catch (TwainException exception2)
      { System.err.println("    setPatchCodeMaxSearchPriorities("+value[i]+")->Failed");
      }
  }
  catch (TwainException exception1)
  { System.err.println("    getSupportedPatchCodeMaxSearchPriorities()->Failed");
  }
  try
  { int value=source.getDefaultPatchCodeMaxSearchPriorities();
    System.err.println("    getDefaultPatchCodeMaxSearchPriorities()->"+value);
  try
  { source.setPatchCodeMaxSearchPriorities(value);
    System.err.println("    setPatchCodeMaxSearchPriorities("+value+")->OK");
  }
  catch (TwainException exception2)
  { System.err.println("    setPatchCodeMaxSearchPriorities("+value+")->Failed");
  }
  }
  catch (TwainException exception1)
  { System.err.println("    getDefaultPatchCodeMaxSearchPriorities()->Failed");
  }
  System.err.println();
  try
  { int value=source.getPatchCodeSearchMode();
    System.err.println("    getPatchCodeSearchMode()->"+value);
  try
  { source.setPatchCodeSearchMode(value);
    System.err.println("    setPatchCodeSearchMode("+value+")->OK");
  }
  catch (TwainException exception2)
  { System.err.println("    setPatchCodeSearchMode("+value+")->Failed");
  }
  }
  catch (TwainException exception1)
  { System.err.println("    getPatchCodeSearchMode()->Failed");
  }

  try
  { int value[]=source.getSupportedPatchCodeSearchMode();
    System.err.print("    getSupportedPatchCodeSearchMode()->{");
    if (value.length>0)
      System.err.print(value[0]);
    for (int i=1; i<value.length; i++)
      System.err.print(", "+value[i]);
    System.err.println("}");
    for (int i=0; i<value.length; i++)
      try
      { source.setPatchCodeSearchMode(value[i]);
        System.err.println("    setPatchCodeSearchMode("+value[i]+")->OK");
      }
      catch (TwainException exception2)
      { System.err.println("    setPatchCodeSearchMode("+value[i]+")->Failed");
      }
  }
  catch (TwainException exception1)
  { System.err.println("    getSupportedPatchCodeSearchMode()->Failed");
  }
  try
  { int value=source.getDefaultPatchCodeSearchMode();
    System.err.println("    getDefaultPatchCodeSearchMode()->"+value);
  try
  { source.setPatchCodeSearchMode(value);
    System.err.println("    setPatchCodeSearchMode("+value+")->OK");
  }
  catch (TwainException exception2)
  { System.err.println("    setPatchCodeSearchMode("+value+")->Failed");
  }
  }
  catch (TwainException exception1)
  { System.err.println("    getDefaultPatchCodeSearchMode()->Failed");
  }
  System.err.println();
  try
  { int value=source.getPatchCodeTimeout();
    System.err.println("    getPatchCodeTimeout()->"+value);
  try
  { source.setPatchCodeTimeout(value);
    System.err.println("    setPatchCodeTimeout("+value+")->OK");
  }
  catch (TwainException exception2)
  { System.err.println("    setPatchCodeTimeout("+value+")->Failed");
  }
  }
  catch (TwainException exception1)
  { System.err.println("    getPatchCodeTimeout()->Failed");
  }

  try
  { int value[]=source.getSupportedPatchCodeTimeout();
    System.err.print("    getSupportedPatchCodeTimeout()->{");
    if (value.length>0)
      System.err.print(value[0]);
    for (int i=1; i<value.length; i++)
      System.err.print(", "+value[i]);
    System.err.println("}");
    for (int i=0; i<value.length; i++)
      try
      { source.setPatchCodeTimeout(value[i]);
        System.err.println("    setPatchCodeTimeout("+value[i]+")->OK");
      }
      catch (TwainException exception2)
      { System.err.println("    setPatchCodeTimeout("+value[i]+")->Failed");
      }
  }
  catch (TwainException exception1)
  { System.err.println("    getSupportedPatchCodeTimeout()->Failed");
  }
  try
  { int value=source.getDefaultPatchCodeTimeout();
    System.err.println("    getDefaultPatchCodeTimeout()->"+value);
  try
  { source.setPatchCodeTimeout(value);
    System.err.println("    setPatchCodeTimeout("+value+")->OK");
  }
  catch (TwainException exception2)
  { System.err.println("    setPatchCodeTimeout("+value+")->Failed");
  }
  }
  catch (TwainException exception1)
  { System.err.println("    getDefaultPatchCodeTimeout()->Failed");
  }
  System.err.println();
  try
  { double value=source.getPhysicalHeight();
    System.err.println("    getPhysicalHeight()->"+value);
  }
  catch (TwainException exception1)
  { System.err.println("    getPhysicalHeight()->Failed");
  }

  try
  { double value=source.getPhysicalWidth();
    System.err.println("    getPhysicalWidth()->"+value);
  }
  catch (TwainException exception1)
  { System.err.println("    getPhysicalWidth()->Failed");
  }

  try
  { int value=source.getPixelFlavor();
    System.err.println("    getPixelFlavor()->"+value);
  try
  { source.setPixelFlavor(value);
    System.err.println("    setPixelFlavor("+value+")->OK");
  }
  catch (TwainException exception2)
  { System.err.println("    setPixelFlavor("+value+")->Failed");
  }
  }
  catch (TwainException exception1)
  { System.err.println("    getPixelFlavor()->Failed");
  }

  try
  { int value[]=source.getSupportedPixelFlavor();
    System.err.print("    getSupportedPixelFlavor()->{");
    if (value.length>0)
      System.err.print(value[0]);
    for (int i=1; i<value.length; i++)
      System.err.print(", "+value[i]);
    System.err.println("}");
    for (int i=0; i<value.length; i++)
      try
      { source.setPixelFlavor(value[i]);
        System.err.println("    setPixelFlavor("+value[i]+")->OK");
      }
      catch (TwainException exception2)
      { System.err.println("    setPixelFlavor("+value[i]+")->Failed");
      }
  }
  catch (TwainException exception1)
  { System.err.println("    getSupportedPixelFlavor()->Failed");
  }
  try
  { int value=source.getDefaultPixelFlavor();
    System.err.println("    getDefaultPixelFlavor()->"+value);
  try
  { source.setPixelFlavor(value);
    System.err.println("    setPixelFlavor("+value+")->OK");
  }
  catch (TwainException exception2)
  { System.err.println("    setPixelFlavor("+value+")->Failed");
  }
  }
  catch (TwainException exception1)
  { System.err.println("    getDefaultPixelFlavor()->Failed");
  }
  System.err.println();
  try
  { int value=source.getPixelFlavorCodes();
    System.err.println("    getPixelFlavorCodes()->"+value);
  try
  { source.setPixelFlavorCodes(value);
    System.err.println("    setPixelFlavorCodes("+value+")->OK");
  }
  catch (TwainException exception2)
  { System.err.println("    setPixelFlavorCodes("+value+")->Failed");
  }
  }
  catch (TwainException exception1)
  { System.err.println("    getPixelFlavorCodes()->Failed");
  }

  try
  { int value[]=source.getSupportedPixelFlavorCodes();
    System.err.print("    getSupportedPixelFlavorCodes()->{");
    if (value.length>0)
      System.err.print(value[0]);
    for (int i=1; i<value.length; i++)
      System.err.print(", "+value[i]);
    System.err.println("}");
    for (int i=0; i<value.length; i++)
      try
      { source.setPixelFlavorCodes(value[i]);
        System.err.println("    setPixelFlavorCodes("+value[i]+")->OK");
      }
      catch (TwainException exception2)
      { System.err.println("    setPixelFlavorCodes("+value[i]+")->Failed");
      }
  }
  catch (TwainException exception1)
  { System.err.println("    getSupportedPixelFlavorCodes()->Failed");
  }
  try
  { int value=source.getDefaultPixelFlavorCodes();
    System.err.println("    getDefaultPixelFlavorCodes()->"+value);
  try
  { source.setPixelFlavorCodes(value);
    System.err.println("    setPixelFlavorCodes("+value+")->OK");
  }
  catch (TwainException exception2)
  { System.err.println("    setPixelFlavorCodes("+value+")->Failed");
  }
  }
  catch (TwainException exception1)
  { System.err.println("    getDefaultPixelFlavorCodes()->Failed");
  }
  System.err.println();
  try
  { int value=source.getPixelType();
    System.err.println("    getPixelType()->"+value);
  try
  { source.setPixelType(value);
    System.err.println("    setPixelType("+value+")->OK");
  }
  catch (TwainException exception2)
  { System.err.println("    setPixelType("+value+")->Failed");
  }
  }
  catch (TwainException exception1)
  { System.err.println("    getPixelType()->Failed");
  }

  try
  { int value[]=source.getSupportedPixelType();
    System.err.print("    getSupportedPixelType()->{");
    if (value.length>0)
      System.err.print(value[0]);
    for (int i=1; i<value.length; i++)
      System.err.print(", "+value[i]);
    System.err.println("}");
    for (int i=0; i<value.length; i++)
      try
      { source.setPixelType(value[i]);
        System.err.println("    setPixelType("+value[i]+")->OK");
      }
      catch (TwainException exception2)
      { System.err.println("    setPixelType("+value[i]+")->Failed");
      }
  }
  catch (TwainException exception1)
  { System.err.println("    getSupportedPixelType()->Failed");
  }
  try
  { int value=source.getDefaultPixelType();
    System.err.println("    getDefaultPixelType()->"+value);
  try
  { source.setPixelType(value);
    System.err.println("    setPixelType("+value+")->OK");
  }
  catch (TwainException exception2)
  { System.err.println("    setPixelType("+value+")->Failed");
  }
  }
  catch (TwainException exception1)
  { System.err.println("    getDefaultPixelType()->Failed");
  }
  System.err.println();
  try
  { int value=source.getPlanarChunky();
    System.err.println("    getPlanarChunky()->"+value);
  try
  { source.setPlanarChunky(value);
    System.err.println("    setPlanarChunky("+value+")->OK");
  }
  catch (TwainException exception2)
  { System.err.println("    setPlanarChunky("+value+")->Failed");
  }
  }
  catch (TwainException exception1)
  { System.err.println("    getPlanarChunky()->Failed");
  }

  try
  { int value[]=source.getSupportedPlanarChunky();
    System.err.print("    getSupportedPlanarChunky()->{");
    if (value.length>0)
      System.err.print(value[0]);
    for (int i=1; i<value.length; i++)
      System.err.print(", "+value[i]);
    System.err.println("}");
    for (int i=0; i<value.length; i++)
      try
      { source.setPlanarChunky(value[i]);
        System.err.println("    setPlanarChunky("+value[i]+")->OK");
      }
      catch (TwainException exception2)
      { System.err.println("    setPlanarChunky("+value[i]+")->Failed");
      }
  }
  catch (TwainException exception1)
  { System.err.println("    getSupportedPlanarChunky()->Failed");
  }
  try
  { int value=source.getDefaultPlanarChunky();
    System.err.println("    getDefaultPlanarChunky()->"+value);
  try
  { source.setPlanarChunky(value);
    System.err.println("    setPlanarChunky("+value+")->OK");
  }
  catch (TwainException exception2)
  { System.err.println("    setPlanarChunky("+value+")->Failed");
  }
  }
  catch (TwainException exception1)
  { System.err.println("    getDefaultPlanarChunky()->Failed");
  }
  System.err.println();
  try
  { double value=source.getRotation();
    System.err.println("    getRotation()->"+value);
  try
  { source.setRotation(value);
    System.err.println("    setRotation("+value+")->OK");
  }
  catch (TwainException exception2)
  { System.err.println("    setRotation("+value+")->Failed");
  }
  }
  catch (TwainException exception1)
  { System.err.println("    getRotation()->Failed");
  }

  try
  { double value[]=source.getSupportedRotation();
    System.err.print("    getSupportedRotation()->{");
    if (value.length>0)
      System.err.print(value[0]);
    for (int i=1; i<value.length; i++)
      System.err.print(", "+value[i]);
    System.err.println("}");
    for (int i=0; i<value.length; i++)
      try
      { source.setRotation(value[i]);
        System.err.println("    setRotation("+value[i]+")->OK");
      }
      catch (TwainException exception2)
      { System.err.println("    setRotation("+value[i]+")->Failed");
      }
  }
  catch (TwainException exception1)
  { System.err.println("    getSupportedRotation()->Failed");
  }
  try
  { double value=source.getDefaultRotation();
    System.err.println("    getDefaultRotation()->"+value);
  try
  { source.setRotation(value);
    System.err.println("    setRotation("+value+")->OK");
  }
  catch (TwainException exception2)
  { System.err.println("    setRotation("+value+")->Failed");
  }
  }
  catch (TwainException exception1)
  { System.err.println("    getDefaultRotation()->Failed");
  }
  System.err.println();
  try
  { double value=source.getShadow();
    System.err.println("    getShadow()->"+value);
  try
  { source.setShadow(value);
    System.err.println("    setShadow("+value+")->OK");
  }
  catch (TwainException exception2)
  { System.err.println("    setShadow("+value+")->Failed");
  }
  }
  catch (TwainException exception1)
  { System.err.println("    getShadow()->Failed");
  }

  try
  { double value[]=source.getSupportedShadow();
    System.err.print("    getSupportedShadow()->{");
    if (value.length>0)
      System.err.print(value[0]);
    for (int i=1; i<value.length; i++)
      System.err.print(", "+value[i]);
    System.err.println("}");
    for (int i=0; i<value.length; i++)
      try
      { source.setShadow(value[i]);
        System.err.println("    setShadow("+value[i]+")->OK");
      }
      catch (TwainException exception2)
      { System.err.println("    setShadow("+value[i]+")->Failed");
      }
  }
  catch (TwainException exception1)
  { System.err.println("    getSupportedShadow()->Failed");
  }
  try
  { double value=source.getDefaultShadow();
    System.err.println("    getDefaultShadow()->"+value);
  try
  { source.setShadow(value);
    System.err.println("    setShadow("+value+")->OK");
  }
  catch (TwainException exception2)
  { System.err.println("    setShadow("+value+")->Failed");
  }
  }
  catch (TwainException exception1)
  { System.err.println("    getDefaultShadow()->Failed");
  }
  System.err.println();
  try
  { int value=source.getSupportedPatchCodeTypes();
    System.err.println("    getSupportedPatchCodeTypes()->"+value);
  }
  catch (TwainException exception1)
  { System.err.println("    getSupportedPatchCodeTypes()->Failed");
  }

  try
  { int value=source.getSupportedSizes();
    System.err.println("    getSupportedSizes()->"+value);
  try
  { source.setSupportedSizes(value);
    System.err.println("    setSupportedSizes("+value+")->OK");
  }
  catch (TwainException exception2)
  { System.err.println("    setSupportedSizes("+value+")->Failed");
  }
  }
  catch (TwainException exception1)
  { System.err.println("    getSupportedSizes()->Failed");
  }

  try
  { int value[]=source.getSupportedSupportedSizes();
    System.err.print("    getSupportedSupportedSizes()->{");
    if (value.length>0)
      System.err.print(value[0]);
    for (int i=1; i<value.length; i++)
      System.err.print(", "+value[i]);
    System.err.println("}");
    for (int i=0; i<value.length; i++)
      try
      { source.setSupportedSizes(value[i]);
        System.err.println("    setSupportedSizes("+value[i]+")->OK");
      }
      catch (TwainException exception2)
      { System.err.println("    setSupportedSizes("+value[i]+")->Failed");
      }
  }
  catch (TwainException exception1)
  { System.err.println("    getSupportedSupportedSizes()->Failed");
  }
  try
  { int value=source.getDefaultSupportedSizes();
    System.err.println("    getDefaultSupportedSizes()->"+value);
  try
  { source.setSupportedSizes(value);
    System.err.println("    setSupportedSizes("+value+")->OK");
  }
  catch (TwainException exception2)
  { System.err.println("    setSupportedSizes("+value+")->Failed");
  }
  }
  catch (TwainException exception1)
  { System.err.println("    getDefaultSupportedSizes()->Failed");
  }
  System.err.println();
  try
  { double value=source.getThreshold();
    System.err.println("    getThreshold()->"+value);
  try
  { source.setThreshold(value);
    System.err.println("    setThreshold("+value+")->OK");
  }
  catch (TwainException exception2)
  { System.err.println("    setThreshold("+value+")->Failed");
  }
  }
  catch (TwainException exception1)
  { System.err.println("    getThreshold()->Failed");
  }

  try
  { double value[]=source.getSupportedThreshold();
    System.err.print("    getSupportedThreshold()->{");
    if (value.length>0)
      System.err.print(value[0]);
    for (int i=1; i<value.length; i++)
      System.err.print(", "+value[i]);
    System.err.println("}");
    for (int i=0; i<value.length; i++)
      try
      { source.setThreshold(value[i]);
        System.err.println("    setThreshold("+value[i]+")->OK");
      }
      catch (TwainException exception2)
      { System.err.println("    setThreshold("+value[i]+")->Failed");
      }
  }
  catch (TwainException exception1)
  { System.err.println("    getSupportedThreshold()->Failed");
  }
  try
  { double value=source.getDefaultThreshold();
    System.err.println("    getDefaultThreshold()->"+value);
  try
  { source.setThreshold(value);
    System.err.println("    setThreshold("+value+")->OK");
  }
  catch (TwainException exception2)
  { System.err.println("    setThreshold("+value+")->Failed");
  }
  }
  catch (TwainException exception1)
  { System.err.println("    getDefaultThreshold()->Failed");
  }
  System.err.println();
  try
  { int value=source.getTimeFill();
    System.err.println("    getTimeFill()->"+value);
  try
  { source.setTimeFill(value);
    System.err.println("    setTimeFill("+value+")->OK");
  }
  catch (TwainException exception2)
  { System.err.println("    setTimeFill("+value+")->Failed");
  }
  }
  catch (TwainException exception1)
  { System.err.println("    getTimeFill()->Failed");
  }

  try
  { boolean value=source.getUndefinedImageSize();
    System.err.println("    getUndefinedImageSize()->"+value);
  try
  { source.setUndefinedImageSize(value);
    System.err.println("    setUndefinedImageSize("+value+")->OK");
  }
  catch (TwainException exception2)
  { System.err.println("    setUndefinedImageSize("+value+")->Failed");
  }
  }
  catch (TwainException exception1)
  { System.err.println("    getUndefinedImageSize()->Failed");
  }

  try
  { int value=source.getUnits();
    System.err.println("    getUnits()->"+value);
  try
  { source.setUnits(value);
    System.err.println("    setUnits("+value+")->OK");
  }
  catch (TwainException exception2)
  { System.err.println("    setUnits("+value+")->Failed");
  }
  }
  catch (TwainException exception1)
  { System.err.println("    getUnits()->Failed");
  }

  try
  { int value[]=source.getSupportedUnits();
    System.err.print("    getSupportedUnits()->{");
    if (value.length>0)
      System.err.print(value[0]);
    for (int i=1; i<value.length; i++)
      System.err.print(", "+value[i]);
    System.err.println("}");
    for (int i=0; i<value.length; i++)
      try
      { source.setUnits(value[i]);
        System.err.println("    setUnits("+value[i]+")->OK");
      }
      catch (TwainException exception2)
      { System.err.println("    setUnits("+value[i]+")->Failed");
      }
  }
  catch (TwainException exception1)
  { System.err.println("    getSupportedUnits()->Failed");
  }
  try
  { int value=source.getDefaultUnits();
    System.err.println("    getDefaultUnits()->"+value);
  try
  { source.setUnits(value);
    System.err.println("    setUnits("+value+")->OK");
  }
  catch (TwainException exception2)
  { System.err.println("    setUnits("+value+")->Failed");
  }
  }
  catch (TwainException exception1)
  { System.err.println("    getDefaultUnits()->Failed");
  }
  System.err.println();
  try
  { int value=source.getTransferMech();
    System.err.println("    getTransferMech()->"+value);
  try
  { source.setTransferMech(value);
    System.err.println("    setTransferMech("+value+")->OK");
  }
  catch (TwainException exception2)
  { System.err.println("    setTransferMech("+value+")->Failed");
  }
  }
  catch (TwainException exception1)
  { System.err.println("    getTransferMech()->Failed");
  }

  try
  { int value[]=source.getSupportedTransferMech();
    System.err.print("    getSupportedTransferMech()->{");
    if (value.length>0)
      System.err.print(value[0]);
    for (int i=1; i<value.length; i++)
      System.err.print(", "+value[i]);
    System.err.println("}");
    for (int i=0; i<value.length; i++)
      try
      { source.setTransferMech(value[i]);
        System.err.println("    setTransferMech("+value[i]+")->OK");
      }
      catch (TwainException exception2)
      { System.err.println("    setTransferMech("+value[i]+")->Failed");
      }
  }
  catch (TwainException exception1)
  { System.err.println("    getSupportedTransferMech()->Failed");
  }
  try
  { int value=source.getDefaultTransferMech();
    System.err.println("    getDefaultTransferMech()->"+value);
  try
  { source.setTransferMech(value);
    System.err.println("    setTransferMech("+value+")->OK");
  }
  catch (TwainException exception2)
  { System.err.println("    setTransferMech("+value+")->Failed");
  }
  }
  catch (TwainException exception1)
  { System.err.println("    getDefaultTransferMech()->Failed");
  }
  System.err.println();
  try
  { double value=source.getXNativeResolution();
    System.err.println("    getXNativeResolution()->"+value);
  }
  catch (TwainException exception1)
  { System.err.println("    getXNativeResolution()->Failed");
  }

  try
  { double value[]=source.getSupportedXNativeResolution();
    System.err.print("    getSupportedXNativeResolution()->{");
    if (value.length>0)
      System.err.print(value[0]);
    for (int i=1; i<value.length; i++)
      System.err.print(", "+value[i]);
    System.err.println("}");
  }
  catch (TwainException exception1)
  { System.err.println("    getSupportedXNativeResolution()->Failed");
  }
  try
  { double value=source.getDefaultXNativeResolution();
    System.err.println("    getDefaultXNativeResolution()->"+value);
  }
  catch (TwainException exception1)
  { System.err.println("    getDefaultXNativeResolution()->Failed");
  }
  System.err.println();
  try
  { double value=source.getXResolution();
    System.err.println("    getXResolution()->"+value);
  try
  { source.setXResolution(value);
    System.err.println("    setXResolution("+value+")->OK");
  }
  catch (TwainException exception2)
  { System.err.println("    setXResolution("+value+")->Failed");
  }
  }
  catch (TwainException exception1)
  { System.err.println("    getXResolution()->Failed");
  }

  try
  { double value[]=source.getSupportedXResolution();
    System.err.print("    getSupportedXResolution()->{");
    if (value.length>0)
      System.err.print(value[0]);
    for (int i=1; i<value.length; i++)
      System.err.print(", "+value[i]);
    System.err.println("}");
    for (int i=0; i<value.length; i++)
      try
      { source.setXResolution(value[i]);
        System.err.println("    setXResolution("+value[i]+")->OK");
      }
      catch (TwainException exception2)
      { System.err.println("    setXResolution("+value[i]+")->Failed");
      }
  }
  catch (TwainException exception1)
  { System.err.println("    getSupportedXResolution()->Failed");
  }
  try
  { double value=source.getDefaultXResolution();
    System.err.println("    getDefaultXResolution()->"+value);
  try
  { source.setXResolution(value);
    System.err.println("    setXResolution("+value+")->OK");
  }
  catch (TwainException exception2)
  { System.err.println("    setXResolution("+value+")->Failed");
  }
  }
  catch (TwainException exception1)
  { System.err.println("    getDefaultXResolution()->Failed");
  }
  System.err.println();
  try
  { double value=source.getXScaling();
    System.err.println("    getXScaling()->"+value);
  try
  { source.setXScaling(value);
    System.err.println("    setXScaling("+value+")->OK");
  }
  catch (TwainException exception2)
  { System.err.println("    setXScaling("+value+")->Failed");
  }
  }
  catch (TwainException exception1)
  { System.err.println("    getXScaling()->Failed");
  }

  try
  { double value[]=source.getSupportedXScaling();
    System.err.print("    getSupportedXScaling()->{");
    if (value.length>0)
      System.err.print(value[0]);
    for (int i=1; i<value.length; i++)
      System.err.print(", "+value[i]);
    System.err.println("}");
    for (int i=0; i<value.length; i++)
      try
      { source.setXScaling(value[i]);
        System.err.println("    setXScaling("+value[i]+")->OK");
      }
      catch (TwainException exception2)
      { System.err.println("    setXScaling("+value[i]+")->Failed");
      }
  }
  catch (TwainException exception1)
  { System.err.println("    getSupportedXScaling()->Failed");
  }
  try
  { double value=source.getDefaultXScaling();
    System.err.println("    getDefaultXScaling()->"+value);
  try
  { source.setXScaling(value);
    System.err.println("    setXScaling("+value+")->OK");
  }
  catch (TwainException exception2)
  { System.err.println("    setXScaling("+value+")->Failed");
  }
  }
  catch (TwainException exception1)
  { System.err.println("    getDefaultXScaling()->Failed");
  }
  System.err.println();
  try
  { double value=source.getYNativeResolution();
    System.err.println("    getYNativeResolution()->"+value);
  }
  catch (TwainException exception1)
  { System.err.println("    getYNativeResolution()->Failed");
  }

  try
  { double value[]=source.getSupportedYNativeResolution();
    System.err.print("    getSupportedYNativeResolution()->{");
    if (value.length>0)
      System.err.print(value[0]);
    for (int i=1; i<value.length; i++)
      System.err.print(", "+value[i]);
    System.err.println("}");
  }
  catch (TwainException exception1)
  { System.err.println("    getSupportedYNativeResolution()->Failed");
  }
  try
  { double value=source.getDefaultYNativeResolution();
    System.err.println("    getDefaultYNativeResolution()->"+value);
  }
  catch (TwainException exception1)
  { System.err.println("    getDefaultYNativeResolution()->Failed");
  }
  System.err.println();
  try
  { double value=source.getYResolution();
    System.err.println("    getYResolution()->"+value);
  try
  { source.setYResolution(value);
    System.err.println("    setYResolution("+value+")->OK");
  }
  catch (TwainException exception2)
  { System.err.println("    setYResolution("+value+")->Failed");
  }
  }
  catch (TwainException exception1)
  { System.err.println("    getYResolution()->Failed");
  }

  try
  { double value[]=source.getSupportedYResolution();
    System.err.print("    getSupportedYResolution()->{");
    if (value.length>0)
      System.err.print(value[0]);
    for (int i=1; i<value.length; i++)
      System.err.print(", "+value[i]);
    System.err.println("}");
    for (int i=0; i<value.length; i++)
      try
      { source.setYResolution(value[i]);
        System.err.println("    setYResolution("+value[i]+")->OK");
      }
      catch (TwainException exception2)
      { System.err.println("    setYResolution("+value[i]+")->Failed");
      }
  }
  catch (TwainException exception1)
  { System.err.println("    getSupportedYResolution()->Failed");
  }
  try
  { double value=source.getDefaultYResolution();
    System.err.println("    getDefaultYResolution()->"+value);
  try
  { source.setYResolution(value);
    System.err.println("    setYResolution("+value+")->OK");
  }
  catch (TwainException exception2)
  { System.err.println("    setYResolution("+value+")->Failed");
  }
  }
  catch (TwainException exception1)
  { System.err.println("    getDefaultYResolution()->Failed");
  }
  System.err.println();
  try
  { double value=source.getYScaling();
    System.err.println("    getYScaling()->"+value);
  try
  { source.setYScaling(value);
    System.err.println("    setYScaling("+value+")->OK");
  }
  catch (TwainException exception2)
  { System.err.println("    setYScaling("+value+")->Failed");
  }
  }
  catch (TwainException exception1)
  { System.err.println("    getYScaling()->Failed");
  }

  try
  { double value[]=source.getSupportedYScaling();
    System.err.print("    getSupportedYScaling()->{");
    if (value.length>0)
      System.err.print(value[0]);
    for (int i=1; i<value.length; i++)
      System.err.print(", "+value[i]);
    System.err.println("}");
    for (int i=0; i<value.length; i++)
      try
      { source.setYScaling(value[i]);
        System.err.println("    setYScaling("+value[i]+")->OK");
      }
      catch (TwainException exception2)
      { System.err.println("    setYScaling("+value[i]+")->Failed");
      }
  }
  catch (TwainException exception1)
  { System.err.println("    getSupportedYScaling()->Failed");
  }
  try
  { double value=source.getDefaultYScaling();
    System.err.println("    getDefaultYScaling()->"+value);
  try
  { source.setYScaling(value);
    System.err.println("    setYScaling("+value+")->OK");
  }
  catch (TwainException exception2)
  { System.err.println("    setYScaling("+value+")->Failed");
  }
  }
  catch (TwainException exception1)
  { System.err.println("    getDefaultYScaling()->Failed");
  }
  System.err.println();
  try
  { int value=source.getZoomFactor();
    System.err.println("    getZoomFactor()->"+value);
  try
  { source.setZoomFactor(value);
    System.err.println("    setZoomFactor("+value+")->OK");
  }
  catch (TwainException exception2)
  { System.err.println("    setZoomFactor("+value+")->Failed");
  }
  }
  catch (TwainException exception1)
  { System.err.println("    getZoomFactor()->Failed");
  }

  try
  { int value[]=source.getSupportedZoomFactor();
    System.err.print("    getSupportedZoomFactor()->{");
    if (value.length>0)
      System.err.print(value[0]);
    for (int i=1; i<value.length; i++)
      System.err.print(", "+value[i]);
    System.err.println("}");
    for (int i=0; i<value.length; i++)
      try
      { source.setZoomFactor(value[i]);
        System.err.println("    setZoomFactor("+value[i]+")->OK");
      }
      catch (TwainException exception2)
      { System.err.println("    setZoomFactor("+value[i]+")->Failed");
      }
  }
  catch (TwainException exception1)
  { System.err.println("    getSupportedZoomFactor()->Failed");
  }
  try
  { int value=source.getDefaultZoomFactor();
    System.err.println("    getDefaultZoomFactor()->"+value);
  try
  { source.setZoomFactor(value);
    System.err.println("    setZoomFactor("+value+")->OK");
  }
  catch (TwainException exception2)
  { System.err.println("    setZoomFactor("+value+")->Failed");
  }
  }
  catch (TwainException exception1)
  { System.err.println("    getDefaultZoomFactor()->Failed");
  }
  System.err.println();

      image=Toolkit.getDefaultToolkit().createImage(source);
      MediaTracker tracker=new MediaTracker(this);
      tracker.addImage(image, 0);
      System.err.println("Start loading image from '"+source+"' ...");
      try
      { tracker.waitForAll();
      }
      catch (InterruptedException e)
      { System.err.println("Image loading was interrupted!");
        e.printStackTrace();
      }
      tracker.removeImage(image);
      System.err.println();
      System.err.println("OK");
      System.err.println("    getActualXResolution()->"+source.getActualXResolution());
      System.err.println("    getActualYResolution()->"+source.getActualYResolution());
      System.err.println("    getActualImageWidthn()->"+source.getActualImageWidth());
      System.err.println("    getActualImageLength()->"+source.getActualImageLength());
      System.err.println("    getActualBitsPerPixel()->"+source.getActualBitsPerPixel());
      System.err.println("    getActualPixelType()->"+source.getActualPixelType());
      setSize(image.getWidth(this), image.getHeight(this));
      setVisible(true);
      TwainManager.close();
    }
    catch (TwainException e)
    { e.printStackTrace();
    }
  }
  
  public static void main(String[] args) throws FileNotFoundException
  { File output=new File("twainAutomatedTest.txt");
    System.out.println("Writing to "+output.getAbsolutePath());
    System.setErr(new PrintStream(new FileOutputStream(output)));
    new TwainAutomatedTest();
  }
}
