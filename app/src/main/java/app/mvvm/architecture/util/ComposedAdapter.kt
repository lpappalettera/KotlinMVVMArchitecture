package app.mvvm.architecture.util

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView

class ComposedAdapter<ItemT : Any, VH : RecyclerView.ViewHolder>(
        private var items: List<ItemT> = emptyList(),
        private val viewHolderCreator: (ViewGroup, Int) -> VH,
        private val viewHolderBinder: (holder: VH, item: ItemT, position: Int) -> Unit,
        private val viewTypeFunction: ((ItemT) -> Int)? = null,
) : RecyclerView.Adapter<VH>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH = viewHolderCreator(parent, viewType)

    override fun onBindViewHolder(holder: VH, position: Int) = viewHolderBinder(holder, items[position], position)

    override fun getItemCount(): Int = items.size

    override fun getItemViewType(position: Int): Int =
            viewTypeFunction?.invoke(items[position]) ?: super.getItemViewType(position)

    fun update(updatedItems: List<ItemT>, itemIdFunction: (ItemT) -> Any) {
        val diffResult = DiffUtil.calculateDiff(DiffCallback(this.items, updatedItems, itemIdFunction))
        diffResult.dispatchUpdatesTo(this)
        items = updatedItems
    }

    private inner class DiffCallback(
            private val oldList: List<ItemT>,
            private val newList: List<ItemT>,
            private val itemIdFunction: (ItemT) -> Any
    ) : DiffUtil.Callback() {
        override fun getOldListSize() = oldList.size

        override fun getNewListSize() = newList.size

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return itemIdFunction(oldList[oldItemPosition]) == itemIdFunction(newList[newItemPosition])
        }

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldList[oldItemPosition] == newList[newItemPosition]
        }
    }

}