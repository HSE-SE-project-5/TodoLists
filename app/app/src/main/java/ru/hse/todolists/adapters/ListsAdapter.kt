package ru.hse.todolists.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.hse.todolists.R
import ru.hse.todolists.data.entities.SimpleList
import ru.hse.todolists.databinding.ListItemBinding

class ListsAdapter : RecyclerView.Adapter<ListsAdapter.ListsViewHolder>() {

    class ListsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    var lists: List<SimpleList> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListsViewHolder {
        return ListsViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.list_item,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return lists.size
    }

    override fun onBindViewHolder(holder: ListsViewHolder, position: Int) {
        val binding = ListItemBinding.bind(holder.itemView)
        val list = lists[position]

        binding.apply {
            tvName.text = list.name
            tvStatus.text = list.status
        }
    }
}
