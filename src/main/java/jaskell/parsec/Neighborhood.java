package jaskell.parsec;

/**
 * Neighborhood description what the neighborhood content of a point
 * The center of neighborhood could be not the point of it, because
 * String need top and bottom in indexes range.
 *
 * @author mars
 * @version 1.0.0
 * @since 2020/06/02 11:58
 */
public class Neighborhood {
  private int bottom;
  private int top;
  private String content;

  public int getBottom() {
    return bottom;
  }

  public void setBottom(int bottom) {
    this.bottom = bottom;
  }

  public int getTop() {
    return top;
  }

  public void setTop(int top) {
    this.top = top;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }
}
