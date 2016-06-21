@ECHO OFF
setlocal

REM Запуск JAVA итилиты IN-KEY-OUT
REM V 1.00 - Первая эксплуатационная версия 12:07 09.01.2015

REM==================================================
REM Установка переменных
SET RUNDIR=%~dp0
SET FILES_PATH=%RUNDIR%
CD "%RUNDIR%"|| ECHO %date% %time% - ERROR CD %RUNDIR%

REM==================================================
REM Запустим
ECHO %date% %time% - TRY RUN IN-KEY-OUT.jar

	REM java.exe -jar "%RUNDIR%IN-KEY-OUT.jar" "%FILES_PATH%IN.TXT" "%FILES_PATH%KEY.TXT" "%FILES_PATH%OUT.TXT"|| (
"C:\Program Files\Java\jdk1.7.0_21\bin\java.exe" -jar "%RUNDIR%IN-KEY-OUT.jar" "%FILES_PATH%IN.TXT" "%FILES_PATH%KEY.TXT" "%FILES_PATH%OUT.TXT"|| (
ECHO %date% %time% - ERROR RUN IN-KEY-OUT.jar
EXIT /B 1
)
ECHO %date% %time% - RUN IN-KEY-OUT.jar - OK
EXIT /B 0
