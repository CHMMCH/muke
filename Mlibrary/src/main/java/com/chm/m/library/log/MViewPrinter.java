package com.chm.m.library.log;

import android.app.Activity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chm.m.library.R;

import java.util.ArrayList;
import java.util.List;

/**
 * @Desc : 可视打印器
 * @Author: chenhongmou
 * @Time: 2022/4/21 15:57
 */
public class MViewPrinter implements MLogPrinter{

    private RecyclerView recyclerView;
    private LogAdapter adapter;
    private MViewPrinterProvider viewProvider;


    public MViewPrinter(Activity activity){
        FrameLayout rootView = activity.findViewById(android.R.id.content);
        recyclerView = new RecyclerView(activity);
        adapter = new LogAdapter(LayoutInflater.from(recyclerView.getContext()));
        LinearLayoutManager layoutManager = new LinearLayoutManager(recyclerView.getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        viewProvider = new MViewPrinterProvider(rootView,recyclerView);
    }

    @NonNull
    public MViewPrinterProvider getViewProvider(){
        return viewProvider;
    }

    @Override
    public void print(@NonNull MLogConfig config, int level, String tag, @NonNull String printString) {
        //将log展示到recycleView
        adapter.addItem(new MLogMo(System.currentTimeMillis(),level,tag,printString));
        //滚动到指定位置
        recyclerView.smoothScrollToPosition(adapter.getItemCount() - 1);
    }

    private static class LogAdapter extends RecyclerView.Adapter<LogViewHolder>{
        private LayoutInflater inflater;
        private List<MLogMo> logs = new ArrayList<>();

        public LogAdapter(LayoutInflater inflater){
            this.inflater = inflater;
        }

        void addItem(MLogMo logItem){
            logs.add(logItem);
            notifyItemInserted(logs.size() - 1);
        }

        @NonNull
        @Override
        public LogViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View itemView = inflater.inflate(R.layout.mlog_item,parent,false);
            return new LogViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(@NonNull LogViewHolder holder, int position) {
            MLogMo logItem = logs.get(position);
            int color = getHighlightColor(logItem.level);
            holder.tagView.setTextColor(color);
            holder.messageView.setTextColor(color);
            holder.tagView.setText(logItem.getFlattened());
            holder.messageView.setText(logItem.log);
        }

        /**
         * 根据log等级获取不同高亮颜色
         * @param logLevel
         * @return
         */
        private int getHighlightColor(int logLevel){
            int highlight;
            switch (logLevel){
                case MLogType.V:
                    highlight = 0xffbbbbbb;
                    break;
                case MLogType.D:
                    highlight = 0xffffffff;
                    break;
                case MLogType.I:
                    highlight = 0xff6a8759;
                    break;
                case MLogType.W:
                    highlight = 0xffbbb529;
                    break;
                case MLogType.E:
                    highlight = 0xffff6b68;
                    break;
                default:
                    highlight = 0xffffff00;
                    break;
            }
            return highlight;
        }

        @Override
        public int getItemCount() {
            return logs.size();
        }
    }

    private static class LogViewHolder extends RecyclerView.ViewHolder{
        TextView tagView;
        TextView messageView;
        public LogViewHolder(@NonNull View itemView) {
            super(itemView);
            tagView = itemView.findViewById(R.id.tv_tag);
            messageView = itemView.findViewById(R.id.tv_message);

        }
    }
}
