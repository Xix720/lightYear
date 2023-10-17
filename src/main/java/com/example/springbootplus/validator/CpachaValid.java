package com.example.springbootplus.validator;

public class CpachaValid {
public static boolean valid(String code, String sessionCode) {
        if (code == null || code.length() == 0) {
            return false;
        }
        if (sessionCode == null || sessionCode.length() == 0) {
            return false;
        }
        if (code.equals(sessionCode)) {
            return true;
        }
        return false;
    }
}
