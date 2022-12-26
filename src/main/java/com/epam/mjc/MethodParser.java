package com.epam.mjc;

import java.util.LinkedList;
import java.util.List;

public class MethodParser {

    /**
     * Parses string that represents a method signature and stores all it's members into a {@link MethodSignature} object.
     * signatureString is a java-like method signature with following parts:
     *      1. access modifier - optional, followed by space: ' '
     *      2. return type - followed by space: ' '
     *      3. method name
     *      4. arguments - surrounded with braces: '()' and separated by commas: ','
     * Each argument consists of argument type and argument name, separated by space: ' '.
     * Examples:
     *      accessModifier returnType methodName(argumentType1 argumentName1, argumentType2 argumentName2)
     *      private void log(String value)
     *      Vector3 distort(int x, int y, int z, float magnitude)
     *      public DateTime getCurrentDateTime()
     *
     * @param signatureString source string to parse
     * @return {@link MethodSignature} object filled with parsed values from source string
     */
    public MethodSignature parseFunction(String signatureString) {
        String modifier;
        String returnType;
        String name;
        List<MethodSignature.Argument> argumentList = new LinkedList<>();
            String[] strings = signatureString.substring(0, signatureString.indexOf('(')).split(" ");
            if (strings.length > 2) {
                modifier = strings[0];
                returnType = strings[1];
                name = strings[2];
            } else {
                modifier = null;
                returnType = strings[0];
                name = strings[1];
            }

            String secondSubstring = signatureString.substring(signatureString.indexOf('(') + 1, signatureString.length() - 1);
            String[] parameterInfo = secondSubstring.split(",");
            if (parameterInfo[0].equals(secondSubstring)) {
                MethodSignature methodSignature1 = new MethodSignature(name);
                methodSignature1.setAccessModifier(modifier);
                methodSignature1.setReturnType(returnType);

                return methodSignature1;
            }
            for (String str : parameterInfo) {
                String[] typeAndName = str.trim().split(" ");
                argumentList.add(new MethodSignature.Argument(typeAndName[0], typeAndName[1]));
            }

            MethodSignature methodSignature = new MethodSignature(name, argumentList);
            methodSignature.setAccessModifier(modifier);
            methodSignature.setReturnType(returnType);

            return methodSignature;
    }
}
