if [%1]==[] goto usage
set service=%1
call mvn clean -f fintech-microservices/%service%/pom.xml
call mvn package -f fintech-microservices/%service%/pom.xml -P test
goto :eof
:usage
@echo Укажите название сервиса
exit /B 1