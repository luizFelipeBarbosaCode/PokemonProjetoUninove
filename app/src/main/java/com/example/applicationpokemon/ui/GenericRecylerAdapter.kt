package com.example.applicationpokemon.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.applicationpokemon.R

//class GenericRecylerAdapter<T>(
//    private val diffCallBack: DiffUtil.ItemCallback<T>
//)
//    : RecyclerView.Adapter<BaseCell>() {
//
//    class AdapterHolderType(var resId:Int, var clazz: Class<out BaseCell>, var reuseIdentifier:Int)
//    interface GenericRecylerAdapterDelegate {
//        fun cellForPosition(adapter: GenericRecylerAdapter<*>, cell:RecyclerView.ViewHolder, position:Int)
//        fun cellType(adapter: GenericRecylerAdapter<*>, position: Int):AdapterHolderType?  {return null}
//        fun didSelectItemAt(adapter:GenericRecylerAdapter<*>, index:Int) {}
//        fun numberOfRows(adapter:GenericRecylerAdapter<*>):Int? { return null}
//    }
//
//var delegate:GenericRecylerAdapterDelegate? = null
//
//private var cellTypes = arrayListOf<AdapterHolderType>()
//
//val differ = AsyncListDiffer(this, diffCallBack)
//
//fun submitList(list: List<T>) = differ.submitList(list)
//
//override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseCell {
//    cellTypes.find {
//        it.reuseIdentifier == viewType
//    }?.let {model ->
//        return model.clazz.getDeclaredConstructor(View::class.java)
//            .newInstance(
//                LayoutInflater.from(parent.context)
//                    .inflate(model.resId, parent, false))
//    }
//
//    return BaseCell(LayoutInflater.from(parent.context).inflate(R.layout.item_run, parent, false))
//}
//
//override fun getItemCount(): Int =
//    delegate?.numberOfRows(this) ?: differ.currentList.size
//
//override fun getItemViewType(position: Int): Int {
//    delegate?.let { delegate ->
//        delegate.cellType(this, position)?.let {type ->
//            cellTypes.add(type)
//            return type.reuseIdentifier
//        }
//    }
//    return super.getItemViewType(position)
//}
//
//override fun onBindViewHolder(cell: BaseCell, position: Int) {
//    cell.prepareForReuse()
//    delegate?.cellForPosition(adapter = this, cell = cell, position = position)
//
//    cell.onClick = {index ->
//        delegate?.didSelectItemAt(adapter = this, index = index)
//    }
//}
//
//}

class GenericRecylerAdapter <T>: RecyclerView.Adapter<BaseCell>() {

    class AdapterHolderType(var resId:Int, var clazz: Class<out BaseCell>, var reuseIdentifier:Int)
    interface GenericRecylerAdapterDelegate {
        fun cellForPosition(adapter: GenericRecylerAdapter<*>, cell:RecyclerView.ViewHolder, position:Int)
        fun cellType(adapter: GenericRecylerAdapter<*>, position: Int):AdapterHolderType?  {return null}
        fun didSelectItemAt(adapter:GenericRecylerAdapter<*>, index:Int) {}
        fun numberOfRows(adapter:GenericRecylerAdapter<*>):Int? { return null}
    }

    var items:MutableList<T> = ArrayList()
        set(value) {
            field = value

            if(delegate != null){
                this.notifyDataSetChanged()
            }
        }

    private var _selectedItem = RecyclerView.NO_POSITION
    val selectedItem:Int
        get() {
            return _selectedItem
        }

    var delegate:GenericRecylerAdapterDelegate? = null

    private var cellTypes = arrayListOf<AdapterHolderType>()

    @Suppress("UNCHECKED_CAST")
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseCell {

        cellTypes.find {
            it.reuseIdentifier == viewType
        }?.let {model ->
            return model.clazz.getDeclaredConstructor(View::class.java).newInstance(LayoutInflater.from(parent.context).inflate(model.resId, parent, false))
        }

        return BaseCell(LayoutInflater.from(parent.context).inflate(R.layout.item_run, parent))
    }

    override fun onBindViewHolder(cell: BaseCell, position: Int) {
        cell.prepareForReuse()
        cell.setSelection(selectedItem == position)
        delegate?.cellForPosition(adapter = this, cell = cell, position = position)

        cell.onClick = {index ->
            delegate?.didSelectItemAt(adapter = this, index = index)
        }
    }

    override fun getItemCount(): Int {
        return delegate?.numberOfRows(this) ?: items.size
    }

    override fun getItemViewType(position: Int): Int {
        delegate?.let {delegate ->
            delegate.cellType(this, position)?.let {type ->
                cellTypes.add(type)
                return type.reuseIdentifier
            }
        }
        return super.getItemViewType(position)
    }

    fun selectItemAt(index: Int) {
        notifyItemChanged(selectedItem)
        _selectedItem = index
        notifyItemChanged(selectedItem)
    }
}