package com.quebotic.qubeoticsamples.utils;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.quebotic.qubeoticsamples.adapter.CustomAdapterDrag;
import com.quebotic.qubeoticsamples.adapter.RecyclerViewAdapter;

public class ItemMoveCallback2 extends ItemTouchHelper.Callback {

    private final ItemTouchHelperContractDrag mAdapter;

    public ItemMoveCallback2(ItemTouchHelperContractDrag adapter) {
        mAdapter = adapter;
    }

    @Override
    public boolean isLongPressDragEnabled() {
        return false;
    }

    @Override
    public boolean isItemViewSwipeEnabled() {
        return false;
    }


    @Override
    public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int i) {

    }

    @Override
    public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        int dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN;
        return makeMovementFlags(dragFlags, 0);
    }

    @Override
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
        mAdapter.onRowMoved(viewHolder.getAdapterPosition(), target.getAdapterPosition());
        return true;
    }

    @Override
    public void onSelectedChanged(RecyclerView.ViewHolder viewHolder,
                                  int actionState) {


        if (actionState != ItemTouchHelper.ACTION_STATE_IDLE) {
            if (viewHolder instanceof RecyclerViewAdapter.MyViewHolder) {
                CustomAdapterDrag.ViewHolder myViewHolder =
                        (CustomAdapterDrag.ViewHolder) viewHolder;
                mAdapter.onRowSelected(myViewHolder);
            }

        }

        super.onSelectedChanged(viewHolder, actionState);
    }

    @Override
    public void clearView(RecyclerView recyclerView,
                          RecyclerView.ViewHolder viewHolder) {
        super.clearView(recyclerView, viewHolder);

        if (viewHolder instanceof RecyclerViewAdapter.MyViewHolder) {
            CustomAdapterDrag.ViewHolder myViewHolder =
                    (CustomAdapterDrag.ViewHolder) viewHolder;
            mAdapter.onRowClear(myViewHolder);
        }
    }

    public interface ItemTouchHelperContractDrag {

        void onRowMoved(int fromPosition, int toPosition);

        void onRowSelected(CustomAdapterDrag.ViewHolder myViewHolder);

        void onRowClear(CustomAdapterDrag.ViewHolder myViewHolder);

    }

}

