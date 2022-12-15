package com.sp.spring.commons.util;

import net.bytebuddy.utility.RandomString;

public class CodeUtility {

    public static String generateCode() {
        return RandomString.make(45);
    }

    public static CodeChallengeGenerator codeChallengeGenerator() {
        return new CodeChallengeGenerator();
    }

}
