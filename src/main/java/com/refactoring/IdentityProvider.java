package com.refactoring;

/**
 * Created by artem on 4/23/17.
 */
public class IdentityProvider {
    public static User getCurrentUser() {
        return new User();
    }
}
