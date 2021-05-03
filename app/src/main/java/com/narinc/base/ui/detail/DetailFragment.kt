package com.narinc.base.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.narinc.base.R
import com.narinc.base.data.model.Item
import com.narinc.base.databinding.FragmentDetailBinding
import com.narinc.base.util.loadOrGone

class DetailFragment : Fragment() {
    private val args: DetailFragmentArgs by navArgs()
    private val item: Item by lazy { args.item }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentDetailBinding.inflate(inflater, container, false)
        binding.apply {
            toolbar.setNavigationOnClickListener { findNavController().navigateUp() }

            tvDetailTitle.loadOrGone(item.title)
            item.author?.let { tvDetailAuthor.loadOrGone(it) }
            tvDetailContent.loadOrGone(item.content)
            tvDetailDescription.loadOrGone(item.description)
            tvDetailSourceAndDate.text = getString(R.string.source_and_date, item.source.name, item.date)
            Glide.with(requireContext()).load(item.imgUrl).into(articleImage)
        }
        return binding.root
    }
}