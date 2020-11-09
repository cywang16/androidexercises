package com.helloworld;

public class MyClass {
    public static void main(String[] args) {
        System.out.println("Hello World");
    }
}

/*
C:\GIT\androidexercises\cotlinscratches\HelloWorld\src\main\java
18:34:28.24> runcmd java com.helloworld.MyClass

:javac
REM set jdkbin="C:\Program Files\Java\jdk1.8.0_20\bin"
set jdkbin="C:\Program Files\Android\Android Studio\jre\bin"
call %jdkbin%\javac %*
goto :eof

:java
REM set jdkbin="C:\Program Files\Java\jdk1.8.0_20\bin"
set jdkbin="C:\Program Files\Android\Android Studio\jre\bin"
call %jdkbin%\java %*
goto :eof

:javap
REM set jdkbin="C:\Program Files\Java\jdk1.8.0_20\bin"
set jdkbin="C:\Program Files\Android\Android Studio\jre\bin"
call %jdkbin%\javap %*
goto :eof
 */