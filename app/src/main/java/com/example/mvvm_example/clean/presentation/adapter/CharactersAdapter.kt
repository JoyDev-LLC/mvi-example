package com.example.mvvm_example.clean.presentation.adapter

import android.annotation.SuppressLint
import android.graphics.PorterDuff
import android.graphics.PorterDuffColorFilter
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import androidx.viewbinding.ViewBinding
import com.bumptech.glide.Glide
import com.example.mvvm_example.R
import com.example.mvvm_example.clean.data.models.CharacterResults
import com.example.mvvm_example.clean.data.models.CharacterResultsItemType
import com.example.mvvm_example.clean.data.models.CharactersButtonItem
import com.example.mvvm_example.clean.data.models.BaseCharactersItem
import com.example.mvvm_example.databinding.CharactersItemBinding
import com.example.mvvm_example.databinding.LoadMoreItemBinding
import com.example.mvvm_example.clean.presentation.CharactersStatusEnum.Companion.getIconResByStatus
import com.example.mvvm_example.utils.PROGRESS_BAR_RADIUS
import com.example.mvvm_example.utils.PROGRESS_BAR_WIDTH
import com.example.mvvm_example.utils.loadImageFromUrl

class CharactersAdapter(
    private val loadMoreClick: () -> Unit
) : ListAdapter<BaseCharactersItem, CharactersAdapter.BaseCharactersViewHolder>(Differ()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseCharactersViewHolder {
        return when (viewType) {
            CharacterResultsItemType -> CharactersViewHolder(
                CharactersItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            )

            else -> CharactersButtonViewHolder(
                LoadMoreItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            )
        }
    }

    override fun getItemViewType(position: Int) = getItem(position).type

    override fun onBindViewHolder(holder: BaseCharactersViewHolder, position: Int) {
        val characterResults = getItem(position)
        holder.bind(characterResults)
    }

    inner class CharactersViewHolder(
        private val binding: CharactersItemBinding
    ) : BaseCharactersViewHolder(binding) {

        @SuppressLint("SetTextI18n")
        override fun bind(item: BaseCharactersItem) = with(binding) {
            val data = item as CharacterResults
            name.text = data.name
            avatarIv.loadImageFromUrl(data.image)
            status.text = "${data.status} – ${data.species}"
            status.setCompoundDrawablesWithIntrinsicBounds(
                data.status.getIconResByStatus() ?: 0, 0, 0, 0
            )
            statusDescription.text = data.location.name
        }
    }

    inner class CharactersButtonViewHolder(
        private val binding: LoadMoreItemBinding
    ) : BaseCharactersViewHolder(binding) {

        init {
            with(binding) {
                loadMoreBtn.setOnClickListener {
                    loadMoreClick.invoke()
                    loadMoreBtn.isClickable = false
                }
            }
        }

        override fun bind(item: BaseCharactersItem) {
            val buttonItem = item as CharactersButtonItem
            binding.loadMoreBtn.isClickable = buttonItem.isClickable
        }
    }

    abstract inner class BaseCharactersViewHolder(binding: ViewBinding) :
        RecyclerView.ViewHolder(binding.root) {
        abstract fun bind(item: BaseCharactersItem)
    }

    private class Differ : DiffUtil.ItemCallback<BaseCharactersItem>() {
        override fun areItemsTheSame(oldItem: BaseCharactersItem, newItem: BaseCharactersItem) =
            when {
                oldItem is CharacterResults && newItem is CharacterResults -> oldItem.id == newItem.id
                else -> false
            }

        @SuppressLint("DiffUtilEquals")
        override fun areContentsTheSame(oldItem: BaseCharactersItem, newItem: BaseCharactersItem) =
            when {
                oldItem is CharacterResults && newItem is CharacterResults -> oldItem == newItem
                else -> false
            }
    }
}
