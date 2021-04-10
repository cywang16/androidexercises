package com.helloworld;

public class MyClass {
    public static void main(String[] args) {
        System.out.println("Hello World");
        for (String s: args) {
            System.out.println(s);
        }
        String[] sDim = args[0].split("_");
        int dimR = Integer.parseInt(sDim[0]), dimC = Integer.parseInt(sDim[1]);
        System.out.println(String.format("%d * %d", dimR, dimC));
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