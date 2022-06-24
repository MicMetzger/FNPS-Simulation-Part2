package main.java.com;

import java.util.*;



public class Logger {
  
  abstract static class ALogger {
    public ALogger() {

    }
    
  }
  
  private transient Logger               instance;  // transient: not serialized (static default
  private           List<LoggerListener> listeners = new ArrayList<>();

  private Logger() {
    listeners = new ArrayList<>();
    instance = this;
  }
  
  public static Logger getLogger(Class<?> clazz) {
    return new Logger();
  }

  public void info(String msg) {
    System.out.println(msg);
  }

  public void error(String msg) {
    System.out.println(msg);
  }

  public void debug(String msg) {
    System.out.println(msg);
  }

  public void warn(String msg) {
    System.out.println(msg);
  }

  public void trace(String msg) {
    System.out.println(msg);
  }

  public void addLoggerListener(LoggerListener listener) {
    listeners.add(listener);
  }

  public void removeListener(LoggerListener listener) {
    listeners.remove(listener);
  }


  class LoggerListener {
    LoggerListener() {
      
    }

  }

}

