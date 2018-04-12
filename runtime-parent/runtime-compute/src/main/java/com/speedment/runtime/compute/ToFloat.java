package com.speedment.runtime.compute;

import com.speedment.common.function.FloatToDoubleFunction;
import com.speedment.common.function.FloatUnaryOperator;
import com.speedment.common.function.ToFloatFunction;
import com.speedment.runtime.compute.expression.Expression;
import com.speedment.runtime.compute.expression.ExpressionType;
import com.speedment.runtime.compute.expression.Expressions;
import com.speedment.runtime.compute.trait.HasAbs;
import com.speedment.runtime.compute.trait.HasAsDouble;
import com.speedment.runtime.compute.trait.HasAsInt;
import com.speedment.runtime.compute.trait.HasAsLong;
import com.speedment.runtime.compute.trait.HasCompare;
import com.speedment.runtime.compute.trait.HasDivide;
import com.speedment.runtime.compute.trait.HasHash;
import com.speedment.runtime.compute.trait.HasMinus;
import com.speedment.runtime.compute.trait.HasMultiply;
import com.speedment.runtime.compute.trait.HasNegate;
import com.speedment.runtime.compute.trait.HasPlus;
import com.speedment.runtime.compute.trait.HasPow;
import com.speedment.runtime.compute.trait.HasSign;
import com.speedment.runtime.compute.trait.HasSqrt;

/**
 * @author Emil Forslund
 * @since  3.1.0
 */
