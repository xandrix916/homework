package edu.hw2;

public class Problem1 {
    public sealed interface Expr {
        double evaluate();

        record Constant(double c) implements Expr {
            @Override public double evaluate() {
                return c;
            }
        }

        record Negate(Expr expression) implements Expr {
            @Override public double evaluate() {
                return -expression.evaluate();
            }
        }

        record Exponent(Expr expression, double power) implements Expr {
            @Override public double evaluate() {
                return Math.pow(expression.evaluate(), power);
            }
        }

        record Addition(Expr expression1, Expr expression2) implements Expr {
            @Override public double evaluate() {
                return expression1.evaluate() + expression2.evaluate();
            }
        }

        record Multiplication(Expr expression1, Expr expression2) implements Expr {
            @Override public double evaluate() {
                return expression1.evaluate() * expression2.evaluate();
            }
        }
    }

    @SuppressWarnings({"InnerTypeLast", "MagicNumber", "RegexpSinglelineJava"})
    public String  calculationExample() {
        var two = new Expr.Constant(2);
        var four = new Expr.Constant(4);
        var negOne = new Expr.Negate(new Expr.Constant(1));
        var sumTwoFour = new Expr.Addition(two, four);
        var mult = new Expr.Multiplication(sumTwoFour, negOne);
        var exp = new Expr.Exponent(mult, 2);
        var res = new Expr.Addition(exp, new Expr.Constant(1));

        return res + " = " + res.evaluate();
    }
}
