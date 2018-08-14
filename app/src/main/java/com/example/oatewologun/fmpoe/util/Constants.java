package com.example.oatewologun.fmpoe.util;

public class Constants {
    public static final String gLvl = "Ground Level";
    public static final String mLvl = "Mezzanine Level";
    public static final String lvl1 = "Level 1";
    public static final String lvl2 = "Level 2";
    public static final String lvl3 = "Level 3";
    public static final String lvl4 = "Level 4";
    public static final String lvl5 = "Level 5";
    public static final String lvl6 = "Level 6";

    public static String[] getLevel6_Rooms(){
        return new String[]{"6006", "6007", "6006(Toilet)", "Lift 1", "Lift 2", "6004"};
    }
    public static String[] getLevel3_Rooms(){
        return new String[]{"3001", "3034(Lab)", "3035(Lab)", "3036(Lab)", "3037", "3082", "3044", "3033(Male Toilet)", "3038(Female Toilet)"};
    }
    public static String[] getLevel2_Rooms(){
        return new String[]{"2031(Male)", "2036(Female)", "2032(Lab)", "2033(Lab)", "2034(Lab)", "2035(Lab)", "2011", "2016", "2007"};
    }
    public static String[] getLevel4_Rooms(){
        return new String[]{"4033(Male)", "4038(Female)", "4042-4045(Combine)"};
    }
    public static String[] getPOEOptions(){
        return new String[]{"Ambient Requirement", "Spatial Requirement", "Technology Requirement", "Building Support and Services"};
    }
}
