<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
    <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

  <title>Do It Notice</title>

</head>

<body class="bg-gradient-primary">

  <div>
  Do It !! Notice<br/>
  새로운 공지사항!
  
  <form action="/api/notice/input" method="post"> 
  제목 : <input type="text" name="title"/> 
  <br/> <br/> 
  내용 : <textarea rows="10" cols="50" name="content"></textarea> <br/> 
  <input type="submit" value="등록 /수정"/> </form> 
  </div>

</body>
</html>