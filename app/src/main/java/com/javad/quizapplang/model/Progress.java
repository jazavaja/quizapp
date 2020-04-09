package com.javad.quizapplang.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Progress {

@SerializedName("section1")
@Expose
private String section1;
@SerializedName("section2")
@Expose
private String section2;
@SerializedName("section3")
@Expose
private String section3;
@SerializedName("section4")
@Expose
private String section4;
@SerializedName("section5")
@Expose
private String section5;
@SerializedName("section6")
@Expose
private String section6;
@SerializedName("section7")
@Expose
private String section7;

public String getSection1() {
return section1;
}

public void setSection1(String section1) {
this.section1 = section1;
}

public String getSection2() {
return section2;
}

public void setSection2(String section2) {
this.section2 = section2;
}

public String getSection3() {
return section3;
}

public void setSection3(String section3) {
this.section3 = section3;
}

public String getSection4() {
return section4;
}

public void setSection4(String section4) {
this.section4 = section4;
}

public String getSection5() {
return section5;
}

public void setSection5(String section5) {
this.section5 = section5;
}

public String getSection6() {
return section6;
}

public void setSection6(String section6) {
this.section6 = section6;
}

public String getSection7() {
return section7;
}

public void setSection7(String section7) {
this.section7 = section7;
}

}