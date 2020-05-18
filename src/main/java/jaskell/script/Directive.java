package jaskell.script;

import java.util.List;

public interface Directive {
    String script();
    List<Parameter<?>> parameters();
}
