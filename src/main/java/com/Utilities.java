package main.java.com;

import java.util.concurrent.TimeUnit;

public class Utilities {
  public static void gapTime() {
    try {
      TimeUnit.SECONDS.sleep(1);
    } catch (InterruptedException err) {
      err.printStackTrace();
    }
  }
  
  public static void gapTime(int seconds) {
    try {
      TimeUnit.SECONDS.sleep(seconds);
    } catch (InterruptedException err) {
      err.printStackTrace();
    }
  }
}
