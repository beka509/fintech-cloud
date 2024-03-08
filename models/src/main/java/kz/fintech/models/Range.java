package kz.fintech.models;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class Range<T extends Comparable> implements Serializable {
    private static final long serialVersionUID = 346788950533105992L;
    private T from;
    private T to;
    private boolean fromInclusive;
    private boolean toInclusive;

    public boolean contains(T number) {
        return (fromInclusive && from.compareTo(number) <= 0 || !fromInclusive && from.compareTo(number) < 0)
                && (toInclusive && to.compareTo(number) >= 0 || !toInclusive && to.compareTo(number) > 0);
    }

    public Range() {
    }
}
