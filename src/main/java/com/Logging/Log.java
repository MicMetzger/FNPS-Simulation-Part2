package main.java.com.Logging;

import java.util.*;



public class Log {
  String        EVENT;
  StringBuilder BUILDER;
  List<String>  EVENTLOG;

  Log(String event) {
    EVENTLOG = new ArrayList<>();
    this.EVENT   = event;
    EVENTLOG.add(event);
  }
  
  public void write(String line) {
    EVENTLOG.add(line);
  }

  @Override
  public String toString() {
    BUILDER = new StringBuilder();
    for (String s : EVENTLOG) {
      BUILDER.append(s);
    }
    BUILDER.append("\n\n");
    return BUILDER.toString();
  }
}
