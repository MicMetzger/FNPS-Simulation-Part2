package test.java.com.Logging;

import static main.java.com.Logging.Logger.*;

import java.io.*;
import main.java.com.Logging.*;
import main.java.com.store.*;
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
   * Test the getlogger() function call to see if it returns the same 
   * instance consistantly over multiple references.
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


  /**
   * Test the Logger SAVE() function call to see if it creates a "test"
   * folder in the proper log output directory, under the proper directory name.
   */
  @Test
  void testLoggerSAVEFileNaming() throws IOException {
    // var logger = Logger.getInstance();
    TESTLOGPATH = new File(LOGFOLD + "TEST_DIR" + "/");
    filePathALT(String.valueOf(TESTLOGPATH));
    SAVE();
    Assertions.assertTrue(TESTLOGPATH.exists());
    Assertions.assertSame(LOGINPUTPATH, TESTLOGPATH.getPath());
    // tearDown();
  }


  /**
   * Test the Logger SAVE() function call to see if it creates a file under
   * the proper directory name, and writes the simulation starting header
   * to the proper file.
   */
  @Test
  void testLoggerSAVEFileContent() throws IOException {
    Store TESTSTORE = new Store();
    TESTSTORE.goNewDay();
    TESTLOGPATH = new File(LOGFOLD + "TEST_DIR" + "/");
    filePathALT(String.valueOf(TESTLOGPATH));
    SAVE();
    Assertions.assertTrue(TESTLOGPATH.exists());
    Assertions.assertSame(LOGINPUTPATH, TESTLOGPATH.getPath());
    // tearDown();
  }
  
  
  @AfterAll
  static void tearDown() {
    if (TESTLOGPATH.exists()) {
      var e = TESTLOGPATH.delete();
    }
    if (new File(LOGINPUTPATH).exists()) {
      var e = new File(LOGINPUTPATH).delete();
    }
  }
}
