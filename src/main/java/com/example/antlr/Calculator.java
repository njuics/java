package com.example.antlr;

import org.antlr.v4.runtime.*;

public class Calculator {
    public static void main(String[] args) {
        // 测试多个表达式
        String[] expressions = {
            "3 + 4 * 2",      // 11
            "(3 + 4) * 2",    // 14
            "10 - 2 * 3",     // 4
            "20 / 4 + 3"      // 8
        };
        
        for (String input : expressions) {
            CharStream stream = CharStreams.fromString(input);
            CalcLexer lexer = new CalcLexer(stream);
            CommonTokenStream tokens = new CommonTokenStream(lexer);
            CalcParser parser = new CalcParser(tokens);

            CalcParser.ExprContext tree = parser.expr();
            System.out.println(input + " = " + evaluate(tree));
        }
    }

    private static int evaluate(CalcParser.ExprContext ctx) {
        // 处理加法
        if (ctx instanceof CalcParser.AddContext) {
            CalcParser.AddContext addCtx = (CalcParser.AddContext) ctx;
            return evaluate(addCtx.expr()) + evaluate(addCtx.term());
        }
        // 处理减法
        if (ctx instanceof CalcParser.SubContext) {
            CalcParser.SubContext subCtx = (CalcParser.SubContext) ctx;
            return evaluate(subCtx.expr()) - evaluate(subCtx.term());
        }
        // 处理项值
        if (ctx instanceof CalcParser.TermValueContext) {
            CalcParser.TermValueContext termCtx = (CalcParser.TermValueContext) ctx;
            return evaluate(termCtx.term());
        }
        throw new IllegalArgumentException("未知的表达式类型");
    }
    
    private static int evaluate(CalcParser.TermContext ctx) {
        // 处理乘法
        if (ctx instanceof CalcParser.MulContext) {
            CalcParser.MulContext mulCtx = (CalcParser.MulContext) ctx;
            return evaluate(mulCtx.term()) * evaluate(mulCtx.factor());
        }
        // 处理除法
        if (ctx instanceof CalcParser.DivContext) {
            CalcParser.DivContext divCtx = (CalcParser.DivContext) ctx;
            return evaluate(divCtx.term()) / evaluate(divCtx.factor());
        }
        // 处理因子值
        if (ctx instanceof CalcParser.FactorValueContext) {
            CalcParser.FactorValueContext factorCtx = (CalcParser.FactorValueContext) ctx;
            return evaluate(factorCtx.factor());
        }
        throw new IllegalArgumentException("未知的项类型");
    }
    
    private static int evaluate(CalcParser.FactorContext ctx) {
        // 处理数字
        if (ctx instanceof CalcParser.NumberContext) {
            CalcParser.NumberContext numberCtx = (CalcParser.NumberContext) ctx;
            return Integer.parseInt(numberCtx.NUMBER().getText());
        }
        // 处理括号表达式
        if (ctx instanceof CalcParser.ParensContext) {
            CalcParser.ParensContext parensCtx = (CalcParser.ParensContext) ctx;
            return evaluate(parensCtx.expr());
        }
        throw new IllegalArgumentException("未知的因子类型");
    }
}
