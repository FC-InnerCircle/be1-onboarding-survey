package lshh.be1onboardingsurvey.common.lib.diff;

import java.util.Map;

public class DiffImplement<Left, Right> implements Diff<Left, Right>{
    Left left;
    Right right;

    public DiffImplement(Left left, Right right) {
        this.left = left;
        this.right = right;
    }
    @Override
    public boolean isSame(){
        return left == right;
    }
    @Override
    public boolean isEqual(){
        if(isSame()) return true;
        return left.equals(right);
    }
    @Override
    public boolean isSameType(){
        if(left != null && right != null) {
            return left.getClass().equals(right.getClass());
        }
        return false;
    }
    @Override
    public boolean isNotChanged(){
        if(isEqual()) return true;
        if(!isSameType()) return false;
        Map<String, Object> leftMap = MapConverter.toMap(left);
        Map<String, Object> rightMap = MapConverter.toMap(right);
        return leftMap.equals(rightMap);
    }
    @Override
    public Left getLeft(){
        return left;
    }
    @Override
    public Right getRight(){
        return right;
    }


}
