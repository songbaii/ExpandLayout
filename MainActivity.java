package org.songbai.expand.view.demo;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<String> mStrings = new ArrayList<>();

    private ExpandLayout mExpandLayout;
    private RecyclerView mRecyclerView;
    private TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mExpandLayout = findViewById(R.id.expand);
        mRecyclerView = findViewById(R.id.rv);
        mTextView = findViewById(R.id.set_expand_tv);

        mExpandLayout.setAnimationDuration(300);
        mExpandLayout.initExpand(false, 150);

        mRecyclerView.setLayoutManager(new GridLayoutManager(this, 4));

        Adapter adapter = new Adapter();
        mRecyclerView.setAdapter(adapter);
        setData();
        adapter.notifyDataSetChanged();

        mTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mExpandLayout.toggleExpand();
                mTextView.setText(mExpandLayout.isExpand() ? "折叠" : "展开");
            }
        });
    }

    private void setData() {
        for (int i = 0; i < 20; i++) {
            mStrings.add(String.format("%s%s%s", i, "-", "item"));
        }
    }

    class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            holder.itemTv.setText(mStrings.get(position));
        }

        @Override
        public int getItemCount() {
            return mStrings == null ? 0 : mStrings.size();
        }

        class ViewHolder extends RecyclerView.ViewHolder {

            private TextView itemTv;

            public ViewHolder(@NonNull View itemView) {
                super(itemView);
                itemTv = itemView.findViewById(R.id.item_tv);
            }
        }
    }
}