/*
 * SonarSource Language Recognizer
 * Copyright (C) 2010 SonarSource
 * dev@sonar.codehaus.org
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 3 of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02
 */
package org.sonar.sslr.grammar;

import com.google.common.collect.Lists;
import org.sonar.sslr.internal.vm.CompilableGrammarRule;
import org.sonar.sslr.internal.vm.FirstOfExpression;
import org.sonar.sslr.internal.vm.NextExpression;
import org.sonar.sslr.internal.vm.NextNotExpression;
import org.sonar.sslr.internal.vm.NothingExpression;
import org.sonar.sslr.internal.vm.OneOrMoreExpression;
import org.sonar.sslr.internal.vm.OptionalExpression;
import org.sonar.sslr.internal.vm.ParsingExpression;
import org.sonar.sslr.internal.vm.SequenceExpression;
import org.sonar.sslr.internal.vm.ZeroOrMoreExpression;

import java.util.List;

/**
 * <p>This class is not intended to be instantiated or subclassed by clients.</p>
 */
abstract class GrammarBuilder {

  /**
   * Allows to describe rule.
   * Result of this method should be used only for execution of methods in it, i.e. you should not save reference on it.
   * No guarantee that this method always returns the same instance for the same key of rule.
   */
  public abstract GrammarRuleBuilder rule(GrammarRuleKey ruleKey);

  /**
   * Allows to specify that given rule should be root for grammar.
   */
  public abstract void setRootRule(GrammarRuleKey ruleKey);

  /**
   * Creates expression of grammar - "sequence".
   *
   * @param e1  first sub-expression
   * @param e2  second sub-expression
   * @throws IllegalArgumentException if any of given arguments is not a parsing expression
   */
  public final Object sequence(Object e1, Object e2) {
    return new SequenceExpression(convertToExpression(e1), convertToExpression(e2));
  }

  /**
   * Creates expression of grammar - "sequence".
   *
   * @param e1  first sub-expression
   * @param e2  second sub-expression
   * @param rest  rest of sub-expressions
   * @throws IllegalArgumentException if any of given arguments is not a parsing expression
   */
  public final Object sequence(Object e1, Object e2, Object... rest) {
    return new SequenceExpression(convertToExpressions(Lists.asList(e1, e2, rest)));
  }

  /**
   * Creates expression of grammar - "first of".
   *
   * @param e1  first sub-expression
   * @param e2  second sub-expression
   * @throws IllegalArgumentException if any of given arguments is not a parsing expression
   */
  public final Object firstOf(Object e1, Object e2) {
    return new FirstOfExpression(convertToExpression(e1), convertToExpression(e2));
  }

  /**
   * Creates expression of grammar - "first of".
   *
   * @param e1  first sub-expression
   * @param e2  second sub-expression
   * @param rest  rest of sub-expressions
   * @throws IllegalArgumentException if any of given arguments is not a parsing expression
   */
  public final Object firstOf(Object e1, Object e2, Object... rest) {
    return new FirstOfExpression(convertToExpressions(Lists.asList(e1, e2, rest)));
  }

  /**
   * Creates expression of grammar - "optional".
   *
   * @param e  sub-expression
   * @throws IllegalArgumentException if given argument is not a parsing expression
   */
  public final Object optional(Object e) {
    return new OptionalExpression(convertToExpression(e));
  }

  /**
   * Creates expression of grammar - "optional".
   *
   * @param e1  first sub-expression
   * @param rest  rest of sub-expressions
   * @throws IllegalArgumentException if any of given arguments is not a parsing expression
   */
  public final Object optional(Object e1, Object... rest) {
    return new OptionalExpression(new SequenceExpression(convertToExpressions(Lists.asList(e1, rest))));
  }

  /**
   * Creates expression of grammar - "one or more".
   *
   * @param e  sub-expression
   * @throws IllegalArgumentException if given argument is not a parsing expression
   */
  public final Object oneOrMore(Object e) {
    return new OneOrMoreExpression(convertToExpression(e));
  }

