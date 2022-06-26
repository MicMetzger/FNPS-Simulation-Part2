package test.java.com.Logging;

import static main.java.com.Logging.Logger.*;

import java.io.*;
import main.java.com.Logging.*;
import org.junit.jupiter.api.*;



class LoggerTest {
  private final  Logger singleInstanceLogger;
  private static File   TESTLOGPATH = new File("");

  LoggerTest() {
    this.singleInstanceLogger = Logger.getInstance();
  }


  /**
   * Test the getlogger() function call to see if it returns the same instance.
   */
  @Test
  void testGetLoggerCallReturnsSameInstance() {
    Logger logger = Logger.getInstance();
    Assertions.assertEquals(singleInstanceLogger, logger);
  }


  /**
   * Test the getlogger() function call to see if it returns the same instance consistantly over multiple references.
   */
  @Test
  void testMultipleCopiesOfLoggerAreTheSameInstance() {
    var logger1 = Logger.getInstance();
    var logger2 = Logger.getInstance();
    var logger3 = Logger.getInstance();
    Assertions.assertSame(logger1, logger2);
    Assertions.assertSame(logger2, logger3);
    Assertions.assertSame(logger3, logger1);
  }


  @Test
  void testLoggerSAVEFileNaming() throws IOException {
    // var logger = Logger.getInstance();
    TESTLOGPATH = new File(LOGFOLD + "TEST_DIR" + "/");
    filePathALT(String.valueOf(TESTLOGPATH));
    SAVE();
    Assertions.assertTrue(TESTLOGPATH.exists());
    Assertions.assertSame(LOGINPUTPATH, TESTLOGPATH.getPath());
    tearDown();
  }

  @AfterAll
  static void tearDown() {
    if (TESTLOGPATH.exists()) {
      TESTLOGPATH.delete();
    }
    if (new File(LOGINPUTPATH).exists()) {
      new File(LOGINPUTPATH).delete();
    }
  }
}