@FunctionalInterface
public interface ToFloat<T>
extends Expression,
        ToFloatFunction<T>,
        HasAsDouble<T>,
        HasAsInt<T>,
        HasAsLong<T>,
        HasAbs<ToFloat<T>>,
        HasSign<ToByte<T>>,
        HasSqrt<ToDouble<T>>,
        HasNegate<ToFloat<T>>,
        HasPow<T, ToDouble<T>, ToDouble<T>>,
        HasPlus<T, ToFloat<T>, ToFloat<T>, ToDouble<T>>,
        HasMinus<T, ToFloat<T>, ToFloat<T>, ToDouble<T>>,
        HasMultiply<T, ToFloat<T>, ToFloat<T>, ToDouble<T>>,
        HasDivide<T, ToInt<T>>,
        HasHash<T>,
        HasCompare<T> {

    float applyAsFloat(T object);

    @Override
    default ExpressionType getExpressionType() {
        return ExpressionType.FLOAT;
    }

    @Override
    default ToDouble<T> asDouble() {
        return this::applyAsFloat;
    }

    @Override
    default ToInt<T> asInt() {
        return object -> (int) applyAsFloat(object);
    }

    @Override
    default ToLong<T> asLong() {
        return object -> (long) applyAsFloat(object);
    }

    default ToDouble<T> mapToDouble(FloatToDoubleFunction operator) {
        return object -> operator.applyAsDouble(applyAsFloat(object));
    }

    default ToFloat<T> map(FloatUnaryOperator operator) {
        return object -> operator.applyAsFloat(applyAsFloat(object));
    }

    @Override
    default ToFloat<T> abs() {
        return Expressions.abs(this);
    }

    @Override
    default ToByte<T> sign() {
        return Expressions.sign(this);
    }

    @Override
    default ToDouble<T> sqrt() {
        return Expressions.sqrt(this);
    }

    @Override
    default ToFloat<T> negate() {
        return Expressions.negate(this);
    }

    @Override
    default ToDouble<T> pow(byte power) {
        return Expressions.pow(this, power);
    }

    @Override
    default ToDouble<T> pow(int power) {
        return Expressions.pow(this, power);
    }

    @Override
    default ToDouble<T> pow(double power) {
        return Expressions.pow(this, power);
    }

    @Override
    default ToDouble<T> pow(ToByte<T> power) {
        return Expressions.pow(this, power);
    }

    @Override
    default ToDouble<T> pow(ToInt<T> power) {
        return Expressions.pow(this, power);
    }

    @Override
    default ToDouble<T> pow(ToDouble<T> power) {
        return Expressions.pow(this, power);
    }

    @Override
    default ToFloat<T> plus(byte other) {
        return Expressions.plus(this, (int) other);
    }

    @Override
    default ToFloat<T> plus(ToByte<T> other) {
        return Expressions.plus(this, other.asInt());
    }

    @Override
    default ToFloat<T> plus(int other) {
        return Expressions.plus(this, other);
    }

    @Override
    default ToFloat<T> plus(ToInt<T> other) {
        return Expressions.plus(this, other);
    }

    @Override
    default ToDouble<T> plus(long other) {
        return Expressions.plus(this, other);
    }

    @Override
    default ToDouble<T> plus(ToLong<T> other) {
        return Expressions.plus(this, other);
    }

    @Override
    default ToDouble<T> plus(double other) {
        return Expressions.plus(this.asDouble(), other);
    }

    @Override
    default ToDouble<T> plus(ToDouble<T> other) {
        return Expressions.plus(this.asDouble(), other);
    }

    @Override
    default ToFloat<T> minus(byte other) {
        return Expressions.minus(this, other);
    }

    @Override
    default ToFloat<T> minus(ToByte<T> other) {
        return Expressions.minus(this, other.asInt());
    }

    @Override
    default ToFloat<T> minus(int other) {
        return Expressions.minus(this, other);
    }

    @Override
    default ToFloat<T> minus(ToInt<T> other) {
        return Expressions.minus(this, other);
    }

    @Override
    default ToDouble<T> minus(long other) {
        return Expressions.minus(this, other);
    }

    @Override
    default ToDouble<T> minus(ToLong<T> other) {
        return Expressions.minus(this, other);
    }

    @Override
    default ToDouble<T> minus(double other) {
        return Expressions.minus(this.asDouble(), other);
    }

    @Override
    default ToDouble<T> minus(ToDouble<T> other) {
        return Expressions.minus(this.asDouble(), other);
    }

    @Override
    default ToFloat<T> multiply(byte other) {
        return Expressions.multiply(this, other);
    }

    @Override
    default ToFloat<T> multiply(ToByte<T> other) {
        return Expressions.multiply(this, other.asInt());
    }

    @Override
    default ToFloat<T> multiply(int other) {
        return Expressions.multiply(this, other);
    }

    @Override
    default ToFloat<T> multiply(ToInt<T> other) {
        return Expressions.multiply(this, other);
    }

    @Override
    default ToDouble<T> multiply(long other) {
        return Expressions.multiply(this, other);
    }

    @Override
    default ToDouble<T> multiply(ToLong<T> other) {
        return Expressions.multiply(this, other);
    }

    @Override
    default ToDouble<T> multiply(double other) {
        return Expressions.multiply(this.asDouble(), other);
    }

    @Override
    default ToDouble<T> multiply(ToDouble<T> other) {
        return Expressions.multiply(this.asDouble(), other);
    }

    @Override
    default ToInt<T> divideFloor(byte divisor) {
        return Expressions.divideFloor(this, divisor);
    }

    @Override
    default ToInt<T> divideFloor(ToByte<T> divisor) {
        return Expressions.divideFloor(this, divisor);
    }

    @Override
    default ToInt<T> divideFloor(int divisor) {
        return Expressions.divideFloor(this, divisor);
    }

    @Override
    default ToInt<T> divideFloor(ToInt<T> divisor) {
        return Expressions.divideFloor(this, divisor);
    }

    @Override
    default ToInt<T> divideFloor(long divisor) {
        return Expressions.divideFloor(this, divisor);
    }

    @Override
    default ToInt<T> divideFloor(ToLong<T> divisor) {
        return Expressions.divideFloor(this, divisor);
    }

    @Override
    default ToDouble<T> divide(byte divisor) {
        return Expressions.divide(this, divisor);
    }

    @Override
    default ToDouble<T> divide(ToByte<T> divisor) {
        return Expressions.divide(this, divisor);
    }

    @Override
    default ToDouble<T> divide(int divisor) {
        return Expressions.divide(this, divisor);
    }

    @Override
    default ToDouble<T> divide(ToInt<T> divisor) {
        return Expressions.divide(this, divisor);
    }

    @Override
    default ToDouble<T> divide(long divisor) {
        return Expressions.divide(this, divisor);
    }

    @Override
    default ToDouble<T> divide(ToLong<T> divisor) {
        return Expressions.divide(this, divisor);
    }

    @Override
    default ToDouble<T> divide(double divisor) {
        return Expressions.divide(this, divisor);
    }

    @Override
    default ToDouble<T> divide(ToDouble<T> divisor) {
        return Expressions.divide(this, divisor);
    }

    @Override
    default long hash(T object) {
        final float f = applyAsFloat(object);
        return f != +0.0f ? Float.floatToIntBits(f) : 0;
    }

    @Override
    default int compare(T first, T second) {
        return Float.compare(
            applyAsFloat(first),
            applyAsFloat(second)
        );
    }
}
