<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html ng-app="app" xmlns:ng="http://angularjs.org" id="ng-app">

<head>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    <meta charset="utf-8">
    <title>share success!</title>
</head>
<script language="JavaScript" type="text/JavaScript">
    var isShareOrnot = ${isShareOrnot };
    var guanwangUrl = ${guanwangUrl};
if(isShareOrnot){
        window.location.href=guanwangUrl;
    }else{
    alert("今天点过了,明天再来点!");
    window.location.href=guanwangUrl;
    }
</script>
<body>
</body>
</html>
