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
package com.sonar.sslr.symboltable;

import com.sonar.sslr.api.AstNode;
import com.sonar.sslr.api.symboltable.Scope;
import com.sonar.sslr.api.symboltable.Symbol;
import com.sonar.sslr.api.symboltable.SymbolTable;

public class MethodSymbol implements Symbol {

  private final SymbolTable symbolTable;
  private final String name;

  public MethodSymbol(SymbolTable symbolTable, String name) {
    this.symbolTable = symbolTable;
    this.name = name;
  }

  @Override
  public String toString() {
    return "Method{" + getName() + "}";
  }

  public AstNode getAstNode() {
    return symbolTable.getAstNode(this);
  }

  public Scope getEnclosingScope() {
    return symbolTable.getEnclosingScope(getAstNode().getParent());
  }

  public String getName() {
    return name;
  }

}
