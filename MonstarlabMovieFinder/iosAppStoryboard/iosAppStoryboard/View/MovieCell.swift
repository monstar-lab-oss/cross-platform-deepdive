import UIKit

/// Custom cell displaying a movie poster and its title.
class MovieCell: UICollectionViewCell {
    @IBOutlet weak var posterView: UIImageView!
    
    @IBOutlet weak var titleView: UILabel!
    
    func loadPoster(url: String) {
        posterView.loadImageFromURL(urlString: url)
    }
}

