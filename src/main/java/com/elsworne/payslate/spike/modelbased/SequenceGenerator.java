package com.elsworne.payslate.spike.modelbased;

import java.util.ArrayList;
import java.util.List;

/** Exhaustively generates histories through a bounded depth; no random seed or library is required. */
public final class SequenceGenerator {
  enum State { DRAFT, CALCULATED, APPROVED, PAID, REVERSED }
  enum Event { CALCULATE, APPROVE, PAY, REVERSE }

  static State step(State state, Event event) {
    return switch (state) {
      case DRAFT -> event == Event.CALCULATE ? State.CALCULATED : state;
      case CALCULATED -> event == Event.APPROVE ? State.APPROVED : state;
      case APPROVED -> event == Event.PAY ? State.PAID : state;
      case PAID -> event == Event.REVERSE ? State.REVERSED : state;
      case REVERSED -> state;
    };
  }

  public static void main(String[] args) {
    int depth = args.length == 0 ? 5 : Integer.parseInt(args[0]);
    if (depth < 1 || depth > 10) throw new IllegalArgumentException("depth 1..10");
    long[] counts = new long[2];
    enumerate(new ArrayList<>(), depth, counts);
    System.out.println("sequences=" + counts[0] + " doubleApprovalDefect=" + counts[1]);
  }

  private static void enumerate(List<Event> path, int depth, long[] counts) {
    if (path.size() == depth) {
      State state = State.DRAFT;
      boolean found = false;
      for (Event event : path) {
        if (state == State.APPROVED && event == Event.APPROVE) found = true;
        state = step(state, event);
      }
      counts[0]++;
      if (found) counts[1]++;
      return;
    }
    for (Event event : Event.values()) {
      path.add(event);
      enumerate(path, depth, counts);
      path.remove(path.size() - 1);
    }
  }
}
