//
//  MainViewModel.swift
//  iosAppStoryboard
//
//  Created by juan sebastian gomez on 24/01/22.
//

import Foundation
import shared

class MainViewModel : NSObject, PopularMoviesView {
    
    private lazy var presenter = ServiceLocator.init().moviesPresenter
    private(set) var movies : [KmmMovie]! {
        didSet {
            self.bindMainViewModelToController()
        }
    }
    
    var bindMainViewModelToController : (() -> ()) = {}
    
    override init() {
        super.init()
    }
    
    
    func startPopularMovieSearch(){
        self.presenter.attachView(view: self)
    }
    
    func detach(){
        self.presenter.detachView()
    }
    
    //
    func setLoadingVisible(visible: Bool) {
    }
    
    func setPopularMovies(movies: [KmmMovie]) {
        print("setPopularMovies =  \(movies.count)")
        self.movies = movies
    }
    
    func showMoviesFailedToLoad() {
        print("showMoviesFailedToLoad")
    }
    
    
}
