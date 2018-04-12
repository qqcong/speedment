package com.speedment.runtime.compute;

import com.speedment.common.function.ShortToDoubleFunction;
import com.speedment.common.function.ShortUnaryOperator;
import com.speedment.runtime.compute.expression.Expression;
import com.speedment.runtime.compute.expression.ExpressionType;
import com.speedment.runtime.compute.expression.Expressions;
import com.speedment.runtime.compute.trait.HasAbs;
import com.speedment.runtime.compute.trait.HasCompare;
import com.speedment.runtime.compute.trait.HasHash;
import com.speedment.runtime.compute.trait.HasNegate;
import com.speedment.runtime.compute.trait.HasSign;
import com.speedment.runtime.compute.trait.HasSqrt;
import com.speedment.runtime.compute.trait.ToNullable;

/**
 * @author Emil Forslund
 * @since 3.1.0
 */
@FunctionalInterface
public interface ToShortNullable<T>
extends Expression,
        ToNullable<T, Short>,
        HasAbs<ToShortNullable<T>>,
        HasSign<ToByteNullable<T>>,
        HasSqrt<ToDoubleNullable<T>>,
        HasNegate<ToShortNullable<T>>,
        HasHash<T>,
        HasCompare<T> {

    @Override
    default ExpressionType getExpressionType() {
        return ExpressionType.SHORT_NULLABLE;
    }

    default short applyAsShort(T object) throws NullPointerException {
        return apply(object);
    }

    default ToShort<T> orThrow() throws NullPointerException {
        return this::apply;
    }

    default ToShort<T> orElseGet(ToShort<T> getter) {
        return object -> isNull(object)
            ? getter.applyAsShort(object)
            : applyAsShort(object);
    }

    default ToShort<T> orElse(short value) {
        return object -> isNull(object)
            ? value
            : applyAsShort(object);
    }

    @Override
    default ToShortNullable<T> abs() {
        return Expressions.absOrNull(this);
    }

    @Override
    default ToShortNullable<T> negate() {
        return Expressions.negateOrNull(this);
    }

    @Override
    default ToByteNullable<T> sign() {
        return Expressions.signOrNull(this);
    }

    @Override
    default ToDoubleNullable<T> sqrt() {
        return Expressions.sqrtOrNull(this);
    }

    default ToDoubleNullable<T> mapToDoubleIfPresent(ShortToDoubleFunction mapper) {
        final ToShortNullable<T> delegate = this;
        return new ToDoubleNullable<T>() {
            @Override
            public Double apply(T object) {
                return delegate.isNull(object) ? null
                    : mapper.applyAsDouble(delegate.applyAsShort(object));
            }

            @Override
            public double applyAsDouble(T object) throws NullPointerException {
                return mapper.applyAsDouble(delegate.applyAsShort(object));
            }

            @Override
            public ToDouble<T> orElseGet(ToDouble<T> getter) {
                return object -> delegate.isNull(object)
                    ? getter.applyAsDouble(object)
                    : mapper.applyAsDouble(delegate.applyAsShort(object));
            }

            @Override
            public ToDouble<T> orElse(double value) {
                return object -> delegate.isNull(object)
                    ? value
                    : mapper.applyAsDouble(delegate.applyAsShort(object));
            }

            @Override
            public boolean isNull(T object) {
                return delegate.isNull(object);
            }

            @Override
            public boolean isNotNull(T object) {
                return delegate.isNotNull(object);
            }
        };
    }

    default ToShortNullable<T> mapIfPresent(ShortUnaryOperator mapper) {
        final ToShortNullable<T> delegate = this;
        return new ToShortNullable<T>() {
            @Override
            public Short apply(T object) {
                return delegate.isNull(object) ? null
                    : mapper.applyAsShort(delegate.applyAsShort(object));
            }

            @Override
            public short applyAsShort(T object) throws NullPointerException {
                return mapper.applyAsShort(delegate.applyAsShort(object));
            }

            @Override
            public ToShort<T> orElseGet(ToShort<T> getter) {
                return object -> delegate.isNull(object)
                    ? getter.applyAsShort(object)
                    : mapper.applyAsShort(delegate.applyAsShort(object));
            }

            @Override
            public ToShort<T> orElse(short value) {
                return object -> delegate.isNull(object)
                    ? value
                    : mapper.applyAsShort(delegate.applyAsShort(object));
            }

            @Override
            public boolean isNull(T object) {
                return delegate.isNull(object);
            }

            @Override
            public boolean isNotNull(T object) {
                return delegate.isNotNull(object);
            }
        };
    }

    @Override
    default long hash(T object) {
        return isNull(object) ? 0 : applyAsShort(object);
    }

    @Override
    default int compare(T first, T second) {
        if (isNull(first)) {
            return isNull(second) ? 0 : 1;
        } else if (isNull(second)) {
            return -1;
        } else {
            return Short.compare(
                applyAsShort(first),
                applyAsShort(second)
            );
        }
    }
}
