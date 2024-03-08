package kz.fintech.commons.components;

public abstract class UtilityBase implements Utility {
    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (!Utility.class.isAssignableFrom(obj.getClass())) return false;
        if (getName() == null) return false;
        return getName().equals(((Utility) obj).getName());
    }
}
