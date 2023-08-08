@echo off

rem Suppress Terminate batch job on CTRL+C
if exist "%~nx0.run" goto mainEntry
echo Y>"%~nx0.run"
if not exist "%~nx0.run" goto mainEntry
echo Y>"%~nx0.Y"
call "%~f0" %* <"%~nx0.Y"
rem Use provided errorlevel
set RETVAL=%ERRORLEVEL%
del /Q "%~nx0.Y" >NUL 2>&1
exit /B %RETVAL%
:mainEntry
del /Q "%~nx0.run" >NUL 2>&1

mvn jetty:run