  /**
   * Creates expression of grammar - "one or more".
   *
   * @param e1  first sub-expression
   * @param rest  rest of sub-expressions
   * @throws IllegalArgumentException if any of given arguments is not a parsing expression
   */
  public final Object oneOrMore(Object e1, Object... rest) {
    return new OneOrMoreExpression(new SequenceExpression(convertToExpressions(Lists.asList(e1, rest))));
  }

  /**
   * Creates expression of grammar - "zero or more".
   *
   * @param e  sub-expression
   * @throws IllegalArgumentException if given argument is not a parsing expression
   */
  public final Object zeroOrMore(Object e) {
    return new ZeroOrMoreExpression(convertToExpression(e));
  }

  /**
   * Creates expression of grammar - "zero or more".
   *
   * @param e1  sub-expression
   * @param rest  rest of sub-expressions
   * @throws IllegalArgumentException if any of given arguments is not a parsing expression
   */
  public final Object zeroOrMore(Object e1, Object... rest) {
    return new ZeroOrMoreExpression(new SequenceExpression(convertToExpressions(Lists.asList(e1, rest))));
  }

  /**
   * Creates expression of grammar - "next".
   *
   * @param e  sub-expression
   * @throws IllegalArgumentException if given argument is not a parsing expression
   */
  public final Object next(Object e) {
    return new NextExpression(convertToExpression(e));
  }

  /**
   * Creates expression of grammar - "next".
   *
   * @param e1  first sub-expression
   * @param rest  rest of sub-expressions
   * @throws IllegalArgumentException if any of given arguments is not a parsing expression
   */
  public final Object next(Object e1, Object... rest) {
    return new NextExpression(new SequenceExpression(convertToExpressions(Lists.asList(e1, rest))));
  }

  /**
   * Creates expression of grammar - "next not".
   *
   * @param e  sub-expression
   * @throws IllegalArgumentException if given argument is not a parsing expression
   */
  public final Object nextNot(Object e) {
    return new NextNotExpression(convertToExpression(e));
  }

  /**
   * Creates expression of grammar - "next not".
   *
   * @param e1  sub-expression
   * @param rest  rest of sub-expressions
   * @throws IllegalArgumentException if any of given arguments is not a parsing expression
   */
  public final Object nextNot(Object e1, Object... rest) {
    return new NextNotExpression(new SequenceExpression(convertToExpressions(Lists.asList(e1, rest))));
  }

  /**
   * Creates expression of grammar - "nothing".
   */
  public final Object nothing() {
    return NothingExpression.INSTANCE;
  }

  protected abstract ParsingExpression convertToExpression(Object e);

  protected final ParsingExpression[] convertToExpressions(List<Object> expressions) {
    ParsingExpression[] result = new ParsingExpression[expressions.size()];
    for (int i = 0; i < expressions.size(); i++) {
      result[i] = convertToExpression(expressions.get(i));
    }
    return result;
  }

  /**
   * Adapts {@link CompilableGrammarRule} to be used as {@link GrammarRuleBuilder}.
   */
  static class RuleBuilder implements GrammarRuleBuilder {

    private final GrammarBuilder b;
    private final CompilableGrammarRule delegate;

    public RuleBuilder(GrammarBuilder b, CompilableGrammarRule delegate) {
      this.b = b;
      this.delegate = delegate;
    }

    public GrammarRuleBuilder is(Object e) {
      if (delegate.getExpression() != null) {
        throw new GrammarException("The rule '" + delegate.getRuleKey() + "' has already been defined somewhere in the grammar.");
      }
      delegate.setExpression(b.convertToExpression(e));
      return this;
    }

    public GrammarRuleBuilder is(Object e, Object... rest) {
      return is(new SequenceExpression(b.convertToExpressions(Lists.asList(e, rest))));
    }

    public GrammarRuleBuilder override(Object e) {
      delegate.setExpression(b.convertToExpression(e));
      return this;
    }

    public GrammarRuleBuilder override(Object e, Object... rest) {
      return override(new SequenceExpression(b.convertToExpressions(Lists.asList(e, rest))));
    }

    public void skip() {
      delegate.skip();
    }

    public void skipIfOneChild() {
      delegate.skipIfOneChild();
    }

    public void recoveryRule() {
      throw new UnsupportedOperationException();
    }

  }

}
