package jaskell.parsec.common;

import jaskell.parsec.ParsecException;

import java.io.EOFException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by Mars Liu on 2016-01-02.
 * TxtState 类型提供一个基本的 state 实现,它将文本转为字符状态，并提供行号查询支持
 * 如果我们要处理的数据量不至于对内存使用造成负担,也没有并发安全的需要,可以使用它.
 */
public class TxtState implements State<Character> {
  private final List<Character> buffer;
  private final Map<Integer, Integer> lines = new HashMap<>();
  private int current = 0;
  private int tran = -1;

  @Override
  public Character next() throws EOFException {
    if (this.current >= this.buffer.size()) {
      throw new EOFException();
    }
    Character re = this.buffer.get(this.current);
    this.current++;
    return re;
  }

  @Override
  public Integer status() {
    return this.current;
  }

  @Override
  public Integer begin() {
    if (this.tran == -1) {
      this.tran = this.current;
    }
    return this.current;
  }

  @Override
  public Integer begin(Integer tran) {
    if (this.tran > tran) {
      this.tran = tran;
    }
    return this.tran;
  }

  @Override
  public void rollback(Integer tran) {
    if (this.tran == tran) {
      this.tran = -1;
    }
    this.current = tran;
  }

  @Override
  public void commit(Integer tran) {
    if (this.tran == tran) {
      this.tran = -1;
    }
  }

  @Override
  public ParsecException trap(String message) {
    return new ParsecException(this.current, message);
  }

  public TxtState(String content, Character newLine) {
    List<Character> characters = new ArrayList<>();
    lines.put(0, 0);
    for (int i = 0; i < content.length(); i++) {
      Character c = content.charAt(i);
      characters.add(c);
      if (c.equals(newLine)) {
        int idx = i;
        lines.keySet().stream().max(Comparator.naturalOrder()).ifPresent(last -> {
          lines.put(last, idx);
          lines.put(idx + 1, idx + 1);
        });
      }
    }
    this.buffer = characters;
  }

  public int lineOfIndex(int index) {
    int i = 0;
    for (int idx : lines.keySet()) {
      if (idx <= index && index <= lines.get(idx)) {
        return i;
      }
      i += 1;
    }
    return -1;
  }

}
