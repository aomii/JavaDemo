package com.aoming.interview.meituan;

public class NumberToChinese {
    private static final String[] NUMBERS = {"零", "一", "二", "三", "四", "五", "六", "七", "八", "九"};
    private static final String[] UNITS = {"", "十", "百", "千"}; // 个级单位
    private static final String[] LEVELS = {"", "万", "亿"}; // 数级单位

    public static String convert(long num) {
        if (num == 0) {
            return "零";
        }
        StringBuilder result = new StringBuilder();
        int level = 0; // 0:个级, 1:万级, 2:亿级
        while (num > 0) {
            long section = num % 10000; // 取最后4位
            if (section != 0) {
                // 处理当前级别的数字（如万级）
                String sectionStr = convertSection(section);
                result.insert(0, sectionStr + LEVELS[level]);
            }
            num = num / 10000;
            level++;
        }
        // 处理"一十"→"十"的情况
        return result.toString().replace("一十", "十");
    }

    // 处理4位以内的数字（如3521→三千五百二十一）
    private static String convertSection(long section) {
        StringBuilder sb = new StringBuilder();
        for (int i = 3; i >= 0; i--) { // 从千位到个位
            int digit = (int) (section / (int) Math.pow(10, i) % 10);
            if (digit == 0) {
                continue;
            } else {
                sb.append(NUMBERS[digit]).append(UNITS[i]);
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println(convert(1000223521)); // 输出：一十二万三千五百二十一
    }
}