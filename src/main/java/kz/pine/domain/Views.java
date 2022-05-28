package kz.pine.domain;

public class Views {
    public interface Id {}

    public interface ShortInfo extends Id {}

    public interface FullProductInfo extends ShortInfo {}

    public interface FullCategoryInfo extends ShortInfo {}

    public interface FullProfileInfo extends ShortInfo {}

    public interface FullCartInfo extends ShortInfo {}

}

