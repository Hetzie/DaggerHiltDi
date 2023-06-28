package com.excellentwebworld.demodaggerhiltdi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.excellentwebworld.demodaggerhiltdi.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val  viewModel : KanyeWestViewModel by viewModels()
    private val kanyeWestViewModel : KanyeWestViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel.getQuotesViewModel()
        kanyeWestViewModel.getQuotesViewModel()


        lifecycleScope.launchWhenStarted {
            viewModel.conversion.collect { value: KanyeWestViewModel.QuotesEvent ->
                when(value){
                    is KanyeWestViewModel.QuotesEvent.Success ->{
                        binding.progressBar.visibility = View.GONE
                        binding.quote.text = value.resultText
                    }
                    is KanyeWestViewModel.QuotesEvent.Failure -> {
                        binding.progressBar.visibility =View.GONE
                        binding.quote.text = value.errorText
                    }
                    is KanyeWestViewModel.QuotesEvent.Loading -> {
                        binding.progressBar.visibility =View.VISIBLE
                    }
                    else -> Unit
                }
            }
            viewModel.conversion.collect{

            }
        }
    }
}