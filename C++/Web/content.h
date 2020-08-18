#ifndef CONTENT_H
#define CONTENT_H

String Website, data, XML, Javascript, contactState, color;
String color1 = "#63f05b", color2 = "#d3deee";

void XMLcontent() {
    XML = "<?xml version ='1.0'?>";
    XML += "<Donnees>";
    XML += "<data>";
    XML += data;
    XML += "</data>";

    XML += "<cont>";
    XML += contactState;
    XML += "</cont>";

    XML += "<color>";
    XML += color;
    XML += "</color>";

    XML += "<bt>";
    XML += String(borderTemp);
    XML += "</bt>";
    XML += "</Donnees>";

    server.send(200, "text/xml", XML);
}

void javascriptContent() {
    Javascript = "<SCRIPT>\n";
    Javascript += "var xmlHttp=createXmlHttpObject();\n";
    Javascript += "function createXmlHttpObject(){\n";
    Javascript += "if(window.XMLHttpRequest){\n";
    Javascript += "xmlHttp=new XMLHttpRequest();\n";
    Javascript += "}else{\n";
    Javascript += "xmlHttp=new ActiveXObject('Microsoft.XMLHTTP');\n";
    Javascript += "}\n";
    Javascript += "return xmlHttp;\n";
    Javascript += "}\n";
    Javascript += "\n";
    Javascript += "function process(){\n";
    Javascript += "xmlHttp.open('PUT','xml',true);\n";
    Javascript += "xmlHttp.onreadystatechange=response;\n";
    Javascript += "xmlHttp.send(null);\n";
    Javascript += "setTimeout('process()',1000);\n";
    Javascript += "}\n";
    Javascript += "function response(){\n";
    Javascript += "xmlResponse=xmlHttp.responseXML;\n";

    Javascript += "xmldoc = xmlResponse.getElementsByTagName('data');\n";
    Javascript += "message = xmldoc[0].firstChild.nodeValue;\n";
    Javascript += "document.getElementById('temperature').innerHTML = message;\n";

    Javascript += "xmldoc = xmlResponse.getElementsByTagName('cont');\n";
    Javascript += "message = xmldoc[0].firstChild.nodeValue;\n";
    Javascript += "document.getElementById('contact').innerHTML = message;\n";

    Javascript += "xmldoc = xmlResponse.getElementsByTagName('color');\n";
    Javascript += "message = xmldoc[0].firstChild.nodeValue;\n";
    Javascript += "document.getElementById('contact').style.color = message;\n";

    Javascript += "xmldoc = xmlResponse.getElementsByTagName('bt');\n";
    Javascript += "message = xmldoc[0].firstChild.nodeValue;\n";
    Javascript += "document.getElementById('value').innerHTML = message;\n";

    Javascript += "}\n";

    Javascript += "</SCRIPT>\n";
}

