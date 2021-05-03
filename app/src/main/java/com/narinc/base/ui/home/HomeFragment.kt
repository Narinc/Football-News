package com.narinc.base.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.core.util.Pair
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.hilt.navigation.fragment.hiltNavGraphViewModels
import androidx.lifecycle.lifecycleScope
import androidx.paging.CombinedLoadStates
import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadState
import com.google.android.material.datepicker.CalendarConstraints
import com.google.android.material.datepicker.DateValidatorPointBackward
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.narinc.base.R
import com.narinc.base.databinding.FragmentHomeBinding
import com.narinc.base.ui.SpaceItemDecoration
import com.narinc.base.util.EventObserver
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import java.util.*

@AndroidEntryPoint
@ExperimentalPagingApi
class HomeFragment : Fragment() {

    private val vm: HomeViewModel by hiltNavGraphViewModels(R.id.navigation_graph)
    private var job: Job? = null
    private lateinit var adapter: ArticleAdapter
    private var _binding: FragmentHomeBinding? = null
    private val binding
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        initAdapter()
        getNewsAndNotifyAdapter()
        showFilterPopupMenu()

        observeData()

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun observeData() {
        vm.apply {
            showMessageEvent.observe(viewLifecycleOwner, EventObserver { message ->
                showMessage(message)
            })
        }
    }

    private fun initAdapter() {
        binding.articleList.addItemDecoration(SpaceItemDecoration.newInstance(requireContext(), 8))
        adapter = ArticleAdapter()
        binding.articleList.adapter = adapter
        adapter.addLoadStateListener { loadState ->
            binding.articleList.isVisible = loadState.refresh is LoadState.NotLoading
            binding.progress.isVisible = loadState.refresh is LoadState.Loading
            manageErrors(loadState)
        }
    }

    private fun manageErrors(loadState: CombinedLoadStates) {
        binding.errorText.isVisible = loadState.refresh is LoadState.Error
        binding.retryButton.isVisible = loadState.refresh is LoadState.Error
        binding.retryButton.setOnClickListener { adapter.retry() }

        val errorState = loadState.source.append as? LoadState.Error
            ?: loadState.source.prepend as? LoadState.Error
            ?: loadState.append as? LoadState.Error
            ?: loadState.prepend as? LoadState.Error

        errorState?.let {
            val errorText = resources.getString(R.string.generic_error) + it.error.toString()
            binding.errorText.text = errorText
        }
    }

    private fun getNewsAndNotifyAdapter() {
        job?.cancel()
        lifecycleScope.launch {
            vm.loadNews().collectLatest { adapter.submitData(it) }
        }
    }

    private fun showFilterPopupMenu() {
        binding.homeToolbar.setOnMenuItemClickListener {
            if (it.itemId == R.id.filter_option) {
                displayDatePicker()
            }
            true
        }
    }

    private fun displayDatePicker() {
        val builder = MaterialDatePicker.Builder.dateRangePicker()
        builder.setTheme(R.style.ThemeOverlay_MaterialComponents_MaterialCalendar)

        val constraintsBuilder = CalendarConstraints.Builder()
        constraintsBuilder.setValidator(DateValidatorPointBackward.now())
        builder.setCalendarConstraints(constraintsBuilder.build())

        builder.setTitleText(getString(R.string.date_picker_title))
        builder.setSelection(vm.dates)

        val picker = builder.build()
        picker.addOnPositiveButtonClickListener { selection: Pair<Long, Long>? ->
            run {
                vm.dates = selection
                getNewsAndNotifyAdapter()
            }
        }
        picker.show(parentFragmentManager, picker.toString())
    }

    private fun showMessage(message: Int) {
        MaterialAlertDialogBuilder(requireContext())
            .setTitle(android.R.string.dialog_alert_title)
            .setMessage(message)
            .setPositiveButton(android.R.string.ok) { dialog, which -> dialog.dismiss() }
            .show()
    }
}