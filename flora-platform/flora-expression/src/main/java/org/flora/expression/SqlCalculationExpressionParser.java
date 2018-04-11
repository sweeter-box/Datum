/*
 * Copyright (c) 2018-present the original author or authors.
 *
 * Permission is hereby granted, free of charge, to any person obtaining
 * a copy of this software and associated documentation files (the
 * "Software"), to deal in the Software without restriction, including
 * without limitation the rights to use, copy, modify, merge, publish,
 * distribute, sublicense, and/or sell copies of the Software, and to
 * permit persons to whom the Software is furnished to do so, subject to
 * the following conditions:
 *
 * The above copyright notice and this permission notice shall be
 * included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 * EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
 * MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE
 * LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION
 * OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION
 * WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package org.flora.expression;

import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.flora.expression.antlr.ErrorListener;
import org.flora.expression.antlr.FloraExpressionLexer;
import org.flora.expression.antlr.FloraExpressionParser;
import org.flora.expression.antlr.visitor.FloraExpressionVisitor;

/**
 * 将求值表达式解析成合法的 sql.
 * 
 * @author 7cat
 * @since 1.0
 */
public class SqlCalculationExpressionParser implements CalculationExpressionParser<String> {

	private ErrorListener listener = new ErrorListener();

	@Override
	public String eval(String expression, CalculationContext context) {
		FloraExpressionLexer lexer = new FloraExpressionLexer(CharStreams.fromString(expression));
		lexer.removeErrorListeners();
		// lexer.addErrorListener(new DiagnosticErrorListener(true));
		lexer.addErrorListener(listener);
		CommonTokenStream tokens = new CommonTokenStream(lexer);
		FloraExpressionParser parser = new FloraExpressionParser(tokens);
		parser.removeErrorListeners();
		parser.addErrorListener(listener);
		return parser.calculations().accept(new FloraExpressionVisitor());
	}

	public void setListener(ErrorListener listener) {
		this.listener = listener;
	}
}
