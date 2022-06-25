package main.java.com.Logging;

import java.io.*;
import java.util.*;



public class Logger {
  private static       Logger    logger;  // transient: not serialized (static default
  private static final String    LOGFILE   = "Logger-n.txt";
  private static List<Log> LOGS      = new ArrayList<>();
  private static Log       CURRENTLOG;
  private static int       LOG_COUNT = 0;
  protected static     int       DAY_TAG   = 0;
  protected static     int       ID_TAG    = 0;


  private Logger(/* String logFile */) {
    // LOGFILE = logFile;
    LOGS   = new ArrayList<>();
    logger = this;
  }

  public static Logger getInstance(/* Class<?> clazz */ /* String File */) {
    synchronized (Logger.class) {
      if (logger == null) {
        return new Logger(/* File */);
      }
    }
    return logger;
  }

  public static void SAVE() throws IOException {
    try (FileWriter output = new FileWriter(LOGFILE, true /* StandardCharsets.UTF_8 */)) {
      LOGS.forEach(data -> {
        try {
          output.write(data.toString());
        } catch (IOException e) {
          throw new RuntimeException(e);
        }
      });
      // output.write(data);
    }
    catch (IOException ignored) {}
  }

  public static void addLog(String table) {
    CURRENTLOG = new Log(table);
    LOGS.add(CURRENTLOG);
  }

  public void removeListener(Log table) {
    LOGS.remove(table);
  }

  public static void LOG(String line) {
    CURRENTLOG.write(line + "\n");
  }
}

