package main.java.com.Logging;

import java.io.*;
import java.text.*;
import java.util.*;
import main.java.com.events.*;
import main.java.com.events.task.*;



public class Logger implements EventObserver {
  private static       Logger           logger;
  private static final String           LOGFOLD    = "LOGS/";
  private static final String           LOGFILE    = "Logger-";
  static               SimpleDateFormat DATEFORMAT = new SimpleDateFormat("yyyy-MM-dd");
  private static       List<Log>        LOGS       = new ArrayList<>();
  private static       Log              LOG;
  private static       int              LOG_COUNT  = 0;
  protected static     int              DAY_TAG    = 0;
  protected static     int              ID_TAG     = 0;


  private Logger(/* String logFile */) {
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

  /**
   * Save.
   *
   * @throws IOException the io exception
   */
  public static void SAVE() throws IOException {
    File file = new File("");
    File LOGPATH = new File(LOGFOLD + DATEFORMAT.format(new Date()) + "/");
    if (!LOGPATH.exists()) {
      LOGPATH.mkdir();
    }
    
    for (int i = 0; i < 101; i++) {
      if (i == 101) {
        System.out.println("Logger: Error: Unable to save logs.");
        return;
      }
      file = new File(LOGFOLD + DATEFORMAT.format(new Date()) + "/" + LOGFILE + i + ".txt");

      if (!file.exists()) {
        file.createNewFile();
        break;
      }
    }

    try (FileWriter output = new FileWriter(file, true /* StandardCharsets.UTF_8 */)) {
      LOGS.forEach(data -> {
        try {
          output.write(data.toString());
        } catch (IOException e) {
          throw new RuntimeException(e);
        }
      });
      // output.write(data);
    } catch (IOException ignored) {}
  }

  public static void addLog(String table) {
    LOG = new Log(table);
    LOGS.add(LOG);
  }

  public void removeListener(Log table) {
    LOGS.remove(table);
  }

  public static void LOG(String line) {
    LOG.write(line + "\n");
  }

  public static void LOG(EventLog event) {
    LOG.write(event + "\n");
  }

  @Override
  public void update(EventObservable watched, Object event) {
    if (event instanceof EmployeeTask) {
      State e = (State) event;
      // LOG("Event: " + e.getName() + " " + e.getStatus().getName());
    }
  }
}

