package com.cliabhach.terrapin.red.shell.di

import com.cliabhach.terrapin.red.shell.details.MovieDetailsViewModel
import com.cliabhach.terrapin.red.shell.search.SearchListener
import com.cliabhach.terrapin.red.shell.search.SearchViewModel
import com.cliabhach.terrapin.red.shell.search.SimpleSearchListener
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

/**
 * @author Philip Cohn-Cort
 */
val shellModule = module {

    single<SearchListener> {
        SimpleSearchListener(api = get())
    }

    viewModel { SearchViewModel() }
    viewModel { MovieDetailsViewModel() }
}