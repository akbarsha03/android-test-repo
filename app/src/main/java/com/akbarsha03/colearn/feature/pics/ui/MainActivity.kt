package com.akbarsha03.colearn.feature.pics.ui

import android.os.Bundle
import android.view.View
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatEditText
import androidx.appcompat.widget.AppCompatRadioButton
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.paging.CombinedLoadStates
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.akbarsha03.colearn.R
import com.akbarsha03.colearn.databinding.ActivityMainBinding
import com.akbarsha03.colearn.feature.pics.models.response.SearchResponse
import com.akbarsha03.colearn.feature.pics.ui.adapters.OnItemSelectedListener
import com.akbarsha03.colearn.feature.pics.ui.adapters.PicsRecyclerAdapter
import com.akbarsha03.colearn.feature.pics.ui.adapters.SearchRecyclerAdapter
import com.akbarsha03.colearn.feature.pics.ui.adapters.state.PicsLoadStateAdapter
import com.akbarsha03.colearn.feature.pics.ui.viewmodels.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


@AndroidEntryPoint
class MainActivity : AppCompatActivity(), OnItemSelectedListener {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels()
    private val picsRecyclerAdapter: PicsRecyclerAdapter = PicsRecyclerAdapter(this)
    private val searchRecyclerAdapter: SearchRecyclerAdapter = SearchRecyclerAdapter(this)

    companion object {
        private const val LAST_SEARCH_QUERY: String = "last_search_query"
        const val DEFAULT_ID = "2423569" // Star wars default id
    }

    override fun onItemSelected(results: SearchResponse.SearchResult) {
        DetailActivity.startActivity(this, results)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString(LAST_SEARCH_QUERY, binding.searchRepo.text.trim().toString())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupViews()
        val query = savedInstanceState?.getString(LAST_SEARCH_QUERY) ?: DEFAULT_ID
        search(query)
    }

    private fun setupViews() {

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val gridLayoutManager = StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL)
        binding.recyclerView.apply {
            layoutManager = gridLayoutManager
            setPicsAdapter()
        }

        binding.searchButton.setOnClickListener { showSearchDialog() }
    }

    private var loadPicsJob: Job? = null
    private var searchPicsJob: Job? = null

    private fun search(query: String) {
        loadPicsJob?.cancel()
        loadPicsJob = lifecycleScope.launch {
            viewModel.getPhotos(query).collectLatest {
                picsRecyclerAdapter.submitData(it)
            }
        }
    }

    private fun showEmptyList(show: Boolean) {
        if (show) {
            binding.emptyList.visibility = View.VISIBLE
            binding.recyclerView.visibility = View.GONE
        } else {
            binding.emptyList.visibility = View.GONE
            binding.recyclerView.visibility = View.VISIBLE
        }
    }

    private fun setPicsAdapter() {

        binding.retryButton.setOnClickListener { picsRecyclerAdapter.retry() }

        binding.recyclerView.adapter = picsRecyclerAdapter.withLoadStateHeaderAndFooter(
            header = PicsLoadStateAdapter { picsRecyclerAdapter.retry() },
            footer = PicsLoadStateAdapter { picsRecyclerAdapter.retry() }
        )

        picsRecyclerAdapter.addLoadStateListener { loadState ->
            val isListEmpty =
                loadState.refresh is LoadState.NotLoading && picsRecyclerAdapter.itemCount == 0
            showEmptyList(isListEmpty)
            viewLoadState(loadState)
        }
    }

    private fun viewLoadState(loadState: CombinedLoadStates) {
        // Only show the list if refresh succeeds.
        binding.recyclerView.isVisible = loadState.source.refresh is LoadState.NotLoading
        // Show loading spinner during initial load or refresh.
        binding.progressBar.isVisible = loadState.source.refresh is LoadState.Loading
        // Show the retry state if initial load or refresh fails.
        binding.retryButton.isVisible = loadState.source.refresh is LoadState.Error

        // Toast on any error, regardless of whether it came from RemoteMediator or PagingSource
        val errorState = loadState.source.append as? LoadState.Error
            ?: loadState.source.prepend as? LoadState.Error
            ?: loadState.append as? LoadState.Error
            ?: loadState.prepend as? LoadState.Error
        errorState?.let {
            Toast.makeText(
                this,
                "Something went wrong ${it.error}",
                Toast.LENGTH_LONG
            ).show()
        }
    }

    private fun setSearchAdapter() {

        binding.retryButton.setOnClickListener { searchRecyclerAdapter.retry() }

        binding.recyclerView.adapter = searchRecyclerAdapter.withLoadStateHeaderAndFooter(
            header = PicsLoadStateAdapter { searchRecyclerAdapter.retry() },
            footer = PicsLoadStateAdapter { searchRecyclerAdapter.retry() }
        )

        searchRecyclerAdapter.addLoadStateListener { loadState ->
            val isListEmpty =
                loadState.refresh is LoadState.NotLoading && searchRecyclerAdapter.itemCount == 0
            showEmptyList(isListEmpty)
            viewLoadState(loadState)
        }

    }

    private fun showSearchDialog() {

        val builder = AlertDialog.Builder(this)
        builder.setTitle(getString(R.string.advanced_options))

        val layout: View = layoutInflater.inflate(R.layout.custom_layout, null)

        builder.setView(layout)

        builder.setPositiveButton(getString(R.string.search)) { _, _ -> // send data from the
            // AlertDialog to the Activity
            val editText = layout.findViewById<AppCompatEditText>(R.id.editText)

            val colorText = getRadioButtonText(layout.findViewById(R.id.colorGroup))
            val sortText = getRadioButtonText(layout.findViewById(R.id.sortGroup))

            val query = editText.text.toString()

            val data = QueryData(
                query = query,
                colorFilter = colorText.toString(),
                sortBy = sortText.toString()
            )

            binding.searchRepo.setText(query, TextView.BufferType.EDITABLE)

            processData(data)
        }

        builder.setNegativeButton(getString(R.string.cancel)) { dialog, _ ->
            dialog.dismiss()
        }

        val dialog: AlertDialog = builder.create()
        dialog.show()
    }

    private fun getRadioButtonText(radioGroup: RadioGroup) =
        radioGroup.findViewById<AppCompatRadioButton>(radioGroup.checkedRadioButtonId)?.text

    private fun processData(query: QueryData) {

        searchPicsJob?.cancel()
        setSearchAdapter()

        searchPicsJob = lifecycleScope.launch {
            viewModel.searchPhotos(query).collectLatest {
                searchRecyclerAdapter.submitData(it)
            }
        }
    }
}