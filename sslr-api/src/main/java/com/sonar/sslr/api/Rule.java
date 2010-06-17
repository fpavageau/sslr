/*
 * Copyright (C) 2010 SonarSource SA
 * All rights reserved
 * mailto:contact AT sonarsource DOT com
 */

package com.sonar.sslr.api;

/**
 * A Rule describes a grammar syntactic rule.
 * 
 * @see Grammar
 * @see <a href="http://en.wikipedia.org/wiki/Backus%E2%80%93Naur_Form">Backus�Naur Form</a>
 */
public interface Rule extends AstNodeSkippingPolicy {

  public Rule is(Object... matchers);
  
  public Rule or(Object... matchers);
  
  public Rule and(Object... matchers);
  
  public Rule orBefore(Object... matchers);

  public Rule isOr(Object... matchers);
  
  public Rule setListener(AstListener listener);

  public Rule skip();
  
  public Rule skipIf(AstNodeSkippingPolicy policy);
  
  public Rule skipIfOneChild();

  public void mockUpperCase();

  public void mock();
}
