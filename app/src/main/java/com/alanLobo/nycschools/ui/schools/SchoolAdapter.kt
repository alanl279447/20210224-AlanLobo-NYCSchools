package com.example.nycschools.ui.schools

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import com.alanLobo.nycschools.databinding.ItemSchoolBinding
import com.example.nycschools.data.entities.SchoolListItem

class SchoolAdapter(private val listener: CharacterItemListener) : RecyclerView.Adapter<SchoolAdapter.CharacterViewHolder>(),
    Filterable {

    interface CharacterItemListener {
        fun onClickedCharacter(dbn: String, item: SchoolListItem)
        fun onPhoneButtonClicked(mdn: String?)
        fun onNavigate(lat: String?, long: String?)
    }

    interface FilterListener {
        fun refreshList(items: List<SchoolListItem>?)
    }

    val items = ArrayList<SchoolListItem>()
    private var valueFilter: ValueFilter? = null

    fun setItems(items: List<SchoolListItem>?) {
          if (items!= null) {
            this.items.clear()
            this.items.addAll(items)
            notifyDataSetChanged()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        val binding: ItemSchoolBinding =
            ItemSchoolBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CharacterViewHolder(binding, listener)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) =
        holder.bind(items[position])

    override fun getFilter(): Filter {
        if (valueFilter == null) {
            valueFilter = ValueFilter(items, object : FilterListener {
                override fun refreshList(items: List<SchoolListItem>?) {
                    setItems(items!!);
                }
            })
        }
        items?.let {
            valueFilter?.setItems(items)
        }
        return valueFilter as ValueFilter
    }

    class ValueFilter(item: List<SchoolListItem>, list: SchoolAdapter.FilterListener) : Filter() {

        var originalList: ArrayList<SchoolListItem>? = ArrayList()
        var itemList: ArrayList<SchoolListItem>? = null
        var listener: SchoolAdapter.FilterListener = list

        init {
            originalList!!.addAll(item)
        }

        fun setItems(items: ArrayList<SchoolListItem>) {
            itemList = items
        }

        override fun performFiltering(constraint: CharSequence): FilterResults {
            val results = FilterResults()
            val filteredList = ArrayList<SchoolListItem>()
            if (!constraint.isNullOrBlank()) {
                for (i in itemList!!.indices) {
                    if (itemList!!.get(i).school_name!!.toUpperCase().contains(constraint.toString().toUpperCase())) {
                        filteredList.add(itemList!!.get(i))
                    }
                }
                results.count = filteredList.size
                results.values = filteredList
            } else {
                results.count = originalList!!.size
                results.values = originalList
            }
            return results
        }

        override fun publishResults(constraint: CharSequence, results: FilterResults) {
            listener.refreshList(if (results.values != null) results.values as List<SchoolListItem> else null)
        }
    }

    class CharacterViewHolder(
        private val itemBinding: ItemSchoolBinding,
        private val listener: SchoolAdapter.CharacterItemListener
    ) : RecyclerView.ViewHolder(itemBinding.root),
        View.OnClickListener {

        private lateinit var schoolItem: SchoolListItem

        init {
            itemBinding.root.setOnClickListener(this)
        }

        @SuppressLint("SetTextI18n")
        fun bind(item: SchoolListItem) {
            this.schoolItem = item
            itemBinding.name.text = item.school_name
            itemBinding.address.text = item.location!!.substring(0, item.location!!.indexOf("("))
            itemBinding.phone.text = item.phone_number
            itemBinding.website.text = item.website
            itemBinding.phone.setOnClickListener({
                listener.onPhoneButtonClicked(item.phone_number)
            })
            itemBinding.navigate.text = "Navigate to adddress"
            itemBinding.navigate.setOnClickListener({
                listener.onNavigate(item.latitude, item.longitude)
            })
        }

        override fun onClick(v: View?) {
            listener.onClickedCharacter(schoolItem.dbn, schoolItem)
        }
    }
}