package sv.edu.api.ui.main

import android.os.Bundle
import android.view.View
import androidx.activity.ComponentActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import sv.udb.api.databinding.ActivityMainBinding
import sv.edu.api.repository.NewsRepository
import sv.edu.api.utils.Resource

class MainActivity : ComponentActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: NewsAdapter
    private val viewModel: NewsViewModel by lazy {
        NewsViewModel(NewsRepository())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Inicializar adapter
        adapter = NewsAdapter(mutableListOf())

        // Configurar RecyclerView
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = this@MainActivity.adapter
        }

        // Configurar swipe refresh
        binding.swipeRefresh.setOnRefreshListener {
            viewModel.fetchNews()
        }

        // Observar cambios del ViewModel
        observeViewModel()

        // Cargar noticias inicialmente
        viewModel.fetchNews()
    }

    private fun observeViewModel() {
        viewModel.news.observe(this) { resource ->
            when (resource) {
                is Resource.Loading -> {
                    binding.progressBar.visibility = View.VISIBLE
                    binding.recyclerView.visibility = View.GONE
                    binding.tvError.visibility = View.GONE
                }
                is Resource.Success -> {
                    binding.progressBar.visibility = View.GONE
                    binding.recyclerView.visibility = View.VISIBLE
                    binding.swipeRefresh.isRefreshing = false
                    binding.tvError.visibility = View.GONE
                    resource.data?.let { adapter.updateData(it.articles) }
                }
                is Resource.Error -> {
                    binding.progressBar.visibility = View.GONE
                    binding.recyclerView.visibility = View.GONE
                    binding.swipeRefresh.isRefreshing = false
                    binding.tvError.text = resource.message
                    binding.tvError.visibility = View.VISIBLE
                }
            }
        }
    }
}
