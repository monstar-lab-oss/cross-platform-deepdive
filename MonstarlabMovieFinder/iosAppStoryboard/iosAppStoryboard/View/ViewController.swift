//
//  ViewController.swift
//  iosAppStoryboard
//
//  Created by juan sebastian gomez on 18/01/22.
//

import UIKit
import shared


class ViewController: UICollectionViewController {
    
 //   private lazy var presenter = ServiceLocator.init().moviesPresenter
    
    var movies = [KmmMovie]()
    let moviesPerRow = 2
    /// Insets for each movie cell
    let sectionInsets = UIEdgeInsets(top: 30.0,
                                     left: 20.0,
                                     bottom: 0.0,
                                     right: 20.0)
    
    private var mainViewModel : MainViewModel!
    
    
    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view.
        callToViewModelForUIUpdate()
    }
    
    func callToViewModelForUIUpdate(){
        self.mainViewModel =  MainViewModel()
        self.mainViewModel.bindMainViewModelToController = {
            self.updateDataSource()
        }
    }
    
    func updateDataSource(){
        self.movies = self.mainViewModel.movies
        collectionView?.reloadData()
    }
    
    override func viewWillAppear(_ animated: Bool) {
        super.viewWillAppear(animated)
        mainViewModel.startPopularMovieSearch()
    }
    
    override func viewWillDisappear(_ animated: Bool) {
        super.viewWillDisappear(animated)
        mainViewModel.detach()
    }
    

    
}


