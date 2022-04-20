package com.chm.m.library.log;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Desc : log管理类
 * @Author : chenhongmou
 * @Time : 2022/4/19 22:58
 */
public class MLogManager {

    private MLogConfig config;
    private static MLogManager instance;

    //保存打印器
    private List<MLogPrinter> printers = new ArrayList<>();

    private MLogManager(MLogConfig config,MLogPrinter[] printers){
        this.config = config;
        this.printers.addAll(Arrays.asList(printers));
    }

    public static MLogManager getInstance(){
        return instance;
    }

    public static void init(@NonNull MLogConfig config,MLogPrinter... printers){
        instance = new MLogManager(config,printers);
    }

    public MLogConfig getConfig(){
        return config;
    }

    public List<MLogPrinter> getPrinters(){
        return printers;
    }

    public void addPrinter(MLogPrinter printer){
        printers.add(printer);
    }

    public void removePrinter(MLogPrinter printer){
        if (printers != null){
            printers.remove(printer);
        }
    }

}
