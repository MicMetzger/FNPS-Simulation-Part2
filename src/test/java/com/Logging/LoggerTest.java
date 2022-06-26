package test.java.com.Logging;

import main.java.com.Logging.*;
import org.junit.jupiter.api.*;



class LoggerTest {
  private final Logger singleInstanceLogger;

  LoggerTest() {
    this.singleInstanceLogger = Logger.getInstance();
  }


  /**
   * Test the getlogger() function call to see if it returns
   * the same instance.
   */
  @Test
  void testGetLoggerCallReturnsSameInstance() {
    Logger logger = Logger.getInstance();
    Assertions.assertEquals(singleInstanceLogger, logger);
  }


  /**
   * Test the getlogger() function call to see if it returns
   * the same instance consistantly over multiple references.
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
  
}
