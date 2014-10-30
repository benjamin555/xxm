#!/bin/sh
echo  "deploy start" 

echo  "stop tomcat" 
service tomcat stop

echo  "clear webapp" 
rm /usr/local/tomcat/webapps/xxm.war
rm  -rf /usr/local/tomcat/webapps/xxm

cd /usr/local/git_work/xxm

echo  "update from git" 

git pull
echo  "install war" 

mvn clean install -Dmaven.test.skip=true
echo   "copy to tomcat" 
cp /usr/local/git_work/xxm/target/xxm-1.0.war /usr/local/tomcat/webapps/xxm.war

echo   "start tomcat" 

service tomcat start

echo "clear apache's static resource"
rm  -rf /var/www/html/xxm
echo "copy static resources to apache"
cp  -rf /usr/local/git_work/xxm/target/xxm-1.0  /var/www/html/xxm
rm  -rf /var/www/html/xxm/WEB-INF/classes
rm  -rf /var/www/html/xxm/WEB-INF/lib


echo   "deploy end" 