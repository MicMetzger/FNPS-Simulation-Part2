package main.java.com.Logging;

import java.util.*;



public class Logger {
  private transient Logger               instance;  // transient: not serialized (static default
  private           List<LoggerListener> listeners = new ArrayList<>();
  private static int ID_TAG = 0;

  private Logger() {
    listeners = new ArrayList<>();
    instance = this;
  }
  
  public static Logger getLogger(Class<?> clazz) {
    return new Logger();
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

