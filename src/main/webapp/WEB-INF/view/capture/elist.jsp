<%@ page import="com.dream.brick.equipment.action.CaptureAction" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 2018/1/11
  Time: 18:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>Title</title>
    <%--IE 9--%>
    <%--<script type="text/javascript" src="https://html5shiv.googlecode.com/svn/trunk/html5.js"></script>--%>
    <script type="text/javascript" src="${basePath}/js/excanvas.compiled.js"></script>

    <script type="text/javascript" src="${basePath}/js/jquery-1.4.4.min.js"></script>
    <script type="text/javascript" src="${basePath}/css/jquery.lightbox.min.css"></script>
    <script type="text/javascript" src="${basePath}/js/jquery.canvasSlideshow.js"></script>

    <script type="text/javascript">

    </script>
    <style>
        td {
            width: 200px;
            height: 200px;
        }

        #showtu {
            left: 50px;
            margin-left: 50px;
        }
    </style>

</head>
<body>
<form>


    <img src="http://localhost:8080/uploads/icture/+'.jpg'">
    <img src="${basePath}/uploads/ictures/+'.getTimestamp()'.jpg">
    <br/>
    起始时间:<input type="datetime-local">
    结束时间:<input type="datetime-local">
    选择设备:<select id="jingtou" name="dis.name" style="width: 220px;">

    <option value="">---请选择---</option>
</select>
    <input type="submit" value="查看">

    <input type="image"> <br/>
</form>

<div id="showtu">
    <table border="1">
        <script type="text/javascript">
            var b = 2
            var c = 5;
            for (var j = 0; j < b; j++) {
                document.write('<tr>');
                for (var i = 0; i < c; i++) {
                    url:
                        <%--var  url = "<%CaptureAction.getFiles("http://localhost:8080/uploads/ictures/");%>";--%>
                        var url = "${fileList}"
                    document.write('<td><img src="${basePath}/uploads/ictures/' + url + '" /></td>');
                }
                document.write('</tr>');
            }
        </script>
    </table>
</div>

<%--<div>--%>
<%--<p>To see how we build this come to <a href="http://www.webair.it/blog" target="_new">webair blog</a><br>--%>
<%--<br>--%>

<%--</p>--%>
<%--<div id="canvas_container" style="position:relative; margin:auto; width:600px;height:450px;">--%>
<%--<canvas id="slideshow" width="800" height="250">--%>
<%--<img src="${basePath}/uploads/images/image1.jpg" alt="description image 1" style="display:none;"/>--%>
<%--<img src="${basePath}/uploads/images/image2.jpg" alt="description image 2" style="display:none;"/>--%>
<%--<img src="${basePath}/uploads/images/image3.jpg" alt="description image 3" style="display:none;"/>--%>
<%--<img src="${basePath}/uploads/images/image4.jpg" alt="description image 4" style="display:none;"/>--%>
<%--<img src="${basePath}/uploads/images/image5.jpg" alt="description image 5" style="display:none;"/>--%>
<%--<img src="${basePath}/uploads/images/image6.jpg" alt="description image 6" style="display:none;"/>--%>
<%--<img src="${basePath}/uploads/images/image7.jpg" alt="description image 7" style="display:none;"/>--%>
<%--<img src="${basePath}/uploads/images/image8.jpg" alt="description image 8" style="display:none;"/>--%>
<%--</canvas>--%>
<%--<script type="text/javascript">--%>
<%--$('#slideshow').canvasSlideshow({num_elem: 3});--%>
<%--</script>--%>
<%--</div>--%>

<%--<div id="lightbox-overlay" style="width: 1523px; height: 477px; display: none;">--%>
<%--<div id="lightbox-overlay-text"><p><span id="lightbox-overlay-text-about"><a href="#"--%>
<%--title="Licenced under the GNU Affero General Public License.">jQuery Lightbox Plugin (balupton edition)</a></span>--%>
<%--</p>--%>
<%--<p>&nbsp;</p>--%>
<%--<p><span id="lightbox-overlay-text-close">Click to close</span><br>&nbsp;<span--%>
<%--id="lightbox-overlay-text-interact" style="display: none;">Hover to interact</span></p></div>--%>
<%--</div>--%>
<%--<div id="lightbox" style="left: 0px; top: 141.6px; display: none;">--%>
<%--<div id="lightbox-imageBox">--%>
<%--<div id="lightbox-imageContainer"><img id="lightbox-image">--%>
<%--<div id="lightbox-nav"><a href="#" id="lightbox-nav-btnPrev"></a><a href="#"--%>
<%--id="lightbox-nav-btnNext"></a></div>--%>
<%--<div id="lightbox-loading"><a href="#" id="lightbox-loading-link"><img--%>
<%--src="${basePath}/uploads/images/loading.gif"></a>--%>
<%--</div>--%>
<%--</div>--%>
<%--</div>--%>
<%--<div id="lightbox-infoBox">--%>
<%--<div id="lightbox-infoContainer">--%>
<%--<div id="lightbox-infoHeader"><span id="lightbox-caption"><a href="#" title="Download."--%>
<%--id="lightbox-caption-title"></a><span--%>
<%--id="lightbox-caption-seperator"></span><span id="lightbox-caption-description"></span></span>--%>
<%--</div>--%>
<%--<div id="lightbox-infoFooter"><span id="lightbox-currentNumber"></span><span id="lightbox-close"><a--%>
<%--href="#" id="lightbox-close-button"--%>
<%--title="You can also click anywhere outside the image to close.">Close X</a></span></div>--%>
<%--<div id="lightbox-infoContainer-clear"></div>--%>
<%--</div>--%>
<%--</div>--%>
<%--</div>--%>
<%--</div>--%>

<script>

    //        for (var i=0;i<cars.length;i++)
    //        {
    //            document.write(cars[i] + '<img src="http://localhost:8080/uploads/icture/001292.jpg">');
    //        }

</script>

<script src="${basePath}/js/js-preview-728x90.js"></script>
<br/><br/><br/>
<script src="${basePath}/js/tj.js"/>
</body>

</html>
<%--<script>--%>
<%--$(function () {--%>
<%--var data = '${qgdisList}';--%>
<%--data = JSON.parse(data);--%>
<%--$('#jingtou').empty();--%>
<%--for (var i = 0; i < data.length; i++) {--%>
<%--$('#jingtou').append("<option value='" + data[i].id + "'>" + data[i].name + "</option>");--%>
<%--}--%>
<%--})--%>
<%--</script>--%>


