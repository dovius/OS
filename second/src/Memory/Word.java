package Memory;

public class Word {

  private String data;

  public Word() {
    data = "0000";
  }

  public Word(int s) {
    setIntValue(s);
  }

  public Word(String s) {
    setValue(s);
  }

  public int getIntValue() {
    try {
      return Integer.valueOf(data);
    } catch (NumberFormatException e) {
      return 0;
    }
  }

  public void setIntValue(int integer) {
    String stringInt = String.valueOf(integer);
    while (stringInt.length() < 4) {
      stringInt = "0" + stringInt;
    }
    setValue(stringInt);
  }

  public String getValue() {
    return data.toUpperCase();
  }

  public void setValue(String s) {
    if (s.length() <= 4) {
      while (s.length() < 4) {
        s = s + " ";
      }
      data = s;
    }
    else {
      data = s.substring(0, 4);
    }
  }

}