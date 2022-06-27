package main.java.com.individuals.track;

public class Scheme {
  private static final String NAME_TEMPLATE = "DEFAULT";
  private static final String DAY_TEMPLATE  = "DEFAULT";
  private static final String ID_TEMPLATE   = "#__";
  private static final String TAG_TEMPLATE  = "@";
  // private final String CLASS_TEMPLATE = "class";
  private static       int    ID_TAG        = 0;

  private       StringBuilder BUILDER;
  private final String[]      FORMAT_SYMBOLS;
  private final int           id;
  private final String        name;

  private Scheme(String name, String... fArgs) {
    this.name           = name;
    this.id             = Scheme.ID_TAG++;
    this.BUILDER        = new StringBuilder();
    this.FORMAT_SYMBOLS = fArgs;
  }


  /**
   * New instance of the data scheme for data table formatting.
   *
   * @param name  the name
   * @param fArgs the fucntion arguments
   * @return New format instance
   */
  static Scheme getScheme(String name, String... fArgs) {
    return new Scheme(name, fArgs);
  }

  static Scheme getdefaultScheme() {
    return new Scheme(NAME_TEMPLATE, DAY_TEMPLATE, ID_TEMPLATE, TAG_TEMPLATE);
  }

  private void clear() {
    BUILDER.setLength(0);
  }

  @Override
  public String toString() {
    BUILDER = new StringBuilder().
        append(ID_TEMPLATE).
        append(TAG_TEMPLATE).
        append(NAME_TEMPLATE).
        append("\n");
    return BUILDER.toString();
  }

}
