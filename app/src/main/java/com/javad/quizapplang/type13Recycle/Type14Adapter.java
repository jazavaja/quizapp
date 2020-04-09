package com.javad.quizapplang.type13Recycle;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.javad.quizapplang.R;

import java.util.Collections;
import java.util.List;

/**
 * Created by Srijith on 08-10-2017.
 */

public class Type14Adapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements
        com.javad.quizapplang.type13Recycle.SwipeAndDragHelper.ActionCompletionContract {
    private static final int USER_TYPE = 1;
    private static final int HEADER_TYPE = 2;
    private List<String> usersList;
    private ItemTouchHelper touchHelper;
    public static String implode = "";

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;

        view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_type14, parent, false);
        return new UserViewHolder(view);

    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {

        ((UserViewHolder) holder).username.setText(usersList.get(position));
        ((UserViewHolder) holder).reorderView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getActionMasked() == MotionEvent.ACTION_DOWN) {
                    touchHelper.startDrag(holder);
                }
                return false;
            }
            });

    }

    @Override
    public int getItemCount() {
        return usersList == null ? 0 : usersList.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (TextUtils.isEmpty(usersList.get(position))) {
            return HEADER_TYPE;
        } else {
            return USER_TYPE;
        }
    }

    public void setUserList(int state ,List<String> usersList) {

        if (state == 1) {


        }else {

            Collections.shuffle(usersList);
        }

        this.usersList = usersList;
        implode = "";
        for (int i = 0; i < usersList.size(); i++) {
            Log.e("orginal list = " + i, usersList.get(i));
            implode += usersList.get(i);
        }

        notifyDataSetChanged();
    }

    @Override
    public void onViewMoved(int oldPosition, int newPosition) {
        String targetUser = usersList.get(oldPosition);
        usersList.remove(oldPosition);
        usersList.add(newPosition, targetUser);
        notifyItemMoved(oldPosition, newPosition);
        implode = "";
        Log.e("implode = ", implode);
        for (int i = 0; i < usersList.size(); i++) {
            implode += usersList.get(i);
            Log.e("change list = "+i , usersList.get(i));
            Log.e("implode moved = "+i , implode);
        }
    }

    @Override
    public void onViewSwiped(int position) {
        usersList.remove(position);
        notifyItemRemoved(position);
    }

    public void setTouchHelper(ItemTouchHelper touchHelper) {

        this.touchHelper = touchHelper;
    }


    public class UserViewHolder extends RecyclerView.ViewHolder {

        ImageView userAvatar;
        TextView username;
        ImageView reorderView;

        public UserViewHolder(View itemView) {
            super(itemView);

            userAvatar = itemView.findViewById(R.id.imageview_profile);
            username = itemView.findViewById(R.id.textview_name);
            reorderView = itemView.findViewById(R.id.imageview_reorder);
        }
    }

}
