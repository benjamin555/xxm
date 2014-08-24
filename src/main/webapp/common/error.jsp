<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isErrorPage="true" %>
<%@ page import="java.sql.*,
                java.util.Map,
                java.util.Iterator,
                java.io.PrintWriter,
                java.io.ByteArrayOutputStream
                " %>
<%@ page import="org.apache.log4j.Logger" %>
<%
	Object _exceptionObj = request.getAttribute("exception");
	if(null == _exceptionObj){
		_exceptionObj = request.getAttribute("exceptionStack");
	}
  Throwable ob = (Throwable)_exceptionObj;
  Logger _log = Logger.getLogger(ob.getClass());
  _log.error(ob.getMessage(), ob);
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html>
<head>
<script language="javascript">
function showMessage(){
	var message = document.getElementById("MESSAGE");
	if(message.style.display == "block")
		message.style.display = "none";
	else
		message.style.display = "block";
}

</script>
<title></title>
</head>
<body topmargin="0" leftmargin="0" rightmargin="0">
<form>
<table border="0" cellpadding="0" cellspacing="0" style="border-collapse: collapse" bordercolor="#111111" width="100%" id="AutoNumber1">
  <tr>
    <td width="100%" height="12">    
    </td>
  </tr>
  <tr>
    <td width="100%" height="12" bgcolor="#E8E8E8"><font size="1" color="#E8E8E8">1</font></td>
  </tr>
  <tr>
    <td width="100%" height="12">
    <table border="0" cellpadding="0" cellspacing="0" style="border-collapse: collapse" bordercolor="#111111" width="100%" id="AutoNumber3">
      <tr>
        <td width="5%">　</td>
        <td width="33%">　</td>
        <td width="5%">　</td>
      </tr>
      <tr>
        <td width="5%">　</td>
        <td width="33%">
        <table border="1" cellpadding="0" cellspacing="0" style="border-collapse: collapse" bordercolor="#111111" width="100%" id="AutoNumber4">
          <tr>
            <td width="100%" bgcolor="#B3D7F5">
            <table border="0" cellpadding="0" cellspacing="0" style="border-collapse: collapse" bordercolor="#111111" width="100%" id="AutoNumber5">
              <tr>
                <td width="100%">　</td>
              </tr>
              <tr>
              <td width="100%">
              <div id="sqld" style="display:block">
              </div>
              </tr>                 
              <tr>
              <td width="100%">
              <div id="dd" style="display:block" align="center">
           		  操作发生错误, 请联系管理员.   
              </div>
              </tr>
           
              <tr>
                <td width="100%">
                <p align="right"><a href="javascript:showMessage();">详细内容</a></td>
              </tr>
              <tr>
              <td width="100%">
              <div id="MESSAGE" style="display:none;overflow:auto;">
	               <%  
					ByteArrayOutputStream buf=new ByteArrayOutputStream();				
					ob.printStackTrace(new PrintWriter(buf, true));	
					out.print("<br>"+buf.toString());
					%>             
              </div>
              </tr>
            </table>
            </td>
          </tr>
          <tr>
            <td width="100%" bgcolor="#EBEBEB">　</td>
          </tr>
        </table>
        </td>
        <td width="5%">　</td>
      </tr>
      <tr>
        <td width="5%">　</td>
        <td width="33%">　</td>
        <td width="5%">　</td>
      </tr>
    </table>
    </td>
  </tr>
  <tr>
    <td width="100%">
</td>
  </tr>
  <tr>
    <td width="100%">
                <p align="center">                 
                  <button name="B1" onclick="javascript:window.history.go(-1)">返回</button>
                </td>
  </tr>
</table>
<script language="javascript">
if(window.history.length==0){
	document.all("B1").innerText="关闭";
	document.all("B1").onclick=winclose;
}else{	
}
function winclose(){
	parent.close();
}

</script>
</form>
</body>
</html>


