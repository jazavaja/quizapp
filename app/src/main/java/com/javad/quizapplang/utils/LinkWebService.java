package com.javad.quizapplang.utils;

import com.javad.quizapplang.App;

public class LinkWebService {
     private static String base=App.BASE_URL;
    public static String getSoalatTaiinSath=base+"/api/v1/placement";
    public static String getLesson=base+"/api/v1/lessons";
}
