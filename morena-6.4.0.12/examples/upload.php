<?PHP
  $dh  = opendir(".");
	while (false !== ($filename = readdir($dh)))
	{ $dc[] = $filename;
	}
  foreach($dc as $f)
  { if (substr($f, 0, 4)=="img_")
      if (filectime($f)<time()-5*60)
        unlink($f);
  }
  $fn="img_".time().".jpg";
	$fp=fopen($fn,"w");
	fwrite($fp, $HTTP_RAW_POST_DATA);
	fclose($fp);
	header("file-name: ".$fn);
?>
