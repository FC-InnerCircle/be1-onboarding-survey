package lshh.be1onboardingsurvey.common.lib.diff;

public interface Diff<Left, Right> {
    static <Left, Right> Diff<Left, Right> of(Left left, Right right) {
        return new DiffImplement<>(left, right);
    }

    boolean isSame();

    boolean isEqual();

    boolean isSameType();

    boolean isNotChanged();

    Left getLeft();

    Right getRight();
}
