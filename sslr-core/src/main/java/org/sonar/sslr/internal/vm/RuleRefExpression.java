/*
 * SonarSource Language Recognizer
 * Copyright (C) 2010-2016 SonarSource SA
 * mailto:contact AT sonarsource DOT com
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
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
 */
package org.sonar.sslr.internal.vm;

import org.sonar.sslr.grammar.GrammarRuleKey;

public class RuleRefExpression extends NativeExpression {

  private final GrammarRuleKey ruleKey;

  public RuleRefExpression(GrammarRuleKey ruleKey) {
    this.ruleKey = ruleKey;
  }

  public GrammarRuleKey getRuleKey() {
    return ruleKey;
  }

  @Override
  public void execute(Machine machine) {
    throw new UnsupportedOperationException();
  }

  @Override
  public String toString() {
    return "Ref " + ruleKey;
  }

}
