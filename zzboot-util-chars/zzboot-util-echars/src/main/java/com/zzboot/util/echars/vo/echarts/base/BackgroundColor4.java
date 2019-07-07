package com.zzboot.util.echars.vo.echarts.base;

import java.io.Serializable;

public class BackgroundColor4  implements Serializable {


    private String type = "pattern";
    private String image = "canvas";
    private String repeat = "repeat";

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getRepeat() {
        return repeat;
    }

    public void setRepeat(String repeat) {
        this.repeat = repeat;
    }
}
