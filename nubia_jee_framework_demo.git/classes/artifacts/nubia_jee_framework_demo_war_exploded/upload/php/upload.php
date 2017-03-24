<?php
     ini_set('display_errors', 'on');
	ini_set('default_charset','utf-8');
	ini_set('error_reporting','E_ALL & ~E_NOTICE');
	//ini_set('error_reporting',E_ALL);
	
    $file_path = './upload/'.urlencode($_POST['filename']);
    //.iconv('utf-8','gbk', $_POST['filename']);
    
    // Parse the Content-Range header, which has the following form:
    // Content-Range: bytes 0-524287/2000000
    $content_range = $_SERVER['HTTP_CONTENT_RANGE'];
    $contentrange = preg_split('/[^0-9]+/', $content_range);
    //$contentrange[1]的值为0,$contentrange[2]的值为524287，$contentrange[3]的值为2000000
    
    $tmp = file_get_contents($_FILES['files']['tmp_name']);
                       
    file_put_contents($file_path,$tmp,FILE_APPEND);
    
    if($contentrange[2] == $contentrange[3]-1) {
        $name_array = explode('.',$file_path);
        $end = end($name_array);
        $ext=strtolower($end);
        $fileName=date('YmdHis').'.'.$ext;
        rename($file_path, './upload/'.$fileName);
    }
?>