String WebsiteContent() {
    javascriptContent();
    Website = "<html> <head> <link rel=\"shortcut icon\" href=\"/favicon.png\" type=\"image/png\"> <title>Dashboard</title> <style> .btButton { text-align: center; background: #7e94bf; color: white; width: 30px; height: 20px; border: none; font-size: 19px; font-family: 'Roboto'; border-radius: 5px; margin-right: 40px; opacity: 0.6; transition: .3s; } .btButton:hover {opacity: 1;} #contact { font-family: 'Roboto'; font-size: 30px; font-weight: bold; } body { background-color: #f0fbff; } #form1 { margin-top: 115px; font-family: 'Roboto'; font-weight: bold; font-size: 30px; color: #21e6c1; } #form2 {font-family: 'Roboto'; font-weight: bold; font-size: 30px; color: #dee1ec; } .container { margin-top: 100px; margin-left: 350px; display: grid; grid-template-columns: 400px 400px 400px 400px; grid-gap: 5px; padding: 5px; } .container > div { transition: 0.5s; box-shadow: 0px 15px 20px rgba(163, 168, 177, 0.2); font-family: \"Office Code Pro\"; font-weight: 500; color: #5d697a; border-radius: 30px; background-color: #fafcfe; height: 350px; width: 80%; text-align: center; padding: 20px 0; font-size: 20px; margin-bottom: 50px; } .container > div:hover { background-color: #fbfbfb; transform: translateY(-3px); } h { color: #a3a8b1; } #container { width: 520px; margin-left: 710px; margin-top: 15px; } button { margin-bottom: 40px; box-shadow: 0px 15px 20px rgba(47, 128, 237, 0.4); background: linear-gradient(#36D1DC, #5B86E5); color: white; width: 200px; height: 70px; border: none; font-size: 19px; font-family: 'Roboto'; border-radius: 10px; margin-right: 40px; margin-top: 10px; opacity: 0.8; transition: .3s; } button:hover { opacity: 1; border-radius: 15px; cursor: pointer; } h2 { font-family: \"Gilroy\"; } #instruct { display: grid; grid-template-columns: 200px 40px 30px 0; grid-gap: 5px; padding:5px; font-size: 16px; font-family: 'Office Code Pro'; margin-left: 755px; color: #5c5c63; } input[type=number] { text-align: center; background-color: #1111; color: #5c5c63; width: 40px; border-radius: 4px; } #contact { color: #e9767c; } </style> </head>";
    Website += "<body onload=\"process()\">";
    Website += "<h2 style=\"opacity: 0.6; margin-left: 610px; margin-top: 30px; font-size: 40px; color: #c8d9eb\">C O O L P A D <span style=\"color: #2794eb\">&#8226</span><span style=\"color: #75baf2\"> &#8226 </span><span style=\"color: #a7d3f7\">&#8226</span> D A S H B O A R D</h2>";
    Website += "<div class=\"container\"> <div><h>Laptop Contact</h><p style=\"margin-top: 150px; font-family: 'Roboto'; font-size: 30px; font-weight: bold;\">";
    Website += "<div id=\"contact\">";
    Website += contactState;
    Website += "</div></p></div>";
    Website += "<div><h>Temperature <span style=\"font-family: 'Arial'\">&#8451</span></h><p style=\"margin-top: 115px; font-size: 100px; margin-left: 10px\">";
    Website += "<p id=\"temperature\" style=\"font-size: 80px; font-family: 'Roboto';\">" + data + "</p>";
    Website += "</p></div>";
    Website += "<div><h>Mode</h><div><form action=\"\" id=\"form1\">";
    Website += "&#9658 AUTO MODE";
    Website += "</form>";
    Website += "<form action=\"\" id=\"form2\">";
    Website += "ALWAYS ON";
    Website += "</form></div></div></div>";
    Website += "<h style=\"font-family: 'Office Code Pro'; font-size: 20px; margin-left: 860px\">Change Mode</h>";
    Website += "<div id=\"container\"> <button onclick=\"onClick(this)\" id=\"button1\">Auto Mode</button></a> <button onclick=\"onClick(this)\" id=\"button2\">Always On</button></a> </div>";
    Website += "<div id=\"instruct\"><div><h>Border temperature:</h></div><div class=\"btButton\" id=\"b1\" onclick='change(this)'>+</div><span id=\"value\" style=\"color: #7e94bf\">";
    Website += String(borderTemp);
    Website += "</span><div class=\"btButton\" id=\"b2\" onclick='change(this)'>-</div></div></body>";
    Website += "<script> function onClick(x) { ";
    Website += "if(x.id == \"button1\") { ";
    Website += "document.getElementById(\"form1\").innerHTML = \"&#9658 AUTO MODE\";";
    Website += "document.getElementById(\"form2\").innerHTML = \"ALWAYS ON\";";
    Website += "document.getElementById(\"form2\").style.color = \"#dee1ec\";";
    Website += "document.getElementById(\"form1\").style.color = \"#21e6c1\";";
    Website += "document.getElementById(\"form2\").style.color = \"#dee1ec\";";
    Website += "request = new XMLHttpRequest(); ";
    Website += "request.open(\"GET\", \"/auto\", true); ";
    Website += "request.send(); } ";
    Website += "if(x.id == \"button2\") { ";
    Website += "document.getElementById(\"form1\").innerHTML = \"AUTO MODE\";";
    Website += "document.getElementById(\"form2\").innerHTML = \"&#9658 ALWAYS ON\";";
    Website += "document.getElementById(\"form2\").style.color = \"#21e6c1\";";

    Website += "document.getElementById(\"form1\").style.color = \"#dee1ec\";";
    Website += "request = new XMLHttpRequest(); ";
    Website += "request.open(\"GET\", \"/always\", true); ";
    Website += "request.send(); } }";
    Website += "function change(x){ if(x.id == \"b1\"){ request = new XMLHttpRequest(); request.open(\"GET\", \"/plus\", true); request.send(); } else { request = new XMLHttpRequest(); request.open(\"GET\", \"/minus\", true); request.send(); }}";
    Website += "</script> </html>";

    Website += Javascript;

    server.send(200, "text/html", Website);
    return Website;
}

#endif  //CONTENT_H
