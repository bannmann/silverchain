package silverchain.grammar;

public final class Range {

  private final Location begin;

  private final Location end;

  public Range(Location begin, Location end) {
    this.begin = begin;
    this.end = end;
  }

  public Location begin() {
    return begin;
  }

  public Location end() {
    return end;
  }
}
