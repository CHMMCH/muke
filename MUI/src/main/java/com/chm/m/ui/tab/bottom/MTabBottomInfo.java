package com.chm.m.ui.tab.bottom;

import android.graphics.Bitmap;

import androidx.fragment.app.Fragment;

/**
 * @Desc : MTabBottom 实体类
 * @Author: chenhongmou
 * @Time: 2022/4/25 16:31
 */
public class MTabBottomInfo<Color> {

    public enum TabType{
        BITMAP,ICON
    }

    public Class<? extends Fragment> fragment;
    public String name;
    public Bitmap defaultBitmap;
    public Bitmap selectedBitmap;
    public String iconFont;
    /**
     *  Tips:在java代码中直接设置iconfont字符串无效，需要定义在string.xml
     */
    public String defaultIconName;
    public String selectedIconName;
    public Color defaultColor;
    public Color tintColor;
    public TabType tabType;

    public MTabBottomInfo(String name,Bitmap defaultBitmap,Bitmap selectedBitmap){
        this.name = name;
        this.defaultBitmap = defaultBitmap;
        this.selectedBitmap = selectedBitmap;
        this.tabType = TabType.BITMAP;
    }

    public MTabBottomInfo(String name,String iconFont, String defaultIconName, String selectedIconName, Color defaultColor, Color tintColor){
        this.name = name;
        this.iconFont = iconFont;
        this.defaultIconName = defaultIconName;
        this.selectedIconName = selectedIconName;
        this.defaultColor = defaultColor;
        this.tintColor = tintColor;
        this.tabType = TabType.ICON;
    }

}
