package com.example.users.features.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.users.databinding.ItemUserBinding
import com.example.users.features.domain.model.User

class UserAdapter(
    private val listener: Listener
) : ListAdapter<User, UserAdapter.UserViewHolder>(UserDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val binding = ItemUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return UserViewHolder(binding, listener)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class UserViewHolder(
        private val binding: ItemUserBinding,
        private val listener: Listener
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(user: User) = with(binding) {
            userName.text = user.name
            userEmail.text = user.email
            userPhone.text = user.phone
            binding.root.setOnClickListener { listener.onUserClick(user)  }
        }
    }

    interface Listener {
        fun onUserClick(user: User)
    }
}
