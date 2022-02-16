//
//  ViewExtensions.swift
//  iosAppStoryboard
//
//  Created by juan sebastian gomez on 25/01/22.
//

import Foundation
import UIKit
import shared


// MARK: - UICollectionViewDataSource
extension ViewController {
    
    override func numberOfSections(in collectionView: UICollectionView) -> Int {
        return movies.count / moviesPerRow
    }
    
    override func collectionView(_ collectionView: UICollectionView,
                                 numberOfItemsInSection section: Int) -> Int {
        return moviesPerRow
    }
    
    override func collectionView(_ collectionView: UICollectionView,
                                 didSelectItemAt indexPath: IndexPath){
        self.performSegue(withIdentifier: "detailView", sender: self)
    }
    
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        if let indexPath = collectionView?.indexPathsForSelectedItems?.first, let destination = segue.destination as? DetailViewController {
            destination.object = movies[indexPath.item]
        }
    }
    
    override func collectionView(
        _ collectionView: UICollectionView,
        cellForItemAt indexPath: IndexPath
    ) -> UICollectionViewCell {
        let cell = collectionView.dequeueReusableCell(withReuseIdentifier: "MovieCell", for: indexPath) as! MovieCell
       
        
        let index = (indexPath.section * moviesPerRow) + indexPath.row
        if (index < movies.count) {
            let movie = movies[index]
            cell.titleView.text = movie.title
            cell.loadPoster(url: movie.completePosterPath)
        }
        
        return cell
    }
}

// MARK: - Collection View Flow Layout Delegate
extension ViewController : UICollectionViewDelegateFlowLayout {
    
    func collectionView(_ collectionView: UICollectionView,
                        layout collectionViewLayout: UICollectionViewLayout,
                        sizeForItemAt indexPath: IndexPath) -> CGSize {
        let padding = Int(sectionInsets.left) * (moviesPerRow + 1)
        let availableWidth = Int(view.frame.width) - padding
        let widthPerItem = availableWidth / moviesPerRow
        
        return CGSize(width: widthPerItem, height: 250)
    }
    
    func collectionView(_ collectionView: UICollectionView,
                        layout collectionViewLayout: UICollectionViewLayout,
                        insetForSectionAt section: Int) -> UIEdgeInsets {
        return sectionInsets
    }
    
    func collectionView(_ collectionView: UICollectionView,
                        layout collectionViewLayout: UICollectionViewLayout,
                        minimumLineSpacingForSectionAt section: Int) -> CGFloat {
        return sectionInsets.left
    }
}

// MARK
extension UIImageView {
    
    /// Function taken from https://stackoverflow.com/a/37019507/1477936 to load an image from a URL.
    func loadImageFromURL(urlString: String) {
        self.image = nil
        URLSession.shared.dataTask(with: NSURL(string: urlString)! as URL, completionHandler: { (data, response, error) -> Void in
            
            if (error != nil) {
                print(error!)
                return
            }
            
            DispatchQueue.main.async(execute: { () -> Void in
                let image = UIImage(data: data!)
                self.image = image
            })
            
        }).resume()
    }}
