/*
 * MIT License
 *
 * Copyright (c) [2019] [He Zhang]
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.github.furion.formula.arithmetic;

import com.github.furion.enums.FormulaType;
import com.github.furion.exception.FormulaException;
import com.github.furion.formula.DyadicFormula;
import com.github.furion.formula.Formula;
import com.github.furion.formula.number.NumberFormula;
import com.github.furion.utils.ExceptionUtils;

public abstract class ArithmeticFormula extends DyadicFormula {

    public ArithmeticFormula(FormulaType formulaType, Formula lhs, Formula rhs) {
        super(formulaType, lhs, rhs);
    }

    public Formula calculate() throws FormulaException {
        Formula left = lhs.calculate();
        if (left == null) {
            throw new FormulaException(ExceptionUtils.MISSING_LEFT_HAND_SIDE);
        }
        Formula right = rhs.calculate();
        if (right == null) {
            throw new FormulaException(ExceptionUtils.MISSING_RIGHT_HAND_SIDE);
        }
        double l = simplify(left);
        double r = simplify(right);
        return calculate(l, r);
    }

    public double simplify(Formula formula) {
        if (formula == null)
            return 0.0;
        if (formula.getFormulaType() == FormulaType.NUMBER_DOUBLE ||
            formula.getFormulaType() == FormulaType.NUMBER_INTEGER) {
            return ((NumberFormula) formula).getValue();
        }
        return 0.0;
    }

    public abstract Formula calculate(double lhs, double rhs) throws FormulaException;
}
